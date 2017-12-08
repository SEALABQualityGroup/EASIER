/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.dataTypes.PrioTypeNorm;
import restrizioniIstanze.dataTypes.RateTypeNorm;
import restrizioniIstanze.dataTypes.SpecialTypeNorm;
import restrizioniIstanze.dataTypes.WeightTypeNorm;
import specificheAEmilia.PrioType;
import specificheAEmilia.RateType;
import specificheAEmilia.SpecialType;
import specificheAEmilia.WeightType;

import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeSpecialType {

	
    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred = false;

    public NormalizeSpecialType(int depth) 
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
	public SpecialTypeNorm normalize(SpecialType dataType)
		throws NormalizeException
		{
		SpecialTypeNorm ris = null;
		if (dataType instanceof PrioType)
			{
			NormalizePrioType normalizePrioType = new NormalizePrioType(this.depth + 1);
			normalizePrioType.setTm(this.tm);
			PrioTypeNorm prioType = normalizePrioType.normalize((PrioType)dataType);
			if (!normalizePrioType.isErrorOccurred())
				ris = prioType;
			else
				{
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizePrioType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof RateType)
			{
			NormalizeRateType normalizeRateType = new NormalizeRateType(this.depth + 1);
			normalizeRateType.setTm(this.tm);
			RateTypeNorm rateType = normalizeRateType.normalize((RateType)dataType);
			if (!normalizeRateType.isErrorOccurred())
				ris = rateType;
			else
				{
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRateType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof WeightType)
			{
			NormalizeWeightType normalizeWeightType = new NormalizeWeightType(this.depth + 1);
			normalizeWeightType.setTm(this.tm);
			WeightTypeNorm weightType = normalizeWeightType.normalize((WeightType)dataType);
			if (!normalizeWeightType.isErrorOccurred())
				ris = weightType;
			else
				{
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeWeightType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		return ris;
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
	public SpecialTypeNorm normalize(SpecialType dataType, TreeMap<String, ValueIdentExpr> tm)
		throws NormalizeException
		{
		SpecialTypeNorm ris = null;
		if (dataType instanceof PrioType)
			{
			NormalizePrioType normalizePrioType = new NormalizePrioType(this.depth + 1);
			PrioTypeNorm prioType = normalizePrioType.normalize((PrioType)dataType,tm);
			ris = prioType;
			}
		else if (dataType instanceof RateType)
			{
			NormalizeRateType normalizeNormalType = new NormalizeRateType(this.depth + 1);
			RateTypeNorm rateType = normalizeNormalType.normalize((RateType)dataType,tm);
			ris = rateType;
			}
		else if (dataType instanceof WeightType)
			{
			NormalizeWeightType normalizeWeightType = new NormalizeWeightType(this.depth + 1);
			WeightTypeNorm weightType = normalizeWeightType.normalize((WeightType)dataType,tm);
			ris = weightType;
			}
		return ris;
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}
	
	

}
