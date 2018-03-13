/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.IntegerNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Integer;
import specificheAEmilia.Length;
import specificheAEmilia.ListCons;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeLength {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeLength(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public IntegerNorm normalizeLength(Length length, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression2 = length.getList();
		NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
		normalizeExpressionStrict.setTm(this.tm);
		ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression2, parent,
				dataType);
		if (!normalizeExpressionStrict.isErrorOccurred()) 
			{
			Expression expression = expression3.getNewExpression();
			if (expression instanceof ListCons) 
				{
				ListCons listCons = (ListCons) expression;
				Expression[] expressions = listCons.getListElements();
				int i = expressions.length;
				Integer integer = new Integer(i);
				IntegerNorm integerNorm = new IntegerNorm();
				integerNorm.setOldExpression(length);
				integerNorm.setNewExpression(integer);
				return integerNorm;
				} 
			else 
				{
				String string = "Normalizing error for " + length.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = "Normalized expression " + expression.toString() + " of " + expression2.toString() + " is not list";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
		else 
			{
			String string = "Normalizing error for " + length.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
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

    public IntegerNorm normalizeLength(Length length, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			IdentExpr expression2 = length.getList();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression2, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				Expression expression4 = expression3.getNewExpression();
				if (expression4 instanceof ListCons) 
					{
					ListCons listCons = (ListCons) expression4;
					Expression[] expressions = listCons.getListElements();
					int i = expressions.length;
					Integer integer = new Integer(i);
					IntegerNorm integerNorm = new IntegerNorm();
					integerNorm.setOldExpression(length);
					integerNorm.setNewExpression(integer);
					return integerNorm;
					} 
				else 
					{
					// 1
					String string = "Normalizing error for " + length.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = "Normalized expression " + expression4.toString() + " of " + expression2.toString() + " is not list";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				// 2
				String string = "Normalizing error for " + length.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
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
