/**
 * 
 */
package scanSpecAEmilia.scanIntestazione;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.VariableDeclaration;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanVarSeq {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanVarSeq(int depth) 
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
	 * dichiarazioni di variabili.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isVarSeq(String specifiche)
		{
		/*
		 * <init_var_formal_par_decl_sequence>. puo' essere void
		 */
		// non vengono accettate sequenze che iniziano
		// con una virgola
		if (specifiche.matches("\\s*\\,(.)*")) 
			{
			String string = specifiche + " is not sequence of variable declarations";
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
			String string = specifiche + " is not sequence of variable declarations";
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
		int i = 0;
		while (s.hasNext())
			{
			dic = s.next();
			ScanVar scanVar = new ScanVar(this.depth + 1);
			if (!scanVar.isVar(dic)) 
				{
				boolean dicTrovata = false;
				dic = dic + ",";
				// caso in cui none'stata ancora individuata una dichiarazione di variabile
				// a causa della dichiarazione di una lista
				while (s.hasNext())
					{
					dic = dic + s.next();
					scanVar = new ScanVar(this.depth + 1);
					if (!scanVar.isVar(dic))
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
					String string = specifiche + " is not sequence of variable declarations";
					this.errorMessage.setErrorMessage(string);
					ErrorMessage errorMessage = scanVar.getErrorMessage();
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
			String string = specifiche + " is not sequence of variable declarations";
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

	/**
	 * Scannerizza una sequenza di dichiarazioni di variabili,
	 * generando un array di oggetti VariableDeclaration. I parametri
	 * void vengono sostituiti con null.
	 *
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti VariableDeclaration.
	 * @throws ScanException
	 */
	public VariableDeclaration[] scanDeclVarSeq(String specifiche)
		throws ScanException
		{
		try {
			VariableDeclaration[] dics = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\z");
			if (s.hasNext("\\s*void\\s*")) {
				dics = new VariableDeclaration[1];
				return dics;
			} else {
				s.useDelimiter("\\s*\\,\\s*");
				// si conta il numero di dichiarazioni di parametri
				int cont = 0;
				String string = new String();
				while (s.hasNext()) {
					string = s.next();
					ScanVar scanVar = new ScanVar(this.depth + 1);
					if (!scanVar.isVar(string)) {
						string = string + ",";
						// caso in cui none'stata ancora individuata una dichiarazione di variabile
						// a causa di una possibile dichiarazione di una lista
						while (s.hasNext()) {
							string = string + s.next();
							ScanVar scanVar2 = new ScanVar(this.depth + 1);
							if (!scanVar2.isVar(string)) {
								string = string + ",";
								continue;
							} else {
								break;
							}
						}
						cont++;
					} else
						cont++;
				}
				dics = new VariableDeclaration[cont];
				s = new MyScanner(specifiche);
				s.useDelimiter("\\s*\\,\\s*");
				// si scannerizza la sequenza di dichiarazioni
				// di parametri
				for (int i = 0; i < cont; i++) {
					String st = s.next();
					ScanVar scanVar = new ScanVar(this.depth + 1);
					if (!scanVar.isVar(st)) {
						st = st + ",";
						// caso in cui none'stata ancora individuata una dichiarazione di variabile
						// a causa di una possibile dichiarazione di una lista
						while (s.hasNext()) {
							st = st + s.next();
							ScanVar scanVar2 = new ScanVar(this.depth + 1);
							if (!scanVar2.isVar(st)) {
								st = st + ",";
								continue;
							} else {
								break;
							}
						}
						ScanVar scanVar2 = new ScanVar(this.depth + 1);
						dics[i] = scanVar2.scanVar(st);
					} else
						{
						ScanVar scanVar2 = new ScanVar(this.depth + 1);
						dics[i] = scanVar2.scanVar(st);
						}
				}
				return dics;
			}
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
