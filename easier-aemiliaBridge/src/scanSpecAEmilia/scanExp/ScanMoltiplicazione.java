/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.Expression;
import specificheAEmilia.Moltiplicazione;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanMoltiplicazione {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanMoltiplicazione(int depth) 
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
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad un'operazione di moltiplicazione.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isMoltiplicazione(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\*\\s*");
		String op1 = new String();
		String op2 = new String();
		String sd = new String();
		// si scannerizza specifiche in modo che alla
		// parte sinistra di un * ci sia un Eat e alla
		// destra ci sia un Eatf (associativit� sinistra)
		ScanEat scanEat = null;
		ScanEatf scanEatf = null;
		while (s.hasNext())
			{
			// op1 contiene il primo operando
			op1 = op1 + s.next();
			s.useDelimiter("\\s*\\z");
			// se none'presente un * si restitusce false
			try {
				s.skip("\\s*\\*\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not multiplication expression";
				this.errorMessage.setErrorMessage(string);
				String string2 = "\"*\" expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			// sd contiene l'operatore di moltiplicazione
			// incontrato
			sd = MyScanner.precMatch(s);
			// op2 contiene il secondo operando
			try {
				op2 = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not multiplication expression";
				this.errorMessage.setErrorMessage(string);
				String string2 = "second operand expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			// se op1e'un Eat e op2e'un Eatf si restituisce
			// true
			scanEat = new ScanEat(this.depth + 1);
			scanEatf = new ScanEatf(this.depth + 1);
			if (scanEat.isEat(op1) && scanEatf.isEatf(op2))
				return true;
			// si aggiunge a op1 il segno di moltiplicazione
			// incontrato e si prosegue con la
			// scannerizzazione dell'input rimanente,
			// perche' ancora non abbiamo trovato
			// una moltiplicazione corretta
			op1 = op1 + sd;
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\z");
			try {
				s.skip(Pattern.quote(op1));
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not multiplication expression";
				this.errorMessage.setErrorMessage(string);
				String string2 = "first operand expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			s.useDelimiter("\\s*\\*\\s*");
			}
		String string = specifiche + " is not multiplication expression";
		this.errorMessage.setErrorMessage(string);
		List<ErrorMessage> list = this.errorMessage.getCauses();
		if (!scanEat.isEat(op1))
			{
			ErrorMessage errorMessage31 = scanEat.getErrorMessage();
			list.add(errorMessage31);
			return false;
			}
		if (!scanEatf.isEatf(op2))
			{
			ErrorMessage errorMessage4 = scanEatf.getErrorMessage();
			list.add(errorMessage4);
			return false;
			}
		return false;
		}

	/**
	 * Crea un oggetto Moltiplicazione, ottenuto attraverso la
	 * scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Moltiplicazione.
	 * @throws ScanException
	 */
	public Moltiplicazione scanMoltiplicazione(String specifiche)
		throws ScanException
		{
		try {
			// Eat * Eatf
			Moltiplicazione a = new Moltiplicazione();
			Expression op1 = null;
			Expression op2 = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\*\\s*");
			String op1s = new String();
			String op2s = new String();
			String sd = new String();
			// si scannerizza specifiche in modo che alla
			// parte sinistra di un * ci sia un Eat e alla
			// destra ci sia un Eatf (associativit� sinistra)
			while (s.hasNext()) {
				s.next();
				// op1s contiene il primo operando
				op1s = op1s + MyScanner.precMatch(s);
				s.useDelimiter("\\s*\\z");
				s.skip("\\s*\\*\\s*");
				// sd contiene l'operatore di moltiplicazione
				// incontrato
				sd = MyScanner.precMatch(s);
				// op2s contiene il secondo operando
				op2s = s.next();
				// se op1se'un Eat e op2se'un Eatf si restituisce
				// un oggetto Moltiplicazione, ottenuto attraverso
				// la scannerizzazione di op1s e op2s
				ScanEat scanEat = new ScanEat(this.depth + 1);
				ScanEatf scanEatf = new ScanEatf(this.depth + 1);
				if (scanEat.isEat(op1s) && scanEatf.isEatf(op2s)) {
					op1 = scanEat.scanEat(op1s);
					op2 = scanEatf.scanEatf(op2s);
					a.setExpr1(op1);
					a.setExpr2(op2);
					return a;
				}
				// si aggiunge a op1s il segno di moltiplicazione
				// incontrato e si prosegue con la
				// scannerizzazione dell'input rimanente,
				// perche' ancora non abbiamo trovato
				// una moltiplicazione corretta
				op1s = op1s + sd;
				s = new MyScanner(specifiche);
				s.useDelimiter("\\s*\\z");
				s.skip(Pattern.quote(op1s));
				s.useDelimiter("\\s*\\*\\s*");
			}
			return a;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}