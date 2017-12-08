/**
 * 
 */
package restrizioniIstanze.dataTypes;

import specificheAEmilia.IntegerType;


/**
 * @author Mirko
 *
 */
public class IntegerTypeNorm 
	extends NormalTypeNorm 
	{

	private IntegerType oldIntegerType;
	private IntegerType newIntegerType;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		IntegerTypeNorm integerTypeNorm = new IntegerTypeNorm();
		integerTypeNorm.setNewIntegerType(this.newIntegerType.copy());
		integerTypeNorm.setOldIntegerType(this.oldIntegerType.copy());
		return integerTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("IntegerTypeNorm object");
		System.out.print("Old IntegerType: ");
		this.oldIntegerType.print();
		System.out.println("New IntegerType: ");
		this.newIntegerType.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof IntegerTypeNorm))
			return false;
		IntegerTypeNorm integerTypeNorm = (IntegerTypeNorm)obj;
		if (!this.newIntegerType.equals(integerTypeNorm.getNewIntegerType()))
			return false;
		if (!this.oldIntegerType.equals(integerTypeNorm.getOldIntegerType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New IntegerType: ";
		string += this.newIntegerType;
		string += " Old IntegerType: ";
		string += this.oldIntegerType + " ";
		return string;
		}

	public void setOldIntegerType(IntegerType dataType) 
		{
		this.oldIntegerType = dataType;
		}

	public void setNewIntegerType(IntegerType integerType) 
		{
		this.newIntegerType = integerType;
		}

	@Override
	public IntegerType getNewDataType() 
		{
		return this.newIntegerType;
		}

	public IntegerType getOldIntegerType() 
		{
		return oldIntegerType;
		}

	public IntegerType getNewIntegerType() 
		{
		return newIntegerType;
		}
	}
