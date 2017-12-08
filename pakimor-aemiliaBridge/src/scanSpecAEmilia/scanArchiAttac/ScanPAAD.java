/**
 * 
 */
package scanSpecAEmilia.scanArchiAttac;

import java.util.InputMismatchException;
import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanAttacDecl.ScanAttacDecl;
import specificheAEmilia.AttacDecl;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanPAAD {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanPAAD(int depth) 
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
	 * vuota di dichiarazioni di collegamenti architetturali
	 * separate da punti e virgola.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isPAAD(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		// la sequenza di collegamenti architetturali
		// puo' essere vuota
		if (s.hasNext("\\s*void\\s*\\z")) return true;
		// la sequenza di collegamenti architeturali non puo'
		// iniziare con un punto e virgola
		if (specifiche.matches("\\s*\\;(.)*")) 
			{
			String string1 = specifiche + " is not sequence of architectural attachments declaration";
			this.errorMessage.setErrorMessage(string1);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " must not begin with \";\"";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string);
			list.add(errorMessage2);
			return false;
			}
		// la sequenza di collegamenti architetturali non puo'
		// terminare con un punto e virgola
		if (specifiche.matches("(.)*\\;\\s*\\z")) 
			{
			String string1 = specifiche + " is not sequence of architectural attachments declaration";
			this.errorMessage.setErrorMessage(string1);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " must not end with \";\"";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string);
			list.add(errorMessage2);
			return false;
			}
		s.useDelimiter("\\s*\\;\\s*");
		String aad = new String();
		boolean founded = false;
		// si verifica se tra punti e virgola ci sono
		// collegamenti architetturali
		while (s.hasNext())
			{
			aad = s.next();
			ScanAttacDecl scanAttacDecl = new ScanAttacDecl(this.depth + 1);
			if (!scanAttacDecl.isAttacDecl(aad)) 
				{
				String string1 = specifiche + " is not sequence of architectural attachments declaration";
				this.errorMessage.setErrorMessage(string1);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanAttacDecl.getErrorMessage();
				list.add(errorMessage2);				
				return false;
				}
			founded = true;
			}
		// la sequenza non deve essere vuota
		if (founded)
			return true;
		else
			{
			String string1 = specifiche + " is not sequence of architectural attachments declaration";
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
	 * Scannerizza una sequenza di dichiarazioni di
	 * collegamenti architetturali separate da punti e virgola,
	 * generando un array di oggetti AttacDecl.
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti AttacDecl.
	 * @throws ScanException
	 */
	public AttacDecl[] scanPAAD(String specifiche)
		throws ScanException
		{
		try {
			AttacDecl[] aas = null;
			MyScanner s = new MyScanner(specifiche);
			// void corrisponde a null
			if (s.hasNext("\\s*void\\s*\\z"))
				return null;
			s.useDelimiter("\\s*\\;\\s*");
			String aad = new String();
			int c = 0;
			// la sequenza non puo' iniziare con un punto e
			// virgola
			if (specifiche.matches("\\s*\\;(.)*"))
				throw new InputMismatchException();
			// la sequenza non puo' terminare con un punto e
			// virgola
			if (specifiche.matches("(.)*\\;\\s*\\z"))
				throw new InputMismatchException();
			// vengono contate le dichiarazioni di collegamenti
			// architetturali
			while (s.hasNext()) {
				aad = s.next();
				c++;
			}
			aas = new AttacDecl[c];
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\;\\s*");
			// vengono scannerizzate le dichiarazioni di
			// collegamenti architetturali
			for (int i = 0; i < c; i++) {
				aad = s.next();
				ScanAttacDecl scanAttacDecl = new ScanAttacDecl(this.depth + 1);
				aas[i] = scanAttacDecl.scanAttacDecl(aad);
			}
			return aas;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
