/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.BooleanNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.IntegerNorm;
import restrizioniIstanze.expressions.ListConsNorm;
import restrizioniIstanze.expressions.RealNorm;
import restrizioniIstanze.expressions.RecordConsNorm;
import specificheAEmilia.And;
import specificheAEmilia.ArrayCons;
import specificheAEmilia.Boolean;
import specificheAEmilia.Concat;
import specificheAEmilia.DataType;
import specificheAEmilia.Different;
import specificheAEmilia.Divisione;
import specificheAEmilia.Equal;
import specificheAEmilia.Expression;
import specificheAEmilia.First;
import specificheAEmilia.Get;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Insert;
import specificheAEmilia.Integer;
import specificheAEmilia.Length;
import specificheAEmilia.ListCons;
import specificheAEmilia.Maggiore;
import specificheAEmilia.MaggioreUguale;
import specificheAEmilia.Minore;
import specificheAEmilia.MinoreUguale;
import specificheAEmilia.Moltiplicazione;
import specificheAEmilia.Negazione;
import specificheAEmilia.Or;
import specificheAEmilia.Put;
import specificheAEmilia.Read;
import specificheAEmilia.Real;
import specificheAEmilia.RecordCons;
import specificheAEmilia.Remove;
import specificheAEmilia.Somma;
import specificheAEmilia.Sottrazione;
import specificheAEmilia.Tail;
import specificheAEmilia.Write;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * Contiene dei metodi per restituire l'espressione piu' normalizzata del suo
 * argomento valutata in un determinato scope. Lo scope puo' essere aggiornato.
 * 
 * @author Acer5736Z_1
 *
 */
public class NormalizeExpression {

	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;
	private boolean errorOccurred;

