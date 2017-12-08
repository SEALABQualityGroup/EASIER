package equivalenzaComportamentale.secondRelease;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Header;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.VarInit;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.GetBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.NullBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.PutBehavior;

public abstract class EquivalenzaBuffer2 
	extends Equivalenza2
	{

	public EquivalenzaBuffer2() {
		super();
	}

	/**
	 * Restituisce l'array con la dichiarazione dei parametri inizializzati.
	 * 
	 * @param i
	 * @return
	 */
	public VarInit[] capDecl(Header i) 
		{
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

	/**
	 * Restituisce un'array contenente i nomi delle azioni di estrazioni di clienti
	 * di ogni classe di clienti dal buffer.
	 * @param interactions
	 *
	 * @return
	 */
	protected String[] getGetsP(AETinteractions interactions) 
		{
		// si alloca memoria per l'array risultato
		List<String> ris = new ArrayList<String>();
		BehavEquation beg =
			elemType.getBehavior().getBehaviors()[0];
		// si preleva lo ChoiceProcess del comportamento
		ProcessTerm processTerm1 = beg.getTermineProcesso();
		AETinteractions tinteractions = elemType.getInteractions();
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
			GetBehavior getBehavior = new GetBehavior(declPars, processTerm, interactions);
			if (getBehavior.isGetBehavior())
				{
				String string = getBehavior.nameAction();
				ris.add(string);
				}
			}
		String[] strings = new String[ris.size()];
		ris.toArray(strings);
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
	protected String[] getPutsP(AETinteractions interactions) 
		{
		// si alloca memoria per l'array risultato
		List<String> ris = new ArrayList<String>();
		BehavEquation beg = this.elemType.getBehavior().getBehaviors()[0];
		// si preleva lo ChoiceProcess del comportamento
		ProcessTerm processTerm1 = beg.getTermineProcesso();
		AETinteractions tinteractions = this.elemType.getInteractions();
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
			PutBehavior putBehavior = new PutBehavior(declPars, processTerm, interactions);
			if (putBehavior.isPutBehavior())
				{
				String string = putBehavior.getPutName();
				ris.add(string);
				}
			}
		String[] strings = new String[ris.size()];
		ris.toArray(strings);
		return strings;
		}
	
	}	