package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.uma.jmetal.solution.AbstractSolution;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public abstract class RSolution<T> extends AbstractSolution<T> {// AbstractGenericSolution<Refactoring, RProblem<?>> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected Path modelPath, sourceModelPath;

    protected boolean refactored;
    protected boolean crossovered;
    protected boolean mutated;

    protected static int SOLUTION_COUNTER = 0;

    protected int name;

    protected double perfQ;
    protected int numPAs;
    protected double reliability;
    protected double architecturalChanges;
    public static final int VARIABLE_INDEX;

    protected RSolution<T>[] parents;

    public static int MutationCounter = 0;
    public static int XOverCounter = 0;

    protected int allowedFailures;
    protected int refactoringLength;
    protected String problemName;

    static {
        VARIABLE_INDEX = 0;
    }

    protected RSolution(Path srcModelPath, String pName) {
        super(1, Configurator.eINSTANCE.getObjectives());
        allowedFailures = Configurator.eINSTANCE.getAllowedFailures();
        refactoringLength = Configurator.eINSTANCE.getLength();
        sourceModelPath = srcModelPath;
        problemName = pName;
    }

    /**
     * Constructor
     */
//    protected RSolution(int numberOfVariables, int numberOfObjectives, int numberOfConstraints, RProblem<?> p) {
//        super(numberOfVariables, numberOfObjectives, numberOfConstraints);
//
//    }

//	public RSolution(RProblem<T> p) {
////		super(problem);
//		problem = p;
//		crossovered = false;
//		mutated = false;
//		refactored = false;
//	}
    public abstract void countingPAs();

    public abstract double evaluatePerformance();

    public abstract void executeRefactoring();

    public abstract void applyTransformation();

    public abstract void computeReliability();

    public abstract void computeArchitecturalChanges();

    public abstract void computeScenarioRT();

    public abstract boolean alter(int i);

    public abstract void invokeSolver();

    public abstract boolean isFeasible();

    public RefactoringAction getActionAt(int index) {
        return ((Refactoring) getVariable(VARIABLE_INDEX)).getActions().get(index);
    }

    public Path getModelPath() {
        return modelPath;
    }

    public double getReliability() {
        return reliability;
    }

    public Path getSourceModelPath() {
        return sourceModelPath;
    }

    public void setRefactored() {
        this.refactored = true;
    }

    public boolean isRefactored() {
        return refactored;
    }

    public void setCrossovered() {
        this.crossovered = true;
        XOverCounter++;
    }

    public void setMutated() {
        this.mutated = true;
        MutationCounter++;
    }

    public boolean isMutated() {
        return mutated;
    }

    public boolean isCrossovered() {
        return crossovered;
    }

    public double getPerfQ() {
        return perfQ;
    }

    public void setPerfQ(float perfQ) {
        this.perfQ = perfQ;
    }

    public int getPAs() {
        return numPAs;
    }

    public int getName() {
        return name;
    }

    public String getProblemName() {
        return problemName;
    }

    public static synchronized int getCounter() {
        return SOLUTION_COUNTER++;
    }

    public void setName() {
        this.name = getCounter();
    }

    protected void resetParents() {
        if (this.parents != null) {
            this.parents[0] = null;
            this.parents[1] = null;
        }
    }

    public RSolution[] getParents() {
        return parents;
    }

    public void setParents(RSolution parent1, RSolution parent2) {
        this.parents[0] = parent1;
        this.parents[1] = parent2;
    }

    /**
     * Prints a VAR file
     */
    public String getVariableString(int index) {

        String strValue = this.getName() + ";";

        List<Double> objs = new ArrayList<>();
        for (int i = 0; i < getNumberOfObjectives(); i++) {
            objs.add(getObjective(i));
        }

        strValue += objs.stream().map(o -> String.valueOf(o)).collect(Collectors.joining(";"));
        strValue += ";";
        strValue += getName() + ",";
        strValue += ((Refactoring) this.getVariable(0)).getActions().stream().map(act -> act.toCSV())
                .collect(Collectors.joining(","));
        return strValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
//		if (!super.equals(obj))
//			return false;
        if (getClass() != obj.getClass())
            return false;
        RSolution<T> other = (RSolution<T>) obj;
        if (crossovered != other.crossovered)
            return false;
        if (modelPath == null ^ other.modelPath == null) {
            return false;
        } //else if (!modelPath.equals(other.modelPath))
//			return false;
        if (mutated != other.mutated)
            return false;
//		if (name != other.name)
//			return false;
        if (numPAs != other.numPAs)
            return false;
        if (Double.doubleToLongBits(perfQ) != Double.doubleToLongBits(other.perfQ))
            return false;
//		if (refactored != other.refactored)
//			return false;
        if (Double.doubleToLongBits(reliability) != Double.doubleToLongBits(other.reliability))
            return false;
        if (parents.length != other.parents.length)
            return false;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] != other.parents[i]) {
                return false;
            }
        }
//        if (!Arrays.equals(parents, other.parents))
//            return false;
        if (getVariable(VARIABLE_INDEX) == null ^ other.getVariable(VARIABLE_INDEX) == null){
                return false;
        } else if (!getVariable(VARIABLE_INDEX).equals(other.getVariable(VARIABLE_INDEX)))
            return false;
        return true;
    }

    public double getArchitecturalChanges() {
        return architecturalChanges;
    }

    /*
     * Returns the solution data as a CSV format
     * "solID,perfQ,#changes,pas,reliability"
     */
    public String objectiveToCSV() {
        return String.format("%s,%s,%s,%s,%s", this.getName(), this.perfQ, this.getArchitecturalChanges(), this.numPAs,
                this.reliability);
    }

    public void refactoringToCSV() {
        new FileUtils().refactoringDumpToCSV(((Refactoring) getVariable(0)).toCSV());
    }

    /**
     * Check if two RSolutions have the same objectives values. If a local
     * minimum/maximum is reached then the two solutions should have the same
     * objective values
     *
     * @param rSolution
     * @return true if two solutions have the same objective values, false otherwise
     */
    public boolean isLocalOptmimalPoint(RSolution<?> rSolution) {
        double ePas = Configurator.eINSTANCE.getLocalOptimalPointEpsilon()[0];
        double eRel = Configurator.eINSTANCE.getLocalOptimalPointEpsilon()[1];
        double ePerfQ = Configurator.eINSTANCE.getLocalOptimalPointEpsilon()[2];
        double eChanges = Configurator.eINSTANCE.getLocalOptimalPointEpsilon()[3];

        return (Math.abs(this.getPAs()) <= Math.abs(rSolution.getPAs()) + ePas
                && Math.abs(this.getPAs()) >= Math.abs(rSolution.getPAs()) - ePas)
                && (Math.abs(this.getArchitecturalChanges()) <= Math.abs(rSolution.getArchitecturalChanges()) * eChanges
                && Math.abs(this.getArchitecturalChanges()) >= Math.abs(rSolution.getArchitecturalChanges()) / eChanges)
                && (Math.abs(this.getPerfQ()) <= Math.abs(rSolution.getPerfQ()) * ePerfQ
                && Math.abs(this.getPerfQ()) >= Math.abs(rSolution.getPerfQ()) / ePerfQ)
                && (Math.abs(this.getReliability()) <= Math.abs(rSolution.getReliability()) * eRel
                && Math.abs(this.getReliability()) >= Math.abs(rSolution.getReliability()) / eRel);
    }
}
