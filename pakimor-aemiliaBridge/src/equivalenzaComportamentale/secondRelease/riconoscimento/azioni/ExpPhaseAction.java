package equivalenzaComportamentale.secondRelease.riconoscimento.azioni;

import java.util.List;

import equivalenzaComportamentale.AETinteractionsParts;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.RateExp;
import utilities.ErrorMessage;

public class ExpPhaseAction extends QNAction implements AEmiliaBase
	{
	
	private Action action;
	
	private AETinteractions tinteractions;
	
	private boolean isPhase;
	private int depth;
	private ErrorMessage errorMessage;

	public ExpPhaseAction(Action action, AETinteractions tinteractions, int depth) 
		{
		/* MODELLED */
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		isPhase = true;
		// Un'azione di fasee'un'azione interna
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts =
			new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getNamesFromInteractions();
		if (list.contains(string))
			{
			// 1
			String string2 = action.toString() + " is not exp-phase action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = string + " is interaction";
			ErrorMessage errorMessage = new ErrorMessage(depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isPhase = isPhase && false;
			return;
			}
		// l'azione di fasee'esponenziale
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof RateExp))
			{
			// 2
			String string2 = action.toString() + " is not exp-phase action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = actionRate.toString() + " is not exponential rate";
			ErrorMessage errorMessage = new ErrorMessage(depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isPhase = isPhase && false;
			return;
			}
		isPhase = isPhase && true;
		}
	
	public ExpPhaseAction(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}
	
	public boolean isPhase()
		{
		return isPhase;
		}

	@Override
	public Object copy() 
		{
		ExpPhaseAction expPhaseAction = new ExpPhaseAction(this.depth);
		// action setting
		Action actionCloned = this.action.copy();
		expPhaseAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		expPhaseAction.tinteractions = tinteractionsCloned;
		return expPhaseAction;
		}

	@Override
	public void print() 
		{
		System.out.println("ExpPhaseAction object");
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
