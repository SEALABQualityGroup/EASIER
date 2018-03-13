/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.ArrayType;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.NormalType;
import specificheAEmilia.Write;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeCompare;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class WriteRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public WriteRising(int depth)
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

	public DataTypeEnum risingWrite(Write write, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		/* MODELED */
		try {
			IdentExpr expression = write.getArray();
			Expression expression2 = write.getElement();
			Expression expression3 = write.getIndex();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType,
					tma);
			if (typeRising.isErrorOccurred())
				{
				// 1
				String string = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			TypeRising typeRising2 = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum2 = typeRising2.rising(expression2, parent, dataType,
					tma);
			if (typeRising2.isErrorOccurred())
				{
				// 2
				String string = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			TypeRising typeRising3 = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum3 = typeRising3.rising(expression3, parent, dataType,
					tma);
			if (typeRising3.isErrorOccurred())
				{
				// 3
				String string = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising3.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!DataTypeEnum.Array.equals(dataTypeEnum)) 
				{
				// 4
				String string = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression + " is not a " + DataTypeEnum.Array;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!DataTypeEnum.Integer.equals(dataTypeEnum3)) 
				{
				// 5
				String string = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression + " is not a " + DataTypeEnum.Integer;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			String string = expression.getNome();
			ValueIdentExpr valueIdentExpr = tma.get(string);
			DataType dataType2 = valueIdentExpr.getDataType();
			// per precondizione dataType2e'un ArrayType
			ArrayType arrayType = (ArrayType)dataType2;
			NormalType normalType = arrayType.getType();
			TypeCompare typeCompare = new TypeCompare();
			if (!typeCompare.sameType(normalType, dataTypeEnum2))
				{
				// 6
				String string1 = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string1);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression2 + " has not same array type ";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return DataTypeEnum.Array;
			} 
		catch (Exception e) 
			{
			throw new TypeRisingException(e);
			}
		}
													
	public DataTypeEnum risingWrite(Write write, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		try {
			IdentExpr expression = write.getArray();
			Expression expression2 = write.getElement();
			Expression expression3 = write.getIndex();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			TypeRising typeRising2 = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum2 = typeRising2.rising(expression2, parent, dataType, this.tm);
			if (typeRising2.isErrorOccurred())
				{
				String string = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			TypeRising typeRising3 = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum3 = typeRising3.rising(expression3, parent, dataType, this.tm);
			if (typeRising3.isErrorOccurred())
				{
				String string = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising3.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!DataTypeEnum.Array.equals(dataTypeEnum)) 
				{
				String string = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression + " is not a " + DataTypeEnum.Array;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!DataTypeEnum.Integer.equals(dataTypeEnum3)) 
				{
				String string = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression + " is not a " + DataTypeEnum.Integer;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			String string = expression.getNome();
			ValueIdentExpr valueIdentExpr = this.tm.get(string);
			DataType dataType2 = valueIdentExpr.getDataType();
			// per precondizione dataType2e'un ArrayType
			ArrayType arrayType = (ArrayType)dataType2;
			NormalType normalType = arrayType.getType();
			TypeCompare typeCompare = new TypeCompare();
			if (!typeCompare.sameType(normalType, dataTypeEnum2))
				{
				String string1 = "type checking error for " + write.toString();
				this.errorMessage.setErrorMessage(string1);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression2 + " has not same array type ";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return DataTypeEnum.Array;
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
