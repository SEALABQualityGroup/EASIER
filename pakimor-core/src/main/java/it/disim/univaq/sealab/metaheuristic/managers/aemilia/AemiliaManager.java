package it.disim.univaq.sealab.metaheuristic.managers.aemilia;

import java.io.File;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.disim.univaq.sealab.metaheuristic.actions.aemilia.AEmiliaCloneAEIRefactoringAction;
import it.disim.univaq.sealab.metaheuristic.actions.aemilia.AEmiliaConstChangesRefactoringAction;
import it.disim.univaq.sealab.metaheuristic.actions.aemilia.RefactoringAction;
import it.disim.univaq.sealab.metaheuristic.evolutionary.Controller;
import it.disim.univaq.sealab.metaheuristic.evolutionary.RSequence;
import it.disim.univaq.sealab.metaheuristic.managers.Manager;
import it.disim.univaq.sealab.metaheuristic.managers.MetamodelManager;
import it.disim.univaq.sealab.metaheuristic.managers.ocl.OclManager;
import it.disim.univaq.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaManager;
import it.disim.univaq.sealab.ttep.rew.classes.MeasureDef;
import it.disim.univaq.sealab.ttep.rew.wizard.providers.ExtractedIndex;
import it.disim.univaq.sealab.twoeagles_bridge.TwoEaglesBridge;
import logicalSpecification.Action;
import mapmeasurestoindices.MapmeasurestoindicesFactory;
import mapmeasurestoindices.MapmeasurestoindicesPackage;
import mapmeasurestoindices.RewMapping;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.Attachment;
import metamodel.mmaemilia.mmaemiliaPackage;
import metamodel.mmaemilia.DataType.Special;
import metamodel.mmaemilia.DataType.SpecialType;
import metamodel.mmaemilia.Headers.ConstInit;

public class AemiliaManager extends MetamodelManager {

	private TwoEaglesBridge twoEaglesBridge;

	private static final String MODEL_FILE_EXTENSION = "aem";
	private static final String METAMODEL_FILE_EXTENISON = "mmaemilia";
	private static final String REW_FILE_EXTENSION = "rew";
	private static final String REWMAPPING_FILE_EXTENSION = "rewmapping";

	private ResourceSet resourceSet;
	private Resource aemiliaResource;

	private String rewFilePath;
	private String aemiliaModelFilePath; // .mmaemilia
	private String sourceValFilePath;
	private String refactoredModelFilePath;
	private String aemFilePath; // .aem
	private String outputFilePath;
	private String rewmappingFilePath;
	private String baseFilePath = "";
	private String refactoreBaseFilePath = "";

	private AEmiliaSpecification model;

	private Resource rewMappingResource;

	// private AtomicLong counter = new AtomicLong(RandomUtils.nextInt(1,
	// Integer.MAX_VALUE));

