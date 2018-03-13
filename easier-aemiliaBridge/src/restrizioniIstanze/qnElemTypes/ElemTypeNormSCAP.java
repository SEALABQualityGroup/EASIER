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
import restrizioniIstanze.comportamenti.BeginningExitBehaviorNoQNExitNorm;
import restrizioniIstanze.comportamenti.ExitBehaviorNorm;
import restrizioniIstanze.comportamenti.PhaseBehaviorNorm;
import restrizioniIstanze.comportamenti.ReturnBehaviorNorm;
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
import equivalenzaComportamentale.secondRelease.EquivalenzaArriviFiniti2;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormSCAP extends ElemTypeNormAP {

	private EquivalenzaArriviFiniti2 equivalenzaArriviFiniti2;

	public ElemTypeNormSCAP(ElemTypeNorm elemTypeNorm, int depth) {
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	private ElemTypeNormSCAP(int depth) {
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	public EquivalenzaArriviFiniti2 getEquivalenzaArriviFiniti2() {
		return equivalenzaArriviFiniti2;
	}

	public void setEquivalenzaArriviFiniti2(EquivalenzaArriviFiniti2 equivalenzaArriviFiniti2) {
		this.equivalenzaArriviFiniti2 = equivalenzaArriviFiniti2;
	}

	public HashMap<String, List<RateInf>> getProbRoutingRates() {
		HashMap<String, List<RateInf>> hashMap = new HashMap<String, List<RateInf>>();
		ElemType elemType2 = this.getNewElemType();
		AETbehavior tbehavior = elemType2.getBehavior();
		AETinteractions tinteractions = elemType2.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'diverso da null
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm, tinteractions);
		// preleviamo la differenza tra processTerm e processTerm2
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, phaseBehaviorNorm.getMaximalPhaseBehavior());
		for (ProcessTerm processTerm2 : list) {
			BeginningExitBehaviorNoQNExitNorm beginningExitBehaviorNoQNExitNorm = new BeginningExitBehaviorNoQNExitNorm(
					processTerm2, tinteractions);
			ProcessTerm processTerm4 = null;
			if (beginningExitBehaviorNoQNExitNorm.isBeginningWithJobsRoutingBehavior())
				processTerm4 = beginningExitBehaviorNoQNExitNorm.getBeginningMaximalJobsRoutingBehavior();
			// per precondizione processTerm4e'diverso da null
			// memorizziamo in listDel i nomi delle azioni di consegna
			BeginningExitBehaviorNoQNExitNorm beginningExitBehaviorNoQNExitNorm2 = new BeginningExitBehaviorNoQNExitNorm(
					processTerm4, tinteractions);
			if (beginningExitBehaviorNoQNExitNorm2.isBeginningWithJobsRoutingBehavior()) {
				HashMap<String, List<RateInf>> hashMap2 = beginningExitBehaviorNoQNExitNorm2.getProbRoutingRates();
				Set<Entry<String, List<RateInf>>> set = hashMap2.entrySet();
				for (Entry<String, List<RateInf>> entry : set) {
					String string = entry.getKey();
					List<RateInf> list2 = entry.getValue();
					if (hashMap.containsKey(string)) {
						List<RateInf> list3 = hashMap.get(string);
						list3.addAll(list2);
					} else {
						hashMap.put(string, list2);
					}
				}
			}
		}
		return hashMap;
	}

	public String[] getReturns() {
		ElemType elemType2 = this.getNewElemType();
		AETbehavior tbehavior = elemType2.getBehavior();
		AETinteractions tinteractions = elemType2.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm, tinteractions);
		// 2.il routing di jobs;
		// preleviamo la differenza tra processTerm e processTerm2
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, phaseBehaviorNorm.getMaximalPhaseBehavior());
		List<String> listRit = new ArrayList<String>();
		for (ProcessTerm processTerm3 : list) {
			BeginningExitBehaviorNoQNExitNorm beginningExitBehaviorNoQNExitNorm = new BeginningExitBehaviorNoQNExitNorm(
					processTerm3, tinteractions);
			ProcessTerm processTerm4 = null;
			if (beginningExitBehaviorNoQNExitNorm.isBeginningWithJobsRoutingBehavior())
				processTerm4 = beginningExitBehaviorNoQNExitNorm.getBeginningMaximalJobsRoutingBehavior();
			// preleviamo la differenza tra processTerm3 e processTerm4
			List<ProcessTerm> list21 = MetodiVari.differenza(processTerm3, processTerm4);
			// ogni comportamento di ritorno deve avere almeno un'azione di ritorno
			// e dei comportamenti di ritorno ce ne devono essere solo uno
			ReturnBehaviorNorm returnBehaviorNorm = new ReturnBehaviorNorm(list21.get(0), tinteractions);
			List<String> listR = returnBehaviorNorm.getReturnActionNames();
			// list puo' contenere elementi gia' presenti in listRit
			for (String string : listR) {
				if (!listRit.contains(string))
					listRit.add(string);
			}
		}
		// impostiamo i nomi delle azioni di ritorno
		String[] returns = new String[listRit.size()];
		listRit.toArray(returns);
		return returns;
	}

	public String[] getDelivers() {
		ElemType elemType2 = this.getNewElemType();
		AETbehavior tbehavior = elemType2.getBehavior();
		AETinteractions tinteractions = elemType2.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm, tinteractions);
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, phaseBehaviorNorm.getMaximalPhaseBehavior());
		List<String> listDel = new ArrayList<String>();
		for (ProcessTerm processTerm3 : list) {
			BeginningExitBehaviorNoQNExitNorm beginningExitBehaviorNoQNExitNorm = new BeginningExitBehaviorNoQNExitNorm(
					processTerm3, tinteractions);
			ProcessTerm processTerm4 = null;
			if (beginningExitBehaviorNoQNExitNorm.isBeginningWithJobsRoutingBehavior())
				processTerm4 = beginningExitBehaviorNoQNExitNorm.getBeginningMaximalJobsRoutingBehavior();
			// memorizziamo in listDel i nomi delle azioni di consegna
			BeginningExitBehaviorNoQNExitNorm beginningExitBehaviorNoQNExitNorm2 = new BeginningExitBehaviorNoQNExitNorm(
					processTerm4, tinteractions);
			if (beginningExitBehaviorNoQNExitNorm2.isBeginningWithJobsRoutingBehavior()) {
				// ogni comportamento di routing ha almeno un'azione di consegna
				List<String> list2 = beginningExitBehaviorNoQNExitNorm2.getDeliverActionNames();
				// list2 puo' contenere elementi gia' presenti in listDel
				for (String string : list2) {
					if (!listDel.contains(string))
						listDel.add(string);
				}
			}
		}
		// impostiamo i nomi delle azioni di consegna
		String[] delivers = new String[listDel.size()];
		listDel.toArray(delivers);
		return delivers;
	}

	public List<ExitBehaviorNorm> getExitBehaviors() {
		List<ExitBehaviorNorm> listE = new ArrayList<ExitBehaviorNorm>();
		ElemType elemType2 = this.getNewElemType();
		AETbehavior tbehavior = elemType2.getBehavior();
		AETinteractions tinteractions = elemType2.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'diverso da null
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm, tinteractions);
		// preleviamo la differenza tra processTerm e processTerm2
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, phaseBehaviorNorm.getMaximalPhaseBehavior());
		for (ProcessTerm processTerm2 : list) {
			BeginningExitBehaviorNoQNExitNorm beginningExitBehaviorNoQNExitNorm = new BeginningExitBehaviorNoQNExitNorm(
					processTerm2, tinteractions);
			listE.add(beginningExitBehaviorNoQNExitNorm);
		}
		return listE;
	}

	@Override
	public boolean restrizioneIstanze7() throws RestrizioniIstanzeException {
		HashMap<String, List<RateInf>> hashMap2 = getProbRoutingRates();
		// applico le regole di type checking per i tassi
		Set<Entry<String, List<RateInf>>> set = hashMap2.entrySet();
		for (Entry<String, List<RateInf>> entry : set) {
			List<RateInf> list = entry.getValue();
			for (RateInf rateInf : list) {
				CheckWeight checkWeight = new CheckWeight(rateInf, this.depth + 1);
				try {
					if (!checkWeight.checkWeight()) {
						String string3 = "Instances restrictions error for " + this.idecl.toString();
						this.errorMessage.setErrorMessage(string3);
						List<ErrorMessage> list2 = this.errorMessage.getCauses();
						ErrorMessage errorMessage = checkWeight.getErrorMessage();
						list2.add(errorMessage);
						return false;
					}
				} catch (TypeCheckingException e) {
					throw new RestrizioniIstanzeException(e);
				}
			}
		}
		return true;
	}

	@Override
	public boolean restrizioneIstanze18() throws RestrizioniIstanzeException {
		List<ExitBehaviorNorm> list = getExitBehaviors();
		for (ExitBehaviorNorm exitBehaviorNorm : list) {
			List<Expression> list2 = exitBehaviorNorm.getProbRoutingPrios();
			if (list2.size() > 1) {
				Expression expression = list2.get(0);
				for (int i = 1; i < list2.size(); i++) {
					Expression expression2 = list2.get(i);
					if (!expression.equals(expression2)) {
						String string3 = "Instances restrictions error for " + this.idecl.toString();
						this.errorMessage.setErrorMessage(string3);
						List<ErrorMessage> list3 = this.errorMessage.getCauses();
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						String string = "piorities " + expression.toString() + " and " + expression2.toString()
								+ " are not equal";
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
	public boolean restrizioneIstanze17() throws RestrizioniIstanzeException {
		HashMap<String, List<RateInf>> hashMap2 = getProbRoutingRates();
		// applico le regole di type checking per i tassi
		Set<Entry<String, List<RateInf>>> set = hashMap2.entrySet();
		for (Entry<String, List<RateInf>> entry : set) {
			List<RateInf> list = entry.getValue();
			for (RateInf rateInf : list) {
				CheckPrio checkPrio = new CheckPrio(rateInf, this.depth + 1);
				try {
					if (!checkPrio.checkPrio()) {
						String string3 = "Instances restrictions error for " + this.idecl.toString();
						this.errorMessage.setErrorMessage(string3);
						List<ErrorMessage> list2 = this.errorMessage.getCauses();
						ErrorMessage errorMessage = checkPrio.getErrorMessage();
						list2.add(errorMessage);
						return false;
					}
				} catch (TypeCheckingException e) {
					throw new RestrizioniIstanzeException(e);
				}
			}
		}
		return true;
	}

	@Override
	public ElemTypeNormSCAP copy() {
		ElemTypeNormSCAP elemTypeNormSCAP = new ElemTypeNormSCAP(this.depth);
		elemTypeNormSCAP.setEquivalenzaArriviFiniti2(this.equivalenzaArriviFiniti2.copy());
		elemTypeNormSCAP.setAEIdecl(getAEIdecl().copy());
		elemTypeNormSCAP.setNewElemType(getNewElemType().copy());
		elemTypeNormSCAP.setOldElemType(getOldElemType().copy());
		return elemTypeNormSCAP;
	}

	@Override
	public void print() {
		System.out.println("ElemTypeNormSCAP object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaArriviFiniti2: ");
		this.equivalenzaArriviFiniti2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ElemTypeNormSCAP))
			return false;
		ElemTypeNormSCAP elemTypeNormSCAP = (ElemTypeNormSCAP) obj;
		if (!this.equivalenzaArriviFiniti2.equals(elemTypeNormSCAP.equivalenzaArriviFiniti2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormSCAP.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormSCAP.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormSCAP.getOldElemType()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String string = new String();
		string += "Single Client Arrival Process ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
	}
}
