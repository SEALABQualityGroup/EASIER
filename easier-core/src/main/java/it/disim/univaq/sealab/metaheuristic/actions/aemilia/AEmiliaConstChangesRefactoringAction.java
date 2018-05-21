package it.disim.univaq.sealab.metaheuristic.actions.aemilia;

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

import it.disim.univaq.sealab.metaheuristic.evolutionary.Controller;
import it.disim.univaq.sealab.metaheuristic.evolutionary.RSequence;
import it.disim.univaq.sealab.metaheuristic.evolutionary.RSolution;
import it.disim.univaq.sealab.metaheuristic.managers.Manager;
import it.disim.univaq.sealab.metaheuristic.managers.MetamodelManager;
import it.disim.univaq.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import it.disim.univaq.sealab.metaheuristic.managers.ocl.OclManager;
import it.disim.univaq.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaStringManager;
import logicalSpecification.AndOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.ForAllOperator;
import logicalSpecification.LogicalSpecificationFactory;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.actions.AEmilia.AEmiliaConstChangesAction;
import logicalSpecification.actions.AEmilia.impl.AEmiliaConstChangesActionImpl;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.DataType.Integer;
import metamodel.mmaemilia.DataType.Special;
import metamodel.mmaemilia.DataType.SpecialType;
import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Expressions.IdentExpr;
import metamodel.mmaemilia.Headers.ConstInit;

