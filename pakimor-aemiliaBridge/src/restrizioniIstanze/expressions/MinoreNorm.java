/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Minore;

/**
 * @author Mio
 *
 */
public class MinoreNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Minore newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Minore minore) 
		{
		this.newExpression = minore;
		}

	@Override
	public Object copy() 
		{
		MinoreNorm minoreNorm = new MinoreNorm();
		minoreNorm.setNewExpression(this.newExpression.copy());
		minoreNorm.setOldExpression(this.oldExpression.copy());
		return minoreNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof MinoreNorm))
			return false;
		MinoreNorm minoreNorm = (MinoreNorm)obj;
		if (!this.newExpression.equals(minoreNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(minoreNorm.getOldExpression()))
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
		System.out.println("MinoreNorm object");
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
