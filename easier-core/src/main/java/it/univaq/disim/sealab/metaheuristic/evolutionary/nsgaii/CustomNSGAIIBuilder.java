//package it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii;
//
//import java.util.List;
//
//import org.uma.jmetal.algorithm.Algorithm;
//import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
//import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
//import org.uma.jmetal.operator.CrossoverOperator;
//import org.uma.jmetal.operator.MutationOperator;
//import org.uma.jmetal.problem.Problem;
//import org.uma.jmetal.solution.Solution;
//
//public class CustomNSGAIIBuilder<S extends Solution<?>> extends NSGAIIBuilder<S>{
//
//	public CustomNSGAIIBuilder(Problem<S> problem, CrossoverOperator<S> crossoverOperator,
//			MutationOperator<S> mutationOperator) {
//		super(problem, crossoverOperator, mutationOperator);
//	}
//	
//
//	public NSGAII<S> build() {
//		NSGAII<S> algorithm = new CustomNSGAII<S>(this.getProblem(), this.getMaxIterations(), this.getPopulationSize(),
//				this.getCrossoverOperator(), this.getMutationOperator(), this.getSelectionOperator(), this.getSolutionListEvaluator());
//		
//	    return algorithm ;
//	  }
//}
