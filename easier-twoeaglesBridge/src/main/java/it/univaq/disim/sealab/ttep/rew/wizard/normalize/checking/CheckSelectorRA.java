/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.wizard.normalize.checking;

import java.util.List;
import java.util.TreeMap;

import it.univaq.disim.sealab.ttep.rew.classes.RewardAssign;
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
public class CheckSelectorRA {

	private RewardAssign rewardAssign;
	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;
	
	public CheckSelectorRA(RewardAssign rewardAssign,
			TreeMap<String, ValueIdentExpr> treeMap, int depth) 
		{
		super();
		this.rewardAssign = rewardAssign;
		this.tm = treeMap;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	/**
	 * Il selettore deve essere valutato come un intero.
	 * 
	 * @return
	 * @throws TypeCheckingException
	 */
	public boolean checkSelector()
		throws TypeCheckingException
		{
		try {
			Expression expression = this.rewardAssign.getSelector();
			if (expression != null) 
				{
				TypeRising typeRising = new TypeRising(this.depth + 1);
				typeRising.setTm(this.tm);
				DataTypeEnum dataTypeEnum;
				dataTypeEnum = typeRising.rising(expression, "", null, this.tm);
				if (typeRising.isErrorOccurred())
					{
					String string = "type checking error for " + this.rewardAssign.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = typeRising.getErrorMessage();
					list.add(errorMessage);
					return false;
					}
				if (dataTypeEnum.equals(DataTypeEnum.Integer)) 
					{
					return true;
					} 
				else 
					{
					String string = "type checking error for " + this.rewardAssign.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = dataTypeEnum + " is not integer type";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					return false;
					}
				} 
			else 
				{
				return true;
				}
			} 
		catch (Exception e) 
			{
			throw new TypeCheckingException(e);
			}
		}
	
	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}	
	}
