/**
 * 
 */
package valutazione.scope;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIdeclInd;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;

/**
 * @author Mirko
 *
 */
public class ScopeAEIdecl {
	
	private ScopeArchiType scopeArchiType;
	
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

    public ScopeAEIdecl(ScopeArchiType scopeArchiType,int depth) 
    	{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
    	}

	/**
     * Restituisce lo scope, che serve per la valutazione delle espressioni,
     * presenti in una dichiarazione di istanze di elementi architetturali
     * . Allo scope ArchiType, si aggiunge un'eventuale voce
     * per l'indice, con valore uguale alla sua espressione di
     * inizializzazione.
     * Solleva una NormalizeException, se ci sono espressioni
     * di inizializzazione di parametri, che non possono essere valutate.
     *
     * @param aeid
     * @return
     * @throws NormalizeException
     */
    public TreeMap<String, ValueIdentExpr> getScopeAEIdecl(AEIdecl aeid)
        throws NormalizeException
        {
        try {
			TreeMap<String, ValueIdentExpr> ris = new TreeMap<String, ValueIdentExpr>();
			// in ris si inseriscono tutte le voci della mappa attuale
			TreeMap<String, ValueIdentExpr> treeMap = this.scopeArchiType.getScope();
			ris.putAll(treeMap);
			// 1) se aeide'un oggetto AEIdeclInd, si aggiunge l'indice
			// a ris, con il valore uguale all'espressione di inizializzazione.
			if (aeid instanceof AEIdeclInd) 
				{
				AEIdeclInd clone = ((AEIdeclInd) aeid).copy();
				String indice = clone.getIndex();
				// eie'l'espressione di inizializzazione per l'indice
				Expression ei = clone.getBeginningExpr();
				// il tipo dinamico di o deve essere un Integer o Real.
				// Si assegna alle espressioni iniziali e finali i loro
				// rispettivi valori.
				// La valutazione delle espressioni iniziale e finale
				// deve essere indipendente.
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression = normalizeExpressionStrict
						.normalize(ei, "", null, ris);
				if (normalizeExpressionStrict.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + aeid;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				Expression expression2 = expression.getNewExpression();
				if (expression2 instanceof Integer)
					ris.put(indice, new ValueIdentExpr(expression2, true,
							new IntegerType()));
				else
					ris.put(indice, new ValueIdentExpr(expression2, false,
							new IntegerType()));
				// si restituisce il nuovo scope
				return ris;
				} 
			else
				return ris;
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
