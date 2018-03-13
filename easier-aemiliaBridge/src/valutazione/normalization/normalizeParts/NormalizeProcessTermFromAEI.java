/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.structure.ProcessTermNorm;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.Action;
import specificheAEmilia.ActionOutput;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.ElemType;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateExp;
import specificheAEmilia.RateInf;
import specificheAEmilia.Rate_;
import specificheAEmilia.Real;
import utilities.ErrorMessage;
import valutazione.Find;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpression.NormalizeExpression;
import valutazione.scope.ScopeArchiType;
import valutazione.scope.ScopeProcessTermFromAEI;
import valutazione.typeChecking.BehavProcessChecking;

/**
 * @author Mirko
 *
 */
public class NormalizeProcessTermFromAEI {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

	public NormalizeProcessTermFromAEI(ScopeArchiType scopeArchiType, int depth) {
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * Normalizza un termine di processo di un tipo di elemento architetturale, a
	 * partire dalla dichiarazione di un'istanza dell'elemento e dall'equazione
	 * comportamentale, a cui il termine di processo appartiene. Oltre
	 * all'intestazione, viene normalizzato anche il termine di processo. Se bee'la
	 * prima equazione comportamentale dell'elemento architetturale nello scope di
	 * valutazione vengono inclusi anche le variabili inizializzate
	 * dell'intestazione di be, che pero' non possono essere valutati perche'
	 * dinamici.
	 *
	 * @param aeid
	 * @return il termine di processo normalizzato.
	 * @throws NormalizeException
	 */
	public ProcessTermNorm normalizeProcessTermFromAEI(AEIdecl aeid, BehavEquation be, ElemType elemType)
			throws NormalizeException {
		try {
			AEIdecl clone = aeid.copy();
			BehavEquation clonebe = be.copy();
			// si preleva lo scope per la valutazione del termine
			// di processo
			ScopeProcessTermFromAEI scopeProcessTermFromAEI = new ScopeProcessTermFromAEI(this.scopeArchiType,
					this.depth + 1);
			TreeMap<String, ValueIdentExpr> sco = scopeProcessTermFromAEI.getScopeProcessTermFromAEI(clone, clonebe);
			if (scopeProcessTermFromAEI.isErrorOccurred()) {
				String string = "Normalizing error for " + aeid;
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scopeProcessTermFromAEI.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			// si normalizza l'eventuale condizione del termine
			// di processo
			ProcessTerm pt = clonebe.getTermineProcesso();
			ProcessTermNorm processTermNorm1 = new ProcessTermNorm();
			processTermNorm1.setOldProcessTerm(be.getTermineProcesso());
			processTermNorm1.setNewProcessTerm(pt);
			processTermNorm1.setAEIdecl(aeid);
			processTermNorm1.setBehavEquation(be);
			if (pt.getCondition() != null) {
				NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
				ExpressionNorm expression = normalizeExpression.normalize(pt.getCondition(), "", null, sco);
				if (normalizeExpression.isErrorOccurred()) {
					String string = "Normalizing error for " + aeid;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
				}
				Expression expression2 = expression.getNewExpression();
				clonebe.getTermineProcesso().setCondition(expression2);
			}
			if (pt instanceof ActionProcess) {
				// si normalizza l'azione del processo
				Action a = ((ActionProcess) pt).getAzione();
				ActionType at = a.getType();
				// si normalizzano le eventuali espressioni di output
				if (at instanceof ActionOutput) {
					NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
					for (int i = 0; i < ((ActionOutput) at).getOutputExprs().length; i++) {
						ExpressionNorm expression = normalizeExpression
								.normalize(((ActionOutput) at).getOutputExprs()[i], "", null, sco);
						if (normalizeExpression.isErrorOccurred()) {
							String string = "Normalizing error for " + aeid;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
						}
						Expression expression2 = expression.getNewExpression();
						((ActionOutput) at).getOutputExprs()[i] = expression2;
					}
				}
				ActionRate ar = a.getRate();
				if (ar instanceof RateExp) {
					RateExp rateExp = (RateExp) ar;
					NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
					ExpressionNorm expression = normalizeExpression.normalize(rateExp.getExpr(), "", null, sco);
					if (normalizeExpression.isErrorOccurred()) {
						String string = "Normalizing error for " + aeid;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
					}
					Expression expression2 = expression.getNewExpression();
					rateExp.setExpr(expression2);
				}
				if (ar instanceof Rate_) {
					Rate_ rate_ = (Rate_) ar;
					NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
					if (rate_.getWeight() == null)
						rate_.setWeight(new Real());
					if(rate_.getPrio() == null)
						rate_.setPrio(new specificheAEmilia.Integer(0));	
					ExpressionNorm expression = normalizeExpression.normalize(rate_.getWeight(), "", null, sco);
					if (normalizeExpression.isErrorOccurred()) {
						String string = "Normalizing error for " + aeid;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
					}
					Expression expression21 = expression.getNewExpression();
					rate_.setWeight(expression21);
					NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
					ExpressionNorm expression2 = normalizeExpression2.normalize(rate_.getPrio(), "", null, sco);
					if (normalizeExpression2.isErrorOccurred()) {
						String string = "Normalizing error for " + aeid;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
					}
					Expression expression3 = expression2.getNewExpression();
					rate_.setPrio(expression3);
				}
				if (ar instanceof RateInf) {
					RateInf rateInf = (RateInf) ar;
					NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
					ExpressionNorm expression;
					if (rateInf.getWeight() == null)
						rateInf.setWeight(new Real());
					if(rateInf.getPrio() == null)
						rateInf.setPrio(new specificheAEmilia.Integer());	
					expression = normalizeExpression.normalize(rateInf.getWeight(), "", null, sco);
					if (normalizeExpression.isErrorOccurred()) {
						String string = "Normalizing error for " + aeid;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
					}
					Expression expression21 = expression.getNewExpression();
					rateInf.setWeight(expression21);
					NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
					ExpressionNorm expression2 = normalizeExpression2.normalize(rateInf.getPrio(), "", null, sco);
					if (normalizeExpression2.isErrorOccurred()) {
						String string = "Normalizing error for " + aeid;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
					}
					Expression expression3 = expression2.getNewExpression();
					rateInf.setPrio(expression3);
				}
				NormalizePartialProcessTerm normalizePartialProcessTerm = new NormalizePartialProcessTerm(
						this.depth + 1);
				ProcessTermNorm processTermNorm = normalizePartialProcessTerm
						.normalizePartialProcessTerm(((ActionProcess) pt).getProcesso(), elemType, sco);
				if (normalizePartialProcessTerm.isErrorOccurred()) {
					String string = "Normalizing error for " + aeid;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizePartialProcessTerm.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
				}
				ProcessTerm processTerm = processTermNorm.getNewProcessTerm();
				((ActionProcess) pt).setProcesso(processTerm);
			}
			if (pt instanceof BehavProcess) {
				BehavProcess behavProcess = (BehavProcess) pt;
				// si normalizzano i parametri attuali della chiamata
				// di comportamento
				if (behavProcess.getExprs() != null) {
					NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
					for (int i = 0; i < behavProcess.getExprs().length; i++) {
						ExpressionNorm expression = normalizeExpression.normalize(behavProcess.getExprs()[i], "", null,
								sco);
						if (normalizeExpression.isErrorOccurred()) {
							String string = "Normalizing error for " + aeid;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
						}
						Expression expression2 = expression.getNewExpression();
						behavProcess.getExprs()[i] = expression2;
					}
				}
				BehavEquation behavEquation = Find.getBehavEquationFromBehavProcess(elemType, behavProcess);
				Header header = behavEquation.getBehavHeader();
				BehavProcessChecking behavProcessChecking = new BehavProcessChecking(behavProcess, sco, header,
						this.depth + 1);
				if (!behavProcessChecking.checkParameters()) {
					String string = "Normalizing error for " + aeid;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = behavProcessChecking.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
				}
			}
			if (pt instanceof ChoiceProcess) {
				// si normalizza ogni processo alternativo
				for (int i = 0; i < ((ChoiceProcess) pt).getProcesses().length; i++) {
					NormalizePartialProcessTerm normalizePartialProcessTerm = new NormalizePartialProcessTerm(
							this.depth + 1);
					ProcessTermNorm processTermNorm = normalizePartialProcessTerm
							.normalizePartialProcessTerm(((ChoiceProcess) pt).getProcesses()[i], elemType, sco);
					if (normalizePartialProcessTerm.isErrorOccurred()) {
						String string = "Normalizing error for " + aeid;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizePartialProcessTerm.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
					}
					ProcessTerm processTerm = processTermNorm.getNewProcessTerm();
					((ChoiceProcess) pt).getProcesses()[i] = processTerm;
				}
			}
			return processTermNorm1;
		} catch (Exception e) {
			throw new NormalizeException(e);
		}
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
