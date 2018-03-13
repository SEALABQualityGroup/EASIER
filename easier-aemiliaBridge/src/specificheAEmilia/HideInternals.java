/**
 * 
 */
package specificheAEmilia;

/**
 * Dichiarazione di occultamento comportamentale, consistente 
 * nel rendere inosservabili tutti i tipi azione
 * che sono interni agli AEI della specifica AEmilia.
 * 
 * @author Mirko
 *
 */
public class HideInternals 
	extends BehavHidingDecl 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("HideInternals object");
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
		if (!(o instanceof HideInternals)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public HideInternals copy()
		{
		HideInternals hideInternals = new HideInternals();
		return hideInternals;
		}	
	
	/*
	 * 
	 * "HIDE INTERACTIONS";	
	 * 
	 */
	@Override
	public String toString() 
		{
		return "HIDE INTERNALS";
		}	
	}
