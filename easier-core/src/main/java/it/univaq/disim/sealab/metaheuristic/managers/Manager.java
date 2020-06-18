package it.univaq.disim.sealab.metaheuristic.managers;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ParserException;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSequence;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
import logicalSpecification.Action;
import logicalSpecification.AndOperator;
import logicalSpecification.EqualOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.ForAllOperator;
import logicalSpecification.GreaterEqualOperator;
import logicalSpecification.GreaterOperator;
import logicalSpecification.LessEqualOperator;
import logicalSpecification.LessOperator;
import logicalSpecification.LogicalSpecificationFactory;
import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.NotOperator;
import logicalSpecification.Operator;
import logicalSpecification.OrOperator;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.SingleValuedParameter;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.Attachment;
import metamodel.mmaemilia.InputInteraction;
import metamodel.mmaemilia.OutputInteraction;

public abstract class Manager {

	protected String modelUri = null;

	protected MetamodelManager metamodelManager;

	protected OclManager oclManager;

	protected OclStringManager oclStringManager;

	protected Controller controller;

	public static int REFACTORING_COUNTER = 0;

	public void init(Path modelUri) {
		metamodelManager.init(modelUri);
	}

	public Manager() {}

	public Manager(MetamodelManager mmManager) {
		setMetamodelManager(mmManager);
		setOclManager(getMetamodelManager().getOclManager());
	}

	public static org.eclipse.emf.common.util.URI string2Uri(String stringToConvert) {
		URI uri = URI.createURI(stringToConvert);
		return uri;
	}

	public org.eclipse.emf.common.util.URI string2FileUri(String stringToConvert) {
		URI fileUri = URI.createFileURI(stringToConvert);
		return fileUri;
	}

	public Object getModel() {
		return metamodelManager.getModel();
	}

	public void setModel(EObject model) {
		metamodelManager.setModel(model);
	}

	public static double calculateCost(Refactoring r) {
		double sum = 0.0;
		for (RefactoringAction action : r.getActions()) {
			sum += action.getCost();
		}
		return sum;
	}

	public abstract Action getTautologyRandomAction(int n, RSequence seq); 

	public boolean evaluateFOL(FOLSpecification folSpec, EObject context) throws ParserException {
		if (folSpec.getRootOperator() instanceof NotOperator)
			return evaluateOperator((NotOperator) folSpec.getRootOperator(), context);
		else if (folSpec.getRootOperator() instanceof AndOperator)
			return evaluateOperator((AndOperator) folSpec.getRootOperator(), context);
		else if (folSpec.getRootOperator() instanceof OrOperator)
			return evaluateOperator((OrOperator) folSpec.getRootOperator(), context);
		else if (folSpec.getRootOperator() instanceof ForAllOperator)
			return evaluateOperator((ForAllOperator) folSpec.getRootOperator(), context);
		else if (folSpec.getRootOperator() instanceof ExistsOperator)
			return evaluateOperator((ExistsOperator) folSpec.getRootOperator(), context);
		else if (folSpec.getRootOperator() instanceof GreaterOperator)
			return evaluateOperator((GreaterOperator) folSpec.getRootOperator(), context);
		else if (folSpec.getRootOperator() instanceof GreaterOperator)
			return evaluateOperator((GreaterEqualOperator) folSpec.getRootOperator(), context);
		else if (folSpec.getRootOperator() instanceof GreaterEqualOperator)
			return evaluateOperator((LessOperator) folSpec.getRootOperator(), context);
		else if (folSpec.getRootOperator() instanceof LessOperator)
			return evaluateOperator((LessEqualOperator) folSpec.getRootOperator(), context);
		return evaluateOperator((EqualOperator) folSpec.getRootOperator());
	}


	public boolean evaluateOperator(NotOperator operator, Object contextualElement) throws ParserException {
		// System.out.print("NOT(");
		boolean app = true;
		if (operator.getArgument() instanceof NotOperator)
			app = !evaluateOperator((NotOperator) operator.getArgument(), contextualElement);
		else if (operator.getArgument() instanceof AndOperator)
			app = !evaluateOperator((AndOperator) operator.getArgument(), contextualElement);
		else if (operator.getArgument() instanceof OrOperator)
			app = !evaluateOperator((OrOperator) operator.getArgument(), contextualElement);
		else if (operator.getArgument() instanceof ForAllOperator)
			app = !evaluateOperator((ForAllOperator) operator.getArgument(), contextualElement);
		else if (operator.getArgument() instanceof ExistsOperator)
			app = !evaluateOperator((ExistsOperator) operator.getArgument(), contextualElement);
		else if (operator.getArgument() instanceof GreaterOperator)
			app = !evaluateOperator((GreaterOperator) operator.getArgument(), contextualElement);
		else if (operator.getArgument() instanceof GreaterEqualOperator)
			app = !evaluateOperator((GreaterEqualOperator) operator.getArgument(), contextualElement);
		else if (operator.getArgument() instanceof LessOperator)
			app = !evaluateOperator((LessOperator) operator.getArgument(), contextualElement);
		else if (operator.getArgument() instanceof LessEqualOperator)
			app = !evaluateOperator((LessEqualOperator) operator.getArgument(), contextualElement);
		else
			app = !evaluateOperator((EqualOperator) operator.getArgument(), contextualElement);
		// System.out.print(")");
		return app;
	}

