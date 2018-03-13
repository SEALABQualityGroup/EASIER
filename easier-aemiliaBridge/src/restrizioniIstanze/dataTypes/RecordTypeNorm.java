/**
 * 
 */
package restrizioniIstanze.dataTypes;

import specificheAEmilia.NormalType;
import specificheAEmilia.RecordType;


/**
 * @author Mirko
 *
 */
public class RecordTypeNorm 
	extends NormalTypeNorm 
	{

	private RecordType oldRecordType;
	private RecordType newRecordType;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public RecordTypeNorm copy() 
		{
		RecordTypeNorm recordTypeNorm = new RecordTypeNorm();
		recordTypeNorm.setNewRecordType(this.newRecordType.copy());
		recordTypeNorm.setOldRecordType(this.oldRecordType.copy());
		return recordTypeNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("RecordTypeNorm object");
		System.out.print("Old RecordType: ");
		this.oldRecordType.print();
		System.out.println("New RecordType: ");
		this.newRecordType.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof RecordTypeNorm))
			return false;
		RecordTypeNorm recordTypeNorm = (RecordTypeNorm)obj;
		if (!this.newRecordType.equals(recordTypeNorm.getNewRecordType()))
			return false;
		if (!this.oldRecordType.equals(recordTypeNorm.getOldRecordType()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New RecordType: ";
		string += this.newRecordType;
		string += " Old RecordType: ";
		string += this.oldRecordType + " ";
		return string;
		}

	public void setOldRecordType(RecordType dataType) 
		{
		this.oldRecordType = dataType;
		}

	public void setNewRecordType(RecordType recordType) 
		{
		this.newRecordType = recordType;
		}

	@Override
	public NormalType getNewDataType() 
		{
		return this.newRecordType;
		}

	public RecordType getOldRecordType() 
		{
		return this.oldRecordType;
		}

	public RecordType getNewRecordType() 
		{
		return this.newRecordType;
		}
	}
