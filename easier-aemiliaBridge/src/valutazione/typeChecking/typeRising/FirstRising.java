/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.ArrayType;
import specificheAEmilia.BooleanType;
import specificheAEmilia.DataType;
import specificheAEmilia.First;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.IntegerRangeType;
import specificheAEmilia.IntegerType;
import specificheAEmilia.ListType;
import specificheAEmilia.NormalType;
import specificheAEmilia.RealType;
import specificheAEmilia.RecordType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class FirstRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public FirstRising(int depth)
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

	public DataTypeEnum risingFirst(First first, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		try {
			IdentExpr expression = first.getList();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string = "type checking error for " + first.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum.equals(DataTypeEnum.List)) 
				{
				String string = "type checking error for " + first.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression + " is not a " + DataTypeEnum.List;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			String string = expression.getNome();
			ValueIdentExpr valueIdentExpr = this.tm.get(string);
			ListType listType = (ListType)valueIdentExpr.getDataType();
			NormalType normalType = listType.getType();			
			if (normalType instanceof ArrayType)
				return DataTypeEnum.Array;
			else if (normalType instanceof BooleanType)
				return DataTypeEnum.Boolean;
			else if (normalType instanceof IntegerRangeType)
				return DataTypeEnum.Integer;
			else if (normalType instanceof IntegerType)
				return DataTypeEnum.Integer;
			else if (normalType instanceof ListType)
				return DataTypeEnum.List;
			else if (normalType instanceof RealType)
				return DataTypeEnum.Real;
			else if (normalType instanceof RecordType)
				return DataTypeEnum.Record;
			else
				throw new TypeRisingException("unexpected type");
			} 
		catch (Exception e) 
			{
			throw new TypeRisingException(e);
			}
		}

	public DataTypeEnum risingFirst(First first, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		try {
			IdentExpr expression = first.getList();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType,
					tma);
			if (typeRising.isErrorOccurred())
				{
				// 1
				String string = "type checking error for " + first.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum.equals(DataTypeEnum.List)) 
				{
				// 2
				String string = "type checking error for " + first.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression + " is not a " + DataTypeEnum.List;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			String string = expression.getNome();
			ValueIdentExpr valueIdentExpr = tma.get(string);
			ListType listType = (ListType)valueIdentExpr.getDataType();
			NormalType normalType = listType.getType();			
			if (normalType instanceof ArrayType)
				return DataTypeEnum.Array;
			else if (normalType instanceof BooleanType)
				return DataTypeEnum.Boolean;
			else if (normalType instanceof IntegerRangeType)
				return DataTypeEnum.Integer;
			else if (normalType instanceof IntegerType)
				return DataTypeEnum.Integer;
			else if (normalType instanceof ListType)
				return DataTypeEnum.List;
			else if (normalType instanceof RealType)
				return DataTypeEnum.Real;
			else if (normalType instanceof RecordType)
				return DataTypeEnum.Record;
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
