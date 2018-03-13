/**
 * 
 */
package specificheAEmilia;

/**
 * "OBS_INTERNALS"
 * 
 * indica tutte le azioni interne e osservabili.
 * 
 * @author Mirko
 *
 */
public class RInternals 
	extends ActionTypeSetR 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("RInternals object");
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
		if (!(o instanceof RInternals)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public RInternals copy()
		{
		RInternals internals = new RInternals();
		return internals;
		}	
	
	/*
	 * 
	 * "OBS_INTERNALS"	
	 * 
	 */
	@Override
	public String toString() 
		{
		return "OBS_INTERNALS";
		}
	
	}
