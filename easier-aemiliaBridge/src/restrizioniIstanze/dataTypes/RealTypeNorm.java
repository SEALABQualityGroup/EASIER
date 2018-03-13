/**
 * 
 */
package restrizioniIstanze.dataTypes;

import specificheAEmilia.NormalType;
import specificheAEmilia.RealType;


/**
 * @author Mirko
 *
 */
public class RealTypeNorm extends NormalTypeNorm {

	private RealType oldRealType;
	private RealType newRealType;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		RealTypeNorm realTypeNorm = new RealTypeNorm();
		realTypeNorm.setNewRealType(this.newRealType.copy());
		realTypeNorm.setOldRealType(this.oldRealType.copy());
		return realTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("RealTypeNorm object");
		System.out.print("Old RealType: ");
		this.oldRealType.print();
		System.out.println("New RealType: ");
		this.newRealType.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof RealTypeNorm))
			return false;
		RealTypeNorm realTypeNorm = (RealTypeNorm)obj;
		if (!this.newRealType.equals(realTypeNorm.getNewRealType()))
			return false;
		if (!this.oldRealType.equals(realTypeNorm.getOldRealType()))
			return false;
		return true;
		}

	public void setOldRealType(RealType dataType) 
		{
		this.oldRealType = dataType;
		}

	public void setNewRealType(RealType realType) 
		{
		this.newRealType = realType;
		}

	@Override
	public NormalType getNewDataType() 
		{
		return this.newRealType;
		}

	public RealType getOldRealType() 
		{
		return this.oldRealType;
		}
	
	public RealType getNewRealType() 
		{
		return newRealType;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New RealType: ";
		string += this.newRealType;
		string += " Old RealType: ";
		string += this.oldRealType + " ";
		return string;
		}
	
	
	}
