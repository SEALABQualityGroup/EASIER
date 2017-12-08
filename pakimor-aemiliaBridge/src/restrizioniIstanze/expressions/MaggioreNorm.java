/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Maggiore;

/**
 * @author Mio
 *
 */
public class MaggioreNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Maggiore newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Maggiore maggiore) 
		{
		this.newExpression = maggiore;
		}

	@Override
	public Object copy() 
		{
		MaggioreNorm maggioreNorm = new MaggioreNorm();
		maggioreNorm.setNewExpression(this.newExpression.copy());
		maggioreNorm.setOldExpression(this.oldExpression.copy());
		return maggioreNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof MaggioreNorm))
			return false;
		MaggioreNorm maggioreNorm = (MaggioreNorm)obj;
		if (!this.newExpression.equals(maggioreNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(maggioreNorm.getOldExpression()))
			return false;
		return true;
		}

	@Override
	public Maggiore getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("MaggioreNorm object");
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
