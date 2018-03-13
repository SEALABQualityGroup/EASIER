package restrizioniIstanze.comportamenti;


import specificheAEmilia.AETinteractions;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.IntegerRangeType;
import specificheAEmilia.Maggiore;
import specificheAEmilia.Minore;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;

public class ConditionalGetBehaviorNorm 
	extends GetBehaviorNorm 
	{
	
	public ConditionalGetBehaviorNorm(
			VarInit[] varInits,
			ProcessTerm processTerm, 
			AETinteractions tinteractions,
			int depth
			) 
		{
		super(varInits,processTerm,tinteractions,depth);
		}
	
	private ConditionalGetBehaviorNorm(int depth) 
		{
		super(depth);
		}
	
	/*
	 * Restituisce true se e solo se processTerm ha una condizione di esecuzione uguale 
	 * alla verifica di non pienezza del buffer dell'i-esimo parametro di declPars.
	 */
	public boolean getConditionParameter(int i) 
		{
		// si preleva l'i-esimo dichiarazione di parametro da declPars
		ParamDeclaration paramDeclaration = this.varInits[i];
		// come precondizione ho che declPare'di tipo VarInit
		VarInit varInit = (VarInit)paramDeclaration;
		// si preleva il nome del parametro
		String string = varInit.getName();
		// come precondizione ho che varInite'un intervallo di interi
		IntegerRangeType integerRangeType = (IntegerRangeType)varInit.getType();
		// si preleva l'estremo superiore dell'intervallo
		Expression expression = integerRangeType.getEndingInt();
		// si crea un'identificatore espressione da string
		IdentExpr identExpr = new IdentExpr(string);
		// si crea un espressione string < espressione
		Minore minore = new Minore(identExpr,expression);
		// si crea un espressione espressione > string
		Maggiore maggiore = new Maggiore(expression,identExpr);
		// si preleva la condizione di processTerm
		Expression espressione2 = processTerm.getCondition();
		// se espressione2 none'uguale a minore o maggiore si restituisce false
		if (minore.equals(espressione2))
			return true;
		else if (maggiore.equals(espressione2))
			return true;
		else
			return false;
		}

	@Override
	public Object copy() 
		{
		ConditionalGetBehaviorNorm conditionalGetBehaviorNorm = new ConditionalGetBehaviorNorm(this.depth);
		// varInits setting
		VarInit[] varInitsCloned = this.varInits.clone();
		for (int i = 0; i < varInitsCloned.length; i++)
			{
			VarInit varInit = varInitsCloned[i];
			varInitsCloned[i] = varInit.copy();
			}
		conditionalGetBehaviorNorm.varInits = varInitsCloned;
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		conditionalGetBehaviorNorm.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		conditionalGetBehaviorNorm.tinteractions = tinteractionsCloned;
		// behavProcess setting
		BehavProcess behavProcessCloned = this.behavProcess.copy();
		conditionalGetBehaviorNorm.behavProcess = behavProcessCloned;
		return conditionalGetBehaviorNorm;
		}

	@Override
	public void print() 
		{
		System.out.println("ConditionalGetBehavior object");
		super.print();
		}

	public boolean isConditionalGetBehavior() 
		{
		if (!this.isGetBehavior())
			return false;
		return true;
		}
	
	@Override
	public String toString() 
		{
		return this.processTerm.toString();
		}
	}
