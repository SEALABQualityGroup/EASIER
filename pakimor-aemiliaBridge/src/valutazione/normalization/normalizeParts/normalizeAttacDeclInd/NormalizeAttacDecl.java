/**
 * 
 */
package valutazione.normalization.normalizeParts.normalizeAttacDeclInd;

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
import valutazione.typeChecking.attacChecking.CheckFromSelector;
import valutazione.typeChecking.attacChecking.CheckToSelector;

/**
 * @author Mirko
 *
 */
class NormalizeAttacDecl {

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeAttacDecl(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	   * Restituisce una dichiarazione di collegamento semplice
	  * con lo scope fornito come parametro, a partire da una
	  * dichiarazione composta.
	  *
	 * @param ad
	 * @param sco
	 * @return
	 * @throws NormalizeException
	 */
	public AttacDeclNorm normalizeAttacDecl(AttacDecl ad, TreeMap<String, ValueIdentExpr> sco)
			throws NormalizeException 
			{
			try {
				AttacDecl clone = ad.copy();
				// effettuiamo il type checking
				CheckFromSelector checkFromSelector = new CheckFromSelector(clone,sco,this.depth + 1);
				if (!checkFromSelector.checkFromSelector()) 
					{
					String string = "Type checking error for "
							+ ad.toString();
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
					String string = "Type checking error for "
							+ ad.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = checkToSelector.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				// bisogna ispezionare la struttura di clone.
				// Si normalizzano le eventuali espressioni di selezione
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
				// si costruisce una dichiarazione semplice, e
				// la si restituisce come risultato
				AttacDecl ris = new AttacDecl();
				ris.setOutputAei(clone.getOutputAei());
				ris.setInputInteraction(clone.getInputInteraction());
				ris.setOutputInteraction(clone.getOutputInteraction());
				ris.setInputAei(clone.getInputAei());
				AttacDeclNorm attacDeclNorm = new AttacDeclNorm();
				attacDeclNorm.setOldAttacDecl(ad);
				attacDeclNorm.setNewAttacDecl(ris);
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
