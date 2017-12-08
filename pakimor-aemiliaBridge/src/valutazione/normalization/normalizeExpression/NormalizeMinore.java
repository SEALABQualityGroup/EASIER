/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import interfacceSpecifiche.NumberExp;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.BooleanNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.MinoreNorm;
import specificheAEmilia.Boolean;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Minore;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeMinore {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeMinore(int depth)
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

	public ExpressionNorm normalizeMinore(Minore minore, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		Expression expression = minore.getExpr1();
		Expression expression2 = minore.getExpr2();
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
					boolean b = d < e;
					Boolean boolean1 = new Boolean(b);
					BooleanNorm booleanNorm = new BooleanNorm();
					booleanNorm.setOldExpression(minore);
					booleanNorm.setNewExpression(boolean1);
					return booleanNorm;
					} 
				else 
					{
					Minore minore2 = new Minore(expression5, expression6);
					MinoreNorm minoreNorm = new MinoreNorm();
					minoreNorm.setOldExpression(minore);
					minoreNorm.setNewExpression(minore2);
					return minoreNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + minore.toString();
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
			String string = "Normalizing error for " + minore.toString();
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

    public ExpressionNorm normalizeMinore(Minore minore, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			Expression expression = minore.getExpr1();
			Expression expression2 = minore.getExpr2();
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
					if ((expression5 instanceof NumberExp)
							&& (expression6 instanceof NumberExp)) 
						{
						NumberExp numberExp = (NumberExp) expression5;
						NumberExp numberExp2 = (NumberExp) expression6;
						double d = numberExp.getNumber();
						double e = numberExp2.getNumber();
						boolean b = d < e;
						Boolean boolean1 = new Boolean(b);
						BooleanNorm booleanNorm = new BooleanNorm();
						booleanNorm.setOldExpression(minore);
						booleanNorm.setNewExpression(boolean1);
						return booleanNorm;
						} 
					else 
						{
						Minore minore2 = new Minore(expression5, expression6);
						MinoreNorm minoreNorm = new MinoreNorm();
						minoreNorm.setOldExpression(minore);
						minoreNorm.setNewExpression(minore2);
						return minoreNorm;
						}
					} 
				else 
					{
					String string = "Normalizing error for " + minore.toString();
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
				String string = "Normalizing error for " + minore.toString();
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
