/**
 * 
 */
package scanSpecAEmilia.scanAETinteractions;

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
class ScanIdSeq {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanIdSeq(int depth) 
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
	 * Restituisce true se e solo se specifichee'una sequenza non
	 * vuota di identificatori separati da punti e virgola.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isIdSeq(String specifiche)
		{
		/* MODELLED */
		// non sono accettate sequenze che iniziano con un
		// punto e virgola
		if (specifiche.matches("\\s*\\;(.)*")) 
			{
			String string = specifiche + " is not identifiers sequence";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = specifiche + " must not begin with \";\"";
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// non sono accettate sequenze che terminano con un
		// punto e virgola
		if (specifiche.matches("(.)*\\;\\s*\\z")) 
			{
			String string = specifiche + " is not identifiers sequence";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = specifiche + " must not end with \";\"";
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\;\\s*");
		String azione = new String();
		int c = 0;
		while (s.hasNext())
			{
			azione = s.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			if (!scanIdent.isIdent(azione)) 
				{
				String string = specifiche + " is not identifiers sequence";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanIdent.getErrorMessage();
				list.add(errorMessage2);				
				return false;
				}
			c++;
			}
		// la sequenza non deve essere vuota
		if (c == 0) 
			{
			String string = specifiche + " is not identifiers sequence";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = "not empty sequence expected";
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Scannerizza una sequenza di identificatori separati da
	 * punti e virgola, generando un array di oggetti String.
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti String.
	 * @throws ScanException
	 */
	public String[] scanIdSeq(String specifiche)
		throws ScanException
		{
		try {
			String[] seqId = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\;\\s*");
			// non sono accettate sequenze che iniziano con un
			// punto e virgola
			if (specifiche.matches("\\s*\\;(.)*"))
				throw new InputMismatchException();
			// non sono accettate sequenze che terminano con un
			// punto e virgola
			if (specifiche.matches("(.)*\\;\\s*\\z"))
				throw new InputMismatchException();
			String azione = new String();
			// si conta il numero delle azioni
			int c = 0;
			while (s.hasNext()) {
				azione = s.next("\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
				c++;
			}
			seqId = new String[c];
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\;\\s*");
			// si scannerizzano le azioni
			for (int i = 0; i < c; i++) {
				azione = s.next("\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
				seqId[i] = azione;
			}
			return seqId;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
