/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Remove;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class RemoveRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public RemoveRising(int depth)
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
	
	public DataTypeEnum risingRemove(Remove remove, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		try {
			IdentExpr expression = remove.getList();
			Expression expression2 = remove.getPosition();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string = "type checking error for " + remove.toString();
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
				String string = "type checking error for " + remove.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!DataTypeEnum.List.equals(dataTypeEnum)) 
				{
				String string = "type checking error for " + remove.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression + " is not a " + DataTypeEnum.List;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!DataTypeEnum.Integer.equals(dataTypeEnum2)) 
				{
				String string = "type checking error for " + remove.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 =expression2 + " is not a "
						+ DataTypeEnum.Integer;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return DataTypeEnum.List;
			} 
		catch (Exception e) 
			{
			throw new TypeRisingException(e);
			}
		}
	
	public DataTypeEnum risingRemove(Remove remove, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		/* MODELED */
		try {
			IdentExpr expression = remove.getList();
			Expression expression2 = remove.getPosition();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, tma);
			if (typeRising.isErrorOccurred())
				{
				// 1
				String string = "type checking error for " + remove.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			TypeRising typeRising2 = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum2 = typeRising2.rising(expression2, parent, dataType, tma);
			if (typeRising2.isErrorOccurred())
				{
				// 2
				String string = "type checking error for " + remove.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!DataTypeEnum.List.equals(dataTypeEnum)) 
				{
				// 3
				String string = "type checking error for " + remove.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression + " is not a " + DataTypeEnum.List;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!DataTypeEnum.Integer.equals(dataTypeEnum2)) 
				{
				// 4
				String string = "type checking error for " + remove.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 =expression2 + " is not a "
						+ DataTypeEnum.Integer;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return DataTypeEnum.List;
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
