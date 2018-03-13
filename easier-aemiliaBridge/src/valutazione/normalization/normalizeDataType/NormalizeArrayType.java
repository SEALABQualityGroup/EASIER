/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.dataTypes.ArrayTypeNorm;
import restrizioniIstanze.dataTypes.DataTypeNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.IntegerNorm;
import specificheAEmilia.ArrayType;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.NormalType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;

/**
 * @author Mirko
 *
 */
class NormalizeArrayType {

	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;
	private boolean errorOccurred = false;

	public NormalizeArrayType(int depth) {
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	public TreeMap<String, ValueIdentExpr> getTm() {
		return tm;
	}

	public void setTm(TreeMap<String, ValueIdentExpr> tm) {
		this.tm = tm;
	}

	/**
	 * Viene restituita la normalizzazione di arrayType, effettuando typeChecking
	 * durante la valutazione.
	 * 
	 * @param arrayType
	 * @return
	 * @throws NormalizeException
	 */
	public ArrayTypeNorm normalize(ArrayType arrayType) throws NormalizeException {
		try {
			ArrayType arrayType2 = arrayType.copy();
			// normalizzo la lunghezza
			Expression expression = arrayType.getLength();
			// in normalizeInteger si effettua typChecking
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			normalizeExpressionStrict.setTm(this.tm);
			ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, "", null);
			if (normalizeExpressionStrict.isErrorOccurred()) {
				String string = "Normalizing error for " + arrayType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			if (expression2 instanceof IntegerNorm) {
				IntegerNorm integer = (IntegerNorm) expression2;
				specificheAEmilia.Integer integer2 = (Integer) integer.getNewExpression();
				int i = integer2.getValore();
				if (i < 1) {
					String string = "Normalizing error for " + arrayType.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = "array length expression " + "must be not less than one";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
				}
			} else {
				String string = "Normalizing error for " + arrayType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = "array length expression " + "must be integer valued";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			arrayType2.setLength(expression2.getNewExpression());
			// normalizzo il tipo
			DataType dataType2 = arrayType.getType();
			NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
			normalizeDataType.setTm(this.tm);
			DataTypeNorm dataType3 = normalizeDataType.normalize(dataType2);
			if (normalizeDataType.isErrorOccurred()) {
				String string = "Normalizing error for " + arrayType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			// la valutazione del tipo dato non cambia tipo
			arrayType2.setType((NormalType) dataType3.getNewDataType());
			ArrayTypeNorm arrayTypeNorm = new ArrayTypeNorm();
			arrayTypeNorm.setOldArrayType(arrayType);
			arrayTypeNorm.setNewArrayType(arrayType2);
			return arrayTypeNorm;
		} catch (Exception exception) {
			throw new NormalizeException(exception);
		}
	}

	/**
	 * Viene restituita la normalizzazione di arrayType, effettuando typeChecking
	 * durante la valutazione. Utilizza uno scope tm per la valutazione, passandolo
	 * come parametro.
	 * 
	 * @param arrayType
	 * @param tm
	 * @return
	 * @throws NormalizeException
	 */
	public ArrayTypeNorm normalize(ArrayType arrayType, TreeMap<String, ValueIdentExpr> tm) throws NormalizeException {
		/* MODELED */
		try {
			ArrayType arrayType2 = arrayType.copy();
			// normalizzo la lunghezza
			Expression expression = arrayType.getLength();
			// in normalizeInteger si effettua typChecking
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, "", null, tm);
			if (normalizeExpressionStrict.isErrorOccurred()) {
				// 1
				String string = "Normalizing error for " + arrayType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			if (expression2 instanceof IntegerNorm) {
				IntegerNorm integer = (IntegerNorm) expression2;
				int i = ((Integer) integer.getNewExpression()).getValore();
				if (i < 1) {
					// 2
					String string = "Normalizing error for " + arrayType.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					String string2 = "array length expression " + "must be not less than one";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
				}
			} else {
				// 3
				String string = "Normalizing error for " + arrayType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = "array length expression " + "must be integer valued";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			arrayType2.setLength(expression2.getNewExpression());
			// normalizzo il tipo
			DataType dataType2 = arrayType.getType();
			NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
			DataTypeNorm dataType3 = normalizeDataType.normalize(dataType2, tm);
			if (normalizeDataType.isErrorOccurred()) {
				// 4
				String string = "Normalizing error for " + arrayType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			// la valutazione del tipo dato non cambia tipo
			arrayType2.setType((NormalType) dataType3.getNewDataType());
			ArrayTypeNorm arrayTypeNorm = new ArrayTypeNorm();
			arrayTypeNorm.setOldArrayType(arrayType);
			arrayTypeNorm.setNewArrayType(arrayType2);
			return arrayTypeNorm;
		} catch (Exception exception) {
			throw new NormalizeException(exception);
		}
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

}
