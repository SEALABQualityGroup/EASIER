/**
 * 
 */
package scanSpecAEmilia.scanAETbehavior;

import java.util.InputMismatchException;
import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanBehavEquation;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.BehavEquation;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanBES {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanBES(int depth) 
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
	 * Restituisce true se e solo se specifichee'una sequenza non
	 * vuota di equazioni comportamentali EMPA
	 * (oggetti BehavEquation) separate da punti e virgola.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isBES(String specifiche)
		{
		/*
		 * <behav_equation_sequence>e'una sequenza non vuota di equazioni
		 * comportamentali EMPA (oggetti BehavEquation) separate da punti e virgole.
		 */
		// non sono accettate sequenze vuote
		if (specifiche.matches(new String())) 
			{
			String string = specifiche + " is not sequence of behavioral equations";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = specifiche + " must not be empty sequence";
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// non sono accettate sequenze che iniziano con un
		// punto e virgola
		if (specifiche.matches("\\s*\\;(.)*")) 
			{
			String string = specifiche + " is not sequence of behavioral equations";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = specifiche + " must not begin with \";\"";
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// non sono accettate sequenze che terminano con un
		// punto e virgola
		if (specifiche.matches("(.)*\\;\\s*\\z")) 
			{
			String string = specifiche + " is not sequence of behavioral equations";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = specifiche + " must not end with \";\"";
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);			
			return false;
			}
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\;\\s*");
		String be = new String();
		// si verifica che ogni sottostringa tra due punti
		// e virgola (senza considerare il punto e virgola
		// dell'intestazione) sia un'equazione comportamentale
		int c = 0;
		while (s.hasNext())
			{
			be = s.next();
			ScanBehavEquation scanBehavEquation = new ScanBehavEquation(this.depth + 1);
			if (!scanBehavEquation.isBehavEquation(be))
				{
				boolean behTrovata = false;
				be = be + ";";
				// caso in cui none'stata ancora individuata una equazione comportamentale
				// a causa della presenza del ; presente nell'intestazione dell'equazione comportamentale
				while (s.hasNext())
					{
					be = be + s.next();
					scanBehavEquation = new ScanBehavEquation(this.depth + 1);
					if (!scanBehavEquation.isBehavEquation(be))
						{
						be = be + ";";
						continue;
						}
					else
						{
						behTrovata = true;
						c++;
						break;
						}
					}
				if (!behTrovata)
					{
					String string = specifiche + " is not sequence of behavioral equations";
					this.errorMessage.setErrorMessage(string);
					ErrorMessage errorMessage = scanBehavEquation.getErrorMessage();
					List<ErrorMessage> list = this.errorMessage.getCauses();
					list.add(errorMessage);
					return false;
					}
				}
			else
				c++;
			}
		if (c == 0) 
			{
			String string = specifiche + " is not sequence of behavioral equations";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = "not empty sequence expected";
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Scannerizza una sequenza di equazioni comportamentali
	 * EMPA separate da punti e virgola, generando un array
	 * di oggetti BehavEquation.
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti BehavEquation.
	 * @throws ScanException
	 */
	public BehavEquation[] scanBES(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * <behav_equation_sequence>e'una sequenza non vuota di equazioni
			 * comportamentali EMPA (oggetti BehavEquation) separate da punti e virgole.
			 */
			BehavEquation[] bes = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\;\\s*");
			String b = new String();
			int c = 0;
			// non sono accettate sequenze vuote
			if (specifiche.matches(new String()))
				throw new InputMismatchException();
			// non sono accettate sequenze che iniziano con un
			// punto e virgola
			if (specifiche.matches("\\s*\\;(.)*"))
				throw new InputMismatchException();
			// non sono accettate sequenze che terminano con un
			// punto e virgola
			if (specifiche.matches("(.)*\\;\\s*\\z"))
				throw new InputMismatchException();
			// si conta il numero di equazioni comportamentali
			while (s.hasNext()) {
				// bisogna considerare anche il ; dell'beh
				b = s.next() + ";";
				b = b + s.next();
				c++;
			}
			// il numero delle equazioni comportamentali deve essere
			// maggiore di 0
			bes = new BehavEquation[c];
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\;\\s*");
			// si scannerizzano tutte le equazioni
			// comportamentali
			for (int i = 0; i < bes.length; i++) {
				b = s.next() + ";";
				b = b + s.next();
				ScanBehavEquation scanBehavEquation = new ScanBehavEquation(this.depth + 1);
				bes[i] = scanBehavEquation.scanBehavEquation(b);
			}
			return bes;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
