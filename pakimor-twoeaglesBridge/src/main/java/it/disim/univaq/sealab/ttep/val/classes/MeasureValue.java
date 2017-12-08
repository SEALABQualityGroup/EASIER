/**
 * 
 */
package it.disim.univaq.sealab.ttep.val.classes;

import specificheAEmilia.Expression;

/**
 * @author Mirko
 *
 */
public class MeasureValue {

	private String measure;
	private Expression selector;
	private Float value;

	public MeasureValue(String measure, Expression selector, Float value) {
		super();
		this.measure = measure;
		this.selector = selector;
		this.value = value;
	}

	public MeasureValue(String measure, Float value) {
		super();
		this.measure = measure;
		this.value = value;
	}

	public String getMeasure() {
		return measure;
	}

	public Expression getSelector() {
		return selector;
	}

	public Float getValue() {
		return value;
	}
}
