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

public class ForkAction extends QNAction implements AEmiliaBase 
	{

	private Action action;
	
	private AETinteractions tinteractions;
	
	private boolean isFork;
	private int depth;
	private ErrorMessage errorMessage;

	public ForkAction(Action action, AETinteractions tinteractions, int depth) 
		{
		/* MODELLED */
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		isFork = true;
		// Un'azione di forke'definita da una 
		// and interazione di output
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getAndOutput();
		if(!list.contains(string)) 
			{
			// 1
			String string2 = action.toString() + " is not fork action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = string + " is not AND and output interaction";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);			
			isFork = isFork && false;
			return;
			}
		// un'azione forke'immediata
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof RateInf)) 
			{
			// 2
			String string2 = action.toString() + " is not fork action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = actionRate.toString() + " is not immediate rate";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isFork = isFork && false;
			return;
			}
		isFork = isFork && true;
		}
	
	public ForkAction(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}
	
	public boolean isFork()
		{
		return isFork;
		}

	@Override
	public Object copy() 
		{
		ForkAction forkAction = new ForkAction(this.depth);
		// action setting
		Action actionCloned = this.action.copy();
		forkAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		forkAction.tinteractions = tinteractionsCloned;
		return forkAction;
		}

	@Override
	public void print() 
		{
		System.out.println("ForkAction object");
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
