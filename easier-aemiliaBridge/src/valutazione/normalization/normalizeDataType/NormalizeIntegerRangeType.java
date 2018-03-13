/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.dataTypes.IntegerRangeTypeNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.Expression;
import specificheAEmilia.IntegerRangeType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;
import valutazione.typeChecking.boundedIntegerChecking.CheckRangeValues1;
import valutazione.typeChecking.boundedIntegerChecking.CheckRangeValues2;

/**
 * @author Mirko
 *
 */
class NormalizeIntegerRangeType {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred = false;

    public NormalizeIntegerRangeType(int depth) 
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

    /**
	 * Viene restituita la normalizzazione di dataType, effettuando
	 * typeChecking durante la valutazione.
	 * 
	 * @param dataType
	 * @return
	 * @throws NormalizeException
	 */
	public IntegerRangeTypeNorm normalize(IntegerRangeType dataType)
		throws NormalizeException
		{
		try {
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			normalizeExpressionStrict.setTm(this.tm);
			IntegerRangeType integerRangeType = dataType.copy();
			Expression expression = integerRangeType.getBeginningInt();
			Expression expression2 = integerRangeType.getEndingInt();
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, "", null);
			if (normalizeExpressionStrict.isErrorOccurred())
				{
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
			normalizeExpressionStrict2.setTm(this.tm);
			ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, "", null);
			if (normalizeExpressionStrict2.isErrorOccurred())
				{
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			integerRangeType.setBeginningInt(expression3.getNewExpression());
			integerRangeType.setEndingInt(expression4.getNewExpression());
			CheckRangeValues1 checkRangeValues1 = new CheckRangeValues1(integerRangeType,this.depth + 1);
			if (!checkRangeValues1.checkRangeValues1())
				{
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRangeValues1.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			CheckRangeValues2 checkRangeValues2 = new CheckRangeValues2(integerRangeType,this.depth + 1);
			if (!checkRangeValues2.checkRangeValues2())
				{
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRangeValues2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			IntegerRangeTypeNorm integerRangeTypeNorm = new IntegerRangeTypeNorm();
			integerRangeTypeNorm.setOldIntegerRangeType(dataType);
			integerRangeTypeNorm.setNewIntegerRangeType(integerRangeType);
			return integerRangeTypeNorm;
			} 
		catch (Exception e) 
			{
			throw new NormalizeException(e);
			}
		}
	
    /**
	 * Viene restituita la normalizzazione di dataType, effettuando
	 * typeChecking durante la valutazione. Utilizza uno scope tm per la valutazione,
	 * passandolo come parametro.
	 * 
	 * @param dataType
	 * @param tm
	 * @return
	 * @throws NormalizeException
	 */
	public IntegerRangeTypeNorm normalize(IntegerRangeType dataType, TreeMap<String, ValueIdentExpr> tm)
		throws NormalizeException
		{
		try {
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			normalizeExpressionStrict.setTm(this.tm);
			IntegerRangeType integerRangeType = dataType.copy();
			Expression expression = integerRangeType.getBeginningInt();
			Expression expression2 = integerRangeType.getEndingInt();
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, "", null,tm);
			if (normalizeExpressionStrict.isErrorOccurred())
				{
				// 1
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, "", null,tm);
			if (normalizeExpressionStrict.isErrorOccurred())
				{
				// 2
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			integerRangeType.setBeginningInt(expression3.getNewExpression());
			integerRangeType.setEndingInt(expression4.getNewExpression());
			CheckRangeValues1 checkRangeValues1 = new CheckRangeValues1(integerRangeType,this.depth + 1);
			if (!checkRangeValues1.checkRangeValues1())
				{
				// 3
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRangeValues1.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			CheckRangeValues2 checkRangeValues2 = new CheckRangeValues2(integerRangeType,this.depth + 1); 
			if (!checkRangeValues2.checkRangeValues2())
				{
				// 4
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRangeValues2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			IntegerRangeTypeNorm integerRangeTypeNorm = new IntegerRangeTypeNorm();
			integerRangeTypeNorm.setOldIntegerRangeType(dataType);
			integerRangeTypeNorm.setNewIntegerRangeType(integerRangeType);
			return integerRangeTypeNorm;
			}
		catch (Exception exception)
			{
			throw new NormalizeException(exception);
			}
		}

	public boolean isErrorOccurred() 
		{
		return this.errorOccurred;
		}

	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}

}
