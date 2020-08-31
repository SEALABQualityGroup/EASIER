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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.uma.jmetal.problem.Problem;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSequence;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.SourceModel;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;

public abstract class MetamodelManager {

	protected Path modelUri;
	protected OclManager oclManager;
	protected OclStringManager oclStringManager;
	protected Manager manager;
	protected Controller controller;

	/* Source models */
	protected List<Path> sourceModelsPath = new ArrayList<>();
	protected List<SourceModel> sourceModels = new ArrayList<SourceModel>();

	// protected String modelPath;

	protected ResourceSet resourceSet;
	protected Resource resource;

	protected static String REFACTORED_MODEL_BASE_PATH;

	public static final double MAX_VALUE = 100;

	protected RProblem<?> problem;

	private Map<UUID, ResourceSet> resourceSetMap = new HashMap<>();

	public abstract void init(Path modelUri);

	public abstract EObject getModel();

	public abstract EObject getModel(final Path sourcePath);

	public abstract void setModel(EObject model);

	public abstract boolean isApplicable(RefactoringAction act, RSequence sequence);

	public abstract String getModelFileExtension();

	public abstract String getMetamodelFileExtension();

	public abstract OclStringManager getOclStringManager();

	public abstract RefactoringAction getRandomAction(int n, RSequence seq) throws UnexpectedException;

	public abstract void packageRegistering();

	public abstract void createNewResourceSet();

	public abstract void refreshModel(Path sourceModelPath);

	public abstract OclManager getOclManager();

	public MetamodelManager() {
		resourceSet = new ResourceSetImpl();
	}

	public Resource getResource() {
		if (resource == null) {
			resource = getResourceSet().getResources().get(0);
		}

		return resource;
	}

	public void setProblem(final Problem p) {
		this.problem = (RProblem) p;
	}

	public Path getModelUri() {
		return modelUri;
	}

	public void setModelUri(Path modelUri) {
		this.modelUri = modelUri;
	}

	public void setOclManager(OclManager oclManager) {
		this.oclManager = oclManager;
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
//			EasierLogger.logger_.info("unload Resources");
		}
	}

	public void unloadModelResource(RSolution solution) {
		for (Iterator<Resource> i = solution.getResources().iterator(); i.hasNext();) {
			Resource current = (Resource) i.next();
			current.unload();
			i.remove();
		}
//		EasierLogger.logger_.info("unload Resources");
	}

	public boolean saveModel() {
		try {
			getResource().save(null);
			return true;
		} catch (IOException e) {
			System.err.println("Error in saving the model " + getResource().toString());
			e.printStackTrace();
			return false;
		}
	}

	public boolean saveModel(Resource modelToSave) {
		try {
			modelToSave.save(null);
			return true;
		} catch (IOException e) {
			System.err.println("Error in saving the model " + modelToSave.toString());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param destionationPath
	 * @see http://download.eclipse.org/modeling/emf/emf/javadoc/2.6.0/org/eclipse/emf/ecore/util/EcoreUtil.Copier.html#EcoreUtil.Copier(boolean)
	 */
	public void save(String destionationPath) {
		Resource res = getResourceSet().createResource(URI.createFileURI(destionationPath));
		res.getContents().add(EcoreUtil.copy(this.getModel()));
		try {
			res.save(null);
		} catch (IOException e) {
			System.err.println("Error in saving the model to -->" + destionationPath);
			e.printStackTrace();
		}
	}

	public void save(RSolution solution) {
		try {
//			if (solution.getResources().isEmpty()) {
//				EasierLogger.logger_.warning("RSolution doesn't have resources");
//			}
//		save(solution.getModelPath().toString());
//			solution.getResources().get(0).save(null);
			solution.getModel().eResource().save(null);

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

	public void setSourceModelsPath(final List<Path> modelsPath) {
		modelsPath.forEach(model -> sourceModelsPath.add(model.resolve("model" + getMetamodelFileExtension())));
	}

	public void setSourceModels(final List<SourceModel> models) {
		sourceModels.addAll(models);
	}

}
