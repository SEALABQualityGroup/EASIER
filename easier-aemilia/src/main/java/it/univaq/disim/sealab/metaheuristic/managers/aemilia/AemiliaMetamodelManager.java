package it.univaq.disim.sealab.metaheuristic.managers.aemilia;

import java.nio.file.Path;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.AEmiliaCloneAEIRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.AEmiliaConstChangesRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaController;
import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaRSequence;
import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSequence;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaStringManager;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import it.univaq.disim.sealab.ttep.rew.classes.MeasureDef;
import it.univaq.disim.sealab.ttep.rew.wizard.providers.ExtractedIndex;
import it.univaq.disim.sealab.twoeagles_bridge.TwoEaglesBridge;
import logicalSpecification.Action;
import mapmeasurestoindices.MapmeasurestoindicesFactory;
import mapmeasurestoindices.MapmeasurestoindicesPackage;
import mapmeasurestoindices.RewMapping;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.Attachment;
import metamodel.mmaemilia.ElemType;
import metamodel.mmaemilia.mmaemiliaPackage;
import metamodel.mmaemilia.Behavior.BehaviorFactory;
import metamodel.mmaemilia.Behavior.ChoiceProcess;
import metamodel.mmaemilia.Behavior.ProcessTerm;
import metamodel.mmaemilia.DataType.Special;
import metamodel.mmaemilia.DataType.SpecialType;
import metamodel.mmaemilia.Headers.ConstInit;

public class AemiliaMetamodelManager extends MetamodelManager {

	private TwoEaglesBridge twoEaglesBridge;

	private static final String MODEL_FILE_EXTENSION = "aem";
	private static final String METAMODEL_FILE_EXTENISON = "mmaemilia";
	private static final String REW_FILE_EXTENSION = "rew";
	private static final String REWMAPPING_FILE_EXTENSION = "rewmapping";

	private ResourceSet resourceSet;
	// private Resource emiliaResource;

	private Path rewFilePath;
	private Path aemiliaModelFilePath; // .mmaemilia
	private Path sourceValFilePath;
	private Path refactoredModelFilePath;
	private Path aemFilePath; // .aem
	private Path outputFilePath;
	private Path rewmappingFilePath;
	// private Path baseFilePath = "";
	// private Path refactoreBaseFilePath = "";

	private AEmiliaSpecification model;

	private Resource rewMappingResource;



	public AemiliaMetamodelManager(Controller ctrl) {
		controller = (AemiliaController) ctrl;
	}

	public void init(Path modelUri) {
		System.out.println("INIT method of AEmiliaManager");
		//
		// setAemFilePath(modelUri + getModelFileExtension());
		// setAemiliaModelFilePath(modelUri + getMetamodelFileExtension());
		// setRewFilePath(modelUri + getRewFileExtension());
		// setRewmappingFilePath(modelUri + getRewmappingFileExtension());
		//
		// // unloadModelResource();
		// packageRegistering();
		// getOclManager().inizialize(getResourceSet());
		//
		// Path ameliaAbsolutePath = getAemiliaModelFilePath();
		// URI uri = URI.createFileURI(ameliaAbsolutePath.toString());
		// resource = getResourceSet().getResource(uri, true);
		//
		// model = (AEmiliaSpecification)
		// EcoreUtil.getObjectByType(getResource().getContents(),
		// mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
		//// setREFACTORED_MODEL_BASE_PATH("/src/main/resources/models/refactored/");
	}

	public void gaussianEliminationSRBMC(Path aemFilePath, Path rewFilePath, Path outputFilePath) {
		getTwoEaglesBridge().gaussianEliminationSRBMC(aemFilePath, rewFilePath, outputFilePath);
	}

	public void sorSRBMC(Path aemFilePath, Path rewFilePath, Path outputFilePath) {
		getTwoEaglesBridge().sorSRBMC(aemFilePath, rewFilePath, outputFilePath);
	}

