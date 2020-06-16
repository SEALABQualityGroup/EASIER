package it.univaq.disim.sealab.metaheuristic.refactoring.operator;

import java.util.Iterator;
import java.util.List;

import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import logicalSpecification.ExistsOperator;
import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;
import logicalSpecification.impl.ExistsOperatorImpl;

public class ExistsRefactoringOperator extends ExistsOperatorImpl {
	
	protected OclManager oclManager;
	
	public ExistsRefactoringOperator() {
		super();
	}

	public ExistsRefactoringOperator(SingleValuedParameter parameter, MultipleValuedParameter collection, OclManager oclMgr) {
		super();
		this.element = parameter;
		this.collection = collection;
		this.oclManager = oclMgr;
	}

	public ExistsRefactoringOperator(MultipleValuedParameter collection, OclManager oclMgr) {
		super();
		this.collection = collection;
		this.oclManager = oclMgr;
	}

	public boolean evaluateOperator(Object contextualElement) {// throws ParserException {
		@SuppressWarnings("unchecked")
		Object el = oclManager.evaluateOCL(this.getElement().getResolvingExpr(), contextualElement);
		if (el == null)
			return false;
		List<Object> coll = (List<Object>) oclManager.evaluateOCL(this.getCollection().getResolvingExpr(),
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
}
