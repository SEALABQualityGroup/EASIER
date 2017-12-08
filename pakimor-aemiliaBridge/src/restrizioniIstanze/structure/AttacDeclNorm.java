/**
 * 
 */
package restrizioniIstanze.structure;

import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.AttacDecl;

/**
 * @author Mirko
 *
 */
public class AttacDeclNorm 
	implements AEmiliaBase 
	{

	private AttacDecl oldAttacDecl;
	private AttacDecl newAttacDecl;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		AttacDeclNorm attacDeclNorm = new AttacDeclNorm();
		attacDeclNorm.setOldAttacDecl(this.oldAttacDecl.copy());
		attacDeclNorm.setNewAttacDecl(this.newAttacDecl.copy());
		return attacDeclNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("AttacDeclNorm object");
		System.out.print("Old AttacDecl: ");
		this.oldAttacDecl.print();
		System.out.println("New AttacDecl: ");
		this.newAttacDecl.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof AttacDeclNorm))
			return false;
		AttacDeclNorm attacDeclNorm = (AttacDeclNorm)obj;
		if (!this.oldAttacDecl.equals(attacDeclNorm.getOldAttacDecl()))
			return false;
		if (!this.newAttacDecl.equals(attacDeclNorm.getNewAttacDecl()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New AttacDecl: ";
		string += this.newAttacDecl;
		string += " Old AttacDecl: ";
		string += this.oldAttacDecl + " ";
		return string;
		}

	public void setOldAttacDecl(AttacDecl ad) 
		{
		this.oldAttacDecl = ad;
		}

	public void setNewAttacDecl(AttacDecl ris) 
		{
		this.newAttacDecl = ris;
		}

	public AttacDecl getNewAttacDecl() 
		{
		return this.newAttacDecl;
		}

	public AttacDecl getOldAttacDecl() 
		{
		return oldAttacDecl;
		}
	}