	public boolean evaluateOperator(AndOperator operator, Object contextualElement) throws ParserException {
		// System.out.print("AND(");
		boolean app = true;
		Iterator<Operator> opsIterator = operator.getArguments().iterator();
		while (opsIterator.hasNext()) {
			Object appOp = opsIterator.next();
			// boolean app = true;
			if (appOp instanceof NotOperator)
				app = app && evaluateOperator((NotOperator) appOp, contextualElement);
			else if (appOp instanceof AndOperator)
				app = app && evaluateOperator((AndOperator) appOp, contextualElement);
			else if (appOp instanceof OrOperator)
				app = app && evaluateOperator((OrOperator) appOp, contextualElement);
			else if (appOp instanceof ForAllOperator)
				app = app && evaluateOperator((ForAllOperator) appOp, contextualElement);
			else if (appOp instanceof ExistsOperator)
				app = app && evaluateOperator((ExistsOperator) appOp, contextualElement);
			else if (appOp instanceof GreaterOperator)
				app = app && evaluateOperator((GreaterOperator) appOp, contextualElement);
			else if (appOp instanceof GreaterEqualOperator)
				app = app && evaluateOperator((GreaterEqualOperator) appOp, contextualElement);
			else if (appOp instanceof LessOperator)
				app = app && evaluateOperator((LessOperator) appOp, contextualElement);
			else if (appOp instanceof LessEqualOperator)
				app = app && evaluateOperator((LessEqualOperator) appOp, contextualElement);
			else
				app = app && evaluateOperator((EqualOperator) appOp, contextualElement);
			// if (opsIterator.hasNext())
			// System.out.print(", ");
			// if(!app)
			// return false;
		}
		// System.out.print(")");
		return app;
	}


	public boolean evaluateOperator(OrOperator operator, Object contextualElement) throws ParserException {
		// System.out.print("OR(");
		boolean app = false;
		Iterator<Operator> opsIterator = operator.getArguments().iterator();
		while (opsIterator.hasNext()) {
			Object appOp = opsIterator.next();
			// boolean app = false;
			if (appOp instanceof NotOperator)
				app = app || evaluateOperator((NotOperator) appOp, contextualElement);
			else if (appOp instanceof AndOperator)
				app = app || evaluateOperator((AndOperator) appOp, contextualElement);
			else if (appOp instanceof OrOperator)
				app = app || evaluateOperator((OrOperator) appOp, contextualElement);
			else if (appOp instanceof ForAllOperator)
				app = app || evaluateOperator((ForAllOperator) appOp, contextualElement);
			else if (appOp instanceof ExistsOperator)
				app = app || evaluateOperator((ExistsOperator) appOp, contextualElement);
			else if (appOp instanceof GreaterOperator)
				app = app || evaluateOperator((GreaterOperator) appOp, contextualElement);
			else if (appOp instanceof GreaterEqualOperator)
				app = app || evaluateOperator((GreaterEqualOperator) appOp, contextualElement);
			else if (appOp instanceof LessOperator)
				app = app || evaluateOperator((LessOperator) appOp, contextualElement);
			else if (appOp instanceof LessEqualOperator)
				app = app || evaluateOperator((LessEqualOperator) appOp, contextualElement);
			else
				app = app || evaluateOperator((EqualOperator) appOp, contextualElement);
			// if (opsIterator.hasNext())
			// System.out.print(", ");
			// if(app)
			// return true;
		}
		// System.out.print(")");
		return app;
	}

	public OclManager getOclManager() {
//		return oclManager;
		return getMetamodelManager().getOclManager();
	}

	public void setOclManager(OclManager oclMan) {
		oclManager = oclMan;
	}

