/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.Scanner;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanInteger {

	private ErrorMessage errorMessage;
	
	public ScanInteger(int depth) 
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
	 * ad un numero intero.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isInteger(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*(-)?(0|([1-9]([0-9])*))\\s*"))
			{
			String string = specifiche + " is not integer";
			this.errorMessage.setErrorMessage(string);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Integer, ottenuto attraverso la
	 * scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Real.
	 * @throws ScanException
	 */
	public specificheAEmilia.Integer scanInteger(String specifiche)
		throws ScanException
		{
		try {
			// digits
			specificheAEmilia.Integer i = new specificheAEmilia.Integer();
			Scanner s = new Scanner(specifiche);
			s.useDelimiter("\\s*\\z");
			int intero = s.nextInt();
			i.setValore(intero);
			return i;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
