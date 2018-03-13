/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
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
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeFirst(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		}

	public ExpressionNorm normalizeFirst(First first, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = first.getList();
		NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
		normalizeExpressionStrict.setTm(this.tm);
		ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent, dataType);
		if (!normalizeExpressionStrict.isErrorOccurred()) 
			{
			Expression expression3 = expression2.getNewExpression();
			if (expression3 instanceof ListCons) 
				{
				ListCons listCons = (ListCons) expression3;
				// listCons puo' essere una list vuota
				Expression[] expressions = listCons.getListElements();
				if (expressions.length == 0) 
					{
					String string = "Normalizing error for " + first.toString();
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
					ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(
							first, expression4);
					return expressionNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + first.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = "Normalized expression " + expression3.toString() + " of " + expression.toString() + 
						" is not list";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
		else 
			{
			String string = "Normalizing error for " + first.toString();
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
	
    public ExpressionNorm normalizeFirst(First first, 
    		String parent,
    		DataType dataType,
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			IdentExpr expression = first.getList();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				Expression expression3 = expression2.getNewExpression();
				if (expression3 instanceof ListCons) 
					{
					ListCons listCons = (ListCons) expression3;
					// per precondizione gli elementi di listCons sono normalizzati
					Expression[] expressions = listCons.getListElements();
					if (expressions.length == 0) 
						{
						// 1
						String string = "Normalizing error for " + first.toString();
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
						ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(
								first, expression4);
						return expressionNorm;
						}
					} 
				else 
					{
					// 2
					String string = "Normalizing error for " + first.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = "Normalized expression " + expression3.toString() + " of " + expression.toString() + 
							" is not list";
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
				String string = "Normalizing error for " + first.toString();
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
