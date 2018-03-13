/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.List;

import restrizioniIstanze.RestrizioniIstanzeException;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ProcessTerm;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public abstract class ElemTypeNormFP 
	extends ElemTypeNorm 
	{

	public String[] getForks() {
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
	List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTermn);
	// list deve avere taglia 1
	ProcessTerm processTerm2 = list.get(0);
	// processTerm2 deve essere un ActionProcess
	ActionProcess actionProcess2 = (ActionProcess)processTerm2;
	ProcessTerm processTerm3 = actionProcess2.getProcesso();
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior2 = new NullBehavior(processTerm3,tinteractions);
	ProcessTerm processTerm2n = nullBehavior2.getMaximalNullBehavior();
	List<ProcessTerm> list2 = MetodiVari.differenza(processTerm3, processTerm2n);
	// list2 deve avere taglia 1
	ProcessTerm processTerm4 = list2.get(0);
	// processTerm4 deve essere un ActionProcess
	ActionProcess actionProcess3 = (ActionProcess)processTerm4;
	Action action2 = actionProcess3.getAzione();
	ActionType actionType2 = action2.getType();
	String string2 = actionType2.getName();
	// imposto i nomi delle azioni fork
	String[] forks = new String[1];
	forks[0] = string2;
	return forks;
	}

	@Override
	public boolean isCompliantInstanceRules()
			throws RestrizioniIstanzeException 
		{
		if (!restrizioneIstanze16())
			return false;
		return true;
		}
	}
