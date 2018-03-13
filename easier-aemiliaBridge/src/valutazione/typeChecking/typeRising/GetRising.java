/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.ArrayType;
import specificheAEmilia.BooleanType;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Get;
import specificheAEmilia.IdentExpr;
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
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class GetRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public GetRising(int depth)
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
	
	public boolean isErrorOccurred() 
		{
		return errorOccurred;
		}

	public DataTypeEnum risingGet(Get get, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		try {
			IdentExpr expression = get.getRecord();
			String string = get.getField();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string2 = "type checking error for " + get.toString();
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum.equals(DataTypeEnum.Record)) 
				{
				String string2 = "type checking error for " + get.toString();
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string3 = expression + " is not a " + DataTypeEnum.Record;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string3);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			String string2 = "".equals(parent) ? string : parent + "." + string;
			ValueIdentExpr valueIdentExpr = this.tm.get(string2);
			DataType dataType2 = valueIdentExpr.getDataType();
			if (dataType2 instanceof ArrayType)
				return DataTypeEnum.Array;
			else if (dataType2 instanceof BooleanType)
				return DataTypeEnum.Boolean;
			else if (dataType2 instanceof IntegerRangeType)
				return DataTypeEnum.Integer;
			else if (dataType2 instanceof IntegerType)
				return DataTypeEnum.Integer;
			else if (dataType2 instanceof ListType)
				return DataTypeEnum.List;
			else if (dataType2 instanceof RealType)
				return DataTypeEnum.Real;
			else if (dataType2 instanceof RecordType)
				return DataTypeEnum.Record;
			else if (dataType2 instanceof PrioType)
				return DataTypeEnum.Integer;
			else if (dataType2 instanceof RateType)
				return DataTypeEnum.Real;
			else if (dataType2 instanceof WeightType)
				return DataTypeEnum.Real;
			else
				throw new TypeRisingException("unexpected type");
			} 
		catch (Exception e) 
			{
			throw new TypeRisingException(e);
			}
		}

	public DataTypeEnum risingGet(Get get, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		/* MODELED */
		try {
			Expression expression = get.getRecord();
			String string = get.getField();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType,
					tma);
			if (!typeRising.isErrorOccurred())
				{
				// 1
				String string2 = "type checking error for " + get.toString();
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum.equals(DataTypeEnum.Record)) 
				{
				// 2
				String string2 = "type checking error for " + get.toString();
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string3 = expression + " is not a " + DataTypeEnum.Record;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string3);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			String string2 = "".equals(parent) ? string : parent + "." + string;
			ValueIdentExpr valueIdentExpr = tma.get(string2);
			DataType dataType2 = valueIdentExpr.getDataType();
			if (dataType2 instanceof ArrayType)
				return DataTypeEnum.Array;
			else if (dataType2 instanceof BooleanType)
				return DataTypeEnum.Boolean;
			else if (dataType2 instanceof IntegerRangeType)
				return DataTypeEnum.Integer;
			else if (dataType2 instanceof IntegerType)
				return DataTypeEnum.Integer;
			else if (dataType2 instanceof ListType)
				return DataTypeEnum.List;
			else if (dataType2 instanceof RealType)
				return DataTypeEnum.Real;
			else if (dataType2 instanceof RecordType)
				return DataTypeEnum.Record;
			else if (dataType2 instanceof PrioType)
				return DataTypeEnum.Integer;
			else if (dataType2 instanceof RateType)
				return DataTypeEnum.Real;
			else if (dataType2 instanceof WeightType)
				return DataTypeEnum.Real;
			else
				throw new TypeRisingException("unexpected type");
			} 
		catch (Exception e) 
			{
			throw new TypeRisingException(e);
			}
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	
	
}
