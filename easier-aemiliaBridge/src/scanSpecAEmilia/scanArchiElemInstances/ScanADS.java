/**
 * 
 */
package scanSpecAEmilia.scanArchiElemInstances;

import java.util.InputMismatchException;
import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanAEIdecl.ScanAEIdecl;
import specificheAEmilia.AEIdecl;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanADS {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanADS(int depth) 
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
	 * Restituisce true se e solo se specifichee'una sequenza non vuota di
	 * dichiarazioni di istanze di elementi architetturali separati
	 * da punti e virgola.
	 * 
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isADS(String specifiche)
		{
		// la sequenza di dichiarazioni non puo' iniziare con
		// un punto e virgola
		if (specifiche.matches("\\s*\\;(.)*")) 
			{
			String string1 = specifiche + " is not sequence of architectural element instances";
			this.errorMessage.setErrorMessage(string1);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " must not begin with \";\"";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string);
			list.add(errorMessage2);
			return false;
			}
		// la sequenza di dichiarazioni non puo' terminare con
		// un punto e virgola
		if (specifiche.matches("(.)*\\;\\s*\\z")) 
			{
			String string1 = specifiche + " is not sequence of architectural element instances";
			this.errorMessage.setErrorMessage(string1);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " must not end with \";\"";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string);
			list.add(errorMessage2);
			return false;
			}
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\;\\s*");
		String adss = new String();
		// si verificano tutte le dichiarazioni tra istanze
		// di elementi architetturali separate da punti e
		// virgola
		int c = 0;
		while (s.hasNext())
			{
			adss = s.next();
			ScanAEIdecl scanAEIdecl = new ScanAEIdecl(this.depth + 1);
			if (!scanAEIdecl.isAEIdecl(adss)) 
				{
				String string1 = specifiche + " is not sequence of architectural element instances";
				this.errorMessage.setErrorMessage(string1);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanAEIdecl.getErrorMessage();
				list.add(errorMessage2);				
				return false;
				}
			c++;
			}
		// non viene accettata una sequenza di dichiarazioni
		// vuota
		if (c==0) 
			{
			String string1 = specifiche + " is not sequence of architectural element instances";
			this.errorMessage.setErrorMessage(string1);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " must be not empty sequence";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string);
			list.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Scannerizza una sequenza di dichiarazioni di istanze
	 * di elementi architetturali separati da punti e virgola,
	 * generando un array di oggetti AEIdecl.
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti AEIdecl.
	 * @throws ScanException
	 */
	public AEIdecl[] scanADS(String specifiche)
		throws ScanException
		{
		try {
			AEIdecl[] ads = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\;\\s*");
			String aad = new String();
			int c = 0;
			// non viene accettata una sequenza di dichiarazioni
			// vuota
			if (specifiche.matches(""))
				throw new InputMismatchException();
			// la sequenza di dichiarazioni non puo' iniziare con
			// un punto e virgola
			if (specifiche.matches("\\s*\\;(.)*"))
				throw new InputMismatchException();
			// la sequenza di dichiarazioni non puo' terminare con
			// un punto e virgola
			if (specifiche.matches("(.)*\\;\\s*\\z"))
				throw new InputMismatchException();
			// si conta il numero di dichiarazioni
			while (s.hasNext()) {
				aad = s.next();
				c++;
			}
			// Deve esserci almeno una dichiarazione di istanza di
			// elemento architetturale
			ads = new AEIdecl[c];
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\;\\s*");
			// si scannerizzano le dichiarazioni di istanze
			// di elementi architetturali
			for (int i = 0; i < c; i++) {
				aad = s.next();
				ScanAEIdecl scanAEIdecl = new ScanAEIdecl(this.depth + 1);
				ads[i] = scanAEIdecl.scanAEIdecl(aad);
			}
			return ads;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
