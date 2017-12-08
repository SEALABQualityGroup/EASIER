/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Put;

/**
 * @author Mio
 *
 */
public class PutNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Put newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Put put) 
		{
		this.newExpression = put;
		}

	@Override
	public Object copy() 
		{
		PutNorm putNorm = new PutNorm();
		putNorm.setNewExpression(this.newExpression.copy());
		putNorm.setOldExpression(this.oldExpression.copy());
		return putNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof PutNorm))
			return false;
		PutNorm putNorm = (PutNorm)obj;
		if (!this.newExpression.equals(putNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(putNorm.getOldExpression()))
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
		System.out.println("PutNorm object");
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
