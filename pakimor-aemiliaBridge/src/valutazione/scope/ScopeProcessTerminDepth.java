/**
 * 
 */
package valutazione.scope;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.dataTypes.DataTypeNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.ArrayCons;
import specificheAEmilia.ArrayType;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.Boolean;
import specificheAEmilia.BooleanType;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerRangeType;
import specificheAEmilia.IntegerType;
import specificheAEmilia.ListCons;
import specificheAEmilia.ListType;
import specificheAEmilia.Local;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.PrioType;
import specificheAEmilia.RateType;
import specificheAEmilia.Real;
import specificheAEmilia.RealType;
import specificheAEmilia.RecordCons;
import specificheAEmilia.RecordType;
import specificheAEmilia.VarInit;
import specificheAEmilia.VariableDeclaration;
import specificheAEmilia.WeightType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeDataType.NormalizeDataType;
import valutazione.normalization.normalizeExpression.NormalizeExpression;

/**
 * @author Mirko
 *
 */
class ScopeProcessTerminDepth {

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

    public ScopeProcessTerminDepth(int depth) 
    	{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
    	}

	/**
     * Restituisce lo scope che serve per la valutazione delle espressioni
     * presenti in un termine di processo, che definisce un comportamento di
     * un ElemType a partire da uno scope fornito come parametro, che contiene i valori per
     * le costanti dell'ElemType.
     * Solleva una NormalizeException se ci sono espressioni
     * di inizializzazione di parametri che non possono essere valutate.
     *
     * @param be
     * @param tma - non viene modificato
     * @return
     * @throws NormalizeException
     */
    public TreeMap<String, ValueIdentExpr> getScopeProcessTerminDepth(BehavEquation be, TreeMap<String, 
    		ValueIdentExpr> tma)
        throws NormalizeException
        {
    	try {
			TreeMap<String, ValueIdentExpr> treeMap = new TreeMap<String, ValueIdentExpr>();
			treeMap.putAll(tma);
			// si preleva l'intestazione del BehavEquation, e si
			// aggiungono gli identificatori allo scope tm
			Header i = be.getBehavHeader();
			// per far si che il parametro abbia un'espressione di
			// inizializzazione, deve essere VarInit
			ParamDeclaration[] dps = i.getParameters();
			int j = 0;
			while (j < dps.length
					&& ((dps[j] instanceof VarInit) || (dps[j] instanceof VariableDeclaration))) 
				{
				if (dps[j] instanceof VarInit) 
					{
					// key rappresenta il nome della costante inizializzata
					String key = ((VarInit) dps[j]).getName();
					// td rappresenta il tipo dato della costante
					DataType td = ((VarInit) dps[j]).getType();
					// ee'l'espressione associata alla costante
					Expression e = ((VarInit) dps[j]).getExpr();
					// se tde'un Boolean si inserisce nella mappa una voce con
					// key come chiave e returnBoolean(e) come valore
					if (td instanceof specificheAEmilia.BooleanType) 
						{
						BooleanType booleanType = (BooleanType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(treeMap);
						DataTypeNorm dataType = normalizeDataType.normalize(
								booleanType, treeMap);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						DataType dataType2 = dataType.getNewDataType();
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2, treeMap);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Boolean)
							treeMap.put(key, new ValueIdentExpr(expression2,
									true, dataType2));
						else
							treeMap.put(key, new ValueIdentExpr(expression2,
									false, dataType2));
						}
					// se tde'un Integer si inserisce nella mappa una voce con
					// key come chiave e returnInteger(e) come valore
					else if (td instanceof IntegerType) 
						{
						IntegerType integerType = (IntegerType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(treeMap);
						DataTypeNorm dataType = normalizeDataType.normalize(
								integerType, treeMap);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						DataType dataType2 = dataType.getNewDataType();
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2, treeMap);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Integer)
							treeMap.put(key, new ValueIdentExpr(expression2,
									true, dataType2));
						else
							treeMap.put(key, new ValueIdentExpr(expression2,
									false, dataType2));
						} 
					else if (td instanceof IntegerRangeType) 
						{
						IntegerRangeType integerRangeType = (IntegerRangeType) td
								.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(treeMap);
						DataTypeNorm dataType = normalizeDataType.normalize(
								integerRangeType, treeMap);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						DataType dataType2 = dataType.getNewDataType();
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2, treeMap);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Integer)
							treeMap.put(key, new ValueIdentExpr(expression2,
									true, dataType2));
						else
							treeMap.put(key, new ValueIdentExpr(expression2,
									false, dataType2));
						} 
					else if (td instanceof RealType) 
						{
						RealType realType = (RealType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(treeMap);
						DataTypeNorm dataType = normalizeDataType.normalize(
								realType, treeMap);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						DataType dataType2 = dataType.getNewDataType();
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2, treeMap);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Real)
							treeMap.put(key, new ValueIdentExpr(expression2,
									true, dataType2));
						else
							treeMap.put(key, new ValueIdentExpr(expression2,
									false, dataType2));
						} 
					else if (td instanceof ListType) 
						{
						ListType listType = (ListType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(treeMap);
						DataTypeNorm dataType = normalizeDataType.normalize(
								listType, treeMap);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						DataType dataType2 = dataType.getNewDataType();
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2, treeMap);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof ListCons)
							treeMap.put(key, new ValueIdentExpr(expression2,
									true, dataType2));
						else
							treeMap.put(key, new ValueIdentExpr(expression2,
									false, dataType2));
						} 
					else if (td instanceof ArrayType) 
						{
						ArrayType arrayType = (ArrayType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(treeMap);
						DataTypeNorm dataType = normalizeDataType.normalize(
								arrayType, treeMap);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						DataType dataType2 = dataType.getNewDataType();
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2, treeMap);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof ArrayCons)
							treeMap.put(key, new ValueIdentExpr(expression2,
									true, dataType2));
						else
							treeMap.put(key, new ValueIdentExpr(expression2,
									false, dataType2));
						} 
					else if (td instanceof RecordType) 
						{
						RecordType recordType = (RecordType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(treeMap);
						DataTypeNorm dataType = normalizeDataType.normalize(
								recordType, treeMap);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						DataType dataType2 = dataType.getNewDataType();
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2, treeMap);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof RecordCons) 
							{
							treeMap.put(key, new ValueIdentExpr(expression2,
									true, dataType2));
							} 
						else 
							{
							treeMap.put(key, new ValueIdentExpr(expression2,
									false, dataType2));
							}
						} 
					else if (td instanceof PrioType) 
						{
						PrioType prioType = (PrioType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(treeMap);
						DataTypeNorm dataType = normalizeDataType.normalize(
								prioType, treeMap);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						DataType dataType2 = dataType.getNewDataType();
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2, treeMap);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Integer)
							treeMap.put(key, new ValueIdentExpr(expression2,
									true, dataType2));
						else
							treeMap.put(key, new ValueIdentExpr(expression2,
									false, dataType2));
						} 
					else if (td instanceof RateType) 
						{
						RateType rateType = (RateType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(treeMap);
						DataTypeNorm dataType = normalizeDataType.normalize(
								rateType, treeMap);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						DataType dataType2 = dataType.getNewDataType();
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2, treeMap);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Real)
							treeMap.put(key, new ValueIdentExpr(expression2,
									true, dataType2));
						else
							treeMap.put(key, new ValueIdentExpr(expression2,
									false, dataType2));
						} 
					else if (td instanceof WeightType) 
						{
						WeightType weightType = (WeightType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(treeMap);
						DataTypeNorm dataType = normalizeDataType.normalize(
								weightType, treeMap);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						DataType dataType2 = dataType.getNewDataType();
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2, treeMap);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for " + be.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Real)
							treeMap.put(key, new ValueIdentExpr(expression2,
									true, dataType2));
						else
							treeMap.put(key, new ValueIdentExpr(expression2,
									false, dataType2));
						} 
					}
				if (dps[j] instanceof VariableDeclaration) 
					{
					String key = dps[j].getName();
					DataType dataType = dps[j].getType();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType2 = normalizeDataType.normalize(
							dataType, treeMap);
					if (normalizeDataType.isErrorOccurred()) 
						{
						String string = "Scope evaluating error for " + be.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					DataType dataType3 = dataType2.getNewDataType();
					treeMap
							.put(key,
									new ValueIdentExpr(null, false, dataType3));
					}
					j++;
				}
			// nel caso in cui non ci sono variabili si incrementa j
			j++;
			// si aggiungono le variabili locali allo scope
			for (int k = j; k < dps.length && (dps[k] instanceof Local); k++) 
				{
				String key = dps[k].getName();
				DataType dataType = dps[k].getType();
				NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
				DataTypeNorm dataType2 = normalizeDataType.normalize(dataType,
						treeMap);
				if (normalizeDataType.isErrorOccurred()) 
					{
					String string = "Scope evaluating error for " + be.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				DataType dataType3 = dataType2.getNewDataType();
				treeMap.put(key, new ValueIdentExpr(null, false, dataType3));
				}
			return treeMap;
			} 
    	catch (Exception e) 
    		{
			throw new NormalizeException(e);
    		}
        }

	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}	
}
