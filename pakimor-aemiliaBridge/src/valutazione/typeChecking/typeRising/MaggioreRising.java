/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Maggiore;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class MaggioreRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public MaggioreRising(int depth)
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

	public DataTypeEnum risingMaggiore(Maggiore maggiore, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		try {
			Expression expression = maggiore.getExpr1();
			Expression expression2 = maggiore.getExpr2();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string = "type checking error for " + maggiore.toString();
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
				String string = "type checking error for " + maggiore.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum.equals(DataTypeEnum.Real)
					&& !dataTypeEnum.equals(DataTypeEnum.Integer)) 
				{
				String string = "type checking error for " + maggiore.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(expression.toString() + " is not a number");
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum2.equals(DataTypeEnum.Real)
					&& !dataTypeEnum2.equals(DataTypeEnum.Integer)) 
				{
				String string = "type checking error for " + maggiore.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(expression2.toString() + " is not a number");
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return DataTypeEnum.Boolean;
			} 
		catch (Exception e) 
			{
			throw new TypeRisingException(e);
			}
		}

	public DataTypeEnum risingMaggiore(Maggiore maggiore, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		try {
			Expression expression = maggiore.getExpr1();
			Expression expression2 = maggiore.getExpr2();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, tma);
			if (typeRising.isErrorOccurred())
				{
				// 1
				String string = "type checking error for " + maggiore.toString();
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
				String string = "type checking error for " + maggiore.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum.equals(DataTypeEnum.Real)
					&& !dataTypeEnum.equals(DataTypeEnum.Integer)) 
				{
				// 3
				String string = "type checking error for " + maggiore.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(expression.toString() + " is not a number");
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum2.equals(DataTypeEnum.Real)
					&& !dataTypeEnum2.equals(DataTypeEnum.Integer)) 
				{
				// 4
				String string = "type checking error for " + maggiore.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(expression2.toString() + " is not a number");
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return DataTypeEnum.Boolean;
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
