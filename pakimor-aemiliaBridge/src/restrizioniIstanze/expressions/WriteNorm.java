/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Write;

/**
 * @author Mio
 *
 */
public class WriteNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Write newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Write write) 
		{
		this.newExpression = write;
		}

	@Override
	public Object copy() 
		{
		WriteNorm writeNorm = new WriteNorm();
		writeNorm.setNewExpression(this.newExpression.copy());
		writeNorm.setOldExpression(this.oldExpression.copy());
		return writeNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof WriteNorm))
			return false;
		WriteNorm writeNorm = (WriteNorm)obj;
		if (!this.newExpression.equals(writeNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(writeNorm.getOldExpression()))
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
		System.out.println("WriteNorm object");
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
