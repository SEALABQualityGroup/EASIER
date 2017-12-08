/**
 * 
 */
package valutazione.typeChecking;

import java.util.List;

import interfacceSpecifiche.NumberExp;
import specificheAEmilia.Expression;
import specificheAEmilia.RateExp;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class RateExpChecking 
	{
	
	private RateExp rateExp;
	private int depth;
	private ErrorMessage errorMessage;
	
	public RateExpChecking(RateExp rateExp,int depth) 
		{
		super();
		this.rateExp = rateExp;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * l'espressione deve essere valutata come un reale positivo.
	 * 
	 * @return
	 */
	public boolean checkPosRealExp() 
		throws TypeCheckingException
		{
		try {
			Expression expression = this.rateExp.getExpr();
			if (expression instanceof NumberExp) 
				{
				NumberExp numberExp = (NumberExp) expression;
				double d = numberExp.getNumber();
				if (d <= 0) 
					{
					String string = "Checking error for " + this.rateExp.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = "rate is not positive";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					return false;
					} 
				else
					return true;
				}	 
			else 
				{
				String string = "Checking error for " + this.rateExp.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = "rate is not number";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				return false;
				}
			} 
		catch (Exception e) 
			{
			throw new TypeCheckingException(e);
			}
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	
	
	}
