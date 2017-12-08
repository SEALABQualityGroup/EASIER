/**
 * 
 */
package valutazione.scope;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerType;
import specificheAEmilia.InteractionDecl;
import specificheAEmilia.InteractionDeclInd;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;

/**
 * @author Mirko
 *
 */
public class ScopeInteractionDecl {

	private ScopeArchiType scopeArchiType;
	
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

    public ScopeInteractionDecl(ScopeArchiType scopeArchiType,int depth) 
    	{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
    	}	

	/**
     * Restituisce lo scope, che serve per la valutazione delle espressioni,
     * presenti in una dichiarazione indicizzata di interazioni.
     * Solleva una NormalizeException, se ci sono espressioni
     * di inizializzazione di parametri, che non possono essere valutate.
     *
     * @param id
     * @return
     * @throws NormalizeException
     */
    public TreeMap<String, ValueIdentExpr> getScopeInteractionDecl(InteractionDecl id)
        throws NormalizeException
        {
        try {
			TreeMap<String, ValueIdentExpr> ris = new TreeMap<String, ValueIdentExpr>();
			TreeMap<String, ValueIdentExpr> treeMap = this.scopeArchiType.getScope();
			ris.putAll(treeMap);
			// se ide'un oggetto InteractionDeclInd, si aggiunge l'indice a
			// tm, con valore uguale all'espressione di inizializzazione.
			if (id instanceof InteractionDeclInd) 
				{
				InteractionDeclInd clone = ((InteractionDeclInd) id).copy();
				String indice = clone.getIndex();
				Expression ei = clone.getBeginningExpr();
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression = normalizeExpressionStrict
						.normalize(ei, "", null, ris);
				if (normalizeExpressionStrict.isErrorOccurred()) 
					{
					String string  = "Scope evaluating error for " + id.toString();
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
				// si restituisce il nuovo scope.
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
