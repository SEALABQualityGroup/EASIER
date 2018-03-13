/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.NormalType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanNormalType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanNormalType(int depth) 
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
	 * ad un tipo di dato normale, in accordo alla grammatica
	 * AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isNormalType(String specifiche)
		{
		/*
		 * <normal_type> ::= "integer"
		 * | "integer" "(" <expr> ".." <expr> ")"
		 * | "real"
		 * | "boolean"
		 * | "list" "(" <normal_type> ")"
		 * | "array" "(" <expr> "," <normal_type> ")"
		 * | "record" "(" <field_decl_sequence> ")"
		 */
		ScanIntegerType scanIntegerType = new ScanIntegerType(this.depth + 1);
		ScanIntervalloInt scanIntervalloInt = new ScanIntervalloInt(this.depth + 1);
		ScanRealType scanRealType = new ScanRealType(this.depth + 1);
		ScanBooleanType scanBooleanType = new ScanBooleanType(this.depth + 1);
		ScanListType scanListType = new ScanListType(this.depth + 1);
		ScanArrayType scanArrayType = new ScanArrayType(this.depth + 1);
		ScanRecordType scanRecordType = new ScanRecordType(this.depth + 1);
		if (!(scanIntegerType.isIntegerType(specifiche) || scanIntervalloInt.isIntervalloInt(specifiche) ||
				scanRealType.isRealType(specifiche) || scanBooleanType.isBooleanType(specifiche) || scanListType.isListType(specifiche) ||
				scanArrayType.isArrayType(specifiche) || scanRecordType.isRecordType(specifiche)))
			{
			String string = specifiche + " is not normal type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanArrayType.getErrorMessage();
			ErrorMessage errorMessage2 = scanBooleanType.getErrorMessage();
			ErrorMessage errorMessage3 = scanIntegerType.getErrorMessage();
			ErrorMessage errorMessage4 = scanIntervalloInt.getErrorMessage();
			ErrorMessage errorMessage5 = scanListType.getErrorMessage();
			ErrorMessage errorMessage6 = scanRealType.getErrorMessage();
			ErrorMessage errorMessage7 = scanRecordType.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			list.add(errorMessage3);
			list.add(errorMessage4);
			list.add(errorMessage5);
			list.add(errorMessage6);
			list.add(errorMessage7);
			return false;
			}
		return true;
		}


	/**
	 * Crea un oggetto NormalType attraverso la scannerizzazione
	 * di specifiche, in accordo alla grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto NormalType.
	 * @throws ScanException
	 */
	public NormalType scanNormalType(String specifiche)
		throws ScanException
		{
		NormalType nt;
		ScanIntegerType scanIntegerType = new ScanIntegerType(this.depth + 1);
		ScanIntervalloInt scanIntervalloInt = new ScanIntervalloInt(this.depth + 1);
		ScanRealType scanRealType = new ScanRealType(this.depth + 1);
		ScanBooleanType scanBooleanType = new ScanBooleanType(this.depth + 1);
		ScanListType scanListType = new ScanListType(this.depth + 1);
		ScanArrayType scanArrayType = new ScanArrayType(this.depth + 1);
		ScanRecordType scanRecordType = new ScanRecordType(this.depth + 1);
		if (scanIntegerType.isIntegerType(specifiche)) 
			nt = scanIntegerType.scanIntegerType(specifiche);
		else if (scanIntervalloInt.isIntervalloInt(specifiche)) 
			nt = scanIntervalloInt.scanIntervalloInt(specifiche);
		else if (scanRealType.isRealType(specifiche)) 
			nt = scanRealType.scanRealType(specifiche);
		else if (scanBooleanType.isBooleanType(specifiche)) 
			nt = scanBooleanType.scanBooleanType(specifiche);
		else if (scanListType.isListType(specifiche)) 
			nt = scanListType.scanListType(specifiche);
		else if (scanArrayType.isArrayType(specifiche)) 
			nt = scanArrayType.scanArrayType(specifiche);
		else if (scanRecordType.isRecordType(specifiche)) 
			nt = scanRecordType.scanRecordType(specifiche);
		else return null;
		return nt;
		}
}
