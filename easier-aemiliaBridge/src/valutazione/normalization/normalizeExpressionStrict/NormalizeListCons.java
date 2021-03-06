/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.ListConsNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.ListCons;

import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeListCons {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeListCons(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ListConsNorm normalizeListCons(ListCons listCons, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		Expression[] expressions = listCons.getListElements();
		Expression[] expressions2 = new Expression[expressions.length];
		for (int i = 0; i < expressions.length; i++) 
			{
			Expression expression = expressions[i];
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			normalizeExpressionStrict.setTm(this.tm);
			ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent,
					dataType);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				Expression expression3 = expression2.getNewExpression();
				expressions2[i] = expression3;
				} 
			else 
				{
				String string = "Normalizing error for " + listCons.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			}
		ListCons listCons2 = new ListCons(expressions2);
		ListConsNorm listConsNorm = new ListConsNorm();
		listConsNorm.setOldExpression(listCons);
		listConsNorm.setNewExpression(listCons2);
		return listConsNorm;
		} 
	catch (Exception e) 
		{
		throw new NormalizeException(e);
		}
	}

    public ListConsNorm normalizeListCons(ListCons listCons, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			Expression[] expressions = listCons.getListElements();
			// expression2 contiene espressioni normalizzate
			Expression[] expressions2 = new Expression[expressions.length];
			for (int i = 0; i < expressions.length; i++) 
				{
				Expression expression = expressions[i];
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent,
						dataType, tma);
				if (!normalizeExpressionStrict.isErrorOccurred()) 
					{
					Expression expression3 = expression2.getNewExpression();
					expressions2[i] = expression3;
					} 
				else 
					{
					String string = "Normalizing error for " + listCons.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				}
			ListCons listCons2 = new ListCons(expressions2);
			ListConsNorm listConsNorm = new ListConsNorm();
			listConsNorm.setOldExpression(listCons);
			listConsNorm.setNewExpression(listCons2);
			return listConsNorm;
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
