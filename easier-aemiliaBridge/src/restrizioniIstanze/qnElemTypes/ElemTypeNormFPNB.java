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
import equivalenzaComportamentale.secondRelease.EquivalenzaForkSenzaBuffer2;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormFPNB 
	extends ElemTypeNormFP 
	{

	private EquivalenzaForkSenzaBuffer2 equivalenzaForkSenzaBuffer2;
	
	public ElemTypeNormFPNB(ElemTypeNorm elemTypeNorm,int depth)
		{
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	private ElemTypeNormFPNB(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public EquivalenzaForkSenzaBuffer2 getEquivalenzaForkSenzaBuffer2() 
		{
		return equivalenzaForkSenzaBuffer2;
		}
	
	public void setEquivalenzaForkSenzaBuffer2(
			EquivalenzaForkSenzaBuffer2 equivalenzaForkSenzaBuffer2) 
		{
		this.equivalenzaForkSenzaBuffer2 = equivalenzaForkSenzaBuffer2;
		}
	
	public String getArrive() 
		{
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ActionProcess actionProcess = (ActionProcess)processTerm;
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior = new NullBehavior(actionProcess,tinteractions);
		ProcessTerm processTermn = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTermn);
		// list deve avere taglia 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess2 = (ActionProcess)processTerm2;
		Action action = actionProcess2.getAzione();
		ActionType actionType = action.getType();
		String string = actionType.getName();
		return string;
		}	
	
	@Override
	public ElemTypeNormFPNB copy() 
		{
		ElemTypeNormFPNB elemTypeNormFPNB = new ElemTypeNormFPNB(this.depth);
		elemTypeNormFPNB.setEquivalenzaForkSenzaBuffer2(this.equivalenzaForkSenzaBuffer2.copy());
		elemTypeNormFPNB.setAEIdecl(getAEIdecl().copy());
		elemTypeNormFPNB.setNewElemType(getNewElemType().copy());
		elemTypeNormFPNB.setOldElemType(getOldElemType().copy());
		return elemTypeNormFPNB;
		}

	@Override
	public void print() 
		{
		System.out.println("ElemTypeNormFPNB object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaForkSenzaBuffer2: ");
		this.equivalenzaForkSenzaBuffer2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ElemTypeNormFPNB))
			return false;
		ElemTypeNormFPNB elemTypeNormFPNB = (ElemTypeNormFPNB)obj;
		if (!this.equivalenzaForkSenzaBuffer2.equals(elemTypeNormFPNB.equivalenzaForkSenzaBuffer2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormFPNB.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormFPNB.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormFPNB.getOldElemType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "Fork Process With No Buffer ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
		}
	
	
	}
