/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import specificheAEmilia.Get;
import specificheAEmilia.IdentExpr;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanGet {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanGet(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	/*
	 * "get" "(" <identifier> "," <identifier> ")"
	 */
	public boolean isGet(String specifiche) 
		{
		/* MODELLED */
		MyScanner myScanner = new MyScanner(specifiche);
		try {
			myScanner.skip("\\s*get\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not get expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"get(\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// riconosco il nome del campo
		myScanner.useDelimiter("\\s*\\,\\s*");
		String identifier = null;
		try {
			identifier = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not get expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "field identifier expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// riconosco il record
		myScanner.useDelimiter("\\s*\\)\\s*\\z");
		try {
			myScanner.skip("\\s*\\,\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not get expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\",\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		String record = null;
		try {
			record = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not get expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "record identifier expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
		if (!scanIdent.isIdent(identifier))
			{
			String string = specifiche + " is not get expression";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage31 = scanIdent.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage31);
			return false;
			}
		if (!scanIdentExpr.isIdentExpr(record))
			{
			String string = specifiche + " is not get expression";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = scanIdentExpr.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage2);
			return false;
			}
		myScanner.useDelimiter("\\s*\\z");
		try {
			myScanner.skip("\\s*\\)\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not get expression";
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

	/*
	 * "get" "(" <identifier> "," <identifier> ")"
	 */
	public Get scanGet(String specifiche)
		throws ScanException
		{
		try {
			Get get = null;
			MyScanner myScanner = new MyScanner(specifiche);
			myScanner.skip("\\s*get\\s*\\(\\s*");
			// scannerizzo il nome del campo del record
			myScanner.useDelimiter("\\s*\\,\\s*");
			String field = myScanner.next();
			// scannerizzo il record
			myScanner.useDelimiter("\\s*\\)\\s*\\z");
			myScanner.skip("\\s*\\,\\s*");
			String record = myScanner.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			String fieldE = scanIdent.scanIdent(field);
			ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
			IdentExpr recordE = scanIdentExpr.scanIdentExpr(record);
			get = new Get(fieldE, recordE);
			myScanner.useDelimiter("\\s*\\z");
			myScanner.skip("\\s*\\)\\s*");
			return get;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
