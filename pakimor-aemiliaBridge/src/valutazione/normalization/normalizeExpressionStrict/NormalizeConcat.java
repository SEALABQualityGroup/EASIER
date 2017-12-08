/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

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
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeConcat(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ListConsNorm normalizeConcat(Concat concat, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		IdentExpr expression = concat.getList1();
		IdentExpr expression2 = concat.getList2();
		NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
		normalizeExpressionStrict.setTm(this.tm);
		ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent, dataType);
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
							String string2 = "Normalizaton error for " + concat.toString() +": " + expression7.toString() + " and " + expression7.toString()
									+ " have not same type";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
							List<ErrorMessage> list = this.errorMessage.getCauses();
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
					if (!(expression5 instanceof ListCons))
						{
						String string = "Normalizing error for " + concat.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = "Normalized expression " + expression5.toString() + " of " + expression.toString() + " is not list";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					if (!(expression6 instanceof ListCons))
						{
						String string = "Normalizing error for " + concat.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = "Normalized expression " + expression6.toString() + " of " + expression2.toString() + " is not list";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
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
				String string = "Normalizing error for " + concat.toString();
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
			String string = "Normalizing error for " + concat.toString();
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

    public ListConsNorm normalizeConcat(Concat concat, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			IdentExpr expression = concat.getList1();
			IdentExpr expression2 = concat.getList2();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) {
				NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, parent,
						dataType, tma);
				if (!normalizeExpressionStrict2.isErrorOccurred()) {
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					if ((expression5 instanceof ListCons)
							&& (expression6 instanceof ListCons)) {
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
								// 1
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
								// 2
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
								// 3
								String string = "Normalizing error for " + concat.toString();
								this.errorMessage.setErrorMessage(string);
								String string2 = "Normalizaton error for " + concat.toString() +": " + expression7.toString() + " and " + expression7.toString()
										+ " have not same type";
								ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
								errorMessage.setErrorMessage(string2);
								List<ErrorMessage> list = this.errorMessage.getCauses();
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
					} else {
						if (!(expression5 instanceof ListCons))
							{
							// 4
							String string = "Normalizing error for " + concat.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							String string2 = "Normalized expression " + expression5.toString() + " of " + expression.toString() + " is not list";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						if (!(expression6 instanceof ListCons))
							{
							// 5
							String string = "Normalizing error for " + concat.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							String string2 = "Normalized expression " + expression6.toString() + " of " + expression2.toString() + " is not list";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						return null;
						}
				} else {
					// 6
					String string = "Normalizing error for " + concat.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
				}
			} else {
				// 7
				String string = "Normalizing error for " + concat.toString();
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
