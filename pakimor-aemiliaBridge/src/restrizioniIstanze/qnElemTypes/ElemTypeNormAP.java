/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.HashMap;
import java.util.List;

import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.comportamenti.PhaseBehaviorNorm;
//import restrizioniIstanze.structure.BranchProbStructNorm;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ElemType;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateExp;
import specificheAEmilia.RateInf;
import utilities.ErrorMessage;
import valutazione.typeChecking.RateExpChecking;
import valutazione.typeChecking.TypeCheckingException;
import valutazione.typeChecking.rateInfChecking.CheckWeight;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.PrePhaseAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public abstract class ElemTypeNormAP extends ElemTypeNorm {

	public Expression[] getTassiProcesso() {
		ElemType elemType2 = this.getNewElemType();
		AETbehavior tbehavior = elemType2.getBehavior();
		AETinteractions tinteractions = elemType2.getInteractions();
		// per precondizione tbehaviore'Tail Recursive
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm, tinteractions);
		ProcessTerm processTerm2 = phaseBehaviorNorm.getPhaseBehavior();
		// per precondizione processTerm2e'diverso da null
		// preleviamo i tassi delle azioni di fase
		PhaseBehaviorNorm phaseBehaviorNorm2 = new PhaseBehaviorNorm(processTerm2, tinteractions);
		List<Expression> listFASI = phaseBehaviorNorm2.getTassi();
		// imposto i tassi del comportamento di fase
		Expression[] tassiProcesso = new Expression[listFASI.size()];
		listFASI.toArray(tassiProcesso);
		return tassiProcesso;
	}

//	public HashMap<String, Double> getProbRoutingDouble() {
//		ElemType elemType = this.getNewElemType();
//		AETbehavior tbehavior = elemType.getBehavior();
//		AETinteractions tinteractions = elemType.getInteractions();
//		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
//		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
//		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
//		BehavEquation behavEquation = behavEquations[0];
//		ProcessTerm processTerm = behavEquation.getTermineProcesso();
//		BranchProbStructNorm branchProbStructNorm = new BranchProbStructNorm(processTerm, tinteractions, 1.0);
//		HashMap<String, Double> hashMap = branchProbStructNorm.getProbRoutingMap();
//		return hashMap;
//	}

	public BehavEquation getPhaseBehavior() {
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
		ProcessTerm processTerm2 = phaseBehaviorNorm.getPhaseBehavior();
		ParamDeclaration[] declPars = new ParamDeclaration[2];
		Header header = new Header("Fase", declPars);
		BehavEquation behavEquation2 = new BehavEquation(header, processTerm2);
		return behavEquation2;
	}

	public PhaseBehaviorNorm getPhaseBehaviorNorm() {
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
		return phaseBehaviorNorm;
	}

	public List<RateExp> getExpRates() {
		ElemType elemType2 = this.getNewElemType();
		AETbehavior tbehavior = elemType2.getBehavior();
		AETinteractions tinteractions = elemType2.getInteractions();
		// per precondizione tbehaviore'Tail Recursive
		TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehaviorNorm phaseBehaviorNorm = new PhaseBehaviorNorm(processTerm, tinteractions);
		ProcessTerm processTerm2 = phaseBehaviorNorm.getPhaseBehavior();
		// per precondizione processTerm2e'diverso da null
		// preleviamo i tassi delle azioni di fase
		PhaseBehaviorNorm phaseBehaviorNorm2 = new PhaseBehaviorNorm(processTerm2, tinteractions);
		List<RateExp> list = phaseBehaviorNorm2.getExpRates();
		// imposto i tassi del comportamento di fase
		return list;
	}

	// le priorita' di tutti i tassi delle azioni di prosecuzione del percorso
	// devono essere uguali tra loro
	public abstract boolean restrizioneIstanze18() throws RestrizioniIstanzeException;

	// le priorita' dei tassi delle azioni di prosecuzione del percorso devono
	// essere interi non minori di uno
	public abstract boolean restrizioneIstanze17() throws RestrizioniIstanzeException;

	// i pesi delle azioni di prosecuzione del percorso devono essere Real maggiori
	// di zero
	public abstract boolean restrizioneIstanze7() throws RestrizioniIstanzeException;

	@Override
	public boolean isCompliantInstanceRules() throws RestrizioniIstanzeException {
		if (!restrizioneIstanze7())
			return false;
		if (!restrizioneIstanze16())
			return false;
		if (!restrizioneIstanze17())
			return false;
		if (!restrizioneIstanze18())
			return false;
		if (!restrizioneIstanze6())
			return false;
		if (!restrizioneIstanze23())
			return false;
		if (!restrizioneIstanze24())
			return false;
		if (!restrizioneIstanze25())
			return false;
		return true;
	}

	// 1)i pesi delle azioni di scelta di fase devono essere Real maggiori di zero
	public boolean restrizioneIstanze5() throws RestrizioniIstanzeException {
		BehavEquation behavEquation = getPhaseBehavior();
		ElemType elemType = getNewElemType();
		AETinteractions tinteractions = elemType.getInteractions();
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
		return true;
	}

	// i tassi delle azioni di fase devono essere Real maggiori di zero
	public boolean restrizioneIstanze6() throws RestrizioniIstanzeException {
		List<RateExp> list = getExpRates();
		for (RateExp rateExp : list) {
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

	// ogni processo choice deve avere azioni pre-phase-action con la stessa
	// priorita'
	public boolean restrizioneIstanze23() throws RestrizioniIstanzeException {
		PhaseBehaviorNorm phaseBehaviorNorm = getPhaseBehaviorNorm();
		RestrizioneIstanze23 restrizioneIstanze23 = new RestrizioneIstanze23(this.depth + 1);
		if (!restrizioneIstanze23.restrizioneIstanze23Rec(phaseBehaviorNorm)) {
			String string3 = "Instances restrictions error for " + this.idecl.toString();
			this.errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			ErrorMessage errorMessage = restrizioneIstanze23.getErrorMessage();
			list2.add(errorMessage);
			return false;
		}
		return true;
	}

	// le priorita' delle azioni pre-phase-action devono essere interi non minori di
	// uno
	public boolean restrizioneIstanze24() throws RestrizioniIstanzeException {
		PhaseBehaviorNorm phaseBehaviorNorm = getPhaseBehaviorNorm();
		RestrizioneIstanze24 restrizioneIstanze24 = new RestrizioneIstanze24(this.depth + 1);
		if (!restrizioneIstanze24.restrizioneIstanze24Rec(phaseBehaviorNorm)) {
			String string3 = "Instances restrictions error for " + this.idecl.toString();
			this.errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			ErrorMessage errorMessage = restrizioneIstanze24.getErrorMessage();
			list2.add(errorMessage);
			return false;
		}
		return true;
	}

	// i pesi delle azioni pre-phase-action devono essere reali maggiori di zero
	public boolean restrizioneIstanze25() throws RestrizioniIstanzeException {
		PhaseBehaviorNorm phaseBehaviorNorm = getPhaseBehaviorNorm();
		RestrizioneIstanze25 restrizioneIstanze25 = new RestrizioneIstanze25(this.depth + 1);
		if (!restrizioneIstanze25.restrizioneIstanze25Rec(phaseBehaviorNorm)) {
			String string3 = "Instances restrictions error for " + this.idecl.toString();
			this.errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			ErrorMessage errorMessage = restrizioneIstanze25.getErrorMessage();
			list2.add(errorMessage);
			return false;
		}
		return true;
	}
}
