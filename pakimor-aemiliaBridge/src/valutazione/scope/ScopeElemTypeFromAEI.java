/**
 * 
 */
package valutazione.scope;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIident;
import specificheAEmilia.ElemType;
import specificheAEmilia.Expression;
import specificheAEmilia.ParamDeclaration;
import utilities.ErrorMessage;
import valutazione.Find;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;

/**
 * @author Mirko
 *
 */
public class ScopeElemTypeFromAEI {

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	private ScopeArchiType scopeArchiType;

	
    public ScopeElemTypeFromAEI(ScopeArchiType scopeArchiType,int depth) 
    	{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
    	}


	/**
     * Restituisce lo scope, che serve per la valutazione di espressioni, all'interno di una dichiarazione
     * di tipo di elemento architetturale, secondo la dichiarazione di istanza fornita come parametro.
     * Il TreeMap restituito contiene gli identificatori con i rispettivi valori, prelevati 
     * dal mapping tra parametri attuali e parametri costanti
     * del tipo di elemento architetturale, presente nella dichiarazione di istanza.
     *
     * @param aeid
     * @return
     * @throws NormalizeException
     */
    public TreeMap<String,ValueIdentExpr> getScopeElemTypeFromAEI(AEIdecl aeid) throws NormalizeException
        {
    	/* MODELED */
        try {
        	AEIdecl idecl = aeid.copy();
			// si preleva il tipo di elemento architetturale
        	AEIident aeIident = idecl.getAeIident();
			ElemType et = Find.getElemTypeFromAei(aeIident, this.scopeArchiType.getAt());
			// a partire dallo scope per il tipo architetturale, si aggiungono i parametri allo scope, con
			// valore uguale a quello che corrispondente al parametro attuale
			ParamDeclaration[] dps = et.getHeader().getParameters();
			Expression[] es = idecl.getActualParams();
			if (dps.length != es.length
					&& !(dps.length == 1 && es.length == 0 && dps[0] == null)) 
				{
				// 1
				String string = "Scope evaluating error for " + aeid.getAET();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string2 = "The size of actual parameters and formals parameters is not equal";
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			TreeMap<String, ValueIdentExpr> tma = new TreeMap<String, ValueIdentExpr>();
			if (es != null) 
				{
				for (int i = 0; i < es.length; i++) 
					{
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(es[i], "", dps[i].getType(), this.scopeArchiType.getScope());
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 2
						String string = "Scope evaluating error for " + aeid.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					Expression expression2 = expression.getNewExpression();
					tma.put(dps[i].getName(), new ValueIdentExpr(expression2,
							true, dps[i].getType()));
					}
				}
			return tma;
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
