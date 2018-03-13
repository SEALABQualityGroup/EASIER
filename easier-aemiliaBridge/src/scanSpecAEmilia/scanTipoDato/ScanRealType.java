/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.RealType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanRealType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanRealType(int depth) 
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
	 * al tipo di dato real.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isRealType(String specifiche)
		{
		// "real"
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*real\\s*"))
			{
			String string = specifiche + " is not real type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"real\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list= this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}


	/**
	 * Crea un oggetto Real attraverso la scannerizzazione
	 * di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Real.
	 * @throws ScanException
	 */
	public RealType scanRealType(String specifiche)
		throws ScanException
		{
		RealType r = new RealType();
		return r;
		}
}
