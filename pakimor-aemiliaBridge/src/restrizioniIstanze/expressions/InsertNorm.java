/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Insert;

/**
 * @author Mio
 *
 */
public class InsertNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Insert newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Insert insert) 
		{
		this.newExpression = insert;
		}

	@Override
	public Object copy() 
		{
		InsertNorm insertNorm = new InsertNorm();
		insertNorm.setNewExpression(this.newExpression.copy());
		insertNorm.setOldExpression(this.oldExpression.copy());
		return insertNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof InsertNorm))
			return false;
		InsertNorm insertNorm = (InsertNorm)obj;
		if (!this.newExpression.equals(insertNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(insertNorm.getOldExpression()))
			return false;
		return true;
		}

	@Override
	public Insert getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("InsertNorm object");
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
