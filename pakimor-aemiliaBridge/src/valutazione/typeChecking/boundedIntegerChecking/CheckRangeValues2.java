/**
 * 
 */
package valutazione.typeChecking.boundedIntegerChecking;

import java.util.List;

import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerRangeType;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
public class CheckRangeValues2 {

	/**
	 *  Per precondizione
	 *  integerRangeTypee'in forma normale.
	 */
	private IntegerRangeType integerRangeType;
	private int depth;
	private ErrorMessage errorMessage;

	public CheckRangeValues2(IntegerRangeType integerRangeType, int depth) 
		{
		super();
		this.integerRangeType = integerRangeType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Il valore della prima espressione non puo' essere piu' grande 
	 * della seconda espressione.
	 * 
	 * @return
	 */
	public boolean checkRangeValues2()
		{
		/* MODELED */
		Expression expression = this.integerRangeType.getBeginningInt();
		Expression expression2 = this.integerRangeType.getEndingInt();
		Integer integer = (Integer)expression;
		Integer integer2 = (Integer)expression2;
		int i = integer.getValore();
		int j = integer2.getValore();
		if (i > j)
			{
			// 1
			String string = "type checking error for " + this.integerRangeType.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = integer.toString() + " must be lesser or equal to " + integer2.toString();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list2.add(errorMessage);
			return false;
			}
		else
			return true;
		}	
	
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}	

}
