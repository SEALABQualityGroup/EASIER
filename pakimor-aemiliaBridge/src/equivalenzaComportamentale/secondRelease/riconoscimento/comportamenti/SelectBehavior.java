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
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.SelectAction;

public class SelectBehavior 
	implements AEmiliaBase
	{

	private ProcessTerm processTerm;
	private AETinteractions tinteractions;
	private int depth;
	private ErrorMessage errorMessage;

	public SelectBehavior(ProcessTerm processTerm,
			AETinteractions tinteractions,int depth) 
		{
		super();
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	public SelectBehavior(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public boolean isSelectionBehavior()
		{
		/* MODELLED */
		// Il comportamento di selezione di jobse'formato 
		// da uno o piu' processi alternativi, in cui ognuno di tali 
		// processi inizia con un'azione di selezione di jobs non condizionata
		if (!(this.processTerm instanceof ActionProcess || 
				this.processTerm instanceof ChoiceProcess))
			{
			// 1
			String string = this.processTerm.toString() + " is not select behavior";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = this.processTerm.toString() + " is not action or choice process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		// si alloca memoria per le azioni di selezione
		List<Action> list = new ArrayList<Action>();
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
				// la condizione di processTerm deve essere true
				Boolean boolean1 = new Boolean(true);
				Expression expression = processTerm.getCondition();
				if (!expression.equals(boolean1))
					{
					// 2
					String string = this.processTerm.toString() + " is not select behavior";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					String string2 = expression.toString() + " is not true";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list2.add(errorMessage);
					return false;
					}
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
				if (!(processTerm3 instanceof ActionProcess))
					{
					// 3
					String string = this.processTerm.toString() + " is not select behavior";
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
				// action deve essere un'azione di selezione
				SelectAction selectAction = new SelectAction(action,this.tinteractions,this.depth + 1);
				if (!selectAction.isSelect())
					{
					// 4
					String string = this.processTerm.toString() + " is not select behavior";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list3 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = selectAction.getErrorMessage();
					list3.add(errorMessage);
					return false;
					}
				else
					list.add(action);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// la condizione di actionProcess deve essere true
			Boolean boolean1 = new Boolean(true);
			Expression expression = actionProcess.getCondition();
			if (!boolean1.equals(expression))
				{
				// 5
				String string = this.processTerm.toString() + " is not select behavior";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				String string2 = expression.toString() + " is not true";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list2.add(errorMessage);
				return false;
				}
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					actionProcess == null ? null : actionProcess.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list2 deve avere taglia uguale a 1
			ProcessTerm processTerm2 = list2.get(0);
			// processTerm2 deve essere un ActionProcess
			if (!(processTerm2 instanceof ActionProcess))
				{
				// 6
				String string = this.processTerm.toString() + " is not select behavior";
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
			// action deve essere un'azione di selezione
			SelectAction selectAction = new SelectAction(action,this.tinteractions,this.depth + 1);
			if (!selectAction.isSelect())
				{
				// 7
				String string = this.processTerm.toString() + " is not select behavior";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = selectAction.getErrorMessage();
				list3.add(errorMessage);
				return false;
				}
			else
				list.add(action);
			}
		else 
			{
			// 8
			String string = this.processTerm.toString() + " is not select behavior";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = processTerm21.toString() + " is not action or choice process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list2.add(errorMessage);
			return false;
			}
		// Le azioni di selezione devono avare nomi distinti
		if (!MetodiVari.distinctNames(list))
			{
			// 9
			String string = this.processTerm.toString() + " is not select behavior";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = "select actions have not distinct names";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list2.add(errorMessage);
			return false;
			}
		return true;
		}
	
	public ProcessTerm getMaximalSelectionBehavior()
		{
		if (!isSelectionBehavior())
			return null;
		// Il comportamento di selezione di jobse'formato 
		// da uno o piu' processi alternativi, in cui ognuno di tali 
		// processi inizia con un'azione di selezione di jobs non condizionata
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
				// action deve essere un'azione di selezione
				// riconosciamo un eventuale comportamento null
				ProcessTerm processTerm4 = actionProcess.getProcesso();
				NullBehavior nullBehavior2 = new NullBehavior(
						processTerm4 == null ? null : processTerm4.copy(),this.tinteractions);
				ProcessTerm processTerm5 = nullBehavior2.getMaximalNullBehavior();
				// poniamo l'i-esimo termine di processo di processTerms2
				// uguale a processTerm2 + action + processTerm5
				ProcessTerm processTerm6 = MetodiVari.somma(action.copy(), 
						processTerm5 == null ? null : processTerm5.copy());
				processTerms2[i] = MetodiVari.somma(
						processTerm2 == null ? null : processTerm2.copy(), 
								processTerm6 == null ? null : processTerm6.copy());
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// la condizione di actionProcess deve essere true
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					actionProcess == null ? null : actionProcess.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list2 deve avere taglia uguale a 1
			ProcessTerm processTerm2 = list2.get(0);
			// processTerm2 deve essere un ActionProcess
			ActionProcess actionProcess2 = (ActionProcess)processTerm2;
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di selezione
			// riconosciamo un eventuale comportamento null
			ProcessTerm processTerm3 = actionProcess2.getProcesso();
			NullBehavior nullBehavior2 = new NullBehavior(
					processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
			ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
			// poniamo il termine di processo risultato 
			// uguale a processTerm + action + processTerm4
			ProcessTerm processTerm5 = MetodiVari.somma(action.copy(), 
					processTerm4 == null ? null : processTerm4.copy());
			processTermR = MetodiVari.somma(
					processTerm == null ? null : processTerm.copy(), 
							processTerm5 == null ? null : processTerm5.copy());
			}
		else return null;
		// sommiamo a processTermR processTerm1
		processTermR = MetodiVari.somma(processTerm1, processTermR);
		return processTermR;
		}

	// restituiamo il comportamento di selezione senza i comportamenti null
	public ProcessTerm getSelectionBehavior()
		{
		if (!isSelectionBehavior())
			return null;
		// Il comportamento di selezione di jobse'formato 
		// da uno o piu' processi alternativi, in cui ognuno di tali 
		// processi inizia con un'azione di selezione di jobs non condizionata
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
				// action deve essere un'azione di selezione
				// poniamo l'i-esimo termine di processo di processTerms2
				// uguale a action + null
				processTerms2[i] = MetodiVari.somma(action.copy(), null);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm21;
			// la condizione di actionProcess deve essere true
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					actionProcess == null ? null : actionProcess.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list2 deve avere taglia uguale a 1
			ProcessTerm processTerm2 = list2.get(0);
			// processTerm2 deve essere un ActionProcess
			ActionProcess actionProcess2 = (ActionProcess)processTerm2;
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di selezione
			// poniamo il termine di processo risultato 
			// uguale a action + null
			processTermR = MetodiVari.somma(action.copy(), null);
			}
		else return null;
		return processTermR;
		}

	// restituisce i nomi
	// delle azioni di selezione e utilizzarlo per
	// il riconoscimento degli elementi base
	// restituisce null se processTerm none'un comportamento di selezione
	public List<String> getSelectionNames()
		{
		if (!isSelectionBehavior())
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
			for (ProcessTerm processTerm : processTerms)
				{
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(
						processTerm == null ? null : processTerm.copy(),this.tinteractions);
				ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(
						processTerm == null ? null : processTerm.copy(), 
								processTerm2 == null ? null : processTerm2.copy());
				ProcessTerm processTerm3 = list2.get(0);
				// processTerm3 deve essere un ActionProcess
				ActionProcess actionProcess = (ActionProcess)processTerm3;
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di selezione
				ActionType actionType = action.getType();
				String string = actionType.getName();
				list.add(string);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					processTerm21 == null ? null : processTerm21.copy(),this.tinteractions);
			ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(
					processTerm21 == null ? null : processTerm21.copy(), 
							processTerm2 == null ? null : processTerm2.copy());
			ProcessTerm processTerm3 = list2.get(0);
			// processTerm3 deve essere un ActionProcess
			ActionProcess actionProcess2 = (ActionProcess)processTerm3;
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di selezione
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
		SelectBehavior selectBehavior = new SelectBehavior(this.depth);
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		selectBehavior.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		selectBehavior.tinteractions = tinteractionsCloned;
		return selectBehavior;
		}

	@Override
	public void print() 
		{
		System.out.println("SelectBehavior object");
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
