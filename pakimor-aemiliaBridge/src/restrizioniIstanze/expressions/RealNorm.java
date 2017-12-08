/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Real;

/**
 * @author Mio
 *
 */
public class RealNorm 
	extends NumberExpNorm 
	{

	private Expression oldExpression;
	private Real newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Real real) 
		{
		this.newExpression = real;
		}

	@Override
	public double getNumber() 
		{
		return this.newExpression.getNumber();
		}

	@Override
	public Object copy() 
		{
		RealNorm realNorm = new RealNorm();
		realNorm.setNewExpression(this.newExpression.copy());
		realNorm.setOldExpression(this.oldExpression.copy());
		return realNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof RealNorm))
			return false;
		RealNorm realNorm = (RealNorm)obj;
		if (!this.newExpression.equals(realNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(realNorm.getOldExpression()))
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
		System.out.println("RealNorm object");
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
