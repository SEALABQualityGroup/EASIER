/**
 * 
 */
package scanSpecAEmilia.scanInteractionDecl;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.AEIident;
import specificheAEmilia.Expression;
import specificheAEmilia.InteractionDecl;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanInteractionDeclP {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanInteractionDeclP(int depth) 
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
	 * ad una dichiarazione di interazione architetturale semplice
	 * secondo la grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isInteractionDeclP(String specifiche)
		{
		/*
		 * Una dichiarazione di interazione architetturale ha la
		 * seguente forma:
		 *
		 * <architectural_interaction_decl> ::=
		 * <identifier> ["[" <expr> "]"] "."
		 * <identifier>
		 */
		String Aei = new String();
		String S = new String();
		String Interaction = new String();
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\.\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
		// Aei contiene l'identificatore di un Aei con un
		// eventuale selettore
		try {
			Aei = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not simple declaration of architectural interaction";
			this.errorMessage.setErrorMessage(string);
			String string2 = "architectural element instance expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (Aei.contains("["))
			{
			MyScanner s1 = new MyScanner(Aei);
			s1.useDelimiter("\\s*\\[\\s*");
			try {
				Aei = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not simple declaration of architectural interaction";
				this.errorMessage.setErrorMessage(string);
				String string2 = "architectural element instance expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			s1.useDelimiter("\\s*\\]\\s*");
			try {
				s1.skip("\\s*\\[\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not simple declaration of architectural interaction";
				this.errorMessage.setErrorMessage(string);
				String string2 = "\"[\" expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			// S rappresenta il sellettore di un Aei
			try {
				S = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not simple declaration of architectural interaction";
				this.errorMessage.setErrorMessage(string);
				String string2 = "selector expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			try {
				s1.skip("\\s*\\]\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not simple declaration of architectural interaction";
				this.errorMessage.setErrorMessage(string);
				String string2 = "\"]\" expected";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			}
		s.useDelimiter("\\s*\\z");
		try {
			s.skip("\\s*\\.\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not simple declaration of architectural interaction";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\".\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// Interactione'il nome dell'interazione
		// architetturale
		try {
			Interaction = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not simple declaration of architectural interaction";
			this.errorMessage.setErrorMessage(string);
			String string2 = "interaction name expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		ScanIdent scanIdent2 = new ScanIdent(this.depth + 1);
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!scanIdent.isIdent(Aei))
			{
			String string = specifiche + " is not simple declaration of architectural interaction";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (!scanIdent2.isIdent(Interaction))
			{
			String string = specifiche + " is not simple declaration of architectural interaction";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanIdent2.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (!S.equals("")) 
			{
			if (!scanExp.isEspressione(S))
				{
				String string = specifiche + " is not simple declaration of architectural interaction";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage = scanExp.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			}
		return true;
		}

	/**
	 * Crea un oggetto InteractionDecl, includendo informazioni
	 * ottenute attraverso la scannerizzazione di una
	 * dichiarazione di interazione architetturale semplice.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto InteractionDecl.
	 * @throws ScanException
	 */
	public InteractionDecl scanInteractionDeclP(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Una dichiarazione di interazione architetturale ha la
			 * seguente forma:
			 *
			 * <architectural_interaction_decl> ::=
			 * <identifier> ["[" <expr> "]"] "."
			 * <identifier>
			 */
			InteractionDecl id = new InteractionDecl();
			String Aei = new String();
			String S = new String();
			String Interaction = new String();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\.\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
			// Aei contiene l'identificatore di un Aei con un
			// eventuale selettore
			Aei = s.next();
			if (Aei.contains("[")) {
				MyScanner s1 = new MyScanner(Aei);
				s1.useDelimiter("\\s*\\[\\s*");
				Aei = s1.next();
				s1.useDelimiter("\\s*\\]\\s*");
				s1.skip("\\s*\\[\\s*");
				// S rappresenta il sellettore di un Aei
				S = s1.next();
				s1.skip("\\s*\\]\\s*");
			}
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*\\.\\s*");
			// Interactione'il nome dell'interazione
			// architetturale
			Interaction = s.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanIdent scanIdent2 = new ScanIdent(this.depth + 1);
			ScanExp scanExp = new ScanExp(this.depth + 1);
			String string = scanIdent.scanIdent(Aei);
			id.setInteraction(scanIdent2.scanIdent(Interaction));
			Expression expression = null;
			if (!S.equals(""))
				expression = scanExp.scanEspressione(S);
			AEIident aeIident = new AEIident(string, expression);
			id.setAei(aeIident);
			return id;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
