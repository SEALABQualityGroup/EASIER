/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;

import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import specificheAEmilia.IdentExpr;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanIdentExpr {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanIdentExpr(int depth) 
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
	 * ad un identificatore presente all'interno di un'espressione.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isIdentExpr(String specifiche)
		{
		/* MODELLED */
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		if (!scanIdent.isIdent(specifiche))
			{
			String string = specifiche + " is not expression identifier";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = scanIdent.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto IdentExpr, ottenuto attraverso la
	 * scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto IdentExpr.
	 * @throws ScanException
	 */
	public IdentExpr scanIdentExpr(String specifiche)
		throws ScanException
		{
		try {
			// id
			IdentExpr ie = new IdentExpr();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			String identificatore = scanIdent.scanIdent(specifiche);
			ie.setNome(identificatore);
			return ie;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
