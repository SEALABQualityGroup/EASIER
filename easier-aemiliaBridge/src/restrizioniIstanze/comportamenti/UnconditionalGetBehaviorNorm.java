package restrizioniIstanze.comportamenti;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Boolean;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;

public class UnconditionalGetBehaviorNorm 
	extends GetBehaviorNorm
	{
	
	public UnconditionalGetBehaviorNorm(
			VarInit[] dps, 
			ProcessTerm processTerm, 
			AETinteractions interactions,
			int depth) 
		{
		super(dps,processTerm,interactions,depth);
		}

	public UnconditionalGetBehaviorNorm(int depth) 
		{
		super(depth);
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

	@Override
	public Object copy() 
		{
		UnconditionalGetBehaviorNorm unconditionalGetBehaviorNorm = new UnconditionalGetBehaviorNorm(this.depth);
		// varInits setting
		VarInit[] varInitsCloned = this.varInits.clone();
		for (int i = 0; i < varInitsCloned.length; i++)
			{
			VarInit varInit = varInitsCloned[i];
			varInitsCloned[i] = varInit.copy();
			}
		unconditionalGetBehaviorNorm.varInits = varInitsCloned;
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		unconditionalGetBehaviorNorm.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		unconditionalGetBehaviorNorm.tinteractions = tinteractionsCloned;
		// behavProcess setting
		BehavProcess behavProcessCloned = this.behavProcess.copy();
		unconditionalGetBehaviorNorm.behavProcess = behavProcessCloned;
		return unconditionalGetBehaviorNorm;
		}

	@Override
	public void print() 
		{
		System.out.println("UnconditionalGetBehaviorNorm object");
		super.print();
		}
	
	@Override
	public String toString() 
		{
		return this.processTerm.toString();
		}
	}
