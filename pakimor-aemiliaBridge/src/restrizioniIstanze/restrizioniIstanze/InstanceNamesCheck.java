/**
 * 
 */
package restrizioniIstanze.restrizioniIstanze;

import java.util.List;

import equivalenzaComportamentale.secondRelease.Equivalenza2;

import restrizioniIstanze.qnElemTypes.ElemTypeNorm;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIident;
import specificheAEmilia.ElemType;
import specificheAEmilia.Header;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class InstanceNamesCheck {

	private List<ElemTypeNorm> listElemTypeNorm;
	private ErrorMessage errorMessage;
	private List<Equivalenza2> listEquivalences;

	public InstanceNamesCheck(List<ElemTypeNorm> listElemTypeNorm, List<Equivalenza2> listEquivalences,int depth) 
		{
		super();
		this.listElemTypeNorm = listElemTypeNorm;
		this.listEquivalences = listEquivalences;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * I nomi delle istanze degli elementi architetturali e dei tipi di elementi architetturali devono essere distinti.
	 * 
	 * @return
	 */
	public boolean instanceNamesCheck() 
		{
		/* MODELED */
		for (int i = 0; i < this.listElemTypeNorm.size(); i++)
			{
			ElemTypeNorm elemTypeNorm = this.listElemTypeNorm.get(i);
			AEIdecl idecl = elemTypeNorm.getAEIdecl();
			AEIident aeIident = idecl.getAeIident();
			for (int j = i + 1; j < this.listElemTypeNorm.size(); j++)
				{
				ElemTypeNorm elemTypeNorm2 = this.listElemTypeNorm.get(j);
				AEIdecl idecl2 = elemTypeNorm2.getAEIdecl();
				AEIident aeIident2 = idecl2.getAeIident();
				if (aeIident.equals(aeIident2))
					{
					// 1
					String string3 = "declaration " + idecl.toString() + " and " + idecl2.toString() + " have same instance name";
					this.errorMessage.setErrorMessage(string3);
					return false;
					}
				}
			// ogni istanza deve avere nome differente dal nome di un tipo di elemento architetturale
			for (int j = i + 1; j < this.listEquivalences.size(); j++)
				{
				Equivalenza2 equivalenza2 = this.listEquivalences.get(j);
				ElemType elemType = equivalenza2.getEt();
				Header header = elemType.getHeader();
				String string = header.getName();
				AEIident aeIident2 = new AEIident(string);
				if (aeIident.equals(aeIident2))
					{
					// 2
					String string3 = "instance declaration " + idecl.toString() + " and architectural element type declaration" + 
							header.toString() + " have same name";
					this.errorMessage.setErrorMessage(string3);
					return false;
					}
				}
			}
		return true;
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
