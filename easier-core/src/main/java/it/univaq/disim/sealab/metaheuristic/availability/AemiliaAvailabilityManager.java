package it.univaq.disim.sealab.metaheuristic.availability;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import it.univaq.disim.sealab.aemiliaMod2text.main.Transformation;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.ElemType;
import metamodel.mmaemilia.InteractionType;
import metamodel.mmaemilia.LocalInteraction;
import metamodel.mmaemilia.OutputInteraction;
import metamodel.mmaemilia.mmaemiliaFactory;
import metamodel.mmaemilia.Behavior.Action;
import metamodel.mmaemilia.Behavior.ActionOutput;
import metamodel.mmaemilia.Behavior.ActionProcess;
import metamodel.mmaemilia.Behavior.BehavEquation;
import metamodel.mmaemilia.Behavior.BehavProcess;
import metamodel.mmaemilia.Behavior.BehaviorFactory;
import metamodel.mmaemilia.Behavior.ChoiceProcess;
import metamodel.mmaemilia.Behavior.ProcessTerm;
import metamodel.mmaemilia.Behavior.RateExp;
import metamodel.mmaemilia.Behavior.RateInf;
import metamodel.mmaemilia.DataType.DataType;
import metamodel.mmaemilia.DataType.DataTypeFactory;
import metamodel.mmaemilia.DataType.Special;
import metamodel.mmaemilia.DataType.SpecialType;
import metamodel.mmaemilia.Expressions.ExpressionsFactory;
import metamodel.mmaemilia.Expressions.IdentExpr;
import metamodel.mmaemilia.Expressions.IdentifierType;
import metamodel.mmaemilia.Headers.Const;
import metamodel.mmaemilia.Headers.ConstInit;
import metamodel.mmaemilia.Headers.HeadersFactory;

public class AemiliaAvailabilityManager {

	private BehaviorFactory behaviourFactory = BehaviorFactory.eINSTANCE;
	private ExpressionsFactory expressionFactory = ExpressionsFactory.eINSTANCE;
	private mmaemiliaFactory aemiliaMMFactory = mmaemiliaFactory.eINSTANCE;

	// private AEmiliaSpecification targetModel;

	private Controller controller;
	private Manager manager;
	private File availabilityFolder;

	public AemiliaAvailabilityManager(Controller ctrl) {
		controller = ctrl;
		manager = controller.getManager();
	}

