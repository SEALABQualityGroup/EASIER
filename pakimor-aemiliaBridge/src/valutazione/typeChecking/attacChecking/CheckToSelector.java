/**
 * 
 */
package valutazione.typeChecking.attacChecking;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.AEIident;
import specificheAEmilia.AttacDecl;
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
public class CheckToSelector {

	private AttacDecl attacDecl;
	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;
	
	public CheckToSelector(AttacDecl attacDecl,
			TreeMap<String, ValueIdentExpr> tm,
			int depth) {
		super();
		this.attacDecl = attacDecl;
		this.tm = tm;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * Il selettore to deve essere valutato come un intero.
	 * 
	 * @return
	 * @throws TypeCheckingException
	 */
	public boolean checkToSelector()
		throws TypeCheckingException
		{
		try {
			AEIident aeIident = this.attacDecl.getInputAei();
			Expression expression = aeIident.getSelector();
			if (expression != null) {
				TypeRising typeRising = new TypeRising(this.depth + 1);
				DataTypeEnum dataTypeEnum;
				dataTypeEnum = typeRising.rising(expression, "", null,
						this.tm);
				if (typeRising.isErrorOccurred())
					{
					String string = "type checking error for " + this.attacDecl.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = typeRising.getErrorMessage();
					list2.add(errorMessage);
					return false;
					}
				if (dataTypeEnum.equals(DataTypeEnum.Integer)) {
					return true;
				} else 
					{
					String string = "type checking error for " + this.attacDecl.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					String string2 = dataTypeEnum.toString() + " is not integer type";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list2.add(errorMessage);
					return false;
					}
			} else {
				return true;
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
