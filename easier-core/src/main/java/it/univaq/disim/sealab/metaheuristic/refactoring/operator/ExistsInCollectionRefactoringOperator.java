package it.univaq.disim.sealab.metaheuristic.refactoring.operator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

public class ExistsInCollectionRefactoringOperator extends ExistsRefactoringOperator {

//	private OclManager oclManager;

	public ExistsInCollectionRefactoringOperator(SingleValuedParameter par, MultipleValuedParameter coll,
			OclManager oclMgr) {
		super(par, coll, oclMgr);
//		this.element = par;
//		this.collection = coll;
//		this.oclManager = oclMgr;
	}

	@SuppressWarnings("unchecked")
	public boolean evaluateOperator(Object contextualElement) {// throws ParserException {
		final Object el = oclManager.evaluateOCL(this.getElement().getResolvingExpr(), contextualElement);
		if (el == null)
			return false;
		List<Object> coll = new ArrayList<Object>(
				(HashSet<Object>) oclManager.evaluateOCL(this.getCollection().getResolvingExpr(), contextualElement));

		// if (coll != null && contextualElement != null) {
		Iterator<Object> resIterator = coll.iterator();
		while (resIterator.hasNext()) {
			Object app = resIterator.next();
			if (el.equals(app))
				return true;
		}
//		}
		return false;
	}
}
