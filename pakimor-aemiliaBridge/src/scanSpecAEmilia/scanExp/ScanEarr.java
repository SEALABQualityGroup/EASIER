/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanEarr {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanEarr(int depth) {
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	/*
	 * <expr> ::= "array_cons" "(" <expr_sequence> ")" | "read" "(" <expr> ","
	 * <identifier> ")" | "write" "(" <expr> "," <expr> "," <identifier> ")"
	 */
	public boolean isEarr(String specifiche) {
		/* MODELLED */
		/*
		 * Earr -> "array_cons" "(" E* ")" | "write" "(" E "," E "," id ")"
		 */
		ScanArrayCons scanArrayCons = new ScanArrayCons(this.depth + 1);
		ScanWrite scanWrite = new ScanWrite(this.depth + 1);
		if (scanArrayCons.isArrayCons(specifiche))
			return true;
		else if (scanWrite.isWrite(specifiche))
			return true;
		else {
			String string = specifiche + " is not array expression";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanArrayCons.getErrorMessage();
			ErrorMessage errorMessage2 = scanWrite.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			return false;
		}
	}

	/*
	 * <expr> ::= "array_cons" "(" <expr_sequence> ")" | "read" "(" <expr> ","
	 * <identifier> ")" | "write" "(" <expr> "," <expr> "," <identifier> ")"
	 */
	public Expression scanEarr(String specifiche) throws ScanException {
		try {
			/*
			 * Earr -> "array_cons" "(" E* ")" | "write" "(" E "," E "," id ")"
			 */
			ScanArrayCons scanArrayCons = new ScanArrayCons(this.depth + 1);
			ScanWrite scanWrite = new ScanWrite(this.depth + 1);
			if (scanArrayCons.isArrayCons(specifiche))
				return scanArrayCons.scanArrayCons(specifiche);
			else if (scanWrite.isWrite(specifiche))
				return scanWrite.scanWrite(specifiche);
			else
				return null;
		} catch (Exception e) {
			throw new ScanException(e);
		}
	}

}
