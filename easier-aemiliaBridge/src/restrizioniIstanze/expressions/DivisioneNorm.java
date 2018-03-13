/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Divisione;
import specificheAEmilia.Expression;

/**
 * @author Mio
 *
 */
public class DivisioneNorm 
	extends ExpressionNorm 
	{
	
	private Expression oldExpression;
	private Divisione newExpression;

	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Divisione divisione) 
		{
		this.newExpression = divisione;
		}

	@Override
	public Object copy() 
		{
		DivisioneNorm divisioneNorm = new DivisioneNorm();
		divisioneNorm.setOldExpression(this.oldExpression);
		divisioneNorm.setNewExpression(this.newExpression);
		return divisioneNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof DivisioneNorm))
			return false;
		DivisioneNorm divisioneNorm = (DivisioneNorm)obj;
		if (!this.oldExpression.equals(divisioneNorm.getOldExpression()))
			return false;
		if (!this.newExpression.equals(divisioneNorm.getNewExpression()))
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
		System.out.println("DivisioneNorm object");
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
