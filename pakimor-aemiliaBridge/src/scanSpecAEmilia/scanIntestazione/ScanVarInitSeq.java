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
class ScanVarInitSeq {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanVarInitSeq(int depth) 
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
	 * dichiarazioni di variabili inizializzate.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isVarInitSeq(String specifiche)
		{
		/*
		 * <init_var_formal_par_decl_sequence>. puo' essere void
		 */
		// non vengono accettate sequenze che iniziano
		// con una virgola
		if (specifiche.matches("\\s*\\,(.)*")) 
			{
			String string = specifiche + " is not sequence of initialized variable declaration";
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
			String string = specifiche + " is not sequence of initialized variable declaration";
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
		// si verifica ogni dichiarazione di variabile
		// inizializzata
		int i = 0;
		while (s.hasNext())
			{
			dic = s.next();
			ScanVarInit scanVarInit = new ScanVarInit(this.depth + 1);
			if (!scanVarInit.isVarInit(dic)) 
				{
				boolean dicTrovata = false;
				dic = dic + ",";
				// caso in cui none'stata ancora individuata una dichiarazione di variabile inizializzata
				// a causa della dichiarazione di una lista
				while (s.hasNext())
					{
					dic = dic + s.next();
					scanVarInit = new ScanVarInit(this.depth + 1);
					if (!scanVarInit.isVarInit(dic))
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
					String string = specifiche + " is not sequence of initialized variable declaration";
					this.errorMessage.setErrorMessage(string);
					ErrorMessage errorMessage = scanVarInit.getErrorMessage();
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
			String string = specifiche + " is not sequence of initialized variable declaration";
			this.errorMessage.setErrorMessage(string);
			String string2 = "at least one variable declaration expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}
}
