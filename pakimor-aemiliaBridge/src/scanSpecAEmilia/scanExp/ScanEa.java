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
class ScanEa {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanEa(int depth) 
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
	 * ad un'operazione aritmetica.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isEa(String specifiche)
		{
		/* MODELLED */
		/*
		 * Ea -> Ea "+" Eat | Ea "e'" Eat | Eat
		 */
		ScanSomma scanSomma = new ScanSomma(this.depth + 1);
		ScanSottrazione scanSottrazione = new ScanSottrazione(this.depth + 1);
		ScanEat scanEat = new ScanEat(this.depth + 1);
		if (!(scanSomma.isSomma(specifiche) || scanSottrazione.isSottrazione(specifiche)
				|| scanEat.isEat(specifiche)))
			{
			String string = specifiche + " is not arithmetic expression";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = errorMessage.getCauses();
			ErrorMessage errorMessage = scanSomma.getErrorMessage();
			ErrorMessage errorMessage2 = scanSottrazione.getErrorMessage();
			ErrorMessage errorMessage3 = scanEat.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			list.add(errorMessage3);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Expression, ottenuto attraverso la
	 * scannerizzazione di un'espressione aritmetica.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Expression.
	 * @throws ScanException
	 */
	public Expression scanEa(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Ea -> Ea "+" Eat | Ea "e'" Eat | Eat
			 */
			ScanSomma scanSomma = new ScanSomma(this.depth + 1);
			ScanSottrazione scanSottrazione = new ScanSottrazione(this.depth + 1);
			ScanEat scanEat = new ScanEat(this.depth + 1);
			if (scanSomma.isSomma(specifiche))
				return scanSomma.scanSomma(specifiche);
			else if (scanSottrazione.isSottrazione(specifiche))
				return scanSottrazione.scanSottrazione(specifiche);
			else if (scanEat.isEat(specifiche))
				return scanEat.scanEat(specifiche);
			else
				return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
