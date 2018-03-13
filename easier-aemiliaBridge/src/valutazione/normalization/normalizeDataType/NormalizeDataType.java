/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.dataTypes.ArrayTypeNorm;
import restrizioniIstanze.dataTypes.BooleanTypeNorm;
import restrizioniIstanze.dataTypes.DataTypeNorm;
import restrizioniIstanze.dataTypes.IntegerRangeTypeNorm;
import restrizioniIstanze.dataTypes.IntegerTypeNorm;
import restrizioniIstanze.dataTypes.ListTypeNorm;
import restrizioniIstanze.dataTypes.PrioTypeNorm;
import restrizioniIstanze.dataTypes.RateTypeNorm;
import restrizioniIstanze.dataTypes.RealTypeNorm;
import restrizioniIstanze.dataTypes.RecordTypeNorm;
import restrizioniIstanze.dataTypes.WeightTypeNorm;
import specificheAEmilia.ArrayType;
import specificheAEmilia.BooleanType;
import specificheAEmilia.DataType;
import specificheAEmilia.IntegerRangeType;
import specificheAEmilia.IntegerType;
import specificheAEmilia.ListType;
import specificheAEmilia.PrioType;
import specificheAEmilia.RateType;
import specificheAEmilia.RealType;
import specificheAEmilia.RecordType;
import specificheAEmilia.WeightType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * Classe utilizzata per la normalizzazione dei tipi di dato.
 * 
 * @author Acer5736Z_1
 *
 */
