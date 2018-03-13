/**
 * 
 */
package valutazione.typeChecking.interactionIndChecking;

import java.util.List;

import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.InteractionDeclInd;
import utilities.ErrorMessage;
import valutazione.typeChecking.TypeCheckingException;

/**
 * @author Jimmy
 *
 */
public class CheckRange3 {

	private InteractionDeclInd interactionDeclInd;
	private int depth;
	private ErrorMessage errorMessage;

	public CheckRange3(InteractionDeclInd interactionDeclInd, int depth) 
		{
		super();
		this.interactionDeclInd = interactionDeclInd;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Il valore dell'espressione iniziale del range  
	 * non puo' essere piu' grande del valore dell'espressione
	 * finale del range. Per precondizione le espressioni iniziale e quella finale
	 * sono in forma normale.
	 * 
	 * @return
	 */
	public boolean checkRange3()
		throws TypeCheckingException
		{
		Expression expression = this.interactionDeclInd.getBeginningExpr();
		// per precondizione expressione'un intero
		Integer integer = (Integer) expression;
		Expression expression3 = this.interactionDeclInd.getEndingExpr();
		// per precondizione expression3e'un intero
		Integer integer2 = (Integer) expression3;
		int i = integer.getValore();
		int j = integer2.getValore();
		if (i > j) 
			{
			String string = "type checking error for " + this.interactionDeclInd.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = "beginning expression " + expression + " is greater than ending expression " + expression3;
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
