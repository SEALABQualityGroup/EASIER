/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Integer;

/**
 * @author Mirko
 *
 */
public class IntegerNorm extends NumberExpNorm {

	@Override
	public Object copy() {
		IntegerNorm integerNorm = new IntegerNorm();
		integerNorm.setNewExpression(this.newExpression.copy());
		integerNorm.setOldExpression(this.oldExpression.copy());
		return integerNorm;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IntegerNorm))
			return false;
		IntegerNorm integerNorm = (IntegerNorm) obj;
		if (!this.newExpression.equals(integerNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(integerNorm.getOldExpression()))
			return false;
		return true;
	}

	@Override
	public void print() {
		System.out.println("IntegerNorm object");
		System.out.print("Old Expression: ");
		this.oldExpression.print();
		System.out.println("New Expression: ");
		this.newExpression.print();
	}

	@Override
	public String toString() {
		String string = new String();
		string += "New Expression: ";
		string += this.newExpression;
		string += " Old Expression: ";
		string += this.oldExpression + " ";
		return string;
	}

	public void setOldExpression(Expression expression) {
		this.oldExpression = expression;
	}

	public void setNewExpression(Integer integer) {
		this.newExpression = integer;
	}

//	public Expression getOldExpression() {
//		return oldExpression;
//	}
//
//	public Integer getNewExpression() {
//		return newExpression;
//	}

	@Override
	public double getNumber() {
		return ((Integer)this.newExpression).getNumber();
	}
}
