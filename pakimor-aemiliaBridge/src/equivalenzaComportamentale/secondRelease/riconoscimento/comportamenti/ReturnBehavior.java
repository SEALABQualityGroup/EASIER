package equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Boolean;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ReturnAction;

public class ReturnBehavior 
	implements AEmiliaBase
	{

	private ProcessTerm processTerm;
	private AETinteractions tinteractions;
	private int depth;
	private ErrorMessage errorMessage;

	public ReturnBehavior(ProcessTerm processTerm,
			AETinteractions tinteractions,int depth) 
		{
		super();
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	public ReturnBehavior(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public boolean isReturnBehavior()
		{
		/* MODELLED */
		// Il comportamento per il ritorno di un job puo' 
		// essere definito in uno dei seguenti modi:
		// - due o piu' processi alternativi, in cui ognunoe'
		// costituito da un'azione di ritorno non 
		// condizionata.
		// - un processo costituito da un'azione di ritorno.
		// Nota:
		// - le azioni di ritorno devono avare nomi distinti.
		if (!(this.processTerm instanceof ChoiceProcess || this.processTerm instanceof ActionProcess))
			{
			// 1
			String string = this.processTerm.toString() + " is not return behavior";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = this.processTerm.toString() + " is not choice process or action process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		// allochiamo memoria per le azioni di ritorno
		List<Action> returnList = new ArrayList<Action>();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(this.processTerm, processTerm1);
		// list deve avere taglia 1
		ProcessTerm processTerm21 = list1.get(0);
		// processTerm21 deve essere un ActionProcess o uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm : processTerms)
				{
				// processTerm deve avere condizione di esecuzione uguale a true
				Expression expression = processTerm.getCondition();
				Boolean boolean1 = new Boolean(true);
				if (!expression.equals(boolean1))
					{
					// 2
					String string = this.processTerm.toString() + " is not return behavior";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = "execution expression " + expression.toString() + " is not true";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					return false;
					}
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(
						processTerm == null ? null : processTerm.copy(),this.tinteractions);
				ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list = MetodiVari.differenza(
						processTerm == null ? null : processTerm.copy(), 
								processTerm2 == null ? null : processTerm2.copy());
				// list deve avere taglia 1
				ProcessTerm processTerm3 = list.get(0);
				// processTerm3 deve essere un ActionProcess
				if (!(processTerm3 instanceof ActionProcess))
					{
					// 3
					String string = this.processTerm.toString() + " is not return behavior";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					String string2 = processTerm3.toString() + " is not action process";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list2.add(errorMessage);
					return false;
					}
				ActionProcess actionProcess = (ActionProcess)processTerm3;
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di ritorno
				ReturnAction return1 = new ReturnAction(action,this.tinteractions,this.depth + 1);
				if (!return1.isReturn())
					{
					// 4
					String string = this.processTerm.toString() + " is not return behavior";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = return1.getErrorMessage();
					list2.add(errorMessage);
					return false;
					}
				else
					returnList.add(action);
				// riconosciamo un eventuale comportamento null
				ProcessTerm processTerm5 = actionProcess.getProcesso();
				NullBehavior nullBehavior2 = new NullBehavior(processTerm5.copy(),this.tinteractions);
				ProcessTerm processTerm6 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(processTerm5.copy(), processTerm6 == null ? null : processTerm6.copy());
				// restituiamo false se list2 ha taglia diversa da 1
				ProcessTerm processTerm7 = list2.get(0);
				// riconosciamo la chiamata comportamentale finale
				// processTerm5 deve essere un BehavProcess
				if (!(processTerm7 instanceof BehavProcess))
					{
					// 5
					String string = this.processTerm.toString() + " is not return behavior";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list3 = this.errorMessage.getCauses();
					String string2 = processTerm7.toString() + " is not behavior call process";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list3.add(errorMessage);
					return false;
					}
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					processTerm21 == null ? null : processTerm21.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list deve avere tagli uguale a 1
			ProcessTerm processTerm2 = list.get(0);
			// processTerm2 deve essere un ActionProcess
			if (!(processTerm2 instanceof ActionProcess))
				{
				// 6
				String string = this.processTerm.toString() + " is not return behavior";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				String string2 = processTerm2.toString() + " is not action process";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list3.add(errorMessage);
				return false;
				}
			ActionProcess actionProcess2 = (ActionProcess)processTerm2;
			Action action = actionProcess2.getAzione();
			ReturnAction return1 = new ReturnAction(action,this.tinteractions,this.depth + 1);
			if (!return1.isReturn())
				{
				// 7
				String string = this.processTerm.toString() + " is not return behavior";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = return1.getErrorMessage();
				list2.add(errorMessage);
				return false;
				}
			else
				returnList.add(action);
			// riconosciamo un eventuale comportamento null
			ProcessTerm processTerm5 = actionProcess2.getProcesso();
			NullBehavior nullBehavior2 = new NullBehavior(processTerm5.copy(),this.tinteractions);
			ProcessTerm processTerm6 = nullBehavior2.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(processTerm5.copy(), processTerm6 == null ? null : processTerm6.copy());
			// restituiamo false se list2 ha taglia diversa da 1
			ProcessTerm processTerm7 = list2.get(0);
			// riconosciamo la chiamata comportamentale finale
			// processTerm5 deve essere un BehavProcess
			if (!(processTerm7 instanceof BehavProcess))
				{
				// 8
				String string = this.processTerm.toString() + " is not return behavior";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				String string2 = processTerm7.toString() + " is not behavior call process";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list3.add(errorMessage);
				return false;
				}
			}
		else
			{
			// 9
			String string = this.processTerm.toString() + " is not return behavior";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = processTerm21 + " is not choice process or action process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list2.add(errorMessage);
			return false;
			}
		return true;
		}
	
	public ProcessTerm getMaximalReturnBehavior()
		{
		if (!isReturnBehavior())
			return null;
		ProcessTerm ris = null;
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(this.processTerm, processTerm1);
		// list deve avere taglia 1
		ProcessTerm processTerm21 = list1.get(0);
		// processTerm21 deve essere un ActionProcess o uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{	
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			ProcessTerm[] processTerms2 = new ProcessTerm[processTerms.length];
			ris = new ChoiceProcess();
			((ChoiceProcess)ris).setProcesses(processTerms2);
			for (int i = 0; i < processTerms.length; i++)
				{
				ProcessTerm processTerm = processTerms[i];
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(
					processTerm == null ? null : processTerm.copy(),this.tinteractions);
				ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list = MetodiVari.differenza(
					processTerm == null ? null : processTerm.copy(), 
							processTerm2 == null ? null : processTerm2.copy());
				// list deve avere taglia 1
				ProcessTerm processTerm3 = list.get(0);
				// processTerm3 deve essere un ActionProcess
				ActionProcess actionProcess = (ActionProcess)processTerm3;
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di ritorno
				ProcessTerm processTerm4 = actionProcess.getProcesso();
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(
						processTerm4 == null ? null : processTerm4.copy(),this.tinteractions);
				ProcessTerm processTerm5 = nullBehavior2.getMaximalNullBehavior();
				// effettuiamo la differenza tra processTerm4 e processTerm5
				List<ProcessTerm> list2 = MetodiVari.differenza(processTerm4, processTerm5);
				// list2 ha taglia 1
				ProcessTerm processTerm6 = list2.get(0);
				// processTerm6 corrisponde alla chiamata comportamentale
				processTerm5 = MetodiVari.somma(processTerm5, processTerm6);
				// sommiamo processTerm2 + action + processTerm5
				processTerms2[i] = MetodiVari.somma(action, processTerm5);
				processTerms2[i] = MetodiVari.somma(processTerm2, processTerms2[i]);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ris = new ActionProcess();
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
				processTerm21 == null ? null : processTerm21.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list = MetodiVari.differenza(
				actionProcess == null ? null : actionProcess.copy(), 
				processTerm == null ? null : processTerm.copy());
			// list deve avere tagli uguale a 1
			ProcessTerm processTerm2 = list.get(0);
			// processTerm2 deve essere un ActionProcess
			ActionProcess actionProcess2 = (ActionProcess)processTerm2;
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di ritorno
			ProcessTerm processTerm3 = actionProcess2.getProcesso();
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior2 = new NullBehavior(
					processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
			ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
			// effettuiamo la differenza tra processTerm4 e processTerm5
			List<ProcessTerm> list2 = MetodiVari.differenza(processTerm3, processTerm4);
			// list2 ha taglia 1
			ProcessTerm processTerm6 = list2.get(0);
			// processTerm6 corrisponde alla chiamata comportamentale
			processTerm4 = MetodiVari.somma(processTerm4, processTerm6);
			// sommiamo processTerm + action + processTerm4
			ris = MetodiVari.somma(action, processTerm4);
			ris = MetodiVari.somma(processTerm, ris);
			}
		else return null;
		// sommiamo a ris processTerm1
		ris = MetodiVari.somma(processTerm1, ris);
		return ris;
		}

	public ProcessTerm getReturnBehavior()
		{
		if (!isReturnBehavior())
			return null;
		ProcessTerm ris = null;
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(this.processTerm, processTerm1);
		// list deve avere taglia 1
		ProcessTerm processTerm21 = list1.get(0);
		// processTerm21 deve essere un ActionProcess o uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{	
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			ProcessTerm[] processTerms2 = new ProcessTerm[processTerms.length];
			ris = new ChoiceProcess();
			((ChoiceProcess)ris).setProcesses(processTerms2);
			for (int i = 0; i < processTerms.length; i++)
				{
				ProcessTerm processTerm = processTerms[i];
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(
						processTerm == null ? null : processTerm.copy(),this.tinteractions);
				ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list = MetodiVari.differenza(
						processTerm == null ? null : processTerm.copy(), 
						processTerm2 == null ? null : processTerm2.copy());
				// list deve avere taglia 1
				ProcessTerm processTerm3 = list.get(0);
				// processTerm3 deve essere un ActionProcess
				ActionProcess actionProcess = (ActionProcess)processTerm3;
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di ritorno
				ProcessTerm processTerm4 = actionProcess.getProcesso();
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(
						processTerm4 == null ? null : processTerm4.copy(),this.tinteractions);
				ProcessTerm processTerm5 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(processTerm4.copy(), 
						processTerm5 == null ? null : processTerm5.copy());
				// list2 deve avere taglia 1
				ProcessTerm processTerm6 = list2.get(0);
				// processTerm6 deve essere il BehavProcess finale
				processTerms2[i] = MetodiVari.somma(action, processTerm6);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					processTerm21 == null ? null : processTerm21.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
							processTerm == null ? null : processTerm.copy());
			// list deve avere taglia uguale a 1
			ProcessTerm processTerm2 = list.get(0);
			// processTerm2 deve essere un ActionProcess
			ActionProcess actionProcess2 = (ActionProcess)processTerm2;
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di ritorno
			ProcessTerm processTerm4 = actionProcess.getProcesso();
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior2 = new NullBehavior(
					processTerm4 == null ? null : processTerm4.copy(),this.tinteractions);
			ProcessTerm processTerm5 = nullBehavior2.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(processTerm4 == null ? null : processTerm4.copy(), 
					processTerm5 == null ? null : processTerm5.copy());
			// list2 deve avere taglia 1
			ProcessTerm processTerm6 = list2.get(0);
			// processTerm6 deve essere il BehavProcess finale
			// sommiamo action + null, ottenendo un ActionProcess con action come azione
			ris = MetodiVari.somma(action, processTerm6);
			}
		else return null;
		return ris;
		}

	// restituisce i nomi delle azioni di ritorno.
	// restituisce null se il comportamento none'un processo di
	// routing di ritorno di jobs
	public List<String> getReturnActionNames()
		{
		List<String> list = new ArrayList<String>();
		if (!isReturnBehavior())
			return null;
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(this.processTerm, processTerm1);
		// list deve avere taglia 1
		ProcessTerm processTerm21 = list1.get(0);
		// processTerm21 deve essere un ActionProcess o uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm : processTerms)
				{
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(
						processTerm == null ? null : processTerm.copy(),this.tinteractions);
				ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(
						processTerm == null ? null : processTerm.copy(), 
								processTerm2 == null ? null : processTerm2.copy());
				// list deve avere taglia 1
				ProcessTerm processTerm3 = list2.get(0);
				// processTerm3 deve essere un ActionProcess
				ActionProcess actionProcess = (ActionProcess)processTerm3;
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di ritorno
				ActionType actionType = action.getType();
				String string = actionType.getName();
				list.add(string);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					processTerm21 == null ? null : processTerm21.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list deve avere tagli uguale a 1
			ProcessTerm processTerm2 = list2.get(0);
			// processTerm2 deve essere un ActionProcess
			ActionProcess actionProcess2 = (ActionProcess)processTerm2;
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di ritorno
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
		ReturnBehavior returnBehavior = new ReturnBehavior(this.depth);
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		returnBehavior.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		returnBehavior.tinteractions = tinteractionsCloned;
		return returnBehavior;
		}

	@Override
	public void print() 
		{
		System.out.println("ReturnBehavior object");
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
