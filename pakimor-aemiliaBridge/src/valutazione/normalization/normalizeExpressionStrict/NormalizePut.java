/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
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
import valutazione.typeChecking.IdentExprRecordTyping;

/**
 * @author Mirko
 *
 */
class NormalizePut {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizePut(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	// dataTypee'il tipo di dato dell'espressione identificata da parent
	public RecordConsNorm normalizePut(Put put, String parent, DataType dataType)
		throws NormalizeException
		{
		try {
			// si effettua il controllo di tipo
			IdentExprRecordTyping identExprRecordTyping = new IdentExprRecordTyping(this.depth + 1);
			if (!identExprRecordTyping.isIdentExpRecord(put)) 
				{
				String string = "Normalizing error for " + put.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = identExprRecordTyping.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			IdentExpr expression = put.getRecord();
			Expression expression2 = put.getValue();
			String field = "".equals(parent) ? put.getField() : parent + "."
					+ put.getField();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			normalizeExpressionStrict.setTm(this.tm);
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent,
					dataType);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
				normalizeExpressionStrict2.setTm(this.tm);
				ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, parent,
						dataType);
				if (!normalizeExpressionStrict2.isErrorOccurred()) 
					{
					Expression expression5 = expression3.getNewExpression();
					Expression expression6 = expression4.getNewExpression();
					// si aggiorna tma
					// per precondizione field deve essere presente in tma
					ValueIdentExpr valueIdentExpr = this.tm.get(field);
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
								String string2 ="Length mismatch between: "
										+ put.toString() + " and "
										+ dataType.toString();
								ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
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
							String string2 ="Data Type " + dataType.toString() + " is not record type";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
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
						String string2 ="Normalized expression " + expression5.toString() + " of " + expression.toString() + " is not record";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
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
					ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
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
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
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
    public RecordConsNorm normalizePut(Put put, String parent, DataType dataType, TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	/* MODELED */
    	try {
			// effettuo il controllo di tipo
			IdentExprRecordTyping identExprRecordTyping = new IdentExprRecordTyping(this.depth + 1);
			if (!identExprRecordTyping.isIdentExpRecord(put)) 
				{
				// 1
				String string = "Normalizing error for " + put.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = identExprRecordTyping.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			IdentExpr expression = put.getRecord();
			Expression expression2 = put.getValue();
			String field = "".equals(parent) ? put.getField() : parent + "."
					+ put.getField();
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression3 = normalizeExpressionStrict.normalize(expression, parent,
					dataType, tma);
			if (!normalizeExpressionStrict.isErrorOccurred()) 
				{
				NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpressionStrict2.normalize(expression2, parent,
						dataType, tma);
				if (!normalizeExpressionStrict2.isErrorOccurred()) 
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
								// 2
								String string = "Normalizing error for " + put.toString();
								this.errorMessage.setErrorMessage(string);
								List<ErrorMessage> list = this.errorMessage.getCauses();
								String string2 ="Length mismatch between: "
										+ put.toString() + " and "
										+ dataType.toString();
								ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
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
							// 3
							String string = "Normalizing error for " + put.toString();
							this.errorMessage.setErrorMessage(string);
							List<ErrorMessage> list = this.errorMessage.getCauses();
							String string2 ="Data Type " + dataType.toString() + " is not record type";
							ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
							errorMessage.setErrorMessage(string2);
							list.add(errorMessage);
							this.errorOccurred = true;
							return null;
							}
						} 
					else 
						{
						// 4
						String string = "Normalizing error for " + put.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2 ="Normalized expression " + expression5.toString() + " of " + expression.toString() + " is not record";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					} 
				else 
					{
					// 5
					String string = "Normalizing error for " + put.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				} 
			else 
				{
				// 6
				String string = "Normalizing error for " + put.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
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

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setTm(TreeMap<String, ValueIdentExpr> tm) {
		this.tm = tm;
	}
    
    
}
