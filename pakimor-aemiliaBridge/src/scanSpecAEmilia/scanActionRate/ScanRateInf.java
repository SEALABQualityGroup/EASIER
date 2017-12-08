/**
 * 
 */
package scanSpecAEmilia.scanActionRate;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.ActionRate;
import specificheAEmilia.RateInf;
import specificheAEmilia.Real;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanRateInf {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanRateInf(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}
	
	/**
	 * Restituisce true se e solo se in specifichee'presente il
	 * tasso di un'azione immediata, secondo la grammatica
	 * AEmilia
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isRateInf(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*inf\\s*");
			}
		catch (NoSuchElementException exception)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not infinite rate";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "infinite rate must begin with \"inf\"";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		if (s.hasNext("\\s*\\((.)*"))
			{
			s.useDelimiter("\\s*,\\s*");
			try {
				s.skip("\\s*\\(\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not infinite rate";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "\"(\" expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			// st corrisponde alla priorita' dell'azione
			String st = new String();
			try {
				st = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not infinite rate";
				errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "priority expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
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
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string = specifiche + " is not infinite rate";
					this.errorMessage.setErrorMessage(string);
					ErrorMessage errorMessage2 = scanExp.getErrorMessage();
					list.add(errorMessage2);
					return false;
					}
				}
			s.useDelimiter("\\s*\\)\\s*\\z");
			try {
				s.skip("\\s*,\\s*");
				}
			catch (NoSuchElementException exception)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not infinite rate";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "Expected \",\": there is not \",\" between priority and weight";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			// st1 corrisponde al peso dell'azione
			String st1 = new String();
			try {
				st1 = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not infinite rate";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "weight expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			ScanExp scanExp2 = new ScanExp(this.depth + 1);
			if (!scanExp2.isEspressione(st1))
				{
				boolean expTrovata = false;
				st1 = st1 + ",";
				// caso in cui none'stata ancora individuata una espressione
				// a causa della presenza di una lista
				while (s.hasNext())
					{
					st1 = st1 + s.next();
					scanExp2 = new ScanExp(this.depth + 1);
					if (!scanExp2.isEspressione(st1))
						{
						st1 = st1 + ",";
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
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string = specifiche + " is not infinite rate";
					this.errorMessage.setErrorMessage(string);
					ErrorMessage errorMessage2 = scanExp2.getErrorMessage();
					list.add(errorMessage2);
					return false;
					}
				}
			s.useDelimiter("\\s*\\z");
			if (!s.hasNext("\\s*\\)\\s*")) 
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not infinite rate";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = specifiche + " must end with \")\"";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;				
				}
			}
		return true;
		}
	
	/**
	 * Produce un'oggetto ActionRate secondo le informazioni
	 * contenute in specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ActionRate.
	 * @throws ScanException
	 */
	public ActionRate scanRateInf(String specifiche) throws ScanException
		{
		try {
			MyScanner s = new MyScanner(specifiche);
			RateInf r = new RateInf();
			s.skip("\\s*inf\\s*");
			if (s.hasNext("\\s*\\((.)*")) {
				s.useDelimiter("\\s*,\\s*");
				s.skip("\\s*\\(\\s*");
				// st corrisponde alla priorità dell'azione
				String st = s.next();
				ScanExp scanExp = new ScanExp(this.depth + 1);
				if (!scanExp.isEspressione(st)) 
					{
					st = st + ",";
					// caso in cui none'stata ancora individuata un'espressione
					// a causa della presenza di una lista
					while (s.hasNext()) 
						{
						st = st + s.next();
						if (!scanExp.isEspressione(st)) 
							{
							st = st + ",";
							continue;
							} 
						else 
							{
							break;
							}
						}
					r.setPrio(scanExp.scanEspressione(st));
					} 
				else
					r.setPrio(scanExp.scanEspressione(st));
				s.useDelimiter("\\s*\\)\\s*\\z");
				s.skip("\\s*,\\s*");
				// st1 corrisponde al peso dell'azione
				String st1 = s.next();
				ScanExp scanExp2 = new ScanExp(this.depth + 1);
				if (!scanExp2.isEspressione(st1)) 
					{
					st1 = st1 + ",";
					// caso in cui none'stata ancora individuata un'espressione
					// a causa della presenza di una lista
					while (s.hasNext()) 
						{
						st1 = st1 + s.next();
						if (!scanExp2.isEspressione(st1)) 
							{
							st1 = st1 + ",";
							continue;
							} 
						else 
							{
							break;
							}
						}
					r.setWeight(scanExp2.scanEspressione(st1));
					} 
				else
					r.setWeight(scanExp2.scanEspressione(st1));
				s.skip("\\s*\\)\\s*\\z");
			} else {
				r.setPrio(new specificheAEmilia.Integer(1));
				r.setWeight(new Real(1));
			}
			return r;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
