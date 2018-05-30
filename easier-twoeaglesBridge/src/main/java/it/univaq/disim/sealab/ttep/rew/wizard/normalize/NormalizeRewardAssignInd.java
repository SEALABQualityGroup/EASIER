/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.wizard.normalize;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import it.univaq.disim.sealab.ttep.rew.classes.RewardAssign;
import it.univaq.disim.sealab.ttep.rew.classes.RewardAssignInd;
import it.univaq.disim.sealab.ttep.rew.wizard.normalize.checking.CheckInteger;
import it.univaq.disim.sealab.ttep.rew.wizard.normalize.structure.RewardAssignNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;
import valutazione.typeChecking.TypeCheckingException;

/**
 * @author Mirko
 *
 */
public class NormalizeRewardAssignInd 
	{

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;	
	
	public NormalizeRewardAssignInd(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public boolean isErrorOccurred() 
		{
		return errorOccurred;
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	public List<RewardAssign> normalizeRewardAssignInd(
			RewardAssignInd rewardAssignInd,
			TreeMap<String, ValueIdentExpr> treeMap) throws NormalizeException
		{
		// normalizzo l'espressione di inizio
		Expression expression = rewardAssignInd.getBeginningExp();
		NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
		ExpressionNorm expressionNorm = normalizeExpressionStrict.normalize(expression, "", null, treeMap);
		if (normalizeExpressionStrict.isErrorOccurred())
			{
			String string = "Normalizing error for " + rewardAssignInd.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
			list.add(errorMessage);
			this.errorOccurred = true;
			return null;
			}
		Expression expression2 = expressionNorm.getNewExpression();
		// normalizzo l'espressione di fine
		Expression expression3 = rewardAssignInd.getEndingExp();
		NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
		ExpressionNorm expressionNorm2 = normalizeExpressionStrict2.normalize(expression3, "", null, treeMap);
		if (normalizeExpressionStrict2.isErrorOccurred())
			{
			String string = "Normalizing error for " + rewardAssignInd.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
			list.add(errorMessage);
			this.errorOccurred = true;
			return null;
			}
		Expression expression4 = expressionNorm2.getNewExpression();
		// effettuo il type checking sull'espressione iniziale e finale
		CheckInteger checkInteger = new CheckInteger(expression2,treeMap,this.depth + 1);
		try {
			if (!checkInteger.checkInteger()) 
				{
				String string = "Normalizing error for " + rewardAssignInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkInteger.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
		catch (TypeCheckingException e) 
			{
			throw new NormalizeException(e);
			}
		CheckInteger checkInteger2 = new CheckInteger(expression4,treeMap,this.depth + 1); 
		try {
			if (!checkInteger2.checkInteger()) 
				{
				String string = "Normalizing error for " + rewardAssignInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkInteger2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
		catch (TypeCheckingException e) 
			{
			throw new NormalizeException(e);
			}
		it.univaq.disim.sealab.ttep.rew.wizard.normalize.checking.CheckRange checkRange = new it.univaq.disim.sealab.ttep.rew.wizard.normalize.checking.CheckRange(expression2,expression4,this.depth + 1);
		try {
			if (!checkRange.checkRange()) 
				{
				String string = "Normalizing error for " + rewardAssignInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRange.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
		catch (TypeCheckingException e) 
			{
			throw new NormalizeException(e);
			}			
		// per ogni intero tra l'espressione finale e quella iniziale genero un'assegnamento di misura
		// si preleva l'espressione iniziale come un intero
		int indiceIniziale = ((Integer)expression2).getValore();
		// si preleva l'espressione finale come un intero
		int indiceFinale = ((Integer)expression4).getValore();
		// si alloca memoria per l'array risultato
		List<RewardAssign> rewardAssigns = new ArrayList<RewardAssign>();
		for (int i = 0; i <= indiceFinale - indiceIniziale; i++) 
			{
			// si aggiorna l'indice
			treeMap.put(rewardAssignInd.getIndex(), new ValueIdentExpr(new Integer(i
					+ indiceIniziale), true, new IntegerType()));
			// si normalizza la dichiarazione secondo il nuovo valore
			// dell'indice
			NormalizeRewardAssign normalizeRewardAssign = new NormalizeRewardAssign(this.depth + 1);
			RewardAssignNorm rewardAssignNorm = normalizeRewardAssign.normalizeRewardAssign(rewardAssignInd, treeMap);
			if (normalizeRewardAssign.isErrorOccurred()) 
				{
				String string = "Normalizing error for " + rewardAssignInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRewardAssign.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			RewardAssign rewardAssign = rewardAssignNorm.getNewRewardAssign();
			rewardAssigns.add(rewardAssign);
			}
		return rewardAssigns;
		}
	}
