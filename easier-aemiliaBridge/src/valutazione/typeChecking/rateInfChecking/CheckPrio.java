/**
 * 
 */
package valutazione.typeChecking.rateInfChecking;

import java.util.List;

import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.RateInf;
import utilities.ErrorMessage;
import valutazione.typeChecking.TypeCheckingException;

/**
 * @author Mirko
 *
 */
public class CheckPrio {

	private RateInf rateInf;
	private int depth;
	private ErrorMessage errorMessage;

	public CheckPrio(RateInf rateInf, int depth) {
		super();
		this.rateInf = rateInf;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * la priorita' deve essere valutata come 
	 * un intero non minore di uno.
	 * 
	 * @return
	 * @throws TypeCheckingException
	 */
	public boolean checkPrio()
		throws TypeCheckingException
		{
		Expression expression = this.rateInf.getPrio();
		if (expression instanceof Integer) 
			{
			Integer integer = (Integer) expression;
			int i = integer.getValore();
			if (i < 1) 
				{
				String string = "type checking error for " + this.rateInf.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				String string2 = i + " is lesser than 1";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list2.add(errorMessage);
				return false;
				} 
			else
				return true;
			} 
		else 
			{
			String string = "type checking error for " + this.rateInf.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = expression + " is not integer";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list2.add(errorMessage);
			return false;
			}
		}
	
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}	

}
