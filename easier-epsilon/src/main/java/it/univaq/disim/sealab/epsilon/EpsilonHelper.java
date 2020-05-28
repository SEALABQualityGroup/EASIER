package it.univaq.disim.sealab.epsilon;

import java.nio.file.Path;
import java.nio.file.Paths;

import it.univaq.disim.sealab.epsilon.egl.EGLStandalone;
import it.univaq.disim.sealab.epsilon.evl.EVLStandalone;

public class EpsilonHelper {
	
	/**
	 * 
	 * @param mmaemiliaFilePath
	 * @param destFilePath
	 * @param ruleFilePath
	 */
	public static void generateAemFile(Path mmaemiliaFilePath, Path destFilePath) {
		
		try {
			final String easierEpsilonProject = Paths.get(new EpsilonHelper().getClass().getResource("/").getFile(), "../../../easier-epsilon/target/classes/").toString();
			new EGLStandalone().execute(mmaemiliaFilePath, destFilePath, Paths.get(easierEpsilonProject, new EGLStandalone().getSource()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param mmaemiliaFilePath
	 * @param ruleFilePath
	 * @return
	 */
	public static int aemiliaPasChecker(Path mmaemiliaFilePath, Path ruleFilePath) {
		return new EVLStandalone().getPAs(mmaemiliaFilePath, ruleFilePath);
	}

}
