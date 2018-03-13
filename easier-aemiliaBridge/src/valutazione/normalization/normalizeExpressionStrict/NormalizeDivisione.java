/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import interfacceSpecifiche.NumberExp;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.IntegerNorm;
import restrizioniIstanze.expressions.NumberExpNorm;
import restrizioniIstanze.expressions.RealNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Divisione;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.Real;

import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeDivisione {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeDivisione(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public NumberExpNorm normalizeDivisione(Divisione divisione, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		Expression expression = divisione.getExpr1();
		Expression expression2 = divisione.getExpr2();
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
				if ((expression5 instanceof NumberExp)
						&& (expression6 instanceof NumberExp)) {
					NumberExp numberExp = (NumberExp) expression5;
					NumberExp numberExp2 = (NumberExp) expression6;
					double d = numberExp.getNumber();
					double e = numberExp2.getNumber();
					if (e != 0) {
						double f = d / e;
						double g = Math.rint(f);
						if (f == g) {
							Double double1 = new Double(f);
							Integer integer = new Integer(double1
									.intValue());
							IntegerNorm integerNorm = new IntegerNorm();
							integerNorm.setOldExpression(divisione);
							integerNorm.setNewExpression(integer);
							return integerNorm;
						} else {
							Real real = new Real(f);
							RealNorm realNorm = new RealNorm();
							realNorm.setOldExpression(divisione);
							realNorm.setNewExpression(real);
							return realNorm;
						}
					} else 
						{
						String string = "Normalizing error for " + divisione.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = "division by zero";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
				} else 
					{
					if (!(expression5 instanceof NumberExp))
						{
						String string = "Normalizing error for " + divisione.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = "Normalized expression " + expression5.toString() + " of " +
							expression.toString() + " is not number";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					if (!(expression6 instanceof NumberExp))
						{
						String string = "Normalizing error for " + divisione.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = "Normalized expression " + expression6.toString() + " of " +
							expression2.toString() + " is not number";
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
				String string = "Normalizing error for " + divisione.toString();
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
			String string = "Normalizing error for " + divisione.toString();
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

    public NumberExpNorm normalizeDivisione(Divisione divisione, 
    		String parent,
    		DataType dataType,
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			Expression expression = divisione.getExpr1();
			Expression expression2 = divisione.getExpr2();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, parent,
						dataType, tma);
				if (!normalizeExpressionStrict2.isErrorOccurred()) {
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					if (expression5 instanceof NumberExp) {
						NumberExp numberExp = (NumberExp) expression5;
						double d = numberExp.getNumber();
						if (expression6 instanceof NumberExp) {
							NumberExp numberExp2 = (NumberExp) expression6;
							double e = numberExp2.getNumber();
							double f = d / e;
							if (e != 0) {
								double g = Math.rint(f);
								if (f == g) {
									Double double1 = new Double(f);
									Integer integer = new Integer(double1
											.intValue());
									IntegerNorm integerNorm = new IntegerNorm();
									integerNorm.setOldExpression(divisione);
									integerNorm.setNewExpression(integer);
									return integerNorm;
								} else {
									Real real = new Real(f);
									RealNorm realNorm = new RealNorm();
									realNorm.setOldExpression(divisione);
									realNorm.setNewExpression(real);
									return realNorm;
								}
							} else 
								{
								// 1
								String string = "Normalizing error for " + divisione.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								String string2 = "division by zero";
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
							String string = "Normalizing error for " + divisione.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							String string2 = "Normalized expression " + expression6.toString() + " of " +
								expression2.toString() + " is not number";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						} 
					else 
						{
						// 3
						String string = "Normalizing error for " + divisione.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = "Normalized expression " + expression5.toString() + " of " +
							expression.toString() + " is not number";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					} 
				else 
					{
					// 4
					String string = "Normalizing error for " + divisione.toString();
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
				// 5
				String string = "Normalizing error for " + divisione.toString();
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
