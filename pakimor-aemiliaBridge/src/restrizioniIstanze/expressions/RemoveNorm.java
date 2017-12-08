/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Remove;

/**
 * @author Mio
 *
 */
public class RemoveNorm 
	extends ExpressionNorm 
	{
	
	private Expression oldExpression;
	private Remove newExpression;

	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Remove remove) 
		{
		this.newExpression = remove;
		}

	@Override
	public Object copy() 
		{
		RemoveNorm removeNorm = new RemoveNorm();
		removeNorm.setNewExpression(this.newExpression.copy());
		removeNorm.setOldExpression(this.oldExpression.copy());
		return removeNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof RemoveNorm))
			return false;
		RemoveNorm removeNorm = (RemoveNorm)obj;
		if (!this.newExpression.equals(removeNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(removeNorm.getOldExpression()))
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
		System.out.println("RemoveNorm object");
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
