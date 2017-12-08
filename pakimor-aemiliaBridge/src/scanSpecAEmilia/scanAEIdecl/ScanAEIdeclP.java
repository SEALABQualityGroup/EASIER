/**
 * 
 */
package scanSpecAEmilia.scanAEIdecl;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIident;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanAEIdeclP {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanAEIdeclP(int depth) 
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
	 * Restituisce true se e solo se specifichee'una dichiarazione
	 * di un'istanza di elemento architetturale semplice secondo
	 * la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isAEIdeclP(String specifiche)
		{
		/*
		 * <AEI_decl> ::= <identifier> ["[" <expr> "]"] ":" <identifier> "(" <pe_expr_sequence> ")"
		 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
		 * <identifier> "[" <expr> "]" ":" <identifier> "(" <pe_expr_sequence> ")"
		 */
		MyScanner s = new MyScanner(specifiche);
		String i = new String();
		String e1 = new String();
		String it = new String();
		String se = new String();
		s.useDelimiter("\\s*\\:\\s*");
		// i contiene il nome dell'AEI con o senza selettore
		try {
			i = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of simple architectural element instance";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "architectural element instance identifier expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		if (i.contains("["))
			{
			MyScanner s1 = new MyScanner(i);
			s1.useDelimiter("\\s*\\[\\s*");
			try {
				i = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not declaration of simple architectural element instance";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "architectural element instance identifier expected";
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
				String string = specifiche + " is not declaration of simple architectural element instance";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "\"[\" expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;					
				}
			try {
				// e1 contiene il selettore di AEI
				e1 = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not declaration of simple architectural element instance";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "selector expression expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;					
				}
			}
		s.useDelimiter("\\s*\\(\\s*");
		try {
			s.skip("\\s*\\:\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of simple architectural element instance";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\":\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;				
			}
		// it contiene il nome dell'AET
		try {
			it = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of simple architectural element instance";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "architectural element type identifier expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\)\\s*\\z");
		try {
			s.skip("\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of simple architectural element instance";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"(\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;				
			}
		// la sequenza di parametri attuali per istanziare
		// un AET puo' essere vuota
		if (s.hasNext())
			{
			se = s.next();
			}
		s.useDelimiter("\\s*\\z");
		try {
			s.skip("\\s*\\)\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of simple architectural element instance";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\")\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;				
			}
		// il selettore puo' non esserci
		if (e1.equals(""))
			{
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanIdent scanIdent2 = new ScanIdent(this.depth + 1);
			if (!scanIdent.isIdent(i))
				{
				String string = specifiche + " is not declaration of simple architectural element instance";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanIdent.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			if (!scanIdent2.isIdent(it))
				{
				String string = specifiche + " is not declaration of simple architectural element instance";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanIdent2.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			ScanExprSeq scanExprSeq = new ScanExprSeq(this.depth + 1);
			if (!scanExprSeq.isExprSeq(se))
				{
				String string = specifiche + " is not declaration of simple architectural element instance";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scanExprSeq.getErrorMessage();
				list.add(errorMessage);
				return false;
				}
			}
		else
			{
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanIdent scanIdent2 = new ScanIdent(this.depth + 1);
			ScanExp scanExp = new ScanExp(this.depth + 1);
			ScanExprSeq scanExprSeq = new ScanExprSeq(this.depth + 1);
			if (!scanIdent.isIdent(i))
				{
				String string = specifiche + " is not declaration of simple architectural element instance";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanIdent.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			if (!scanIdent2.isIdent(it))
				{
				String string = specifiche + " is not declaration of simple architectural element instance";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanIdent2.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			if (!scanExprSeq.isExprSeq(se))
				{
				String string = specifiche + " is not declaration of simple architectural element instance";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scanExprSeq.getErrorMessage();
				list.add(errorMessage);
				return false;
				}
			if (!scanExp.isEspressione(e1))
				{
				String string = specifiche + " is not declaration of simple architectural element instance";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage3 = scanExp.getErrorMessage();
				list.add(errorMessage3);
				return false;
				}
			}
		return true;
		}

	/**
	 * Restituisce un oggetto AEIdecl con le stesse informazioni
	 * contenute in specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto AEIdecl.
	 * @throws ScanException
	 */
	public AEIdecl scanAEIdeclP(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * <AEI_decl> ::= <identifier> ["[" <expr> "]"] ":" <identifier> "(" <pe_expr_sequence> ")"
			 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
			 * <identifier> "[" <expr> "]" ":" <identifier> "(" <pe_expr_sequence> ")"
			 */
			AEIdecl ad = new AEIdecl();
			MyScanner s = new MyScanner(specifiche);
			String i = new String();
			String e1 = new String();
			String it = new String();
			String se = new String();
			s.useDelimiter("\\s*\\:\\s*");
			// i contiene il nome dell'AEI con o senza selettore
			i = s.next();
			if (i.contains("[")) {
				MyScanner s1 = new MyScanner(i);
				s1.useDelimiter("\\s*\\[\\s*");
				i = s1.next();
				s1.useDelimiter("\\s*\\]\\s*");
				s1.skip("\\s*\\[\\s*");
				// e1 contiene il selettore di AEI
				e1 = s1.next();
			}
			s.useDelimiter("\\s*\\(\\s*");
			s.skip("\\s*\\:\\s*");
			// it contiene il nome dell'AET
			it = s.next();
			s.useDelimiter("\\s*\\)\\s*\\z");
			s.skip("\\s*\\(\\s*");
			// la sequenza di parametri attuali per istanziare
			// un AET puà essere vuota
			if (s.hasNext())
				se = s.next();
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*\\)\\s*");
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanExprSeq scanExprSeq = new ScanExprSeq(this.depth + 1);
			String string = scanIdent.scanIdent(i);
			Expression expression = null;
			ad.setAET(scanIdent.scanIdent(it));
			ad.setActualParams(scanExprSeq.scanExprSeq(se));
			// il selettore puo' non esserci
			ScanExp scanExp = new ScanExp(this.depth + 1);
			if (!e1.equals(""))
				expression = scanExp.scanEspressione(e1);
			AEIident aeIident = new AEIident(string, expression);
			ad.setAeIident(aeIident);
			return ad;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
