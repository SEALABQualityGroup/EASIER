/**
 * 
 */
package scanSpecAEmilia.scanIntestazione;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import scanSpecAEmilia.scanTipoDato.ScanNormalType;
import scanSpecAEmilia.scanTipoDato.ScanTipoDato;
import specificheAEmilia.Local;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanLocal {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanLocal(int depth) 
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
	 * ad una dichiarazione di variabile locale.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isLocal(String specifiche)
		{
		/*
		 * "local" <normal_type> <identifier> viene di solito
		 * utilizzata quando si sincronizza un'azione di input di
		 * un'istanza dell'AET con un'azione di output di un
		 * altro AEI. Tale dichiarazione viene utilizzata
		 * nell'intestazione di un'equazione comportamentale.
		 */
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*local\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not local variable declaration";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"local\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// tipodato contiene il nome del tipo dato
		String tipoDato = null;
		try {
			tipoDato = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not local variable declaration";
			this.errorMessage.setErrorMessage(string);
			String string2 = "data type expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\z");
		// il tipo di dato puo' essere IntegerRangeType
		if (s.hasNext("\\s*\\((.*)\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*"))
			{
			s.useDelimiter("\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
			try {
				tipoDato = tipoDato + s.next() + ")";
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not local variable declaration";
				this.errorMessage.setErrorMessage(string);
				String string2 = "range of integers expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			s.useDelimiter("\\s*\\z");
			try {
				s.skip("\\s*\\)\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not local variable declaration";
				this.errorMessage.setErrorMessage(string);
				String string2 = "\")\" expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			}
		// nomeLoc contiene il nome della variabile locale
		String nomeLoc = null;
		try {
			nomeLoc = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not local variable declaration";
			this.errorMessage.setErrorMessage(string);
			String string2 = "local variable identifier expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		try {
			s.skip("\\s*\\z");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not local variable declaration";
			this.errorMessage.setErrorMessage(string);
			String string2 = "spaces expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanTipoDato scanTipoDato = new ScanTipoDato(this.depth + 1);
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		if (!scanIdent.isIdent(nomeLoc))
			{
			String string = specifiche + " is not local variable declaration";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanTipoDato.isTipoDato(tipoDato))
			{
			String string = specifiche + " is not local variable declaration";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanTipoDato.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Local, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Local.
	 * @throws ScanException
	 */
	public Local scanLocal(String specifiche)
		throws ScanException
		{
		try {
			Local l = new Local();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*local\\s*");
			// tipodato contiene il nome del tipo dato
			String tipoDato = s.next();
			s.useDelimiter("\\s*\\z");
			// il tipo di dato puo' essere IntegerRangeType
			if (s
					.hasNext("\\s*\\((.*)\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*")) {
				s
						.useDelimiter("\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
				tipoDato = tipoDato + s.next() + ")";
				s.useDelimiter("\\s*\\z");
				s.skip("\\s*\\)\\s*");
			}
			// nomeLoc contiene il nome della variabile locale
			String nomeLoc = s.next();
			s.skip("\\s*\\z");
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanNormalType scanNormalType = new ScanNormalType(this.depth + 1);
			l.setName(scanIdent.scanIdent(nomeLoc));
			l.setType(scanNormalType.scanNormalType(tipoDato));
			return l;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
