/**
 * 
 */
package specificheAEmilia;

/**
 * "ALL_OBSERVABLES" indica tutte le azioni 
 * di tipo OBS_INTERNALS e OBS_INTERACTIONS.
 * 
 * @author Mirko
 *
 */
public class RAll 
	extends ActionTypeSetR 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("RAll object");
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
		if (!(o instanceof RAll)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public RAll copy()
		{
		RAll all = new RAll();
		return all;
		}	
	
	/*
	 * 
	 * "ALL_OBSERVABLES";	
	 * 
	 */
	@Override
	public String toString() 
		{
		return "ALL_OBSERVABLES";
		}
	
	}
