package it.univaq.disim.sealab.metaheuristic.evolutionary.pesaii;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2Builder;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.problem.Problem;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public class CustomPESA2Builder<S extends RSolution<?>> extends PESA2Builder<S> {

	public CustomPESA2Builder(Problem<S> problem, CrossoverOperator<S> crossoverOperator,
			MutationOperator<S> mutationOperator) {
		super(problem, crossoverOperator, mutationOperator);
	}

	public PESA2<S> build() {
		PESA2<S> algorithm = new CustomPESA2<S>(this.getProblem(), this.getMaxEvaluations(), this.getPopulationSize(),
				this.getArchiveSize(), this.getBiSections(), this.getCrossoverOperator(), this.getMutationOperator(),
				this.getSolutionListEvaluator());

		return algorithm;
	}

}
