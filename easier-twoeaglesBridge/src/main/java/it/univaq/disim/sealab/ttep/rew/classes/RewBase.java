/**
 * 
 */
package it.univaq.disim.sealab.ttep.rew.classes;

/**
 * @author Mirko
 *
 */
public interface RewBase {

	/**
	 * Restituisce la rappresentazione come stringa nel linguaggio Rew 
	 * di questo oggetto.
	 *  
	 * @return
	 */
	public String toString(); 

	public Object copy();
	
	public boolean equals(Object object);
}
