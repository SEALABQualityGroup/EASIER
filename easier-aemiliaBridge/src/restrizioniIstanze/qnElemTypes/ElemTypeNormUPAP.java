/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.comportamenti.ExitBehaviorNoQNExitNorm;
import restrizioniIstanze.comportamenti.ExitBehaviorNorm;
import restrizioniIstanze.comportamenti.ExitBehaviorWithQNExitNorm;
import restrizioniIstanze.comportamenti.PhaseBehaviorNorm;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ElemType;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateInf;
import utilities.ErrorMessage;
import valutazione.typeChecking.TypeCheckingException;
import valutazione.typeChecking.rateInfChecking.CheckPrio;
import valutazione.typeChecking.rateInfChecking.CheckWeight;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.EquivalenzaArriviInfiniti2;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormUPAP 
	extends ElemTypeNormAP 
	{

	private EquivalenzaArriviInfiniti2 equivalenzaArriviInfiniti2;
	
	public ElemTypeNormUPAP(ElemTypeNorm elemTypeNorm,int depth)
		{
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	private ElemTypeNormUPAP(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public EquivalenzaArriviInfiniti2 getEquivalenzaArriviInfiniti2() 
		{
		return equivalenzaArriviInfiniti2;
		}
	
	public void setEquivalenzaArriviInfiniti2(
			EquivalenzaArriviInfiniti2 equivalenzaArriviInfiniti2) 
		{
		this.equivalenzaArriviInfiniti2 = equivalenzaArriviInfiniti2;
		}
	
	public HashMap<String, List<RateInf>> getProbRoutingRates() 
		{
		HashMap<String, List<RateInf>> hashMap = new HashMap<String, List<RateInf>>();
		ElemType elemType2 = this.getNewElemType();
		AETbehavior tbehavior = elemType2.getBehavior();
		AETinteractions tinteractions = elemType2.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		// per precondizione tbehaviore'diverso da null
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm,tinteractions);
		// preleviamo la differenza tra processTerm e processTerm2
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, phaseBehaviorNorm.getMaximalPhaseBehavior());
		for (ProcessTerm processTerm2 : list)
			{
			ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm2,tinteractions);
			ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm2,tinteractions);
			ProcessTerm processTerm4 = null;
			if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
				processTerm4 = exitBehaviorNoQNExitNorm.getMaximalJobsRoutingBehavior();
			else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
				processTerm4 = exitBehaviorWithQNExitNorm.getMaximalJobsRoutingBehavior();
			// per precondizione processTerm4e'diverso da null 
			// memorizziamo in listDel i nomi delle azioni di consegna
			ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm2 = new ExitBehaviorNoQNExitNorm(processTerm4,tinteractions);
			ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm2 = new ExitBehaviorWithQNExitNorm(processTerm4,tinteractions);
			if (exitBehaviorNoQNExitNorm2.isJobsRoutingBehavior())
				{
				HashMap<String, List<RateInf>> hashMap2 = exitBehaviorNoQNExitNorm2.getProbRoutingRates();
				Set<Entry<String, List<RateInf>>> set = hashMap2.entrySet();
				for (Entry<String, List<RateInf>> entry : set)
					{
					String string = entry.getKey();
					List<RateInf> list2 = entry.getValue();
					if (hashMap.containsKey(string))
						{
						List<RateInf> list3 = hashMap.get(string);
						list3.addAll(list2);
						}
					else
						{
						hashMap.put(string, list2);
						}
					}
				}
			else if (exitBehaviorWithQNExitNorm2.isJobsRoutingBehavior())
				{
				HashMap<String, List<RateInf>> hashMap2 = exitBehaviorWithQNExitNorm2.getProbRoutingRates();
				Set<Entry<String, List<RateInf>>> set = hashMap2.entrySet();
				for (Entry<String, List<RateInf>> entry : set)
					{
					String string = entry.getKey();
					List<RateInf> list2 = entry.getValue();
					if (hashMap.containsKey(string))
						{
						List<RateInf> list3 = hashMap.get(string);
						list3.addAll(list2);
						}
					else
						{
						hashMap.put(string, list2);
						}
					}
				}			
			}
		return hashMap;
		}
	
	public String[] getDelivers() 
		{
		ElemType elemType = this.getNewElemType();
		// trasformiamo il comportamento
		// elemType in uno tail recursion
		AETbehavior tbehavior = elemType.getBehavior();
		AETinteractions tinteractions = elemType.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm,tinteractions);
		// preleviamo la differenza tra processTerm e processTerm2
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, phaseBehaviorNorm.getMaximalPhaseBehavior());
		// allochiamo memoria per i nomi delle azioni di consegna
		List<String> listDel = new ArrayList<String>();
		
		// per il primo elemento di list dobbiamo cercare un comportamento di routing di jobs
		ProcessTerm processTerm3 = list.get(0);

		ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm3,tinteractions);
		ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm3,tinteractions);
		ProcessTerm processTerm4 = null;
		if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
			processTerm4 = exitBehaviorNoQNExitNorm.getMaximalJobsRoutingBehavior();
		else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
			processTerm4 = exitBehaviorWithQNExitNorm.getMaximalJobsRoutingBehavior();
		// memorizziamo in listDel i nomi delle azioni di consegna
		ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm2 = new ExitBehaviorNoQNExitNorm(processTerm4,tinteractions);
		ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm2 = new ExitBehaviorWithQNExitNorm(processTerm4,tinteractions);
		if (exitBehaviorNoQNExitNorm2.isJobsRoutingBehavior())
			{
			List<String> list2 = exitBehaviorNoQNExitNorm2.getDeliverActionNames();
			// list2 puo' contenere elementi gia' presenti in listDel
			for (String string : list2)
				{
				if (!listDel.contains(string))
					listDel.add(string);
				}
			}
		else if (exitBehaviorWithQNExitNorm2.isJobsRoutingBehavior())
			{
			List<String> list2 = exitBehaviorWithQNExitNorm2.getDeliverActionNames();
			// list2 puo' contenere elementi gia' presenti in listDel
			for (String string : list2)
				{
				if (!listDel.contains(string))
					listDel.add(string);
				}
			}
		// impostiamo i nomi delle azioni di consegna
		String[] delivers = new String[listDel.size()];
		listDel.toArray(delivers);
		return delivers;
		}
	
	public List<ExitBehaviorNorm> getExitBehaviors() 
		{
		ElemType elemType = this.getNewElemType();
		// trasformiamo il comportamento
		// elemType in uno tail recursion
		AETbehavior tbehavior = elemType.getBehavior();
		AETinteractions tinteractions = elemType.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm,tinteractions);
		// preleviamo la differenza tra processTerm e processTerm2
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, phaseBehaviorNorm.getMaximalPhaseBehavior());
		List<ExitBehaviorNorm> list2 = new ArrayList<ExitBehaviorNorm>();
		for (ProcessTerm processTerm2 : list)
			{
			ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm2,tinteractions);
			ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm2,tinteractions);
			if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
				list2.add(exitBehaviorNoQNExitNorm);
			else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
				list2.add(exitBehaviorWithQNExitNorm);
			}
		return list2;
		}
	
	@Override
	public boolean restrizioneIstanze7() throws RestrizioniIstanzeException 
		{
		HashMap<String, List<RateInf>> hashMap2 = getProbRoutingRates();
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
	
	@Override
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

	@Override
	public boolean restrizioneIstanze18() 
		throws RestrizioniIstanzeException 
		{
		List<ExitBehaviorNorm> list = getExitBehaviors();
		for (ExitBehaviorNorm exitBehaviorNorm : list)
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
						String string = "priorities " + expression.toString() + " and " + expression2.toString() + " are not equal";
						errorMessage.setErrorMessage(string);
						list3.add(errorMessage);
						return false;
						}
					}
				}
			}
		return true;
		}

	@Override
	public ElemTypeNormUPAP copy() 
		{
		ElemTypeNormUPAP elemTypeNormUPAP = new ElemTypeNormUPAP(this.depth);
		elemTypeNormUPAP.setEquivalenzaArriviInfiniti2(this.equivalenzaArriviInfiniti2.copy());
		elemTypeNormUPAP.setAEIdecl(getAEIdecl().copy());
		elemTypeNormUPAP.setNewElemType(getNewElemType().copy());
		elemTypeNormUPAP.setOldElemType(getOldElemType().copy());
		return elemTypeNormUPAP;
		}

	@Override
	public void print() 
		{
		System.out.println("ElemTypeNormUPAP object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaArriviInfiniti2: ");
		this.equivalenzaArriviInfiniti2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ElemTypeNormUPAP))
			return false;
		ElemTypeNormUPAP elemTypeNormUPAP = (ElemTypeNormUPAP)obj;
		if (!this.equivalenzaArriviInfiniti2.equals(elemTypeNormUPAP.equivalenzaArriviInfiniti2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormUPAP.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormUPAP.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormUPAP.getOldElemType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "Unlimited Population Arrival Process ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
		}	
	}
