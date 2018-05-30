/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirko
 *
 */
public class RewSpec implements RewBase {

	private List<MeasureDef> measureDefs;

	public RewSpec(List<MeasureDef> measureDefs) {
		super();
		this.measureDefs = measureDefs;
	}

	public List<MeasureDef> getMeasureDefs() {
		return measureDefs;
	}

	@Override
	public String toString() 
		{
		String string = new String();
		for (MeasureDef measureDef : this.measureDefs)
			{
			string = string + measureDef.toString();
			}
		return string;
		}

	@Override
	public RewSpec copy() 
		{
		List<MeasureDef> measureDefs = new ArrayList<MeasureDef>();
		for (MeasureDef measureDef : this.measureDefs)
			{
			MeasureDef measureDef2 = measureDef.copy();
			measureDefs.add(measureDef2);
			}
		RewSpec rewSpec = new RewSpec(measureDefs);
		return rewSpec;
		}
	}
