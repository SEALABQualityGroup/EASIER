/**
 * 
 */
package it.disim.univaq.sealab.ttep.val.classes;

import java.util.List;

/**
 * @author Mirko
 *
 */
public class ValSpec 
	{

	private List<MeasureValue> measures;

	public ValSpec(List<MeasureValue> measures) 
		{
		super();
		this.measures = measures;
		}


	public List<MeasureValue> getMeasures() 
		{
		return measures;
		}
	}
