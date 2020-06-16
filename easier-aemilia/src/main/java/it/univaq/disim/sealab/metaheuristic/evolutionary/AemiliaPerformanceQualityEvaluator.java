package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import it.univaq.disim.sealab.epsilon.EpsilonHelper;
import it.univaq.disim.sealab.epsilon.evl.EVLStandalone;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import it.univaq.disim.sealab.metaheuristic.twoeagles.NewValParser;
import it.univaq.disim.sealab.ttep.val.ValParser;
import it.univaq.disim.sealab.ttep.val.classes.MeasureValue;
import it.univaq.disim.sealab.ttep.val.classes.ValSpec;
import mapmeasurestoindices.IndexType;
import metamodel.mmaemilia.ArchitecturalInteraction;

public class AemiliaPerformanceQualityEvaluator implements PerformanceQualityEvaluator {

	private String sourceValFile;
	private String refactoredValFile;
	private ValSpec valSpecToBeEvaluated;
	private ValSpec originalValSpec;
	private Map<MeasureValue, Double> qualityMap;
	private OclManager oclManager;

	public AemiliaPerformanceQualityEvaluator(OclManager oclManager) {
		qualityMap = new HashMap<>();
		this.oclManager = oclManager;
	}

	/**
	 * Counts the number of performance antipatterns on the emf model, based on the
	 * rule file
	 * 
	 * @param mmaemiliaFilePath
	 * @param ruleFilePath
	 */
	public int performanceAntipatternEvaluatorEpsilon(Path mmaemiliaFilePath, Path ruleFilePath) {
		try {
			return EpsilonHelper.aemiliaPasChecker(mmaemiliaFilePath, ruleFilePath);
		} catch (Exception e) {
			System.err.println("Error in Performance Antipattern detection phase for " + mmaemiliaFilePath);
			e.printStackTrace();
		}
		return -1;
	}

	@Deprecated
	public Map<String, List<ArchitecturalInteraction>> performanceAntipatternEvaluator(EObject model,
			Path ruleFilePath) {
		int numOfPAs = 0;

		List<Object> contextualArchiInteractions = new ArrayList<Object>();

		TreeIterator<EObject> eAllContents = model.eAllContents();

		while (eAllContents.hasNext()) {
			EObject next = eAllContents.next();
			if (next instanceof ArchitecturalInteraction) { // && next.eContainer() instanceof ElemType) {
				contextualArchiInteractions.add(next);
			}
		}
		return oclManager.countPAsFromOCLFromFile(ruleFilePath, contextualArchiInteractions);
	}

	/**
	 * Returns the PerfQ value calculated as refactoredValFile over sourceValFile.
	 * 
	 * @param sourceValFile
	 * @param refactoredValFile
	 * @return the calculated quality
	 * @throws Exception 
	 */
	public float performanceQuality(final Path sourceValFile, final Path refactoredValFile) throws Exception {
		setOriginalValSpec(sourceValFile);
		setValSpecToBeEvaluated(refactoredValFile);
		qualityMap.clear();
		for (MeasureValue mes : getOriginalValSpec().getMeasures()) {
			for (MeasureValue refMes : getValSpecToBeEvaluated().getMeasures()) {
				if (mes.getMeasure().equals(refMes.getMeasure())) {
					calculateQuality(mes, refMes);
				}
			}
		}
		return calcuateWholeQuality();
	}

	private Float calcuateWholeQuality() {
		float overallQuality = 0;
		for (double val : qualityMap.values()) {
			overallQuality += val;
		}
		return (overallQuality / qualityMap.size());
	}

