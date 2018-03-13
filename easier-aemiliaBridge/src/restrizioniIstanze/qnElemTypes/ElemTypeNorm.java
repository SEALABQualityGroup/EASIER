/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.ArrayList;
import java.util.List;

import restrizioniIstanze.RestrizioniIstanzeException;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.ElemType;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateExp;
import specificheAEmilia.RateInf;
import specificheAEmilia.Rate_;
import utilities.ErrorMessage;
import valutazione.Find;

/**
 * @author Mirko
 *
 */
public class ElemTypeNorm implements AEmiliaBase {

	private ElemType newElemType;
	private ElemType oldElemType;
	AEIdecl idecl;
	int depth;
	ErrorMessage errorMessage;

	public ElemType getNewElemType() {
		return this.newElemType;
	}

	public void setOldElemType(ElemType et) {
		this.oldElemType = et;
	}

	public void setNewElemType(ElemType clone) {
		this.newElemType = clone;
	}

	public void setAEIdecl(AEIdecl aeid) {
		this.idecl = aeid;
	}

	public AEIdecl getAEIdecl() {
		return this.idecl;
	}

	public ElemType getOldElemType() {
		return oldElemType;
	}

	protected List<BehavProcess> computeLeaf(ProcessTerm processTerm2) {
		List<BehavProcess> list = new ArrayList<BehavProcess>();
		// se processTerm2e'ActionProcess e il suo processoe'o null o una
		// chiamata di comportamento si imposta un BehavProcess come processo di
		// processTerm2
		if (processTerm2 instanceof ActionProcess) {
			ActionProcess actionProcess = (ActionProcess) processTerm2;
			ProcessTerm processTerm3 = actionProcess.getProcesso();
			if (processTerm3 instanceof BehavProcess) {
				BehavProcess behavProcess = (BehavProcess) processTerm3;
				list.add(behavProcess);
			}
			if (processTerm3 == null) {
				BehavProcess behavProcess = new BehavProcess();
				actionProcess.setProcesso(behavProcess);
				list.add(behavProcess);
			}
			if (processTerm3 instanceof ActionProcess)
				return computeLeaf(processTerm3);
			if (processTerm3 instanceof ChoiceProcess) {
				// chiamiamo computLeaf su ogni termine di processo
				// alternativo
				ChoiceProcess choiceProcess = (ChoiceProcess) processTerm3;
				ProcessTerm[] processTerms = choiceProcess.getProcesses();
				for (ProcessTerm processTerm : processTerms) {
					List<BehavProcess> list2 = computeLeaf(processTerm);
					list.addAll(list2);
				}
			}
		}
		// se processTerm2e'ChoiceProcess chiamiamo computLeaf su ogni termine di
		// processo
		// alternativo
		if (processTerm2 instanceof ChoiceProcess) {
			ChoiceProcess choiceProcess = (ChoiceProcess) processTerm2;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm3 : processTerms) {
				List<BehavProcess> list2 = computeLeaf(processTerm3);
				list.addAll(list2);
			}
		}
		// se processTerm2e'BehavProcess, lo aggiungiamo a list
		if (processTerm2 instanceof BehavProcess)
			list.add((BehavProcess) processTerm2);
		return list;
	}

	public boolean isCompliantInstanceRules() throws RestrizioniIstanzeException {
		return false;
	}

	@Override
	public void print() {
		System.out.println("ElemTypeNorm object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ElemTypeNorm))
			return false;
		ElemTypeNorm elemTypeNorm = (ElemTypeNorm) obj;
		if (!getAEIdecl().equals(elemTypeNorm.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNorm.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNorm.getOldElemType()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String string = new String();
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
	}

	@Override
	public ElemTypeNorm copy() {
		ElemTypeNorm elemTypeNorm = new ElemTypeNorm();
		elemTypeNorm.setAEIdecl(getAEIdecl().copy());
		elemTypeNorm.setNewElemType(getNewElemType().copy());
		elemTypeNorm.setOldElemType(getOldElemType().copy());
		return elemTypeNorm;
	}

	/**
	 * Le azioni in cui un identificatore di tipo azione si presenta devono essere
	 * tutte temporizzate esponenzialmente, immediate con la stessa priorita',
	 * passive con la stessa priorita'.
	 * 
	 * @return
	 */
	public boolean restrizioneIstanze16() throws RestrizioniIstanzeException {
		ElemType elemType = getNewElemType();
		AETbehavior tbehavior = elemType.getBehavior();
		List<Action> list = Find.getActionsFromAETBehavior(tbehavior);
		for (int i = 0; i < list.size(); i++) {
			Action action = list.get(i);
			ActionType actionType = action.getType();
			String string = actionType.getName();
			ActionRate actionRate = action.getRate();
			for (int j = i + 1; j < list.size(); j++) {
				Action action2 = list.get(j);
				ActionType actionType2 = action2.getType();
				String string2 = actionType2.getName();
				ActionRate actionRate2 = action2.getRate();
				if (string.equals(string2)) {
					if (actionRate instanceof RateExp) {
						if (!(actionRate2 instanceof RateExp)) {
							String string3 = "Instances restrictions error for " + this.idecl.toString();
							this.errorMessage.setErrorMessage(string3);
							List<ErrorMessage> list2 = this.errorMessage.getCauses();
							String string4 = action.toString() + " has exponential rate but " + action2.toString()
									+ " has not exponential rate";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string4);
							list2.add(errorMessage);
							return false;
						}
					}
					if (actionRate instanceof RateInf) {
						RateInf rateInf = (RateInf) actionRate;
						Expression expression = rateInf.getPrio();
						if (!(actionRate2 instanceof RateInf)) {
							String string3 = "Instances restrictions error for " + this.idecl.toString();
							this.errorMessage.setErrorMessage(string3);
							List<ErrorMessage> list2 = this.errorMessage.getCauses();
							String string4 = action.toString() + " has immediate rate but " + action2.toString()
									+ " has not immediate rate";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string4);
							list2.add(errorMessage);
							return false;
						}
						RateInf rateInf2 = (RateInf) actionRate2;
						Expression expression2 = rateInf2.getPrio();
						if (!expression.equals(expression2)) {
							String string3 = "Instances restrictions error for " + this.idecl.toString();
							this.errorMessage.setErrorMessage(string3);
							List<ErrorMessage> list2 = this.errorMessage.getCauses();
							String string4 = action.toString() + " and " + action2.toString()
									+ " has not same priority";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string4);
							list2.add(errorMessage);
							return false;
						}
					}
					if (actionRate instanceof Rate_) {
						Rate_ rate_ = (Rate_) actionRate;
						Expression expression = rate_.getPrio();
						if (!(actionRate2 instanceof Rate_)) {
							String string3 = "Instances restrictions error for " + this.idecl.toString();
							this.errorMessage.setErrorMessage(string3);
							List<ErrorMessage> list2 = this.errorMessage.getCauses();
							String string4 = action.toString() + " has passive rate but " + action2.toString()
									+ " has not passive rate";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string4);
							list2.add(errorMessage);
							return false;
						}
						Rate_ rate_2 = (Rate_) actionRate2;
						Expression expression2 = rate_2.getPrio();
						if (!expression.equals(expression2)) {
							String string3 = "Instances restrictions error for " + this.idecl.toString();
							this.errorMessage.setErrorMessage(string3);
							List<ErrorMessage> list2 = this.errorMessage.getCauses();
							String string4 = action.toString() + " and " + action2.toString()
									+ " has not same priority";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string4);
							list2.add(errorMessage);
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
