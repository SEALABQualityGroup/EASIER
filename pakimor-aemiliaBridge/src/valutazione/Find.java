package valutazione;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import restrizioniSpecifiche.associative.ActionTypeBehavEquationRel;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIident;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.ANDinteractions;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.ArchiElemInstances;
import specificheAEmilia.ArchiElemTypes;
import specificheAEmilia.ArchiTopology;
import specificheAEmilia.ArchiType;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.ElemType;
import specificheAEmilia.Header;
import specificheAEmilia.InputInteractions;
import specificheAEmilia.ORinteractions;
import specificheAEmilia.OutputInteractions;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.Stop;
import specificheAEmilia.UNIinteractions;

/**
 * Classe statica che contiene metodi che restituisce una parte
 * di una specifica chee'in relazione con altre parti della stessa
 * specifica.
 *
 * @author Mirko
 *
 */
public class Find {

	/**
	 * Restituisce il tipo di elemento architetturale con il nome
	 * fornito come parametro.
	 *
	 * @param nomeAet
	 * @return
	 */
	public static ElemType getElemType(String nomeAet, ArchiType at)
		{
		ArchiElemTypes aets = at.getArchiElemTypes();
		ElemType[] ets = aets.getElementTypes();
		ElemType et = null;
		for (int i = 0; i < ets.length; i++)
			{
			String temp = ets[i].getHeader().getName();
			if (temp.equals(nomeAet)) et = ets[i];
			}
		return et;
		}

	/**
	 * Restituisce il tipo di elemento architetturale, a cui l'istanza
	 * con nome nomeAei appartiene, o null, se nomeAei non ha un tipo di
	 * elemento architetturale associato.
	 *
	 * @param nomeAei
	 * @return
	 */
	public static ElemType getElemTypeFromAei(AEIident nomeAei, ArchiType at)
		{
		// si preleva il nome del tipo di elemento
		// architetturale dall'istanza
		ArchiTopology ato = at.getTopologia();
		ArchiElemInstances aeis = ato.getAEIs();
		AEIdecl[] aeids = aeis.getAEIdeclSeq();
		String nomeAet = new String();
		for (int i = 0; i < aeids.length; i++)
			{
			AEIident aeIident = aeids[i].getAeIident();
			if (nomeAei.equals(aeIident))
				nomeAet = aeids[i].getAET();
			}
		// si preleva il tipo di elemento architetturale
		// dal nome del tipo di elemento dell'istanza
		ArchiElemTypes aets = at.getArchiElemTypes();
		ElemType[] ets = aets.getElementTypes();
		ElemType ris = null;
		for (int i = 0; i < ets.length; i++)
			{
			if (nomeAet.equals(ets[i].getHeader().getName()))
				ris = ets[i];
			}
		return ris;
		}

	/**
	 * Restituisce il tipo di elemento architetturale, a cui pt appartiene, o null, se pt non ha
	 * un ElemType a cui appartiene.
	 *
	 * @param pt
	 * @param at
	 * @return
	 */
	public static ElemType getElemTypeFromProcessTerm(ProcessTerm pt, ArchiType at)
		throws Exception
		{
		// rise'il risultato del metodo
		ElemType ris = null;
		// c conta il numero di pt. Se c > 1 si lancia un'eccezione.
		// Si prelevano i tipi di elementi architetturali.
		int c = 0;
		ElemType[] ets = at.getArchiElemTypes().getElementTypes();
		// si effettua la ricerca di pt all'interno degli elementi
		for (int i = 0; i < ets.length; i++)
			{
			BehavEquation[] bes = ets[i].getBehavior().getBehaviors();
			// si effettua la ricerca di pt all'interno dei comportamenti
			for (int j = 0; j < bes.length; j++)
				{
				if (bes[j].getTermineProcesso().equals(pt))
					{
					ris = ets[i];
					c++;
					}
				}
			}
		if (c > 1) throw new Exception("Process term in more than one behavior");
		return ris;
		}

