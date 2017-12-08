/**
 * 
 */
package restrizioniIstanze.dataTypes;

import specificheAEmilia.DataType;
import specificheAEmilia.WeightType;


/**
 * @author Mirko
 *
 */
public class WeightTypeNorm extends SpecialTypeNorm {

	private WeightType oldWeightType;
	private WeightType newWeightType;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		WeightTypeNorm weightTypeNorm = new WeightTypeNorm();
		weightTypeNorm.setNewWeightType(this.newWeightType.copy());
		weightTypeNorm.setOldWeightType(this.oldWeightType.copy());
		return weightTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("WeightTypeNorm object");
		System.out.print("Old WeightType: ");
		this.oldWeightType.print();
		System.out.println("New WeightType: ");
		this.newWeightType.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof WeightTypeNorm))
			return false;
		WeightTypeNorm weightTypeNorm = (WeightTypeNorm)obj;
		if (!this.newWeightType.equals(weightTypeNorm.getNewWeightType()))
			return false;
		if (!this.oldWeightType.equals(weightTypeNorm.getOldWeightType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New WeightType: ";
		string += this.newWeightType;
		string += " Old WeightType: ";
		string += this.oldWeightType + " ";
		return string;
		}

	public void setOldWeightType(WeightType dataType) 
		{
		this.oldWeightType = dataType;
		}

	public void setNewWeightType(WeightType weightType) 
		{
		this.newWeightType = weightType;
		}

	@Override
	public DataType getNewDataType() 
		{
		return this.newWeightType;
		}

	public WeightType getOldWeightType() 
		{
		return this.oldWeightType;
		}
	
	public WeightType getNewWeightType() 
		{
		return this.newWeightType;
		}
	}
