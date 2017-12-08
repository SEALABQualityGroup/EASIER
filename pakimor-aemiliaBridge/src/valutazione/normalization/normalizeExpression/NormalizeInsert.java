/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.InsertNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Insert;
import specificheAEmilia.ListCons;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeInsert {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizeInsert(int depth)
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

	public ExpressionNorm normalizeInsert(Insert insert, String parent, DataType dataType)
	throws NormalizeException
	{
	try {
		Expression expression = insert.getItem();
		IdentExpr expression2 = insert.getList();
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
				if (expression6 instanceof ListCons) 
					{
					ListCons listCons = (ListCons) expression6;
					Expression[] expressions = listCons.getListElements();
					CopyOnWriteArrayList<Expression> copyOnWriteArrayList = new CopyOnWriteArrayList<Expression>(expressions);
					copyOnWriteArrayList.add(expression5);
					Expression[] expressions2 = copyOnWriteArrayList.toArray(new Expression[0]);
					listCons.setListElements(expressions2);
					MetodiVari metodiVari = new MetodiVari();
					ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(insert, listCons);
					return expressionNorm;
					} 
				else 
					{
					Insert insert2 = new Insert(expression5, expression2.copy());
					InsertNorm insertNorm = new InsertNorm();
					insertNorm.setOldExpression(insert);
					insertNorm.setNewExpression(insert2);
					return insertNorm;
					}
				} 
			else 
				{
				String string = "Normalizing error for " + insert.toString();
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
			String string = "Normalizing error for " + insert.toString();
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

    public ExpressionNorm normalizeInsert(Insert insert, String parent, DataType dataType, 
    		TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			Expression expression = insert.getItem();
			IdentExpr expression2 = insert.getList();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent, dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent, dataType, tma);
				if (!normalizeExpression2.isErrorOccurred()) 
					{
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					if (expression6 instanceof ListCons) 
						{
						ListCons listCons = (ListCons) expression6;
						Expression[] expressions = listCons.getListElements();
						CopyOnWriteArrayList<Expression> copyOnWriteArrayList = new CopyOnWriteArrayList<Expression>(expressions);
						copyOnWriteArrayList.add(expression5);
						Expression[] expressions2 = copyOnWriteArrayList.toArray(new Expression[0]);
						listCons.setListElements(expressions2);
						MetodiVari metodiVari = new MetodiVari();
						ExpressionNorm expressionNorm = metodiVari.getExpressionNorm(insert, listCons);
						return expressionNorm;
						} 
					else 
						{
						Insert insert2 = new Insert(expression5, expression2.copy());
						InsertNorm insertNorm = new InsertNorm();
						insertNorm.setOldExpression(insert);
						insertNorm.setNewExpression(insert2);
						return insertNorm;
						}
					} 
				else 
					{
					String string = "Normalizing error for " + insert.toString();
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
				String string = "Normalizing error for " + insert.toString();
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
