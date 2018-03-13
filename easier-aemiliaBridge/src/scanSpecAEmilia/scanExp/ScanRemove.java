/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.Remove;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanRemove {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanRemove(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	// "remove" "(" <expr> "," <identifier> ")"
	public boolean isRemove(String specifiche)
		{
		/* MODELLED */
		MyScanner myScanner = new MyScanner(specifiche);
		try {
			myScanner.skip("\\s*remove\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not remove expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"remove(\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// riconosco la prima espressione
		myScanner.useDelimiter("\\s*\\,\\s*");
		String position = null;
		try {
			position = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not remove expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "position expression expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!scanExp.isEspressione(position))
			{
			boolean expTrovata = true;
			position = position + ",";
			// cao in cui none'stata individuata un'espressione
			// a causa della presenza di una lista
			ScanExp scanExp2 = new ScanExp(this.depth + 1);
			while (myScanner.hasNext())
				{
				position = position + myScanner.next();
				scanExp2 = new ScanExp(this.depth + 1);
				if (!scanExp2.isEspressione(position))
					{
					position = position + ",";
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
				String string = specifiche + " is not remove expression";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage3 = scanExp2.getErrorMessage();
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
			String string = specifiche + " is not remove expression";
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
			String string = specifiche + " is not remove expression";
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
			String string2 = specifiche + " is not remove expression";
			this.errorMessage.setErrorMessage(string2);
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
			String string = specifiche + " is not remove expression";
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
	
	// "remove" "(" <expr> "," <identifier> ")"
	public Remove scanRemove(String string)
		throws ScanException
		{
		try {
			Remove remove = new Remove();
			MyScanner myScanner = new MyScanner(string);
			myScanner.skip("\\s*remove\\s*\\(\\s*");
			// scannerizzo la prima espressione
			myScanner.useDelimiter("\\s*\\,\\s*");
			String position = myScanner.next();
			ScanExp scanExp = new ScanExp(this.depth + 1);
			if (!scanExp.isEspressione(position)) {
				position = position + ",";
				// caso in cui none'stata individuata un'espressione
				// a causa della presenza di una lista
				while (myScanner.hasNext()) {
					position = position + myScanner.next();
					ScanExp scanExp2 = new ScanExp(this.depth + 1);
					if (!scanExp2.isEspressione(position)) {
						position = position + ",";
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
			remove.setPosition(scanExp2.scanEspressione(position));
			remove.setList(scanIdentExpr.scanIdentExpr(list));
			myScanner.useDelimiter("\\s*\\z");
			myScanner.skip("\\s*\\)\\s*");
			return remove;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
