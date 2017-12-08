package equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import specificheAEmilia.AETbehavior;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Header;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.Stop;
import utilities.ErrorMessage;

public class TailRecursion 
	{
	
	private AETbehavior tbehavior;
	private int depth;
	private ErrorMessage errorMessage;

	public TailRecursion(AETbehavior tbehavior, int depth) 
		{
		super();
		this.tbehavior = tbehavior;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	private List<ProcessTerm> computeLeaf(BehavEquation behavEquation)
		{
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		List<ProcessTerm> list = computeLeaf(processTerm);
		return list;
		}

	private List<ProcessTerm> computeLeaf(ProcessTerm processTerm2) 
		{
		// processTerm2e'una foglia se:
		// 1)e'un ActionProcess con uno stop o BehavProcess
		// come processo
		// 2)e'uno stop
		List<ProcessTerm> list = new ArrayList<ProcessTerm>();
		// se processTerm2e'ActionProcess, chiamiamo computeLeaf sul termine di processo di
		// ActionProcess, se tale termine di processo none'uno stop o BehavProcess
		if (processTerm2 instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm2;
			ProcessTerm processTerm3 = actionProcess.getProcesso();
			if (!(processTerm3 instanceof Stop || processTerm3 instanceof BehavProcess))
				{
				List<ProcessTerm> list2 = computeLeaf(processTerm3);
				list.addAll(list2);
				}
			else
				list.add(actionProcess);
			}
		// se processTerm2e'ChoiceProcess chiamiamo computLeaf su ogni termine di processo
		// alternativo
		if (processTerm2 instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm2;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm3 : processTerms)
				{
				List<ProcessTerm> list2 = computeLeaf(processTerm3);
				list.addAll(list2);
				}
			}
		// se processTerm2e'uno stop, lo aggiungiamo a list
		if (processTerm2 instanceof Stop)
			list.add(processTerm2);
		return list;
		}
	
	public AETbehavior makeOneEquation()
		{
		AETbehavior tbehavior = this.tbehavior.copy();
		// 2.1) a partire dal secondo comportamento:
		// 2.1.1) per ogni equazione comportamentale si sostituiscono le sue chiamate
		// con il termine di processo dell'equazione che si sta considerando.
		List<BehavEquation> list = new CopyOnWriteArrayList<BehavEquation>(tbehavior.getBehaviors());
		while (list.size() > 1)
			{
			List<BehavEquation> list2 = new ArrayList<BehavEquation>();
			BehavEquation behavEquation = list.get(1);
			// si tolgono le chiamate a behavEquation nella prima equazione
			Header header = behavEquation.getBehavHeader();
			String string = header.getName();
			BehavEquation behavEquation2 = list.get(0);
			ProcessTerm processTerm = behavEquation2.getTermineProcesso();
			deleteCall(string,processTerm,behavEquation.getTermineProcesso());
			list2.add(behavEquation2);
			// si tolgono le chiamate a behavEquation nelle equazioni successive la seconda
			for (int i = 2; i < list.size(); i++)
				{
				ProcessTerm processTerm2 = list.get(i).getTermineProcesso();
				deleteCall(string,processTerm2,behavEquation.getTermineProcesso());
				list2.add(list.get(i));
				}
			list = list2;
			}
		// 2.1.2) alla fine del punto 2.1.1 otteniamo un comportamento con una sola equazione comportamentale,
		// che ha come foglie: termini di processo stop, chiamate ricorsive a comportamenti eliminati,
		// chiamate ricorsive alla prima equazione comportamentale.
		BehavEquation[] behavEquations = new BehavEquation[list.size()];
		list.toArray(behavEquations);
		tbehavior.setBehaviors(behavEquations);
		return tbehavior;
		}

	// processTerme'un termine di processo in cui le chiamate comportamentali con nome string
	// vanno sostituite con processNew
	private void deleteCall(String string, ProcessTerm processTerm,ProcessTerm processNew) 
		{
		List<ProcessTerm> list = computeLeaf(processTerm);
		for (ProcessTerm processTerm2 : list)
			{
			// per precondizione processTerm2 puo' essere solo uno stop o un ActionProcess
			// con stop o BehavProcess come termine di processo
			// se processTerm2e'uno ActionProcess con BehavProcess con nome uguale a string
			// sostituiamo il processo di ActionProcess con processNew
			if (processTerm2 instanceof ActionProcess)
				{
				ActionProcess actionProcess = (ActionProcess)processTerm2;
				ProcessTerm processTerm3 = actionProcess.getProcesso();
				if (processTerm3 instanceof BehavProcess)
					{
					BehavProcess behavProcess = (BehavProcess)processTerm3;
					String string2 = behavProcess.getName();
					// se stringe'uguale a string2 sostituisco processTerm3 con processNew
					if (string.equals(string2))
						actionProcess.setProcesso(processNew.copy());
					}
				}
			}
		}
	
	public boolean isTailRecursion()
		{
		/* MODELLED */
		// 3) sviluppiamo un metodo che restituisce true se e solo se il comportamentoe'tail recursion: 
		// ogni foglia del comportamentoe'una chiamata ricorsiva alla prima equazione comportamentale.
		AETbehavior tbehavior = makeOneEquation();
		// preleviamo il nome dell'unica equazione comportamentale (per precondizione)
		BehavEquation[] behavEquations = tbehavior.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		Header header = behavEquation.getBehavHeader();
		String string = header.getName();
		List<ProcessTerm> list = computeLeaf(behavEquation);
		for (ProcessTerm processTerm : list)
			{
			if (processTerm instanceof Stop)
				{
				// 1
				String string2 = this.tbehavior + " is not tail recursion behavior";
				this.errorMessage.setErrorMessage(string2);
				String string3 = "there is a leaf that is a stop process";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				list2.add(errorMessage);
				return false;
				}
			// processTerm puo' essere solo un ActionProcess
			ActionProcess actionProcess = (ActionProcess)processTerm;
			ProcessTerm processTerm2 = actionProcess.getProcesso();
			if (processTerm2 instanceof Stop)
				{
				// 2
				String string2 = this.tbehavior + " is not tail recursion behavior";
				this.errorMessage.setErrorMessage(string2);
				String string3 = "there is a leaf that is a stop process";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				list2.add(errorMessage);
				return false;
				}
			if (processTerm2 instanceof BehavProcess)
				{
				BehavProcess behavProcess = (BehavProcess)processTerm2;
				String string2 = behavProcess.getName();
				if (!string.equals(string2))
					{
					// 3
					String string3 = this.tbehavior + " is not tail recursion behavior";
					this.errorMessage.setErrorMessage(string3);
					String string4 = "leaf " + processTerm2 + " is not initial behavior " + string;
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string4);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					list2.add(errorMessage);
					return false;
					}
				}
			}
		return true;
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
