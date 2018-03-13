/**
 * 
 */
package restrizioniIstanze.restrizioniIstanze;

import java.util.List;

import restrizioniIstanze.structure.ArchiTypeNorm;
import restrizioniSpecifiche.RestrizioniSpecException;
import specificheAEmilia.ArchiType;
import utilities.ErrorMessage;
import valutazione.normalization.normalizeParts.NormalizeAll;
import valutazione.scope.ScopeArchiType;

/**
 * @author Mirko
 *
 */
class NormalizzaTipo {

	private boolean errorOccurred;

	private ErrorMessage errorMessage = new ErrorMessage(2);

	private ScopeArchiType scopeArchiType;

	/**
	 * Viene utilizzato per la normalizzazione del tipo architetturale.
	 * 
	 * @param archiType2
	 * @return
	 * @throws RestrizioniSpecException
	 */
	public ArchiType normalizzaTipo(ArchiType archiType2) throws RestrizioniSpecException {
		try {
			/* MODELED */
			scopeArchiType = new ScopeArchiType(archiType2, 3);
			if (scopeArchiType.isErrorOccurred()) {
				// 1
				String string = "Normalizing error for " + archiType2.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scopeArchiType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			NormalizeAll normalizeAll = new NormalizeAll(scopeArchiType, 3);
			ArchiTypeNorm archiType = normalizeAll.normalizeAll();
			if (normalizeAll.isErrorOccurred()) {
				// 2
				String string = "Normalizing error for " + archiType2.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeAll.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			ArchiType archiType3 = archiType.getNewArchiType();
			this.scopeArchiType.setAt(archiType3);
			return archiType3;
		} catch (Exception e) {
			throw new RestrizioniSpecException(e);
		}
	}

	public ScopeArchiType getScopeArchiType() {
		return scopeArchiType;
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
