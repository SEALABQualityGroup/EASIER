/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Tail;

/**
 * @author Mio
 *
 */
public class TailNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Tail newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Tail tail) 
		{
		this.newExpression = tail;
		}

	@Override
	public Object copy() 
		{
		TailNorm tailNorm = new TailNorm();
		tailNorm.setNewExpression(this.newExpression.copy());
		tailNorm.setOldExpression(this.oldExpression.copy());
		return tailNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof TailNorm))
			return false;
		TailNorm tailNorm = (TailNorm)obj;
		if (!this.newExpression.equals(tailNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(tailNorm.getOldExpression()))
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
		System.out.println("TailNorm object");
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
		return this.oldExpression;
		}
	}
