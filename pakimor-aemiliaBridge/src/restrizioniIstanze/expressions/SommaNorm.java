/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Somma;

/**
 * @author Mio
 *
 */
public class SommaNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Somma newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Somma somma) 
		{
		this.newExpression = somma;
		}

	@Override
	public Object copy() 
		{
		SommaNorm sommaNorm = new SommaNorm();
		sommaNorm.setNewExpression(this.newExpression.copy());
		sommaNorm.setOldExpression(this.oldExpression.copy());
		return sommaNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof SommaNorm))
			return false;
		SommaNorm sommaNorm = (SommaNorm)obj;
		if (!this.newExpression.equals(sommaNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(sommaNorm.getOldExpression()))
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
		System.out.println("SommaNorm object");
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
