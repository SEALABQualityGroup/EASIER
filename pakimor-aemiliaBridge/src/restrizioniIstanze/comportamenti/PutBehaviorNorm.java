package restrizioniIstanze.comportamenti;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Integer;
import specificheAEmilia.Maggiore;
import specificheAEmilia.Minore;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.Real;
import specificheAEmilia.Sottrazione;
import specificheAEmilia.VarInit;
import utilities.ErrorMessage;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.PutAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;

public class PutBehaviorNorm implements AEmiliaBase
	{

	private VarInit[] paramDeclarations;
	private ProcessTerm processTerm;
	private AETinteractions tinteractions;
	private BehavProcess behavProcess;
	private int depth;
	private ErrorMessage errorMessage;
	
	public PutBehaviorNorm( 
			VarInit[] dps, 
			ProcessTerm processTermP, 
			AETinteractions interactions,
			int depth) 
		{
		super();
		this.paramDeclarations = dps;
		this.processTerm = processTermP;
		this.tinteractions = interactions;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		// processTerm deve essere un ActionProcess
		// si preleva il comportamento null massimo
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si preleva la coda dallo j-esimo ActionProcess
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(
				processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(
				processTerm3 == null ? null : processTerm3.copy(), 
						processTerm4 == null ? null : processTerm4.copy());
		// list deve avere taglia uguale a 1
		ProcessTerm processTerm5 = list2.get(0);
		// processTerm5 deve essere un BehavProcess
		BehavProcess behavProcess = (BehavProcess)processTerm5;
		this.behavProcess = behavProcess;
		}

	private PutBehaviorNorm(int depth) 
		{
		super();
		this.depth = depth;
		}
	
	// restituisce l'azione
	// put
	// restituisce null se processTerm none'
	// un processo put
	public String getPutName()
		{
		// processTerm deve essere un ActionProcess
		// si preleva il comportamento null massimo
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve avere taglia uguale a 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si restituisce l'azione dell'ActionProcess
		Action a1 = actionProcess.getAzione();
		// a1 deve essere un'azione put
		ActionType actionType = a1.getType();
		String string = actionType.getName();
		return string;
		}
	
	/**
	 * Verifica se la condizione di esecuzione del termine di processoe'una condizione
	 * indicante che il numero di clienti attuale di una certa classee'maggiore di zero.
	 *  
	 * @return
	 */
	public boolean checkEmpty() 
		{
		Expression c1 = processTerm.getCondition();
		// l'ActionProcess puo' essere eseguito se e
		// solo se il numero di clienti di una certa
		// classee'maggiore di zero
		boolean b = false;
		for (int k = 0; k < this.paramDeclarations.length; k++)
			{
			if (c1.equals(new Maggiore(
				new IdentExpr(this.paramDeclarations[k].getName()),
				new Integer(0)))
				||
				c1.equals(new Minore(
					new Integer(0),
					new IdentExpr(this.paramDeclarations[k].getName())))
					||
					c1.equals(new Maggiore(
							new IdentExpr(this.paramDeclarations[k].getName()),
							new Real(0)))
							||
							c1.equals(new Minore(
									new Real(0),
									new IdentExpr(this.paramDeclarations[k].getName())))			
					)
				b = true;
			}
		if (!b)
			{
			String string = "behavioral process error for " + this.behavProcess.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = "execution condition " + c1.toString() + " is not empty checking";
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		return b;
		}
	
	/**
	 * Restituisce il numero della classe di clienti relativa al processo put.
	 * Se non c'e'una corrispondenza tra dichiarazioni di parametri ed espressioni si restituisce - 1.
	 * 
	 * @param es1
	 * @return
	 */
	protected int getPutClassNumber(Expression[] es1) 
		{
		// si costruisce una lista che contiene gli identificatori
		// espressione dei parametri - 1
		List<Expression> list1 = new ArrayList<Expression>();
		// si costruisce un'array per i reali
		List<Expression> list21 = new ArrayList<Expression>();
		for (int l = 0; l < this.paramDeclarations.length; l++)
			{
			list1.add(new Sottrazione(
					new IdentExpr(this.paramDeclarations[l].getName()),
					new Integer(1)));
			list21.add(new Sottrazione(
					new IdentExpr(this.paramDeclarations[l].getName()),
					new Real(1)));
			}
		// si costruisce una lista che contiene le espressioni
		// di inizializzazione dei parametri
		List<Expression> list2 = new ArrayList<Expression>();
		for (int l = 0; l < this.paramDeclarations.length; l++)
			{
			list2.add(((VarInit)this.paramDeclarations[l]).getExpr());
			}
		int z = -1;
		// ci deve essere una delle espressioni chee'uguale 
		// al nome dello z-esimo parametro dell'intestazione - 1
		for (int k = 0; k < es1.length; k++)
			{
			// si verifica che es1[k] sia contenuto in list 
			if (list1.contains(es1[k]))
				z = list1.indexOf(es1[k]);
			if (list21.contains(es1[k]))
				z = list21.indexOf(es1[k]);
			}
		return z;
		}
	
	/**
	 * Restituisce il numero del processo put riconosciuto, 
	 * a seconda della posizione del relativo
	 * parametro in dps.
	 * Come precondizione processTerm deve essere un processo put.
	 * 
	 */
	public int putIdentificationNumber() 
		{
		BehavProcess bp1 = MetodiVari.returnTail(
				this.processTerm == null ? null : this.processTerm.copy());
		// per precondizione bp1 none'null
		// si prelevano i parametri attuali per la
		// chiamata del comportamento
		Expression[] es1 = bp1.getExprs();
		return getPutClassNumber(es1);
		}

	public ProcessTerm getMaximalPutBehavior()
		{
		ProcessTerm ris = null;
		// processTerm deve essere un ActionProcess
		// si preleva il comportamento null massimo
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve avere taglia uguale a 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si restituisce l'azione dell'ActionProcess
		Action a1 = actionProcess.getAzione();
		// a1 deve essere un'azione put
		// si preleva la coda dallo j-esimo ActionProcess
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(
				processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		
		// effettuo la differenza tra processTerm3 e processTerm4
		List<ProcessTerm> list2 = MetodiVari.differenza(processTerm3, processTerm4);
		// list2 deve avere taglia uno
		// pt corrisponde alla chiamata comportamentale
		ProcessTerm pt = list2.get(0);
		processTerm4 = MetodiVari.somma(processTerm4, pt);
		
		// imposto il processo risultato uguale a processTerm + a1 + processTerm4
		ris = MetodiVari.somma(a1, processTerm4);
		ris = MetodiVari.somma(processTerm, ris);
		// imposto la condizione del risultato
		Expression expression = this.processTerm.getCondition();
		ris.setCondition(expression.copy());
		return ris;
		}

	// si verificano le espressioni sulla chiamata comportamentale
	public boolean checkBehavProcess()
		{
		Expression[] es1 = this.behavProcess.getExprs();
		int z = getPutClassNumber(es1);
		// se none'stato trovato il parametro attuale corrispondente alla capacita' per la classe
		// di clienti si restituisce false
		if (z == -1) 
			{
			String string = "behavioral process error for " + this.behavProcess.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = "there is not capacity expression for " + this.processTerm.toString() + " in " + this.behavProcess.toString();
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		// ci devono essere es1.length - 1 espressioni uguali ad uno dei parametri dell'intestazione
		// diverso dallo z-esimo parametro
		ParametersRelation parametersRelation = new ParametersRelation(this.depth + 1);
		if (!parametersRelation.parametersRelation(this.paramDeclarations, es1, z)) 
			{
			String string = "behavioral process error for " + this.behavProcess.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = parametersRelation.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	public boolean isPutBehavior()
		{
		// processTerm deve essere un ActionProcess
		if (!(processTerm instanceof ActionProcess))
			return false;
		// si preleva il comportamento null massimo
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve avere taglia uguale a 1
		if (list.size() != 1)
			return false;
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		if (!(processTerm2 instanceof ActionProcess))
			return false;
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si restituisce l'azione dell'ActionProcess
		Action a1 = actionProcess.getAzione();
		// a1 deve essere un'azione put
		PutAction putAction = new PutAction(a1,this.tinteractions);
		if (!putAction.isPut())
			return false;
		// si preleva la coda dallo j-esimo ActionProcess
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		// riconosciamo un eventuale comportamento null
		NullBehavior nullBehavior2 = new NullBehavior(
				processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(
				processTerm3 == null ? null : processTerm3.copy(), 
						processTerm4 == null ? null : processTerm4.copy());
		// list deve avere taglia uguale a 1
		if (list2.size() != 1)
			return false;
		ProcessTerm processTerm5 = list2.get(0);
		// processTerm5 deve essere un BehavProcess
		if (!(processTerm5 instanceof BehavProcess))
			return false;
		return true;
		}
	
	@Override
	public Object copy() 
		{
		PutBehaviorNorm putBehaviorNorm = new PutBehaviorNorm(this.depth);
		// paramDeclarations setting
		VarInit[] paramDeclarationsCloned = this.paramDeclarations.clone();
		for (int i = 0; i < paramDeclarationsCloned.length; i++)
			{
			VarInit paramDeclaration = paramDeclarationsCloned[i];
			paramDeclarationsCloned[i] = paramDeclaration.copy();
			}
		putBehaviorNorm.paramDeclarations = paramDeclarationsCloned;
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		putBehaviorNorm.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		putBehaviorNorm.tinteractions = tinteractionsCloned;
		// behavProcess setting
		BehavProcess behavProcessCloned = this.behavProcess.copy();
		putBehaviorNorm.behavProcess = behavProcessCloned;
		return putBehaviorNorm;
		}

	@Override
	public void print() 
		{
		System.out.println("PutBehaviorNorm object");
		// paramDeclarations printing
		System.out.println("ParamDeclarations:");
		for (ParamDeclaration paramDeclaration : this.paramDeclarations)
			{
			paramDeclaration.print();
			}
		// processTerm printing
		System.out.println("ProcessTerm:");
		this.processTerm.print();
		// tinteractions printing
		System.out.println("Tinteractions:");
		this.tinteractions.print();
		// behavProcess printing
		System.out.println("BehavProcess:");
		this.behavProcess.print();
		}
	
	@Override
	public String toString() 
		{
		return this.processTerm.toString();
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	
	
	}