	public void doAvailability() {

		for (File solutionFolder : availabilityFolder.listFiles()) {
			if (solutionFolder.isDirectory()) {
				for (File modelFile : solutionFolder.listFiles()) {
					if (modelFile.isFile() && FilenameUtils.getExtension(modelFile.getPath()).equals("mmaemilia")) {
						Resource targetResource = manager.getMetamodelManager().getResourceSet()
								.getResource(manager.string2FileUri(modelFile.getAbsolutePath()), true);
						addAvailability((AEmiliaSpecification) targetResource.getContents().get(0));
						try {
							targetResource.save(null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Transformation.GenerateAEMTransformation(modelFile.getPath(), solutionFolder.getPath());
					}
				}
			}
		}
	}

	private void addAvailability(AEmiliaSpecification targetModel) {

		for (ElemType elemType : targetModel.getArchiTypeDecl().getAetDeclaration().getEtDeclaration()) {
			OutputInteraction failOutputInteraction = createOutputInteraction(elemType, "fail_" + elemType.getEtName(),
					targetModel);

			ConstInit constWeight = createConstWeight(elemType, "13");
			
			targetModel.getArchiTypeDecl().getHeader().getInitConst().add(constWeight);
			elemType.getElemHeader().getCostant().add(createConst(constWeight));
			
			updateArchiElemInstanceParameters(elemType, targetModel, constWeight);

			for (BehavEquation behEq : elemType.getBehaviorDecl().getEquations()) {
				ChoiceProcess availabilityChoice = behaviourFactory.createChoiceProcess();
				ActionProcess failProcess = createFailBranchProcess(elemType, failOutputInteraction, behEq, constWeight);
				ActionProcess goodProcess = createGoodBranchProcess(elemType, behEq, constWeight);
				availabilityChoice.getProcesses().add(failProcess);
				availabilityChoice.getProcesses().add(goodProcess);
				behEq.setPt(availabilityChoice);
			}
		}
		// manager.getMetamodelManager().saveModel(targetModel.eResource());
	}
	
	private void updateArchiElemInstanceParameters(ElemType e, AEmiliaSpecification targetModel, ConstInit cWeight) {
		for(ArchiElemInstance aei : targetModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl()) {
			if(aei.getTypeOf().getEtName().equals(e.getEtName())) {
				aei.getActualParam().add(cWeight.getName());
			}
		}
	}

	private Const createConst(ConstInit constWeight) {
		Const k = HeadersFactory.eINSTANCE.createConst();
		k.setConstantData(EcoreUtil.copy(constWeight.getInitConstData()));
		k.setName(constWeight.getName());
		return k;
	}

//	private ActionProcess createGoodBranchProcess(ElemType elemType, BehavEquation behEq) {
//
//		ProcessTerm oldProcess = EcoreUtil.copy(behEq.getPt());
//
//		ActionProcess goodBranchProcess = createActionProcess("true", IdentifierType.TRUTH_VAL);
//		// adding action
//		Action action = createAction("keep");
//		action.setRate(createRateInf("1", "0.9"));
//		action.setIs(createLocalInteraction("keep_" + elemType.getEtName(), InteractionType.UNI));
//		goodBranchProcess.setAct(action);
//		// adding process
//		goodBranchProcess.setProcess(oldProcess);
//
//		return goodBranchProcess;
//	}

	private ActionProcess createGoodBranchProcess(ElemType elemType, BehavEquation behEq, ConstInit cWeight) {

		ProcessTerm oldProcess = EcoreUtil.copy(behEq.getPt());

		ActionProcess goodBranchProcess = createActionProcess("true", IdentifierType.TRUTH_VAL);
		// adding action
		Action action = createAction("keep");
		action.setRate(createRateInf("1", "1 - "+cWeight.getName()));
		action.setIs(createLocalInteraction("keep_" + elemType.getEtName(), InteractionType.UNI));
		goodBranchProcess.setAct(action);
		// adding process
		goodBranchProcess.setProcess(oldProcess);

		return goodBranchProcess;
	}

	private ActionProcess createActionProcess(String conditionName, IdentifierType identifierType) {
		ActionProcess actionProcess = behaviourFactory.createActionProcess();
		actionProcess.setCondition(createIdentityExpression(conditionName, identifierType));
		return actionProcess;
	}

	private ActionProcess createFailBranchProcess(ElemType elemType, OutputInteraction failOutputInteraction,
			BehavEquation behavEquation, ConstInit cWeight) {

		ActionProcess failBranchProcess = createActionProcess("true", IdentifierType.TRUTH_VAL);
		Action loseAction = createAction("lose");
		loseAction.setRate(createRateInf("1", cWeight.getName()));
		loseAction.setIs(createLocalInteraction("lose_" + elemType.getEtName(), InteractionType.UNI));
		failBranchProcess.setAct(loseAction);

		ActionProcess toFailProcess = createFailActionProcess(elemType, failOutputInteraction.getIntName(),
				failOutputInteraction, behavEquation);
		failBranchProcess.setProcess(toFailProcess);

		return failBranchProcess;
	}

	private IdentExpr createIdentityExpression(String name, IdentifierType type) {
		IdentExpr actionProIdentExp = expressionFactory.createIdentExpr();
		actionProIdentExp.setName(name);
		actionProIdentExp.setType(type);
		return actionProIdentExp;
	}

	private ActionProcess createFailActionProcess(ElemType elemType, String actionName,
			OutputInteraction failOutputInteraction, BehavEquation behavEquation) {

		ActionProcess actionProcess = behaviourFactory.createActionProcess();
		// creating identity expression
		actionProcess.setCondition(createIdentityExpression("true", IdentifierType.TRUTH_VAL));
		// creating process term
		actionProcess.setProcess(createBehavProcess("true", IdentifierType.TRUTH_VAL, behavEquation));

		// creating action
		Action failAction = createAction(actionName);
		failAction.setType(behaviourFactory.createActionType());
		failAction.setRate(createRateExp("1"));
		OutputInteraction outputInt = aemiliaMMFactory.createOutputInteraction();
		outputInt.setIntName(failOutputInteraction.getIntName());
		failAction.setIs(outputInt);
		elemType.getOiDecl().add(failOutputInteraction);
		actionProcess.setAct(failAction);

		return actionProcess;
	}

	private BehavProcess createBehavProcess(String identExpName, IdentifierType identityType,
			BehavEquation behavEquation) {
		BehavProcess behavProcess = behaviourFactory.createBehavProcess();
		behavProcess.setEqCall(behavEquation);
		behavProcess.setCondition(createIdentityExpression(identExpName, identityType));
		return behavProcess;
	}

	private RateExp createRateExp(String lambda) {
		RateExp rateExp = behaviourFactory.createRateExp();
		rateExp.setExpr(createIdentityExpression(lambda, IdentifierType.NUMERIC_CONST));
		return rateExp;
	}

	private OutputInteraction createOutputInteraction(ElemType elemType, String intName,
			AEmiliaSpecification targetModel) {
		OutputInteraction failOutputInteraction = aemiliaMMFactory.createOutputInteraction();
		failOutputInteraction.setIntName(intName);
		elemType.getOiDecl().add(failOutputInteraction);
		// adding fail port
		addFailPorts(failOutputInteraction, elemType, targetModel);
		return failOutputInteraction;
	}

	private LocalInteraction createLocalInteraction(String name, InteractionType intType) {
		LocalInteraction localInteraction = aemiliaMMFactory.createLocalInteraction();
		localInteraction.setType(intType);
		localInteraction.setIntName(name);
		return localInteraction;
	}

	private void addFailPorts(OutputInteraction outputInteraction, ElemType elemType,
			AEmiliaSpecification targetModel) {
		for (ArchiElemInstance aei : targetModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl()) {
			if (aei.getTypeOf().equals(elemType)) {
				ArchitecturalInteraction failArchInteraction = aemiliaMMFactory.createArchitecturalInteraction();
				failArchInteraction.setIs_A(outputInteraction);
				failArchInteraction.setFromInstance(aei);
				failArchInteraction.setName("fail_" + elemType.getEtName());
				targetModel.getArchiTypeDecl().getAtDeclaration().getAiDecl().add(failArchInteraction);
			}
		}
	}

	private RateInf createRateInf(String priority, String weight) {
		RateInf rate = behaviourFactory.createRateInf();
		IdentExpr priorityExp = expressionFactory.createIdentExpr();
		IdentExpr weightExp = expressionFactory.createIdentExpr();
		priorityExp.setName(priority);
		rate.setInf_priority(priorityExp);
		weightExp.setName(weight);
		rate.setInf_weight(weightExp);

		return rate;
	}

	private ConstInit createConstWeight(ElemType elem, String weightValue) {
		ConstInit constWeight = HeadersFactory.eINSTANCE.createConstInit();
		constWeight.setName(elem.getEtName() + "_lose");
		Special weightDataType = DataTypeFactory.eINSTANCE.createSpecial();
		weightDataType.setType(SpecialType.WEIGHT);
		constWeight.setInitConstData(weightDataType);		
		IdentExpr weightExp = expressionFactory.createIdentExpr();
		weightExp.setName(weightValue);
		constWeight.setInitConstExpr(weightExp);
		return constWeight;
	}

	private Action createAction(String name) {
		Action action = behaviourFactory.createAction();
		action.setName(name);
		return action;
	}

	public void setFolder(File folder) {
		availabilityFolder = folder;
	}

}
