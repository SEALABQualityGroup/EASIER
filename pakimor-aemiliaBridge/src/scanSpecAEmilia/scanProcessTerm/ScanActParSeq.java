/**
 * 
 */
package scanSpecAEmilia.scanProcessTerm;

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
class ScanActParSeq {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanActParSeq(int depth) 
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
	 * dichiarazioni di parametri attuali separate da virgole.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isActParSeq(String specifiche)
		{
		if (specifiche.matches("\\s*\\,(.)*")) 
			{
			String string = specifiche + " is not sequence of actual parameters";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence begin with comma";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (specifiche.matches("(.)*\\,\\s*\\z")) 
			{
			String string = specifiche + " is not sequence of actual parameters";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence end with comma";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\,\\s*");
		while (s.hasNext())
			{
			String st = null;
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
	 * Scannerizza una sequenza di parametri attuali,
	 * generando un array di oggetti Expression.
	 *
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti Expression.
	 * @throws ScanException
	 */
	public Expression[] scanActParSeq(String specifiche)
		throws ScanException
		{
		try {
			// <actual_par_sequence>
			Expression[] pars = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\,\\s*");
			int contExp = 0;
			while (s.hasNext()) {
				contExp++;
				s.next();
			}
			pars = new Expression[contExp];
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\,\\s*");
			for (int i = 0; i < contExp; i++) {
				String st = s.next();
				ScanExp scanExp = new ScanExp(this.depth + 1);
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
