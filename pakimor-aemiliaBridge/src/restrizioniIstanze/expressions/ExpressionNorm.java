/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;

/**
 * @author Mirko
 *
 */
public abstract class ExpressionNorm implements AEmiliaBase {

	protected Expression oldExpression;
	protected Expression newExpression;
	
	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public abstract Object copy();

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public abstract void print();
	
	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract String toString();
	
	public void setNewExpression(Expression exp) {
		newExpression = exp;
	}

	public Expression getNewExpression() {
		return newExpression;
	}
	
	public void setOldExpression(Expression exp) {
		oldExpression = exp;
	}
	
	public Expression getOldExpression() {
		return oldExpression;
	}
}
