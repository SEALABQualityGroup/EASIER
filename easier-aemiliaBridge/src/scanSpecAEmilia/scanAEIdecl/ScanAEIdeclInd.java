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
import specificheAEmilia.AEIdeclInd;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanAEIdeclInd {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanAEIdeclInd(int depth) 
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
	 * Restituisce true se e solo se specifiche indica una
	 * dichiarazione di istanze di elementi architetturali
	 * indicizzata.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isAEIdeclInd(String specifiche)
		{
		/*
		 * <AEI_decl> ::= <identifier> ["[" <expr> "]"] ":" <identifier> "(" <pe_expr_sequence> ")"
		 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
		 * <identifier> "[" <expr> "]" ":" <identifier> "(" <pe_expr_sequence> ")"
		 */
		MyScanner s = new MyScanner(specifiche);
		String ind = new String();
		String ei = new String();
		String ef = new String();
		String aei = new String();
		try {
			s.skip("\\s*FOR_ALL\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"FOR_ALL\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		// ind contiene il nome dell'indice
		try {
			ind = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "index identifier expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		try {
			s.skip("\\s*IN\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"IN\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\.\\.\\s*");
		// ei contiene l'espressione iniziale
		try {
			ei = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "beginning expression expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*\\[");
		try {
			s.skip("\\s*\\.\\.\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"..\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		// ef contiene l'espressione finale
		try {
			ef = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "ending expression expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\z");
		// aei contiene il nome di un AET con una sequenza
		// di parametri attuali
		try {
			aei = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "architectural element expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		ScanExp scanExp = new ScanExp(this.depth + 1);
		ScanExp scanExp2 = new ScanExp(this.depth + 1);
		if (!scanIdent.isIdent(ind))
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = scanIdent.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		if (!scanExp.isEspressione(ei))
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = scanExp.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		if (!scanExp2.isEspressione(ef))
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = scanExp.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		ScanAEIdeclP scanAEIdeclP = new ScanAEIdeclP(this.depth + 1);
		if (!scanAEIdeclP.isAEIdeclP(aei))
			{
			String string = specifiche + " is not indexed declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage3 = scanAEIdeclP.getErrorMessage();
			list.add(errorMessage3);
			return false;
			}
		return true;
		}

	/**
	 * Restituisce un oggetto AEIdeclInd generato dalla scansione
	 * di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto AEIdeclInd.
	 * @throws ScanException
	 */
	public AEIdeclInd scanAEIdeclInd(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * <AEI_decl> ::= <identifier> ["[" <expr> "]"] ":" <identifier> "(" <pe_expr_sequence> ")"
			 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
			 * <identifier> "[" <expr> "]" ":" <identifier> "(" <pe_expr_sequence> ")"
			 */
			AEIdeclInd adi = new AEIdeclInd();
			MyScanner s = new MyScanner(specifiche);
			String ind = new String();
			String ei = new String();
			String ef = new String();
			String aei = new String();
			AEIdecl decl = new AEIdecl();
			s.skip("\\s*FOR_ALL\\s*");
			// ind contiene il nome dell'indice
			ind = s.next();
			s.skip("\\s*IN\\s*");
			s.useDelimiter("\\s*\\.\\.\\s*");
			// ei contiene l'espressione iniziale
			ei = s.next();
			s.useDelimiter("\\s*[a-zA-Z_&&[^0-9]](\\w*)\\s*\\[");
			s.skip("\\s*\\.\\.\\s*");
			// ef contiene l'espressione finale
			ef = s.next();
			s.useDelimiter("\\s*\\z");
			// aei contiene il nome di un AET con una sequenza
			// di parametri attuali
			aei = s.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			adi.setIndex(scanIdent.scanIdent(ind));
			ScanExp scanExp = new ScanExp(this.depth + 1);
			adi.setBeginningExpr(scanExp.scanEspressione(ei));
			adi.setEndingExpr(scanExp.scanEspressione(ef));
			ScanAEIdeclP scanAEIdeclP = new ScanAEIdeclP(this.depth + 1);
			decl = scanAEIdeclP.scanAEIdeclP(aei);
			adi.setAeIident(decl.getAeIident());
			adi.setActualParams(decl.getActualParams());
			adi.setAET(decl.getAET());
			return adi;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
