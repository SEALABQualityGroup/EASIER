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
class ScanParEa {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanParEa(int depth) 
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
	 * ad un'espressione tra parentesi tonde.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isParEa(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (s.hasNext("\\s*\\(.*\\)(\\s)*\\z"))
			{
			s.skip("\\s*\\(");
			s.useDelimiter("\\)(\\s)*\\z");
			// st contiene l'espressione aritmetica senza
			// parentesi
			String st = null;
			try {
				st = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not arithmetic expression in parentheses";
				this.errorMessage.setErrorMessage(string);
				String string2 = "arithmetic expression expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			ScanEa scanEa = new ScanEa(this.depth + 1);
			if (!scanEa.isEa(st))
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanEa.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			return true;
			}
		String string = specifiche + " is not arithmetic expression";
		this.errorMessage.setErrorMessage(string);
		return false;
		}


	/**
	 * Crea un oggetto Expression, ottenuto attraverso la
	 * scannerizzazione di un'espressione aritmetica tra
	 * parentesi tonde.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Expression.
	 * @throws ScanException
	 */
	public Expression scanParEa(String specifiche)
		throws ScanException
		{
		try {
			// (Ea)
			Expression e = null;
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*\\(\\s*");
			s.useDelimiter("\\s*\\)\\s*\\z");
			// ste'un'espressione aritmetica senza parentesi
			String st = s.next();
			ScanEa scanEa = new ScanEa(this.depth + 1);
			e = scanEa.scanEa(st);
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*\\)\\s*");
			return e;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
