package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

//public class UMLRCrossover<S extends UMLRSolution> extends RCrossover<S> {
public class UMLRCrossover extends RCrossover<UMLRSolution> {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public UMLRCrossover(double crossoverProbability) {
        super(crossoverProbability);
    }

    /**
     * Perform the crossover operation.
     * <p>
     * The result can be
     * <ul>
     *     <li>parent1, parent2 : when no crossover operation took place</li>
     *     <li>parent1,child2 : when child1 is not applicable</li>
     *     <li>child1,parent2 : when child2 is not applicable</li>
     *     <li>child1,child2 : when the crossover operation took place</li>
     * </ul>
     *
     * @param probability Crossover setProbability
     * @param parent1     The first parent
     * @param parent2     The second parent
     * @return An array containing the two solutions
     */
    public List<UMLRSolution> doCrossover(double probability, UMLRSolution parent1, UMLRSolution parent2) {

        List<UMLRSolution> offspring = new ArrayList<>(2);
        UMLRSolution parent1copy = new UMLRSolution(parent1);
        offspring.add(parent1copy);

        UMLRSolution parent2copy = new UMLRSolution(parent2);
        offspring.add(parent2copy);

        if (JMetalRandom.getInstance().nextDouble() < probability) {
            // 1. Get the length of a solution
            int refactoringLength = Configurator.eINSTANCE.getLength();

            // 2. Calculate the point to make the crossover
            // it can be between 1 and length -1
            int crossoverPoint = JMetalRandom.getInstance().nextInt(1, refactoringLength - 1);

            // 5. Apply the crossover to the variable;
//            offspring = parent1.createChildren(parent2, crossoverPoint);

            Path sourceModelPath = parent1.getSourceModelPath();
            String problemName = parent1.getProblemName();

            UMLRSolution child1 = new UMLRSolution(sourceModelPath, problemName);
            UMLRSolution child2 = new UMLRSolution(sourceModelPath, problemName);

            child1.setParents(parent1, parent2);
            child2.setParents(parent2, parent1);

            // create the child by combining the two parents using the crossover point
            Map<Integer, List<List<RefactoringAction>>> parent1IndependentSequence = independentSequence(parent1);
            Map<Integer, List<List<RefactoringAction>>> parent2IndependentSequence = independentSequence(parent2);

            Refactoring child1Refactoring = new Refactoring(child1.getModelPath().toString());
            child1Refactoring.getActions().addAll(parent1IndependentSequence.get(crossoverPoint).get(JMetalRandom.getInstance().nextInt(0,
                    parent1IndependentSequence.get(crossoverPoint).size() - 1)));
            child1Refactoring.getActions().addAll(parent2IndependentSequence.get(refactoringLength - crossoverPoint).
                    get(JMetalRandom.getInstance().nextInt(0,
                            parent2IndependentSequence.get(refactoringLength - crossoverPoint).size() - 1)));
            child1.setVariable(0, child1Refactoring);
            child1.setCrossovered(true);

            Refactoring child2Refactoring = new Refactoring(child2.getModelPath().toString());
            child2Refactoring.getActions().addAll(parent2IndependentSequence.get(crossoverPoint).get(JMetalRandom.getInstance().nextInt(0,
                    parent2IndependentSequence.get(crossoverPoint).size() - 1)));
            child2Refactoring.getActions().addAll(parent1IndependentSequence.get(refactoringLength - crossoverPoint).
                    get(JMetalRandom.getInstance().nextInt(0,
                            parent1IndependentSequence.get(refactoringLength - crossoverPoint).size() - 1)));
            child2.setVariable(0, child2Refactoring);
            child2.setCrossovered(true);

            offspring.set(0, child1);
            offspring.set(1, child2);

        }

        // It can be equal to parent1, parent2; parent1,child2; child1,parent2; child1,child2;
        return offspring;
    }

    /**
     * Generates a list of subsets by incrementing the given subset of one element.
     * The new element must occur after the subset in the refactoring sequence.
     *
     * @param root     Starting subset to increment for the generation of new subsets
     * @param position Position at which the root subset was generated
     * @return List of new subsets generated from root.
     */
    List<AbstractMap.SimpleEntry<Integer, List<RefactoringAction>>> generateSubsetsByIncrement(List<RefactoringAction> root, int position, UMLRSolution sol) {

        // refactoring length
        int refactoringLength = Configurator.eINSTANCE.getLength();

        // Entire refactoring sequence
        List<RefactoringAction> sequence = sol.getVariable(0).getActions();

        // New subsets to be generated
        List<AbstractMap.SimpleEntry<Integer, List<RefactoringAction>>> subsets = new ArrayList<>();

        // Check if there are still actions in the sequence we can take starting after the given position
        int start = position + 1;
        if (start > refactoringLength)
            return subsets;

        // Loop over the refactoring sequence after position
        for (int j = start; j < sequence.size(); j++) {
            List<RefactoringAction> newSet = new ArrayList<>();

            // Add all the elements already in the root subset,
            // that is the subset we use as a base for the generation.
            newSet.addAll(root);

            // Add one more element from the refactoring sequence
            newSet.add(sequence.get(j));

            subsets.add(new AbstractMap.SimpleEntry<>(j, newSet));
        }

        return subsets;
    }


    /**
     * Compute the list of all the possible subsets of the refactoring sequence that are independent.
     *
     * @return List of independent subsets.
     */
    Map<Integer, List<List<RefactoringAction>>> independentSequence(UMLRSolution sol) {

        // Entire refactoring sequence
        List<RefactoringAction> sequence = sol.getVariable(0).getActions();

        // Map that will contain all the possible subsequences (subsets) of a given length.
        // The SimpleEntry keeps the position at which the subset was generated and the subset itself.
        Map<Integer, List<AbstractMap.SimpleEntry<Integer, List<RefactoringAction>>>> possibleSubsets = new HashMap<>();

        // Initialize the map by generating the subsequences of length 1
        possibleSubsets.put(1, new ArrayList<>(generateSubsetsByIncrement(new ArrayList<>(), -1, sol)));


        // Loop over the possible subsets length after length 1
        for (int subsetLength = 2; subsetLength < sequence.size(); subsetLength++) {

            // Generate the subsequences of length +1 from the last ones
            possibleSubsets.put(subsetLength, possibleSubsets.get(subsetLength - 1).stream()
                    .flatMap(set -> generateSubsetsByIncrement(set.getValue(), set.getKey(), sol).stream())
                    .collect(Collectors.toList()));
        }

        // The Map of independent subsequences
        return possibleSubsets.keySet().stream()
                .collect(Collectors.<Integer, Integer, List<List<RefactoringAction>>>toMap(
                        k -> k,
                        k -> possibleSubsets.get(k).stream().map(AbstractMap.SimpleEntry::getValue)
                                .filter(sol::isIndependent)
                                .collect(Collectors.toList())
                ));
    }

}
