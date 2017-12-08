/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import restrizioniIstanze.structure.ArchiInteractionsNorm;
import restrizioniIstanze.structure.InteractionDeclNorm;
import specificheAEmilia.ArchiInteractions;
import specificheAEmilia.InteractionDecl;
import specificheAEmilia.InteractionDeclInd;
import utilities.ErrorMessage;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeParts.normalizeInteractionDeclInd.NormalizeInteractionDeclInd;
import valutazione.scope.ScopeArchiType;

/**
 * @author Mirko
 *
 */
public class NormalizeInteractions {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeInteractions(ScopeArchiType scopeArchiType,int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Normalizza le dichiarazioni di interazioni,
	 * presenti nel tipo architetturale.
	 *
	 * @return le dichiarazioni di interazioni normalizzate
	 * @throws NormalizeException
	 */
	public ArchiInteractionsNorm normalizeInteractions() 
		throws NormalizeException 
		{
		try {
			ArrayList<InteractionDecl> al = null;
			// si prelevano le dichiarazioni di interazioni
			// architetturali
			ArchiInteractions ai = this.scopeArchiType.getAt().getTopologia()
					.getArchiInteractions();
			ArchiInteractions clone = ai.copy();
			// si preleva l'array delle dichiarazioni
			InteractionDecl[] ids = clone.getInteractions();
			// si normalizzano opportunamente tutte le dichiarazioni
			if (ids != null) 
				{
				al = new ArrayList<InteractionDecl>(0);
				for (int i = 0; i < ids.length; i++) 
					{
					if (ids[i] instanceof InteractionDeclInd) 
						{
						NormalizeInteractionDeclInd normalizeInteractionDeclInd = new NormalizeInteractionDeclInd(this.scopeArchiType,this.depth + 1);
						InteractionDecl[] risnorm = normalizeInteractionDeclInd.normalizeInteractionDeclInd((InteractionDeclInd) ids[i]);
						if (normalizeInteractionDeclInd.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + ai;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeInteractionDeclInd.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						al.addAll(new CopyOnWriteArrayList<InteractionDecl>(risnorm));
						} 
					else 
						{
						NormalizeInteractionDecl normalizeInteractionDecl = new NormalizeInteractionDecl(this.scopeArchiType,this.depth + 1);
						InteractionDeclNorm interactionDeclNorm = normalizeInteractionDecl.normalizeInteractionDecl(ids[i]);
						if (normalizeInteractionDecl.isErrorOccurred()) 
							{
							String string = "Normalizing error for " + ai;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeInteractionDecl.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						ids[i] = interactionDeclNorm.getNewInteractionDecl();
						al.add(ids[i]);
						}
					}
				// si assegnano le nuove dichiarazioni di interazioni normalizzate
				// alla tipologia architetturale
				al.trimToSize();
				InteractionDecl[] ris = new InteractionDecl[al.size()];
				al.toArray(ris);
				clone = new ArchiInteractions(ris);
				} 
			else 
				{
				clone = new ArchiInteractions(ids);
				}
			ArchiInteractionsNorm archiInteractionsNorm = new ArchiInteractionsNorm();
			archiInteractionsNorm.setOldArchiInteractions(ai);
			archiInteractionsNorm.setNewArchiInteractions(clone);
			return archiInteractionsNorm;
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
