/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.dataTypes.NormalTypeNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.structure.HeaderNorm;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.Const;
import specificheAEmilia.ConstInit;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.NormalType;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.VarInit;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeDataType.NormalizeNormalType;
import valutazione.normalization.normalizeExpression.NormalizeExpression;
import valutazione.scope.ScopeArchiType;
import valutazione.scope.ScopeBehavHeaderFromAEI;

/**
 * @author Mirko
 *
 */
public class NormalizeBehavHeaderFromAEI {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeBehavHeaderFromAEI(ScopeArchiType scopeArchiType, int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Normalizza l'intestazione di un comportamento di
	 * un elemento architetturale, a partire da una dichiarazione di una sua istanza,
	 * e dall'intestazione di una sua equazione comportamentale.
	 *
	 * @param aeid
	 * @param i - intestazione da normalizzare
	 * @return l'intestazione normalizzata
	 * @throws NormalizeException
	 */
	public HeaderNorm normalizeBehavHeaderFromAEI(AEIdecl aeid, Header i)
			throws NormalizeException 
			{
			/* MODELED */
			try {
				Header clone = i.copy();
				// si preleva lo scope per la valutazione dell'intestazione
				ScopeBehavHeaderFromAEI scopeBehavHeaderFromAEI = new ScopeBehavHeaderFromAEI(this.scopeArchiType,this.depth + 1);
				TreeMap<String, ValueIdentExpr> sco = scopeBehavHeaderFromAEI.getScopeBehavHeaderFromAEI(aeid);
				if (scopeBehavHeaderFromAEI.isErrorOccurred()) 
					{
					// 1
					String string = "Normalizing error of " + i + " for "
							+ aeid;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = this.scopeArchiType.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				// si prelevano le dichiarazioni dei parametri da normalizzare
				ParamDeclaration[] dps = clone.getParameters();
				if (dps == null) 
					{
					HeaderNorm headerNorm = new HeaderNorm();
					headerNorm.setOldHeader(i);
					headerNorm.setNewHeader(clone);
					headerNorm.setAEIdecl(aeid);
					return headerNorm;
					}
				if (dps instanceof ConstInit[]) 
					{
					// 2
					String string = "Normalizing error of " + i + " for "
							+ aeid;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = "Architectural type header normalization";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				if (dps instanceof Const[]) 
					{
					// 3
					String string = "Normalizing error of " + i + " for "
							+ aeid;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = "Architectural element type header normalization";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				for (int j = 0; j < dps.length; j++) 
					{
					// si normalizzano le eventuali espressioni di inizializzazione e il tipo di dato
					if (dps[j] instanceof VarInit) 
						{
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expression = normalizeExpression
								.normalize(((VarInit) dps[j]).getExpr(), "",
										((VarInit) dps[j]).getType(), sco);
						if (normalizeExpression.isErrorOccurred()) 
							{
							// 4
							String string = "Normalizing error of " + i + " for "
									+ aeid;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						((VarInit) dps[j]).setExpr(expression2);
						NormalizeNormalType normalizeNormalType = new NormalizeNormalType(this.depth + 1);
						NormalTypeNorm normalTypeNorm = normalizeNormalType.normalize(((VarInit) dps[j]).getType(), sco);
						if (normalizeNormalType.isErrorOccurred())
							{
							// 5
							String string = "Normalizing error of " + i + " for "
									+ aeid;
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeNormalType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalType normalType = normalTypeNorm.getNewDataType();
						((VarInit) dps[j]).setType(normalType);
						}
					}
				HeaderNorm headerNorm = new HeaderNorm();
				headerNorm.setOldHeader(i);
				headerNorm.setNewHeader(clone);
				headerNorm.setAEIdecl(aeid);
				return headerNorm;
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
