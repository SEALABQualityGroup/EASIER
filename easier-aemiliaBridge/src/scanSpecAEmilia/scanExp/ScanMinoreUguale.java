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
import specificheAEmilia.MinoreUguale;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanMinoreUguale {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanMinoreUguale(int depth) 
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
	 * ad un'operazione booleana di minore o uguale.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isMinoreUguale(String specifiche)
		{
		/* MODELLED */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\<\\s*\\=\\s*");
		String op1 = new String();
		String op2 = new String();
		String sd = new String();
		// si scannerizza specifiche in modo che alla
		// parte sinistra di un <= ci sia un Ea e alla
		// destra ci sia un Ea (associativit� sinistra)
		ScanEa scanEa = null;
		ScanEa scanEa2 = null;
		while (s.hasNext())
			{
			// op1 contiene il primo operando
			op1 = op1 + s.next();
			s.useDelimiter("\\s*\\z");
			// se none'presente un <= si restitusce false
			try {
				s.skip("\\s*\\<\\s*\\=\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not less than or equal expression";
				this.errorMessage.setErrorMessage(string);
				String string2 = "\"<=\" expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			// sd contiene l'operatore di minore o uguale
			// incontrato
			sd = MyScanner.precMatch(s);
			// op2 contiene il secondo operando
			try {
				op2 = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not less than or equal expression";
				this.errorMessage.setErrorMessage(string);
				String string2 = "second operand expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			// se op1e'un Ea e op2e'un Ea si restituisce
			// true
			scanEa = new ScanEa(this.depth + 1);
			scanEa2 = new ScanEa(this.depth + 1);
			if (scanEa.isEa(op1) && scanEa2.isEa(op2))
				return true;
			// si aggiunge a op1 il segno di minore o uguale
			// incontrato e si prosegue con la
			// scannerizzazione dell'input rimanente,
			// perche' ancora non abbiamo trovato
			// un'operazione di minore o uguale corretta
			op1 = op1 + sd;
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\z");
			try {
				s.skip(Pattern.quote(op1));
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not less than or equal expression";
				this.errorMessage.setErrorMessage(string);
				String string2 = "first operand expected";
				ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
				errorMessage3.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage3);
				return false;
				}
			s.useDelimiter("\\s*\\<\\s*\\=\\s*");
			}
		String string = specifiche + " is not less than or equal expression";
		this.errorMessage.setErrorMessage(string);
		List<ErrorMessage> list = this.errorMessage.getCauses();
		if (!scanEa.isEa(op1))
			{
			ErrorMessage errorMessage31 = scanEa.getErrorMessage();
			list.add(errorMessage31);
			return false;
			}
		else if (!scanEa2.isEa(op2))
			{
			ErrorMessage errorMessage4 = scanEa2.getErrorMessage();
			list.add(errorMessage4);
			return false;
			}
		return false;
		}

	/**
	 * Crea un oggetto MinoreUguale, ottenuto attraverso la
	 * scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto MinoreUguale.
	 * @throws ScanException
	 */
	public MinoreUguale scanMinoreUguale(String specifiche)
		throws ScanException
		{
		try {
			// Ea <= Ea
			MinoreUguale a = new MinoreUguale();
			Expression op1 = null;
			Expression op2 = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\<\\s*\\=\\s*");
			String op1s = new String();
			String op2s = new String();
			String sd = new String();
			// si scannerizza specifiche in modo che alla
			// parte sinistra di un <= ci sia un Ea e alla
			// destra ci sia un Ea (associativit� sinistra)
			while (s.hasNext()) {
				s.next();
				// op1s contiene il primo operando
				op1s = op1s + MyScanner.precMatch(s);
				s.useDelimiter("\\s*\\z");
				s.skip("\\s*\\<\\s*\\=\\s*");
				// sd contiene l'operatore di minore o uguale
				// incontrato
				sd = MyScanner.precMatch(s);
				// op2s contiene il secondo operando
				op2s = s.next();
				// se op1se'un Ea e op2se'un Ea si restituisce
				// un oggetto MinoreUguale, ottenuto attraverso
				// la scannerizzazione di op1s e op2s 
				ScanEa scanEa = new ScanEa(this.depth + 1);
				ScanEa scanEa2 = new ScanEa(this.depth + 1);
				if (scanEa.isEa(op1s) && scanEa2.isEa(op2s)) {
					op1 = scanEa.scanEa(op1s);
					op2 = scanEa2.scanEa(op2s);
					a.setExpr1(op1);
					a.setExpr2(op2);
					return a;
				}
				// si aggiunge a op1s il segno di minore o uguale
				// incontrato e si prosegue con la
				// scannerizzazione dell'input rimanente,
				// perche' ancora non abbiamo trovato
				// un'operazione di minore o uguale corretta
				op1s = op1s + sd;
				s = new MyScanner(specifiche);
				s.useDelimiter("\\s*\\z");
				s.skip(Pattern.quote(op1s));
				s.useDelimiter("\\s*\\<\\s*\\=\\s*");
			}
			return a;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