	public void aemiliaModelUpdate(final Path valFilePath, final Path rewFilePath, final Path rewMappingFilePath,
			final Path refactoredMmAemiliaFilePath, final AemiliaRSolution solution) {
		try {
			Resource rewMappingResource = createRewmappingResource(rewMappingFilePath);
			RewMapping rewMapping = MapmeasurestoindicesFactory.eINSTANCE.createRewMapping();
			getTwoEaglesBridge().measuresToIndices(rewMappingResource, rewMapping, rewFilePath,
					(AEmiliaSpecification) getResource(refactoredMmAemiliaFilePath).getContents().get(0));

			getTwoEaglesBridge().aemiliaModelUpdate(valFilePath, rewMappingFilePath,
					(AEmiliaSpecification) getResource(refactoredMmAemiliaFilePath).getContents().get(0));
		} catch (Exception e) {
			System.err.println(ExceptionUtils.getStackTrace(e));
			System.err.println("Solution number: " + solution.getName());
		}
	}

	@Deprecated
	public Resource getResource(String aemFilePath) {
		URI uri = URI.createFileURI(aemFilePath);
		Resource aemiliaResource = getResourceSet().getResource(uri, true);
		return aemiliaResource;
	}

	public Resource getResource(Path aemFilePath) {
		// String ameliaAbsolutePath = getAemiliaModelFilePath();
		final URI uri = URI.createFileURI(aemFilePath.toString());
		Resource aemiliaResource = getResourceSet().getResource(uri, true);
		return aemiliaResource;
	}

