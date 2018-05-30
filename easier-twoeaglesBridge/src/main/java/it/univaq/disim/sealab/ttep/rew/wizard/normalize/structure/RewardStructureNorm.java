/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.wizard.normalize.structure;

import it.univaq.disim.sealab.ttep.rew.classes.RewBase;
import it.univaq.disim.sealab.ttep.rew.classes.RewardStructure;

/**
 * @author Jimmy
 *
 */
public class RewardStructureNorm 
	implements RewBase
	{

	private RewardStructure oldRewardStructure;
	private RewardStructure newRewardStructure;
	
	@Override
	public RewardStructureNorm copy() 
		{
		RewardStructureNorm rewardStructureNorm = new RewardStructureNorm();
		rewardStructureNorm.setNewRewardStructure(this.newRewardStructure.copy());
		rewardStructureNorm.setOldRewardStructure(this.oldRewardStructure.copy());
		return rewardStructureNorm;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New Reward Structure: ";
		string += this.newRewardStructure;
		string += " Old Reward Structure: ";
		string += this.oldRewardStructure + " ";
		return string;
		}

	public RewardStructure getOldRewardStructure() {
		return oldRewardStructure;
	}

	public void setOldRewardStructure(RewardStructure oldRewardStructure) {
		this.oldRewardStructure = oldRewardStructure;
	}

	public RewardStructure getNewRewardStructure() {
		return newRewardStructure;
	}

	public void setNewRewardStructure(RewardStructure newRewardStructure) {
		this.newRewardStructure = newRewardStructure;
	}
	
	
	}
