/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.List;

import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.Real;
import utilities.ErrorMessage;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
public class ConcatInd {

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public ConcatInd(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

    /**
     * Concatena nome con il valore di e, che puo' essere un
     * Real o un Integer.
     *
     * @param nome
     * @param e
     * @return
     * @throws NormalizeException
     */
    public String concatInd(String nome, Expression e)
        throws NormalizeException
        {
        try {
			String ris = null;
			if (e instanceof Integer) 
				{
				java.lang.Integer i = new java.lang.Integer(((Integer) e)
						.getValore());
				ris = nome + i.toString();
				} 
			else if (e instanceof Real) 
				{
				Double d = new Double(((Real) e).getValore());
				java.lang.Integer i = new java.lang.Integer(d.intValue());
				ris = nome + i.toString();
				} 
			else 
				{
				String string = "Type checking error for " + nome;
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = e + " is not a integer";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return ris;
			} 
        catch (Exception e2) 
        	{
			throw new NormalizeException(e2);
        	}
        }

	public boolean isErrorOccurred() 
		{
		return errorOccurred;
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
