/**
 * 
 */
package scanSpecAEmilia.scanExp;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.Real;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanReal {

	private ErrorMessage errorMessage;
	
	public ScanReal(int depth) 
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
	 * ad un numero reale.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isReal(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*(-)?(0|([1-9]([0-9])*))\\.(\\d)+\\s*"))
			{
			String string = specifiche + " is not real";
			this.errorMessage.setErrorMessage(string);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Real, ottenuto attraverso la
	 * scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Real.
	 * @throws ScanException
	 */
	public Real scanReal(String specifiche)
		throws ScanException
		{
		try {
			// digits.digits
			Real r = new Real();
			double f = scanDoubleJava(specifiche);
			r.setValore(f);
			return r;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
	
	/**
	 * Crea un numero double,  ottenuto attraverso la
	 * scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un numero double.
	 * @throws ScanException
	 */

	private double scanDoubleJava(String specifica)
		throws ScanException
		{
		try {
			// scannerizza un float in forma puntata
			MyScanner s = new MyScanner(specifica);
			s.useDelimiter("\\s*\\z");
			Double d = new Double(0);
			String st = s.next();
			d = new Double(st);
			return d.doubleValue();
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
