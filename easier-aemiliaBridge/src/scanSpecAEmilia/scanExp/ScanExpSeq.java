/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
public class ScanExpSeq {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanExpSeq(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	// expSeq puo' essere una sequenza vuota di espressioni
	public boolean isExpSeq(String specifiche) 
		{
		/* MODELLED */
		if (specifiche.matches("\\s*\\,(.)*")) 
			{
			String string1 = specifiche + " is not sequence of expressions";
			this.errorMessage.setErrorMessage(string1);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string = specifiche + " must not begin with \",\"";
			errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (specifiche.matches("(.)*\\,\\s*\\z")) 
			{
			String string1 = specifiche + " is not sequence of expressions";
			this.errorMessage.setErrorMessage(string1);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string = specifiche + " must not end with \",\"";
			errorMessage.setErrorMessage(string);
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
	
	// expSeq puo' essere una sequenza vuota di espressioni
	public Expression[] scanExpSeq(String expSeq) 
		throws ScanException
		{
		try {
			Expression[] pars = null;
			MyScanner s = new MyScanner(expSeq);
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
						ScanExp scanExp2 = new ScanExp(this.depth + 1);
						if (!scanExp2.isEspressione(string)) {
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
			s = new MyScanner(expSeq);
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
						ScanExp scanExp2 = new ScanExp(this.depth + 1);
						if (!scanExp2.isEspressione(st)) {
							st = st + ",";
							continue;
						} else {
							break;
						}
					}
					ScanExp scanExp2 = new ScanExp(this.depth + 1);
					pars[i] = scanExp2.scanEspressione(st);
				} else
					{
					ScanExp scanExp2 = new ScanExp(this.depth + 1);
					pars[i] = scanExp2.scanEspressione(st);
					}
			}
			return pars;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
