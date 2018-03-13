package valutazione.normalization;

import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIdeclInd;
import specificheAEmilia.Action;
import specificheAEmilia.ActionOutput;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.And;
import specificheAEmilia.ArchiAttachments;
import specificheAEmilia.ArchiElemInstances;
import specificheAEmilia.ArchiElemTypes;
import specificheAEmilia.ArchiInteractions;
import specificheAEmilia.ArchiTopology;
import specificheAEmilia.ArchiType;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.AttacDeclInd;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Boolean;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Concat;
import specificheAEmilia.ConstInit;
import specificheAEmilia.Different;
import specificheAEmilia.Divisione;
import specificheAEmilia.ElemType;
import specificheAEmilia.Equal;
import specificheAEmilia.Expression;
import specificheAEmilia.First;
import specificheAEmilia.Header;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Insert;
import specificheAEmilia.Integer;
import specificheAEmilia.InteractionDecl;
import specificheAEmilia.InteractionDeclInd;
import specificheAEmilia.Length;
import specificheAEmilia.ListCons;
import specificheAEmilia.Maggiore;
import specificheAEmilia.MaggioreUguale;
import specificheAEmilia.Minore;
import specificheAEmilia.MinoreUguale;
import specificheAEmilia.Moltiplicazione;
import specificheAEmilia.Negazione;
import specificheAEmilia.Or;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.RateExp;
import specificheAEmilia.RateInf;
import specificheAEmilia.Rate_;
import specificheAEmilia.Real;
import specificheAEmilia.Remove;
import specificheAEmilia.Somma;
import specificheAEmilia.Sottrazione;
import specificheAEmilia.Tail;
import specificheAEmilia.VarInit;

/**
 * Classe statica che contiene dei metodi per verificare la
 * normalizzazione di espressioni contenute in parti
 * di una specifica di un tipo architetturale.
 * Un'espressionee'normalizzata, se al suo interno non sono presenti identificatori.
 *
 * @author Mirko
 *
 */
public class NormalizeChecking {

	/**
	 * Restituisce true se e solo se ee'un'espressione normalizzata.
	 *
	 * @param e
	 * @return
	 */
	public static boolean isNormalizedExp(Expression e)
		{
		if (e == null) return false;
		else if (e instanceof And) return false;
		else if (e instanceof Boolean) return true;
		else if (e instanceof Concat) return false;
		else if (e instanceof Different) return false;
		else if (e instanceof Divisione) return false;
		else if (e instanceof First) return false;
		else if (e instanceof IdentExpr) return false;
		else if (e instanceof Insert) return false;
		else if (e instanceof Integer) return true;
		else if (e instanceof Length) return false;
		else if (e instanceof ListCons) return true;
		else if (e instanceof Maggiore) return false;
		else if (e instanceof MaggioreUguale) return false;
		else if (e instanceof Minore) return false;
		else if (e instanceof MinoreUguale) return false;
		else if (e instanceof Moltiplicazione) return false;
		else if (e instanceof Negazione) return false;
		else if (e instanceof Or) return false;
		else if (e instanceof Real) return true;
		else if (e instanceof Remove) return false;
		else if (e instanceof Somma) return false;
		else if (e instanceof Sottrazione) return false;
		else if (e instanceof Tail) return false;
		else if (e instanceof Equal) return false;
		else return false;
		}

	/**
	 * Restituisce true se e solo se aeide'una dichiarazione di istanza normalizzata.
	 *
	 * @param aeid
	 * @return
	 */
	public static boolean isNormalizedAEIdecl(AEIdecl aeid)
		{
		if (aeid == null) return false;
		if (aeid instanceof AEIdeclInd) return false;
		if (aeid.getActualParams() != null)
			{
			for (int i = 0; i < aeid.getActualParams().length; i++)
				{
				if (!isNormalizedExp(aeid.getActualParams()[i])) return false;
				}
			}
		return true;
		}

