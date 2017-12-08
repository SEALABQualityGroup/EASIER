/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import interfacceSpecifiche.NumberExp;
import restrizioniIstanze.expressions.BooleanNorm;
import restrizioniIstanze.expressions.DifferentNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.Boolean;
import specificheAEmilia.DataType;
import specificheAEmilia.Different;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeDifferent {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeDifferent(int depth)
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

	public ExpressionNorm normalizeDifferent(Different different, String parent, DataType dataType)
		throws NormalizeException
		{
		try {
			Expression expression = different.getExpr1();
			Expression expression2 = different.getExpr2();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			normalizeExpression.setTm(this.tm);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent, dataType);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
				normalizeExpression2.setTm(this.tm);
				ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent,
						dataType);
				if (!normalizeExpression2.isErrorOccurred()) 
					{
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					if ((expression5 instanceof NumberExp)
							&& (expression6 instanceof NumberExp)) 
						{
						NumberExp numberExp = (NumberExp) expression5;
						NumberExp numberExp2 = (NumberExp) expression6;
						double d = numberExp.getNumber();
						double e = numberExp2.getNumber();
						Boolean boolean1 = new Boolean(d != e);
						BooleanNorm booleanNorm = new BooleanNorm();
						booleanNorm.setOldExpression(different);
						booleanNorm.setNewExpression(boolean1);
						return booleanNorm;
						} 
					else 
						{
						Different different2 = new Different(expression5,
								expression6);
						DifferentNorm differentNorm = new DifferentNorm();
						differentNorm.setOldExpression(different);
						differentNorm.setNewExpression(different2);
						return differentNorm;
						}
					} 
				else 
					{
					String string = "Normalizing error for "
							+ different.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				String string = "Normalizing error for "
					+ different.toString();
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

    public ExpressionNorm normalizeDifferent(Different different,
    		String parent,
    		DataType dataType,
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			Expression expression = different.getExpr1();
			Expression expression2 = different.getExpr2();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent,
						dataType, tma);
				if (!normalizeExpression2.isErrorOccurred()) 
					{
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					if (expression5 instanceof NumberExp) 
						{
						NumberExp numberExp = (NumberExp) expression5;
						double d = numberExp.getNumber();
						if (expression6 instanceof NumberExp) 
							{
							NumberExp numberExp2 = (NumberExp) expression6;
							double e = numberExp2.getNumber();
							if (d != e) 
								{
								Boolean boolean1 = new Boolean(true);
								BooleanNorm booleanNorm = new BooleanNorm();
								booleanNorm.setOldExpression(different);
								booleanNorm.setNewExpression(boolean1);
								return booleanNorm;
								} 
							else 
								{
								Boolean boolean1 = new Boolean(false);
								BooleanNorm booleanNorm = new BooleanNorm();
								booleanNorm.setOldExpression(different);
								booleanNorm.setNewExpression(boolean1);
								return booleanNorm;
								}
							} 
						else 
							{
							Different different2 = new Different(expression5,
									expression6);
							DifferentNorm differentNorm = new DifferentNorm();
							differentNorm.setOldExpression(different);
							differentNorm.setNewExpression(different2);
							return differentNorm;
							}
						} 
					else 
						{
						Different different2 = new Different(expression5,
								expression6);
						DifferentNorm differentNorm = new DifferentNorm();
						differentNorm.setOldExpression(different);
						differentNorm.setNewExpression(different2);
						return differentNorm;
						}
					} 
				else 
					{
					String string = "Normalizing error for "
							+ different.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				String string = "Normalizing error for "
						+ different.toString();
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
