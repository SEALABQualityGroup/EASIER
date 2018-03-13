package scanSpecAEmilia;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.scanActionRate.ScanActionRate;
import scanSpecAEmilia.scanActionType.ScanActionType;
import specificheAEmilia.Action;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import utilities.ErrorMessage;

/**
 * Classe utilizzata per scannerizzare ogni parte di un'azione
 * presente in una specifica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ScanAction {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanAction(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifichee'conforme alla
	 * grammatica AEmilia per un'azione
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isAction(String specifiche)
		{
		/*
		 * <action> ::= "<" <action_type> "," <action_rate> ">"
		 *
		 * <action_type> ::= <identifier>
		 * | <identifier> "?" "(" <local_var_sequence> ")"
		 * | <identifier> "!" "(" <expr_sequence> ")"
		 * <action_rate> ::= "exp" "(" <expr> ")"
		 * | "inf" "(" <expr> "," <expr> ")"
		 * | "inf"
		 * | "_" "(" <expr> "," <expr> ")"
		 * | "_"
		 */
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*<\\s*");
			}
		catch (NoSuchElementException exception)
			{
			String string = specifiche + " is not action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = "Action must begin with \"<\"";
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		// il delimitatore cambia a seconda se in specifiche
		// sia presente un'azione semplice o un'azione
		// di input o output
		String tipoAzione = new String();
		if (specifiche.contains("?")||specifiche.contains("!"))
			{
			s.useDelimiter("\\s*\\)\\s*,\\s*");
			try {
				// tipoAzione contiene la specifica di un
				// ActionType
				tipoAzione = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not action";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string2 = "Action type expected: there is not action type";
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				return false;
				}
			// se tipoAzione contiene ( o none'un'azione oe'un'azione strutturata
			// poich� un'identificatore non puo' contenenre la parentesi (
			tipoAzione = tipoAzione + ")";
			s.useDelimiter("\\s*>\\s*");
			try {
				s.skip("\\s*\\)\\s*");
				}
			catch (NoSuchElementException exception)
				{
				String string = specifiche + " is not action";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string2 = "Expected \")\": there is not \")\" at the end of action type "+ tipoAzione;
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				return false;
				}
			try {
				s.skip("\\s*,\\s*");
				}
			catch (NoSuchElementException exception)
				{
				String string = specifiche + " is not action";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string2 = "Expected \",\": there is not \",\" between action type and action rate";
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				return false;
				}
			}
		else
			{
			s.useDelimiter("\\s*,\\s*");
			try {
				// tipoAzione contiene la specifica di un
				// ActionType
				tipoAzione = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not action";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string2 = "action type expected";
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				return false;
				}
			s.useDelimiter("\\s*>\\s*");
			try {
				s.skip("\\s*,\\s*");
				}
			catch (NoSuchElementException exception)
				{
				String string = specifiche + " is not action";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string2 = "Expected \",\": there is not \",\" between action type and action rate";
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				return false;
				}
			}
		String tassoAzione = new String();
		try {
			// tassoAzione contiene la specifica di un
			// ActionRate
			tassoAzione = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = "action rate expected";
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		// bisogna che non ci sia nient'altro nell'input
		s.useDelimiter("\\s*\\z");
		s.skip("\\s*>\\s*");
		if (s.hasNext("\\S+"))
			{
			String string = specifiche + " is not action";
			this.errorMessage.setErrorMessage(string);
			return false;
			}
		ScanActionType scanActionType = new ScanActionType(this.depth + 1);
		if (!scanActionType.isActionType(tipoAzione))
			{
			String string = specifiche + " is not action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanActionType.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		ScanActionRate scanActionRate = new ScanActionRate(this.depth + 1);
		if (!scanActionRate.isActionRate(tassoAzione))
			{
			String string = specifiche + " is not action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanActionRate.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Restituisce un oggetto Action che contiene informazioni
	 * di un'azione presente nelle specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto Action.
	 * @throws ScanException
	 */
	public Action scanAction(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * <action> ::= "<" <action_type> "," <action_rate> ">"
			 *
			 * <action_type> ::= <identifier>
			 * | <identifier> "?" "(" <local_var_sequence> ")"
			 * | <identifier> "!" "(" <expr_sequence> ")"
			 * <action_rate> ::= "exp" "(" <expr> ")"
			 * | "inf" "(" <expr> "," <expr> ")"
			 * | "inf"
			 * | "_" "(" <expr> "," <expr> ")"
			 * | "_"
			 */
			Action a = new Action();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*<\\s*");
			// il delimitatore cambia a seconda se in specifiche
			// sia presente un'azione semplice o un'azione
			// di input o output
			String tipoAzione = new String();
			if (specifiche.contains("?") || specifiche.contains("!")) {
				s.useDelimiter("\\s*\\)\\s*,\\s*");
				tipoAzione = s.next();
				// tipoAzione contiene la specifica di un
				// ActionType
				tipoAzione = tipoAzione + ")";
				s.useDelimiter("\\s*>\\s*");
				s.skip("\\s*\\)\\s*,\\s*");
			} else {
				s.useDelimiter("\\s*,\\s*");
				tipoAzione = s.next();
				// tipoAzione contiene la specifica di un
				// ActionType
				s.useDelimiter("\\s*>\\s*");
				s.skip("\\s*,\\s*");
			}
			// tassoAzione contiene la specifica di un
			// ActionRate
			String tassoAzione = s.next();
			ScanActionType scanActionType = new ScanActionType(this.depth + 1);
			ActionType t = scanActionType.scanActionType(tipoAzione);
			ScanActionRate scanActionRate = new ScanActionRate(this.depth + 1);
			ActionRate r = scanActionRate.scanActionRate(tassoAzione);
			a.setType(t);
			a.setRate(r);
			return a;
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
