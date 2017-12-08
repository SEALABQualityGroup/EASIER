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
import specificheAEmilia.BooleanType;
import specificheAEmilia.DataType;
import specificheAEmilia.ElemType;
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
public class ScopeProcessTerm {

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
    public ScopeProcessTerm(int depth) 
    	{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
    	}

	/**
     * Restituisce lo scope, che serve per la valutazione delle espressioni
     * presenti in un termine di processo, che definisce un comportamento di
     * un ElemType. 
     *
     * @param be
     * @param et
     * @return
     * @throws NormalizeException
     */
    public TreeMap<String, ValueIdentExpr> getScopeProcessTerm(BehavEquation be, ElemType et)
        throws NormalizeException
        {
        try {
			// si preleva l'intestazione di et.
			TreeMap<String, ValueIdentExpr> tma = new TreeMap<String, ValueIdentExpr>();
			// si aggiungono gli identificatori dell'intestazione di et
			// al contesto corrente (parametri formali per l'istanziazione
			// dell'AET)
			Header intElemType = et.getHeader();
			ParamDeclaration[] spc = intElemType.getParameters();
			for (int j = 0; j < spc.length && spc[0] != null; j++) 
				{
				String key = spc[j].getName();
				// la costante viene inizializzata a null
				tma.put(key, new ValueIdentExpr(null, false, spc[j].getType()));
				}
			// si preleva l'intestazione del BehavEquation precedente e si
			// aggiungono gli identificatori allo scope tma
			Header i = be.getBehavHeader();
			// per far si che il parametro abbia un'espressione di
			// inizializzazione deve essere VarInit
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
						normalizeDataType.setTm(tma);
						DataTypeNorm dataType = normalizeDataType
								.normalize(booleanType);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
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
								.normalize(e, "", dataType2);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof specificheAEmilia.Boolean)
							tma.put(key, new ValueIdentExpr(expression2, true,
									dataType2));
						else
							tma.put(key, new ValueIdentExpr(expression2, false,
									dataType2));
						}
					// se tde'un Integer si inserisce nella mappa una voce con
					// key come chiave e returnInteger(e) come valore.
					else if (td instanceof IntegerType) 
						{
						IntegerType integerType = (IntegerType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(tma);
						DataTypeNorm dataType = normalizeDataType
								.normalize(integerType);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
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
								.normalize(e, "", dataType2);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Integer)
							tma.put(key, new ValueIdentExpr(expression2, true,
									dataType2));
						else
							tma.put(key, new ValueIdentExpr(expression2, false,
									dataType2));
						} 
					else if (td instanceof IntegerRangeType) 
						{
						IntegerRangeType integerRangeType = (IntegerRangeType) td
								.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(tma);
						DataTypeNorm dataType = normalizeDataType
								.normalize(integerRangeType);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
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
								.normalize(e, "", dataType2);
						Expression expression2 = expression.getNewExpression();
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						if (expression2 instanceof Integer)
							tma.put(key, new ValueIdentExpr(expression2, true,
									dataType2));
						else
							tma.put(key, new ValueIdentExpr(expression2, false,
									dataType2));
						} 
					else if (td instanceof RealType) 
						{
						RealType realType = (RealType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(tma);
						DataTypeNorm dataType = normalizeDataType
								.normalize(realType);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
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
								.normalize(e, "", dataType2);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Real)
							tma.put(key, new ValueIdentExpr(expression2, true,
									dataType2));
						else
							tma.put(key, new ValueIdentExpr(expression2, false,
									dataType2));
						} 
					else if (td instanceof ListType) 
						{
						ListType listType = (ListType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(tma);
						DataTypeNorm dataType = normalizeDataType
								.normalize(listType);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
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
								.normalize(e, "", dataType2);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof ListCons)
							tma.put(key, new ValueIdentExpr(expression2, true,
									dataType2));
						else
							tma.put(key, new ValueIdentExpr(expression2, false,
									dataType2));
						} 
					else if (td instanceof ArrayType) 
						{
						ArrayType arrayType = (ArrayType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(tma);
						DataTypeNorm dataType = normalizeDataType
								.normalize(arrayType);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
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
								.normalize(e, "", dataType2);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof ArrayCons)
							tma.put(key, new ValueIdentExpr(expression2, true,
									dataType2));
						else
							tma.put(key, new ValueIdentExpr(expression2, false,
									dataType2));
						} 
					else if (td instanceof RecordType) 
						{
						RecordType recordType = (RecordType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(tma);
						DataTypeNorm dataType = normalizeDataType
								.normalize(recordType);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
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
								.normalize(e, "", dataType2);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof RecordCons)
							tma.put(key, new ValueIdentExpr(expression2, true,
									dataType2));
						else
							tma.put(key, new ValueIdentExpr(expression2, false,
									dataType2));
						} 
					else if (td instanceof PrioType) 
						{
						PrioType prioType = (PrioType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(tma);
						DataTypeNorm dataType = normalizeDataType
								.normalize(prioType);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
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
								.normalize(e, "", dataType2);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Integer)
							tma.put(key, new ValueIdentExpr(expression2, true,
									dataType2));
						else
							tma.put(key, new ValueIdentExpr(expression2, false,
									dataType2));
						} 
					else if (td instanceof RateType) 
						{
						RateType rateType = (RateType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(tma);
						DataTypeNorm dataType = normalizeDataType
								.normalize(rateType);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						DataType dataType2 = dataType.getNewDataType();
						NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
						ExpressionNorm expression = normalizeExpression
								.normalize(e, "", dataType2);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Real)
							tma.put(key, new ValueIdentExpr(expression2, true,
									dataType2));
						else
							tma.put(key, new ValueIdentExpr(expression2, false,
									dataType2));
						} 
					else if (td instanceof WeightType) 
						{
						WeightType weightType = (WeightType) td.copy();
						NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
						normalizeDataType.setTm(tma);
						DataTypeNorm dataType = normalizeDataType
								.normalize(weightType);
						if (normalizeDataType.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
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
								.normalize(e, "", dataType2);
						if (normalizeExpression.isErrorOccurred()) 
							{
							String string = "Scope evaluating error for behavioral eqaution " + be + " belong to " + et.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = normalizeExpression.getErrorMessage();
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						Expression expression2 = expression.getNewExpression();
						if (expression2 instanceof Real)
							tma.put(key, new ValueIdentExpr(expression2, true,
									dataType2));
						else
							tma.put(key, new ValueIdentExpr(expression2, false,
									dataType2));
						} 
					}
				if (dps[j] instanceof VariableDeclaration) 
					{
					String key = dps[j].getName();
					tma.put(key, new ValueIdentExpr(null, false, dps[j]
							.getType()));
					}
				j++;
				}
			// nel caso in cui non ci sono variabili si incrementa j
			j++;
			// si aggiungono le variabili locali allo scope
			for (int k = j; k < dps.length && (dps[k] instanceof Local); k++) 
				{
				String key = dps[k].getName();
				tma.put(key, new ValueIdentExpr(null, false, dps[k].getType()));
				}
			return tma;
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

	
}
