/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.BooleanNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.NegazioneNorm;
import specificheAEmilia.Boolean;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Negazione;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeNegazione {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeNegazione(int depth)
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

	public ExpressionNorm normalizeNegazione(Negazione negazione, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		Expression expression = negazione.getExpr1();
		NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
		normalizeExpression.setTm(this.tm);
		ExpressionNorm expression2 = normalizeExpression.normalize(expression, parent, dataType);
		if (!normalizeExpression.isErrorOccurred()) 
			{
			Expression expression3 = expression2.getNewExpression();
			if (expression3 instanceof Boolean) 
				{
				Boolean boolean1 = (Boolean) expression3;
				boolean b = boolean1.getValore();
				Boolean boolean2 = new Boolean(!b);
				BooleanNorm booleanNorm = new BooleanNorm();
				booleanNorm.setOldExpression(negazione);
				booleanNorm.setNewExpression(boolean2);
				return booleanNorm;
				} 
			else 
				{
				Negazione negazione2 = new Negazione(expression3);
				NegazioneNorm negazioneNorm = new NegazioneNorm();
				negazioneNorm.setOldExpression(negazione);
				negazioneNorm.setNewExpression(negazione2);
				return negazioneNorm;
				}
			} 
		else 
			{
			String string = "Normalizing error for " + negazione.toString();
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

    public ExpressionNorm normalizeNegazione(Negazione negazione, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			Expression expression = negazione.getExpr1();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression2 = normalizeExpression.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				Expression expression3 = expression2.getNewExpression();
				if (expression3 instanceof Boolean) 
					{
					Boolean boolean1 = (Boolean) expression3;
					boolean b = boolean1.getValore();
					boolean c = !b;
					Boolean boolean2 = new Boolean(c);
					BooleanNorm booleanNorm = new BooleanNorm();
					booleanNorm.setOldExpression(negazione);
					booleanNorm.setNewExpression(boolean2);
					return booleanNorm;
					} 
				else 
					{
					Negazione negazione2 = new Negazione(expression3);
					NegazioneNorm negazioneNorm = new NegazioneNorm();
					negazioneNorm.setOldExpression(negazione);
					negazioneNorm.setNewExpression(negazione2);
					return negazioneNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + negazione.toString();
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
