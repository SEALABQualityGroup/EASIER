/**
 * 
 */
package scanSpecAEmilia.scanProcessTerm;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanBehavProcess {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanBehavProcess(int depth) 
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
	 * ad una chiamata di comportamento.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isBehavProcess(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\(\\s*");
		String st = null;
		try {
			st = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not behavior call";
			this.errorMessage.setErrorMessage(string);
			String string2 = "identifier expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\)\\s*\\z");
		try {
			s.skip("\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not behavior call";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"(\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		String aps = null;
		if (s.hasNext())
			{
			aps = s.next();
			}
		try {
			s.skip("\\s*\\)\\s*");
			}
		catch (NoSuchElementException suchElementException)
			{
			String string = specifiche + " is not behavior call";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\")\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		ScanActParSeq scanActParSeq = new ScanActParSeq(this.depth + 1);
		if (!scanIdent.isIdent(st))
			{
			String string = specifiche + " is not behavior call";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// i parametri attuali possono non esserci
		if (aps != null)
			{
			if (!scanActParSeq.isActParSeq(aps))
				{
				String string = specifiche + " is not behavior call";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage = scanActParSeq.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			}
		return true;
		}

	/**
	 * Crea un oggetto BehavProcess, includendo informazioni
	 * ottenute attraverso la scannerizzazione di una
	 * chiamata di comportamento.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto BehavProcess.
	 * @throws ScanException
	 */
	public BehavProcess scanBehavProcess(String specifiche)
		throws ScanException
		{
		try {
			// <identifier> "(" <actual_par_sequence> ")"
			BehavProcess bp = new BehavProcess();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\(\\s*");
			String st = s.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			String i = scanIdent.scanIdent(st);
			bp.setName(i);
			s.useDelimiter("\\s*\\)\\s*\\z");
			s.skip("\\s*\\(\\s*");
			bp.setExprs(new Expression[0]);
			if (s.hasNext()) {
				String aps = s.next();
				ScanActParSeq scanActParSeq = new ScanActParSeq(this.depth + 1);
				bp.setExprs(scanActParSeq.scanActParSeq(aps));
			}
			bp.setCondition(new specificheAEmilia.Boolean(true));
			return bp;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
