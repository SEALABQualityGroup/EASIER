/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ArrayCons;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanArrayCons {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanArrayCons(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	// "array_cons" "(" <expr_sequence> ")"
	// dove <expr_sequence>e'una sequenza non vuota
	// di espressioni separate da virgole
	public boolean isArrayCons(String specifiche)
		{
		/* MODELLED */
		MyScanner myScanner = new MyScanner(specifiche);
		myScanner.useDelimiter("\\s*\\z");
		String expSeq = new String();
		try {
			myScanner.skip("\\s*array_cons\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not array construction expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"array_cons(\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// expSeqe'una sequenza non vuota di espressioni separate da virgole
		myScanner.useDelimiter("\\s*\\)\\s*\\z");
		// la sequenza di espressioni non puo' essere vuota
		try {
			expSeq = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not array construction expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence of expressions expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		myScanner.useDelimiter("\\s*\\z");
		try {
			myScanner.skip("\\s*\\)\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not array construction expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\")\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanExpSeq scanExpSeq = new ScanExpSeq(this.depth + 1);
		if (!scanExpSeq.isExpSeq(expSeq))
			{
			String string = specifiche + " is not array construction expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = expSeq + " is not expressions sequence";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		return true;
		}

	// "array_cons" "(" <expr_sequence> ")"
	// dove <expr_sequence>e'una sequenza non vuota
	// di espressioni separate da virgole
	public ArrayCons scanArrayCons(String string)
		throws ScanException
		{
		try {
			ArrayCons arrayCons = new ArrayCons();
			MyScanner myScanner = new MyScanner(string);
			myScanner.useDelimiter("\\s*\\)\\s*\\z");
			myScanner.skip("\\s*array_cons\\s*\\(\\s*");
			String expSeq = myScanner.next();
			ScanExpSeq scanExpSeq = new ScanExpSeq(this.depth + 1);
			arrayCons.setArrayElements(scanExpSeq.scanExpSeq(expSeq));
			myScanner.useDelimiter("\\s*\\z");
			myScanner.skip("\\s*\\)\\s*");
			return arrayCons;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
