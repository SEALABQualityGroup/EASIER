/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Boolean;
import specificheAEmilia.Expression;


/**
 * @author Mio
 *
 */
public class BooleanNorm 
	extends ExpressionNorm 
	{
	private Expression oldExpression;
	private Boolean newExpression;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		BooleanNorm booleanNorm = new BooleanNorm();
		booleanNorm.setNewExpression(this.newExpression.copy());
		booleanNorm.setOldExpression(this.oldExpression.copy());
		return booleanNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("BooleanNorm object");
		System.out.print("Old Expression: ");
		this.oldExpression.print();
		System.out.println("New Expression: ");
		this.newExpression.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof BooleanNorm))
			return false;
		BooleanNorm booleanNorm = (BooleanNorm)obj;
		if (!this.newExpression.equals(booleanNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(booleanNorm.getOldExpression()))
			return false;
		return true;
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

	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Boolean boolean1) 
		{
		this.newExpression = boolean1;
		}
	
	public Expression getOldExpression()
		{
		return this.oldExpression;
		}
	
	public Boolean getNewExpression()
		{
		return this.newExpression;
		}
	}
