package it.univaq.disim.sealab.metaheuristic.refactoring.operator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import logicalSpecification.AndOperator;
import logicalSpecification.EqualOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.ForAllOperator;
import logicalSpecification.GreaterEqualOperator;
import logicalSpecification.GreaterOperator;
import logicalSpecification.LessEqualOperator;
import logicalSpecification.LessOperator;
import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.NotOperator;
import logicalSpecification.OrOperator;
import logicalSpecification.impl.ForAllOperatorImpl;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.Attachment;
import metamodel.mmaemilia.InputInteraction;
import metamodel.mmaemilia.OutputInteraction;

public class ForallRefactoringOperator extends ForAllOperatorImpl {

	private OclManager oclManager;

	public ForallRefactoringOperator(MultipleValuedParameter collection, OclManager oclMgr) {
		super();
		this.collection = collection;
		this.oclManager = oclMgr;
	}

	public boolean evaluateOperator(Object contextualElement) {// throws ParserException {
		List<Object> coll = new ArrayList<Object>();
		if (oclManager.evaluateOCL(this.getCollection().getResolvingExpr(), contextualElement) instanceof HashSet<?>) {
			HashSet<Object> hashSetRes = (HashSet<Object>) oclManager
					.evaluateOCL(this.getCollection().getResolvingExpr(), contextualElement);
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
		for (Object obj : coll) {
			// boolean app = true;
			this.getArgument().evaluateOperator(obj);

			/*
			 * if (operator.getArgument() instanceof NotOperator) app =
			 * evaluateOperator((NotOperator) operator.getArgument(), object); else if
			 * (operator.getArgument() instanceof AndOperator) app =
			 * evaluateOperator((AndOperator) operator.getArgument(), object); else if
			 * (operator.getArgument() instanceof OrOperator) app =
			 * evaluateOperator((OrOperator) operator.getArgument(), object); else if
			 * (operator.getArgument() instanceof ForAllOperator) app =
			 * evaluateOperator((ForAllOperator) operator.getArgument(), object); else if
			 * (operator.getArgument() instanceof ExistsOperator) app =
			 * evaluateExistsObj((ExistsOperator) operator.getArgument(), object,
			 * contextualElement); else if (operator.getArgument() instanceof
			 * GreaterOperator) app = evaluateOperator((GreaterOperator)
			 * operator.getArgument(), object); else if (operator.getArgument() instanceof
			 * GreaterEqualOperator) app = evaluateOperator((GreaterEqualOperator)
			 * operator.getArgument(), object); else if (operator.getArgument() instanceof
			 * LessOperator) app = evaluateOperator((LessOperator) operator.getArgument(),
			 * object); else if (operator.getArgument() instanceof LessEqualOperator) app =
			 * evaluateOperator((LessEqualOperator) operator.getArgument(), object); else
			 * app = evaluateOperator((EqualOperator) operator.getArgument(), object);
			 */
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
}
