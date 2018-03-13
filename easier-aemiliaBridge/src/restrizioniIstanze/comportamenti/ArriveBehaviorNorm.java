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
import specificheAEmilia.RateNoExp;
import specificheAEmilia.Rate_;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;

public class ArriveBehaviorNorm implements AEmiliaBase
	{
		
	private ProcessTerm processTerm;
	private AETinteractions tinteractions;

	public ArriveBehaviorNorm(ProcessTerm processTerm,
			AETinteractions tinteractions) 
		{
		super();
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		}
	
	public ArriveBehaviorNorm() 
		{
		super();
		}

	public List<Expression> getPrioSelezione()
		{
		// la prima azione null determina una priorita' di selezione
		List<Expression> list = new ArrayList<Expression>();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(this.processTerm, processTerm1);
		// per precondizione list ha taglia 1
		ProcessTerm processTerm21 = list1.get(0);
		// per precondizione processTerm21 deve essere uno ActionProcess o uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm : processTerms)
				{
				// per precondizione processTerme'un ActionProcess
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action action = actionProcess.getAzione();
				ActionRate actionRate = action.getRate();
				// per precondizione actionRatee'RateNoExp
				RateNoExp rateNoExp = (RateNoExp)actionRate;
				Expression expression = rateNoExp.getPrio();
				list.add(expression);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			// processTerm21e'un ActionProcess
			ActionProcess actionProcess = (ActionProcess)this.processTerm;
			Action action = actionProcess.getAzione();
			ActionRate actionRate = action.getRate();
			// per precondizione actionRate deve essere RateNoExp
			RateNoExp rateNoExp = (RateNoExp)actionRate;
			Expression expression = rateNoExp.getPrio();
			list.add(expression);
			}
		else return null;
		return list;
		}

	public List<Expression> getProbSelezione()
		{
		// la prima azione null determina una priorita' di selezione
		List<Expression> list = new ArrayList<Expression>();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior1 = new NullBehavior(this.processTerm,this.tinteractions);
		ProcessTerm processTerm1 = nullBehavior1.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(this.processTerm, processTerm1);
		// per precondizione list ha taglia 1
		ProcessTerm processTerm21 = list1.get(0);
		// processTerm21 deve essere uno ActionProcess o uno ChoiceProcess
		if (processTerm21 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm21;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm : processTerms)
				{
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action action = actionProcess.getAzione();
				ActionRate actionRate = action.getRate();
				// per precondizione actionRatee'RateNoExp
				RateNoExp rateNoExp = (RateNoExp)actionRate;
				Expression expression = rateNoExp.getWeight();
				list.add(expression);
				}
			}
		else if (processTerm21 instanceof ActionProcess)
			{
			// processTerm21e'un ActionProcess
			ActionProcess actionProcess = (ActionProcess)this.processTerm;
			Action action = actionProcess.getAzione();
			ActionRate actionRate = action.getRate();
			// per precondizione actionRate deve essere RateNoExp
			RateNoExp rateNoExp = (RateNoExp)actionRate;
			Expression expression = rateNoExp.getWeight();
			list.add(expression);
			}
		else return null;
		return list;
		}
	
	// restituisce i nomi delle azioni 
	// di arrivo. Restituisce null se processTerm none'un comportamento di arrivi.
	public List<String> getArriveNames()
		{
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
	
	// restituisce null se processTerm non rappresenta un comportamento di arrivo
	public ProcessTerm getMaximalArrivalBehavior()
		{
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
	
	@Override
	public Object copy() 
		{
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm();
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		arriveBehaviorNorm.setProcessTerm(processTermCloned);
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		arriveBehaviorNorm.setTinteractions(tinteractionsCloned);
		return arriveBehaviorNorm;
		}

	@Override
	public void print() 
		{
		System.out.println("ArriveBehaviorNorm object");
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

	// restituisce i tassi delle azioni 
	// di arrivo. Restituisce null se processTerm none'un comportamento di arrivi.
	public List<Rate_> getArrivalRates() 
		{
		// si alloca memoria per i tassi delle azioni di arrivo
		List<Rate_> list = new ArrayList<Rate_>();
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
				Rate_ rate_ = (Rate_)action.getRate();
				list.add(rate_);
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
			Rate_ rate_ = (Rate_)action.getRate();
			list.add(rate_);
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
