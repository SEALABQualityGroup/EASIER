/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.IntegerType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanIntegerType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanIntegerType(int depth) 
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
	 * al tipo di dato integer.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isIntegerType(String specifiche)
		{
		// "integer"
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*integer\\s*"))
			{
			String string = specifiche + " is not integer type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"integer\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}


	/**
	 * Crea un oggetto Integer attraverso la scannerizzazione
	 * di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Integer.
	 * @throws ScanException
	 */
	public IntegerType scanIntegerType(String specifiche)
		throws ScanException
		{
		try {
			IntegerType i = new IntegerType();
			if (isIntegerType(specifiche)) {
				return i;
			} else
				return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
