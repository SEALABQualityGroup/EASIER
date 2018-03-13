/**
 * 
 */
package specificheAEmilia;

/**
 * <action_type_set_r> ::= <identifier>
 * | "OBS_INTERNALS"
 * | "OBS_INTERACTIONS"
 * | "ALL_OBSERVABLES"
 * 
 * <identifier> indica un tipo azione. 
 * OBS_INTERNALS indica tutte le azioni interne e osservabili.
 * OBS_INTERACTIONS indica tutte le interazioni non architetturali e osservabili.
 * ALL_OBSERVABLES indica tutte le azioni di tipo OBS_INTERNALS e OBS_INTERACTIONS.
 * 
 * @author Mirko
 *
 */
public class ActionTypeSetR implements AEmiliaBase
	{	
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ActionTypeSetR object");
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param o - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof ActionTypeSetR)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ActionTypeSetR copy()
		{
		ActionTypeSetR actionTypeSetR = new ActionTypeSetR();
		return actionTypeSetR;
		}	
	}