	/**
	 * Restituisce true se e solo se ae'un'azione normalizzata.
	 *
	 * @param a
	 * @return
	 */
	public static boolean isNormalizedAction(Action a)
		{
		if (a == null) return false;
		ActionType at = a.getType();
		if (at instanceof ActionOutput)
			{
			Expression[] es = ((ActionOutput)at).getOutputExprs();
			if (es != null)
				{
				for (int i = 0; i < es.length; i++)
					{
					if (!isNormalizedExp(es[i])) return false;
					}
				}
			}
		ActionRate ar = a.getRate();
		if (ar instanceof RateExp)
			{
			if (!isNormalizedExp(((RateExp)ar).getExpr())) return false;
			}
		if (ar instanceof RateInf)
			{
			if (!isNormalizedExp(((RateInf)ar).getWeight())) return false;
			if (!isNormalizedExp(((RateInf)ar).getPrio())) return false;
			}
		if (ar instanceof Rate_)
			{
			if (!isNormalizedExp(((Rate_)ar).getWeight())) return false;
			if (!isNormalizedExp(((Rate_)ar).getPrio())) return false;
			}
		return true;
		}

	/**
	 * Restituisce true se e solo se ade'una dichiarazione di collegamento
	 * normalizzata.
	 *
	 * @param ad
	 * @return
	 */
	public static boolean isNormalizedAttacDecl(AttacDecl ad)
		{
		if (ad == null) return false;
		if (ad instanceof AttacDeclInd) return false;
		return true;
		}

	/**
	 * Restituisce true se e solo se ie'un'intestazione normalizzata.
	 *
	 * @param i
	 * @return
	 */
	public static boolean isNormalizedIntestazione(Header i)
		{
		if (i == null) return false;
		ParamDeclaration[] dps = i.getParameters();
		if (dps != null)
			{
			for (int j = 0; j < dps.length; j++)
				{
				if (dps[j] instanceof ConstInit)
					{
					if (!isNormalizedExp(((ConstInit)dps[j]).getExpr())) return false;
					}
				if (dps[j] instanceof VarInit)
					{
					if (!isNormalizedExp(((VarInit)dps[j]).getExpr())) return false;
					}
				}
			}
		return true;
		}

	/**
	 * Restituisce true se e solo se pte'un termine di processo normalizzato.
	 *
	 * @param pt
	 * @return
	 */
	public static boolean isNormalizedProcessTerm(ProcessTerm pt)
		{
		if (pt == null) return false;
		if (!isNormalizedExp(pt.getCondition())) return false;
		if (pt instanceof ActionProcess)
			{
			ActionProcess ap = (ActionProcess)pt;
			if (!isNormalizedAction(ap.getAzione())) return false;
			if (!isNormalizedProcessTerm(ap.getProcesso())) return false;
			}
		if (pt instanceof ChoiceProcess)
			{
			ChoiceProcess cp = (ChoiceProcess)pt;
			if (cp.getProcesses() != null)
				{
				for (int i = 0; i < cp.getProcesses().length; i++)
					{
					if (!isNormalizedProcessTerm(cp.getProcesses()[i])) return false;
					}
				}
			}
		if (pt instanceof BehavProcess)
			{
			BehavProcess bp = (BehavProcess)pt;
			if (bp.getExprs() != null)
				{
				for (int i = 0; i < bp.getExprs().length; i++)
					{
					if (!isNormalizedExp(bp.getExprs()[i])) return false;
					}
				}
			}
		return true;
		}

	/**
	 * Restituisce true se e solo se bee'un'equazione di comportamento normalizzata.
	 *
	 * @param be
	 * @return
	 */
	public static boolean isNormalizedBehavEquation(BehavEquation be)
		{
		if (be == null) return false;
		if (!isNormalizedIntestazione(be.getBehavHeader())) return false;
		if (!isNormalizedProcessTerm(be.getTermineProcesso())) return false;
		return true;
		}

