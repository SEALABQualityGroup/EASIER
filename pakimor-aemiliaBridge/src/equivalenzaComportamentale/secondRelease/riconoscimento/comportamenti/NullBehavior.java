/**
 * 
 */
package equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti;

import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.NullAction;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ProcessTerm;

/**
 * Rappresenta un comportamento con una sequenza di zero o piu' azioni null.
 * 
 * @author Mirko
 *
 */
public class NullBehavior implements AEmiliaBase
	{
	
	private ProcessTerm processTerm;
	private AETinteractions tinteractions;
	
	public NullBehavior(ProcessTerm processTerm, 
			AETinteractions tinteractions) 
		{
		super();
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		}

	public NullBehavior() 
		{
		super();
		}

	/*
	 * Restituisce il termine di processo piu' grande di processTerm costituito da zero
	 * o piu' azioni null.
	 * restituisce null se processTerm non inizia con un'azione null.
	 */
	public ProcessTerm getMaximalNullBehavior()
		{
		ProcessTerm ris = null;
		if (!(this.processTerm instanceof ActionProcess))
			return ris;
		ActionProcess actionProcess = (ActionProcess)this.processTerm;
		Action action = actionProcess.getAzione();
		// action deve essere un'azione null
		NullAction nullAction = new NullAction(action,tinteractions);
		if (!nullAction.isNull())
			return ris;
		ProcessTerm processTerm = actionProcess.getProcesso();
		NullBehavior nullBehavior = new NullBehavior(
				processTerm == null ? null : processTerm.copy(),tinteractions);
		ProcessTerm processTerm2 = nullBehavior.getMaximalNullBehavior();
		ris = new ActionProcess(action.copy(),processTerm2);
		return ris;
		}

	@Override
	public Object copy() 
		{
		NullBehavior nullBehavior = new NullBehavior();
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		nullBehavior.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		nullBehavior.tinteractions = tinteractionsCloned;
		return nullBehavior;
		}

	@Override
	public void print() 
		{
		System.out.println("NullBehavior object");
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}
	}
