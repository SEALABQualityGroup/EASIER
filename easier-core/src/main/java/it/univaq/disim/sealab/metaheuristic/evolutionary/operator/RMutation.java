package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public class RMutation<S extends RSolution<?>> implements MutationOperator<S> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final double DEFAULT_PROBABILITY = 0.01;
	private static final double DEFAULT_DISTRIBUTION_INDEX = 20.0;
	private final double distributionIndex;
	private final double mutationProbability;
	private RepairRSolution solutionRepair;

	/** Constructor */
	public RMutation() {
		this(DEFAULT_PROBABILITY, DEFAULT_DISTRIBUTION_INDEX);
	}

	/** Constructor */
	public RMutation(RProblem<S> problem, double distributionIndex) {
		this(1.0 / problem.getNumberOfVariables(), distributionIndex);
	}

	/** Constructor */
	public RMutation(double mutationProbability, double distributionIndex) {
		this(mutationProbability, distributionIndex, new RepairRSolutionAtBounds());
	}

	/** Constructor */
	public RMutation(double mutationProbability, double distributionIndex, RepairRSolution solutionRepair) {
		if (mutationProbability < 0) {
			throw new JMetalException("Mutation probability is negative: " + mutationProbability);
		} else if (distributionIndex < 0) {
			throw new JMetalException("Distribution index is negative: " + distributionIndex);
		}
		this.mutationProbability = mutationProbability;
		this.distributionIndex = distributionIndex;

	}

	/* Getters */
	public double getMutationProbability() {
		return mutationProbability;
	}

	/** Execute() method */
	@Override
	public S execute(S solution) throws JMetalException {
		if (null == solution) {
			throw new JMetalException("Null parameter");
		}
		int allowed = 1000;
		doMutation(mutationProbability, solution, allowed);
		return solution;
	}

	/** Perform the mutation operation */
	private void doMutation(double probability, S solution, int allowed_failures) {

		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			if (JMetalRandom.getInstance().nextDouble() <= probability) {
				boolean altered = false;
				int num_failures = 0;
				while (!altered) {
					if (solution.alter(JMetalRandom.getInstance().nextInt(0,
							((Refactoring) solution.getVariable(0)).getActions().size() - 1))) {
						solution.setMutated();
						break;
					} else {
						num_failures++;
						if (num_failures >= allowed_failures)
							break;
					}
				}
			}
		}
	}


}
