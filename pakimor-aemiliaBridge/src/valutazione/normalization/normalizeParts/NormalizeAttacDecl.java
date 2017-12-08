/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.structure.AttacDeclNorm;
import specificheAEmilia.AEIident;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;
import valutazione.scope.ScopeArchiType;
import valutazione.scope.ScopeAttacDecl;
import valutazione.typeChecking.attacChecking.CheckFromSelector;
import valutazione.typeChecking.attacChecking.CheckToSelector;

/**
 * @author Mirko
 *
 */
public class NormalizeAttacDecl {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeAttacDecl(ScopeArchiType scopeArchiType, int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Normalizza le espressioni contenute nella dichiarazione di collegamento architetturale,
	 * fornita come parametro.
	 *
	 * @param ad
	 * @return la dichiarazione di collegamento architetturale normalizzata.
	 * @throws NormalizeException
	 */
	public AttacDeclNorm normalizeAttacDecl(AttacDecl ad) 
		throws NormalizeException 
		{
		try {
			AttacDecl clone = ad.copy();
			// si preleva lo scope della dichiarazione di collegamento
			ScopeAttacDecl scopeAttacDecl = new ScopeAttacDecl(this.scopeArchiType,this.depth + 1);
			TreeMap<String, ValueIdentExpr> sco = scopeAttacDecl.getScopeAttacDecl(clone);
			if (scopeAttacDecl.isErrorOccurred()) 
				{
				String string = "Error to get scope for " + ad;
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scopeAttacDecl.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			// effettuiamo il type checking
			CheckFromSelector checkFromSelector = new CheckFromSelector(clone,sco,this.depth + 1);
			if (!checkFromSelector.checkFromSelector()) 
				{
				String string = "Type checking error for " + clone;
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkFromSelector.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			CheckToSelector checkToSelector = new CheckToSelector(clone,sco,this.depth + 1);
			if (!checkToSelector.checkToSelector()) 
				{
				String string = "Type checking error for " + clone;
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkToSelector.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			// si valuta le eventuali espressioni di selezione
			// di istanze
			AEIident aeIident = clone.getOutputAei();
			Expression fromSelector = aeIident.getSelector();
			if (fromSelector != null) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression = normalizeExpressionStrict.normalize(fromSelector, "", null, sco);
				if (normalizeExpressionStrict.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + ad.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				Expression expression2 = expression.getNewExpression();
				aeIident.setSelector(expression2);
				}
			AEIident aeIident2 = clone.getInputAei();
			Expression toSelector = aeIident2.getSelector();
			if (toSelector != null) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression = normalizeExpressionStrict.normalize(toSelector, "", null, sco);
				if (normalizeExpressionStrict.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + ad.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				Expression expression2 = expression.getNewExpression();
				aeIident2.setSelector(expression2);
				}
			AttacDeclNorm attacDeclNorm = new AttacDeclNorm();
			attacDeclNorm.setOldAttacDecl(ad);
			attacDeclNorm.setNewAttacDecl(clone);
			return attacDeclNorm;
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
