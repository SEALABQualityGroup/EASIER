/**
 * 
 */
package restrizioniIstanze.structure;

import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.ParamDeclaration;

/**
 * @author Mirko
 *
 */
public class ParamDeclarationNorm implements AEmiliaBase {

	private ParamDeclaration oldParamDeclaration;
	private ParamDeclaration newParamDeclaration;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		ParamDeclarationNorm paramDeclarationNorm = new ParamDeclarationNorm();
		paramDeclarationNorm.setNewParamDeclaration(this.newParamDeclaration.copy());
		paramDeclarationNorm.setOldParamDeclaration(this.oldParamDeclaration.copy());
		return paramDeclarationNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("ParamDeclarationNorm object");
		System.out.print("Old ParamDeclaration: ");
		this.oldParamDeclaration.print();
		System.out.println("New ParamDeclaration: ");
		this.newParamDeclaration.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ParamDeclarationNorm))
			return false;
		ParamDeclarationNorm paramDeclarationNorm = (ParamDeclarationNorm)obj;
		if (!this.newParamDeclaration.equals(paramDeclarationNorm.getNewParamDeclaration()))
			return false;
		if (!this.oldParamDeclaration.equals(paramDeclarationNorm.getOldParamDeclaration()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New ParamDeclaration: ";
		string += this.newParamDeclaration;
		string += " Old ParamDeclaration: ";
		string += this.oldParamDeclaration + " ";
		return string;
		}

	public ParamDeclaration getNewParamDeclaration() 
		{
		return this.newParamDeclaration;
		}

	public void setOldParamDeclaration(ParamDeclaration dp) 
		{
		this.oldParamDeclaration = dp;
		}

	public void setNewParamDeclaration(ParamDeclaration paramDeclaration) 
		{
		this.newParamDeclaration = paramDeclaration;
		}

	public ParamDeclaration getOldParamDeclaration() 
		{
		return oldParamDeclaration;
		}
	}
