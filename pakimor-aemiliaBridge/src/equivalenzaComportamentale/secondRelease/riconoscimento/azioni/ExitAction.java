package equivalenzaComportamentale.secondRelease.riconoscimento.azioni;

import java.util.List;

import equivalenzaComportamentale.AETinteractionsParts;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.RateNoExp;
import utilities.ErrorMessage;

public class ExitAction extends QNAction implements AEmiliaBase
	{
	
	private Action action;
	
	private AETinteractions tinteractions;
	
	private boolean isDeliver; 
	private int depth;
	private ErrorMessage errorMessage;

	public ExitAction(Action action, AETinteractions tinteractions, int depth) 
		{
		/* MODELLED */
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		isDeliver = true;
		// Un'azione di consegnae'una uni o or interazione di output
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getUniOutput();
		List<String> list2 = tinteractionsParts.getOrOutput();
		if (!(list.contains(string) || list2.contains(string)))
			{
			// 1
			String string2 = action + " is not exit action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string3 = string + " is not UNI or OR output interaction";
			ErrorMessage errorMessage = new ErrorMessage(depth + 1);
			errorMessage.setErrorMessage(string3);
			list3.add(errorMessage);
			isDeliver = false;
			return;
			}
		// non temporizzata esponenzialmente
		ActionRate actionRate = this.action.getRate();
		// il tasso dell'azione deve essere RateNoExp
		if (!(actionRate instanceof RateNoExp))
			{
			// 2
			String string2 = action + " is not exit action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string3 = actionRate.toString() + " is exponential rate";
			ErrorMessage errorMessage = new ErrorMessage(depth + 1);
			errorMessage.setErrorMessage(string3);
			list3.add(errorMessage);
			isDeliver = false;
			return;
			}
		}
	
	public ExitAction(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}
	
	public boolean isDeliver()
		{
		return isDeliver;
		}

	public String getName()
		{
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		return string;
		}
	
	@Override
	public Object copy() 
		{
		ExitAction exitAction = new ExitAction(this.depth);
		// action setting
		Action actionCloned = this.action.copy();
		exitAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		exitAction.tinteractions = tinteractionsCloned;
		return exitAction;
		}

	@Override
	public void print() 
		{
		System.out.println("ExitAction object");
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
