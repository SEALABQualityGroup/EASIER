package equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti;

import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.PutAction;

public class PutBehavior implements AEmiliaBase
	{

	private VarInit[] paramDeclarations;
	private ProcessTerm processTerm;
	private AETinteractions tinteractions;
	private BehavProcess behavProcess;
	
	public PutBehavior( 
			VarInit[] dps, 
			ProcessTerm processTerm, 
			AETinteractions interactions) 
		{
		super();
		this.paramDeclarations = dps;
		this.processTerm = processTerm;
		this.tinteractions = interactions;
		}

	public PutBehavior() 
		{
		super();
		}
	
	public boolean isPutBehavior()
		{
		// processTerm deve essere un ActionProcess
		if (!(processTerm instanceof ActionProcess))
			return false;
		// si preleva il comportamento null massimo
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve avere taglia uguale a 1
		if (list.size() != 1)
			return false;
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		if (!(processTerm2 instanceof ActionProcess))
			return false;
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si restituisce l'azione dell'ActionProcess
		Action a1 = actionProcess.getAzione();
		// a1 deve essere un'azione put
		PutAction putAction = new PutAction(a1,this.tinteractions);
		if (!putAction.isPut())
			return false;
		// si preleva la coda dallo j-esimo ActionProcess
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(
				processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(
				processTerm3 == null ? null : processTerm3.copy(), 
						processTerm4 == null ? null : processTerm4.copy());
		// list deve avere taglia uguale a 1
		if (list2.size() != 1)
			return false;
		ProcessTerm processTerm5 = list2.get(0);
		// processTerm5 deve essere un BehavProcess
		if (!(processTerm5 instanceof BehavProcess))
			return false;
		BehavProcess behavProcess = (BehavProcess)processTerm5;
		this.behavProcess = behavProcess;
		return true;
		}
	
	public ProcessTerm getMaximalPutBehavior()
		{
		if (!isPutBehavior())
			return null;
		ProcessTerm ris = null;
		// processTerm deve essere un ActionProcess
		// si preleva il comportamento null massimo
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve avere taglia uguale a 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si restituisce l'azione dell'ActionProcess
		Action a1 = actionProcess.getAzione();
		// a1 deve essere un'azione put
		// si preleva la coda dallo j-esimo ActionProcess
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(
				processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		
		// effettuo la differenza tra processTerm3 e processTerm4
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm3, processTerm4);
		// list2 deve avere taglia uno
		// pt corrisponde alla chiamata comportamentale
		ProcessTerm pt = list2.get(0);
		processTerm4 = MetodiVari.somma(processTerm4, pt);
		
		// imposto il processo risultato uguale a processTerm + a1 + processTerm4
		ris = MetodiVari.somma(a1, processTerm4);
		ris = MetodiVari.somma(processTerm, ris);
		// imposto la condizione del risultato
		Expression expression = this.processTerm.getCondition();
		ris.setCondition(expression.copy());
		return ris;
		}

	public ProcessTerm getPutBehavior()
		{
		if (!isPutBehavior())
			return null;
		ProcessTerm ris = null;
		// processTerm deve essere un ActionProcess
		// si preleva il comportamento null massimo
		NullBehavior nullBehavior = new NullBehavior(
			this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
			this.processTerm == null ? null : this.processTerm.copy(), 
					processTerm == null ? null : processTerm.copy());
		// list deve avere taglia uguale a 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si restituisce l'azione dell'ActionProcess
		Action a1 = actionProcess.getAzione();
		
		// a1 deve essere un'azione put
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(
				processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		// effettuo la differenza tra processTerm3 e processTerm4
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm3, processTerm4);
		// list2 deve avere taglia uno
		// pt corrisponde alla chiamata comportamentale
		ProcessTerm pt = list2.get(0);
		
		// imposto il processo risultato uguale ad un ActionProcess con azione a1
		ris = new ActionProcess();
		((ActionProcess)ris).setAzione(a1);
		((ActionProcess)ris).setProcesso(pt);
		// imposto la condizione del risultato
		Expression expression = this.processTerm.getCondition();
		ris.setCondition(expression.copy());
		return ris;
		}

	// restituisce l'azione
	// put
	// restituisce null se processTerm none'
	// un processo put
	public String getPutName()
		{
		if (!isPutBehavior())
			return null;
		// processTerm deve essere un ActionProcess
		// si preleva il comportamento null massimo
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve avere taglia uguale a 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si restituisce l'azione dell'ActionProcess
		Action a1 = actionProcess.getAzione();
		// a1 deve essere un'azione put
		ActionType actionType = a1.getType();
		String string = actionType.getName();
		return string;
		}

	@Override
	public Object copy() 
		{
		PutBehavior putBehavior = new PutBehavior();
		// paramDeclarations setting
		VarInit[] paramDeclarationsCloned = this.paramDeclarations.clone();
		for (int i = 0; i < paramDeclarationsCloned.length; i++)
			{
			VarInit paramDeclaration = paramDeclarationsCloned[i];
			paramDeclarationsCloned[i] = paramDeclaration.copy();
			}
		putBehavior.paramDeclarations = paramDeclarationsCloned;
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		putBehavior.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		putBehavior.tinteractions = tinteractionsCloned;
		// behavProcess setting
		BehavProcess behavProcessCloned = this.behavProcess.copy();
		putBehavior.behavProcess = behavProcessCloned;
		return putBehavior;
		}

	@Override
	public void print() 
		{
		System.out.println("PutBehavior object");
		// paramDeclarations printing
		System.out.println("ParamDeclarations:");
		for (ParamDeclaration paramDeclaration : this.paramDeclarations)
			{
			paramDeclaration.print();
			}
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		// behavProcess printing
		System.out.println("BehavProcess:");
		this.behavProcess.print();
		}
	}
