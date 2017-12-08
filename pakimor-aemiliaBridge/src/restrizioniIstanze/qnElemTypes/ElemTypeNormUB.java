/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.ArrayList;
import java.util.List;

import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.comportamenti.UnconditionalGetBehaviorNorm;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Header;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;
import utilities.ErrorMessage;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.EquivalenzaBufferIllimitato2;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public class ElemTypeNormUB 
	extends ElemTypeNormB 
	{

	private EquivalenzaBufferIllimitato2 equivalenzaBufferIllimitato2;
	
	public ElemTypeNormUB(ElemTypeNorm elemTypeNorm, int depth)
		{
		setAEIdecl(elemTypeNorm.getAEIdecl());
		setNewElemType(elemTypeNorm.getNewElemType());
		setOldElemType(elemTypeNorm.getOldElemType());
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	private ElemTypeNormUB(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);		
		}

	public EquivalenzaBufferIllimitato2 getEquivalenzaBufferIllimitato2() 
		{
		return equivalenzaBufferIllimitato2;
		}
	
	public void setEquivalenzaBufferIllimitato2(
			EquivalenzaBufferIllimitato2 equivalenzaBufferIllimitato2) 
		{
		this.equivalenzaBufferIllimitato2 = equivalenzaBufferIllimitato2;
		}

	public List<UnconditionalGetBehaviorNorm> getUnconditionalGetBehaviors() 
		{
		AETbehavior tbehavior = this.getNewElemType().getBehavior();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = tbehavior2.getBehaviors()[0];
		Header header = behavEquation.getBehavHeader();
		VarInit[] varInits = this.capDecl(header);
		// per precondizioni le espressioni di inizializzazione di varInits sono
		// gia' state valutate e devono corrispondere a interi non negativi
		// conto il numero di classi servite
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
		// n processi get 
		// incondizionati e n processi put;
		List<UnconditionalGetBehaviorNorm> unconditionalGetBehaviors = new ArrayList<UnconditionalGetBehaviorNorm>();
		for (ProcessTerm processTerm2 : processTerms)
			{
			UnconditionalGetBehaviorNorm unconditionalGetBehaviorNorm = 
				new UnconditionalGetBehaviorNorm(varInits, processTerm2, tinteractions,this.depth + 1);
			if (unconditionalGetBehaviorNorm.isUnconditionalGetBehavior())
				{
				unconditionalGetBehaviors.add(unconditionalGetBehaviorNorm);
				}
			}
		return unconditionalGetBehaviors;
		}
	
	// si verificano le condizioni sulle espressioni dei processi get
	// 11) si verificano le espressioni sulla chiamata comportamentale
	public boolean restrizioneIstanze11()
		{
		List<UnconditionalGetBehaviorNorm> list = getUnconditionalGetBehaviors();
		for (UnconditionalGetBehaviorNorm unconditionalGetBehavior : list)
			{
			if (!unconditionalGetBehavior.checkBehavProcess())
				{
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = unconditionalGetBehavior.getErrorMessage();
				list2.add(errorMessage);
				return false;
				}
			}
		return true;
		}

	@Override
	public boolean isCompliantInstanceRules()
			throws RestrizioniIstanzeException 
		{
		if (!restrizioneIstanze8()) return false;
		if (!restrizioneIstanze9()) return false;
		if (!restrizioneIstanze10()) return false;
		if (!restrizioneIstanze11()) return false;
		if (!restrizioneIstanze16()) return false;
		return true;
		}

	@Override
	public ElemTypeNormUB copy() 
		{
		ElemTypeNormUB elemTypeNormUB = new ElemTypeNormUB(this.depth);
		elemTypeNormUB.setEquivalenzaBufferIllimitato2(this.equivalenzaBufferIllimitato2.copy());
		elemTypeNormUB.setAEIdecl(getAEIdecl().copy());
		elemTypeNormUB.setNewElemType(getNewElemType().copy());
		elemTypeNormUB.setOldElemType(getOldElemType().copy());
		return elemTypeNormUB;
		}

	@Override
	public void print() 
		{
		System.out.println("ElemTypeNormUB object");
		System.out.print("Old ElemType: ");
		getOldElemType().print();
		System.out.println("New ElemType: ");
		getNewElemType().print();
		System.out.println("EquivalenzaBufferIllimitato2: ");
		this.equivalenzaBufferIllimitato2.print();
		System.out.println("AEIdecl: ");
		getAEIdecl().print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ElemTypeNormUB))
			return false;
		ElemTypeNormUB elemTypeNormUB = (ElemTypeNormUB)obj;
		if (!this.equivalenzaBufferIllimitato2.equals(elemTypeNormUB.equivalenzaBufferIllimitato2))
			return false;
		if (!getAEIdecl().equals(elemTypeNormUB.getAEIdecl()))
			return false;
		if (!getNewElemType().equals(elemTypeNormUB.getNewElemType()))
			return false;
		if (!getOldElemType().equals(elemTypeNormUB.getOldElemType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "Unlimited Buffer ";
		string += "New ElemType: ";
		string += getNewElemType();
		string += " Old ElemType: ";
		string += getOldElemType() + " ";
		string += " AEIdecl: ";
		string += getAEIdecl() + " ";
		return string;
		}	
	}
