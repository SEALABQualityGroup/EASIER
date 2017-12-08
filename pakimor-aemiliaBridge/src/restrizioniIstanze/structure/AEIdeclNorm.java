/**
 * 
 */
package restrizioniIstanze.structure;

import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEmiliaBase;

/**
 * @author Mirko
 *
 */
public class AEIdeclNorm implements AEmiliaBase {

	private AEIdecl oldAEIdecl;
	private AEIdecl newAEIdecl;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		AEIdeclNorm ideclNorm = new AEIdeclNorm();
		ideclNorm.setNewAEIdecl(this.newAEIdecl.copy());
		ideclNorm.setOldAEIdecl(this.oldAEIdecl.copy());
		return ideclNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("AEIdeclNorm object");
		System.out.print("Old AEIdecl: ");
		this.oldAEIdecl.print();
		System.out.println("New AEIdecl: ");
		this.newAEIdecl.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof AEIdeclNorm))
			return false;
		AEIdeclNorm ideclNorm = (AEIdeclNorm)obj;
		if (!this.oldAEIdecl.equals(ideclNorm.getOldAEIdecl()))
			return false;
		if (!this.newAEIdecl.equals(ideclNorm.getNewAEIdecl()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New AEIdecl: ";
		string += this.newAEIdecl;
		string += " Old AEIdecl: ";
		string += this.oldAEIdecl + " ";
		return string;
		}

	public void setOldAEIdecl(AEIdecl aeid) 
		{
		this.oldAEIdecl = aeid;
		}

	public void setNewAEIdecl(AEIdecl clone) 
		{
		this.newAEIdecl = clone;
		}

	public AEIdecl getNewAEIdecl() 
		{
		return this.newAEIdecl;
		}

	public AEIdecl getOldAEIdecl() 
		{
		return oldAEIdecl;
		}
	}
