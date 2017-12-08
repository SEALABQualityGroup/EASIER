/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.Concat;
import specificheAEmilia.DataType;
import specificheAEmilia.IdentExpr;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class ConcatRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public ConcatRising(int depth)
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

	public DataTypeEnum risingConcat(Concat concat, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		try {
			IdentExpr expression = concat.getList1();
			IdentExpr expression2 = concat.getList2();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string = "type checking error for " + concat.toString();
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
				String string = "type checking error for " + concat.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum.equals(DataTypeEnum.List)) 
				{
				String string = "type checking error for " + concat.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression.toString() + " is not a list";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum2.equals(DataTypeEnum.List)) 
				{
				String string = "type checking error for " + concat.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression2.toString() + " is not a list";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			String string = expression.getNome();
			String string2 = expression2.getNome();
			ValueIdentExpr valueIdentExpr = this.tm.get(string);
			ValueIdentExpr valueIdentExpr2 = this.tm.get(string2);
			DataType dataType2 = valueIdentExpr.getDataType();
			DataType dataType3 = valueIdentExpr2.getDataType();
			if (!dataType2.equals(dataType3))
				{
				String string3 = "type checking error for " + concat.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string4 = expression + " and " + expression2 + " have not same type";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string4);
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
	

	/**
	 * Oltre al tipo List controlla se i due suoi argomenti sono due liste e 
	 * gli argomenti delle due liste sono dello stesso tipo.
	 * 
	 * @param concat
	 * @param parent
	 * @param dataType
	 * @param tma
	 * @return
	 * @throws TypeRisingException
	 */
	public DataTypeEnum risingConcat(Concat concat, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		/* MODELED */
		try {
			IdentExpr expression = concat.getList1();
			IdentExpr expression2 = concat.getList2();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType,
					tma);
			if (typeRising.isErrorOccurred())
				{
				// 1
				String string = "type checking error for " + concat.toString();
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
			if (!typeRising2.isErrorOccurred())
				{
				// 2
				String string = "type checking error for " + concat.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum.equals(DataTypeEnum.List)) 
				{
				// 3
				String string = "type checking error for " + concat.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression.toString() + " is not a list";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum2.equals(DataTypeEnum.List)) 
				{
				// 4
				String string = "type checking error for " + concat.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression2.toString() + " is not a list";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			String string = expression.getNome();
			String string2 = expression2.getNome();
			ValueIdentExpr valueIdentExpr = tma.get(string);
			ValueIdentExpr valueIdentExpr2 = tma.get(string2);
			DataType dataType2 = valueIdentExpr.getDataType();
			DataType dataType3 = valueIdentExpr2.getDataType();
			if (!dataType2.equals(dataType3))
				{
				// 5
				String string3 = "type checking error for " + concat.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string4 = expression + " and " + expression2 + " have not same type";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string4);
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
