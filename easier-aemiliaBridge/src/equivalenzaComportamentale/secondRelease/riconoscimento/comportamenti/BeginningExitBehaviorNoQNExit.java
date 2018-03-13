package equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
import specificheAEmilia.Boolean;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ChooseAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ExitAction;

public class BeginningExitBehaviorNoQNExit extends ExitBehavior
	{
	
	private ProcessTerm processTerm;
	private AETinteractions tinteractions;
	private int depth;
	private ErrorMessage errorMessage;

	public BeginningExitBehaviorNoQNExit(ProcessTerm processTerm,
			AETinteractions tinteractions,int depth) 
		{
		super();
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	public BeginningExitBehaviorNoQNExit(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}
		
	// restituisce il comportamento di routing di processTerm.
	// Restituisce null se processTerm non inizia con un comportamento di routing.
	// puo' restituire un termine di processo con foglie uguali a null.
	// Gli eventuali comportamenti null vengono eliminati dal termine processo risultato.
	public ProcessTerm getBeginningJobsRoutingBehavior()
		{
		if (!(isBeginningExitBehavior()))
			return null;
		// Il comportamento per il routing di jobs puo' essere 
		// definito in uno dei seguenti modi:
		// - due o piu' processi alternativi, 
		// in cui ognunoe'costituito da un'azione di 
		// scelta non condizionata, seguita da un'eventuale azione 
		// di consegna.
		// - un processo costituito da un'azione di consegna.
		// - le azioni di scelta devono avere la stessa priorita';
		// - le azioni di consegna devono avere nomi distinti.
		ProcessTerm processTermR = null;
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
			processTermR = new ChoiceProcess();
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			ProcessTerm[] processTerms2 = new ProcessTerm[processTerms.length];
			((ChoiceProcess)processTermR).setProcesses(processTerms2);
			for (int i = 0; i < processTerms.length; i++)
				{
				ProcessTerm processTerm = processTerms[i];
				// per precondizione processTerme'un ActionProcess
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action action = actionProcess.getAzione();
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// action deve essere un'azione di scelta
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(
						processTerm2 == null ? null : processTerm2.copy(),this.tinteractions);
				ProcessTerm processTerm3 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list = MetodiVari.differenza(
						processTerm2 == null ? null : processTerm2.copy(), 
						processTerm3 == null ? null : processTerm3.copy());
				// list deve avere taglia uguale a 1
				ProcessTerm processTerm4 = list.get(0);
				// si imposta all'i-esimo termine di processo di processTerms2
				ProcessTerm processTerm5 = MetodiVari.somma(action.copy(), null);
				// per precondizione processTerm4 deve essere un ActionProcess
				// con un'azione di consegna
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				Action action2 = actionProcess2.getAzione();
				// action2 deve essere un'azione di consegna
				ProcessTerm processTerm8 = MetodiVari.somma(action2.copy(), null);
				// sommiamo processTerm5 a processTerm8 e impostiamo l'i-esimo termine di
				// processo di processTerms2
				ProcessTerm processTerm9 = MetodiVari.somma(
						processTerm5 == null ? null : processTerm5.copy(), 
						processTerm8 == null ? null : processTerm8.copy());
				processTerms2[i] = processTerm9;
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					actionProcess == null ? null : actionProcess.copy(),this.tinteractions);
			ProcessTerm processTerm4 = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm4 == null ? null : processTerm4.copy());
			// la taglia di list2e'uguale a 1
			ProcessTerm processTerm5 = list2.get(0);
			// per precondizione processTerm5e'un ActionProcess
			ActionProcess actionProcess2 = (ActionProcess)processTerm5;	
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di consegna
			// riconosciamo un eventuale comportamento null
			ProcessTerm processTerm8 = MetodiVari.somma(action.copy(), null);
			// sommiamo processTerm4 a processTerm8
			processTermR = processTerm8;
			}
		else return null;
		return processTermR;
		}

	@Override
	public Object copy() 
		{
		BeginningExitBehaviorNoQNExit exitBehavior = new BeginningExitBehaviorNoQNExit(this.depth);
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		exitBehavior.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		exitBehavior.tinteractions = tinteractionsCloned;
		return exitBehavior;
		}

	@Override
	public void print() 
		{
		System.out.println("ExitBehavior object");
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}
	
	public boolean isBeginningExitBehavior()
		{
		/* MODELLED */
		// Il comportamento per il routing di jobs puo' essere 
		// definito in uno dei seguenti modi:
		// - due o piu' processi alternativi, 
		// in cui ognunoe'costituito da un'azione di 
		// scelta non condizionata, seguita da un'azione 
		// di consegna.
		// - un processo costituito da un'azione di consegna.
		if (!(this.processTerm instanceof ChoiceProcess || 
				this.processTerm instanceof ActionProcess))
			{
			// 1
			String string = this.processTerm.toString() + " is not exit behavior with no queueing network exit";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = this.processTerm.toString() + " is not choice process or action process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		// allochiamo una lista per le azioni di scelta
		List<Action> chooseList = new ArrayList<Action>();
		// allochiamo una lista per le azioni di consegna
		List<Action> deliverList = new ArrayList<Action>();
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
			// il numero di processi deve essere maggiore o uguale a due (� vero per precondizione)
			for (ProcessTerm processTerm : processTerms)
				{
				Expression expression = processTerm.getCondition();
				// l'espressione deve essere incondizionata
				Boolean boolean1 = new Boolean(true);
				if (!boolean1.equals(expression))
					{
					// 2
					String string = this.processTerm.toString() + " is not exit behavior with no queueing network exit";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = "execution condition " + expression + " is not true";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					return false;
					}
				if (!(processTerm instanceof ActionProcess))
					{
					// 3
					String string = this.processTerm.toString() + " is not exit behavior with no queueing network exit";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = processTerm.toString() + " is not action process";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					return false;
					}
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action action = actionProcess.getAzione();
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// action deve essere un'azione di scelta
				ChooseAction chooseAction = new ChooseAction(action,this.tinteractions,this.depth + 1);
				if (!chooseAction.isChoose()) 
					{
					// 4
					String string = this.processTerm.toString() + " is not exit behavior with no queueing network exit";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = chooseAction.getErrorMessage();
					list.add(errorMessage);
					return false;
					}
				// preleviamo il comportamento null massimo
				NullBehavior nullBehavior = new NullBehavior(
						processTerm2 == null ? null : processTerm2.copy(),this.tinteractions);
				ProcessTerm processTerm3 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list = MetodiVari.differenza(
						processTerm2 == null ? null : processTerm2.copy(), 
						processTerm3 == null ? null : processTerm3.copy());
				// list deve essere composta da un singolo termine di processo
				ProcessTerm processTerm4 = list.get(0);
				// processTerm4 deve essere un ActionProcess
				// con un'azione di consegna
				if (!(processTerm4 instanceof ActionProcess))
					{
					// 5
					String string = this.processTerm.toString() + " is not exit behavior with no queueing network exit";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					String string2 = processTerm4.toString() + " is not action process";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list2.add(errorMessage);
					return false;
					}
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				Action action2 = actionProcess2.getAzione();
				// action2 deve essere un'azione di consegna
				ExitAction exitAction = new ExitAction(action2,this.tinteractions,this.depth + 1);
				if (!exitAction.isDeliver())
					{
					// 6
					String string = this.processTerm.toString() + " is not exit behavior with no queueing network exit";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = exitAction.getErrorMessage();
					list2.add(errorMessage);
					return false;
					}
				chooseList.add(action);
				deliverList.add(action2);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// preleviamo il comportamento null massimo di actionProcess
			NullBehavior nullBehavior = new NullBehavior(
					actionProcess == null ? null : actionProcess.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list deve essere una lista di taglia 1
			ProcessTerm processTerm2 = list.get(0);
			// processTerm2 deve essere un ActionProcess
			if (!(processTerm2 instanceof ActionProcess))
				{
				// 7
				String string = this.processTerm.toString() + " is not exit behavior with no queueing network exit";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				String string2 = processTerm2.toString() + " is not action process";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list2.add(errorMessage);
				return false;
				}
			ActionProcess actionProcess2 = (ActionProcess)processTerm2;
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di consegna
			ExitAction exitAction = new ExitAction(action,this.tinteractions,this.depth + 1);
			if (!exitAction.isDeliver())
				{
				// 8
				String string = this.processTerm.toString() + " is not exit behavior with no queueing network exit";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = exitAction.getErrorMessage();
				list2.add(errorMessage);
				return false;
				}
			deliverList.add(action);
			}
		else 
			{
			// 9
			String string = this.processTerm.toString() + " is not exit behavior with no queueing network exit";
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
	
	// restituisce il comportamento di routing massimo di processTerm con null come foglie.
	// Restituisce null se processTerm non inizia con un comportamento di routing.
	public ProcessTerm getBeginningMaximalJobsRoutingBehavior()
		{
		if (!(isBeginningExitBehavior()))
			return null;
		// Il comportamento per il routing di jobs puo' essere 
		// definito in uno dei seguenti modi:
		// - due o piu' processi alternativi, 
		// in cui ognunoe'costituito da un'azione di 
		// scelta non condizionata, seguita da un'eventuale azione 
		// di consegna.
		// - un processo costituito da un'azione di consegna.
		// - le azioni di scelta devono avere la stessa priorita';
		ProcessTerm processTermR = null;
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
			processTermR = new ChoiceProcess();
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			ProcessTerm[] processTerms2 = new ProcessTerm[processTerms.length];
			((ChoiceProcess)processTermR).setProcesses(processTerms2);
			for (int i = 0; i < processTerms.length; i++)
				{
				ProcessTerm processTerm = processTerms[i];
				// per precondizione processTerme'un ActionProcess
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action action = actionProcess.getAzione();
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// action deve essere un'azione di scelta
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(
						processTerm2 == null ? null : processTerm2.copy(),this.tinteractions);
				ProcessTerm processTerm3 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list = MetodiVari.differenza(
						processTerm2 == null ? null : processTerm2.copy(), 
						processTerm3 == null ? null : processTerm3.copy());
				// list deve avere taglia uguale a 1
				ProcessTerm processTerm4 = list.get(0);
				// si imposta all'i-esimo termine di processo di processTerms2
				ProcessTerm processTerm5 = MetodiVari.somma(action.copy(), 
						processTerm3 == null ? null : processTerm3.copy());
				// per precondizione processTerm4 deve essere un ActionProcess
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				Action action2 = actionProcess2.getAzione();
				ProcessTerm processTerm6 = actionProcess2.getProcesso();
				// action2 deve essere un'azione di consegna
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(
						processTerm6 == null ? null : processTerm6.copy(),this.tinteractions);
				ProcessTerm processTerm7 = nullBehavior2.getMaximalNullBehavior();
				// sommiamo l'azione di exit con processTerm7
				ProcessTerm processTerm8 = MetodiVari.somma(action2.copy(), 
						processTerm7 == null ? null : processTerm7.copy());
				// sommiamo processTerm5 a processTerm8 e impostiamo l'i-esimo termine di
				// processo di processTerms2
				ProcessTerm processTerm9 = MetodiVari.somma(
						processTerm5 == null ? null : processTerm5.copy(), 
						processTerm8 == null ? null : processTerm8.copy());
				processTerms2[i] = processTerm9;
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					actionProcess == null ? null : actionProcess.copy(),this.tinteractions);
			ProcessTerm processTerm4 = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm4 == null ? null : processTerm4.copy());
			// la taglia di list2e'uguale a 1
			ProcessTerm processTerm5 = list2.get(0);
			// per precondizione processTerm5e'un ActionProcess
			ActionProcess actionProcess2 = (ActionProcess)processTerm5;	
			Action action = actionProcess2.getAzione();
			ProcessTerm processTerm6 = actionProcess2.getProcesso(); 
			// action deve essere un'azione di consegna
			// riconosciamo un'altro eventuale comportamento null
			NullBehavior nullBehavior2 = new NullBehavior(
					processTerm6 == null ? null : processTerm6.copy(),this.tinteractions);
			ProcessTerm processTerm7 = nullBehavior2.getMaximalNullBehavior();
			// si imposta il termine di processo risultato
			ProcessTerm processTerm8 = MetodiVari.somma(action.copy(), 
					processTerm7 == null ? null : processTerm7.copy());
			// sommiamo processTerm4 a processTerm8
			processTermR = MetodiVari.somma(
					processTerm4 == null ? null : processTerm4.copy(), 
					processTerm8 == null ? null : processTerm8.copy());
			}
		else return null;
		// sommiamo a processTerm1 processTermR
		processTermR = MetodiVari.somma(processTerm1, processTermR);
		return processTermR;
		}
	
	public List<String> getDeliverActionNames()
		{
		if (!isBeginningExitBehavior())
			return null;
		List<String> listD = new ArrayList<String>();
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
			// il numero di processi deve essere maggiore o uguale a due
			for (ProcessTerm processTerm : processTerms)
				{
				ActionProcess actionProcess = (ActionProcess)processTerm;
				// l'espressione deve essere incondizionata
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// action deve essere un'azione di scelta
				// preleviamo il comportamento null massimo
				NullBehavior nullBehavior = new NullBehavior(
						processTerm2 == null ? null : processTerm2.copy(),this.tinteractions);
				ProcessTerm processTerm3 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list = MetodiVari.differenza(
						processTerm2 == null ? null : processTerm2.copy(), 
						processTerm3 == null ? null : processTerm3.copy());
				// list deve essere composta da un singolo termine di processo
				ProcessTerm processTerm4 = list.get(0);
				// per precondizione processTerm4 deve essere un ActionProcess
				// con un'azione di consegna
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				Action action2 = actionProcess2.getAzione();
				// action2 deve essere un'azione di consegna
				ActionType actionType = action2.getType();
				String string = actionType.getName();
				listD.add(string);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// preleviamo il comportamento null massimo di actionProcess
			NullBehavior nullBehavior = new NullBehavior(
					actionProcess == null ? null : actionProcess.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list deve essere una lista di taglia 1
			ProcessTerm processTerm2 = list.get(0);
			// processTerm2 deve essere un ActionProcess
			ActionProcess actionProcess2 = (ActionProcess)processTerm2;
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di consegna
			ActionType actionType = action.getType();
			String string = actionType.getName();
			listD.add(string);
			}
		return listD;
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
