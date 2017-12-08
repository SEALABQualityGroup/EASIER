/**
 * 
 */
package specificheAEmilia;

/**
 * "RESTRICT" "OBS_INTERACTIONS" 
 * 
 * Evita l'esecuzione di tutte le interazioni non architetturali 
 * osservabili.
 * 
 * @author Mirko
 *
 */
public class RestrictInteractions 
	extends BehavRestrictionDecl 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("RestrictInteractions object");
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
		if (!(o instanceof RestrictInteractions)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public RestrictInteractions copy()
		{
		RestrictInteractions restrictInteractions = new RestrictInteractions();
		return restrictInteractions;
		}	
	
	/*
	 * 
	 * "RESTRICT OBS_INTERACTIONS"	
	 * 
	 */
	@Override
	public String toString() 
		{
		return "RESTRICT OBS_INTERACTIONS";
		}
	
	}
