/**
 * 
 */
package scanSpecAEmilia.scanActionRate;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ActionRate;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanActionRate {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanActionRate(int depth) {
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * Restituisce true se e solo se specifichee'un tasso azione secondo la
	 * grammatica AEmilia.
	 * 
	 * @param specifiche
	 *            - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isActionRate(String specifiche) {
		ScanRateExp scanRateExp = new ScanRateExp(this.depth + 1);
		ScanRateInf scanRateInf = new ScanRateInf(this.depth + 1);
		ScanRate_ scanRate_ = new ScanRate_(this.depth + 1);
		if (!(scanRateExp.isRateExp(specifiche) || scanRateInf.isRateInf(specifiche)
				|| scanRate_.isRate_(specifiche))) {
			String string = specifiche + " is not action rate";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanRate_.getErrorMessage();
			ErrorMessage errorMessage2 = scanRateExp.getErrorMessage();
			ErrorMessage errorMessage3 = scanRateInf.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			list.add(errorMessage3);
			return false;
		} else
			return true;
	}

	/**
	 * Restituisce un oggetto ActionRate in accordo alle informazioni contenute in
	 * specifiche.
	 * 
	 * @param specifiche
	 *            - oggetto String.
	 * @return un oggetto ActionRate.
	 * @throws ScanException
	 */
	public ActionRate scanActionRate(String specifiche) throws ScanException {
		try {
			/*
			 * <action_rate> ::= "exp" "(" <expr> ")" | "inf" "(" <expr> "," <expr> ")" |
			 * "inf" | "_" "(" <expr> "," <expr> ")" | "_"
			 */
			ActionRate r = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*[(>]\\s*");
			// se specifiche contiene exp allora corrisponde
			// ad un tasso di un'azione temporizzata
			// esponenzialmente
			ScanRateExp scanRateExp = new ScanRateExp(this.depth + 1);
			ScanRateInf scanRateInf = new ScanRateInf(this.depth + 1);
			ScanRate_ scanRate_ = new ScanRate_(this.depth + 1);
			if (s.hasNext("\\s*exp\\s*"))
				r = scanRateExp.scanRateExp(specifiche);
			// se specifiche contiene inf allora corrisponde
			// al tasso di un'azione immediata
			else if (s.hasNext("\\s*inf\\s*"))
				r = scanRateInf.scanRateInf(specifiche);
			// se specifiche contiene _ allora corrisponde
			// al tasso di un'azione passiva
			else if (s.hasNext("\\s*_\\s*"))
				r = scanRate_.scanRate_(specifiche);
			else
				throw new ScanException(specifiche + " is not ActionRate object");
			return r;
		} catch (Exception e) {
			throw new ScanException(e);
		}
	}

	public ErrorMessage getErrorMessage() {
		return this.errorMessage;
	}
}
