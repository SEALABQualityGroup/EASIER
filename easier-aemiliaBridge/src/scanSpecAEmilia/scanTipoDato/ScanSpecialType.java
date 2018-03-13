/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.SpecialType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanSpecialType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanSpecialType(int depth) 
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
	 * ad un tipo di dato speciale, in accordo alla grammatica
	 * AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isSpecialType(String specifiche)
		{
		/*
		 * <special_type> ::= "prio"
		 * | "rate"
		 * | "weight"
		 */
		ScanPrioType scanPrioType = new ScanPrioType(this.depth + 1);
		ScanRateType scanRateType = new ScanRateType(this.depth + 1);
		ScanWeightType scanWeightType = new ScanWeightType(this.depth + 1);
		if (!(scanPrioType.isPrioType(specifiche) || 
				scanRateType.isRateType(specifiche) || 
				scanWeightType.isWeightType(specifiche)))
			{
			String string = specifiche + " is not special type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanPrioType.getErrorMessage();
			ErrorMessage errorMessage2 = scanRateType.getErrorMessage();
			ErrorMessage errorMessage3 = scanWeightType.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			list.add(errorMessage3);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto SpecialType attraverso la scannerizzazione
	 * di specifiche, in accordo alla grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto SpecialType.
	 * @throws ScanException
	 */
	public SpecialType scanSpecialType(String specifiche)
		throws ScanException
		{
		SpecialType st;
		ScanPrioType scanPrioType = new ScanPrioType(this.depth + 1);
		ScanRateType scanRateType = new ScanRateType(this.depth + 1);
		ScanWeightType scanWeightType = new ScanWeightType(this.depth + 1);
		if (scanPrioType.isPrioType(specifiche)) st = scanPrioType.scanPrioType(specifiche);
		else if (scanRateType.isRateType(specifiche)) st = scanRateType.scanRateType(specifiche);
		else if (scanWeightType.isWeightType(specifiche)) st = scanWeightType.scanWeightType(specifiche);
		else return null;
		return st;
		}
}
