/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.ArrayList;
import java.util.List;

import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.comportamenti.GetBehaviorNorm;
import restrizioniIstanze.comportamenti.PutBehaviorNorm;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.Integer;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;
import utilities.ErrorMessage;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

/**
 * @author Mirko
 *
 */
public abstract class ElemTypeNormB 
	extends ElemTypeNorm 
	{

	/**
	 * Restituisce l'array con la dichiarazione dei parametri inizializzati.
	 * 
	 * @param i
	 * @return
	 */
	public VarInit[] capDecl(Header i) {
	// si prelevano i parametri dall'intestazione inizializzati e non locali
	ParamDeclaration[] declps = i.getParameters();
	List<VarInit> dpsList = new ArrayList<VarInit>();
	for (ParamDeclaration paramDeclaration : declps)
		{
		if (paramDeclaration instanceof VarInit)
			{
			VarInit varInit = (VarInit)paramDeclaration;
			dpsList.add(varInit);
			}
		}
	VarInit[] declPars = new VarInit[dpsList.size()];
	dpsList.toArray(declPars);
	return declPars;
	}

	public Expression[] getCapacitaIniziali() {
	AETbehavior tbehavior = this.getNewElemType().getBehavior();
	TailRecursion tailRecursion = new TailRecursion(tbehavior, this.depth + 1);
	// per precondizione tbehaviore'tail recirsive
	AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
	
	// alloco memoria per le capacita' iniziali
	List<Expression> listCapacitaIniziali = new ArrayList<Expression>();
	
	// per precondizione tbehavior2 ha una sola equazione
	BehavEquation behavEquation = tbehavior2.getBehaviors()[0];
	Header header = behavEquation.getBehavHeader();
	VarInit[] varInits = this.capDecl(header);
	// Il comportamento di un buffer a capacita' finitae'definito nel seguente modo:
	// Nell'equazione devono essere dichiarati dei parametri p1, p2,e', pn 
	// che rappresentano il numero di clienti di ogni classe presenti nel buffer;
	for (VarInit varInit : varInits)
		{
		Expression expression = varInit.getExpr();
		listCapacitaIniziali.add(expression);
		}		
	// imposto le capacita' iniziali
	Expression[] capacitaIniziali = new Expression[listCapacitaIniziali.size()];
	listCapacitaIniziali.toArray(capacitaIniziali);
	
	return capacitaIniziali;
	}

	/**
	 * @param interactions 
	 */
	protected String getPutFromGetP(String string, AETinteractions interactions) {
	// si trova il numero di identificazione di string come comportamento get
	// si prelevano i processi alternativi del
	// comportamento
	BehavEquation beg = this.getNewElemType().getBehavior().getBehaviors()[0];
	// si preleva lo ChoiceProcess del comportamento
	ProcessTerm processTerm1 = beg.getTermineProcesso();
	AETinteractions tinteractions = this.getNewElemType().getInteractions();
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior = new NullBehavior(processTerm1,tinteractions);
	ProcessTerm processTerm21 = nullBehavior.getMaximalNullBehavior();
	List<ProcessTerm> list1 = MetodiVari.differenza(processTerm1, processTerm21);
	// list deve avere taglia 1
	ProcessTerm processTerm3 = list1.get(0);
	// come precondizione processTerm3 deve essere uno ChoiceProcess
	ChoiceProcess cp1 = (ChoiceProcess)processTerm3;
	// si prelevano i processi alternativi del
	// comportamento
	Header header = beg.getBehavHeader();
	ProcessTerm[] pts1 = cp1.getProcesses();
	VarInit[] declPars = capDecl(header);
	int getNumber = -1;
	for (int i = 0; i < pts1.length; i++)
		{
		ProcessTerm processTerm = pts1[i];
		GetBehaviorNorm getBehaviorNorm = new GetBehaviorNorm(declPars, processTerm, interactions,this.depth + 1);
		if (getBehaviorNorm.isGetBehavior())
			{
			String string2 = getBehaviorNorm.nameAction();
			if (string.equals(string2))
				getNumber = getBehaviorNorm.getIdentificationNumber();
			}
		}
	// tra tutti i comportamenti di put si preleva quello che ha il numero di identificazione
	// trovato nel passo precedente
	// si preleva il nome dell'azione di put del comportamento di put trovato
	String string2 = null;
	for (int i = 0; i < pts1.length; i++)
		{
		ProcessTerm processTerm = pts1[i];
		PutBehaviorNorm putBehaviorNorm = new PutBehaviorNorm(declPars, processTerm, interactions,this.depth + 1);
		if (putBehaviorNorm.isPutBehavior())
			{
			int putNumber = putBehaviorNorm.putIdentificationNumber();
			if (getNumber == putNumber)
				string2 = putBehaviorNorm.getPutName();
			}
		}
	return string2;
	}

	public String getPutFromGet(String string) {
	AETinteractions tinteractions = this.getNewElemType().getInteractions();
	return this.getPutFromGetP(string, tinteractions);
	}

	public List<PutBehaviorNorm> getPutBehaviors() {
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
	List<PutBehaviorNorm> putBehaviors = new ArrayList<PutBehaviorNorm>();
	for (ProcessTerm processTerm2 : processTerms)
		{
		PutBehaviorNorm putBehaviorNorm = new PutBehaviorNorm(varInits, processTerm2, tinteractions,this.depth + 1);
		if (putBehaviorNorm.isPutBehavior())
			{
			putBehaviors.add(putBehaviorNorm);
			}
		}
	return putBehaviors;
	}

	/**
	 * Restituisce un'array contenente i nomi delle azioni di estrazioni di clienti
	 * di ogni classe di clienti dal buffer.
	 * @param interactions
	 *
	 * @return
	 */
	protected String[] getGetsP(AETinteractions interactions) {
	// si alloca memoria per l'array risultato
	List<String> ris = new ArrayList<String>();
	BehavEquation beg = this.getNewElemType().getBehavior().getBehaviors()[0];
	// si preleva lo ChoiceProcess del comportamento
	ProcessTerm processTerm1 = beg.getTermineProcesso();
	AETinteractions tinteractions = this.getNewElemType().getInteractions();
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior = new NullBehavior(processTerm1,tinteractions);
	ProcessTerm processTerm21 = nullBehavior.getMaximalNullBehavior();
	List<ProcessTerm> list1 = MetodiVari.differenza(processTerm1, processTerm21);
	// list deve avere taglia 1
	ProcessTerm processTerm3 = list1.get(0);
	// per precondizione processTerm3e'uno ChoiceProcess
	ChoiceProcess cp1 = (ChoiceProcess)processTerm3;
	// si prelevano i processi alternativi del
	// comportamento
	Header header = beg.getBehavHeader();
	ProcessTerm[] pts1 = cp1.getProcesses();
	VarInit[] declPars = capDecl(header); 
	for (int i = 0; i < pts1.length; i++)
		{
		ProcessTerm processTerm = pts1[i];
		GetBehaviorNorm getBehaviorNorm = new GetBehaviorNorm(declPars, processTerm, interactions,this.depth + 1);
		if (getBehaviorNorm.isGetBehavior())
			{
			String string = getBehaviorNorm.nameAction();
			ris.add(string);
			}
		}
	String[] strings = new String[ris.size()];
	ris.toArray(strings);
	return strings;
	}

	public String[] getGets() {
	AETinteractions tinteractions = this.getNewElemType().getInteractions();
	// le uniche azioni di input corrispondono alle azioni get;
	String[] strings = this.getGetsP(tinteractions);
	return strings;
	}

	/**
	 * Restituisce un'array composto dai nomi delle azioni di memorizzazione di clienti 
	 * di ogni classe
	 * dal buffer.
	 * @param interactions
	 *
	 * @return
	 */
	protected String[] getPutsP(AETinteractions interactions) {
	// si alloca memoria per l'array risultato
	List<String> ris = new ArrayList<String>();
	BehavEquation beg = this.getNewElemType().getBehavior().getBehaviors()[0];
	// si preleva lo ChoiceProcess del comportamento
	ProcessTerm processTerm1 = beg.getTermineProcesso();
	AETinteractions tinteractions = this.getNewElemType().getInteractions();
	// riconosciamo un eventuale comportamento null
	NullBehavior nullBehavior = new NullBehavior(processTerm1,tinteractions);
	ProcessTerm processTerm21 = nullBehavior.getMaximalNullBehavior();
	List<ProcessTerm> list1 = MetodiVari.differenza(processTerm1, processTerm21);
	// list deve avere taglia 1
	ProcessTerm processTerm3 = list1.get(0);
	// per precondizione processTerm3 deve essere uno ChoiceProcess
	ChoiceProcess cp1 = (ChoiceProcess)processTerm3;
	// si prelevano i processi alternativi del
	// comportamento
	Header header = beg.getBehavHeader();
	ProcessTerm[] pts1 = cp1.getProcesses();
	VarInit[] declPars = capDecl(header); 
	for (int i = 0; i < pts1.length; i++)
		{
		ProcessTerm processTerm = pts1[i];
		PutBehaviorNorm putBehaviorNorm = new PutBehaviorNorm(declPars, processTerm, interactions,this.depth + 1);
		if (putBehaviorNorm.isPutBehavior())
			{
			String string = putBehaviorNorm.getPutName();
			ris.add(string);
			}
		}
	String[] strings = new String[ris.size()];
	ris.toArray(strings);
	return strings;
	}

	public String[] getPuts() {
	AETinteractions tinteractions = this.getNewElemType().getInteractions();
	// le uniche azioni di output corrispondono alle azioni put;
	String[] strings2 = this.getPutsP(tinteractions);
	return strings2;
	}

	public int getNumeroClassiCliente() {
	AETbehavior tbehavior = this.getNewElemType().getBehavior();
	TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
	AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
	// alloco memoria per il numero delle classi clienti
	int numClassi = 0;
	// per precondizione tbehavior2 ha una sola equazione
	BehavEquation behavEquation = tbehavior2.getBehaviors()[0];
	Header header = behavEquation.getBehavHeader();
	VarInit[] varInits = this.capDecl(header);
	numClassi = varInits.length;
	return numClassi;
	}

	@Override
	public boolean isCompliantInstanceRules()
			throws RestrizioniIstanzeException 
		{
		if (!restrizioneIstanze8()) return false;
		if (!restrizioneIstanze9()) return false;
		if (!restrizioneIstanze10()) return false;
		if (!restrizioneIstanze16()) return false;
		return true;
		}

	// si verificano le condizioni sulle espressioni dei processi put
	// 9) si verificano le espressioni sulla chiamata comportamentale
	public boolean restrizioneIstanze10() 
		{
		List<PutBehaviorNorm> list = getPutBehaviors();
		for (PutBehaviorNorm putBehavior : list)
			{
			if (!putBehavior.checkBehavProcess())
				{
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = putBehavior.getErrorMessage();
				list2.add(errorMessage);
				return false;
				}
			}
		return true;
		}

	// 6) I parametri presenti nell�intestazione del comportamento devono essere 
	// inizializzati a interi non negativi
	public boolean restrizioneIstanze8() 
		{
		// per precondizioni le espressioni di inizializzazione di varInits sono
		// gia' state valutate e devono corrispondere a interi non negativi
		Expression[] espressiones = getCapacitaIniziali();
		for (Expression expression : espressiones)
			{
			// per precondizione expressione'un intero
			Integer integer = (Integer)expression;
			int i = integer.getValore();
			if (i < 0)
				{
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string = "beginning capacity " + expression.toString() + " is less than zero";
				errorMessage.setErrorMessage(string);
				list2.add(errorMessage);
				return false;
				}
			}
		return true;
		}

	// si verificano le condizioni sulle espressioni dei processi put
	// 8) si verifica la condizione di buffer vuoto
	public boolean restrizioneIstanze9() 
		{
		List<PutBehaviorNorm> list = getPutBehaviors();
		for (PutBehaviorNorm putBehaviorNorm : list)
			{
			if (!putBehaviorNorm.checkEmpty())
				{
				String string3 = "Instances restrictions error for " + this.idecl.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = putBehaviorNorm.getErrorMessage();
				list2.add(errorMessage);
				return false;
				}
			}
		return true;
		}
	
	public String getGetFromPut(String string) 
		{
		// si trova il numero di identificazione di string come comportamento put
		// si prelevano i processi alternativi del
		// comportamento
		BehavEquation beg = this.getNewElemType().getBehavior().getBehaviors()[0];
		// si preleva lo ChoiceProcess del comportamento
		ProcessTerm processTerm1 = beg.getTermineProcesso();
		AETinteractions tinteractions = this.getNewElemType().getInteractions();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior = new NullBehavior(processTerm1,tinteractions);
		ProcessTerm processTerm21 = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list1 = MetodiVari.differenza(processTerm1, processTerm21);
		// list deve avere taglia 1
		ProcessTerm processTerm3 = list1.get(0);
		// come precondizione processTerm3 deve essere uno ChoiceProcess
		ChoiceProcess cp1 = (ChoiceProcess)processTerm3;
		// si prelevano i processi alternativi del
		// comportamento
		Header header = beg.getBehavHeader();
		ProcessTerm[] pts1 = cp1.getProcesses();
		VarInit[] declPars = capDecl(header);
		int putNumber = -1;
		for (int i = 0; i < pts1.length; i++)
			{
			ProcessTerm processTerm = pts1[i];
			PutBehaviorNorm putBehaviorNorm = new PutBehaviorNorm(declPars, processTerm, tinteractions, this.depth + 1);
			if (putBehaviorNorm.isPutBehavior())
				{
				String string2 = putBehaviorNorm.getPutName();
				if (string.equals(string2))
					putNumber = putBehaviorNorm.putIdentificationNumber();
				}
			}
		// tra tutti i comportamenti di get si preleva quello che ha il numero di identificazione
		// trovato nel passo precedente
		// si preleva il nome dell'azione di get del comportamento di get trovato
		String string2 = null;
		for (int i = 0; i < pts1.length; i++)
			{
			ProcessTerm processTerm = pts1[i];
			GetBehaviorNorm getBehaviorNorm = new GetBehaviorNorm(declPars, processTerm, tinteractions,this.depth + 1);
			if (getBehaviorNorm.isGetBehavior())
				{
				int getNumber = getBehaviorNorm.getIdentificationNumber();
				if (getNumber == putNumber)
					string2 = getBehaviorNorm.nameAction();
				}
			}
		return string2;
		}
	}
