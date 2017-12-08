package equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETinteractions;
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
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ChooseAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ExitAction;

public class ExitBehaviorWithQNExit extends ExitBehavior
	{
	
	private ProcessTerm processTerm;
	private AETinteractions tinteractions;
	private int depth;
	private ErrorMessage errorMessage;

	public ExitBehaviorWithQNExit(ProcessTerm processTerm,
			AETinteractions tinteractions, int depth) 
		{
		super();
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	public ExitBehaviorWithQNExit(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public boolean isExitBehavior()
		{
		/* MODELLED */
		// Il comportamento per il routing di jobs puo' essere 
		// definito in uno dei seguenti modi:
		// - due o piu' processi alternativi, 
		// in cui ognunoe'costituito da un'azione di 
		// scelta non condizionata, seguita da un'eventuale azione 
		// di consegna.
		// - un processo costituito da un'azione di consegna.
		// - tra tutte le choose-action, puo' esserci 
		// una sola di tali azioni che none'seguita 
		// da nessuna deliver-action, che indica l'uscita 
		// dalla rete di code
		if (!(this.processTerm instanceof ChoiceProcess || 
				this.processTerm instanceof ActionProcess))
			{
			// 1
			String string = this.processTerm.toString() + " is not exit behavior with queueing network exit";
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
		// processTerm21 deve essere uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			// il numero di processi deve essere maggiore o uguale a due
			for (ProcessTerm processTerm : processTerms)
				{
				Expression expression = processTerm.getCondition();
				// l'espressione deve essere incondizionata
				Boolean boolean1 = new Boolean(true);
				if (!boolean1.equals(expression))
					{
					// 2
					String string = this.processTerm + " is not exit behavior with queueing network exit";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = expression.toString() + " is not true";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					return false;
					}
				if (!(processTerm instanceof ActionProcess))
					{
					// 3
					String string = this.processTerm.toString() + " is not exit behavior with queueing network exit";
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
				ProcessTerm processTerm4 = actionProcess.getProcesso();
				// action deve essere un'azione di scelta
				ChooseAction chooseAction = new ChooseAction(action,this.tinteractions,this.depth + 1);
				if (!chooseAction.isChoose()) 
					{
					// 4
					String string = this.processTerm + " is not exit behavior with queueing network exit";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = chooseAction.getErrorMessage();
					list.add(errorMessage);
					return false;
					}
				else
					chooseList.add(action);
				// per precondizione processTerm4 deve essere un ActionProcess
				// con un'azione di consegna
				// o un BehavProcess, eventualmente preceduto da un comportamento null
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(
						processTerm4 == null ? null : processTerm4.copy(),this.tinteractions);
				ProcessTerm processTerm5 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(
						processTerm4 == null ? null : processTerm4.copy(), 
						processTerm5 == null ? null : processTerm5.copy());
				// list2 deve avere taglia uguale a 1
				ProcessTerm processTerm6 = list2.get(0);
				// processTerm6 puo' essere un ActionProcess
				if (processTerm6 instanceof ActionProcess)
					{
					ActionProcess actionProcess4 = (ActionProcess)processTerm6;
					Action action2 = actionProcess4.getAzione();
					// action2 deve essere un'azione di consegna
					ExitAction exitAction = new ExitAction(action2,this.tinteractions,this.depth + 1);
					if (!exitAction.isDeliver())
						{
						// 5
						String string = this.processTerm + " is not exit behavior with queueing network exit";
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list3 = this.errorMessage.getCauses();
						ErrorMessage errorMessage = exitAction.getErrorMessage();
						list3.add(errorMessage);
						return false;
						}
					else
						deliverList.add(action2);
					// riconosciamo un eventuale comportamento null
					ProcessTerm pt = actionProcess4.getProcesso();
					NullBehavior nullBehavior = new NullBehavior(pt,this.tinteractions);
					ProcessTerm pt2 = nullBehavior.getMaximalNullBehavior();
					List<ProcessTerm> list = MetodiVari.differenza(pt.copy(), pt2 == null ? null : pt2.copy());
					// list ha taglia 1
					ProcessTerm pt3 = list.get(0);
					// riconosciamo la chiamata comportamentale finale
					// pt3 deve essere un BehavProcess
					if (!(pt3 instanceof BehavProcess))
						{
						// 6
						String string = this.processTerm + " is not exit behavior with queueing network exit";
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list3 = this.errorMessage.getCauses();
						String string2 = pt3.toString() + " is not behavior call process";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list3.add(errorMessage);
						return false;
						}
					}
				// processTerm6 deve essere un BehavProcess, altrimenti
				// si potrebbero riconoscere comportamenti in cui 
				// processTerm6e'uno ChoiceProcess (<choose1, inf(1,0.3)>.<deliver1, inf(1,0.4)>)
				// con deliver1 che none'un'interazione
				else if (!(processTerm6 instanceof BehavProcess))
					{
					// 7
					String string = this.processTerm + " is not exit behavior with queueing network exit";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list3 = this.errorMessage.getCauses();
					String string2 = processTerm6.toString() + " is not behavior call process";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list3.add(errorMessage);
					return false;
					}
				}
			}
		else 
			{
			// 8
			String string = this.processTerm.toString() + " is not exit behavior with queueing network exit";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = processTerm21.toString() + " is not choice process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		// il numero delle azioni di consegna deve essere uguale 
		// al numero delle azioni di scelta - 1 
		int i = chooseList.size();
		int j = deliverList.size();
		if (!(i - 1 == j))
			{
			// 9
			String string = this.processTerm.toString() + " is not exit behavior with queueing network exit";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = " #(deliver actions) must be #(choice actions) - 1";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	// restituisce il comportamento di routing massimo di processTerm.
	// Restituisce null se processTerm non corrisponde a un comportamento di routing.
	public ProcessTerm getMaximalJobsRoutingBehavior()
		{
		if (!(isExitBehavior()))
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
				// con un'azione di consegna o un BehavProcess
				if (processTerm4 instanceof ActionProcess)
					{
					ActionProcess actionProcess2 = (ActionProcess)processTerm4;
					Action action2 = actionProcess2.getAzione();
					ProcessTerm processTerm6 = actionProcess2.getProcesso();
					// action2 deve essere un'azione di consegna
					// riconosciamo un eventuale comportamento null
					NullBehavior nullBehavior2 = new NullBehavior(
							processTerm6 == null ? null : processTerm6.copy(),this.tinteractions);
					ProcessTerm processTerm7 = nullBehavior2.getMaximalNullBehavior();
					// sommiamo l'azione di exit con processTerm6
					ProcessTerm processTerm8 = MetodiVari.somma(action2.copy(), 
							processTerm7 == null ? null : processTerm7.copy());
					// facciamo la differenza tra processTerm6 e processTerm7
					List<ProcessTerm> list2 = MetodiVari.differenza(processTerm6, processTerm7);
					// list2.get(0) corrisponde alla chiamata comportamentale finale
					processTerm8 = MetodiVari.somma(processTerm8, list2.get(0));
					// sommiamo processTerm5 a processTerm8 e impostiamo l'i-esimo termine di
					// processo di processTerms2
					ProcessTerm processTerm9 = MetodiVari.somma(
							processTerm5 == null ? null : processTerm5.copy(), 
							processTerm8 == null ? null : processTerm8.copy());
					processTerms2[i] = processTerm9;
					}
				else
					{
					processTerm5 = MetodiVari.somma(processTerm5, processTerm4);
					processTerms2[i] = processTerm5;
					}
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
			// facciamo la differenza tra processTerm6 e processTerm7
			List<ProcessTerm> list = MetodiVari.differenza(processTerm6, processTerm7);
			// list.get(0) corrisponde alla chiamata comportamentale finale
			processTerm8 = MetodiVari.somma(processTerm8, list.get(0));
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
		
	// restituisce i nomi delle azioni di consegna.
	// restituisce null se il comportamento none'un processo di
	// routing di jobs
	// restituisce un array non vuoto.
	public List<String> getDeliverActionNames()
		{
		if (!isExitBehavior())
			return null;
		List<String> list = new ArrayList<String>();
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
				ProcessTerm processTerm4 = actionProcess.getProcesso();
				// per precondizione processTerm4 deve essere un ActionProcess
				// con un'azione di consegna
				// o un BehavProcess, eventualmente preceduto da un comportamento null
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(processTerm4,this.tinteractions);
				ProcessTerm processTerm5 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(processTerm4, processTerm5);
				// list2 deve avere taglia uguale a 1
				ProcessTerm processTerm6 = list2.get(0);
				// processTerm6 puo' essere un ActionProcess
				if (processTerm6 instanceof ActionProcess)
					{
					ActionProcess actionProcess4 = (ActionProcess)processTerm6;
					Action action2 = actionProcess4.getAzione();
					// action2 deve essere un'azione di consegna
					ActionType actionType = action2.getType();
					String string = actionType.getName();
					list.add(string);
					}
				else
					list.add(null);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(actionProcess,this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(actionProcess, processTerm);
			// list deve avere taglia 1
			ProcessTerm processTerm2 = list2.get(0);
			// processTerm2 deve essere un ActionProcess
			ActionProcess actionProcess2 = (ActionProcess)processTerm2;
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di consegna
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
		ExitBehaviorWithQNExit exitBehavior = new ExitBehaviorWithQNExit(this.depth + 1);
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
		System.out.println("ExitBehaviorWithQNExit object");
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}
	
	public ProcessTerm getJobsRoutingBehavior()
		{
		if (!(isExitBehavior()))
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
				ProcessTerm processTerm5 = MetodiVari.somma(action.copy(), null);
				// per precondizione processTerm4 deve essere un ActionProcess
				// con un'azione di consegna o un BehavProcess, eventualmente preceduto da
				// un comportamento null
				if (processTerm4 instanceof ActionProcess)
					{
					ActionProcess actionProcess2 = (ActionProcess)processTerm4;
					Action action2 = actionProcess2.getAzione();
					// action2 deve essere un'azione di consegna
					ProcessTerm pt = actionProcess2.getProcesso();
					NullBehavior nullBehavior2 = new NullBehavior(pt.copy(),this.tinteractions);
					ProcessTerm pt2 = nullBehavior2.getMaximalNullBehavior();
					List<ProcessTerm> list2 = MetodiVari.differenza(pt.copy(),pt2 == null ? null : pt2.copy());
					// list2 deve avere taglia 1
					// pt3 corrisponde alla chiamata comportamentale finale
					ProcessTerm pt3 = list2.get(0);
					// sommiamo l'azione di exit con pt3
					ProcessTerm processTerm8 = MetodiVari.somma(action2.copy(), pt3);
					// sommiamo processTerm5 a processTerm8 e impostiamo l'i-esimo termine di
					// processo di processTerms2
					ProcessTerm processTerm9 = MetodiVari.somma(
							processTerm5 == null ? null : processTerm5.copy(), 
							processTerm8 == null ? null : processTerm8.copy());
					processTerms2[i] = processTerm9;
					}
				else
					{
					processTerm5 = MetodiVari.somma(processTerm5, processTerm4);
					processTerms2[i] = processTerm5;
					}
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
			ProcessTerm pt = actionProcess2.getProcesso();
			NullBehavior nullBehavior2 = new NullBehavior(pt.copy(),this.tinteractions);
			ProcessTerm pt2 = nullBehavior2.getMaximalNullBehavior();
			List<ProcessTerm> list = MetodiVari.differenza(pt.copy(),pt2 == null ? null : pt2.copy());
			// list deve avere taglia 1
			// pt3 corrisponde alla chiamata comportamentale finale
			ProcessTerm pt3 = list.get(0);
			// sommiamo l'azione di exit con pt3
			ProcessTerm processTerm8 = MetodiVari.somma(action.copy(), pt3);
			// sommiamo processTerm4 a processTerm8
			processTermR = processTerm8;
			}
		else return null;
		return processTermR;
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
