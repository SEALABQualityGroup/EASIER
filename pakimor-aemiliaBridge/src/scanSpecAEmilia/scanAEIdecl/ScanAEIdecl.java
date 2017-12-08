package scanSpecAEmilia.scanAEIdecl;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.AEIdecl;
import utilities.ErrorMessage;

/**
 * Classe utilizzata per scannerizzare ogni parte di una
 * dichiarazione di istanza di un elemento architetturale,
 * dettata dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 * <AEI_decl> ::= <identifier> ["[" <expr> "]"] ":" <identifier> "(" <pe_expr_sequence> ")"
 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * <identifier> "[" <expr> "]" ":" <identifier> "(" <pe_expr_sequence> ")"
 *
 * Nella sua forma più semplice, una dichiarazione AEI contiene
 * l'identificatore di un AEI, un'espressione intera racchiusa tra
 * parentesi quadre, che rappresenta un selettore e deve essere
 * libero da identificatori non dichiarati e invocazioni a
 * generatori di numeri pseudo-casuali, l'identificatore dell'AET
 * relativo, che deve essere stato definito nella prima sezione
 * della specifica AEmilia, e una sequenza possibilmente vuota di
 * espressioni libere da invocazioni a generatori di numeri
 * pseudo-casuali, che forniscono i valori attuali per i parametri
 * formali costanti dell'AET. I soli identificatori che si possono
 * presentare nell'espressione di selezione e nei parametri
 * attuali sono i parametri formali costanti dichiarati
 * nell'intestazione del tipo architetturale.
 * La seconda formae'utile per dichiarare in maniera concisa
 * diverse istanze dello stesso AET attraverso un meccanismo di
 * indicizzazione. Questa richiede la specifica
 * dell'identificatore indice, che si puo' presentare
 * nell'espressione di selezione e nei parametri attuali, insieme
 * con il suo intervallo, chee'dato da due espressioni intere.
 * Queste due espressioni devono essere libere da identificatori
 * non dichiarati e invocazioni a generatori di numeri
 * pseudo-casuali, con il valore della prima espressione non più
 * grande del valore della seconda espressione. Anche nella forma
 * più semplice di una dichiarazione di AEI l'identificatore
 * di un AEI puo' essere aumentato con un'espressione di selezione.
 * Questoe'utile ogni voltae'desiderabile dichiarare un insieme
 * di istanze indicizzate dello stesso AET, ma solo qualcuna di
 * loro ha un'espressione di selezione comune.
 */

public class ScanAEIdecl {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanAEIdecl(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifichee'una dichiarazione
	 * di istanza di elemento architetturale secondo la
	 * grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isAEIdecl(String specifiche)
		{
		/*
		 * <AEI_decl> ::= <identifier> ["[" <expr> "]"] ":" <identifier> "(" <pe_expr_sequence> ")"
		 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
		 * <identifier> "[" <expr> "]" ":" <identifier> "(" <pe_expr_sequence> ")"
		 */
		ScanAEIdeclP scanAEIdeclP = new ScanAEIdeclP(this.depth + 1);
		ScanAEIdeclInd scanAEIdeclInd = new ScanAEIdeclInd(this.depth + 1);
		if (!(scanAEIdeclP.isAEIdeclP(specifiche) || scanAEIdeclInd.isAEIdeclInd(specifiche)))
			{
			String string = specifiche + " is not architectural element instance declaration";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanAEIdeclP.getErrorMessage();
			ErrorMessage errorMessage2 = scanAEIdeclInd.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			return false;
			}
		else 
			return true;
		}

	/**
	 * Crea un oggetto AEIdecl scannerizzando specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto AEIdecl.
	 * @throws ScanException
	 */
	public AEIdecl scanAEIdecl(String specifiche)
		throws ScanException
		{
		try {
			AEIdecl d = new AEIdecl();
			ScanAEIdeclP scanAEIdeclP = new ScanAEIdeclP(this.depth + 1);
			ScanAEIdeclInd scanAEIdeclInd = new ScanAEIdeclInd(this.depth + 1);
			if (scanAEIdeclP.isAEIdeclP(specifiche))
				d = scanAEIdeclP.scanAEIdeclP(specifiche);
			else if (scanAEIdeclInd.isAEIdeclInd(specifiche))
				d = scanAEIdeclInd.scanAEIdeclInd(specifiche);
			return d;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}