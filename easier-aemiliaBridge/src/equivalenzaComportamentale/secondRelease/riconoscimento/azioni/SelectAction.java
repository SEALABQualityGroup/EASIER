package equivalenzaComportamentale.secondRelease.riconoscimento.azioni;

import java.util.List;

import equivalenzaComportamentale.AETinteractionsParts;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.RateInf;
import utilities.ErrorMessage;

public class SelectAction extends QNAction implements AEmiliaBase 
	{

	private Action action;
	
	private AETinteractions tinteractions;
	
	private boolean isSelect;
	private int depth;
	private ErrorMessage errorMessage;

	public SelectAction(Action action, AETinteractions tinteractions, int depth) 
		{
		/* MODELLED */
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		isSelect = true;
		// Un'azione di selezione 
		//e'definita da una uni interazione di input
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts =
			new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getUniInput();
		if (!list.contains(string))
			{
			// 1
			String string2 = action.toString() + " is not select action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = string + " is not UNI input interaction";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isSelect = isSelect && false;
			return;
			}
		// selecte'un'azione immediata
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof RateInf))
			{
			// 2
			String string2 = action.toString() + " is not select action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = actionRate.toString() + " is not immediate rate";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isSelect = isSelect && false;
			return;
			}
		isSelect = isSelect && true;
		}
	
	public SelectAction(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public boolean isSelect()
		{
		return isSelect;
		}

	@Override
	public Object copy() 
		{
		SelectAction selectAction = new SelectAction(this.depth);
		// action setting
		Action actionCloned = this.action.copy();
		selectAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		selectAction.tinteractions = tinteractionsCloned;
		return selectAction;
		}

	@Override
	public void print() 
		{
		System.out.println("SelectAction object");
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
