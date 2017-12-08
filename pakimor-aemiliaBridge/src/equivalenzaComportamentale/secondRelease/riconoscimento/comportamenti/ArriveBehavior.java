package equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
import specificheAEmilia.Boolean;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ArriveAction;

public class ArriveBehavior implements AEmiliaBase
	{
		
	private ProcessTerm processTerm;
	private AETinteractions tinteractions;
	private int depth;
	private ErrorMessage errorMessage;

	public ArriveBehavior(ProcessTerm processTerm,
			AETinteractions tinteractions, int depth) 
		{
		super();
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	public ArriveBehavior(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	/*
	 * Nella seguente verifica non consideriamo le azioni null
	 */
	public boolean isArrivalBehavior()
		{
		/* MODELLED */
		// Il comportamento di arrivi di jobse'
		// formato da uno o piu' processi alternativi, 
		// in cui ognuno di tali processie'costituito 
		// da un'azione di arrivo di jobs non condizionata
		if (!(this.processTerm instanceof ActionProcess || 
				this.processTerm instanceof ChoiceProcess))
			{
			// 1
			String string = this.processTerm.toString() + " is not arrive behavior";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = this.processTerm.toString() + " is not action or choice process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(this.processTerm, processTerm1);
		// list deve avere taglia 1
		ProcessTerm processTerm21 = list1.get(0);
		// processTerm2 deve essere un ActionProcess o uno ChoiceProcess
		// si alloca memoria per le azioni di arrivo
		List<Action> list = new ArrayList<Action>();
		if (processTerm21 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm : processTerms)
				{
				// la condizione di processTerm deve essere true
				Boolean boolean1 = new Boolean(true);
				Expression expression = processTerm.getCondition();
				if (!expression.equals(boolean1))
					{
					// 2
					String string = this.processTerm.toString() + " is not arrive behavior";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					String string2 = expression.toString() + " is not true";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list2.add(errorMessage);
					return false;
					}
				// preleviamo il comportamento null massimo
				NullBehavior nullBehavior = new NullBehavior(processTerm,this.tinteractions);
				ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(
						processTerm == null ? null : processTerm.copy(), 
						processTerm2 == null ? null : processTerm2.copy());
				// list2 deve essere composto da un unico termine di processo
				ProcessTerm processTerm3 = list2.get(0);
				// processTerm3 deve essere un ActionProcess
				if (!(processTerm3 instanceof ActionProcess))
					{
					// 3
					String string = this.processTerm.toString() + " is not arrive behavior";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list3 = this.errorMessage.getCauses();
					String string2 = processTerm3.toString() + " is not action process";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list3.add(errorMessage);
					return false;
					}
				ActionProcess actionProcess = (ActionProcess)processTerm3;
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di arrivo
				ArriveAction arriveAction = new ArriveAction(action,this.tinteractions,this.depth + 1);
				if (!arriveAction.isArrive())
					{
					// 4
					String string = this.processTerm.toString() + " is not arrive behavior";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list3 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = arriveAction.getErrorMessage();
					list3.add(errorMessage);
					return false;
					}
				else
					list.add(action);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			// la condizione di actionProcess deve essere true
			Boolean boolean1 = new Boolean(true);
			Expression expression = processTerm21.getCondition();
			if (!boolean1.equals(expression))
				{
				// 5
				String string = this.processTerm.toString() + " is not arrive behavior";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				String string2 = expression.toString() + " is not true";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list3.add(errorMessage);
				return false;
				}
			// action puo' iniziare con un'azione di arrivo o un'azione null
			NullBehavior nullBehavior = new NullBehavior(
					processTerm21 == null ? null : processTerm21.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					processTerm21 == null ? null : processTerm21.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list2 deve essere definita da soltanto un termine di processo
			// e tale termine di processo deve iniziare con un'azione Arrive
			ProcessTerm processTerm2 = list2.get(0);
			// processTerm2 deve essere un ActionProcess
			if (!(processTerm2 instanceof ActionProcess))
				{
				// 6
				String string = this.processTerm.toString() + " is not arrive behavior";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				String string2 = processTerm2.toString() + " is not action process";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list3.add(errorMessage);
				return false;
				}
			ActionProcess actionProcess = (ActionProcess)processTerm2;
			Action action = actionProcess.getAzione();
			// action deve essere un'azione di arrivo
			ArriveAction arriveAction = new ArriveAction(action,this.tinteractions,this.depth + 1);
			if (!arriveAction.isArrive())
				{
				// 7
				String string = this.processTerm.toString() + " is not arrive behavior";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = arriveAction.getErrorMessage();
				list3.add(errorMessage);
				return false;
				}
			else
				list.add(action);
			}
		else 
			{
			// 8
			String string = this.processTerm.toString() + " is not arrive behavior";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string2 = processTerm21.toString() + " is not action or choice process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list3.add(errorMessage);
			return false;
			}
		// Le azioni di arrivo devono avare nomi distinti
		if (!MetodiVari.distinctNames(list))
			{
			// 9
			String string = this.processTerm.toString() + " is not arrive behavior";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = "arrive actions have not distinct names";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list2.add(errorMessage);
			return false;
			}
		return true;
		}
		
	// restituisce null se processTerm non rappresenta un comportamento di arrivo
	public ProcessTerm getMaximalArrivalBehavior()
		{
		// Il comportamento di arrivi di jobse'
		// formato da uno o piu' processi alternativi, 
		// in cui ognuno di tali processie'costituito 
		// da un'azione di arrivo di jobs non condizionata
		if (!isArrivalBehavior())
			return null;
		// si alloca memoria per il termine di processo risultato
		ProcessTerm ris = null;
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(this.processTerm, processTerm1);
		// list ha taglia 1
		ProcessTerm processTerm21 = list.get(0);
		// processTerm21 deve essere uno ActionProcess o uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			ris = new ChoiceProcess();
			ProcessTerm[] processTerms2 = new ProcessTerm[processTerms.length];
			((ChoiceProcess)ris).setProcesses(processTerms2);
			for (int i = 0; i < processTerms.length; i++)
				{
				ProcessTerm processTerm = processTerms[i];
				// preleviamo il comportamento null massimo
				NullBehavior nullBehavior = new NullBehavior(
						processTerm == null ? null : processTerm.copy(),this.tinteractions);
				ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(
						processTerm == null ? null : processTerm.copy(), 
						processTerm2 == null ? null : processTerm2.copy());
				// list2 deve essere composto da un unico termine di processo
				ProcessTerm processTerm3 = list2.get(0);
				// processTerm3 deve essere un ActionProcess
				ActionProcess actionProcess = (ActionProcess)processTerm3;
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di arrivo
				ProcessTerm processTerm4 = actionProcess.getProcesso();
				// preleviamo il comportamento null massimo di processTerm4
				NullBehavior nullBehavior2 = new NullBehavior(
						processTerm4 == null ? null : processTerm4.copy(),this.tinteractions);
				ProcessTerm processTerm5 = nullBehavior2.getMaximalNullBehavior();
				// l'i-esimo comportamento di processTerms2 da impostaree'dato dalla
				// concatenazione processTerm2 + action + processTerm5
				ProcessTerm processTerm6 = MetodiVari.somma(action.copy(), 
						processTerm5 == null ? null : processTerm5.copy());
				ProcessTerm processTerm7 = MetodiVari.somma(
						processTerm2 == null ? null : processTerm2.copy(), 
						processTerm6 == null ? null : processTerm6.copy());
				processTerms2[i] = processTerm7;
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			// action puo' iniziare con un'azione di arrivo o un'azione null
			NullBehavior nullBehavior = new NullBehavior(
					processTerm21 == null ? null : processTerm21.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					processTerm21 == null ? null : processTerm21.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list2 deve essere definita da soltanto un termine di processo
			// e tale termine di processo deve iniziare con un'azione Arrive
			ProcessTerm processTerm2 = list2.get(0);
			// processTerm2 deve essere un ActionProcess
			ActionProcess actionProcess = (ActionProcess)processTerm2;
			Action action = actionProcess.getAzione();
			// action deve essere un'azione di arrivo
			ProcessTerm processTerm4 = actionProcess.getProcesso();
			// preleviamo il comportamento null massimo di processTerm4
			NullBehavior nullBehavior2 = new NullBehavior(
					processTerm4 == null ? null : processTerm4.copy(),this.tinteractions);
			ProcessTerm processTerm5 = nullBehavior2.getMaximalNullBehavior();
			// il comportamento da restituiree'dato dalla
			// concatenazione processTerm + action + processTerm5
			ProcessTerm processTerm6 = MetodiVari.somma(action.copy(), 
					processTerm5 == null ? null : processTerm5.copy());
			ris = MetodiVari.somma(
					processTerm == null ? null : processTerm.copy(), 
					processTerm6 == null ? null : processTerm6.copy());
			}
		else return null;
		// sommiamo a ris processTerm1
		ris = MetodiVari.somma(processTerm1, ris);
		return ris;
		}

	// restituisce il comportamento di arrivo senza azioni null
	public ProcessTerm getArrivalBehavior()
		{
		// Il comportamento di arrivi di jobse'
		// formato da uno o piu' processi alternativi, 
		// in cui ognuno di tali processie'costituito 
		// da un'azione di arrivo di jobs non condizionata
		if (!isArrivalBehavior())
			return null;
		// si alloca memoria per il termine di processo risultato
		ProcessTerm ris = null;
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(this.processTerm, processTerm1);
		// list ha taglia 1
		ProcessTerm processTerm21 = list.get(0);
		// processTerm21 deve essere uno ActionProcess o uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			ris = new ChoiceProcess();
			ProcessTerm[] processTerms2 = new ProcessTerm[processTerms.length];
			((ChoiceProcess)ris).setProcesses(processTerms2);
			for (int i = 0; i < processTerms.length; i++)
				{
				ProcessTerm processTerm = processTerms[i];
				// preleviamo il comportamento null massimo
				NullBehavior nullBehavior = new NullBehavior(
						processTerm == null ? null : processTerm.copy(),this.tinteractions);
				ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(
						processTerm == null ? null : processTerm.copy(), 
						processTerm2 == null ? null : processTerm2.copy());
				// list2 deve essere composto da un unico termine di processo
				ProcessTerm processTerm3 = list2.get(0);
				// processTerm3 deve essere un ActionProcess
				ActionProcess actionProcess = (ActionProcess)processTerm3;
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di arrivo
				processTerms2[i] = new ActionProcess();
				((ActionProcess)processTerms2[i]).setAzione(action);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			// action puo' iniziare con un'azione di arrivo o un'azione null
			NullBehavior nullBehavior = new NullBehavior(
					processTerm21 == null ? null : processTerm21.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					processTerm21 == null ? null : processTerm21.copy(), 
							processTerm == null ? null : processTerm.copy());
			// list2 deve essere definita da soltanto un termine di processo
			// e tale termine di processo deve iniziare con un'azione Arrive
			ProcessTerm processTerm2 = list2.get(0);
			// processTerm2 deve essere un ActionProcess
			ActionProcess actionProcess = (ActionProcess)processTerm2;
			Action action = actionProcess.getAzione();
			// action deve essere un'azione di arrivo
			ris = new ActionProcess();
			((ActionProcess)ris).setAzione(action);
			}
		else return null;
		return ris;
		}
	
	// restituisce i nomi delle azioni 
	// di arrivo. Restituisce null se processTerm none'un comportamento di arrivi.
	public List<String> getArriveNames()
		{
		if (!isArrivalBehavior())
			return null;
		// si alloca memoria per le azioni di arrivo
		List<String> list = new ArrayList<String>();
		// Il comportamento di arrivi di jobse'
		// formato da uno o piu' processi alternativi, 
		// in cui ognuno di tali processie'costituito 
		// da un'azione di arrivo di jobs non condizionata
		// si riconosce un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list21 = MetodiVari.differenza(this.processTerm, processTerm1);
		// list2 deve avere taglia 1
		ProcessTerm processTerm21 = list21.get(0);
		// processTerm2 deve essere un ActionProcess o uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm : processTerms)
				{
				// preleviamo il comportamento null massimo
				NullBehavior nullBehavior = new NullBehavior(
						processTerm == null ? null : processTerm.copy(),this.tinteractions);
				ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(
						processTerm == null ? null : processTerm.copy(), 
						processTerm2 == null ? null : processTerm2.copy());
				// list2 deve essere composto da un unico termine di processo
				ProcessTerm processTerm3 = list2.get(0);
				// processTerm3 deve essere un ActionProcess
				ActionProcess actionProcess = (ActionProcess)processTerm3;
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di arrivo
				ActionType actionType = action.getType();
				String string = actionType.getName();
				list.add(string);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			// action puo' iniziare con un'azione di arrivo o un'azione null
			NullBehavior nullBehavior = new NullBehavior(
					processTerm21 == null ? null : processTerm21.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					processTerm21 == null ? null : processTerm21.copy(), 
							processTerm == null ? null : processTerm.copy());
			// per precondizione list2e'definita da soltanto un termine di processo
			// e inizia con un'azione Arrive
			ProcessTerm processTerm2 = list2.get(0);
			// processTerm2e'un ActionProcess
			ActionProcess actionProcess = (ActionProcess)processTerm2;
			Action action = actionProcess.getAzione();
			ActionType actionType = action.getType();
			String string = actionType.getName();
			list.add(string);
			}
		else return null;
		return list;
		}
	
	@Override
	public Object copy() 
		{
		ArriveBehavior arriveBehavior = new ArriveBehavior(this.depth);
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		arriveBehavior.setProcessTerm(processTermCloned);
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		arriveBehavior.setTinteractions(tinteractionsCloned);
		return arriveBehavior;
		}

	@Override
	public void print() 
		{
		System.out.println("ArriveBehavior object");
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}

	public void setProcessTerm(ProcessTerm processTerm) 
		{
		this.processTerm = processTerm;
		}

	public void setTinteractions(AETinteractions tinteractions) 
		{
		this.tinteractions = tinteractions;
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
