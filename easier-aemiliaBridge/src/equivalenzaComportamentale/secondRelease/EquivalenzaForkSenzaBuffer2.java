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
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ArriveAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ForkAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

public class EquivalenzaForkSenzaBuffer2
	extends EquivalenzaFork2
	{
	
	public EquivalenzaForkSenzaBuffer2(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public EquivalenzaForkSenzaBuffer2(ElemType et) 
		{
		super();
		this.elemType = et;
		this.errorMessage = new ErrorMessage(0);
		}

	public boolean isForkSenzaBuffer() 
		{
		/* MODELLED */
		// Il comportamento di un processo fork 
		// senza buffere'definito dalla seguente 
		// sequenza di azioni:
		// 1. un'azione di arrivo;
		// 2. un'azione di fork.
		// 3. l'unica azione di inpute'quella di arrivo;
		// 4. l'unica azione di outpute'l'azione di fork.
		// si trasforma il comportamento di this.getEt()
		// in un'unica equazione
		AETbehavior tbehavior = this.elemType.getBehavior();
		AETinteractions tinteractions = this.elemType.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		if (!tailRecursion.isTailRecursion())
			{
			// 1
			String string = this.elemType.toString() + " is not fork without buffer";
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
			String string = this.elemType.toString() + " is not fork without buffer";
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
		List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTermn);
		// list deve avere taglia 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		if (!(processTerm2 instanceof ActionProcess))
			{
			// 3
			String string = this.elemType.toString() + " is not fork without buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = processTerm2.toString() + " is not action process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list2.add(errorMessage);
			return false;
			}
		ActionProcess actionProcess2 = (ActionProcess)processTerm2;
		Action action = actionProcess2.getAzione();
		// action deve essere un'azione di arrivo
		ArriveAction arriveAction = new ArriveAction(action,tinteractions,this.depth + 1);
		if (!arriveAction.isArrive())
			{
			// 4
			String string = this.elemType.toString() + " is not fork without buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			ErrorMessage errorMessage = arriveAction.getErrorMessage();
			list2.add(errorMessage);
			return false;
			}
		ProcessTerm processTerm3 = actionProcess2.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(processTerm3,tinteractions);
		ProcessTerm processTerm2n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm3, processTerm2n);
		// list2 deve avere taglia 1
		ProcessTerm processTerm4 = list2.get(0);
		// processTerm4 deve essere un ActionProcess
		if (!(processTerm4 instanceof ActionProcess))
			{
			// 5
			String string = this.elemType.toString() + " is not fork without buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string2 = processTerm4.toString() + " is not action process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list3.add(errorMessage);
			return false;
			}
		ActionProcess actionProcess3 = (ActionProcess)processTerm4;
		Action action2 = actionProcess3.getAzione();
		// action2 deve essere un'azione di fork
		ForkAction forkAction = new ForkAction(action2,tinteractions,this.depth + 1);
		if (!forkAction.isFork())
			{
			// 6
			String string = this.elemType.toString() + " is not fork without buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			ErrorMessage errorMessage = forkAction.getErrorMessage();
			list3.add(errorMessage);
			return false;
			}
		// per precondizione action2 ha tasso RateInf
		// l'unica azione di inpute'quella di arrivo
		ActionType actionType = action.getType();
		String string = actionType.getName();
		List<String> list31 = new ArrayList<String>();
		list31.add(string);
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(tinteractions);
		List<String> list3 = tinteractionsParts.getInputInteractions();
		if (!list3.containsAll(list31))
			{
			// 7
			String string2 = this.elemType.toString() + " is not fork without buffer";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list4 = this.errorMessage.getCauses();
			String string3 = "there are arrive actions that are not input interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list4.add(errorMessage);
			return false;
			}
		if (!list31.containsAll(list3))
			{
			// 8
			String string2 = this.elemType.toString() + " is not fork without buffer";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list4 = this.errorMessage.getCauses();
			String string3 = "there are input interactions that are not arrive actions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list4.add(errorMessage);
			return false;
			}
		// l'unica azione di outpute'l'azione di fork
		ActionType actionType2 = action2.getType();
		String string2 = actionType2.getName();
		List<String> list41 = new ArrayList<String>();
		list41.add(string2);
		List<String> list4 = tinteractionsParts.getOutputInteractions();
		if (!list4.containsAll(list41))
			{
			// 9
			String string21 = this.elemType.toString() + " is not fork without buffer";
			this.errorMessage.setErrorMessage(string21);
			List<ErrorMessage> list42 = this.errorMessage.getCauses();
			String string3 = "there are fork actions that are not output interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list42.add(errorMessage);
			return false;
			}
		if (!list41.containsAll(list4))
			{
			// 10
			String string21 = this.elemType.toString() + " is not fork without buffer";
			this.errorMessage.setErrorMessage(string21);
			List<ErrorMessage> list42 = this.errorMessage.getCauses();
			String string3 = "there are output interactions that are not fork actions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list42.add(errorMessage);
			return false;
			}
		// imposto il numero di fork
		this.numeroForks = 1;
		return true;
		}
	
	// Restituisce
	// un comportamento con le equazioni standard
	public AETbehavior getNormalizedBehavior()
		{
		if (!isForkSenzaBuffer())
			return null;
		// Il comportamento di un processo fork 
		// senza buffere'definito dalla seguente 
		// sequenza di azioni:
		// 1. un'azione di arrivo;
		// 2. un'azione di fork.
		// 3. l'unica azione di inpute'quella di arrivo;
		// 4. l'unica azione di outpute'l'azione di fork.
		// si trasforma il comportamento di this.getEt()
		// in un'unica equazione
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
		List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTermn);
		// list deve avere taglia 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess2 = (ActionProcess)processTerm2;
		Action action = actionProcess2.getAzione();
		// action deve essere un'azione di arrivo
		// processTerm3 deve essere un ActionProcess
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
		// action2 deve essere un'azione di fork
		// impostiamo il comportamento risultato
		// il termine processo risultatoe'dato da action + action2 + (coda di processTerm)
		BehavProcess behavProcess = MetodiVari.returnTail(processTerm);
		ProcessTerm processTerm5 = MetodiVari.somma(action2, behavProcess);
		processTerm5 = MetodiVari.somma(action, processTerm5);
		Header header = behavEquation.getBehavHeader().copy();
		BehavEquation behavEquation2 = new BehavEquation(header,processTerm5);
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
		return isForkSenzaBuffer();
		}

	@Override
	public EquivalenzaForkSenzaBuffer2 copy() 
		{
		EquivalenzaForkSenzaBuffer2 equivalenzaForkSenzaBuffer2 = new EquivalenzaForkSenzaBuffer2(this.depth);
		// numeroForks setting
		equivalenzaForkSenzaBuffer2.numeroForks = this.numeroForks;
		// elemType
		ElemType elemTypeCloned = this.elemType.copy();
		equivalenzaForkSenzaBuffer2.elemType = elemTypeCloned;
		return equivalenzaForkSenzaBuffer2;
		}

	@Override
	public void print() 
		{
		System.out.println("EquivalenzaForkSenzaBuffer Object");
		// numeroForks printing
		System.out.println("NumeroForks:");
		System.out.println(this.numeroForks);
		// elemType printing
		System.out.println("ElemType:");
		this.elemType.print();
		}
	}
