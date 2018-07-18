package it.univaq.disim.sealab.metaheuristic.actions.aemilia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaStringManager;
import logicalSpecification.AndOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.ForAllOperator;
import logicalSpecification.LogicalSpecificationFactory;
import logicalSpecification.Parameter;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.actions.AEmilia.AEmiliaCloneAEIAction;
import logicalSpecification.actions.AEmilia.impl.AEmiliaCloneAEIActionImpl;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.Attachment;
import metamodel.mmaemilia.From;
import metamodel.mmaemilia.InputInteraction;
import metamodel.mmaemilia.OutputInteraction;
import metamodel.mmaemilia.To;
import metamodel.mmaemilia.mmaemiliaFactory;
import metamodel.mmaemilia.Expressions.IdentExpr;

public class AEmiliaCloneAEIRefactoringAction extends AEmiliaCloneAEIActionImpl
		implements AEmiliaCloneAEIAction, RefactoringAction {

	private RSolution solution;
	private Manager manager;
	private String randomCloneIndex;

	public AEmiliaCloneAEIRefactoringAction(RSolution sol) {
		this.solution = sol;
		this.manager = sol.getManager();

		EList<ArchiElemInstance> listOfClonableAEI = this.getListOfClonableInstances();
		int randomInt = RandomUtils.nextInt(0, listOfClonableAEI.size());
		if (listOfClonableAEI.isEmpty()) {
			Controller.logger_.warning("No clonable AEIs for Solution #" + solution.getName() + "!");
			this.sourceAEI = null;
		} else {
			this.sourceAEI = listOfClonableAEI.get(randomInt);
			// this.setRandomCloneIndex(rand);
			this.setRandomCloneIndex(getSaltString());
			// this.setRandomCloneIndex(RandomUtils.nextInt(0, 100));
			if (sourceAEI.getSelector() != null) {
				setSelector();
			}
			this.setName("AEmiliaCloneAEIAction_" + sourceAEI.getInstanceName());
			this.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MetamodelManager.MAX_VALUE));
			this.setNumOfChanges(sol.getController().getCloningWeight());
			this.setParameters();
			this.createPreCondition();
			this.createPostCondition();
		}

	}

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	public void execute() {

		// EList<ArchiElemInstance> listOfClonableAEI =
		// this.getListOfClonableInstances();
		// int randomInt = RandomUtils.nextInt(0, listOfClonableAEI.size());
		// if (listOfClonableAEI.isEmpty()) {
		// Controller.logger_.warning("ERROR NO CLONABLE INSTANCES");
		// this.sourceAEI = null;
		// } else {
		// this.sourceAEI = listOfClonableAEI.get(randomInt);
		// this.setRandomCloneIndex(RandomUtils.nextInt(0, 100));
		// if (sourceAEI.getSelector() != null) {
		// setSelector();
		// }
		clonedAEI = (ArchiElemInstance) EcoreUtil.copy(sourceAEI);
		clonedAEI.setTypeOf(sourceAEI.getTypeOf());
		clonedAEI.setInstanceName(sourceAEI.getInstanceName() + "_cloned_" + getRandomCloneIndex());
		((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAeiDecl().add(clonedAEI);

		cloningOutputInteractions();
		cloningInputInteractions();
		cloningArchitecturalInteraction();
		// }

		// if (sourceAEI.getSelector() != null) {
		// setSelector();
		// } else {
		// //clonedAEI.setInstanceName(clonedAEI.getInstanceName() + "_cloned_" +
		// RandomUtils.nextInt(0, 100));
		// clonedAEI.setInstanceName(sourceAEI.getInstanceName() + "_cloned_" +
		// getRandomCloneIndex());
		// }
		//
		//
		//
		// ((AEmiliaSpecification)
		// this.getModel()).getArchiTypeDecl().getAtDeclaration().getAeiDecl().add(clonedAEI);
		//
		// cloningOutputInteractions();
		// cloningInputInteractions();
		// cloningArchitecturalInteraction();
		
		try {
			if(!isThereAnyClone()) {
				throw new Exception("error in clone action");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log();
	}
	
	private boolean isThereAnyClone(){
//		((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAeiDecl().contains(clonedAEI);
		return ((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAeiDecl().contains(clonedAEI);
		
	}

	public void log() {
		Controller.logger_.info("CLONING " + sourceAEI.getInstanceName());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void cloningArchitecturalInteraction() {
		EList<ArchitecturalInteraction> listOfArchitecturalInteraction = ((AEmiliaSpecification) this.getModel())
				.getArchiTypeDecl().getAtDeclaration().getAiDecl();
		EList<ArchitecturalInteraction> listOfClonedArchitecturalInteraction = new BasicEList<>();

		for (ArchitecturalInteraction architecturalInteraction : listOfArchitecturalInteraction) {
			if (architecturalInteraction.getFromInstance().getInstanceName().equals(sourceAEI.getInstanceName())) {
				ArchitecturalInteraction ai = EcoreUtil.copy(architecturalInteraction);
				ai.setFromInstance(clonedAEI);
				listOfClonedArchitecturalInteraction.add(ai);
			}
		}
		if (!listOfClonedArchitecturalInteraction.isEmpty()) {
			listOfArchitecturalInteraction.addAll(listOfClonedArchitecturalInteraction);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void cloningInputInteractions() {
		EList<Attachment> listOfAtts = getAttachmentsOfAEI(sourceAEI);
		EList<InputInteraction> listOFII = sourceAEI.getTypeOf().getIiDecl();

		for (Attachment att : listOfAtts) {
			for (InputInteraction ii : listOFII) {
				if (ii.getIntName().equals(att.getEnd().getIsInput().getIntName())
						&& att.getEnd().getToInstance().getInstanceName().equals(sourceAEI.getInstanceName())) {
					att.getStart().getIsOutput().setType(metamodel.mmaemilia.InteractionType.OR);

					Attachment newAtt = mmaemiliaFactory.eINSTANCE.createAttachment();

					From from = mmaemiliaFactory.eINSTANCE.createFrom();
					from.setFromInstance(att.getStart().getFromInstance());
					from.setIsOutput(att.getStart().getIsOutput());

					To to = mmaemiliaFactory.eINSTANCE.createTo();
					to.setToInstance(clonedAEI);
					to.setIsInput(att.getEnd().getIsInput());

					newAtt.setStart(from);
					newAtt.setEnd(to);

					((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAttDecl()
							.add(newAtt);

				}
			}
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void cloningOutputInteractions() {
		EList<Attachment> listOfAtts = getAttachmentsOfAEI(sourceAEI);
		EList<OutputInteraction> listOfOI = sourceAEI.getTypeOf().getOiDecl();
		// EList<Attachment> clonedAtts = new BasicEList<Attachment>();
		for (Attachment att : listOfAtts) {
			for (OutputInteraction oi : listOfOI) {
				if (oi.getIntName().equals(att.getStart().getIsOutput().getIntName())
						&& att.getStart().getFromInstance().getInstanceName().equals(sourceAEI.getInstanceName())) {
					// listOfOI.contains(att.getStart().getIsOutput())) {
					att.getEnd().getIsInput().setType(metamodel.mmaemilia.InteractionType.OR);

					Attachment newAtt = mmaemiliaFactory.eINSTANCE.createAttachment();

					From from = mmaemiliaFactory.eINSTANCE.createFrom();
					from.setFromInstance(clonedAEI);
					from.setIsOutput(att.getStart().getIsOutput());

					To to = mmaemiliaFactory.eINSTANCE.createTo();
					to.setToInstance(att.getEnd().getToInstance());
					to.setIsInput(att.getEnd().getIsInput());

					newAtt.setStart(from);
					newAtt.setEnd(to);

					((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAttDecl()
							.add(newAtt);
				}
			}
		}
	}

	public void setParameters() {
		// ACTION CLONE AEI
		List<Parameter> addParams = new ArrayList<>();

		OclManager oclManager = manager.getOclManager();
		OclAemiliaStringManager oclStringManager = (OclAemiliaStringManager) manager.getOclStringManager();

		if (sourceAEI != null) {
			setAeiToCloneSVP(
					manager.createSingleValueParameter(oclStringManager.getAEIQuery(sourceAEI.getInstanceName())));
			// addParams.add(getAeiToCloneSVP());
		}

		setAllAeisMVP(manager.createMultipleValuedParameter(oclStringManager.getAEIListQuery()));

		setAllLocalInteractsMVP(manager.createMultipleValuedParameter(
				oclStringManager.getAllLocalInteractsQuery(sourceAEI.getInstanceName())));

		setAllUniInteractsMVP(manager.createMultipleValuedParameter(oclStringManager.getAllUniInteractsQuery()));

		// if (clonedAEI != null) {
		setClonedAeiSVP(manager.createSingleValueParameter(
				oclStringManager.getClonedAEIQuery(sourceAEI.getInstanceName() + "_cloned_" + getRandomCloneIndex())));
		// setClonedAeiSVP(manager.createSingleValueParameter(oclStringManager.getClonedAEIQuery(clonedAEI.getInstanceName())));
		// addParams.add(getClonedAeiSVP());

		setAllInLocalInteractsMVP(manager.createMultipleValuedParameter(
				oclStringManager.getAllInLocalInteractsQuery(sourceAEI.getInstanceName())));
		// setAllInLocalInteractsMVP(manager.createMultipleValuedParameter(oclStringManager.getAllInLocalInteractsQuery(clonedAEI.getInstanceName())));

		// allUniInteracts already set

		setAllOutLocalInteractsMVP(manager.createMultipleValuedParameter(
				oclStringManager.getAllOutLocalInteractsQuery(sourceAEI.getInstanceName())));
		// setAllOutLocalInteractsMVP(manager.createMultipleValuedParameter(
		// oclStringManager.getAllOutLocalInteractsQuery(clonedAEI.getInstanceName())));

		setAllInRemoteInteractsMVP(manager.createMultipleValuedParameter(
				oclStringManager.getAllInRemoteInteractsQuery(sourceAEI.getInstanceName())));
		setAllOutRemoteInteractsMVP(manager.createMultipleValuedParameter(
				oclStringManager.getAllOutRemoteInteractsQuery(sourceAEI.getInstanceName())));

		setAllOrInteractsMVP(manager.createMultipleValuedParameter(oclStringManager.getAllOrInteractsQuery()));
		// setAllAttachesMVP(manager.createMultipleValuedParameter(oclStringManager.getAllAttachesQuery()));

		// }
	}

	public void createPreCondition() {
		PreCondition preCondition = LogicalSpecificationFactory.eINSTANCE.createPreCondition();
		FOLSpecification clonePreSpecification = manager
				.createFOLSpectification("CloneAeiPreCondition");
		AndOperator clonePreAnd = manager.createAndOperator();

		ExistsOperator existsAeiToClone = manager
				.createExistsOperator(getAeiToCloneSVP(), getAllAeisMVP());
		clonePreAnd.getArguments().add(existsAeiToClone);

		ForAllOperator forallLocalInteracts = manager.createForAllOperator(getAllLocalInteractsMVP());
		ExistsOperator localInteractsAreUni = manager.createExistsOperator(getAllUniInteractsMVP());
		forallLocalInteracts.setArgument(localInteractsAreUni);
		clonePreAnd.getArguments().add(forallLocalInteracts);

		clonePreSpecification.setRootOperator(clonePreAnd);
		preCondition.setConditionFormula(clonePreSpecification);
		setPre(preCondition);
	}

	public void createPostCondition() {
		PostCondition postCondition = LogicalSpecificationFactory.eINSTANCE.createPostCondition();
		FOLSpecification clonePostSpecification = manager.createFOLSpectification("CloneAeiPostCondition");
		AndOperator clonePostAnd = manager.createAndOperator();

		ExistsOperator existsAeiToClone = manager.createExistsOperator(getAeiToCloneSVP(), getAllAeisMVP());
		clonePostAnd.getArguments().add(existsAeiToClone);

		ExistsOperator existsClonedAei = manager.createExistsOperator(getClonedAeiSVP(), getAllAeisMVP());
		clonePostAnd.getArguments().add(existsClonedAei);

		ForAllOperator forallAttSrcAeiIsTo = manager.createForAllOperator(getAllInLocalInteractsMVP());
		ExistsOperator attSrcAeiIsToExists = manager.createExistsOperator(getAllUniInteractsMVP());
		forallAttSrcAeiIsTo.setArgument(attSrcAeiIsToExists);
		clonePostAnd.getArguments().add(forallAttSrcAeiIsTo);

		ForAllOperator forallAttSrcAeiIsFrom = manager.createForAllOperator(getAllOutLocalInteractsMVP());
		ExistsOperator attSrcAeiIsFromExists = manager.createExistsOperator(getAllOrInteractsMVP());
		forallAttSrcAeiIsFrom.setArgument(attSrcAeiIsFromExists);
		clonePostAnd.getArguments().add(forallAttSrcAeiIsFrom);

		clonePostSpecification.setRootOperator(clonePostAnd);
		postCondition.setConditionFormula(clonePostSpecification);
		setPost(postCondition);
	}

	public RSolution getSolution() {
		return solution;
	}

	public void setSolution(RSolution solution) {
		this.solution = solution;
	}

	public EObject getModel() {
		return this.solution.getModel();
	}

	public boolean isApplicable() {
		// return !getListOfClonableInstances().isEmpty();
		return isClonable(this.getSourceAEI());
	}

	public boolean isClonable(ArchiElemInstance aei) {
		EList<Attachment> listOfAttachment = getAttachmentsOfAEI(aei);
		// EList<ArchiElemInstance> listOfNeighs = getNeighsOfAEI(aei);
		if (listOfAttachment.isEmpty()) {
			return false;
		}
		for (Attachment att : listOfAttachment) {
			if (!att.getEnd().getIsInput().getType().equals(metamodel.mmaemilia.InteractionType.UNI)
					|| !att.getStart().getIsOutput().getType().equals(metamodel.mmaemilia.InteractionType.UNI)) {
				return false;
			}
		}
		// for(ArchiElemInstance neigh : listOfNeighs ) {
		// for(Action a : this.getRefactoring().getActions()) {
		// if(a instanceof AEmiliaCloneAEIRefactoringAction)
		// if(((AEmiliaCloneAEIRefactoringAction) a).getSourceAEI().equals(neigh))
		// return false;
		// }
		// }

		return true;
	}

	public EList<ArchiElemInstance> getListOfClonableInstances() {
		EList<ArchiElemInstance> listOfClonableInstances = new BasicEList<>();
		for (ArchiElemInstance aei : ((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration()
				.getAeiDecl()) {
			if (isClonable(aei))
				listOfClonableInstances.add(aei);
		}
		return listOfClonableInstances;
	}

	public void setSelector() {
		List<ArchiElemInstance> listOfAEIes = ((AEmiliaSpecification) this.getModel()).getArchiTypeDecl()
				.getAtDeclaration().getAeiDecl();
		int selector = 0;
		for (ArchiElemInstance aei : listOfAEIes) {
			if (aei.getTypeOf() == sourceAEI.getTypeOf()) {
				int aeiSelector = Integer.valueOf(((IdentExpr) aei.getSelector()).getName());
				if (aeiSelector > selector) {
					selector = aeiSelector;
				}
			}
		}
		((IdentExpr) clonedAEI.getSelector()).setName(String.valueOf(selector + 1));
	}

	public EList<Attachment> getAttachmentsOfAEI(ArchiElemInstance aei) {
		EList<Attachment> listOfAttachment = ((AEmiliaSpecification) this.getModel()).getArchiTypeDecl()
				.getAtDeclaration().getAttDecl();
		EList<Attachment> listOfAtts = new BasicEList<Attachment>();
		for (Attachment att : listOfAttachment) {
			if (att.getEnd().getToInstance().getInstanceName().equals(aei.getInstanceName())
					|| att.getStart().getFromInstance().getInstanceName().equals(aei.getInstanceName())) {
				listOfAtts.add(att);
			}
			// if (att.getEnd().getToInstance().equals(aei) ||
			// att.getStart().getFromInstance().equals(aei)) {
			// listOfAtts.add(att);
			// }
		}
		return listOfAtts;
	}

	public EList<ArchiElemInstance> getNeighsOfAEI(ArchiElemInstance aei) {
		EList<ArchiElemInstance> listOfAEIs = new BasicEList<ArchiElemInstance>();
		EList<Attachment> listOfAttachment = ((AEmiliaSpecification) this.getModel()).getArchiTypeDecl()
				.getAtDeclaration().getAttDecl();
		for (Attachment att : listOfAttachment) {
			if (att.getEnd().getToInstance().getInstanceName().equals(aei.getInstanceName())
					|| att.getStart().getFromInstance().getInstanceName().equals(aei.getInstanceName())) {
				if (att.getEnd().getToInstance().getInstanceName().equals(aei.getInstanceName()))
					listOfAEIs.add(att.getStart().getFromInstance());
				if (att.getStart().getFromInstance().getInstanceName().equals(aei.getInstanceName()))
					listOfAEIs.add(att.getEnd().getToInstance());

			}
			// if (att.getEnd().getToInstance().equals(aei) ||
			// att.getStart().getFromInstance().equals(aei)) {
			// listOfAtts.add(att);
			// }
		}
		return listOfAEIs;
	}

	public AEmiliaCloneAEIRefactoringAction clone(RSolution solution) {
		// long rand = ((AemiliaManager)
		// Manager.getInstance(null).getMetamodelManager()).getNextUniqueIndex();
		AEmiliaCloneAEIRefactoringAction newAction = new AEmiliaCloneAEIRefactoringAction(this.solution);

		for (ArchiElemInstance aei : ((AEmiliaSpecification) solution.getModel()).getArchiTypeDecl().getAtDeclaration()
				.getAeiDecl()) {
			if (aei.getInstanceName().equals(this.sourceAEI.getInstanceName())) {
				newAction.setSourceAEI(aei);
				break;
			}
		}

		newAction.setClonedAEI(null);
		newAction.setSolution(solution);
		newAction.setNumOfChanges(this.getNumOfChanges());
		newAction.setCost(this.getCost());
		newAction.setName(this.getName());

		// TODO to be checked
		newAction.setParameters();
		newAction.createPreCondition();
		newAction.createPostCondition();
		// newAction.setAeiToCloneSVP(this.getAeiToCloneSVP());
		// newAction.setAllAeisMVP(this.getAllAeisMVP());
		// newAction.setAllAttachesMVP(this.getAllAttachesMVP());
		// newAction.setAllInAttachesMVP(this.getAllInAttachesMVP());
		// newAction.setAllLocalInteractsMVP(this.getAllLocalInteractsMVP());
		// newAction.setAllOutAttachesMVP(this.getAllOutAttachesMVP());
		// newAction.setAllUniInteractsMVP(this.getAllUniInteractsMVP());
		// newAction.setClonedAeiSVP(this.getClonedAeiSVP());

		return newAction;
	}

	public String getRandomCloneIndex() {
		return randomCloneIndex;
	}

	public void setRandomCloneIndex(String randomCloneIndex) {
		this.randomCloneIndex = randomCloneIndex;
	}

}
