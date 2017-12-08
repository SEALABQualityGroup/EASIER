package equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti;

import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.GetAction;

public class GetBehavior implements AEmiliaBase
	{
	
	protected VarInit[] varInits;
	protected ProcessTerm processTerm;
	protected AETinteractions tinteractions;

	public GetBehavior() 
		{
		super();
		}
	
	public GetBehavior(VarInit[] varInits,
			ProcessTerm processTerm, AETinteractions tinteractions) 
		{
		super();
		this.varInits = varInits;
		this.processTerm = processTerm;
		this.tinteractions = tinteractions;
		}

	/**
	 * Restituisce true se e solo se processTerm rappresenta un processo get incondizionato.
	 * 
	 */
	public boolean isGetBehavior() 
		{
		if (!(processTerm instanceof ActionProcess))
			return false;
		// si preleva il comportamento null massimo di processTerm
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve essere una lista di taglia 1
		if (list.size() != 1)
			return false;
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		if (!(processTerm2 instanceof ActionProcess))
			return false;
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si preleva l'azione dall'ActionProcess
		Action a1 = actionProcess.getAzione();
		// a1 deve essere un'azione get
		GetAction getAction = new GetAction(a1,this.tinteractions);
		if (!getAction.isGet())
			return false;
		// riconosciamo un eventuale comportamento null
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		NullBehavior nullBehavior2 = new NullBehavior(
				processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(
				processTerm3 == null ? null : processTerm3.copy(), 
						processTerm4 == null ? null : processTerm4.copy());
		// restituiamo false se list2 ha taglia diversa da 1
		if (list2.size() != 1)
			return false;
		ProcessTerm processTerm5 = list2.get(0);
		// processTerm5 deve essere un BehavProcess
		if (!(processTerm5 instanceof BehavProcess))
			return false;
		return true;
		}

	// restituisce null se processTerm none'un GetBehavior
	public ProcessTerm getMaximalGetBehavior()
		{
		if (!isGetBehavior())
			return null;
		ProcessTerm ris = null;
		// si preleva il comportamento null massimo di processTerm
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve essere una lista di taglia 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		ris = new ActionProcess();
		// si preleva l'azione dall'ActionProcess
		Action a1 = actionProcess.getAzione();
		// a1 deve essere un'azione get
		((ActionProcess)ris).setAzione(a1);
		ris = MetodiVari.somma(processTerm, ris);
		Expression expression = this.processTerm.getCondition();
		ris.setCondition(expression.copy());
		// riconosciamo un eventuale comportamento null
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		NullBehavior nullBehavior2 = new NullBehavior(processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		// effettuo la differenza tra processTerm3 e processTerm4
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm3.copy(), processTerm4 == null ? null : processTerm4.copy());
		// list2 deve avere taglia 1
		ProcessTerm processTerm5 = list2.get(0);
		// processTerm5 corrisponde alla chiamata comportamentale finale
		ris = MetodiVari.somma(ris,processTerm4);
		ris = MetodiVari.somma(ris, processTerm5);
		return ris;
		}

	// restituisce null se processTerm none'un GetBehavior
	public ProcessTerm getGetBehavior()
		{
		if (!isGetBehavior())
			return null;
		ProcessTerm ris = null;
		// si preleva il comportamento null massimo di processTerm
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve essere una lista di taglia 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		ris = new ActionProcess();
		// si preleva l'azione dall'ActionProcess
		Action a1 = actionProcess.getAzione();
		// a1 deve essere un'azione get
		((ActionProcess)ris).setAzione(a1);
		Expression expression = this.processTerm.getCondition();
		ris.setCondition(expression.copy());
		// riconosciamo un eventuale comportamento null
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		NullBehavior nullBehavior2 = new NullBehavior(processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		// effettuo la differenza tra processTerm3 e processTerm4
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm3.copy(), processTerm4 == null ? null : processTerm4.copy());
		// list2 deve avere taglia 1
		ProcessTerm processTerm5 = list2.get(0);
		// processTerm5 corrisponde alla chiamata comportamentale finale
		((ActionProcess)ris).setProcesso(processTerm5);
		return ris;
		}
	
	/**
	 * Restituisce il nome dell'azione di get. Come precondizione processTerme'un comportamento get.
	 * 
	 * @return
	 */
	public String nameAction() 
		{
		// si preleva il comportamento null massimo di processTerm
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve essere una lista di taglia 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si preleva l'azione dall'ActionProcess
		Action a1 = actionProcess.getAzione();
		// si restituisce il nome dell'azione
		return a1.getType().getName();
		}
	
	@Override
	public Object copy() 
		{
		GetBehavior getBehavior = new GetBehavior();
		// varInits setting
		VarInit[] varInitsCloned = this.varInits.clone();
		for (int i = 0; i < varInitsCloned.length; i++)
			{
			VarInit varInit = varInitsCloned[i];
			varInitsCloned[i] = varInit.copy();
			}
		getBehavior.varInits = varInitsCloned;
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		getBehavior.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		getBehavior.tinteractions = tinteractionsCloned;
		return getBehavior;
		}

	@Override
	public void print() 
		{
		System.out.println("GetBehavior object");
		// varInits printing
		System.out.println("VarInits:");
		for (VarInit varInit : this.varInits)
			{
			varInit.print();
			}
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		}
	}