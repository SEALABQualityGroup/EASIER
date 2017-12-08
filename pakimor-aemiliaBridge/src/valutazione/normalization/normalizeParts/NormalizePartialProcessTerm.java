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
import valutazione.typeChecking.BehavProcessChecking;

/**
 * @author Mirko
 *
 */
public class NormalizePartialProcessTerm {

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizePartialProcessTerm(int depth) {
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * Normalizza una parte del termine di processo di
	 * un'equazione comportamentale, secondo lo scope passato
	 * come parametro.
	 *
	 * @param pt
	 * @param elemType
	 * @param sco
	 * @return il termine di processo normalizzato.
	 * @throws NormalizeException
	 */
	ProcessTermNorm normalizePartialProcessTerm(ProcessTerm pt, ElemType elemType, TreeMap<String,ValueIdentExpr> sco)
			throws NormalizeException {
			try {
				ProcessTerm clone = pt.copy();
				// si normalizza l'eventuale condizione del termine
				// di processo
				if (clone.getCondition() != null) 
					{
					NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
					ExpressionNorm expression = normalizeExpression.normalize(
							clone.getCondition(), "", null, sco);
					if (normalizeExpression.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + pt;
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
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						for (int i = 0; i < ((ActionOutput) at)
								.getOutputExprs().length; i++) 
							{
							ExpressionNorm expression = normalizeExpression
									.normalize(((ActionOutput) at)
											.getOutputExprs()[i], "", null, sco);
							if (normalizeExpression.isErrorOccurred()) 
								{
								String string = "Normalizing error for " + pt;
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
						RateExp rateExp = (RateExp) ar;
						NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expression = normalizeExpression2
								.normalize(rateExp.getExpr(), "", null, sco);
						if (normalizeExpression2.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + pt;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						rateExp.setExpr(expression2);
						}
					if (ar instanceof Rate_) 
						{
						Rate_ rate_ = (Rate_) ar;
						NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
						if (rate_.getWeight() == null)
							rate_.setWeight(new Real());
						if(rate_.getPrio() == null)
							rate_.setPrio(new specificheAEmilia.Integer(1));	
						ExpressionNorm expression = normalizeExpression2
								.normalize(rate_.getWeight(), "", null, sco);
						if (normalizeExpression2.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + pt;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression21 = expression.getNewExpression();
						rate_.setWeight(expression21);
						NormalizeExpression normalizeExpression3 = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expression2 = normalizeExpression3
								.normalize(rate_.getPrio(), "", null, sco);
						if (normalizeExpression3.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + pt;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression3.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression3 = expression2.getNewExpression();
						rate_.setPrio(expression3);
						}
					if (ar instanceof RateInf) 
						{
						RateInf rateInf = (RateInf) ar;
						NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
						if (rateInf.getWeight() == null)
							rateInf.setWeight(new Real());
						if(rateInf.getPrio() == null)
							rateInf.setPrio(new specificheAEmilia.Integer(1));	
						ExpressionNorm expression = normalizeExpression2
								.normalize(rateInf.getWeight(), "", null, sco);
						if (normalizeExpression2.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + pt;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression21 = expression.getNewExpression();
						rateInf.setWeight(expression21);
						NormalizeExpression normalizeExpression3 = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expression2 = normalizeExpression3
								.normalize(rateInf.getPrio(), "", null, sco);
						if (normalizeExpression3.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + pt;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression3.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression3 = expression2.getNewExpression();
						rateInf.setPrio(expression3);
						}
					NormalizePartialProcessTerm normalizePartialProcessTerm = new NormalizePartialProcessTerm(this.depth + 1);
					ProcessTermNorm processTermNorm = normalizePartialProcessTerm.normalizePartialProcessTerm(
							((ActionProcess) clone).getProcesso(), elemType,
							sco);
					if (normalizePartialProcessTerm.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + pt;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizePartialProcessTerm.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					ProcessTerm processTerm = processTermNorm
							.getNewProcessTerm();
					((ActionProcess) clone).setProcesso(processTerm);
					}
				if (clone instanceof BehavProcess) 
					{
					BehavProcess behavProcess = (BehavProcess) clone;
					// si normalizzano i parametri attuali della chiamata
					// di comportamento
					if (behavProcess.getExprs() != null) 
						{
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						for (int i = 0; i < ((BehavProcess) clone).getExprs().length; i++) 
							{
							ExpressionNorm expression = normalizeExpression
									.normalize(behavProcess.getExprs()[i], "",
											null, sco);
							if (normalizeExpression.isErrorOccurred()) 
								{
								String string = "Normalizing error for " + pt;
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								}
							Expression expression2 = expression
									.getNewExpression();
							behavProcess.getExprs()[i] = expression2;
							}
						}
					BehavEquation behavEquation = Find
							.getBehavEquationFromBehavProcess(elemType,
									behavProcess);
					Header header = behavEquation.getBehavHeader();
					BehavProcessChecking behavProcessChecking = new BehavProcessChecking(
							behavProcess, sco, header, this.depth + 1);
					if (!behavProcessChecking.checkParameters()) 
						{
						String string = "Normalizing error for " + pt;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = behavProcessChecking.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					}
				if (clone instanceof ChoiceProcess) 
					{
					// si normalizza ogni processo alternativo
					for (int i = 0; i < ((ChoiceProcess) clone).getProcesses().length; i++) 
						{
						NormalizePartialProcessTerm normalizePartialProcessTerm = new NormalizePartialProcessTerm(this.depth + 1);
						ProcessTermNorm processTermNorm = normalizePartialProcessTerm.normalizePartialProcessTerm(
								((ChoiceProcess) clone).getProcesses()[i],
								elemType, sco);
						if (normalizePartialProcessTerm.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + pt;
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
				processTermNorm.setOldProcessTerm(pt);
				processTermNorm.setNewProcessTerm(clone);
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
