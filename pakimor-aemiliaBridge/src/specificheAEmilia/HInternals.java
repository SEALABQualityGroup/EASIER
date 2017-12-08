/**
 * 
 */
package specificheAEmilia;

/**
 * "INTERNALS" indica tutte le azioni interne.
 * 
 * @author Mirko
 *
 */
public class HInternals 
	extends ActionTypeSetH 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("HInternals object");
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
		if (!(o instanceof HInternals)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public HInternals copy()
		{
		HInternals internals = new HInternals();
		return internals;
		}	
	
	/*
	 * 
	 * "INTERNALS";	
	 * 
	 */
	@Override
	public String toString() 
		{
		return "INTERNALS";
		}
	
	}
