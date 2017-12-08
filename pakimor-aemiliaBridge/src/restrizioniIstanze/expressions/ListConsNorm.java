/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.ListCons;


/**
 * @author Mirko
 *
 */
public class ListConsNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private ListCons newExpression;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		ListConsNorm listConsNorm = new ListConsNorm();
		listConsNorm.setNewExpression(this.newExpression.copy());
		listConsNorm.setOldExpression(this.oldExpression.copy());
		return listConsNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("ListConsNorm object");
		System.out.print("Old Expression: ");
		this.oldExpression.print();
		System.out.println("New Expression: ");
		this.newExpression.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ListConsNorm))
			return false;
		ListConsNorm listConsNorm = (ListConsNorm)obj;
		if (!this.newExpression.equals(listConsNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(listConsNorm.getOldExpression()))
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

	public void setNewExpression(ListCons listCons) 
		{
		this.newExpression = listCons;
		}

	@Override
	public ListCons getNewExpression() 
		{
		return this.newExpression;
		}

	public Expression getOldExpression() 
		{
		return oldExpression;
		}
	}
