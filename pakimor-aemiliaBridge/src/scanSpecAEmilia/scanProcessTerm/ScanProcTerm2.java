/**
 * 
 */
package scanSpecAEmilia.scanProcessTerm;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanProcTerm2 {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanProcTerm2(int depth) 
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
	 * ad un termine di processo la cui esecuzione puo' essere
	 * condizionata da un'espressione.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isProcTerm2(String specifiche)
		{
		/* MODELLED */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\-\\s*\\>\\s*"); 
		if (s.hasNext("\\s*cond\\s*\\((.)*\\)\\s*"))
			{
			s.skip("\\s*cond\\s*\\(\\s*");
			s.useDelimiter("\\s*\\)\\s*\\-\\s*\\>\\s*");
			String e = null;
			try {
				e = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not conditional process term";
				this.errorMessage.setErrorMessage(string);
				String string2 = "expression expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			try {
				s.skip("\\s*\\)\\s*\\-\\s*\\>\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not conditional process term";
				this.errorMessage.setErrorMessage(string);
				String string2 = "\")->\" expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			ScanExp scanExp = new ScanExp(this.depth + 1);
			if (!scanExp.isEspressione(e))
				{
				String string = specifiche + " is not contitional process term";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage = scanExp.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			}
		s.useDelimiter("\\s*\\z");
		String st = null;
		try {
			st = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not conditional process term";
			this.errorMessage.setErrorMessage(string);
			String string2 = "process term expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanProcessTerm scanProcessTerm = new ScanProcessTerm(this.depth + 1);
		if (!scanProcessTerm.isProcessTerm(st))
			{
			String string = specifiche + " is not contitional process term";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanProcessTerm.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	
	/**
	 * Crea un oggetto ProcessTerm, includendo informazioni
	 * ottenute attraverso la scannerizzazione di un termine
	 * di processo la cui esecuzione puo' essere condizionata
	 * da un'espressione.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto ProcessTerm.
	 * @throws ScanException
	 */
	public ProcessTerm scanProcTerm2(String specifiche)
		throws ScanException
		{
		try {
			// <process_term_2> ::= ["cond" "(" <expr> ")" "->"] <process_term>
			ProcessTerm pt;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\-\\s*\\>\\s*");
			if (s.hasNext("\\s*cond\\s*\\((.)*\\)\\s*")) {
				s.skip("\\s*cond\\s*\\(\\s*");
				s.useDelimiter("\\s*\\)\\s*\\-\\s*\\>\\s*");
				String e = s.next();
				s.skip("\\s*\\)\\s*\\-\\s*\\>\\s*");
				s.useDelimiter("\\s*\\z");
				String st = s.next();
				ScanProcessTerm scanProcessTerm = new ScanProcessTerm(this.depth + 1);
				pt = scanProcessTerm.scanProcessTerm(st);
				ScanExp scanExp = new ScanExp(this.depth + 1);
				pt.setCondition(scanExp.scanEspressione(e));
				return pt;
			}
			s.useDelimiter("\\s*\\z");
			String st = s.next();
			ScanProcessTerm scanProcessTerm = new ScanProcessTerm(this.depth + 1);
			return scanProcessTerm.scanProcessTerm(st);
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
