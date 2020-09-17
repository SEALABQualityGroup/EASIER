package it.univaq.disim.sealab.metaheuristic.refactoring.operator;

import java.util.Iterator;
import java.util.List;

import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import logicalSpecification.ExistsOperator;
import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.Operator;
import logicalSpecification.SingleValuedParameter;
import logicalSpecification.impl.ExistsOperatorImpl;

public class ExistsRefactoringOperator extends ExistsOperatorImpl implements RefactoringOperator{
	
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
	
	public boolean evaluateOperator(Object contextualElement, Object obj) {
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
	
	public boolean equals(ExistsOperator op2) {
		if (op2 != null) {
			if (this.getCollection().equals(op2.getCollection()))
				if (this.getElement() != null && op2.getElement() != null)
					if (this.getElement().equals(op2.getElement())) {
						if (this.getArgument() != null && op2.getArgument() != null)
							return this.getArgument().equals(op2.getArgument());
					}
		}
		return false;
	}

	public boolean guarantees(Operator op2) {
		if (op2 != null) {
			if (this != op2) {
				if (this.getArgument() != null)
					return this.getArgument().guarantees(op2);
				else if (op2 instanceof ExistsOperator)
					return this.equals(op2);
				return false;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean evaluateOperator() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
