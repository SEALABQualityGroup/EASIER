package it.disim.univaq.sealab.metaheuristic.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import it.disim.univaq.sealab.metaheuristic.evolutionary.Controller;
import it.disim.univaq.sealab.metaheuristic.managers.Manager;

public class ThresholdUtils {

	public static void uptodateSingleValueThresholds(String pathToSave) {
		Map<String, String> valuesMap = new HashMap<String, String>();
		
		valuesMap.put("serviceThLB", Float.toString(calculateServiceThLB()));
		valuesMap.put("opResDemUB", Float.toString(calculateOpResDemUB()));
		
		valuesMap.put("respTimeUB", Float.toString(calculateRespTimeUP()));
		valuesMap.put("opResDemLB", Float.toString(calculateOpResDemLB()));
		valuesMap.put("opResDemUB", Float.toString(calculateOpResDemUB()));

//		String path = ThresholdUtils.class.getResource("/ocl/default/detectionSingleValuePA.ocl").getPath();
		
		Controller controller = Manager.getInstance(null).getController();
		
		String path = controller.getRuleTemplateFilePath();

		String templateString;
		try {
			templateString = readFile(path, Charset.defaultCharset());
			StringSubstitutor sub = new StringSubstitutor(valuesMap);
			String resolvedString = sub.replace(templateString);
			
			String thFileToSave = pathToSave + "detectionSingleValuePA.ocl";
			
			File f = new File(thFileToSave);
			f.getParentFile().mkdirs(); 
			f.createNewFile();
			
			PrintWriter out = new PrintWriter(thFileToSave);
			out.print(resolvedString);
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static float calculateOpResDemUB() {
//		return (float) 7.83611241;
		return (float) 1.23456789;
	}
	
	private static float calculateRespTimeUP() {
		return (float) 62562684.4;
	}
	private static float calculateOpResDemLB() {
		return (float) 100000;
	}
	
	private static float calculateServiceThLB() {
		return (float) 0.1276143;
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
