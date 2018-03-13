package equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.Boolean;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;

public class UnconditionalGetBehavior 
	extends GetBehavior
	{
	
	public UnconditionalGetBehavior(
			VarInit[] dps, 
			ProcessTerm processTerm, 
			AETinteractions interactions) 
		{
		super(dps,processTerm,interactions);
		}

	public UnconditionalGetBehavior() 
		{
		super();
		}
	
	public boolean isUnconditionalGetBehavior()
		{
		if (!getConditionTrue(processTerm)) return false;
		if (!isGetBehavior()) 
			return false;
		return true;
		}
	
	/**
	 * Restituisce true se e solo se la condizione di esecuzione
	 * di processTerme'true
	 * 
	 * @param processTerm
	 */
	private boolean getConditionTrue(ProcessTerm processTerm) 
		{
		// si preleva la condizione di esecuzione
		// del processo
		Expression c1 = processTerm.getCondition();
		// la condizione deve essere uguale a true
		if (!c1.equals(new Boolean(true))) return false;
		else return true;
		}
	
	// restituisce null se UnconditionalGetBehavior none'un UnconditionalGetBehavior
	public ProcessTerm getMaximalUnconditionalGetBehavior()
		{
		if (!getConditionTrue(processTerm)) return null;
		if (!isGetBehavior()) 
			return null;
		return getMaximalGetBehavior();
		}

	// restituisce null se ConditionalGetBehavior none'un ConditionalGetBehavior
	public ProcessTerm getUnconditionalGetBehavior()
		{
		if (!getConditionTrue(processTerm)) return null;
		if (!isGetBehavior()) 
			return null;
		return getGetBehavior();
		}

	@Override
	public Object copy() 
		{
		UnconditionalGetBehavior unconditionalGetBehavior = new UnconditionalGetBehavior();
		// varInits setting
		VarInit[] varInitsCloned = this.varInits.clone();
		for (int i = 0; i < varInitsCloned.length; i++)
			{
			VarInit varInit = varInitsCloned[i];
			varInitsCloned[i] = varInit.copy();
			}
		unconditionalGetBehavior.varInits = varInitsCloned;
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		unconditionalGetBehavior.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		unconditionalGetBehavior.tinteractions = tinteractionsCloned;
		return unconditionalGetBehavior;
		}

	@Override
	public void print() 
		{
		System.out.println("UnconditionalGetBehavior object");
		super.print();
		}
	}
