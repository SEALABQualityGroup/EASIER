/**
 * 
 */
package scanSpecAEmilia.scanArchiInteractions;

import java.util.InputMismatchException;
import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanInteractionDecl.ScanInteractionDecl;
import specificheAEmilia.InteractionDecl;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanPAID {
	
	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanPAID(int depth) 
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
	 * Restituisce true se e solo se specifichee'una sequenza di
	 * dichiarazioni di interazioni architetturali.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isPAID(String specifiche)
		{
		// specifiche non puo' iniziare con un punto e virgola
		if (specifiche.matches("\\s*\\;(.)*"))
			{
			String string1 = specifiche + " is not sequence of architectural interactions declarations";
			this.errorMessage.setErrorMessage(string1);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " must not begin with \";\"";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string);
			list.add(errorMessage2);
			return false;
			}
		// specifiche non puo' terminare con un punto e virgola
		if (specifiche.matches("(.)*\\;\\s*\\z")) 
			{
			String string1 = specifiche + " is not sequence of architectural interactions declarations";
			this.errorMessage.setErrorMessage(string1);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " must not end with \";\"";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string);
			list.add(errorMessage2);
			return false;
			}
		MyScanner s = new MyScanner(specifiche);
		if (s.hasNext("\\s*void\\s*\\z")) return true;
		s.useDelimiter("\\s*\\;\\s*");
		String aid = new String();
		// deve esserci almeno una dichiarazione
		boolean founded = false;
		// verifica see'una sequenza di dichiarazioni
		// di interazioni architetturali
		while (s.hasNext())
			{
			aid = s.next();
			ScanInteractionDecl scanInteractionDecl = new ScanInteractionDecl(this.depth + 1);
			if (!scanInteractionDecl.isInteractionDecl(aid))
				{
				String string1 = specifiche + " is not sequence of architectural interactions declarations";
				this.errorMessage.setErrorMessage(string1);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanInteractionDecl.getErrorMessage();
				list.add(errorMessage2);				
				return false;
				}
			founded = true;
			}
		if (founded)
			return true;
		else
			{
			String string1 = specifiche + " is not sequence of architectural interactions declarations";
			this.errorMessage.setErrorMessage(string1);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " must be not empty sequence";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string);
			list.add(errorMessage2);
			return false;
			}
		}

	/**
	 * Scannerizza una sequenza di dichiarazioni di interazioni,
	 * generando un array di oggetti InteractionDecl.
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti InteractionDecl.
	 * @throws ScanException
	 */
	public InteractionDecl[] scanPAID(String specifiche)
		throws ScanException
		{
		try {
			InteractionDecl[] ids = null;
			MyScanner s = new MyScanner(specifiche);
			if (s.hasNext("\\s*void\\s*\\z"))
				return null;
			s.useDelimiter("\\s*\\;\\s*");
			String aid = new String();
			int c = 0;
			// specifiche non puo' iniziare con un punto e virgola
			if (specifiche.matches("\\s*\\;(.)*"))
				throw new InputMismatchException();
			// specifiche non puo' terminare con un punto e virgola
			if (specifiche.matches("(.)*\\;\\s*\\z"))
				throw new InputMismatchException();
			// si contano le dichiarazioni
			while (s.hasNext()) {
				aid = s.next();
				c++;
			}
			ids = new InteractionDecl[c];
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\;\\s*");
			// si scannerizzano le dichiarazioni
			for (int i = 0; i < c; i++) {
				aid = s.next();
				ScanInteractionDecl scanInteractionDecl = new ScanInteractionDecl(this.depth + 1);
				ids[i] = scanInteractionDecl.scanInteractionDecl(aid);
			}
			return ids;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}


}