	private void calculateQuality(MeasureValue sourceMes, MeasureValue refMes) throws Exception {
		// Float quality = Float.parseFloat("0.0");
		double quality = 0.0;
		// if(sourceMes.getValue() == 0) {
		// if (Pattern.compile(Pattern.quote(IndexType.THROUGHPUT.getLiteral()),
		// Pattern.CASE_INSENSITIVE).matcher(sourceMes.getMeasure()).find())
		// if(refMes.getValue() >= 0 && refMes.getValue() <= 1)
		// quality = refMes.getValue();
		// else
		// quality = Float.parseFloat("1.0");
		// else
		// if (Pattern.compile(Pattern.quote(IndexType.RESPONSE_TIME.getLiteral()),
		// Pattern.CASE_INSENSITIVE).matcher(sourceMes.getMeasure()).find())
		// if(refMes.getValue() >= 0 && refMes.getValue() <= 1)
		// quality = -1 * refMes.getValue();
		// else
		// quality = -1 * Float.parseFloat("1.0");
		// else
		// if (Pattern.compile(Pattern.quote(IndexType.UTILIZATION.getLiteral()),
		// Pattern.CASE_INSENSITIVE).matcher(sourceMes.getMeasure()).find())
		// if(refMes.getValue() > 0.8)
		// quality = -1 * (1 - refMes.getValue());
		// else
		// quality = refMes.getValue();
		// } else {
		if (sourceMes.getValue() == 0)
			throw new Exception("There is a problem in the source val measure");
		if (refMes.getValue() == 0) {
			System.out.println("The refactored model has measure --> " + refMes.getMeasure() + " with 0 value!!!");
			quality = 0.0;
		} else
			quality = (refMes.getValue() - sourceMes.getValue()) / (refMes.getValue() + sourceMes.getValue());
		// if (Pattern.compile(Pattern.quote(IndexType.THROUGHPUT.getLiteral()),
		// Pattern.CASE_INSENSITIVE).matcher(sourceMes.getMeasure()).find()) {
		// } else
		if (Pattern.compile(Pattern.quote(IndexType.RESPONSE_TIME.getLiteral()), Pattern.CASE_INSENSITIVE)
				.matcher(sourceMes.getMeasure()).find())
			quality *= -1;
		else if (Pattern.compile(Pattern.quote(IndexType.UTILIZATION.getLiteral()), Pattern.CASE_INSENSITIVE)
				.matcher(sourceMes.getMeasure()).find())
			if (refMes.getValue() > 0.8 && sourceMes.getValue() > 0.8)
				quality *= -1;
			else if (refMes.getValue() > 0.8)
				quality = quality - (refMes.getValue() - Float.parseFloat("0.8"));
			else if (sourceMes.getValue() > 0.8)
				quality = quality + (sourceMes.getValue() - Float.parseFloat("0.8"));
		// }
		if (Pattern.compile(Pattern.quote(IndexType.THROUGHPUT.getLiteral()), Pattern.CASE_INSENSITIVE)
				.matcher(sourceMes.getMeasure()).find()
				|| Pattern.compile(Pattern.quote(IndexType.UTILIZATION.getLiteral()), Pattern.CASE_INSENSITIVE)
						.matcher(sourceMes.getMeasure()).find()) {
			// Controller.logger_.info("Measure " + refMes.getMeasure() + " is " +
			// refMes.getValue());
			qualityMap.put(sourceMes, quality);
		}

	}

	private ValSpec getValSpec(Path valFilePath) {

		return NewValParser.parsingValFile(valFilePath);

		/*
		 * FileInputStream valFileInputStream = null; try { valFileInputStream = new
		 * FileInputStream(valFilePath.toFile()); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); return null; } ValParser valParser = new
		 * ValParser(valFileInputStream);
		 * 
		 * ValSpec valSpec = null; try { valSpec = valParser.ValSpec(); } catch
		 * (it.univaq.disim.sealab.ttep.val.ParseException e) { e.printStackTrace();
		 * return null; }
		 * 
		 * return valSpec;
		 */
	}

	public String getSourceValFile() {
		return sourceValFile;
	}

	public void setSourceValFile(String sourceValFile) {
		this.sourceValFile = sourceValFile;
	}

	public String getRefactoredValFile() {
		return refactoredValFile;
	}

	public void setRefactoredValFile(String refactoredValFile) {
		this.refactoredValFile = refactoredValFile;
	}

	public ValSpec getValSpecToBeEvaluated() {
		return valSpecToBeEvaluated;
	}

	public void setValSpecToBeEvaluated(Path valSpecToBeEvaluatedPath) {
		this.valSpecToBeEvaluated = getValSpec(valSpecToBeEvaluatedPath);
	}

	public ValSpec getOriginalValSpec() {
		return originalValSpec;
	}

	public void setOriginalValSpec(Path originalValSpecPath) {
		this.originalValSpec = getValSpec(originalValSpecPath);
	}

}
