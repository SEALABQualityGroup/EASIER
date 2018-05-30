package it.univaq.disim.sealab.metaheuristic.managers.aemilia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import it.univaq.from_aemilia_to_qn_plug_in.handlers.GeneratoreModelloAemilia;
import mapmeasurestoindices.MapmeasurestoindicesPackage;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.ElemType;
import metamodel.mmaemilia.InteractionType;
import metamodel.mmaemilia.LocalInteraction;
import metamodel.mmaemilia.OutputInteraction;
import metamodel.mmaemilia.mmaemiliaFactory;
import metamodel.mmaemilia.mmaemiliaPackage;
import metamodel.mmaemilia.Behavior.Action;
import metamodel.mmaemilia.Behavior.ActionProcess;
import metamodel.mmaemilia.Behavior.BehavEquation;
import metamodel.mmaemilia.Behavior.BehaviorFactory;
import metamodel.mmaemilia.Behavior.ChoiceProcess;
import metamodel.mmaemilia.Behavior.ProcessTerm;
import metamodel.mmaemilia.Behavior.RateExp;
import metamodel.mmaemilia.Behavior.RateInf;
import metamodel.mmaemilia.Expressions.ExpressionsFactory;
import metamodel.mmaemilia.Expressions.IdentExpr;
import metamodel.mmaemilia.Expressions.IdentifierType;

public class AemiliaManagerTest {

	private AEmiliaSpecification targetModel;
	private ResourceSet resourceSet;
	private Resource resource;

	private String AEMILIA_ABSOLUT_PATH;

	private BehaviorFactory behaviourFactory = BehaviorFactory.eINSTANCE;
	private ExpressionsFactory expressionFactory = ExpressionsFactory.eINSTANCE;
	private mmaemiliaFactory aemiliaMMFactory = mmaemiliaFactory.eINSTANCE;

	@Before
	public void init() {
		packageRegistering();

		AEMILIA_ABSOLUT_PATH = getClass().getResource("/models/AemiliaModels/choiceExample/FTA/fta.aem").getPath();

		// assertEquals("/Users/peo12/git/sealab/easier/easier-core/src/test/resources/models/AemiliaModels/choiceExample/modCompAemilia.aem",
		// AEMILIA_ABSOLUT_PATH);

		createAemiliaEcoreFile(AEMILIA_ABSOLUT_PATH);

		String aemiliaEcore = getClass().getResource("/models/AemiliaModels/choiceExample/FTA/fta.mmaemilia").getPath();

		resource = getResource(aemiliaEcore);
		targetModel = (AEmiliaSpecification) EcoreUtil.getObjectByType(resource.getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
	}

	private void createAemiliaEcoreFile(String sourceAemPath) {
		GeneratoreModelloAemilia genModel = new GeneratoreModelloAemilia();
		FileInputStream aemFileInputStream;
		try {
			aemFileInputStream = new FileInputStream(new File(sourceAemPath));
			genModel.execute_ase(aemFileInputStream, null,
					"/Users/peo12/git/sealab/easier/easier-core/src/test/resources/models/AemiliaModels/choiceExample/FTA/fta_2.mmaemilia");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void packageRegistering() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("rewmapping", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("mmaemilia", new XMIResourceFactoryImpl());
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(MapmeasurestoindicesPackage.eINSTANCE.getNsURI(),
				MapmeasurestoindicesPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(mmaemiliaPackage.eINSTANCE.getNsURI(), mmaemiliaPackage.eINSTANCE);

	}

	private Resource getResource(String ameliaAbsolutePath) {
		URI uri = URI.createFileURI(ameliaAbsolutePath);
		resource = resourceSet.getResource(uri, true);
		return resource;
	}

	@Test
	public void addAvailability() {

		for (ElemType elemType : targetModel.getArchiTypeDecl().getAetDeclaration().getEtDeclaration()) {
			OutputInteraction failOutputInteraction = createOutputInteraction(elemType, "fail_" + elemType.getEtName());

			for (BehavEquation behEq : elemType.getBehaviorDecl().getEquations()) {
				ChoiceProcess availabilityChoice = behaviourFactory.createChoiceProcess();
				
				ActionProcess failProcess = createFailBranchProcess(elemType, failOutputInteraction);
				ActionProcess goodProcess = createGoodBranchProcess(elemType, behEq);
//
				availabilityChoice.getProcesses().add(failProcess);
				availabilityChoice.getProcesses().add(goodProcess);
				
				behEq.setPt(availabilityChoice);

			}

		}

		saveModel();
	}

	private ActionProcess createGoodBranchProcess(ElemType elemType, BehavEquation behEq) {

		ProcessTerm oldProcess = EcoreUtil.copy(behEq.getPt());

		ActionProcess goodBranchProcess = createActionProcess("true", IdentifierType.TRUTH_VAL);
		// adding action
		Action action = createAction("keep");
		action.setRate(createRateInf("1", "0.9"));
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

	private ActionProcess createFailBranchProcess(ElemType elemType, OutputInteraction failOutputInteraction) {

		ActionProcess failBranchProcess = createActionProcess("true", IdentifierType.TRUTH_VAL);
		Action loseAction = createAction("lose");
		loseAction.setRate(createRateInf("1", "0.1"));
		loseAction.setIs(createLocalInteraction("lose_" + elemType.getEtName(), InteractionType.UNI));
		failBranchProcess.setAct(loseAction);

		ActionProcess toFailProcess = createFailActionProcess(elemType, "fail", failOutputInteraction);
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
			OutputInteraction failOutputInteraction) {

		ActionProcess actionProcess = behaviourFactory.createActionProcess();
		// creating identity expression
		actionProcess.setCondition(createIdentityExpression("true", IdentifierType.TRUTH_VAL));
		// creating process term
		actionProcess.setProcess(createProcessTerm("true", IdentifierType.TRUTH_VAL));

		// creating action
		Action failAction = createAction(actionName);
		failAction.setRate(createRateExp("1"));
		failAction.setIs(failOutputInteraction);
		actionProcess.setAct(failAction);

		return actionProcess;
	}

	private ProcessTerm createProcessTerm(String identExpName, IdentifierType identityType) {
		ProcessTerm processTerm = behaviourFactory.createProcessTerm();
		processTerm.setCondition(createIdentityExpression(identExpName, identityType));
		return processTerm;
	}

	private RateExp createRateExp(String lambda) {
		RateExp rateExp = behaviourFactory.createRateExp();
		rateExp.setExpr(createIdentityExpression(lambda, IdentifierType.NUMERIC_CONST));
		return rateExp;
	}

	private OutputInteraction createOutputInteraction(ElemType elemType, String intName) {
		OutputInteraction failOutputInteraction = aemiliaMMFactory.createOutputInteraction();
		failOutputInteraction.setIntName(intName);
		elemType.getOiDecl().add(failOutputInteraction);
		// adding fail port
		addFailPorts(failOutputInteraction, elemType);
		return failOutputInteraction;
	}

	private LocalInteraction createLocalInteraction(String name, InteractionType intType) {
		LocalInteraction localInteraction = aemiliaMMFactory.createLocalInteraction();
		localInteraction.setType(intType);
		localInteraction.setIntName(name);
		return localInteraction;
	}

	private void addFailPorts(OutputInteraction outputInteraction, ElemType elemType) {
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

	private Action createAction(String name) {
		Action action = behaviourFactory.createAction();
		action.setName(name);
		return action;
	}

	public boolean saveModel() {
		try {
			resource.save(null);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
