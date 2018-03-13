/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Length;

/**
 * @author Mio
 *
 */
public class LengthNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Length newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Length length) 
		{
		this.newExpression = length;
		}

	@Override
	public Object copy() 
		{
		LengthNorm lengthNorm = new LengthNorm();
		lengthNorm.setNewExpression(this.newExpression.copy());
		lengthNorm.setOldExpression(this.newExpression.copy());
		return lengthNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof LengthNorm))
			return false;
		LengthNorm lengthNorm = (LengthNorm)obj;
		if (!this.newExpression.equals(lengthNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(lengthNorm.getOldExpression()))
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
		System.out.println("LengthNorm object");
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
