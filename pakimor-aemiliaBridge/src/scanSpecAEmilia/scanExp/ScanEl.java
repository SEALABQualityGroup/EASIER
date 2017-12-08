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
class ScanEl {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanEl(int depth) 
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
	<expr> ::= "list_cons" "(" <pe_expr_sequence> ")"
	| "first" "(" <identifier> ")"
	| "tail" "(" <identifier> ")"
	| "concat" "(" <identifier> "," <identifier> ")"
	| "insert" "(" <expr> "," <identifier> ")"
	| "remove" "(" <expr> "," <identifier> ")"
	| "length" "(" <identifier> ")"
	 */
	public boolean isEl(String specifiche) 
		{
		/* MODELLED */
		/*
		 * El -> "list_cons" "(" E* ")" | "tail" "(" id ")" | "concat" "(" id 	"," id ")" 
		 * | "insert" "(" E "," id ")" | "remove" "(" E "," id ")"
		 */
		ScanListCons scanListCons = new ScanListCons(this.depth + 1);
		ScanTail scanTail = new ScanTail(this.depth + 1);
		ScanConcat scanConcat = new ScanConcat(this.depth + 1);
		ScanInsert scanInsert = new ScanInsert(this.depth + 1);
		ScanRemove scanRemove = new ScanRemove(this.depth + 1);
		if (scanListCons.isListCons(specifiche)) return true;
		else if (scanTail.isTail(specifiche)) return true;
		else if (scanConcat.isConcat(specifiche)) return true;
		else if (scanInsert.isInsert(specifiche)) return true;
		else if (scanRemove.isRemove(specifiche)) return true;
		else
			{
			String string = specifiche + " is not list expression";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage1 = scanConcat.getErrorMessage();
			ErrorMessage errorMessage2 = scanInsert.getErrorMessage();
			ErrorMessage errorMessage3 = scanListCons.getErrorMessage();
			ErrorMessage errorMessage4 = scanRemove.getErrorMessage();
			ErrorMessage errorMessage5 = scanTail.getErrorMessage();
			list.add(errorMessage1);
			list.add(errorMessage2);
			list.add(errorMessage3);
			list.add(errorMessage4);
			list.add(errorMessage5);
			return false;
			}
		}

	/*
	<expr> ::= "list_cons" "(" <pe_expr_sequence> ")"
	| "first" "(" <identifier> ")"
	| "tail" "(" <identifier> ")"
	| "concat" "(" <identifier> "," <identifier> ")"
	| "insert" "(" <expr> "," <identifier> ")"
	| "remove" "(" <expr> "," <identifier> ")"
	| "length" "(" <identifier> ")"
	 */
	public Expression scanEl(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * El -> "list_cons" "(" E* ")" | "tail" "(" id ")" | "concat" "(" id 	"," id ")" 
			 * | "insert" "(" E "," id ")" | "remove" "(" E "," id ")"
			 */
			ScanListCons scanListCons = new ScanListCons(this.depth + 1);
			ScanTail scanTail = new ScanTail(this.depth + 1);
			ScanConcat scanConcat = new ScanConcat(this.depth + 1);
			ScanInsert scanInsert = new ScanInsert(this.depth + 1);
			ScanRemove scanRemove = new ScanRemove(this.depth + 1);
			if (scanListCons.isListCons(specifiche))
				return scanListCons.scanListCons(specifiche);
			else if (scanTail.isTail(specifiche))
				return scanTail.scanTail(specifiche);
			else if (scanConcat.isConcat(specifiche))
				return scanConcat.scanConcat(specifiche);
			else if (scanInsert.isInsert(specifiche))
				return scanInsert.scanInsert(specifiche);
			else if (scanRemove.isRemove(specifiche))
				return scanRemove.scanRemove(specifiche);
			else
				return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
