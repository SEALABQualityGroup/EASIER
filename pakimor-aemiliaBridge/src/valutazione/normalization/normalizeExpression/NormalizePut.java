/**
 * 
 */
package valutazione.normalization.normalizeExpression;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.PutNorm;
import restrizioniIstanze.expressions.RecordConsNorm;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Put;
import specificheAEmilia.RecordCons;
import specificheAEmilia.RecordType;
import specificheAEmilia.VariableDeclaration;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizePut {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred;

    public NormalizePut(int depth)
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
	
	// dataTypee'il tipo di dato dell'espressione identificata da parent
	public ExpressionNorm normalizePut(Put put, String parent, DataType dataType)
		throws NormalizeException
		{
    	try {
			IdentExpr expression = put.getRecord();
			Expression expression2 = put.getValue();
			String field = parent + put.getField();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			normalizeExpression.setTm(this.tm);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent, dataType);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
				normalizeExpression2.setTm(this.tm);
				ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent,
						dataType, this.tm);
				if (!normalizeExpression2.isErrorOccurred()) 
					{
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					// si aggiorna tma
					// per precondizione field deve essere presente in tma
					ValueIdentExpr valueIdentExpr = this.tm.get(field);
					valueIdentExpr.setValore(expression6);
					if (expression6 instanceof RecordCons) 
						{
						RecordCons recordCons = (RecordCons) expression5;
						Expression[] expressions = recordCons.getValues();
						// si effettua il controllo di tipo
						if (dataType instanceof RecordType) 
							{
							RecordType recordType = (RecordType) dataType;
							VariableDeclaration[] variableDeclarations = recordType
									.getVariableDeclarations();
							if (variableDeclarations.length != expressions.length) 
								{
								String string = "Normalizing error for " + put.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
								String string2 = "Type mismatch between: "
										+ put.toString() + " and "
										+ dataType.toString();
								errorMessage.setErrorMessage(string2);
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								} 
							else 
								{
								for (int i = 0; i < expressions.length; i++) 
									{
									VariableDeclaration variableDeclaration = variableDeclarations[i];
									String string = variableDeclaration
											.getName();
									if (string.equals(put.getField()))
										expressions[i] = expression6;
									}
								RecordConsNorm recordConsNorm = new RecordConsNorm();
								recordConsNorm.setOldExpression(put);
								recordConsNorm.setNewExpression(recordCons);
								return recordConsNorm;
								}
							} 
						else 
							{
							String string = "Normalizing error for " + put.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2 = "Type mismatch between: "
									+ put.toString() + " and "
									+ dataType.toString();
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						} 
					else 
						{
						Put put2 = new Put(put.getField(), expression6,
								expression.copy());
						PutNorm putNorm = new PutNorm();
						putNorm.setOldExpression(put);
						putNorm.setNewExpression(put2);
						return putNorm;
						}
					} 
				else 
					{
					String string = "Normalizing error for " + put.toString();
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
				String string = "Normalizing error for " + put.toString();
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
	
	// l'espressione put.getRecord() deve corrispondere ad un IdentExpr
	// caricato nello scope
    public ExpressionNorm normalizePut(Put put, String parent, DataType dataType, TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			IdentExpr expression = put.getRecord();
			Expression expression2 = put.getValue();
			String field = parent + put.getField();
			NormalizeExpression normalizeExpression = new NormalizeExpression(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpression.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpression.isErrorOccurred()) 
				{
				NormalizeExpression normalizeExpression2 = new NormalizeExpression(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpression2.normalize(expression2, parent,
						dataType, tma);
				if (!normalizeExpression2.isErrorOccurred()) 
					{
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					// si aggiorna tma
					// per precondizione field deve essere presente in tma
					ValueIdentExpr valueIdentExpr = tma.get(field);
					valueIdentExpr.setValore(expression6);
					if (expression5 instanceof RecordCons) 
						{
						RecordCons recordCons = (RecordCons) expression5;
						Expression[] expressions = recordCons.getValues();
						// si effettua il controllo di tipo
						if (dataType instanceof RecordType) 
							{
							RecordType recordType = (RecordType) dataType;
							VariableDeclaration[] variableDeclarations = recordType
									.getVariableDeclarations();
							if (variableDeclarations.length != expressions.length) 
								{
								String string = "Normalizing error for " + put.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
								String string2 = "Type mismatch between: "
										+ put.toString() + " and "
										+ dataType.toString();
								errorMessage.setErrorMessage(string2);
								list.add(errorMessage);
								this.errorOccurred = true;
								return null;
								} 
							else 
								{
								for (int i = 0; i < expressions.length; i++) 
									{
									VariableDeclaration variableDeclaration = variableDeclarations[i];
									String string = variableDeclaration
											.getName();
									if (string.equals(put.getField()))
										expressions[i] = expression6;
									}
								RecordConsNorm recordConsNorm = new RecordConsNorm();
								recordConsNorm.setOldExpression(put);
								recordConsNorm.setNewExpression(recordCons);
								return recordConsNorm;
								}
							} 
						else 
							{
							String string = "Normalizing error for " + put.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							String string2 = "Type mismatch between: "
									+ put.toString() + " and "
									+ dataType.toString();
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						} 
					else 
						{
						Put put2 = new Put(put.getField(), expression6,
								expression.copy());
						PutNorm putNorm = new PutNorm();
						putNorm.setOldExpression(put);
						putNorm.setNewExpression(put2);
						return putNorm;
						}
					} 
				else 
					{
					String string = "Normalizing error for " + put.toString();
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
				String string = "Normalizing error for " + put.toString();
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
