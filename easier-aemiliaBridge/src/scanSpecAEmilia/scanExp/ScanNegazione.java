/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.Expression;
import specificheAEmilia.Negazione;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanNegazione {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanNegazione(int depth) 
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
	 * ad un'operazione booleana di negazione.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isNegazione(String specifiche)
		{
		/* MODELLED */
		MyScanner s = new MyScanner(specifiche);
		if (s.hasNext("\\s*\\!(.)*"))
			{
			s.skip("\\s*\\!\\s*");
			s.useDelimiter("\\s*\\z");
			// st contiene un Ebt
			String st = null;
			try {
				st = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not negation expression";
				this.errorMessage.setErrorMessage(string);
				String string2 = "boolean expression term expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			ScanEbt scanEbt = new ScanEbt(this.depth + 1);
			if (!scanEbt.isEbt(st))
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage31 = scanEbt.getErrorMessage();
				list.add(errorMessage31);
				}
			else return true;
			}
		String string = specifiche + " is not negation expression";
		this.errorMessage.setErrorMessage(string);
		return false;
		}

	/**
	 * Crea un oggetto Negazione, ottenuto attraverso la
	 * scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Negazione.
	 * @throws ScanException
	 */
	public Negazione scanNegazione(String specifiche)
		throws ScanException
		{
		try {
			// !Ebt
			Negazione n = new Negazione();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*\\!\\s*");
			s.useDelimiter("\\s*\\z");
			// st contiene un Ebt
			String st = s.next();
			ScanEbt scanEbt = new ScanEbt(this.depth + 1);
			Expression op = scanEbt.scanEbt(st);
			n.setExpr1(op);
			return n;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
	
}
