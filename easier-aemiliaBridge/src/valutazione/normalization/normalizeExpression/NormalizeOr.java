/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.BooleanNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.OrNorm;
import specificheAEmilia.Boolean;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Or;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeOr {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeOr(int depth)
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

	public ExpressionNorm normalizeOr(Or or, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		Expression expression = or.getExpr1();
		Expression expression2 = or.getExpr2();
		NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
		normalizeExpression.setTm(this.tm);
		ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent, dataType);
		if (!normalizeExpression.isErrorOccurred()) 
			{
			Expression expression5 = expression3.getNewExpression();
			if (expression5 instanceof Boolean) 
				{
				Boolean boolean1 = (Boolean) expression5;
				boolean b = boolean1.getValore();
				if (b) 
					{
					Boolean boolean2 = new Boolean(true);
					BooleanNorm booleanNorm = new BooleanNorm();
					booleanNorm.setOldExpression(or);
					booleanNorm.setNewExpression(boolean2);
					return booleanNorm;
					} 
				else 
					{
					NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
					normalizeExpression2.setTm(this.tm);
					ExpressionNorm expression4 = normalizeExpression2.normalize(expression2,
							parent, dataType);
					if (!normalizeExpression2.isErrorOccurred()) 
						{
						Expression expression6 = expression4
								.getNewExpression();
						if (expression6 instanceof Boolean) 
							{
							Boolean boolean2 = (Boolean) expression6;
							BooleanNorm booleanNorm = new BooleanNorm();
							booleanNorm.setOldExpression(or);
							booleanNorm.setNewExpression(boolean2);
							return booleanNorm;
							} 
						else 
							{
							Or or2 = new Or(boolean1, expression6);
							OrNorm orNorm = new OrNorm();
							orNorm.setOldExpression(or);
							orNorm.setNewExpression(or2);
							return orNorm;
							}
						} 
					else 
						{
						String string = "Normalizing error for " + or.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					}
				} 
			else 
				{
				NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
				normalizeExpression2.setTm(this.tm);
				ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent,
						dataType);
				if (!normalizeExpression2.isErrorOccurred()) 
					{
					Expression expression6 = expression4.getNewExpression();
					Or or2 = new Or(expression5, expression6);
					OrNorm orNorm = new OrNorm();
					orNorm.setOldExpression(or);
					orNorm.setNewExpression(or2);
					return orNorm;
					} 
				else 
					{
					String string = "Normalizing error for " + or.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				}
			} 
		else 
			{
			String string = "Normalizing error for " + or.toString();
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

    public ExpressionNorm normalizeOr(Or or, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			Expression expression = or.getExpr1();
			Expression expression2 = or.getExpr2();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				Expression expression4 = expression3.getNewExpression();
				if (expression4 instanceof Boolean) 
					{
					Boolean boolean1 = (Boolean) expression4;
					boolean b = boolean1.getValore();
					if (b) 
						{
						Boolean boolean2 = new Boolean(true);
						BooleanNorm booleanNorm = new BooleanNorm();
						booleanNorm.setOldExpression(or);
						booleanNorm.setNewExpression(boolean2);
						return booleanNorm;
						} 
					else 
						{
						NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expressionNorm = normalizeExpression2.normalize(expression2,
								parent, dataType, tma);
						if (!normalizeExpression2.isErrorOccurred()) 
							{
							Expression expression5 = expressionNorm
									.getNewExpression();
							if (expression5 instanceof Boolean) 
								{
								Boolean boolean2 = (Boolean) expression5;
								BooleanNorm booleanNorm = new BooleanNorm();
								booleanNorm.setOldExpression(or);
								booleanNorm.setNewExpression(boolean2);
								return booleanNorm;
								} 
							else 
								{
								Or or2 = new Or(boolean1, expression5);
								OrNorm orNorm = new OrNorm();
								orNorm.setOldExpression(or);
								orNorm.setNewExpression(or2);
								return orNorm;
								}
							} 
						else 
							{
							String string = "Normalizing error for " + or.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						}
					} 
				else 
					{
					NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
					ExpressionNorm expressionNorm = normalizeExpression2.normalize(expression2,
							parent, dataType, tma);
					if (!normalizeExpression2.isErrorOccurred()) 
						{
						Expression expression6 = expressionNorm
								.getNewExpression();
						Or or2 = new Or(expression4, expression6);
						OrNorm orNorm = new OrNorm();
						orNorm.setOldExpression(or);
						orNorm.setNewExpression(or2);
						return orNorm;
						} 
					else 
						{
						String string = "Normalizing error for " + or.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					}
				} 
			else 
				{
				String string = "Normalizing error for " + or.toString();
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
