package equivalenzaComportamentale.secondRelease.riconoscimento.azioni;

import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.RateInf;
import utilities.ErrorMessage;
import equivalenzaComportamentale.AETinteractionsParts;

public class ChooseAction extends QNAction implements AEmiliaBase
	{
	
	private Action action;
	private AETinteractions tinteractions;
	private boolean isChoose;
	private ErrorMessage errorMessage;
	private int depth;
	
	public ChooseAction(Action action,
			AETinteractions tinteractions, int depth) 
		{
		/* MODELLED */
		super();
		this.action = action;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		isChoose = true;
		// Un'azione di prosecuzione del percorsoe'
		// definita da un'azione interna
		ActionType actionType = this.action.getType();
		String string = actionType.getName();
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(this.tinteractions);
		List<String> list = tinteractionsParts.getNamesFromInteractions();
		// se il nome dell'azionee'contenuto in list si restituisce false
		// perche' vuol dire che l'azione none'interna
		if (list.contains(string)) 
			{
			// 1
			String string2 = action.toString() + " is not choose action";
			this.errorMessage.setErrorMessage(string2);
			String string3 = string + " is interaction";
			ErrorMessage errorMessage = new ErrorMessage(depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			list2.add(errorMessage);
			isChoose = false;
			return;
			}
		// L'azione deve essere immediata
		ActionRate actionRate = this.action.getRate();
		if (!(actionRate instanceof RateInf))
			{
			// 2
			String string2 = action.toString() + " is not choose action";
			this.errorMessage.setErrorMessage(string2);
			String string3 = string + " is not immediate rate";
			ErrorMessage errorMessage = new ErrorMessage(depth + 1);
			errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			list2.add(errorMessage);
			isChoose = false;
			return;
			}
		}
	
	
	public ChooseAction(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public RateInf getRate()
		{
		RateInf rateInf = (RateInf)this.action.getRate();
		return rateInf;
		}

	public boolean isChoose()
		{
		return isChoose;
		}


	@Override
	public Object copy() 
		{
		ChooseAction chooseAction = new ChooseAction(this.depth + 1);
		// action setting
		Action actionCloned = this.action.copy();
		chooseAction.action = actionCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		chooseAction.tinteractions = tinteractionsCloned;
		return chooseAction;
		}


	@Override
	public void print() 
		{
		System.out.println("ChooseAction object");
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
