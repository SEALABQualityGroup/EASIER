/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import restrizioniIstanze.structure.ArchiAttachmentsNorm;
import restrizioniIstanze.structure.AttacDeclNorm;
import specificheAEmilia.ArchiAttachments;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.AttacDeclInd;
import utilities.ErrorMessage;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeParts.normalizeAttacDeclInd.NormalizeAttacDeclInd;
import valutazione.scope.ScopeArchiType;

/**
 * @author Mirko
 *
 */
public class NormalizeAttacs {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeAttacs(ScopeArchiType scopeArchiType, int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Normalizza le dichiarazioni di collegamenti,
	 * presenti nel tipo architetturale.
	 *
	 * @return la dichiarazione di collegamenti normalizzata.
	 * @throws NormalizeException
	 */
	public ArchiAttachmentsNorm normalizeAttacs() 
		throws NormalizeException 
		{
		try {
			ArrayList<AttacDecl> al = null;
			// si prelevano le dichiarazioni di collegamenti
			// architetturali
			ArchiAttachments aa = this.scopeArchiType.getAt().getTopologia()
					.getAttachments();
			ArchiAttachments clone = aa.copy();
			// si preleva l'array delle dichiarazioni
			AttacDecl[] ads = clone.getAttachments();
			// si normalizzano opportunamente tutte le dichiarazioni
			if (ads != null) 
				{
				al = new ArrayList<AttacDecl>(0);
				for (int i = 0; i < ads.length; i++) 
					{
					if (ads[i] instanceof AttacDeclInd) 
						{
						NormalizeAttacDeclInd normalizeAttacDeclInd = new NormalizeAttacDeclInd(this.scopeArchiType,this.depth + 1); 
						AttacDecl[] risnorm = normalizeAttacDeclInd.normalizeAttacDeclInd((AttacDeclInd) ads[i]);
						if (normalizeAttacDeclInd.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + aa.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeAttacDeclInd.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						al.addAll(new CopyOnWriteArrayList<AttacDecl>(risnorm));
						} 
					else 
						{
						NormalizeAttacDecl normalizeAttacDecl = new NormalizeAttacDecl(this.scopeArchiType,this.depth + 1);
						AttacDeclNorm attacDeclNorm = normalizeAttacDecl.normalizeAttacDecl(ads[i]);
						if (normalizeAttacDecl.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + aa.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeAttacDecl.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						ads[i] = attacDeclNorm.getNewAttacDecl();
						al.add(ads[i]);
						}
					}
				// si assegnano le nuove dichiarazioni di collegamenti normalizzate
				// alla tipologia architetturale
				al.trimToSize();
				AttacDecl[] ris = new AttacDecl[al.size()];
				al.toArray(ris);
				clone = new ArchiAttachments(ris);
				} 
			else 
				{
				clone = new ArchiAttachments(ads);
				}
			ArchiAttachmentsNorm archiAttachmentsNorm = new ArchiAttachmentsNorm();
			archiAttachmentsNorm.setOldArchiAttachments(aa);
			archiAttachmentsNorm.setNewArchiAttachments(clone);
			return archiAttachmentsNorm;
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