	public void init(String modelUri) {
		setAemFilePath(modelUri + getModelFileExtension());
		setAemiliaModelFilePath(modelUri + getMetamodelFileExtension());
		setRewFilePath(modelUri + getRewFileExtension());
		setRewmappingFilePath(modelUri + getRewmappingFileExtension());

		// unloadModelResource();
		packageRegistering();
		getOclManager().inizialize(getResourceSet());

		String ameliaAbsolutePath = getAemiliaModelFilePath();
		URI uri = URI.createFileURI(ameliaAbsolutePath);
		aemiliaResource = getResourceSet().getResource(uri, true);

		model = (AEmiliaSpecification) EcoreUtil.getObjectByType(getResource().getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
		setREFACTORED_MODEL_BASE_PATH("/src/main/resources/models/refactored/");
	}

	public void aemiliaModelGeneration() {
		getTwoEaglesBridge().aemiliaModelGeneration(getAemiliaModelFilePath(), getRefactoredModelFilePath());
	}

	@SuppressWarnings("unused")
	public void gaussianEliminationSRBMC() {
		getTwoEaglesBridge().gaussianEliminationSRBMC(getAemFilePath(), getRewFilePath(), getOutputFilePath());
	}

	@SuppressWarnings("unused")
	public void gaussianEliminationSRBMC(String aemFilePath, String rewFilePath, String outputFilePath) {
		getTwoEaglesBridge().gaussianEliminationSRBMC(aemFilePath, rewFilePath, outputFilePath);
	}

	public void measuresToIndices() {
		getTwoEaglesBridge().measuresToIndices((Resource) createRewmappingResource(),
				MapmeasurestoindicesFactory.eINSTANCE.createRewMapping(), getRewFilePath());
	}

	public void aemiliaModelUpdate() {
		getTwoEaglesBridge().aemiliaModelUpdate(getAemFilePath(), getRewmappingFilePath());
	}

	public void aemiliaModelUpdate(String valFilePath, String rewFilePath, String rewMappingFilePath,
			String refactoredMmAemiliaFilePath) {
		Resource rewMappingResource = createRewmappingResource(rewMappingFilePath);
		RewMapping rewMapping = MapmeasurestoindicesFactory.eINSTANCE.createRewMapping();
		getTwoEaglesBridge().setAemiliaSpecification(
				(AEmiliaSpecification) getResource(refactoredMmAemiliaFilePath).getContents().get(0));
		getTwoEaglesBridge().measuresToIndices(rewMappingResource, rewMapping, rewFilePath);

		getTwoEaglesBridge().aemiliaModelUpdate(valFilePath, rewMappingFilePath);
	}

	@Override
	public Resource getResource() {
		return aemiliaResource;
	}

	public Resource getResource(String aemFilePath) {
		// String ameliaAbsolutePath = getAemiliaModelFilePath();
		URI uri = URI.createFileURI(aemFilePath);
		aemiliaResource = getResourceSet().getResource(uri, true);
		return aemiliaResource;
	}

	public Resource createRewmappingResource() {
		packageRegistering();
		if (rewMappingResource == null) {
			URI fileURI = URI.createFileURI(getBasePath() + getRewmappingFilePath());
			rewMappingResource = resourceSet.createResource(fileURI);
		}
		return rewMappingResource;
	}

	public Resource createRewmappingResource(String rewmappingFilePath) {
		packageRegistering();
		URI fileURI = URI.createFileURI(rewmappingFilePath);
		Resource asResource = resourceSet.createResource(fileURI);
		return asResource;
	}

	/**
	 * 
	 * @param measureDefList
	 *            -> contains the rew's measure definitions
	 * @return
	 */
	public List<ExtractedIndex> createSelectedIndices(List<MeasureDef> measureDefList) {
		return getTwoEaglesBridge().createSelectedIndices(measureDefList);
	}

	public void packageRegistering() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(REWMAPPING_FILE_EXTENSION,
				new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(METAMODEL_FILE_EXTENISON,
				new XMIResourceFactoryImpl());
		resourceSet = getResourceSet();
		resourceSet.getPackageRegistry().put(MapmeasurestoindicesPackage.eINSTANCE.getNsURI(),
				MapmeasurestoindicesPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(mmaemiliaPackage.eINSTANCE.getNsURI(), mmaemiliaPackage.eINSTANCE);
	}

	public String getAemFilePath() {
		return getBasePath() + aemFilePath + getModelFileExtension();
	}

	public void setAemFilePath(String aemFile) {
		this.aemFilePath = aemFile;
	}

	public String getBasePath() {
		return baseFilePath;
	}

	public void setBasePath(String basePath) {
		this.baseFilePath = basePath;
	}

	@Override
	public AEmiliaSpecification getModel() {
		if (model == null) {
			this.packageRegistering();
			this.model = (AEmiliaSpecification) EcoreUtil.getObjectByType(
					getResourceSet().getResource(Manager.string2Uri(aemiliaModelFilePath), true).getContents(),
					mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
//			this.model = (AEmiliaSpecification) getResourceSet().getResource(Manager.string2Uri(aemiliaModelFilePath),
//					true);
			Controller.logger_.warning("AemiliaManager's model has been set to sourceModel: " + aemiliaModelFilePath);
		}
		return model;
	}

	public String getRefactoredModelFilePath() {
		return getRefactoredModelBasePath() + getRefactoredModelFilePath() + getModelFileExtension();
	}

	@Override
	public String getModelFileExtension() {
		return "." + MODEL_FILE_EXTENSION;
	}

	// TO BE MOVED UP TO SUPER CLASS
	public String getMetamodelFileExtension() {
		return "." + METAMODEL_FILE_EXTENISON;
	}

	public static String getRewFileExtension() {
		return "." + REW_FILE_EXTENSION;
	}

	public static String getRewmappingFileExtension() {
		return "." + REWMAPPING_FILE_EXTENSION;
	}

	public void setModelPath(String modelPath) {
		this.setAemiliaModelFilePath(modelPath);
	}

	public String getRefactoredModelPath() {
		return refactoredModelFilePath;
	}

	public void setRefactoredModelPath(String refactoredModelPath) {
		this.refactoredModelFilePath = refactoredModelPath;
	}

	public String getRewFilePath() {
		return this.rewFilePath;
	}

	public void setRewFilePath(String rewFilePath) {
		this.rewFilePath = rewFilePath;
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	public String getRewmappingFilePath() {
		return getBaseFilePath() + rewmappingFilePath + getRewmappingFileExtension();
	}

	public void setRewmappingFilePath(String rewmappingFilePath) {
		this.rewmappingFilePath = rewmappingFilePath;
	}

	public String getBaseFilePath() {
		return baseFilePath;
	}

	public void setBaseFilePath(String baseFilePath) {
		this.baseFilePath = baseFilePath;
	}

	public String getAemiliaModelFilePath() {
		return aemiliaModelFilePath;
	}

	public void setAemiliaModelFilePath(String aemiliaModelFilePath) {
		this.aemiliaModelFilePath = aemiliaModelFilePath;
	}

	public String getRefactoreBaseFilePath() {
		return refactoreBaseFilePath;
	}

	public void setRefactoreBaseFilePath(String refactoreBaseFilePath) {
		this.refactoreBaseFilePath = refactoreBaseFilePath;
	}

	@Override
	public RefactoringAction getRandomAction(int length) throws UnexpectedException {

		// int index = JMetalRandom.getInstance().getRandomGenerator().nextInt(0, length
		// - 1);
		//
		// switch (index) {
		//
		// case 0:
		// return getRandomCloneAEIAction();
		// case 1:
		// return getRandomCapacityChangeAction();
		// case 2:
		// return getRandomRateChangeAction();
		// case 3:
		// return getRandomWeightChangeAction();
		// default:
		// throw new UnexpectedException("");
		// }

		System.out.println("Se leggi questo c'è un eerore in GetRandomAction");
		return null;
	}

	@Override
	public RefactoringAction getRandomAction(int length, RSequence seq) throws UnexpectedException {

		int index = 0;

		// TODO run with this nextInt in the loop
		do {
			index = JMetalRandom.getInstance().getRandomGenerator().nextInt(0, length - 1);
		} while (index != 0 && index != 2);

		switch (index) {

		case 0:
			return getRandomCloneAEIAction(seq);
		case 1:
			return getRandomCapacityChangeAction(seq);
		case 2:
			return getRandomRateChangeAction(seq);
		case 3:
			return getRandomWeightChangeAction(seq);
		default:
			throw new UnexpectedException("");
		}
	}

	// public long getNextUniqueIndex() {
	// return counter.getAndIncrement();
	// }

	private RefactoringAction getRandomCloneAEIAction(RSequence seq) {
		AEmiliaCloneAEIRefactoringAction action = new AEmiliaCloneAEIRefactoringAction(seq.getSolution());

		// EList<ArchiElemInstance> listOfClonableAEI =
		// action.getListOfClonableInstances();
		// int randomInt = RandomUtils.nextInt(0, listOfClonableAEI.size());
		// if (listOfClonableAEI.isEmpty()) {
		// Controller.logger_.warning("ERROR doen not exist any clonableAEI");
		// return null;
		// }
		// action.setSourceAEI(listOfClonableAEI.get(randomInt));
		// action.setModel(seq.getModel());
		// action.setSolution(seq.getSolution());
		// action.setName("AEmiliaCloneAEIAction");

		if (action.getSourceAEI() == null)
			return null;

		if (!action.isApplicable())
			return null;

		if (!isApplicable(action, seq))
			return null;

		// action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1,
		// MAX_VALUE));
		// action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1,
		// MAX_VALUE));
		// action.setParameters();
		// action.createPreCondition();
		// action.createPostCondition();
		return action;
	}

	public boolean isApplicable(AEmiliaCloneAEIRefactoringAction action, RSequence seq) {
		// TODO Auto-generated method stub
		return isClonable(action, seq);
	}

	private boolean isClonable(AEmiliaCloneAEIRefactoringAction action, RSequence seq) {
		// TODO Auto-generated method stub
		// EList<Attachment> listOfAttachment =
		// getAttachmentsOfAEI(action.getSourceAEI());
		if (action == null)
			return false;
		if (seq == null)
			return true;
		EList<ArchiElemInstance> listOfNeighs = getNeighsOfAEI(action.getSourceAEI());
		// if (listOfAttachment.isEmpty()) {
		// return false;
		// }
		// for (Attachment att : listOfAttachment) {
		// if
		// (!att.getEnd().getIsInput().getType().equals(metamodel.mmaemilia.InteractionType.UNI)
		// ||
		// !att.getStart().getIsOutput().getType().equals(metamodel.mmaemilia.InteractionType.UNI))
		// {
		// return false;
		// }
		// }

		for (Action a : seq.getRefactoring().getActions()) {
			boolean found = false;
			int i = 0;
			while (i < listOfNeighs.size() && !found) {
				ArchiElemInstance neigh = listOfNeighs.get(i);
				if (a instanceof AEmiliaCloneAEIRefactoringAction)
					if (((AEmiliaCloneAEIRefactoringAction) a).getSourceAEI().getInstanceName()
							.equals(neigh.getInstanceName()))
						found = true;
				i++;
			}
			if (found)
				return false;
		}
		return true;
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
		}
		return listOfAEIs;
	}

	public RefactoringAction getRandomCapacityChangeAction(RSequence seq) {
		if (getRandomCapacity(seq) == null) {
			System.out.println("Action is not applicable!!!!");
			return null;
		}
		AEmiliaConstChangesRefactoringAction action = new AEmiliaConstChangesRefactoringAction(seq.getSolution());
		action.setModel(seq.getModel());
		action.setSolution(seq.getSolution());
		action.setName("AEmiliaConstChangesAction");
		action.setSourceConstInit(getRandomCapacity(seq));
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));

		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		return action;
	}

	private RefactoringAction getRandomRateChangeAction(RSequence seq) {
		// if (getRandomRate(seq) == null) {
		// System.out.println("Action is not applicable!!!!");
		// return null;
		// }

		AEmiliaConstChangesRefactoringAction action = new AEmiliaConstChangesRefactoringAction(seq.getSolution());
		// action.setModel(seq.getModel());
		// action.setSolution(seq.getSolution());
		// action.setSourceConstInit(getRandomRate(seq));
		// action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1,
		// MAX_VALUE));
		//// action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1,
		// MAX_VALUE));
		// action.setName("AEmiliaConstChangesAction");
		//
		// action.setParameters();
		// action.createPreCondition();
		// action.createPostCondition();
		if (action.getSourceConstInit() == null)
			return null;

		if (!isApplicable(action, seq))
			return null;

		return action;
	}

	public boolean isApplicable(AEmiliaConstChangesRefactoringAction action, RSequence seq) {
		// TODO Auto-generated method stub
		if (action == null)
			return false;
		if (seq == null)
			return true;
		boolean found = false;
		int counter = 0;
		int i = 0;
		while (i < seq.getRefactoring().getActions().size() && !found) {
			Action a = seq.getRefactoring().getActions().get(i);
			if (a instanceof AEmiliaConstChangesRefactoringAction)
				if (((AEmiliaConstChangesRefactoringAction) a).getSourceConstInit().getName()
						.equals(action.getSourceConstInit().getName()))
					counter++;
			if (counter > 1)
				found = true;
			i++;
		}
		if (found)
			return false;
		return true;
	}

	public boolean isIn(AEmiliaCloneAEIRefactoringAction action, RSequence seq) {
		// TODO Auto-generated method stub
		boolean found = false;
		int i = 0;
		while (i < seq.getRefactoring().getActions().size() && !found) {
			Action a = seq.getRefactoring().getActions().get(i);
			if (a instanceof AEmiliaCloneAEIRefactoringAction)
				if ((((AEmiliaCloneAEIRefactoringAction) a).getSourceAEI().getInstanceName() + "_cloned_"
						+ ((AEmiliaCloneAEIRefactoringAction) a).getRandomCloneIndex()).equals(
								action.getSourceAEI().getInstanceName() + "_cloned_" + action.getRandomCloneIndex()))
					found = true;
			i++;
		}
		if (found)
			return true;
		return false;
	}

	public boolean isIn(AEmiliaConstChangesRefactoringAction action, RSequence seq) {
		// TODO Auto-generated method stub
		boolean found = false;
		int i = 0;
		while (i < seq.getRefactoring().getActions().size() && !found) {
			Action a = seq.getRefactoring().getActions().get(i);
			if (a instanceof AEmiliaConstChangesRefactoringAction)
				if (((AEmiliaConstChangesRefactoringAction) a).getSourceConstInit().getName()
						.equals(action.getSourceConstInit().getName()))
					found = true;
			i++;
		}
		if (found)
			return true;
		return false;
	}

	public boolean isInExcluding(AEmiliaCloneAEIRefactoringAction action, RSequence seq, int index) {
		// TODO Auto-generated method stub
		boolean found = false;
		int i = 0;
		while (i < seq.getRefactoring().getActions().size() && !found) {
			if (i != index) {
				Action a = seq.getRefactoring().getActions().get(i);
				if (a instanceof AEmiliaCloneAEIRefactoringAction)
					if ((((AEmiliaCloneAEIRefactoringAction) a).getSourceAEI().getInstanceName() + "_cloned_"
							+ ((AEmiliaCloneAEIRefactoringAction) a).getRandomCloneIndex())
									.equals(action.getSourceAEI().getInstanceName() + "_cloned_"
											+ action.getRandomCloneIndex()))
						found = true;
			}
			i++;
		}
		if (found)
			return true;
		return false;
	}

	public boolean isInExcluding(AEmiliaConstChangesRefactoringAction action, RSequence seq, int index) {
		// TODO Auto-generated method stub
		boolean found = false;
		int i = 0;
		while (i < seq.getRefactoring().getActions().size() && !found) {
			if (i != index) {
				Action a = seq.getRefactoring().getActions().get(i);
				if (a instanceof AEmiliaConstChangesRefactoringAction)
					if (((AEmiliaConstChangesRefactoringAction) a).getSourceConstInit().getName()
							.equals(action.getSourceConstInit().getName()))
						found = true;
			}
			i++;
		}
		if (found)
			return true;
		return false;
	}

	private RefactoringAction getRandomWeightChangeAction(RSequence seq) {
		if (getRandomWeight(seq) == null) {
			System.out.println("Action is not applicable!!!!");
			return null;
		}
		AEmiliaConstChangesRefactoringAction action = new AEmiliaConstChangesRefactoringAction(seq.getSolution());
		action.setModel(seq.getModel());
		action.setSourceConstInit(getRandomWeight(seq));
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setName("AEmiliaConstChangesAction");

		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		return action;
	}

	private Action getRandomMoveInputInteractionToNewAEIAction() {
		// System.out.println("--------------
		// getRandomMoveInputInteractionToNewAEIAction ------------------");
		// AEmiliaMoveInputInteractionAction action =
		// AEmiliaFactory.eINSTANCE.createAEmiliaMoveInputInteractionAction();
		// // Action action = new UMLAddComponentRefactoringAction(list_of_nodes);
		// action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1,
		// MAX_VALUE));
		// action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1,
		// MAX_VALUE));
		// action.setParameters();
		// action.createPreCondition();
		// action.createPostCondition();
		// ArchiElemInstance sourceAEI = (ArchiElemInstance) ((OclAemiliaManager)
		// Manager.getInstance(null)
		// .getOclManager()).evaluateQuery(OclAemiliaStringManager.getInstance().getRandomAEIQuery(0,
		// 3));
		// // action.setSourceAEI(sourceAEI);
		// System.out.println("--------------
		// getRandomMoveInputInteractionToNewAEIAction ------------------");
		// return action;
		return null;
	}

	private ConstInit getRandomCapacity(RSequence seq) {
		if (seq.getModel() == null) {
			Controller.logger_.warning("Model is null");
			return null;
		} else {
			EList<ConstInit> listOfConsts = ((AEmiliaSpecification) seq.getModel()).getArchiTypeDecl().getHeader()
					.getInitConst();

			List<ConstInit> listOfRandomCapacities = new ArrayList<>();

			for (ConstInit c : listOfConsts) {
				if (c.getName().contains("_size"))
					listOfRandomCapacities.add(c);
			}

			if (listOfRandomCapacities.isEmpty()) {
				System.out.println("Action is not applicable!!!!");
				return null;
			}

			int rangeMin = 0;
			int rangeMax = listOfRandomCapacities.size();
			return listOfRandomCapacities.get((int) (RandomUtils.nextInt(rangeMin, rangeMax)));
		}
	}

	// private ConstInit getRandomRate(RSequence seq) {
	// EList<ConstInit> listOfConsts = ((AEmiliaSpecification)
	// seq.getModel()).getArchiTypeDecl().getHeader()
	// .getInitConst();
	//
	// List<ConstInit> listOfRandomRanges = new ArrayList<>();
	//
	// for (ConstInit c : listOfConsts) {
	// if (c.getInitConstData() instanceof Special
	// && ((Special) c.getInitConstData()).getType() == SpecialType.RATE)
	// listOfRandomRanges.add(c);
	// }
	//
	// if (listOfRandomRanges.isEmpty()) {
	// System.out.println("Action is not applicable!!!!");
	// return null;
	// }
	//
	// int rangeMin = 0;
	// int rangeMax = listOfRandomRanges.size();
	// return listOfRandomRanges.get((int) (RandomUtils.nextInt(rangeMin,
	// rangeMax)));
	// }

	private ConstInit getRandomWeight(RSequence seq) {
		EList<ConstInit> listOfConsts = ((AEmiliaSpecification) seq.getModel()).getArchiTypeDecl().getHeader()
				.getInitConst();
		List<ConstInit> listOfRandomWeights = new ArrayList<>();
		if (listOfRandomWeights.isEmpty()) {
			return null;
		} else {
			for (ConstInit c : listOfConsts) {
				if (c.getInitConstData() instanceof Special
						&& ((Special) c.getInitConstData()).getType() == SpecialType.WEIGHT)
					listOfRandomWeights.add(c);
			}
			int rangeMin = 0;
			int rangeMax = listOfRandomWeights.size();
			return listOfRandomWeights.get((int) (RandomUtils.nextInt(rangeMin, rangeMax)));
		}
	}

	@Override
	public OclManager getOclManager() {
		if (oclManager == null) {
			oclManager = new OclAemiliaManager();
		}
		return oclManager;
	}

	@Override
	public void setModel(EObject model) {
		this.model = (AEmiliaSpecification) model;
	}

	public TwoEaglesBridge getTwoEaglesBridge() {
		if (twoEaglesBridge == null) {
			// twoEaglesBridge = new
			// TwoEaglesBridge(Manager.getInstance(null).getController().getTwoTowersKernelPath());
			twoEaglesBridge = new TwoEaglesBridge();
			String twoTowersKernelPath = Manager.getInstance(null).getController().getTwoTowersKernelPath();
			twoEaglesBridge.setTwoTowersKernelPath(twoTowersKernelPath);
			twoEaglesBridge.setResourceSet(this.getResourceSet());
			twoEaglesBridge.setAemiliaSpecification(getModel());
		}
		return twoEaglesBridge;
	}

	public void setTwoEaglesBridge(TwoEaglesBridge twoEaglesBridge) {
		this.twoEaglesBridge = twoEaglesBridge;
	}

	public String getSourceValFilePath() {
		return sourceValFilePath;
	}

	public void setSourceValFilePath(String valFilePath) {
		sourceValFilePath = valFilePath;
	}

	public void refreshModel(String mmaemiliaFilePath) {
		getResourceSet().getResources().get(0).unload();
		Resource res = getResourceSet().getResource(Manager.string2Uri(mmaemiliaFilePath), true);
		assert (res.getContents().get(0).equals((AEmiliaSpecification) EcoreUtil.getObjectByType(res.getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION)));
		this.model = (AEmiliaSpecification) EcoreUtil.getObjectByType(res.getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
	}
}
