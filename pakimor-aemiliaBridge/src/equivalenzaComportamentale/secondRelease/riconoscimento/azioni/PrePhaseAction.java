package equivalenzaComportamentale.secondRelease.riconoscimento.azioni;

import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.Expression;
import specificheAEmilia.RateInf;
import utilities.ErrorMessage;
import equivalenzaComportamentale.AETinteractionsParts;

public class PrePhaseAction extends QNAction implements AEmiliaBase
	{
	
	private Action action;
	private AETinteractions tinteractions;
	private boolean isPhaseChoice;
	private int depth;
	private ErrorMessage errorMessage;

	public PrePhaseAction(Action action, AETinteractions tinteractions, int depth) 
		{
		/* MODELLED */
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		isPhaseChoice = true;
		// Un'azione di scelta fasee'un'azione interna
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts =
			new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getNamesFromInteractions();
		if (list.contains(string))
			{
			// 1
			String string2 = action.toString() + " is not pre-phase action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = string + " is interaction";
			ErrorMessage errorMessage = new ErrorMessage(depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isPhaseChoice = isPhaseChoice && false;
			return;
			}
		// un'azione di scelta fasee'immediata
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof RateInf))
			{
			// 2
			String string2 = action.toString() + " is not pre-phase action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string3 = actionRate.toString() + " is not immediate rate";
			ErrorMessage errorMessage = new ErrorMessage(depth + 1);
			errorMessage.setErrorMessage(string3);
			list2.add(errorMessage);
			isPhaseChoice = isPhaseChoice && false;
			return;
			}
		isPhaseChoice = isPhaseChoice && true;
		}
	
	public PrePhaseAction(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public boolean isPhaseChoice()
		{
		return isPhaseChoice;
		}

	public Expression getPrio()
		{
		ActionRate actionRate = this.action.getRate();
		// per precondizione actionRatee'un RateInf
		RateInf rateInf = (RateInf) actionRate;
		Expression expression = rateInf.getPrio();
		return expression;
		}
	
	@Override
	public Object copy() 
		{
		PrePhaseAction prePhaseAction = new PrePhaseAction(this.depth);
		// action setting
		Action actionCloned = this.action.copy();
		prePhaseAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		prePhaseAction.tinteractions = tinteractionsCloned;
		return prePhaseAction;
		}

	@Override
	public void print() 
		{
		System.out.println("PrePhaseAction object");
		// action printing
		System.out.println("Action:");
		this.action.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}

	public RateInf getRate() 
		{
		ActionRate actionRate = this.action.getRate();
		// per precondizione actionRatee'un RateInf
		RateInf rateInf = (RateInf) actionRate;
		return rateInf;
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
