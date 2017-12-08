/**
 * 
 */
package valutazione.typeChecking;

import java.util.List;

import specificheAEmilia.Expression;
import specificheAEmilia.Get;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Put;
import utilities.ErrorMessage;

/**
 * controlla che l'espressione per il record presente in un'espressione get o put 
 * sia di tipo IdentExpr, perche' altrimenti i campi del record non avrebbero un tipo.
 * 
 * @author Mirko
 *
 */
public class IdentExprRecordTyping 
	{
	private int depth;
	private ErrorMessage errorMessage;
	
	public IdentExprRecordTyping(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se get ha come record un oggetto
	 * IdentExpr.
	 * 
	 * @param get
	 * @return
	 */
	public boolean isIdentExpRecord(Get get) throws TypeCheckingException
		{
		/* MODELED */
		Expression expression = get.getRecord();
		boolean b = expression instanceof IdentExpr;
		if (!b)
			{
			// 1
			String string = "type checking error for " + get.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = "record is not identifier";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		return true;
		}
	
	/**
	 * Restituisce true se e solo se put ha come record un oggetto
	 * IdentExpr.
	 * 
	 * @param put
	 * @return
	 */
	public boolean isIdentExpRecord(Put put) throws TypeCheckingException
		{
		/* MODELED */
		Expression expression = put.getRecord();
		boolean b = expression instanceof IdentExpr;
		if (!b)
			{
			// 1
			String string = "type checking error for " + put.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = "record is not identifier";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	
	}
