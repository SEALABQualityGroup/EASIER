/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.TreeMap;

import restrizioniIstanze.dataTypes.RealTypeNorm;
import specificheAEmilia.RealType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeRealType {

    private TreeMap<String, ValueIdentExpr> tm;
    private ErrorMessage errorMessage;
    private boolean errorOccurred = false;

    public NormalizeRealType(int depth) 
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
	public RealTypeNorm normalize(RealType dataType)
		throws NormalizeException
		{
		try {
			RealType realType = dataType.copy();
			RealTypeNorm realTypeNorm = new RealTypeNorm();
			realTypeNorm.setOldRealType(dataType);
			realTypeNorm.setNewRealType(realType);
			return realTypeNorm;
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
	public RealTypeNorm normalize(RealType dataType, TreeMap<String, ValueIdentExpr> tm)
		throws NormalizeException
		{
		try {
			RealType realType = dataType.copy();
			RealTypeNorm realTypeNorm = new RealTypeNorm();
			realTypeNorm.setOldRealType(dataType);
			realTypeNorm.setNewRealType(realType);
			return realTypeNorm;
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
