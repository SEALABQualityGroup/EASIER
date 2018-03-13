/**
 * 
 */
package valutazione.normalization.normalizeParts.normalizeInteractionDeclInd;

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
import valutazione.typeChecking.InteractionChecking;

/**
 * @author Mirko
 *
 */
class NormalizeInteractionDecl {

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeInteractionDecl(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	   * Restituisce una dichiarazione di interazione semplice
	  * con lo scope fornito come parametro, a partire da una
	  * dichiarazione composta.
	  *
	 * @param ad
	 * @param sco
	 * @return
	 * @throws NormalizeException
	 */
	public InteractionDeclNorm normalizeInteractionDecl(InteractionDecl ad, TreeMap<String, ValueIdentExpr> sco)
			throws NormalizeException 
			{
			try {
				InteractionDecl clone = ad.copy();
				// effettuiamo il type checking
				InteractionChecking interactionChecking = new InteractionChecking(
						clone, sco,this.depth + 1);
				if (!interactionChecking.checkSelector()) 
					{
					String string = "Normalizing error "+ ad;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = interactionChecking.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				// bisogna ispezionare la struttura di clone.
				// Si normalizza l'eventuale espressione di selezione.
				AEIident aeIident = clone.getAei();
				Expression selector = aeIident.getSelector();
				if (selector != null) 
					{
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(selector, "", null, sco);
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
				// si costruisce una dichiarazione semplice, e
				// la si restituisce come risultato
				InteractionDecl ris = new InteractionDecl();
				ris.setAei(clone.getAei());
				ris.setInteraction(clone.getInteraction());
				InteractionDeclNorm interactionDeclNorm = new InteractionDeclNorm();
				interactionDeclNorm.setOldInteractionDecl(ad);
				interactionDeclNorm.setNewInteractionDecl(ris);
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
