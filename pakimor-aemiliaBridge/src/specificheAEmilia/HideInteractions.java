/**
 * 
 */
package specificheAEmilia;

/**
 * Dichiarazione di occultamento comportamentale, consistente nel rendere 
 * inosservabili tutte le interazioni
 * non architetturali degli AEI della specifica AEmilia.
 * 
 * @author Mirko
 *
 */
public class HideInteractions 
	extends BehavHidingDecl 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("HideInteractions object");
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
		if (!(o instanceof HideInteractions)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public HideInteractions copy()
		{
		HideInteractions hideInteractions = new HideInteractions();
		return hideInteractions;
		}	
	
	/*
	 * 
	 * "HIDE INTERACTIONS";	
	 * 
	 */
	@Override
	public String toString() 
		{
		return "HIDE INTERACTIONS";
		}
	}
