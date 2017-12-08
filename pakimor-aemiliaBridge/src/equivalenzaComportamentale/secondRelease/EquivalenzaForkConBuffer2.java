package equivalenzaComportamentale.secondRelease;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.ElemType;
import specificheAEmilia.Header;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;
import equivalenzaComportamentale.AETinteractionsParts;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ForkAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.SelectAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

public class EquivalenzaForkConBuffer2
	extends EquivalenzaFork2
	{

	public EquivalenzaForkConBuffer2(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public EquivalenzaForkConBuffer2(ElemType et) 
		{
		super();
		this.elemType = et;
		this.errorMessage = new ErrorMessage(0);
		}

	public boolean isForkConBuffer() 
		{
		/* MODELLED */
		// Il comportamento di un processo fork 
		// con buffere'definito dalla seguente 
		// sequenza di azioni:
		// 1.un'azione di selezione;
		// 2.un'azione di fork.
		// 3.l'unica azione di inpute'quella di selezione;
		// 4.l'unica azione di outpute'l'azione di fork.
		// si trasforma il comportamento di this.getEt()
		// in un'unica equazione
		AETbehavior tbehavior = this.elemType.getBehavior();
		AETinteractions tinteractions = this.elemType.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		if (!tailRecursion.isTailRecursion())
			{
			// 1
			String string = this.elemType.toString() + " is not fork with buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = tailRecursion.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// processTerm deve essere un ActionProcess
		if (!(processTerm instanceof ActionProcess))
			{
			// 2
			String string = this.elemType.toString() + " is not fork with buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = processTerm.toString() + " is not action process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		ActionProcess actionProcess = (ActionProcess)processTerm;
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior = new NullBehavior(actionProcess,tinteractions);
		ProcessTerm processTermn = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> listn = MetodiVari.differenza(actionProcess, processTermn);
		// listn deve avere taglia uno
		processTermn = listn.get(0);
		// processTermn deve essere un ActionProcess
		if (!(processTermn instanceof ActionProcess))
			{
			// 3
			String string = this.elemType.toString() + " is not fork with buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = processTermn.toString() + " is not action process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		ActionProcess actionProcessn = (ActionProcess)processTermn;
		Action action = actionProcessn.getAzione();
		// action deve essere un'azione di selezione
		SelectAction selectAction = new SelectAction(action,tinteractions,this.depth + 1);
		if (!selectAction.isSelect())
			{
			// 4
			String string = this.elemType.toString() + " is not fork with buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = selectAction.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		ProcessTerm processTerm2 = actionProcessn.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
		ProcessTerm processTerm2n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2n = MetodiVari.differenza(processTerm2, processTerm2n);
		// list2n deve avere taglia 1
		ProcessTerm processTerm3 = list2n.get(0);
		// processTerm3 deve essere un ActionProcess
		if (!(processTerm3 instanceof ActionProcess))
			{
			// 5
			String string = this.elemType.toString() + " is not fork with buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = processTerm3.toString() + " is not action process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		ActionProcess actionProcess2 = (ActionProcess)processTerm3;
		Action action2 = actionProcess2.getAzione();
		// action2 deve essere un'azione di fork
		ForkAction forkAction = new ForkAction(action2,tinteractions,this.depth + 1);
		if (!forkAction.isFork())
			{
			// 6
			String string = this.elemType.toString() + " is not fork with buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = forkAction.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		// per precondizione action2 deve essere un'azione immediata
		// l'unica azione di inpute'quella di selezione
		ActionType actionType = action.getType();
		String string = actionType.getName();
		List<String> list = new ArrayList<String>();
		list.add(string);
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(tinteractions);
		List<String> list2 = tinteractionsParts.getInputInteractions();
		if (!list.containsAll(list2))
			{
			// 7
			String string2 = this.elemType.toString() + " is not fork with buffer";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string3 = "there are input interactions that are not select actions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list3.add(errorMessage);
			return false;
			}
		if (!list2.containsAll(list))
			{
			// 8
			String string2 = this.elemType.toString() + " is not fork with buffer";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string3 = "there are select actions that are not input interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list3.add(errorMessage);
			return false;
			}
		// l'unica azione di outpute'l'azione di fork
		ActionType actionType2 = action2.getType();
		String string2 = actionType2.getName();
		List<String> list3 = new ArrayList<String>();
		list3.add(string2);
		List<String> list4 = tinteractionsParts.getOutputInteractions();
		if (!list4.containsAll(list3))
			{
			// 9
			String string3 = this.elemType.toString() + " is not fork with buffer";
			this.errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list5 = this.errorMessage.getCauses();
			String string4 = "there are fork actions that are not output interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string4);
			list5.add(errorMessage);
			return false;
			}
		if (!list3.containsAll(list4))
			{
			// 10
			String string3 = this.elemType.toString() + " is not fork with buffer";
			this.errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list5 = this.errorMessage.getCauses();
			String string4 = "there are output interactions that are not fork actions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string4);
			list5.add(errorMessage);
			return false;
			}
		// imposto il numero dei forks
		this.numeroForks = 1;
		return true;
		}
	
	// Restituisce
	// un comportamento con le equazioni standard
	public AETbehavior getNormalizedBehavior()
		{
		if (!isForkConBuffer())
			return null;
		AETbehavior tbehavior = this.elemType.getBehavior();
		AETinteractions tinteractions = this.elemType.getInteractions();
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
		// listn deve avere taglia uno
		ProcessTerm processTerm2 = listn.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess2 = (ActionProcess)processTerm2;
		Action action = actionProcess2.getAzione();
		// action deve essere un'azione di selezione
		ProcessTerm processTerm3 = actionProcess2.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(processTerm3,tinteractions);
		ProcessTerm processTerm2n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2n = MetodiVari.differenza(processTerm3, processTerm2n);
		// list2n deve avere taglia 1
		actionProcess2 = (ActionProcess)list2n.get(0);
		Action action2 = actionProcess2.getAzione();
		// action2 deve essere un'azione di fork
		// impostiamo l'unica equazione comportamentale del comportamento risultato
		Header header = behavEquation.getBehavHeader().copy();
		// impostiamo il termine di processo da restiuire uguale a action + action2 + (coda di processTerm)
		BehavProcess behavProcess = MetodiVari.returnTail(processTerm);
		processTerm3 = MetodiVari.somma(action2, behavProcess);
		processTerm3 = MetodiVari.somma(action, processTerm3);
		BehavEquation behavEquation2 = new BehavEquation(header,processTerm3);
		BehavEquation[] behavEquations2 = new BehavEquation[1];
		behavEquations2[0] = behavEquation2;
		AETbehavior tbehavior3 = new AETbehavior(behavEquations2);
		return tbehavior3;
		}

	public int getNumeroForks() 
		{
		return this.numeroForks;
		}

	@Override
	public boolean isEquivalente() 
		{
		return isForkConBuffer();
		}

	@Override
	public EquivalenzaForkConBuffer2 copy() {
		EquivalenzaForkConBuffer2 equivalenzaForkConBuffer2 = new EquivalenzaForkConBuffer2(this.depth);
		// numeroForks setting
		equivalenzaForkConBuffer2.numeroForks = this.numeroForks;
		// elemType setting
		ElemType elemTypeCloned = this.elemType.copy();
		equivalenzaForkConBuffer2.elemType = elemTypeCloned;
		return equivalenzaForkConBuffer2;
	}

	@Override
	public void print() 
		{
		System.out.println("EquivalenzaForkConBuffer2 object");
		// numeroForks printing
		System.out.println("NumeroForks:");
		System.out.println(this.numeroForks);
		// elemType
		System.out.println("ElemType:");
		this.elemType.print();
		}
	}
