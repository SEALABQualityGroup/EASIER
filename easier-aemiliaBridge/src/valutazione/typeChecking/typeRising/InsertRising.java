/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Insert;
import specificheAEmilia.ListType;
import specificheAEmilia.NormalType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeCompare;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class InsertRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public InsertRising(int depth)
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

	/**
	 * Controlla se l'elemento da inserire e gli elementi della lista sono dello stesso tipo.
	 * 
	 * @param insert
	 * @param parent
	 * @param dataType
	 * @return
	 * @throws TypeRisingException
	 */
	public DataTypeEnum risingInsert(Insert insert, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		try {
			Expression expression = insert.getItem();
			IdentExpr expression2 = insert.getList();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(insert, parent, dataType, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string2 = "type checking error for " + insert.toString();
				this.errorMessage.setErrorMessage(string2);
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
				String string2 = "type checking error for " + insert.toString();
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum2.equals(DataTypeEnum.List)) 
				{
				String string2 = "type checking error for " + insert.toString();
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = expression2 + " is not a " + DataTypeEnum.List;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			String string = expression2.getNome();
			ValueIdentExpr valueIdentExpr = this.tm.get(string);
			DataType dataType2 = valueIdentExpr.getDataType();
			// per precondizione dataType2e'ListType
			ListType listType = (ListType)dataType2;
			NormalType normalType = listType.getType();
			TypeCompare typeCompare = new TypeCompare();
			if (!typeCompare.sameType(normalType, dataTypeEnum))
				{
				String string2 = "type checking error for " + insert.toString();
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string3 = " Item " + expression
						+ " and elements list " + expression2
						+ " have different type";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string3);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			else
				return DataTypeEnum.List;
			} 
		catch (Exception e) 
			{
			throw new TypeRisingException(e);
			}
		}

	/**
	 * Controlla se l'elemento da inserire e gli elementi della lista sono dello stesso tipo.
	 * 
	 * @param insert
	 * @param parent
	 * @param dataType
	 * @param tma
	 * @return il tipo List
	 * @throws TypeRisingException
	 */
	public DataTypeEnum risingInsert(Insert insert, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		try {
			Expression expression = insert.getItem();
			IdentExpr expression2 = insert.getList();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType,
					tma);
			if (typeRising.isErrorOccurred())
				{
				// 1
				String string2 = "type checking error for " + insert.toString();
				this.errorMessage.setErrorMessage(string2);
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
				String string2 = "type checking error for " + insert.toString();
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			if (!dataTypeEnum2.equals(DataTypeEnum.List)) 
				{
				// 3
				String string2 = "type checking error for " + insert.toString();
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = expression + " is not a " + DataTypeEnum.List;
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			String string = expression2.getNome();
			ValueIdentExpr valueIdentExpr = tma.get(string);
			DataType dataType2 = valueIdentExpr.getDataType();
			// per precondizione dataType2e'ListType
			ListType listType = (ListType)dataType2;
			NormalType normalType = listType.getType();
			TypeCompare typeCompare = new TypeCompare();
			if (!typeCompare.sameType(normalType, dataTypeEnum))
				{
				// 4
				String string2 = "type checking error for " + insert.toString();
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string3 = " Item " + expression
						+ " and elements list " + expression2
						+ " have different type";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string3);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			else
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
