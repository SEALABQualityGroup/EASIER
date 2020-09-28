package it.univaq.disim.sealab.metaheuristic.actions.aemilia;

import java.util.Iterator;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaStringManager;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import logicalSpecification.AndOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.LogicalSpecificationFactory;
import logicalSpecification.NotOperator;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.actions.AEmilia.impl.AEmiliaRemoveClonedAEIActionImpl;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.Attachment;
import metamodel.mmaemilia.InputInteraction;
import metamodel.mmaemilia.OutputInteraction;

public class AEmiliaRemoveClonedAEIRefactoringAction extends AEmiliaRemoveClonedAEIActionImpl
		implements RefactoringAction {

	private AemiliaRSolution solution;
	private Manager manager;
	private String randomCloneIndex;

	public AEmiliaRemoveClonedAEIRefactoringAction(AEmiliaSpecification model) {
		// this.solution = (AemiliaRSolution) sol;
		// this.manager = sol.getManager();

		this.model = model;

		EList<ArchiElemInstance> listOfRemovableClonedAEI = this.getListOfRemovableCloneInstances();
		int randomInt = RandomUtils.nextInt(0, listOfRemovableClonedAEI.size());

		if (listOfRemovableClonedAEI.isEmpty()) {
//			EasierLogger.logger_.warning("No removable cloned AEIs for Solution #" + solution.getName() + "!");
			this.sourceAEI = null;
		} else {
			// Remove the cloned
			this.sourceAEI = listOfRemovableClonedAEI.get(randomInt);

			this.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MetamodelManager.MAX_VALUE));
			// TODO decide the cost of the removing action, at now it has the same cost of
			// the adding action
			// this.setNumOfChanges(sol.getProblem().getCloningWeight());

			/*
			 * this.setParameters(); this.createPreCondition(); this.createPostCondition();
			 */
		}
	}

	public AEmiliaRemoveClonedAEIRefactoringAction(AemiliaRSolution sol) {
		this.solution = (AemiliaRSolution) sol;
		this.manager = sol.getManager();

		EList<ArchiElemInstance> listOfRemovableClonedAEI = this.getListOfRemovableCloneInstances();
		int randomInt = RandomUtils.nextInt(0, listOfRemovableClonedAEI.size());

		if (listOfRemovableClonedAEI.isEmpty()) {
//			EasierLogger.logger_.warning("No removable cloned AEIs for Solution #" + solution.getName() + "!");
			this.sourceAEI = null;
		} else {
			// Remove the cloned
			this.sourceAEI = listOfRemovableClonedAEI.get(randomInt);

			// this.setName("AEmiliaCloneAEIAction_" + sourceAEI.getInstanceName());
			this.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MetamodelManager.MAX_VALUE));
			// TODO decide the cost of the removing action, at now it has the same cost of
			// the adding action
			this.setNumOfChanges(sol.getProblem().getCloningWeight());

			this.setParameters();
			this.createPreCondition();
			this.createPostCondition();
		}
	}

	public void setParameters() {

		OclAemiliaStringManager oclStringManager = (OclAemiliaStringManager) manager.getOclStringManager();
		setAllAeisMVP(manager.createMultipleValuedParameter(oclStringManager.getAEIListQuery()));
		
		if (sourceAEI != null) {
			setAeiToCloneSVP(
					manager.createSingleValueParameter(oclStringManager.getAEIQuery(sourceAEI.getInstanceName())));
			// addParams.add(getAeiToCloneSVP());
		}
		
	}

	public void createPreCondition() {

		PreCondition preCondition = LogicalSpecificationFactory.eINSTANCE.createPreCondition();
		FOLSpecification preconditionSpecification = manager.createFOLSpectification("RemoveCloneAeiPreCondition");
		AndOperator rootAnd = manager.createAndOperator();

		ExistsOperator existsAeiClonedToRemove = manager.createExistsInCollectionOperator(getAeiToCloneSVP(), getAllAeisMVP());
		rootAnd.getArguments().add(existsAeiClonedToRemove);

//		ForAllOperator forallLocalInteracts = manager.createForAllOperator(getAllLocalInteractsMVP());
//		ExistsOperator localInteractsAreUni = manager.createExistsOperator(getAllUniInteractsMVP());
//		forallLocalInteracts.setArgument(localInteractsAreUni);
//		
//		clonePreAnd.getArguments().add(forallLocalInteracts);

//		preconditionSpecification.setRootOperator(clonePreAnd);
		preconditionSpecification.setRootOperator(rootAnd);
		preCondition.setConditionFormula(preconditionSpecification);
		setPre(preCondition);

	}

	public void createPostCondition() {
		PostCondition postCondition = LogicalSpecificationFactory.eINSTANCE.createPostCondition();
		FOLSpecification clonePostSpecification = manager.createFOLSpectification("RemoveCloneAeiPostCondition");
		AndOperator clonePostAnd = manager.createAndOperator();

//		ExistsOperator existsAeiToClone = manager.createExistsOperator(getAeiToCloneSVP(), getAllAeisMVP());
//		clonePostAnd.getArguments().add(existsAeiToClone);

		NotOperator notOperator = manager.createNotOperator();

		ExistsOperator existsClonedAei = manager.createExistsInCollectionOperator(getAeiToCloneSVP(), getAllAeisMVP());
		clonePostAnd.getArguments().add(existsClonedAei);

		notOperator.setArgument(existsClonedAei);
//		notOperator.setExistsOperator(existsClonedAei);

		/*
		 * ForAllOperator forallAttSrcAeiIsTo =
		 * manager.createForAllOperator(getAllInLocalInteractsMVP()); ExistsOperator
		 * attSrcAeiIsToExists = manager.createExistsOperator(getAllUniInteractsMVP());
		 * forallAttSrcAeiIsTo.setArgument(attSrcAeiIsToExists);
		 * clonePostAnd.getArguments().add(forallAttSrcAeiIsTo);
		 * 
		 * ForAllOperator forallAttSrcAeiIsFrom =
		 * manager.createForAllOperator(getAllOutLocalInteractsMVP()); ExistsOperator
		 * attSrcAeiIsFromExists = manager.createExistsOperator(getAllOrInteractsMVP());
		 * forallAttSrcAeiIsFrom.setArgument(attSrcAeiIsFromExists);
		 * clonePostAnd.getArguments().add(forallAttSrcAeiIsFrom);
		 */

//		clonePostSpecification.setRootOperator(clonePostAnd);
		clonePostSpecification.setRootOperator(clonePostAnd);
		postCondition.setConditionFormula(clonePostSpecification);
		setPost(postCondition);
	}

	public void execute() {

		removingInteractions();
		removingArchitecturalInteraction();

		((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAeiDecl().remove(sourceAEI);

		log();
	}

	public void removingInteractions() {
		EList<Attachment> listOfAtts = getAttachmentsOfAEI(sourceAEI);
		EList<OutputInteraction> listOfOI = sourceAEI.getTypeOf().getOiDecl();
		EList<InputInteraction> listOFII = sourceAEI.getTypeOf().getIiDecl();
		// EList<Attachment> clonedAtts = new BasicEList<Attachment>();
		for (Attachment att : listOfAtts) {
			for (OutputInteraction oi : listOfOI) {
				if (oi.getIntName().equals(att.getStart().getIsOutput().getIntName())
						&& att.getStart().getFromInstance().getInstanceName().equals(sourceAEI.getInstanceName())) {

					((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAttDecl()
							.remove(att);
				}
			}
			for (InputInteraction ii : listOFII) {
				if (ii.getIntName().equals(att.getEnd().getIsInput().getIntName())
						&& att.getEnd().getToInstance().getInstanceName().equals(sourceAEI.getInstanceName())) {

					((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAttDecl()
							.remove(att);

				}
			}
		}
	}

	public void removingInputInteractions() {
		EList<Attachment> listOfAtts = getAttachmentsOfAEI(sourceAEI);
		EList<InputInteraction> listOFII = sourceAEI.getTypeOf().getIiDecl();

		for (Attachment att : listOfAtts) {
			for (InputInteraction ii : listOFII) {
				if (ii.getIntName().equals(att.getEnd().getIsInput().getIntName())
						&& att.getEnd().getToInstance().getInstanceName().equals(sourceAEI.getInstanceName())) {

					((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAttDecl()
							.remove(att);

				}
			}
		}
	}

	/**
	 * Removes the ARCHI_ATTACHMENTS of the pointed cloned AEI
	 */
	public void removingArchitecturalInteraction() {

		EList<ArchitecturalInteraction> listOfArchitecturalInteraction = ((AEmiliaSpecification) this.getModel())
				.getArchiTypeDecl().getAtDeclaration().getAiDecl();
		
		Iterator<ArchitecturalInteraction> iterator = listOfArchitecturalInteraction.iterator();
		
		while(iterator.hasNext()) {
			
			if(iterator.next().getFromInstance().getInstanceName().equals(sourceAEI.getInstanceName())) {
				iterator.remove();
			}
			
		}

//		for (ArchitecturalInteraction architecturalInteraction : listOfArchitecturalInteraction) {
//			if (architecturalInteraction.getFromInstance().getInstanceName().equals(sourceAEI.getInstanceName())) {
//				/*
//				 * ArchitecturalInteraction ai = EcoreUtil.copy(architecturalInteraction);
//				 * ai.setFromInstance(clonedAEI); listOfClonedArchitecturalInteraction.add(ai);
//				 */
//				((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAiDecl()
//						.remove(architecturalInteraction);
//			}
//		}
	}

	/**
	 * Returns all the attachment of the aei
	 * 
	 * @param aei
	 * @return
	 */
	public EList<Attachment> getAttachmentsOfAEI(ArchiElemInstance aei) {
		EList<Attachment> listOfAttachment = ((AEmiliaSpecification) this.getModel()).getArchiTypeDecl()
				.getAtDeclaration().getAttDecl();
		EList<Attachment> listOfAtts = new BasicEList<Attachment>();
		for (Attachment att : listOfAttachment) {
			if (att.getEnd().getToInstance().getInstanceName().equals(aei.getInstanceName())
					|| att.getStart().getFromInstance().getInstanceName().equals(aei.getInstanceName())) {
				listOfAtts.add(att);
			}
		}
		return listOfAtts;
	}

	@Override
	public void log() {
//		EasierLogger.logger_.info("Removing Clone --> " + sourceAEI.getInstanceName());
	}

	/**
	 * Returns the list of removable cloned instances. It collects all the instances
	 * with `_cloned_` keyword in the name
	 * 
	 * @return
	 */
	public EList<ArchiElemInstance> getListOfRemovableCloneInstances() {
		EList<ArchiElemInstance> listOfRemovalbeClonednstances = new BasicEList<>();
		for (ArchiElemInstance aei : ((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration()
				.getAeiDecl()) {
			if (aei.getInstanceName().contains("_cloned_"))
				listOfRemovalbeClonednstances.add(aei);
		}
		return listOfRemovalbeClonednstances;
	}

	@Override
	public EObject getModel() {
		return this.solution.getModel();
	}

	@Override
	public AEmiliaRemoveClonedAEIRefactoringAction clone(RSolution sol) {
		// long rand = ((AemiliaMetamodelManager)
		// Manager.getInstance(null).getMetamodelManager()).getNextUniqueIndex();
		AEmiliaRemoveClonedAEIRefactoringAction newAction = new AEmiliaRemoveClonedAEIRefactoringAction((AemiliaRSolution)sol);

		for (ArchiElemInstance aei : ((AEmiliaSpecification) sol.getModel()).getArchiTypeDecl().getAtDeclaration()
				.getAeiDecl()) {
			if (aei.getInstanceName().equals(this.sourceAEI.getInstanceName())) {
				newAction.setSourceAEI(aei);
				break;
			}
		}

		//newAction.setClonedAEI(null);
//		newAction.setSolution(solution);
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
	
	@Override
	public String toString() {
		return "REMOVE CLONE " + sourceAEI.getInstanceName();
	}

	@Override
	public boolean cleanUp() {
		// TODO Auto-generated method stub
		return true;
	}

}
