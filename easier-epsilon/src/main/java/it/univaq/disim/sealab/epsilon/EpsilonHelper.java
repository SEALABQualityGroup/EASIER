package it.univaq.disim.sealab.epsilon;

import java.io.BufferedReader;
import java.net.URL;
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
			// TODO NPE
//			final String easierEpsilonProject = Paths
//					.get(EpsilonHelper.class.getClassLoader().getResource("/").getFile(),
//							"../../../easier-epsilon/target/classes/")
//					.toString();

			new EGLStandalone().execute(mmaemiliaFilePath, destFilePath);

		} catch (Exception e) {
			System.err.println("mmaemiliaFilePath  --> " + mmaemiliaFilePath);
			System.err.println("destFilePath  --> " + destFilePath);
			System.err.println("GetResource getFile NULL");
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
