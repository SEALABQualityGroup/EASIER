/**
 * 
 */
package restrizioniIstanze.structure;

import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.Header;

/**
 * @author Mirko
 *
 */
public class HeaderNorm implements AEmiliaBase {

	private Header oldHeader;
	private Header newHeader;
	private AEIdecl idecl;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		HeaderNorm headerNorm = new HeaderNorm();
		headerNorm.setAEIdecl(this.idecl.copy());
		headerNorm.setNewHeader(this.newHeader.copy());
		headerNorm.setOldHeader(this.oldHeader.copy());
		return headerNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("HeaderNorm object");
		System.out.print("Old Header: ");
		this.oldHeader.print();
		System.out.println("New Header: ");
		this.newHeader.print();
		System.out.println("AEIdecl: ");
		this.idecl.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof HeaderNorm))
			return false;
		HeaderNorm headerNorm = (HeaderNorm)obj;
		if (!this.idecl.equals(headerNorm.getAEIdecl()))
			return false;
		if (!this.newHeader.equals(headerNorm.getNewHeader()))
			return false;
		if (!this.oldHeader.equals(headerNorm.getOldHeader()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New Header: ";
		string += this.newHeader;
		string += " Old Header: ";
		string += this.oldHeader + " ";
		string += " AEIdecl: ";
		string += this.idecl + " ";
		return string;
		}

	public void setOldHeader(Header i) 
		{
		this.oldHeader = i;
		}

	public void setNewHeader(Header clone) 
		{
		this.newHeader = clone;
		}

	public Header getNewHeader() 
		{
		return this.newHeader;
		}	

	public void setAEIdecl(AEIdecl aeid) 
		{
		this.idecl = aeid;
		}
	
	public AEIdecl getAEIdecl() 
		{
		return this.idecl;
		}

	public Header getOldHeader() 
		{
		return oldHeader;
		} 
	}
