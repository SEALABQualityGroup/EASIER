/**
 * 
 */
package valutazione.normalization.normalizeParts.normalizeInteractionDeclInd;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.structure.InteractionDeclNorm;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerType;
import specificheAEmilia.InteractionDecl;
import specificheAEmilia.InteractionDeclInd;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;
import valutazione.scope.ScopeArchiType;
import valutazione.scope.ScopeInteractionDecl;

/**
 * @author Mirko
 *
 */
public class NormalizeInteractionDeclInd {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

	public NormalizeInteractionDeclInd(ScopeArchiType scopeArchiType, int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce un array di dichiarazioni di interazioni semplici, a partire
	 * da una dichiarazione indicizzata.
	 *
	 * @param idi
	 * @return
	 * @throws NormalizeException
	 */
	public InteractionDecl[] normalizeInteractionDeclInd(InteractionDeclInd idi)
			throws NormalizeException {
			try {
				InteractionDeclInd clone = idi.copy();
				// si preleva lo scope della dichiarazione
				// di interazione architetturale
				ScopeInteractionDecl scopeInteractionDecl = new ScopeInteractionDecl(this.scopeArchiType,this.depth + 1);
				TreeMap<String, ValueIdentExpr> sco = scopeInteractionDecl.getScopeInteractionDecl(clone);
				if (scopeInteractionDecl.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + idi;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = scopeInteractionDecl.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				int indiceIniziale = 0, indiceFinale = 0;
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				// si valuta l'espressione iniziale
				ExpressionNorm expression = normalizeExpressionStrict
						.normalize(clone.getBeginningExpr(), "", null, sco);
				if (normalizeExpressionStrict.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + idi.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				Expression expression21 = expression.getNewExpression();
				clone.setExprInizio(expression21);
				// si valuta l'espressione finale
				NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression2 = normalizeExpressionStrict2
						.normalize(clone.getEndingExpr(), "", null, sco);
				if (normalizeExpressionStrict2.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + idi.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				Expression expression3 = expression2.getNewExpression();
				clone.setExprFine(expression3);
				// effettuo il type checking
				valutazione.typeChecking.interactionIndChecking.CheckRange checkRange = new valutazione.typeChecking.interactionIndChecking.CheckRange(clone,sco,this.depth + 1);
				if (!checkRange.checkRange()) 
					{
					String string = "Normalizing error for  " + clone;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = checkRange.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				valutazione.typeChecking.interactionIndChecking.CheckRange2 checkRange2 = new valutazione.typeChecking.interactionIndChecking.CheckRange2(clone,sco,this.depth + 1);
				if (!checkRange2.checkRange2()) 
					{
					String string = "Normalizing error for  " + clone;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = checkRange2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				valutazione.typeChecking.interactionIndChecking.CheckRange3 checkRange3 = new valutazione.typeChecking.interactionIndChecking.CheckRange3(clone,this.depth + 1);
				if (!checkRange3.checkRange3()) 
					{
					String string = "Normalizing error for  " + clone;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = checkRange3.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				// si preleva l'espressione iniziale come un
				// intero
				indiceIniziale = ((Integer) clone.getBeginningExpr())
						.getValore();
				// si preleva l'espressione finale come un
				// intero
				indiceFinale = ((Integer) clone.getEndingExpr()).getValore();
				// si alloca memoria per l'array risultato
				InteractionDecl[] ris = new InteractionDecl[indiceFinale
						- indiceIniziale + 1];
				for (int i = 0; i < indiceFinale - indiceIniziale + 1; i++) 
					{
					// si aggiorna l'indice
					sco.put(clone.getIndex(), new ValueIdentExpr(new Integer(i
							+ indiceIniziale), true, new IntegerType()));
					// si normalizza la dichiarazione secondo il nuovo valore
					// per l'indice
					NormalizeInteractionDecl normalizeInteractionDecl = new NormalizeInteractionDecl(this.depth + 1);
					InteractionDeclNorm interactionDeclNorm = normalizeInteractionDecl.normalizeInteractionDecl(
							clone, sco);
					if (normalizeInteractionDecl.isErrorOccurred()) 
						{
						String string = "Normalizing error for "+ idi.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeInteractionDecl.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					ris[i] = interactionDeclNorm.getNewInteractionDecl();
					}
				// si restituisce l'array
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
