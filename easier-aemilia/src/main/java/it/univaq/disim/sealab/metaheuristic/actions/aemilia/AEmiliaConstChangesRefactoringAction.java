package it.univaq.disim.sealab.metaheuristic.actions.aemilia;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaStringManager;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import logicalSpecification.AndOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.LogicalSpecificationFactory;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.actions.AEmilia.AEmiliaConstChangesAction;
import logicalSpecification.actions.AEmilia.impl.AEmiliaConstChangesActionImpl;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.DataType.Integer;
import metamodel.mmaemilia.DataType.Special;
import metamodel.mmaemilia.DataType.SpecialType;
import metamodel.mmaemilia.Expressions.IdentExpr;
import metamodel.mmaemilia.Headers.ConstInit;

public class AEmiliaConstChangesRefactoringAction extends AEmiliaConstChangesActionImpl
		implements AEmiliaConstChangesAction, RefactoringAction {

	private AemiliaRSolution solution;
	private Manager manager;
	private double newWorkload;
	private double sourceConstInitOldValue;

	public AEmiliaConstChangesRefactoringAction(AemiliaRSolution sol) {
		this.solution = sol;
		this.manager = sol.getManager();

		this.setModel(sol.getModel());
		// this.setSolution(sol);
		// this.setSourceConstInit(getRandomRate(sol));
		this.setSourceConstInit(getRandomConst(sol));
		if (sourceConstInit.getName().contains("workload"))
			System.err.println("There was an error in filtering workload");
		this.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MetamodelManager.MAX_VALUE));

		this.setSourceConstInitOldValue(
				Double.valueOf((String) ((IdentExpr) sourceConstInit.getInitConstExpr()).getName()));

		this.setParameters();
		this.createPreCondition();
		this.createPostCondition();

		this.setNumOfChanges(sol.getController().getConfigurator().getConstChangesWeight());
	}

	private ConstInit getRandomConst(AemiliaRSolution sol) {
		EList<ConstInit> listOfConsts = ((AEmiliaSpecification) sol.getModel()).getArchiTypeDecl().getHeader()
				.getInitConst();

		if (listOfConsts.isEmpty()) {
			return null;
		}

		// if (sol.getController().getWorkloadRange() == -1
		// && listOfConsts.get(index).getName().contains("workload")) {
		// do {
		// index = JMetalRandom.getInstance().getRandomGenerator().nextInt(0,
		// listOfConsts.size() - 1);
		// } while (listOfConsts.get(index).getName().contains("workload"));
		// }
		if (sol.getController().getConfigurator().getWorkloadRange() == -1)
			return getRandomRate(sol);
		else {
			return listOfConsts
					.get(JMetalRandom.getInstance().getRandomGenerator().nextInt(0, listOfConsts.size() - 1));
		}
	}

	private ConstInit getRandomRate(AemiliaRSolution sol) {
		EList<ConstInit> listOfConsts = ((AEmiliaSpecification) sol.getModel()).getArchiTypeDecl().getHeader()
				.getInitConst();

		List<ConstInit> listOfRandomRanges = new ArrayList<>();

		for (ConstInit c : listOfConsts) {
			if (c.getInitConstData() instanceof Special
					&& ((Special) c.getInitConstData()).getType() == SpecialType.RATE && c.getName().contains("_rate"))
				listOfRandomRanges.add(c);
		}

		if (listOfRandomRanges.isEmpty()) {
			return null;
		}

		int rangeMin = 0;
		int rangeMax = listOfRandomRanges.size() - 1;
//		return listOfRandomRanges.get((int) (RandomUtils.nextInt(rangeMin, rangeMax)));
		ConstInit returnValue = listOfRandomRanges
				.get(JMetalRandom.getInstance().getRandomGenerator().nextInt(rangeMin, rangeMax));
		if (returnValue.getName().contains("workload"))
			System.err.println("There was an error in filtering random rate!!!");
		return returnValue;
	}

	public void execute() {
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		Number number;
		String rep = ((IdentExpr) sourceConstInit.getInitConstExpr()).getName();
		String rep_val = "";

		if (sourceConstInit.getInitConstData() instanceof Integer && sourceConstInit.getName().contains("_size")) {
			int val = java.lang.Integer.parseInt(((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
			((IdentExpr) sourceConstInit.getInitConstExpr())
					.setName(String.valueOf(((Double) (val * generateFactorOfChange())).intValue()));
		} else if (sourceConstInit.getInitConstData() instanceof Special
				&& ((Special) sourceConstInit.getInitConstData()).getType() == SpecialType.RATE) {

			if (sourceConstInit.getName().contains("workload")) {
				newWorkload = manager.getController().getConfigurator().getWorkloadRange();
				rep_val = String.format("%.4f", newWorkload);
			} else { // (sourceConstInit.getName().contains("rate")) {
				try {
					number = format.parse(rep);
					double val = number.doubleValue();
					rep_val = String.format("%.4f", ((val * generateFactorOfChange())));
					// ((IdentExpr) sourceConstInit.getInitConstExpr()).setName(rep_val);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			((IdentExpr) sourceConstInit.getInitConstExpr()).setName(rep_val);
		} else if (sourceConstInit.getInitConstData() instanceof Special
				&& ((Special) sourceConstInit.getInitConstData()).getType() == SpecialType.WEIGHT) {
			rep = ((IdentExpr) sourceConstInit.getInitConstExpr()).getName();
			try {
				number = format.parse(rep);
				double val = number.doubleValue();
				double newVal = 2;

				while (newVal > 1) {
					newVal = val * generateFactorOfChange();
					rep_val = String.format("%.2f", ((newVal)));
				}
				((IdentExpr) sourceConstInit.getInitConstExpr()).setName(rep_val);
			} catch (ParseException e) {
				System.err.println("Error in parsing the value --> " + rep);
				e.printStackTrace();
			}

		} else {
			System.out.println("Not supported type: " + sourceConstInit.getInitConstData().toString());
		}
		log();
	}

	public double generateFactorOfChange() {
		double rangeMin = 0.5;
		double rangeMax = 2;

		double factorOfChange = RandomUtils.nextDouble(rangeMin, rangeMax);
		this.factorOfChange = factorOfChange;

		NumberFormat format = NumberFormat.getInstance(Locale.US);
		Number number;

		try {
			number = format.parse(new DecimalFormat("##.####").format(factorOfChange));
			factorOfChange = number.doubleValue();
		} catch (ParseException e) {
			System.err.println("Error in formatting the factor of change --> " + factorOfChange);
			e.printStackTrace();
		}
		return factorOfChange;
	}

	@Override
	public String toString() {
		if (sourceConstInit.getName().contains("workload"))
			return "CHANGING WORKLOAD " + sourceConstInit.getName() + " (" + getSourceConstInitOldValue() + ") --> "
					+ ((IdentExpr) sourceConstInit.getInitConstExpr()).getName();
		else
			return "CHANGING RATE/WEIGHT/SIZE " + sourceConstInit.getName() + " (" + getSourceConstInitOldValue()
					+ ") * (" + getFactorOfChange() + ") --> "
					+ ((IdentExpr) sourceConstInit.getInitConstExpr()).getName();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 */
	public void log() {
//		if (sourceConstInit.getName().contains("workload"))
//			EasierLogger.logger_
//					.info("CHANGING WORKLOAD " + sourceConstInit.getName() + " (" + getSourceConstInitOldValue()
//							+ ") --> " + ((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
//		else
//			EasierLogger.logger_.info("CHANGING RATE/WEIGHT/SIZE " + sourceConstInit.getName() + " ("
//					+ getSourceConstInitOldValue() + ") * (" + getFactorOfChange() + ") --> "
//					+ ((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
	}

	public AemiliaRSolution getSolution() {
		return solution;
	}

	public void setSolution(AemiliaRSolution solution) {
		this.solution = solution;
	}

	public EObject getModel() {
		return this.solution.getModel();
	}

	public void setParameters() {
		OclManager oclManager = manager.getOclManager();
		OclAemiliaStringManager oclStringManager = (OclAemiliaStringManager) manager.getOclStringManager();

		if (sourceConstInit != null) {
			setSourceConstInitSVP(
					manager.createSingleValueParameter(oclStringManager.getConstInitQuery(sourceConstInit.getName())));
		}

		setAllConstInitsMVP(manager.createMultipleValuedParameter(oclStringManager.getAllConstInitListQuery()));

	}

	public void createPreCondition() {
		PreCondition preCondition = LogicalSpecificationFactory.eINSTANCE.createPreCondition();
		FOLSpecification constChangePreSpecification = manager.createFOLSpectification("ConstChangePreCondition");
		AndOperator constChangePreAnd = manager.createAndOperator();

		ExistsOperator existsConstInitToChange = manager.createExistsInCollectionOperator(getSourceConstInitSVP(),
				getAllConstInitsMVP());
		constChangePreAnd.getArguments().add(existsConstInitToChange);

		constChangePreSpecification.setRootOperator(constChangePreAnd);
		preCondition.setConditionFormula(constChangePreSpecification);
		setPre(preCondition);
	}

	public void createPostCondition() {
		PostCondition postCondition = LogicalSpecificationFactory.eINSTANCE.createPostCondition();
		FOLSpecification constChangePostSpecification = manager.createFOLSpectification("ConstChangePostCondition");
		AndOperator constChangePostAnd = manager.createAndOperator();

		ExistsOperator existsConstInitToChange = manager.createExistsInCollectionOperator(getSourceConstInitSVP(),
				getAllConstInitsMVP());
		constChangePostAnd.getArguments().add(existsConstInitToChange);

		constChangePostSpecification.setRootOperator(constChangePostAnd);
		postCondition.setConditionFormula(constChangePostSpecification);
		setPost(postCondition);
	}

	
	//TODO remove it
	public AEmiliaConstChangesRefactoringAction clone(AemiliaRSolution solution) {
		AEmiliaConstChangesRefactoringAction newAction = new AEmiliaConstChangesRefactoringAction(solution);

		for (ConstInit constInit : ((AEmiliaSpecification) solution.getModel()).getArchiTypeDecl().getHeader()
				.getInitConst()) {
			if (constInit.getName().equals(this.getSourceConstInit().getName())) {
				newAction.setSourceConstInit(constInit);
				break;
			}
		}

		newAction.setFactorOfChange(this.getFactorOfChange());
//		newAction.setSolution(solution);
		newAction.setNumOfChanges(this.getNumOfChanges());
		newAction.setCost(this.getCost());
		newAction.setName(this.getName());

		newAction.setParameters();
		newAction.createPreCondition();
		newAction.createPostCondition();

		// TODO to be checked

		return newAction;
	}

	public double getSourceConstInitOldValue() {
		return sourceConstInitOldValue;
	}

	public void setSourceConstInitOldValue(double sourceConstInitOldValue) {
		this.sourceConstInitOldValue = sourceConstInitOldValue;
	}

	@Override
	public RefactoringAction clone(RSolution solution) {
		AEmiliaConstChangesRefactoringAction newAction = new AEmiliaConstChangesRefactoringAction(
				(AemiliaRSolution) solution);

		for (ConstInit constInit : ((AEmiliaSpecification) solution.getModel()).getArchiTypeDecl().getHeader()
				.getInitConst()) {
			if (constInit.getName().equals(this.getSourceConstInit().getName())) {
				newAction.setSourceConstInit(constInit);
				break;
			}
		}

		newAction.setFactorOfChange(this.getFactorOfChange());
//		newAction.setSolution(solution);
		newAction.setNumOfChanges(this.getNumOfChanges());
		newAction.setCost(this.getCost());
		newAction.setName(this.getName());

		newAction.setParameters();
		newAction.createPreCondition();
		newAction.createPostCondition();

		// TODO to be checked

		return newAction;
	}

	@Override
	public void cleanUp() {	}
}
