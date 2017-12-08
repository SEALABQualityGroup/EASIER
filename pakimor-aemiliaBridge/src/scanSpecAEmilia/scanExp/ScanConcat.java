/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.Concat;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanConcat {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanConcat(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	// "concat" "(" <identifier> "," <identifier> ")"
	public boolean isConcat(String specifiche)
		{
		/* MODELLED */
		MyScanner myScanner = new MyScanner(specifiche);
		try {
			myScanner.skip("\\s*concat\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not concat expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"concat(\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// riconosco la prima espressione
		myScanner.useDelimiter("\\s*\\,\\s*");
		String list1 = null;
		try {
			list1 = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not concat expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "list identifier expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
		if (!scanIdentExpr.isIdentExpr(list1))
			{
			String string = specifiche + " is not concat expression";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage31 = scanIdentExpr.getErrorMessage();
			list.add(errorMessage31);
			return false;
			}
		// riconosco la seconda espressione
		myScanner.useDelimiter("\\s*\\)\\s*\\z");
		try {
			myScanner.skip("\\s*\\,\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not concat expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\",\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		String list21 = null;
		try {
			list21 = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not concat expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "list identifier expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanIdentExpr scanIdentExpr2 = new ScanIdentExpr(this.depth + 1);
		if (!scanIdentExpr2.isIdentExpr(list21))
			{
			String string = specifiche + " is not concat expression";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage32 = scanIdentExpr2.getErrorMessage();
			list.add(errorMessage32);
			return false;
			}
		myScanner.useDelimiter("\\s*\\z");
		try {
			myScanner.skip("\\s*\\)\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not concat expression";
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
	
	// "concat" "(" <identifier> "," <identifier> ")"
	public Concat scanConcat(String string)
		throws ScanException
		{
		try {
			Concat concat = new Concat();
			MyScanner myScanner = new MyScanner(string);
			myScanner.skip("\\s*concat\\s*\\(\\s*");
			// scannerizzo la prima espressione
			myScanner.useDelimiter("\\s*\\,\\s*");
			String list1 = myScanner.next();
			// scannerizzo la seconda espressione
			myScanner.useDelimiter("\\s*\\)\\s*\\z");
			myScanner.skip("\\s*\\,\\s*");
			String list2 = myScanner.next();
			ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
			concat.setList1(scanIdentExpr.scanIdentExpr(list1));
			ScanIdentExpr scanIdentExpr2 = new ScanIdentExpr(this.depth + 1);
			concat.setList2(scanIdentExpr2.scanIdentExpr(list2));
			myScanner.useDelimiter("\\s*\\z");
			myScanner.skip("\\s*\\)\\s*");
			return concat;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
