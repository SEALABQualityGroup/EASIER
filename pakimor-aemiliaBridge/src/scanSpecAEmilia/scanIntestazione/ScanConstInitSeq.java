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
class ScanConstInitSeq {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanConstInitSeq(int depth) 
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
	 * dichiarazioni di costanti inizializzate.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isConstInitSeq(String specifiche)
		{
		/*
		 * <init_const_formal_par_decl_sequence>e'o void o una
		 * sequenza non vuota di dichiarazioni separate da
		 * virgole di parametri formali costanti inizializzati.
		 */
		// non vengono accettate sequenze che iniziano
		// con una virgola
		if (specifiche.matches("\\s*\\,(.)*")) 
			{
			String string = specifiche + " is not sequence of initilized constants";
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
			String string = specifiche + " is not sequence of initilized constants";
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
		// inizializzata
		while (s.hasNext())
			{
			dic = s.next();
			ScanConstInit scanConstInit = new ScanConstInit(this.depth + 1);
			if (!scanConstInit.isConstInit(dic)) 
				{
				boolean dicTrovata = false;
				dic = dic + ",";
				// caso in cui none'stata ancora individuata una dichiarazione di costante inizializzata
				// a causa della dichiarazione di una lista
				while (s.hasNext())
					{
					dic = dic + s.next();
					ScanConstInit scanConstInit2 = new ScanConstInit(this.depth + 1);
					if (!scanConstInit2.isConstInit(dic))
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
					String string = specifiche + " is not sequence of initilized constants";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = scanConstInit.getErrorMessage();
					list.add(errorMessage);
					return false;
					}
				}
			}
		return true;
		}
	}
