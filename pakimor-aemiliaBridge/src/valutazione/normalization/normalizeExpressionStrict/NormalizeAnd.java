/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.BooleanNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.And;
import specificheAEmilia.Boolean;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeAnd {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeAnd(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

    /**
     * 
     * @param and
     * @param parent - il nome del parent a cui appartiene. Viene utilizzato per la
     * normalizzazione dell'espressione Get.
     * @param dataType - tipo dell'espressione da normalizzare. Viene utilizzato per la 
     * normalizzazione dell'espressione RecordCons
     * @return
     * @throws NormalizeException
     */
    public BooleanNorm normalizeAnd(And and, String parent, DataType dataType)
		throws NormalizeException
		{
    	try {
			Expression expression = and.getExpr1();
			Expression expression2 = and.getExpr2();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			normalizeExpressionStrict.setTm(this.tm);
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent, dataType);
			if (!normalizeExpressionStrict.isErrorOccurred()) {
				NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
				normalizeExpressionStrict2.setTm(this.tm);
				ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, parent,
						dataType);
				if (!normalizeExpressionStrict2.isErrorOccurred()) {
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					if (expression5 instanceof Boolean) {
						Boolean boolean1 = (Boolean) expression5;
						if (expression6 instanceof Boolean) {
							Boolean boolean2 = (Boolean) expression6;
							boolean b = boolean1.getValore();
							boolean c = boolean2.getValore();
							boolean d = b && c;
							Boolean boolean3 = new Boolean(d);
							BooleanNorm booleanNorm = new BooleanNorm();
							booleanNorm.setOldExpression(and);
							booleanNorm.setNewExpression(boolean3);
							return booleanNorm;
						} else {
							String string = "Normalizing error for " + and.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list2 = this.errorMessage.getCauses();
							String string2 = "Normalized expression " + expression6.toString() + 
							" of " + expression2.toString() + 
							" is not Boolean ";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
							list2.add(errorMessage);
							this.errorOccurred = true;
							return null;
						}
					} else {
						String string = "Normalizing error for " + and.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list2 = this.errorMessage.getCauses();
						String string2 = "Normalized expression " + expression5.toString() + 
						" of " + expression.toString() + 
						" is not Boolean ";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list2.add(errorMessage);
						this.errorOccurred = true;
						return null;
					}
				} else {
					String string = "Normalizing error for " + and.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
					list2.add(errorMessage);
					this.errorOccurred = true;
					return null;
				}
			} else {
				String string = "Normalizing error for " + and.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
				list2.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			} 
    	catch (Exception e) 
    		{
			throw new NormalizeException(e);
    		}
		}

    public BooleanNorm normalizeAnd(And and, 
    		String parent,
    		DataType dataType,
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			Expression expression = and.getExpr1();
			Expression expression2 = and.getExpr2();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			normalizeExpressionStrict.setTm(tma);
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) {
				NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
				normalizeExpressionStrict2.setTm(tma);
				ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, parent,
						dataType, tma);
				if (!normalizeExpressionStrict2.isErrorOccurred()) {
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					if (expression5 instanceof Boolean) {
						Boolean boolean1 = (Boolean) expression5;
						if (expression6 instanceof Boolean) {
							Boolean boolean2 = (Boolean) expression6;
							boolean b = boolean1.getValore();
							boolean c = boolean2.getValore();
							boolean d = b && c;
							Boolean boolean3 = new Boolean(d);
							BooleanNorm booleanNorm = new BooleanNorm();
							booleanNorm.setOldExpression(and);
							booleanNorm.setNewExpression(boolean3);
							return booleanNorm;
						} else {
							// 1
							String string = "Normalizing error for " + and.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list2 = this.errorMessage.getCauses();
							String string2 = "Normalized expression " + expression6.toString() + 
							" of " + expression2.toString() + 
							" is not Boolean ";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
							list2.add(errorMessage);
							this.errorOccurred = true;
							return null;
						}
					} else {
						// 2
						String string = "Normalizing error for " + and.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list2 = this.errorMessage.getCauses();
						String string2 = "Normalized expression " + expression5.toString() + 
						" of " + expression.toString() + 
						" is not Boolean ";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list2.add(errorMessage);
						this.errorOccurred = true;
						return null;
					}
				} else {
					// 3
					String string = "Normalizing error for " + and.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
					list2.add(errorMessage);
					this.errorOccurred = true;
					return null;
				}
			} else {
				// 4
				String string = "Normalizing error for " + and.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
				list2.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
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

	public void setTm(TreeMap<String, ValueIdentExpr> tm) {
		this.tm = tm;
	}
    
    
    
}
