package it.univaq.disim.sealab.easier.uml.utils;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.epsilon.etl.ETLStandalone;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;

public class WorkflowUtils {

    public void applyTransformation(Path sourceModelPath) {

        System.out.print("Applying transformation ... ");
//		EasierUmlModel uml = null;
        ETLStandalone executor = null;
        String uml2lqnModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
                "easier-uml2lqn", "org.univaq.uml2lqn").toString();
        try (EasierUmlModel uml = EpsilonStandalone.createUmlModel(sourceModelPath.toString())) {
//			uml = EpsilonStandalone.createUmlModel(sourceModelPath.toString());

            executor = new ETLStandalone(sourceModelPath.getParent());
            executor.setSource(Paths.get(uml2lqnModule, "uml2lqn.etl"));
            executor.setModel(uml);
            executor.setModel(executor.createXMLModel("LQN", sourceModelPath.getParent().resolve("output.xml"),
                    org.eclipse.emf.common.util.URI.createFileURI(Paths.get(uml2lqnModule, "lqnxsd", "lqn.xsd").toString()),
                    false, true));
            executor.execute();
            executor.clearMemory();
        } catch (EolModelLoadingException | URISyntaxException e) {
            System.err.println("Error in runnig the ETL transformation");
            e.printStackTrace();
        } catch (EolRuntimeException e) {
            throw new RuntimeException(e);
        }
        new UMLMemoryOptimizer().cleanup();
//        uml = null;
//        executor = null;
        System.out.println("done");
    }

    public void invokeSolver(Path folderPath) throws Exception {
        Path lqnSolverPath = Configurator.eINSTANCE.getSolver();
        Path lqnModelPath = folderPath.resolve("output.xml");

        XMLUtil.conformanceChecking(lqnModelPath);

        // to allow cycles in the lqn model
        final String params = "-P cycles=yes";

        Process process = null;
        try {
            process = new ProcessBuilder(lqnSolverPath.toString(), params, lqnModelPath.toString()).start();
            process.waitFor();

            if (!Files.exists(folderPath.resolve("output.lqxo"))) {
                final String lqnError = new BufferedReader(new InputStreamReader(process.getErrorStream())).lines()
                        .map(act -> act.toString()).collect(Collectors.joining(","));
                throw new Exception(lqnError);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e1){
            e1.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void backAnnotation(Path sourceModelPath) {

		EOLStandalone bckAnn = new EOLStandalone();
		EasierUmlModel uml = null;

		try {
			uml = EpsilonStandalone.createUmlModel(sourceModelPath.toString());
		} catch (URISyntaxException | EolRuntimeException e) {
			e.printStackTrace();
		}

		bckAnn.setModel(uml);

		String uml2lqnModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-uml2lqn", "org.univaq.uml2lqn").toString();

		bckAnn.setSource(Paths.get(uml2lqnModule, "backAnnotation.eol"));

		// Points to lqn schema file and stores pacakges into the global package
		// registry
		XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
		String schema = Paths.get(uml2lqnModule, "lqnxsd", "lqn.xsd").toString();
//		String schema = Configurator.eINSTANCE.getUml2Lqn().resolve("org.univaq.uml2lqn").resolve("lqnxsd")
//				.resolve("lqn.xsd").toString();
		Collection<EObject> generatedPackages = xsdEcoreBuilder
				.generate(org.eclipse.emf.common.util.URI.createURI(schema));
		for (EObject generatedEObject : generatedPackages) {
			if (generatedEObject instanceof EPackage) {
				EPackage generatedPackage = (EPackage) generatedEObject;
				EPackage.Registry.INSTANCE.put(generatedPackage.getNsURI(), generatedPackage);
			}
		}
		bckAnn.setModel(bckAnn.createPlainXMLModel("LQXO", sourceModelPath.getParent().resolve("output.lqxo"), true,
				false, true));

		try {
			bckAnn.execute();
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		bckAnn.clearMemory();
		new UMLMemoryOptimizer().cleanup();
		bckAnn = null;

	}

}
