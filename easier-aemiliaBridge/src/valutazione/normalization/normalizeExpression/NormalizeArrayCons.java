/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ArrayConsNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.ArrayCons;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.typeChecking.NotEmptySequence;

/**
 * @author Mirko
 *
 */
class NormalizeArrayCons {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeArrayCons(int depth)
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

	public ArrayConsNorm normalizeArrayCons(ArrayCons arrayCons, String parent,
			DataType dataType)
		throws NormalizeException
		{
		try {
			Expression[] expressions = arrayCons.getArrayElements();
			// si effettua il type checking
			NotEmptySequence notEmptySequence = new NotEmptySequence(this.depth + 1);
			if (!notEmptySequence.isNotEmptySequence(arrayCons)) 
				{
				String string = "Normalizing error for " + arrayCons;
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = notEmptySequence.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			Expression[] expressions2 = new Expression[expressions.length];
			for (int i = 0; i < expressions.length; i++) 
				{
				Expression expression = expressions[i];
				NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
				normalizeExpression.setTm(this.tm);
				ExpressionNorm expression2 = normalizeExpression.normalize(expression, parent,
						dataType);
				if (!normalizeExpression.isErrorOccurred()) 
					{
					Expression expression3 = expression2.getNewExpression();
					expressions2[i] = expression3;
					} 
				else 
					{
					String string = "Normalizing error for " + arrayCons.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				}
			ArrayCons arrayCons2 = new ArrayCons(expressions2);
			ArrayConsNorm arrayConsNorm = new ArrayConsNorm();
			arrayConsNorm.setOldExpression(arrayCons);
			arrayConsNorm.setNewExpression(arrayCons2);
			return arrayConsNorm;
			} 
		catch (Exception e) 
			{
			throw new NormalizeException(e);
			}
		}

    public ArrayConsNorm normalizeArrayCons(ArrayCons arrayCons,
    		String parent,
    		DataType dataType,
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			Expression[] expressions = arrayCons.getArrayElements();
			// si effettua il type checking
			NotEmptySequence notEmptySequence = new NotEmptySequence(this.depth + 1);
			if (!notEmptySequence.isNotEmptySequence(arrayCons)) 
				{
				String string = "Normalizing error for " + arrayCons;
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = notEmptySequence.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			Expression[] expressions2 = new Expression[expressions.length];
			for (int i = 0; i < expressions.length; i++) 
				{
				NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
				ExpressionNorm expression = normalizeExpression.normalize(expressions[i], parent,	dataType, tma);
				if (!normalizeExpression.isErrorOccurred())
					expressions2[i] = expression.getNewExpression();
				else 
					{
					String string = "Normalizing error for " + arrayCons.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = notEmptySequence.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				}
			ArrayCons arrayCons2 = new ArrayCons(expressions2);
			ArrayConsNorm arrayConsNorm = new ArrayConsNorm();
			arrayConsNorm.setOldExpression(arrayCons);
			arrayConsNorm.setNewExpression(arrayCons2);
			return arrayConsNorm;
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
