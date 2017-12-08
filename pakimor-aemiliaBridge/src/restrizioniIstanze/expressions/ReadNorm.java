/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Read;

/**
 * @author Mio
 *
 */
public class ReadNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Read newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Read read) 
		{
		this.newExpression = read;
		}

	@Override
	public Object copy() 
		{
		ReadNorm readNorm = new ReadNorm();
		readNorm.setNewExpression(this.newExpression.copy());
		readNorm.setOldExpression(this.oldExpression.copy());
		return readNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ReadNorm))
			return false;
		ReadNorm readNorm = (ReadNorm)obj;
		if (!this.newExpression.equals(readNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(readNorm.getOldExpression()))
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
		System.out.println("ReadNorm object");
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
