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
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.JoinAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ExitBehaviorNoQNExit;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ExitBehaviorWithQNExit;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

public class EquivalenzaJoin2
	extends Equivalenza2
	{
		
	public EquivalenzaJoin2(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public EquivalenzaJoin2(ElemType et) 
		{
		super();
		this.elemType = et;
		this.errorMessage = new ErrorMessage(0);
		}

	public boolean isJoin() 
		{
		/* MODELLED */
		// Il comportamento di un processo joine'definito dalla seguente sequenza di comportamenti:
		// 1. un'azione di join;
		// 2. il routing di job.
		// 3. l'unica azione di inpute'l'azione di join;
		// 4. le uniche azioni di output sono le azioni di consegna.
		// 5. il comportamento di routinge'opzionale
		// 6. trasformiamo il comportamento in un comportamento tailRecursion
		AETinteractions tinteractions = this.elemType.getInteractions();
		AETbehavior tbehavior = this.elemType.getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		if (!tailRecursion.isTailRecursion())
			{
			// 1
			String string = this.elemType + " is not join";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = tailRecursion.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations deve avere una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// processTerm deve essere un ActionProcess
		if (!(processTerm instanceof ActionProcess))
			{
			// 2
			String string = this.elemType + " is not join";
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
			String string = this.elemType + " is not join";
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
		// action deve essere un'azione di join
		JoinAction joinAction = new JoinAction(action,tinteractions,this.depth + 1);
		if (!joinAction.isJoin())
			{
			// 4
			String string = this.elemType + " is not join";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			ErrorMessage errorMessage = joinAction.getErrorMessage();
			list2.add(errorMessage);
			return false;
			}
		ActionType actionType = action.getType();
		String string = actionType.getName();
		// riconosciamo un eventuale comportamento null
		ProcessTerm processTerm2 = actionProcess2n.getProcesso();
		NullBehavior nullBehavior2 = new NullBehavior(processTerm2,tinteractions);
		ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm2, processTerm4n);
		// list2 deve avere taglia 1
		ProcessTerm processTerm5n = list2.get(0);
		// processTerm5n deve essere un processo di routing oppure puo' essere o una
		// chiamata di comportamento
		ExitBehaviorNoQNExit exitBehaviorNoQNExit = new ExitBehaviorNoQNExit(processTerm5n,tinteractions,this.depth + 1);
		ExitBehaviorWithQNExit exitBehaviorWithQNExit = new ExitBehaviorWithQNExit(processTerm5n,tinteractions,this.depth + 1);
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(tinteractions);
		if (exitBehaviorNoQNExit.isExitBehavior())
			{
			// le uniche azioni di output sono le azioni di consegna.
			List<String> list3 = exitBehaviorNoQNExit.getDeliverActionNames();
			// list3 puo' contenere dei null
			list3 = MetodiVari.onlyNotNull(list3);
			List<String> list4 = tinteractionsParts.getOutputInteractions();
			if (!list3.containsAll(list4))
				{
				// 5
				String string2 = this.elemType + " is not join";
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list5 = this.errorMessage.getCauses();
				String string3 = "there are output interactions that are not deliver actions";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string3);
				list5.add(errorMessage);
				return false;
				}
			if (!list4.containsAll(list3))
				{
				// 6
				String string2 = this.elemType + " is not join";
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list5 = this.errorMessage.getCauses();
				String string3 = "there are deliver actions that are not output interactions";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string3);
				list5.add(errorMessage);
				return false;
				}
			}
		else if (exitBehaviorWithQNExit.isExitBehavior())
			{
			// le uniche azioni di output sono le azioni di consegna.
			List<String> list3 = exitBehaviorWithQNExit.getDeliverActionNames();
			// list3 puo' contenere dei null
			list3 = MetodiVari.onlyNotNull(list3);
			List<String> list4 = tinteractionsParts.getOutputInteractions();
			if (!list3.containsAll(list4))
				{
				// 7
				String string2 = this.elemType + " is not join";
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list5 = this.errorMessage.getCauses();
				String string3 = "there are output interactions that are not deliver actions";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string3);
				list5.add(errorMessage);
				return false;
				}
			if (!list4.containsAll(list3))
				{
				// 8
				String string2 = this.elemType + " is not join";
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list5 = this.errorMessage.getCauses();
				String string3 = "there are deliver actions that are not output interactions";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string3);
				list5.add(errorMessage);
				return false;
				}
			}
		else if (!(processTerm5n instanceof BehavProcess))
			{
			// 9
			String string2 = this.elemType + " is not join";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list5 = this.errorMessage.getCauses();
			String string3 = processTerm5n.toString() + " is not behavior call process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			ErrorMessage errorMessage2 = exitBehaviorNoQNExit.getErrorMessage();
			ErrorMessage errorMessage3 = exitBehaviorWithQNExit.getErrorMessage();
			list5.add(errorMessage);
			list5.add(errorMessage2);
			list5.add(errorMessage3);
			return false;
			}
		// l'unica azione di inpute'l'azione di join
		List<String> list3 = tinteractionsParts.getInputInteractions();
		List<String> list4 = new ArrayList<String>();
		list4.add(string);
		if (!list3.containsAll(list4))
			{
			// 10
			String string2 = this.elemType + " is not join";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list5 = this.errorMessage.getCauses();
			String string3 = "there are join actions that are not input interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list5.add(errorMessage);
			return false;
			}
		if (!list4.containsAll(list3))
			{
			// 11
			String string2 = this.elemType + " is not join";
			this.errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list5 = this.errorMessage.getCauses();
			String string3 = "there are input interactions that are not join actions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string3);
			list5.add(errorMessage);
			return false;
			}
		return true;
		}

	@Override
	public boolean isEquivalente() 
		{
		return this.isJoin();
		}
	
	// Restituisce
	// un comportamento con le equazioni standard
	public AETbehavior getNormalizedBehavior()
		{
		if (!isJoin())
			return null;
		// si alloca memoria per il comportamento da restituire
		AETbehavior tbehaviorR = new AETbehavior();
		// c'� un'equazione per il join e una opzionale per il routing
		List<BehavEquation> behavEquationsR = new ArrayList<BehavEquation>();
		AETbehavior tbehavior = this.elemType.getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione behavEquations deve avere una sola equazione
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
		// processTerm3n deve essere un ActionProcess
		if (!(processTerm3n instanceof ActionProcess))
			return null;
		ActionProcess actionProcess2n = (ActionProcess)processTerm3n;
		Action action = actionProcess2n.getAzione();
		ProcessTerm processTerm21 = actionProcess2n.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(processTerm21,tinteractions);
		ProcessTerm processTerm4n = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm21, processTerm4n);
		// list2 deve avere taglia uguale a 1
		if (list2.size() != 1)
			return null;
		ProcessTerm processTerm2 = list2.get(0);
		// processTerm2 deve essere un processo di routing oppure puo' essere una chiamata
		// comportamentale o null
		// costruiamo un'equazione per il join
		ParamDeclaration[] declPars = new ParamDeclaration[2];
		declPars[0] = null;
		declPars[1] = null;
		Header header = new Header("Join",declPars);
		ExitBehaviorNoQNExit exitBehaviorNoQNExit = new ExitBehaviorNoQNExit(processTerm2,tinteractions,this.depth + 1);
		ExitBehaviorWithQNExit exitBehaviorWithQNExit = new ExitBehaviorWithQNExit(processTerm2,tinteractions,this.depth + 1);
		BehavProcess behavProcess = null;
		if (exitBehaviorNoQNExit.isExitBehavior())
			behavProcess = new BehavProcess("Routing",new Expression[0]);
		else if (exitBehaviorWithQNExit.isExitBehavior())
			behavProcess = new BehavProcess("Routing",new Expression[0]);
		else
			behavProcess = new BehavProcess("Join",new Expression[0]);
		Action action2 = action.copy();
		ActionProcess actionProcess2 = new ActionProcess(action2,behavProcess);
		BehavEquation behavEquation2 = new BehavEquation(header,actionProcess2);
		behavEquationsR.add(behavEquation2);
		if (exitBehaviorNoQNExit.isExitBehavior())
			{
			// costruiamo un'equazione per il routing
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			declPars2[0] = null;
			declPars2[1] = null;
			Header intestazione2 = new Header("Routing",declPars2);
			// processTerm2c deve essere un comportamento di routig senza azioni null
			ProcessTerm processTerm2c = exitBehaviorNoQNExit.getJobsRoutingBehavior();
			ProcessTerm processTerm3 = processTerm2c == null ? null : processTerm2c.copy();
			List<BehavProcess> list3 = computeLeaf(processTerm3);
			for (BehavProcess behavProcess3 : list3)
				{
				behavProcess3.setName("Join");
				behavProcess3.setExprs(new Expression[0]);
				}
			BehavEquation behavEquation3 = new BehavEquation(intestazione2,processTerm3);
			behavEquationsR.add(behavEquation3);
			}
		else if (exitBehaviorWithQNExit.isExitBehavior())
			{
			// costruiamo un'equazione per il routing
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			declPars2[0] = null;
			declPars2[1] = null;
			Header intestazione2 = new Header("Routing",declPars2);
			// processTerm2c deve essere un comportamento di routig senza azioni null
			ProcessTerm processTerm2c = exitBehaviorWithQNExit.getJobsRoutingBehavior();
			ProcessTerm processTerm3 = processTerm2c == null ? null : processTerm2c.copy();
			List<BehavProcess> list3 = computeLeaf(processTerm3);
			for (BehavProcess behavProcess3 : list3)
				{
				behavProcess3.setName("Join");
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
	public EquivalenzaJoin2 copy() 
		{
		EquivalenzaJoin2 equivalenzaJoin2 = new EquivalenzaJoin2(this.depth);
		// elemType setting
		ElemType elemTypeCloned = this.elemType.copy();
		equivalenzaJoin2.elemType = elemTypeCloned;
		return equivalenzaJoin2;
		}

	@Override
	public void print() 
		{
		System.out.println("EquivalenzaJoin2 Object");
		// elemType printing
		System.out.println("ElemType:");
		this.elemType.print();
		}
	}
