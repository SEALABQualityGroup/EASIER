/**
 * 
 */
package equivalenzaComportamentale.secondRelease.riconoscimento.azioni;

import java.util.List;

import equivalenzaComportamentale.AETinteractionsParts;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.RateInf;

/**
 * @author Mirko
 *
 */
public class NullAction extends QNAction implements AEmiliaBase
	{
	
	private Action action;
	
	private AETinteractions tinteractions;
	
	private boolean isNull;

	public NullAction(Action action, 
			AETinteractions tinteractions) 
		{
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		
		isNull = true;
		/*
		 * Un'azione nulle'definita da un'azione 
		 * immediata e interna.
		 */
		// l'azione null deve essere immediata
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof RateInf))
			isNull = isNull && false;
		// l'azione null deve essere un'azione interna
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getNamesFromInteractions();
		if (list.contains(string))
			isNull = isNull && false;
		isNull = isNull && true;
		}

	public NullAction() 
		{
		super();
		}

	public boolean isNull()
		{
		return isNull;
		}

	@Override
	public Object copy() 
		{
		NullAction nullAction = new NullAction();
		// action setting
		Action actionCloned = this.action.copy();
		nullAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		nullAction.tinteractions = tinteractionsCloned;
		return nullAction;
		}

	@Override
	public void print() 
		{
		System.out.println("NullAction object");
		// action printing
		System.out.println("Action:");
		this.action.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}
	
	@Override
	public String toString() 
		{
		return this.action.toString();
		}	
	}
