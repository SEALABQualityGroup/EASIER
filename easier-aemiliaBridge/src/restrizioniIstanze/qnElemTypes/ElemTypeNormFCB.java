/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.ArrayList;
import java.util.List;

import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.comportamenti.ConditionalGetBehaviorNorm;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerRangeType;
import specificheAEmilia.Maggiore;
import specificheAEmilia.Minore;
import specificheAEmilia.NormalType;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;
import utilities.ErrorMessage;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.EquivalenzaBufferLimitato2;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormFCB 
	extends ElemTypeNormB 
	{

	private EquivalenzaBufferLimitato2 equivalenzaBufferLimitato2;

	public ElemTypeNormFCB(ElemTypeNorm elemTypeNorm, int depth)
		{
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	private ElemTypeNormFCB(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public EquivalenzaBufferLimitato2 getEquivalenzaBufferLimitato2() 
		{
		return equivalenzaBufferLimitato2;
		}
	
	public void setEquivalenzaBufferLimitato2(
			EquivalenzaBufferLimitato2 equivalenzaBufferLimitato2) 
		{
		this.equivalenzaBufferLimitato2 = equivalenzaBufferLimitato2;
		}
	
	public Expression[] getLimitiClassi() 
		{
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		// per precondizione tbehaviore'tail recirsive
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		
		// alloco memoria per i limiti di capacita' di ogni classe
		List<Expression> listLimitiClassi = new ArrayList<Expression>();
		
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = tbehavior2.getBehaviors()[0];
		Header header = behavEquation.getBehavHeader();
		VarInit[] varInits = this.capDecl(header);
		// 2) Devono essere tutti dichiarati 
		// come intervalli di interi;
		for (VarInit varInit : varInits)
			{
			NormalType normalType = varInit.getType();
			// per precondizione normalTypee'di tipo IntegerRangeType
			IntegerRangeType integerRangeType = (IntegerRangeType)normalType;
			Expression espressione2 = integerRangeType.getEndingInt();
			// memorizzo il limite
			listLimitiClassi.add(espressione2);
			}
				
		// imposto i campi dell'oggetto
		// imposto i limiti per le classi
		Expression[] limitiClassi = new Expression[listLimitiClassi.size()];
		listLimitiClassi.toArray(limitiClassi);
		
		return limitiClassi;
		}
	
	public Expression getLimiteClasse(String string) 
		{
		return this.getLimiteClasseP(string);
		}
	
	private Expression getLimiteClasseP(String string) 
		{
		ProcessTerm processTerm = this.getNewElemType().getBehavior().getBehaviors()[0].getTermineProcesso();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior = new NullBehavior(processTerm,tinteractions);
		ProcessTerm processTerm21 = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(processTerm, processTerm21);
		// list deve avere taglia 1
		ProcessTerm processTerm3 = list1.get(0);
		// come precondizione ho che processTerm3e'uno choice
		ChoiceProcess choiceProcess = (ChoiceProcess)processTerm3;
		// si trova il comportamento che ha string come nome di azione
		ProcessTerm processTerm2 = findGet(choiceProcess,string);
		Expression expression = processTerm2.getCondition();
		Expression espressione2 = null;
		// come precondizioe ho che espressionee'un maggiore o un minore
		if (expression instanceof Maggiore)
			{
			Maggiore maggiore = (Maggiore)expression;
			// prelevo il primo operando
			espressione2 = maggiore.getExpr1();
			}
		if (expression instanceof Minore)
			{
			Minore minore = (Minore)expression;
			// prelevo il secondo operando
			espressione2 = minore.getExpr2();
			}
		return espressione2;
		}
	
	// restituisce null se choiceProcess non contiene un processo
	// get con nome dell'azione uguale a string
	private ProcessTerm findGet(ChoiceProcess choiceProcess, String string) 
		{
		ProcessTerm[] processTerms = choiceProcess.getProcesses();
		for (ProcessTerm processTerm2 : processTerms)
			{
			// come precondizione ho che processTerm2e'
			// un ActionProcess
			ActionProcess actionProcess = (ActionProcess)processTerm2;
			Action action = actionProcess.getAzione();
			ActionType actionType = action.getType();
			String string2 = actionType.getName();
			if (string.equals(string2))
				return actionProcess;
			}
		return null;
		}
	
	public List<ConditionalGetBehaviorNorm> getConditionalGetBehaviors() 
		{
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = tbehavior2.getBehaviors()[0];
		Header header = behavEquation.getBehavHeader();
		VarInit[] varInits = this.capDecl(header);
		// devono essere presenti 2n processi alternativi
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior = new NullBehavior(processTerm,tinteractions);
		ProcessTerm processTerm21 = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(processTerm, processTerm21);
		// list deve avere taglia 1
		ProcessTerm processTerm3 = list1.get(0);
		// processTerm3 deve essere un processo choice
		ChoiceProcess choiceProcess = (ChoiceProcess)processTerm3;
		ProcessTerm[] processTerms = choiceProcess.getProcesses();
		// n processi get condizionati e n processi put;
		List<ConditionalGetBehaviorNorm> conditionalGetBehaviors = new ArrayList<ConditionalGetBehaviorNorm>();
		for (ProcessTerm processTerm2 : processTerms)
			{
			ConditionalGetBehaviorNorm conditionalGetBehaviorNorm =
				new ConditionalGetBehaviorNorm(varInits, processTerm2, tinteractions,this.depth + 1);
			if (conditionalGetBehaviorNorm.isConditionalGetBehavior())
				{
				conditionalGetBehaviors.add(conditionalGetBehaviorNorm);
				}
			}
		return conditionalGetBehaviors;
		}
	
	/*
	 * 4) le capacita' di ogni classe deve essere un Integer; (isOnlyInteger)
	 */
	public boolean restrizioneIstanze12()
		{
		Expression[] espressiones = getLimitiClassi();
		for (Expression expression : espressiones)
			{
			if (!MetodiVari.isOnlyInteger(expression))
				{
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string = "capacity " + expression.toString() + " is not integer";
				errorMessage.setErrorMessage(string);
				list2.add(errorMessage);
				return false;
				}
			}
		return true;
		}
	
	// si verificano le condizioni sulle espressioni dei processi get
	// 10) si verificano le condizioni di buffer pieno
	public boolean restrizioneIstanze14()
		{
		List<ConditionalGetBehaviorNorm> list = getConditionalGetBehaviors();
		for (ConditionalGetBehaviorNorm conditionalGetBehaviorNorm : list)
			{
			int n = conditionalGetBehaviorNorm.getIdentificationNumber();
			// se la condizione di esecuzione di processTerm non corrisponde
			// alla condizione di buffer pieno si restituisce false
			if (!conditionalGetBehaviorNorm.getConditionParameter(n))
				{
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = conditionalGetBehaviorNorm.getErrorMessage();
				list2.add(errorMessage);
				return false;
				}
			}
		return true;
		}
	
	// si verificano le condizioni sulle espressioni dei processi get
	// 11) si verificano le espressioni sulla chiamata comportamentale
	public boolean restrizioneIstanze11()
		{
		List<ConditionalGetBehaviorNorm> list = getConditionalGetBehaviors();
		for (ConditionalGetBehaviorNorm conditionalGetBehaviorNorm : list)
			{
			if (!conditionalGetBehaviorNorm.checkBehavProcess())
				{
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = conditionalGetBehaviorNorm.getErrorMessage();
				list2.add(errorMessage);
				return false;
				}
			}
		return true;
		}
	
	// 7) Il parametro sulla capacita' attuale del buffer per una determinata classe di clienti 
	// deve essere compreso tra gli estremi dell'intervallo che definisce il parametro di capacita'.
	public boolean restrizioneIstanze13() throws RestrizioniIstanzeException
		{
		try {
			// si rende tail recursion il comportamento
			AETbehavior tbehavior = getNewElemType().getBehavior();
			TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
			AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
			// per precondizione tbehavior2 ha una sola equazione
			BehavEquation behavEquation = tbehavior2.getBehaviors()[0];
			Header header = behavEquation.getBehavHeader();
			VarInit[] varInits = capDecl(header);
			for (VarInit varInit : varInits) {
				NormalType normalType = varInit.getType();
				// verifichiamo che il primo estremo dell'intervalloe'0
				IntegerRangeType integerRangeType = (IntegerRangeType) normalType;
				Expression expression = integerRangeType.getBeginningInt();
				Expression espressione2 = integerRangeType.getEndingInt();
				// per precondizione espressione deve essere un Integer
				Integer integer = (Integer) expression;
				// integer deve avere valore zero
				int i = integer.getValore();
				if (i != 0)
					{
					String string3 = "Instances restrictions error for " + this.idecl.toString();
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string = "infimum " + expression.toString() + " must be zero";
					errorMessage.setErrorMessage(string);
					list2.add(errorMessage);
					return false;
					}
				// espressione2 deve essere un Integer, poich� per precondizione abbiamo
				// il tipo di elemento architetturale normalizzato
				Integer integer2 = (Integer) espressione2;
				// integer2 deve avere valore maggiore di zero
				// integer2e'la capacita' del buffer
				int j = integer2.getValore();
				if (j <= 0)
					{
					String string3 = "Instances restrictions error for " + this.idecl.toString();
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string = "supremum " + expression.toString() + " must be greater than zero";
					errorMessage.setErrorMessage(string);
					list2.add(errorMessage);
					return false;
					}
				// verifichiamo che l'espressione di inizializzazione di ogni parametro sia 
				// compressa nel range dell'intervallo che definisce il parametro
				// per precondizione, grazie alle classi di checking, varInite'un 'intero 
				Integer integer3 = (Integer) varInit.getExpr();
				if (integer3.getValore() > integer2.getValore())
					{
					String string3 = "Instances restrictions error for " + this.idecl.toString();
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string = "beginning expression " + integer3.toString() + " must be less than or equal " + integer2.toString();
					errorMessage.setErrorMessage(string);
					list2.add(errorMessage);
					return false;
					}
				if (integer3.getValore() < integer.getValore())
					{
					String string3 = "Instances restrictions error for " + this.idecl.toString();
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string = "beginning expression " + integer3.toString() + " must be less than or equal " + integer2.toString();
					errorMessage.setErrorMessage(string);
					list2.add(errorMessage);
					return false;
					}
			}
			return true;
			} 
		catch (Exception e) 
			{
			throw new RestrizioniIstanzeException(e);
			}
		}
	
	@Override
	public boolean isCompliantInstanceRules()
			throws RestrizioniIstanzeException 
		{
		if (!restrizioneIstanze8()) return false;
		if (!restrizioneIstanze9()) return false;
		if (!restrizioneIstanze10()) return false;
		if (!restrizioneIstanze11()) return false;
		if (!restrizioneIstanze12()) return false;
		if (!restrizioneIstanze13()) return false;
		if (!restrizioneIstanze14()) return false;
		if (!restrizioneIstanze16()) return false;
		return true;
		}

	@Override
	public ElemTypeNormFCB copy() 
		{
		ElemTypeNormFCB elemTypeNormFCB = new ElemTypeNormFCB(this.depth);
		elemTypeNormFCB.setEquivalenzaBufferLimitato2(this.equivalenzaBufferLimitato2.copy());
		elemTypeNormFCB.setAEIdecl(getAEIdecl().copy());
		elemTypeNormFCB.setNewElemType(getNewElemType().copy());
		elemTypeNormFCB.setOldElemType(getOldElemType().copy());
		return elemTypeNormFCB;
		}

	@Override
	public void print() 
		{
		System.out.println("ElemTypeNormFCB object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaBufferLimitato2: ");
		this.equivalenzaBufferLimitato2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ElemTypeNormFCB))
			return false;
		ElemTypeNormFCB elemTypeNormFCB = (ElemTypeNormFCB)obj;
		if (!this.equivalenzaBufferLimitato2.equals(elemTypeNormFCB.equivalenzaBufferLimitato2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormFCB.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormFCB.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormFCB.getOldElemType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "Finite Capacity Buffer ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
		}
	
	
	}
