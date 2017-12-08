/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;

/**
 * @author Mio
 *
 */
public class IdentExprNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private IdentExpr newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(IdentExpr identExpr) 
		{
		this.newExpression = identExpr;
		}

	@Override
	public Object copy() 
		{
		IdentExprNorm identExprNorm = new IdentExprNorm();
		identExprNorm.setNewExpression(this.newExpression.copy());
		identExprNorm.setOldExpression(this.oldExpression.copy());
		return identExprNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof IdentExprNorm))
			return false;
		IdentExprNorm identExprNorm = (IdentExprNorm)obj;
		if (!this.newExpression.equals(identExprNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(identExprNorm.getOldExpression()))
			return false;
		return true;
		}

	@Override
	public IdentExpr getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("IdentExprNorm object");
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
