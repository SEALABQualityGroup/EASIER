/**
 * 
 */
package restrizioniSpecifiche.associative;

import specificheAEmilia.ActionType;
import specificheAEmilia.BehavEquation;

/**
 * @author Mirko
 *
 */
public class ActionTypeBehavEquationRel 
	{

	private ActionType actionType;
	private BehavEquation behavEquation;
	
	public ActionTypeBehavEquationRel(ActionType actionType,
			BehavEquation behavEquation) {
		super();
		this.actionType = actionType;
		this.behavEquation = behavEquation;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public BehavEquation getBehavEquation() {
		return behavEquation;
	}
	
	}
