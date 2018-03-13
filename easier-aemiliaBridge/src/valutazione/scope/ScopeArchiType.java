/**
 * 
 */
package valutazione.scope;

import interfacceSpecifiche.NumberExp;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import restrizioniIstanze.dataTypes.DataTypeNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import specificheAEmilia.ArchiType;
import specificheAEmilia.ArrayCons;
import specificheAEmilia.ArrayType;
import specificheAEmilia.BooleanType;
import specificheAEmilia.ConstInit;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerRangeType;
import specificheAEmilia.IntegerType;
import specificheAEmilia.ListCons;
import specificheAEmilia.ListType;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.PrioType;
import specificheAEmilia.RateType;
import specificheAEmilia.Real;
import specificheAEmilia.RealType;
import specificheAEmilia.RecordCons;
import specificheAEmilia.RecordType;
import specificheAEmilia.WeightType;
import utilities.ErrorMessage;
import valutazione.ArchiTypeParts;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeDataType.NormalizeDataType;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;
import valutazione.typeChecking.ConstInitChecking;

/**
 * @author Mirko
 *
 */
public class ScopeArchiType {

	private ArchiTypeParts atp;
	
	private TreeMap<String, ValueIdentExpr> tm = new TreeMap<String, ValueIdentExpr>();

	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

