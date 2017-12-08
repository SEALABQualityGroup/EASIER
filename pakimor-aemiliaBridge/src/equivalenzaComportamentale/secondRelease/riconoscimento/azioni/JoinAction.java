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

public class JoinAction extends QNAction implements AEmiliaBase 
	{

	private Action action;
	
	private AETinteractions tinteractions;
	
	private boolean isJoin;
	private int depth;
	private ErrorMessage errorMessage;

	public JoinAction(Action action, AETinteractions tinteractions, int depth) 
		{
		/* MODELED */
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		isJoin = true;
		// Un'azione di joine'definita da una 
		// and interazione di input
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts =
			new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getAndInput();
		if (!list.contains(string)) 
			{
			// 1
			String string2 = action.toString() + " is not join action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = string + " is not AND and input interaction";
			ErrorMessage errorMessage = new ErrorMessage(depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isJoin = isJoin && false;
			return;
			}
		// join deve essere un'azione immediata
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof RateInf))
			{
			// 2
			String string2 = action.toString() + " is not join action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = actionRate.toString() + " is not immediate rate";
			ErrorMessage errorMessage = new ErrorMessage(depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isJoin = isJoin && false;
			return;
			}
		isJoin = isJoin && true;
		}
	
	public JoinAction(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}
	
	public boolean isJoin()
		{
		return isJoin;
		}

	@Override
	public Object copy() 
		{
		JoinAction joinAction = new JoinAction(this.depth);
		// action setting
		Action actionCloned = this.action.copy();
		joinAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		joinAction.tinteractions = tinteractionsCloned;
		return joinAction;
		}

	@Override
	public void print() 
		{
		System.out.println("JoinAction object");
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
