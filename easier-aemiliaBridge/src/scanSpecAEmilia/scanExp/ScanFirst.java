/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.First;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanFirst {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanFirst(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	// "first" "(" <identifier> ")"
	public boolean isFirst(String specifiche)
		{
		/* MODELLED */
		MyScanner myScanner = new MyScanner(specifiche);
		try {
			myScanner.skip("\\s*first\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not first expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"first(\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		myScanner.useDelimiter("\\s*\\)\\s*\\z");
		String espressione = null;
		try {
			espressione = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not first expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "expression identifier expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
		if (!scanIdentExpr.isIdentExpr(espressione))
			{
			String string = specifiche + " is not first expression";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage31 = scanIdentExpr.getErrorMessage();
			list.add(errorMessage31);
			return false;
			}
		myScanner.useDelimiter("\\s*\\z");
		try {
			myScanner.skip("\\s*\\)\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not first expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\")\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		return true;
		}
	
	// "first" "(" <identifier> ")"
	public First scanFirst(String string)
		throws ScanException
		{
		try {
			First first = new First();
			MyScanner myScanner = new MyScanner(string);
			myScanner.skip("\\s*first\\s*\\(\\s*");
			myScanner.useDelimiter("\\s*\\)\\s*\\z");
			String espressione = myScanner.next();
			ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
			first.setList(scanIdentExpr.scanIdentExpr(espressione));
			myScanner.useDelimiter("\\s*\\z");
			myScanner.skip("\\s*\\)\\s*");
			return first;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
