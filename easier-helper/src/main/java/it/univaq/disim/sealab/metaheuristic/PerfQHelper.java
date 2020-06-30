package it.univaq.disim.sealab.metaheuristic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaPerformanceQualityEvaluator;
import it.univaq.disim.sealab.metaheuristic.utils.CSVUtils;

public class PerfQHelper {

//	private static Path sourceValPath = Paths
//			.get("/home/peo/git/sealab/easier/easier-aemilia/src/main/resources/models/FTA/workload_5/model.val");

	public float calculateDelta(Path sourceValPath, Path valPath) {

		AemiliaPerformanceQualityEvaluator evaluator = new AemiliaPerformanceQualityEvaluator(null);

		try {
			return evaluator.performanceQuality(sourceValPath, valPath);

		} catch (Exception e) {
			System.err.println("Error while calculating the perfQ between " + sourceValPath.toString() + " and "
					+ valPath.toString());
			e.printStackTrace();
		}

		return 0;
	}

	// pareto file is a file with all non dominated solution
	public void extractSoutionFromPareto(Path sourceValPath, Path paretoFile) {
		String row;
		Path repository = paretoFile.getParent().getParent().resolve("tmp");
		BufferedReader csvReader;
		try {
			csvReader = new BufferedReader(new FileReader(paretoFile.toFile()));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(" ");
				Path valPath = repository.resolve(String.valueOf((Integer.parseInt(data[0]) / 100))).resolve(data[0])
						.resolve(data[0] + ".aem.val");
				System.out.println("PerfQ between " + sourceValPath.toString() + " and " + valPath.toString() + " --> "
						+ calculateDelta(sourceValPath,valPath));
				simpleSolutionWriterToCSV(paretoFile.getParent(),
						sourceValPath.toString() + "," + valPath.toString() + "," + calculateDelta(sourceValPath,valPath));
			}
			csvReader.close();
		} catch (IOException e) {
			System.err.println("Error while extracting info from the pareto file");
			e.printStackTrace();
		}

	}

	public static void simpleSolutionWriterToCSV(Path folder, String l) {
//		Path toWrite = Paths.get(
//				"/mnt/store/research/easier/worse-to-better/easier_better_20200623_1910/referenceFront/perfQwrtWorkload5.csv");
		try (FileWriter fw = new FileWriter(folder.resolve("perfQwrtWorkload5.csv").toFile(), true)) {
			List<String> line = new ArrayList<String>();
			line.addAll(Arrays.asList("source", "better_pareto", "PAs"));
			CSVUtils.writeHeader(fw, line);
			line.clear();
			line.add(l);
			CSVUtils.writeLine(fw, line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
