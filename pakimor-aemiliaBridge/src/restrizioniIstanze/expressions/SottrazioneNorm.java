/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Sottrazione;

/**
 * @author Mio
 *
 */
public class SottrazioneNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Sottrazione newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Sottrazione sottrazione) 
		{
		this.newExpression = sottrazione;
		}

	@Override
	public Object copy() 
		{
		SottrazioneNorm sottrazioneNorm = new SottrazioneNorm();
		sottrazioneNorm.setNewExpression(this.newExpression.copy());
		sottrazioneNorm.setOldExpression(this.oldExpression.copy());
		return sottrazioneNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof SottrazioneNorm))
			return false;
		SottrazioneNorm sottrazioneNorm = (SottrazioneNorm)obj;
		if (!this.newExpression.equals(sottrazioneNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(sottrazioneNorm.getOldExpression()))
			return false;
		return true;
		}

	@Override
	public Sottrazione getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("SottrazioneNorm object");
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
