package it.univaq.disim.sealab.epsilon.evl;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.types.EolSequence;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.dom.Constraint;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;

public class EVLStandalone extends EpsilonStandalone {

	/*
	 * It retrieves the evl file from the resources and then copies it to the tmp
	 * folder
	 */
	public EVLStandalone() {
		module = new EvlModule();
		model = new ArrayList<>();
	}

	@Override
	public IEolModule createModule() {
		return new EvlModule();
	}

	public EpsilonStandalone setModel(Path mmaemiliaFilePath) {
		model.add(createEmfModel("aemilia", mmaemiliaFilePath, this.metamodelPath.toString(), true, true));
		return this;
	}

	@Override
	public void postProcess(Path destFilePath) {
	}

	/**
	 * It returns a map of unsatisfied constraints in descending order.
	 * 
	 * @return A sorted entry of Constraints with the number of unsatisfied
	 *         instances, in descending order.
	 */
	public Map<Constraint, Integer> getUnsatisfiedConstraints() {
		try {
			preProcess();
			execute();
		} catch (Exception e) {
			System.err.println("Error in Performance antipattern detection using the file " + model.toString());
			e.printStackTrace();
		}

		return ((EvlModule) this.module).getContext().getUnsatisfiedConstraintsBySize();

//		int pas = ((EvlModule) this.module).getContext().getUnsatisfiedConstraints().size();
//		return pas;
	}

	/**
	 * Extracts a map<performance antipattern type, map<target.name, fuzzy_value>>
	 * 	  
	 * @return map<performance antipattern type, map<target.name, fuzzy_value>>
	 */
	public Map<String, Map<String, Double>> extractFuzzyValues() {
		try {
			preProcess();
			execute();
		} catch (Exception e) {
			System.err.println("Error in Performance antipattern detection using the file " + model.toString());
			e.printStackTrace();
		}

		org.eclipse.epsilon.eol.types.EolMap<?, ?> mapOfPas = ((org.eclipse.epsilon.eol.types.EolMap<?, ?>) ((EvlModule) this.module)
				.getContext().getFrameStack().get("fuzzy_values").getValue());

		Map<String, Map<String, Double>> perfAntippaternsClassification = new HashMap<>();
		for (Object key : mapOfPas.keySet()) {
			Map<String, Double> perfAntipatternMap = new HashMap<>();	
//			org.eclipse.epsilon.eol.types.EolSequence<?> antipattern = (EolSequence<?>) mapOfPas.get(key);
			org.eclipse.epsilon.eol.types.EolMap<?, ?> antipatternMap = ((org.eclipse.epsilon.eol.types.EolMap<?, ?>) mapOfPas.get(key));
			
			for(Object modelElement : antipatternMap.keySet()) {
				double fuzzyValue = (double) antipatternMap.get(modelElement);
				perfAntipatternMap.put((String) modelElement, fuzzyValue < 1 ? fuzzyValue : 1);
			}
			
//			for(Object detail : antipattern.keySet()) {
//				String modelElement = (String) ((org.eclipse.epsilon.eol.types.EolMap<?, ?>)detail).keySet().stream().findFirst().get();
//				double fuzzyValue = (double) ((org.eclipse.epsilon.eol.types.EolMap<?, ?>)detail).get(modelElement);
//				perfAntipatternMap.put(modelElement, fuzzyValue < 1 ? fuzzyValue : 1);
//			}
			perfAntippaternsClassification.put((String)key, perfAntipatternMap);
		}
		return perfAntippaternsClassification;
	}

	@Override
	public void preProcess() {

	}

//	@Override
//	public void clearMemory() {
//		super.clearMemory();
//		((EvlModule)this.module).getPre().clear();
//		((EvlModule)this.module).getContext().dispose();
//	}

}