    public ScopeArchiType(ArchiType archiType,int depth) throws NormalizeException 
    	{
    	this.depth = depth;
    	this.errorMessage = new ErrorMessage(depth);
		this.atp = new ArchiTypeParts(archiType);
        try {
			// si assegna al campo l'argomento del costruttore
			// i contiene l'intestazione del tipo architetturale
			Header i = this.atp.getAt().getArchiTypeHeader();
			// ci contiene i parametri costanti dell'intestazione del
			// tipo architetturale
			ParamDeclaration[] ci = i.getParameters();
			for (int j = 0; j < ci.length && ci[0] != null; j++) 
				{
				ConstInit constInit = (ConstInit) ci[j];
				// controllo constInit
				ConstInitChecking constInitChecking = new ConstInitChecking(
						constInit, this.tm,this.depth + 1);
				if (!constInitChecking.checkExprType()) 
					{
					// 1
					String string = "Scope evaluating error for " + this.atp.getAt().toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = constInitChecking.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return;
					}
				// keye'il nome della costante
				String key = constInit.getName();
				// tde'il tipo della costante
				DataType td = constInit.getType();
				// ee'l'espressione associata alla costante
				Expression e = constInit.getExpr();
				// se tde'un Boolean si inserisce nella mappa una voce con
				// key come chiave e returnBoolean(e) come valore            
				if (td instanceof specificheAEmilia.BooleanType) 
					{
					BooleanType booleanType = (BooleanType) td.copy();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType = normalizeDataType
							.normalize(booleanType,this.tm);
					if (normalizeDataType.isErrorOccurred()) 
						{
						// 2
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					DataType dataType2 = dataType.getNewDataType();
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(e, "", dataType2);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 3
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					Expression expression2 = expression.getNewExpression();
					if (expression2 instanceof specificheAEmilia.Boolean) 
						{
						this.tm.put(key, new ValueIdentExpr(expression2, true,
								dataType2));
						((ConstInit) ci[j]).setExpr(expression2);
						} 
					else 
						{
						// 4
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = key + " is boolean but its value " + expression2.toString() + "  is not boolean";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					}
				// se tde'un Integer si inserisce nella mappa una voce con
				// key come chiave e returnInteger(e) come valore
				else if (td instanceof IntegerType) 
					{
					IntegerType integerType = (IntegerType) td.copy();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType = normalizeDataType
							.normalize(integerType,this.tm);
					if (normalizeDataType.isErrorOccurred()) 
						{
						// 5
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					DataType dataType2 = dataType.getNewDataType();
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(e, "", dataType2);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 6
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					Expression expression2 = expression.getNewExpression();
					if (expression2 instanceof specificheAEmilia.Integer) 
						{
						this.tm.put(key, new ValueIdentExpr(expression2, true,
								dataType2));
						((ConstInit) ci[j]).setExpr(expression2);
						} 
					else 
						{
						// 7
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = key + " is integer but its value "+ expression2.toString() + " is not integer";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					} 
				else if (td instanceof IntegerRangeType) 
					{
					IntegerRangeType integerRangeType = (IntegerRangeType) td
							.copy();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType = normalizeDataType
							.normalize(integerRangeType,this.tm);
					if (normalizeDataType.isErrorOccurred()) 
						{
						// 8
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					DataType dataType2 = dataType.getNewDataType();
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(e, "", dataType2);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 9
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					Expression expression2 = expression.getNewExpression();
					if (expression2 instanceof specificheAEmilia.Integer) 
						{
						this.tm.put(key, new ValueIdentExpr(expression2, true,
								dataType2));
						((ConstInit) ci[j]).setExpr(expression2);
						} 
					else 
						{
						// 10
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = key + " is integer but its value "+ expression2.toString() + " is not integer";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					} 
				else if (td instanceof RealType) 
					{
					RealType realType = (RealType) td.copy();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType = normalizeDataType
							.normalize(realType,this.tm);
					if (normalizeDataType.isErrorOccurred()) 
						{
						// 11
						String string  = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					DataType dataType2 = dataType.getNewDataType();
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(e, "", dataType2);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 12
						String string  = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					Expression expression2 = expression.getNewExpression();
					if (expression2 instanceof NumberExp) 
						{
						NumberExp numberExp = (NumberExp) expression2;
						double d = numberExp.getNumber();
						Real real = new Real(d);
						this.tm.put(key, new ValueIdentExpr(real, true,
								dataType2));
						((ConstInit) ci[j]).setExpr(expression2);
						} 
					else 
						{
						// 13
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = key + " is number but its value "+ expression2.toString() + " is not number";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					} 
				else if (td instanceof ListType) 
					{
					ListType listType = (ListType) td.copy();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType = normalizeDataType
							.normalize(listType,this.tm);
					if (normalizeDataType.isErrorOccurred()) 
						{
						// 14
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					DataType dataType2 = dataType.getNewDataType();
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(e, "", dataType2);
					Expression expression2 = expression.getNewExpression();
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 15
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					if (expression2 instanceof ListCons) 
						{
						this.tm.put(key, new ValueIdentExpr(expression2, true,
								dataType2));
						((ConstInit) ci[j]).setExpr(expression2);
						} 
					else 
						{
						// 16
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = key + " is list but its value "+ expression2.toString() + " is not list";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					} 
				else if (td instanceof ArrayType) 
					{
					ArrayType arrayType = (ArrayType) td.copy();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType = normalizeDataType
							.normalize(arrayType,this.tm);
					if (normalizeDataType.isErrorOccurred()) 
						{
						// 17
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					DataType dataType2 = dataType.getNewDataType();
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(e, "", dataType2);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 18
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					Expression expression2 = expression.getNewExpression();
					if (expression2 instanceof ArrayCons) 
						{
						this.tm.put(key, new ValueIdentExpr(expression2, true,
								dataType2));
						((ConstInit) ci[j]).setExpr(expression2);
						} 
					else 
						{
						// 19
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = key + " is array but its value "+ expression2.toString() + " is not array";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					} 
				else if (td instanceof RecordType) 
					{
					RecordType recordType = (RecordType) td.copy();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType = normalizeDataType
							.normalize(recordType,this.tm);
					if (normalizeDataType.isErrorOccurred()) 
						{
						// 20
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					DataType dataType2 = dataType.getNewDataType();
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(e, "", dataType2);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 21
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					Expression expression2 = expression.getNewExpression();
					if (expression2 instanceof RecordCons) 
						{
						this.tm.put(key, new ValueIdentExpr(expression2, true,
								dataType2));
						((ConstInit) ci[j]).setExpr(expression2);
						} 
					else 
						{
						// 22
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = key + " is record but its value "+ expression2.toString() + " is not record";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					} 
				else if (td instanceof PrioType) 
					{
					PrioType prioType = (PrioType) td.copy();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType = normalizeDataType
							.normalize(prioType,this.tm);
					if (normalizeDataType.isErrorOccurred()) 
						{
						// 23
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					DataType dataType2 = dataType.getNewDataType();
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(e, "", dataType2);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 24
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					Expression expression2 = expression.getNewExpression();
					if (expression2 instanceof Integer) 
						{
						this.tm.put(key, new ValueIdentExpr(expression2, true,
								dataType2));
						((ConstInit) ci[j]).setExpr(expression2);
						} 
					else 
						{
						// 25
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = key + " is piority but its value "+ expression2.toString() + " is not integer";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					} 
				else if (td instanceof RateType) 
					{
					RateType rateType = (RateType) td.copy();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType = normalizeDataType
							.normalize(rateType,this.tm);
					if (normalizeDataType.isErrorOccurred()) 
						{
						// 26
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					DataType dataType2 = dataType.getNewDataType();
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(e, "", dataType2);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 27
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					Expression expression2 = expression.getNewExpression();
					if (expression2 instanceof NumberExp) 
						{
						NumberExp numberExp = (NumberExp) expression2;
						double d = numberExp.getNumber();
						Real real = new Real(d);
						this.tm.put(key, new ValueIdentExpr(real, true,
								dataType2));
						((ConstInit) ci[j]).setExpr(expression2);
						} 
					else 
						{
						// 28 
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = key + " is rate but its value "+ expression2.toString() + " is not number";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					} 
				else if (td instanceof WeightType) 
					{
					WeightType weightType = (WeightType) td.copy();
					NormalizeDataType normalizeDataType = new NormalizeDataType(this.depth + 1);
					DataTypeNorm dataType = normalizeDataType
							.normalize(weightType,this.tm);
					if (normalizeDataType.isErrorOccurred()) 
						{
						// 29
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeDataType.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					DataType dataType2 = dataType.getNewDataType();
					ExpressionNorm expression = normalizeExpressionStrict
							.normalize(e, "", dataType2);
					if (normalizeExpressionStrict.isErrorOccurred()) 
						{
						// 30
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					Expression expression2 = expression.getNewExpression();
					if (expression2 instanceof NumberExp) 
						{
						NumberExp numberExp = (NumberExp) expression2;
						double d = numberExp.getNumber();
						Real real = new Real(d);
						this.tm.put(key, new ValueIdentExpr(real, true,
								dataType2));
						((ConstInit) ci[j]).setExpr(expression2);
						} 
					else 
						{
						// 31
						String string = "Scope evaluating error for " + this.atp.getAt().toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 = key + " is weight but its value "+ expression2.toString() + " is not number";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return;
						}
					} 
				}
			} 
        catch (Exception e) 
        	{
        	throw new NormalizeException(e);
        	}		
    	}

	/**
     * Crea un nuovo oggetto Scope, definendo una TreeMap, che ha come chiavi,
     * i nomi dei parametri costanti inizializzati presenti
     * nell'intestazione dell'ArchiType, e come valori, le valutazioni
     * delle rispettive espressioni di inizializzazione.
     *
     */
    public TreeMap<String, ValueIdentExpr> getScope()
        {
    	return this.tm;         
    	}	
    
    /**
     * Stampa sullo standard output le coppie identificatore - valore
     * contenute nella mappa.
     *
     * @param t
     */
    public void stampaScope(TreeMap<String, ValueIdentExpr> t)
        {
        Set<String> keys = t.keySet();
        String[] as = new String[keys.size()];
        keys.toArray(as);
        Collection<ValueIdentExpr> valori = t.values();
        Object[] os = valori.toArray();
        for (int i = 0; i < as.length; i++)
            {
            System.out.print("The identifier "+as[i]);
            if (os[i] != null)
            System.out.println(" have value "+((ValueIdentExpr)os[i]).getValore());
            else
            System.out.println(" have value "+os[i]);
            }
        System.out.println();
        }	
	
    /**
     * Assegna un tipo architetturale ad un oggetto Scope.
     *
     * @param at
     */
    public void setAt(ArchiType at) 
    	{
        this.atp.setAt(at);
    	}
    
    /**
     * Assegna uno scope ArchiType.
     *
     * @param tm
     */
    public void setScopeArchiType(TreeMap<String, ValueIdentExpr> tm)
        {
        this.tm = tm;
        }

	/**
	 * Restituisce il tipo architetturale.
	 *
	 * @return
	 */
	public ArchiType getAt() 
		{
	    return atp.getAt();
		}

	public boolean isErrorOccurred() 
		{
		return errorOccurred;
		}

	public void setErrorOccurred(boolean errorOccurred) 
		{
		this.errorOccurred = errorOccurred;
		}

	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}

	public ArchiTypeParts getAtp() {
		return atp;
	}
}
