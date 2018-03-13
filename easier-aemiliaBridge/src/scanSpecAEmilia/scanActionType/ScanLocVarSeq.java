/**
 * 
 */
package scanSpecAEmilia.scanActionType;

import java.util.InputMismatchException;
import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanLocVarSeq {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanLocVarSeq(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}
	
	/**
	 * Restituisce true se e solo se specifichee'una sequenza non vuota
	 * di variabili locali separate da virgole.
	 * @param specifiche - oggetto String.
	 * @param message 
	 * @return un valore booleano.
	 */
	public boolean isLocVarSeq(String specifiche)
		{
		// non viene accettata una sequenza di variabili
		// locali che inizia con una virgola
		if (specifiche.matches("\\s*\\,(.)*")) 
			{
			String string1 = specifiche + " is not sequence of local variable";
			this.errorMessage.setErrorMessage(string1);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string = specifiche + " must not begin with \",\"";
			errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// non viene accettata una sequenza di variabili
		// locali che termina con una virgola
		if (specifiche.matches("(.)*\\,\\s*\\z")) 
			{
			String string1 = specifiche + " is not sequence of local variable";
			this.errorMessage.setErrorMessage(string1);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string = specifiche + " must not end with \",\"";
			errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\,\\s*");
		// ogni stringa tra due virgole deve essere un
		// identificatore
		int c = 0;
		while (s.hasNext())
			{
			c++;
			String st = new String();
			st = s.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			if (!scanIdent.isIdent(st))
				{
				String string = st + " must be identifier";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage = scanIdent.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			}
		// la sequenza non puo' essere vuota
		if (c == 0)
			{
			String string1 = specifiche + " is not sequence of local variable";
			this.errorMessage.setErrorMessage(string1);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string = "local variables sequence must not be empty";
			errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}
	
	/**
	 * Restituisce un array di identificatori che costituisce
	 * una sequenza di variabili locali separate da virgole,
	 * come indicato in specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti String.
	 * @throws ScanException
	 */
	public String[] scanLocVarSeq(String specifiche)
		throws ScanException
		{
		try {
			// non viene accettata una sequenza di variabili
			// locali che inizia con una virgola
			if (specifiche.matches("\\s*\\,(.)*"))
				throw new InputMismatchException();
			// non viene accettata una sequenza di variabili
			// locali che termina con una virgola
			if (specifiche.matches("(.)*\\,\\s*\\z"))
				throw new InputMismatchException();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\,\\s*");
			// si contano quante variabili locali ci sono
			int i = 0;
			while (s.hasNext()) {
				s.next("\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
				i++;
			}
			String[] ris = new String[i];
			i = 0;
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*,\\s*");
			// ogni stringa tra due virgole deve essere un
			// identificatore
			while (s.hasNext()) {
				String st = s
						.next("\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
				ScanIdent scanIdent = new ScanIdent(this.depth + 1);
				ris[i] = scanIdent.scanIdent(st);
				i++;
			}
			return ris;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}	
}
