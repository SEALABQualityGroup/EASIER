/**
 * 
 */
package valutazione.scope;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.AEIdecl;
import specificheAEmilia.BehavEquation;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
public class ScopeProcessTermFromAEI {

	private ScopeArchiType scopeArchiType;

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

    public ScopeProcessTermFromAEI(ScopeArchiType scopeArchiType,int depth) 
    	{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
    	}

	/**
     * Restituisce lo scope che serve per la valutazione di espressioni all'interno 
     * di un termine di processo
     * , secondo la dichiarazione di istanza e l'equazione comportamentale forniti come parametri.
     * L'equazione comportamentale deve appartenere all'ElemType relativo alla dichiarazione di istanza,
     * altrimenti viene lanciata una NormalizeException.
     * Il TreeMap restituito contiene il mapping tra parametri attuali e parametri costanti
     * del tipo di elemento architetturale presente nella dichiarazione di istanza, e gli identificatori
     * dell'intestazione di comportamento, al quale il termine di processo appartiene.
     * Gli identificatore dell'intestazione dell'equazione pero' non possono essere valutati.
     *
     * @param aeid
     * @param be
     * @return
     * @throws NormalizeException
     */
    public TreeMap<String, ValueIdentExpr> getScopeProcessTermFromAEI(AEIdecl aeid, BehavEquation be)
        throws NormalizeException
        {
        try {
			// si controlla se aeide'un'istanza del tipo di elemento architetturale
			// che ha be come equazione comportamentale
			// si aggiunge alla scope di be lo scope dell'ElemType di aeid
        	ScopeBehavHeaderFromAEI scopeBehavHeaderFromAEI = new ScopeBehavHeaderFromAEI(this.scopeArchiType,this.depth + 1);
			TreeMap<String, ValueIdentExpr> scoaeid = scopeBehavHeaderFromAEI.getScopeBehavHeaderFromAEI(aeid);
			if (scopeBehavHeaderFromAEI.isErrorOccurred()) 
				{
				String string = "Scope evaluating error for behaviorla equation " + be.toString() + " belong to " + aeid.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scopeBehavHeaderFromAEI.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			ScopeProcessTerm2 scopeProcessTerm2 = new ScopeProcessTerm2(this.depth + 1);
			TreeMap<String, ValueIdentExpr> scopt = scopeProcessTerm2.getScopeProcessTerm(be,
					scoaeid);
			if (scopeProcessTerm2.isErrorOccurred()) 
				{
				String string = "Scope evaluating error for behaviorla equation " + be.toString() + " belong to " + aeid.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scopeProcessTerm2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			// tramite la seguente istruzione, si valorizzano i parametri dell'ElemeType,
			// che hanno valore null, con i parametri
			// attuali presenti in aeid
			scoaeid.putAll(scopt);
			return scoaeid;
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
