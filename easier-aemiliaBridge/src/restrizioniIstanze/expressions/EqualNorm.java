/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Equal;
import specificheAEmilia.Expression;

/**
 * @author Mio
 *
 */
public class EqualNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Equal newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Equal equal) 
		{
		this.newExpression = equal;
		}

	@Override
	public Object copy() 
		{
		EqualNorm equalNorm = new EqualNorm();
		equalNorm.setOldExpression(this.oldExpression.copy());
		equalNorm.setNewExpression(this.newExpression.copy());
		return equalNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof EqualNorm))
			return false;
		EqualNorm equalNorm = (EqualNorm)obj;
		if (!(this.newExpression.equals(equalNorm.getNewExpression())))
			return false;
		if (!(this.oldExpression.equals(equalNorm.getOldExpression())))
			return false;
		return true;
		}

	@Override
	public Equal getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("EqualNorm object");
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
