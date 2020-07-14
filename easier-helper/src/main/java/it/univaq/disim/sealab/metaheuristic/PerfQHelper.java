package it.univaq.disim.sealab.metaheuristic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javatuples.Quartet;

import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaPerformanceQualityEvaluator;
import it.univaq.disim.sealab.metaheuristic.utils.CSVUtils;

public class PerfQHelper {

//	private static Path sourceValPath = Paths
//			.get("/home/peo/git/sealab/easier/easier-aemilia/src/main/resources/models/FTA/workload_5/model.val");
	private static int ACTIONS = 4;

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
	public List<Quartet<Integer, Float, Float, Float>> extractSoutionFromPareto(Path paretoFile, Path repository, Path sourceValPath) {
		String row;
		List<Quartet<Integer, Float, Float, Float>> results = new ArrayList<>();;
//		BufferedReader csvReader;
		try(BufferedReader csvReader= new BufferedReader(new FileReader(paretoFile.toFile()))) {
//			csvReader = new BufferedReader(new FileReader(paretoFile.toFile()));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(" ");
				Path valPath = repository.resolve(String.valueOf((Integer.parseInt(data[0]) / 100))).resolve(data[0])
						.resolve(data[0] + ".aem.val");
				int id = Integer.valueOf(data[0]);
				float perfQ = calculateDelta(sourceValPath, valPath);
				float changes = Float.parseFloat(data[2]);
				float pas = Float.parseFloat(data[3]);
				results.add(Quartet.with(id, perfQ, changes, pas));
//				simpleSolutionWriterToCSV(paretoFile.getParent(),
//						sourceValPath.toString() + "," + valPath.toString() + "," + calculateDelta(sourceValPath,valPath));
			}
//			csvReader.close();
		} catch (IOException e) {
			System.err.println("Error while extracting info from the pareto file");
			e.printStackTrace();
		}

		return results;
	}
	
	/**
	 * Returns a map with solID as key, and delta perfq as value 
	 * @param paretoFolder
	 * @param repository
	 * @param sourceValPath
	 * @return
	 */
	public List<Quartet<Integer, Float, Float, Float>> getDeltaForParetoFrontiers(Path paretoFolder, Path repository, Path sourceValPath) {
		
		List<Path> varFiles = extractVarFiles(paretoFolder);
		List<Quartet<Integer, Float, Float, Float>> results = new ArrayList<>();
		List<Quartet<Integer, Float, Float, Float>> returns = new ArrayList<>();
		
		varFiles.forEach(varFile -> results.addAll(extractSolIDsFromVARfile(varFile)));
		
		for(Quartet<Integer, Float, Float, Float> q : results) {
			int solID = q.getValue0();
			Path valPath = repository.resolve(String.valueOf(solID / 100)).resolve(String.valueOf(solID))
					.resolve(solID + ".aem.val");
			returns.add(q.setAt1(calculateDelta(sourceValPath, valPath)));
			
		}
		
		return returns;
	}
	
	/**
	 * Given a folder it retrieves all VAR_run.tsv files
	 * @param paretoFolder
	 * @return
	 */
	public List<Path> extractVarFiles(Path paretoFolder){
		List<Path> varFiles = new ArrayList<>();
		
		try (Stream<Path> paths = Files.walk(paretoFolder)) {
			
			varFiles = paths.filter(path -> path.getFileName().toString().startsWith("VAR"))                                    
            .collect(Collectors.toList());
			
		}catch(IOException e) {
			System.err.println("ERROR in extracting var files from --> " + paretoFolder);
			e.printStackTrace();
		}
		return varFiles;
	}
	
	/**
	 * Given a VAR tsv file it extracts the list of solution ids
	 * @param varFile
	 * @return
	 */
	public List<Quartet<Integer, Float, Float, Float>> extractSolIDsFromVARfile(Path varFile) {
		String row;
		
		List<Quartet<Integer, Float, Float, Float>> returns = new ArrayList<>();
//		BufferedReader tsvReader;
		try (BufferedReader tsvReader = new BufferedReader(new FileReader(varFile.toFile()))){
			while ((row = tsvReader.readLine()) != null) {
				if(row.contains("Solution ID")) {
					int id = Integer.valueOf(row.substring(row.indexOf(":")+2, row.indexOf("(")-1));
					String objs = row.substring(row.indexOf("(")+1, row.indexOf(")")-1).trim();
					float perfQ = Float.parseFloat(objs.split(",")[0]);
					float changes = Float.parseFloat(objs.split(",")[1]);
					float pas = Float.parseFloat(objs.split(",")[2]);
					
					returns.add(Quartet.with(id, perfQ, changes, pas));
					
					for(int i=0; i < ACTIONS; i++) {
						tsvReader.readLine();
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error in extracting solution IDs from the var file --> " + varFile.toString());
			e.printStackTrace();
		}
		return returns;
	}

	public static void simpleSolutionWriterToCSV(Path csvFile, Quartet<Integer, Float, Float, Float> q, String sourceModel) {
//		Path toWrite = Paths.get(
//				"/mnt/store/research/easier/worse-to-better/easier_better_20200623_1910/referenceFront/perfQwrtWorkload5.csv");
		try (FileWriter fw = new FileWriter(csvFile.toFile(), true)) {
			List<String> line = new ArrayList<String>();
			line.addAll(Arrays.asList("solID", "optimalModel", "delta_perf_q", "changes", "pas"));
			CSVUtils.writeHeader(fw, line);
			line.clear();
			String l = q.getValue0() + ";" + sourceModel + ";" + q.getValue1() + ";" + q.getValue2() + ";" + q.getValue3();
			line.add(l);
			CSVUtils.writeLine(fw, line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
