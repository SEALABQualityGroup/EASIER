/**
 * 
 */
package restrizioniIstanze.dataTypes;

import specificheAEmilia.IntegerRangeType;


/**
 * @author Mirko
 *
 */
public class IntegerRangeTypeNorm 
	extends NormalTypeNorm 
	{

	private IntegerRangeType oldIntegerRangeType;
	private IntegerRangeType newIntegerRangeType;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		IntegerRangeTypeNorm integerRangeTypeNorm = new IntegerRangeTypeNorm();
		integerRangeTypeNorm.setNewIntegerRangeType(this.newIntegerRangeType.copy());
		integerRangeTypeNorm.setOldIntegerRangeType(this.oldIntegerRangeType.copy());
		return integerRangeTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("IntegerRangeTypeNorm object");
		System.out.print("Old IntegerRangeType: ");
		this.oldIntegerRangeType.print();
		System.out.println("New IntegerRangeType: ");
		this.newIntegerRangeType.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof IntegerRangeTypeNorm))
			return false;
		IntegerRangeTypeNorm integerRangeTypeNorm = (IntegerRangeTypeNorm)obj;
		if (!this.newIntegerRangeType.equals(integerRangeTypeNorm.getNewIntegerRangeType()))
			return false;
		if (!this.oldIntegerRangeType.equals(integerRangeTypeNorm.getOldIntegerRangeType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New IntegerRangeType: ";
		string += this.newIntegerRangeType;
		string += " Old IntegerRangeType: ";
		string += this.oldIntegerRangeType + " ";
		return string;
		}

	public void setOldIntegerRangeType(IntegerRangeType dataType) 
		{
		this.oldIntegerRangeType = dataType;
		}

	public void setNewIntegerRangeType(IntegerRangeType integerRangeType) 
		{
		this.newIntegerRangeType = integerRangeType;
		}

	@Override
	public IntegerRangeType getNewDataType() 
		{
		return this.newIntegerRangeType;
		}

	public IntegerRangeType getOldIntegerRangeType() 
		{
		return oldIntegerRangeType;
		}

	public IntegerRangeType getNewIntegerRangeType() 
		{
		return newIntegerRangeType;
		}
	}
