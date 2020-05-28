package it.univaq.disim.sealab.epsilon.evl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;

public class EVLStandalone extends EpsilonStandalone{
	
//	public static void main(String[] args) throws Exception {
//		new EVLStandalone().execute();
//	}
	
	@Override
	public IEolModule createModule() {
		return new EvlModule();
	}

	@Override
	public IModel getModel(Path mmaemiliaFilePath) throws Exception {
		return createEmfModel("aemilia", mmaemiliaFilePath, Paths.get(getClass().getResource("/metamodels/mmAEmilia.ecore").getFile()), true, true);
	}

	@Override
	public String getSource() throws Exception {
//		final File rootFolder = Utility.getFileFromResource(RULES_FOLDER);
//		List<File> allEVLFilesPath = new ArrayList<File>();
//		Utility.search(".*\\.evl", rootFolder, allEVLFilesPath);
		return "evl/aemilia-pas-checker.evl";
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
	public int getPAs(Path mmaemiliaFilePath, Path ruleFilePath) {
		try {
			execute(mmaemiliaFilePath, File.createTempFile("evl", "tmp").toPath(), ruleFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ((EvlModule)this.module).getContext().getUnsatisfiedConstraints().size();
	}

	@Override
	public void preProcess() {
		// TODO Auto-generated method stub
		
	}
}

