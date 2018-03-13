/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import restrizioniIstanze.expressions.ConcatNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.ListConsNorm;
import specificheAEmilia.Concat;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.ListCons;

import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.typeRising.TypeRising;

/**
 * @author Mirko
 *
 */
class NormalizeConcat {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeConcat(int depth)
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

	public ExpressionNorm normalizeConcat(Concat concat, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = concat.getList1();
		IdentExpr expression2 = concat.getList2();
		NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
		normalizeExpression.setTm(this.tm);
		ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent, dataType);
		if (!normalizeExpression.isErrorOccurred()) 
			{
			NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
			normalizeExpression2.setTm(this.tm);
			ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent,
					dataType);
			if (!normalizeExpression2.isErrorOccurred()) 
				{
				Expression expression5 = expression3.getNewExpression();
				Expression expression6 = expression4.getNewExpression();
				if ((expression5 instanceof ListCons)
						&& (expression6 instanceof ListCons)) 
					{
					ListCons listCons = (ListCons) expression5;
					ListCons listCons2 = (ListCons) expression6;
					Expression[] expressions = listCons.getListElements();
					Expression[] expressions2 = listCons2.getListElements();
					// expressions e expressions2 devono avere gli stessi tipi di elementi
					if (expressions.length > 0 && expressions2.length > 0)
						{
						Expression expression7 = expressions[0];
						Expression expression8 = expressions2[0];
						TypeRising typeRising = new TypeRising(this.depth + 1); 
						DataTypeEnum dataTypeEnum = typeRising.rising(expression7, "", null, this.tm);
						if (typeRising.isErrorOccurred())
							{
							String string = "Normalizing error for " + concat.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = typeRising.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						TypeRising typeRising2 = new TypeRising(this.depth + 1);
						DataTypeEnum dataTypeEnum2 = typeRising2.rising(expression8, "", null, this.tm);
						if (typeRising2.isErrorOccurred())
							{
							String string = "Normalizing error for " + concat.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = typeRising2.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						if (!dataTypeEnum.equals(dataTypeEnum2))
							{
							String string = "Normalizing error for " + concat.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							String string2 = expression7 + " and " + expression8 + " have not same type";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						}
					CopyOnWriteArrayList<Expression> copyOnWriteArrayList = new CopyOnWriteArrayList<Expression>(
							expressions);
					CopyOnWriteArrayList<Expression> copyOnWriteArrayList2 = new CopyOnWriteArrayList<Expression>(
							expressions2);
					copyOnWriteArrayList.addAll(copyOnWriteArrayList2);
					Expression[] expressions3 = copyOnWriteArrayList
							.toArray(new Expression[0]);
					ListCons listCons3 = new ListCons(expressions3);
					ListConsNorm listConsNorm = new ListConsNorm();
					listConsNorm.setOldExpression(concat);
					listConsNorm.setNewExpression(listCons3);
					return listConsNorm;
					} 
				else 
					{
					Concat concat2 = new Concat(expression.copy(), expression2.copy());
					ConcatNorm concatNorm = new ConcatNorm();
					concatNorm.setOldExpression(concat);
					concatNorm.setNewExpression(concat2);
					return concatNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + concat.toString();
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
			String string = "Normalizing error for " + concat.toString();
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

    public ExpressionNorm normalizeConcat(Concat concat, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			IdentExpr expression = concat.getList1();
			IdentExpr expression2 = concat.getList2();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent,
						dataType, tma);
				if (!normalizeExpression2.isErrorOccurred()) 
					{
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					if ((expression5 instanceof ListCons)
							&& (expression6 instanceof ListCons)) 
						{
						ListCons listCons = (ListCons) expression5;
						ListCons listCons2 = (ListCons) expression6;
						Expression[] expressions = listCons.getListElements();
						Expression[] expressions2 = listCons2.getListElements();
						// expressions e expressions2 devono avere gli stessi tipi di elementi
						if (expressions.length > 0 && expressions2.length > 0)
							{
							Expression expression7 = expressions[0];
							Expression expression8 = expressions2[0];
							TypeRising typeRising = new TypeRising(this.depth + 1); 
							DataTypeEnum dataTypeEnum = typeRising.rising(expression7, "", null, tma);
							if (typeRising.isErrorOccurred())
								{
								String string = "Normalizing error for " + concat.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = typeRising.getErrorMessage();
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								}
							TypeRising typeRising2 = new TypeRising(this.depth + 1);
							DataTypeEnum dataTypeEnum2 = typeRising2.rising(expression8, "", null, tma);
							if (typeRising2.isErrorOccurred())
								{
								String string = "Normalizing error for " + concat.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = typeRising2.getErrorMessage();
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								}
							if (!dataTypeEnum.equals(dataTypeEnum2))
								{
								String string = "Normalizing error for " + concat.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								String string2 = expression7 + " and " + expression8 + " have not same type";
								ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
								errorMessage.setErrorMessage(string2);
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								}
							}
						CopyOnWriteArrayList<Expression> copyOnWriteArrayList = new CopyOnWriteArrayList<Expression>(
								expressions);
						CopyOnWriteArrayList<Expression> copyOnWriteArrayList2 = new CopyOnWriteArrayList<Expression>(
								expressions2);
						copyOnWriteArrayList.addAll(copyOnWriteArrayList2);
						ListCons listCons3 = new ListCons(copyOnWriteArrayList
								.toArray(new Expression[0]));
						ListConsNorm listConsNorm = new ListConsNorm();
						listConsNorm.setOldExpression(concat);
						listConsNorm.setNewExpression(listCons3);
						return listConsNorm;
						} 
					else 
						{
						Concat concat2 = new Concat(expression.copy(), expression2.copy());
						ConcatNorm concatNorm = new ConcatNorm();
						concatNorm.setOldExpression(concat);
						concatNorm.setNewExpression(concat2);
						return concatNorm;
						}
					} 
				else 
					{
					String string = "Normalizing error for " + concat.toString();
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
				String string = "Normalizing error for " + concat.toString();
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
