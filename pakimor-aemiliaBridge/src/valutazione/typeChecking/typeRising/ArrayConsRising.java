/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.ArrayCons;
import specificheAEmilia.ArrayType;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.NormalType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeCompare;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class ArrayConsRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public ArrayConsRising(int depth)
    	{
    	super();
    	this.tm = new TreeMap<String, ValueIdentExpr>();
    	this.depth = depth;
    	this.errorMessage  = new ErrorMessage(depth);
    	}
    
	public TreeMap<String, ValueIdentExpr> getTm() 
		{
		return tm;
		}

	public void setTm(TreeMap<String, ValueIdentExpr> tm) 
		{
		this.tm = tm;
		}
	
	public boolean isErrorOccurred() 
		{
		return errorOccurred;
		}

	public DataTypeEnum risingArrayCons(ArrayCons arrayCons, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		try {
			Expression[] expressions = arrayCons.getArrayElements();
			if (expressions.length > 0) 
				{
				// se gli elementi della lista non sono dello stesso tipo solleviamo un'eccezione
				Expression expression = expressions[0];
				TypeRising typeRising = new TypeRising(this.depth + 1);
				DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent, dataType, this.tm);
				if (typeRising.isErrorOccurred())
					{
					String string = "type checking error for " + arrayCons.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = typeRising.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				for (int i = 1; i < expressions.length; i++) 
					{
					Expression expression2 = expressions[i];
					TypeRising typeRising2 = new TypeRising(this.depth + 1);
					DataTypeEnum dataTypeEnum2 = typeRising2.rising(expression2, parent,
							dataType, this.tm);
					if (typeRising2.isErrorOccurred())
						{
						String string = "type checking error for " + arrayCons.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = typeRising2.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					if (!dataTypeEnum.equals(dataTypeEnum2)) 
						{
						String string = "type checking error for " + arrayCons.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2  = arrayCons
								+ " have not elements with same type: "
								+ expression.toString() + " has type "
								+ dataTypeEnum + "; " + expression2.toString()
								+ " has type " + dataTypeEnum2;
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					}
				// se dataType none'null allora dataType deve essere un ArrayType
				if (dataType != null)
					{
					if (!(dataType instanceof ArrayType))
						{
						// bisogna verificare che il tipo di dataType corrisponda a quello degli elementi dell'array
						String string2 = "type checking error for " + arrayCons.toString();
						this.errorMessage.setErrorMessage(string2);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string3 = " data type " + dataType 
								+ " must be array type";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string3);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					ArrayType arrayType = (ArrayType)dataType;
					NormalType normalType = arrayType.getType();
					TypeCompare typeCompare = new TypeCompare();
					if (!typeCompare.sameType(normalType, dataTypeEnum))
						{
						// bisogna verificare che il tipo di dataType corrisponda a quello degli elementi dell'array
						String string2 = "type checking error for " + arrayCons.toString();
						this.errorMessage.setErrorMessage(string2);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string3 = " Item " + expression
								+ " and elements array have different type";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string3);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					}
				}
			return DataTypeEnum.Array;
			} 
		catch (Exception e) 
			{
			throw new TypeRisingException(e);
			}
		}
	
	public DataTypeEnum risingArrayCons(ArrayCons arrayCons, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		/* MODELED */
		try {
			Expression[] expressions = arrayCons.getArrayElements();
			if (expressions.length > 0) 
				{
				// se gli elementi della lista non sono dello stesso tipo solleviamo un'eccezione
				Expression expression = expressions[0];
				TypeRising typeRising = new TypeRising(this.depth + 1);
				DataTypeEnum dataTypeEnum = typeRising.rising(expression, parent,
						dataType, tma);
				if (typeRising.isErrorOccurred())
					{
					// 1
					String string = "type checking error for " + arrayCons.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = typeRising.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				for (int i = 1; i < expressions.length; i++) 
					{
					Expression expression2 = expressions[i];
					TypeRising typeRising2 = new TypeRising(this.depth + 1);
					DataTypeEnum dataTypeEnum2 = typeRising2.rising(expression2, parent,
							dataType, tma);
					if (typeRising2.isErrorOccurred())
						{
						// 2
						String string = "type checking error for " + arrayCons.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = typeRising2.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					if (!dataTypeEnum.equals(dataTypeEnum2)) 
						{
						// 3
						String string = "type checking error for " + arrayCons.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string2  = arrayCons
								+ " have not elements with same type: "
								+ expression.toString() + " has type "
								+ dataTypeEnum + "; " + expression2.toString()
								+ " has type " + dataTypeEnum2;
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string2);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					}
				// se dataType none'null allora dataType deve essere un ArrayType
				if (dataType != null)
					{
					if (!(dataType instanceof ArrayType))
						{
						// 4
						// bisogna verificare che il tipo di dataType corrisponda a quello degli elementi dell'array
						String string2 = "type checking error for " + arrayCons.toString();
						this.errorMessage.setErrorMessage(string2);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string3 = " data type " + dataType 
								+ " must be array type";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string3);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					ArrayType arrayType = (ArrayType)dataType;
					NormalType normalType = arrayType.getType();
					TypeCompare typeCompare = new TypeCompare();
					if (!typeCompare.sameType(normalType, dataTypeEnum))
						{
						// 5
						// bisogna verificare che il tipo di dataType corrisponda a quello degli elementi dell'array
						String string2 = "type checking error for " + arrayCons.toString();
						this.errorMessage.setErrorMessage(string2);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						String string3 = " Item " + expression
								+ " and elements array have different type";
						ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
						errorMessage.setErrorMessage(string3);
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					}
				}
			return DataTypeEnum.Array;
			} 
		catch (Exception e) 
			{
			throw new TypeRisingException(e);
			}
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	
	
}
