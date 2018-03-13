/**
 * 
 */
package restrizioniIstanze.dataTypes;

import specificheAEmilia.DataType;
import specificheAEmilia.PrioType;


/**
 * @author Mirko
 *
 */
public class PrioTypeNorm 
	extends SpecialTypeNorm 
	{

	private PrioType oldPrioType;
	private PrioType newPrioType;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		PrioTypeNorm prioTypeNorm = new PrioTypeNorm();
		prioTypeNorm.setNewPrioType(this.newPrioType.copy());
		prioTypeNorm.setOldPrioType(this.oldPrioType.copy());
		return prioTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("PrioTypeNorm object");
		System.out.print("Old PrioType: ");
		this.oldPrioType.print();
		System.out.println("New PrioType: ");
		this.newPrioType.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof PrioTypeNorm))
			return false;
		PrioTypeNorm prioTypeNorm = (PrioTypeNorm)obj;
		if (!this.newPrioType.equals(prioTypeNorm.getNewPrioType()))
			return false;
		if (!this.oldPrioType.equals(prioTypeNorm.getOldPrioType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New PrioType: ";
		string += this.newPrioType;
		string += " Old PrioType: ";
		string += this.oldPrioType + " ";
		return string;
		}

	public void setOldPrioType(PrioType dataType) 
		{
		this.oldPrioType = dataType;
		}

	public void setNewPrioType(PrioType prioType) 
		{
		this.newPrioType = prioType;
		}

	public PrioType getOldPrioType() 
		{
		return oldPrioType;
		}

	public PrioType getNewPrioType() 
		{
		return newPrioType;
		}

	@Override
	public DataType getNewDataType() 
		{
		return this.newPrioType;
		}
	}
