/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ListCons;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanListCons {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanListCons(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	// "list_cons" "(" <pe_expr_sequence> ")"
	// dove <pe_expr_sequence>e'una sequenza possibilmente vuota
	// di espressioni separate da virgole
	public boolean isListCons(String specifiche)
		{
		/* MODELLED */
		MyScanner myScanner = new MyScanner(specifiche);
		myScanner.useDelimiter("\\s*\\z");
		String expSeq = new String();
		try {
			myScanner.skip("\\s*list_cons\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not list construction expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"list_cons(\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// expSeqe'una sequenza possibilmente vuota di espressioni separate da virgole
		myScanner.useDelimiter("\\s*\\)\\s*\\z");
		// la sequenza di espressioni puo' essere vuota
		if (myScanner.hasNext()) 
			{
			expSeq = myScanner.next();
			}
		myScanner.useDelimiter("\\s*\\z");
		try {
			myScanner.skip("\\s*\\)\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not list construction expression";
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
			String string = specifiche + " is not list construction expression";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage3 = scanExpSeq.getErrorMessage();
			list.add(errorMessage3);
			return false;
			}
		return true;
		}

	// "list_cons" "(" <pe_expr_sequence> ")"
	// dove <pe_expr_sequence>e'una sequenza
	// di espressioni separate da virgole
	public ListCons scanListCons(String string)
		throws ScanException
		{
		try {
			ListCons listCons = new ListCons();
			MyScanner myScanner = new MyScanner(string);
			myScanner.useDelimiter("\\s*\\)\\s*\\z");
			myScanner.skip("\\s*list_cons\\s*\\(\\s*");
			String expSeq = myScanner.next();
			ScanExpSeq scanExpSeq = new ScanExpSeq(this.depth + 1);
			listCons.setListElements(scanExpSeq.scanExpSeq(expSeq));
			myScanner.useDelimiter("\\s*\\z");
			myScanner.skip("\\s*\\)\\s*");
			return listCons;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
