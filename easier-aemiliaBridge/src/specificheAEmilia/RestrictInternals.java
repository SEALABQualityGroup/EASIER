/**
 * 
 */
package specificheAEmilia;

/**
 * "RESTRICT" "OBS_INTERNALS"
 * 
 * evita l'esecuzione di tutti i tipi azione osservabili,
 * che sono interni agli AEI della specifica AEmilia.
 * 
 * @author Mirko
 *
 */
public class RestrictInternals 
	extends BehavRestrictionDecl 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("RestrictInternals object");
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
		if (!(o instanceof RestrictInternals)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public RestrictInternals copy()
		{
		RestrictInternals restrictInternals = new RestrictInternals();
		return restrictInternals;
		}	
	
	/*
	 * 
	 * "RESTRICT" "OBS_INTERNALS"
	 * 
	 */
	@Override
	public String toString() 
		{
		return "RESTRICT OBS_INTERNALS";
		}
	
	}
