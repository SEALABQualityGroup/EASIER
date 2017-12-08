/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.First;

/**
 * @author Mio
 *
 */
public class FirstNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private First newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(First first) 
		{
		this.newExpression = first;
		}

	@Override
	public Object copy() 
		{
		FirstNorm firstNorm = new FirstNorm();
		firstNorm.setOldExpression(this.oldExpression.copy());
		firstNorm.setNewExpression(this.newExpression.copy());
		return firstNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof FirstNorm))
			return false;
		FirstNorm firstNorm = (FirstNorm)obj;
		if (!this.newExpression.equals(firstNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(firstNorm.getOldExpression()))
			return false;
		return true;
		}

	@Override
	public First getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("FirstNorm object");
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
