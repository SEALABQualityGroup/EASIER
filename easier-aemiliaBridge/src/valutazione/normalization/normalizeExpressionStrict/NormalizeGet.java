/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Get;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.RecordCons;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.typeChecking.IdentExprRecordTyping;

/**
 * @author Mirko
 *
 */
class NormalizeGet {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeGet(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	// l'espressione get.getRecord() deve corrispondere ad un IdentExpr
	// caricato nello scope
	// parent deve corrisponde al nome del record
	public ExpressionNorm normalizeGet(Get get, String parent, DataType dataType)
		throws NormalizeException
		{
		try {
			// si effettua il controllo di tipo
			IdentExprRecordTyping identExprRecordTyping = new IdentExprRecordTyping(this.depth + 1);
			if (!identExprRecordTyping.isIdentExpRecord(get)) 
				{
				String string = "Normalizing error for " + get.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = identExprRecordTyping.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			IdentExpr expression = get.getRecord();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1); 
			normalizeExpressionStrict.setTm(this.tm);
			ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent, dataType);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				Expression expression3 = expression2.getNewExpression();
				String string = get.getField();
				if (expression3 instanceof RecordCons) 
					{
					String string2 = "".equals(parent) ? string : parent + "."
							+ string;
					if (this.tm.containsKey(string2)) 
						{
						ValueIdentExpr valueIdentExpr = this.tm.get(string2);
						Expression expression4 = valueIdentExpr.getValore();
						MetodiVari metodiVari = new MetodiVari();
						ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(get,
								expression4);
						return expressionNorm;
						} 
					else 
						{
						String string3 = "Normalizing error for " + get.toString();
						this.errorMessage.setErrorMessage(string3);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						String string4 = string + " must belong to "
								+ expression2.toString();
						errorMessage.setErrorMessage(string4);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					} 
				else 
					{
					String string3 = "Normalizing error for " + get.toString();
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string4 = "Normalized expression " + expression3.toString() + " of " + expression.toString() + 
						" is not record";
					errorMessage.setErrorMessage(string4);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + get.toString();
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
	
	// l'espressione get.getRecord() deve corrispondere ad un IdentExpr
	// caricato nello scope
    public ExpressionNorm normalizeGet(Get get, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			// effettuo il controllo di tipo
			IdentExprRecordTyping identExprRecordTyping = new IdentExprRecordTyping(this.depth + 1);
			if (!identExprRecordTyping.isIdentExpRecord(get)) 
				{
				// 1
				String string = "Normalizing error for " + get.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = identExprRecordTyping.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			IdentExpr expression = get.getRecord();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				Expression expression3 = expression2.getNewExpression();
				String string = get.getField();
				if (expression3 instanceof RecordCons) 
					{
					String string2 = "".equals(parent) ? string : parent + "."
							+ string;
					if (tma.containsKey(string2)) 
						{
						ValueIdentExpr valueIdentExpr = tma.get(string2);
						Expression expression4 = valueIdentExpr.getValore();
						MetodiVari metodiVari = new MetodiVari();
						ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(get,
								expression4);
						return expressionNorm;
						} 
					else 
						{
						// 2
						String string3 = "Normalizing error for " + get.toString();
						this.errorMessage.setErrorMessage(string3);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						String string4 = string + " must belong to "
								+ expression2.toString();
						errorMessage.setErrorMessage(string4);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					} 
				else 
					{
					// 3
					String string3 = "Normalizing error for " + get.toString();
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string4 = "Normalized expression " + expression3.toString() + " of " + expression.toString() + 
						" is not record";
					errorMessage.setErrorMessage(string4);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				// 4
				String string = "Normalizing error for " + get.toString();
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
