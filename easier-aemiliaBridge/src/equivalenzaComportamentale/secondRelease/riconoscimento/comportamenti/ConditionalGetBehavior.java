package equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti;


import specificheAEmilia.AETinteractions;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;

public class ConditionalGetBehavior 
	extends GetBehavior 
	{
	
	public ConditionalGetBehavior(
			VarInit[] varInits,
			ProcessTerm processTerm, 
			AETinteractions tinteractions
			) 
		{
		super(varInits,processTerm,tinteractions);
		}
	
	public ConditionalGetBehavior() 
		{
		super();
		}

	public boolean isConditionalGetBehavior()
		{
		if (!this.isGetBehavior())
			return false;
		return true;
		}
	
	// restituisce null se ConditionalGetBehavior none'un ConditionalGetBehavior
	public ProcessTerm getMaximalConditionalGetBehavior()
		{
		if (!this.isGetBehavior())
			return null;
		ProcessTerm processTerm = getMaximalGetBehavior();
		Expression expression = this.processTerm.getCondition();
		processTerm.setCondition(expression.copy());
		return processTerm;
		}

	// restituisce null se ConditionalGetBehavior none'un ConditionalGetBehavior
	public ProcessTerm getConditionalGetBehavior()
		{
		if (!this.isGetBehavior())
			return null;
		ProcessTerm processTerm = getGetBehavior();
		Expression expression = this.processTerm.getCondition();
		processTerm.setCondition(expression.copy());
		return processTerm;
		}
	
	@Override
	public Object copy() 
		{
		ConditionalGetBehavior conditionalGetBehavior = new ConditionalGetBehavior();
		// varInits setting
		VarInit[] varInitsCloned = this.varInits.clone();
		for (int i = 0; i < varInitsCloned.length; i++)
			{
			VarInit varInit = varInitsCloned[i];
			varInitsCloned[i] = varInit.copy();
			}
		conditionalGetBehavior.varInits = varInitsCloned;
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		conditionalGetBehavior.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		conditionalGetBehavior.tinteractions = tinteractionsCloned;
		return conditionalGetBehavior;
		}

	@Override
	public void print() 
		{
		System.out.println("ConditionalGetBehavior object");
		super.print();
		}
	}