	/**
	 * Restituisce l'intestazione del comportamento a cui pt appartiene, o null, se pt non ha
	 * un comportamento associato.
	 *
	 * @param pt
	 * @param at
	 * @return
	 * @throws Exception
	 */
	public static Header getBehavHeaderFromProcessTerm(ProcessTerm pt, ArchiType at)
		throws Exception
		{
		// rise'il risultato del metodo
		Header ris = null;
		// c conta il numero di pt. Se c > 1 si lancia un'eccezione.
		// Si prelevano i tipi di elementi architetturali
		int c = 0;
		ElemType[] ets = at.getArchiElemTypes().getElementTypes();
		// si effettua la ricerca di pt all'interno degli elementi
		for (int i = 0; i < ets.length; i++)
			{
			BehavEquation[] bes = ets[i].getBehavior().getBehaviors();
			// si effettua la ricerca di pt all'interno dei comportamenti
			for (int j = 0; j < bes.length; j++)
				{
				if (bes[j].getTermineProcesso().equals(pt))
					{
					ris = bes[j].getBehavHeader();
					c++;
					}
				}
			}
		if (c > 1) throw new Exception("Process term in more than one behavior");
		return ris;
		}

	/**
	 * Restituisce l'equazione comportamentale a cui pt appartiene, o null, se pt non ha
	 * un comportamento associato.
	 *
	 * @param pt
	 * @param at
	 * @return
	 * @throws Exception
	 */
	public static BehavEquation getBehavHequationFromProcessTerm(ProcessTerm pt, ArchiType at)
		throws Exception
		{
		// rise'il risultato del metodo
		BehavEquation ris = null;
		// c conta il numero di pt. Se c > 1 si lancia un'eccezione.
		// Si prelevano i tipi di elementi architetturali
		int c = 0;
		ElemType[] ets = at.getArchiElemTypes().getElementTypes();
		// Si effettua la ricerca di pt all'interno degli elementi
		for (int i = 0; i < ets.length; i++)
			{
			BehavEquation[] bes = ets[i].getBehavior().getBehaviors();
			// Si effettua la ricerca di pt all'interno dei comportamenti
			for (int j = 0; j < bes.length; j++)
				{
				if (bes[j].getTermineProcesso().equals(pt))
					{
					ris = bes[j];
					c++;
					}
				}
			}
		if (c > 1) throw new Exception("Process term in more than one behavior");
		return ris;
		}

	/**
	 * Restituisce il tipo di elemento architetturale a cui i appartiene, o null, se i non ha un
	 * tipo di elemento architetturale di appartenenza.
	 *
	 * @param i
	 * @param at
	 * @return
	 */
	public static ElemType getElemTypeFromBehavHeader(Header i, ArchiType at)
		throws Exception
		{
		// rise'il risultato del metodo
		ElemType ris = null;
		ElemType[] ets = at.getArchiElemTypes().getElementTypes();
		// si effettua la ricerca di i all'interno degli elementi
		int cont = 0;
		for (int j = 0; j < ets.length; j++)
			{
			BehavEquation[] bes = ets[j].getBehavior().getBehaviors();
			// si effettua la ricerca di i all'interno dei comportamenti
			for (int k = 0; k < bes.length; k++)
				{
				if (bes[k].getBehavHeader().equals(i))
					{
					ris = ets[j];
					cont++;
					}
				}
			}
		if (cont > 1) throw new Exception(
				"Behavior header in more than one architectural element type");
		return ris;
		}

	/**
	 * Restituisce i termini di processo del tipo di elemento architetturale dell'istanza dichiarata in aeid.
	 *
	 * @param aeid
	 * @param at
	 * @return
	 */
	public static ProcessTerm[] getProcessTermsFromAEI(AEIdecl aeid, ArchiType at)
		{
		// si preleva il tipo di elemento architetturale dalla dichiarazione di istanza
		AEIident aeIident = aeid.getAeIident();
		ElemType et = getElemTypeFromAei(aeIident, at);
		// si prelevano le equazioni comportamentali
		BehavEquation[] bes = et.getBehavior().getBehaviors();
		// si alloca memoria per l'array risultato
		ProcessTerm[] ris = new ProcessTerm[bes.length];
		// si prelevano i termini di processo
		for (int i = 0; i < bes.length; i++)
			{
			ris[i] = bes[i].getTermineProcesso();
			}
		return ris;
		}

