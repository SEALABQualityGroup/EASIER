/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.ListConsNorm;
import restrizioniIstanze.expressions.RemoveNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Integer;
import specificheAEmilia.ListCons;
import specificheAEmilia.Remove;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeRemove {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeRemove(int depth)
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

	public ExpressionNorm normalizeRemove(Remove remove, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = remove.getList();
		Expression expression2 = remove.getPosition();
		NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
		normalizeExpression.setTm(this.tm);
		ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent, dataType);
		if (!normalizeExpression.isErrorOccurred()) 
			{
			NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
			normalizeExpression2.setTm(this.tm);
			ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent, dataType);
			if (!normalizeExpression2.isErrorOccurred()) 
				{
				Expression expression5 = expression3.getNewExpression();
				Expression expression6 = expression4.getNewExpression();
				if ((expression5 instanceof ListCons)
						&& (expression6 instanceof Integer)) 
					{
					ListCons listCons = (ListCons) expression5;
					Integer integer = (Integer) expression6;
					Expression[] expressions = listCons.getListElements();
					int i = integer.getValore();
					if (i < 1) 
						{
						String string = "Normalizing error for " + remove.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						String string2 = "The position is less than one";
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					CopyOnWriteArrayList<Expression> copyOnWriteArrayList = new CopyOnWriteArrayList<Expression>(
							expressions);
					if (copyOnWriteArrayList.size() < i) 
						{
						String string = "Normalizing error for " + remove.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						String string2  = "List has not sufficiently many elements";
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					copyOnWriteArrayList.remove(i - 1);
					Expression[] expressions2 = copyOnWriteArrayList
							.toArray(new Expression[0]);
					ListCons listCons2 = new ListCons(expressions2);
					ListConsNorm listConsNorm = new ListConsNorm();
					listConsNorm.setOldExpression(remove);
					listConsNorm.setNewExpression(listCons2);
					return listConsNorm;
					} 
				else 
					{
					Remove remove2 = new Remove(expression6, expression.copy());
					RemoveNorm removeNorm = new RemoveNorm();
					removeNorm.setOldExpression(remove);
					removeNorm.setNewExpression(remove2);
					return removeNorm;
					}
				} 
			else 
				{
				String string  = "Normalizing error for " + remove.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
		else 
			{
			String string  = "Normalizing error for " + remove.toString();
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

    public ExpressionNorm normalizeRemove(Remove remove, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			IdentExpr expression = remove.getList();
			Expression expression2 = remove.getPosition();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent, dataType, tma);
				if (!normalizeExpression2.isErrorOccurred()) 
					{
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					if ((expression5 instanceof ListCons)
							&& (expression6 instanceof Integer)) 
						{
						ListCons listCons = (ListCons) expression5;
						Integer integer = (Integer) expression6;
						Expression[] expressions = listCons.getListElements();
						int i = integer.getValore();
						if (i < 1) 
							{
							String string = "Normalizing error for " + remove.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2 = "The position is less than one";
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						CopyOnWriteArrayList<Expression> copyOnWriteArrayList = new CopyOnWriteArrayList<Expression>(
								expressions);
						if (copyOnWriteArrayList.size() < i) 
							{
							String string = "Normalizing error for " + remove.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2  = "List has not sufficiently many elements";
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						copyOnWriteArrayList.remove(i - 1);
						Expression[] expressions2 = copyOnWriteArrayList
								.toArray(new Expression[0]);
						ListCons listCons2 = new ListCons(expressions2);
						ListConsNorm listConsNorm = new ListConsNorm();
						listConsNorm.setOldExpression(remove);
						listConsNorm.setNewExpression(listCons2);
						return listConsNorm;
						} 
					else 
						{
						Remove remove2 = new Remove(expression6, expression.copy());
						RemoveNorm removeNorm = new RemoveNorm();
						removeNorm.setOldExpression(remove);
						removeNorm.setNewExpression(remove2);
						return removeNorm;
						}
					} 
				else 
					{
					String string  = "Normalizing error for " + remove.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				String string  = "Normalizing error for " + remove.toString();
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
