/**
 * 
 */
package specificheAEmilia;

/**
 * Dichiarazione di occultamento comportamentale in cui 
 * si rende inosservabili tutti i tipi azione che sono interni
 * agli AEI della specifica AEmilia e tutte le interazioni
 * non architetturali degli AEI della specifica AEmilia.
 * 
 * @author Mirko
 *
 */
public class HideAll 
	extends BehavHidingDecl 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("HideAll object");
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
		if (!(o instanceof HideAll)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public HideAll copy()
		{
		HideAll hideAll = new HideAll();
		return hideAll;
		}	
	
	/*
	 * return "HIDE ALL";
	 */
	@Override
	public String toString() 
		{
		return "HIDE ALL";
		}
	}
