/**
 * 
 */
package scanSpecAEmilia.scanActionType;

import java.util.List;

import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import specificheAEmilia.ActionType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanActionType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanActionType(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Restituisce true se e solo se specifiche rappresenta un
	 * tipo azione secondo la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isActionType(String specifiche)
		{
		ScanActionInput scanActionInput = new ScanActionInput(this.depth + 1);
		ScanActionOutput scanActionOutput = new ScanActionOutput(this.depth + 1);
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		if (!(scanIdent.isIdent(specifiche) ||
			scanActionInput.isActionInput(specifiche) ||
			scanActionOutput.isActionOutput(specifiche)))
			{
			String string = specifiche + " is not action type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			ErrorMessage errorMessage2 = scanActionInput.getErrorMessage();
			ErrorMessage errorMessage3 = scanActionOutput.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			list.add(errorMessage3);
			return false;
			}
		else 
			return true;
		}
	
	/**
	 * Restituisce un oggetto ActionType secondo le informazioni
	 * contenute in specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ActionType.
	 * @throws ScanException
	 */
	public ActionType scanActionType(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * <action_type> ::= <identifier>
			 * | <identifier> "?" "(" <local_var_sequence> ")"
			 * | <identifier> "!" "(" <expr_sequence> ")"
			 */
			// rendere piu' leggibile con l'utilizzo di opportuni pattern
			ActionType t = new ActionType();
			ScanActionInput scanActionInput = new ScanActionInput(this.depth + 1);
			ScanActionOutput scanActionOutput = new ScanActionOutput(this.depth + 1);
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			if (scanActionInput.isActionInput(specifiche))
				t = scanActionInput.scanActionInput(specifiche);
			else if (scanActionOutput.isActionOutput(specifiche))
				t = scanActionOutput.scanActionOutput(specifiche);
			else if (scanIdent.isIdent(specifiche))
				t.setName(scanIdent.scanIdent(specifiche));
			return t;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
	}
