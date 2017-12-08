/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.IntegerNorm;
import restrizioniIstanze.expressions.LengthNorm;
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
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeLength(int depth)
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
	
	public ExpressionNorm normalizeLength(Length length, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression2 = length.getList();
		NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
		normalizeExpression.setTm(this.tm);
		ExpressionNorm expression3 = normalizeExpression.normalize(expression2, parent,	dataType);
		if (!normalizeExpression.isErrorOccurred()) 
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
				Length length2 = new Length(expression2.copy());
				LengthNorm lengthNorm = new LengthNorm();
				lengthNorm.setOldExpression(length);
				lengthNorm.setNewExpression(length2);
				return lengthNorm;
				}
			} 
		else 
			{
			String string = "Normalizing error for " + length.toString();
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

    public ExpressionNorm normalizeLength(Length expression, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			IdentExpr expression2 = expression.getList();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression2, parent, dataType);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				Expression expression4 = expression3.getNewExpression();
				if (expression4 instanceof ListCons) 
					{
					ListCons listCons = (ListCons) expression4;
					Expression[] expressions = listCons.getListElements();
					int i = expressions.length;
					Integer integer = new Integer(i);
					IntegerNorm integerNorm = new IntegerNorm();
					integerNorm.setOldExpression(expression);
					integerNorm.setNewExpression(integer);
					return integerNorm;
					} 
				else 
					{
					Length length = new Length(expression2.copy());
					LengthNorm lengthNorm = new LengthNorm();
					lengthNorm.setOldExpression(expression);
					lengthNorm.setNewExpression(length);
					return lengthNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + expression.toString();
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
