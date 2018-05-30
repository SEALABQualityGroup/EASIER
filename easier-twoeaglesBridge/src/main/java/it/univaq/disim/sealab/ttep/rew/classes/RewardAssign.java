/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.classes;

import specificheAEmilia.Expression;

/**
 * @author Mirko
 *
 */
public class RewardAssign implements RewBase {

	private String actionType;
	private Expression selector;
	private String aei;
	private RewardType rewardType;
	private Expression reward;
	
	public RewardAssign(String actionType, String aei, RewardType rewardType, Expression reward) 
		{
		super();
		this.actionType = actionType;
		this.aei = aei;
		this.rewardType = rewardType;
		this.reward = reward;
		}

	public RewardAssign(String actionType, Expression selector, String aei, RewardType rewardType, Expression reward) 
		{
		super();
		this.actionType = actionType;
		this.selector = selector;
		this.aei = aei;
		this.rewardType = rewardType;
		this.reward = reward;		
		}

	public String getActionType() {
		return actionType;
	}

	public Expression getSelector() {
		return selector;
	}

	public String getAei() {
		return aei;
	}
	
	public RewardType getRewardType() {
		return rewardType;
	}

	public Expression getReward() {
		return reward;
	}

	@Override
	public String toString() 
		{
		String string = "ENABLED (" + this.aei;
		if (this.selector != null)
			{
			string = string + "[" + this.selector.toString() + "]";
			}
		string = string + "." + this.actionType.toString() + ")";
		string = string + " -> " + this.rewardType.toString() + "(" + this.reward.toString() + ")";
		return string;
		}

	@Override
	public RewardAssign copy() 
		{
		String actionType = new String(this.actionType);
		String aei = new String(this.aei);
		Expression selector = null;
		if (this.selector != null)
			selector = this.selector.copy();
		RewardType rewardType = this.rewardType;
		Expression reward = this.reward.copy();
		RewardAssign rewardAssign = new RewardAssign(actionType, selector, aei,rewardType,reward);
		return rewardAssign;
		}
	}
