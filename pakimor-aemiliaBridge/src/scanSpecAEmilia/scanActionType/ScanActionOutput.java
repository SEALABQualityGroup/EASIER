/**
 * 
 */
package scanSpecAEmilia.scanActionType;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import specificheAEmilia.ActionOutput;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanActionOutput {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanActionOutput(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}

	/**
	 * Restituisce true se e solo se specifiche rappresenta
	 * un'azione di output secondo la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isActionOutput(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\!\\s*");
		// i contiene il nome dell'azione di output
		String i = new String();
		try {
			i = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not output action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "action identifier expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		s.useDelimiter("\\s*\\)(\\s)*\\z");
		try {
			s.skip("\\s*\\!\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not output action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"!(\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		// lvs contiene una sequenza di espressioni di output
		// separate da virgole
		String lvs = new String();
		try {
			lvs = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not output action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "expression sequence expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		if (!scanIdent.isIdent(i))
			{
			String string = specifiche + " is not output action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = i + " is not identifier";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		ScanExprSeq scanExprSeq = new ScanExprSeq(this.depth + 1);
		if (!scanExprSeq.isExprSeq(lvs))
			{
			String string = specifiche + " is not output action";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = scanExprSeq.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Restituisce un oggetto ActionOutput contenente informazioni
	 * presenti in specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ActionOutput.
	 * @throws ScanException
	 */
	public ActionOutput scanActionOutput(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * <identifier> "!" "(" <expr_sequence> ")"
			 */
			// rendere più leggibile con l'utilizzo di opportuni pattern
			ActionOutput ao = new ActionOutput();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\!\\s*");
			// st1 contiene il nome dell'azione di output
			String st1 = s.next("\\s*[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)\\s*");
			ao.setName(st1);
			s.useDelimiter("\\s*\\)(\\s)*\\z");
			s.skip("\\s*\\!\\s*\\(\\s*");
			// lvs contiene una sequenza di espressioni
			// di output separate da virgole
			String lvs = s.next();
			ScanExprSeq scanExprSeq = new ScanExprSeq(this.depth + 1);
			ao.setOutputExprs(scanExprSeq.scanExprSeq(lvs));
			return ao;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
