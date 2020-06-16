package it.univaq.disim.sealab.metaheuristic.refactoring.operator;

import java.util.Iterator;
import java.util.List;

import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import logicalSpecification.ExistsOperator;
import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;
import logicalSpecification.impl.ExistsOperatorImpl;

public class ExistsInCollectionRefactoringOperator extends ExistsRefactoringOperator{
	
//	private OclManager oclManager;
	
	public ExistsInCollectionRefactoringOperator(SingleValuedParameter par, MultipleValuedParameter coll, OclManager oclMgr) {
		super(par, coll, oclMgr);
		this.element = par;
		this.collection = coll;
		this.oclManager = oclMgr;
	}

	public boolean evaluateOperator(Object obj, Object contextualElement) {
//		throws ParserException {
		if (obj == null)
			return false;
		List<Object> coll = (List<Object>) oclManager.evaluateOCL(this.getCollection().getResolvingExpr(),
				contextualElement);
		boolean found = false;
		
		if (coll != null && contextualElement != null) {
			Iterator<Object> resIterator = coll.iterator();
			while (resIterator.hasNext() && !found) {
				Object app = resIterator.next();
				if (app.equals(obj))
					found = true;
			}
		}
		return found;
	}
	
}
