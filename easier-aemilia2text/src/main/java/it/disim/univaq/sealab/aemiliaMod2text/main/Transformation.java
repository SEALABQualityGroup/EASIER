package it.disim.univaq.sealab.aemiliaMod2text.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//class that exposes two methods corresponding to the transformations
public class Transformation {

	// relative path of transformation results
	private static String outFolder = "./output";

	public static void GenerateAEMTransformation(String inputAEM_Model, String outputFolder) {

		// relative path
//		File baseFolderFile = new File("./m2t/");
//		for (File file : baseFolderFile.listFiles()) {
//
//			if (file.getName().startsWith("readme")) {
//				continue;
//			}
//
//			Generate.run(file.getAbsolutePath(), outFolder);
//		}
//		Generate.run(inputAEM_Model, outputFolder);
		List<String> arguments = new ArrayList<>();
		arguments.add("80");
		Generate.run(inputAEM_Model, outputFolder);
	}

	public static void GenerateREWTransformation(String inputAEmilia_Model, String outputFolder) {

		// relative path
//		File baseFolderFile = new File("./m2t/");
//		for (File file : baseFolderFile.listFiles()) {
//
//			if (file.getName().startsWith("readme")) {
//				continue;
//			}
//
//			Generaterew.run(file.getAbsolutePath(), outFolder);
//
//		}
		Generaterew.run(inputAEmilia_Model, outputFolder);
	}

}
