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
class ScanErecord {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanErecord(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	/*
	 * <expr> ::= "record_cons" "(" <expr_sequence> ")"
	 * 	| "get" "(" <identifier> "," <identifier> ")"
	 * 	| "put" "(" <identifier> "," <expr> "," <identifier> ")"
	 */
	public boolean isErecord(String specifiche) 
		{
		/* MODELLED */
		/*
		 * Erecord -> "record_cons" "(" E* ")"
		 * 	| "put" "(" id "," E "," E ")"
		 */
		ScanRecordCons scanRecordCons = new ScanRecordCons(this.depth + 1);
		ScanPut scanPut = new ScanPut(this.depth + 1);
		if (scanRecordCons.isRecordCons(specifiche)) return true;
		else if (scanPut.isPut(specifiche)) return true;
		else 
			{
			String string = specifiche + " is not record expression";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanRecordCons.getErrorMessage();
			ErrorMessage errorMessage2 = scanPut.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			return false;
			}
		}

	/*
	 * <expr> ::= "record_cons" "(" <expr_sequence> ")"
	 * 	| "get" "(" <identifier> "," <identifier> ")"
	 * 	| "put" "(" <identifier> "," <expr> "," <identifier> ")"
	 */
	public Expression scanErecord(String specifiche) 
		throws ScanException 
		{
		try {
			/*
			 * Erecord -> "record_cons" "(" E* ")"
			 * 	| "put" "(" id "," E "," id ")"
			 */
			ScanRecordCons scanRecordCons = new ScanRecordCons(this.depth + 1);
			ScanPut scanPut = new ScanPut(this.depth + 1);
			if (scanRecordCons.isRecordCons(specifiche))
				return scanRecordCons.scanRecordCons(specifiche);
			else if (scanPut.isPut(specifiche))
				return scanPut.scanPut(specifiche);
			else
				return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
