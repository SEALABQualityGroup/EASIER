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
import utilities.ErrorMessage;
import equivalenzaComportamentale.AETinteractionsParts;
import equivalenzaComportamentale.MetodiVari;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.BeginningExitBehaviorNoQNExit;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.PhaseBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ReturnBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

public class EquivalenzaArriviFiniti2 
	extends EquivalenzaArrivi2 
	{
	
	public EquivalenzaArriviFiniti2(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public EquivalenzaArriviFiniti2(ElemType et1) 
		{
		super();
		this.setEt(et1);
		this.errorMessage = new ErrorMessage(0);
		}

	public boolean isProcessoArriviFiniti() 
		{
		/* MODELLED */
		// Il comportamento di un processo di arrivi finiti 
		//e'definito dalla seguente sequenza di 
		// comportamenti:
		// 1.una distribuzione di tipo fase;
		// trasformiamo il comportamento
		// elemType in uno tail recursion
		ElemType elemType2 = this.elemType;
		AETbehavior tbehavior = elemType2.getBehavior();
		AETinteractions tinteractions = elemType2.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		if (!tailRecursion.isTailRecursion())
			{
			// 1
			String string = this.elemType.toString() + " is not finite arrival process";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = tailRecursion.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehavior phaseBehavior = new PhaseBehavior(processTerm,tinteractions,this.depth + 1);
		// se processTerm2e'null, allora non abbiamo trovato un comportamento di tipo fase
		if (phaseBehavior.getPhaseBehavior() == null)
			{
			// 2
			String string = this.elemType.toString() + " is not finite arrival process";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = phaseBehavior.getErrorMessage(); 
			list.add(errorMessage);
			return false;
			}
		// 2.il routing di jobs;
		// preleviamo la differenza tra processTerm e processTerm2
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, phaseBehavior.getMaximalPhaseBehavior());
		// se list ha cardinalit� maggiore di 1 vuol dire che il comportamentoe'di tipo 
		// Hyperesponenziale.
		// allochiamo memoria per i nomi delle azioni di ritorno
		List<String> listRit = new ArrayList<String>();
		// allochiamo memoria per i nomi delle azioni di consegna
		List<String> listDel = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++)
			{
			ProcessTerm processTerm3 = list.get(i);
			ProcessTerm processTerm4 = null;
			BeginningExitBehaviorNoQNExit beginningExitBehaviorNoQNExit = new BeginningExitBehaviorNoQNExit(processTerm3,tinteractions,this.depth + 1);
			if (beginningExitBehaviorNoQNExit.isBeginningExitBehavior())
				{
				processTerm4 = beginningExitBehaviorNoQNExit.getBeginningMaximalJobsRoutingBehavior();
				List<String> list2 = beginningExitBehaviorNoQNExit.getDeliverActionNames();
				listDel.addAll(list2);
				}
			else 
				{
				// 3
				String string = this.elemType.toString() + " is not finite arrival process";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = beginningExitBehaviorNoQNExit.getErrorMessage(); 
				list2.add(errorMessage);
				return false;
				}
			// 3.il ritorno di jobs.
			// preleviamo la differenza tra processTerm3 e processTerm4
			List<ProcessTerm> list21 = MetodiVari.differenza(processTerm3, processTerm4);
			// per ogni elemento di list21 dobbiamo cercare un comportamento di ritorno di jobs
			for (ProcessTerm processTerm5 : list21)
				{
				ReturnBehavior returnBehavior = new ReturnBehavior(processTerm5,tinteractions,this.depth + 1);
				if (!returnBehavior.isReturnBehavior())
					{
					// 4
					String string = this.elemType.toString() + " is not finite arrival process";
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = returnBehavior.getErrorMessage(); 
					list2.add(errorMessage);
					return false;
					}
				else
					{
					// ogni comportamento di ritorno deve avere almeno un'azione di ritorno
					List<String> listR = returnBehavior.getReturnActionNames();
					listRit.addAll(listR);
					}
				}
			}
		// le uniche azioni di output devono essere le azioni di consegna
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(tinteractions);
		List<String> list2 = tinteractionsParts.getOutputInteractions();
		if (!list2.containsAll(listDel))
			{
			// 5
			String string = this.elemType.toString() + " is not finite arrival process";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2List = this.errorMessage.getCauses();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = "there are delivery actions that are not output interactions";
			errorMessage.setErrorMessage(string2);
			list2List.add(errorMessage);
			return false;
			}
		if (!listDel.containsAll(list2))
			{
			// 6
			String string = this.elemType.toString() + " is not finite arrival process";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2List = this.errorMessage.getCauses();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = "there are output interaction that are not delivery actions";
			errorMessage.setErrorMessage(string2);
			list2List.add(errorMessage);
			return false;
			}
		// le uniche azioni di input devono essere le azioni di ritorno
		List<String> list3 = tinteractionsParts.getInputInteractions();
		if (!list3.containsAll(listRit))
			{
			// 7
			String string = this.elemType.toString() + " is not finite arrival process";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2List = this.errorMessage.getCauses();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = "there are return actions that are not input interactions";
			errorMessage.setErrorMessage(string2);
			list2List.add(errorMessage);
			return false;
			}
		if (!listRit.containsAll(list3))
			{
			// 8
			String string = this.elemType.toString() + " is not finite arrival process";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2List = this.errorMessage.getCauses();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string2 = "there are input interactions that are not return actions";
			errorMessage.setErrorMessage(string2);
			list2List.add(errorMessage);
			return false;
			}
		return true;
		}
	
	// restituisce un comportamento con le equazioni standard.
	// restituisce null se et none'un processo di arrivi finiti
	public AETbehavior getNormalizedBehavior()
		{
		if (!isProcessoArriviFiniti())
			return null;
		AETbehavior tbehaviorR = new AETbehavior();
		// il comportamento risultato ha tre equazioni
		List<BehavEquation> behavEquationsR = new ArrayList<BehavEquation>();
		// preleviamo il comportamento di fase
		ElemType elemType = this.elemType;
		AETbehavior tbehavior = elemType.getBehavior();
		AETinteractions tinteractions = elemType.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehavior phaseBehavior = new PhaseBehavior(processTerm,tinteractions, this.depth + 1);
		ProcessTerm processTerm2 = phaseBehavior.getPhaseBehavior();
		// costruiamo un'equazione per il comportamento di fase
		ParamDeclaration[] declPars = new ParamDeclaration[2];
		declPars[0] = null;
		declPars[1] = null;
		Header header = new Header("Fase",declPars);
		BehavEquation behavEquation2 = new BehavEquation(header,processTerm2);
		behavEquationsR.add(behavEquation2);
		List<BehavProcess> list3 = computeLeaf(processTerm2);
		// 2.il routing di jobs;
		// preleviamo la differenza tra processTerm e processTerm2
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, phaseBehavior.getMaximalPhaseBehavior());
		// per ogni elemento di list dobbiamo cercare un comportamento di routing di jobs
		int j = 1;
		int k = 1;
		for (int i = 0; i < list.size(); i++)
			{
			ProcessTerm processTerm3 = list.get(i);
			BeginningExitBehaviorNoQNExit beginningExitBehaviorNoQNExit = new BeginningExitBehaviorNoQNExit(processTerm3,tinteractions,this.depth + 1);
			// crea un'equazione per il routing
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			declPars2[0] = null;
			declPars2[1] = null;
			Header intestazione2 = new Header("Routing"+(i+1),declPars2);
			ProcessTerm processTerm4 = null;
			if (beginningExitBehaviorNoQNExit.isBeginningExitBehavior())
				processTerm4 = beginningExitBehaviorNoQNExit.getBeginningJobsRoutingBehavior();
			BehavEquation behavEquation3 = new BehavEquation(intestazione2,
					processTerm4);
			behavEquationsR.add(behavEquation3);
			// 3.il ritorno di jobs.
			ProcessTerm pt = null;
			if (beginningExitBehaviorNoQNExit.isBeginningExitBehavior())
				pt = beginningExitBehaviorNoQNExit.getBeginningMaximalJobsRoutingBehavior();
			List<ProcessTerm> list2 = MetodiVari.differenza(processTerm3, pt);
			List<BehavProcess> list4 = computeLeaf(processTerm4);
			for (BehavProcess behavProcess4 : list4)
				{
				behavProcess4.setName("Return"+k);
				k++;
				behavProcess4.setExprs(new Expression[0]);
				}
			// per ogni elemento di list2 dobbiamo cercare un comportamento di ritorno di jobs
			for (ProcessTerm processTerm51 : list2)
				{
				// si crea l'equazione per il ritorno
				ParamDeclaration[] declPars3 = new ParamDeclaration[2];
				declPars3[0] = null;
				declPars3[1] = null;
				Header intestazione3 = new Header("Return"+j,declPars3);
				j++;
				ReturnBehavior returnBehavior = new ReturnBehavior(processTerm51,tinteractions,this.depth + 1);
				ProcessTerm processTerm5 = returnBehavior.getReturnBehavior();
				BehavEquation behavEquation4 = new BehavEquation(intestazione3,processTerm5);
				behavEquationsR.add(behavEquation4);
				// si impostano correttamente le chiamate alle equazioni
				List<BehavProcess> list5 = computeLeaf(processTerm5);
				for (BehavProcess behavProcess4 : list5)
					{
					behavProcess4.setName("Fase");
					behavProcess4.setExprs(new Expression[0]);
					}
				}
			}
		for (int i = 0; i < list3.size(); i++)
			{
			BehavProcess behavProcess = list3.get(i);
			behavProcess.setName("Routing"+(i+1));
			behavProcess.setExprs(new Expression[0]);
			}
		// si impostano correttamente le chiamate alle equazioni
		BehavEquation[] behavEquations2 = new BehavEquation[behavEquationsR.size()];
		behavEquationsR.toArray(behavEquations2);
		tbehaviorR.setBehaviors(behavEquations2);
		return tbehaviorR;
		}

	@Override
	public boolean isEquivalente() 
		{
		return isProcessoArriviFiniti();
		}

	@Override
	public EquivalenzaArriviFiniti2 copy() 
		{
		EquivalenzaArriviFiniti2 equivalenzaArriviFiniti2 = new EquivalenzaArriviFiniti2(this.depth + 1);
		// elemType setting
		ElemType elemTypeCloned = this.elemType.copy();
		equivalenzaArriviFiniti2.elemType = elemTypeCloned;
		return equivalenzaArriviFiniti2;
		}

	@Override
	public void print() 
		{
		System.out.println("EquivalenzaArriviFiniti2 object");
		// elemType printing
		System.out.println("ElemType:");
		this.elemType.print();
		}
	}
