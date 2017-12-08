package valutazione;

import java.util.Vector;

import specificheAEmilia.AEIdecl;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.ArchiAttachments;
import specificheAEmilia.ArchiElemInstances;
import specificheAEmilia.ArchiElemTypes;
import specificheAEmilia.ArchiInteractions;
import specificheAEmilia.ArchiTopology;
import specificheAEmilia.ArchiType;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ElemType;
import specificheAEmilia.InteractionDecl;
import specificheAEmilia.Header;
import specificheAEmilia.ProcessTerm;

/**
 * Utilizzata per ottenere delle parti di una specifica AEmilia.
 * 
 * @author Mirko
 *
 */
public class ArchiTypeParts {

	/**
	 * ate'una rappresentazione ad oggetti di una specifica AEmilia.
	 */
	private ArchiType at;

	public ArchiTypeParts(ArchiType at) 
		{
		super();
		this.at = at;
		}

	/**
	 * Restituisce un'array di tutte le intestazioni dei comportamenti,
	 * presenti nel tipo architetturale.
	 *
	 * @return
	 */
	public Header[] getBehavHeaders() {
	Vector<Header> v = new Vector<Header>();
	ArchiElemTypes aets = this.at.getArchiElemTypes();
	ElemType[] ets = aets.getElementTypes();
	for (int i = 0; i < ets.length; i++)
	    {
	    AETbehavior b = ets[i].getBehavior();
	    BehavEquation[] bes = b.getBehaviors();
	    for (int j = 0; j < bes.length; j++)
	        {
	        v.add(bes[j].getBehavHeader());
	        }
	    }
	Header[] ris = new Header[v.size()];
	v.toArray(ris);
	return ris;
	}

	/**
	 * Restituisce un'array di tutte le equazioni comportamentali,
	 * presenti nel tipo architetturale.
	 *
	 * @return
	 */
	public BehavEquation[] getBehavHequations() {
	Vector<BehavEquation> v = new Vector<BehavEquation>();
	ArchiElemTypes aets = this.at.getArchiElemTypes();
	ElemType[] ets = aets.getElementTypes();
	for (int i = 0; i < ets.length; i++)
	    {
	    AETbehavior b = ets[i].getBehavior();
	    BehavEquation[] bes = b.getBehaviors();
	    for (int j = 0; j < bes.length; j++)
	        {
	        v.add(bes[j]);
	        }
	    }
	BehavEquation[] ris = new BehavEquation[v.size()];
	v.toArray(ris);
	return ris;
	}

	/**
	 * Restituisce un array di tutti i tipi di elementi architetturali,
	 * presenti nel tipo architetturale.
	 *
	 * @return
	 */
	public ElemType[] getElemTypes() {
	return this.at.getArchiElemTypes().getElementTypes();
	}

	/**
	 * Restituisce un array, che contiene tutti i termini di processo,
	 * presenti nel tipo architetturale.
	 *
	 * @return
	 */
	public ProcessTerm[] getProcessTerms() {
	Vector<ProcessTerm> v = new Vector<ProcessTerm>();
	ArchiElemTypes aets = this.at.getArchiElemTypes();
	ElemType[] ets = aets.getElementTypes();
	for (int i = 0; i < ets.length; i++)
	    {
	    AETbehavior b = ets[i].getBehavior();
	    BehavEquation[] bes = b.getBehaviors();
	    for (int j = 0; j < bes.length; j++)
	        {
	        v.add(bes[j].getTermineProcesso());
	        }
	    }
	v.trimToSize();
	ProcessTerm[] pts = new ProcessTerm[v.size()];
	v.toArray(pts);
	return pts;
	}

	/**
	 * Restituisce un array di tutte le dichiarazioni di AEI, presenti nel
	 * tipo architetturale.
	 *
	 * @return
	 */
	public AEIdecl[] getAEIdecls() {
	ArchiTopology at = this.at.getTopologia();
	ArchiElemInstances aeis = at.getAEIs();
	return aeis.getAEIdeclSeq();
	}

	/**
	 * Restituisce un array di tutte le dichiarazioni di collegamenti tra
	 * elementi, presenti nel tipo architetturale.
	 *
	 * @return
	 */
	public AttacDecl[] getAttacDecls() {
	ArchiTopology at = this.at.getTopologia();
	ArchiAttachments aa = at.getAttachments();
	return aa.getAttachments();
	}

	/**
	 * Restituisce un array di tutte le dichiarazioni di interazioni
	 * architetturali.
	 * @return
	 */
	public InteractionDecl[] getInteractionDecls() {
	ArchiTopology at = this.at.getTopologia();
	ArchiInteractions ais = at.getArchiInteractions();
	return ais.getInteractions();
	}

	public ArchiType getAt() {
		return at;
	}

	public void setAt(ArchiType at) {
		this.at = at;
	}
	
	}