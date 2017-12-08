/**
 * 
 */
package valutazione.typeChecking;

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

/**
 * Classe utilizzata per verificare se un oggetto DataTypee'compatibile con
 * un oggetto DataTypeEnum.
 * 
 * @author Mirko
 *
 */
public class TypeCompare 
	{
	
	public boolean sameType(DataType dataType, DataTypeEnum dataTypeEnum) 
		{
		if (dataType instanceof ArrayType) 
			{
			return DataTypeEnum.Array.equals(dataTypeEnum);
			}
		else if (dataType instanceof BooleanType)
			{
			return DataTypeEnum.Boolean.equals(dataTypeEnum);
			}
		else if (dataType instanceof IntegerRangeType)
			{
			return DataTypeEnum.Integer.equals(dataTypeEnum);
			}
		else if (dataType instanceof IntegerType)
			{
			return DataTypeEnum.Integer.equals(dataTypeEnum);
			}
		else if (dataType instanceof ListType)
			{
			return DataTypeEnum.List.equals(dataTypeEnum);
			}
		else if (dataType instanceof RealType)
			{
			if (DataTypeEnum.Integer.equals(dataTypeEnum))
				return true;
			else if (DataTypeEnum.Real.equals(dataTypeEnum))
				return true;
			else return false;
			}
		else if (dataType instanceof RecordType)
			{
			return DataTypeEnum.Record.equals(dataTypeEnum);
			}
		else if (dataType instanceof PrioType)
			{
			return DataTypeEnum.Integer.equals(dataTypeEnum);
			}
		else if (dataType instanceof RateType)
			{
			if (DataTypeEnum.Integer.equals(dataTypeEnum))
				return true;
			else if (DataTypeEnum.Real.equals(dataTypeEnum))
				return true;
			else return false;
			}
		else if (dataType instanceof WeightType)
			{
			if (DataTypeEnum.Integer.equals(dataTypeEnum))
				return true;
			else if (DataTypeEnum.Real.equals(dataTypeEnum))
				return true;
			else return false;
			}
		else return false;
		}
	}
