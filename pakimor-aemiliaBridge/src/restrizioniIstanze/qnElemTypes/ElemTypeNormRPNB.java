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
import equivalenzaComportamentale.secondRelease.EquivalenzaRoutingSenzaBuffer2;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormRPNB 
	extends ElemTypeNormRP 
	{

	private EquivalenzaRoutingSenzaBuffer2 equivalenzaRoutingSenzaBuffer2;

	public ElemTypeNormRPNB(ElemTypeNorm elemTypeNorm,int depth)
		{
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	private ElemTypeNormRPNB(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public EquivalenzaRoutingSenzaBuffer2 getEquivalenzaRoutingSenzaBuffer2() 
		{
		return equivalenzaRoutingSenzaBuffer2;
		}
	
	public void setEquivalenzaRoutingSenzaBuffer2(
			EquivalenzaRoutingSenzaBuffer2 equivalenzaRoutingSenzaBuffer2) 
		{
		this.equivalenzaRoutingSenzaBuffer2 = equivalenzaRoutingSenzaBuffer2;
		}

	public String getArrive() 
		{
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations ha una solo equazione
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
		ActionProcess actionProcess2n = (ActionProcess)processTerm3n;
		Action action = actionProcess2n.getAzione();
		// action deve essere un'azione di arrivo
		// l'unica azione di inpute'quella di arrivo
		ActionType actionType = action.getType();
		String string = actionType.getName();
		return string;
		}
	
	@Override
	public ElemTypeNormRPNB copy() 
		{
		ElemTypeNormRPNB elemTypeNormRPNB = new ElemTypeNormRPNB(this.depth);
		elemTypeNormRPNB.setEquivalenzaRoutingSenzaBuffer2(this.equivalenzaRoutingSenzaBuffer2.copy());
		elemTypeNormRPNB.setAEIdecl(getAEIdecl().copy());
		elemTypeNormRPNB.setNewElemType(getNewElemType().copy());
		elemTypeNormRPNB.setOldElemType(getOldElemType().copy());
		return elemTypeNormRPNB;
		}

	@Override
	public void print() 
		{
		System.out.println("ElemTypeNormRPNB object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaRoutingSenzaBuffer2: ");
		this.equivalenzaRoutingSenzaBuffer2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ElemTypeNormRPNB))
			return false;
		ElemTypeNormRPNB elemTypeNormRPNB = (ElemTypeNormRPNB)obj;
		if (!this.equivalenzaRoutingSenzaBuffer2.equals(elemTypeNormRPNB.equivalenzaRoutingSenzaBuffer2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormRPNB.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormRPNB.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormRPNB.getOldElemType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "Routing Process With No Buffer ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
		}	
	}
