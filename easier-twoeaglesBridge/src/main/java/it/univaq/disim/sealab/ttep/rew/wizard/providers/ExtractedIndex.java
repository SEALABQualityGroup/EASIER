/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.wizard.providers;

/**
 * @author Mirko
 *
 */
public class ExtractedIndex 
	{
	private String aei;
	private String action;
	private String index;
	
	
	public ExtractedIndex(String aei,
			String action, String indice) {
		super();
		this.aei = aei;
		this.action = action;
		this.index = indice;
	}
	
	public String getAei() {
		return aei;
	}
	
	public String getAction() {
		return action;
	}

	public String getIndex() {
		return index;
	}
	}
