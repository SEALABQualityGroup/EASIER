/**
 * 
 */
package scanSpecAEmilia.scanIntestazione;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.ParamDeclaration;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanDeclPar {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanDeclPar(int depth) 
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
	 * ad una dichiarazione di parametro.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	/*
	 * "const" <data_type> <identifier> ":=" <expr>e'una
	 * dichiarazione di parametri formali costanti e inizializzati
	 * utilizzata nell'intestazione della definizione di un tipo
	 * architetturale.
	 *
	 * "const" <data_type> <identifier>e'una dichiarazione di
	 * parametro formale costante utilizzata nell'intestazione di
	 * un AET, il cui valoree'definito nella dichiarazione delle
	 * istanze dell'AET nella sezione di topologia architetturale.
	 *
	 * "local" <normal_type> <identifier> viene di solito
	 * utilizzata quando si sincronizza un'azione di input di
	 * un'istanza dell'AET con un'azione di output di un altro AEI.
	 * Tale dichiarazione viene utilizzata nell'intestazione di
	 * un'equazione comportamentale.
	 *
	 * <normal_type> <identifier> ":=" <expr> viene utilizzata
	 * nell'intestazione della prima equazione comportamentale e
	 * consiste nella dichiarazione di un parametro formale
	 * variabile inizializzata.
	 *
	 * <normal_type> <identifier> viene utilizzata nell'equazioni
	 * comportamentali susseguenti la prima e consiste nella
	 * dichiarazione di un parametro formale variabile. Nessuna
	 * espressione di inizializzazionee'necessaria per i parametri
	 * formali variabili di ogni equazione comportamentale
	 * susseguente, poich�, ad essi, saranno assegnati i valori
	 * dei parametri attuali contenuti nell'invocazioni
	 * dell'equazione comportamentale correlata.
	 */
	public boolean isDeclPar(String specifiche)
		{
		ScanConst scanConst = new ScanConst(this.depth + 1);
		ScanConstInit scanConstInit = new ScanConstInit(this.depth + 1);
		ScanLocal scanLocal = new ScanLocal(this.depth + 1);
		ScanVar scanVar = new ScanVar(this.depth + 1);
		ScanVarInit scanVarInit = new ScanVarInit(this.depth + 1);
		if (!(scanConst.isConst(specifiche) ||
			scanConstInit.isConstInit(specifiche) ||
			scanLocal.isLocal(specifiche) ||
			scanVar.isVar(specifiche) ||
			scanVarInit.isVarInit(specifiche)))
			{
			String string = specifiche + " is not parameter declaration";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanConst.getErrorMessage();
			ErrorMessage errorMessage2 = scanConstInit.getErrorMessage();
			ErrorMessage errorMessage3 = scanLocal.getErrorMessage();
			ErrorMessage errorMessage4 = scanVar.getErrorMessage();
			ErrorMessage errorMessage5 = scanVarInit.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			list.add(errorMessage3);
			list.add(errorMessage4);
			list.add(errorMessage5);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ParamDeclaration, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto ParamDeclaration.
	 * @throws ScanException
	 */
	public ParamDeclaration scanDeclPar(String specifiche)
		throws ScanException
		{
		try {
			ScanConst scanConst = new ScanConst(this.depth + 1);
			ScanConstInit scanConstInit = new ScanConstInit(this.depth + 1);
			ScanLocal scanLocal = new ScanLocal(this.depth + 1);
			ScanVar scanVar = new ScanVar(this.depth + 1);
			ScanVarInit scanVarInit = new ScanVarInit(this.depth + 1);
			ParamDeclaration dp = null;
			if (scanConst.isConst(specifiche))
				dp = scanConst.scanConst(specifiche);
			else if (scanConstInit.isConstInit(specifiche))
				dp = scanConstInit.scanConstInit(specifiche);
			else if (scanLocal.isLocal(specifiche))
				dp = scanLocal.scanLocal(specifiche);
			else if (scanVar.isVar(specifiche))
				dp = scanVar.scanVar(specifiche);
			else if (scanVarInit.isVarInit(specifiche))
				dp = scanVarInit.scanVarInit(specifiche);
			else
				return null;
			return dp;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
