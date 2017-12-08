/**
 * 
 */
package valutazione.scope;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.AttacDeclInd;
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
public class ScopeAttacDecl {

	private ScopeArchiType scopeArchiType;
	
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

    public ScopeAttacDecl(ScopeArchiType scopeArchiType,int depth) 
    	{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
    	}

	/**
     * Restituisce lo scope, che serve per la valutazione delle espressioni,
     * presenti in una dichiarazione indicizzata di legami architetturali.
     * Solleva una NormalizeException, se ci sono espressioni
     * di inizializzazione di parametri, che non possono essere valutate.
     *
     * @param ad
     * @return
     * @throws NormalizeException
     */
    public TreeMap<String, ValueIdentExpr> getScopeAttacDecl(AttacDecl ad)
        throws NormalizeException
        {
        try {
			TreeMap<String, ValueIdentExpr> ris = new TreeMap<String, ValueIdentExpr>();
			TreeMap<String, ValueIdentExpr> treeMap = this.scopeArchiType.getScope();
			ris.putAll(treeMap);
			// se ide'un oggetto AttacDeclInd, si aggiungono gli indici
			// a tm, con valore uguale all'espressione di inizializzazione.
			if (ad instanceof AttacDeclInd) 
				{
				AttacDeclInd clone = ((AttacDeclInd) ad).copy();
				String indice1 = clone.getIndex1();
				Expression ei1 = clone.getBeginningExpr1();
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression = normalizeExpressionStrict
						.normalize(ei1, "", null, ris);
				if (normalizeExpressionStrict.isErrorOccurred()) 
					{
					String string  = "Scope evaluating error for " + ad.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				Expression expression21 = expression.getNewExpression();
				if (expression21 instanceof Integer)
					ris.put(indice1, new ValueIdentExpr(expression21, true,
							new IntegerType()));
				else
					ris.put(indice1, new ValueIdentExpr(expression21, false,
							new IntegerType()));
				if (clone.getIndex2() != null) 
					{
					String indice2 = clone.getIndex2();
					Expression ei2 = clone.getBeginningExpr2();
					NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
					ExpressionNorm expression2 = normalizeExpressionStrict2
							.normalize(ei2, "", null, ris);
					if (normalizeExpressionStrict2.isErrorOccurred()) 
						{
						String string = "Scope evaluating error for " + ad.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					Expression expression3 = expression2.getNewExpression();
					if (expression3 instanceof Integer)
						ris.put(indice2, new ValueIdentExpr(expression3, true,
								new IntegerType()));
					else
						ris.put(indice2, new ValueIdentExpr(expression3, false,
								new IntegerType()));
					}
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
