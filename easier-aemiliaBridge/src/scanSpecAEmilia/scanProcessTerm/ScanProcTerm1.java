/**
 * 
 */
package scanSpecAEmilia.scanProcessTerm;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanProcTerm1 {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanProcTerm1(int depth) 
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
	 * ad un termine di processo o alla chiamata di un
	 * comportamento.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isProcTerm1(String specifiche)
		{
		ScanProcessTerm scanProcessTerm = new ScanProcessTerm(this.depth + 1);
		ScanBehavProcess scanBehavProcess = new ScanBehavProcess(this.depth + 1);
		if (!(scanProcessTerm.isProcessTerm(specifiche) || scanBehavProcess.isBehavProcess(specifiche)))
			{
			String string = specifiche + " is not process term";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanBehavProcess.getErrorMessage();
			ErrorMessage errorMessage2 = scanProcessTerm.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			return false;
			}
		return true;
		}


	/**
	 * Crea un oggetto ProcessTerm, includendo informazioni
	 * ottenute attraverso la scannerizzazione di un termine
	 * di processo o di una chiamata di un comportamento.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto ProcessTerm.
	 * @throws ScanException
	 */
	public ProcessTerm scanProcTerm1(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * <process_term_1> ::= <process_term>
			 * | <identifier> "(" <actual_par_sequence> ")"
			 */
			ProcessTerm pt;
			ScanProcessTerm scanProcessTerm = new ScanProcessTerm(this.depth + 1);
			ScanBehavProcess scanBehavProcess = new ScanBehavProcess(this.depth + 1);
			if (scanProcessTerm.isProcessTerm(specifiche))
				pt = scanProcessTerm.scanProcessTerm(specifiche);
			else if (scanBehavProcess.isBehavProcess(specifiche))
				pt = scanBehavProcess.scanBehavProcess(specifiche);
			else
				return null;
			return pt;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
