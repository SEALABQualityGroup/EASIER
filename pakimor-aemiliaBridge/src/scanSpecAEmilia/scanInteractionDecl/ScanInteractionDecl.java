package scanSpecAEmilia.scanInteractionDecl;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.InteractionDecl;
import utilities.ErrorMessage;


/**
 * Classe utilizzata per scannerizzare ogni parte di una
 * dichiarazione di interazione architetturale, dettata
 * dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

/*
 * Una dichiarazione di interazione architetturale ha la seguente
 * forma:
 *
 * <architectural_interaction_decl> ::=
 * <identifier> ["[" <expr> "]"] "." <identifier>
 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * <identifier> "[" <expr> "]" "." <identifier>
 *
 * Nella sua forma piu' semplice, una dichiarazione di interazione
 * architetturale contiene l'identificatore di un AEI a cui
 * l'interazione appartiene, una possibile espressione intera
 * racchiusa tra parentesi quadre, che rappresenta un selettore e
 * deve essere privo di identificatori non dichiarati e invocazioni
 * a generatori di numeri pseudo-casuali, e l'identificatore
 * dell'interazione. I soli identificatori che si possono
 * presentare nella possibile espressione di selezione sono
 * quelli dei parametri formali costanti dichiarati
 * nell'intestazione del tipo architetturale.
 * La seconda formae'utile per dichiarare in modo conciso diverse
 * interazioni architetturali attraverso un meccanismo di
 * indicizzazione. Questo richiede la specifica
 * dell'identificatore indice, che si puo' successivamente
 * presentare nell'espressione di selezione, insieme con il suo
 * intervallo, chee'dato da due espressioni intere.
 * Queste due espressioni devono essere prive da identificatori
 * non dichiarati e invocazioni a generatori di numeri
 * pseudo-casuali, con il valore della prima espressione
 * non piu' grande del valore della seconda espressione
 */

public class ScanInteractionDecl {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanInteractionDecl(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad una dichiarazione di interazione architetturale
	 * secondo la grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */

	public boolean isInteractionDecl(String specifiche)
		{
		/*
		 * Una dichiarazione di interazione architetturale ha la
		 * seguente forma:
		 *
		 * <architectural_interaction_decl> ::=
		 * <identifier> ["[" <expr> "]"] "."
		 * <identifier> |
		 * "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
		 * <identifier> "[" <expr> "]" "." <identifier>
		 */
		ScanInteractionDeclP scanInteractionDeclP = new ScanInteractionDeclP(this.depth + 1);
		ScanInteractionDeclInd scanInteractionDeclInd = new ScanInteractionDeclInd(this.depth + 1);
		if (!(scanInteractionDeclP.isInteractionDeclP(specifiche) ||
				scanInteractionDeclInd.isInteractionDeclInd(specifiche)))
			{
			String string = specifiche + " is not declaration of architectural declaration";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanInteractionDeclP.getErrorMessage();
			ErrorMessage errorMessage2 = scanInteractionDeclInd.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto InteractionDecl, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto InteractionDecl.
	 * @throws ScanException
	 */

	public InteractionDecl scanInteractionDecl(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Una dichiarazione di interazione architetturale ha la
			 * seguente forma:
			 *
			 * <architectural_interaction_decl> ::=
			 * <identifier> ["[" <expr> "]"] "."
			 * <identifier> |
			 * "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
			 * <identifier> "[" <expr> "]" "." <identifier>
			 */
			ScanInteractionDeclP scanInteractionDeclP = new ScanInteractionDeclP(this.depth + 1);
			ScanInteractionDeclInd scanInteractionDeclInd = new ScanInteractionDeclInd(this.depth + 1);
			InteractionDecl id = null;
			if (scanInteractionDeclP.isInteractionDeclP(specifiche))
				id = scanInteractionDeclP.scanInteractionDeclP(specifiche);
			else if (scanInteractionDeclInd.isInteractionDeclInd(specifiche))
				id = scanInteractionDeclInd.scanInteractionDeclInd(specifiche);
			else
				return null;
			return id;
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