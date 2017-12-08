/**
 * 
 */
package scanSpecAEmilia.scanAEIdecl;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanExprSeq {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanExprSeq(int depth) 
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
	 * Restituisce true se e solo se specifichee'una sequenza
	 * di espressioni separate da virgole.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isExprSeq(String specifiche)
		{
		if (specifiche.matches("\\s*\\,(.)*"))
			{
			String string1 = specifiche + " is not sequence of expressions";
			this.errorMessage.setErrorMessage(string1);
			String string = specifiche + " must not begin with \",\"";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (specifiche.matches("(.)*\\,\\s*\\z")) 
			{
			String string1 = specifiche + " is not sequence of expressions";
			this.errorMessage.setErrorMessage(string1);
			String string = specifiche + " must not end with \",\"";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\,\\s*");
		while (s.hasNext())
			{
			String st = new String();
			st = s.next();
			ScanExp scanExp = new ScanExp(this.depth + 1);
			if (!scanExp.isEspressione(st))
				{
				boolean expTrovata = false;
				st = st + ",";
				// caso in cui none'stata ancora individuata una espressione
				// a causa della presenza di una lista
				while (s.hasNext())
					{
					st = st + s.next();
					scanExp = new ScanExp(this.depth + 1);
					if (!scanExp.isEspressione(st))
						{
						st = st + ",";
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
					String string1 = specifiche + " is not sequence of expressions";
					this.errorMessage.setErrorMessage(string1);
					ErrorMessage errorMessage = scanExp.getErrorMessage();
					List<ErrorMessage> list = this.errorMessage.getCauses();
					list.add(errorMessage);
					return false;
					}
				}
			}
		return true;
		}

	/**
	 * Restituisce un array di oggetti Expression, dettato dalla
	 * stringa specifiche.
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti Expression.
	 * @throws ScanException
	 */
	public Expression[] scanExprSeq(String specifiche)
		throws ScanException
		{
		try {
			Expression[] pars = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\,\\s*");
			int contExp = 0;
			String string = new String();
			while (s.hasNext()) {
				string = s.next();
				ScanExp scanExp = new ScanExp(this.depth + 1);
				if (!scanExp.isEspressione(string)) {
					string = string + ",";
					// caso in cui none'stata ancora individuata un'espressione
					// a causa di una possibile dichiarazione di una lista
					while (s.hasNext()) {
						string = string + s.next();
						if (!scanExp.isEspressione(string)) {
							string = string + ",";
							continue;
						} else {
							break;
						}
					}
					contExp++;
				} else
					contExp++;
			}
			pars = new Expression[contExp];
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\,\\s*");
			for (int i = 0; i < contExp; i++) {
				String st = s.next();
				ScanExp scanExp = new ScanExp(this.depth + 1);
				if (!scanExp.isEspressione(st)) {
					st = st + ",";
					// caso in cui none'stata ancora individuata un'espressione
					// a causa della presenza di una lista
					while (s.hasNext()) {
						st = st + s.next();
						if (!scanExp.isEspressione(st)) {
							st = st + ",";
							continue;
						} else {
							break;
						}
					}
					pars[i] = scanExp.scanEspressione(st);
				} else
					pars[i] = scanExp.scanEspressione(st);
			}
			return pars;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}	
}
