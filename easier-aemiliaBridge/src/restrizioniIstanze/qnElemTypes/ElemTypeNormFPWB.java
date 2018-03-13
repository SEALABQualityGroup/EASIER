/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.List;

import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.EquivalenzaForkConBuffer2;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormFPWB 
	extends ElemTypeNormFP 
	{

	private EquivalenzaForkConBuffer2 equivalenzaForkConBuffer2;
	
	public ElemTypeNormFPWB(ElemTypeNorm elemTypeNorm,int depth)
		{
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	private ElemTypeNormFPWB(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public EquivalenzaForkConBuffer2 getEquivalenzaForkConBuffer2() 
		{
		return equivalenzaForkConBuffer2;
		}
	
	public void setEquivalenzaForkConBuffer2(
			EquivalenzaForkConBuffer2 equivalenzaForkConBuffer2) 
		{
		this.equivalenzaForkConBuffer2 = equivalenzaForkConBuffer2;
		}
	
	public String getSelect() 
		{
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// processTerm deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm;
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior = new NullBehavior(actionProcess,tinteractions);
		ProcessTerm processTermn = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> listn = MetodiVari.differenza(actionProcess, processTermn);
		processTermn = listn.get(0);
		ActionProcess actionProcessn = (ActionProcess)processTermn;
		Action action = actionProcessn.getAzione();
		ActionType actionType = action.getType();
		String string = actionType.getName();
		return string;
		}
	
	@Override
	public ElemTypeNormFPWB copy() 
		{
		ElemTypeNormFPWB elemTypeNormFPWB = new ElemTypeNormFPWB(this.depth);
		elemTypeNormFPWB.setEquivalenzaForkConBuffer2(this.equivalenzaForkConBuffer2.copy());
		elemTypeNormFPWB.setAEIdecl(getAEIdecl().copy());
		elemTypeNormFPWB.setNewElemType(getNewElemType().copy());
		elemTypeNormFPWB.setOldElemType(getOldElemType().copy());
		return elemTypeNormFPWB;
		}

	@Override
	public void print() 
		{
		System.out.println("ElemTypeNormFPWB object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaForkConBuffer2: ");
		this.equivalenzaForkConBuffer2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ElemTypeNormFPWB))
			return false;
		ElemTypeNormFPWB elemTypeNormFPWB = (ElemTypeNormFPWB)obj;
		if (!this.equivalenzaForkConBuffer2.equals(elemTypeNormFPWB.equivalenzaForkConBuffer2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormFPWB.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormFPWB.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormFPWB.getOldElemType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "Fork Process With Buffer ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
		}
	
	
	}
