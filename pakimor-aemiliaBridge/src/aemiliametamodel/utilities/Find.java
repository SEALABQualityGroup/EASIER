/**
 * 
 */
package aemiliametamodel.utilities;

import it.univaq.from_aemilia_to_qn_plug_in.serialize.MMAemiliaToArchiType;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.AEIident;
import specificheAEmilia.Expression;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchiTopology;
import metamodel.mmaemilia.ArchiType;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.Elem;
import metamodel.mmaemilia.ElemType;
import metamodel.mmaemilia.InputInteraction;
import metamodel.mmaemilia.Interaction;
import metamodel.mmaemilia.OutputInteraction;
import metamodel.mmaemilia.Behavior.Action;
import metamodel.mmaemilia.Behavior.ActionProcess;
import metamodel.mmaemilia.Behavior.BehavEquation;
import metamodel.mmaemilia.Behavior.Behavior;
import metamodel.mmaemilia.Behavior.ChoiceProcess;
import metamodel.mmaemilia.Behavior.ProcessTerm;

/**
 * @author Mirko
 *
 */
public class Find {

	public ArchiElemInstance getArchiElemInstance(ArchiType archiType, String name, Expression selector) {
		AEIident aeIident = new AEIident(name, selector);
		ArchiTopology archiTopology = archiType.getAtDeclaration();
		List<ArchiElemInstance> archiElemInstances = archiTopology.getAeiDecl();
		for (ArchiElemInstance archiElemInstance : archiElemInstances) {
			String string = archiElemInstance.getInstanceName();
			metamodel.mmaemilia.Expressions.Expression expression = archiElemInstance.getSelector();
			MMAemiliaToArchiType mmAemiliaToArchiType = new MMAemiliaToArchiType();
			Expression expression2 = mmAemiliaToArchiType.getExpression(expression);
			AEIident aeIident2 = new AEIident(string, expression2);
			if (aeIident.equals(aeIident2))
				return archiElemInstance;
		}
		return null;
	}

	public Interaction getInteraction(ArchiElemInstance archiElemInstance, String actionType) {
		ElemType elemType = archiElemInstance.getTypeOf();
		List<InputInteraction> inputInteractions = elemType.getIiDecl();
		List<OutputInteraction> outputInteractions = elemType.getOiDecl();
		for (InputInteraction inputInteraction : inputInteractions) {
			String string = inputInteraction.getIntName();
			if (actionType.equals(string))
				return inputInteraction;
		}
		for (OutputInteraction outputInteraction : outputInteractions) {
			String string = outputInteraction.getIntName();
			if (actionType.equals(string))
				return outputInteraction;
		}
		return null;
	}

	public List<Action> getActions(ArchiElemInstance archiElemInstance, String actionType) {
		List<Action> actions = new ArrayList<Action>();
		Elem elem = archiElemInstance.getElem();
		Behavior behavior = elem.getBehaviorDecl();
		List<BehavEquation> behavEquations = behavior.getEquations();
		for (BehavEquation behavEquation : behavEquations) {
			ProcessTerm processTerm = behavEquation.getPt();
			List<Action> actions2 = getActionsFromPt(processTerm, actionType);
			actions.addAll(actions2);
		}
		return actions;
	}

	private List<Action> getActionsFromPt(ProcessTerm processTerm, String actionType) {
		List<Action> actions = new ArrayList<Action>();
		if (processTerm instanceof ChoiceProcess) {
			ChoiceProcess choiceProcess = (ChoiceProcess) processTerm;
			List<ProcessTerm> processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm2 : processTerms) {
				List<Action> actions2 = getActionsFromPt(processTerm2, actionType);
				actions.addAll(actions2);
			}
		}
		if (processTerm instanceof ActionProcess) {
			ActionProcess actionProcess = (ActionProcess) processTerm;
			Action action = actionProcess.getAct();
			String string = action.getName();
			if (actionType.equals(string))
				actions.add(action);
			ProcessTerm processTerm2 = actionProcess.getProcess();
			List<Action> actions2 = getActionsFromPt(processTerm2, actionType);
			actions.addAll(actions2);
		}
		return actions;
	}

	public Action getActionUnique(ArchiElemInstance archiElemInstance, String actionType) {
		Elem elem = archiElemInstance.getElem();
		Behavior behavior = elem.getBehaviorDecl();
		List<BehavEquation> behavEquations = behavior.getEquations();
		for (BehavEquation behavEquation : behavEquations) {
			ProcessTerm processTerm = behavEquation.getPt();
			Action action = getActionUniqueFromPt(processTerm, actionType);
			if (action != null)
				return action;
		}
		return null;
	}

	private Action getActionUniqueFromPt(ProcessTerm processTerm, String actionType) {
		if (processTerm instanceof ChoiceProcess) {
			ChoiceProcess choiceProcess = (ChoiceProcess) processTerm;
			List<ProcessTerm> processTerms = choiceProcess.getProcesses();
			for (ProcessTerm processTerm2 : processTerms) {
				Action action = getActionUniqueFromPt(processTerm2, actionType);
				if (action != null)
					return action;
			}
		}
		if (processTerm instanceof ActionProcess) {
			ActionProcess actionProcess = (ActionProcess) processTerm;
			Action action = actionProcess.getAct();
			String string = action.getName();
			if (actionType.equals(string))
				return action;
			else {
				ProcessTerm processTerm2 = actionProcess.getProcess();
				Action action2 = getActionUniqueFromPt(processTerm2, actionType);
				if (action2 != null)
					return action2;
			}
		}
		return null;
	}

	public ArchitecturalInteraction getArchiInteraction(metamodel.mmaemilia.ArchiType archiType, String aei,
			Expression expression, String actionType) {
		// si concatena name con selector
		String aeiString = aei;
		if (expression != null)
			aeiString = aeiString + expression.toString();
		ArchiTopology archiTopology = archiType.getAtDeclaration();
		List<ArchitecturalInteraction> architecturalInteractions = archiTopology.getAiDecl();
		for (ArchitecturalInteraction architecturalInteraction : architecturalInteractions) {
			ArchiElemInstance archiElemInstance = architecturalInteraction.getFromInstance();
			String string = architecturalInteraction.getName();
			String string2 = archiElemInstance.getInstanceName();
			if (string.equals(actionType) && string2.equals(aeiString)) {
				return architecturalInteraction;
			}
		}
		return null;
	}
}
