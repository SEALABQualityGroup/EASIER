/**
 * 
 */
package valutazione.typeChecking.interactionIndChecking;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.Expression;
import specificheAEmilia.InteractionDeclInd;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeCheckingException;
import valutazione.typeChecking.typeRising.TypeRising;

/**
 * @author Jimmy
 *
 */
public class CheckRange2 {

	private InteractionDeclInd interactionDeclInd;
	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;

	public CheckRange2(InteractionDeclInd interactionDeclInd,
			TreeMap<String, ValueIdentExpr> tm, int depth) 
		{
		super();
		this.interactionDeclInd = interactionDeclInd;
		this.tm = tm;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(this.depth);
		}

	/**
	 * L'espressione finale del range deve essere un intero.
	 * 
	 * @return
	 * @throws TypeCheckingException
	 */
	public boolean checkRange2()
		throws TypeCheckingException
		{
		try {
			Expression expression = this.interactionDeclInd.getEndingExpr();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum;
			dataTypeEnum = typeRising.rising(expression, "", null, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string = "type checking error for " + this.interactionDeclInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list2.add(errorMessage);
				return false;
				}
			if (DataTypeEnum.Integer.equals(dataTypeEnum))
				return true;
			else
				{
				String string = "type checking error for " + this.interactionDeclInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				String string2 = dataTypeEnum + " is not integer type";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list2.add(errorMessage);
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
