/**
 * 
 */
package scanSpecAEmilia.scanActionRate;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.ActionRate;
import specificheAEmilia.RateExp;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanRateExp 
	{
	
	private int depth;
	private ErrorMessage errorMessage;
		
	public ScanRateExp(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche indica il tasso
	 * di un'azione temporizzata esponenzialmente.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isRateExp(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		try {	
			s.skip("\\s*exp\\s*\\(\\s*");
			}
		catch(NoSuchElementException exception)
			{
			String string = specifiche + " is not exponential rate";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "exponential rate must begin with \"exp(\"";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		s.useDelimiter("\\s*\\)\\s*\\z");
		String ea = new String();
		try {
			// ea corrisponde alla durata dell'azione
			ea = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not exponential rate";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "expression expected: there is not rate expression";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!scanExp.isEspressione(ea)) 
			{
			String string = specifiche + " is not exponential rate";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = scanExp.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*\\)\\s*")) 
			{
			String string = specifiche + " is not exponential rate";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "exponential rate must end with \")\"";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ActionRate secondo le informazioni
	 * presenti in specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ActionRate.
	 * @throws ScanException
	 */
	public ActionRate scanRateExp(String specifiche)
		throws ScanException
		{
		try {
			MyScanner s = new MyScanner(specifiche);
			RateExp r = new RateExp();
			s.skip("\\s*exp\\s*\\(\\s*");
			s.useDelimiter("\\s*\\)\\s*\\z");
			// ea corrisponde alla durata dell'azione
			String ea = s.next();
			ScanExp scanExp = new ScanExp(this.depth + 1);
			r.setExpr(scanExp.scanEspressione(ea));
			s.skip("\\s*\\)\\s*\\z");
			return r;
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
