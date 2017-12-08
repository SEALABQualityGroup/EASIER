/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ArrayConsNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.ArrayCons;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Integer;
import specificheAEmilia.Write;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeWrite {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeWrite(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ArrayConsNorm normalizeWrite(Write write, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = write.getArray();
		Expression expression2 = write.getElement();
		Expression expression3 = write.getIndex();
		NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
		normalizeExpressionStrict.setTm(this.tm);
		ExpressionNorm expression4 = normalizeExpressionStrict.normalize(expression, parent,
				dataType);
		if (!normalizeExpressionStrict.isErrorOccurred()) 
			{
			NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1); 
			normalizeExpressionStrict2.setTm(this.tm);
			ExpressionNorm expression5 = normalizeExpressionStrict2.normalize(expression2, parent,
					dataType);
			if (!normalizeExpressionStrict2.isErrorOccurred()) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict3 = new NormalizeExpressionStrict(this.depth + 1);
				normalizeExpressionStrict3.setTm(this.tm);
				ExpressionNorm expression6 = normalizeExpressionStrict3.normalize(expression3, parent,
						dataType);
				if (!normalizeExpressionStrict3.isErrorOccurred()) 
					{
					Expression expression7 = expression4.getNewExpression();
					Expression expression8 = expression5.getNewExpression();
					Expression expression9 = expression6.getNewExpression();
					if ((expression7 instanceof ArrayCons)
							&& (expression9 instanceof Integer)) 
						{
						ArrayCons arrayCons = (ArrayCons) expression7;
						Integer integer = (Integer) expression9;
						Expression[] expressions = arrayCons
								.getArrayElements();
						int i = integer.getValore();
						if (i >= expressions.length) 
							{
							String string = "Normalizing error for " + write.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2 = "Index of bound exception: index = "
									+ i
									+ "; array length = "
									+ expressions.length;
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							} 
						else if (i < 0) 
							{
							String string = "Normalizing error for " + write.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2 = "Index of bound exception: index = "
								+ i;
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							} 
						else 
							{
							expressions[i] = expression8;
							ArrayConsNorm arrayConsNorm = new ArrayConsNorm();
							arrayConsNorm.setOldExpression(write);
							arrayConsNorm.setNewExpression(arrayCons);
							return arrayConsNorm;
							}
						} 
					else 
						{
						if (!(expression7 instanceof ArrayCons))
							{
							String string = "Normalizing error for " + write.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2 = "Normalized expression " + expression7.toString() + " of " + expression.toString() + " is not array";
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						if (!(expression9 instanceof Integer))
							{
							String string = "Normalizing error for " + write.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2 = "Normalized expression " + expression9.toString() + " of " + expression3.toString() + " is not integer";
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
					String string = "Normalizing error for " + write.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict3.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + write.toString();
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
			String string = "Normalizing error for " + write.toString();
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

    public ArrayConsNorm normalizeWrite(Write write, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			IdentExpr expression = write.getArray();
			Expression expression2 = write.getElement();
			Expression expression3 = write.getIndex();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression4 = normalizeExpressionStrict.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression5 = normalizeExpressionStrict2.normalize(expression2, parent,
						dataType, tma);
				if (!normalizeExpressionStrict2.isErrorOccurred()) 
					{
					NormalizeExpressionStrict normalizeExpressionStrict3 = new NormalizeExpressionStrict(this.depth + 1);
					ExpressionNorm expression6 = normalizeExpressionStrict3.normalize(expression3, parent,
							dataType, tma);
					if (!normalizeExpressionStrict3.isErrorOccurred()) 
						{
						Expression expression7 = expression4.getNewExpression();
						Expression expression8 = expression5.getNewExpression();
						Expression expression9 = expression6.getNewExpression();
						if ((expression7 instanceof ArrayCons)
								&& (expression9 instanceof Integer)) 
							{
							ArrayCons arrayCons = (ArrayCons) expression7;
							Integer integer = (Integer) expression9;
							Expression[] expressions = arrayCons
									.getArrayElements();
							int i = integer.getValore();
							if (i >= expressions.length) 
								{
								// 1
								String string = "Normalizing error for " + write.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
								String string2 = "Index of bound exception: index = "
										+ i
										+ "; array length = "
										+ expressions.length;
								errorMessage.setErrorMessage(string2);
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								} 
							else if (i < 0) 
								{
								// 2
								String string = "Normalizing error for " + write.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
								String string2 = "Index of bound exception: index = "
									+ i;
								errorMessage.setErrorMessage(string2);
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								} 
							else 
								{
								expressions[i] = expression8;
								ArrayConsNorm arrayConsNorm = new ArrayConsNorm();
								arrayConsNorm.setOldExpression(write);
								arrayConsNorm.setNewExpression(arrayCons);
								return arrayConsNorm;
								}
							} 
						else 
							{
							if (!(expression7 instanceof ArrayCons))
								{
								// 3
								String string = "Normalizing error for " + write.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
								String string2 = "Normalized expression " + expression7.toString() + " of " + expression.toString() + " is not array";
								errorMessage.setErrorMessage(string2);
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								}
							if (!(expression9 instanceof Integer))
								{
								// 4
								String string = "Normalizing error for " + write.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
								String string2 = "Normalized expression " + expression9.toString() + " of " + expression3.toString() + " is not integer";
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
						// 5
						String string = "Normalizing error for " + write.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict3.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					} 
				else 
					{
					// 6
					String string = "Normalizing error for " + write.toString();
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
				// 7
				String string = "Normalizing error for " + write.toString();
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
