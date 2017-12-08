/**
 * 
 */
package valutazione.typeChecking;

import java.util.List;

import specificheAEmilia.ArrayCons;
import specificheAEmilia.Expression;
import specificheAEmilia.RecordCons;
import utilities.ErrorMessage;

/**
 * Contiene due metodi per stabilire se le sequenze di espressioni
 * presenti in un'espressione array_cons o record_cons siano vuote o meno.
 * 
 * @author Mirko
 *
 */
public class NotEmptySequence 
	{
	
	private int depth;
	private ErrorMessage errorMessage;
	
	public NotEmptySequence(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public boolean isNotEmptySequence(ArrayCons arrayCons) throws TypeCheckingException
		{
		/* MODELED */
		try {
			Expression[] expressions = arrayCons.getArrayElements();
			boolean b = expressions.length > 0;
			if (!b)
				{
				// 1
				String string = "Checking error for " + arrayCons.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = "Empty sequence founded";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				}
			return b;
			} 
		catch (Exception e) 
			{
			throw new TypeCheckingException(e);
			}
		}
	
	public boolean isNotEmptySequence(RecordCons recordCons) throws TypeCheckingException
		{
		try {
			Expression[] expressions = recordCons.getValues();
			boolean b = expressions.length > 0;
			if (!b)
				{
				String string = "Checking error for " + recordCons.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = "Empty sequence founded";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				}
			return b;
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
