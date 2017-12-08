/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Put;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanPut {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanPut(int depth) 
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
	 * "put" "(" <identifier> "," <expr> "," <identifier> ")"
	 */
	public boolean isPut(String specifiche) 
		{
		/* MODELLED */
		MyScanner myScanner = new MyScanner(specifiche);
		try {
			myScanner.skip("\\s*put\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not put expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"put(\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// riconosco la prima espressione
		myScanner.useDelimiter("\\s*\\,\\s*");
		String identifier = null;
		try {
			identifier = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not put expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "identifier expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// riconosco la seconda espressione
		String value = null;
		try {
			value = myScanner.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not put expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "expression expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!scanExp.isEspressione(value))
			{
			boolean expTrovata = false;
			value = value + ",";
			// caso in cui none'stata individuata un'espressione
			// a causa della presenza di una lista
			while (myScanner.hasNext())
				{
				value = value + myScanner.next();
				scanExp = new ScanExp(this.depth + 1);
				if (!scanExp.isEspressione(value))
					{
					value = value + ",";
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
				String string = specifiche + " is not put expression";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage3 = scanExp.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			}
		// riconosco la terza espressione
		myScanner.useDelimiter("\\s*\\)\\s*\\z");
		try {
			myScanner.skip("\\s*\\,\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not put expression";
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
			String string = specifiche + " is not put expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = "expression identifier expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		if (!scanIdent.isIdent(identifier))
			{
			String string = specifiche + " is not put expression";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage31 = scanIdent.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage31);			
			return false;
			}
		ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
		if (!scanIdentExpr.isIdentExpr(record))
			{
			String string = record + " is not expression identifier";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage4 = scanIdentExpr.getErrorMessage();
			list.add(errorMessage4);			
			return false;
			}
		myScanner.useDelimiter("\\s*\\z");
		try {
			myScanner.skip("\\s*\\)\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not write expression";
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
	 * "put" "(" <identifier> "," <expr> "," <identifier> ")"
	 */
	public Put scanPut(String specifiche) 
		throws ScanException
		{
		try {
			Put put = null;
			MyScanner myScanner = new MyScanner(specifiche);
			myScanner.skip("\\s*put\\s*\\(\\s*");
			// scannerizzo il nome del campo del record
			myScanner.useDelimiter("\\s*\\,\\s*");
			String field = myScanner.next();
			// scannerizzo il valore da assegnare al campo del record
			String value = myScanner.next();
			ScanExp scanExp = new ScanExp(this.depth + 1);
			if (!scanExp.isEspressione(value)) {
				value = value + ",";
				// caso in cui none'stata individuata un'espressione
				// a causa della presenza di una lista
				while (myScanner.hasNext()) {
					value = value + myScanner.next();
					ScanExp scanExp2 = new ScanExp(this.depth + 1);
					if (!scanExp2.isEspressione(value)) {
						value = value + ",";
						continue;
					} else {
						break;
					}
				}
			}
			// scannerizzo il record
			myScanner.useDelimiter("\\s*\\)\\s*\\z");
			myScanner.skip("\\s*\\,\\s*");
			String record = myScanner.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			String fieldE = scanIdent.scanIdent(field);
			ScanExp scanExp2 = new ScanExp(this.depth + 1);
			ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
			Expression valueE = scanExp2.scanEspressione(value);
			IdentExpr recordE = scanIdentExpr.scanIdentExpr(record);
			put = new Put(fieldE, valueE, recordE);
			myScanner.useDelimiter("\\s*\\z");
			myScanner.skip("\\s*\\)\\s*");
			return put;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
