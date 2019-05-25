package it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public class CustomNSGAIIBuilder<S extends RSolution> extends NSGAIIBuilder<S>{

	public CustomNSGAIIBuilder(Problem<AemiliaRSolution> problem, CrossoverOperator<S> crossoverOperator,
			MutationOperator<S> mutationOperator) {
		super((Problem<S>) problem, (CrossoverOperator<S>)crossoverOperator, (MutationOperator<S>)mutationOperator);
	}
	

	public NSGAII<S> build() {
		NSGAII<S> algorithm = new CustomNSGAII<S>((Problem<AemiliaRSolution>) this.getProblem(), this.getMaxIterations(), this.getPopulationSize(),
				this.getCrossoverOperator(), this.getMutationOperator(), this.getSelectionOperator(), this.getSolutionListEvaluator());
	    return algorithm ;
	  }


	public void setListEvaluator( SolutionListEvaluator<RSolution> solutionListEvaluator) {
		
		this.setSolutionListEvaluator((SolutionListEvaluator<S>) solutionListEvaluator);
		
	}
}
