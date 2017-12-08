/**
 * 
 */
package specificheAEmilia;

/**
 * "OBS_INTERACTIONS"
 * 
 * indica tutte le interazioni 
 * non architetturali e osservabili.
 * 
 * @author Mirko
 *
 */
public class RInteractions 
	extends ActionTypeSetR 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("RInteractions object");
		super.print();
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param o - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof RInteractions)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public RInteractions copy()
		{
		RInteractions interactions = new RInteractions();
		return interactions;
		}	
	
	/*
	 * 
	 * "OBS_INTERACTIONS"	
	 * 
	 */
	@Override
	public String toString() 
		{
		return "OBS_INTERACTIONS";
		}
	
	}
