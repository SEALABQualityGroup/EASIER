/**
 * 
 */
package valutazione.typeChecking.attacIndChecking;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.AttacDeclInd;
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
public class CheckRange4 {

	private AttacDeclInd attacDeclInd;
	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;
	
	public CheckRange4(AttacDeclInd attacDeclInd,
			TreeMap<String, ValueIdentExpr> tm,
			int depth) {
		super();
		this.attacDeclInd = attacDeclInd;
		this.tm = tm;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * L'espressione finale del secondo range deve essere un intero.
	 * 
	 * @return
	 * @throws TypeCheckingException
	 */
	public boolean checkRange4()
		throws TypeCheckingException
		{
		try {
			Expression expression = this.attacDeclInd.getEndingExpr2();
			if (expression != null) {
				TypeRising typeRising = new TypeRising(this.depth + 1);
				DataTypeEnum dataTypeEnum = typeRising.rising(expression,
						"", null, this.tm);
				if (typeRising.isErrorOccurred())
					{
					String string = "type checking error for " + this.attacDeclInd.toString();
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
					String string = "type checking error for " + this.attacDeclInd.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					String string2 = dataTypeEnum + " is not integer type";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list2.add(errorMessage);
					return false;
					}
			} else
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
