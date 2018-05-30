/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.wizard.normalize;

import java.util.List;
import java.util.TreeMap;

import it.univaq.disim.sealab.ttep.rew.classes.RewardAssign;
import it.univaq.disim.sealab.ttep.rew.classes.RewardType;
import it.univaq.disim.sealab.ttep.rew.wizard.normalize.structure.RewardAssignNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;

/**
 * @author Mirko
 *
 */
public class NormalizeRewardAssign {

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

	public NormalizeRewardAssign(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public RewardAssignNorm normalizeRewardAssign(
			RewardAssign rewardAssign,
			TreeMap<String, ValueIdentExpr> treeMap) throws NormalizeException 
		{
		try {
			// effettuiamo il type checking
			it.univaq.disim.sealab.ttep.rew.wizard.normalize.checking.CheckSelectorRA checkSelectorRA = new it.univaq.disim.sealab.ttep.rew.wizard.normalize.checking.CheckSelectorRA(rewardAssign, treeMap, this.depth + 1);
			if (!checkSelectorRA.checkSelector()) 
				{
				String string = "Normalizing error for " + rewardAssign.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkSelectorRA.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			// Si normalizza l'eventuale espressione di selezione
			Expression expression2 = null;
			if (rewardAssign.getSelector() != null) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression = normalizeExpressionStrict.normalize(rewardAssign.getSelector(), "", null, treeMap);
				if (normalizeExpressionStrict.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + rewardAssign.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				expression2 = expression.getNewExpression();
				}
			// si normalizza il reward
			Expression reward = rewardAssign.getReward();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expressionNorm = normalizeExpressionStrict.normalize(reward, "", null, treeMap);
			if (normalizeExpressionStrict.isErrorOccurred())
				{
				String string = "Normalizing error for " + reward.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			Expression expression = expressionNorm.getNewExpression();
			String actionType = rewardAssign.getActionType();
			String aei = rewardAssign.getAei();
			RewardType rewardType = rewardAssign.getRewardType();
			RewardAssign rewardAssign2 = new RewardAssign(actionType, expression2, aei, rewardType, expression);
			RewardAssignNorm rewardAssignNorm = new RewardAssignNorm();
			rewardAssignNorm.setNewRewardAssign(rewardAssign2);
			rewardAssignNorm.setOldRewardAssign(rewardAssign);
			return rewardAssignNorm;
			} 
		catch (Exception e) 
			{
			throw new NormalizeException(e);
			}
		}
	}
