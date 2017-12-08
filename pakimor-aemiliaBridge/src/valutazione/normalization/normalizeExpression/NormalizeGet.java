/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.GetNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Get;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.RecordCons;

import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeGet {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeGet(int depth)
    	{
    	super();
    	this.tm = new TreeMap<String, ValueIdentExpr>();
    	this.depth = depth;
    	this.errorMessage = new ErrorMessage(depth);
    	}
    
	public TreeMap<String, ValueIdentExpr> getTm() 
		{
		return tm;
		}

	public void setTm(TreeMap<String, ValueIdentExpr> tm) 
		{
		this.tm = tm;
		}

	// l'espressione get.getRecord() deve corrispondere ad un IdentExpr
	// caricato nello scope
	// parent deve corrisponde al nome del record
	public ExpressionNorm normalizeGet(Get get, String parent, DataType dataType)
		throws NormalizeException
		{
    	try {
			IdentExpr expression = get.getRecord();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			normalizeExpression.setTm(this.tm);
			ExpressionNorm expression2 = normalizeExpression.normalize(expression, parent, dataType);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				Expression expression3 = expression2.getNewExpression();
				String string = get.getField();
				if (expression3 instanceof RecordCons) 
					{
					String string2 = "".equals(parent) ? string : parent + "."
							+ string;
					if (this.tm.containsKey(string2)) 
						{
						ValueIdentExpr valueIdentExpr = this.tm.get(string2);
						Expression expression4 = valueIdentExpr.getValore();
						MetodiVari metodiVari = new MetodiVari();
						ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(get,expression4);
						return expressionNorm;
						} 
					else 
						{
						String string3 = "Normalizing error for " + get.toString();
						this.errorMessage.setErrorMessage(string3);
						String string4 = string + " must belong to " + expression2.toString();
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string4);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					} 
				else 
					{
					Get get2 = new Get(new String(string), expression.copy());
					GetNorm getNorm = new GetNorm();
					getNorm.setOldExpression(get);
					getNorm.setNewExpression(get2);
					return getNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + get.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
    	catch (Exception e) 
    		{
    		throw new NormalizeException(e);
    		}
		}
	
	// l'espressione get.getRecord() deve corrispondere ad un IdentExpr
	// caricato nello scope
    public ExpressionNorm normalizeGet(Get get, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			IdentExpr expression = get.getRecord();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression2 = normalizeExpression.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				Expression expression3 = expression2.getNewExpression();
				String string = get.getField();
				if (expression3 instanceof RecordCons) 
					{
					String string2 = "".equals(parent) ? string : parent + "."
							+ string;
					if (tma.containsKey(string2)) 
						{
						ValueIdentExpr valueIdentExpr = tma.get(string2);
						Expression expression4 = valueIdentExpr.getValore();
						MetodiVari metodiVari = new MetodiVari();
						return metodiVari.getExpressionNorm(get, expression4);
						} 
					else 
						{
						String string3 = "Normalizing error for " + get.toString();
						this.errorMessage.setErrorMessage(string3);
						String string4 = string + " must belong to " + expression2.toString();
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string4);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					} 
				else 
					{
					Get get2 = new Get(new String(string), expression.copy());
					GetNorm getNorm = new GetNorm();
					getNorm.setOldExpression(get);
					getNorm.setNewExpression(get2);
					return getNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + get.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
    	catch (Exception e) 
    		{
    		throw new NormalizeException(e);
    		}
    	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}
    
    
}
