package it.univaq.disim.sealab.metaheuristic.evolutionary;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

public class CustomNSGAIIBuilder<S extends Solution<?>> extends NSGAIIBuilder<S>{

	public CustomNSGAIIBuilder(Problem<S> problem, CrossoverOperator<S> crossoverOperator,
			MutationOperator<S> mutationOperator) {
		super(problem, crossoverOperator, mutationOperator);
		
	}


}
