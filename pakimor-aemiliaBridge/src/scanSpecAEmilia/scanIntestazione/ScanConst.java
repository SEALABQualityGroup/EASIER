/**
 * 
 */
package scanSpecAEmilia.scanIntestazione;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import scanSpecAEmilia.scanTipoDato.ScanTipoDato;
import specificheAEmilia.Const;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanConst {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanConst(int depth) 
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
	 * ad una dichiarazione di costante.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isConst(String specifiche)
		{
		/*
		 * "const" <data_type> <identifier>e'una dichiarazione di
		 * parametro formale costante utilizzata nell'intestazione
		 * di un AET, il cui valoree'definito nella dichiarazione
		 * delle istanze dell'AET nella sezione di topologia
		 * architetturale.
		 */
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*const\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of constant";
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
			String string = specifiche + " is not declaration of constant";
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
				String string = specifiche + " is not declaration of constant";
				this.errorMessage.setErrorMessage(string);
				String string2 = "integer range type expected";
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
				String string = specifiche + " is not declaration of constant";
				this.errorMessage.setErrorMessage(string);
				String string2 = "\")\" expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			}
		// nomeConste'il nome della costante
		String nomeConst = null;
		try {
			nomeConst = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of constant";
			this.errorMessage.setErrorMessage(string);
			String string2 = "constant name expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.skip("\\s*\\z");
		ScanTipoDato scanTipoDato = new ScanTipoDato(this.depth + 1);
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		if (!scanTipoDato.isTipoDato(tipoDato))
			{
			String string = specifiche + " is not declaration of constant";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanTipoDato.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (!scanIdent.isIdent(nomeConst))
			{
			String string = specifiche + " is not declaration of constant";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Const, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Const.
	 * @throws ScanException
	 */
	public Const scanConst(String specifiche)
		throws ScanException
		{
		try {
			Const c = new Const();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*const\\s*");
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
			// nomeConste'il nome della costante
			String nomeConst = s.next();
			s.skip("\\s*\\z");
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanTipoDato scanTipoDato = new ScanTipoDato(this.depth + 1);
			c.setName(scanIdent.scanIdent(nomeConst));
			c.setType(scanTipoDato.scanTipoDato(tipoDato));
			return c;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
