/**
 * 
 */
package restrizioniIstanze.structure;

import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.ArchiType;

/**
 * @author Mirko
 *
 */
public class ArchiTypeNorm implements AEmiliaBase {

	private ArchiType oldArchiType;
	private ArchiType newArchiType;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		ArchiTypeNorm archiTypeNorm = new ArchiTypeNorm();
		archiTypeNorm.setOldArchiType(this.oldArchiType.copy());
		archiTypeNorm.setNewArchiType(this.newArchiType.copy());
		return archiTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("ArchiTypeNorm object");
		System.out.print("Old ArchiType: ");
		this.oldArchiType.print();
		System.out.println("New ArchiType: ");
		this.newArchiType.print();
		}

	@Override
	public boolean equals(Object arg0) 
		{
		if (!(arg0 instanceof ArchiTypeNorm))
			return false;
		ArchiTypeNorm archiTypeNorm = (ArchiTypeNorm)arg0;
		if (!this.oldArchiType.equals(archiTypeNorm.getOldArchiType()))
			return false;
		if (!this.newArchiType.equals(archiTypeNorm.getNewArchiType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New ArchiType: ";
		string += this.newArchiType;
		string += " Old ArchiType: ";
		string += this.oldArchiType + " ";
		return string;
		}

	public void setOldArchiType(ArchiType at) 
		{
		this.oldArchiType = at;
		}

	public void setNewArchiType(ArchiType clone) 
		{
		this.newArchiType = clone;
		}

	public ArchiType getNewArchiType() 
		{
		return this.newArchiType;
		}

	public ArchiType getOldArchiType() 
		{
		return this.oldArchiType;
		}
	
	
}
