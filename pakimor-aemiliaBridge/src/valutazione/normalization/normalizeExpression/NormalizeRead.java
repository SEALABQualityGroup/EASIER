/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.ReadNorm;
import specificheAEmilia.ArrayCons;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Integer;
import specificheAEmilia.Read;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeRead {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeRead(int depth)
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

	public ExpressionNorm normalizeRead(Read read, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = read.getArray();
		Expression expression2 = read.getIndex();
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
				if ((expression5 instanceof ArrayCons)
						&& (expression6 instanceof Integer)) 
					{
					ArrayCons arrayCons = (ArrayCons) expression5;
					Integer integer = (Integer) expression6;
					Expression[] expressions = arrayCons.getArrayElements();
					int i = integer.getValore();
					MetodiVari metodiVari = new MetodiVari();
					ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(
							read, expressions[i]);
					return expressionNorm;
					} 
				else 
					{
					Read read2 = new Read(expression6, expression.copy());
					ReadNorm readNorm = new ReadNorm();
					readNorm.setOldExpression(read);
					readNorm.setNewExpression(read2);
					return readNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + read.toString();
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
			String string = "Normalizing error for " + read.toString();
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

    public ExpressionNorm normalizeRead(Read read, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			IdentExpr expression = read.getArray();
			Expression expression2 = read.getIndex();
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
					if ((expression5 instanceof ArrayCons)
							&& (expression6 instanceof Integer)) 
						{
						ArrayCons arrayCons = (ArrayCons) expression5;
						Integer integer = (Integer) expression6;
						Expression[] expressions = arrayCons.getArrayElements();
						int i = integer.getValore();
						MetodiVari metodiVari = new MetodiVari();
						ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(
								read, expressions[i]);
						return expressionNorm;
						} 
					else 
						{
						Read read2 = new Read(expression6, expression.copy());
						ReadNorm readNorm = new ReadNorm();
						readNorm.setOldExpression(read);
						readNorm.setNewExpression(read2);
						return readNorm;
						}
					} 
				else 
					{
					String string = "Normalizing error for " + read.toString();
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
				String string = "Normalizing error for " + read.toString();
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
