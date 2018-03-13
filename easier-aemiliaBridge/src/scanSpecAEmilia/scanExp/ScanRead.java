/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Read;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanRead {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanRead(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	// "read" "(" <expr> "," <identifier> ")"
	public boolean isRead(String specifiche)
		{
		/* MODELLED */
		MyScanner myScanner = new MyScanner(specifiche);
		try {
			myScanner.skip("\\s*read\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not read expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"read(\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// riconosco la prima espressione
		myScanner.useDelimiter("\\s*\\,\\s*");
		String index = null;
		try {
			index = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not read expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "expression expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!scanExp.isEspressione(index))
			{
			boolean expTrovata = false;
			index = index + ",";
			// caso in cui none'stata individuata un'espressione
			// a causa della presenza di una lista
			while (myScanner.hasNext())
				{
				index = index + myScanner.next();
				scanExp = new ScanExp(this.depth + 1);
				if (!scanExp.isEspressione(index))
					{
					index = index + ",";
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
				String string = specifiche + " is not read expression";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage3 = scanExp.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			}
		// riconosco la seconda espressione
		myScanner.useDelimiter("\\s*\\)\\s*\\z");
		try {
			myScanner.skip("\\s*\\,\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not read expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\",\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		String array = null;
		try {
			array = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not read expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "array identifier expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
		if (!scanIdentExpr.isIdentExpr(array))
			{
			String string = array + " is not identifier expression";
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
			String string = specifiche + " is not read expression";
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
	
	// "read" "(" <expr> "," <identifier> ")"
	public Read scanRead(String string)
		throws ScanException
		{
		try {
			Read read = null;
			MyScanner myScanner = new MyScanner(string);
			myScanner.skip("\\s*read\\s*\\(\\s*");
			// scannerizzo la prima espressione
			myScanner.useDelimiter("\\s*\\,\\s*");
			String index = myScanner.next();
			ScanExp scanExp = new ScanExp(this.depth + 1);
			if (!scanExp.isEspressione(index)) {
				index = index + ",";
				// caso in cui none'stata individuata un'espressione
				// a causa della presenza di una lista
				while (myScanner.hasNext()) {
					index = index + myScanner.next();
					ScanExp scanExp2 = new ScanExp(this.depth + 1);
					if (!scanExp2.isEspressione(index)) {
						index = index + ",";
						continue;
					} else {
						break;
					}
				}
			}
			// scannerizzo la seconda espressione
			myScanner.useDelimiter("\\s*\\)\\s*\\z");
			myScanner.skip("\\s*\\,\\s*");
			String array = myScanner.next();
			ScanExp scanExp2 = new ScanExp(this.depth + 1);
			ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
			Expression indexE = scanExp2.scanEspressione(index);
			IdentExpr arrayE = scanIdentExpr.scanIdentExpr(array);
			read = new Read(indexE, arrayE);
			myScanner.useDelimiter("\\s*\\z");
			myScanner.skip("\\s*\\)\\s*");
			return read;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
