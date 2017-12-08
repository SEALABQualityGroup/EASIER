/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.RecordCons;


/**
 * @author Mirko
 *
 */
public class RecordConsNorm extends ExpressionNorm {

	private Expression oldExpression;
	private RecordCons newExpression;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public RecordConsNorm copy() 
		{
		RecordConsNorm recordConsNorm = new RecordConsNorm();
		recordConsNorm.setNewExpression(this.newExpression.copy());
		recordConsNorm.setOldExpression(this.oldExpression.copy());
		return recordConsNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("RecordConsNorm object");
		System.out.print("Old Expression: ");
		this.oldExpression.print();
		System.out.println("New Expression: ");
		this.newExpression.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof RecordConsNorm))
			return false;
		RecordConsNorm recordConsNorm = (RecordConsNorm)obj;
		if (!this.newExpression.equals(recordConsNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(recordConsNorm.getOldExpression()))
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

	public void setNewExpression(RecordCons recordCons) 
		{
		this.newExpression = recordCons;
		}

	@Override
	public RecordCons getNewExpression() 
		{
		return this.newExpression;
		}

	public Expression getOldExpression() 
		{
		return this.oldExpression;
		}
	}
