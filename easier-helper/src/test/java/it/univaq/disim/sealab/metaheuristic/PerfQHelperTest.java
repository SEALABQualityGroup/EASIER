package it.univaq.disim.sealab.metaheuristic;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.javatuples.Quartet;
import org.junit.Before;
import org.junit.Test;

public class PerfQHelperTest {

	private PerfQHelper perfQHelper;
	private Path repository, sourceValPath;

	@Before
	public void init() {
		perfQHelper = new PerfQHelper();
		repository = Paths.get("/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/tmp");
		sourceValPath = Paths
				.get("/home/peo/git/sealab/easier/easier-aemilia/src/main/resources/models/FTA/workload_5/model.val");
	}

	@Test
	public void extractRefereceParetoPerfQ() {

		List<Path> paretoFiles = new ArrayList<>();
		List<Path> csvFiles = new ArrayList<>();
		
//		paretoFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1018_Length_4_CloningWeight_1.5_MaxCloning_3.NSGAII.rf"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1018_Length_4_CloningWeight_1.5_MaxCloning_3.NSGAII.csv"));
		
//		paretoFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1018_Length_4_CloningWeight_1.5_MaxCloning_3.SPEA2.rf"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1018_Length_4_CloningWeight_1.5_MaxCloning_3.SPEA2.csv"));
//		
//		paretoFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1018_Length_4_CloningWeight_1.5_MaxCloning_3.rf"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1018_Length_4_CloningWeight_1.5_MaxCloning_3.csv"));
//
//		paretoFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1087_Length_4_CloningWeight_1.5_MaxCloning_3.NSGAII.rf"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1087_Length_4_CloningWeight_1.5_MaxCloning_3.NSGAII.csv"));
//		
//		paretoFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1087_Length_4_CloningWeight_1.5_MaxCloning_3.SPEA2.rf"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1087_Length_4_CloningWeight_1.5_MaxCloning_3.SPEA2.csv"));
//		
//		paretoFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1087_Length_4_CloningWeight_1.5_MaxCloning_3.rf"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1087_Length_4_CloningWeight_1.5_MaxCloning_3.csv"));
//
//		paretoFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/2113_Length_4_CloningWeight_1.5_MaxCloning_3.NSGAII.rf"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/2113_Length_4_CloningWeight_1.5_MaxCloning_3.NSGAII.csv"));
//		
//		paretoFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/2113_Length_4_CloningWeight_1.5_MaxCloning_3.SPEA2.rf"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/2113_Length_4_CloningWeight_1.5_MaxCloning_3.SPEA2.csv"));
//		
//		paretoFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/2113_Length_4_CloningWeight_1.5_MaxCloning_3.rf"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/2113_Length_4_CloningWeight_1.5_MaxCloning_3.csv"));
		
//		paretoFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/3577_Length_4_CloningWeight_1.5_MaxCloning_3.rf"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/3577_Length_4_CloningWeight_1.5_MaxCloning_3.csv"));
		
		paretoFiles.add(Paths.get(
				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1481_Length_4_CloningWeight_1.5_MaxCloning_3.rf"));
		csvFiles.add(Paths.get(
				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/1481_Length_4_CloningWeight_1.5_MaxCloning_3.csv"));

		List<List<Quartet<Integer, Float, Float, Float>>> results = new ArrayList<>();
		paretoFiles.forEach(paretoFolder -> results
				.add(perfQHelper.extractSoutionFromPareto(paretoFolder, repository, sourceValPath)));

		csvFiles.forEach(csvFile -> results.forEach(
				quartet -> quartet.forEach(q -> PerfQHelper.simpleSolutionWriterToCSV(csvFile, q, "workload5"))));

	}

	@Test
	public void extractVarFilesFromParetoFolder() {

		List<Path> paretoFolders = new ArrayList<>();
		List<Path> csvFiles = new ArrayList<>();

		// SPEA
//		paretoFolders.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/Exp/data/SPEA2/1018_Length_4_CloningWeight_1.5_MaxCloning_3"));
//		paretoFolders.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/Exp/data/SPEA2/1087_Length_4_CloningWeight_1.5_MaxCloning_3"));
//		paretoFolders.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/Exp/data/SPEA2/2113_Length_4_CloningWeight_1.5_MaxCloning_3"));
//
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/SPEA2_1018_Length_4_CloningWeight_1.5_MaxCloning_3.csv"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/SPEA2_1087_Length_4_CloningWeight_1.5_MaxCloning_3.csv"));
//		csvFiles.add(Paths.get(
//				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/SPEA2_2113_Length_4_CloningWeight_1.5_MaxCloning_3.csv"));

		// NSGA
		paretoFolders.add(Paths.get(
				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/referenceFront/Exp/data/SPEA2/1481_Length_4_CloningWeight_1.5_MaxCloning_3"));
		
		csvFiles.add(Paths.get(
				"/mnt/store/research/easier/worse-to-better/ew_20200617_2000_eb_20200627_1640/SPEA2_1481_Length_4_CloningWeight_1.5_MaxCloning_3.csv"));

		List<List<Quartet<Integer, Float, Float, Float>>> results = new ArrayList<>();
		paretoFolders.forEach(paretoFolder -> results
				.add(perfQHelper.getDeltaForParetoFrontiers(paretoFolder, repository, sourceValPath)));

		csvFiles.forEach(csvFile -> results.forEach(
				quartet -> quartet.forEach(q -> PerfQHelper.simpleSolutionWriterToCSV(csvFile, q, "workload5"))));

		System.out.println(results);
	}

}
