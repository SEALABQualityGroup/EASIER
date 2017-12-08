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
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
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
import equivalenzaComportamentale.secondRelease.EquivalenzaJoin2;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormJP 
	extends ElemTypeNorm 
	{

	private EquivalenzaJoin2 equivalenzaJoin2;

	public ElemTypeNormJP(ElemTypeNorm elemTypeNorm,int depth)
		{
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	private ElemTypeNormJP(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public EquivalenzaJoin2 getEquivalenzaJoin2() 
		{
		return equivalenzaJoin2;
		}
	
	public void setEquivalenzaJoin2(EquivalenzaJoin2 equivalenzaJoin2) 
		{
		this.equivalenzaJoin2 = equivalenzaJoin2;
		}
	
	public HashMap<String, List<RateInf>> getProbRoutingRates() 
		{
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		// per precondizione tbehaviore'tail recursive
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations deve avere una sola equazione
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
		// riconosciamo un eventuale comportamento null
		ProcessTerm processTerm2 = actionProcess2n.getProcesso();
		NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
		ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
		// per precondizione list2 deve avere taglia 1
		ProcessTerm processTerm5n = list2.get(0);
		// processTerm5n deve essere un processo di routing oppure puo' essere una
		// chiamata di comportamento 
		HashMap<String, List<RateInf>> hashMap = new HashMap<String, List<RateInf>>();
		ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5n,tinteractions);
		ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5n,tinteractions);
		if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
			{
			HashMap<String, List<RateInf>> hashMap2 = exitBehaviorNoQNExitNorm.getProbRoutingRates();
			hashMap.putAll(hashMap2);
			}
		else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
			{
			HashMap<String, List<RateInf>> hashMap2 = exitBehaviorWithQNExitNorm.getProbRoutingRates();
			hashMap2.putAll(hashMap);
			}		
		return hashMap;
		}
	
	// le espressioni per poter essere delle probabilita' di routing vanno normalizzate
	public HashMap<String, List<Expression>> getProbRouting()
		{
		HashMap<String, List<Expression>> hashMap = null;
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		// per precondizone tbehaviore'tai recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations deve avere una sola equazione
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
		// riconosciamo un eventuale comportamento null
		ProcessTerm processTerm2 = actionProcess2n.getProcesso();
		NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
		ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
		// per precondizione list2 deve avere taglia 1
		ProcessTerm processTerm5n = list2.get(0);
		// processTerm5n deve essere un processo di routing oppure puo' essere o una
		// chiamata di comportamento
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
		else if (processTerm5n instanceof BehavProcess)
			{
			hashMap = new HashMap<String, List<Expression>>(); 
			}
		return hashMap;
		}
	
	public String getJoin() 
		{
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations deve avere una sola equazione
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
		Action action = actionProcess2n.getAzione();
		// action deve essere un'azione di join
		ActionType actionType = action.getType();
		String string = actionType.getName();
		// imposto memoria per il nome dell'azione di join
		String join = string;
		return join;
		}
	
	public String[] getDelivers() 
		{
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations deve avere una sola equazione
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
		// action deve essere un'azione di join
		// riconosciamo un eventuale comportamento null
		ProcessTerm processTerm2 = actionProcess2n.getProcesso();
		NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
		ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
		// list2 deve avere taglia 1
		ProcessTerm processTerm5n = list2.get(0);
		// processTerm5n deve essere un processo di routing oppure puo' essere o una
		// chiamata di comportamento
		String[] delivers = new String[0];
		ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5n,tinteractions);
		ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5n,tinteractions);
		if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
			{
			// imposto memoria per i nomi delle azioni di consegna
			List<String> listDelivers = exitBehaviorNoQNExitNorm.getDeliverActionNames();
			delivers = new String[listDelivers.size()];
			listDelivers.toArray(delivers);
			}
		else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
			{
			// imposto memoria per i nomi delle azioni di consegna
			List<String> listDelivers = exitBehaviorWithQNExitNorm.getDeliverActionNames();
			delivers = new String[listDelivers.size()];
			listDelivers.toArray(delivers);
			}
		return delivers;
		}
	
	// i pesi delle azioni di prosecuzione del percorso devono essere Real maggiori di zero
	public boolean restrizioneIstanze7() 
		throws RestrizioniIstanzeException
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
	
	// Le priorita' delle azioni di prosecuzione del percorso devono essere interi non minori di uno
	public boolean restrizioneIstanze17() 
		throws RestrizioniIstanzeException 
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

		// 	Le priorita' di tutti i tassi delle azioni di prosecuzione del percorso devono essere uguali tra loro.
		public boolean restrizioneIstanze18() 
			throws RestrizioniIstanzeException 
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

		public ExitBehaviorNorm getExitBehavior() 
		{
		ExitBehaviorNorm exitBehaviorNorm = null;
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		// per precondizione tbehaviore'tail recursive
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations deve avere una sola equazione
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
		// riconosciamo un eventuale comportamento null
		ProcessTerm processTerm2 = actionProcess2n.getProcesso();
		NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
		ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
		// per precondizione list2 deve avere taglia 1
		ProcessTerm processTerm5n = list2.get(0);
		// processTerm5n deve essere un processo di routing oppure puo' essere una
		// chiamata di comportamento 
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

	@Override
	public ElemTypeNormJP copy() 
		{
		ElemTypeNormJP elemTypeNormJP = new ElemTypeNormJP(this.depth);
		elemTypeNormJP.setEquivalenzaJoin2(this.equivalenzaJoin2.copy());
		elemTypeNormJP.setAEIdecl(getAEIdecl().copy());
		elemTypeNormJP.setNewElemType(getNewElemType().copy());
		elemTypeNormJP.setOldElemType(getOldElemType().copy());
		return elemTypeNormJP;
		}

	@Override
	public void print() 
		{
		System.out.println("ElemTypeNormJP object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaJoin2: ");
		this.equivalenzaJoin2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ElemTypeNormJP))
			return false;
		ElemTypeNormJP elemTypeNormJP = (ElemTypeNormJP)obj;
		if (!this.equivalenzaJoin2.equals(elemTypeNormJP.equivalenzaJoin2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormJP.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormJP.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormJP.getOldElemType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "Join Process ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
		}
	}
