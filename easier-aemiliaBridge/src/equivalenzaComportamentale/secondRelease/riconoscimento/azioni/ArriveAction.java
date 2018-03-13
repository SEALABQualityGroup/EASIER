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

public class ArriveAction extends QNAction implements AEmiliaBase
	{
	
	private Action action;
	
	private AETinteractions tinteractions;
	
	private boolean isArrive;
	private int depth;
	private ErrorMessage errorMessage;

	public ArriveAction(Action action, AETinteractions tinteractions, int depth) 
		{
		/* MODELLED */
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		isArrive = true;
		// Un'azione di arrivoe'
		// definita da una uni o or interazione di input
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts =
			new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getOrInput();
		List<String> list2 = tinteractionsParts.getUniInput();
		if (!(list.contains(string) || list2.contains(string)))
			{
			// 1
			String string2 = action + " is not arrive action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string3 = string + " is not OR or UNI and input interaction";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list3.add(errorMessage);
			isArrive = isArrive && false;
			return;
			}
		// l'azione di arrivee'passiva
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof Rate_))
			{
			// 2
			String string2 = action + " is not arrive action";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string3 = actionRate.toString() + " is not passive rate";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list3.add(errorMessage);
			isArrive = isArrive && false;
			return;
			}
		isArrive = isArrive && true;
		}
	
	public ArriveAction(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public boolean isArrive()
		{
		return isArrive;
		}

	@Override
	public Object copy() 
		{
		ArriveAction arriveAction = new ArriveAction(this.depth);
		// action setting
		Action actionCloned = this.action.copy();
		arriveAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		arriveAction.tinteractions = tinteractionsCloned;
		return arriveAction;
		}

	@Override
	public void print() 
		{
		System.out.println("ArriveAction object");
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
