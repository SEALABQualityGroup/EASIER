package it.univaq.disim.sealab.metaheuristic.managers;

import java.io.IOException;
import java.nio.file.Path;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.eclipse.emf.common.command.AbortExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import it.univaq.disim.sealab.metaheuristic.actions.aemilia.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSequence;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.SourceModel;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
import logicalSpecification.Action;
import metamodel.mmaemilia.AEmiliaSpecification;

public abstract class MetamodelManager {

	protected Path modelUri;
	protected OclManager oclManager;
	protected OclStringManager oclStringManager;
	protected Manager manager;
	protected Controller controller;

	/* Source models */
	protected List<Path> sourceModelsPath = new ArrayList<>();
	protected List<SourceModel> sourceModels = new ArrayList();

	// protected String modelPath;

	protected ResourceSet resourceSet;
	protected Resource resource;

	protected static String REFACTORED_MODEL_BASE_PATH;

	public static final double MAX_VALUE = 100;

	private Map<UUID, ResourceSet> resourceSetMap = new HashMap<>();

	public abstract void init(Path modelUri);

	public abstract EObject getModel();

	public abstract EObject getModel(final Path sourcePath);

	public abstract void setModel(EObject model);

	public abstract String getModelFileExtension();

	public abstract String getMetamodelFileExtension();

	public Resource getResource() {
		return resource;
	}

	public Path getModelUri() {
		return modelUri;
	}

	public void setModelUri(Path modelUri) {
		this.modelUri = modelUri;
	}

	public abstract OclManager getOclManager();

	public void setOclManager(OclManager oclManager) {
		this.oclManager = oclManager;
	}

	public OclStringManager getOclStringManager() {
		return oclStringManager;
	}

	public void setOclStringManager(OclStringManager oclStringManager) {
		this.oclStringManager = oclStringManager;
	}

	public void unloadModelResource() {
		if (getResource() != null) { // unload previous resources if existing
			// unload every resource in the resourceSet including profiles
			for (Iterator<Resource> i = getResourceSet().getResources().iterator(); i.hasNext();) {
				Resource current = (Resource) i.next();
				current.unload();
				i.remove();
			}
			Controller.logger_.info("unload Resources");
		}
	}

	public void unloadModelResource(RSolution solution) {
		assert (solution.getResources().size() == 1);
		for (Iterator<Resource> i = solution.getResources().iterator(); i.hasNext();) {
			Resource current = (Resource) i.next();
			current.unload();
			i.remove();
		}
		Controller.logger_.info("unload Resources");
	}

	public boolean saveModel() {
		try {
			getResource().save(null);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean saveModel(Resource modelToSave) {
		try {
			modelToSave.save(null);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param destionationPath
	 * @see http://download.eclipse.org/modeling/emf/emf/javadoc/2.6.0/org/eclipse/emf/ecore/util/EcoreUtil.Copier.html#EcoreUtil.Copier(boolean)
	 */
	// PAKIMOR _FIXME before these modifications we created a copy with links
	// between the source and the generated resources
	public void save(String destionationPath) {
		// original resources
		// Resource resource = this.resourceSet.getResources().get(0);

		// it is mandatory to comment this method
		// unloadModelResource();

		Resource res = getResourceSet().createResource(manager.string2FileUri(destionationPath));

		// res.getContents().add(getModel());
		// assert(resource.getContents().get(0).equals(getModel()));

		// res.getContents().add(EcoreUtil.copy(resource.getContents().get(0)));
		res.getContents().add(EcoreUtil.copy(this.getModel()));

		try {
			res.save(null);
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		// unloadModelResource();
		// init(getModelUri());
		// unloadModelResource();
		// init(getModelUri());
	}

	public void save(RSolution solution) {
		try {
			if (solution.getResources() == null) {
				Controller.logger_.warning("RSolution doesn't have resources");
			}
			assert (solution.getResources().get(0).getContents().get(0).equals(solution.getModel()));

			solution.getResources().get(0).save(null);

		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}

	public void save(String destionationPath, RSolution solution) {
		try {
			solution.getResources().get(0).save(null);
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}

	public ResourceSet getResourceSet() {
		if (resourceSet == null)
			resourceSet = new ResourceSetImpl();
		return resourceSet;
	}

	public void setResourceSet(ResourceSet set) {
		this.resourceSet = set;
	}

//	public void setRefactoredModelBasePath(String basePath) {
//		setREFACTORED_MODEL_BASE_PATH(basePath);
//	}
//
//	public String getRefactoredModelBasePath() {
//		return getREFACTORED_MODEL_BASE_PATH();
//	}
//
//	public String getREFACTORED_MODEL_BASE_PATH() {
//		return REFACTORED_MODEL_BASE_PATH;
//	}
//
//	public void setREFACTORED_MODEL_BASE_PATH(String rEFACTORED_MODEL_BASE_PATH) {
//		REFACTORED_MODEL_BASE_PATH = rEFACTORED_MODEL_BASE_PATH;
//	}

	public void setSourceModelsPath(final List<Path> modelsPath) {
		modelsPath.forEach(model -> sourceModelsPath.add(model.resolve("model" + getMetamodelFileExtension())));
	}
	
	public void setSourceModels(final List<SourceModel> models) {
		sourceModels.addAll(models);
	}

	public abstract Action getRandomAction(int n) throws UnexpectedException;

	public abstract RefactoringAction getRandomAction(int n, RSequence seq) throws UnexpectedException;

	public abstract void packageRegistering();

	public abstract void createNewResourceSet();

	public abstract void refreshModel(Path sourceModelPath);
}
