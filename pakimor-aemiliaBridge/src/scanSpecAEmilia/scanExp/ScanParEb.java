/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanParEb {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanParEb(int depth) 
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
	 * ad un'operazione booleana tra parentesi tonde.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isParEb(String specifiche)
		{
		/* MODELLED */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (s.hasNext("\\s*\\(.*\\)(\\s)*\\z"))
			{
			s.skip("\\s*\\(");
			s.useDelimiter("\\)(\\s)*\\z");
			// ste'un'espressione booleana senza parentesi
			String st = null;
			try {
				st = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not boolean expression in parentheses";
				this.errorMessage.setErrorMessage(string);
				String string2 = "boolean expression expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			ScanEb scanEb = new ScanEb(this.depth + 1);
			if (!scanEb.isEb(st))
				{
				String string = specifiche + " is not boolean expression in parentheses";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanEb.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			return true;
			}
		else
			{
			String string = specifiche + " is not boolean expression in parentheses";
			this.errorMessage.setErrorMessage(string);
			String string2 = "parentheses expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);			
			return false;
			}
		}

	/**
	 * Crea un oggetto Expression, ottenuto attraverso la
	 * scannerizzazione di un'espressione booleana tra
	 * parentesi tonde.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Expression.
	 * @throws ScanException
	 */
	public Expression scanParEb(String specifiche)
		throws ScanException
		{
		try {
			// (Eb)
			Expression e = null;
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*\\(\\s*");
			s.useDelimiter("\\s*\\)\\s*\\z");
			// st contiene un'espressione booleana senza
			// parentesi
			String st = s.next();
			ScanEb scanEb = new ScanEb(this.depth + 1);
			e = scanEb.scanEb(st);
			return e;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
