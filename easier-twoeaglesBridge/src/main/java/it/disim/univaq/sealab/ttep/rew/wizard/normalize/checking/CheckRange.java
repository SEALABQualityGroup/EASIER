/**
 * 
 */
package it.disim.univaq.sealab.ttep.rew.wizard.normalize.checking;

import java.util.List;

import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import utilities.ErrorMessage;
import valutazione.typeChecking.TypeCheckingException;

/**
 * @author Mirko
 *
 */
public class CheckRange {

	private Expression beginningExp;
	private Expression endingExp;
	private int depth;
	private ErrorMessage errorMessage;
	
	public CheckRange(Expression beginningExp, Expression endingExp, int depth) 
		{
		super();
		this.beginningExp = beginningExp;
		this.endingExp = endingExp;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Il valore dell'espressione iniziale   
	 * non puo' essere piu' grande del valore dell'espressione
	 * finale. Per precondizione le espressioni iniziale e quella finale sono in forma normale.
	 * 
	 * @return
	 */
	public boolean checkRange()
		throws TypeCheckingException
		{
		try {
			Expression expression = this.beginningExp;
			// per precondizione expression deve essere un intero
			Integer integer = (Integer) expression;
			Expression expression3 = this.endingExp;
			// per precondizione expression3 deve essere un intero
			Integer integer2 = (Integer) expression3;
			int i = integer.getValore();
			int j = integer2.getValore();
			if (i > j) 
				{
				String string = "type checking error for a range";
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

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}		
}
