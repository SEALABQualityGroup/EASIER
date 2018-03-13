/**
 * 
 */
package restrizioniIstanze.structure;

import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.ArchiAttachments;

/**
 * @author Mirko
 *
 */
public class ArchiAttachmentsNorm implements AEmiliaBase {

	private ArchiAttachments newArchiAttachments;
	private ArchiAttachments oldArchiAttachments;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		ArchiAttachmentsNorm archiAttachmentsNorm = new ArchiAttachmentsNorm();
		archiAttachmentsNorm.setOldArchiAttachments(this.oldArchiAttachments.copy());
		archiAttachmentsNorm.setNewArchiAttachments(this.newArchiAttachments.copy());
		return archiAttachmentsNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("ArchiAttachmentsNorm object");
		System.out.print("Old ArchiAttachments: ");
		this.oldArchiAttachments.print();
		System.out.println("New ArchiAttachments: ");
		this.newArchiAttachments.print();
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ArchiAttachmentsNorm))
			return false;
		ArchiAttachmentsNorm archiAttachmentsNorm = (ArchiAttachmentsNorm)obj;
		if (!this.oldArchiAttachments.equals(archiAttachmentsNorm.getOldArchiAttachments()))
			return false;
		if (!this.newArchiAttachments.equals(archiAttachmentsNorm.getNewArchiAttachments()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New ArchiAttachments: ";
		string += this.newArchiAttachments;
		string += " Old ArchiAttachments: ";
		string += this.oldArchiAttachments + " ";
		return string;
		}

	public ArchiAttachments getNewArchiAttachments() 
		{
		return this.newArchiAttachments;
		}

	public void setOldArchiAttachments(ArchiAttachments aa) 
		{
		this.oldArchiAttachments = aa;
		}

	public void setNewArchiAttachments(ArchiAttachments clone) 
		{
		this.newArchiAttachments = clone;
		}

	public ArchiAttachments getOldArchiAttachments() 
		{
		return oldArchiAttachments;
		}
	}
