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
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;
import equivalenzaComportamentale.AETinteractionsParts;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.ArriveAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ExitBehaviorNoQNExit;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ExitBehaviorWithQNExit;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

public class EquivalenzaRoutingSenzaBuffer2
	extends EquivalenzaRouting2
	{
	
	public EquivalenzaRoutingSenzaBuffer2(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public EquivalenzaRoutingSenzaBuffer2(ElemType elemType) 
		{
		super();
		this.elemType = elemType;
		this.errorMessage = new ErrorMessage(0);
		}

	public boolean isRoutingSenzaBuffer() 
		{
		/* MODELLED */
		// Il comportamento di un processo di routing con buffere'
		// definito dalla seguente sequenza di comportamenti:
		// 1. un'azione di arrivo;
		// 2. il routing di jobs.
		// 3. le uniche azioni di inpute'l'azione di arrivo;
		// 4. le uniche azioni di output sono le azioni di consegna.
		// 5. il comportamento di routinge'opzionale
		AETbehavior tbehavior = this.elemType.getBehavior();
		AETinteractions tinteractions = this.elemType.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		if (!tailRecursion.isTailRecursion())
			{
			// 1
			String string = this.elemType.toString() + " is not routing with no buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = tailRecursion.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations ha una solo equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// processTerm deve essere un ActionProcess
		if (!(processTerm instanceof ActionProcess))
			{
			// 2
			String string = this.elemType.toString() + " is not routing with no buffer";
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
		ProcessTerm processTerm2n = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTerm2n);
		// list deve avere taglia 1
		ProcessTerm processTerm3n = list.get(0);
		// processTerm3n deve essere un ActionProcess
		if (!(processTerm3n instanceof ActionProcess))
			{
			// 3
			String string = this.elemType.toString() + " is not routing with no buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			String string2 = processTerm3n.toString() + " is not action process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list2.add(errorMessage);
			return false;
			}
		ActionProcess actionProcess2n = (ActionProcess)processTerm3n;
		Action action = actionProcess2n.getAzione();
		// action deve essere un'azione di arrivo
		ArriveAction arriveAction = new ArriveAction(action,tinteractions,this.depth + 1);
		if (!arriveAction.isArrive())
			{
			// 4
			String string = this.elemType.toString() + " is not routing with no buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			ErrorMessage errorMessage = arriveAction.getErrorMessage();
			list2.add(errorMessage);
			return false;
			}
		ProcessTerm processTerm2 = actionProcess2n.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
		ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
		// list2 deve avere taglia 1
		ProcessTerm processTerm5n = list2.get(0);
		// processTerm5n deve essere un comportamento di routing o un BehavProcess
		ExitBehaviorNoQNExit exitBehaviorNoQNExit = new ExitBehaviorNoQNExit(processTerm5n,tinteractions,this.depth + 1);
		ExitBehaviorWithQNExit exitBehaviorWithQNExit = new ExitBehaviorWithQNExit(processTerm5n,tinteractions,this.depth + 1);
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(tinteractions);
		if (exitBehaviorNoQNExit.isExitBehavior())
			{
			// le uniche azioni di output sono le azioni di consegna
			// list2 puo' contenere dei null
			List<String> list3 = exitBehaviorNoQNExit.getDeliverActionNames();
			list3 = MetodiVari.onlyNotNull(list3);
			List<String> list4 = tinteractionsParts.getOutputInteractions();
			if (!list3.containsAll(list4))
				{
				// 5
				String string = this.elemType.toString() + " is not routing with no buffer";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list5 = this.errorMessage.getCauses();
				String string2 = "there are output interactions that are not deliver actions";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list5.add(errorMessage);
				return false;
				}
			if (!(list4.containsAll(list3)))
				{
				// 6
				String string = this.elemType.toString() + " is not routing with no buffer";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list5 = this.errorMessage.getCauses();
				String string2 = "there are deliver actions that are not output interactions";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list5.add(errorMessage);
				return false;
				}
			}
		else if (exitBehaviorWithQNExit.isExitBehavior())
			{
			// le uniche azioni di output sono le azioni di consegna
			// list2 puo' contenere dei null
			List<String> list3 = exitBehaviorWithQNExit.getDeliverActionNames();
			list3 = MetodiVari.onlyNotNull(list3);
			List<String> list4 = tinteractionsParts.getOutputInteractions();
			if (!list3.containsAll(list4))
				{
				// 7
				String string = this.elemType.toString() + " is not routing with no buffer";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list5 = this.errorMessage.getCauses();
				String string2 = "there are output interactions that are not deliver actions";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list5.add(errorMessage);
				return false;
				}
			if (!(list4.containsAll(list3)))
				{
				// 8
				String string = this.elemType.toString() + " is not routing with no buffer";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list5 = this.errorMessage.getCauses();
				String string2 = "there are deliver actions that are not output interactions";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list5.add(errorMessage);
				return false;
				}
			}
		else if (!(processTerm5n instanceof BehavProcess))
			{
			// 9
			String string = this.elemType.toString() + " is not routing with no buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list5 = this.errorMessage.getCauses();
			String string2 = processTerm5n.toString() + " is not behavior call process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			ErrorMessage errorMessage2 = exitBehaviorNoQNExit.getErrorMessage();
			ErrorMessage errorMessage3 = exitBehaviorWithQNExit.getErrorMessage();
			list5.add(errorMessage);
			list5.add(errorMessage2);
			list5.add(errorMessage3);
			return false;
			}
		// l'unica azione di inpute'quella di arrivo
		ActionType actionType = action.getType();
		String string = actionType.getName();
		List<String> list3 = new ArrayList<String>();
		list3.add(string);
		List<String> list5 = tinteractionsParts.getInputInteractions();
		if (!list5.containsAll(list3))
			{
			// 10
			String string2 = this.elemType.toString() + " is not routing with no buffer";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list4 = this.errorMessage.getCauses();
			String string3 = "there are arrive actions that are not input interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list4.add(errorMessage);
			return false;
			}
		if (!list3.containsAll(list5))
			{
			// 11
			String string2 = this.elemType.toString() + " is not routing with no buffer";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list4 = this.errorMessage.getCauses();
			String string3 = "there are input interactions that are not arrive actions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list4.add(errorMessage);
			return false;
			}
		return true;
		}

	@Override
	public boolean isEquivalente() 
		{
		return isRoutingSenzaBuffer();
		}

	// restituisce
	// un comportamento con le equazioni standard
	public AETbehavior getNormalizedBehavior()
		{
		if (!isRoutingSenzaBuffer())
			return null;
		// allochiamo memoria per il comportamento risultato
		AETbehavior tbehaviorR = new AETbehavior();
		// il comportamento risultato ha due equazioni
		List<BehavEquation> behavEquationsR = new ArrayList<BehavEquation>();
		AETbehavior tbehavior = this.elemType.getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		// preleviamo il comportamento ad un'unica equazione
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// per precondizione processTerm deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm;
		// riconosciamo un eventuale comportamento null
		AETinteractions tinteractions = this.elemType.getInteractions();
		NullBehavior nullBehavior = new NullBehavior(actionProcess,tinteractions);
		ProcessTerm processTerm2n = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(actionProcess, processTerm2n);
		// list deve avere taglia 1
		if (list.size() != 1)
			return null;
		ProcessTerm processTerm3n = list.get(0);
		// processTerm2 deve essere un ActionProcess
		if (!(processTerm3n instanceof ActionProcess))
			return null;
		ActionProcess actionProcess2n = (ActionProcess)processTerm3n;
		Action action = actionProcess2n.getAzione();
		// per precondizione action deve essere un'azione di arrivo
		ProcessTerm processTerm2 = actionProcess2n.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
		ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
		// list2 deve avere taglia 1
		if (list2.size() != 1)
			return null;
		ProcessTerm processTerm5n = list2.get(0);
		// per precondizione processTerm5n deve essere un comportamento di routing,
		// null o un BehavProcess
		// impostiamo la prima equazione
		ParamDeclaration[] declPars = new ParamDeclaration[2];
		declPars[0] = null;
		declPars[1] = null;
		Header header = new Header("Arrive",declPars);
		BehavProcess behavProcess = null;
		ExitBehaviorNoQNExit exitBehaviorNoQNExit = new ExitBehaviorNoQNExit(processTerm5n,tinteractions,this.depth + 1);
		ExitBehaviorWithQNExit exitBehaviorWithQNExit = new ExitBehaviorWithQNExit(processTerm5n,tinteractions,this.depth + 1);
		if (exitBehaviorNoQNExit.isExitBehavior())
			behavProcess = new BehavProcess("Routing",new Expression[0]);
		else if (exitBehaviorWithQNExit.isExitBehavior())
			behavProcess = new BehavProcess("Routing",new Expression[0]);
		else
			behavProcess = new BehavProcess("Arrive",new Expression[0]);
		Action action2 = action.copy();
		ActionProcess actionProcess2 = new ActionProcess(action2,behavProcess);
		BehavEquation behavEquation2 = new BehavEquation(header,actionProcess2);
		behavEquationsR.add(behavEquation2);
		// impostiamo la seconda equazione
		if (exitBehaviorNoQNExit.isExitBehavior())
			{
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			declPars2[0] = null;
			declPars2[1] = null;
			Header intestazione2 = new Header("Routing",declPars2);
			ProcessTerm processTerm2c = exitBehaviorNoQNExit.getJobsRoutingBehavior(); 
			ProcessTerm processTerm3 = processTerm2c == null ? null : processTerm2c.copy();
			List<BehavProcess> list3 = computeLeaf(processTerm3);
			for (BehavProcess behavProcess3 : list3)
				{
				behavProcess3.setName("Arrive");
				behavProcess3.setExprs(new Expression[0]);
				}
			BehavEquation behavEquation3 = new BehavEquation(intestazione2,processTerm3);
			behavEquationsR.add(behavEquation3);
			}
		else if (exitBehaviorWithQNExit.isExitBehavior())
			{
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			declPars2[0] = null;
			declPars2[1] = null;
			Header intestazione2 = new Header("Routing",declPars2);
			ProcessTerm processTerm2c = exitBehaviorWithQNExit.getJobsRoutingBehavior(); 
			ProcessTerm processTerm3 = processTerm2c == null ? null : processTerm2c.copy();
			List<BehavProcess> list3 = computeLeaf(processTerm3);
			for (BehavProcess behavProcess3 : list3)
				{
				behavProcess3.setName("Arrive");
				behavProcess3.setExprs(new Expression[0]);
				}
			BehavEquation behavEquation3 = new BehavEquation(intestazione2,processTerm3);
			behavEquationsR.add(behavEquation3);
			}
		BehavEquation[] equations = new BehavEquation[behavEquationsR.size()];
		behavEquationsR.toArray(equations);
		tbehaviorR.setBehaviors(equations);
		return tbehaviorR;
		}

	@Override
	public EquivalenzaRoutingSenzaBuffer2 copy() 
		{
		EquivalenzaRoutingSenzaBuffer2 equivalenzaRoutingSenzaBuffer2 = new EquivalenzaRoutingSenzaBuffer2(this.depth);
		// elemType setting
		ElemType elemTypeCloned = this.elemType.copy();
		equivalenzaRoutingSenzaBuffer2.elemType = elemTypeCloned;
		return equivalenzaRoutingSenzaBuffer2;
		}

	@Override
	public void print() 
		{
		System.out.println("EquivalenzaRoutingSenzaBuffer2 Object");
		// elemType printing
		System.out.println("ElemType:");
		this.elemType.print();
		}	
	}
