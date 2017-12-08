/**
 * 
 */
package scanSpecAEmilia.scanTipoDato;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanIntestazione.ScanVarSeq;
import specificheAEmilia.RecordType;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanRecordType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanRecordType(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	public boolean isRecordType(String string)
		{
		/*
		 * "record" "(" <field_decl_sequence> ")"
		 */
		MyScanner s = new MyScanner(string);
		String dichiarazioniCampi = new String();
		s.useDelimiter("\\s*\\)\\s*");
		try {
			s.skip("\\s*record\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string2 = string + " is not record type";
			this.errorMessage.setErrorMessage(string2);
			String string3 = "\"record(\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// la sequenza di dichiarazioni di campi non puo' essere vuota
		try {
			dichiarazioniCampi = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string2 = string + " is not record type";
			this.errorMessage.setErrorMessage(string2);
			String string3 = "sequence of filed declarations expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanVarSeq scanVarSeq = new ScanVarSeq(this.depth + 1);
		if (!scanVarSeq.isVarSeq(dichiarazioniCampi))
			{
			String string2 = string + " is not record type";
			this.errorMessage.setErrorMessage(string2);
			ErrorMessage errorMessage = scanVarSeq.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	
	public RecordType scanRecordType(String string)
		throws ScanException
		{
		try {
			/*
			 * "record" "(" <field_decl_sequence> ")"
			 */
			RecordType recordType = new RecordType();
			MyScanner s = new MyScanner(string);
			String fieldDeclarations = new String();
			s.useDelimiter("\\s*\\)\\s*");
			s.skip("\\s*record\\s*\\(\\s*");
			// la sequenza di dichiarazioni di campi non puo'essere vuota
			fieldDeclarations = s.next();
			ScanVarSeq scanVarSeq = new ScanVarSeq(this.depth + 1);
			recordType.setVariableDeclarations(scanVarSeq.scanDeclVarSeq(fieldDeclarations));
			return recordType;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
