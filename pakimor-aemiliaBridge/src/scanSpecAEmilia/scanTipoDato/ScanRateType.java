/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.RateType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanRateType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanRateType(int depth) 
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
	 * al tipo di dato rate.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isRateType(String specifiche)
		{
		// "rate"
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*rate\\s*"))
			{
			String string = specifiche + " is not rate type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"rate\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}


	/**
	 * Crea un oggetto Rate attraverso la scannerizzazione
	 * di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Rate.
	 * @throws ScanException
	 */
	public RateType scanRateType(String specifiche)
		throws ScanException
		{
		RateType r = new RateType();
		return r;
		}
}
