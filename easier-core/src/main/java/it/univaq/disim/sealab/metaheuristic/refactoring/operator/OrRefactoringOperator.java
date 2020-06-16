package it.univaq.disim.sealab.metaheuristic.refactoring.operator;

import java.util.Iterator;

import logicalSpecification.AndOperator;
import logicalSpecification.EqualOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.ForAllOperator;
import logicalSpecification.GreaterEqualOperator;
import logicalSpecification.GreaterOperator;
import logicalSpecification.LessEqualOperator;
import logicalSpecification.LessOperator;
import logicalSpecification.NotOperator;
import logicalSpecification.Operator;
import logicalSpecification.OrOperator;
import logicalSpecification.impl.OrOperatorImpl;

public class OrRefactoringOperator extends OrOperatorImpl {

	public boolean evaluateOperator(Object contextualElement) {//throws ParserException {
		// System.out.print("OR(");
		boolean app = false;
		Iterator<Operator> opsIterator = this.getArguments().iterator();
		while (opsIterator.hasNext()) {
			Operator appOp = opsIterator.next();
			// boolean app = false;
			app = app || appOp.evaluateOperator(contextualElement);
			/*
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
				app = app || evaluateOperator((EqualOperator) appOp, contextualElement);*/
			// if (opsIterator.hasNext())
			// System.out.print(", ");
			// if(app)
			// return true;
		}
		// System.out.print(")");
		return app;
	}
	
}
