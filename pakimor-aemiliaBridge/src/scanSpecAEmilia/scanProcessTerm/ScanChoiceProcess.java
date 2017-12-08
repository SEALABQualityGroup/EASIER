/**
 * 
 */
package scanSpecAEmilia.scanProcessTerm;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ChoiceProcess;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanChoiceProcess {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanChoiceProcess(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}


	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}


	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad una scelta condizionata tra termini di processo.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isChoiceProcess(String specifiche)
		{
		/* MODELLED */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\{\\s*");
		try {
			s.next("\\s*choice\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not choice process";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"choice\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\}\\s*\\z");
		try {
			s.skip("\\s*\\{\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not choice process";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"{\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		String pt2s = null;
		try {
			pt2s = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{	
			String string = specifiche + " is not choice process";
			this.errorMessage.setErrorMessage(string);
			String string2 = "alternative processes expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		try {
			s.skip("\\s*\\}\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not choice process";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"}\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanProcTerm2Seq scanProcTerm2Seq = new ScanProcTerm2Seq(this.depth + 1);
		if (!scanProcTerm2Seq.isProcTerm2Seq(pt2s))
			{
			String string = specifiche + " is not choice process";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanProcTerm2Seq.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}


	/**
	 * Crea un oggetto ChoiceProcess, includendo informazioni
	 * ottenute attraverso la scannerizzazione di una
	 * scelta tra termini di processo.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto ChoiceProcess.
	 * @throws ScanException
	 */
	public ChoiceProcess scanChoiceProcess(String specifiche) throws ScanException
		{
		try {
			// "choice" "{" <process_term_2_sequence> "}"
			ChoiceProcess cp = new ChoiceProcess();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\}\\s*\\z");
			s.skip("\\s*choice\\s*\\{\\s*");
			String st = s.next();
			ScanProcTerm2Seq scanProcTerm2Seq = new ScanProcTerm2Seq(this.depth + 1);
			cp.setProcesses(scanProcTerm2Seq.scanProcTerm2Seq(st));
			cp.setCondition(new specificheAEmilia.Boolean(true));
			return cp;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
