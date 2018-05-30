/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.classes;

import specificheAEmilia.Expression;

/**
 * @author Mirko
 *
 */
public class MeasureDef implements RewBase {

	private String identifier;
	private Expression selector;
	private RewardStructure rewardStructure;
	
	public MeasureDef(String identifier, RewardStructure rewardStructure) {
		super();
		this.identifier = identifier;
		this.rewardStructure = rewardStructure;
	}

	public MeasureDef(String identifier, Expression expression,
			RewardStructure rewardStructure) {
		super();
		this.identifier = identifier;
		this.selector = expression;
		this.rewardStructure = rewardStructure;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Expression getSelector() {
		return selector;
	}

	public RewardStructure getRewardStructure() {
		return rewardStructure;
	}

	@Override
	public String toString() 
		{
		String string = "MEASURE ";
		string = string + this.identifier;
		if (this.selector != null)
			{
			string = string + "[" + this.selector.toString() + "]";
			}
		string = string + " IS " + this.rewardStructure.toString();
		return string;
		}

	@Override
	public MeasureDef copy() 
		{
		Expression expression = null;
		if (this.selector != null)
			expression = this.selector.copy();
		String identifier = new String(this.identifier);
		RewardStructure rewardStructure = this.rewardStructure.copy();
		MeasureDef measureDef = new MeasureDef(identifier, expression, rewardStructure);
		return measureDef;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof MeasureDef))
			return false;
		if (obj instanceof MeasureDefInd)
			return false;
		MeasureDef measureDef = (MeasureDef)obj;
		String identifier = measureDef.getIdentifier();
		Expression expression = measureDef.getSelector();
		if (this.identifier.equals(identifier))
			{
			if (this.selector == null && expression == null)
				return true;
			else if (this.selector == null && expression != null)
				return false;
			else if (this.selector != null && expression == null)
				return false;
			else 
				{
				if (this.selector.equals(expression))
					return true;
				else
					return false;
				}
			}
		else
			return false;
		}
	}
