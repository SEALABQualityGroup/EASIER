/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Or;

/**
 * @author Mio
 *
 */
public class OrNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Or newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Or or) 
		{
		this.newExpression = or;
		}

	@Override
	public Object copy() 
		{
		OrNorm orNorm = new OrNorm();
		orNorm.setNewExpression(this.newExpression.copy());
		orNorm.setOldExpression(this.oldExpression.copy());
		return orNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof OrNorm))
			return false;
		OrNorm orNorm = (OrNorm)obj;
		if (!(this.newExpression.equals(orNorm.getNewExpression())))
			return false;
		if (!(this.oldExpression.equals(orNorm.getOldExpression())))
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
		System.out.println("OrNorm object");
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
