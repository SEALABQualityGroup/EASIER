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
import restrizioniIstanze.comportamenti.ExitBehaviorNoQNExitNorm;
import restrizioniIstanze.comportamenti.ExitBehaviorNorm;
import restrizioniIstanze.comportamenti.ExitBehaviorWithQNExitNorm;
import restrizioniIstanze.comportamenti.PhaseBehaviorNorm;
import restrizioniIstanze.comportamenti.SelectBehaviorNorm;
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
import specificheAEmilia.Stop;
import utilities.ErrorMessage;
import valutazione.typeChecking.RateExpChecking;
import valutazione.typeChecking.TypeCheckingException;
import valutazione.typeChecking.rateInfChecking.CheckPrio;
import valutazione.typeChecking.rateInfChecking.CheckWeight;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.EquivalenzaServizioConBuffer2;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.PrePhaseAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormSPWB extends ElemTypeNormSP {

	private EquivalenzaServizioConBuffer2 equivalenzaServizioConBuffer2;

	public ElemTypeNormSPWB(ElemTypeNorm elemTypeNorm, int depth) {
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	private ElemTypeNormSPWB(int depth) {
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	public EquivalenzaServizioConBuffer2 getEquivalenzaServizioConBuffer2() {
		return equivalenzaServizioConBuffer2;
	}

	public void setEquivalenzaServizioConBuffer2(EquivalenzaServizioConBuffer2 equivalenzaServizioConBuffer2) {
		this.equivalenzaServizioConBuffer2 = equivalenzaServizioConBuffer2;
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
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di selezione
		// alloco memoria per la mappa dei tassi di servizio
		HashMap<String, Expression[]> selTassiMap = new HashMap<String, Expression[]>();

		// prelevo i nomi delle azioni di selezione
		List<String> listSelNames = selectBehaviorNorm.getSelectionNames();
		// prelevo i tassi delle azioni di selezione
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				selectBehaviorNorm.getMaximalSelectionBehavior());
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// si preleva il comportamento di fase di phaseBehavior
			ProcessTerm processTerm4 = phaseBehaviorNorm.getPhaseBehavior();
			// per precondizione processTerm4 none'null
			// imposto la mappa per i nomi delle chooses da quelle di selezione
			String string = listSelNames.get(i);
			// aggiorno la mappa dei tassi di servizio
			PhaseBehaviorNorm phaseBehaviorNorm2 = new PhaseBehaviorNorm(processTerm4, tinteractions);
			List<Expression> listTassi = phaseBehaviorNorm2.getTassi();
			Expression[] espressionesTassi = new Expression[listTassi.size()];
			listTassi.toArray(espressionesTassi);
			selTassiMap.put(string, espressionesTassi);
		}

		return selTassiMap;
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
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di selezione
		// alloco memoria per le probabilita' di routing
		List<Expression> listProbSel = selectBehaviorNorm.getProbSelezione();

		// imposto le probabilita' di selezione
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
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di selezione
		// alloco memoria per la mappa delle azioni di selezione con le azioni di scelta
		HashMap<String, HashMap<String, List<Action>>> selChooseActionsmap = new HashMap<String, HashMap<String, List<Action>>>();
		// prelevo i nomi delle azioni di selezione
		List<String> listSelNames = selectBehaviorNorm.getSelectionNames();
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				selectBehaviorNorm.getMaximalSelectionBehavior());
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			String string = listSelNames.get(i);
			List<ProcessTerm> listP = MetodiVari.differenza(processTerm3, phaseBehaviorNorm.getMaximalPhaseBehavior());
			for (ProcessTerm processTerm2 : listP) {
				ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm2,
						tinteractions);
				ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm2,
						tinteractions);
				// aggiorno la mappa per i nomi delle chooses da quelle di arrivo
				// imposto le mappe con chiave i nomi delle azioni di arrivo e
				// valori dati dai comportamenti di routing
				if (!selChooseActionsmap.containsKey(string)) {
					if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior()) {
						// aggiorno la mappa per le azioni di choose da quelle di arrivo
						HashMap<String, List<Action>> listChooseActions = exitBehaviorNoQNExitNorm.getChooseAction();
						selChooseActionsmap.put(string, listChooseActions);
					} else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior()) {
						// aggiorno la mappa per le azioni di choose da quelle di arrivo
						HashMap<String, List<Action>> listChooseActions = exitBehaviorWithQNExitNorm.getChooseAction();
						selChooseActionsmap.put(string, listChooseActions);
					} else {
						HashMap<String, List<Action>> listChooseActions = new HashMap<String, List<Action>>();
						selChooseActionsmap.put(string, listChooseActions);
					}
				} else {
					HashMap<String, List<Action>> hashMap = selChooseActionsmap.get(string);
					if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior()) {
						// aggiorno la mappa per le azioni di choose da quelle di arrivo
						HashMap<String, List<Action>> hashMapC = exitBehaviorNoQNExitNorm.getChooseAction();
						Set<Entry<String, List<Action>>> set = hashMapC.entrySet();
						for (Entry<String, List<Action>> entry : set) {
							String string2 = entry.getKey();
							List<Action> list2 = entry.getValue();
							if (hashMap.containsKey(string2)) {
								List<Action> list3 = hashMap.get(string2);
								list3.addAll(list2);
							} else {
								hashMap.put(string2, list2);
							}
						}
					} else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior()) {
						// aggiorno la mappa per le azioni di choose da quelle di arrivo
						HashMap<String, List<Action>> hashMapC = exitBehaviorWithQNExitNorm.getChooseAction();
						Set<Entry<String, List<Action>>> set = hashMapC.entrySet();
						for (Entry<String, List<Action>> entry : set) {
							String string2 = entry.getKey();
							List<Action> list2 = entry.getValue();
							if (hashMap.containsKey(string2)) {
								List<Action> list3 = hashMap.get(string2);
								list3.addAll(list2);
							} else {
								hashMap.put(string2, list2);
							}
						}
					}
				}
			}
		}

		HashMap<String, HashMap<String, List<RateInf>>> hashMap = new HashMap<String, HashMap<String, List<RateInf>>>();
		Set<Entry<String, HashMap<String, List<Action>>>> set = selChooseActionsmap.entrySet();
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
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di selezione
		// si preleva il comportamento per la selezione
		// alloco memoria per le priorita' di selezione
		List<Expression> listPrioSel = selectBehaviorNorm.getPrioSelezione();
		// imposto le priorita' di selezione
		Expression[] prioSelezione = new Expression[listPrioSel.size()];
		listPrioSel.toArray(prioSelezione);

		return prioSelezione;
	}

	public RateInf[] getSelectionRates() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di selezione
		// prelevo i tassi delle azioni di selezione
		List<RateInf> listSelRates = selectBehaviorNorm.getSelectionRates();
		// imposto l'array dei tassi delle azioni di selezione
		RateInf[] ratesSel = new RateInf[listSelRates.size()];
		listSelRates.toArray(ratesSel);
		return ratesSel;
	}

	public HashMap<String, List<String>> getDeliversFromSelection() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		// per precondizione tbehaviore'tail recursion
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		// si preleva il comportamento per la selezione
		// alloco memoria per la mappa di associazione tra i nomi delle azioni di
		// selezione
		// e i nomi delle azioni di consegna associati
		HashMap<String, List<String>> selNameActionsMap = new HashMap<String, List<String>>();
		// prelevo i nomi delle azioni di selezione
		List<String> listSelNames = selectBehaviorNorm.getSelectionNames();
		// prelevo i tassi delle azioni di selezione
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				selectBehaviorNorm.getMaximalSelectionBehavior());
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// si preleva il comportamento di fase di phaseBehavior
			ProcessTerm processTerm4 = phaseBehaviorNorm.getMaximalPhaseBehavior();
			// processTerm4e'diverso da null
			List<ProcessTerm> list = MetodiVari.differenza(processTerm3, processTerm4);
			// verifica che list contenga comportamenti di routing di job
			// processTerm5 deve essere o un comportamento di routing o
			// null o un BehavProcess
			for (ProcessTerm processTerm5 : list) {
				ExitBehaviorNoQNExitNorm exitBehaviorNoQNExit = new ExitBehaviorNoQNExitNorm(processTerm5,
						tinteractions);
				ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5,
						tinteractions);
				// imposto la mappa per i nomi delle chooses da quelle di selezione
				String string = listSelNames.get(i);
				if (exitBehaviorNoQNExit.isJobsRoutingBehavior()) {
					// aggiorno la mappa per i nomi delle azioni di consegna da quelle di selezione
					List<String> listDelNames = exitBehaviorNoQNExit.getDeliverActionNames();
					if (selNameActionsMap.containsKey(string)) {
						List<String> list2 = selNameActionsMap.get(string);
						list2.addAll(listDelNames);
					} else {
						selNameActionsMap.put(string, listDelNames);
					}
				} else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la mappa per i nomi delle azioni di consegna da quelle di selezione
					List<String> listDelNames = exitBehaviorWithQNExitNorm.getDeliverActionNames();
					if (selNameActionsMap.containsKey(string)) {
						List<String> list2 = selNameActionsMap.get(string);
						list2.addAll(listDelNames);
					} else {
						selNameActionsMap.put(string, listDelNames);
					}
				} else {
					// processTerm5 puo' non essere un comportamento di routing
					if (!selNameActionsMap.containsKey(string)) {
						selNameActionsMap.put(string, new ArrayList<String>());
					}
				}
			}
		}
		return selNameActionsMap;
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
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		// per precondizione processTerme'un comportamento di selezione
		// prelevo i tassi delle azioni di selezione
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				selectBehaviorNorm.getMaximalSelectionBehavior());
		// alloco memoria per i nomi delle azioni di consegna
		List<String> listD = new ArrayList<String>();
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// per preconzione processTerm4e'diverso da null
			List<ProcessTerm> list = MetodiVari.differenza(processTerm3, phaseBehaviorNorm.getMaximalPhaseBehavior());
			// verifica che list contenga comportamenti di routing di job
			// processTerm5 deve essere o un comportamento di routing o
			// null o un BehavProcess
			for (ProcessTerm processTerm5 : list) {
				ExitBehaviorNoQNExitNorm exitBehaviorNoQNExitNorm = new ExitBehaviorNoQNExitNorm(processTerm5,
						tinteractions);
				ExitBehaviorWithQNExitNorm exitBehaviorWithQNExitNorm = new ExitBehaviorWithQNExitNorm(processTerm5,
						tinteractions);
				if (exitBehaviorNoQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la struttura dei nomi delle azioni di consegna
					List<String> list2 = exitBehaviorNoQNExitNorm.getDeliverActionNames();
					// list2 puo' contenere elementi gia' presenti in listD
					for (String string : list2) {
						if (!listD.contains(string))
							listD.add(string);
					}
				} else if (exitBehaviorWithQNExitNorm.isJobsRoutingBehavior()) {
					// aggiorno la struttura dei nomi delle azioni di consegna
					List<String> list2 = exitBehaviorWithQNExitNorm.getDeliverActionNames();
					// list2 puo' contenere elementi gia' presenti in listD
					for (String string : list2) {
						if (!listD.contains(string))
							listD.add(string);
					}
				}
			}
		}
		// imposto i nomi di tutte le azioni di consegna
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
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		// si preleva il comportamento per la selezione
		// si preleva la differenza tra processTerm e processTerm2
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				selectBehaviorNorm.getMaximalSelectionBehavior());
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
			ProcessTerm processTerm4 = listCR.get(i);
			List<BehavProcess> list = computeLeaf(processTerm3);
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			Header intestazione2 = new Header("Fase" + i, declPars2);
			for (int j = 0; j < list.size(); j++) {
				BehavProcess behavProcess = list.get(j);
				behavProcess.setExprs(new Expression[0]);
				// l'equazione di routinge'opzionale
				if (processTerm4 != null)
					behavProcess.setName("Routing" + j);
				else
					behavProcess.setName("Select");
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
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				selectBehaviorNorm.getMaximalSelectionBehavior());
		// alloco memoria per i comportamenti di fase
		List<ProcessTerm> listF = new ArrayList<ProcessTerm>();
		// alloco memoria per i comportamenti di routing
		List<ProcessTerm> listCR = new ArrayList<ProcessTerm>();
		for (ProcessTerm processTerm3 : processTerms) {
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			// si preleva il comportamento di fase di phaseBehavior
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
			ProcessTerm processTerm4 = listCR.get(i);
			List<BehavProcess> list = computeLeaf(processTerm3);
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			Header intestazione2 = new Header("Fase" + i, declPars2);
			for (int j = 0; j < list.size(); j++) {
				BehavProcess behavProcess = list.get(j);
				behavProcess.setExprs(new Expression[0]);
				// l'equazione di routinge'opzionale
				if (processTerm4 != null)
					behavProcess.setName("Routing" + j);
				else
					behavProcess.setName("Select");
			}
			BehavEquation behavEquation3 = new BehavEquation(intestazione2, processTerm3);
			listRis.add(behavEquation3);
		}
		List<String> listSel = selectBehaviorNorm.getSelectionNames();
		for (int i = 0; i < listSel.size(); i++) {
			String string = listSel.get(i);
			BehavEquation behavEquation2 = listRis.get(i);
			Header header = behavEquation2.getBehavHeader();
			String string2 = header.getName();
			hashMap.put(string, string2);
		}
		return hashMap;
	}

	public String[] getSelectionsNames() {
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		// si preleva il comportamento per la selezione
		// prelevo i nomi delle azioni di selezione
		List<String> listSelNames = selectBehaviorNorm.getSelectionNames();
		// imposto l'array dei nomi delle azioni di selezione
		String[] stringsSel = new String[listSelNames.size()];
		listSelNames.toArray(stringsSel);
		return stringsSel;
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
//		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
//		// si preleva il comportamento per l'arrivo
//		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
//				selectBehaviorNorm.getMaximalSelectionBehavior());
//		// alloco memoria per i comportamenti di fase
//		List<String> listArr = selectBehaviorNorm.getSelectionNames();
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
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		List<String> listSelNames = selectBehaviorNorm.getSelectionNames();
		// si preleva la differenza tra processTerm e processTerm2
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				selectBehaviorNorm.getMaximalSelectionBehavior());
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			String string = listSelNames.get(i);
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
		SelectBehaviorNorm selectBehaviorNorm = new SelectBehaviorNorm(processTerm, tinteractions);
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm,
				selectBehaviorNorm.getMaximalSelectionBehavior());
		List<String> listSel = selectBehaviorNorm.getSelectionNames();
		for (int i = 0; i < processTerms.size(); i++) {
			ProcessTerm processTerm3 = processTerms.get(i);
			String string = listSel.get(i);
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm3, tinteractions);
			hashMap.put(string, phaseBehaviorNorm);
		}
		return hashMap;
	}

	/*
	 * 5) le priorita' delle azioni di selezione devono essere Integer
	 */
	public boolean restrizioneIstanze15() {
		Expression[] espressiones = getPrioSelezione();
		for (Expression expression : espressiones) {
			if (!(MetodiVari.isOnlyInteger(expression))) {
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string = "piority " + expression.toString() + " must be integer";
				errorMessage.setErrorMessage(string);
				list3.add(errorMessage);
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean restrizioneIstanze5() throws RestrizioniIstanzeException {
		List<BehavEquation> behavEquations = getServiceEquations();
		AETinteractions tinteractions = getNewElemType().getInteractions();
		for (BehavEquation behavEquation : behavEquations) {
			ProcessTerm processTerm = behavEquation.getTermineProcesso();
			PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm, tinteractions);
			// per precondizione phaseBehaviore'un comportamento di fase
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
		RateInf[] rateInfs = this.getSelectionRates();
		// applico le regole di type checking per i tassi
		for (RateInf rateInf : rateInfs) {
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
		return true;
	}

	@Override
	public boolean restrizioneIstanze22() throws RestrizioniIstanzeException {
		RateInf[] rateInfs = this.getSelectionRates();
		// applico le regole di type checking per i tassi
		for (RateInf rateInf : rateInfs) {
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
	public ElemTypeNormSPWB copy() {
		ElemTypeNormSPWB elemTypeNormSPWB = new ElemTypeNormSPWB(this.depth);
		elemTypeNormSPWB.setEquivalenzaServizioConBuffer2(this.equivalenzaServizioConBuffer2.copy());
		elemTypeNormSPWB.setAEIdecl(getAEIdecl().copy());
		elemTypeNormSPWB.setNewElemType(getNewElemType().copy());
		elemTypeNormSPWB.setOldElemType(getOldElemType().copy());
		return elemTypeNormSPWB;
	}

	@Override
	public void print() {
		System.out.println("ElemTypeNormSPWB object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaServizioConBuffer2: ");
		this.equivalenzaServizioConBuffer2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ElemTypeNormSPWB))
			return false;
		ElemTypeNormSPWB elemTypeNormSPWB = (ElemTypeNormSPWB) obj;
		if (!this.equivalenzaServizioConBuffer2.equals(elemTypeNormSPWB.equivalenzaServizioConBuffer2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormSPWB.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormSPWB.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormSPWB.getOldElemType()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String string = new String();
		string += "Service Process With Buffer ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
	}
}