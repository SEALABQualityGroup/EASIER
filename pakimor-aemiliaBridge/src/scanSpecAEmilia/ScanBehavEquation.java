package scanSpecAEmilia;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import personalScanner.MyScanner;
import scanSpecAEmilia.scanIntestazione.ScanIntestazione;
import scanSpecAEmilia.scanProcessTerm.ScanProcessTerm;
import specificheAEmilia.BehavEquation;
import utilities.ErrorMessage;


/**
 * Classe utilizzata per scannerizzare ogni parte di
 * un'equazione comportamentale, dettata
 * dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 * Un'equazione comportamentale EMPA ha la seguente forma:
 *
 * <behav_equation_header> "=" <process_term>
 */

public class ScanBehavEquation {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanBehavEquation(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad un'equazione comportamentale secondo la grammatica
	 * AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isBehavEquation(String specifiche)
		{
		/* MODELLED */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\=\\s*");
		String pt = new String();
		String beh = new String();
		String u = new String();
		ScanIntestazione scanIntestazione = null;
		ScanProcessTerm scanProcessTerm = null;
		// si scannerizza specifiche fin quando la parte
		// sinistra di un segno di ugualee'un'intestazione
		// e la parte destrae'un termine di processo
		while (s.hasNext())
			{
			// beh contiene l'intestazione di un'equazione
			// comportamento
			beh = beh + s.next();
			s.useDelimiter("\\s*\\z");
			try {
				s.skip("\\s*\\=\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not behavioral equation declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "\"=\" expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			u = MyScanner.precMatch(s);
			// pt contiene un termine di processo
			try {
				pt = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not behavioral equation declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "process term expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			scanIntestazione = new ScanIntestazione(this.depth + 1);
			scanProcessTerm = new ScanProcessTerm(this.depth + 1);
			if (scanIntestazione.isIntestazione(beh) &&
					scanProcessTerm.isProcessTerm(pt))
				return true;
			else {
				// nel caso in cui none'stato trovato un
				// termine di processo e un'intestazione
				// si aggiunge l'uguale scannerizzato
				// all'intestazione
				beh = beh + u;
				s = new MyScanner(specifiche);
				s.useDelimiter("\\s*\\z");
				// si scannerizza la parte dell'intestazione
				// gia' esaminata
				s.skip(Pattern.quote(beh));
				s.useDelimiter("\\s*\\=\\s*");
				}
			}
		List<ErrorMessage> list = this.errorMessage.getCauses();
		String string = specifiche + " is not behavioral equation declaration";
		this.errorMessage.setErrorMessage(string);
		if (!scanIntestazione.isIntestazione(beh))
			{
			ErrorMessage errorMessage2 = scanIntestazione.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		if (!scanProcessTerm.isProcessTerm(pt))
			{
			ErrorMessage errorMessage = scanProcessTerm.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return false;
		}

	/**
	 * Crea un oggetto BehavEquation, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto BehavEquation.
	 * @throws ScanException
	 */
	public BehavEquation scanBehavEquation(String specifiche)
		throws ScanException
		{
		try {
			BehavEquation be = new BehavEquation();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\=\\s*");
			String pt = new String();
			String beh = new String();
			String u = new String();
			// si scannerizza specifiche fin quando la parte
			// sinistra di un segno di ugualee'un'intestazione
			// e la parte destrae'un termine di processo
			while (s.hasNext()) {
				// beh contiene l'intestazione di un'equazione
				// comportamento
				beh = beh + s.next();
				s.useDelimiter("\\s*\\z");
				s.skip("\\s*\\=\\s*");
				u = MyScanner.precMatch(s);
				// pt contiene un termine di processo
				pt = s.next();
				// se abbiamo trovato un'intestazione e un
				// termine di processo allora restituiamo
				// un oggetto BehavEquation
				ScanIntestazione scanIntestazione = new ScanIntestazione(this.depth + 1);
				ScanProcessTerm scanProcessTerm = new ScanProcessTerm(this.depth + 1);
				if (scanIntestazione.isIntestazione(beh)
						&& scanProcessTerm.isProcessTerm(pt)) {
					be.setBehavHeader(scanIntestazione
							.scanIntestazione(beh));
					be.setTermineProcesso(scanProcessTerm
							.scanProcessTerm(pt));
					return be;
				} else {
					// nel caso in cui none'stato trovato un
					// termine di processo e un'intestazione
					// si aggiunge l'uguale scannerizzato
					// all'intestazione (sie'ancora all'interno dell'intestazione)
					beh = beh + u;
					s = new MyScanner(specifiche);
					s.useDelimiter("\\s*\\z");
					// si scannerizza la parte dell'intestazione
					// gia' esaminata
					s.skip(Pattern.quote(beh));
					s.useDelimiter("\\s*\\=\\s*");
				}
			}
			return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
