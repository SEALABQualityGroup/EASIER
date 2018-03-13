/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.ArrayCons;
import specificheAEmilia.Expression;


/**
 * @author Mirko
 *
 */
public class ArrayConsNorm extends ExpressionNorm {

	private Expression oldExpression;
	private ArrayCons newExpression;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		ArrayConsNorm arrayConsNorm = new ArrayConsNorm();
		arrayConsNorm.setOldExpression(this.oldExpression.copy());
		arrayConsNorm.setNewExpression(this.newExpression.copy());
		return arrayConsNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("ArrayConsNorm object");
		System.out.print("Old Expression: ");
		this.oldExpression.print();
		System.out.println("New Expression: ");
		this.newExpression.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ArrayConsNorm))
			return false;
		ArrayConsNorm arrayConsNorm = (ArrayConsNorm)obj;
		if (!this.oldExpression.equals(arrayConsNorm.getOldExpression()))
			return false;
		if (!this.newExpression.equals(arrayConsNorm.getNewExpression()))
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

	public void setNewExpression(ArrayCons arrayCons2) 
		{
		this.newExpression = arrayCons2;
		}

	@Override
	public ArrayCons getNewExpression() 
		{
		return this.newExpression;
		}

	public Expression getOldExpression() 
		{
		return oldExpression;
		}
	}
