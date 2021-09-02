package it.univaq.disim.sealab.metaheuristic.evolutionary.rnsgaii;

import java.util.List;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAII;
import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAIIBuilder;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAII;

public class CustomRNSGAIIBuilder<S extends RSolution<?>> extends RNSGAIIBuilder<S> {
	
	private List<Double> interestPoint;
	private double epsilon;

	public CustomRNSGAIIBuilder(Problem<S> problem, CrossoverOperator<S> crossoverOperator,
			MutationOperator<S> mutationOperator, List<Double> interestPoint, double epsilon) {
		super(problem, crossoverOperator, mutationOperator, interestPoint, epsilon);
		
		this.interestPoint = interestPoint;
		this.epsilon = epsilon;
	}

	public RNSGAII<S> build() {
		RNSGAII<S> algorithm;

		algorithm = new CustomRNSGAII<>(this.getProblem(), this.getMaxIterations(), this.getPopulationSize(),
				matingPoolSize, offspringPopulationSize, this.getCrossoverOperator(), this.getMutationOperator(),
				this.getSelectionOperator(), this.getSolutionListEvaluator(), interestPoint, epsilon);

		return algorithm;
	}

}