	/**
	 * Restituisce true se e solo se ete'un tipo di elemento architetturale normalizzato.
	 *
	 * @param et
	 * @return
	 */
	public static boolean isNormalizedElemType(ElemType et)
		{
		if (et == null) return false;
		Header i = et.getHeader();
		if (!isNormalizedIntestazione(i)) return false;
		BehavEquation[] bes = et.getBehavior().getBehaviors();
		if (bes != null)
			{
			for (int j = 0; j < bes.length; j++)
				{
				if (!isNormalizedBehavEquation(bes[j])) return false;
				}
			}
		return true;
		}

	/**
	 * Restituisce true se e solo se ide'una dichiarazione di interazione normalizzata.
	 *
	 * @param id
	 * @return
	 */
	public static boolean isNormalizedInteractionDecl(InteractionDecl id)
		{
		if (id == null) return false;
		if (id instanceof InteractionDeclInd) return false;
		return true;
		}

	/**
	 * Restituisce true se e solo se aa contiene dichiarazioni di collegamenti normalizzati.
	 *
	 * @param aa
	 * @return
	 */
	public static boolean isNormalizedAttacs(ArchiAttachments aa)
		{
		if (aa == null) return false;
		AttacDecl[] ads = aa.getAttachments();
		if (ads != null)
			{
			for (int i = 0; i < ads.length; i++)
				{
				if (!isNormalizedAttacDecl(ads[i])) return false;
				}
			}
		return true;
		}

	/**
	 * Restituisce true se e solo se aeis contiene dichiarazioni di istanze normalizzate.
	 *
	 * @param aeis
	 * @return
	 */
	public static boolean isNormalizedInstances(ArchiElemInstances aeis)
		{
		if (aeis == null) return false;
		AEIdecl[] aeids = aeis.getAEIdeclSeq();
		if (aeids != null)
			{
			for (int i = 0; i < aeids.length; i++)
				{
				if (!isNormalizedAEIdecl(aeids[i])) return false;
				}
			}
		return true;
		}

	/**
	 * Restituisce true se e solo se ai contiene dichiarazioni di interazioni normalizzate.
	 *
	 * @param ai
	 * @return
	 */
	public static boolean isNormalizedInteractions(ArchiInteractions ai)
		{
		if (ai == null) return false;
		InteractionDecl[] ids = ai.getInteractions();
		if (ids != null)
			{
			for (int i = 0; i < ids.length; i++)
				{
				if (!isNormalizedInteractionDecl(ids[i])) return false;
				}
			}
		return true;
		}

	/**
	 * Restituisce true se e solo se aet contiene oggetti ElemType normalizzati.
	 *
	 * @param aet
	 * @return
	 */
	public static boolean isNormalizedArchiElemTypes(ArchiElemTypes aet)
		{
		if (aet == null) return false;
		ElemType[] ets = aet.getElementTypes();
		if (ets != null)
			{
			for (int i = 0; i < ets.length; i++)
				{
				if (!isNormalizedElemType(ets[i])) return false;
				}
			}
		return true;
		}

	/**
	 * Restituisce true se e solo se ate'una topologia con dichiarazioni normalizzate.
	 *
	 * @param at
	 * @return
	 */
	public static boolean isNormalizedTopology(ArchiTopology at)
		{
		if (at == null) return false;
		// si verifica se le interazioni sono normalizzate
		if (!isNormalizedInteractions(at.getArchiInteractions()))
			return false;
		// si verifica se i collegamenti sono normalizzati
		if (!isNormalizedAttacs(at.getAttachments()))
			return false;
		// si verifica se le dichiarazioni di istanze sono normalizzate
		if (!isNormalizedInstances(at.getAEIs()))
			return false;
		// se il tipo architetturale non contiene dichiarazioni indicizzate si restituice true
		return true;
		}

	/**
	 * Restituisce true se e solo se ate'un tipo architetturale normalizzato.
	 *
	 * @param at
	 * @return
	 */
	public static boolean isNormalizedAll(ArchiType at)
		{
		if (at == null) return false;
		if (!isNormalizedIntestazione(at.getArchiTypeHeader()))
			return false;
		if (!isNormalizedArchiElemTypes(at.getArchiElemTypes()))
			return false;
		if (!isNormalizedTopology(at.getTopologia()))
			return false;
		return true;
		}
}
