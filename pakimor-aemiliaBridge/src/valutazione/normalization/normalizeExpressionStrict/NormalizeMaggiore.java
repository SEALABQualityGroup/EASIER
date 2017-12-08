/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import interfacceSpecifiche.NumberExp;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.BooleanNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.Boolean;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Maggiore;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeMaggiore {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeMaggiore(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public BooleanNorm normalizeMaggiore(Maggiore maggiore, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		Expression expression = maggiore.getExpr1();
		Expression expression2 = maggiore.getExpr2();
		NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
		normalizeExpressionStrict.setTm(this.tm);
		ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent, dataType);
		if (!normalizeExpressionStrict.isErrorOccurred()) 
			{
			NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
			normalizeExpressionStrict2.setTm(this.tm);
			ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, parent,
					dataType);
			if (!normalizeExpressionStrict2.isErrorOccurred()) 
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
					boolean b = d > e;
					Boolean boolean1 = new Boolean(b);
					BooleanNorm booleanNorm = new BooleanNorm();
					booleanNorm.setOldExpression(maggiore);
					booleanNorm.setNewExpression(boolean1);
					return booleanNorm;
					} 
				else 
					{
					if (!(expression5 instanceof NumberExp))
						{
						String string = "Normalizing error for " + maggiore.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = "Normalized expression " + expression5.toString() + " of " + expression.toString()  + " is not number";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					if (!(expression6 instanceof NumberExp))
						{
						String string = "Normalizing error for " + maggiore.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = "Normalized expression " + expression6.toString() + " of " + expression2.toString()  + " is not number";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					return null;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + maggiore.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
		else 
			{
			String string = "Normalizing error for " + maggiore.toString();
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

    public BooleanNorm normalizeMaggiore(Maggiore maggiore, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			Expression expression = maggiore.getExpr1();
			Expression expression2 = maggiore.getExpr2();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, parent,
						dataType, tma);
				if (!normalizeExpressionStrict2.isErrorOccurred()) 
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
						boolean b = d > e;
						Boolean boolean1 = new Boolean(b);
						BooleanNorm booleanNorm = new BooleanNorm();
						booleanNorm.setOldExpression(maggiore);
						booleanNorm.setNewExpression(boolean1);
						return booleanNorm;
						} 
					else 
						{
						if (!(expression5 instanceof NumberExp))
							{
							// 1
							String string = "Normalizing error for " + maggiore.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							String string2 = "Normalized expression " + expression5.toString() + " of " + expression.toString()  + " is not number";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						if (!(expression6 instanceof NumberExp))
							{
							// 2
							String string = "Normalizing error for " + maggiore.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							String string2 = "Normalized expression " + expression6.toString() + " of " + expression2.toString()  + " is not number";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						return null;
						}
					} 
				else 
					{
					// 3
					String string = "Normalizing error for " + maggiore.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				// 4
				String string = "Normalizing error for " + maggiore.toString();
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
