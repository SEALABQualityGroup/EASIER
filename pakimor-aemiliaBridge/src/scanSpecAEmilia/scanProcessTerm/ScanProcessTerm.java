package scanSpecAEmilia.scanProcessTerm;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;

/**
 * Classe utilizzata per scannerizzare ogni parte della
 * dichiarazione di un termine di processo, dettata
 * dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

/*
 * Un termine di processo segue l'intestazione di equazione comportamentale ed e'
 * definita nel seguente modo:
 *
 * <process_term> ::= "stop"
 * | <action> "." <process_term_1>
 * | "choice" "{" <process_term_2_sequence> "}"
 * <process_term_1> ::= <process_term>
 * | <identifier> "(" <actual_par_sequence> ")"
 * <process_term_2> ::= ["cond" "(" <expr> ")" "->"] <process_term>
 *
 * <process_term_2_sequence>e'una sequenza di almeno due termini di processo separati
 * da virgole, ognuna possibilmente preceduta da un'espressione booleana che stabilisce
 * la condizione sotto cuie'disponibile.
 * <actual_par_sequence>e'una sequenza possibilmente vuota di espressioni.
 */

public class ScanProcessTerm {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanProcessTerm(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad un termine di processo secondo la grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isProcessTerm(String specifiche)
		{
		/* MODELLED */
		ScanStop scanStop = new ScanStop(this.depth + 1);
		ScanActionProcess scanActionProcess = new ScanActionProcess(this.depth + 1);
		ScanChoiceProcess scanChoiceProcess = new ScanChoiceProcess(this.depth + 1);
		if (!(scanStop. isStop(specifiche) || scanActionProcess.isActionProcess(specifiche)
				|| scanChoiceProcess.isChoiceProcess(specifiche)))
			{
			String string = specifiche + " is not process term";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanStop.getErrorMessage();
			ErrorMessage errorMessage2 = scanActionProcess.getErrorMessage();
			ErrorMessage errorMessage3 = scanChoiceProcess.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			list.add(errorMessage3);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ProcessTerm, includendo informazioni
	 * ottenute attraverso la scannerizzazione di un termine
	 * di processo secondo la grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto ProcessTerm.
	 * @throws ScanException
	 */

	public ProcessTerm scanProcessTerm(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * <process_term> ::= "stop"
			 * | <action> "." <process_term_1>
			 * | "choice" "{" <process_term_2_sequence> "}"
			 * <process_term_1> ::= <process_term>
			 * | <identifier> "(" <actual_par_sequence> ")"
			 * <process_term_2> ::= ["cond" "(" <expr> ")" "->"] <process_term>
			 */
			ProcessTerm pt;
			ScanStop scanStop = new ScanStop(this.depth + 1);
			ScanActionProcess scanActionProcess = new ScanActionProcess(this.depth + 1);
			ScanChoiceProcess scanChoiceProcess = new ScanChoiceProcess(this.depth + 1);
			if (scanStop.isStop(specifiche))
				pt = scanStop.scanStop(specifiche);
			else if (scanActionProcess.isActionProcess(specifiche))
				pt = scanActionProcess.scanActionProcess(specifiche);
			else if (scanChoiceProcess.isChoiceProcess(specifiche))
				pt = scanChoiceProcess.scanChoiceProcess(specifiche);
			else
				return null;
			return pt;
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