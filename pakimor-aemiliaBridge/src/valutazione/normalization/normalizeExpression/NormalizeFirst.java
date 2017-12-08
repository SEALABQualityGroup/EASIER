/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.FirstNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.First;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.ListCons;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeFirst {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeFirst(int depth)
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

	public ExpressionNorm normalizeFirst(First first, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = first.getList();
		NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
		normalizeExpression.setTm(this.tm);
		ExpressionNorm expression2 = normalizeExpression.normalize(expression, parent, dataType);
		if (!normalizeExpression.isErrorOccurred()) 
			{
			Expression expression3 = expression2.getNewExpression();
			if (expression3 instanceof ListCons) 
				{
				ListCons listCons = (ListCons) expression3;
				// listCons puo' essere una list vuota
				Expression[] expressions = listCons.getListElements();
				if (expressions.length == 0) 
					{
					String string = "Normalizing erroro for " + first.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = "list " + listCons.toString()
							+ " has zero lenght";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					} 
				else 
					{
					Expression expression4 = expressions[0];
					// per precondizione expression4e'un'espressione gia' normalizzata
					MetodiVari metodiVari = new MetodiVari();
					ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(first, expression4);
					return expressionNorm;
					}
				} 
			else 
				{
				First first2 = new First(expression.copy());
				FirstNorm firstNorm = new FirstNorm();
				firstNorm.setOldExpression(first);
				firstNorm.setNewExpression(first2);
				return firstNorm;
				}
			} 
		else 
			{
			String string = "Normalizing error for " + first.toString();
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

    public ExpressionNorm normalizeFirst(First first, 
    		String parent,
    		DataType dataType,
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			IdentExpr expression = first.getList();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression2 = normalizeExpression.normalize(expression, parent, dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				Expression expression3 = expression2.getNewExpression();
				if (expression3 instanceof ListCons) 
					{
					ListCons listCons = (ListCons) expression3;
					// per precondizione gli elementi di listCons sono normalizzati
					Expression[] expressions = listCons.getListElements();
					if (expressions.length == 0) 
						{
						String string = "Normalizing erroro for " + first.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = "list " + listCons.toString()
								+ " has zero lenght";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						} 
					else 
						{
						Expression expression4 = expressions[0];
						MetodiVari metodiVari = new MetodiVari();
						return metodiVari.getExpressionNorm(first, expression4);
						}
					} 
				else 
					{
					First first2 = new First(expression.copy());
					FirstNorm firstNorm = new FirstNorm();
					firstNorm.setOldExpression(first);
					firstNorm.setNewExpression(first2);
					return firstNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + first.toString();
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
