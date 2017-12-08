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
class ScanEbt {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanEbt(int depth) 
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
	 * a termini booleani di un'espressione booleana.
	 *
	 * @param specifiche - oggetto String.
	 * @param errorMessage
	 * @return un valore booleano.
	 */
	public boolean isEbt(String specifiche)
		{
		/* MODELLED */
		/*
		 * Ebt -> id | true | false | Ea "<" Ea | Ea "<=" Ea | Ea ">" Ea
		 * 		| Ea ">=" Ea | Ea "=" Ea | Ea "!=" Ea | "(" Eb ")" | "!"Ebt
		 */
		ScanBoolean scanBoolean = new ScanBoolean(this.depth + 1);
		ScanParEb scanParEb = new ScanParEb(this.depth + 1);
		ScanMinore scanMinore = new ScanMinore(this.depth + 1);
		ScanMinoreUguale scanMinoreUguale = new ScanMinoreUguale(this.depth + 1);
		ScanMaggiore scanMaggiore = new ScanMaggiore(this.depth + 1);
		ScanMaggioreUguale scanMaggioreUguale = new ScanMaggioreUguale(this.depth + 1);
		ScanUguale scanUguale = new ScanUguale(this.depth + 1);
		ScanDiverso scanDiverso = new ScanDiverso(this.depth + 1);
		ScanNegazione scanNegazione = new ScanNegazione(this.depth + 1);
		ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
		if (!(scanParEb.isParEb(specifiche) || scanBoolean.isBoolean(specifiche) ||
				scanMinore.isMinore(specifiche) || scanMinoreUguale.isMinoreUguale(specifiche) || 
				scanMaggiore.isMaggiore(specifiche) || scanMaggioreUguale.isMaggioreUguale(specifiche) ||
				scanUguale.isUguale(specifiche) || scanDiverso.isDiverso(specifiche) ||
				scanNegazione.isNegazione(specifiche) || scanIdentExpr.isIdentExpr(specifiche)))
			{
			String string = specifiche + " is not boolean expression term";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanBoolean.getErrorMessage();
			ErrorMessage errorMessage1 = scanParEb.getErrorMessage();
			ErrorMessage errorMessage2 = scanMinore.getErrorMessage();
			ErrorMessage errorMessage3 = scanMinoreUguale.getErrorMessage();
			ErrorMessage errorMessage4 = scanMaggiore.getErrorMessage();
			ErrorMessage errorMessage5 = scanMaggioreUguale.getErrorMessage();
			ErrorMessage errorMessage6 = scanUguale.getErrorMessage();
			ErrorMessage errorMessage7 = scanDiverso.getErrorMessage();
			ErrorMessage errorMessage8 = scanNegazione.getErrorMessage();
			ErrorMessage errorMessage9 = scanIdentExpr.getErrorMessage();
			list.add(errorMessage1);
			list.add(errorMessage2);
			list.add(errorMessage3);
			list.add(errorMessage4);
			list.add(errorMessage5);
			list.add(errorMessage6);
			list.add(errorMessage7);
			list.add(errorMessage8);
			list.add(errorMessage9);
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Expression, ottenuto attraverso la
	 * scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Expression.
	 * @throws ScanException
	 */
	public Expression scanEbt(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Ebt -> id | true | false | Ea "<" Ea | Ea "<=" Ea | Ea ">" Ea
			 * 		| Ea ">=" Ea | Ea "=" Ea | Ea "!=" Ea | "(" Eb ")" | "!"Ebt
			 */
			ScanBoolean scanBoolean = new ScanBoolean(this.depth + 1);
			ScanParEb scanParEb = new ScanParEb(this.depth + 1);
			ScanMinore scanMinore = new ScanMinore(this.depth + 1);
			ScanMinoreUguale scanMinoreUguale = new ScanMinoreUguale(this.depth + 1);
			ScanMaggiore scanMaggiore = new ScanMaggiore(this.depth + 1);
			ScanMaggioreUguale scanMaggioreUguale = new ScanMaggioreUguale(this.depth + 1);
			ScanUguale scanUguale = new ScanUguale(this.depth + 1);
			ScanDiverso scanDiverso = new ScanDiverso(this.depth + 1);
			ScanNegazione scanNegazione = new ScanNegazione(this.depth + 1);
			ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
			if (scanParEb.isParEb(specifiche))
				return scanParEb.scanParEb(specifiche);
			else if (scanIdentExpr.isIdentExpr(specifiche))
				return scanIdentExpr.scanIdentExpr(specifiche);
			else if (scanBoolean.isBoolean(specifiche))
				return scanBoolean.scanBoolean(specifiche);
			else if (scanMinore.isMinore(specifiche))
				return scanMinore.scanMinore(specifiche);
			else if (scanMinoreUguale.isMinoreUguale(specifiche))
				return scanMinoreUguale.scanMinoreUguale(specifiche);
			else if (scanMaggiore.isMaggiore(specifiche))
				return scanMaggiore.scanMaggiore(specifiche);
			else if (scanMaggioreUguale.isMaggioreUguale(specifiche))
				return scanMaggioreUguale.scanMaggioreUguale(specifiche);
			else if (scanUguale.isUguale(specifiche))
				return scanUguale.scanUguale(specifiche);
			else if (scanNegazione.isNegazione(specifiche))
				return scanNegazione.scanNegazione(specifiche);
			else if (scanDiverso.isDiverso(specifiche))
				return scanDiverso.scanDiverso(specifiche);
			else
				return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
