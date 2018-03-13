/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.PrioType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanPrioType {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanPrioType(int depth) 
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
	 * al tipo di dato prio.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isPrioType(String specifiche)
		{
		// "prio"
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*prio\\s*"))
			{
			String string = specifiche + "  is not priority type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"prio\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Prio attraverso la scannerizzazione
	 * di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Prio.
	 * @throws ScanException
	 */
	public PrioType scanPrioType(String specifiche)
		throws ScanException
		{
		PrioType p = new PrioType();
		return p;
		}

}
