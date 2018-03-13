/**
 * 
 */
package it.disim.univaq.sealab.ttep.rew.wizard.normalize;

import it.disim.univaq.sealab.ttep.rew.classes.MeasureDef;
import it.disim.univaq.sealab.ttep.rew.classes.RewardStructure;
import it.disim.univaq.sealab.ttep.rew.wizard.normalize.structure.MeasureDefNorm;
import it.disim.univaq.sealab.ttep.rew.wizard.normalize.structure.RewardStructureNorm;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;

/**
 * @author Jimmy
 *
 */
public class NormalizeMeasureDef 
	{

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeMeasureDef(int depth) 
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



	public MeasureDefNorm normalizeMeasureDef(MeasureDef measureDef,
			TreeMap<String, ValueIdentExpr> treeMap) throws NormalizeException
		{
		try {
			// effettuiamo il type checking
			it.disim.univaq.sealab.ttep.rew.wizard.normalize.checking.CheckSelector checkSelector = new it.disim.univaq.sealab.ttep.rew.wizard.normalize.checking.CheckSelector(measureDef, treeMap, this.depth + 1);
			if (!checkSelector.checkSelector()) 
				{
				String string = "Normalizing error for " + measureDef.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkSelector.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			// Si normalizza l'eventuale espressione di selezione
			Expression expression2 = null;
			if (measureDef.getSelector() != null) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression = normalizeExpressionStrict.normalize(measureDef.getSelector(), "", null, treeMap);
				if (normalizeExpressionStrict.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + measureDef.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				expression2 = expression.getNewExpression();
				}
			// si normalizza la reward structure 
			RewardStructure rewardStructure = measureDef.getRewardStructure();
			NormalizeRewardStructure normalizeRewardStructure = new NormalizeRewardStructure(this.depth + 1);
			RewardStructureNorm rewardStructureNorm = normalizeRewardStructure.normalizeRewardStructure(rewardStructure,treeMap);
			if (normalizeRewardStructure.isErrorOccurred())
				{
				String string = "Normalizing error for " + measureDef.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRewardStructure.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			RewardStructure rewardStructure2 = rewardStructureNorm.getNewRewardStructure();
			// si costruisce una dichiarazione semplice
			String identifier = measureDef.getIdentifier();
			MeasureDef measureDef2 = new MeasureDef(identifier, expression2, rewardStructure2);
			MeasureDefNorm measureDefNorm = new MeasureDefNorm();
			measureDefNorm.setOldMeasureDef(measureDef);
			measureDefNorm.setNewMeasureDef(measureDef2);
			return measureDefNorm;
			} 
		catch (Exception e) 
			{
			throw new NormalizeException(e);
			}
		}
	}
