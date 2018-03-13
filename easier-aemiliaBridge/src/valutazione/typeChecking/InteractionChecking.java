/**
 * 
 */
package valutazione.typeChecking;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.AEIident;
import specificheAEmilia.Expression;
import specificheAEmilia.InteractionDecl;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.typeRising.TypeRising;

/**
 * @author Mirko
 *
 */
public class InteractionChecking 
	{

	private InteractionDecl interactionDecl;
	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;

	public InteractionChecking(InteractionDecl interactionDecl,
			TreeMap<String, ValueIdentExpr> tm, int depth) 
		{
		super();
		this.interactionDecl = interactionDecl;
		this.tm = tm;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Il selettore deve essere valutato come un intero.
	 * 
	 * @return
	 * @throws TypeCheckingException
	 */
	public boolean checkSelector()
		throws TypeCheckingException
		{
		try {
			AEIident aeIident = this.interactionDecl.getAei();
			Expression expression = aeIident.getSelector();
			if (expression != null) {
				TypeRising typeRising = new TypeRising(this.depth + 1);
				DataTypeEnum dataTypeEnum;
				dataTypeEnum = typeRising.rising(expression, "", null,
						this.tm);
				if (typeRising.isErrorOccurred())
					{
					String string = "type checking error for " + this.interactionDecl.toString();
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
					String string = "type checking error for " + this.interactionDecl.toString();
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
