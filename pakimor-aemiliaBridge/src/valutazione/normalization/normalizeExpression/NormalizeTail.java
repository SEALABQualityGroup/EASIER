/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.ListConsNorm;
import restrizioniIstanze.expressions.TailNorm;
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
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeTail(int depth)
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
	
	public ExpressionNorm normalizeTail(Tail tail, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = tail.getList();
		NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
		normalizeExpression.setTm(this.tm);
		ExpressionNorm expression2 = normalizeExpression.normalize(expression, parent, dataType);
		if (!normalizeExpression.isErrorOccurred()) 
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
				Tail tail2 = new Tail(expression.copy());
				TailNorm tailNorm = new TailNorm();
				tailNorm.setOldExpression(tail);
				tailNorm.setNewExpression(tail2);
				return tailNorm;
				}
			} 
		else 
			{
			String string = "Normalizing error for " + tail.toString();
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

    public ExpressionNorm normalizeTail(Tail tail, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			IdentExpr expression = tail.getList();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression2 = normalizeExpression.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
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
					Tail tail2 = new Tail(expression.copy());
					TailNorm tailNorm = new TailNorm();
					tailNorm.setOldExpression(tail);
					tailNorm.setNewExpression(tail2);
					return tailNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + tail.toString();
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
