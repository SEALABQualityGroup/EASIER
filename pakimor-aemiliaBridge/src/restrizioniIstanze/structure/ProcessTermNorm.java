/**
 * 
 */
package restrizioniIstanze.structure;

import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ProcessTerm;

/**
 * @author Mirko
 *
 */
public class ProcessTermNorm 
	implements AEmiliaBase 
	{
	
	private ProcessTerm oldProcessTerm;
	private ProcessTerm newProcessTerm;
	private AEIdecl aEIdecl;
	private BehavEquation behavEquation;

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() 
		{
		ProcessTermNorm processTermNorm = new ProcessTermNorm();
		processTermNorm.setAEIdecl(this.aEIdecl.copy());
		processTermNorm.setBehavEquation(this.behavEquation.copy());
		processTermNorm.setNewProcessTerm(this.newProcessTerm.copy());
		processTermNorm.setOldProcessTerm(this.oldProcessTerm.copy());
		return processTermNorm;
		}

	/* (non-Javadoc)
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() 
		{
		System.out.println("ProcessTermNorm object");
		System.out.print("Old ProcessTerm: ");
		this.oldProcessTerm.print();
		System.out.println("New ProcessTerm: ");
		this.newProcessTerm.print();
		System.out.print("AEIdecl: ");
		this.aEIdecl.print();
		System.out.println("BehavEquation: ");
		this.behavEquation.print();
		}

	public ProcessTerm getNewProcessTerm() 
		{
		return this.newProcessTerm;
		}

	public void setOldProcessTerm(ProcessTerm processTerm) 
		{
		this.oldProcessTerm = processTerm;
		}

	public void setNewProcessTerm(ProcessTerm processTerm) 
		{
		this.newProcessTerm = processTerm;
		}

	public void setBehavEquation(BehavEquation be) 
		{
		this.behavEquation = be;
		}

	public void setAEIdecl(AEIdecl aeid) 
		{
		this.aEIdecl = aeid;
		}

	public ProcessTerm getOldProcessTerm() 
		{
		return oldProcessTerm;
		}

	public BehavEquation getBehavEquation() 
		{
		return behavEquation;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof ProcessTermNorm))
			return false;
		ProcessTermNorm processTermNorm = (ProcessTermNorm)obj;
		if (!this.behavEquation.equals(processTermNorm.getBehavEquation()))
			return false;
		if (!this.aEIdecl.equals(processTermNorm.getAEIdecl()))
			return false;
		if (!this.newProcessTerm.equals(processTermNorm.getNewProcessTerm()))
			return false;
		if (!this.oldProcessTerm.equals(processTermNorm.getOldProcessTerm()))
			return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New ProcessTerm: ";
		string += this.newProcessTerm;
		string += " Old ProcessTerm: ";
		string += this.oldProcessTerm + " ";
		string += "AEIdecl: ";
		string += this.aEIdecl;
		string += " BehavEquation: ";
		string += this.behavEquation + " ";
		return string;
		}

	public AEIdecl getAEIdecl() 
		{
		return aEIdecl;
		}
	}
