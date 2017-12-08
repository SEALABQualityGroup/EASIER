package equivalenzaComportamentale.secondRelease.riconoscimento.azioni;

import java.util.List;

import equivalenzaComportamentale.AETinteractionsParts;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.Rate_;
import utilities.ErrorMessage;

public class ReturnAction extends QNAction implements AEmiliaBase 
	{

	private Action action;
	
	private AETinteractions tinteractions;
	
	private boolean isReturn;
	private int depth;
	private ErrorMessage errorMessage;

	public ReturnAction(Action action, AETinteractions tinteractions,int depth) 
		{
		/* MODELLED */
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		isReturn = true;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		// Un'azione di ritornoe'definita da una 
		// uni interazione di input
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts =
			new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getUniInput();
		if (!list.contains(string))
			{
			// 1
			String string2 = action.toString() + " is not return action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = string + " is not uni interaction";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isReturn = isReturn && false;
			return;
			}
		// l'azione return deve essere passiva
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof Rate_))
			{
			// 2
			String string2 = action.toString() + " is not return action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = actionRate.toString() + " is not passive rate";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isReturn = isReturn && false;
			return;
			}
		isReturn = isReturn && true;
		}
	
	public ReturnAction(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);				
		}

	public boolean isReturn()
		{
		return isReturn;
		}

	@Override
	public Object copy() 
		{
		ReturnAction returnAction = new ReturnAction(this.depth);
		// action setting
		Action actionCloned = this.action.copy();
		returnAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		returnAction.tinteractions = tinteractionsCloned;
		return returnAction;
		}

	@Override
	public void print() 
		{
		System.out.println("ReturnAction object");
		// action printing
		System.out.println("Action:");
		this.action.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	
	@Override
	public String toString() 
		{
		return this.action.toString();
		}	
	}
