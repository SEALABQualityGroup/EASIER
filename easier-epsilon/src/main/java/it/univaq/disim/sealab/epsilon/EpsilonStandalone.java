package it.univaq.disim.sealab.epsilon;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.xml.XmlModel;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.types.EolModelElementType;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

import it.univaq.disim.sealab.epsilon.eol.UmlModel;
import it.univaq.disim.sealab.epsilon.utility.Utility;

public abstract class EpsilonStandalone {

	protected IEolModule module;
	protected List<Variable> parameters = new ArrayList<>();
	protected List<IModel> model;

	protected Path metamodelPath;

	protected Path source;

	protected Object result;

	public abstract IEolModule createModule();

	/**
	 * It sets to the EOL file the given object @param obj 
	 * @param type specifies the obj type
	 * @return
	 */
	public EpsilonStandalone setParameter(EObject targetObject, String type, String variableName) {
		EolModelElementType modelType = new EolModelElementType(type);
		Variable var = new Variable(variableName, targetObject, modelType);
		parameters.add(var);
		return this;
	}
	
	public EpsilonStandalone setParameter(EObject targetObj, String type) {
		setParameter(targetObj, type, "self");
		return this;
	}
	
	public abstract EpsilonStandalone setModel(Path modelFilePath);
	public abstract EpsilonStandalone setModel(IModel model);

	public abstract void postProcess(Path destFilePath);

	public abstract void preProcess();

	/**
	 * 
	 * @param mmaemiliaFilePath
	 * @param destFilePath
	 * @param ruleFilePath
	 * @throws Exception
	 */
	public void execute(Path destFilePath) throws Exception {

		doExecute();

		postProcess(destFilePath);

		module.getContext().getModelRepository().dispose();
	}

	public void execute() throws Exception {

		doExecute();

	}

	public void storedOnDispose() {
		module.getContext().getModelRepository().dispose();
	}
	
	public EpsilonStandalone setModule(IEolModule mod) {
		module = mod;
		return this;
	}

	public Path getSource() throws Exception {
		return source;
	}
	
	public List<IModel> getModel() {
		return model;
	}

	protected void doExecute() throws Exception {
		module.parse(getSource().toFile());

		if (module.getParseProblems().size() > 0) {
			System.err.println("Parse errors occured...");
			for (ParseProblem problem : module.getParseProblems()) {
				System.err.println(problem.toString());
			}
		}

		model.forEach(m -> module.getContext().getModelRepository().addModel(m));
//		module.getContext().getModelRepository().add(model);

		for (Variable parameter : parameters) {
			module.getContext().getFrameStack().put(parameter);
		}

		preProcess();
		result = execute(module);
	}

	public Path getMetamodelPath() {
		return metamodelPath;
	}

	public EpsilonStandalone setMetamodelPath(Path mmPath) throws IOException {
		if (Files.exists(mmPath))
			metamodelPath = mmPath;
		if (metamodelPath == null) {
			InputStream mmIn = EpsilonHelper.class.getClassLoader().getResourceAsStream("metamodels/mmAEmilia.ecore");
			metamodelPath = mmPath;
			Files.copy(mmIn, metamodelPath);
		}
		return this;
	}

	public List<Variable> getParameters() {
		return parameters;
	}

	public void setSource(Path src) {
		source = src;
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

	public EmfModel createOutputEmfModel(String name, Path model, String metamodel) {
		EmfModel emfModel = new EmfModel();
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_NAME, name);
		properties.put(EmfModel.PROPERTY_FILE_BASED_METAMODEL_URI, metamodel);
		properties.put(EmfModel.PROPERTY_MODEL_URI, model.toFile());
		properties.put(EmfModel.PROPERTY_READONLOAD, false);
		properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, true);
		try {
			emfModel.load(properties, (IRelativePathResolver) null);
		} catch (EolModelLoadingException e) {
			System.err.println("Error in loading the model with properties!");
			e.printStackTrace();
		}
		return emfModel;
	}

	/**
	 * Loads the @param model as a UML model.
	 * 
	 * @param name            is the name in the Epsilon module
	 * @param model
	 * @param metamodelURI       is the metamodel nsURI
	 * @param readOnLoad      if true the model is read from the file system
	 * @param storeOnDisposal if true the mode is store in the file system
	 * @return the loaded model
	 * @throws EolModelLoadingException
	 * @throws URISyntaxException
	 */
	public static EmfModel createUmlModel(String name, Path model, String metamodelURI, boolean readOnLoad,
			boolean storedOnDisposal) throws EolModelLoadingException, URISyntaxException {
		EmfModel emfModel = new UmlModel();

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet = UMLResourcesUtil.init(resourceSet);
		UMLResourcesUtil.initGlobalRegistries();
		UMLResourcesUtil.initLocalRegistries(resourceSet);

		emfModel.setMetamodelUri(UMLPackage.eINSTANCE.getNsURI());
		emfModel.setName(name);
		emfModel.setModelFile(model.toString());
		emfModel.setStoredOnDisposal(storedOnDisposal);
		emfModel.setReadOnLoad(readOnLoad);
		
		emfModel.load();

		return emfModel;
	}
	
	public XmlModel createXMLModel(String name,  Path xmlFilePath) {
		// Load the XML document
		XmlModel xmlModel = new XmlModel();
		String lqnXSD = Paths.get("/home/peo/git/sealab/uml2lqn/org.univaq.uml2lqn", "lqnxsd", "lqn.xsd").toString();
		try {
//			xmlModel.setFile(Utility.getFileFromResource(xmlFilePath));
			StringProperties properties = new StringProperties();
			properties.put(XmlModel.PROPERTY_NAME, name);
			properties.put(XmlModel.PROPERTY_XSD_URI, lqnXSD);
			properties.put(EmfModel.PROPERTY_EXPAND, true);
			properties.put(EmfModel.PROPERTY_READONLOAD, false);
			properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, true);
			properties.put(XmlModel.PROPERTY_MODEL_URI, "/tmp/test.xml");
			
//			xmlModel.setName(name);
//			xmlModel.setReadOnLoad(false);
//			xmlModel.setStoredOnDisposal(true);
			
			xmlModel.load(properties, (IRelativePathResolver) null);
			
		} catch (EolModelLoadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlModel;
	}
	
	public IEolModule getModule() {
		return module;
	}

}