/**
 * 
 */
package valutazione.typeChecking.aeiChecking;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.AEIdecl;
import specificheAEmilia.DataType;
import specificheAEmilia.ElemType;
import specificheAEmilia.Expression;
import specificheAEmilia.Header;
import specificheAEmilia.ParamDeclaration;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeCheckingException;
import valutazione.typeChecking.TypeCompare;
import valutazione.typeChecking.typeRising.TypeRising;

/**
 * @author Jimmy
 *
 */
public class CheckParameters {

	private AEIdecl idecl;
	private ElemType elemType;
	private TreeMap<String, ValueIdentExpr> tm;
	private int depth;
	private ErrorMessage errorMessage;
	
	public CheckParameters(AEIdecl idecl, ElemType elemType,
			TreeMap<String, ValueIdentExpr> tm, int depth) 
		{
		super();
		this.idecl = idecl;
		this.elemType = elemType;
		this.tm = tm;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * La sequenza di parametri attuali deve corrispondere per numero ordine e tipo alla sequenza dei parametri formali dell�AET.
	 * 
	 * @return
	 */
	public boolean checkParameters()
		throws TypeCheckingException
		{
		try {
			Header header = this.elemType.getHeader();
			ParamDeclaration[] paramDeclarations = header.getParameters();
			Expression[] expressions = this.idecl.getActualParams();
			// ricordarsi che paramDeclarations a differenza di expressions puo' contenere dei null
			List<ParamDeclaration> list = new ArrayList<ParamDeclaration>();
			for (ParamDeclaration paramDeclaration : paramDeclarations)
				{
				if (paramDeclaration != null)
					list.add(paramDeclaration);
				}
			if (list.size() != expressions.length) 
				{
				String string = "type checking error for " + this.idecl.toString();
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
					String string = "type checking error for " + this.idecl.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = typeRising.getErrorMessage();
					list2.add(errorMessage);
					return false;
					}
				TypeCompare typeCompare = new TypeCompare();
				if (!typeCompare.sameType(dataType, dataTypeEnum)) 
					{
					String string = "type checking error for " + this.idecl.toString();
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
