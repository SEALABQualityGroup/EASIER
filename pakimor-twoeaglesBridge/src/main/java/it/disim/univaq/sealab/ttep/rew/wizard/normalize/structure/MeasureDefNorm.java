/**
 * 
 */
package it.disim.univaq.sealab.ttep.rew.wizard.normalize.structure;

import it.disim.univaq.sealab.ttep.rew.classes.MeasureDef;
import it.disim.univaq.sealab.ttep.rew.classes.RewBase;

/**
 * @author Jimmy
 *
 */
public class MeasureDefNorm 	
	implements RewBase 
	{

	private MeasureDef oldMeasureDef;
	private MeasureDef newMeasureDef;
	
	@Override
	public String toString() 
		{
		String string = new String();
		string += "New Measure Definition: ";
		string += this.newMeasureDef;
		string += " Old Measure Definition: ";
		string += this.oldMeasureDef + " ";
		return string;
		}

	/* (non-Javadoc)
	 * @see it.disim.univaq.sealab.ttep.rew.classes.RewBase#copy()
	 */
	@Override
	public MeasureDefNorm copy() 
		{
		MeasureDefNorm measureDefNorm = new MeasureDefNorm();
		measureDefNorm.setNewMeasureDef(this.newMeasureDef.copy());
		measureDefNorm.setOldMeasureDef(this.oldMeasureDef.copy());
		return measureDefNorm;
		}

	public MeasureDef getOldMeasureDef() 
		{
		return oldMeasureDef;
		}

	public void setOldMeasureDef(MeasureDef oldMeasureDef) 
		{
		this.oldMeasureDef = oldMeasureDef;
		}

	public MeasureDef getNewMeasureDef() 
		{
		return newMeasureDef;
		}

	public void setNewMeasureDef(MeasureDef newMeasureDef) 
		{
		this.newMeasureDef = newMeasureDef;
		}
	}
