/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.WeightType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanWeightType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanWeightType(int depth) 	
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
	 * al tipo di dato weight.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isWeightType(String specifiche)
		{
		// "weight"
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*weight\\s*"))
			{
			String string = specifiche + "  is not weight type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"weight\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Weight attraverso la scannerizzazione
	 * di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Weight.
	 * @throws ScanException
	 */
	public WeightType scanWeightType(String specifiche)
		throws ScanException
		{
		WeightType w = new WeightType();
		return w;
		}
}
