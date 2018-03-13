/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.structure.ProcessTermNorm;
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
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateExp;
import specificheAEmilia.RateInf;
import specificheAEmilia.Rate_;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpression.NormalizeExpression;
import valutazione.scope.ScopeArchiType;
import valutazione.scope.ScopeProcessTerm;

/**
 * @author Mirko
 *
 */
public class NormalizeProcessTerm {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeProcessTerm(ScopeArchiType scopeArchiType, int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * @return il termine di processo normalizzato.
	 * @throws NormalizeException
	 */
	public ProcessTermNorm normalizeProcessTerm(BehavEquation be, ElemType et)
			throws NormalizeException {
			try {
				ProcessTerm clone = be.getTermineProcesso().copy();
				// si preleva lo scope per la valutazione del termine
				// di processo
				ScopeProcessTerm scopeProcessTerm = new ScopeProcessTerm(this.depth + 1);
				TreeMap<String, ValueIdentExpr> sco = scopeProcessTerm.getScopeProcessTerm(be, et);
				if (scopeProcessTerm.isErrorOccurred())
					{
					String string = "Normalizing error for " + be;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = this.scopeArchiType.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				// si normalizza l'eventuale condizione del termine
				// di processo
				if (clone.getCondition() != null) 
					{
					NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
					ExpressionNorm expression = normalizeExpression.normalize(
							clone.getCondition(), "", null, sco);
					if (normalizeExpression.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + be;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					Expression expression2 = expression.getNewExpression();
					clone.setCondition(expression2);
					}
				if (clone instanceof ActionProcess) 
					{
					// si normalizza l'azione del processo
					Action a = ((ActionProcess) clone).getAzione();
					ActionType at = a.getType();
					// si normalizzano le eventuali espressioni di output
					if (at instanceof ActionOutput) 
						{
						for (int i = 0; i < ((ActionOutput) at)
								.getOutputExprs().length; i++) 
							{
							NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
							ExpressionNorm expression = normalizeExpression
									.normalize(((ActionOutput) at)
											.getOutputExprs()[i], "", null, sco);
							if (normalizeExpression.isErrorOccurred()) 
								{
								String string = "Normalizing error for " + be;
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								}
							Expression expression2 = expression
									.getNewExpression();
							((ActionOutput) at).getOutputExprs()[i] = expression2;
							}
						}
					ActionRate ar = a.getRate();
					if (ar instanceof RateExp) 
						{
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expression = normalizeExpression
								.normalize(((RateExp) ar).getExpr(), "", null,
										sco);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + be;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						((RateExp) ar).setExpr(expression2);
						}
					if (ar instanceof Rate_) 
						{
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expression = normalizeExpression
								.normalize(((Rate_) ar).getWeight(), "", null,
										sco);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + be;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression21 = expression.getNewExpression();
						((Rate_) ar).setWeight(expression21);
						NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expression2 = normalizeExpression2
								.normalize(((Rate_) ar).getPrio(), "", null,
										sco);
						if (normalizeExpression2.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + be;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression3 = expression2.getNewExpression();
						((Rate_) ar).setPrio(expression3);
						}
					if (ar instanceof RateInf) 
						{
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expression = normalizeExpression
								.normalize(((RateInf) ar).getWeight(), "",
										null, sco);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + be;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression21 = expression.getNewExpression();
						((RateInf) ar).setWeight(expression21);
						NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expression2 = normalizeExpression2
								.normalize(((RateInf) ar).getPrio(), "", null,
										sco);
						if (normalizeExpression2.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + be;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression3 = expression2.getNewExpression();
						((RateInf) ar).setPrio(expression3);
						}
					NormalizePartialProcessTerm normalizePartialProcessTerm = new NormalizePartialProcessTerm(this.depth + 1);
					ProcessTermNorm processTermNorm = normalizePartialProcessTerm.normalizePartialProcessTerm(
							((ActionProcess) clone).getProcesso(), et, sco);
					if (normalizePartialProcessTerm.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + be;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizePartialProcessTerm.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					((ActionProcess) clone).setProcesso(processTermNorm
							.getNewProcessTerm());
					}
				if (clone instanceof BehavProcess) 
					{
					// si normalizzano i parametri attuali della chiamata
					// di comportamento
					if (((BehavProcess) clone).getExprs() != null) 
						{
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						for (int i = 0; i < ((BehavProcess) clone).getExprs().length; i++) 
							{
							ExpressionNorm expression = normalizeExpression
									.normalize(((BehavProcess) clone)
											.getExprs()[i], "", null, sco);
							if (normalizeExpression.isErrorOccurred()) 
								{
								String string = "Normalizing error for " + be;
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								}
							Expression expression2 = expression
									.getNewExpression();
							((BehavProcess) clone).getExprs()[i] = expression2;
							}
						}
					}
				if (clone instanceof ChoiceProcess) 
					{
					// si normalizza ogni processo alternativo
					for (int i = 0; i < ((ChoiceProcess) clone).getProcesses().length; i++) 
						{
						NormalizePartialProcessTerm normalizePartialProcessTerm = new NormalizePartialProcessTerm(this.depth + 1);
						ProcessTermNorm processTermNorm = normalizePartialProcessTerm.normalizePartialProcessTerm(
								((ChoiceProcess) clone).getProcesses()[i], et,
								sco);
						if (normalizePartialProcessTerm.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + be;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizePartialProcessTerm.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						((ChoiceProcess) clone).getProcesses()[i] = processTermNorm
								.getNewProcessTerm();
						}
					}
				ProcessTermNorm processTermNorm = new ProcessTermNorm();
				processTermNorm.setOldProcessTerm(be.getTermineProcesso());
				processTermNorm.setNewProcessTerm(clone);
				processTermNorm.setBehavEquation(be);
				return processTermNorm;
				} 
			catch (Exception e) 
				{
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
