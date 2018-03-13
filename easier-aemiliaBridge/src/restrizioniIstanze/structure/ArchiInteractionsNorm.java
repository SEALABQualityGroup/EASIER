/**
 * 
 */
package restrizioniIstanze.structure;

import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.ArchiInteractions;

/**
 * @author Mirko
 *
 */
public class ArchiInteractionsNorm implements AEmiliaBase {

	private ArchiInteractions oldArchiInteractions;
	private ArchiInteractions newArchiInteractions;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		ArchiInteractionsNorm archiInteractionsNorm = new ArchiInteractionsNorm();
		archiInteractionsNorm.setOldArchiInteractions(this.oldArchiInteractions.copy());
		archiInteractionsNorm.setNewArchiInteractions(this.newArchiInteractions.copy());
		return archiInteractionsNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("ArchiInteractionsNorm object");
		System.out.print("Old ArchiInteractions: ");
		this.oldArchiInteractions.print();
		System.out.println("New ArchiInteractions: ");
		this.newArchiInteractions.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ArchiInteractionsNorm))
			return false;
		ArchiInteractionsNorm archiInteractionsNorm = (ArchiInteractionsNorm)obj;
		if (!this.oldArchiInteractions.equals(archiInteractionsNorm.getOldArchiInteractions()))
			return false;
		if (!this.newArchiInteractions.equals(archiInteractionsNorm.getNewArchiInteractions()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New ArchiInteractions: ";
		string += this.newArchiInteractions;
		string += " Old ArchiInteractions: ";
		string += this.oldArchiInteractions + " ";
		return string;
		}

	public ArchiInteractions getNewArchiInteractions() 
		{
		return this.newArchiInteractions;
		}

	public void setOldArchiInteractions(ArchiInteractions ai) 
		{
		this.oldArchiInteractions = ai;
		}

	public void setNewArchiInteractions(ArchiInteractions clone) 
		{
		this.newArchiInteractions = clone;
		}

	public ArchiInteractions getOldArchiInteractions() 
		{
		return oldArchiInteractions;
		}
	}
