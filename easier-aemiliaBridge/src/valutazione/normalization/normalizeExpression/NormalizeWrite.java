/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ArrayConsNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.WriteNorm;
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
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeWrite(int depth)
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

	public ExpressionNorm normalizeWrite(Write write, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = write.getArray();
		Expression expression2 = write.getElement();
		Expression expression3 = write.getIndex();
		NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
		normalizeExpression.setTm(this.tm);
		ExpressionNorm expression4 = normalizeExpression.normalize(expression, parent, dataType);
		if (!normalizeExpression.isErrorOccurred()) 
			{
			NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
			normalizeExpression2.setTm(this.tm);
			ExpressionNorm expression5 = normalizeExpression2.normalize(expression2, parent, dataType);
			if (!normalizeExpression2.isErrorOccurred()) 
				{
				NormalizeExpression normalizeExpression3 = new NormalizeExpression(this.depth + 1);
				normalizeExpression3.setTm(this.tm);
				ExpressionNorm expression6 = normalizeExpression3.normalize(expression3, parent, dataType);
				if (!normalizeExpression3.isErrorOccurred()) 
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
						Write write2 = new Write(expression9, expression8,
								expression.copy());
						WriteNorm writeNorm = new WriteNorm();
						writeNorm.setOldExpression(write);
						writeNorm.setNewExpression(write2);
						return writeNorm;
						}
					} 
				else 
					{
					String string = "Normalizing error for " + write.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpression3.getErrorMessage();
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
				ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
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

    public ExpressionNorm normalizeWrite(Write write, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			IdentExpr expression = write.getArray();
			Expression expression2 = write.getElement();
			Expression expression3 = write.getIndex();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression4 = normalizeExpression.normalize(expression, parent, dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
				ExpressionNorm expression5 = normalizeExpression2.normalize(expression2, parent, dataType, tma);
				if (!normalizeExpression2.isErrorOccurred()) 
					{
					NormalizeExpression normalizeExpression3 = new NormalizeExpression(this.depth + 1);
					ExpressionNorm expression6 = normalizeExpression3.normalize(expression3, parent, dataType, tma);
					if (!normalizeExpression3.isErrorOccurred()) 
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
							Write write2 = new Write(expression9, expression8,
									expression.copy());
							WriteNorm writeNorm = new WriteNorm();
							writeNorm.setOldExpression(write);
							writeNorm.setNewExpression(write2);
							return writeNorm;
							}
						} 
					else 
						{
						String string = "Normalizing error for " + write.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression3.getErrorMessage();
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
					ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
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
