/**
 * 
 */
package valutazione.typeChecking;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.BehavProcess;
import specificheAEmilia.DataType;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.Local;
import specificheAEmilia.ParamDeclaration;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.typeRising.TypeRising;

/**
 * @author Mirko
 *
 */
public class BehavProcessChecking 
	{

	private BehavProcess behavProcess;
	private TreeMap<String, ValueIdentExpr> tm;
	private Header header;
	private int depth;
	private ErrorMessage errorMessage;
	
	public BehavProcessChecking(BehavProcess behavProcess,
			TreeMap<String, ValueIdentExpr> tma, Header header, int depth) 
		{
		super();
		this.behavProcess = behavProcess;
		this.tm = tma;
		this.header = header;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * I parametri attuali devono corrispondere per numero, ordine e tipo 
	 * ai parametri formali variabili dell�equazione comportamentale invocata.
	 * 
	 * @return
	 */
	public boolean checkParameters()
		throws TypeCheckingException
		{
		try {
			Expression[] expressions = this.behavProcess.getExprs();
			ParamDeclaration[] paramDeclarations = this.header.getParameters();
			// ricordarsi che paramDeclarations a differenza di expressions puo' contenere dei null e dei parametri locali
			List<ParamDeclaration> list = new ArrayList<ParamDeclaration>();
			for (ParamDeclaration paramDeclaration : paramDeclarations)
				{
				if (paramDeclaration != null)
					{
					if (!(paramDeclaration instanceof Local))
						list.add(paramDeclaration);
					}
				}
			if (expressions.length != list.size()) 
				{
				String string = "type checking error for " + this.behavProcess.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				String string2 = "sequence of formal parameters " + list.toString() + 
					" and actual parameters " + expressions.toString() + " have different length";
				ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
				errorMessage.setErrorMessage(string2);
				list2.add(errorMessage);
				return false;
				}
			for (int i = 0; i < expressions.length; i++) 
				{
				Expression expression = expressions[i];
				ParamDeclaration paramDeclaration = list.get(i);
				DataType dataType = paramDeclaration.getType();
				TypeRising typeRising = new TypeRising(this.depth + 1);
				DataTypeEnum dataTypeEnum;
				dataTypeEnum = typeRising.rising(expression, "", dataType,
						this.tm);
				if (typeRising.isErrorOccurred())
					{
					String string = "type checking error for " + this.behavProcess.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = typeRising.getErrorMessage();
					list2.add(errorMessage);
					return false;
					}
				TypeCompare typeCompare = new TypeCompare();
				boolean b1 = typeCompare.sameType(dataType, dataTypeEnum);
				if (!b1) 
					{
					String string = "type checking error for " + this.behavProcess.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					String string2 = dataType.toString() + " and " + dataTypeEnum.toString() + " have different type";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					list2.add(errorMessage);
					return false;
					}
				}
			return true;
			} 
		catch (Exception e) 
			{
			throw new TypeCheckingException(e);
			}
		}
	
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}	

	}
