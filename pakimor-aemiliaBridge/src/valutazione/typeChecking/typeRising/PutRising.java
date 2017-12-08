/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Put;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class PutRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public PutRising(int depth)
    	{
    	super();
    	this.tm = new TreeMap<String, ValueIdentExpr>();
    	this.depth = depth;
    	this.errorMessage  = new ErrorMessage(depth);
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

	public DataTypeEnum risingPut(Put put, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		try {
			IdentExpr expression = put.getRecord();
			Expression expression2 = put.getValue();
			String field = "".equals(parent) ? put.getField() : parent + "."
					+ put.getField();
			// affinche' put.getField() appartenga a expression bisogna che sia presente nello scope
			if (this.tm.containsKey(field)) 
				{
				ValueIdentExpr valueIdentExpr = this.tm.get(field);
				Expression expression3 = valueIdentExpr.getValore();
				TypeRising typeRising = new TypeRising(this.depth + 1);
				DataTypeEnum dataTypeEnum = typeRising.rising(expression3, parent,
						dataType, this.tm);
				if (typeRising.isErrorOccurred())
					{
					String string = "type checking error for " + put.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = typeRising.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				TypeRising typeRising2 = new TypeRising(this.depth + 1);
				DataTypeEnum dataTypeEnum2 = typeRising2.rising(expression2, parent,
						dataType, this.tm);
				if (typeRising2.isErrorOccurred())
					{
					String string = "type checking error for " + put.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = typeRising2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				if (!dataTypeEnum.equals(dataTypeEnum2)) 	
					{
					String string = "type checking error for " + put.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = expression + " and " + expression2
							+ " have not same type: " + expression
							+ " has type " + dataTypeEnum + "; " + expression2
							+ " has type " + dataTypeEnum2;
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				String string = "type checking error for " + put.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = put.getField() + " is not a field of "
						+ expression + " record";
				this.errorOccurred = true;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				return null;
				}
			return DataTypeEnum.Record;
			} 
		catch (Exception e) 
			{
			throw new TypeRisingException(e);
			}
		}
	
	public DataTypeEnum risingPut(Put put, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		/* MODELED */
		try {
			IdentExpr expression = put.getRecord();
			Expression expression2 = put.getValue();
			String field = "".equals(parent) ? put.getField() : parent + "."
					+ put.getField();
			// affinche' put.getField() appartenga a expression bisogna che sia presente nello scope
			if (this.tm.containsKey(field)) 
				{
				ValueIdentExpr valueIdentExpr = this.tm.get(field);
				Expression expression3 = valueIdentExpr.getValore();
				TypeRising typeRising = new TypeRising(this.depth + 1);
				DataTypeEnum dataTypeEnum = typeRising.rising(expression3, parent,
						dataType, tma);
				if (typeRising.isErrorOccurred())
					{
					// 1
					String string = "type checking error for " + put.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = typeRising.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				TypeRising typeRising2 = new TypeRising(this.depth + 1);
				DataTypeEnum dataTypeEnum2 = typeRising2.rising(expression2, parent,
						dataType, tma);
				if (typeRising2.isErrorOccurred())
					{
					// 2
					String string = "type checking error for " + put.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = typeRising2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				if (!dataTypeEnum.equals(dataTypeEnum2)) 	
					{
					// 3
					String string = "type checking error for " + put.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = expression + " and " + expression2
							+ " have not same type: " + expression
							+ " has type " + dataTypeEnum + "; " + expression2
							+ " has type " + dataTypeEnum2;
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				// 4
				String string = "type checking error for " + put.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = put.getField() + " is not a field of "
						+ expression + " record";
				this.errorOccurred = true;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				return null;
				}
			return DataTypeEnum.Record;
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
