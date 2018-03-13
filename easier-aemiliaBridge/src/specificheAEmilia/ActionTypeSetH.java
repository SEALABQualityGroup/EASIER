/**
 * 
 */
package specificheAEmilia;

/**
 *  *  <action_type_set_h> ::= <identifier>
 *  | "INTERNALS"
 *  | "INTERACTIONS"
 *  | "ALL"
 *
 * dove <identifier> indica un tipo azione, INTERNALS tutti i tipi azione interni,
 * INTERACTIONS tutte le interazioni non architetturali, ALL denota sia le azioni
 * INTERNALS che INTERACTIONS.
  
 * @author Mirko
 *
 */
public class ActionTypeSetH implements AEmiliaBase
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ActionTypeSetH object");
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param o - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof ActionTypeSetH)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ActionTypeSetH copy()
		{
		ActionTypeSetH actionTypeSetH = new ActionTypeSetH();
		return actionTypeSetH;
		}
	}
