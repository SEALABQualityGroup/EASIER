package it.univaq.disim.sealab.epsilon.evl;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.EvlModule;

import it.univaq.disim.sealab.epsilon.EpsilonHelper;
import it.univaq.disim.sealab.epsilon.EpsilonStandalone;

public class EVLStandalone extends EpsilonStandalone {

//	public static void main(String[] args) throws Exception {
//		new EVLStandalone().execute();
//	}

	/*
	 * It retrieves the evl file from the resources and then copies it to the tmp folder
	 */
	public EVLStandalone() {
		if (Files.exists(Paths.get("/tmp/aemilia-pas-checker.evl"))) {
			rulePath = Paths.get("/tmp/aemilia-pas-checker.evl");
		}else {
			InputStream mmIn = EpsilonHelper.class.getClassLoader().getResourceAsStream("evl/aemilia-pas-checker.evl");
//		metamodelPath = Files.createTempFile("", "");
			rulePath = Paths.get("/tmp/aemilia-pas-checker.evl");
			try {
				Files.copy(mmIn, rulePath);
			} catch (IOException e) {
				System.err.println("Error in copying the EVL file to " + rulePath);
				e.printStackTrace();
			}
		}
	}

	@Override
	public IEolModule createModule() {
		return new EvlModule();
	}

	@Override
	public IModel getModel(Path mmaemiliaFilePath) throws Exception {
		return createEmfModel("aemilia", mmaemiliaFilePath, EpsilonStandalone.getMetamodelPath().toString(), true, true);
	}

	@Override
	public Path getSource() throws Exception {
//		final File rootFolder = Utility.getFileFromResource(RULES_FOLDER);
//		List<File> allEVLFilesPath = new ArrayList<File>();
//		Utility.search(".*\\.evl", rootFolder, allEVLFilesPath);
		return rulePath;
	}

	@Override
	public void postProcess(Path destFilePath) {

//		EvlModule module = (EvlModule) this.module;
//		
//		Collection<UnsatisfiedConstraint> unsatisfied = module.getContext().getUnsatisfiedConstraints();
//	
//		if (unsatisfied.size() > 0) {
//			System.err.println(unsatisfied.size() + " constraint(s) have not been satisfied");
//			for (UnsatisfiedConstraint uc : unsatisfied) {
//				System.err.println(uc.getMessage());
//			}
//		}
//		else {
//			System.out.println("All constraints have been satisfied");
//		}
	}

	/**
	 * 
	 * @param mmaemiliaFilePath
	 * @return
	 */
	public int getPAs(Path mmaemiliaFilePath, Path rulePath) {
		try {
			this.setSource(rulePath);
			execute(mmaemiliaFilePath);//, File.createTempFile("evl", "tmp").toPath());
		} catch (Exception e) {
			System.err.println("Error in Performance antipattern detection using the file " + mmaemiliaFilePath);
			e.printStackTrace();
		}
		return ((EvlModule) this.module).getContext().getUnsatisfiedConstraints().size();
	}

	@Override
	public void preProcess() {
		// TODO Auto-generated method stub

	}

	@Override
	public IModel getModel(Path modelFilePath, Path metamodelPath) throws Exception {
		return getModel(modelFilePath);
	}
}
