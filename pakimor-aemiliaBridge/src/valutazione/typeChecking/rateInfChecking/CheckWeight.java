/**
 * 
 */
package valutazione.typeChecking.rateInfChecking;

import java.util.List;

import interfacceSpecifiche.NumberExp;
import specificheAEmilia.Expression;
import specificheAEmilia.RateInf;
import utilities.ErrorMessage;
import valutazione.typeChecking.TypeCheckingException;

/**
 * @author Mirko
 *
 */
public class CheckWeight {

	private RateInf rateInf;
	private int depth;
	private ErrorMessage errorMessage;
	
	public CheckWeight(RateInf rateInf,int depth) 
		{
		super();
		this.rateInf = rateInf;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * il peso deve essere valutato come un reale positivo.
	 * 
	 * @return
	 * @throws TypeCheckingException
	 */
	public boolean checkWeight()
		throws TypeCheckingException
		{
		Expression expression = this.rateInf.getWeight();
		if (expression instanceof NumberExp) 
			{
			NumberExp numberExp = (NumberExp) expression;
			double d = numberExp.getNumber();
			if (d <= 0) 
				{
				String string = "type checking error for " + this.rateInf.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				String string2 = d + " is not greater than 0";
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
			String string2 = expression + " is not a number";
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
