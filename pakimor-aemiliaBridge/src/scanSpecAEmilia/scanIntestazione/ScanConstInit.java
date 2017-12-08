/**
 * 
 */
package scanSpecAEmilia.scanIntestazione;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import scanSpecAEmilia.scanExp.ScanExp;
import scanSpecAEmilia.scanTipoDato.ScanTipoDato;
import specificheAEmilia.ConstInit;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanConstInit {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanConstInit(int depth) 
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
	 * ad una dichiarazione di costante inizializzata.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isConstInit(String specifiche)
		{
		/*
		 * "const" <data_type> <identifier> ":=" <expr>e'una
		 * dichiarazione di parametri formali costanti e
		 * inizializzati utilizzata nell'intestazione della
		 * definizione di un tipo architetturale.
		 */
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*const\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not initialized constant";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"const\" expected";
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
			String string = specifiche + " is not initialized constant";
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
		if (s.hasNext("\\s*\\((.*)\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*\\:\\s*\\=\\s*" +
				"(.)*"))
			{
			s.useDelimiter("\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
			try {
				tipoDato = tipoDato + s.next() + ")";
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not initialized constant";
				this.errorMessage.setErrorMessage(string);
				String string2 = "data type expected";
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
				String string = specifiche + " is not initialized constant";
				this.errorMessage.setErrorMessage(string);
				String string2 = "\")\" expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			}
		s.useDelimiter("\\s*\\:\\s*\\=\\s*");
		// nomeConste'il nome della costante
		String nomeConst = null;
		try {
			nomeConst = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not initialized constant";
			this.errorMessage.setErrorMessage(string);
			String string2 = "constant name expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\z");
		try {
			s.skip("\\s*\\:\\s*\\=\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not initialized constant";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\":=\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// exprInite'l'espressione di inizializzazione
		// per la costante
		String exprInit = null;
		try {
			exprInit = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not initialized constant";
			this.errorMessage.setErrorMessage(string);
			String string2 = "initial expression expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.skip("\\s*\\z");
		ScanTipoDato scanTipoDato = new ScanTipoDato(this.depth + 1);
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!scanTipoDato.isTipoDato(tipoDato))
			{
			String string = specifiche + " is not initialized constant";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanTipoDato.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanIdent.isIdent(nomeConst))
			{
			String string = specifiche + " is not initialized constant";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanExp.isEspressione(exprInit))
			{
			String string = specifiche + " is not initialized constant";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanExp.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ConstInit, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto ConstInit.
	 * @throws ScanException
	 */
	public ConstInit scanConstInit(String specifiche)
		throws ScanException
		{
		try {
			ConstInit ci = new ConstInit();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*const\\s*");
			// tipodato contiene il nome del tipo dato
			String tipoDato = s.next();
			s.useDelimiter("\\s*\\z");
			// il tipo di dato puo' essere IntegerRangeType
			if (s
					.hasNext("\\s*\\((.*)\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*\\:\\s*\\=\\s*"
							+ "(.)*")) {
				s
						.useDelimiter("\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
				tipoDato = tipoDato + s.next() + ")";
				s.useDelimiter("\\s*\\z");
				s.skip("\\s*\\)\\s*");
			}
			s.useDelimiter("\\s*\\:\\s*\\=\\s*");
			// nomeConste'il nome della costante
			String nomeConst = s.next();
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*\\:\\s*\\=\\s*");
			// exprInite'l'espressione di inizializzazione
			// per la costante
			String exprInit = s.next();
			s.skip("\\s*\\z");
			ScanTipoDato scanTipoDato = new ScanTipoDato(this.depth + 1);
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanExp scanExp = new ScanExp(this.depth + 1);
			ci.setType(scanTipoDato.scanTipoDato(tipoDato));
			ci.setName(scanIdent.scanIdent(nomeConst));
			ci.setExpr(scanExp.scanEspressione(exprInit));
			return ci;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