	/**
	 * Restituisce le intestazioni dei comportamenti del tipo di elemento architetturale
	 * dell'istanza dichiarata in aeid.
	 *
	 * @param aeid
	 * @param at
	 * @return
	 */
	public static Header[] getBehavHeadersFromAEI(AEIdecl aeid, ArchiType at)
		{
		// si preleva il tipo di elemento architetturale dalla dichiarazione di istanza
		AEIident aeIident = aeid.getAeIident();
		ElemType et = getElemTypeFromAei(aeIident, at);
		// si prelevano le equazioni comportamentali
		BehavEquation[] bes = et.getBehavior().getBehaviors();
		// si alloca memoria per l'array risultato
		Header[] ris = new Header[bes.length];
		// si prelevano le intestazioni dei comportamenti
		for (int i = 0; i < bes.length; i++)
			{
			ris[i] = bes[i].getBehavHeader();
			}
		return ris;
		}

	/**
	 * Restituisce true se e solo se ie'l'intestazione di un'equazione comportamentale
	 * appartenente a et.
	 *
	 * @param i
	 * @param et
	 * @return
	 */
	public static boolean isIntestazioneInElemType(Header i,ElemType et)
		{
		boolean ris = false;
		BehavEquation[] bes =
			et.getBehavior().getBehaviors();
		for (int j = 0; j < bes.length; j++)
			{
			Header in = bes[j].getBehavHeader();
			if (in.equals(i)) ris = true;
			}
		return ris;
		}

	/**
	 * Restituisce una dichiarazione di istanza che hanno nomeAEI come istanza.
	 *
	 * @param nomeAEI
	 * @param at
	 * @return
	 */
	public static AEIdecl getAEIdeclFromAei(AEIident nomeAEI, ArchiType at)
		{
		AEIdecl ris = null;
		// si prelevano le dichiarazioni di istanze
		AEIdecl[] aeids = at.getTopologia().getAEIs().getAEIdeclSeq();
		// si cercano tutte quelle dichiarazioni che hanno nomeAEI come
		// nome di istanza
		for (int i = 0; i < aeids.length; i++)
			{
			AEIident aeIident = aeids[i].getAeIident();  
			if (aeIident.equals(nomeAEI))
				ris = aeids[i];
			}
		return ris;
		}

	/**
	 * Restituisce una lista di tassi delle azioni con nome nameAction, presenti all'interno delle equazioni
	 * comportamentali di un tipo di elemento architetturale.
	 *
	 * @param nomeAction
	 * @param elemType
	 * @return
	 */
	public static List<ActionRate> getRatesFromActionName(String nameAction, ElemType elemType)
		{
		List<ActionRate> list = new ArrayList<ActionRate>();
		AETbehavior tbehavior = elemType.getBehavior();
		BehavEquation[] behavEquations = tbehavior.getBehaviors();
		for (int i = 0; i < behavEquations.length; i++)
			{
			ProcessTerm processTerm = behavEquations[i].getTermineProcesso();
			if (processTerm instanceof ActionProcess)
				{
				ActionProcess actionProcess = (ActionProcess) processTerm;
				Action action = actionProcess.getAzione();
				ActionType actionType = action.getType();
				ActionRate actionRate = action.getRate();
				if (actionType.getName().equals(nameAction))
					list.add(actionRate);
				ProcessTerm processTerm2 = actionProcess.getProcesso();
				List<ActionRate> list2 = getRatesFromActionNamePT(nameAction,processTerm2);
				list.addAll(list2);
				}
			if (processTerm instanceof ChoiceProcess)
				{
				ChoiceProcess choiceProcess = (ChoiceProcess)processTerm;
				ProcessTerm[] processTerms = choiceProcess.getProcesses();
				for (ProcessTerm processTerm2 : processTerms)
					{
					List<ActionRate> list2 = getRatesFromActionNamePT(nameAction,processTerm2);
					list.addAll(list2);
					}
				}
			}
		return list;
		}

