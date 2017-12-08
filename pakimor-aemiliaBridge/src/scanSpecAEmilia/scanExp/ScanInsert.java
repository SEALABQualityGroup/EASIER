/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.Insert;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
public class ScanInsert {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanInsert(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	// "insert" "(" <expr> "," <identifier> ")"
	public boolean isInsert(String specifiche)
		{
		/* MODELLED */
		MyScanner myScanner = new MyScanner(specifiche);
		try {
			myScanner.skip("\\s*insert\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not insert expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"insert(\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// riconosco la prima espressione
		myScanner.useDelimiter("\\s*\\,\\s*");
		String item = null;
		try {
			item = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not insert expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "item expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!scanExp.isEspressione(item))
			{
			boolean expTrovata = true;
			item = item + ",";
			// caso in cui none'stata individuata un'espressione
			// a causa della presenza di una lista
			while (myScanner.hasNext())
				{
				item = item + myScanner.next();
				scanExp = new ScanExp(this.depth + 1);
				if (!scanExp.isEspressione(item))
					{
					item = item + ",";
					continue;
					}
				else
					{
					expTrovata = true;
					break;
					}
				}
			if (!expTrovata)
				{
				String string = specifiche + " is not insert expression";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage3 = scanExp.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			}
		// riconosco l'identificatore
		myScanner.useDelimiter("\\s*\\)\\s*\\z");
		try {
			myScanner.skip("\\s*\\,\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not insert expression";
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
			String string = specifiche + " is not insert expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "list identifier expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
		if (!scanIdentExpr.isIdentExpr(list21))
			{
			String string = specifiche + " is not insert expression";
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
			String string = specifiche + " is not insert expression";
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
	
	// "insert" "(" <expr> "," <identifier> ")"
	public Insert scanInsert(String string)
		throws ScanException
		{
		try {
			Insert insert = new Insert();
			MyScanner myScanner = new MyScanner(string);
			myScanner.skip("\\s*insert\\s*\\(\\s*");
			// scannerizzo la prima espressione
			myScanner.useDelimiter("\\s*\\,\\s*");
			String item = myScanner.next();
			ScanExp scanExp = new ScanExp(this.depth + 1);
			if (!scanExp.isEspressione(item)) {
				item = item + ",";
				// caso in cui none'stata individuata un'espressione
				// a causa della presenza di una lista
				while (myScanner.hasNext()) {
					item = item + myScanner.next();
					ScanExp scanExp2 = new ScanExp(this.depth + 1);
					if (!scanExp2.isEspressione(item)) {
						item = item + ",";
						continue;
					} else {
						break;
					}
				}
			}
			// scannerizzo la seconda espressione
			myScanner.useDelimiter("\\s*\\)\\s*\\z");
			myScanner.skip("\\s*\\,\\s*");
			String list = myScanner.next();
			ScanExp scanExp2 = new ScanExp(this.depth + 1);
			ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
			insert.setItem(scanExp2.scanEspressione(item));
			insert.setList(scanIdentExpr.scanIdentExpr(list));
			myScanner.useDelimiter("\\s*\\z");
			myScanner.skip("\\s*\\)\\s*");
			return insert;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
