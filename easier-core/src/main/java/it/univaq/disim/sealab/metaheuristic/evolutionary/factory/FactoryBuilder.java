package it.univaq.disim.sealab.metaheuristic.evolutionary.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.uma.jmetal.qualityindicator.impl.Epsilon;
import org.uma.jmetal.qualityindicator.impl.GeneralizedSpread;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistancePlus;
import org.uma.jmetal.qualityindicator.impl.Spread;
import org.uma.jmetal.qualityindicator.impl.hypervolume.impl.PISAHypervolume;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public class FactoryBuilder<S extends RSolution<?>> {

	private final static Map<String, Class<?>> qualityIndicatorsMap = new HashMap<String, Class<?>>() {/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		put("SPREAD", Spread.class);
		put("IGD+", InvertedGenerationalDistancePlus.class);
		put("IGD", InvertedGenerationalDistance.class);
		put("EPSILON", Epsilon.class);
		put("HYPER_VOLUME", PISAHypervolume.class);
		put("GENERALIZED_SPREAD", GeneralizedSpread.class);
	}}; 

	public FactoryBuilder() {}

	public GenericIndicator<S> createQualityIndicators(String qI) {
		try {
			try {
				return (GenericIndicator<S>) qualityIndicatorsMap.get(qI).getDeclaredConstructor().newInstance();
			} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				System.err.println("newInstance on QualityIndicator fails. ");
				e.printStackTrace();
			}
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("[ERROR]");
			e.printStackTrace();
		}
		
		System.out.println("[ERROR] no available quality indicators builder");
		return null;
	}

}
