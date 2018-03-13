/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.comportamenti.ArriveBehaviorNorm;
import restrizioniIstanze.comportamenti.ExitBehaviorNoQNExitNorm;
import restrizioniIstanze.comportamenti.ExitBehaviorNorm;
import restrizioniIstanze.comportamenti.ExitBehaviorWithQNExitNorm;
import restrizioniIstanze.comportamenti.PhaseBehaviorNorm;
//import restrizioniIstanze.structure.BranchProbStructNorm;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.Action;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateExp;
import specificheAEmilia.RateInf;
import specificheAEmilia.Rate_;
import specificheAEmilia.Stop;
import utilities.ErrorMessage;
import valutazione.typeChecking.RateExpChecking;
import valutazione.typeChecking.TypeCheckingException;
import valutazione.typeChecking.rateInfChecking.CheckPrio;
import valutazione.typeChecking.rateInfChecking.CheckWeight;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.EquivalenzaServizioSenzaBuffer2;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.PrePhaseAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormSPNB extends ElemTypeNormSP {

	private EquivalenzaServizioSenzaBuffer2 equivalenzaServizioSenzaBuffer2;

	public ElemTypeNormSPNB(ElemTypeNorm elemTypeNorm, int depth) {
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	public ElemTypeNormSPNB(int depth) {
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	public EquivalenzaServizioSenzaBuffer2 getEquivalenzaServizioSenzaBuffer2() {
		return equivalenzaServizioSenzaBuffer2;
	}

	public void setEquivalenzaServizioSenzaBuffer2(EquivalenzaServizioSenzaBuffer2 equivalenzaServizioSenzaBuffer2) {
		this.equivalenzaServizioSenzaBuffer2 = equivalenzaServizioSenzaBuffer2;
	}

	public HashMap<String, Expression[]> getTassiServizioFromSel() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// per precondizione processTerme'un processo di arrivi
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// prelevo i nomi delle azioni di arrivo
		List<String> listArrNames = arriveBehaviorNorm.getArriveNames();
		// alloco memoria per i tassi di servizio
		HashMap<String, Expression[]> arrTassiMap = new HashMap<String, Expression[]>();
		// si preleva la differenza tra processTerm e processTerm2
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				arriveBehaviorNorm.getMaximalArrivalBehavior());
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// si preleva il comportamento di fase di phaseBehavior
			ProcessTerm processTerm4 = phaseBehaviorNorm.getMaximalPhaseBehavior();
			// per precondizione processTerm4e'diverso da null
			// aggiorno la mappa per i nomi delle chooses da quelle di arrivo
			String string = listArrNames.get(i);
			// aggiorno la mappa dei tassi di servizio
			PhaseBehaviorNorm phaseBehaviorNorm2 = new PhaseBehaviorNorm(processTerm4, tinteractions);
			List<Expression> listTassi = phaseBehaviorNorm2.getTassi();
			Expression[] espressionesTassi = new Expression[listTassi.size()];
			listTassi.toArray(espressionesTassi);
			arrTassiMap.put(string, espressionesTassi);
		}

		return arrTassiMap;
	}

	public HashMap<String, HashMap<String, List<Expression>>> getProbRoutingFromSel() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di arrivi
		// alloco memoria per la mappa delle probabilita' di routing
		HashMap<String, HashMap<String, List<Expression>>> arrProbSelMap = new HashMap<String, HashMap<String, List<Expression>>>();
		// prelevo i nomi delle azioni di arrivo
		List<String> listArrNames = arriveBehaviorNorm.getArriveNames();
		// si preleva la differenza tra processTerm e processTerm2
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				arriveBehaviorNorm.getMaximalArrivalBehavior());
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// si preleva il comportamento di fase di phaseBehavior
			ProcessTerm processTerm4 = phaseBehaviorNorm.getMaximalPhaseBehavior();
			// per precondizione processTerm4e'diverso da null
			// prelevo la differenza tra processTerm3 e processTerm4
			// processTerm4 deve essere un comportamento di fase massimo
			List<ProcessTerm> list = MetodiVari.differenza(processTerm3, processTerm4);
			String string = listArrNames.get(i);
			// verifica che list contenga comportamenti di routing di job
			// processTerm5 puo' esssere o un comportamento di routing o null
			// o un BehavProcess
			for (ProcessTerm processTerm5 : list) {
				ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5,
						tinteractions);
				ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5,
						tinteractions);
				// aggiorno la mappa per i nomi delle chooses da quelle di arrivo
				if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la mappa delle probabilita' di routing
					HashMap<String, List<Expression>> hashMap = exitBehaviorNoQNExitNorm.getProbsRouting();
					if (arrProbSelMap.containsKey(string)) {
						HashMap<String, List<Expression>> hashMap2 = arrProbSelMap.get(string);
						Set<Entry<String, List<Expression>>> set = hashMap.entrySet();
						for (Entry<String, List<Expression>> entry : set) {
							String string2 = entry.getKey();
							List<Expression> list2 = entry.getValue();
							if (hashMap2.containsKey(string2)) {
								List<Expression> list3 = hashMap2.get(string2);
								list3.addAll(list2);
							} else {
								hashMap2.put(string2, list2);
							}
						}
					} else {
						arrProbSelMap.put(string, hashMap);
					}
				} else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la mappa delle probabilita' di routing
					HashMap<String, List<Expression>> hashMap = exitBehaviorWithQNExitNorm.getProbsRouting();
					if (arrProbSelMap.containsKey(string)) {
						HashMap<String, List<Expression>> hashMap2 = arrProbSelMap.get(string);
						Set<Entry<String, List<Expression>>> set = hashMap.entrySet();
						for (Entry<String, List<Expression>> entry : set) {
							String string2 = entry.getKey();
							List<Expression> list2 = entry.getValue();
							if (hashMap2.containsKey(string2)) {
								List<Expression> list3 = hashMap2.get(string2);
								list3.addAll(list2);
							} else {
								hashMap2.put(string2, list2);
							}
						}
					} else {
						arrProbSelMap.put(string, hashMap);
					}
				} else {
					HashMap<String, List<Expression>> hashMap = new HashMap<String, List<Expression>>();
					if (!hashMap.containsKey(string)) {
						arrProbSelMap.put(string, hashMap);
					}
				}
			}
		}
		return arrProbSelMap;
	}

	public Expression[] getProbSelezione() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un processo di arrivi
		// assegno le probabilita' di selezione
		List<Expression> listProbSel = arriveBehaviorNorm.getProbSelezione();
		// imposto l'array delle probabilita' di selezione
		Expression[] probSelezione = new Expression[listProbSel.size()];
		listProbSel.toArray(probSelezione);
		return probSelezione;
	}

	public HashMap<String, HashMap<String, List<RateInf>>> getProbRoutingRatesFromSel() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di arrivi
		// alloco memoria per la mappa delle azioni di arrivo con quelle di scelta
		HashMap<String, HashMap<String, List<Action>>> arrChooseActionsMap = new HashMap<String, HashMap<String, List<Action>>>();
		// prelevo i nomi delle azioni di arrivo
		List<String> listArrNames = arriveBehaviorNorm.getArriveNames();
		// si preleva la differenza tra processTerm e processTerm2
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				arriveBehaviorNorm.getMaximalArrivalBehavior());
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// si preleva il comportamento di fase di phaseBehavior
			ProcessTerm processTerm4 = phaseBehaviorNorm.getMaximalPhaseBehavior();
			// per precondizione processTerm4e'diverso da null
			// prelevo la differenza tra processTerm3 e processTerm4
			// processTerm4 deve essere un comportamento di fase massimo
			List<ProcessTerm> listP = MetodiVari.differenza(processTerm3, processTerm4);
			String stringA = listArrNames.get(i);
			for (ProcessTerm processTerm2 : listP) {
				ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm2,
						tinteractions);
				ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm2,
						tinteractions);
				// aggiorno la mappa per i nomi delle chooses da quelle di arrivo
				// imposto le mappe con chiave i nomi delle azioni di arrivo e
				// valori dati dai comportamenti di routing
				if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la mappa per le azioni di choose da quelle di arrivo
					HashMap<String, List<Action>> listChooseActions = exitBehaviorNoQNExitNorm.getChooseAction();
					if (arrChooseActionsMap.containsKey(stringA)) {
						HashMap<String, List<Action>> hashMap = arrChooseActionsMap.get(stringA);
						Set<Entry<String, List<Action>>> set = listChooseActions.entrySet();
						for (Entry<String, List<Action>> entry : set) {
							String string = entry.getKey();
							List<Action> list = entry.getValue();
							if (hashMap.containsKey(string)) {
								List<Action> list2 = hashMap.get(string);
								list2.addAll(list);
							} else {
								hashMap.put(string, list);
							}
						}
					} else {
						arrChooseActionsMap.put(stringA, listChooseActions);
					}
				} else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la mappa per le azioni di choose da quelle di arrivo
					HashMap<String, List<Action>> listChooseActions = exitBehaviorWithQNExitNorm.getChooseAction();
					if (arrChooseActionsMap.containsKey(stringA)) {
						HashMap<String, List<Action>> hashMap = arrChooseActionsMap.get(stringA);
						Set<Entry<String, List<Action>>> set = listChooseActions.entrySet();
						for (Entry<String, List<Action>> entry : set) {
							String string = entry.getKey();
							List<Action> list = entry.getValue();
							if (hashMap.containsKey(string)) {
								List<Action> list2 = hashMap.get(string);
								list2.addAll(list);
							} else {
								hashMap.put(string, list);
							}
						}
					} else {
						arrChooseActionsMap.put(stringA, listChooseActions);
					}
				} else {
					HashMap<String, List<Action>> listChooseActions = new HashMap<String, List<Action>>();
					if (!arrChooseActionsMap.containsKey(stringA)) {
						arrChooseActionsMap.put(stringA, listChooseActions);
					}
				}
			}
		}

		HashMap<String, HashMap<String, List<RateInf>>> hashMap = new HashMap<String, HashMap<String, List<RateInf>>>();
		Set<Entry<String, HashMap<String, List<Action>>>> set = arrChooseActionsMap.entrySet();
		for (Entry<String, HashMap<String, List<Action>>> entry : set) {
			String string = entry.getKey();
			HashMap<String, List<Action>> hashMap2 = entry.getValue();
			Set<Entry<String, List<Action>>> set2 = hashMap2.entrySet();
			HashMap<String, List<RateInf>> hashMap3 = new HashMap<String, List<RateInf>>();
			for (Entry<String, List<Action>> entry2 : set2) {
				String string2 = entry2.getKey();
				List<Action> list = entry2.getValue();
				List<RateInf> rateInfs = new ArrayList<RateInf>(list.size());
				for (int i = 0; i < list.size(); i++) {
					Action action = list.get(i);
					RateInf rateInf = (RateInf) action.getRate();
					rateInfs.add(rateInf);
				}
				hashMap3.put(string2, rateInfs);
			}
			hashMap.put(string, hashMap3);
		}
		return hashMap;
	}

	public Expression[] getPrioSelezione() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di arrivi
		// assegno le priorita' di selezione
		List<Expression> listPrioSel = arriveBehaviorNorm.getPrioSelezione();

		// imposto l'array delle priorita' di selezione
		Expression[] prioSelezione = new Expression[listPrioSel.size()];
		listPrioSel.toArray(prioSelezione);
		return prioSelezione;
	}

	public Rate_[] getArrivalRates() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di arrivi
		// prelevo i tassi delle azioni di arrivo
		List<Rate_> listArrRates = arriveBehaviorNorm.getArrivalRates();
		// imposto l'array dei tassi delle azioni di arrivo
		Rate_[] ratesArr = new Rate_[listArrRates.size()];
		listArrRates.toArray(ratesArr);
		return ratesArr;
	}

	public HashMap<String, List<String>> getDeliversFromSelection() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehavior e tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// processTerme'un comportamento di arrivi
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// alloco memoria per la mappa dei nomi delle azioni di consegna da quelli
		// di selezione
		HashMap<String, List<String>> arrDelSelMap = new HashMap<String, List<String>>();
		// prelevo i nomi delle azioni di arrivo
		List<String> listArrNames = arriveBehaviorNorm.getArriveNames();
		// si preleva la differenza tra processTerm e processTerm2
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				arriveBehaviorNorm.getMaximalArrivalBehavior());
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// si preleva il comportamento di fase di phaseBehavior
			ProcessTerm processTerm4 = phaseBehaviorNorm.getMaximalPhaseBehavior();
			// per precondizione processTerm4e'diverso da null
			// prelevo la differenza tra processTerm3 e processTerm4
			// processTerm4 deve essere un comportamento di fase massimo
			List<ProcessTerm> list = MetodiVari.differenza(processTerm3, processTerm4);
			// verifica che list contenga comportamenti di routing di job
			// processTerm5 puo' esssere o un comportamento di routing o null
			// o un BehavProcess
			String string = listArrNames.get(i);
			for (ProcessTerm processTerm5 : list) {
				ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5,
						tinteractions);
				ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5,
						tinteractions);
				// aggiorno la mappa per i nomi delle chooses da quelle di arrivo
				// imposto le mappe con chiave i nomi delle azioni di arrivo e
				// valori dati dai comportamenti di routing
				if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la mappa delle azioni di consegna
					List<String> list2Del = exitBehaviorNoQNExitNorm.getDeliverActionNames();
					if (arrDelSelMap.containsKey(string)) {
						List<String> list2 = arrDelSelMap.get(string);
						list2.addAll(list2Del);
					} else {
						arrDelSelMap.put(string, list2Del);
					}
				} else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la mappa delle azioni di consegna
					List<String> list2Del = exitBehaviorWithQNExitNorm.getDeliverActionNames();
					if (arrDelSelMap.containsKey(string)) {
						List<String> list2 = arrDelSelMap.get(string);
						list2.addAll(list2Del);
					} else {
						arrDelSelMap.put(string, list2Del);
					}
				} else {
					// processTerm5 puo' non essere un comportamento di routing
					if (!arrDelSelMap.containsKey(string)) {
						arrDelSelMap.put(string, new ArrayList<String>());
					}
				}
			}
		}
		return arrDelSelMap;
	}

	public String[] getDelivers() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di arrivi
		// si preleva la differenza tra processTerm e processTerm2
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				arriveBehaviorNorm.getMaximalArrivalBehavior());
		// alloco memoria per i nomi delle azioni di consegna
		List<String> listD = new ArrayList<String>();
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// si preleva il comportamento di fase di phaseBehavior
			ProcessTerm processTerm4 = phaseBehaviorNorm.getMaximalPhaseBehavior();
			// per precondizione processTerm4e'diverso da null
			// prelevo la differenza tra processTerm3 e processTerm4
			// processTerm4 deve essere un comportamento di fase massimo
			List<ProcessTerm> list = MetodiVari.differenza(processTerm3, processTerm4);
			// se list ha lunghezza maggiore di uno vuol dire che ho incontrato un
			// comportamento
			// di fase di tipo hyperesponenziale.
			// processTerm5 puo' esssere o un comportamento di routing o null
			// o un BehavProcess
			for (ProcessTerm processTerm5 : list) {
				ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5,
						tinteractions);
				ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5,
						tinteractions);
				// aggiorno la mappa per i nomi delle chooses da quelle di arrivo
				if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la mappa delle azioni di consegna
					List<String> list2Del = exitBehaviorNoQNExitNorm.getDeliverActionNames();
					// aggiorno la struttura dei nomi delle azioni di consegna
					// list2Del puo' contenere elementi gia' presenti in listD
					for (String string : list2Del) {
						if (!listD.contains(string))
							listD.add(string);
					}
				} else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la mappa delle azioni di consegna
					List<String> list2Del = exitBehaviorWithQNExitNorm.getDeliverActionNames();
					// aggiorno la struttura dei nomi delle azioni di consegna
					// list2Del puo' contenere elementi gia' presenti in listD
					for (String string : list2Del) {
						if (!listD.contains(string))
							listD.add(string);
					}
				}
			}
		}
		// imposto l'array che contiene tutti i nomi delle azioni di consegna
		String[] delivers = new String[listD.size()];
		listD.toArray(delivers);
		return delivers;
	}

	public List<BehavEquation> getServiceEquations() {
		List<BehavEquation> listRis = new ArrayList<BehavEquation>();
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// si preleva il comportamento per l'arrivo
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm, arriveBehaviorNorm.getArrivalBehavior());
		// alloco memoria per i comportamenti di fase
		List<ProcessTerm> listF = new ArrayList<ProcessTerm>();
		// alloco memoria per i comportamenti di routing
		List<ProcessTerm> listCR = new ArrayList<ProcessTerm>();
		for (ProcessTerm processTerm3 : processTerms) {
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// si preleva il comportamento di fase di phaseBehavior
			// prelevo la differenza tra processTerm3 e processTerm4
			// aggiungo processTerm4 a listF
			// processTerm4 nella seguente istruzione deve essere privo di azioni null
			listF.add(phaseBehaviorNorm.getPhaseBehavior());
			List<ProcessTerm> list = MetodiVari.differenza(processTerm3, phaseBehaviorNorm.getMaximalPhaseBehavior());
			// aggiungiamo il primo elemento di list a list2, perche' potrebbero esserci
			// piu' comportamenti
			// di routing a causa della presenza di una distribuzione hyperesponziale
			// list puo' essere vuota perche' il comportamento di routinge'opzionale
			if (!list.isEmpty()) {
				// bisogna verificare se ci sono BehavProcess o Stop.
				// In questo caso va aggiunto null
				for (ProcessTerm processTerm5 : list) {
					if (processTerm5 instanceof Stop || processTerm5 instanceof BehavProcess)
						listCR.add(null);
					else {
						ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5,
								tinteractions);
						ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(
								processTerm5, tinteractions);
						if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
							// nella seguente istruzione list
							// deve essere privo di azioni null
							listCR.add(exitBehaviorNoQNExitNorm.getJobsRoutingBehavior());
						else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
							// nella seguente istruzione list
							// deve essere privo di azioni null
							listCR.add(exitBehaviorWithQNExitNorm.getJobsRoutingBehavior());
					}
				}
			} else
				listCR.add(null);
		}
		// imposto le equazioni di comportamento di fase
		for (int i = 0; i < listF.size(); i++) {
			ProcessTerm processTerm3 = listF.get(i);
			List<BehavProcess> list = computeLeaf(processTerm3);
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			Header intestazione2 = new Header("Fase" + i, declPars2);
			for (int j = 0; j < list.size(); j++) {
				BehavProcess behavProcess = list.get(j);
				behavProcess.setExprs(new Expression[0]);
				// deve esserci una sola equazione di routing per ogni equazione di fase
				behavProcess.setName("Routing" + j);
			}
			BehavEquation behavEquation3 = new BehavEquation(intestazione2, processTerm3);
			listRis.add(behavEquation3);
		}
		return listRis;
	}

	public HashMap<String, String> getServicesNamesFromSelections() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		List<BehavEquation> listRis = new ArrayList<BehavEquation>();
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// si preleva il comportamento per l'arrivo
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				arriveBehaviorNorm.getMaximalArrivalBehavior());
		// alloco memoria per i comportamenti di fase
		List<ProcessTerm> listF = new ArrayList<ProcessTerm>();
		// alloco memoria per i comportamenti di routing
		List<ProcessTerm> listCR = new ArrayList<ProcessTerm>();

		for (ProcessTerm processTerm3 : processTerms) {
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// prelevo la differenza tra processTerm3 e processTerm4
			// aggiungo processTerm4 a listF
			listF.add(phaseBehaviorNorm.getPhaseBehavior());
			List<ProcessTerm> list = MetodiVari.differenza(processTerm3, phaseBehaviorNorm.getMaximalPhaseBehavior());
			// aggiungiamo il primo elemento di list a list2, perche' potrebbero esserci
			// piu' comportamenti
			// di routing a causa della presenza di una distribuzione hyperesponziale
			// list puo' essere vuota perche' il comportamento di routinge'opzionale
			if (!list.isEmpty()) {
				// bisogna verificare se ci sono BehavProcess o Stop.
				// In questo caso va aggiunto null
				for (ProcessTerm processTerm5 : list) {
					if (processTerm5 instanceof Stop || processTerm5 instanceof BehavProcess)
						listCR.add(null);
					else {
						ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5,
								tinteractions);
						ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(
								processTerm5, tinteractions);
						if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior())
							listCR.add(exitBehaviorNoQNExitNorm.getJobsRoutingBehavior());
						else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior())
							listCR.add(exitBehaviorWithQNExitNorm.getJobsRoutingBehavior());
					}
				}
			} else
				listCR.add(null);

		}
		// imposto le equazioni di comportamento di fase
		for (int i = 0; i < listF.size(); i++) {
			ProcessTerm processTerm3 = listF.get(i);
			List<BehavProcess> list = computeLeaf(processTerm3);
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			Header intestazione2 = new Header("Fase" + i, declPars2);
			for (int j = 0; j < list.size(); j++) {
				BehavProcess behavProcess = list.get(j);
				behavProcess.setExprs(new Expression[0]);
				behavProcess.setName("Routing" + j);
			}
			BehavEquation behavEquation3 = new BehavEquation(intestazione2, processTerm3);
			listRis.add(behavEquation3);
		}
		List<String> listArr = arriveBehaviorNorm.getArriveNames();
		for (int i = 0; i < listArr.size(); i++) {
			String string = listArr.get(i);
			BehavEquation behavEquation2 = listRis.get(i);
			Header header = behavEquation2.getBehavHeader();
			String string2 = header.getName();
			hashMap.put(string, string2);
		}
		return hashMap;
	}

	public String[] getArrivesNames() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// prelevo i nomi delle azioni di arrivo
		List<String> listArrNames = arriveBehaviorNorm.getArriveNames();
		// imposto l'array dei nomi delle azioni di arrivo
		String[] selectionsNames = new String[listArrNames.size()];
		listArrNames.toArray(selectionsNames);
		return selectionsNames;
	}

