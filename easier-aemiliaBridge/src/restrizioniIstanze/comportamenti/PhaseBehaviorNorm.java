package restrizioniIstanze.comportamenti;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateExp;
import specificheAEmilia.RateNoExp;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ExpPhaseAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.PrePhaseAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;

public class PhaseBehaviorNorm implements AEmiliaBase
	{
	
	private ProcessTerm processTerm;
	private AETinteractions tinteractions;
	private List<PrePhaseAction> prePhaseActions;
	private List<PhaseBehaviorNorm> nextPhaseBehaviorsNorm;

	public PhaseBehaviorNorm(ProcessTerm processTerm,
			AETinteractions tinteractions) 
		{
		super();
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		this.prePhaseActions = new ArrayList<PrePhaseAction>();
		this.nextPhaseBehaviorsNorm = new ArrayList<PhaseBehaviorNorm>();
		}
	
	public PhaseBehaviorNorm() 
		{
		super();
		}

	// restituisce il comportamento di fase massimo di processTerm.
	// Restituisce null se processTerm non inizia con un comportamento di fase
	// puo' restituire un termine di processo con foglie uguali a null
	public ProcessTerm getMaximalPhaseBehavior()
		{
		ProcessTerm ris = null;
		// caso in cui la distribuzionee'hyperesponenziale
		if (this.processTerm instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)this.processTerm;
			ris = new ChoiceProcess();
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			ProcessTerm[] processTerms2 = new ProcessTerm[processTerms.length];
			((ChoiceProcess)ris).setProcesses(processTerms2);
			for (int i = 0; i < processTerms.length; i++)
				{
				ProcessTerm processTerm = processTerms[i];
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action phaseChoiceA = actionProcess.getAzione();
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// phaseChoiceAe'un'azione di scelta fase
				PrePhaseAction prePhaseAction = new PrePhaseAction(phaseChoiceA,this.tinteractions,0);
				if (!prePhaseAction.isPhaseChoice())
					return null;
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(
						processTerm2 == null ? null : processTerm2.copy(),this.tinteractions);
				ProcessTerm processTerm3 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list = MetodiVari.differenza(
						processTerm2 == null ? null : processTerm2.copy(), 
								processTerm3 == null ? null : processTerm3.copy());
				// list ha taglia uguale a 1
				if (list.size() != 1)
					return null;
				ProcessTerm processTerm4 = list.get(0);
				// processTerm4 deve essere un ActionProcess con un'azione di fase
				if (!(processTerm4 instanceof ActionProcess))
					return null;
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				Action phaseAction = actionProcess2.getAzione();
				// phaseActione'un'azione di fase
				ExpPhaseAction expPhaseAction = new ExpPhaseAction(phaseAction,this.tinteractions,0);
				if (!expPhaseAction.isPhase())
					return null;
				ProcessTerm processTerm5 = actionProcess2.getProcesso();
				// riconosciamo un eventuale comportamento null
				nullBehavior = new NullBehavior(
						processTerm5 == null ? null : processTerm5.copy(),this.tinteractions);
				ProcessTerm processTerm3b = nullBehavior.getMaximalNullBehavior();
				// si effettua la differenza tra processTerm5 e processTerm3b 
				List<ProcessTerm> list2 = MetodiVari.differenza(
						processTerm5 == null ? null : processTerm5.copy(), 
								processTerm3b == null ? null : processTerm3b.copy());
				// list2 deve avere taglia 1
				if (list2.size() != 1)
					return null;
				processTerm5 = list2.get(0);
				// sommiamo phaseChoiceA + processTerm3 + phaseAction + processTerm3b + processTerm6
				ProcessTerm processTerm6 = null;
				// si richiama ricorsivamente questo metodo wrappando processTerm5
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(
						processTerm5 == null ? null : processTerm5.copy(),this.tinteractions);
				processTerm6 = phaseBehaviorNorm.getMaximalPhaseBehavior();
				// sommiamo phaseChoiceA + processTerm3 + phaseAction + processTerm3b + processTerm6
				ProcessTerm processTerm7 = MetodiVari.somma(phaseChoiceA.copy(), 
						processTerm3 == null ? null : processTerm3.copy());
				ProcessTerm processTerm8 = MetodiVari.somma(phaseAction.copy(), 
						processTerm3b == null ? null : processTerm3b.copy());
				processTerm8 = MetodiVari.somma(
						processTerm8 == null ? null : processTerm8.copy(), 
								processTerm6 == null ? null : processTerm6.copy());
				processTerms2[i] = MetodiVari.somma(
						processTerm7 == null ? null : processTerm7.copy(), 
								processTerm8 == null ? null : processTerm8.copy());
				}
			}
		// caso in cui la distribuzionee'esponenziale
		// o hypoesponenziale
		if (this.processTerm instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)this.processTerm;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					actionProcess == null ? null : actionProcess.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list deve avere taglia uguale a 1
			if (list.size() != 1)
				return null;
			ProcessTerm processTerm2 = list.get(0);
			// possiamo avere un caso in cui c'� un comportamento null prima di uno ChoiceProcess
			// e quindi processTerm2 puo' non essere un ActionProcess
			if (processTerm2 instanceof ActionProcess)
				{
				// processTerm2 deve essere un ActionProcess
				ActionProcess actionProcess2 = (ActionProcess)processTerm2;
				Action phaseA = actionProcess2.getAzione();
				ProcessTerm processTerm3 = actionProcess2.getProcesso();
				// phaseAe'un'azione di fase
				ExpPhaseAction expPhaseAction = new ExpPhaseAction(phaseA,this.tinteractions,0);
				if (!expPhaseAction.isPhase())
					return null;
				// riconosciamo un eventuale comportamento null
				nullBehavior = new NullBehavior(
					processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
				ProcessTerm processTerm3b = nullBehavior.getMaximalNullBehavior();
				// effettuaiamo la differenza tra processTerm3 e processTerm3b
				List<ProcessTerm> list2 = MetodiVari.differenza(
					processTerm3 == null ? null : processTerm3.copy(), 
							processTerm3b == null ? null : processTerm3b.copy());
				// list2 deve avere taglia 1
				if (list2.size() != 1)
					return null;
				processTerm3 = list2.get(0);
				// si richiama ricorsivamente questa funzione su un wrapper di processTerm3
				ProcessTerm processTerm4 = null;
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(
					processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
				processTerm4 = phaseBehaviorNorm.getMaximalPhaseBehavior();
				// sommiamo processTerm + phaseA + processTerm3b + processTerm4
				ProcessTerm processTerm5 = MetodiVari.somma(processTerm3b, 
						processTerm4 == null ? null : processTerm4.copy());
				processTerm5 = MetodiVari.somma(phaseA.copy(), 
					processTerm5 == null ? null : processTerm5);
				ris = MetodiVari.somma(
					processTerm == null ? null : processTerm.copy(), 
							processTerm5 == null ? null : processTerm5.copy());
				}
			else if (processTerm2 instanceof ChoiceProcess)
				{
				// si richiama ricorsivamente questo metodo
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm2,tinteractions);
				ris = phaseBehaviorNorm.getMaximalPhaseBehavior();
				// sommiamo a ris processTerm
				ris = MetodiVari.somma(processTerm, ris);
				}
			else return null;
			}
		return ris;
		}

	// restituisce una lista di espressioni effettuando una visita in profondit� destra
	// dell'albero dei comportamenti.
	public List<Expression> getTassi() 
		{
		List<Expression> list = new ArrayList<Expression>();
		// caso in cui la distribuzionee'hyperesponenziale
		if (this.processTerm instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)this.processTerm;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm : processTerms)
				{
				ActionProcess actionProcess = (ActionProcess)processTerm;
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(processTerm2,this.tinteractions);
				ProcessTerm processTerm3 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm3);
				// list2 deve avere taglia uguale a 1
				ProcessTerm processTerm4 = list2.get(0);
				// processTerm4 deve essere un ActionProcess con un'azione di fase
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				Action phaseAction = actionProcess2.getAzione();
				ActionRate actionRate = phaseAction.getRate();
				// per precondizione actionRate deve essere un RateExp
				RateExp rateExp = (RateExp)actionRate;
				Expression expression = rateExp.getExpr();
				list.add(expression);
				ProcessTerm processTerm5 = actionProcess2.getProcesso();
				// si riconosce un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(processTerm5,this.tinteractions);
				ProcessTerm processTerm61 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list31 = MetodiVari.differenza(processTerm5, processTerm61);
				// list3 ha taglia 1
				ProcessTerm processTerm62 = list31.get(0);
				// si richiama ricorsivamente questo metodo wrappando processTerm5
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm62,this.tinteractions);
				List<Expression> list3 = phaseBehaviorNorm.getTassi();
				if (list3 != null)
					list.addAll(list3);
				}
			}
		// caso in cui la distribuzionee'esponenziale
		// o hypoesponenziale
		if (this.processTerm instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)this.processTerm;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(actionProcess,this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(actionProcess, processTerm);
			// list deve avere taglia uguale a 1
			ProcessTerm processTerm2 = list2.get(0);
			// possiamo avere un caso in cui c'� un comportamento null prima di uno ChoiceProcess
			// e quindi processTerm2 puo' non essere un ActionProcess
			if (processTerm2 instanceof ActionProcess)
				{
				ActionProcess actionProcess2 = (ActionProcess)processTerm2;
				Action phaseA = actionProcess2.getAzione();
				ActionRate actionRate = phaseA.getRate();
				// per precondizione actionRatee'RateExp
				RateExp rateExp = (RateExp)actionRate;
				Expression expression = rateExp.getExpr();
				list.add(expression);
				ProcessTerm processTerm3 = actionProcess2.getProcesso();
				// si riconosce un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(processTerm3,this.tinteractions);
				ProcessTerm processTerm41 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list31 = MetodiVari.differenza(processTerm3, processTerm41);
				// list3 deve avere taglia 1
				ProcessTerm processTerm5 = list31.get(0);
				// si richiama ricorsivamente questa funzione su un wrapper di processTerm5
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm5,this.tinteractions);
				ProcessTerm processTerm4 = phaseBehaviorNorm.getMaximalPhaseBehavior();
				phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm4,this.tinteractions);
				List<Expression> list3 = phaseBehaviorNorm.getTassi();
				if (list3 != null)
					list.addAll(list3);
				}
			else if (processTerm2 instanceof ChoiceProcess)
				{
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm2,tinteractions);
				return phaseBehaviorNorm.getTassi();
				}
			else return null;
			}
		return list;
		}
	
	// restituisce una lista di espressioni effettuando una visita in profondit� destra
	// dell'albero dei comportamenti.
	// l'array restituito puo' essere vuoto.
	public List<Expression> getPesiScelta() 
		{
		List<Expression> list = new ArrayList<Expression>();
		// caso in cui la distribuzionee'hyperesponenziale
		if (this.processTerm instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)this.processTerm;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm : processTerms)
				{
				ActionProcess actionProcess = (ActionProcess)processTerm;
				// actionProcess ha un'azione di scelta di fase
				Action action = actionProcess.getAzione();
				// per precondizione actione'un'azione di scelta di fase
				ActionRate actionRate1 = action.getRate();
				// per precondizione actionRatee'un RateNoExp
				RateNoExp rateNoExp = (RateNoExp)actionRate1;
				Expression espressione1 = rateNoExp.getWeight();
				list.add(espressione1);
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(processTerm2,this.tinteractions);
				ProcessTerm processTerm3 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm3);
				// list2 deve avere taglia uguale a 1
				ProcessTerm processTerm4 = list2.get(0);
				// processTerm4 deve essere un ActionProcess con un'azione di fase
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				ProcessTerm processTerm5 = actionProcess2.getProcesso();
				// si riconosce un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(processTerm5,this.tinteractions);
				ProcessTerm processTerm61 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list31 = MetodiVari.differenza(processTerm5, processTerm61);
				// list3 ha taglia 1
				ProcessTerm processTerm62 = list31.get(0);
				// si richiama ricorsivamente questo metodo wrappando processTerm5
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm62,this.tinteractions);
				List<Expression> list3 = phaseBehaviorNorm.getPesiScelta();
				if (list3 != null)
					list.addAll(list3);
				}
			}
		// caso in cui la distribuzionee'esponenziale
		// o hypoesponenziale
		if (this.processTerm instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)this.processTerm;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(actionProcess,this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(actionProcess, processTerm);
			// list deve avere taglia uguale a 1
			ProcessTerm processTerm2 = list2.get(0);
			// possiamo avere un caso in cui c'� un comportamento null prima di uno ChoiceProcess
			// e quindi processTerm2 puo' non essere un ActionProcess
			if (processTerm2 instanceof ActionProcess)
				{
				ActionProcess actionProcess2 = (ActionProcess)processTerm2;
				ProcessTerm processTerm3 = actionProcess2.getProcesso();
				// si riconosce un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(processTerm3,this.tinteractions);
				ProcessTerm processTerm41 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list31 = MetodiVari.differenza(processTerm3, processTerm41);
				// list3 deve avere taglia 1
				ProcessTerm processTerm5 = list31.get(0);
				// si richiama ricorsivamente questa funzione su un wrapper di processTerm5
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm5,this.tinteractions);
				ProcessTerm processTerm4 = phaseBehaviorNorm.getMaximalPhaseBehavior();
				phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm4,this.tinteractions);
				List<Expression> list3 = phaseBehaviorNorm.getPesiScelta();
				if (list3 != null)
					list.addAll(list3);
				}
			else if (processTerm2 instanceof ChoiceProcess)
				{
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm2,tinteractions);
				return phaseBehaviorNorm.getPesiScelta();
				}
			else return null;
			}
		return list;
		}

	// restituisce il comportamento di fase di processTerm, senza considerare i comportamenti null.
	// Restituisce null se processTerm non inizia con un comportamento di fase
	// puo' restituire un termine di processo con foglie uguali a null
	public ProcessTerm getPhaseBehavior()
		{
		ProcessTerm ris = null;
		// caso in cui la distribuzionee'hyperesponenziale
		if (this.processTerm instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)this.processTerm;
			ris = new ChoiceProcess();
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			ProcessTerm[] processTerms2 = new ProcessTerm[processTerms.length];
			((ChoiceProcess)ris).setProcesses(processTerms2);
			for (int i = 0; i < processTerms.length; i++)
				{
				ProcessTerm processTerm = processTerms[i];
				ActionProcess actionProcess = (ActionProcess)processTerm;
				Action phaseChoiceA = actionProcess.getAzione();
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// phaseChoiceAe'un'azione di scelta fase
				PrePhaseAction prePhaseAction = new PrePhaseAction(phaseChoiceA,this.tinteractions,0);
				if (!prePhaseAction.isPhaseChoice())
					return null;
				else
					this.prePhaseActions.add(prePhaseAction);				
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(
						processTerm2 == null ? null : processTerm2.copy(),this.tinteractions);
				ProcessTerm processTerm3 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list = MetodiVari.differenza(
						processTerm2 == null ? null : processTerm2.copy(), 
								processTerm3 == null ? null : processTerm3.copy());
				// list ha taglia uguale a 1
				if (list.size() != 1)
					return null;
				ProcessTerm processTerm4 = list.get(0);
				// processTerm4 deve essere un ActionProcess con un'azione di fase
				if (!(processTerm4 instanceof ActionProcess))
					return null;
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				Action phaseAction = actionProcess2.getAzione();
				// phaseActione'un'azione di fase
				ExpPhaseAction expPhaseAction = new ExpPhaseAction(phaseAction,this.tinteractions,0);
				if (!expPhaseAction.isPhase())
					return null;
				ProcessTerm processTerm5 = actionProcess2.getProcesso();
				// bisogna riconoscere un eventuale comportamento null ed effettuare la differenza
				NullBehavior nullBehavior2 = new NullBehavior(
						processTerm5 == null ? null : processTerm5.copy(),this.tinteractions);
				ProcessTerm processTerm61 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(
						processTerm5 == null ? null : processTerm5.copy(),
								processTerm61 == null ? null : processTerm61.copy());
				// list2 ha taglia uno
				if (list2.size() != 1)
					return null;
				ProcessTerm processTerm62 = list2.get(0);
				// si richiama ricorsivamente questo metodo wrappando processTerm62
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(
						processTerm62 == null ? null : processTerm62.copy(),this.tinteractions);
				ProcessTerm processTerm6 = phaseBehaviorNorm.getPhaseBehavior();
				if (processTerm6 != null)
					{
					this.nextPhaseBehaviorsNorm.add(phaseBehaviorNorm);
					}
				// sommiamo phaseChoiceA + phaseAction + processTerm6
				ProcessTerm processTerm7 = MetodiVari.somma(phaseAction.copy(), 
						processTerm6 == null ? null : processTerm6.copy());
				processTerms2[i] = MetodiVari.somma(phaseChoiceA.copy(), 
						processTerm7 == null ? null : processTerm7.copy());
				}
			}
		// caso in cui la distribuzionee'esponenziale
		// o hypoesponenziale
		if (this.processTerm instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)this.processTerm;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(
					actionProcess == null ? null : actionProcess.copy(),this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list = MetodiVari.differenza(
					actionProcess == null ? null : actionProcess.copy(), 
					processTerm == null ? null : processTerm.copy());
			// list deve avere taglia uguale a 1
			if (list.size() != 1)
				return null;
			ProcessTerm processTerm2 = list.get(0);
			// possiamo avere un caso in cui c'� un comportamento null prima di uno ChoiceProcess
			// e quindi processTerm2 puo' non essere un ActionProcess
			if (processTerm2 instanceof ActionProcess)
				{
				ActionProcess actionProcess2 = (ActionProcess)processTerm2;
				Action phaseA = actionProcess2.getAzione();
				ProcessTerm processTerm3 = actionProcess2.getProcesso();
				// phaseAe'un'azione di fase
				ExpPhaseAction expPhaseAction = new ExpPhaseAction(phaseA,this.tinteractions,0);
				if (!expPhaseAction.isPhase())
					return null;
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(processTerm3,this.tinteractions);
				ProcessTerm processTerm41 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(
						processTerm3 == null ? null : processTerm3.copy(), 
							processTerm41 == null ? null : processTerm41.copy());
				// list2 deve avere taglia 1
				if (list2.size() != 1)
					return null;
				ProcessTerm processTerm42 = list2.get(0);
				// si richiama ricorsivamente questa funzione su un wrapper di processTerm3
				ProcessTerm processTerm4 = null;
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(
						processTerm42,this.tinteractions);
				processTerm4 = phaseBehaviorNorm.getPhaseBehavior();
				if (processTerm4 != null)
					this.nextPhaseBehaviorsNorm.add(phaseBehaviorNorm);
				// sommiamo phaseA + processTerm4
				ProcessTerm processTerm5 = MetodiVari.somma(phaseA.copy(), 
					processTerm4 == null ? null : processTerm4.copy());
				ris = processTerm5;
				}
			else if (processTerm2 instanceof ChoiceProcess)
				{
				PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm2,tinteractions);
				ProcessTerm processTerm3 = phaseBehaviorNorm.getPhaseBehavior();
				if (processTerm3 != null)
					this.nextPhaseBehaviorsNorm.add(phaseBehaviorNorm);
				return processTerm3; 
				}
			else return null;
			}
		return ris;
		}
		
	public List<PrePhaseAction> getPrePhaseActions() 
		{
		return prePhaseActions;
		}

	public List<PhaseBehaviorNorm> getNextPhaseBehaviorsNorm() 
		{
		return nextPhaseBehaviorsNorm;
		}
	
	// restituisce la lista di tassi esponenziali effettuando una visita in profondit� destra
	// dell'albero dei comportamenti.
	public List<RateExp> getExpRates() 
		{
		List<RateExp> list = new ArrayList<RateExp>();
		// caso in cui la distribuzionee'hyperesponenziale
		if (this.processTerm instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)this.processTerm;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm : processTerms)
				{
				ActionProcess actionProcess = (ActionProcess)processTerm;
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				// riconosciamo un eventuale comportamento null
				NullBehavior nullBehavior = new NullBehavior(processTerm2,this.tinteractions);
				ProcessTerm processTerm3 = nullBehavior.getMaximalNullBehavior();
				List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm3);
				// list2 deve avere taglia uguale a 1
				ProcessTerm processTerm4 = list2.get(0);
				// processTerm4 deve essere un ActionProcess con un'azione di fase
				ActionProcess actionProcess2 = (ActionProcess)processTerm4;
				Action phaseAction = actionProcess2.getAzione();
				ActionRate actionRate = phaseAction.getRate();
				// per precondizione actionRate deve essere un RateExp
				RateExp rateExp = (RateExp)actionRate;
				list.add(rateExp);
				ProcessTerm processTerm5 = actionProcess2.getProcesso();
				// si riconosce un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(processTerm5,this.tinteractions);
				ProcessTerm processTerm61 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list31 = MetodiVari.differenza(processTerm5, processTerm61);
				// list3 ha taglia 1
				ProcessTerm processTerm62 = list31.get(0);
				// si richiama ricorsivamente questo metodo wrappando processTerm5
				PhaseBehaviorNorm phaseBehavior = new PhaseBehaviorNorm(processTerm62,this.tinteractions);
				List<RateExp> list3 = phaseBehavior.getExpRates();
				if (list3 != null)
					list.addAll(list3);
				}
			}
		// caso in cui la distribuzionee'esponenziale
		// o hypoesponenziale
		if (this.processTerm instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)this.processTerm;
			// riconosciamo un eventuale comportamento null
			NullBehavior nullBehavior = new NullBehavior(actionProcess,this.tinteractions);
			ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(actionProcess, processTerm);
			// list deve avere taglia uguale a 1
			ProcessTerm processTerm2 = list2.get(0);
			// possiamo avere un caso in cui c'� un comportamento null prima di uno ChoiceProcess
			// e quindi processTerm2 puo' non essere un ActionProcess
			if (processTerm2 instanceof ActionProcess)
				{
				ActionProcess actionProcess2 = (ActionProcess)processTerm2;
				Action phaseA = actionProcess2.getAzione();
				ActionRate actionRate = phaseA.getRate();
				// per precondizione actionRatee'RateExp
				RateExp rateExp = (RateExp)actionRate;
				list.add(rateExp);
				ProcessTerm processTerm3 = actionProcess2.getProcesso();
				// si riconosce un eventuale comportamento null
				NullBehavior nullBehavior2 = new NullBehavior(processTerm3,this.tinteractions);
				ProcessTerm processTerm41 = nullBehavior2.getMaximalNullBehavior();
				List<ProcessTerm> list31 = MetodiVari.differenza(processTerm3, processTerm41);
				// list3 deve avere taglia 1
				ProcessTerm processTerm5 = list31.get(0);
				// si richiama ricorsivamente questa funzione su un wrapper di processTerm5
				PhaseBehaviorNorm phaseBehavior = new PhaseBehaviorNorm(processTerm5,this.tinteractions);
				ProcessTerm processTerm4 = phaseBehavior.getMaximalPhaseBehavior();
				phaseBehavior = new PhaseBehaviorNorm(processTerm4,this.tinteractions);
				List<RateExp> list3 = phaseBehavior.getExpRates();
				if (list3 != null)
					list.addAll(list3);
				}
			else if (processTerm2 instanceof ChoiceProcess)
				{
				PhaseBehaviorNorm phaseBehavior = new PhaseBehaviorNorm(processTerm2,tinteractions);
				return phaseBehavior.getExpRates();
				}
			else return null;
			}
		return list;
		}
	
	@Override
	public Object copy() 
		{
		PhaseBehaviorNorm phaseBehavior = new PhaseBehaviorNorm();
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		phaseBehavior.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		phaseBehavior.tinteractions = tinteractionsCloned;
		return phaseBehavior;
		}

	@Override
	public void print() 
		{
		System.out.println("PhaseBehaviorNorm object");
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}

	@Override
	public String toString() 
		{
		return this.processTerm.toString();
		}
	}