public class AEmiliaConstChangesRefactoringAction extends AEmiliaConstChangesActionImpl
		implements AEmiliaConstChangesAction, RefactoringAction {

	private RSolution solution;
	private Manager manager = Manager.getInstance(null);
	private double newWorkload;
	private double sourceConstInitOldValue;

	public AEmiliaConstChangesRefactoringAction(RSolution sol) {
		this.solution = sol;
		this.manager = Manager.getInstance(null);

		this.setModel(sol.getModel());
		// this.setSolution(sol);
		// this.setSourceConstInit(getRandomRate(sol));
		this.setSourceConstInit(getRandomConst(sol));
		this.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MetamodelManager.MAX_VALUE));
		this.setName("AEmiliaConstChangesAction_" + this.getSourceConstInit().getName());
		this.setSourceConstInitOldValue(
				Double.valueOf((String) ((IdentExpr) sourceConstInit.getInitConstExpr()).getName()));

		this.setParameters();
		this.createPreCondition();
		this.createPostCondition();

		this.setNumOfChanges(1);
	}

	private ConstInit getRandomConst(RSolution sol) {
		EList<ConstInit> listOfConsts = ((AEmiliaSpecification) sol.getModel()).getArchiTypeDecl().getHeader()
				.getInitConst();

		// List<ConstInit> listOfRandomRanges = new ArrayList<>();
		//
		// for (ConstInit c : listOfConsts) {
		// if (c.getInitConstData() instanceof Special
		// && ((Special) c.getInitConstData()).getType() == SpecialType.RATE &&
		// c.getName().contains("_rate"))
		// listOfRandomRanges.add(c);
		// }
		//
		if (listOfConsts.isEmpty()) {
			return null;
		}
		int index = JMetalRandom.getInstance().getRandomGenerator().nextInt(0, listOfConsts.size() - 1);
		if(manager.getController().getWorkloadRange() == -1 && listOfConsts.get(index).getName().contains("workload")) {
			do {
				index = JMetalRandom.getInstance().getRandomGenerator().nextInt(0, listOfConsts.size() - 1);
			} while(listOfConsts.get(index).getName().contains("workload"));
		}
		return listOfConsts.get(index);
	}

	private ConstInit getRandomRate(RSolution sol) {
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
		int rangeMax = listOfRandomRanges.size();
		return listOfRandomRanges.get((int) (RandomUtils.nextInt(rangeMin, rangeMax)));
	}

	public void execute() {
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		Number number;
		String rep = ((IdentExpr) sourceConstInit.getInitConstExpr()).getName();
		String rep_val = "";

		if (sourceConstInit.getInitConstData() instanceof Integer && sourceConstInit.getName().contains("_size")) {
			int val = java.lang.Integer.parseInt(((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
			((IdentExpr) sourceConstInit.getInitConstExpr())
					.setName(String.valueOf(((Double) (val * factorOfChange)).intValue()));
		} else if (sourceConstInit.getInitConstData() instanceof Special) {
			// && ((Special) sourceConstInit.getInitConstData()).getType() ==
			// SpecialType.RATE) {

			if (sourceConstInit.getName().contains("workload")) {
				newWorkload = manager.getController().getWorkloadRange();
				rep_val = String.format("%.4f", newWorkload);
			} else { // (sourceConstInit.getName().contains("rate")) {
				try {
					number = format.parse(rep);
					double val = number.doubleValue();
					rep_val = String.format("%.4f", ((val * factorOfChange)));
					// ((IdentExpr) sourceConstInit.getInitConstExpr()).setName(rep_val);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			((IdentExpr) sourceConstInit.getInitConstExpr()).setName(rep_val);
			log();
		}
		// else if (sourceConstInit.getInitConstData() instanceof Special
		// && ((Special) sourceConstInit).getType() == SpecialType.WEIGHT) {
		//
		//// String rep = ((IdentExpr) sourceConstInit.getInitConstExpr()).getName();
		//
		// try {
		// number = format.parse(rep);
		// double val = number.doubleValue();
		//// String rep_val = String.format("%.4f", ((val * factorOfChange)));
		// rep_val = String.format("%.4f", ((val * factorOfChange)));
		// ((IdentExpr) sourceConstInit.getInitConstExpr()).setName(rep_val);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// }
		else {
			System.out.println("Not supported type: " + sourceConstInit.getInitConstData().toString());
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public double generateFactorOfChange() {
		double rangeMin = 0.5;
		double rangeMax = 2;

		factorOfChange = RandomUtils.nextDouble(rangeMin, rangeMax);

		NumberFormat format = NumberFormat.getInstance(Locale.US);
		Number number;

		try {
			number = format.parse(new DecimalFormat("##.####").format(factorOfChange));
			factorOfChange = number.doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return factorOfChange;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void log() {
		if (sourceConstInit.getName().contains("workload"))
			Controller.logger_
					.info("CHANGING WORKLOAD " + sourceConstInit.getName() + " (" + getSourceConstInitOldValue()
							+ ") --> " + ((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
		else
			Controller.logger_.info("CHANGING RATE/WEIGHT/SIZE " + sourceConstInit.getName() + " ("
					+ getSourceConstInitOldValue() + ") * (" + getFactorOfChange() + ") --> "
					+ ((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
	}

	public RSolution getSolution() {
		return solution;
	}

	public void setSolution(RSolution solution) {
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
		FOLSpecification constChangePreSpecification = Manager.getInstance(new AemiliaManager())
				.createFOLSpectification("ConstChangePreCondition");
		AndOperator constChangePreAnd = Manager.getInstance(new AemiliaManager()).createAndOperator();

		ExistsOperator existsConstInitToChange = Manager.getInstance(new AemiliaManager())
				.createExistsOperator(getSourceConstInitSVP(), getAllConstInitsMVP());
		constChangePreAnd.getArguments().add(existsConstInitToChange);

		constChangePreSpecification.setRootOperator(constChangePreAnd);
		preCondition.setConditionFormula(constChangePreSpecification);
		setPre(preCondition);
	}

	public void createPostCondition() {
		PostCondition postCondition = LogicalSpecificationFactory.eINSTANCE.createPostCondition();
		FOLSpecification constChangePostSpecification = Manager.getInstance(new AemiliaManager())
				.createFOLSpectification("ConstChangePostCondition");
		AndOperator constChangePostAnd = Manager.getInstance(new AemiliaManager()).createAndOperator();

		ExistsOperator existsConstInitToChange = Manager.getInstance(new AemiliaManager())
				.createExistsOperator(getSourceConstInitSVP(), getAllConstInitsMVP());
		constChangePostAnd.getArguments().add(existsConstInitToChange);

		constChangePostSpecification.setRootOperator(constChangePostAnd);
		postCondition.setConditionFormula(constChangePostSpecification);
		setPost(postCondition);
	}

	public AEmiliaConstChangesRefactoringAction clone(RSolution solution) {
		AEmiliaConstChangesRefactoringAction newAction = new AEmiliaConstChangesRefactoringAction(solution);

		for (ConstInit constInit : ((AEmiliaSpecification) solution.getModel()).getArchiTypeDecl().getHeader()
				.getInitConst()) {
			if (constInit.getName().equals(this.getSourceConstInit().getName())) {
				newAction.setSourceConstInit(constInit);
				break;
			}
		}

		newAction.setFactorOfChange(this.getFactorOfChange());
		newAction.setSolution(solution);
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

}
