/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.structure.InteractionDeclNorm;
import specificheAEmilia.AEIident;
import specificheAEmilia.Expression;
import specificheAEmilia.InteractionDecl;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;
import valutazione.scope.ScopeArchiType;
import valutazione.scope.ScopeInteractionDecl;
import valutazione.typeChecking.InteractionChecking;

/**
 * @author Mirko
 *
 */
public class NormalizeInteractionDecl {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeInteractionDecl(ScopeArchiType scopeArchiType,int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}	

	/**
	 * Normalizza le espressioni, contenute nella dichiarazione di interazione architetturale, fornita
	 * come parametro.
	 *
	 * @param id
	 * @return la dichiarazione di interazione normalizzata.
	 * @throws NormalizeException
	 */
	public InteractionDeclNorm normalizeInteractionDecl(InteractionDecl id)
			throws NormalizeException {
			try {
				InteractionDecl clone = id.copy();
				// si preleva lo scope per la valutazione della dichiarazione
				// di interazione architetturale
				ScopeInteractionDecl scopeInteractionDecl = new ScopeInteractionDecl(this.scopeArchiType,this.depth + 1);
				TreeMap<String, ValueIdentExpr> sco = scopeInteractionDecl.getScopeInteractionDecl(clone);
				if (scopeInteractionDecl.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + id;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = scopeInteractionDecl.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				// effettuiamo il type checking
				InteractionChecking interactionChecking = new InteractionChecking(
						clone, sco,this.depth + 1);
				if (!interactionChecking.checkSelector()) 
					{
					String string = "Normalizing error for " + id;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = interactionChecking.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				// si normalizza l'eventuale espressione di selezione
				// di istanza
				AEIident aeIident = clone.getAei();
				Expression selector = aeIident.getSelector();
				if (selector != null) 
					{
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					ExpressionNorm expression = normalizeExpressionStrict.normalize(selector, "", null, sco);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + id;
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
				InteractionDeclNorm interactionDeclNorm = new InteractionDeclNorm();
				interactionDeclNorm.setOldInteractionDecl(id);
				interactionDeclNorm.setNewInteractionDecl(clone);
				return interactionDeclNorm;
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
