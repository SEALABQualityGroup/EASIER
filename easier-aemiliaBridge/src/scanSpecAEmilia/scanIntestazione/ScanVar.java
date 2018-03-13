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
import specificheAEmilia.VariableDeclaration;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanVar {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanVar(int depth) 
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
	 * ad una dichiarazione di variabile.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isVar(String specifiche)
		{
		/*
		 * <normal_type> <identifier> viene utilizzata
		 * nell'equazioni comportamentali susseguenti la prima e
		 * consiste nella dichiarazione di un parametro formale
		 * variabile. Nessuna espressione di inizializzazione e'
		 * necessaria per i parametri formali variabili di ogni
		 * equazione comportamentale susseguente, poich�, ad essi,
		 * saranno assegnati i valori dei parametri attuali
		 * contenuti nell'invocazioni dell'equazione
		 * comportamentale correlata.
		 */
		MyScanner s = new MyScanner(specifiche);
		// tipodato contiene il nome del tipo dato
		String tipoDato = null;
		try {
			tipoDato = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not variable declaration";
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
				String string = specifiche + " is not variable declaration";
				this.errorMessage.setErrorMessage(string);
				String string2 = "ranges of integer declaration expected";
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
				String string = specifiche + " is not variable declaration";
				this.errorMessage.setErrorMessage(string);
				String string2 = "\")\" expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			}
		// nomeVar contiene il nome della variabile
		String nomeVar = null;
		try {
			nomeVar = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not variable declaration";
			this.errorMessage.setErrorMessage(string);
			String string2 = "variable name expected";
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
			String string = specifiche + " is not variable declaration";
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
		if (!scanIdent.isIdent(nomeVar))
			{
			String string = specifiche + " is not variable declaration";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanNormalType.isNormalType(tipoDato))
			{
			String string = specifiche + " is not variable declaration";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanNormalType.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto VariableDeclaration, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto VariableDeclaration.
	 * @throws ScanException
	 */
	public VariableDeclaration scanVar(String specifiche)
		throws ScanException
		{
		try {
			VariableDeclaration v = new VariableDeclaration();
			MyScanner s = new MyScanner(specifiche);
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
			// nomeVar contiene il nome della variabile
			String nomeVar = s.next();
			s.skip("\\s*\\z");
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanNormalType scanNormalType = new ScanNormalType(this.depth + 1);
			v.setName(scanIdent.scanIdent(nomeVar));
			v.setType(scanNormalType.scanNormalType(tipoDato));
			return v;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
