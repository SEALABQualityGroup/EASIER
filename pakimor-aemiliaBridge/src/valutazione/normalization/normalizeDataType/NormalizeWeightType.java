/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.TreeMap;

import restrizioniIstanze.dataTypes.WeightTypeNorm;
import specificheAEmilia.WeightType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeWeightType {

    private TreeMap<String, ValueIdentExpr> tm;
    private ErrorMessage errorMessage;
    private boolean errorOccurred = false;

    public NormalizeWeightType(int depth) 
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
	public WeightTypeNorm normalize(WeightType dataType)
		throws NormalizeException
		{
		try {
			WeightType weightType = dataType.copy();
			WeightTypeNorm weightTypeNorm = new WeightTypeNorm();
			weightTypeNorm.setOldWeightType(dataType);
			weightTypeNorm.setNewWeightType(weightType);
			return weightTypeNorm;
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
	public WeightTypeNorm normalize(WeightType dataType, TreeMap<String, ValueIdentExpr> tm)
		throws NormalizeException
		{
		try {
			WeightType weightType = dataType.copy();
			WeightTypeNorm weightTypeNorm = new WeightTypeNorm();
			weightTypeNorm.setOldWeightType(dataType);
			weightTypeNorm.setNewWeightType(weightType);
			return weightTypeNorm;
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
