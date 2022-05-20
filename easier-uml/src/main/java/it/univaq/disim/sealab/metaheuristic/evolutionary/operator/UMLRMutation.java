package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.RefactoringActionFactory;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UMLRMutation extends RMutation<UMLRSolution> {

    /**
     * Constructor
     *
     * @param mutationProbability
     * @param distributionIndex
     */
    public UMLRMutation(double mutationProbability, double distributionIndex) {
        super(mutationProbability, distributionIndex);
    }

    /**
     * Perform the mutation operation
     */
    @Override
    protected void doMutation(double probability, UMLRSolution solution, int allowed_failures) {
        for (int i = 0; i < solution.getNumberOfVariables(); i++) {

            // guard condition
            if (JMetalRandom.getInstance().nextDouble() <= probability) {

                final Map<String, Set<String>> initialElements = solution.getInitialElements();

                for (int j = 0; j < allowed_failures; j++) {
                    Refactoring ref = solution.getVariable(i);

                    int randomPosition = JMetalRandom.getInstance().nextInt(0, ref.getActions().size() - 1);

                    // the refactoring action that will change
                    RefactoringAction candidateToBeMutated = ref.getActions().get(randomPosition);

                    Map<String, Set<String>> filteredAvailableElements = filterOutElementOf(solution, candidateToBeMutated);

                    // filter out elements belong to the candidateToBeRemoved from the availableElements
//                    for (String k : availableElements.keySet()) {
//                        Set<String> kFilteredElements = availableElements.get(k);
//                        if (candidateToBeMutated.getCreatedElements().get(k) != null)
//                            kFilteredElements = availableElements.get(k).stream().filter(aElem -> !candidateToBeMutated.getCreatedElements().get(k).contains(aElem)).collect(Collectors.toSet());
//                        filteredAvailableElements.put(k, kFilteredElements);
//                    }

                    RefactoringAction newCandidate = RefactoringActionFactory.getRandomAction(filteredAvailableElements, initialElements);
                    ref.getActions().set(randomPosition, newCandidate);

                    if (solution.isFeasible()) {
                        solution.setMutated(true);
                        break;
                    }

                    filteredAvailableElements = filterOutElementOf(solution, newCandidate);

                }
            }
        }
    }

    private Map<String, Set<String>> filterOutElementOf(UMLRSolution solution, RefactoringAction refactoringAction) {
        Map<String, Set<String>> availableElements = solution.getAvailableElements();

        Map<String, Set<String>> filteredAvailableElements = new HashMap<>();
        // filter out elements belong to the candidateToBeRemoved from the availableElements
        for (String k : availableElements.keySet()) {
            Set<String> kFilteredElements = availableElements.get(k);
            if (refactoringAction.getCreatedElements().get(k) != null)
                kFilteredElements = availableElements.get(k).stream().filter(aElem -> !refactoringAction.getCreatedElements().get(k).contains(aElem)).collect(Collectors.toSet());
            filteredAvailableElements.put(k, kFilteredElements);
        }
        return filteredAvailableElements;
    }
}
