/**
 * 
 */
package specificheAEmilia;

/**
 * "INTERACTIONS" indica tutte le interazioni
 * non architetturali.
 * 
 * @author Mirko
 *
 */
public class HInteractions 
	extends ActionTypeSetH 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("HInteractions object");
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
		if (!(o instanceof HInteractions)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public HInteractions copy()
		{
		HInteractions interactions = new HInteractions();
		return interactions;
		}	
	
	/*
	 * 
	 * "INTERACTIONS";	
	 * 
	 */
	@Override
	public String toString() 
		{
		return "INTERACTIONS";
		}
	
	}
