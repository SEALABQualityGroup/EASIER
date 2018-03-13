package restrizioniIstanze.comportamenti;

import interfacceSpecifiche.NumberExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Boolean;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateInf;
import specificheAEmilia.Real;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ChooseAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ExitAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;

public class ExitBehaviorNoQNExitNorm extends ExitBehaviorNorm
	{
	
	private ProcessTerm processTerm;
	private AETinteractions tinteractions;

	public ExitBehaviorNoQNExitNorm(ProcessTerm processTerm,
			AETinteractions tinteractions) 
		{
		super();
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		}
	
	public ExitBehaviorNoQNExitNorm() 
		{
		super();
		}
	
	// restituisce le azioni di choose.
	// restituisce null se il comportamento none'un processo di
	// routing di jobs
	public HashMap<String, List<Action>> getChooseAction()
		{
		HashMap<String, List<Action>> hashMap = new HashMap<String, List<Action>>();
		// Il comportamento per il routing di jobs puo' essere 
		// definito in uno dei seguenti modi:
		// - due o piu' processi alternativi, 
		// in cui ognunoe'costituito da un'azione di 
		// scelta non condizionata, seguita da un'azione 
		// di consegna.
		// - un processo costituito da un'azione di consegna.
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
				Action action = actionProcess.getAzione();
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
				if (hashMap.containsKey(string))
					{
					List<Action> list2 = hashMap.get(string);
					list2.add(action);
					}
				else
					{
					List<Action> list2 = new ArrayList<Action>(1);
					list2.add(action);
					hashMap.put(string, list2);
					}
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
			List<Action> list2 = new ArrayList<Action>();
			hashMap.put(string, list2);
			}
		return hashMap;
		}
	
	public HashMap<String, List<Expression>> getProbsRouting() 
		{
		HashMap<String, List<Expression>> hashMap = new HashMap<String, List<Expression>>();
		// Il comportamento per il routing di jobs puo' essere 
		// definito in uno dei seguenti modi:
		// - due o piu' processi alternativi, 
		// in cui ognunoe'costituito da un'azione di 
		// scelta non condizionata, seguita da un'azione 
		// di consegna.
		// - un processo costituito da un'azione di consegna.
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
				Action action = actionProcess.getAzione();
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// action deve essere un'azione di scelta
				ActionRate actionRate = action.getRate();
				// actionRate deve essere di tipo RateInf
				RateInf rateInf = (RateInf)actionRate;
				Expression expression = rateInf.getWeight();
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
				if (hashMap.containsKey(string))
					{
					List<Expression> list2 = hashMap.get(string);
					list2.add(expression);
					}
				else
					{
					List<Expression> list2 = new ArrayList<Expression>(1);
					list2.add(expression);
					hashMap.put(string, list2);
					}
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
			List<Expression> list2 = new ArrayList<Expression>(1);
			Expression expression = new Real(1d);
			list2.add(expression);
			hashMap.put(string, list2);
			}
		return hashMap;
		}
	
	@Override
	public Object copy() 
		{
		ExitBehaviorNoQNExitNorm exitBehavior = new ExitBehaviorNoQNExitNorm();
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
		System.out.println("ExitBehaviorNoQNExitNorm object");
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}
	
	@Override
	public BehavProcess getBehavProcess()
		{
		// Il comportamento per il routing di jobs puo' essere 
		// definito in uno dei seguenti modi:
		// - due o piu' processi alternativi, 
		// in cui ognunoe'costituito da un'azione di 
		// scelta non condizionata, seguita da un'azione 
		// di consegna.
		// - un processo costituito da un'azione di consegna.
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
			BehavProcess behavProcess = null;
			for (ProcessTerm processTerm : processTerms)
				{
				ActionProcess actionProcess = (ActionProcess)processTerm;
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
				// riconosciamo un eventuale comportamento null
				ProcessTerm processTerm5 = actionProcess2.getProcesso();
				NullBehavior nullBehavior2 = new NullBehavior(processTerm5.copy(),this.tinteractions);
				ProcessTerm processTerm6 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(processTerm5.copy(), processTerm6 == null ? null : processTerm6.copy());
				// restituiamo false se list2 ha taglia diversa da 1
				ProcessTerm processTerm7 = list2.get(0);
				// riconosciamo la chiamata comportamentale finale
				// processTerm5 deve essere un BehavProcess
				behavProcess = (BehavProcess)processTerm7;
				break;
				}
			return behavProcess;
			}
		else 
			{
			// processTerm21 deve essere un ActionProcess
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
			// riconosciamo un eventuale comportamento null
			ProcessTerm processTerm5 = actionProcess2.getProcesso();
			NullBehavior nullBehavior2 = new NullBehavior(processTerm5.copy(),this.tinteractions);
			ProcessTerm processTerm6 = nullBehavior2.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(processTerm5.copy(), processTerm6 == null ? null : processTerm6.copy());
			// restituiamo false se list2 ha taglia diversa da 1
			ProcessTerm processTerm7 = list2.get(0);
			// riconosciamo la chiamata comportamentale finale
			// processTerm7 deve essere un BehavProcess
			BehavProcess behavProcess = (BehavProcess)processTerm7;
			return behavProcess;
			}
		}

	@Override
	public HashMap<String, Double> getProbRoutingMap()
		{
		// Il comportamento per il routing di jobs puo' essere 
		// definito in uno dei seguenti modi:
		// - due o piu' processi alternativi, 
		// in cui ognunoe'costituito da un'azione di 
		// scelta non condizionata, seguita da un'azione 
		// di consegna.
		// - un processo costituito da un'azione di consegna.
		HashMap<String, Double> hashMap = new HashMap<String, Double>();
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
			// effettuo la somma dei pesi
			double somma = 0;
			for (ProcessTerm processTerm : processTerms)
				{
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di scelta
				RateInf rateInf = (RateInf)action.getRate();
				NumberExp numberExp = (NumberExp)rateInf.getWeight();
				double d = numberExp.getNumber();
				somma = somma + d;
				}
			// il numero di processi deve essere maggiore o uguale a due
			for (ProcessTerm processTerm : processTerms)
				{
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action action = actionProcess.getAzione();
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// action deve essere un'azione di scelta
				ActionRate actionRate = action.getRate();
				// per precondizione actionRate deve essre u RateInf
				RateInf rateInf = (RateInf)actionRate;
				Expression expression = rateInf.getWeight();
				// per precondizione expressione'un NumberExp
				NumberExp numberExp = (NumberExp)expression;
				double d = numberExp.getNumber();
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
				if (!hashMap.containsKey(string))
					hashMap.put(string, d / somma);
				else
					{
					double e = hashMap.get(string);
					e = e + (d / somma);
					hashMap.put(string, e);
					}
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
			hashMap.put(string, 1d);
			}
		return hashMap;
		}		
	
	public boolean isJobsRoutingBehavior()
		{
		// Il comportamento per il routing di jobs puo' essere 
		// definito in uno dei seguenti modi:
		// - due o piu' processi alternativi, 
		// in cui ognunoe'costituito da un'azione di 
		// scelta non condizionata, seguita da un'azione 
		// di consegna.
		// - un processo costituito da un'azione di consegna.
		if (!(this.processTerm instanceof ChoiceProcess || 
				this.processTerm instanceof ActionProcess))
			return false;
		// allochiamo una lista per le azioni di scelta
		List<Action> chooseList = new ArrayList<Action>();
		// allochiamo una lista per le azioni di consegna
		List<Action> deliverList = new ArrayList<Action>();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(this.processTerm, processTerm1);
		// list deve avere taglia 1
		if (list1.size() != 1)
			return false;
		ProcessTerm processTerm21 = list1.get(0);
		// processTerm21 deve essere un ActionProcess o uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			// il numero di processi deve essere maggiore o uguale a due
			if (processTerms.length < 2)
				return false;
			for (ProcessTerm processTerm : processTerms)
				{
				if (!(processTerm instanceof ActionProcess))
					return false;
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Expression expression = actionProcess.getCondition();
				// l'espressione deve essere incondizionata
				Boolean boolean1 = new Boolean(true);
				if (!boolean1.equals(expression))
					return false;
				Action action = actionProcess.getAzione();
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// action deve essere un'azione di scelta
				ChooseAction chooseAction = new ChooseAction(action,this.tinteractions,0);
				if (!chooseAction.isChoose()) 
					return false;
				// preleviamo il comportamento null massimo
				NullBehavior nullBehavior = new NullBehavior(
						processTerm2 == null ? null : processTerm2.copy(),this.tinteractions);
				ProcessTerm processTerm3 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list = MetodiVari.differenza(
						processTerm2 == null ? null : processTerm2.copy(), 
						processTerm3 == null ? null : processTerm3.copy());
				// list deve essere composta da un singolo termine di processo
				if (list.size() != 1)
					return false;
				ProcessTerm processTerm4 = list.get(0);
				// per precondizione processTerm4 deve essere un ActionProcess
				// con un'azione di consegna
				if (!(processTerm4 instanceof ActionProcess))
					return false;
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				Action action2 = actionProcess2.getAzione();
				// action2 deve essere un'azione di consegna
				ExitAction exitAction = new ExitAction(action2,this.tinteractions,0);
				if (!exitAction.isDeliver())
					return false;
				chooseList.add(action);
				deliverList.add(action2);
				// riconosciamo un eventuale comportamento null
				ProcessTerm processTerm5 = actionProcess2.getProcesso();
				NullBehavior nullBehavior2 = new NullBehavior(processTerm5.copy(),this.tinteractions);
				ProcessTerm processTerm6 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(processTerm5.copy(), processTerm6 == null ? null : processTerm6.copy());
				// restituiamo false se list2 ha taglia diversa da 1
				if (list2.size() != 1)
					return false;
				ProcessTerm processTerm7 = list2.get(0);
				// riconosciamo la chiamata comportamentale finale
				// processTerm5 deve essere un BehavProcess
				if (!(processTerm7 instanceof BehavProcess))
					return false;
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
			if (list.size() != 1)
				return false;
			ProcessTerm processTerm2 = list.get(0);
			// processTerm2 deve essere un ActionProcess
			if (!(processTerm2 instanceof ActionProcess))
				return false;
			ActionProcess actionProcess2 = (ActionProcess)processTerm2;
			Action action = actionProcess2.getAzione();
			// action deve essere un'azione di consegna
			ExitAction exitAction = new ExitAction(action,this.tinteractions,0);
			if (!exitAction.isDeliver())
				return false;
			deliverList.add(action);
			// riconosciamo un eventuale comportamento null
			ProcessTerm processTerm5 = actionProcess2.getProcesso();
			NullBehavior nullBehavior2 = new NullBehavior(processTerm5.copy(),this.tinteractions);
			ProcessTerm processTerm6 = nullBehavior2.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(processTerm5.copy(), processTerm6 == null ? null : processTerm6.copy());
			// restituiamo false se list2 ha taglia diversa da 1
			if (list2.size() != 1)
				return false;
			ProcessTerm processTerm7 = list2.get(0);
			// riconosciamo la chiamata comportamentale finale
			// processTerm7 deve essere un BehavProcess
			if (!(processTerm7 instanceof BehavProcess))
				return false;			
			}
		else return false;
		return true;
		}
	
	// restituisce i nomi delle azioni di consegna.
	// restituisce null se il comportamento none'un processo di
	// routing di jobs
	// restituisce un array non vuoto.
	public List<String> getDeliverActionNames()
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
	
	public HashMap<String, List<RateInf>> getProbRoutingRates() 
		{
		HashMap<String, List<RateInf>> hashMap = new HashMap<String, List<RateInf>>();
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
				Action action = actionProcess.getAzione();
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// action deve essere un'azione di scelta
				ChooseAction chooseAction = new ChooseAction(action,this.tinteractions,0);
				RateInf rateInf = chooseAction.getRate();
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
				ExitAction exitAction = new ExitAction(action2,this.tinteractions,0);
				String string = exitAction.getName();
				if (hashMap.containsKey(string))
					{
					List<RateInf> rateInfs = hashMap.get(string);
					rateInfs.add(rateInf);
					hashMap.put(string, rateInfs);
					}
				else
					{
					List<RateInf> rateInfs = new ArrayList<RateInf>(1);
					rateInfs.add(rateInf);
					hashMap.put(string, rateInfs);
					}
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
			ExitAction exitAction = new ExitAction(action,this.tinteractions,0);
			String string = exitAction.getName();
			RateInf rateInf = new RateInf(new Integer(1),new Real(1.0));
			List<RateInf> rateInfs = new ArrayList<RateInf>(1);
			rateInfs.add(rateInf);
			hashMap.put(string, rateInfs);
			}
		return hashMap;
		}		
	
	public ProcessTerm getJobsRoutingBehavior()
		{
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
				// con un'azione di consegna
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				Action action2 = actionProcess2.getAzione();
				// action2 deve essere un'azione di consegna
				// riconosciamo un eventuale comportamento null
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
	
	// restituisce il comportamento di routing massimo di processTerm.
	// Restituisce null se processTerm non corrisponde a un comportamento di routing.
	public ProcessTerm getMaximalJobsRoutingBehavior()
		{
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
				// con un'azione di consegna o un BehavProcess, eventualmente preceduto da
				// un comportamento null
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
					processTerms2[i] = processTerm5;
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
	

	@Override
	public List<Expression> getProbRoutingPrios() 
		{
		List<Expression> listE = new ArrayList<Expression>();
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
				Action action = actionProcess.getAzione();
				// action deve essere un'azione di scelta
				ChooseAction chooseAction = new ChooseAction(action,this.tinteractions,0);
				RateInf rateInf = chooseAction.getRate();
				Expression expression = rateInf.getPrio();
				listE.add(expression);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			Expression expression = new Integer(1);
			listE.add(expression);
			}
		return listE;
		}
	
	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ExitBehaviorNoQNExitNorm))
			return false;
		ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = (ExitBehaviorNoQNExitNorm)obj;
		if (!this.processTerm.equals(exitBehaviorNoQNExitNorm.processTerm))
			return false;
		if (!this.tinteractions.equals(exitBehaviorNoQNExitNorm.tinteractions))
			return false;
		return true;
		}
	
	@Override
	public String toString() 
		{
		return this.processTerm.toString();
		}
	}
