/**
 * 
 */
package it.disim.univaq.sealab.ttep.rew.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirko
 *
 */
public class RewardStructure implements RewBase {

	private List<RewardAssign> rewardAssigns;

	public RewardStructure(List<RewardAssign> rewardAssigns) {
		super();
		this.rewardAssigns = rewardAssigns;
	}

	public List<RewardAssign> getRewardAssigns() {
		return rewardAssigns;
	}

	@Override
	public String toString() 
		{
		String string = new String();
		for (RewardAssign rewardAssign : this.rewardAssigns)
			{
			string = string + rewardAssign.toString();
			}
		return string;
		}

	@Override
	public RewardStructure copy() 
		{
		List<RewardAssign> rewardAssigns = new ArrayList<RewardAssign>();
		for (RewardAssign rewardAssign : this.rewardAssigns)
			{
			RewardAssign rewardAssign2 = rewardAssign.copy();
			rewardAssigns.add(rewardAssign2);
			}
		RewardStructure rewardStructure = new RewardStructure(rewardAssigns);
		return rewardStructure;
		}
	}
