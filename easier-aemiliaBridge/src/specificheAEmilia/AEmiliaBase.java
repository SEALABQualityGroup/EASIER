/**
 * 
 */
package specificheAEmilia;

/**
 * Interfaccia base per tutte le classi della 
 * rappresentazione ad oggetti di una specifica AEmilia.
 * 
 * @author Mirko
 *
 */
public interface AEmiliaBase 
	{

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Object copy();

	/**
	 * Restituisce true se l'oggetto contiene le stesse informazioni
	 * contenute nel parametro del metodo.
	 * @param o - oggetto da confrontare.
	 * @return un booleano.
	 */
	public boolean equals(Object o);

	/**
	 * Stampa sullo standard output le informazioni relative
	 * all'oggetto.
	 */ 
	public void print();
	
	/**
	 * Restituisce la rappresentazione come stringa nel linguaggio AEmilia 
	 * di questo oggetto.
	 *  
	 * @return
	 */
	public String toString(); 
	
	}
