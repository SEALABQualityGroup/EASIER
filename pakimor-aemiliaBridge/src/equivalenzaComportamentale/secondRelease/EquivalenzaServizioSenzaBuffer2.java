package equivalenzaComportamentale.secondRelease;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.ElemType;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.Stop;
import utilities.ErrorMessage;
import equivalenzaComportamentale.AETinteractionsParts;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ArriveBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ExitBehaviorNoQNExit;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ExitBehaviorWithQNExit;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.PhaseBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

public class EquivalenzaServizioSenzaBuffer2 
	extends	EquivalenzaServizio2
	{
	
	public EquivalenzaServizioSenzaBuffer2(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public EquivalenzaServizioSenzaBuffer2(ElemType et) 
		{
		super();
		this.elemType = et;
		this.errorMessage = new ErrorMessage(0);
		}

	public boolean isServizioSenzaBuffer() 
		{
		/* MODELLED */
		// Il comportamento di un processo di servizio senza buffere'
		// definito dalla seguente sequenza di comportamenti:
		// 1. l'arrivo di jobs;
		// 2. una distribuzione di tipo fase;
		// 3. routing di jobs.
		// 4. le uniche azioni di input sono le azioni di arrivo;
		// 5. le uniche azioni di output sono le azioni di consegna.
		// 6. il comportamento di routinge'opzionale
		AETbehavior tbehavior = this.elemType.getBehavior();
		AETinteractions tinteractions = this.elemType.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		if (!tailRecursion.isTailRecursion())
			{
			// 1
			String string = this.elemType.toString() + " is not service process with no buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = tailRecursion.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehavior arriveBehavior = new ArriveBehavior(processTerm,tinteractions,this.depth + 1);
		if (!arriveBehavior.isArrivalBehavior())
			{
			// 2
			String string = this.elemType.toString() + " is not service process with no buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = arriveBehavior.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		// si preleva la differenza tra processTerm e processTerm2
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm, arriveBehavior.getMaximalArrivalBehavior());
		// alloco memoria per i nomi delle azioni di consegna
		List<String> listD = new ArrayList<String>();
		for (int i = 0; i < processTerms.size(); i++)
			{
			ProcessTerm processTerm3 = processTerms.get(i);
			PhaseBehavior phaseBehavior = new PhaseBehavior(processTerm3,tinteractions,this.depth + 1);
			// si preleva il comportamento di fase di phaseBehavior
			ProcessTerm processTerm4 = phaseBehavior.getPhaseBehavior();
			if (processTerm4 == null)
				{
				// 3
				String string = this.elemType.toString() + " is not service process with no buffer";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = phaseBehavior.getErrorMessage();
				list.add(errorMessage);
				return false;
				}
			// prelevo la differenza tra processTerm3 e processTerm4
			// processTerm4 deve essere un comportamento di fase massimo
			List<ProcessTerm> list = MetodiVari.differenza(processTerm3, phaseBehavior.getMaximalPhaseBehavior());
			// se list ha lunghezza maggiore di uno vuol dire che ho incontrato un comportamento
			// di fase di tipo hyperesponenziale
			for (int j = 0; j < list.size(); j++)
				{
				ExitBehaviorNoQNExit exitBehaviorNoQNExit2 = new ExitBehaviorNoQNExit(list.get(j),tinteractions,this.depth + 1);
				ExitBehaviorWithQNExit exitBehaviorWithQNExit2 = new ExitBehaviorWithQNExit(list.get(j),tinteractions,this.depth + 1);
				if (exitBehaviorNoQNExit2.isExitBehavior())
					{
					List<String> list2 = exitBehaviorNoQNExit2.getDeliverActionNames();
					listD.addAll(list2);
					}
				else if (exitBehaviorWithQNExit2.isExitBehavior())
					{
					List<String> list2 = exitBehaviorWithQNExit2.getDeliverActionNames();
					listD.addAll(list2);
					}
				else if (!(list.get(j) instanceof BehavProcess))
					{
					// 4
					String string = this.elemType.toString() + " is not service process with no buffer";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					String string2 = list.get(j).toString() + " is not behavior call process";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					ErrorMessage errorMessage3 = exitBehaviorNoQNExit2.getErrorMessage();
					ErrorMessage errorMessage4 = exitBehaviorWithQNExit2.getErrorMessage();
					list2.add(errorMessage);
					list2.add(errorMessage3);
					list2.add(errorMessage4);
					return false;
					}
				}
			}
		// Le uniche azioni di input sono le azioni di arrivo.
		List<String> list = arriveBehavior.getArriveNames();
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(tinteractions);
		List<String> list2 = tinteractionsParts.getInputInteractions();
		if (!list.containsAll(list2))
			{
			// 5
			String string = this.elemType.toString() + " is not service process with no buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string2 = "there are input interactions that are not arrive actions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list3.add(errorMessage);
			return false;
			}
		if (!list2.containsAll(list))
			{
			// 6
			String string = this.elemType.toString() + " is not service process with no buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string2 = "there are arrive actions that are not input interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list3.add(errorMessage);
			return false;
			}
		// Le uniche azioni di output sono le azioni di consegna.
		List<String> list3 = tinteractionsParts.getOutputInteractions();
		List<String> listD2 = MetodiVari.onlyNotNull(listD);
		if (!list3.containsAll(listD2))
			{
			// 7
			String string = this.elemType.toString() + " is not service process with no buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list4 = this.errorMessage.getCauses();
			String string2 = "there are deliver actions that are not output interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list4.add(errorMessage);
			return false;
			}
		if (!listD2.containsAll(list3))
			{
			// 8
			String string = this.elemType.toString() + " is not service process with no buffer";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list4 = this.errorMessage.getCauses();
			String string2 = "there are output interactions that are not deliver actions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list4.add(errorMessage);
			return false;
			}
		return true;
		}

	// restituisce un comportamento con le equazioni standard.
	// Ritorna null se et none'un processo di servizio senza buffer
	public AETbehavior getNormalizedBehavior()
		{
		if (!isServizioSenzaBuffer())
			return null;
		// allochiamo memoria per il comportamento risultato e le sue equazioni
		AETbehavior tbehaviorR = new AETbehavior();
		List<BehavEquation> listR = new ArrayList<BehavEquation>();
		AETbehavior tbehavior = this.elemType.getBehavior();
		AETinteractions tinteractions = this.elemType.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		ArriveBehavior arriveBehavior = new ArriveBehavior(processTerm,tinteractions,this.depth + 1);
		// si preleva il comportamento per l'arrivo
		ProcessTerm processTerm2 = arriveBehavior.getMaximalArrivalBehavior();
		// si preleva la differenza tra processTerm e processTerm2
		// processTerm2 deve essere un'equazione di arrivo massima
		List<ProcessTerm> processTerms = MetodiVari.differenza(processTerm, processTerm2);
		// alloco memoria per i comportamenti di fase
		List<ProcessTerm> listF = new ArrayList<ProcessTerm>();
		// alloco memoria per le equazioni di routing, una per ogni elemento di list
		List<ProcessTerm> listCR = new ArrayList<ProcessTerm>();
		for (ProcessTerm processTerm3 : processTerms)
			{
			PhaseBehavior phaseBehavior = new PhaseBehavior(processTerm3,tinteractions,this.depth + 1);
			// si preleva il comportamento di fase di phaseBehavior
			listF.add(phaseBehavior.getPhaseBehavior());
			List<ProcessTerm> list = MetodiVari.differenza(processTerm3, 
					phaseBehavior.getMaximalPhaseBehavior());
			// potrebbero esserci piu' comportamenti
			// di routing a causa della presenza di una distribuzione hyperesponziale
			if (!list.isEmpty())
				{
				for (ProcessTerm processTerm5 : list)
					{
					// bisogna verificare se ci sono BehavProcess o Stop.
					// In questo caso va aggiunto null
					if (processTerm5 instanceof Stop || processTerm5 instanceof BehavProcess)
						listCR.add(null);
					else
						{
						// processTerm5 deve essere un comportamento di routing senza azioni null
						ExitBehaviorNoQNExit exitBehaviorNoQNExit = new ExitBehaviorNoQNExit(processTerm5,tinteractions,this.depth + 1);
						ExitBehaviorWithQNExit exitBehaviorWithQNExit = new ExitBehaviorWithQNExit(processTerm5,tinteractions,this.depth + 1);
						if (exitBehaviorNoQNExit.isExitBehavior())
							listCR.add(exitBehaviorNoQNExit.getJobsRoutingBehavior());
						else if (exitBehaviorWithQNExit.isExitBehavior())
							listCR.add(exitBehaviorWithQNExit.getJobsRoutingBehavior());
						}
					}
				}
			else
				listCR.add(null);
			}
		// imposto l'equazione di arrivi
		ProcessTerm processTerm3s = arriveBehavior.getArrivalBehavior();
		List<BehavProcess> listS = computeLeaf(processTerm3s);
		ParamDeclaration[] declPars = new ParamDeclaration[2];
		declPars[0] = null;
		declPars[1] = null;
		Header header = new Header("Arrivo",declPars);
		for (int i = 0; i < listS.size(); i++)
			{
			BehavProcess behavProcess = listS.get(i);
			behavProcess.setExprs(new Expression[0]);
			behavProcess.setName("Fase"+(i+1));
			}
		BehavEquation behavEquation2 = new BehavEquation(header,processTerm3s);
		listR.add(behavEquation2);
		// imposto le equazioni di comportamento di fase
		int j = 0;
		for (int i = 0; i < listF.size(); i++)
			{
			ProcessTerm processTerm3 = listF.get(i);
			ProcessTerm processTerm4 = listCR.get(i);
			List<BehavProcess> list = computeLeaf(processTerm3);
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			declPars2[0] = null;
			declPars2[1] = null;
			Header intestazione2 = new Header("Fase"+(i+1),declPars2);
			for (BehavProcess behavProcess : list)
				{
				behavProcess.setExprs(new Expression[0]);
				// puo' esserci una sola equazione di routing per ogni equazione di fase
				// l'equazione di routinge'opzionale
				if (processTerm4 != null)
					{
					behavProcess.setName("Routing"+(j+1));
					j++;
					}
				else
					behavProcess.setName("Arrivo");
				}
			BehavEquation behavEquation3 = new BehavEquation(intestazione2,processTerm3);
			listR.add(behavEquation3);
			}
		// imposto le equazioni di routing
		int k = 1;
		for (int i = 0; i < listCR.size(); i++)
			{
			ProcessTerm processTerm3 = listCR.get(i);
			if (processTerm3 != null)
				{
				List<BehavProcess> list = computeLeaf(processTerm3);
				ParamDeclaration[] declPars2 = new ParamDeclaration[2];
				declPars2[0] = null;
				declPars2[1] = null;
				Header intestazione2 = new Header("Routing"+k,declPars2);
				k++;
				for (BehavProcess behavProcess : list)
					{
					behavProcess.setExprs(new Expression[0]);
					behavProcess.setName("Arrivo");
					}
				BehavEquation behavEquation3 = new BehavEquation(intestazione2,processTerm3);
				listR.add(behavEquation3);
				}
			}
		// imposto le equazioni per il comportamento risultato
		BehavEquation[] behavEquations2 = new BehavEquation[listR.size()];
		listR.toArray(behavEquations2);
		tbehaviorR.setBehaviors(behavEquations2);
		return tbehaviorR;		
		}

	@Override
	public boolean isEquivalente() 
		{
		return isServizioSenzaBuffer();
		}

	@Override
	public EquivalenzaServizioSenzaBuffer2 copy() 
		{
		EquivalenzaServizioSenzaBuffer2 equivalenzaServizioSenzaBuffer2 = new EquivalenzaServizioSenzaBuffer2(this.depth);
		// elemType setting
		ElemType elemTypeCloned = this.elemType.copy();
		equivalenzaServizioSenzaBuffer2.elemType = elemTypeCloned;
		return equivalenzaServizioSenzaBuffer2;
		}

	@Override
	public void print() 
		{
		System.out.println("EquivalenzaServizioSenzaBuffer2 Object");
		// elemType printing
		System.out.println("ElemType:");
		this.elemType.print();
		}
	}