//	public HashMap<String, HashMap<String, Double>> getProbRoutingDouble() {
//		HashMap<String, HashMap<String, Double>> hashMap = new HashMap<String, HashMap<String, Double>>();
//		AETbehavior tbehavior = this.getNewElemType().getBehavior();
//		AETinteractions tinteractions = this.getNewElemType().getInteractions();
//		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
//		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
//		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
//		// per precondizione tbehavior2 ha una sola equazione
//		BehavEquation behavEquation = behavEquations[0];
//		ProcessTerm processTerm = behavEquation.getTermineProcesso();
//		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
//		// si preleva il comportamento per l'arrivo
//		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
//				arriveBehaviorNorm.getMaximalArrivalBehavior());
//		// alloco memoria per i comportamenti di fase
//		List<String> listArr = arriveBehaviorNorm.getArriveNames();
//		for (int i = 0; i < listArr.size(); i++) {
//			String string = listArr.get(i);
//			ProcessTerm processTerm2 = processTerms.get(i);
//			BranchProbStructNorm branchProbStructNorm = new BranchProbStructNorm(processTerm2, tinteractions, 1);
//			HashMap<String, Double> hashMap2 = branchProbStructNorm.getProbRoutingMap();
//			hashMap.put(string, hashMap2);
//		}
//		return hashMap;
//	}

	public HashMap<String, List<ExitBehaviorNorm>> getExitBehaviors() {
		HashMap<String, List<ExitBehaviorNorm>> hashMap = new HashMap<String, List<ExitBehaviorNorm>>();
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di arrivi
		// prelevo i nomi delle azioni di arrivo
		List<String> listArrNames = arriveBehaviorNorm.getArriveNames();
		// si preleva la differenza tra processTerm e processTerm2
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				arriveBehaviorNorm.getMaximalArrivalBehavior());
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			String string = listArrNames.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// si preleva il comportamento di fase di phaseBehavior
			ProcessTerm processTerm4 = phaseBehaviorNorm.getMaximalPhaseBehavior();
			// per precondizione processTerm4e'diverso da null
			// prelevo la differenza tra processTerm3 e processTerm4
			// processTerm4 deve essere un comportamento di fase massimo
			List<ProcessTerm> list = MetodiVari.differenza(processTerm3, processTerm4);
			// verifica che list contenga comportamenti di routing di job
			// processTerm5 puo' esssere o un comportamento di routing o null
			// o un BehavProcess
			List<ExitBehaviorNorm> list2 = new ArrayList<ExitBehaviorNorm>();
			for (int j = 0; j < list.size(); j++) {
				ProcessTerm processTerm2 = list.get(j);
				ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm2,
						tinteractions);
				ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm2,
						tinteractions);
				if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior()) {
					list2.add(exitBehaviorNoQNExitNorm);
				} else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior()) {
					list2.add(exitBehaviorWithQNExitNorm);
				}
			}
			hashMap.put(string, list2);
		}
		return hashMap;
	}

	public HashMap<String, PhaseBehaviorNorm> getPhaseBehaviorsNorm() {
		HashMap<String, PhaseBehaviorNorm> hashMap = new HashMap<String, PhaseBehaviorNorm>();
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehaviorNorm arriveBehaviorNorm = new ArriveBehaviorNorm(processTerm, tinteractions);
		// si preleva il comportamento per l'arrivo
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				arriveBehaviorNorm.getMaximalArrivalBehavior());
		List<String> listArr = arriveBehaviorNorm.getArriveNames();
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			String string = listArr.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			hashMap.put(string, phaseBehaviorNorm);
		}
		return hashMap;
	}

	@Override
	public boolean restrizioneIstanze5() throws RestrizioniIstanzeException {
		List<BehavEquation> behavEquations = getServiceEquations();
		AETinteractions tinteractions = getNewElemType().getInteractions();
		for (BehavEquation behavEquation : behavEquations) {
			ProcessTerm processTerm = behavEquation.getTermineProcesso();
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm, tinteractions);
			// per precondizione phaseBehaviore'un comportamento di fase
			List<PrePhaseAction> list = phaseBehaviorNorm.getPrePhaseActions();
			for (PrePhaseAction prePhaseAction : list) {
				RateInf rateInf = prePhaseAction.getRate();
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
	public boolean restrizioneIstanze6() throws RestrizioniIstanzeException {
		List<BehavEquation> behavEquations = getServiceEquations();
		AETinteractions tinteractions = getNewElemType().getInteractions();
		List<RateExp> listRis = new ArrayList<RateExp>();
		for (BehavEquation behavEquation : behavEquations) {
			ProcessTerm processTerm = behavEquation.getTermineProcesso();
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm, tinteractions);
			// per precondizione phaseBehaviore'un comportamento di fase
			List<RateExp> list = phaseBehaviorNorm.getExpRates();
			listRis.addAll(list);
		}
		for (RateExp rateExp : listRis) {
			// applico la regola di type checking per i tassi
			RateExpChecking rateExpChecking = new RateExpChecking(rateExp, this.depth + 1);
			try {
				if (!rateExpChecking.checkPosRealExp()) {
					String string3 = "Instances restrictions error for " + this.idecl.toString();
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = rateExpChecking.getErrorMessage();
					list2.add(errorMessage);
					return false;
				}
			} catch (TypeCheckingException e) {
				throw new RestrizioniIstanzeException(e);
			}
		}
		return true;
	}

	@Override
	public boolean restrizioneIstanze7() throws RestrizioniIstanzeException {
		HashMap<String, HashMap<String, List<RateInf>>> hashMap2 = getProbRoutingRatesFromSel();
		Collection<HashMap<String, List<RateInf>>> collection = hashMap2.values();
		for (HashMap<String, List<RateInf>> hashMap : collection) {
			// applico le regole di type checking per i tassi
			Set<Entry<String, List<RateInf>>> set = hashMap.entrySet();
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
		}
		return true;
	}

	@Override
	public boolean restrizioneIstanze19() throws RestrizioniIstanzeException {
		HashMap<String, HashMap<String, List<RateInf>>> hashMap2 = getProbRoutingRatesFromSel();
		Set<Entry<String, HashMap<String, List<RateInf>>>> set = hashMap2.entrySet();
		for (Entry<String, HashMap<String, List<RateInf>>> entry : set) {
			HashMap<String, List<RateInf>> hashMap = entry.getValue();
			Set<Entry<String, List<RateInf>>> set2 = hashMap.entrySet();
			for (Entry<String, List<RateInf>> entry2 : set2) {
				List<RateInf> list = entry2.getValue();
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
		}
		return true;
	}

	@Override
	public boolean restrizioneIstanze20() throws RestrizioniIstanzeException {
		HashMap<String, List<ExitBehaviorNorm>> hashMap = getExitBehaviors();
		Set<Entry<String, List<ExitBehaviorNorm>>> set = hashMap.entrySet();
		for (Entry<String, List<ExitBehaviorNorm>> entry : set) {
			List<ExitBehaviorNorm> list = entry.getValue();
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
		}
		return true;
	}

	@Override
	public boolean restrizioneIstanze21() throws RestrizioniIstanzeException {
		Rate_[] rate_s = this.getArrivalRates();
		// applico le regole di type checking per i tassi
		for (Rate_ rate_ : rate_s) {
			valutazione.typeChecking.rate_Checking.CheckPrio checkPrio = new valutazione.typeChecking.rate_Checking.CheckPrio(
					rate_, this.depth + 1);
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
		return true;
	}

	@Override
	public boolean restrizioneIstanze22() throws RestrizioniIstanzeException {
		Rate_[] rate_s = this.getArrivalRates();
		// applico le regole di type checking per i tassi
		for (Rate_ rate_ : rate_s) {
			valutazione.typeChecking.rate_Checking.CheckWeight checkWeight = new valutazione.typeChecking.rate_Checking.CheckWeight(
					rate_, this.depth + 1);
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
		return true;
	}

	@Override
	public boolean restrizioneIstanze23() throws RestrizioniIstanzeException {
		HashMap<String, PhaseBehaviorNorm> phaseBehaviorNormMap = getPhaseBehaviorsNorm();
		Set<Entry<String, PhaseBehaviorNorm>> set = phaseBehaviorNormMap.entrySet();
		for (Entry<String, PhaseBehaviorNorm> entry : set) {
			RestrizioneIstanze23 restrizioneIstanze23 = new RestrizioneIstanze23(this.depth + 1);
			PhaseBehaviorNorm phaseBehaviorNorm = entry.getValue();
			if (!restrizioneIstanze23.restrizioneIstanze23Rec(phaseBehaviorNorm)) {
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = restrizioneIstanze23.getErrorMessage();
				list2.add(errorMessage);
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean restrizioneIstanze24() throws RestrizioniIstanzeException {
		HashMap<String, PhaseBehaviorNorm> phaseBehaviorNormMap = getPhaseBehaviorsNorm();
		Set<Entry<String, PhaseBehaviorNorm>> set = phaseBehaviorNormMap.entrySet();
		for (Entry<String, PhaseBehaviorNorm> entry : set) {
			RestrizioneIstanze24 restrizioneIstanze24 = new RestrizioneIstanze24(this.depth + 1);
			PhaseBehaviorNorm phaseBehaviorNorm = entry.getValue();
			if (!restrizioneIstanze24.restrizioneIstanze24Rec(phaseBehaviorNorm)) {
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = restrizioneIstanze24.getErrorMessage();
				list2.add(errorMessage);
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean restrizioneIstanze25() throws RestrizioniIstanzeException {
		HashMap<String, PhaseBehaviorNorm> phaseBehaviorNormMap = getPhaseBehaviorsNorm();
		Set<Entry<String, PhaseBehaviorNorm>> set = phaseBehaviorNormMap.entrySet();
		for (Entry<String, PhaseBehaviorNorm> entry : set) {
			RestrizioneIstanze25 restrizioneIstanze25 = new RestrizioneIstanze25(this.depth + 1);
			PhaseBehaviorNorm phaseBehaviorNorm = entry.getValue();
			if (!restrizioneIstanze25.restrizioneIstanze25Rec(phaseBehaviorNorm)) {
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = restrizioneIstanze25.getErrorMessage();
				list2.add(errorMessage);
				return false;
			}
		}
		return true;
	}

	@Override
	public ElemTypeNormSPNB copy() {
		ElemTypeNormSPNB elemTypeNormSPNB = new ElemTypeNormSPNB(this.depth);
		elemTypeNormSPNB.setEquivalenzaServizioSenzaBuffer2(this.equivalenzaServizioSenzaBuffer2.copy());
		elemTypeNormSPNB.setAEIdecl(getAEIdecl().copy());
		elemTypeNormSPNB.setNewElemType(getNewElemType().copy());
		elemTypeNormSPNB.setOldElemType(getOldElemType().copy());
		return elemTypeNormSPNB;
	}

	@Override
	public void print() {
		System.out.println("ElemTypeNormSPNB object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaServizioSenzaBuffer2: ");
		this.equivalenzaServizioSenzaBuffer2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ElemTypeNormSPNB))
			return false;
		ElemTypeNormSPNB elemTypeNormSPNB = (ElemTypeNormSPNB) obj;
		if (!this.equivalenzaServizioSenzaBuffer2.equals(elemTypeNormSPNB.equivalenzaServizioSenzaBuffer2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormSPNB.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormSPNB.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormSPNB.getOldElemType()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String string = new String();
		string += "Service Process With No Buffer ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
	}
}
