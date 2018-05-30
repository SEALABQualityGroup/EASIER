package it.univaq.disim.sealab.metaheuristic.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.text.StringSubstitutor;
import org.eclipse.emf.common.util.EList;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import it.univaq.disim.sealab.ttep.val.classes.MeasureValue;
import it.univaq.disim.sealab.ttep.val.classes.ValSpec;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.DataType.Special;
import metamodel.mmaemilia.DataType.SpecialType;
import metamodel.mmaemilia.Expressions.IdentExpr;
import metamodel.mmaemilia.Headers.ConstInit;

public class ThresholdUtils {

	public static void uptodateSingleValueThresholds(String mmaemiliaFolderPath, String mmaemiliaFilePath,
			String valFilePath, AemiliaManager metamodelManager, Controller controller) {

//		Controller controller = Manager.getInstance(null).getController();
		ValSpec valSpec = metamodelManager.getTwoEaglesBridge().getValSpec(valFilePath);
		AEmiliaSpecification aemiliaModel = metamodelManager.getModel(mmaemiliaFilePath);

		createNewOclFile(controller.getRuleTemplateFilePath(), mmaemiliaFolderPath, aemiliaModel, valSpec);

	}

	private static String extractingInstaceName(String measure) {
		String instanceName = measure.substring(0, measure.indexOf("_"));
		return instanceName;
	}

	private static void createNewOclFile(String ruleTemplateFilePath, String pathToSave,
			AEmiliaSpecification aemiliaModel, ValSpec valSpec) {
		String templateString;
		Map<String, String> valuesMap = new HashMap<String, String>();
		// Pipe and Filter
		valuesMap.put("serviceThLB", Float.toString(calculateServiceThLB(valSpec)));
		valuesMap.put("opResDemUB", Float.toString(calculateOpResDemUB(aemiliaModel)));

		// Extensive Processing
		valuesMap.put("respTimeUB", Float.toString(calculateRespTimeUP()));
		valuesMap.put("opResDemLB", Float.toString(calculateOpResDemLB()));
		valuesMap.put("opResDemUB", Float.toString(calculateOpResDemUB(aemiliaModel)));
		try {
			templateString = readFile(ruleTemplateFilePath, Charset.defaultCharset());
			StringSubstitutor sub = new StringSubstitutor(valuesMap);
			String resolvedString = sub.replace(templateString);

			String newRuleFilePath = pathToSave + "detectionSingleValuePA.ocl";

			File f = new File(newRuleFilePath);
			f.getParentFile().mkdirs();
			f.createNewFile();

			PrintWriter out = new PrintWriter(newRuleFilePath);
			out.print(resolvedString);
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static float calculateOpResDemUB(AEmiliaSpecification aemiliaModel) {
		Float thRate = (float) 0.0;
		int countRates = 0;
		EList<ConstInit> listOfConsts = aemiliaModel.getArchiTypeDecl().getHeader().getInitConst();
		for (ConstInit aemiliaConst : listOfConsts) {
			if (aemiliaConst.getInitConstData() instanceof Special) {
				if (aemiliaConst.getName().contains("rate")
						&& ((Special) aemiliaConst.getInitConstData()).getType().equals(SpecialType.RATE)) {
					thRate += Float.parseFloat(((IdentExpr) aemiliaConst.getInitConstExpr()).getName());
					countRates++;
				}
			}
		}

		if (countRates != 0) {
			thRate = thRate / countRates;
		}

		return thRate;
	}

	private static float calculateRespTimeUP() {
		return (float) 100;
	}

	private static float calculateOpResDemLB() {
		return (float) 100000;
	}

	private static float calculateServiceThLB(ValSpec valSpec) {
		List<MeasureValue> measuredValues = valSpec.getMeasures();

		Map<String, MutablePair<Float, Integer>> typeMap = new HashMap<>();

		int countThroughput = 0;
		Float totalThroughput = (float) 0.0;
		Float th_Throughput = (float) 0.0;

		for (MeasureValue measure : measuredValues) {
			if (measure.getMeasure().contains("throughput") && measure.getMeasure().contains("Single")) {
				String instanceName = extractingInstaceName(measure.getMeasure());
				if (typeMap.containsKey(instanceName)) {
					typeMap.get(instanceName).right += 1;
					typeMap.get(instanceName).left += measure.getValue();

				} else {
					MutablePair<Float, Integer> throughputCountPair = new MutablePair<Float, Integer>();
					throughputCountPair.right = 0;
					throughputCountPair.left = (float) 0.0;
					throughputCountPair.right += 1;
					throughputCountPair.left += measure.getValue();

					typeMap.put(instanceName, throughputCountPair);
				}

			}
		}

		for (String key : typeMap.keySet()) {
			totalThroughput += (typeMap.get(key).left / typeMap.get(key).right);
		}

		th_Throughput = totalThroughput / typeMap.size();

		return th_Throughput;
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
