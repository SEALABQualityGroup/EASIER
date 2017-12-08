/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.IntegerRangeType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanIntervalloInt {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanIntervalloInt(int depth) 
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
	 * al tipo di dato intervallo di interi.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isIntervalloInt(String specifiche)
		{
		// "integer" "(" <expr> ".." <expr> ")"
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\.\\.\\s*");
		try {
			s.skip("\\s*integer\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not range of integer type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"integer\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		String inizio = null;
		try {
			inizio = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not range of integer type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "beginning integer expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\)\\s*\\z");
		try {
			s.skip("\\s*\\.\\.\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not range of integer type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"..\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		String fine = null;
		try {
			fine = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not range of integer type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "ending integer expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*\\)\\s*")) 
			{
			String string = specifiche + " is not range of integer type";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\")\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanExp scanExp = new ScanExp(this.depth + 1);
		ScanExp scanExp2 = new ScanExp(this.depth + 1);
		if (!scanExp.isEspressione(inizio))
			{
			String string = specifiche + " is not range of integer type";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanExp.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (!scanExp2.isEspressione(fine))
			{
			String string = specifiche + " is not range of integer type";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanExp2.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto IntegerRangeType attraverso la scannerizzazione
	 * di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto IntegerRangeType.
	 * @throws ScanException
	 */
	public IntegerRangeType scanIntervalloInt(String specifiche)
		throws ScanException
		{
		try {
			if (isIntervalloInt(specifiche)) {
				IntegerRangeType ii = new IntegerRangeType();
				MyScanner s = new MyScanner(specifiche);
				s.useDelimiter("\\s*\\.\\.\\s*");
				s.skip("\\s*integer\\s*\\(\\s*");
				String inizio = s.next();
				s.useDelimiter("\\s*\\)\\s*\\z");
				s.skip("\\s*\\.\\.\\s*");
				String fine = s.next();
				ScanExp scanExp = new ScanExp(this.depth + 1);
				ii.setBeginningInt(scanExp.scanEspressione(inizio));
				ii.setEndingInt(scanExp.scanEspressione(fine));
				return ii;
			} else
				return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
