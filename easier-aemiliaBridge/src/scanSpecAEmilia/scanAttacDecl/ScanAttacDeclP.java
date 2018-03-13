/**
 * 
 */
package scanSpecAEmilia.scanAttacDecl;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.AEIident;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanAttacDeclP {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanAttacDeclP(int depth) 
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
	 * alla dichiarazione semplice di un collegamento tra elementi
	 * architetturali secondo la grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isAttacDeclP(String specifiche)
		{
		/*
		 * Una dichiarazione di collegamento architetturale ha la seguente forma:
		 *
		 * <architectural_attachment_decl> ::= "FROM" <identifier> ["[" <expr> "]"]
		 * "." <identifier> "TO" <identifier> ["[" <expr> "]"] "." <identifier>
		 */
		String FromAei = new String();
		String SF = new String();
		String InputInteraction = new String();
		String ToAei = new String();
		String ST = new String();
		String OutputInteraction = new String();
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*FROM\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"FROM\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		s.useDelimiter("\\s*\\.\\s*[a-zA-Z_&&[^0-9<>=!()]](\\w*)\\s*");
		// FromAei contiene l'Aei di partenza
		try {
			FromAei = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "source aei expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		// FromAei puo' contenere un selettore
		if (FromAei.contains("["))
			{
			MyScanner s1 = new MyScanner(FromAei);
			s1.useDelimiter("\\s*\\[\\s*");
			try {
				FromAei = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not simple attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "source aei expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			s1.useDelimiter("\\s*\\]\\s*");
			try {
				s1.skip("\\s*\\[\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not simple attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "\"[\" expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			// SFe'il selettore di istanze
			try {
				SF = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not simple attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "instance selector expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			try {
				s1.skip("\\s*\\]\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not simple attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "\"]\" expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			}
		s.useDelimiter("\\s*TO\\s*");
		try {
			s.skip("\\s*\\.\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\".\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		// OutputInteractione'l'interazione di output
		try {
			OutputInteraction = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "output interaction expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		s.useDelimiter("\\s*\\.\\s*[a-zA-Z_&&[^0-9<>=!()]](\\w*)\\s*");
		try {
			s.skip("\\s*TO\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"TO\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		// ToAeie'l'istanza di arrivo
		try {
			ToAei = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "target aei expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		// ToAei puo' contenere un selettore
		if (ToAei.contains("["))
			{
			MyScanner s1 = new MyScanner(ToAei);
			s1.useDelimiter("\\s*\\[\\s*");
			try {
				ToAei = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not simple attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "target aei expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			s1.useDelimiter("\\s*\\]\\s*");
			try {
				s1.skip("\\s*\\[\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not simple attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "\"[\" expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			// STe'il selettore di istanze
			try {
				ST = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not simple attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "instance selector expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			try {
				s1.skip("\\s*\\]\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not simple attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "\"]\" expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			}
		s.useDelimiter("\\s*\\z");
		try {
			s.skip("\\s*\\.\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\".\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		// InputInteractione'l'interazione di input
		try {
			InputInteraction = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "input interaction expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		if (!scanIdent.isIdent(FromAei))
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = scanIdent.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		ScanIdent scanIdent2 = new ScanIdent(this.depth + 1);
		if (!scanIdent2.isIdent(OutputInteraction))
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = scanIdent2.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		ScanIdent scanIdent3 = new ScanIdent(this.depth + 1);
		if (!scanIdent3.isIdent(ToAei))
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = scanIdent3.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		ScanIdent scanIdent4 = new ScanIdent(this.depth + 1);
		if (!scanIdent.isIdent(InputInteraction))
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not simple attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = scanIdent4.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!SF.equals(""))
			{
			if (!scanExp.isEspressione(SF))
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not simple attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = scanExp.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			}
		ScanExp scanExp2 = new ScanExp(this.depth + 1);
		if (!ST.equals("")) 
			{
			if (!scanExp2.isEspressione(ST))
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not simple attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = scanExp2.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			}
		return true;
		}

	/**
	 * Crea un oggetto AttacDecl come una dichiarazione semplice di
	 * un collegamento tra elementi architetturali, includendo
	 * informazioni ottenute attraverso la scannerizzazione di
	 * specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto AttacDecl.
	 * @throws ScanException
	 */
	public AttacDecl scanAttacDeclP(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Una dichiarazione di collegamento architetturale ha la seguente forma:
			 *
			 * <architectural_attachment_decl> ::= "FROM" <identifier> ["[" <expr> "]"]
			 * "." <identifier> "TO" <identifier> ["[" <expr> "]"] "." <identifier>
			 */
			AttacDecl ad = new AttacDecl();
			String FromAei = new String();
			String SF = new String();
			String InputInteraction = new String();
			String ToAei = new String();
			String ST = new String();
			String OutputInteraction = new String();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*FROM\\s*");
			s.useDelimiter("\\s*\\.\\s*[a-zA-Z_&&[^0-9<>=!()]](\\w*)\\s*");
			// istanza di arrivo del collegamento
			FromAei = s.next();
			// FromAei puo' contenere un selettore
			if (FromAei.contains("[")) {
				MyScanner s1 = new MyScanner(FromAei);
				s1.useDelimiter("\\s*\\[\\s*");
				FromAei = s1.next();
				s1.useDelimiter("\\s*\\]\\s*");
				s1.skip("\\s*\\[\\s*");
				// selettore di istanze
				SF = s1.next();
				s1.skip("\\s*\\]\\s*");
			}
			s.useDelimiter("\\s*TO\\s*");
			s.skip("\\s*\\.\\s*");
			// OutputInteractione'l'interazione di output
			OutputInteraction = s.next();
			s.useDelimiter("\\s*\\.\\s*[a-zA-Z_&&[^0-9<>=!()]](\\w*)\\s*");
			s.skip("\\s*TO\\s*");
			// ToAeie'l'istanza di arrivo
			ToAei = s.next();
			// ToAei puo' contenere un selettore
			if (ToAei.contains("[")) {
				MyScanner s1 = new MyScanner(ToAei);
				s1.useDelimiter("\\s*\\[\\s*");
				ToAei = s1.next();
				s1.useDelimiter("\\s*\\]\\s*");
				s1.skip("\\s*\\[\\s*");
				ST = s1.next();
				s1.skip("\\s*\\]\\s*");
			}
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*\\.\\s*");
			// InputInteractione'l'interazione di input
			InputInteraction = s.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			String string = scanIdent.scanIdent(FromAei);
			Expression expression = null;
			String string2 = scanIdent.scanIdent(ToAei);
			Expression expression2 = null;
			ad.setOutputInteraction(scanIdent.scanIdent(OutputInteraction));
			ad.setInputInteraction(scanIdent.scanIdent(InputInteraction));
			ScanExp scanExp = new ScanExp(this.depth + 1);
			if (!SF.equals(""))
				expression = scanExp.scanEspressione(SF);
			if (!ST.equals(""))
				expression2 = scanExp.scanEspressione(ST);
			AEIident aeIident = new AEIident(string, expression);
			AEIident aeIident2 = new AEIident(string2, expression2);
			ad.setOutputAei(aeIident);
			ad.setInputAei(aeIident2);
			return ad;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
