/**
 * 
 */
package valutazione.typeChecking.aeiIndChecking;

import java.util.List;

import specificheAEmilia.AEIdeclInd;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import utilities.ErrorMessage;
import valutazione.typeChecking.TypeCheckingException;

/**
 * @author Jimmy
 *
 */
public class CheckRange3 {

	private AEIdeclInd ideclInd;
	private int depth;
	private ErrorMessage errorMessage;
	
	public CheckRange3(AEIdeclInd ideclInd, int depth) {
		super();
		this.ideclInd = ideclInd;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * Il valore dell'espressione iniziale del range  
	 * non puo' essere piu' grande del valore dell'espressione
	 * finale del range. Per precondizione le espressioni iniziale e quella finale sono in forma normale.
	 * 
	 * @return
	 */
	public boolean checkRange3()
		throws TypeCheckingException
		{
		try {
			Expression expression = this.ideclInd.getBeginningExpr();
			// per precondizione expression deve essere un intero
			Integer integer = (Integer) expression;
			Expression expression3 = this.ideclInd.getEndingExpr();
			// per precondizione expression3 deve essere un intero
			Integer integer2 = (Integer) expression3;
			int i = integer.getValore();
			int j = integer2.getValore();
			if (i > j) 
				{
				String string = "type checking error for " + this.ideclInd.toString();
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
		catch (Exception e) 
			{
			throw new TypeCheckingException(e);
			}
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	
	
}
