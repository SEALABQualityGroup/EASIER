/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.List;

import restrizioniIstanze.structure.ArchiAttachmentsNorm;
import restrizioniIstanze.structure.ArchiElemInstancesNorm;
import restrizioniIstanze.structure.ArchiInteractionsNorm;
import restrizioniIstanze.structure.ArchiTypeNorm;
import specificheAEmilia.ArchiAttachments;
import specificheAEmilia.ArchiElemInstances;
import specificheAEmilia.ArchiInteractions;
import specificheAEmilia.ArchiType;
import utilities.ErrorMessage;
import valutazione.normalization.NormalizeException;
import valutazione.scope.ScopeArchiType;

/**
 * @author Mirko
 *
 */
public class NormalizeAll {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

	public NormalizeAll(ScopeArchiType scopeArchiType, int depth) {
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * Normalizza la specifica AEmilia: semplificando le dichiarazioni indicizzate,
	 * valorizzando le espressioni e assegnando un valore agli identificatori.
	 *
	 * @return il tipo architetturale normalizzato.
	 * @throws NormalizeException
	 */
	public ArchiTypeNorm normalizeAll() throws NormalizeException {
		/* MODELED */
		try {
			ArchiType clone = this.scopeArchiType.getAt().copy();
			// si normalizzano le dichiarazioni di collegamenti di
			// elementi architetturali
			NormalizeAttacs normalizeAttacs = new NormalizeAttacs(this.scopeArchiType, this.depth + 1);
			ArchiAttachmentsNorm aa = normalizeAttacs.normalizeAttacs();
			if (normalizeAttacs.isErrorOccurred()) {
				// 1
				String string = "Normalizing error for " + clone.toString();
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage = normalizeAttacs.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			ArchiAttachments archiAttachments = aa.getNewArchiAttachments();
			clone.getTopologia().setAttachments(archiAttachments);
			// si normalizzano le dichiarazioni di interazioni architetturali
			NormalizeInteractions normalizeInteractions = new NormalizeInteractions(this.scopeArchiType,
					this.depth + 1);
			ArchiInteractionsNorm ai = normalizeInteractions.normalizeInteractions();
			if (normalizeInteractions.isErrorOccurred()) {
				// 2
				String string = "Normalizing error for " + clone.toString();
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage = normalizeInteractions.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			ArchiInteractions archiInteractions = ai.getNewArchiInteractions();
			clone.getTopologia().setArchiInteractions(archiInteractions);
			// si normalizzano le dichiarazioni di istanze di tipi di elementi
			// architetturali
			NormalizeInstances normalizeInstances = new NormalizeInstances(this.scopeArchiType, this.depth + 1);
			ArchiElemInstancesNorm aei = normalizeInstances.normalizeInstances();
			if (normalizeInstances.isErrorOccurred()) {
				// 3
				String string = "Normalizing error for " + clone.toString();
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage = normalizeInstances.getErrorMessage();
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			ArchiElemInstances archiElemInstances = aei.getNewArchiElemInstances();
			clone.getTopologia().setAEIs(archiElemInstances);
			ArchiTypeNorm archiTypeNorm = new ArchiTypeNorm();
			archiTypeNorm.setOldArchiType(this.scopeArchiType.getAt());
			archiTypeNorm.setNewArchiType(clone);
			return archiTypeNorm;
		} catch (Exception e) {
			throw new NormalizeException(e);
		}
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
