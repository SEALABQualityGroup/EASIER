/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.MaggioreUguale;

/**
 * @author Mio
 *
 */
public class MaggioreUgualeNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private MaggioreUguale newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(MaggioreUguale maggioreUguale) 
		{
		this.newExpression = maggioreUguale;
		}

	@Override
	public Object copy() 
		{
		MaggioreUgualeNorm maggioreUgualeNorm = new MaggioreUgualeNorm();
		maggioreUgualeNorm.setNewExpression(this.newExpression.copy());
		maggioreUgualeNorm.setOldExpression(this.oldExpression.copy());
		return maggioreUgualeNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof MaggioreUgualeNorm))
			return false;
		MaggioreUgualeNorm maggioreUgualeNorm = (MaggioreUgualeNorm)obj;
		if (!this.newExpression.equals(maggioreUgualeNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(maggioreUgualeNorm.getOldExpression()))
			return false;
		return true;
		}

	@Override
	public MaggioreUguale getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("MaggioreUgualeNorm object");
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
