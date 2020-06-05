package it.univaq.disim.sealab.metaheuristic.actions.aemilia;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
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

	private ArchiElemInstance sourceAEI;

	public AEmiliaRemoveClonedAEIRefactoringAction(AEmiliaSpecification model) {
		// this.solution = (AemiliaRSolution) sol;
		// this.manager = sol.getManager();

		this.model = model;

		EList<ArchiElemInstance> listOfRemovableClonedAEI = this.getListOfRemovableCloneInstances();
		int randomInt = RandomUtils.nextInt(0, listOfRemovableClonedAEI.size());

		if (listOfRemovableClonedAEI.isEmpty()) {
			EasierLogger.logger_.warning("No removable cloned AEIs for Solution #" + solution.getName() + "!");
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

	public AEmiliaRemoveClonedAEIRefactoringAction(RSolution sol) {
		this.solution = (AemiliaRSolution) sol;
		this.manager = sol.getManager();

		EList<ArchiElemInstance> listOfRemovableClonedAEI = this.getListOfRemovableCloneInstances();
		int randomInt = RandomUtils.nextInt(0, listOfRemovableClonedAEI.size());

		if (listOfRemovableClonedAEI.isEmpty()) {
			EasierLogger.logger_.warning("No removable cloned AEIs for Solution #" + solution.getName() + "!");
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

	public void execute() {

		removingInteractions();
		removingArchitecturalInteraction();
		
		((AEmiliaSpecification)this.getModel()).getArchiTypeDecl().getAtDeclaration().getAeiDecl().remove(sourceAEI);

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

		for (ArchitecturalInteraction architecturalInteraction : listOfArchitecturalInteraction) {
			System.out.println(architecturalInteraction.getFromInstance().getInstanceName().toString());
			if (architecturalInteraction.getFromInstance().getInstanceName().equals(sourceAEI.getInstanceName())) {
				/*
				 * ArchitecturalInteraction ai = EcoreUtil.copy(architecturalInteraction);
				 * ai.setFromInstance(clonedAEI); listOfClonedArchitecturalInteraction.add(ai);
				 */
				((AEmiliaSpecification) this.getModel()).getArchiTypeDecl().getAtDeclaration().getAiDecl()
						.remove(architecturalInteraction);
			}
		}
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

			System.out.println(att.getStart().getFromInstance().getInstanceName() + "-->"
					+ att.getEnd().getToInstance().getInstanceName());

			if (att.getEnd().getToInstance().getInstanceName().equals(aei.getInstanceName())
					|| att.getStart().getFromInstance().getInstanceName().equals(aei.getInstanceName())) {
				listOfAtts.add(att);
			}
		}
		return listOfAtts;
	}

	@Override
	public void log() {
		EasierLogger.logger_.info("Removing Clone --> " + sourceAEI.getInstanceName());
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
	public RefactoringAction clone(RSolution solution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSolution(RSolution sol) {
		this.solution = (AemiliaRSolution) sol;
	}

}
