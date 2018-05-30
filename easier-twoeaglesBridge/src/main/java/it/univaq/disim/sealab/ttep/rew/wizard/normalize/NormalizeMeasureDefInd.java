/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.wizard.normalize;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import it.univaq.disim.sealab.ttep.rew.classes.MeasureDef;
import it.univaq.disim.sealab.ttep.rew.classes.MeasureDefInd;
import it.univaq.disim.sealab.ttep.rew.wizard.normalize.checking.CheckInteger;
import it.univaq.disim.sealab.ttep.rew.wizard.normalize.structure.MeasureDefNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;
import valutazione.scope.ScopeArchiType;
import valutazione.typeChecking.TypeCheckingException;

/**
 * @author Mirko
 *
 */
public class NormalizeMeasureDefInd {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

	public NormalizeMeasureDefInd(ScopeArchiType scopeArchiType, int depth) {
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public List<MeasureDef> normalizeMeasureDefInd(MeasureDefInd measureDefInd) throws NormalizeException {
		// normalizzo l'espressione di inizio
		Expression expression = measureDefInd.getBeginningExp();
		NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
		TreeMap<String, ValueIdentExpr> treeMap = this.scopeArchiType.getScope();
		ExpressionNorm expressionNorm = normalizeExpressionStrict.normalize(expression, "", null, treeMap);
		if (normalizeExpressionStrict.isErrorOccurred()) {
			String string = "Normalizing error for " + measureDefInd.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
			list.add(errorMessage);
			this.errorOccurred = true;
			return null;
		}
		Expression expression2 = expressionNorm.getNewExpression();
		// normalizzo l'espressione di fine
		Expression expression3 = measureDefInd.getEndingExp();
		NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
		ExpressionNorm expressionNorm2 = normalizeExpressionStrict2.normalize(expression3, "", null, treeMap);
		if (normalizeExpressionStrict2.isErrorOccurred()) {
			String string = "Normalizing error for " + measureDefInd.toString();
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
			list.add(errorMessage);
			this.errorOccurred = true;
			return null;
		}
		Expression expression4 = expressionNorm2.getNewExpression();
		// effettuo il type checking sull'espressione iniziale e finale
		CheckInteger checkInteger = new CheckInteger(expression2, treeMap, this.depth + 1);
		try {
			if (!checkInteger.checkInteger()) {
				String string = "Normalizing error for " + measureDefInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkInteger.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} catch (TypeCheckingException e) {
			throw new NormalizeException(e);
		}
		CheckInteger checkInteger2 = new CheckInteger(expression4, treeMap, this.depth + 1);
		try {
			if (!checkInteger2.checkInteger()) {
				String string = "Normalizing error for " + measureDefInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkInteger2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} catch (TypeCheckingException e) {
			throw new NormalizeException(e);
		}
		it.univaq.disim.sealab.ttep.rew.wizard.normalize.checking.CheckRange checkRange = new it.univaq.disim.sealab.ttep.rew.wizard.normalize.checking.CheckRange(
				expression, expression3, this.depth + 1);
		try {
			if (!checkRange.checkRange()) {
				String string = "Normalizing error for " + measureDefInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRange.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
		} catch (TypeCheckingException e) {
			throw new NormalizeException(e);
		}
		// per ogni intero tra l'espressione finale e quella iniziale genero una
		// definizione di misura
		// si preleva l'espressione iniziale come un intero
		int indiceIniziale = ((Integer) expression2).getValore();
		// si preleva l'espressione finale come un intero
		int indiceFinale = ((Integer) expression4).getValore();
		// si alloca memoria per l'array risultato
		List<MeasureDef> measureDefs = new ArrayList<MeasureDef>();
		for (int i = 0; i <= indiceFinale - indiceIniziale; i++) {
			// si aggiorna l'indice
			treeMap.put(measureDefInd.getIndex(),
					new ValueIdentExpr(new Integer(i + indiceIniziale), true, new IntegerType()));
			// si normalizza la dichiarazione secondo il nuovo valore
			// dell'indice
			NormalizeMeasureDef normalizeMeasureDef = new NormalizeMeasureDef(this.depth + 1);
			MeasureDefNorm measureDefNorm = normalizeMeasureDef.normalizeMeasureDef(measureDefInd, treeMap);
			if (normalizeMeasureDef.isErrorOccurred()) {
				String string = "Normalizing error for " + measureDefInd.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeMeasureDef.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			MeasureDef measureDef2 = measureDefNorm.getNewMeasureDef();
			measureDefs.add(measureDef2);
		}
		return measureDefs;
	}
}
