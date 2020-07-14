package it.univaq.disim.sealab.epsilon;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.plainxml.PlainXmlModel;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

import it.univaq.disim.sealab.epsilon.utility.Utility;

public abstract class EpsilonStandalone {

	protected IEolModule module;
	protected List<Variable> parameters = new ArrayList<Variable>();

	private static Path metamodelPath;
	
	protected static Path rulePath;

	protected Object result;

	public abstract IEolModule createModule();

	public abstract Path getSource() throws Exception;

	public abstract IModel getModel(Path modelFilePath) throws Exception;

	public abstract IModel getModel(Path modelFilePath, Path metamodelPath) throws Exception;

	public abstract void postProcess(Path destFilePath);

	public abstract void preProcess();

	/**
	 * 
	 * @param mmaemiliaFilePath
	 * @param destFilePath
	 * @param ruleFilePath
	 * @throws Exception
	 */
	public void execute(Path mmaemiliaFilePath, Path destFilePath) throws Exception {

		doExecute(mmaemiliaFilePath);

		postProcess(destFilePath);

		module.getContext().getModelRepository().dispose();
	}
	
	public void execute(Path mmaemiliaFilePath) throws Exception {

		doExecute(mmaemiliaFilePath);

		module.getContext().getModelRepository().dispose();
	}
	
	private void doExecute(Path mmaemiliaFilePath) throws Exception {
		module = createModule();
//		module.parse(getRulePath().toFile());
		module.parse(getSource().toFile());

		if (module.getParseProblems().size() > 0) {
			System.err.println("Parse errors occured...");
			for (ParseProblem problem : module.getParseProblems()) {
				System.err.println(problem.toString());
			}
//			return;
		}

//		for (IModel model : getModels()) {
		module.getContext().getModelRepository().addModel(getModel(mmaemiliaFilePath, getMetamodelPath()));
//		}

		for (Variable parameter : parameters) {
			module.getContext().getFrameStack().put(parameter);
		}

		preProcess();
		result = execute(module);
	}

	public static Path getMetamodelPath() throws IOException {
		if(Files.exists(Paths.get("/tmp/mmAemlia.ecore")))
			metamodelPath = Paths.get("/tmp/mmAemlia.ecore");
		if (metamodelPath == null) {
			InputStream mmIn = EpsilonHelper.class.getClassLoader().getResourceAsStream("metamodels/mmAEmilia.ecore");
//			metamodelPath = Files.createTempFile("", "");
			metamodelPath = Paths.get("/tmp/mmAemlia.ecore");
			Files.copy(mmIn, metamodelPath);
		}
		return metamodelPath;
	}

	public List<Variable> getParameters() {
		return parameters;
	}
	
	public void setSource(Path rp) {
		rulePath = rp;
	}

	protected Object execute(IEolModule module) throws EolRuntimeException {
		return module.execute();
	}

	public synchronized EmfModel createEmfModel(String name, Path model, String metamodel, boolean readOnLoad,
			boolean storeOnDisposal) {
		EmfModel emfModel = new EmfModel();
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_NAME, name);
		properties.put(EmfModel.PROPERTY_FILE_BASED_METAMODEL_URI, metamodel);
		properties.put(EmfModel.PROPERTY_MODEL_URI, model.toUri().toString());
		properties.put(EmfModel.PROPERTY_READONLOAD, readOnLoad + "");
		properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, storeOnDisposal + "");
		try {
			emfModel.load(properties, (IRelativePathResolver) null);
		} catch (EolModelLoadingException e) {
			System.err.println("Error in loading the model with properties!");
			e.printStackTrace();
		}
		return emfModel;
	}

	public EmfModel createOutputEmfModel(String name, String model, String metamodel, boolean readOnLoad,
			boolean storeOnDisposal) {
		EmfModel emfModel = new EmfModel();
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_NAME, name);
		properties.put(EmfModel.PROPERTY_FILE_BASED_METAMODEL_URI,
				Utility.getFileFromResource(metamodel).toURI().toString());
		properties.put(EmfModel.PROPERTY_MODEL_URI, Utility.createFile(model).toURI().toString());
		properties.put(EmfModel.PROPERTY_READONLOAD, readOnLoad + "");
		properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, storeOnDisposal + "");
		try {
			emfModel.load(properties, (IRelativePathResolver) null);
		} catch (EolModelLoadingException e) {
			System.err.println("Error in loading the model with properties!");
			e.printStackTrace();
		}
		return emfModel;
	}

	protected EmfModel createEmfModelByURI(String name, String model, String metamodel, boolean readOnLoad,
			boolean storeOnDisposal) throws EolModelLoadingException, URISyntaxException {
		EmfModel emfModel = new EmfModel();
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_NAME, name);
		properties.put(EmfModel.PROPERTY_METAMODEL_URI, metamodel);
		properties.put(EmfModel.PROPERTY_MODEL_URI, Utility.getURIFromResource(model).toString());
		properties.put(EmfModel.PROPERTY_READONLOAD, readOnLoad + "");
		properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, storeOnDisposal + "");
		emfModel.load(properties, (IRelativePathResolver) null);
		return emfModel;
	}

	// TODO remove, it is no longer used
//	public PlainXmlModel createXMLModel(String name, String xmlFilePath) {
//		// Load the XML document
//		PlainXmlModel xmlModel = new PlainXmlModel();
//		try {
//			xmlModel.setFile(Utility.getFileFromResource(xmlFilePath));
//			xmlModel.setName(name);
//			xmlModel.load();
//		} catch (EolModelLoadingException e) {
//			e.printStackTrace();
//		}
//		return xmlModel;
//	}

}