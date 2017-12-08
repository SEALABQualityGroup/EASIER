/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.TreeMap;

import restrizioniIstanze.dataTypes.IntegerTypeNorm;
import specificheAEmilia.IntegerType;

import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeIntegerType {

    private TreeMap<String, ValueIdentExpr> tm;
    private ErrorMessage errorMessage;
    private boolean errorOccurred = false;

    public NormalizeIntegerType(int depth) 
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
	public IntegerTypeNorm normalize(IntegerType dataType)
		throws NormalizeException
		{
		try {
			IntegerType integerType = dataType.copy();
			IntegerTypeNorm integerTypeNorm = new IntegerTypeNorm();
			integerTypeNorm.setOldIntegerType(dataType);
			integerTypeNorm.setNewIntegerType(integerType);
			return integerTypeNorm;
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
	public IntegerTypeNorm normalize(IntegerType dataType, TreeMap<String, ValueIdentExpr> tm)
		throws NormalizeException
		{
		try {
			IntegerType integerType = dataType.copy();
			IntegerTypeNorm integerTypeNorm = new IntegerTypeNorm();
			integerTypeNorm.setOldIntegerType(dataType);
			integerTypeNorm.setNewIntegerType(integerType);
			return integerTypeNorm;
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
