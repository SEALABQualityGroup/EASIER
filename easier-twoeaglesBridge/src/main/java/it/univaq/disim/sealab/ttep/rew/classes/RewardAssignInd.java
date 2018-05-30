/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.classes;

import specificheAEmilia.Expression;

/**
 * @author Mirko
 *
 */
public class RewardAssignInd extends RewardAssign {

	private String index;
	private Expression beginningExp;
	private Expression endingExp;
	
	public RewardAssignInd(String actionType, Expression expression,
			String aei, String index, Expression beginningExp,
			Expression endingExp, RewardType rewardType, Expression reward) {
		super(actionType, expression, aei, rewardType, reward);
		this.index = index;
		this.beginningExp = beginningExp;
		this.endingExp = endingExp;
	}
	public RewardAssignInd(String actionType, String aei, String index,
			Expression beginningExp, Expression endingExp, RewardType rewardType, Expression reward) {
		super(actionType, aei, rewardType, reward);
		this.index = index;
		this.beginningExp = beginningExp;
		this.endingExp = endingExp;
	}
	public String getIndex() {
		return index;
	}
	public Expression getBeginningExp() {
		return beginningExp;
	}
	public Expression getEndingExp() {
		return endingExp;
	}
	@Override
	public String toString() 
		{
		String string = "FOR_ALL " + this.index + " IN " + this.beginningExp.toString() + ".." + this.endingExp.toString();
		string = string + super.toString();
		return string;
		}
	
	@Override
	public RewardAssignInd copy() 
		{
		Expression beginningExp = this.beginningExp.copy();
		Expression endingExp = this.endingExp.copy();
		String index = new String(this.index);
		String actionType = new String(this.getActionType());
		String aei = new String(this.getAei());
		Expression selector = null;
		if (this.getSelector() != null)
			selector = this.getSelector().copy();
		RewardType rewardType = this.getRewardType();
		Expression reward = this.getReward();
		RewardAssignInd rewardAssignInd = new RewardAssignInd(actionType, selector, aei, index, beginningExp, endingExp, rewardType, reward);
		return rewardAssignInd;
		}
	}
