/**
 * 
 */
package valutazione.typeChecking.aeiIndChecking;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.AEIdeclInd;
import specificheAEmilia.Expression;
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

	private AEIdeclInd ideclInd;
	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;
	
	public CheckRange2(AEIdeclInd ideclInd, TreeMap<String, ValueIdentExpr> tm, int depth) 
		{
		super();
		this.ideclInd = ideclInd;
		this.tm = tm;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * L'espressione finale dell'intervallo deve essere un intero.
	 *  
	 * @return
	 * @throws TypeCheckingException
	 */
	public boolean checkRange2()
		throws TypeCheckingException
		{
		try {
			Expression expression = this.ideclInd.getEndingExpr();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, "",
					null, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string = "type checking error for " + this.ideclInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list2.add(errorMessage);
				return false;
				}
			if (!DataTypeEnum.Integer.equals(dataTypeEnum))
				{
				String string = "type checking error for " + this.ideclInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				String string2 = dataTypeEnum.toString() + " is not integer type";
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
