/**
 * 
 */
package specificheAEmilia;

/**
 * "ALL" indica sia le azioni interne, che
 * le interazioni non architetturali.
 * 
 * @author Mirko
 *
 */
public class HAll 
	extends ActionTypeSetH 
	{
	
	/**
	 * Stampa sullo standard output le informazioni
	 * relative a questo oggetto.
	 */
	public void print()
		{
		System.out.println("HAll object");
		super.print();
		}

	/**
	 * Restituisce true se e solo se questo oggettoe'uguale
	 * a p.
	 * @param o - oggetto.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof HAll)) return false;
		HAll all = (HAll)o;
		boolean ris = super.equals(all);
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public HAll copy()
		{
		HAll all = new HAll();
		return all;
		}

	/*
	 * "ALL"	 
	 */
	@Override
	public String toString() 
		{
		return "ALL";
		}
	
	}
