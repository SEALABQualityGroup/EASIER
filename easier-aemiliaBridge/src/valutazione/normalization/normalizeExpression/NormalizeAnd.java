/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.AndNorm;
import restrizioniIstanze.expressions.BooleanNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.And;
import specificheAEmilia.Boolean;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeAnd {

	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;
	private boolean errorOccurred;

	public NormalizeAnd(int depth) {
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
	 * L'operatore ande'soggetto a short circuitation.
	 * 
	 * @param and
	 * @param parent
	 *            - il nome del parent a cui appartiene. Viene utilizzato per la
	 *            normalizzazione dell'espressione Get.
	 * @param dataType
	 *            - tipo dell'espressione da normalizzare. Viene utilizzato per la
	 *            normalizzazione dell'espressione RecordCons
	 * @return
	 * @throws NormalizeException
	 */
	public ExpressionNorm normalizeAnd(And and, String parent, DataType dataType) throws NormalizeException {
		try {
			Expression expression = and.getExpr1();
			Expression expression2 = and.getExpr2();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			normalizeExpression.setTm(this.tm);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent, dataType);
			if (!normalizeExpression.isErrorOccurred()) {
				Expression expression5 = expression3.getNewExpression();
				if (expression5 instanceof Boolean) {
					Boolean boolean1 = (Boolean) expression5;
					boolean b = boolean1.getValore();
					if (!b) {
						Boolean boolean2 = new Boolean(false);
						BooleanNorm booleanNorm = new BooleanNorm();
						booleanNorm.setOldExpression(and);
						booleanNorm.setNewExpression(boolean2);
						return booleanNorm;
					} else {
						NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
						normalizeExpression2.setTm(this.tm);
						ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent, dataType);
						if (!normalizeExpression2.isErrorOccurred()) {
							Expression expression6 = expression4.getNewExpression();
							if (expression6 instanceof Boolean) {
								Boolean boolean2 = (Boolean) expression6;
								BooleanNorm booleanNorm = new BooleanNorm();
								booleanNorm.setOldExpression(and);
								booleanNorm.setNewExpression(boolean2);
								return booleanNorm;
							} else {
								And and2 = new And(boolean1, expression6);
								AndNorm andNorm = new AndNorm();
								andNorm.setOldExpression(and);
								andNorm.setNewExpression(and2);
								return andNorm;
							}
						} else {
							String string = "Normalizing error for " + and.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;

						}
					}
				} else {
					NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
					normalizeExpression2.setTm(this.tm);
					ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent, dataType);
					if (!normalizeExpression2.isErrorOccurred()) {
						Expression expression6 = expression4.getNewExpression();
						And and2 = new And(expression5, expression6);
						AndNorm andNorm = new AndNorm();
						andNorm.setOldExpression(and);
						andNorm.setNewExpression(and2);
						return andNorm;
					} else {
						String string = "Normalizing error for " + and.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
					}
				}
			} else {
				String string = "Normalizing error for " + and.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} catch (Exception e) {
			throw new NormalizeException(e);
		}
	}

	public ExpressionNorm normalizeAnd(And and, String parent, DataType dataType, TreeMap<String, ValueIdentExpr> tma)
			throws NormalizeException {
		try {
			Expression expression = and.getExpr1();
			Expression expression2 = and.getExpr2();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent, dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) {
				Expression expression4 = expression3.getNewExpression();
				if (expression4 instanceof Boolean) {
					Boolean boolean1 = (Boolean) expression4;
					boolean b = boolean1.getValore();
					if (!b) {
						Boolean boolean2 = new Boolean(false);
						BooleanNorm booleanNorm = new BooleanNorm();
						booleanNorm.setOldExpression(and);
						booleanNorm.setNewExpression(boolean2);
						return booleanNorm;
					} else {
						NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expressionNorm = normalizeExpression2.normalize(expression2, parent, dataType,
								tma);
						if (!normalizeExpression2.isErrorOccurred()) {
							Expression expression5 = expressionNorm.getNewExpression();
							if (expression5 instanceof Boolean) {
								Boolean boolean2 = (Boolean) expression5;
								BooleanNorm booleanNorm = new BooleanNorm();
								booleanNorm.setOldExpression(and);
								booleanNorm.setNewExpression(boolean2);
								return booleanNorm;
							} else {
								And and2 = new And(boolean1, expression5);
								AndNorm andNorm = new AndNorm();
								andNorm.setOldExpression(and);
								andNorm.setNewExpression(and2);
								return andNorm;
							}
						} else {
							String string = "Normalizing error for " + and.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
						}
					}
				} else {
					NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
					ExpressionNorm expressionNorm = normalizeExpression2.normalize(expression2, parent, dataType, tma);
					if (!normalizeExpression2.isErrorOccurred()) {
						Expression expression6 = expressionNorm.getNewExpression();
						And and2 = new And(expression4, expression6);
						AndNorm andNorm = new AndNorm();
						andNorm.setOldExpression(and);
						andNorm.setNewExpression(and2);
						return andNorm;
					} else {
						String string = "Normalizing error for " + and.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpression2.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
					}
				}
			} else {
				String string = "Normalizing error for " + and.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} catch (Exception e) {
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
