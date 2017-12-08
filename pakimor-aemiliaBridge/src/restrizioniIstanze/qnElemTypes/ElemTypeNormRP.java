/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.comportamenti.ExitBehaviorNoQNExitNorm;
import restrizioniIstanze.comportamenti.ExitBehaviorNorm;
import restrizioniIstanze.comportamenti.ExitBehaviorWithQNExitNorm;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateInf;
import utilities.ErrorMessage;
import valutazione.typeChecking.TypeCheckingException;
import valutazione.typeChecking.rateInfChecking.CheckPrio;
import valutazione.typeChecking.rateInfChecking.CheckWeight;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public abstract class ElemTypeNormRP 
	extends ElemTypeNorm 
	{

	public HashMap<String, List<Expression>> getProbRouting() {
	HashMap<String, List<Expression>> hashMap = null;
	AETbehavior tbehavior = this.getNewElemType().getBehavior();
	AETinteractions tinteractions = this.getNewElemType().getInteractions();
	TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
	// per precondizione tbehaviore'tail recursive
	AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
	BehavEquation[] behavEquations = tbehavior2.getBehaviors();
	// per precondizione behavEquations ha una solo equazione
	BehavEquation behavEquation = behavEquations[0];
	ProcessTerm processTerm = behavEquation.getTermineProcesso();
	// per precondizione processTerm deve essere un ActionProcess
	ActionProcess actionProcess = (ActionProcess)processTerm;
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior = new NullBehavior(actionProcess,tinteractions);
	ProcessTerm processTerm2n = nullBehavior.getMaximalNullBehavior();
	List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTerm2n);
	// per precondizione list deve avere taglia 1
	ProcessTerm processTerm3n = list.get(0);
	// per precondizione processTerm3n deve essere un ActionProcess
	ActionProcess actionProcess2n = (ActionProcess)processTerm3n;
	// per precondizione action deve essere un'azione di arrivo
	ProcessTerm processTerm2 = actionProcess2n.getProcesso();
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
	ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
	List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
	// per precondizione list2 deve avere taglia 1
	ProcessTerm processTerm5n = list2.get(0);
	// processTerm5n deve essere un comportamento di routing o un BehavProcess
	ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5n,tinteractions);
	ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5n,tinteractions);
	if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
		{
		hashMap = exitBehaviorNoQNExitNorm.getProbsRouting();
		}
	else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
		{
		hashMap = exitBehaviorWithQNExitNorm.getProbsRouting();
		}
	else if (processTerm2 instanceof BehavProcess)
		{
		hashMap = exitBehaviorNoQNExitNorm.getProbsRouting();
		}
	return hashMap;
	}

	public HashMap<String, List<RateInf>> getProbRoutingRates() {
	HashMap<String, List<RateInf>> hashMap = null;
	AETbehavior tbehavior = this.getNewElemType().getBehavior();
	AETinteractions tinteractions = this.getNewElemType().getInteractions();
	TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
	// per precondizione tbehaviore'tail recursive
	AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
	BehavEquation[] behavEquations = tbehavior2.getBehaviors();
	// per precondizione behavEquations ha una solo equazione
	BehavEquation behavEquation = behavEquations[0];
	ProcessTerm processTerm = behavEquation.getTermineProcesso();
	// per precondizione processTerm deve essere un ActionProcess
	ActionProcess actionProcess = (ActionProcess)processTerm;
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior = new NullBehavior(actionProcess,tinteractions);
	ProcessTerm processTerm2n = nullBehavior.getMaximalNullBehavior();
	List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTerm2n);
	// per precondizione list deve avere taglia 1
	ProcessTerm processTerm3n = list.get(0);
	// per precondizione processTerm3n deve essere un ActionProcess
	ActionProcess actionProcess2n = (ActionProcess)processTerm3n;
	// per precondizione action deve essere un'azione di arrivo
	ProcessTerm processTerm2 = actionProcess2n.getProcesso();
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
	ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
	List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
	// per precondizione list2 deve avere taglia 1
	ProcessTerm processTerm5n = list2.get(0);
	// processTerm5n deve essere un comportamento di routing o un BehavProcess
	ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5n,tinteractions);
	ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5n,tinteractions);
	if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
		{
		hashMap = exitBehaviorNoQNExitNorm.getProbRoutingRates();
		}
	else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
		{
		hashMap = exitBehaviorWithQNExitNorm.getProbRoutingRates();
		}
	else if (processTerm2 instanceof BehavProcess)
		{
		hashMap = new HashMap<String, List<RateInf>>();
		}
	return hashMap;
	}

	public String[] getDelivers() {
	AETbehavior tbehavior = this.getNewElemType().getBehavior();
	AETinteractions tinteractions = this.getNewElemType().getInteractions();
	TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
	AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
	BehavEquation[] behavEquations = tbehavior2.getBehaviors();
	// per precondizione behavEquations ha una solo equazione
	BehavEquation behavEquation = behavEquations[0];
	ProcessTerm processTerm = behavEquation.getTermineProcesso();
	// processTerm deve essere un ActionProcess
	ActionProcess actionProcess = (ActionProcess)processTerm;
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior = new NullBehavior(actionProcess,tinteractions);
	ProcessTerm processTerm2n = nullBehavior.getMaximalNullBehavior();
	List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTerm2n);
	// list deve avere taglia 1
	ProcessTerm processTerm3n = list.get(0);
	// processTerm3n deve essere un ActionProcess
	ActionProcess actionProcess2n = (ActionProcess)processTerm3n;
	// action deve essere un'azione di arrivo
	ProcessTerm processTerm2 = actionProcess2n.getProcesso();
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
	ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
	List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
	// list2 deve avere taglia 1
	ProcessTerm processTerm5n = list2.get(0);
	// processTerm5n deve essere un comportamento di routing o un BehavProcess
	String[] delivers = new String[0];
	ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5n,tinteractions);
	ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5n,tinteractions);
	if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
		{
		List<String> listDelivNames = exitBehaviorNoQNExitNorm.getDeliverActionNames();
		delivers = new String[listDelivNames.size()];
		listDelivNames.toArray(delivers);
		}
	else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
		{
		List<String> listDelivNames = exitBehaviorWithQNExitNorm.getDeliverActionNames();
		delivers = new String[listDelivNames.size()];
		listDelivNames.toArray(delivers);
		}
	return delivers;
	}

	public ExitBehaviorNorm getExitBehavior() {
	ExitBehaviorNorm exitBehaviorNorm = null;
	AETbehavior tbehavior = this.getNewElemType().getBehavior();
	AETinteractions tinteractions = this.getNewElemType().getInteractions();
	TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
	// per precondizione tbehaviore'tail recursive
	AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
	BehavEquation[] behavEquations = tbehavior2.getBehaviors();
	// per precondizione behavEquations ha una solo equazione
	BehavEquation behavEquation = behavEquations[0];
	ProcessTerm processTerm = behavEquation.getTermineProcesso();
	// per precondizione processTerm deve essere un ActionProcess
	ActionProcess actionProcess = (ActionProcess)processTerm;
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior = new NullBehavior(actionProcess,tinteractions);
	ProcessTerm processTerm2n = nullBehavior.getMaximalNullBehavior();
	List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTerm2n);
	// per precondizione list deve avere taglia 1
	ProcessTerm processTerm3n = list.get(0);
	// per precondizione processTerm3n deve essere un ActionProcess
	ActionProcess actionProcess2n = (ActionProcess)processTerm3n;
	// per precondizione action deve essere un'azione di arrivo
	ProcessTerm processTerm2 = actionProcess2n.getProcesso();
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
	ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
	List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
	// per precondizione list2 deve avere taglia 1
	ProcessTerm processTerm5n = list2.get(0);
	// processTerm5n deve essere un comportamento di routing o un BehavProcess
	ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5n,tinteractions);
	ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5n,tinteractions);
	if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
		{
		exitBehaviorNorm = exitBehaviorNoQNExitNorm;
		}
	else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
		{
		exitBehaviorNorm = exitBehaviorWithQNExitNorm;
		}
	return exitBehaviorNorm;
	}

	@Override
	public boolean isCompliantInstanceRules()
			throws RestrizioniIstanzeException 
		{
		if (!restrizioneIstanze16()) return false;
		if (!restrizioneIstanze17()) return false;
		if (!restrizioneIstanze18()) return false;
		if (!restrizioneIstanze7()) return false;
		return true;
		}

	/*
	 * 3) i pesi delle azioni di prosecuzione del percorso devono essere Real maggiori di zero
	 */
	public boolean restrizioneIstanze7() throws RestrizioniIstanzeException 
		{
		HashMap<String, List<RateInf>> hashMap2 = getProbRoutingRates();
		// applico le regole di type checking per i tassi
		Set<Entry<String,List<RateInf>>> set = hashMap2.entrySet();
		for (Entry<String,List<RateInf>> entry : set)
			{
			List<RateInf> list = entry.getValue();
			for (RateInf rateInf : list)
				{
				CheckWeight checkWeight = new CheckWeight(rateInf,this.depth + 1);
				try {
					if (!checkWeight.checkWeight())
						{
						String string3 = "Instances restrictions error for " + this.idecl.toString();
						this.errorMessage.setErrorMessage(string3);
						List<ErrorMessage> list2 = this.errorMessage.getCauses();
						ErrorMessage errorMessage = checkWeight.getErrorMessage();
						list2.add(errorMessage);
						return false;
						}
					} 
				catch (TypeCheckingException e) 
					{
					throw new RestrizioniIstanzeException(e);
					}
				}
			}
		return true;
		}

	// le priorita' dei tassi delle azioni di prosecuzione del percorso devono essere interi non minori di uno
	public boolean restrizioneIstanze17() throws RestrizioniIstanzeException 
		{
		HashMap<String, List<RateInf>> hashMap2 = getProbRoutingRates();
		// applico le regole di type checking per i tassi
		Set<Entry<String,List<RateInf>>> set = hashMap2.entrySet();
		for (Entry<String,List<RateInf>> entry : set)
			{
			List<RateInf> list = entry.getValue();
			for (RateInf rateInf : list)
				{
				CheckPrio checkPrio = new CheckPrio(rateInf,this.depth + 1);
				try {
					if (!checkPrio.checkPrio())
						{
						String string3 = "Instances restrictions error for " + this.idecl.toString();
						this.errorMessage.setErrorMessage(string3);
						List<ErrorMessage> list2 = this.errorMessage.getCauses();
						ErrorMessage errorMessage = checkPrio.getErrorMessage();
						list2.add(errorMessage);
						return false;
						}
					} 
				catch (TypeCheckingException e) 
					{
					throw new RestrizioniIstanzeException(e);
					}
				}
			}
		return true;
		}

	// le priorita' di tutti i tassi delle azioni di prosecuzione del percorso devono essere uguali tra loro
	public boolean restrizioneIstanze18() throws RestrizioniIstanzeException 
		{
		ExitBehaviorNorm exitBehaviorNorm = getExitBehavior();
		if (exitBehaviorNorm != null)
			{
			List<Expression> list2 = exitBehaviorNorm.getProbRoutingPrios();
			if (list2.size() > 1)
				{
				Expression expression = list2.get(0);
				for (int i = 1; i < list2.size(); i++)
					{
					Expression expression2 = list2.get(i);
					if (!expression.equals(expression2))
						{
						String string3 = "Instances restrictions error for " + this.idecl.toString();
						this.errorMessage.setErrorMessage(string3);
						List<ErrorMessage> list3 = this.errorMessage.getCauses();
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						String string = "piorities " + expression.toString() + " and " + expression2.toString() + " are not equal";
						errorMessage.setErrorMessage(string);
						list3.add(errorMessage);
						return false;
						}
					}
				}
			}
		return true;
		}
	}
