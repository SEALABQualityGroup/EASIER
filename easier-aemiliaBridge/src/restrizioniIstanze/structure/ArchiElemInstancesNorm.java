/**
 * 
 */
package restrizioniIstanze.structure;

import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.ArchiElemInstances;

/**
 * @author Mirko
 *
 */
public class ArchiElemInstancesNorm 
	implements AEmiliaBase 
	{

	private ArchiElemInstances oldArchiElemInstances;
	private ArchiElemInstances newArchiElemInstances;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		ArchiElemInstancesNorm archiElemInstancesNorm = new ArchiElemInstancesNorm();
		archiElemInstancesNorm.setOldArchiElemInstances(this.oldArchiElemInstances.copy());
		archiElemInstancesNorm.setNewArchiElemInstances(this.newArchiElemInstances.copy());
		return archiElemInstancesNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("ArchiElemInstancesNorm object");
		System.out.print("Old ArchiElemInstances: ");
		this.oldArchiElemInstances.print();
		System.out.println("New ArchiElemInstances: ");
		this.newArchiElemInstances.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ArchiElemInstancesNorm))
			return false;
		ArchiElemInstancesNorm archiElemInstancesNorm = (ArchiElemInstancesNorm)obj;
		if (!this.oldArchiElemInstances.equals(archiElemInstancesNorm.getOldArchiElemInstances()))
			return false;
		if (!this.newArchiElemInstances.equals(archiElemInstancesNorm.getNewArchiElemInstances()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New ArchiElemInstances: ";
		string += this.newArchiElemInstances;
		string += " Old ArchiElemInstances: ";
		string += this.oldArchiElemInstances + " ";
		return string;
		}

	public ArchiElemInstances getNewArchiElemInstances() 
		{
		return this.newArchiElemInstances;
		}

	public void setOldArchiElemInstances(ArchiElemInstances aei) 
		{
		this.oldArchiElemInstances = aei;
		}

	public void setNewArchiElemInstances(ArchiElemInstances clone) 
		{
		this.newArchiElemInstances = clone;
		}

	public ArchiElemInstances getOldArchiElemInstances() 
		{
		return oldArchiElemInstances;
		}
	}
