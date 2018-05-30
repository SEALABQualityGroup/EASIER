/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.wizard.normalize.structure;

import it.univaq.disim.sealab.ttep.rew.classes.RewBase;
import it.univaq.disim.sealab.ttep.rew.classes.RewardAssign;

/**
 * @author Mirko
 *
 */
public class RewardAssignNorm 
	implements RewBase
	{

	private RewardAssign newRewardAssign;
	private RewardAssign oldRewardAssign;
	
	public RewardAssign getNewRewardAssign() {
		return newRewardAssign;
	}

	public void setNewRewardAssign(RewardAssign newRewardAssign) {
		this.newRewardAssign = newRewardAssign;
	}

	public RewardAssign getOldRewardAssign() {
		return oldRewardAssign;
	}

	public void setOldRewardAssign(RewardAssign oldRewardAssign) {
		this.oldRewardAssign = oldRewardAssign;
	}

	@Override
	public Object copy() 
		{
		RewardAssignNorm rewardAssignNorm = new RewardAssignNorm();
		rewardAssignNorm.setNewRewardAssign(this.newRewardAssign);
		rewardAssignNorm.setOldRewardAssign(this.oldRewardAssign);
		return rewardAssignNorm;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New Reward Assign: ";
		string += this.newRewardAssign;
		string += " Old Reward Assign: ";
		string += this.oldRewardAssign + " ";
		return string;
		}

	}
