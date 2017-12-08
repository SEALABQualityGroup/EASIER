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
class ScanEat {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanEat(int depth) 
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
	 * ad un'operazione di moltiplicazione, divisione, o
	 * termine di un'espressione aritmetica.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isEat(String specifiche)
		{
		/* MODELLED */
		/*
		 * Eat -> Eat "*" Eatf | Eat "/" Eatf | Eatf
		 */
		ScanDivisione scanDivisione = new ScanDivisione(this.depth + 1);
		ScanMoltiplicazione scanMoltiplicazione = new ScanMoltiplicazione(this.depth + 1);
		ScanEatf scanEatf = new ScanEatf(this.depth + 1);
		if (!(scanDivisione.isDivisione(specifiche) ||
				scanMoltiplicazione.isMoltiplicazione(specifiche)
				|| scanEatf.isEatf(specifiche)))
			{
			String string = specifiche + " is not arithmetic expression term";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanDivisione.getErrorMessage();
			ErrorMessage errorMessage2 = scanMoltiplicazione.getErrorMessage();
			ErrorMessage errorMessage3 = scanEatf.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			list.add(errorMessage3);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Expression, ottenuto attraverso la
	 * scannerizzazione di un termine di un'espressione
	 * aritmetica.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Expression.
	 * @throws ScanException
	 */
	public Expression scanEat(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Eat -> Eat "*" Eatf | Eat "/" Eatf | Eatf
			 */
			ScanMoltiplicazione scanMoltiplicazione = new ScanMoltiplicazione(this.depth + 1);
			ScanDivisione scanDivisione = new ScanDivisione(this.depth + 1);
			ScanEatf scanEatf = new ScanEatf(this.depth + 1);
			if (scanMoltiplicazione.isMoltiplicazione(specifiche))
				return scanMoltiplicazione.scanMoltiplicazione(specifiche);
			else if (scanDivisione.isDivisione(specifiche))
				return scanDivisione.scanDivisione(specifiche);
			else if (scanEatf.isEatf(specifiche))
				return scanEatf.scanEatf(specifiche);
			else
				return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
