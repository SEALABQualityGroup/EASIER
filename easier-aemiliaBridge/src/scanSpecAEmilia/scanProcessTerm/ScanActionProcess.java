/**
 * 
 */
package scanSpecAEmilia.scanProcessTerm;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanAction;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ActionProcess;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanActionProcess {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanActionProcess(int depth) 
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
	 * ad un'azione.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isActionProcess(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\>\\s*\\.\\s*");
		String a = null;
		try {
			a = s.next()+">";
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not action process";
			this.errorMessage.setErrorMessage(string);
			String string2 = "action expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\z");
		try {
			s.skip("\\s*\\>\\s*\\.\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not action process";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\">\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		String pt1 = null;
		try {
			pt1 = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not action process";
			this.errorMessage.setErrorMessage(string);
			String string2 = "process expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanAction scanAction = new ScanAction(this.depth + 1);
		ScanProcTerm1 scanProcTerm1 = new ScanProcTerm1(this.depth + 1);
		if (!scanAction.isAction(a))
			{	
			String string = specifiche + " is not action process";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanAction.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (!scanProcTerm1.isProcTerm1(pt1))
			{
			String string = specifiche + " is not action process";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanProcTerm1.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ActionProcess, includendo informazioni
	 * ottenute attraverso la scannerizzazione di un'azione.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto ActionProcess.
	 * @throws ScanException
	 */
	public ActionProcess scanActionProcess(String specifiche)
		throws ScanException
		{
		try {
			// <action> "." <process_term_1>
			ActionProcess ap = new ActionProcess();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\>\\s*\\.\\s*");
			String a = s.next() + ">";
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*\\>\\s*\\.\\s*");
			String pt1 = s.next();
			ScanAction scanAction = new ScanAction(this.depth + 1);
			ScanProcTerm1 scanProcTerm1 = new ScanProcTerm1(this.depth + 1);
			ap.setCondition(new specificheAEmilia.Boolean(true));
			ap.setAzione(scanAction.scanAction(a));
			ap.setProcesso(scanProcTerm1.scanProcTerm1(pt1));
			return ap;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
