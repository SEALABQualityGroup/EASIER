/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Negazione;

/**
 * @author Mio
 *
 */
public class NegazioneNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Negazione newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Negazione negazione) 
		{
		this.newExpression = negazione;
		}

	@Override
	public Object copy() {
		NegazioneNorm negazioneNorm = new NegazioneNorm();
		negazioneNorm.setNewExpression(this.newExpression.copy());
		negazioneNorm.setOldExpression(this.oldExpression.copy());
		return negazioneNorm;
	}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof NegazioneNorm))
			return false;
		NegazioneNorm negazioneNorm = (NegazioneNorm)obj;
		if (!this.newExpression.equals(negazioneNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(negazioneNorm.getOldExpression()))
			return false;
		return true;
		}

	@Override
	public Negazione getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("NegazioneNorm object");
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
