/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.List;

import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.PrePhaseAction;
import restrizioniIstanze.comportamenti.PhaseBehaviorNorm;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class RestrizioneIstanze23 {

	private int depth;
	private ErrorMessage errorMessage;
	
	public RestrizioneIstanze23(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	protected boolean restrizioneIstanze23Rec(PhaseBehaviorNorm phaseBehaviorNorm) 
		{
		List<PrePhaseAction> list = phaseBehaviorNorm.getPrePhaseActions();
		if (list.size() > 1)
			{
			PrePhaseAction prePhaseAction = list.get(0);
			Expression expression = prePhaseAction.getPrio();
			for (int i = 1; i < list.size(); i++)
				{
				PrePhaseAction prePhaseAction2 = list.get(i);
				Expression expression2 = prePhaseAction2.getPrio();
				if (!expression.equals(expression2))
					{
					String string3 = "Instances restrictions error for " + phaseBehaviorNorm.toString();
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					String string4 = "Pre phase action " + prePhaseAction.toString() + " and " + prePhaseAction2.toString() +  
						" has not same priority";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string4);
					list2.add(errorMessage);
					return false;
					}
				}
			}
		List<PhaseBehaviorNorm> list2 = phaseBehaviorNorm.getNextPhaseBehaviorsNorm();
		for (PhaseBehaviorNorm phaseBehaviorNorm2 : list2)
			{
			RestrizioneIstanze23 restrizioneIstanze23 = new RestrizioneIstanze23(this.depth + 1);
			if (!restrizioneIstanze23.restrizioneIstanze23Rec(phaseBehaviorNorm2))
				{
				String string3 = "Instances restrictions error for " + phaseBehaviorNorm2.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = restrizioneIstanze23.getErrorMessage();
				list3.add(errorMessage);
				return false;
				}
			}
		return true;
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}	
}
