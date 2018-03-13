/**
 * 
 */
package scanSpecAEmilia.scanProcessTerm;

import java.util.List;

import personalScanner.MyScanner;
import specificheAEmilia.Stop;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanStop {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanStop(int depth) 
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
	 * al termine stop.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isStop(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*stop\\s*"))
			{
			String string = specifiche + " is not stop";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"stop\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Stop attraverso la scannerizzazione di un
	 * termine stop.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Stop.
	 */
	public Stop scanStop(String specifiche)
		{
		// "stop"
		Stop s = new Stop();
		s.setCondition(new specificheAEmilia.Boolean(true));
		return s;
		}
}
