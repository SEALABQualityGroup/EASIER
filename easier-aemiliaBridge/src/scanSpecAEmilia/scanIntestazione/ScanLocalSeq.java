/**
 * 
 */
package scanSpecAEmilia.scanIntestazione;

import java.util.List;

import personalScanner.MyScanner;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanLocalSeq {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanLocalSeq(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	/**
	 * Restituisce true se e solo se specifichee'una sequenza di
	 * dichiarazioni di variabili locali.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isLocalSeq(String specifiche)
		{
		/*
		 * <local_var_decl_sequence>. puo' essere void
		 */
		// non vengono accettate sequenze che iniziano
		// con una virgola
		if (specifiche.matches("\\s*\\,(.)*")) 
			{
			String string = specifiche + " is not sequence of local variables declarations";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence beging with comma";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// non vengono accettate sequenze che terminano
		// con una virgola
		if (specifiche.matches("(.)*\\,\\s*\\z")) 
			{
			String string = specifiche + " is not sequence of local variables declarations";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence end with comma";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		if (s.hasNext("\\s*void\\s*")) return true;
		s.useDelimiter("\\s*\\,\\s*");
		String dic = new String();
		// si verifica ogni dichiarazione di variabile locale
		int i = 0;
		while (s.hasNext())
			{
			dic = s.next();
			ScanLocal scanLocal = new ScanLocal(this.depth + 1);
			if (!scanLocal.isLocal(dic)) 
				{
				boolean dicTrovata = false;
				dic = dic + ",";
				// caso in cui none'stata ancora individuata una dichiarazione di variabile locale
				// a causa di un'eventuale dichiarazione di lista
				while (s.hasNext())
					{
					dic = dic + s.next();
					scanLocal = new ScanLocal(this.depth + 1);
					if (!scanLocal.isLocal(dic))
						{
						dic = dic + ",";
						continue;
						}
					else 
						{
						dicTrovata = true;
						break;
						}
					}
				if (!dicTrovata)
					{
					String string = specifiche + " is not sequence of local variables declarations";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = scanLocal.getErrorMessage();
					list.add(errorMessage);
					return false;
					}
				else
					i++;
				}
			else
				i++;
			}
		if (i == 0)
			{
			String string = specifiche + " is not sequence of local variable declarations";
			this.errorMessage.setErrorMessage(string);
			String string2 = "at least one local variable declaration expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}
}
