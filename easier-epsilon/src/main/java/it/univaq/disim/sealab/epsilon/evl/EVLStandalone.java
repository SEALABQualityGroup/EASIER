package it.univaq.disim.sealab.epsilon.evl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.EvlModule;

import it.univaq.disim.sealab.epsilon.EpsilonHelper;
import it.univaq.disim.sealab.epsilon.EpsilonStandalone;

public class EVLStandalone extends EpsilonStandalone {

	/*
	 * It retrieves the evl file from the resources and then copies it to the tmp
	 * folder
	 */
	public EVLStandalone() {
		if (Files.exists(Paths.get("/tmp/aemilia-pas-checker.evl"))) {
			source = Paths.get("/tmp/aemilia-pas-checker.evl");
		} else {
			InputStream mmIn = EpsilonHelper.class.getClassLoader().getResourceAsStream("evl/aemilia-pas-checker.evl");
			source = Paths.get("/tmp/aemilia-pas-checker.evl");
			try {
				Files.copy(mmIn, source);
			} catch (IOException e) {
				System.err.println("Error in copying the EVL file to " + source);
				e.printStackTrace();
			}
		}
		module = new EvlModule();
		
		model = new ArrayList<>();
	}

	@Override
	public IEolModule createModule() {
		return new EvlModule();
	}

//	@Override
////	public IModel getModel(Path mmaemiliaFilePath) throws Exception {
//	public IModel getModel() {
////		return createEmfModel("aemilia", mmaemiliaFilePath, EpsilonStandalone.getMetamodelPath().toString(), true, true);
//		return model;
//	}

	public EpsilonStandalone setModel(Path mmaemiliaFilePath) {
		model.add(createEmfModel("aemilia", mmaemiliaFilePath, this.metamodelPath.toString(), true, true));
		return this;
	}

	@Override
	public void postProcess(Path destFilePath) {
	}

	/**
	 * 
	 * @param mmaemiliaFilePath
	 * @return
	 */
	public int getPAs(Path mmaemiliaFilePath, Path rulePath) {
		return getPAs();
	}
	
	public int getPAs() {
		try {
			execute();
		} catch (Exception e) {
			System.err.println("Error in Performance antipattern detection using the file " + model.toString());
			e.printStackTrace();
		}
		return ((EvlModule) this.module).getContext().getUnsatisfiedConstraints().size();
	}

	@Override
	public void preProcess() {
	}

}
