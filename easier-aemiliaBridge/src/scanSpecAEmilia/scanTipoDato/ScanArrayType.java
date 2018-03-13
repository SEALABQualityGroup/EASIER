/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.ArrayType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanArrayType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanArrayType(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	public boolean isArrayType(String string)
		{
		// "array" "(" <expr> "," <normal_type> ")"
		MyScanner s = new MyScanner(string);
		s.useDelimiter("\\s*\\,\\s*");
		try {
			s.skip("\\s*array\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string2 = string + " is not array type";
			this.errorMessage.setErrorMessage(string2);
			String string3 = "\"array(\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		String lunghezza = null;
		try {
			lunghezza = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string2 = string + " is not array type";
			this.errorMessage.setErrorMessage(string2);
			String string3 = "expression expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// riconosco l'espressione
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!scanExp.isEspressione(lunghezza))
			{
			boolean expTrovata = false;
			lunghezza += ",";
			// caso in cui none'stata individuata un'espressione
			// a causa della presenza di una lista
			while (s.hasNext())
				{
				lunghezza += s.next();
				scanExp = new ScanExp(this.depth + 1);
				if (!scanExp.isEspressione(lunghezza))
					{
					lunghezza += ",";
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
				String string2 = string + " is not array type";
				this.errorMessage.setErrorMessage(string2);
				ErrorMessage errorMessage = scanExp.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				return false;
				}
			}
		s.useDelimiter("\\s*\\)\\s*\\z");
		try {
			s.skip("\\s*\\,\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string2 = string + " is not array type";
			this.errorMessage.setErrorMessage(string2);
			String string3 = "\",\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// riconosco il tipo di dato
		String tipo = null;
		try {
			tipo = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string2 = string + " is not array type";
			this.errorMessage.setErrorMessage(string2);
			String string3 = "data type expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\z");
		if (!s.hasNext("\\s*\\)\\s*")) 
			{
			String string2 = string + " is not array type";
			this.errorMessage.setErrorMessage(string2);
			String string3 = "\")\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanNormalType scanNormalType = new ScanNormalType(this.depth + 1);
		if (!scanNormalType.isNormalType(tipo))
			{
			String string2 = string + " is not array type";
			this.errorMessage.setErrorMessage(string2);
			ErrorMessage errorMessage = scanNormalType.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}
	
	public ArrayType scanArrayType(String string) throws ScanException
		{
		try {
			// "array" "(" <expr> "," <normal_type> ")"
			ArrayType arrayType = new ArrayType();
			MyScanner s = new MyScanner(string);
			s.useDelimiter("\\s*\\,\\s*");
			s.skip("\\s*array\\s*\\(\\s*");
			// scannerizzo la lunghezza
			String lunghezza = s.next();
			ScanExp scanExp = new ScanExp(this.depth + 1);
			if (!scanExp.isEspressione(lunghezza)) {
				lunghezza = lunghezza + ",";
				// caso in cui none'stata individuata un'espressione
				// a causa della presenza di una lista
				while (s.hasNext()) {
					lunghezza = lunghezza + s.next();
					if (!scanExp.isEspressione(lunghezza)) {
						lunghezza = lunghezza + ",";
						continue;
					} else {
						break;
					}
				}
			}
			s.useDelimiter("\\s*\\)\\s*\\z");
			s.skip("\\s*\\,\\s*");
			// scannerizzo il tipo
			String tipo = s.next();
			ScanNormalType scanNormalType = new ScanNormalType(this.depth + 1);
			arrayType.setLength(scanExp.scanEspressione(lunghezza));
			arrayType.setType(scanNormalType.scanNormalType(tipo));
			return arrayType;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
