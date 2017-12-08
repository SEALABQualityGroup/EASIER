/**
 * 
 */
package restrizioniIstanze.dataTypes;

import specificheAEmilia.ArrayType;


/**
 * @author Mirko
 *
 */
public class ArrayTypeNorm 
	extends NormalTypeNorm 
	{

	private ArrayType oldArrayType;
	private ArrayType newArrayType;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		ArrayTypeNorm arrayTypeNorm = new ArrayTypeNorm();
		arrayTypeNorm.setOldArrayType(this.oldArrayType.copy());
		arrayTypeNorm.setNewArrayType(this.newArrayType.copy());
		return arrayTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("ArrayTypeNorm object");
		System.out.print("Old ArrayType: ");
		this.oldArrayType.print();
		System.out.println("New ArrayType: ");
		this.newArrayType.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ArrayTypeNorm))
			return false;
		ArrayTypeNorm arrayTypeNorm = (ArrayTypeNorm)obj;
		if (!this.oldArrayType.equals(arrayTypeNorm.getOldArrayType()))
			return false;
		if (!this.newArrayType.equals(arrayTypeNorm.getNewArrayType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New ArrayType: ";
		string += this.newArrayType;
		string += " Old ArrayType: ";
		string += this.oldArrayType + " ";
		return string;
		}

	public void setOldArrayType(ArrayType arrayType) 
		{
		this.oldArrayType = arrayType;
		}

	public void setNewArrayType(ArrayType arrayType2) 
		{
		this.newArrayType = arrayType2;
		}

	public ArrayType getOldArrayType() 
		{
		return oldArrayType;
		}

	public ArrayType getNewArrayType() 
		{
		return newArrayType;
		}
	
	@Override
	public ArrayType getNewDataType() 
		{
		return this.newArrayType;
		}
	}
