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
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeArrayCons(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ArrayConsNorm normalizeArrayCons(ArrayCons arrayCons, String parent,
			DataType dataType)
		throws NormalizeException
		{
		try {
			Expression[] expressions = arrayCons.getArrayElements();
			// si effettua type checking
			NotEmptySequence notEmptySequence = new NotEmptySequence(this.depth + 1);
			if (!notEmptySequence.isNotEmptySequence(arrayCons)) 
				{
				String string = "Normalizing error for " + arrayCons.toString();
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
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				normalizeExpressionStrict.setTm(this.tm);
				ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent,
						dataType);
				if (!normalizeExpressionStrict.isErrorOccurred()) 
					{
					Expression expression3 = expression2.getNewExpression();
					expressions2[i] = expression3;
					} 
				else 
					{
					String string = "Normalizing error for "
							+ arrayCons.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
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
    	/* MODELED */
    	try {
			Expression[] expressions = arrayCons.getArrayElements();
			// si effettua type checking
			NotEmptySequence notEmptySequence = new NotEmptySequence(this.depth + 1);
			if (!notEmptySequence.isNotEmptySequence(arrayCons)) 
				{
				// 1
				String string = "Normalizing error for " + arrayCons.toString();
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
				NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression = normalizeExpressionStrict.normalize(expressions[i], parent,
						dataType, tma);
				if (!normalizeExpressionStrict.isErrorOccurred())
					expressions2[i] = expression.getNewExpression();
				else 
					{
					// 2
					String string = "Normalizing error for "
							+ arrayCons.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
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
