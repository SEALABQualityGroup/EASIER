/**
 * 
 */
package scanSpecAEmilia.scanInteractionDecl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.AEIident;
import specificheAEmilia.InteractionDecl;
import specificheAEmilia.InteractionDeclInd;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanInteractionDeclInd {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanInteractionDeclInd(int depth) 
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
	 * a dichiarazioni di interazioni architetturali ottenute
	 * in modo indicizzato.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isInteractionDeclInd(String specifiche)
		{
		/*
		 * Una dichiarazione di interazione architetturale ha la
		 * seguente forma:
		 *
		 * <architectural_interaction_decl> ::=
		 * "FOR_ALL" <identifier>
		 * "IN" <expr> ".." <expr>
		 * <identifier> "[" <expr> "]" "." <identifier>
		 */
		String indice = new String();
		String ExprInizio = new String();
		String ExprFine = new String();
		String r = new String();
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*FOR_ALL\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"FOR_ALL\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*IN\\s*");
		// identificatore indice
		try {
			indice = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "index expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\.\\.\\s*");
		try {
			s.skip("\\s*IN\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"IN\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// espressione iniziale
		try {
			ExprInizio = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "beginning expression expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\z");
		try {
			s.skip("\\s*\\.\\.\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"..\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// rime'l'espressione finale + la dichiarazione
		// semplice di un'interazione architetturale
		String rim = null;
		try {
			rim = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "ending expression and declaration of simple architectural interaction expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s = new MyScanner(rim);
		s.useDelimiter("\\s*\\.\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
		// del contiene l'espressione finale +
		// il nome dell'Aei con l'eventuale selettore
		String del = null;
		try {
			del = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "ending expression and aei name expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		MyScanner s1 = new MyScanner(del);
		s1.useDelimiter("\\s*\\[\\s*");
		// adesso del contiene l'espressione finale +
		// il nome dell'Aei senza selettore
		try {
			del = s1.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "ending expression and aei name without selector expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s1 = new MyScanner(del);
		s1.useDelimiter("\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)(\\s*)\\z");
		// ExprFine contiene l'espressione finale
		try {
			ExprFine = s1.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "ending expression expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s = new MyScanner (rim);
		s.useDelimiter("\\s*\\z");
		try {
			s.skip(Pattern.quote(ExprFine));
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "ending expression expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// re'la dichiarazione semplice di
		// un'interazione architetturale
		try {
			r = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "declaration of simple architectural interaction expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		ScanExp scanExp = new ScanExp(this.depth + 1);
		ScanExp scanExp2 = new ScanExp(this.depth + 1);
		ScanInteractionDeclP scanInteractionDeclP = new ScanInteractionDeclP(this.depth + 1);
		if (!scanIdent.isIdent(indice))
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (!scanExp.isEspressione(ExprInizio))
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanExp.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (!scanExp2.isEspressione(ExprFine))
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanExp2.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (!scanInteractionDeclP.isInteractionDeclP(r))
			{
			String string = specifiche + " is not declaration of indexed interactions";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanInteractionDeclP.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto InteractionDeclInd, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto InteractionDeclInd.
	 * @throws ScanException
	 */
	public InteractionDeclInd scanInteractionDeclInd(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Una dichiarazione di interazione architetturale ha la
			 * seguente forma:
			 *
			 * <architectural_interaction_decl> ::=
			 * "FOR_ALL" <identifier>
			 * "IN" <expr> ".." <expr>
			 * <identifier> "[" <expr> "]" "." <identifier>
			 */
			InteractionDeclInd idi = new InteractionDeclInd();
			String indice = new String();
			String ExprInizio = new String();
			String ExprFine = new String();
			String r = new String();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*FOR_ALL\\s*");
			s.useDelimiter("\\s*IN\\s*");
			// identificatore indice
			indice = s.next();
			s.useDelimiter("\\s*\\.\\.\\s*");
			s.skip("\\s*IN\\s*");
			// espressione iniziale
			ExprInizio = s.next();
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*\\.\\.\\s*");
			// rime'l'espressione finale + la dichiarazione
			// semplice di un'interazione architetturale
			String rim = s.next();
			s = new MyScanner(rim);
			s.useDelimiter("\\s*\\.\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
			// del contiene l'espressione finale +
			// il nome dell'Aei con l'eventuale selettore
			String del = s.next();
			MyScanner s1 = new MyScanner(del);
			s1.useDelimiter("\\s*\\[\\s*");
			// adesso del contiene l'espressione finale +
			// il nome dell'Aei senza selettore
			del = s1.next();
			s1 = new MyScanner(del);
			s1.useDelimiter("\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*\\z");
			// ExprFine contiene l'espressione finale
			ExprFine = s1.next();
			s = new MyScanner(rim);
			s.useDelimiter("\\s*\\z");
			s.skip(Pattern.quote(ExprFine));
			// re'la dichiarazione semplice di
			// un'interazione architetturale
			r = s.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanExp scanExp = new ScanExp(this.depth + 1);
			idi.setIndex(scanIdent.scanIdent(indice));
			idi.setExprInizio(scanExp.scanEspressione(ExprInizio));
			idi.setExprFine(scanExp.scanEspressione(ExprFine));
			ScanInteractionDeclP scanInteractionDeclP = new ScanInteractionDeclP(this.depth + 1);
			InteractionDecl id = scanInteractionDeclP.scanInteractionDeclP(r);
			idi.setAei(id.getAei());
			idi.setInteraction(id.getInteraction());
			AEIident aeIident = id.getAei();
			idi.setAei(aeIident);
			return idi;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
