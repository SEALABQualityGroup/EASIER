/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Get;

/**
 * @author Mio
 *
 */
public class GetNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Get newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Get get) 
		{
		this.newExpression = get;
		}

	@Override
	public Object copy() 
		{
		GetNorm getNorm = new GetNorm();
		getNorm.setOldExpression(this.oldExpression.copy());
		getNorm.setNewExpression(this.newExpression.copy());
		return getNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof GetNorm))
			return false;
		GetNorm getNorm = (GetNorm)obj;
		if (!this.newExpression.equals(getNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(getNorm.getOldExpression()))
			return false;
		return true;
		}

	@Override
	public Get getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("GetNorm object");
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
