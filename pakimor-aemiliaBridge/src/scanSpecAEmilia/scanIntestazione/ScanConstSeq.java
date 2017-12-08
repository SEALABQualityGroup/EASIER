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
class ScanConstSeq {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanConstSeq(int depth) 
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
	 * dichiarazioni di costanti.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isConstSeq(String specifiche)
		{
		/*
		 * <const_formal_par_decl_sequence>e'o void o una
		 * sequenza non vuota di dichiarazioni separate da virgole
		 * di parametri formali costanti.
		 */
		// non vengono accettate sequenze che iniziano
		// con una virgola
		if (specifiche.matches("\\s*\\,(.)*")) 
			{
			String string = specifiche + " is not sequence of constants";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence begin with comma";
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
			String string = specifiche + " is not sequence of constants";
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
		// si verifica ogni dichiarazione di costante
		int i = 0;
		while (s.hasNext())
			{
			dic = s.next();
			ScanConst scanConst = new ScanConst(this.depth + 1);
			if (!scanConst.isConst(dic)) 
				{
				boolean dicTrovata = false;
				dic = dic + ",";
				// caso in cui none'stata ancora individuata una dichiarazione di costante
				// a causa di un'eventuale dichiarazione di lista
				while (s.hasNext())
					{
					dic = dic + s.next();
					ScanConst scanConst2 = new ScanConst(this.depth + 1);
					if (!scanConst2.isConst(dic))
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
					String string = specifiche + " is not sequence of constant declarations";
					this.errorMessage.setErrorMessage(string);
					ErrorMessage errorMessage = scanConst.getErrorMessage();
					List<ErrorMessage> list = this.errorMessage.getCauses();
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
			String string = specifiche + " is not sequence of constant declarations";
			this.errorMessage.setErrorMessage(string);
			String string2 = "at least one constant declaration expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}
}
