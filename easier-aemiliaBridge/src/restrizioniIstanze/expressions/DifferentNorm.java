/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Different;
import specificheAEmilia.Expression;

/**
 * @author Mio
 *
 */
public class DifferentNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Different newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Different different) 
		{
		this.newExpression = different;
		}

	@Override
	public Object copy() 
		{
		DifferentNorm differentNorm = new DifferentNorm();
		differentNorm.setOldExpression(this.oldExpression);
		differentNorm.setNewExpression(this.newExpression);
		return differentNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof DifferentNorm))
			return false;
		DifferentNorm differentNorm = (DifferentNorm)obj;
		if (!this.oldExpression.equals(differentNorm.getOldExpression()))
			return false;
		if (!this.newExpression.equals(differentNorm.getNewExpression()))
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
		System.out.println("DifferentNorm object");
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
