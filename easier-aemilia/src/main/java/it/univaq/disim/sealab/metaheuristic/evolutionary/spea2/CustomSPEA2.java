package it.univaq.disim.sealab.metaheuristic.evolutionary.spea2;

import java.util.List;

import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

@SuppressWarnings("serial")
public class CustomSPEA2<S extends Solution<?>> extends SPEA2<S>{

	private String name;

	/**
	 * Constructor
	 */
	public CustomSPEA2(Problem<S> problem, int maxIterations, int populationSize,
			CrossoverOperator<S> crossoverOperator, MutationOperator<S> mutationOperator,
			SelectionOperator<List<S>, S> selectionOperator, SolutionListEvaluator <S> evaluator) {
		super(problem, maxIterations, populationSize, crossoverOperator, mutationOperator, selectionOperator,
				evaluator);
		this.name = "Custom_SPEA_2";
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		this.name = n;
	}

}