	public boolean evaluateOperator(ForAllOperator operator, Object contextualElement) throws ParserException {
		List<Object> coll = new ArrayList<Object>();
		if (getOclManager().evaluateOCL(operator.getCollection().getResolvingExpr(),
				contextualElement) instanceof HashSet<?>) {
			HashSet<Object> hashSetRes = (HashSet<Object>) getOclManager()
					.evaluateOCL(operator.getCollection().getResolvingExpr(), contextualElement);
			for (Object object : hashSetRes) {
				if (object instanceof ArchiElemInstance)
					coll.add((ArchiElemInstance) object);
				else if (object instanceof InputInteraction)
					coll.add((InputInteraction) object);
				else if (object instanceof OutputInteraction)
					coll.add((OutputInteraction) object);
				else if (object instanceof Attachment)
					coll.add((Attachment) object);
			}
		}
		// } else {
		// List<Object> coll = (List<Object>)
		// getOclManager().evaluateOCL(operator.getCollection().getResolvingExpr(),
		// contextualElement);
		// }
		boolean app = true;
		for (Object object : coll) {
			// boolean app = true;
			if (operator.getArgument() instanceof NotOperator)
				app = evaluateOperator((NotOperator) operator.getArgument(), object);
			else if (operator.getArgument() instanceof AndOperator)
				app = evaluateOperator((AndOperator) operator.getArgument(), object);
			else if (operator.getArgument() instanceof OrOperator)
				app = evaluateOperator((OrOperator) operator.getArgument(), object);
			else if (operator.getArgument() instanceof ForAllOperator)
				app = evaluateOperator((ForAllOperator) operator.getArgument(), object);
			else if (operator.getArgument() instanceof ExistsOperator)
				app = evaluateExistsObj((ExistsOperator) operator.getArgument(), object, contextualElement);
			else if (operator.getArgument() instanceof GreaterOperator)
				app = evaluateOperator((GreaterOperator) operator.getArgument(), object);
			else if (operator.getArgument() instanceof GreaterEqualOperator)
				app = evaluateOperator((GreaterEqualOperator) operator.getArgument(), object);
			else if (operator.getArgument() instanceof LessOperator)
				app = evaluateOperator((LessOperator) operator.getArgument(), object);
			else if (operator.getArgument() instanceof LessEqualOperator)
				app = evaluateOperator((LessEqualOperator) operator.getArgument(), object);
			else
				app = evaluateOperator((EqualOperator) operator.getArgument(), object);
			// if (res.get(res.size() - 1) != object)
			// System.out.print(", ");
			// else
			// System.out.print(")");
			// if(!app)
			// return false;
		}
		// System.out.print(")");
		return app;
	}

	public boolean evaluateExistsObj(ExistsOperator operator, Object obj, Object contextualElement)
			throws ParserException {
		if (obj == null)
			return false;
		List<Object> coll = (List<Object>) getOclManager().evaluateOCL(operator.getCollection().getResolvingExpr(),
				contextualElement);
		boolean found = false;
		if (coll != null && contextualElement != null) {
			Iterator<Object> resIterator = coll.iterator();
			while (resIterator.hasNext() && !found) {
				Object app = resIterator.next();
				if (app.equals(obj))
					found = true;
			}
			return found;
		}
		return found;
	}

	public boolean evaluateOperator(ExistsOperator operator, Object contextualElement) throws ParserException {
		@SuppressWarnings("unchecked")
		Object el = getOclManager().evaluateOCL(operator.getElement().getResolvingExpr(), contextualElement);
		if (el == null)
			return false;
		List<Object> coll = (List<Object>) getOclManager().evaluateOCL(operator.getCollection().getResolvingExpr(),
				contextualElement);

		boolean found = false;
		if (coll != null && contextualElement != null) {
			Iterator<Object> resIterator = coll.iterator();
			while (resIterator.hasNext() && !found) {
				Object app = resIterator.next();
				if (app.equals(el))
					found = true;
			}
			return found;
		}
		return found;
	}

