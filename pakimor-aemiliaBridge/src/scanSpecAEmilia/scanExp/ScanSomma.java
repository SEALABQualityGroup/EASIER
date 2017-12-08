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
import specificheAEmilia.Somma;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanSomma {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanSomma(int depth) 
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
	 * ad un'operazione di somma.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
public boolean isSomma(String specifiche)
		{
		/* MODELLED */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\+\\s*");
		String op1 = new String();
		String op2 = new String();
		String sd = new String();
		// si scannerizza specifiche in modo che alla
		// parte sinistra di un + ci sia un Ea e alla
		// destra ci sia un Eat (associativit� sinistra)
		ScanEa scanEa = null;
		ScanEat scanEat = null;
		while (s.hasNext())
			{
			// op1 contiene il primo operando
			op1 = op1 + s.next();
			s.useDelimiter("\\s*\\z");
			// se none'presente un + si restitusce false
			try {
				s.skip("\\s*\\+\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not sum expression";
				this.errorMessage.setErrorMessage(string);
				String string2 = "\"+\" expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			// sd contiene l'operatore di somma
			// incontrato
			sd = MyScanner.precMatch(s);
			// op2 contiene il secondo operando
			try {
				op2 = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not sum expression";
				this.errorMessage.setErrorMessage(string);
				String string2 = "second operand expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			// se op1e'un Ea e op2e'un Eat si restituisce
			// true
			scanEa = new ScanEa(this.depth + 1);
			scanEat = new ScanEat(this.depth + 1);
			if (scanEa.isEa(op1) && scanEat.isEat(op2))
				return true;
			// si aggiunge a op1 il segno di somma
			// incontrato e si prosegue con la
			// scannerizzazione dell'input rimanente,
			// perche' ancora non abbiamo trovato
			// una somma corretta
			op1 = op1 + sd;
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\z");
			try {
				s.skip(Pattern.quote(op1));
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not sum expression";
				this.errorMessage.setErrorMessage(string);
				String string2 = "first operand expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			s.useDelimiter("\\s*\\+\\s*");
			}
		String string = specifiche + " is not sum expression";
		this.errorMessage.setErrorMessage(string);
		List<ErrorMessage> list = this.errorMessage.getCauses();
		if (!scanEa.isEa(op1))
			{
			ErrorMessage errorMessage31 = scanEa.getErrorMessage();
			list.add(errorMessage31);
			return false;
			}
		if (!scanEat.isEat(op2))
			{
			ErrorMessage errorMessage4 = scanEat.getErrorMessage();
			list.add(errorMessage4);
			return false;
			}
		return false;
		}

/**
 * Crea un oggetto Somma, ottenuto attraverso la
 * scannerizzazione di specifiche.
 *
 * @param specifiche - oggetto String.
 * @return un oggetto Somma.
 * @throws ScanException
 */
public Somma scanSomma(String specifiche)
	throws ScanException
	{
	try {
		// Ea + Eat
		Somma a = new Somma();
		Expression op1 = null;
		Expression op2 = null;
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\+\\s*");
		String op1s = new String();
		String op2s = new String();
		String sd = new String();
		// si scannerizza specifiche in modo che alla
		// parte sinistra di un + ci sia un Ea e alla
		// destra ci sia un Eat (associativit� sinistra)
		while (s.hasNext()) {
			s.next();
			// op1s contiene il primo operando
			op1s = op1s + MyScanner.precMatch(s);
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*\\+\\s*");
			// sd contiene l'operatore di somma
			// incontrato
			sd = MyScanner.precMatch(s);
			// op2s contiene il secondo operando
			op2s = s.next();
			// se op1se'un Ea e op2se'un Eat si restituisce
			// un oggetto Somma, ottenuto attraverso
			// la scannerizzazione di op1s e op2s
			ScanEa scanEa = new ScanEa(this.depth + 1);
			ScanEat scanEat = new ScanEat(this.depth + 1);
			if (scanEa.isEa(op1s) && scanEat.isEat(op2s)) {
				op1 = scanEa. scanEa(op1s);
				op2 = scanEat.scanEat(op2s);
				a.setExpr1(op1);
				a.setExpr2(op2);
				return a;
			}
			// si aggiunge a op1s il segno di somma
			// incontrato e si prosegue con la
			// scannerizzazione dell'input rimanente,
			// perche' ancora non abbiamo trovato
			// una somma corretta
			op1s = op1s + sd;
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\z");
			s.skip(Pattern.quote(op1s));
			s.useDelimiter("\\s*\\+\\s*");
		}
		return a;
		} 
	catch (Exception e) 
		{
		throw new ScanException(e);
		}
	}
}