	public NormalizeExpression(int depth) {
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
	 * Normalizza una generica espressione in un oggetto Integer, Real, Boolean,
	 * ListCons, ArrayCons, RecordCons. Se expression non puo' essere trasformato in
	 * un oggetto di tipo normale si restituisce la sua forma normale. dataTypee'il
	 * tipo di dato dell'espressione identificata da parent se parente'una stringa
	 * non vuota. Altrimenti, dataTypee'il tipo di dato di expression.
	 * 
	 * @param expression
	 * @param parent
	 *            - il nome del parent a cui appartiene. Viene utilizzato per la
	 *            normalizzazione dell'espressione Get.
	 * @param dataType
	 *            - tipo dell'espressione da normalizzare. Viene utilizzato per la
	 *            normalizzazione dell'espressione RecordCons
	 * @return
	 * @throws NormalizeException
	 */
	public ExpressionNorm normalize(Expression expression, String parent, DataType dataType) throws NormalizeException {
		if (expression instanceof And) {
			And and = (And) expression;
			NormalizeAnd normalizeAnd = new NormalizeAnd(this.depth + 1);
			normalizeAnd.setTm(this.tm);
			ExpressionNorm expression2 = normalizeAnd.normalizeAnd(and, parent, dataType);
			if (!normalizeAnd.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeAnd.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof ArrayCons) {
			ArrayCons arrayCons = (ArrayCons) expression;
			NormalizeArrayCons normalizeArrayCons = new NormalizeArrayCons(this.depth + 1);
			normalizeArrayCons.setTm(this.tm);
			ExpressionNorm expression2 = normalizeArrayCons.normalizeArrayCons(arrayCons, parent, dataType);
			if (!normalizeArrayCons.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeArrayCons.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Boolean) {
			Boolean boolean1 = (Boolean) expression.copy();
			BooleanNorm booleanNorm = new BooleanNorm();
			booleanNorm.setOldExpression((Boolean) expression);
			booleanNorm.setNewExpression(boolean1);
			return booleanNorm;
		} else if (expression instanceof Concat) {
			Concat concat = (Concat) expression;
			NormalizeConcat normalizeConcat = new NormalizeConcat(this.depth + 1);
			normalizeConcat.setTm(this.tm);
			ExpressionNorm expression2 = normalizeConcat.normalizeConcat(concat, parent, dataType);
			if (!normalizeConcat.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeConcat.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Different) {
			Different different = (Different) expression;
			NormalizeDifferent normalizeDifferent = new NormalizeDifferent(this.depth + 1);
			normalizeDifferent.setTm(this.tm);
			ExpressionNorm expression2 = normalizeDifferent.normalizeDifferent(different, parent, dataType);
			if (!normalizeDifferent.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeDifferent.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Divisione) {
			Divisione divisione = (Divisione) expression;
			NormalizeDivisione normalizeDivisione = new NormalizeDivisione(this.depth + 1);
			normalizeDivisione.setTm(this.tm);
			ExpressionNorm expression2 = normalizeDivisione.normalizeDivisione(divisione, parent, dataType);
			if (!normalizeDivisione.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeDivisione.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Equal) {
			Equal equal = (Equal) expression;
			NormalizeEqual normalizeEqual = new NormalizeEqual(this.depth + 1);
			normalizeEqual.setTm(this.tm);
			ExpressionNorm expression2 = normalizeEqual.normalizeEqual(equal, parent, dataType);
			if (!normalizeEqual.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeEqual.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof First) {
			First first = (First) expression;
			NormalizeFirst normalizeFirst = new NormalizeFirst(this.depth + 1);
			normalizeFirst.setTm(this.tm);
			ExpressionNorm expression2 = normalizeFirst.normalizeFirst(first, parent, dataType);
			if (!normalizeFirst.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeFirst.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Get) {
			Get get = (Get) expression;
			NormalizeGet normalizeGet = new NormalizeGet(this.depth + 1);
			normalizeGet.setTm(this.tm);
			ExpressionNorm expression2 = normalizeGet.normalizeGet(get, parent, dataType);
			if (!normalizeGet.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeGet.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof IdentExpr) {
			IdentExpr identExpr = (IdentExpr) expression;
			NormalizeIdentExpr normalizeIdentExpr = new NormalizeIdentExpr(this.depth + 1);
			normalizeIdentExpr.setTm(this.tm);
			ExpressionNorm expression2 = normalizeIdentExpr.normalizeIdentExpr(identExpr, parent, dataType);
			if (!normalizeIdentExpr.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeIdentExpr.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Insert) {
			Insert insert = (Insert) expression;
			NormalizeInsert normalizeInsert = new NormalizeInsert(this.depth + 1);
			normalizeInsert.setTm(this.tm);
			ExpressionNorm expression2 = normalizeInsert.normalizeInsert(insert, parent, dataType);
			if (!normalizeInsert.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeInsert.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Integer) {
			Integer integer = (Integer) expression.copy();
			IntegerNorm integerNorm = new IntegerNorm();
			integerNorm.setOldExpression((Integer) expression);
			integerNorm.setNewExpression(integer);
			return integerNorm;
		} else if (expression instanceof Length) {
			Length length = (Length) expression;
			NormalizeLength normalizeLength = new NormalizeLength(this.depth + 1);
			normalizeLength.setTm(this.tm);
			ExpressionNorm expression2 = normalizeLength.normalizeLength(length, parent, dataType);
			if (!normalizeLength.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeLength.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof ListCons) {
			ListCons listCons = (ListCons) expression;
			NormalizeListCons normalizeListCons = new NormalizeListCons(this.depth + 1);
			normalizeListCons.setTm(this.tm);
			ListConsNorm listConsNorm = normalizeListCons.normalizeListCons(listCons, parent, dataType);
			if (!normalizeListCons.isErrorOccurred())
				return listConsNorm;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeListCons.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Maggiore) {
			Maggiore maggiore = (Maggiore) expression;
			NormalizeMaggiore normalizeMaggiore = new NormalizeMaggiore(this.depth + 1);
			normalizeMaggiore.setTm(this.tm);
			ExpressionNorm expression2 = normalizeMaggiore.normalizeMaggiore(maggiore, parent, dataType);
			if (!normalizeMaggiore.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMaggiore.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof MaggioreUguale) {
			MaggioreUguale maggioreUguale = (MaggioreUguale) expression;
			NormalizeMaggioreUguale normalizeMaggioreUguale = new NormalizeMaggioreUguale(this.depth + 1);
			normalizeMaggioreUguale.setTm(this.tm);
			ExpressionNorm expression2 = normalizeMaggioreUguale.normalizeMaggioreUguale(maggioreUguale, parent,
					dataType);
			if (!normalizeMaggioreUguale.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMaggioreUguale.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Minore) {
			Minore minore = (Minore) expression;
			NormalizeMinore normalizeMinore = new NormalizeMinore(this.depth + 1);
			normalizeMinore.setTm(this.tm);
			ExpressionNorm expression2 = normalizeMinore.normalizeMinore(minore, parent, dataType);
			if (!normalizeMinore.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMinore.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof MinoreUguale) {
			MinoreUguale minoreUguale = (MinoreUguale) expression;
			NormalizeMinoreUguale normalizeMinoreUguale = new NormalizeMinoreUguale(this.depth + 1);
			normalizeMinoreUguale.setTm(this.tm);
			ExpressionNorm expression2 = normalizeMinoreUguale.normalizeMinoreUguale(minoreUguale, parent, dataType);
			if (!normalizeMinoreUguale.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMinoreUguale.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Moltiplicazione) {
			Moltiplicazione moltiplicazione = (Moltiplicazione) expression;
			NormalizeMoltiplicazione normalizeMoltiplicazione = new NormalizeMoltiplicazione(this.depth + 1);
			normalizeMoltiplicazione.setTm(this.tm);
			ExpressionNorm expression2 = normalizeMoltiplicazione.normalizeMoltiplicazione(moltiplicazione, parent,
					dataType);
			if (!normalizeMoltiplicazione.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMoltiplicazione.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Negazione) {
			Negazione negazione = (Negazione) expression;
			NormalizeNegazione normalizeNegazione = new NormalizeNegazione(this.depth + 1);
			normalizeNegazione.setTm(this.tm);
			ExpressionNorm expression2 = normalizeNegazione.normalizeNegazione(negazione, parent, dataType);
			if (!normalizeNegazione.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeNegazione.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Or) {
			Or or = (Or) expression;
			NormalizeOr normalizeOr = new NormalizeOr(this.depth + 1);
			normalizeOr.setTm(this.tm);
			ExpressionNorm expression2 = normalizeOr.normalizeOr(or, parent, dataType);
			if (!normalizeOr.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeOr.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Put) {
			Put put = (Put) expression;
			NormalizePut normalizePut = new NormalizePut(this.depth + 1);
			normalizePut.setTm(this.tm);
			ExpressionNorm expression2 = normalizePut.normalizePut(put, parent, dataType);
			if (!normalizePut.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizePut.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Read) {
			Read read = (Read) expression;
			NormalizeRead normalizeRead = new NormalizeRead(this.depth + 1);
			normalizeRead.setTm(this.tm);
			ExpressionNorm expression2 = normalizeRead.normalizeRead(read, parent, dataType);
			if (!normalizeRead.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRead.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Real) {
			Real real = (Real) expression.copy();
			Double d = real.getValore();
			if (Math.rint(d) == d) {
				IntegerNorm integerNorm = new IntegerNorm();
				integerNorm.setOldExpression(expression);
				Integer integer = new Integer(d.intValue());
				integerNorm.setNewExpression(integer);
				return integerNorm;
			} else {
				RealNorm realNorm = new RealNorm();
				realNorm.setOldExpression(expression);
				realNorm.setNewExpression(real);
				return realNorm;
			}
		} else if (expression instanceof RecordCons) {
			RecordCons recordCons = (RecordCons) expression;
			NormalizeRecordCons normalizeRecordCons = new NormalizeRecordCons(this.depth + 1);
			normalizeRecordCons.setTm(this.tm);
			RecordConsNorm expression2 = normalizeRecordCons.normalizeRecordCons(recordCons, parent, dataType);
			if (!normalizeRecordCons.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRecordCons.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Remove) {
			Remove remove = (Remove) expression;
			NormalizeRemove normalizeRemove = new NormalizeRemove(this.depth + 1);
			normalizeRemove.setTm(this.tm);
			ExpressionNorm expression2 = normalizeRemove.normalizeRemove(remove, parent, dataType);
			if (!normalizeRemove.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRemove.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Somma) {
			Somma somma = (Somma) expression;
			NormalizeSomma normalizeSomma = new NormalizeSomma(this.depth + 1);
			normalizeSomma.setTm(this.tm);
			ExpressionNorm expression2 = normalizeSomma.normalizeSomma(somma, parent, dataType);
			if (!normalizeSomma.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeSomma.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Sottrazione) {
			Sottrazione sottrazione = (Sottrazione) expression;
			NormalizeSottrazione normalizeSottrazione = new NormalizeSottrazione(this.depth + 1);
			normalizeSottrazione.setTm(this.tm);
			ExpressionNorm expression2 = normalizeSottrazione.normalizeSottrazione(sottrazione, parent, dataType);
			if (!normalizeSottrazione.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeSottrazione.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Tail) {
			Tail tail = (Tail) expression;
			NormalizeTail normalizeTail = new NormalizeTail(this.depth + 1);
			normalizeTail.setTm(this.tm);
			ExpressionNorm expression2 = normalizeTail.normalizeTail(tail, parent, dataType);
			if (!normalizeTail.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeTail.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Write) {
			Write write = (Write) expression;
			NormalizeWrite normalizeWrite = new NormalizeWrite(this.depth + 1);
			normalizeWrite.setTm(this.tm);
			ExpressionNorm expression2 = normalizeWrite.normalizeWrite(write, parent, dataType);
			if (!normalizeWrite.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeWrite.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else
			return null;
	}

	/**
	 * Normalizza una generica espressione in un oggetto Integer, Real, Boolean,
	 * ListCons, ArrayCons, RecordCons. Se expression non puo' essere trasformato in
	 * un oggetto di tipo normale si restituisce la sua forma normale. dataTypee'il
	 * tipo di dato dell'espressione identificata da parent se parente'una stringa
	 * non vuota. Altrimenti, dataTypee'il tipo di dato di expression.
	 * 
	 * @param expression
	 * @param parent
	 *            - il nome del parent a cui appartiene. Viene utilizzato per la
	 *            normalizzazione dell'espressione Get.
	 * @param dataType
	 *            - tipo dell'espressione da normalizzare. Viene utilizzato per la
	 *            normalizzazione dell'espressione RecordCons
	 * @param tma
	 *            - scope per la normalizzazione dell'espressione.
	 * 
	 * @return
	 * @throws NormalizeException
	 */
	public ExpressionNorm normalize(Expression expression, String parent, DataType dataType,
			TreeMap<String, ValueIdentExpr> tma) throws NormalizeException {
		if (expression instanceof And) {
			And and = (And) expression;
			NormalizeAnd normalizeAnd = new NormalizeAnd(this.depth + 1);
			ExpressionNorm expression2 = normalizeAnd.normalizeAnd(and, parent, dataType, tma);
			if (!normalizeAnd.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeAnd.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof ArrayCons) {
			ArrayCons arrayCons = (ArrayCons) expression;
			NormalizeArrayCons normalizeArrayCons = new NormalizeArrayCons(this.depth + 1);
			ExpressionNorm expression2 = normalizeArrayCons.normalizeArrayCons(arrayCons, parent, dataType, tma);
			if (!normalizeArrayCons.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeArrayCons.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Boolean) {
			Boolean boolean1 = (Boolean) expression.copy();
			BooleanNorm booleanNorm = new BooleanNorm();
			booleanNorm.setOldExpression((Boolean) expression);
			booleanNorm.setNewExpression(boolean1);
			return booleanNorm;
		} else if (expression instanceof Concat) {
			Concat concat = (Concat) expression;
			NormalizeConcat normalizeConcat = new NormalizeConcat(this.depth + 1);
			ExpressionNorm expression2 = normalizeConcat.normalizeConcat(concat, parent, dataType, tma);
			if (!normalizeConcat.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeConcat.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Different) {
			Different different = (Different) expression;
			NormalizeDifferent normalizeDifferent = new NormalizeDifferent(this.depth + 1);
			ExpressionNorm expression2 = normalizeDifferent.normalizeDifferent(different, parent, dataType, tma);
			if (!normalizeDifferent.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeDifferent.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Divisione) {
			Divisione divisione = (Divisione) expression;
			NormalizeDivisione normalizeDivisione = new NormalizeDivisione(this.depth + 1);
			ExpressionNorm expression2 = normalizeDivisione.normalizeDivisione(divisione, parent, dataType, tma);
			if (!normalizeDivisione.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeDivisione.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Equal) {
			Equal equal = (Equal) expression;
			NormalizeEqual normalizeEqual = new NormalizeEqual(this.depth + 1);
			ExpressionNorm expression2 = normalizeEqual.normalizeEqual(equal, parent, dataType, tma);
			if (!normalizeEqual.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeEqual.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof First) {
			First first = (First) expression;
			NormalizeFirst normalizeFirst = new NormalizeFirst(this.depth + 1);
			ExpressionNorm expression2 = normalizeFirst.normalizeFirst(first, parent, dataType, tma);
			if (!normalizeFirst.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeFirst.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Get) {
			Get get = (Get) expression;
			NormalizeGet normalizeGet = new NormalizeGet(this.depth + 1);
			ExpressionNorm expression2 = normalizeGet.normalizeGet(get, parent, dataType, tma);
			if (!normalizeGet.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeGet.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof IdentExpr) {
			IdentExpr identExpr = (IdentExpr) expression;
			NormalizeIdentExpr normalizeIdentExpr = new NormalizeIdentExpr(this.depth + 1);
			ExpressionNorm expression2 = normalizeIdentExpr.normalizeIdentExpr(identExpr, parent, dataType, tma);
			if (!normalizeIdentExpr.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeIdentExpr.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Insert) {
			Insert insert = (Insert) expression;
			NormalizeInsert normalizeInsert = new NormalizeInsert(this.depth + 1);
			ExpressionNorm expression2 = normalizeInsert.normalizeInsert(insert, parent, dataType, tma);
			if (!normalizeInsert.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeInsert.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Integer) {
			Integer integer = (Integer) expression.copy();
			IntegerNorm integerNorm = new IntegerNorm();
			integerNorm.setOldExpression((Integer) expression);
			integerNorm.setNewExpression(integer);
			return integerNorm;
		} else if (expression instanceof Length) {
			Length length = (Length) expression;
			NormalizeLength normalizeLength = new NormalizeLength(this.depth + 1);
			ExpressionNorm expression2 = normalizeLength.normalizeLength(length, parent, dataType, tma);
			if (!normalizeLength.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeLength.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof ListCons) {
			ListCons listCons = (ListCons) expression;
			NormalizeListCons normalizeListCons = new NormalizeListCons(this.depth + 1);
			ListConsNorm listConsNorm = normalizeListCons.normalizeListCons(listCons, parent, dataType, tma);
			if (!normalizeListCons.isErrorOccurred())
				return listConsNorm;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeListCons.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Maggiore) {
			Maggiore maggiore = (Maggiore) expression;
			NormalizeMaggiore normalizeMaggiore = new NormalizeMaggiore(this.depth + 1);
			ExpressionNorm expression2 = normalizeMaggiore.normalizeMaggiore(maggiore, parent, dataType, tma);
			if (!normalizeMaggiore.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMaggiore.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof MaggioreUguale) {
			MaggioreUguale maggioreUguale = (MaggioreUguale) expression;
			NormalizeMaggioreUguale normalizeMaggioreUguale = new NormalizeMaggioreUguale(this.depth + 1);
			ExpressionNorm expression2 = normalizeMaggioreUguale.normalizeMaggioreUguale(maggioreUguale, parent,
					dataType, tma);
			if (!normalizeMaggioreUguale.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMaggioreUguale.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Minore) {
			Minore minore = (Minore) expression;
			NormalizeMinore normalizeMinore = new NormalizeMinore(this.depth + 1);
			ExpressionNorm expression2 = normalizeMinore.normalizeMinore(minore, parent, dataType, tma);
			if (!normalizeMinore.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMinore.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof MinoreUguale) {
			MinoreUguale minoreUguale = (MinoreUguale) expression;
			NormalizeMinoreUguale normalizeMinoreUguale = new NormalizeMinoreUguale(this.depth + 1);
			ExpressionNorm expression2 = normalizeMinoreUguale.normalizeMinoreUguale(minoreUguale, parent, dataType,
					tma);
			if (!normalizeMinoreUguale.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMinoreUguale.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Moltiplicazione) {
			Moltiplicazione moltiplicazione = (Moltiplicazione) expression;
			NormalizeMoltiplicazione normalizeMoltiplicazione = new NormalizeMoltiplicazione(this.depth + 1);
			ExpressionNorm expression2 = normalizeMoltiplicazione.normalizeMoltiplicazione(moltiplicazione, parent,
					dataType, tma);
			if (!normalizeMoltiplicazione.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMoltiplicazione.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Negazione) {
			Negazione negazione = (Negazione) expression;
			NormalizeNegazione normalizeNegazione = new NormalizeNegazione(this.depth + 1);
			ExpressionNorm expression2 = normalizeNegazione.normalizeNegazione(negazione, parent, dataType, tma);
			if (!normalizeNegazione.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeNegazione.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Or) {
			Or or = (Or) expression;
			NormalizeOr normalizeOr = new NormalizeOr(this.depth + 1);
			ExpressionNorm expression2 = normalizeOr.normalizeOr(or, parent, dataType, tma);
			if (!normalizeOr.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeOr.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Put) {
			Put put = (Put) expression;
			NormalizePut normalizePut = new NormalizePut(this.depth + 1);
			ExpressionNorm expression2 = normalizePut.normalizePut(put, parent, dataType, tma);
			if (!normalizePut.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizePut.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Read) {
			Read read = (Read) expression;
			NormalizeRead normalizeRead = new NormalizeRead(this.depth + 1);
			ExpressionNorm expression2 = normalizeRead.normalizeRead(read, parent, dataType, tma);
			if (!normalizeRead.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRead.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Real) {
			Real real = (Real) expression.copy();
			Double d = real.getValore();
			if (Math.rint(d) == d) {
				IntegerNorm integerNorm = new IntegerNorm();
				integerNorm.setOldExpression(expression);
				Integer integer = new Integer(d.intValue());
				integerNorm.setNewExpression(integer);
				return integerNorm;
			} else {
				RealNorm realNorm = new RealNorm();
				realNorm.setOldExpression(expression);
				realNorm.setNewExpression(real);
				return realNorm;
			}
		} else if (expression instanceof RecordCons) {
			RecordCons recordCons = (RecordCons) expression;
			NormalizeRecordCons normalizeRecordCons = new NormalizeRecordCons(this.depth + 1);
			RecordConsNorm expression2 = normalizeRecordCons.normalizeRecordCons(recordCons, parent, dataType, tma);
			if (!normalizeRecordCons.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRecordCons.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Remove) {
			Remove remove = (Remove) expression;
			NormalizeRemove normalizeRemove = new NormalizeRemove(this.depth + 1);
			ExpressionNorm expression2 = normalizeRemove.normalizeRemove(remove, parent, dataType, tma);
			if (!normalizeRemove.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeRemove.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Somma) {
			Somma somma = (Somma) expression;
			NormalizeSomma normalizeSomma = new NormalizeSomma(this.depth + 1);
			ExpressionNorm expression2 = normalizeSomma.normalizeSomma(somma, parent, dataType, tma);
			if (!normalizeSomma.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeSomma.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Sottrazione) {
			Sottrazione sottrazione = (Sottrazione) expression;
			NormalizeSottrazione normalizeSottrazione = new NormalizeSottrazione(this.depth + 1);
			ExpressionNorm expression2 = normalizeSottrazione.normalizeSottrazione(sottrazione, parent, dataType, tma);
			if (!normalizeSottrazione.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeSottrazione.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Tail) {
			Tail tail = (Tail) expression;
			NormalizeTail normalizeTail = new NormalizeTail(this.depth + 1);
			ExpressionNorm expression2 = normalizeTail.normalizeTail(tail, parent, dataType, tma);
			if (!normalizeTail.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeTail.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else if (expression instanceof Write) {
			Write write = (Write) expression;
			NormalizeWrite normalizeWrite = new NormalizeWrite(this.depth + 1);
			ExpressionNorm expression2 = normalizeWrite.normalizeWrite(write, parent, dataType, tma);
			if (!normalizeWrite.isErrorOccurred())
				return expression2;
			else {
				String string = "Normalizing error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeWrite.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} else
			return null;
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public void setErrorOccurred(boolean errorOccurred) {
		this.errorOccurred = errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return this.errorMessage;
	}
}