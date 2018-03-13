/**
 * 
 */
package restrizioniIstanze.dataTypes;

import specificheAEmilia.ListType;


/**
 * @author Mirko
 *
 */
public class ListTypeNorm 
	extends NormalTypeNorm 
	{

	private ListType oldListType;
	private ListType newListType;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		ListTypeNorm listTypeNorm = new ListTypeNorm();
		listTypeNorm.setNewListType(this.newListType.copy());
		listTypeNorm.setOldListType(this.oldListType.copy());
		return listTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("ListTypeNorm object");
		System.out.print("Old ListType: ");
		this.oldListType.print();
		System.out.println("New ListType: ");
		this.newListType.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ListTypeNorm))
			return false;
		ListTypeNorm listTypeNorm = (ListTypeNorm)obj;
		if (!this.newListType.equals(listTypeNorm.getNewListType()))
			return false;
		if (!this.oldListType.equals(listTypeNorm.getOldListType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New ListType: ";
		string += this.newListType;
		string += " Old ListType: ";
		string += this.oldListType + " ";
		return string;
		}

	public void setOldListType(ListType dataType) 
		{
		this.oldListType = dataType;
		}

	public void setNewListType(ListType listType) 
		{
		this.newListType = listType;
		}

	@Override
	public ListType getNewDataType() 
		{
		return this.newListType;
		}

	public ListType getOldListType() 
		{
		return oldListType;
		}

	public ListType getNewListType() 
		{
		return newListType;
		}
	}
