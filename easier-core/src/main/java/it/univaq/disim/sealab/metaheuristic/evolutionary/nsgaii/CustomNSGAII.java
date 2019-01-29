package it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.mutable.MutableInt;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.comparator.ObjectiveComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.solutionattribute.impl.CrowdingDistance;

import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;

/**
 * @author Mattia D'Emidio
 */
@SuppressWarnings("serial")
public class CustomNSGAII<S extends Solution<?>> extends NSGAII<S> {

	private ProgressBar pbar;
	private MutableInt done;
	private MutableInt total;
	
	private String name;

	/**
	 * Constructor
	 */
	public CustomNSGAII(Problem<S> problem, int maxIterations, int populationSize,
			CrossoverOperator<S> crossoverOperator, MutationOperator<S> mutationOperator,
			SelectionOperator<List<S>, S> selectionOperator, SolutionListEvaluator <S> evaluator) {
		super(problem, maxIterations, populationSize, crossoverOperator, mutationOperator, selectionOperator,
				evaluator);
		this.name = "Custom_NSGA_II";
	}

	@Override
	protected void updateProgress() {
		evaluations += getMaxPopulationSize();
		pbar.update(done, total, getMaxPopulationSize());

	}

	@Override
	protected boolean isStoppingConditionReached() {
		return evaluations >= maxEvaluations;
	}

	@Override
	protected void initProgress() {
		evaluations = getMaxPopulationSize();
		this.pbar = new ProgressBar(
				"Evolutionary Algorithm Optimization in Progress: mating-selecting-mutating individuals");
		this.done = new MutableInt(0);
		this.total = new MutableInt(this.maxEvaluations);
		pbar.update(done, total, getMaxPopulationSize());
	}

	public void computeCrowdingDistances() {
		List<S> solutionList = this.getPopulation();
		int size = solutionList.size();

		if (size == 0) {
			return;
		}

		if (size == 1) {
			this.getResult().get(0).setAttribute(getAttributeIdentifier(), Double.POSITIVE_INFINITY);
			return;
		}

		if (size == 2) {
			solutionList.get(0).setAttribute(getAttributeIdentifier(), Double.POSITIVE_INFINITY);
			solutionList.get(1).setAttribute(getAttributeIdentifier(), Double.POSITIVE_INFINITY);

			return;
		}

		for (int i = 0; i < size; i++) {
			solutionList.get(i).setAttribute(getAttributeIdentifier(), 0.0);
		}

		double objetiveMaxn;
		double objetiveMinn;
		double distance;

		int numberOfObjectives = solutionList.get(0).getNumberOfObjectives();

		for (int i = 0; i < numberOfObjectives; i++) {
			// Sort the population by Obj n
			Collections.sort(solutionList, new ObjectiveComparator<S>(i));
			objetiveMinn = solutionList.get(0).getObjective(i);
			objetiveMaxn = solutionList.get(solutionList.size() - 1).getObjective(i);

			// Set de crowding distance
			solutionList.get(0).setAttribute(getAttributeIdentifier(), Double.POSITIVE_INFINITY);
			solutionList.get(size - 1).setAttribute(getAttributeIdentifier(), Double.POSITIVE_INFINITY);

			for (int j = 1; j < size - 1; j++) {
				distance = solutionList.get(j + 1).getObjective(i) - solutionList.get(j - 1).getObjective(i);
				distance = distance / (objetiveMaxn - objetiveMinn);
				distance += (double) solutionList.get(j).getAttribute(getAttributeIdentifier());
				solutionList.get(j).setAttribute(getAttributeIdentifier(), distance);
			}
		}
	}

	private Object getAttributeIdentifier() {
		return CrowdingDistance.class;
	}

	@Override
	protected List<S> createInitialPopulation() {
		pbar = new ProgressBar("Evolutionary Algorithm Optimization in Progress: creating individuals");
		this.done = new MutableInt(0);
		this.total = new MutableInt(getMaxPopulationSize());
		List<S> population = new ArrayList<>(getMaxPopulationSize());
		for (int i = 0; i < getMaxPopulationSize(); i++) {
			S newIndividual = getProblem().createSolution();
			population.add(newIndividual);
			pbar.update(done, total, 1);

		}
		this.pbar = null;
		this.done = null;
		this.total = null;
		return population;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		this.name = n;
	}

	@Override
	public String getDescription() {
		return "Nondominated Sorting Genetic Algorithm version II. Version using measures";
	}
}
