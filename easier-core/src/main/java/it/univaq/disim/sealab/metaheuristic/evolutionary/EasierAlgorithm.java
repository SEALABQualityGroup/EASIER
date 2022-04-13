package it.univaq.disim.sealab.metaheuristic.evolutionary;

public interface EasierAlgorithm {

	void clear();
	void populationToCSV();
	boolean isStoppingConditionReached();
	boolean isStagnantState();
}
