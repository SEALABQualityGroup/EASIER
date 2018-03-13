/**
 * 
 */
package restrizioniIstanze.structure;

import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.InteractionDecl;

/**
 * @author Mirko
 *
 */
public class InteractionDeclNorm implements AEmiliaBase {

	private InteractionDecl newInteractionDecl;
	private InteractionDecl oldInteractionDecl;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		InteractionDeclNorm interactionDeclNorm = new InteractionDeclNorm();
		interactionDeclNorm.setNewInteractionDecl(this.newInteractionDecl.copy());
		interactionDeclNorm.setOldInteractionDecl(this.oldInteractionDecl.copy());
		return interactionDeclNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("InteractionDeclNorm object");
		System.out.print("Old InteractionDecl: ");
		this.oldInteractionDecl.print();
		System.out.println("New InteractionDecl: ");
		this.newInteractionDecl.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof InteractionDeclNorm))
			return false;
		InteractionDeclNorm interactionDeclNorm = (InteractionDeclNorm)obj;
		if (!this.newInteractionDecl.equals(interactionDeclNorm.getNewInteractionDecl()))
			return false;
		if (!this.oldInteractionDecl.equals(interactionDeclNorm.getOldInteractionDecl()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New InteractionDecl: ";
		string += this.newInteractionDecl;
		string += " Old InteractionDecl: ";
		string += this.oldInteractionDecl + " ";
		return string;
		}

	public void setOldInteractionDecl(InteractionDecl ad) 
		{
		this.oldInteractionDecl = ad;
		}

	public void setNewInteractionDecl(InteractionDecl ris) 
		{
		this.newInteractionDecl = ris;
		}

	public InteractionDecl getNewInteractionDecl() 
		{
		return this.newInteractionDecl;
		}

	public InteractionDecl getOldInteractionDecl() 
		{
		return oldInteractionDecl;
		}
	}
