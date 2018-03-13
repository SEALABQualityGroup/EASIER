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
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ExitBehaviorNoQNExit;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.ExitBehaviorWithQNExit;
import equivalenzaComportamentale.secondRelease.riconoscimento.comportamenti.PhaseBehavior;
import equivalenzaComportamentale.secondRelease.riconoscimento.elementiBase.TailRecursion;

public class EquivalenzaArriviInfiniti2
	extends EquivalenzaArrivi2
	{
	
	public EquivalenzaArriviInfiniti2(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public EquivalenzaArriviInfiniti2(ElemType elemType) 
		{
		super();
		this.elemType = elemType;
		this.errorMessage = new ErrorMessage(0);
		}

	public boolean isProcessoArriviInfiniti() 
		{
		/* MODELLED */
		// Il comportamento di un processo di arrivi infiniti 
		//e'definito dalla seguente sequenza di comportamenti:
		// 1.una distribuzione di tipo fase;
		// 2.il routing di jobs.
		// 1.una distribuzione di tipo fase;
		ElemType elemType = this.elemType;
		// trasformiamo il comportamento
		// elemType in uno tail recursion
		AETbehavior tbehavior = elemType.getBehavior();
		AETinteractions tinteractions = elemType.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		if (!tailRecursion.isTailRecursion())
			{
			// 1
			String string = this.elemType.toString() + " is not infinite arrival process";
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
		if (phaseBehavior.getPhaseBehavior() == null)
			{
			// 2
			String string = this.elemType.toString() + " is not infinite arrival process";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = phaseBehavior.getErrorMessage(); 
			list.add(errorMessage);
			return false;
			}
		// 2.il routing di jobs;
		// preleviamo la differenza tra processTerm e processTerm2
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, phaseBehavior.getMaximalPhaseBehavior());
		// allochiamo memoria per i nomi delle azioni di consegna
		List<String> listDel = new ArrayList<String>();
		// se list ha lunghezza maggiore di uno vuol dire che ho incontrato un comportamento
		// di fase di tipo hyperesponenziale
		for (int i = 0; i < list.size(); i++)
			{
			ExitBehaviorNoQNExit exitBehaviorNoQNExit3 = new ExitBehaviorNoQNExit(list.get(i),tinteractions,this.depth + 1);
			ExitBehaviorWithQNExit exitBehaviorWithQNExit3 = new ExitBehaviorWithQNExit(list.get(i),tinteractions,this.depth + 1);
			if (exitBehaviorNoQNExit3.isExitBehavior())
				{
				List<String> list2 = exitBehaviorNoQNExit3.getDeliverActionNames();
				listDel.addAll(list2);
				}
			else if (exitBehaviorWithQNExit3.isExitBehavior())
				{
				List<String> list2 = exitBehaviorWithQNExit3.getDeliverActionNames();
				listDel.addAll(list2);
				}
			else 
				{
				// 3
				String string = this.elemType.toString() + " is not infinite arrival process";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = exitBehaviorNoQNExit3.getErrorMessage();
				ErrorMessage errorMessage2 = exitBehaviorWithQNExit3.getErrorMessage();
				list2.add(errorMessage);
				list2.add(errorMessage2);
				return false;
				}
			}
		// le uniche azioni di output devono essere le azioni di consegna
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(tinteractions);
		List<String> list2 = tinteractionsParts.getOutputInteractions();
		if (!list2.containsAll(listDel))
			{
			// 4
			String string = this.elemType.toString() + " is not infinite arrival process";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string2 = "there are deliver action that are not output interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list3.add(errorMessage);
			return false;
			}
		if (!listDel.containsAll(list2))
			{
			// 5
			String string = this.elemType.toString() + " is not infinite arrival process";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list3 = this.errorMessage.getCauses();
			String string2 = "there are output interactions that are not deliver action";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list3.add(errorMessage);
			return false;
			}
		// non deve avere azioni di input
		List<String> list3 = tinteractionsParts.getInputInteractions();
		if (!list3.isEmpty())
			{
			// 6
			String string = this.elemType.toString() + " is not infinite arrival process";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list4 = this.errorMessage.getCauses();
			String string2 = "there are input interactions";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			list4.add(errorMessage);
			return false;
			}
		return true;
		}
	
	// Restituisce un comportamento con le equazioni standard.
	// restituisce null se et none'un processo di arrivi finiti
	public AETbehavior getNormalizedBehavior()
		{
		if (!isProcessoArriviInfiniti())
			return null;
		AETbehavior tbehaviorR = new AETbehavior();
		// il comportamento risultato ha due equazioni
		List<BehavEquation> behavEquationsR = new ArrayList<BehavEquation>();
		// preleviamo il comportamento di fase
		AETbehavior tbehavior = this.elemType.getBehavior();
		AETinteractions tinteractions = this.elemType.getInteractions();
		TailRecursion tailRecursion = new TailRecursion(tbehavior,this.depth + 1);
		AETbehavior tbehavior2 = tailRecursion.makeOneEquation();
		BehavEquation[] behavEquations = tbehavior2.getBehaviors();
		// per precondizione tbehavior2 ha una sola equazione
		BehavEquation behavEquation = behavEquations[0];
		ProcessTerm processTerm = behavEquation.getTermineProcesso();
		// verifichiamo la presenza della distribuzione di tipo fase
		PhaseBehavior phaseBehavior = new PhaseBehavior(processTerm,tinteractions,this.depth + 1);
		// costruiamo un'equazione per il comportamento di fase
		ParamDeclaration[] declPars = new ParamDeclaration[2];
		declPars[0] = null;
		declPars[1] = null;
		Header header = new Header("Fase",declPars);
		ProcessTerm processTerm2 = phaseBehavior.getPhaseBehavior();
		BehavEquation behavEquation2 = new BehavEquation(header,
				processTerm2);
		behavEquationsR.add(behavEquation2);
		// 2.il routing di jobs;
		List<ProcessTerm> list = MetodiVari.differenza(processTerm, 
				phaseBehavior.getMaximalPhaseBehavior());
		// per ogni elemento di list dobbiamo cercare un comportamento di routing di jobs
		for (int i = 0; i < list.size(); i++)
			{
			ProcessTerm processTerm3 = list.get(i);
			ExitBehaviorNoQNExit exitBehaviorNoQNExit = new ExitBehaviorNoQNExit(processTerm3,tinteractions,this.depth + 1);
			ExitBehaviorWithQNExit exitBehaviorWithQNExit = new ExitBehaviorWithQNExit(processTerm3,tinteractions,this.depth + 1);
			ProcessTerm processTerm4 = null;
			if (exitBehaviorNoQNExit.isExitBehavior())
				processTerm4 = exitBehaviorNoQNExit.getJobsRoutingBehavior();
			else if (exitBehaviorWithQNExit.isExitBehavior())
				processTerm4 = exitBehaviorWithQNExit.getJobsRoutingBehavior();
			// impostiamo la coda per processTerm4
			BehavProcess behavProcess2 = new BehavProcess("Fase",new Expression[0]);
			processTerm4 = MetodiVari.somma(processTerm4, behavProcess2);
			// crea un'equazione per il routing
			ParamDeclaration[] declPars2 = new ParamDeclaration[2];
			declPars2[0] = null;
			declPars2[1] = null;
			Header intestazione2 = new Header("Routing"+(i+1),declPars2);
			BehavEquation behavEquation3 = new BehavEquation(intestazione2,processTerm4);
			behavEquationsR.add(behavEquation3);
			// si impostano correttamente le chiamate alle equazioni
			List<BehavProcess> list4 = computeLeaf(processTerm4);
			for (BehavProcess behavProcess4 : list4)
				{
				behavProcess4.setName("Fase");
				behavProcess4.setExprs(new Expression[0]);
				}
			}
		int j = 0;
		List<BehavProcess> list3 = computeLeaf(processTerm2);
		for (BehavProcess processTerm5 : list3)
			{
			processTerm5.setName("Routing"+(j+1));
			processTerm5.setExprs(new Expression[0]);
			j++;
			}
		BehavEquation[] behavEquations2 = new BehavEquation[behavEquationsR.size()];
		behavEquationsR.toArray(behavEquations2);
		tbehaviorR.setBehaviors(behavEquations2);
		return tbehaviorR;
		}

	@Override
	public boolean isEquivalente() 
		{
		return this.isProcessoArriviInfiniti();
		}

	@Override
	public EquivalenzaArriviInfiniti2 copy() 
		{
		EquivalenzaArriviInfiniti2 equivalenzaArriviInfiniti2 = new EquivalenzaArriviInfiniti2(this.depth + 1);
		// elemType setting
		ElemType elemTypeCloned = this.elemType.copy();
		equivalenzaArriviInfiniti2.elemType = elemTypeCloned;
		return equivalenzaArriviInfiniti2;
		}

	@Override
	public void print() 
		{
		System.out.println("EquivalenzaArriviInfiniti2 object");
		// elemType printing
		System.out.println("ElemType:");
		this.elemType.print();
		}
	}
