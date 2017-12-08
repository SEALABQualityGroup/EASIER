/**
 * 
 */
package specificheAEmilia;

/**
 * "RESTRICT" "ALL_OBSERVABLES"
 * 
 * evita l'esecuzione dei tipi azioni interne e interazioni non architetturali
 * osservabili.
 * 
 * @author Mirko
 *
 */
public class RestrictAll 
	extends BehavRestrictionDecl 
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("RestrictAll object");
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
		if (!(o instanceof RestrictAll)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public RestrictAll copy()
		{
		RestrictAll restrictAll = new RestrictAll();
		return restrictAll;
		}	
	
	/*
	 *  "RESTRICT" "ALL_OBSERVABLES";
	 */
	@Override
	public String toString() 
		{
		return "RESTRICT ALL_OBSERVABLES";
		}	
	}
