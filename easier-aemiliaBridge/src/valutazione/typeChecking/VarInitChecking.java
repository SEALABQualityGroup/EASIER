package valutazione.typeChecking;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.VarInit;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.typeRising.TypeRising;

/**
 * @author Mirko
 *
 */
public class VarInitChecking 
	{
	
	private VarInit varInit;
	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;

	public VarInitChecking(VarInit varInit, TreeMap<String, ValueIdentExpr> tma, int depth) 
		{
		super();
		this.varInit = varInit;
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
		try {
			Expression expression = this.varInit.getExpr();
			DataType dataType = this.varInit.getType();
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum;
			dataTypeEnum = typeRising.rising(expression, "", dataType, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string = "Checking error for " + this.varInit.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list.add(errorMessage);
				return false;
				}
			TypeCompare typeCompare = new TypeCompare();
			boolean b = typeCompare.sameType(dataType, dataTypeEnum);
			if (!b)
				{
				String string = "Checking error for " + this.varInit.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = expression.toString() + "  has not type " + dataType.toString();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				}
			return b;
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
