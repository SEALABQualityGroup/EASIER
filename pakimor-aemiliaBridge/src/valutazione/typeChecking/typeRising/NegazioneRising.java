/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Negazione;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class NegazioneRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public NegazioneRising(int depth)
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

	public DataTypeEnum risingNegazione(Negazione negazione, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		try {
			Expression expression = negazione.getExpr1();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string = "type checking error for " + negazione.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum.equals(DataTypeEnum.Boolean)) 
				{
				String string = "type checking error for " + negazione.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression.toString() + " is not a "
						+ DataTypeEnum.Boolean;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
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

	public DataTypeEnum risingNegazione(Negazione negazione, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		/* MODELED */
		try {
			Expression expression = negazione.getExpr1();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, tma);
			if (typeRising.isErrorOccurred())
				{
				// 1
				String string = "type checking error for " + negazione.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum.equals(DataTypeEnum.Boolean)) 
				{
				// 2
				String string = "type checking error for " + negazione.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression.toString() + " is not a "
						+ DataTypeEnum.Boolean;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				this.errorOccurred = true;
				list.add(errorMessage);
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
