/**
 * 
 */
package valutazione.normalization.normalizeParts.normalizeAEIdeclInd;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.structure.AEIdeclNorm;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIdeclInd;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;
import valutazione.scope.ScopeAEIdecl;
import valutazione.scope.ScopeArchiType;
import valutazione.typeChecking.aeiIndChecking.CheckRange;
import valutazione.typeChecking.aeiIndChecking.CheckRange2;
import valutazione.typeChecking.aeiIndChecking.CheckRange3;

/**
 * @author Mirko
 *
 */
public class NormalizeAEIdeclInd {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

	public NormalizeAEIdeclInd(ScopeArchiType scopeArchiType, int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}


	/**
	 * Restituisce un array di dichiarazioni di istanze
	 * di elementi architetturali semplici,
	 * a partire da una dichiarazione indicizzata.
	 *
	 * @param aeidi
	 * @return
	 * @throws NormalizeException
	 */
	public AEIdecl[] normalizeAEIdeclInd(AEIdeclInd aeidi) throws NormalizeException {
	try {
		AEIdeclInd clone = aeidi.copy();
		// si preleva lo scope della dichiarazione di istanza
		ScopeAEIdecl scopeAEIdecl = new ScopeAEIdecl(this.scopeArchiType,this.depth + 1);
		TreeMap<String, ValueIdentExpr> sco = scopeAEIdecl.getScopeAEIdecl(clone);
		int indiceIniziale = 0, indiceFinale = 0;
		// si valuta l'espressione iniziale
		NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
		ExpressionNorm expression = normalizeExpressionStrict.normalize(clone
				.getBeginningExpr(), "", null, sco);
		if (normalizeExpressionStrict.isErrorOccurred()) 
			{
			String string = "Normalizing error for " + aeidi.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
			list.add(errorMessage);
			this.errorOccurred = true;
			return null;
			}
		// si valuta l'espressione finale
		NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
		ExpressionNorm expression2 = normalizeExpressionStrict2.normalize(clone
				.getEndingExpr(), "", null, sco);
		if (normalizeExpressionStrict2.isErrorOccurred()) 
			{
			String string = "Normalizing error for " + aeidi.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
			list.add(errorMessage);
			this.errorOccurred = true;
			return null;
			}
		Expression expression21 = expression.getNewExpression();
		clone.setBeginningExpr(expression21);
		Expression expression3 = expression2.getNewExpression();
		clone.setEndingExpr(expression3);
		// effettuo il type checking
		CheckRange checkRange = new CheckRange(clone,sco,this.depth + 1);
		if (!checkRange.checkRange()) 
			{
			String string = "Normalizing error for " + aeidi.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = checkRange.getErrorMessage();
			list.add(errorMessage);
			this.errorOccurred = true;
			return null;
			}
		CheckRange2 checkRange2 = new CheckRange2(clone,sco,this.depth + 1); 
		if (!checkRange2.checkRange2()) 
			{
			String string = "Normalizing error for " + aeidi.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = checkRange2.getErrorMessage();
			list.add(errorMessage);
			this.errorOccurred = true;
			return null;
			}
		CheckRange3 checkRange3 = new CheckRange3(clone,this.depth + 1);
		if (!checkRange3.checkRange3()) 
			{
			String string = "Normalizing error for " + aeidi.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = checkRange3.getErrorMessage();
			list.add(errorMessage);
			this.errorOccurred = true;
			return null;
			}
		// si preleva l'espressione iniziale come un intero
		indiceIniziale = ((Integer) clone.getBeginningExpr()).getValore();
		// si preleva l'espressione finale come un intero
		indiceFinale = ((Integer) clone.getEndingExpr()).getValore();
		// si alloca memoria per l'array risultato
		AEIdecl[] ris = new AEIdecl[indiceFinale - indiceIniziale + 1];
		for (int i = 0; i < indiceFinale - indiceIniziale + 1; i++) 
			{
			// si aggiorna l'indice
			sco.put(clone.getIndex(), new ValueIdentExpr(new Integer(i
					+ indiceIniziale), true, new IntegerType()));
			// si normalizza la dichiarazione secondo il nuovo valore
			// dell'indice
			NormalizeAEIdecl normalizeAEIdecl = new NormalizeAEIdecl(this.scopeArchiType,this.depth + 1);
			AEIdeclNorm ideclNorm = normalizeAEIdecl.normalizeAEIdecl(clone, sco);
			if (normalizeAEIdecl.isErrorOccurred()) 
				{
				String string = "Normalizing error for " + aeidi.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeAEIdecl.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			ris[i] = ideclNorm.getNewAEIdecl();
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
