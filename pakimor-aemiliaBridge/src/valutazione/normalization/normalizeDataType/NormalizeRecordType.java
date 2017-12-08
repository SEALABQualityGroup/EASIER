/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.dataTypes.NormalTypeNorm;
import restrizioniIstanze.dataTypes.RecordTypeNorm;
import specificheAEmilia.NormalType;
import specificheAEmilia.RecordType;
import specificheAEmilia.VariableDeclaration;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeRecordType {

	
    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred = false;

    public NormalizeRecordType(int depth) 
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	public TreeMap<String, ValueIdentExpr> getTm() 
		{
		return tm;
		}
	
	public void setTm(TreeMap<String, ValueIdentExpr> tm) 
		{
		this.tm = tm;
		}

    /**
	 * Viene restituita la normalizzazione di dataType, effettuando
	 * typeChecking durante la valutazione.
	 * 
	 * @param dataType
	 * @return
	 * @throws NormalizeException
	 */
	public RecordTypeNorm normalize(RecordType dataType)
		throws NormalizeException
		{
		try {
			RecordType recordType = dataType.copy();
			for (VariableDeclaration variableDeclaration : recordType
					.getVariableDeclarations()) 
				{
				NormalType normalType = variableDeclaration.getType();
				NormalizeNormalType normalizeNormalType = new NormalizeNormalType(this.depth + 1);
				normalizeNormalType.setTm(this.tm);
				NormalTypeNorm normalType2 = normalizeNormalType.normalize(normalType);
				if (normalizeNormalType.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + dataType.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeNormalType.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				variableDeclaration.setType(normalType2.getNewDataType());
				}
			RecordTypeNorm recordTypeNorm = new RecordTypeNorm();
			recordTypeNorm.setOldRecordType(dataType);
			recordTypeNorm.setNewRecordType(recordType);
			return recordTypeNorm;
			} 
		catch (Exception e) 
			{
			throw new NormalizeException(e);
			}
		}

	
    /**
	 * Viene restituita la normalizzazione di dataType, effettuando
	 * typeChecking durante la valutazione. Utilizza uno scope tm per la valutazione,
	 * passandolo come parametro.
	 * 
	 * @param dataType
	 * @param tm
	 * @return
	 * @throws NormalizeException
	 */
	public RecordTypeNorm normalize(RecordType dataType, TreeMap<String, ValueIdentExpr> tm)
		throws NormalizeException
		{
		try {
			RecordType recordType = dataType.copy();
			for (VariableDeclaration variableDeclaration : recordType
					.getVariableDeclarations()) 
				{
				NormalType normalType = variableDeclaration.getType();
				NormalizeNormalType normalizeNormalType = new NormalizeNormalType(this.depth + 1);
				NormalTypeNorm normalType2 = normalizeNormalType.normalize(normalType, tm);
				if (normalizeNormalType.isErrorOccurred()) 
					{
					// 1
					String string = "Normalizing error for " + dataType.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeNormalType.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;					
					}
				variableDeclaration.setType(normalType2.getNewDataType());
				}
			RecordTypeNorm recordTypeNorm = new RecordTypeNorm();
			recordTypeNorm.setOldRecordType(dataType);
			recordTypeNorm.setNewRecordType(recordType);
			return recordTypeNorm;
			}
		catch (Exception e) 
			{
			throw new NormalizeException(e);
			}
		}

	public boolean isErrorOccurred() 
		{
		return this.errorOccurred;
		}

	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}
	
}
