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
import equivalenzaComportamentale.secondRelease.EquivalenzaRoutingConBuffer2;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormRPWB 
	extends ElemTypeNormRP 
	{

	private EquivalenzaRoutingConBuffer2 equivalenzaRoutingConBuffer2;

	public ElemTypeNormRPWB(ElemTypeNorm elemTypeNorm, int depth)
		{
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	private ElemTypeNormRPWB(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public EquivalenzaRoutingConBuffer2 getEquivalenzaRoutingConBuffer2() 
		{
		return equivalenzaRoutingConBuffer2;
		}
	
	public void setEquivalenzaRoutingConBuffer2(
			EquivalenzaRoutingConBuffer2 equivalenzaRoutingConBuffer2) 
		{
		this.equivalenzaRoutingConBuffer2 = equivalenzaRoutingConBuffer2;
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
		ProcessTerm processTerm2n = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTerm2n);
		// list deve avere taglia 1
		ProcessTerm processTerm3n = list.get(0);
		// processTerm3n deve essere un ActionProcess
		ActionProcess actionProcess2 = (ActionProcess)processTerm3n;
		Action action = actionProcess2.getAzione();
		// imposto il nome dell'azione di selezione
		ActionType actionType2 = action.getType();
		String string2 = actionType2.getName();
		return string2;
		}
	
	@Override
	public ElemTypeNormRPWB copy() 
		{
		ElemTypeNormRPWB elemTypeNormRPWB = new ElemTypeNormRPWB(this.depth);
		elemTypeNormRPWB.setEquivalenzaRoutingConBuffer2(this.equivalenzaRoutingConBuffer2.copy());
		elemTypeNormRPWB.setAEIdecl(getAEIdecl().copy());
		elemTypeNormRPWB.setNewElemType(getNewElemType().copy());
		elemTypeNormRPWB.setOldElemType(getOldElemType().copy());
		return elemTypeNormRPWB;
		}

	@Override
	public void print() 
		{
		System.out.println("ElemTypeNormRPWB object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaRoutingConBuffer2: ");
		this.equivalenzaRoutingConBuffer2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ElemTypeNormRPWB))
			return false;
		ElemTypeNormRPWB elemTypeNormRPWB = (ElemTypeNormRPWB)obj;
		if (!this.equivalenzaRoutingConBuffer2.equals(elemTypeNormRPWB.equivalenzaRoutingConBuffer2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormRPWB.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormRPWB.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormRPWB.getOldElemType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "Routing Process With Buffer ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
		}
	}
