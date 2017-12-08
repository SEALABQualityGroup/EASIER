/**
 * 
 */
package restrizioniIstanze.dataTypes;

import specificheAEmilia.DataType;
import specificheAEmilia.RateType;


/**
 * @author Mirko
 *
 */
public class RateTypeNorm extends SpecialTypeNorm {

	private RateType oldRateType;
	private RateType newRateType;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		RateTypeNorm rateTypeNorm = new RateTypeNorm();
		rateTypeNorm.setNewRateType(this.newRateType.copy());
		rateTypeNorm.setOldRateType(this.oldRateType.copy());
		return rateTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("RateTypeNorm object");
		System.out.print("Old RateType: ");
		this.oldRateType.print();
		System.out.println("New RateType: ");
		this.newRateType.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof RateTypeNorm))
			return false;
		RateTypeNorm rateTypeNorm = (RateTypeNorm)obj;
		if (!this.newRateType.equals(rateTypeNorm.getNewRateType()))
			return false;
		if (!this.oldRateType.equals(rateTypeNorm.getOldRateType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New RateType: ";
		string += this.newRateType;
		string += " Old RateType: ";
		string += this.oldRateType + " ";
		return string;
		}

	public void setOldRateType(RateType dataType) 
		{
		this.oldRateType = dataType;
		}

	public void setNewRateType(RateType rateType) 
		{
		this.newRateType = rateType;
		}

	public RateType getOldRateType() 
		{
		return oldRateType;
		}

	public RateType getNewRateType() 
		{
		return newRateType;
		}

	@Override
	public DataType getNewDataType() 
		{
		return this.newRateType;
		}
	}
