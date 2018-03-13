package equivalenzaComportamentale.secondRelease.riconoscimento.azioni;

import java.util.List;

import equivalenzaComportamentale.AETinteractionsParts;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.Rate_;

public class GetAction extends QNAction implements AEmiliaBase
	{

	private Action action;
	
	private AETinteractions tinteractions;
	
	private boolean isGet;

	public GetAction(Action action, AETinteractions tinteractions) 
		{
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		
		isGet = true;
		// Un'azione gete'definita 
		// da una uni o or interazione di input
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getOrInput();
		List<String> list2 = tinteractionsParts.getUniInput();
		if (!(list.contains(string) || list2.contains(string)))
			isGet = isGet && false;
		// get deve essere un'azione passiva
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof Rate_))
			isGet = isGet && false;
		isGet = isGet && true;
		}
	
	public GetAction() 
		{
		super();
		}

	public boolean isGet()
		{
		return isGet;
		}

	@Override
	public Object copy() 
		{
		GetAction getAction = new GetAction();
		// action setting
		Action actionCloned = this.action.copy();
		getAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		getAction.tinteractions = tinteractionsCloned;
		return getAction;
		}

	@Override
	public void print() 
		{
		System.out.println("GetAction object");
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
