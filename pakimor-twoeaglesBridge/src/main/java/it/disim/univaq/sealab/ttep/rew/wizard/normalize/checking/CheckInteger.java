/**
 * 
 */
package it.disim.univaq.sealab.ttep.rew.wizard.normalize.checking;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeCheckingException;
import valutazione.typeChecking.typeRising.TypeRising;

/**
 * @author Mirko
 *
 */
public class CheckInteger {

	private Expression expression;
	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;
	
	public CheckInteger(Expression expression,
			TreeMap<String, ValueIdentExpr> tm, int depth) 
		{
		super();
		this.expression = expression;
		this.tm = tm;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	/**
	 * L'espressione deve essere un intero.
	 *  
	 * @return
	 * @throws TypeCheckingException
	 */
	public boolean checkInteger()
		throws TypeCheckingException
		{
		try {
			TypeRising typeRising = new TypeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = typeRising.rising(this.expression, "",
					null, this.tm);
			if (typeRising.isErrorOccurred())
				{
				String string = "type checking error for " + this.expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = typeRising.getErrorMessage();
				list2.add(errorMessage);
				return false;
				}
			if (!DataTypeEnum.Integer.equals(dataTypeEnum))
				{
				String string = "type checking error for " + this.expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				String string2 = dataTypeEnum + " is not integer";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list2.add(errorMessage);
				return false;
				}
			else
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
