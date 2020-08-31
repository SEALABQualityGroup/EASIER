package it.univaq.disim.sealab.epsilon.refactoring;

import java.net.URISyntaxException;
import java.nio.file.Path;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.types.EolModelElementType;
import org.eclipse.uml2.uml.UMLPackage;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;

public class EOLStandalone extends EpsilonStandalone {

	public EOLStandalone() {
		module = createModule();
	}

	@Override
	public IEolModule createModule() {
		return new EolModule();
	}

	@Override
	public Path getSource() throws Exception {
		return source;
	}

	public EpsilonStandalone setModel(Path modelFilePath) {
		try {
			model = createEmfModelByURI("UML", modelFilePath, UMLPackage.eNS_URI, true, true);
		} catch (EolModelLoadingException | URISyntaxException e) {
			System.err.println("Error in setModel!!!");
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public void postProcess(Path destFilePath) {
	}

	public Object getTarget() {

		return module.getContext().getFrameStack().get("target").getValue();

	}

	@Override
	public void preProcess() {
	}

	public void execute() throws Exception {
		doExecute();

		module.getContext().getModelRepository().dispose();
		module.getContext().getFrameStack().getFrames();
	}

	private void doExecute() throws Exception {
//		module = createModule();
		module.parse(getSource().toFile());

		if (module.getParseProblems().size() > 0) {
			System.err.println("Parse errors occured...");
			for (ParseProblem problem : module.getParseProblems()) {
				System.err.println(problem.toString());
			}
		}

		module.getContext().getModelRepository().addModel(model);

		for (Variable parameter : parameters) {
			module.getContext().getFrameStack().put(parameter);
		}

		preProcess();
		result = execute(module);
	}
}
