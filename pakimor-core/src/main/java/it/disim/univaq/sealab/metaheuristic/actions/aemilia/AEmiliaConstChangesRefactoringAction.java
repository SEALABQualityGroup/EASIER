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

public class AEmiliaConstChangesRefactoringAction extends AEmiliaConstChangesActionImpl implements AEmiliaConstChangesAction, RefactoringAction{
	
	private RSolution solution;
	private Manager manager = Manager.getInstance(null);
	
	public AEmiliaConstChangesRefactoringAction(RSolution sol) {
		this.solution = sol;
		this.manager = Manager.getInstance(null);
		
		this.setModel(sol.getModel());
//		this.setSolution(sol);
		this.setSourceConstInit(getRandomRate(sol));
		this.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MetamodelManager.MAX_VALUE));
		this.setName("AEmiliaConstChangesAction_" + this.getSourceConstInit().getName());

		this.setParameters();
		this.createPreCondition();
		this.createPostCondition();
		
		this.setNumOfChanges(1);
	}
	
	private ConstInit getRandomRate(RSolution sol) {
		EList<ConstInit> listOfConsts = ((AEmiliaSpecification) sol.getModel()).getArchiTypeDecl().getHeader()
				.getInitConst();

		List<ConstInit> listOfRandomRanges = new ArrayList<>();

		for (ConstInit c : listOfConsts) {
			if (c.getInitConstData() instanceof Special
					&& ((Special) c.getInitConstData()).getType() == SpecialType.RATE && c.getName().contains("_rate") )
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
		if (sourceConstInit.getInitConstData() instanceof Integer && sourceConstInit.getName().contains("_size")) {
			int val = java.lang.Integer.parseInt(((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
			((IdentExpr) sourceConstInit.getInitConstExpr())
					.setName(String.valueOf(((Double) (val * factorOfChange)).intValue()));
		} else if (sourceConstInit.getInitConstData() instanceof Special
				&& ((Special) sourceConstInit.getInitConstData()).getType() == SpecialType.RATE) {
			String rep = ((IdentExpr) sourceConstInit.getInitConstExpr()).getName();
			// rep = rep.replaceAll(",", ".");

			NumberFormat format = NumberFormat.getInstance(Locale.US);
			Number number;
			try {
				number = format.parse(rep);
				double val = number.doubleValue();
				String rep_val = String.format("%.4f", ((val * factorOfChange)));
				((IdentExpr) sourceConstInit.getInitConstExpr()).setName(rep_val);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// double val = java.lang.Double.parseDouble(rep);
			// String rep_val = String.format("%.4f", ((val * factorOfChange)));
			// rep_val = rep_val.replaceAll(",", ".");
			// ((IdentExpr) sourceConstInit.getInitConstExpr()).setName(rep_val);
		} else if (sourceConstInit.getInitConstData() instanceof Special
				&& ((Special) sourceConstInit).getType() == SpecialType.WEIGHT) {
			String rep = ((IdentExpr) sourceConstInit.getInitConstExpr()).getName();
			// rep = rep.replaceAll(",", ".");

			NumberFormat format = NumberFormat.getInstance(Locale.US);
			Number number;
			try {
				number = format.parse(rep);
				double val = number.doubleValue();
				String rep_val = String.format("%.4f", ((val * factorOfChange)));
				((IdentExpr) sourceConstInit.getInitConstExpr()).setName(rep_val);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// double val = java.lang.Double.parseDouble(rep);
			// ((IdentExpr)
			// sourceConstInit.getInitConstExpr()).setName(String.format("%.4f", ((val *
			// factorOfChange))));
		} else {
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
		 Controller.logger_.info(sourceConstInit.getName() + " --> "
		 + ((IdentExpr) sourceConstInit.getInitConstExpr()).getName() + " --> " +
		 factorOfChange);
//		 Controller.logger_.info("Model --> "+ this.getModel());
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
		//TODO
		OclManager oclManager = manager.getOclManager();
		OclAemiliaStringManager oclStringManager = (OclAemiliaStringManager) manager.getOclStringManager();

		if (sourceConstInit != null) {
			setSourceConstInitSVP(
					manager.createSingleValueParameter(oclStringManager.getConstInitQuery(sourceConstInit.getName())));
		}

		setAllConstInitsMVP(manager.createMultipleValuedParameter(oclStringManager.getAllConstInitListQuery()));
		
	}
	
	public void createPreCondition() {
		//TODO
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
		//TODO
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
		
		for (ConstInit constInit : ((AEmiliaSpecification) solution.getModel()).getArchiTypeDecl().getHeader().getInitConst()) {
			if(constInit.getName().equals(this.getSourceConstInit().getName())) {
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
		
		//TODO to be checked

		return newAction;
	}

}
