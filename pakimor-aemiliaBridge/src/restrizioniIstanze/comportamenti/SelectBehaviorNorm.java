package restrizioniIstanze.comportamenti;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateInf;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;

public class SelectBehaviorNorm 
	implements AEmiliaBase
	{

	private ProcessTerm processTerm;
	private AETinteractions tinteractions;

	public SelectBehaviorNorm(ProcessTerm processTerm,
			AETinteractions tinteractions) 
		{
		super();
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		}
	
	public SelectBehaviorNorm() 
		{
		super();
		}

	public List<Expression> getPrioSelezione()
		{
		List<Expression> list = new ArrayList<Expression>();
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
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action action = actionProcess.getAzione();
				ActionRate actionRate = action.getRate();
				// actionRate deve essere un RateInf
				RateInf rateInf = (RateInf)actionRate;
				Expression expression = rateInf.getPrio();
				list.add(expression);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess2 = (ActionProcess)processTerm21;
			Action action = actionProcess2.getAzione();
			ActionRate actionRate = action.getRate();
			// actionRate deve essere un RateInf
			RateInf rateInf = (RateInf)actionRate;
			Expression expression = rateInf.getPrio();
			list.add(expression);
			}
		else return null;
		return list;
		}
	
	public List<Expression> getProbSelezione()
		{
		List<Expression> list = new ArrayList<Expression>();
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
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action action = actionProcess.getAzione();
				ActionRate actionRate = action.getRate();
				// actionRate deve essere un RateInf
				RateInf rateInf = (RateInf)actionRate;
				Expression expression = rateInf.getWeight();
				list.add(expression);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			ActionProcess actionProcess2 = (ActionProcess)processTerm;
			Action action = actionProcess2.getAzione();
			ActionRate actionRate = action.getRate();
			// actionRate deve essere un RateInf
			RateInf rateInf = (RateInf)actionRate;
			Expression expression = rateInf.getWeight();
			list.add(expression);
			}
		else return null;
		return list;
		}
	
	public ProcessTerm getMaximalSelectionBehavior()
		{
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
	
	// restituisce i nomi
	// delle azioni di selezione e utilizzarlo per
	// il riconoscimento degli elementi base
	// restituisce null se processTerm none'un comportamento di selezione
	public List<String> getSelectionNames()
		{
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
		SelectBehaviorNorm selectBehavior = new SelectBehaviorNorm();
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
		System.out.println("SelectBehaviorNorm object");
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}

	// restituisce i tassi 
	// delle azioni di selezione
	public List<RateInf> getSelectionRates() 
		{
		List<RateInf> list = new ArrayList<RateInf>();
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
				RateInf rateInf = (RateInf)action.getRate();
				list.add(rateInf);
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
			RateInf rateInf = (RateInf)action.getRate();
			list.add(rateInf);
			}
		else return null;
		return list;
		}
	
	@Override
	public String toString() 
		{
		return this.processTerm.toString();
		}
	}
