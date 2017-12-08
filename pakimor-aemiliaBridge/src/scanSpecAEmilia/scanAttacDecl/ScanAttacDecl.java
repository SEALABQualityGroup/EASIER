package scanSpecAEmilia.scanAttacDecl;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.AttacDecl;
import utilities.ErrorMessage;

/**
 * Classe utilizzata per scannerizzare ogni parte della
 * dichiarazione di un collegamento tra elementi architetturali,
 * dettata dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 * Una dichiarazione di collegamento architetturale ha la seguente
 * forma:
 *
 * <architectural_attachment_decl> ::= "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
 * "TO" <identifier> ["[" <expr> "]"] "." <identifier>
 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * ["AND" "FOR_ALL" <identifier> "IN" <expr> ".." <expr>]
 * "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
 * "TO" <identifier> ["[" <expr> "]"] "." <identifier>
 *
 * Nella sua forma piu' semplice, una dichiarazione di collegamento
 * architetturale contiene l'indicazione di un'interazione di
 * output seguita dall'indicazione di un'interazione di input.
 * Ognuna delle due interazionie'espressa in una notazione puntata
 * attraverso l'identificatore dell' AEI a cui l'interazione
 * appartiene, un'espressione intera racchiusa tra parentesi
 * quadre, che rappresenta un selettore e deve essere privo di
 * identificatori non dichiarati e invocazioni a generatori di
 * numeri pseudo-casuali, e l'identificatore dell'interazione.
 * L'interazione non deve essere di tipo architetturale. I due
 * AEI devono essere diversi tra loro. Almeno una delle due
 * interazioni deve essere una uni-interaction, e almeno una di
 * loro deve essere un'azione passiva all'interno del
 * comportamento dell'AEI a cui appartiene. I soli identificatori
 * che si possono presentare nell'espressione di selezione
 * possibile sono quelli dei parametri formali costanti dichiarati
 * nell'intestazione del tipo architetturale. La seconda forma e'
 * utile per dichiarare in modo conciso diversi collegamenti
 * architetturali attraverso un meccanismo di indicizzazione.
 * Questo richiede la specifica aggiuntiva di fino a due
 * identificatori di indice differenti, che si possono presntare
 * nell'espressioni di selezione, insieme con i loro intervalli,
 * ognuno dei qualie'dato da due espressioni intere. Queste due
 * espressioni devono essere prive di identificari non dichiarati
 * e invocazioni di numeri generatori di pseudo-casuali, con il
 * valore della prima espressione non piu' grande del valore della
 * seconda espressione. Tutte le uni-interazioni attacate alla
 * stessa and o or interazione deve appartenere a AEI differenti.
 * Tra tutte le uni-interazioni attaccate alla stessa
 * and-interazione passiva, al piu' una puo' essere un'azione non
 * passiva nel comportamento dell'AEI al quale appartiene.
 */

public class ScanAttacDecl {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanAttacDecl(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * alla dichiarazione di un colleagamento tra elementi
	 * architetturali secondo la grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isAttacDecl(String specifiche)
		{
		/*
		 * Una dichiarazione di collegamento architetturale ha la seguente forma:
		 *
		 * <architectural_attachment_decl> ::= "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
		 * "TO" <identifier> ["[" <expr> "]"] "." <identifier>
		 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
		 * ["AND" "FOR_ALL" <identifier> "IN" <expr> ".." <expr>]
		 * "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
		 * "TO" <identifier> ["[" <expr> "]"] "." <identifier>
		 */
		ScanAttacDeclP scanAttacDeclP = new ScanAttacDeclP(this.depth + 1);
		ScanAttacDeclInd scanAttacDeclInd = new ScanAttacDeclInd(this.depth + 1);
		if (!(scanAttacDeclP.isAttacDeclP(specifiche) ||
				scanAttacDeclInd.isAttacDeclInd(specifiche)))
			{
			String string = specifiche + " is not declaration of attachments";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanAttacDeclP.getErrorMessage();
			ErrorMessage errorMessage2 = scanAttacDeclInd.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			list.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto AttacDecl, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto AttacDecl.
	 * @throws ScanException
	 */
	public AttacDecl scanAttacDecl(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Una dichiarazione di collegamento architetturale ha la seguente forma:
			 *
			 * <architectural_attachment_decl> ::= "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
			 * "TO" <identifier> ["[" <expr> "]"] "." <identifier>
			 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
			 * ["AND" "FOR_ALL" <identifier> "IN" <expr> ".." <expr>]
			 * "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
			 * "TO" <identifier> ["[" <expr> "]"] "." <identifier>
			 */
			ScanAttacDeclP scanAttacDeclP = new ScanAttacDeclP(this.depth + 1);
			ScanAttacDeclInd scanAttacDeclInd = new ScanAttacDeclInd(this.depth + 1);
			if (scanAttacDeclP.isAttacDeclP(specifiche))
				return scanAttacDeclP.scanAttacDeclP(specifiche);
			else if (scanAttacDeclInd.isAttacDeclInd(specifiche))
				return scanAttacDeclInd.scanAttacDeclInd(specifiche);
			else return null;
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
