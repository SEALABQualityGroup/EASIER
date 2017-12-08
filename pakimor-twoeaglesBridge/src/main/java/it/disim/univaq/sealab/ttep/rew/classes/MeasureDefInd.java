/**
 * 
 */
package it.disim.univaq.sealab.ttep.rew.classes;

import specificheAEmilia.Expression;

/**
 * @author Mirko
 *
 */
public class MeasureDefInd extends MeasureDef {

	private String index;
	private Expression beginningExp;
	private Expression endingExp;
	
	public MeasureDefInd(String identifier, RewardStructure rewardStructure,
			String index, Expression beginningExp, Expression endingExp) {
		super(identifier, rewardStructure);
		this.index = index;
		this.beginningExp = beginningExp;
		this.endingExp = endingExp;
	}
	
	public MeasureDefInd(String identifier, Expression expression,
			RewardStructure rewardStructure, String index,
			Expression beginningExp, Expression endingExp) {
		super(identifier, expression, rewardStructure);
		this.index = index;
		this.beginningExp = beginningExp;
		this.endingExp = endingExp;
	}
;
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
		String string = "FOR_ALL ";
		string = string + this.index + " IN "+this.beginningExp.toString()+".."+this.endingExp.toString();
		string = string + super.toString();
		return string;
		}

	@Override
	public MeasureDefInd copy() 
		{
		Expression beginningExp = this.beginningExp.copy();
		Expression endingExp = this.endingExp.copy();
		String index = new String(this.index);
		Expression expression = null;
		if (this.getSelector() != null)
			expression = this.getSelector().copy();
		String identifier = new String(this.getIdentifier());
		RewardStructure rewardStructure = this.getRewardStructure().copy();
		MeasureDefInd measureDefInd = new MeasureDefInd(identifier, expression, rewardStructure, index, beginningExp, endingExp);
		return measureDefInd;
		}
	}
