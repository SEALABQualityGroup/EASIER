/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

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
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeIdentExpr(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
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
			String string4 = "indentifier " + string2 + " is not in scope";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
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
				String string3 = "Normalizing error for " + identExpr.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string4 = identExpr + " is not strict normalizable";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string4);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
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
    	/* MODELED */
    	try {
			String string = identExpr.getNome();
			String string2 = "".equals(parent) ? string : parent + "." + string;
			ValueIdentExpr valueIdentExpr = tma.get(string2);
			if (valueIdentExpr == null) 
				{
				// 1
				String string3 = "Normalizing error for " + identExpr.toString();
				this.errorMessage.setErrorMessage(string3);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string4 = "indentifier " + string2 + " is not in scope";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
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
					// 2
					String string3 = "Normalizing error for " + identExpr.toString();
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string4 = identExpr + " is not strict normalizable";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string4);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
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
