/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import restrizioniIstanze.structure.AEIdeclNorm;
import restrizioniIstanze.structure.ArchiElemInstancesNorm;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIdeclInd;
import specificheAEmilia.ArchiElemInstances;
import utilities.ErrorMessage;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeParts.normalizeAEIdeclInd.NormalizeAEIdeclInd;
import valutazione.scope.ScopeArchiType;

/**
 * @author Mirko
 *
 */
public class NormalizeInstances {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeInstances(ScopeArchiType scopeArchiType,int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Normalizza le dichiarazioni di istanze di elementi,
	 * presenti nel tipo architetturale.
	 *
	 * @return le dichiarazioni di istanze normalizzate.
	 * @throws NormalizeException
	 */
	public ArchiElemInstancesNorm normalizeInstances() throws NormalizeException {
		try {
			ArrayList<AEIdecl> al = new ArrayList<AEIdecl>(0);
			// si prelevano le dichiarazioni di istanze di
			// elementi architetturali
			ArchiElemInstances aei = this.scopeArchiType.getAt().getTopologia().getAEIs();
			ArchiElemInstances clone = aei.copy();
			// si preleva l'array delle dichiarazioni
			AEIdecl[] aeids = clone.getAEIdeclSeq();
			// si normalizzano opportunamente tutte le dichiarazioni
			for (int i = 0; i < aeids.length; i++) 
				{
				if (aeids[i] instanceof AEIdeclInd) 
					{
					NormalizeAEIdeclInd normalizeAEIdeclInd = new NormalizeAEIdeclInd(this.scopeArchiType,this.depth + 1);
					AEIdecl[] risnorm = normalizeAEIdeclInd.normalizeAEIdeclInd((AEIdeclInd) aeids[i]);
					if (normalizeAEIdeclInd.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + aei;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeAEIdeclInd.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					al.addAll(new CopyOnWriteArrayList<AEIdecl>(risnorm));
					} 
				else 
					{
					NormalizeAEIdecl normalizeAEIdecl = new NormalizeAEIdecl(this.scopeArchiType,this.depth + 1);
					AEIdeclNorm ideclNorm = normalizeAEIdecl.normalizeAEIdecl(aeids[i]);
					if (normalizeAEIdecl.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + aei;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeAEIdecl.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					aeids[i] = ideclNorm.getNewAEIdecl();
					al.add(aeids[i]);
					}
				}
			// si assegnano le nuove dichiarazioni di istanze normalizzate
			// a clone
			al.trimToSize();
			AEIdecl[] ris = new AEIdecl[al.size()];
			al.toArray(ris);
			clone.setAEIdeclSeq(ris);
			ArchiElemInstancesNorm archiElemInstancesNorm = new ArchiElemInstancesNorm();
			archiElemInstancesNorm.setOldArchiElemInstances(aei);
			archiElemInstancesNorm.setNewArchiElemInstances(clone);
			return archiElemInstancesNorm;
			} 
		catch (Exception e) 
			{
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
