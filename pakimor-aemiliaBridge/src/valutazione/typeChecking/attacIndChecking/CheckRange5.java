/**
 * 
 */
package valutazione.typeChecking.attacIndChecking;

import java.util.List;

import specificheAEmilia.AttacDeclInd;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import utilities.ErrorMessage;
import valutazione.typeChecking.TypeCheckingException;

/**
 * @author Jimmy
 *
 */
public class CheckRange5 {

	private AttacDeclInd attacDeclInd;
	private int depth;
	private ErrorMessage errorMessage;
	
	public CheckRange5(AttacDeclInd attacDeclInd, int depth) {
		super();
		this.attacDeclInd = attacDeclInd;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * Il valore dell'espressione iniziale del primo range  
	 * non puo' essere piu' grande del valore dell'espressione
	 * finale del primo range. Per precondizione le espressioni iniziali e finali
	 * sono in forma normale.
	 * 
	 * @return
	 */
	public boolean checkRange5()
		throws TypeCheckingException
		{
		try {
			Expression expression = this.attacDeclInd.getBeginningExpr1();
			// per precondizione expressione'un intero
			Integer integer = (Integer) expression;
			Expression expression3 = this.attacDeclInd.getEndingExpr1();
			// per precondizione expression3e'un intero
			Integer integer2 = (Integer) expression3;
			int i = integer.getValore();
			int j = integer2.getValore();
			if (i > j) 
				{
				String string = "type checking error for " + this.attacDeclInd.toString();
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
