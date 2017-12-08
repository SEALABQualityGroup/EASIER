/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Concat;
import specificheAEmilia.Expression;

/**
 * @author Mio
 *
 */
public class ConcatNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Concat newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Concat concat) 
		{
		this.newExpression = concat;
		}

	@Override
	public Object copy() 
		{
		ConcatNorm concatNorm = new ConcatNorm();
		concatNorm.setOldExpression(this.oldExpression);
		concatNorm.setNewExpression(this.newExpression);
		return concatNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ConcatNorm))
			return false;
		ConcatNorm concatNorm = (ConcatNorm)obj;
		if (!concatNorm.oldExpression.equals(concatNorm.getOldExpression()))
			return false;
		if (!concatNorm.newExpression.equals(concatNorm.getNewExpression()))
			return false;
		return true;
		}

	@Override
	public Expression getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("ConcatNorm object");
		System.out.print("Old Expression: ");
		this.oldExpression.print();
		System.out.println("New Expression: ");
		this.newExpression.print();
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New Expression: ";
		string += this.newExpression;
		string += " Old Expression: ";
		string += this.oldExpression + " ";
		return string;
		}

	public Expression getOldExpression() 
		{
		return oldExpression;
		}
	}
