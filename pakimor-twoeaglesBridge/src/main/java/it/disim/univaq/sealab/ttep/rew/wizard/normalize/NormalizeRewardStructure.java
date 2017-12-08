/**
 * 
 */
package it.disim.univaq.sealab.ttep.rew.wizard.normalize;

import it.disim.univaq.sealab.ttep.rew.classes.RewardAssign;
import it.disim.univaq.sealab.ttep.rew.classes.RewardAssignInd;
import it.disim.univaq.sealab.ttep.rew.classes.RewardStructure;
import it.disim.univaq.sealab.ttep.rew.wizard.normalize.structure.RewardAssignNorm;
import it.disim.univaq.sealab.ttep.rew.wizard.normalize.structure.RewardStructureNorm;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Jimmy
 *
 */
public class NormalizeRewardStructure {

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

	public NormalizeRewardStructure(int depth) 
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

	public RewardStructureNorm normalizeRewardStructure(
			RewardStructure rewardStructure,
			TreeMap<String, ValueIdentExpr> treeMap) throws NormalizeException
		{
		try {
			List<RewardAssign> al = new ArrayList<RewardAssign>();
			// si prelevano i reward assignment
			List<RewardAssign> rewardAssigns = rewardStructure.getRewardAssigns();
			// si normalizzano opportunamente tutte le dichiarazioni
			for (int i = 0; i < rewardAssigns.size(); i++) 
				{
				if (rewardAssigns.get(i) instanceof RewardAssignInd) 
					{
					RewardAssignInd rewardAssignInd = (RewardAssignInd)rewardAssigns.get(i);
					NormalizeRewardAssignInd normalizeRewardAssignInd = new NormalizeRewardAssignInd(this.depth + 1);
					List<RewardAssign> rewardAssigns2 = normalizeRewardAssignInd.normalizeRewardAssignInd(rewardAssignInd,treeMap);
					if (normalizeRewardAssignInd.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + rewardStructure.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeRewardAssignInd.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					al.addAll(rewardAssigns2);
					} 
				else 
					{
					NormalizeRewardAssign normalizeRewardAssign = new NormalizeRewardAssign(this.depth + 1);
					RewardAssignNorm rewardAssignNorm = normalizeRewardAssign.normalizeRewardAssign(rewardAssigns.get(i),treeMap);
					if (normalizeRewardAssign.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + rewardStructure.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeRewardAssign.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					RewardAssign rewardAssign = rewardAssignNorm.getNewRewardAssign();
					al.add(rewardAssign);
					}
				}
			// si assegnano i nuoi reward assignment
			RewardStructure rewardStructure2 = new RewardStructure(al);
			RewardStructureNorm rewardStructureNorm = new RewardStructureNorm();
			rewardStructureNorm.setNewRewardStructure(rewardStructure2);
			rewardStructureNorm.setOldRewardStructure(rewardStructure);
			return rewardStructureNorm;
			} 
		catch (Exception e) 
			{
			throw new NormalizeException(e);
			}
		}
	}
