/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import java.util.List;

import equivalenzaComportamentale.secondRelease.riconoscimento.azioni.PrePhaseAction;
import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.comportamenti.PhaseBehaviorNorm;
import specificheAEmilia.RateInf;
import utilities.ErrorMessage;
import valutazione.typeChecking.TypeCheckingException;
import valutazione.typeChecking.rateInfChecking.CheckWeight;

/**
 * @author Mirko
 *
 */
class RestrizioneIstanze25 {

	private int depth;
	private ErrorMessage errorMessage;
	
	public RestrizioneIstanze25(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public boolean restrizioneIstanze25Rec(PhaseBehaviorNorm phaseBehaviorNorm)
	throws RestrizioniIstanzeException {
		List<PrePhaseAction> list = phaseBehaviorNorm.getPrePhaseActions();
		for (PrePhaseAction prePhaseAction :list)
			{
			RateInf rateInf = prePhaseAction.getRate();
			CheckWeight checkWeight = new CheckWeight(rateInf,this.depth + 1);
			try {
				if (!checkWeight.checkWeight())
					{
					String string3 = "Instances restrictions error for " + phaseBehaviorNorm.toString();
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = checkWeight.getErrorMessage();
					list2.add(errorMessage);
					return false;
					}
				} 
			catch (TypeCheckingException e) 
				{
				throw new RestrizioniIstanzeException(e);
				}
			}
		List<PhaseBehaviorNorm> list2 = phaseBehaviorNorm.getNextPhaseBehaviorsNorm();
		for (PhaseBehaviorNorm phaseBehaviorNorm2 : list2)
			{
			RestrizioneIstanze25 restrizioneIstanze25 = new RestrizioneIstanze25(this.depth + 1);
			if (!restrizioneIstanze25.restrizioneIstanze25Rec(phaseBehaviorNorm2))
				{
				String string3 = "Instances restrictions error for " + phaseBehaviorNorm2.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = restrizioneIstanze25.getErrorMessage();
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
