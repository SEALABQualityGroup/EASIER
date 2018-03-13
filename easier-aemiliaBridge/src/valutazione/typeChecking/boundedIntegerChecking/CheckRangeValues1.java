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
public class CheckRangeValues1 {

	private IntegerRangeType integerRangeType;
	private int depth;
	private ErrorMessage errorMessage;

	public CheckRangeValues1(IntegerRangeType integerRangeType, int depth) {
		super();
		this.integerRangeType = integerRangeType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * Entrambe le espressioni che definiscono l'intervallo di interi
	 * devono essere valutate come interi.
	 *  
	 * @return
	 */
	public boolean checkRangeValues1()
		{
		/* MODELED */
		Expression expression = this.integerRangeType.getBeginningInt();
		Expression expression2 = this.integerRangeType.getEndingInt();
		if (!(expression instanceof Integer))
			{
			// 1
			String string = "type checking error for " + this.integerRangeType.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = "beginning expression " + expression.toString() + " is not integer";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list2.add(errorMessage);
			return false;
			}
		if (!(expression2 instanceof Integer))
			{
			// 2
			String string = "type checking error for " + this.integerRangeType.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = "ending expression " + expression2.toString() + " is not integer";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list2.add(errorMessage);
			return false;
			}
		return true;
		}
	
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}	

}