	/**
	 * Restituisce i tassi delle azioni di processTerm con nome nameAction.
	 * s
	 * @param nameAction
	 * @param processTerm
	 * @return
	 */
	private static List<ActionRate> getRatesFromActionNamePT(String nameAction,
			ProcessTerm processTerm) 
		{
		if (processTerm instanceof ActionProcess)
			{
			List<ActionRate> list = new ArrayList<ActionRate>();
			ActionProcess actionProcess = (ActionProcess)processTerm;
			Action action = actionProcess.getAzione();
			ActionType actionType = action.getType();
			ActionRate actionRate = action.getRate();
			if (actionType.getName().equals(nameAction))
				list.add(actionRate);
			ProcessTerm processTerm2 = actionProcess.getProcesso();
			List<ActionRate> list2 = getRatesFromActionNamePT(nameAction,processTerm2);
			list.addAll(list2);
			return list;
			}
		else if (processTerm instanceof ChoiceProcess)
			{
			List<ActionRate> list = new ArrayList<ActionRate>();
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm2 : processTerms)
				{
				List<ActionRate> list2 = getRatesFromActionNamePT(nameAction, processTerm2);
				list.addAll(list2);
				}
			return list;
			}
		else  
			{
			List<ActionRate> list = new ArrayList<ActionRate>();
			return list;
			}
		}

	/**
	 * Restituisce la dichiarazione di istanza di aeisn che ha nomeAEI come nome di istanza.
	 *  
	 * @param nomeAEI
	 * @param aeisn
	 * @return
	 */
	public static AEIdecl getAEIdeclFromAei(AEIident nomeAEI,
			ArchiElemInstances aeisn) 
		{
		AEIdecl ris = null;
		// si prelevano le dichiarazioni di istanze
		AEIdecl[] aeids = aeisn.getAEIdeclSeq();
		// si cercano tutte quelle dichiarazioni che hanno nomeAEI come
		// nome di istanza
		for (int i = 0; i < aeids.length; i++)
			{
			AEIident aeIident = aeids[i].getAeIident();
			if (aeIident.equals(nomeAEI))
				ris = aeids[i];
			}
		return ris;
		}

	/**
	 * Restituisce l'equazione comportamentale di elemType corrispondente alla chiamata behavProcess.
	 * Se non trova tale equazione restituisce null.
	 * 
	 * @param elemType
	 * @param behavProcess
	 * @return
	 */
	public static BehavEquation getBehavEquationFromBehavProcess(ElemType elemType, BehavProcess behavProcess)
		{
		String string = behavProcess.getName();
		AETbehavior tbehavior = elemType.getBehavior();
		BehavEquation[] behavEquations = tbehavior.getBehaviors();
		for (BehavEquation behavEquation : behavEquations)
			{
			Header header = behavEquation.getBehavHeader();
			String string2 = header.getName();
			if (string.equals(string2))
				return behavEquation;
			}
		return null;
		}
	
	public static List<Action> getActionsFromAETBehavior(AETbehavior tbehavior)
		{
		List<Action> list = new ArrayList<Action>();
		BehavEquation[] behavEquations = tbehavior.getBehaviors();
		for (BehavEquation behavEquation : behavEquations)
			{
			ProcessTerm processTerm = behavEquation.getTermineProcesso();
			List<Action> list2 = getActionsAETBehaviorRec(processTerm);
			list.addAll(list2);
			}
		return list;
		}

	private static List<Action> getActionsAETBehaviorRec(ProcessTerm processTerm) 
		{
		List<Action> list = new ArrayList<Action>();
		if (processTerm instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm;
			Action action = actionProcess.getAzione();
			list.add(action);
			ProcessTerm processTerm2 = actionProcess.getProcesso();
			List<Action> list2 = getActionsAETBehaviorRec(processTerm2);
			list.addAll(list2);
			return list; 
			}
		if (processTerm instanceof BehavProcess)
			{
			return list;
			}
		if (processTerm instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm2 : processTerms)
				{
				List<Action> list2 = getActionsAETBehaviorRec(processTerm2);
				list.addAll(list2);
				}
			return list;
			}
		if (processTerm instanceof Stop)
			{
			return list;
			}
		return null;
		}

	public static Set<String> getActionNamesFromAETBehavior(AETbehavior tbehavior)
		{
		Set<String> set = new HashSet<String>();
		BehavEquation[] behavEquations = tbehavior.getBehaviors();
		for (BehavEquation behavEquation : behavEquations)
			{
			ProcessTerm processTerm = behavEquation.getTermineProcesso();
			Set<String> set2 = getActionNamesAETBehaviorRec(processTerm);
			set.addAll(set2);
			}
		return set;
		}
	
	private static Set<String> getActionNamesAETBehaviorRec(ProcessTerm processTerm) 
		{
		Set<String> set = new HashSet<String>();
		if (processTerm instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm;
			Action action = actionProcess.getAzione();
			ActionType actionType = action.getType();
			String string = actionType.getName();
			set.add(string);
			ProcessTerm processTerm2 = actionProcess.getProcesso();
			Set<String> set2 = getActionNamesAETBehaviorRec(processTerm2);
			set.addAll(set2);
			return set; 
			}
		if (processTerm instanceof BehavProcess)
			{
			return set;
			}
		if (processTerm instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm2 : processTerms)
				{
				Set<String> set2 = getActionNamesAETBehaviorRec(processTerm2);
				set.addAll(set2);
				}
			return set;
			}
		if (processTerm instanceof Stop)
			{
			return set;
			}
		return null;
		}
	
	public static Set<String> getInteractionNamesFromAETinteractions(AETinteractions tinteractions)
		{
		Set<String> set = new HashSet<String>();
		InputInteractions inputInteractions = tinteractions.getInIn();
		OutputInteractions outputInteractions = tinteractions.getOuIn();
		if (inputInteractions != null)
			{
			ANDinteractions dinteractions = inputInteractions.getAnd();
			ORinteractions rinteractions = inputInteractions.getOr();
			UNIinteractions iinteractions = inputInteractions.getUni();
			if (dinteractions != null)
				{
				String[] strings = dinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			if (rinteractions != null)
				{
				String[] strings = rinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			if (iinteractions != null)
				{
				String[] strings = iinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			}
		if (outputInteractions != null)
			{
			ANDinteractions dinteractions = outputInteractions.getAnd();
			ORinteractions rinteractions = outputInteractions.getOr();
			UNIinteractions iinteractions = outputInteractions.getUni();
			if (dinteractions != null)
				{
				String[] strings = dinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			if (rinteractions != null)
				{
				String[] strings = rinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			if (iinteractions != null)
				{
				String[] strings = iinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			}
		return set;
		}

	public static Set<String> getInputInteractionNamesFromAETinteractions(AETinteractions tinteractions)
		{
		Set<String> set = new HashSet<String>();
		InputInteractions inputInteractions = tinteractions.getInIn();
		if (inputInteractions != null)
			{
			ANDinteractions dinteractions = inputInteractions.getAnd();
			ORinteractions rinteractions = inputInteractions.getOr();
			UNIinteractions iinteractions = inputInteractions.getUni();
			if (dinteractions != null)
				{
				String[] strings = dinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			if (rinteractions != null)
				{
				String[] strings = rinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			if (iinteractions != null)
				{
				String[] strings = iinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			}
		return set;
		}

	public static Set<String> getOutputInteractionNamesFromAETinteractions(AETinteractions tinteractions)
		{
		Set<String> set = new HashSet<String>();
		OutputInteractions outputInteractions = tinteractions.getOuIn();
		if (outputInteractions != null)
			{
			ANDinteractions dinteractions = outputInteractions.getAnd();
			ORinteractions rinteractions = outputInteractions.getOr();
			UNIinteractions iinteractions = outputInteractions.getUni();
			if (dinteractions != null)
				{
				String[] strings = dinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			if (rinteractions != null)
				{
				String[] strings = rinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			if (iinteractions != null)
				{
				String[] strings = iinteractions.getActions();
				for (String string : strings)
					{
					set.add(string);
					}
				}
			}
		return set;
		}
	
	public static List<ActionTypeBehavEquationRel> getActionTypeFromInteractionName(String string, AETbehavior tbehavior)
		{
		List<ActionTypeBehavEquationRel> list = new ArrayList<ActionTypeBehavEquationRel>();
		BehavEquation[] behavEquations = tbehavior.getBehaviors();
		for (BehavEquation behavEquation : behavEquations)
			{
			ProcessTerm processTerm = behavEquation.getTermineProcesso();
			List<ActionType> list2 = getActionTypeFromInteractionNameRec(string, processTerm);
			for (ActionType actionType : list2)
				{
				ActionTypeBehavEquationRel actionTypeBehavEquationRel = new ActionTypeBehavEquationRel(actionType,behavEquation);
				list.add(actionTypeBehavEquationRel);
				}
			}
		return list;
		}
	
	private static List<ActionType> getActionTypeFromInteractionNameRec(String string, ProcessTerm processTerm) 
		{
		List<ActionType> list = new ArrayList<ActionType>();
		if (processTerm instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm;
			Action action = actionProcess.getAzione();
			ActionType actionType = action.getType();
			String string2 = actionType.getName();
			if (string.equals(string2))
				list.add(actionType);
			ProcessTerm processTerm2 = actionProcess.getProcesso();
			List<ActionType> list2 = getActionTypeFromInteractionNameRec(string, processTerm2);
			list.addAll(list2);
			return list; 
			}
		if (processTerm instanceof BehavProcess)
			{
			return list;
			}
		if (processTerm instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm2 : processTerms)
				{
				List<ActionType> list2 = getActionTypeFromInteractionNameRec(string, processTerm2);
				list.addAll(list2);
				}
			return list;
			}
		if (processTerm instanceof Stop)
			{
			return list;
			}
		return null;
		}
	
	public static List<Action> getActionFromInteraction(String string, AETbehavior aeTbehavior)
		{
		List<Action> actions = new ArrayList<Action>();
		BehavEquation[] behavEquations = aeTbehavior.getBehaviors();
		for (int i = 0; i < behavEquations.length; i++)
			{
			BehavEquation behavEquation = behavEquations[i];
			ProcessTerm processTerm = behavEquation.getTermineProcesso();
			List<Action> actions2 = getActionFromInteractionRec(string, processTerm);
			actions.addAll(actions2);
			}
		return actions;
		}
	
	private static List<Action> getActionFromInteractionRec(String string, ProcessTerm processTerm) 
		{
		List<Action> list = new ArrayList<Action>();
		if (processTerm instanceof ActionProcess)
			{
			ActionProcess actionProcess = (ActionProcess)processTerm;
			Action action = actionProcess.getAzione();
			ActionType actionType = action.getType();
			String string2 = actionType.getName();
			if (string.equals(string2))
				list.add(action);
			ProcessTerm processTerm2 = actionProcess.getProcesso();
			List<Action> list2 = getActionFromInteractionRec(string, processTerm2);
			list.addAll(list2);
			return list; 
			}
		if (processTerm instanceof BehavProcess)
			{
			return list;
			}
		if (processTerm instanceof ChoiceProcess)
			{
			ChoiceProcess choiceProcess = (ChoiceProcess)processTerm;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm2 : processTerms)
				{
				List<Action> list2 = getActionFromInteractionRec(string, processTerm2);
				list.addAll(list2);
				}
			return list;
			}
		if (processTerm instanceof Stop)
			{
			return list;
			}
		return null;
		}

	/**
	 * Restituisce una lista di tutti gli oggetti ChoiceProcess presenti all'interno
	 * del comportamento di un tipo di elemento architetturale.
	 *
	 * @param elemType
	 * @return
	 */
//	public static List<ChoiceProcess> getChoiceProcess(ElemType elemType)
//		{
//		List<ChoiceProcess> list = new ArrayList<ChoiceProcess>();
//		AETbehavior tbehavior = elemType.getComportamento();
//		BehavEquation[] behavEquations = tbehavior.getComportamenti();
//		for (int i = 0; i < behavEquations.length; i++)
//			{
//			ProcessTerm processTerm = behavEquations[i].getTermineProcesso();
//
//			}
//		}
//
//	private List<ChoiceProcess> getChoiceProcess(ProcessTerm processTerm)
//		{
//		List<ChoiceProcess> list = new ArrayList<ChoiceProcess>();
//		if (processTerm instanceof ActionProcess)
//			{
//			ActionProcess actionProcess = (ActionProcess) processTerm;
//			list.addAll(getChoiceProcess(actionProcess.getProcesso()));
//			}
//		if (processTerm instanceof ChoiceProcess)
//			{
//			ChoiceProcess choiceProcess = (ChoiceProcess) processTerm;
//			list.add(choiceProcess);
//			for (int i = 0; i < choiceProcess.getProcessi().length; i++)
//				{
//
//				}
//			}
//		}
}
