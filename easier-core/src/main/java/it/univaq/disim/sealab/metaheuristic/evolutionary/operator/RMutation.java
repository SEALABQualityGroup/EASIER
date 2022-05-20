package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.JMetalException;

public abstract class RMutation<S extends RSolution<?>> implements MutationOperator<S> {

    private static final long serialVersionUID = 1L;

    private static final double DEFAULT_PROBABILITY = 0.01;
    private static final double DEFAULT_DISTRIBUTION_INDEX = 20.0;
    private final double distributionIndex;
    private final double mutationProbability;
    private RepairRSolution solutionRepair;

    /**
     * Constructor
     */
    public RMutation(double mutationProbability, double distributionIndex) {
//		this(mutationProbability, distributionIndex, new RepairRSolutionAtBounds());
        this.mutationProbability = mutationProbability;
        this.distributionIndex = distributionIndex;
    }

    /* Getters */
    public double getMutationProbability() {
        return mutationProbability;
    }

    /**
     * Execute() method
     */
    @Override
    public S execute(S solution) throws JMetalException {
        if (null == solution) {
            throw new JMetalException("Null parameter");
        }
        int allowed = Configurator.eINSTANCE.getAllowedFailures();
        doMutation(mutationProbability, solution, allowed);
        return solution;
    }

    /**
     * Perform the mutation operation
     */
    protected abstract void doMutation(double mutationProbability, S solution, int allowed_failures);

}
