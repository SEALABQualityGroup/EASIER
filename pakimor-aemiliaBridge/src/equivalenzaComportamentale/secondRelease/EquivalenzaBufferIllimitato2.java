package equivalenzaComportamentale.secondRelease;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.ElemType;
import specificheAEmilia.Header;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;
import utilities.ErrorMessage;
import equivalenzaComportamentale.AETinteractionsParts;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.PutBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.UnconditionalGetBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

public class EquivalenzaBufferIllimitato2 
	extends EquivalenzaBuffer2 
	{
	
	public EquivalenzaBufferIllimitato2(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public EquivalenzaBufferIllimitato2(ElemType elemType) 
		{
		super();
		this.elemType = elemType;
		this.errorMessage = new ErrorMessage(0);
		}
	
	public boolean isBufferIllimitato() 
		{
		/* MODELLED */
		// Il comportamento di un buffer a capacita' infinitae'definito nel seguente modo:
		// 1) Nell'equazione devono essere dichiarati dei parametri p1, p2,e', pn 
		// che rappresentano il numero di clienti di ogni classe presenti nel buffer;
		// 3) Devono essere presenti 2n processi alternativi: n processi get 
		// incondizionati e n processi put;
		// 4) le uniche azioni di input corrispondono alle azioni get;
		// 5) le uniche azioni di output corrispondono alle azioni put;
		// 6) le azioni get devono avere nomi distinti;
		// 7) le azioni put devono avere nomi distinti.
		// si rende tail recursion il comportamento
		AETbehavior tbehavior = this.elemType.getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		if (!tailRecursion.isTailRecursion())
			{
			// 1
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = tailRecursion.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = tbehavior2.getBehaviors()[0];
		Header header = behavEquation.getBehavHeader();
		VarInit[] varInits = this.capDecl(header);
		// per precondizioni le espressioni di inizializzazione di varInits sono
		// gia' state valutate e devono corrispondere a interi non negativi
		// conto il numero di classi servite
		// devono essere presenti 2n processi alternativi
		int i = varInits.length;
		AETinteractions tinteractions = this.elemType.getInteractions();
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior = new NullBehavior(processTerm,tinteractions);
		ProcessTerm processTerm21 = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(processTerm, processTerm21);
		// list deve avere taglia 1
		ProcessTerm processTerm3 = list1.get(0);
		// processTerm3 deve essere un processo choice
		if (!(processTerm3 instanceof ChoiceProcess))
			{
			// 2
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = processTerm3.toString() + " is not choice process";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		ChoiceProcess choiceProcess = (ChoiceProcess)processTerm3;
		ProcessTerm[] processTerms = choiceProcess.getProcesses();
		int j = processTerms.length;
		if ((2 * i) != j)
			{
			// 3
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string21 = new String();
			for (int k = 0; k < varInits.length - 1; k++)
				{
				VarInit varInit = varInits[k];
				string21 = string21 + varInit.toString() + ", ";
				}
			if (varInits.length > 0)
				string21 = string21 + varInits[varInits.length - 1];
			String string2 = "double of variables initialized number " + (string21.equals(new String()) ? 0 : string21) + 
				" is not equal to number of alternative processes of " + choiceProcess.toString();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		// ke'il contatore dei processi get
		int k = 0;
		HashSet<UnconditionalGetBehavior> hashSet = new HashSet<UnconditionalGetBehavior>();
		// le'il contatore dei processi put
		int l = 0;
		HashSet<PutBehavior> hashSet2 = new HashSet<PutBehavior>();
		// n processi get 
		// incondizionati e n processi put;
		for (ProcessTerm processTerm2 : processTerms)
			{
			UnconditionalGetBehavior unconditionalGetBehavior = 
				new UnconditionalGetBehavior(varInits, processTerm2, tinteractions);
			if (unconditionalGetBehavior.isUnconditionalGetBehavior())
				{
				k++;
				hashSet.add(unconditionalGetBehavior);
				}
			PutBehavior putBehavior = new PutBehavior(varInits, processTerm2, tinteractions);
			if (putBehavior.isPutBehavior())
				{
				l++;
				hashSet2.add(putBehavior);
				}
			}
		if ((2 * k) != j) 
			{
			// 4
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = "double of unconditional get processes number is not equal to alternative processes number. " +
				"Number of get processes is " + k + ". Number of alternative processes number is " + j;
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		if ((2 * l) != j) 
			{
			// 5
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = "double of put processes number is not equal to alternative processes number. "
				+ "Number of put process is " + l + ". Number of alternative processes number is " + j;
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		// le uniche azioni di input corrispondono alle azioni get;
		String[] strings = this.getGetsP(tinteractions);
		List<String> list = new CopyOnWriteArrayList<String>(strings);
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(tinteractions);
		List<String> list2 = tinteractionsParts.getInputInteractions();
		if (!list.containsAll(list2))
			{
			// 6
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string2 = "there are input interactions that are not get actions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list3.add(errorMessage);
			return false;
			}
		if (!list2.containsAll(list))
			{
			// 7
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string2 = "there are get actions that are not input interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list3.add(errorMessage);
			return false;
			}
		// le uniche azioni di output corrispondono alle azioni put;
		String[] strings2 = this.getPutsP(tinteractions);
		List<String> list3 = new CopyOnWriteArrayList<String>(strings2);
		List<String> list4 = tinteractionsParts.getOutputInteractions();
		if (!list3.containsAll(list4))
			{
			// 8
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list5 = this.errorMessage.getCauses();
			String string2 = "there are output interactions that are not put actions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list5.add(errorMessage);
			return false;
			}
		if (!list4.containsAll(list3))
			{
			// 9
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list5 = this.errorMessage.getCauses();
			String string2 = "there are put actions that are not output interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list5.add(errorMessage);
			return false;
			}
		// le azioni get devono avere nomi distinti;
		if (!MetodiVari.distinct(list))
			{
			// 10
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list5 = this.errorMessage.getCauses();
			String string2 = "get actions " + list.toString() + " have not distinct names";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list5.add(errorMessage);
			return false;
			}
		// le azioni put devono avere nomi distinti.
		if (!MetodiVari.distinct(list3))
			{
			// 11
			String string = this.elemType.toString() + " is not infinite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list5 = this.errorMessage.getCauses();
			String string2 = "put actions " + list3.toString() + " have not distinct names";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list5.add(errorMessage);
			return false;
			}
		return true;
		}
	
	// Restituisce
	// un comportamento con le equazioni standard,
	// in cui ogni processo get e put non hanno
	// comportamenti null
	public AETbehavior getNormalizedBehavior()
		{
		if (!isBufferIllimitato())
			return null;
		AETbehavior tbehavior = this.elemType.getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = tbehavior2.getBehaviors()[0];		
		Header header = behavEquation.getBehavHeader();
		VarInit[] varInits = this.capDecl(header);		
		AETinteractions tinteractions = this.elemType.getInteractions();
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior = new NullBehavior(processTerm,tinteractions);
		ProcessTerm processTerm21 = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(processTerm, processTerm21);
		// list deve avere taglia 1
		ProcessTerm processTerm3 = list1.get(0);
		ChoiceProcess choiceProcess = (ChoiceProcess)processTerm3;
		ProcessTerm[] processTerms = choiceProcess.getProcesses();
		ChoiceProcess ris = new ChoiceProcess();
		ProcessTerm[] processTerms2 = new ProcessTerm[processTerms.length];
		ris.setProcesses(processTerms2);
		// processTermse'composto da processi get e put
		for (int i = 0; i < processTerms.length; i++)
			{
			ProcessTerm processTerm2 = processTerms[i];
			UnconditionalGetBehavior unconditionalGetBehavior =
				new UnconditionalGetBehavior(varInits,processTerm2,tinteractions);
			PutBehavior putBehavior = new PutBehavior(varInits,processTerm2,tinteractions);
			if (unconditionalGetBehavior.isUnconditionalGetBehavior())
				processTerms2[i] = unconditionalGetBehavior.getUnconditionalGetBehavior();
			else if (putBehavior.isPutBehavior())
				processTerms2[i] = putBehavior.getPutBehavior();
			}
		Header intestazione2 = header.copy();
		BehavEquation behavEquation2 = new BehavEquation(intestazione2,ris);
		BehavEquation[] behavEquations = new BehavEquation[1];
		behavEquations[0] = behavEquation2;
		AETbehavior tbehavior3 = new AETbehavior(behavEquations);
		return tbehavior3;
		}

	@Override
	public boolean isEquivalente() 
		{
		return this.isBufferIllimitato();
		}

	@Override
	public EquivalenzaBufferIllimitato2 copy() 
		{
		EquivalenzaBufferIllimitato2 equivalenzaBufferIllimitato2 = new EquivalenzaBufferIllimitato2(this.depth);
		// elemType setting
		ElemType elemTypeCloned = this.elemType.copy();
		equivalenzaBufferIllimitato2.elemType = elemTypeCloned;
		return equivalenzaBufferIllimitato2;
		}

	@Override
	public void print() 
		{
		System.out.println("EquivalenzaBufferIllimitato2 object");
		// elemType printing
		System.out.println("ElemType:");
		this.elemType.print();
		}
	}
