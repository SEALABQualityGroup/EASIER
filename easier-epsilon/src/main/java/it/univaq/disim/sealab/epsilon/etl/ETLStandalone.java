package it.univaq.disim.sealab.epsilon.etl;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.etl.EtlModule;
import org.eclipse.uml2.uml.UMLPackage;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;

public class ETLStandalone extends EpsilonStandalone {

	private Path outputFolder;

	final static String lqnMetamodel = "/home/peo/git/sealab/uml2lqn/org.univaq.uml2lqn/lqnxsd/lqn_renamed.xsd.ecore";

	public ETLStandalone() {
		source = Paths.get("/home/peo/git/sealab/uml2lqn/org.univaq.uml2lqn", "uml2lqn2.etl");
		;
//		InputStream mmIn = EpsilonHelper.class.getClassLoader().getResourceAsStream("evl/aemilia-pas-checker.evl");
//		source = Paths.get("/tmp/aemilia-pas-checker.evl");
//		try {
//			Files.copy(mmIn, source);
//		} catch (IOException e) {
//			System.err.println("Error in copying the ETL file to " + source);
//			e.printStackTrace();
//		}
		module = new EtlModule();
		model = new ArrayList<>();
	}

	public ETLStandalone(Path dest) {
		this();
		outputFolder = dest;
	}

	@Override
	public IEolModule createModule() {
		return new EtlModule();
	}

	/**
	 * It execute the module and disposes the module's modelRepository
	 */
//	@Override
//	public void execute() throws Exception {
//
//		super.doExecute();
//		super.storedOnDispose();
//
////		module.getContext().getModelRepository().dispose();
//	}

	@Override
	public EpsilonStandalone setModel(Path modelFilePath) {

//		final String lqnMetamodel = "/home/peo/git/sealab/uml2lqn/org.univaq.uml2lqn/lqnxsd/lqn.xsd.ecore";

		try {
			model.add(EpsilonStandalone.createUmlModel("UML", modelFilePath, UMLPackage.eNS_URI, true, false));
			// Sets the LQN model from the
//			model.add(createXMLModel("LQN", modelFilePath.getParent().resolve("output.xml")));
//			model.add(createOutputEmfModel("LQN", modelFilePath.getParent().resolve("output.xml"), lqnMetamodel));
			model.add(createXMLModel("LQN", modelFilePath.getParent().resolve("output.xml"),
					org.eclipse.emf.common.util.URI
							.createFileURI("/home/peo/git/sealab/uml2lqn/org.univaq.uml2lqn/lqnxsd/lqn.xsd"), false, true));
		} catch (EolModelLoadingException | URISyntaxException e) {
			System.err.println("Error in setModel!!!");
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public void postProcess(Path destFilePath) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preProcess() {
		// TODO Auto-generated method stub

	}


}
