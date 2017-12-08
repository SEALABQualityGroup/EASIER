/**
 * 
 */
package restrizioniIstanze.restrizioniIstanze;

import java.util.List;

import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.qnElemTypes.ElemTypeNorm;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class CompliantInstancesRules {

	private ErrorMessage errorMessage = new ErrorMessage(1);
	private List<ElemTypeNorm> elemTypeNormList;

	public CompliantInstancesRules(List<ElemTypeNorm> elemTypeNormList) {
		super();
		this.elemTypeNormList = elemTypeNormList;
	}

	public boolean isCompliantInstancesRules() throws RestrizioniIstanzeException {
		try {
			for (ElemTypeNorm elemTypeNorm : this.elemTypeNormList) {
				if (!elemTypeNorm.isCompliantInstanceRules()) {
					String string = "Instance rule error for " + elemTypeNorm.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = elemTypeNorm.getErrorMessage();
					list.add(errorMessage);
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			throw new RestrizioniIstanzeException(e);
		}
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
