/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.TreeMap;

import restrizioniIstanze.dataTypes.PrioTypeNorm;
import specificheAEmilia.PrioType;

import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizePrioType {

    private TreeMap<String, ValueIdentExpr> tm;
    private ErrorMessage errorMessage;
    private boolean errorOccurred = false;

    public NormalizePrioType(int depth) 
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
	public PrioTypeNorm normalize(PrioType dataType)
		throws NormalizeException
		{
		try {
			PrioType prioType = dataType.copy();
			PrioTypeNorm prioTypeNorm = new PrioTypeNorm();
			prioTypeNorm.setOldPrioType(dataType);
			prioTypeNorm.setNewPrioType(prioType);
			return prioTypeNorm;
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
	public PrioTypeNorm normalize(PrioType dataType, TreeMap<String, ValueIdentExpr> tm)
		throws NormalizeException
		{
		try {
			PrioType prioType = dataType.copy();
			PrioTypeNorm prioTypeNorm = new PrioTypeNorm();
			prioTypeNorm.setOldPrioType(dataType);
			prioTypeNorm.setNewPrioType(prioType);
			return prioTypeNorm;
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
