/**
 * 
 */
package scanSpecAEmilia.scanActionType;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import specificheAEmilia.ActionInput;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanActionInput {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanActionInput(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche definisce
	 * un'azione di input secondo la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isActionInput(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\?\\s*");
		// i contiene l'identificatore dell'azione
		// di input
		String i = new String();
		try {
			i = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not input action";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "action identifier expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		s.useDelimiter("\\s*\\)(\\s)*\\z");
		try {
			s.skip("\\s*\\?\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not input action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"?(\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		// lsv contiene la sequenza di variabili locali
		String lvs = new String();
		try {
			lvs = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not input action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "local variables sequence expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		if (!scanIdent.isIdent(i))
			{
			String string = specifiche + " is not input action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = i + " is not identifier";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		ScanLocVarSeq scanLocVarSeq = new ScanLocVarSeq(this.depth + 1);
		if (!scanLocVarSeq.isLocVarSeq(lvs))
			{
			String string = specifiche + " is not input action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = scanLocVarSeq.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Restituisce un'azione di input secondo le informazioni
	 * contenute in specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ActionInput.
	 * @throws ScanException
	 */
	public ActionInput scanActionInput(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * <identifier> "?" "(" <local_var_sequence> ")"
			 */
			/*
			 * istruzioni per il testing.
			 System.out.println("la specifica per scanActionInput e': "+specifiche);
			 */
			ActionInput ai = new ActionInput();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\?\\s*\\(\\s*");
			// st1 contiene l'identificatore dell'azione di input
			String st1 = s.next();
			ai.setName(st1);
			s.skip("\\s*\\?\\s*\\(\\s*");
			s.useDelimiter("\\s*\\)(\\s)*\\z");
			// lvs contiene la sequenza di variabili locali
			String lvs = s.next();
			ScanLocVarSeq scanLocVarSeq = new ScanLocVarSeq(this.depth + 1);
			ai.setInputVariables(scanLocVarSeq.scanLocVarSeq(lvs));
			return ai;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
	
	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}
}
