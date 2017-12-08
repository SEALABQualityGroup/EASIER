/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.ListConsNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.ListCons;
import specificheAEmilia.Tail;

import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeTail {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeTail(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ListConsNorm normalizeTail(Tail tail, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = tail.getList();
		NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
		normalizeExpressionStrict.setTm(this.tm);
		ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent,
				dataType);
		if (!normalizeExpressionStrict.isErrorOccurred()) 
			{
			Expression expression3 = expression2.getNewExpression();
			if (expression3 instanceof ListCons) 
				{
				ListCons listCons = (ListCons) expression3;
				Expression[] expressions = listCons.getListElements();
				// expressionse'un'array di espressioni gia' normalizzate
				CopyOnWriteArrayList<Expression> copyOnWriteArrayList = new CopyOnWriteArrayList<Expression>(
						expressions);
				if (copyOnWriteArrayList.size() > 0) 
					{
					copyOnWriteArrayList.remove(0);
					Expression[] expressions2 = copyOnWriteArrayList
							.toArray(new Expression[0]);
					ListCons listCons2 = new ListCons(expressions2);
					ListConsNorm listConsNorm = new ListConsNorm();
					listConsNorm.setOldExpression(tail);
					listConsNorm.setNewExpression(listCons2);
					return listConsNorm;
					} 
				else 
					{
					Expression[] expressions2 = new Expression[0];
					ListCons listCons2 = new ListCons(expressions2);
					ListConsNorm listConsNorm = new ListConsNorm();
					listConsNorm.setOldExpression(tail);
					listConsNorm.setNewExpression(listCons2);
					return listConsNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + tail.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				String string2 = "Normalized expression " + expression3.toString() + " of " + expression.toString() + " is not list";
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
		else 
			{
			String string = "Normalizing error for " + tail.toString();
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
	
    public ListConsNorm normalizeTail(Tail tail, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			IdentExpr expression = tail.getList();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				Expression expression3 = expression2.getNewExpression();
				if (expression3 instanceof ListCons) 
					{
					ListCons listCons = (ListCons) expression3;
					Expression[] expressions = listCons.getListElements();
					// expressionse'un'array di espressioni gia' normalizzate
					CopyOnWriteArrayList<Expression> copyOnWriteArrayList = new CopyOnWriteArrayList<Expression>(
							expressions);
					if (copyOnWriteArrayList.size() > 0) 
						{
						copyOnWriteArrayList.remove(0);
						Expression[] expressions2 = copyOnWriteArrayList
								.toArray(new Expression[0]);
						ListCons listCons2 = new ListCons(expressions2);
						ListConsNorm listConsNorm = new ListConsNorm();
						listConsNorm.setOldExpression(tail);
						listConsNorm.setNewExpression(listCons2);
						return listConsNorm;
						} 
					else 
						{
						Expression[] expressions2 = new Expression[0];
						ListCons listCons2 = new ListCons(expressions2);
						ListConsNorm listConsNorm = new ListConsNorm();
						listConsNorm.setOldExpression(tail);
						listConsNorm.setNewExpression(listCons2);
						return listConsNorm;
						}
					} 
				else 
					{
					// 1
					String string = "Normalizing error for " + tail.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string2 = "Normalized expression " + expression3.toString() + " of " + expression.toString() + " is not list";
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				// 2
				String string = "Normalizing error for " + tail.toString();
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
