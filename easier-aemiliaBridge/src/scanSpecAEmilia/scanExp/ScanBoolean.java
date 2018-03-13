/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.Scanner;

import scanSpecAEmilia.ScanException;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanBoolean {

	private ErrorMessage errorMessage;
	
	public ScanBoolean(int depth) 
		{
		super();
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad un valore booleano.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isBoolean(String specifiche)
		{
		Scanner s = new Scanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (!s.hasNextBoolean())
			{
			String string = specifiche + " is not boolean";
			this.errorMessage.setErrorMessage(string);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Boolean, ottenuto attraverso la
	 * scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Boolean.
	 * @throws ScanException
	 */
	public specificheAEmilia.Boolean scanBoolean(String specifiche)
		throws ScanException
		{
		try {
			// false | true
			specificheAEmilia.Boolean e = new specificheAEmilia.Boolean();
			Scanner s = new Scanner(specifiche);
			s.useDelimiter("\\s*\\z");
			boolean b = s.nextBoolean();
			e.setValore(b);
			return e;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
