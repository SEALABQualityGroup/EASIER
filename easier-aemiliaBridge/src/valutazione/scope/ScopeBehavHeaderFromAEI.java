/**
 * 
 */
package valutazione.scope;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.AEIdecl;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
public class ScopeBehavHeaderFromAEI {

	private ScopeArchiType scopeArchiType;
	
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

    public ScopeBehavHeaderFromAEI(ScopeArchiType scopeArchiType,int depth) 
    	{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
    	}


	/**
     * Restituisce lo scope che serve per la valutazione di espressioni 
     * all'interno di un'intestazione
     * di comportamento, secondo la dichiarazione di istanza fornita come parametro.
     * Il TreeMap restituito contiene gli identificatori con i rispettivi valori, ottenuti
     * dal mapping tra parametri attuali e parametri costanti del tipo di elemento
     * architetturale, specificato nella dichiarazione di istanza.
     *
     * @param aeid
     * @return
     * @throws NormalizeException
     */
    public TreeMap<String, ValueIdentExpr> getScopeBehavHeaderFromAEI(AEIdecl aeid)
        throws NormalizeException
        {
        // in getScopeElemTypeFromAEI avviene il mapping tra parametri attuali e
        // i parametri definiti nel tipo di elemento architetturale
    	ScopeElemTypeFromAEI scopeElemTypeFromAEI = new ScopeElemTypeFromAEI(this.scopeArchiType,this.depth + 1);
        TreeMap<String, ValueIdentExpr> ris = scopeElemTypeFromAEI.getScopeElemTypeFromAEI(aeid);
        if (scopeElemTypeFromAEI.isErrorOccurred())
        	{
        	// 1
        	String string = "Scope evaluating error for " + aeid.toString();
        	this.errorMessage.setErrorMessage(string);
        	List<ErrorMessage> list = this.errorMessage.getCauses();
        	ErrorMessage errorMessage = scopeElemTypeFromAEI.getErrorMessage();
        	list.add(errorMessage);
        	this.errorOccurred = true;
        	return null;
        	}
        return ris;
        }


	public boolean isErrorOccurred() {
		return errorOccurred;
	}


	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
    
}
