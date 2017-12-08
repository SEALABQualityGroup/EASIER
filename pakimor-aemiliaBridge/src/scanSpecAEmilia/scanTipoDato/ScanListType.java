/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ListType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanListType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanListType(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	public boolean isListType(String string)
		{
		// "list" "(" <normal_type> ")"
		MyScanner s = new MyScanner(string);
		s.useDelimiter("\\s*\\)\\s*\\z");
		try {
			s.skip("\\s*list\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string2 = string + " is not list type";
			this.errorMessage.setErrorMessage(string2);
			String string3 = "\"list\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		String normalType = null;
		try {
			normalType = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string2 = string + " is not list type";
			this.errorMessage.setErrorMessage(string2);
			String string3 = "normal type expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*\\)\\s*")) 
			{
			String string2 = string + " is not list type";
			this.errorMessage.setErrorMessage(string2);
			String string3 = "\")\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanNormalType scanNormalType = new ScanNormalType(this.depth + 1);
		if (!scanNormalType.isNormalType(normalType))
			{
			String string2 = string + " is not list type";
			this.errorMessage.setErrorMessage(string2);
			ErrorMessage errorMessage = scanNormalType.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}
	
	
	public ListType scanListType(String string) throws ScanException
		{
		try {
			// "list" "(" <normal_type> ")"
			ListType listType = new ListType();
			MyScanner s = new MyScanner(string);
			s.useDelimiter("\\s*\\)\\s*\\z");
			s.skip("\\s*list\\s*\\(\\s*");
			String normalType = s.next();
			ScanNormalType scanNormalType = new ScanNormalType(this.depth + 1);
			listType.setType(scanNormalType.scanNormalType(normalType));
			return listType;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
