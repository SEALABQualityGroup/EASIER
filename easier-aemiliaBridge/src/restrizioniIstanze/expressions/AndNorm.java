/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.And;
import specificheAEmilia.Expression;

/**
 * @author Mio
 *
 */
public class AndNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private And newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(And and) 
		{
		this.newExpression = and;
		}

	@Override
	public Object copy() 
		{
		AndNorm andNorm = new AndNorm();
		andNorm.setOldExpression(this.oldExpression.copy());
		andNorm.setNewExpression(this.newExpression.copy());
		return andNorm;
		}	

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof AndNorm))
			return false;
		AndNorm andNorm = (AndNorm)obj;
		if (!this.oldExpression.equals(andNorm.getOldExpression()))
			return false;
		if (!this.newExpression.equals(andNorm.getNewExpression()))
			return false;
		return true;
		}

	@Override
	public And getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("AndNorm object");
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
