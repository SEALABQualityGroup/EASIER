/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeIdentExpr {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeIdentExpr(int depth)
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

	public ExpressionNorm normalizeIdentExpr(IdentExpr identExpr, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		String string = identExpr.getNome();
		String string2 = "".equals(parent) ? string : parent + "." + string;
		ValueIdentExpr valueIdentExpr = this.tm.get(string2);
		if (valueIdentExpr == null) 
			{
			String string3 = "Normalizing error for " + identExpr.toString();
			this.errorMessage.setErrorMessage(string3);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			String string4 = "indentifier " + string2 + " is not in scope";
			errorMessage.setErrorMessage(string4);
			list.add(errorMessage);
			this.errorOccurred = true;
			return null;
			}	 
		else 
			{
			if (valueIdentExpr.isValutazione()) 
				{
				Expression expression = valueIdentExpr.getValore();
				MetodiVari metodiVari = new MetodiVari();
				ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(
						identExpr, expression);
				return expressionNorm;
				} 
			else 
				{
				Expression expression = identExpr.copy();
				MetodiVari metodiVari = new MetodiVari();
				ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(
						identExpr, expression);
				return expressionNorm;
				}
			}
		} 
	catch (Exception e) 
		{
		throw new NormalizeException(e);
		}
	}


    // qui si puo' effettuare type checking
    public ExpressionNorm normalizeIdentExpr(IdentExpr identExpr, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			String string = identExpr.getNome();
			String string2 = "".equals(parent) ? string : parent + "." + string;
			ValueIdentExpr valueIdentExpr = tma.get(string2);
			if (valueIdentExpr == null) 
				{
				String string3 = "Normalizing error for " + identExpr.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string4 = "indentifier " + string2 + " is not in scope";
				errorMessage.setErrorMessage(string4);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				} 
			else 
				{
				if (valueIdentExpr.isValutazione()) 
					{
					Expression expression = valueIdentExpr.getValore();
					MetodiVari metodiVari = new MetodiVari();
					return metodiVari.getExpressionNorm(identExpr, expression);
					} 
				else 
					{
					Expression expression = identExpr.copy();
					MetodiVari metodiVari = new MetodiVari();
					return metodiVari.getExpressionNorm(identExpr, expression);
					}
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
