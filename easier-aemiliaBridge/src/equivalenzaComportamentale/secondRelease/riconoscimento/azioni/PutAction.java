package equivalenzaComportamentale.secondRelease.riconoscimento.azioni;

import java.util.List;

import equivalenzaComportamentale.AETinteractionsParts;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.Rate_;

public class PutAction extends QNAction implements AEmiliaBase 
	{

	private Action action;
	
	private AETinteractions tinteractions;
	
	private boolean isPut;

	public PutAction(Action action, AETinteractions tinteractions) 
		{
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		
		isPut = true;
		// Un'azione di pute'definita da 
		// una uni interazione di output
		// L'azione di pute'una or-interazione se e soltanto see'attaccata
		// ad un processo di servizio multiserver
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts =
			new AETinteractionsParts(this.tinteractions);
		List<String> list2 = tinteractionsParts.getUniOutput();
		List<String> list3 = tinteractionsParts.getOrOutput();
		if (!(list2.contains(string)) && !(list3.contains(string)))
			isPut = isPut && false;
		// l'azione di pute'passiva
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof Rate_))
			isPut = isPut && false;
		isPut = isPut && true;
		}
	
	public PutAction() 
		{
		super();
		}

	public boolean isPut()
		{
		return isPut;
		}

	@Override
	public Object copy() 
		{
		PutAction putAction = new PutAction();
		// action setting
		Action actionCloned = this.action.copy();
		putAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		putAction.tinteractions = tinteractionsCloned;
		return putAction;
		}

	@Override
	public void print() 
		{
		System.out.println("PutAction object");
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
