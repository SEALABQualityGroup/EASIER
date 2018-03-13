/**
 * 
 */
package restrizioniIstanze.comportamenti;

import java.util.ArrayList;
import java.util.List;

import specificheAEmilia.Expression;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Integer;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.Sottrazione;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ParametersRelation {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ParametersRelation(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se es1 corrisponde alle dichiarazioni di dps, tranne che per
	 * lo z-esimo elemento.
	 * 
	 * @param dps
	 * @param es1
	 * @param z
	 */
	protected boolean parametersRelation(ParamDeclaration[] dps, Expression[] es1, int z) 
		{
		boolean b = true;
		for (int k = 0; k < es1.length; k++)
			{
			if (k != z)
				{
				// si costruisce un array che contiene gli identificatori
				// espressione dei parametri
				List<IdentExpr> list = new ArrayList<IdentExpr>();
				for (int l = 0; l < dps.length; l++)
					{
					list.add(new IdentExpr(dps[l].getName()));
					}
				// si verifica che es1[k] sia contenuto in list
				if (!list.contains(es1[k]))
					{
					String string = "relating error for " + dps + " and " + es1;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list2 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string2 = list + " must contains " + es1[k].toString();
					errorMessage.setErrorMessage(string2);
					list2.add(errorMessage);
					b = false;
					}
				}
			else
				{
				// si costruisce un array che contiene gli identificatori
				// espressione dei parametri meno uno e l'array che contiene uno
				// meno gli identificatori espressione dei parametri
				List<Sottrazione> list = new ArrayList<Sottrazione>();
				List<Sottrazione> list2 = new ArrayList<Sottrazione>();
				for (int l = 0; l < dps.length; l++)
					{
					Integer integer = new Integer(1);
					IdentExpr identExpr = new IdentExpr(dps[l].getName());
					Sottrazione sottrazione = new Sottrazione(integer,identExpr);
					Sottrazione sottrazione2 = new Sottrazione(identExpr,integer);
					list.add(sottrazione);
					list2.add(sottrazione2);
					}
				// si verifica che es1[k] sia contenuto in list
				if (!list.contains(es1[k]) && !list2.contains(es1[k]))
					{
					String string = "relating error for " + dps + " and " + es1;
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list3 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					String string2 = list + " or " + list2 + " must contains " + es1[k].toString();
					errorMessage.setErrorMessage(string2);
					list3.add(errorMessage);
					b = false;
					}
				}
			}
		if (!b)
			return false;
		return true;
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	
	
}