public class NormalizeDataType 
	{
	
    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred = false;

    public NormalizeDataType(int depth) 
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
	public DataTypeNorm normalize(DataType dataType)
		throws NormalizeException
		{
		/* MODELED */
		DataTypeNorm ris = null;
		if (dataType instanceof ArrayType)
			{
			NormalizeArrayType normalizeArrayType = new NormalizeArrayType(this.depth + 1);
			normalizeArrayType.setTm(this.tm);
			ArrayTypeNorm arrayType = normalizeArrayType.normalize((ArrayType)dataType);
			if (!normalizeArrayType.isErrorOccurred())
				ris = arrayType;
			else
				{
				// 1
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeArrayType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof BooleanType)
			{
			NormalizeBooleanType normalizeBooleanType = new NormalizeBooleanType(this.depth + 1);
			normalizeBooleanType.setTm(this.tm);
			BooleanTypeNorm booleanType = normalizeBooleanType.normalize((BooleanType)dataType);
			if (!normalizeBooleanType.isErrorOccurred())
				ris = booleanType;
			else
				{
				// 2
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeBooleanType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof IntegerRangeType)
			{
			NormalizeIntegerRangeType normalizeIntegerRangeType = new NormalizeIntegerRangeType(this.depth + 1);
			normalizeIntegerRangeType.setTm(this.tm);
			IntegerRangeTypeNorm integerRangeType = normalizeIntegerRangeType.normalize((IntegerRangeType)dataType);
			if (!normalizeIntegerRangeType.isErrorOccurred())
				ris = integerRangeType;
			else
				{
				// 3
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeIntegerRangeType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof IntegerType)
			{
			NormalizeIntegerType normalizeIntegerType = new NormalizeIntegerType(this.depth + 1);
			normalizeIntegerType.setTm(this.tm);
			IntegerTypeNorm integerType = normalizeIntegerType.normalize((IntegerType)dataType);
			if (!normalizeIntegerType.isErrorOccurred())
				ris = integerType;
			else
				{
				// 4
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeIntegerType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof ListType)
			{
			NormalizeListType normalizeListType = new NormalizeListType(this.depth + 1);
			normalizeListType.setTm(this.tm);
			ListTypeNorm listType = normalizeListType.normalize((ListType)dataType);
			if (!normalizeListType.isErrorOccurred())
				ris = listType;
			else
				{
				// 5
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeListType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof RealType)
			{
			NormalizeRealType normalizeRealType = new NormalizeRealType(this.depth + 1);
			normalizeRealType.setTm(this.tm);
			RealTypeNorm realType = normalizeRealType.normalize((RealType)dataType);
			if (!normalizeRealType.isErrorOccurred())
				ris = realType;
			else
				{
				// 6
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRealType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof RecordType)
			{
			NormalizeRecordType normalizeRecordType = new NormalizeRecordType(this.depth + 1);
			normalizeRecordType.setTm(this.tm);
			RecordTypeNorm recordType = normalizeRecordType.normalize((RecordType)dataType);
			if (!normalizeRecordType.isErrorOccurred())
				ris = recordType;
			else
				{
				// 7
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRecordType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof PrioType)
			{
			NormalizePrioType normalizePrioType = new NormalizePrioType(this.depth + 1);
			normalizePrioType.setTm(this.tm);
			PrioTypeNorm prioType = normalizePrioType.normalize((PrioType)dataType);
			if (!normalizePrioType.isErrorOccurred())
				ris = prioType;
			else
				{
				// 8
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
				// 9
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
				// 10
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
	public DataTypeNorm normalize(DataType dataType, TreeMap<String, ValueIdentExpr> tm)
		throws NormalizeException
		{
		/* MODELED */
		DataTypeNorm ris = null;
		if (dataType instanceof ArrayType)
			{
			NormalizeArrayType normalizeArrayType = new NormalizeArrayType(this.depth + 1);
			ArrayTypeNorm arrayType = normalizeArrayType.normalize((ArrayType)dataType,tm);
			if (!normalizeArrayType.isErrorOccurred())
				ris = arrayType;
			else
				{
				// 1
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeArrayType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof BooleanType)
			{
			NormalizeBooleanType normalizeBooleanType = new NormalizeBooleanType(this.depth + 1);
			BooleanTypeNorm booleanType = normalizeBooleanType.normalize((BooleanType)dataType,tm);
			ris = booleanType;
			}
		else if (dataType instanceof IntegerRangeType)
			{
			NormalizeIntegerRangeType normalizeIntegerRangeType = new NormalizeIntegerRangeType(this.depth + 1);
			IntegerRangeTypeNorm integerRangeType = normalizeIntegerRangeType.normalize((IntegerRangeType)dataType,tm);
			if (!normalizeIntegerRangeType.isErrorOccurred())
				ris = integerRangeType;
			else
				{
				// 3
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeIntegerRangeType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof IntegerType)
			{
			NormalizeIntegerType normalizeIntegerType = new NormalizeIntegerType(this.depth + 1);
			IntegerTypeNorm integerType = normalizeIntegerType.normalize((IntegerType)dataType,tm);
			ris = integerType;
			}
		else if (dataType instanceof ListType)
			{
			NormalizeListType normalizeListType = new NormalizeListType(this.depth + 1);
			ListTypeNorm listType = normalizeListType.normalize((ListType)dataType,tm);
			if (!normalizeListType.isErrorOccurred())
				ris = listType;
			else
				{
				// 5
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeListType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof RealType)
			{
			NormalizeRealType normalizeRealType = new NormalizeRealType(this.depth + 1);
			RealTypeNorm realType = normalizeRealType.normalize((RealType)dataType,tm);
			ris = realType;
			}
		else if (dataType instanceof RecordType)
			{
			NormalizeRecordType normalizeRecordType = new NormalizeRecordType(this.depth + 1);
			RecordTypeNorm recordType = normalizeRecordType.normalize((RecordType)dataType,tm);
			if (!normalizeRecordType.isErrorOccurred())
				ris = recordType;
			else
				{
				// 7
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRecordType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		else if (dataType instanceof PrioType)
			{
			NormalizePrioType normalizePrioType = new NormalizePrioType(this.depth + 1);
			PrioTypeNorm prioType = normalizePrioType.normalize((PrioType)dataType,tm);
			ris = prioType;
			}
		else if (dataType instanceof RateType)
			{
			NormalizeRateType normalizeRateType = new NormalizeRateType(this.depth + 1);
			RateTypeNorm rateType = normalizeRateType.normalize((RateType)dataType,tm);
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

	public boolean isErrorOccurred() 
		{
		return errorOccurred;
		}

	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}
	}