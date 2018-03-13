/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.TreeMap;

import restrizioniIstanze.dataTypes.BooleanTypeNorm;
import specificheAEmilia.BooleanType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeBooleanType {

    private TreeMap<String, ValueIdentExpr> tm;
    private ErrorMessage errorMessage;
    private boolean errorOccurred = false;

    public NormalizeBooleanType(int depth) 
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
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
	public BooleanTypeNorm normalize(BooleanType dataType)
		throws NormalizeException
		{
		try {
			BooleanType booleanType = dataType.copy();
			BooleanTypeNorm booleanTypeNorm = new BooleanTypeNorm();
			booleanTypeNorm.setOldBooleanType(dataType);
			booleanTypeNorm.setNewBooleanType(booleanType);
			return booleanTypeNorm;
			}
		catch (Exception exception)
			{
			throw new NormalizeException(exception);
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
	public BooleanTypeNorm normalize(BooleanType dataType, TreeMap<String, ValueIdentExpr> tm)
		throws NormalizeException
		{
		try {
			BooleanType booleanType = dataType.copy();
			BooleanTypeNorm booleanTypeNorm = new BooleanTypeNorm();
			booleanTypeNorm.setOldBooleanType(dataType);
			booleanTypeNorm.setNewBooleanType(booleanType);
			return booleanTypeNorm;
			}
		catch (Exception exception)
			{
			throw new NormalizeException(exception);
			}
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}
	
	

}
