/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.RecordCons;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanRecordCons {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanRecordCons(int depth) 
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
	 * "record_cons" "(" <expr_sequence> ")"
	 */
	public boolean isRecordCons(String specifiche) 
		{
		/* MODELLED */
		MyScanner myScanner = new MyScanner(specifiche);
		myScanner.useDelimiter("\\s*\\z");
		String expSeq = new String();
		try {
			myScanner.skip("\\s*record_cons\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not record construction expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"record_cons(\" expected";
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
			String string = specifiche + " is not record construction expression";
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
			String string = specifiche + " is not record construction expression";
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
			String string = specifiche + " is not record construction expression";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage3 = scanExpSeq.getErrorMessage();
			list.add(errorMessage3);
			return false;
			}
		return true;
		}
	
	/*
	 * "record_cons" "(" <expr_sequence> ")"
	 */
	public RecordCons scanRecordCons(String specifiche)
		throws ScanException
		{
		try {
			RecordCons recordCons = new RecordCons();
			MyScanner myScanner = new MyScanner(specifiche);
			myScanner.useDelimiter("\\s*\\)\\s*\\z");
			myScanner.skip("\\s*record_cons\\s*\\(\\s*");
			String expSeq = myScanner.next();
			ScanExpSeq scanExpSeq = new ScanExpSeq(this.depth + 1);
			recordCons.setValues(scanExpSeq.scanExpSeq(expSeq));
			myScanner.useDelimiter("\\s*\\z");
			myScanner.skip("\\s*\\)\\s*");
			return recordCons;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
