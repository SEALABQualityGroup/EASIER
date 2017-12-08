/**
 * 
 */
package valutazione.typeChecking;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.ConstInit;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.typeRising.TypeRising;

/**
 * @author Mirko
 *
 */
public class ConstInitChecking {

	private ConstInit constInit;
	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;

	public ConstInitChecking(ConstInit constInit, TreeMap<String, ValueIdentExpr> tma, int depth) 
		{
		super();
		this.constInit = constInit;
		this.tm = tma;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * L'espressione assegnata deve essere dello stesso tipo dell'identificatore.
	 * 
	 * @return
	 */
	public boolean checkExprType()
		throws TypeCheckingException
		{
		/* MODELED */
		try {
			Expression expression = this.constInit.getExpr();
			DataType dataType = this.constInit.getType();
			TypeRising typeRising = new TypeRising(5);
			DataTypeEnum dataTypeEnum;
			dataTypeEnum = typeRising.rising(expression, "", dataType, this.tm);
			if (typeRising.isErrorOccurred())
				{
				// 1
				String string = "type checking error for " + this.constInit.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				return false;
				}
			TypeCompare typeCompare = new TypeCompare();
			if (!typeCompare.sameType(dataType, dataTypeEnum))
				{
				// 2
				String string = "type checking error for " + this.constInit.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = dataType.toString() + " and " + dataTypeEnum.toString() + " have different type";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				return false;
				}
			return true;
			} 
		catch (Exception e) 
			{
			throw new TypeCheckingException(e);
			}
		}
	
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}	

}
