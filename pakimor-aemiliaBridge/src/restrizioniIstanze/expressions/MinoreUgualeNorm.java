/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.MinoreUguale;

/**
 * @author Mio
 *
 */
public class MinoreUgualeNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private MinoreUguale newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(MinoreUguale minoreUguale) 
		{
		this.newExpression = minoreUguale;
		}

	@Override
	public Object copy() 
		{
		MinoreUgualeNorm minoreUgualeNorm = new MinoreUgualeNorm();
		minoreUgualeNorm.setNewExpression(this.newExpression.copy());
		minoreUgualeNorm.setOldExpression(this.oldExpression.copy());
		return minoreUgualeNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof MinoreUgualeNorm))
			return false;
		MinoreUgualeNorm minoreUgualeNorm = (MinoreUgualeNorm)obj;
		if (!this.newExpression.equals(minoreUgualeNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(minoreUgualeNorm.getOldExpression()))
			return false;
		return true;
		}

	@Override
	public MinoreUguale getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("MinoreUgualeNorm object");
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
