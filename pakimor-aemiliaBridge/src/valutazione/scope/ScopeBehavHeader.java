/**
 * 
 */
package valutazione.scope;

import java.util.TreeMap;

import specificheAEmilia.ElemType;
import specificheAEmilia.Header;
import specificheAEmilia.ParamDeclaration;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
public class ScopeBehavHeader {
	    
    /**
     * Restituisce lo scope che serve per la valutazione delle espressioni
     * presenti nell'intestazione della prima equazione comportamentale
     * di un ElemType.
     *
     * @param bh
     * @param et
     * @return
     */
    public TreeMap<String, ValueIdentExpr> getScopeBehavHeader(Header bh, ElemType et)
        throws NormalizeException
        {
        try {
			TreeMap<String, ValueIdentExpr> tma = new TreeMap<String, ValueIdentExpr>();
			// si aggiungono allo scope i parametri dell'intestazione di et
			Header intElemType = et.getHeader();
			// l'intestazione di un ElemType contiene solo costanti
			// non inizializzate
			ParamDeclaration[] spc = intElemType.getParameters();
			for (int j = 0; j < spc.length && spc[0] != null; j++) 
				{
				String key = spc[j].getName();
				// nella mappa si inizializza a null il valore della costante
				tma.put(key, new ValueIdentExpr(null, false, spc[j].getType()));
				}
			return tma;
			} 
        catch (Exception e) 
        	{
			throw new NormalizeException(e);
        	}
        }


}
