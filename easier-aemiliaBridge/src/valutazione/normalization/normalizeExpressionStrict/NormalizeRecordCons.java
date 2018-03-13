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
import specificheAEmilia.NormalType;
import specificheAEmilia.RecordCons;
import specificheAEmilia.RecordType;
import specificheAEmilia.VariableDeclaration;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.typeChecking.NotEmptySequence;

/**
 * @author Mirko
 *
 */
class NormalizeRecordCons {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred;
    private int depth;
    private ErrorMessage errorMessage;
    
    public NormalizeRecordCons(int depth)
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

    // viene aggiornato lo scope con l'inizializzazione dei campi di recordCons
    // parente'il nome del record
	public RecordConsNorm normalizeRecordCons(RecordCons recordCons, String parent,
			DataType dataType)
		throws NormalizeException
		{
    	try {
			Expression[] expressions = recordCons.getValues();
			RecordType recordType = (RecordType) dataType;
			VariableDeclaration[] variableDeclarations = recordType
					.getVariableDeclarations();
			if (variableDeclarations.length != expressions.length) 
				{
				String string = "Normalizing error for " + recordCons.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = "Length mismatch between: " + "record type = "
						+ dataType.toString() + "; record = "
						+ recordCons.toString();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				} 
			else 
				{
				// si normalizzano le espressioni contenute nella definizione del record
				Expression[] expressions2 = new Expression[expressions.length];
				// si effettua type checking
				NotEmptySequence notEmptySequence = new NotEmptySequence(this.depth + 1);
				if (!notEmptySequence.isNotEmptySequence(recordCons)) 
					{
					String string = "Normalizing error for " + recordCons.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = notEmptySequence.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				for (int i = 0; i < expressions.length; i++) 
					{
					Expression expression = expressions[i];
					VariableDeclaration variableDeclaration = variableDeclarations[i];
					String string = variableDeclaration.getName();
					NormalType normalType = variableDeclaration.getType();
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					normalizeExpressionStrict.setTm(this.tm);
					ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent,
							normalType);
					if (!normalizeExpressionStrict.isErrorOccurred()) 
						{
						Expression expression3 = expression2.getNewExpression();
						expressions2[i] = expression3;
						// aggiungiamo string allo scope
						ValueIdentExpr valueIdentExpr = new ValueIdentExpr(
								expression3, true, normalType);
						String campo = "".equals(parent) ? string : parent
								+ "." + string;
						this.tm.put(campo, valueIdentExpr);
						} 
					else 
						{
						String string2 = "Normalizing error for " + recordCons.toString();
						this.errorMessage.setErrorMessage(string2);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					}
				RecordCons recordCons2 = new RecordCons(expressions2);
				RecordConsNorm recordConsNorm = new RecordConsNorm();
				recordConsNorm.setOldExpression(recordCons);
				recordConsNorm.setNewExpression(recordCons2);
				return recordConsNorm;
				}
			} 
    	catch (Exception e) 
    		{
			throw new NormalizeException(e);
    		}
		}
	
    // viene aggiornato lo scope con l'inizializzazione dei campi di recordCons
    // parente'il nome del record
    public RecordConsNorm normalizeRecordCons(RecordCons recordCons, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
    	throws NormalizeException
    	{
    	try {
			Expression[] expressions = recordCons.getValues();
			RecordType recordType = (RecordType) dataType;
			VariableDeclaration[] variableDeclarations = recordType
					.getVariableDeclarations();
			if (variableDeclarations.length != expressions.length) 
				{
				String string = "Normalizing error for " + recordCons.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string2 = "Length mismatch between: " + "record type = "
						+ dataType.toString() + "; record = "
						+ recordCons.toString();
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				} 
			else 
				{
				// si normalizzano le espressioni contenute nella definizione del record
				Expression[] expressions2 = new Expression[expressions.length];
				// si effettua type checking
				NotEmptySequence notEmptySequence = new NotEmptySequence(this.depth + 1);
				if (!notEmptySequence.isNotEmptySequence(recordCons)) 
					{
					String string = "Normalizing error for " + recordCons.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = notEmptySequence.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				for (int i = 0; i < expressions.length; i++) 
					{
					Expression expression = expressions[i];
					VariableDeclaration variableDeclaration = variableDeclarations[i];
					String string = variableDeclaration.getName();
					NormalType normalType = variableDeclaration.getType();
					NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
					ExpressionNorm expression2 = normalizeExpressionStrict.normalize(expression, parent,
							normalType, tma);
					if (!normalizeExpressionStrict.isErrorOccurred()) 
						{
						Expression expression3 = expression2.getNewExpression();
						expressions2[i] = expression3;
						// aggiungiamo string allo scope
						ValueIdentExpr valueIdentExpr = new ValueIdentExpr(
								expression3, true, normalType);
						String campo = "".equals(parent) ? string : parent
								+ "." + string;
						tma.put(campo, valueIdentExpr);
						}	 
					else 
						{
						String string2 = "Normalizing error for " + recordCons.toString();
						this.errorMessage.setErrorMessage(string2);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					}
				RecordCons recordCons2 = new RecordCons(expressions2);
				RecordConsNorm recordConsNorm = new RecordConsNorm();
				recordConsNorm.setOldExpression(recordCons);
				recordConsNorm.setNewExpression(recordCons2);
				return recordConsNorm;
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
