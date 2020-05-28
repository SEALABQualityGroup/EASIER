package it.univaq.disim.sealab.epsilon.egl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.epsilon.egl.EglTemplateFactory;
import org.eclipse.epsilon.egl.EglTemplateFactoryModuleAdapter;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.models.IModel;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.utility.Utility;

public class EGLStandalone extends EpsilonStandalone{

//	public static void main(String[] args) throws Exception {
//		new EGLStandalone().execute();
//	}
	
	@Override
	public IEolModule createModule() {
		return new EglTemplateFactoryModuleAdapter(new EglTemplateFactory());
	}

	@Override
	public IModel getModel(Path mmaemiliaFilePath) throws Exception {
		return createEmfModel("aemilia", mmaemiliaFilePath, Paths.get(getClass().getResource("/metamodels/mmAEmilia.ecore").getFile()), true, true);
	}

	@Override
	public String getSource() throws Exception {
		return "egl/mmaemilia2aem.egl";
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
