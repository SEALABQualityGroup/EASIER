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
import specificheAEmilia.IntegerRangeType;
import specificheAEmilia.NormalType;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;
import utilities.ErrorMessage;
import equivalenzaComportamentale.AETinteractionsParts;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ConditionalGetBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.PutBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

public class EquivalenzaBufferLimitato2
	extends EquivalenzaBuffer2
	{
	
	public EquivalenzaBufferLimitato2(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public EquivalenzaBufferLimitato2(ElemType elemType) 
		{
		super();
		this.elemType = elemType;
		this.errorMessage = new ErrorMessage(0);
		}

	public boolean isBufferLimitato() 
		{
		/* MODELLED */
		// 1) Il comportamento di un buffer a capacita' finitae'definito nel seguente modo:
		// Nell'equazione devono essere dichiarati dei parametri p1, p2,e', pn 
		// che rappresentano il numero di clienti di ogni classe presenti nel buffer;
		// 2) I parametri presenti nell�intestazione del comportamento devono essere tutti dichiarati 
		// come intervalli di interi;
		// 3) Devono essere presenti 2n processi alternativi: n processi get e n processi put.
		// 4) Le uniche azioni di input corrispondono alle azioni get;
		// 5) Le uniche azioni di output corrispondono alle azioni put;
		// 6) le azioni get devono avere nomi distinti;
		// 7) le azioni put devono avere nomi distinti.
		// si rende tail recursion il comportamento
		AETbehavior tbehavior = this.elemType.getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		if (!tailRecursion.isTailRecursion())
			{
			// 1
			String string = this.elemType.toString() + " is not finite capacity buffer";
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
		// 2) Devono essere tutti dichiarati 
		// come intervalli di interi;
		for (VarInit varInit : varInits)
			{
			NormalType normalType = varInit.getType();
			if (!(normalType instanceof IntegerRangeType))
				{
				// 2
				String string = this.elemType.toString() + " is not finite capacity buffer";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = varInit.toString() + " is not parameter with integer range type";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				return false;
				}
			}		
		// devono essere presenti 2n processi alternativi
		int i = varInits.length;
		AETinteractions tinteractions = this.elemType.getInteractions();
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior = new NullBehavior(processTerm,tinteractions);
		ProcessTerm processTerm21 = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(processTerm, processTerm21);
		// list deve avere taglia 1 per precondizione
		ProcessTerm processTerm3 = list1.get(0);
		// processTerm3 deve essere un processo choice
		if (!(processTerm3 instanceof ChoiceProcess))
			{
			// 3
			String string = this.elemType.toString() + " is not finite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = processTerm3 + " is not choice process";
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
			// 4
			String string = this.elemType.toString() + " is not finite capacity buffer";
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
		// ke'il contatore dei processi get condizionati
		int k = 0;
		HashSet<ConditionalGetBehavior> hashSet = new HashSet<ConditionalGetBehavior>();
		// le'il contatore dei processi put
		int l = 0;
		HashSet<PutBehavior> hashSet2 = new HashSet<PutBehavior>();
		// n processi get condizionati e n processi put;
		for (ProcessTerm processTerm2 : processTerms)
			{
			ConditionalGetBehavior conditionalGetBehavior =
				new ConditionalGetBehavior(varInits, processTerm2, tinteractions);
			if (conditionalGetBehavior.isConditionalGetBehavior())
				{
				k++;
				hashSet.add(conditionalGetBehavior);
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
			// 5
			String string = this.elemType.toString() + " is not finite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = "double of conditional get processes number is not equal to alternative processes number. " +
				"Number of conditional get processes is " + k + ". Number of alternative processes number is " + j;
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		if ((2 * l) != j) 
			{
			// 6
			String string = this.elemType.toString() + " is not finite capacity buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = "double of put processes number is not equal to alternative processes number. " +
				"Number of put process is " + l + ". Number of alternative processes number is " + j;
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
			// 7
			String string = this.elemType.toString() + " is not finite capacity buffer";
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
			// 8
			String string = this.elemType.toString() + " is not finite capacity buffer";
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
			// 9
			String string = this.elemType.toString() + " is not finite capacity buffer";
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
			// 10
			String string = this.elemType.toString() + " is not finite capacity buffer";
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
			// 11
			String string = this.elemType.toString() + " is not finite capacity buffer";
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
			// 12
			String string = this.elemType.toString() + " is not finite capacity buffer";
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
	// un comportamento con le equazioni standard
	public AETbehavior getNormalizedBehavior()
		{
		if (!isBufferLimitato())
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
			ConditionalGetBehavior conditionalGetBehavior =
				new ConditionalGetBehavior(varInits,processTerm2,tinteractions);
			PutBehavior putBehavior = new PutBehavior(varInits,processTerm2,tinteractions);
			if (conditionalGetBehavior.isConditionalGetBehavior())
				processTerms2[i] = conditionalGetBehavior.getConditionalGetBehavior();
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
		return isBufferLimitato();
		}

	@Override
	public EquivalenzaBufferLimitato2 copy() 
		{
		EquivalenzaBufferLimitato2 equivalenzaBufferLimitato2 = new EquivalenzaBufferLimitato2(this.depth);
		// elemType setting
		ElemType elemTypeCloned = this.elemType.copy();
		equivalenzaBufferLimitato2.elemType = elemTypeCloned;
		return equivalenzaBufferLimitato2;
		}

	@Override
	public void print() 
		{
		System.out.println("EquivalenzaBufferLimitato2 object");
		// elemType printing
		System.out.println("ElemType:");
		this.elemType.print();
		}
	}
