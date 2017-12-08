package restrizioniIstanze.comportamenti;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Integer;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.Real;
import specificheAEmilia.Somma;
import specificheAEmilia.VarInit;
import utilities.ErrorMessage;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.GetAction;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;

public class GetBehaviorNorm implements AEmiliaBase
	{
	
	protected VarInit[] varInits;
	protected ProcessTerm processTerm;
	protected AETinteractions tinteractions;
	protected BehavProcess behavProcess;
	int depth;
	private ErrorMessage errorMessage;

	public GetBehaviorNorm(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	public GetBehaviorNorm(VarInit[] varInits,
			ProcessTerm processTermP, AETinteractions tinteractions, int depth) 
		{
		super();
		this.varInits = varInits;
		this.processTerm = processTermP;
		this.tinteractions = tinteractions;
		// si preleva il comportamento null massimo di processTerm
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve essere una lista di taglia 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// riconosciamo un eventuale comportamento null
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		NullBehavior nullBehavior2 = new NullBehavior(
				processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(
				processTerm3 == null ? null : processTerm3.copy(), 
						processTerm4 == null ? null : processTerm4.copy());
		// list2 ha taglia diversa da 1
		ProcessTerm processTerm5 = list2.get(0);
		// processTerm5 deve essere un BehavProcess
		this.behavProcess = (BehavProcess)processTerm5;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce il numero del processo get riconosciuto, a seconda della posizione del relativo
	 * parametro in dps e nella chiamata di comportamento di processTerm.
	 * Come precondizione processTerm deve essere un processo get.
	 * Restituisce -1 se processTerm none'un comportamento get.
	 */
	public int getIdentificationNumber() 
		{
		BehavProcess bp1 = MetodiVari.returnTail(
				this.processTerm == null ? null : this.processTerm.copy());
		// per precondizione bp1 none'null
		// si prelevano i parametri attuali per la
		// chiamata del comportamento
		Expression[] es1 = bp1.getExprs();
		return getGetClassNumber(es1);
		}

	protected int getGetClassNumber(Expression[] es1) 
		{
		// si costruiscono array contenenti gli identificatori
		// espressione dei parametri + 1
		List<Expression> list = new ArrayList<Expression>();
		// si costruisce anche una lista per la commutativit� della somma
		List<Expression> list2 = new ArrayList<Expression>();
		// si costruiscono gli array per i reali
		List<Expression> list31 = new ArrayList<Expression>();
		// si costruisce anche una lista per la commutativit� della somma
		List<Expression> list4 = new ArrayList<Expression>();
		for (int l = 0; l < this.varInits.length; l++)
			{
			list.add(new Somma(
					new IdentExpr(this.varInits[l].getName()),
					new Integer(1)));
			list2.add(new Somma(
					new Integer(1),
					new IdentExpr(this.varInits[l].getName())));
			list31.add(new Somma(
					new IdentExpr(this.varInits[l].getName()),
					new Real(1)));
			list4.add(new Somma(
					new Real(1),
					new IdentExpr(this.varInits[l].getName())));
			}
		// si costruisce una lista che contiene le espressioni
		// di inizializzazione dei parametri
		List<Expression> list3 = new ArrayList<Expression>();
		for (int l = 0; l < this.varInits.length; l++)
			{
			list3.add(((VarInit)this.varInits[l]).getExpr());
			}
		int z = -1;
		// ci deve essere una delle espressioni chee'uguale 
		// al nome dello z-esimo parametro dell'intestazione + 1
		for (int k = 0; k < es1.length; k++)
			{
			if (list.contains(es1[k])) z = list.indexOf(es1[k]);
			if (list2.contains(es1[k])) z = list2.indexOf(es1[k]);
			if (list31.contains(es1[k])) z = list31.indexOf(es1[k]);
			if (list4.contains(es1[k])) z = list4.indexOf(es1[k]);
			}
		return z;
		}

	/**
	 * Restituisce true se e solo se es1 corrisponde alle dichiarazioni di dps, tranne che per
	 * lo z-esimo elemento. 
	 * 
	 * @param dps
	 * @param es1
	 * @param z
	 */
	protected boolean parametersRelation(ParamDeclaration[] dps, Expression[] es1, int z) 
		{
		for (int k = 0; k < es1.length; k++)
			{
			if (k != z)
				{
				// si costruisce un array che contiene gli identificatori
				// espressione dei parametri
				List<IdentExpr> list = new ArrayList<IdentExpr>();
				for (int l = 0; l < dps.length; l++)
					{
					list.add(new IdentExpr(dps[l].getName()));
					}
				// si verifica che es1[k] sia contenuto in list
				if (!list.contains(es1[k]))
					{
					String string = "relating error for " + dps + " and " + es1;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string2 = list + " must contains " + es1[k].toString();
					errorMessage.setErrorMessage(string2);
					list2.add(errorMessage);
					return false;
					}
				}
			else
				{
				// si costruisce un array che contiene gli identificatori
				// espressione dei parametri piu' uno e l'array che contiene uno
				// piu' gli identificatori espressione dei parametri
				List<Somma> list = new ArrayList<Somma>();
				List<Somma> list2 = new ArrayList<Somma>();
				for (int l = 0; l < dps.length; l++)
					{
					Integer integer = new Integer(1);
					IdentExpr identExpr = new IdentExpr(dps[l].getName());
					Somma somma = new Somma(integer,identExpr);
					Somma somma2 = new Somma(identExpr,integer);
					list.add(somma);
					list2.add(somma2);
					}
				// si verifica che es1[k] sia contenuto in list
				if (!list.contains(es1[k]) && !list2.contains(es1[k]))
					{
					String string = "relating error for " + dps + " and " + es1;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list3 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string2 = list + " or " + list2 + " must contains " + es1[k].toString();
					errorMessage.setErrorMessage(string2);
					list3.add(errorMessage);
					return false;
					}
				}
			}
		return true;
		}
	
	/**
	 * Restituisce il nome dell'azione di get. Come precondizione processTerme'un comportamento get.
	 * 
	 * @return
	 */
	public String nameAction() 
		{
		// si preleva il comportamento null massimo di processTerm
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve essere una lista di taglia 1
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si preleva l'azione dall'ActionProcess
		Action a1 = actionProcess.getAzione();
		// si restituisce il nome dell'azione
		return a1.getType().getName();
		}

	public BehavProcess getBehavProcess() 
		{
		return behavProcess;
		}
	
	// si verificano le espressioni sulla chiamata comportamentale
	public boolean checkBehavProcess()
		{
		// si prelevano i parametri attuali per la
		// chiamata del comportamento
		Expression[] es1 = this.behavProcess.getExprs();
		int z = getGetClassNumber(es1);
		// si restituisce false se none'stato trovato un parametro attuale
		// corrispondente alla capacita' della classe di clienti
		if (z == -1) return false;
		// ci devono essere es1.length - 1 espressioni uguali ad un parametro dell'intestazione
		// diverso dallo z-esimo parametro
		if (!parametersRelation(this.varInits, es1, z)) return false;
		return true;
		}

	/**
	 * Restituisce true se e solo se processTerm rappresenta 
	 * un processo get incondizionato.
	 * 
	 */
	public boolean isGetBehavior() 
		{
		if (!(processTerm instanceof ActionProcess))
			return false;
		// si preleva il comportamento null massimo di processTerm
		NullBehavior nullBehavior = new NullBehavior(
				this.processTerm == null ? null : this.processTerm.copy(),this.tinteractions);
		ProcessTerm processTerm = nullBehavior.getMaximalNullBehavior();
		List<ProcessTerm> list = MetodiVari.differenza(
				this.processTerm == null ? null : this.processTerm.copy(), 
						processTerm == null ? null : processTerm.copy());
		// list deve essere una lista di taglia 1
		if (list.size() != 1)
			return false;
		ProcessTerm processTerm2 = list.get(0);
		// processTerm2 deve essere un ActionProcess
		if (!(processTerm2 instanceof ActionProcess))
			return false;
		ActionProcess actionProcess = (ActionProcess)processTerm2;
		// si preleva l'azione dall'ActionProcess
		Action a1 = actionProcess.getAzione();
		// a1 deve essere un'azione get
		GetAction getAction = new GetAction(a1,this.tinteractions);
		if (!getAction.isGet())
			return false;
		// riconosciamo un eventuale comportamento null
		ProcessTerm processTerm3 = actionProcess.getProcesso();
		NullBehavior nullBehavior2 = new NullBehavior(
				processTerm3 == null ? null : processTerm3.copy(),this.tinteractions);
		ProcessTerm processTerm4 = nullBehavior2.getMaximalNullBehavior();
		List<ProcessTerm> list2 = MetodiVari.differenza(
				processTerm3 == null ? null : processTerm3.copy(), 
						processTerm4 == null ? null : processTerm4.copy());
		// restituiamo false se list2 ha taglia diversa da 1
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
		GetBehaviorNorm getBehaviorNorm = new GetBehaviorNorm(this.depth);
		// varInits setting
		VarInit[] varInitsCloned = this.varInits.clone();
		for (int i = 0; i < varInitsCloned.length; i++)
			{
			VarInit varInit = varInitsCloned[i];
			varInitsCloned[i] = varInit.copy();
			}
		getBehaviorNorm.varInits = varInitsCloned;
		// processTerm setting
		ProcessTerm processTermCloned = this.processTerm.copy();
		getBehaviorNorm.processTerm = processTermCloned;
		// tinteractions setting
		AETinteractions tinteractionsCloned = this.tinteractions.copy();
		getBehaviorNorm.tinteractions = tinteractionsCloned;
		// behavProcess setting
		BehavProcess behavProcessCloned = this.behavProcess.copy();
		getBehaviorNorm.behavProcess = behavProcessCloned;
		return getBehaviorNorm;
		}

	@Override
	public void print() 
		{
		System.out.println("GetBehaviorNorm object");
		// varInits printing
		System.out.println("VarInits:");
		for (VarInit varInit : this.varInits)
			{
			varInit.print();
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
