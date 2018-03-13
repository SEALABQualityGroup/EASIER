/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.structure.AEIdeclNorm;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIident;
import specificheAEmilia.ElemType;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.Find;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;
import valutazione.scope.ScopeAEIdecl;
import valutazione.scope.ScopeArchiType;
import valutazione.typeChecking.aeiChecking.CheckParameters;
import valutazione.typeChecking.aeiChecking.CheckSelector;

/**
 * @author Mirko
 *
 */
public class NormalizeAEIdecl {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeAEIdecl(ScopeArchiType scopeArchiType,int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Normalizza le espressioni contenute nella dichiarazione
	 * di istanza, fornita come parametro.
	 *
	 * @param aeid
	 * @return la dichiarazione normalizzata
	 * @throws NormalizeException
	 */
	public AEIdeclNorm normalizeAEIdecl(AEIdecl aeid) 
		throws NormalizeException 
		{
		try {
			AEIdecl clone = aeid.copy();
			// bisogna ispezionare la struttura di clone.
			// Si preleva lo scope della dichiarazione di istanza
			ScopeAEIdecl scopeAEIdecl = new ScopeAEIdecl(this.scopeArchiType,this.depth + 1);
			// ASE sco contains all pre-defined constants
			TreeMap<String, ValueIdentExpr> sco = scopeAEIdecl.getScopeAEIdecl(clone);
			if (scopeAEIdecl.isErrorOccurred())
				{
				String string = "Normalizing error for " + aeid.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scopeAEIdecl.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			// effettuiamo il type checking
			AEIident aeIident = clone.getAeIident();
			ElemType elemType = Find.getElemTypeFromAei(aeIident, this.scopeArchiType.getAt());
			CheckSelector checkSelector = new CheckSelector(clone, sco, this.depth + 1);
			if (!checkSelector.checkSelector()) 
				{
				String string = "Type checking error for " + aeid.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkSelector.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			CheckParameters checkParameters = new CheckParameters(clone, elemType, sco,this.depth + 1);
			if (!checkParameters.checkParameters()) 
				{
				String string = "Type checking error for " + aeid.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkParameters.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			// si normalizza l'eventuale espressione di selezione
			Expression selector = aeIident.getSelector();
			if (selector != null) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression = normalizeExpressionStrict.normalize(selector, "", null, sco);
				if (normalizeExpressionStrict.isErrorOccurred()) 
					{
					String string = "Type checking error for " + aeid.toString();
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
			// si normalizzano i valori attuali per i parametri formali costanti dell'AET.
			if (clone.getActualParams() != null) 
				{
				for (int i = 0; i < clone.getActualParams().length; i++) 
					{
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(clone.getActualParams()[i], "", null, sco);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + aeid.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						continue;
						}
					// ASE old expression has been set 
					//instead of new one, which contains the value of actualParam[i] 
//					Expression expression2 = expression.getNewExpression();
					Expression expression2 = expression.getOldExpression();
					clone.getActualParams()[i] = expression2;
					}
				}
			AEIdeclNorm ideclNorm = new AEIdeclNorm();
			ideclNorm.setOldAEIdecl(aeid);
			ideclNorm.setNewAEIdecl(clone);
			return ideclNorm;
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
