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
import scanSpecAEmilia.scanTipoDato.ScanNormalType;
import specificheAEmilia.VarInit;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanVarInit {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanVarInit(int depth) 
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
	 * ad una dichiarazione di variabile inizializzata.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isVarInit(String specifiche)
		{
		/*
		 * <normal_type> <identifier> ":=" <expr> viene
		 * utilizzata nell'intestazione della prima
		 * equazione comportamentale e consiste nella
		 * dichiarazione di un parametro formale
		 * variabile inizializzato.
		 */
		MyScanner s = new MyScanner(specifiche);
		// tipodato contiene il nome del tipo dato
		String tipoDato = null;
		try {
			tipoDato = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of initialized variable";
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
			s.useDelimiter("\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*\\:\\s*\\=\\s*");
			try {
				tipoDato = tipoDato + s.next() + ")";
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not declaration of initialized variable";
				this.errorMessage.setErrorMessage(string);
				String string2 = "range of integer declaration expected";
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
				String string = specifiche + " is not declaration of initialized variable";
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
		// nomeVarInit contiene il nome della variabile
		String nomeVarInit = null;
		try {
			nomeVarInit = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of initialized variable";
			this.errorMessage.setErrorMessage(string);
			String string2 = "initialized variable name expected";
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
			String string = specifiche + " is not declaration of initialized variable";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\":=\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// exprInit contiene l'espressione di
		// inizializzazione
		String exprInit = null;
		try {
			exprInit = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of initialized variable";
			this.errorMessage.setErrorMessage(string);
			String string2 = "initial expression expected";
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
			String string = specifiche + " is not declaration of initialized variable";
			this.errorMessage.setErrorMessage(string);
			String string2 = "spaces expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanNormalType scanNormalType = new ScanNormalType(this.depth + 1);
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!scanExp.isEspressione(exprInit))
			{
			String string = specifiche + " is not declaration of initialized variable";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanExp.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanIdent.isIdent(nomeVarInit))
			{
			String string = specifiche + " is not declaration of initialized variable";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanNormalType.isNormalType(tipoDato))
			{
			String string = specifiche + " is not declaration of initialized variable";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanNormalType.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto VarInit, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto VarInit.
	 * @throws ScanException
	 */
	public VarInit scanVarInit(String specifiche)
		throws ScanException
		{
		try {
			VarInit vi = new VarInit();
			MyScanner s = new MyScanner(specifiche);
			// tipodato contiene il nome del tipo dato
			String tipoDato = s.next();
			s.useDelimiter("\\s*\\z");
			// il tipo di dato puo' essere IntegerRangeType
			if (s
					.hasNext("\\s*\\((.)*\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*\\:\\s*\\=\\s*"
							+ "(.)*")) {
				s
						.useDelimiter("\\)\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
				tipoDato = tipoDato + s.next() + ")";
				s.useDelimiter("\\s*\\z");
				s.skip("\\s*\\)\\s*");
			}
			s.useDelimiter("\\s*\\:\\s*\\=\\s*");
			// nomeVarInit contiene il nome della variabile
			String nomeVarInit = s.next();
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*\\:\\s*\\=\\s*");
			// exprInit contiene l'espressione di
			// inizializzazione
			String exprInit = s.next();
			s.skip("\\s*\\z");
			ScanNormalType scanNormalType = new ScanNormalType(this.depth + 1);
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanExp scanExp = new ScanExp(this.depth + 1);
			vi.setType(scanNormalType.scanNormalType(tipoDato));
			vi.setName(scanIdent.scanIdent(nomeVarInit));
			vi.setExpr(scanExp.scanEspressione(exprInit));
			return vi;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
