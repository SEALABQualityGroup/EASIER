/**
 * 
 */
package restrizioniIstanze.dataTypes;

import specificheAEmilia.BooleanType;


/**
 * @author Mirko
 *
 */
public class BooleanTypeNorm 
	extends NormalTypeNorm 
	{

	private BooleanType oldBooleanType;
	private BooleanType newBooleanType;
	
	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		BooleanTypeNorm booleanTypeNorm = new BooleanTypeNorm();
		booleanTypeNorm.setOldBooleanType(this.oldBooleanType.copy());
		booleanTypeNorm.setNewBooleanType(this.newBooleanType.copy());
		return booleanTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("BooleanTypeNorm object");
		System.out.print("Old BooleanType: ");
		this.oldBooleanType.print();
		System.out.println("New BooleanType: ");
		this.newBooleanType.print();
		}	

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof BooleanTypeNorm))
			return false;
		BooleanTypeNorm booleanTypeNorm = (BooleanTypeNorm)obj;
		if (!this.oldBooleanType.equals(booleanTypeNorm.getOldBooleanType()))
			return false;
		if (!this.newBooleanType.equals(booleanTypeNorm.getNewBooleanType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New BooleanType: ";
		string += this.newBooleanType;
		string += " Old BooleanType: ";
		string += this.oldBooleanType + " ";
		return string;
		}

	public void setOldBooleanType(BooleanType dataType) 
		{
		this.oldBooleanType = dataType;
		}

	public void setNewBooleanType(BooleanType booleanType) 
		{
		this.newBooleanType = booleanType;
		}

	@Override
	public BooleanType getNewDataType() 
		{
		return this.newBooleanType;
		}

	public BooleanType getOldBooleanType() 
		{
		return this.oldBooleanType;
		}

	public BooleanType getNewBooleanType() 
		{
		return this.newBooleanType;
		}
	}
