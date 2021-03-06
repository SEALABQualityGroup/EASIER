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
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeRemove(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ListConsNorm normalizeRemove(Remove remove, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = remove.getList();
		Expression expression2 = remove.getPosition();
		NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
		normalizeExpressionStrict.setTm(this.tm);
		ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent,
				dataType);
		if (!normalizeExpressionStrict.isErrorOccurred()) 
			{
			NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
			normalizeExpressionStrict2.setTm(this.tm);
			ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, parent,
					dataType);
			if (!normalizeExpressionStrict2.isErrorOccurred()) 
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
						String string2 = "List has not sufficiently many elements";
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
					if (!(expression5 instanceof ListCons))
						{
						String string = "Normalizing error for " + remove.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						String string2 = "Normalized expression " + expression5.toString() + " of " + expression.toString() + " is not list";
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					if (!(expression6 instanceof Integer))
						{
						String string = "Normalizing error for " + remove.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						String string2 = "Normalized expression " + expression6.toString() + " of " + expression2.toString() + " is not integer";
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					return null;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + remove.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			} 
		else 
			{
			String string = "Normalizing error for " + remove.toString();
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

    public ListConsNorm normalizeRemove(Remove remove, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			IdentExpr expression = remove.getList();
			Expression expression2 = remove.getPosition();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, parent,
						dataType, tma);
				if (!normalizeExpressionStrict2.isErrorOccurred()) 
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
							// 1
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
							// 2
							String string = "Normalizing error for " + remove.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2 = "List has not sufficiently many elements";
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
						if (!(expression5 instanceof ListCons))
							{
							// 3
							String string = "Normalizing error for " + remove.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2 = "Normalized expression " + expression5.toString() + " of " + expression.toString() + " is not list";
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						if (!(expression6 instanceof Integer))
							{
							// 4
							String string = "Normalizing error for " + remove.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2 = "Normalized expression " + expression6.toString() + " of " + expression2.toString() + " is not integer";
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						return null;
						}
					} 
				else 
					{
					// 5
					String string = "Normalizing error for " + remove.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				// 6
				String string = "Normalizing error for " + remove.toString();
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
