/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanEb {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanEb(int depth) 
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
	 * ad un'operazione booleana.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isEb(String specifiche)
		{
		/* MODELLED */
		/*
		 * Eb -> Eb "&&" Ebt | Eb "||" Ebt | Ebt
		 */
		ScanAnd scanAnd = new ScanAnd(this.depth + 1);
		ScanOr scanOr = new ScanOr(this.depth + 1);
		ScanEbt scanEbt = new ScanEbt(this.depth + 1);
		if (!(scanAnd.isAnd(specifiche) || scanOr.isOr(specifiche) ||
			 	scanEbt.isEbt(specifiche)))
			{
			String string = specifiche + " is not boolean expression";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage1 = scanAnd.getErrorMessage();
			ErrorMessage errorMessage2 = scanOr.getErrorMessage();
			ErrorMessage errorMessage3 = scanEbt.getErrorMessage();
			list.add(errorMessage1);
			list.add(errorMessage2);
			list.add(errorMessage3);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Expression, ottenuto attraverso la
	 * scannerizzazione di un'espressione booleana.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Expression.
	 * @throws ScanException
	 */
	public Expression scanEb(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Eb -> Eb "&&" Ebt | Eb "||" Ebt | Ebt
			 */
			ScanAnd scanAnd = new ScanAnd(this.depth + 1);
			ScanOr scanOr = new ScanOr(this.depth + 1);
			ScanEbt scanEbt = new ScanEbt(this.depth + 1);
			if (scanAnd.isAnd(specifiche))
				return scanAnd.scanAnd(specifiche);
			else if (scanOr.isOr(specifiche))
				return scanOr.scanOr(specifiche);
			else if (scanEbt.isEbt(specifiche))
				return scanEbt.scanEbt(specifiche);
			else
				return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
