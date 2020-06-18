package it.univaq.disim.sealab.epsilon.egl;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.epsilon.egl.EglTemplateFactory;
import org.eclipse.epsilon.egl.EglTemplateFactoryModuleAdapter;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.models.IModel;

import it.univaq.disim.sealab.epsilon.EpsilonHelper;
import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.utility.Utility;

public class EGLStandalone extends EpsilonStandalone{

//	public static void main(String[] args) throws Exception {
//		new EGLStandalone().execute();
//	}
	
	private Path metamodelPath, rulePath;
	
	@Override
	public IEolModule createModule() {
		return new EglTemplateFactoryModuleAdapter(new EglTemplateFactory());
	}
	
	public IModel getModel(Path mmaemiliaFilePath, Path metamodelPath) throws Exception {
		this.metamodelPath = metamodelPath;
		return getModel(mmaemiliaFilePath);
	}

	@Override
	public IModel getModel(Path mmaemiliaFilePath) throws Exception {
		// TODO reset the getResource path
		return createEmfModel("aemilia", mmaemiliaFilePath, this.metamodelPath.toString(), true, true);
	}

	@Override
	public Path getSource() throws Exception {
		if(Files.exists(Paths.get("/tmp/rule_egl")))
			rulePath = Paths.get("/tmp/rule_egl");
		if (rulePath == null) {
			InputStream ruleIn = EpsilonHelper.class.getClassLoader().getResourceAsStream("egl/mmaemilia2aem.egl");
//			rulePath = Files.createTempFile("", "");
			rulePath = Paths.get("/tmp/rule_egl");
			Files.copy(ruleIn, rulePath);
		}
		return rulePath;
//		return "egl/mmaemilia2aem.egl";
	}

	@Override
	public void postProcess(Path destFilePath) {
		String text = (String) result;
		Utility.saveStringToFile(text, destFilePath);
	}

	@Override
	public void preProcess() {
		// TODO Auto-generated method stub
	}
}
