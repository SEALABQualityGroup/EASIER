/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.BooleanType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanBooleanType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanBooleanType(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * al tipo di dato boolean.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isBooleanType(String specifiche)
		{
		// "boolean"
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*boolean\\s*"))
			{
			String string = specifiche + "  is not boolean type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"boolean\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}
	
	/**
	 * Crea un oggetto Boolean attraverso la scannerizzazione
	 * di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Boolean.
	 * @throws ScanException
	 */
	public BooleanType scanBooleanType(String specifiche)
		throws ScanException
		{
		try {
			BooleanType b = new BooleanType();
			if (isBooleanType(specifiche)) {
				return b;
			} else
				return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