	public Resource createRewmappingResource() {
		packageRegistering();
		if (rewMappingResource == null) {
			URI fileURI = URI.createFileURI(rewmappingFilePath.toString());
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

	public Resource createRewmappingResource(Path rewmappingFilePath) {
		packageRegistering();
		final URI fileURI = URI.createFileURI(rewmappingFilePath.toString());
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

	public Path getAemFilePath() {
		// return getBasePath() + aemFilePath + getModelFileExtension();
		return aemFilePath;
	}

	public void setAemFilePath(Path aemFile) {
		this.aemFilePath = aemFile;
	}

	@Override
	public AEmiliaSpecification getModel() {
		if (model == null) {
			this.packageRegistering();
			if (aemiliaModelFilePath == null) {
				aemiliaModelFilePath = ((RProblem)problem).getSourceModelPath();
			}
			this.model = createModel(aemiliaModelFilePath);
			// this.model = (AEmiliaSpecification)
			// getResourceSet().getResource(Manager.string2Uri(aemiliaModelFilePath),
			// true);
			EasierLogger.logger_.warning("AemiliaMetamodelManager's model has been set to sourceModel: " + aemiliaModelFilePath);
		}
		return model;
	}

	/**
	 * It returns a new aemilia model
	 * 
	 * @param sourceFolderPath
	 *            is the source folder path
	 * 
	 * @see it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager#getModel(java.nio.file.Path)
	 */
	@Override
	public AEmiliaSpecification getModel(final Path sourceFolderPath) {
		this.packageRegistering();
		return createModel(sourceFolderPath);
		// return createModel(sourcePath.resolve("model." +
		// METAMODEL_FILE_EXTENISON).toString());
	}

	private AEmiliaSpecification createModel(Path aemiliaModelFilePath) {
		return (AEmiliaSpecification) EcoreUtil.getObjectByType(
				getResourceSet().getResource(Manager.string2Uri(aemiliaModelFilePath.toString()), true).getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
	}

	@Override
	public String getModelFileExtension() {
		return "." + MODEL_FILE_EXTENSION;
	}

	public String getMetamodelFileExtension() {
		return "." + METAMODEL_FILE_EXTENISON;
	}

	public static String getRewFileExtension() {
		return "." + REW_FILE_EXTENSION;
	}

	public static String getRewmappingFileExtension() {
		return "." + REWMAPPING_FILE_EXTENSION;
	}

	public void setModelPath(Path modelPath) {
		this.aemFilePath = modelPath;
	}

	public Path getAemiliaModelFilePath() {
		return aemiliaModelFilePath;
	}

	public void setAemiliaModelFilePath(Path aemiliaModelFilePath) {
		this.aemiliaModelFilePath = aemiliaModelFilePath;
	}

	@Override
	public RefactoringAction getRandomAction(int length) throws UnexpectedException {
		System.out.println("Se leggi questo c'è un erore in GetRandomAction");
		return null;
	}

	@Override
	public RefactoringAction getRandomAction(int length, RSequence seq) throws UnexpectedException {

		int index = 0;

		// TODO run with this nextInt in the loop
		// do {
		index = JMetalRandom.getInstance().getRandomGenerator().nextInt(0, length - 1);
		// } while (index != 0 && index != 2);

		switch (index) {

		case 0:
			return getRandomCloneAEIAction((AemiliaRSequence) seq);
		case 1:
			return getRandomCapacityChangeAction((AemiliaRSequence) seq);
		case 2:
			return getRandomRateChangeAction((AemiliaRSequence) seq);
		case 3:
			return getRandomWeightChangeAction((AemiliaRSequence) seq);
		case 4:
			return getRandomWorkloadChangeAction((AemiliaRSequence) seq);
		default:
			throw new UnexpectedException("");
		}
	}

	private RefactoringAction getRandomCloneAEIAction(AemiliaRSequence seq) {
		AEmiliaCloneAEIRefactoringAction action = null;
		try {
			action = new AEmiliaCloneAEIRefactoringAction((AemiliaRSolution) seq.getSolution());

			if (action.getSourceAEI() == null)
				return null;

			if (!action.isApplicable())
				return null;

			if (!isApplicable(action, seq))
				return null;
		} catch (IllegalArgumentException e) {
			AemiliaRSolution s = (AemiliaRSolution) seq.getSolution();
			System.out.println(seq.toString());
			System.out.println(s.toString());
			e.printStackTrace();
		} 
		return action;
	}

	public boolean isApplicable(AEmiliaCloneAEIRefactoringAction action, AemiliaRSequence seq) {
		return isClonable(action, seq);
	}

	private boolean isClonable(AEmiliaCloneAEIRefactoringAction action, AemiliaRSequence seq) {
		if (action == null)
			return false;
		if (seq == null)
			return true;
		EList<ArchiElemInstance> listOfNeighs = getNeighsOfAEI(action.getSourceAEI());

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

	public RefactoringAction getRandomCapacityChangeAction(AemiliaRSequence seq) {
		ConstInit sourceConst = getRandomCapacity(seq);
		if (sourceConst == null) {
			System.out.println("Action is not applicable!!!!");
			return null;
		}
		AEmiliaConstChangesRefactoringAction action = new AEmiliaConstChangesRefactoringAction((AemiliaRSolution) seq.getSolution());
		action.setModel(seq.getModel());
		action.setSolution(seq.getSolution());
		action.setName("AEmiliaConstChangesCapacityAction");
		// action.setSourceConstInit(sourceConst);
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(controller.getConfigurator().getConstChangesWeight());

		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		return action;
	}

	private RefactoringAction getRandomRateChangeAction(AemiliaRSequence seq) {
		ConstInit sourceConst = getRandomRate(seq);
		if (sourceConst == null) {
			System.out.println("Action is not applicable!!!!");
			return null;
		}

		AEmiliaConstChangesRefactoringAction action = new AEmiliaConstChangesRefactoringAction((AemiliaRSolution) seq.getSolution());
		action.setModel(seq.getModel());
		action.setSolution(seq.getSolution());
		action.setName("AEmiliaConstChangesRateAction");
		// action.setSourceConstInit(sourceConst);
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(controller.getConfigurator().getConstChangesWeight());

		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();

		if (action.getSourceConstInit() == null)
			return null;

		if (!isApplicable(action, seq))
			return null;

		return action;
	}

	private RefactoringAction getRandomWorkloadChangeAction(AemiliaRSequence seq) {
		if (controller.getConfigurator().getWorkloadRange() == -1) {
			return null;
		}
		ConstInit sourceConst = getRandomWorkload(seq);
		if (sourceConst == null) {
			System.out.println("Action is not applicable!!!!");
			return null;
		}

		AEmiliaConstChangesRefactoringAction action = new AEmiliaConstChangesRefactoringAction((AemiliaRSolution) seq.getSolution());
		action.setModel(seq.getModel());
		action.setSolution(seq.getSolution());
		// action.setSourceConstInit(sourceConst);
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(controller.getConfigurator().getConstChangesWeight());
		action.setName("AEmiliaConstChangesWorkloadAction");

		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		if (action.getSourceConstInit() == null)
			return null;

		if (!isApplicable(action, seq))
			return null;

		return action;
	}

	public boolean isApplicable(AEmiliaConstChangesRefactoringAction action, AemiliaRSequence seq) {
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

	public boolean isIn(AEmiliaCloneAEIRefactoringAction action, AemiliaRSequence seq) {
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

	public boolean isIn(AEmiliaConstChangesRefactoringAction action, AemiliaRSequence seq) {
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

	public boolean isInExcluding(AEmiliaCloneAEIRefactoringAction action, AemiliaRSequence seq, int index) {
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

	public boolean isInExcluding(AEmiliaConstChangesRefactoringAction action, AemiliaRSequence seq, int index) {
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

	private RefactoringAction getRandomWeightChangeAction(AemiliaRSequence seq) {
		ConstInit sourceConst = getRandomWeight(seq);
		if (sourceConst == null) {
			System.out.println("Action is not applicable!!!!");
			return null;
		}
		AEmiliaConstChangesRefactoringAction action = new AEmiliaConstChangesRefactoringAction((AemiliaRSolution) seq.getSolution());
		action.setModel(seq.getModel());
		action.setSourceConstInit(sourceConst);
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(controller.getConfigurator().getConstChangesWeight());
		action.setName("AEmiliaConstChangesWeightAction");

		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		if (action.getSourceConstInit() == null)
			return null;

		if (!isApplicable(action, seq))
			return null;
		return action;
	}

	private ConstInit getRandomCapacity(AemiliaRSequence seq) {
		if (seq.getModel() == null) {
			EasierLogger.logger_.warning("Model is null");
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

	private ConstInit getRandomWeight(AemiliaRSequence seq) {
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
			int rangeMax = listOfRandomWeights.size() - 1;

			return listOfRandomWeights.get(JMetalRandom.getInstance().getRandomGenerator().nextInt(rangeMin, rangeMax));
		}
	}

	private ConstInit getRandomRate(AemiliaRSequence seq) {
		EList<ConstInit> listOfConsts = ((AEmiliaSpecification) seq.getModel()).getArchiTypeDecl().getHeader()
				.getInitConst();
		List<ConstInit> listOfRandomWeights = new ArrayList<>();
		if (!listOfRandomWeights.isEmpty()) {
			return null;
		} else {
			for (ConstInit c : listOfConsts) {
				if (c.getInitConstData() instanceof Special
						&& ((Special) c.getInitConstData()).getType() == SpecialType.RATE)
					listOfRandomWeights.add(c);
			}
			int rangeMin = 0;
			int rangeMax = listOfRandomWeights.size() - 1;

			return listOfRandomWeights.get(JMetalRandom.getInstance().getRandomGenerator().nextInt(rangeMin, rangeMax));
		}
	}

	private ConstInit getRandomWorkload(AemiliaRSequence seq) {
		EList<ConstInit> listOfConsts = ((AEmiliaSpecification) seq.getModel()).getArchiTypeDecl().getHeader()
				.getInitConst();
		List<ConstInit> listOfRandomWeights = new ArrayList<>();
		if (!listOfRandomWeights.isEmpty()) {
			return null;
		} else {
			for (ConstInit c : listOfConsts) {
				if (c.getInitConstData() instanceof Special
						&& ((Special) c.getInitConstData()).getType() == SpecialType.RATE
						&& c.getName().contains("workload"))
					listOfRandomWeights.add(c);
			}
			int rangeMin = 0;
			int rangeMax = listOfRandomWeights.size() - 1;

			return listOfRandomWeights.get(JMetalRandom.getInstance().getRandomGenerator().nextInt(rangeMin, rangeMax));
		}
	}

	@Override
	public OclManager getOclManager() {
		return new OclAemiliaManager((AemiliaController) controller);
	}

	@Override
	public void setModel(EObject model) {
		this.model = (AEmiliaSpecification) model;
	}

	public TwoEaglesBridge getTwoEaglesBridge() {
		if (twoEaglesBridge == null) {
			twoEaglesBridge = new TwoEaglesBridge();
			Path twoTowersKernelPath = controller.getConfigurator().getTTKernel();
			twoEaglesBridge.setTwoTowersKernelPath(twoTowersKernelPath);
			twoEaglesBridge.setResourceSet(this.getResourceSet());
			// twoEaglesBridge.setAemiliaSpecification(getModel());
		}
		return twoEaglesBridge;
	}

	public void setTwoEaglesBridge(TwoEaglesBridge twoEaglesBridge) {
		this.twoEaglesBridge = twoEaglesBridge;
	}

	public Path getSourceValFilePath() {
		return sourceValFilePath;
	}

	public void setSourceValFilePath(Path valFilePath) {
		sourceValFilePath = valFilePath;
	}

	public void refreshModel(String mmaemiliaFilePath) {
		getResourceSet().getResources().get(0).unload();
		Resource res = getResourceSet().getResource(Manager.string2Uri(mmaemiliaFilePath), true);
		this.model = (AEmiliaSpecification) EcoreUtil.getObjectByType(res.getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
	}

	public void addAvailabilityChoice(AEmiliaSpecification targetModel) {

		for (ElemType aet : targetModel.getArchiTypeDecl().getAetDeclaration().getEtDeclaration()) {

			ChoiceProcess availabilityChoice = BehaviorFactory.eINSTANCE.createChoiceProcess();

			ProcessTerm pt = BehaviorFactory.eINSTANCE.createProcessTerm();

			availabilityChoice.getProcesses().add(aet.getBehaviorDecl().getEquations().get(0).getPt());

		}
	}

	private boolean containsCloningInstance(List<RefactoringAction> refactoring) {
		for (RefactoringAction action : refactoring) {
			if (action instanceof AEmiliaCloneAEIRefactoringAction) {
				return true;
			}
		}
		return false;
	}

	public void save(AemiliaRSolution solution) {
		super.save(solution);
		packageRegistering();
		AEmiliaSpecification savedModel = (AEmiliaSpecification) EcoreUtil.getObjectByType(getResourceSet()
				.getResource(Manager.string2Uri(solution.getMmaemiliaFilePath().toString()), true).getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);

		try {
			if (containsCloningInstance(solution.getVariableValue(0).getRefactoring().getActions()))
				if (!isThereAnyClone(savedModel)) {
					throw new Exception("error in clone action");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isThereAnyClone(AEmiliaSpecification savedModel) {
		for (ArchiElemInstance aei : savedModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl()) {
			if (aei.getInstanceName().contains("_cloned_")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void createNewResourceSet() {
		// TODO Auto-generated method stub
	}

	/**
	 * Gets as input the mmaemilia file path
	 * 
	 * @param sourceModelPath
	 */
	public void refreshModel(final Path sourceModelPath) {
		getResourceSet().getResources().get(0).unload();
		Resource res = getResourceSet().getResource(Manager.string2Uri(sourceModelPath.toString()), true);

		this.model = (AEmiliaSpecification) EcoreUtil.getObjectByType(res.getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
	}

	@Override
	public OclStringManager getOclStringManager() {
		if(oclStringManager == null)
			oclStringManager = new OclAemiliaStringManager();
		return oclStringManager;
	}

	@Override
	public boolean isApplicable(RefactoringAction action, RSequence seq) {
		return isClonable((AEmiliaCloneAEIRefactoringAction) action, (AemiliaRSequence) seq);
	}
}