	public boolean evaluateOperator(GreaterOperator operator, Object contextualElement) throws ParserException {
		if (operator.getLhs().getResolvingExpr() == null || operator.getLhs().getResolvingExpr().contentEquals("")
				|| operator.getRhs().getResolvingExpr() == null
				|| operator.getRhs().getResolvingExpr().contentEquals("")) {
			// getNotOperator(), getAndOperator() and getOrOperator(), are opposites -> TO
			// CHECK IF SET (POSSIBLE null VALUES)
			if (operator.getNotOperator() != null) {
				if (operator.getNotOperator().getAndOperator() != null)
					return true;
				if (operator.getNotOperator().getOrOperator() != null)
					return false;
			} else {
				if (operator.getAndOperator() != null)
					return true;
				if (operator.getOrOperator() != null)
					return false;
			}
		}
		Object lhsRes = getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(), contextualElement);
		Object rhsRes = getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(), contextualElement);
		if ((lhsRes instanceof Integer && rhsRes instanceof Integer)
				|| (lhsRes instanceof Double && rhsRes instanceof Double)) {
			double lhs = Double.parseDouble(lhsRes.toString());
			double rhs = Double.parseDouble(rhsRes.toString());
			// System.out.print(lhs + " ?>? " + rhs);
			if (lhs < operator.getFactor() * rhs)
				return false;
		} else {
			HashSet<Object> lhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(),
					contextualElement);
			HashSet<Object> rhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(),
					contextualElement);
			if (lhsHS.size() == rhsHS.size()) {
				List<Object> lhsObjs = new ArrayList<Object>();
				for (Object object : lhsHS) {
					if (object instanceof Double)
						lhsObjs.add((double) object);
					else
						return false;
				}
				List<Object> rhsObjs = new ArrayList<Object>();
				for (Object object : rhsHS) {
					if (object instanceof Double)
						rhsObjs.add((double) object);
					else
						return false;
				}
				Iterator<Object> lhsIterator = lhsObjs.iterator();
				Iterator<Object> rhsIterator = rhsObjs.iterator();
				while (lhsIterator.hasNext()) {
					Object appLhs = lhsIterator.next();
					Object appRhs = rhsIterator.next();
					if (((Double) appLhs).doubleValue() <= operator.getFactor() * ((Double) appRhs).doubleValue())
						return false;
				}
			}
		}
		return true;
	}

	public boolean evaluateOperator(GreaterEqualOperator operator, Object contextualElement) throws ParserException {
		if (operator.getLhs().getResolvingExpr() == null || operator.getLhs().getResolvingExpr().contentEquals("")
				|| operator.getRhs().getResolvingExpr() == null
				|| operator.getRhs().getResolvingExpr().contentEquals("")) {
			// getNotOperator(), getAndOperator() and getOrOperator(), are opposites -> TO
			// CHECK IF SET (POSSIBLE null VALUES)
			if (operator.getNotOperator() != null) {
				if (operator.getNotOperator().getAndOperator() != null)
					return true;
				if (operator.getNotOperator().getOrOperator() != null)
					return false;
			} else {
				if (operator.getAndOperator() != null)
					return true;
				if (operator.getOrOperator() != null)
					return false;
			}
		}
		Object lhsRes = getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(), contextualElement);
		Object rhsRes = getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(), contextualElement);
		if ((lhsRes instanceof Integer && rhsRes instanceof Integer)
				|| (lhsRes instanceof Double && rhsRes instanceof Double)) {
			double lhs = Double.parseDouble(lhsRes.toString());
			double rhs = Double.parseDouble(rhsRes.toString());
			// System.out.print(lhs + " ?>=? " + rhs);
			if (lhs < operator.getFactor() * rhs)
				return false;
		} else {
			HashSet<Object> lhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(),
					contextualElement);
			HashSet<Object> rhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(),
					contextualElement);
			if (lhsHS.size() == rhsHS.size()) {
				List<Object> lhsObjs = new ArrayList<Object>();
				for (Object object : lhsHS) {
					if (object instanceof Double)
						lhsObjs.add((double) object);
					else
						return false;
				}
				List<Object> rhsObjs = new ArrayList<Object>();
				for (Object object : rhsHS) {
					if (object instanceof Double)
						rhsObjs.add((double) object);
					else
						return false;
				}
				Iterator<Object> lhsIterator = lhsObjs.iterator();
				Iterator<Object> rhsIterator = rhsObjs.iterator();
				while (lhsIterator.hasNext()) {
					Object appLhs = lhsIterator.next();
					Object appRhs = rhsIterator.next();
					if (((Double) appLhs).doubleValue() < operator.getFactor() * ((Double) appRhs).doubleValue())
						return false;
				}
			}
		}
		return true;
	}

	public boolean evaluateOperator(LessOperator operator, Object contextualElement) throws ParserException {
		if (operator.getLhs().getResolvingExpr() == null || operator.getLhs().getResolvingExpr().contentEquals("")
				|| operator.getRhs().getResolvingExpr() == null
				|| operator.getRhs().getResolvingExpr().contentEquals("")) {
			if (operator.getNotOperator() != null) {
				if (operator.getNotOperator().getAndOperator() != null)
					return true;
				if (operator.getNotOperator().getOrOperator() != null)
					return false;
			} else {
				if (operator.getAndOperator() != null)
					return true;
				if (operator.getOrOperator() != null)
					return false;
			}
		}
		Object lhsRes = getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(), contextualElement);
		Object rhsRes = getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(), contextualElement);
		if ((lhsRes instanceof Integer && rhsRes instanceof Integer)
				|| (lhsRes instanceof Double && rhsRes instanceof Double)) {
			double lhs = Double.parseDouble(lhsRes.toString());
			double rhs = Double.parseDouble(rhsRes.toString());
			// System.out.print(lhs + " ?<? " + rhs);
			if (lhs < operator.getFactor() * rhs)
				return false;
		} else {
			HashSet<Object> lhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(),
					contextualElement);
			HashSet<Object> rhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(),
					contextualElement);
			if (lhsHS.size() == rhsHS.size()) {
				List<Object> lhsObjs = new ArrayList<Object>();
				for (Object object : lhsHS) {
					if (object instanceof Double)
						lhsObjs.add((double) object);
					else
						return false;
				}
				List<Object> rhsObjs = new ArrayList<Object>();
				for (Object object : rhsHS) {
					if (object instanceof Double)
						rhsObjs.add((double) object);
					else
						return false;
				}
				Iterator<Object> lhsIterator = lhsObjs.iterator();
				Iterator<Object> rhsIterator = rhsObjs.iterator();
				while (lhsIterator.hasNext()) {
					Object appLhs = lhsIterator.next();
					Object appRhs = rhsIterator.next();
					if (((Double) appLhs).doubleValue() >= operator.getFactor() * ((Double) appRhs).doubleValue())
						return false;
				}
			}
		}
		return true;
	}

	public boolean evaluateOperator(LessEqualOperator operator, Object contextualElement) throws ParserException {
		if (operator.getLhs().getResolvingExpr() == null || operator.getLhs().getResolvingExpr().contentEquals("")
				|| operator.getRhs().getResolvingExpr() == null
				|| operator.getRhs().getResolvingExpr().contentEquals("")) {
			// getNotOperator(), getAndOperator() and getOrOperator(), are opposites -> TO
			// CHECK IF SET (POSSIBLE null VALUES)
			if (operator.getNotOperator() != null) {
				if (operator.getNotOperator().getAndOperator() != null)
					return true;
				if (operator.getNotOperator().getOrOperator() != null)
					return false;
			} else {
				if (operator.getAndOperator() != null)
					return true;
				if (operator.getOrOperator() != null)
					return false;
			}
		}
		Object lhsRes = getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(), contextualElement);
		Object rhsRes = getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(), contextualElement);
		if ((lhsRes instanceof Integer && rhsRes instanceof Integer)
				|| (lhsRes instanceof Double && rhsRes instanceof Double)) {
			double lhs = Double.parseDouble(lhsRes.toString());
			double rhs = Double.parseDouble(rhsRes.toString());
			// System.out.print(lhs + " ?<=? " + rhs);
			if (lhs < operator.getFactor() * rhs)
				return false;
		} else {
			HashSet<Object> lhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(),
					contextualElement);
			HashSet<Object> rhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(),
					contextualElement);
			if (lhsHS.size() == rhsHS.size()) {
				List<Object> lhsObjs = new ArrayList<Object>();
				for (Object object : lhsHS) {
					if (object instanceof Double)
						lhsObjs.add((double) object);
					else
						return false;
				}
				List<Object> rhsObjs = new ArrayList<Object>();
				for (Object object : rhsHS) {
					if (object instanceof Double)
						rhsObjs.add((double) object);
					else
						return false;
				}
				Iterator<Object> lhsIterator = lhsObjs.iterator();
				Iterator<Object> rhsIterator = rhsObjs.iterator();
				while (lhsIterator.hasNext()) {
					Object appLhs = lhsIterator.next();
					Object appRhs = rhsIterator.next();
					if (((Double) appLhs).doubleValue() > operator.getFactor() * ((Double) appRhs).doubleValue())
						return false;
				}
			}
		}
		return true;
	}

	public boolean evaluateOperator(EqualOperator operator) throws ParserException {
		if (operator.getLhs().getResolvingExpr() == null || operator.getLhs().getResolvingExpr().contentEquals("")
				|| operator.getRhs().getResolvingExpr() == null
				|| operator.getRhs().getResolvingExpr().contentEquals("")) {
			// getNotOperator(), getAndOperator() and getOrOperator(), are opposites -> TO
			// CHECK IF SET (POSSIBLE null VALUES)
			if (operator.getNotOperator() != null) {
				if (operator.getNotOperator().getAndOperator() != null)
					return true;
				if (operator.getNotOperator().getOrOperator() != null)
					return false;
			} else {
				if (operator.getAndOperator() != null)
					return true;
				if (operator.getOrOperator() != null)
					return false;
			}
		}
		Object lhsRes = getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(), getModel());
		Object rhsRes = getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(), getModel());
		if ((lhsRes instanceof Integer && rhsRes instanceof Integer)
				|| (lhsRes instanceof Double && rhsRes instanceof Double)) {
			double lhs = Double.parseDouble(lhsRes.toString());
			double rhs = Double.parseDouble(rhsRes.toString());
			// System.out.print(lhs + " ?=? " + rhs);
			if (lhs < operator.getFactor() * rhs)
				return false;
		} else {
			HashSet<Object> lhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(),
					getModel());
			HashSet<Object> rhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(),
					getModel());
			if (lhsHS.size() == rhsHS.size()) {
				List<Object> lhsObjs = new ArrayList<Object>();
				for (Object object : lhsHS) {
					if (object instanceof Double)
						lhsObjs.add((double) object);
					else
						return false;
				}
				List<Object> rhsObjs = new ArrayList<Object>();
				for (Object object : rhsHS) {
					if (object instanceof Double)
						rhsObjs.add((double) object);
					else
						return false;
				}
				Iterator<Object> lhsIterator = lhsObjs.iterator();
				Iterator<Object> rhsIterator = rhsObjs.iterator();
				while (lhsIterator.hasNext()) {
					Object appLhs = lhsIterator.next();
					Object appRhs = rhsIterator.next();
					if (((Double) appLhs).doubleValue() != operator.getFactor() * ((Double) appRhs).doubleValue())
						return false;
				}
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean evaluateOperator(EqualOperator operator, Object contextualElement) throws ParserException {
		if (operator.getLhs().getResolvingExpr() == null || operator.getLhs().getResolvingExpr().contentEquals("")
				|| operator.getRhs().getResolvingExpr() == null
				|| operator.getRhs().getResolvingExpr().contentEquals("")) {
			if (operator.getNotOperator() != null) {
				if (operator.getNotOperator().getAndOperator() != null)
					return true;
				if (operator.getNotOperator().getOrOperator() != null)
					return false;
			} else {
				if (operator.getAndOperator() != null)
					return true;
				if (operator.getOrOperator() != null)
					return false;
			}
		}
		Object lhsRes = getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(), contextualElement);
		Object rhsRes = getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(), contextualElement);
		if ((lhsRes instanceof Integer && rhsRes instanceof Integer)
				|| (lhsRes instanceof Double && rhsRes instanceof Double)) {
			double lhs = Double.parseDouble(lhsRes.toString());
			double rhs = Double.parseDouble(rhsRes.toString());
			// System.out.print(lhs + " ?=? " + rhs);
			if (lhs < operator.getFactor() * rhs)
				return false;
		} else {
			HashSet<Object> lhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getLhs().getResolvingExpr(),
					contextualElement);
			HashSet<Object> rhsHS = (HashSet<Object>) getOclManager().evaluateOCL(operator.getRhs().getResolvingExpr(),
					contextualElement);
			if (lhsHS.size() == rhsHS.size()) {
				List<Object> lhsObjs = new ArrayList<Object>();
				for (Object object : lhsHS) {
					if (object instanceof Double)
						lhsObjs.add((double) object);
					else
						return false;
				}
				List<Object> rhsObjs = new ArrayList<Object>();
				for (Object object : rhsHS) {
					if (object instanceof Double)
						rhsObjs.add((double) object);
					else
						return false;
				}
				Iterator<Object> lhsIterator = lhsObjs.iterator();
				Iterator<Object> rhsIterator = rhsObjs.iterator();
				while (lhsIterator.hasNext()) {
					Object appLhs = lhsIterator.next();
					Object appRhs = rhsIterator.next();
					if (((Double) appLhs).doubleValue() != operator.getFactor() * ((Double) appRhs).doubleValue())
						return false;
				}
			}
		}
		return true;
	}

	public PreCondition calculatePreCondition(Refactoring r) {
		PreCondition res = null;
		if (r != null) {
			if (r.getActions() != null) {
				if (r.getActions().size() == 1)
					res = r.getActions().get(0).getPre();
				if (r.getActions().size() > 1) {
					res = r.getActions().get(0).getPre();
					AndOperator resAnd = createAndOperator();
					for (int i = 1; i < r.getActions().size(); i++) {
						resAnd.getArguments()
								.addAll(((AndOperator) calculatePreCondition(res, r.getActions().get(i).getPre(),
										r.getActions().get(i - 1).getPost()).getConditionFormula().getRootOperator())
												.getArguments());
						res.getConditionFormula().setRootOperator(resAnd);
					}
				}
			}
		}
		return res;
	}

	public PreCondition calculatePreCondition(PreCondition pre1, PreCondition pre2, PostCondition post1) {
		PreCondition res = null;
		// TODO TO MODIFY
		if (pre1 == null || pre2 == null || post1 == null) {
			if (pre1 == null)
				res = pre2;
			if (pre2 == null)
				res = pre1;
		} else {
			res = LogicalSpecificationFactory.eINSTANCE.createPreCondition();
			FOLSpecification resFol = LogicalSpecificationFactory.eINSTANCE.createFOLSpecification();
			resFol.setRootOperator(Manager.append((AndOperator) pre1.getConditionFormula().getRootOperator(),
					(AndOperator) reducePreCondition(pre2, post1).getConditionFormula().getRootOperator()));
			res.setConditionFormula(resFol);
		}
		return res;
	}

	public PreCondition reducePreCondition(PreCondition pre, PostCondition post) {
		PreCondition res = pre;
		if (res != null && post != null) {
			AndOperator resRootOp = (AndOperator) res.getConditionFormula().getRootOperator();
			AndOperator postRootOp = (AndOperator) post.getConditionFormula().getRootOperator();
			if (resRootOp != null && postRootOp != null)
				if (resRootOp.getArguments() != null && postRootOp != null) {
					ArrayList<Boolean> resRootOpDelArrayList = new ArrayList<Boolean>(resRootOp.getArguments().size());
					for (int i = 0; i < resRootOp.getArguments().size(); i++) {
						resRootOpDelArrayList.add(i, false);
					}
					for (int i = 0; i < resRootOp.getArguments().size() - 1; i++) {
						if (postRootOp.guarantees(resRootOp.getArguments().get(i))) {
							resRootOpDelArrayList.set(i, true);
						}
					}
					int i = 0;
					while (i < resRootOp.getArguments().size()) {
						if (resRootOpDelArrayList.get(i)) {
							resRootOp.getArguments().remove(i);
							resRootOpDelArrayList.remove(i);
						} else
							i++;
					}
				}
		}
		return res;
	}

	public PostCondition calculatePostCondition(Refactoring r) {
		PostCondition res = null;

		if (r != null) {
			if (r.getActions() != null) {
				if (r.getActions().size() == 1)
					res = r.getActions().get(0).getPost();
				if (r.getActions().size() > 1) {
					res = r.getActions().get(0).getPost();
					for (int i = 1; i < r.getActions().size() - 1; i++) {
						res = reducePostCondition(calculatePostCondition(res, r.getActions().get(i).getPost()));
					}
				}
			}
		}
		return res;
	}

	public PostCondition reducePostCondition(PostCondition post) {
		PostCondition res = post;
		if (res != null) {
			AndOperator resRootOp = (AndOperator) res.getConditionFormula().getRootOperator();
			if (resRootOp != null)
				if (resRootOp.getArguments() != null)
					if (resRootOp.getArguments().size() > 1) {
						ArrayList<Boolean> resRootOpDelArrayList = new ArrayList<Boolean>(
								resRootOp.getArguments().size());
						for (int i = 0; i < resRootOp.getArguments().size(); i++) {
							resRootOpDelArrayList.add(i, false);
						}
						for (int i = 0; i < resRootOp.getArguments().size() - 1; i++) {
							boolean found = false;
							int j = i + 1;
							while (j < resRootOp.getArguments().size() && !found) {
								///// CHIAMARE isInverse, non equal
								// if (FolManager.equal(resRootOp.getArguments().get(i),
								// resRootOp.getArguments().get(j)))
								if (resRootOp.getArguments().get(i).equals(resRootOp.getArguments().get(j)))
									found = true;
								j++;
							}
							if (found) {
								resRootOpDelArrayList.set(i, true);
							}
						}
						int i = 0;
						while (i < resRootOp.getArguments().size()) {
							if (resRootOpDelArrayList.get(i)) {
								resRootOp.getArguments().remove(i);
								resRootOpDelArrayList.remove(i);
							} else
								i++;
						}
					}
		}
		return res;
	}

	public static PostCondition calculatePostCondition(PostCondition post1, PostCondition post2) {
		PostCondition res = null;
		if (post1 == null || post2 == null) {
			if (post1 == null)
				res = post2;
			if (post2 == null)
				res = post1;
		} else {
			res = LogicalSpecificationFactory.eINSTANCE.createPostCondition();
			FOLSpecification resFol = LogicalSpecificationFactory.eINSTANCE.createFOLSpecification();
			resFol.setRootOperator(Manager.append((AndOperator) post1.getConditionFormula().getRootOperator(),
					(AndOperator) post2.getConditionFormula().getRootOperator()));
			res.setConditionFormula(resFol);
		}
		return res;
	}

	public static AndOperator append(AndOperator and1, AndOperator and2) {
		AndOperator res = null;
		if (and1 == null || and2 == null) {
			if (and1 == null)
				res = and2;
			if (and2 == null)
				res = and1;
		} else {
			if (and1.getArguments() != null && and2.getArguments() != null) {
				res = LogicalSpecificationFactory.eINSTANCE.createAndOperator();
				res.getArguments().addAll(and1.getArguments());
				res.getArguments().addAll(and2.getArguments());
			}
		}
		return res;
	}

	public SingleValuedParameter createSingleValueParameter(String expr) {
		SingleValuedParameter singleValuedParameter = LogicalSpecificationFactory.eINSTANCE
				.createSingleValuedParameter();
		singleValuedParameter.setResolvingExpr(expr);
		return singleValuedParameter;
	}

	public MultipleValuedParameter createMultipleValuedParameter() {
		MultipleValuedParameter multipleValuedParameter = LogicalSpecificationFactory.eINSTANCE
				.createMultipleValuedParameter();
		return multipleValuedParameter;
	}

	public MultipleValuedParameter createMultipleValuedParameter(String expr) {
		MultipleValuedParameter multipleValuedParameter = LogicalSpecificationFactory.eINSTANCE
				.createMultipleValuedParameter();
		multipleValuedParameter.setResolvingExpr(expr);
		return multipleValuedParameter;
	}

	public ForAllOperator createForAllOperator(MultipleValuedParameter collection) {
		ForAllOperator forAll = LogicalSpecificationFactory.eINSTANCE.createForAllOperator();
		forAll.setCollection(collection);
		return forAll;
	}

	public ExistsOperator createExistsOperator(SingleValuedParameter parameter, MultipleValuedParameter collection) {
		ExistsOperator existsOperator = LogicalSpecificationFactory.eINSTANCE.createExistsOperator();
		existsOperator.setElement(parameter);
		existsOperator.setCollection(collection);
		return existsOperator;
	}

	public ExistsOperator createExistsOperator(MultipleValuedParameter collection) {
		ExistsOperator existsOperator = LogicalSpecificationFactory.eINSTANCE.createExistsOperator();
		existsOperator.setCollection(collection);
		return existsOperator;
	}

	public AndOperator createAndOperator() {
		return LogicalSpecificationFactory.eINSTANCE.createAndOperator();
	}

	public FOLSpecification createFOLSpectification(String string) {
		FOLSpecification fol = LogicalSpecificationFactory.eINSTANCE.createFOLSpecification();
		fol.setName(string);
		return fol;
	}

	public NotOperator createNotOperator() {
		return LogicalSpecificationFactory.eINSTANCE.createNotOperator();
	}

	public NotOperator createNotOperator(Operator operator) {
		NotOperator o = LogicalSpecificationFactory.eINSTANCE.createNotOperator();
		o.setArgument(operator);
		return o;
	}

	public PreCondition createPreCondition() {
		return LogicalSpecificationFactory.eINSTANCE.createPreCondition();
	}

	public PostCondition createPostCondition() {
		return LogicalSpecificationFactory.eINSTANCE.createPostCondition();
	}

	public MetamodelManager getMetamodelManager() {
		return metamodelManager;
	}

	public void setMetamodelManager(MetamodelManager mmManager) {
		metamodelManager = mmManager;
	}

	public String getModelFileExtension() {
		return metamodelManager.getModelFileExtension();
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public OclStringManager getOclStringManager() {
		return oclStringManager;
	}

	public void setOclStringManager(OclStringManager oclStringManager) {
		this.oclStringManager = oclStringManager;
	}

	public static int getREFACTORING_COUNTER() {
		return REFACTORING_COUNTER;
	}

	public static void setREFACTORING_COUNTER(int rEFACTORING_COUNTER) {
		REFACTORING_COUNTER = rEFACTORING_COUNTER;
	}
}
