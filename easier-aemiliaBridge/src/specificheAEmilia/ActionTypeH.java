/**
 * 
 */
package specificheAEmilia;

/**
 * <identifier>, dove identifier
 * indica un tipo azione.
 *  
 * @author Mirko
 *
 */
public class ActionTypeH 
	extends ActionTypeSetH
	{
	
	private String actionType;

	public String getActionType() 
		{
		return actionType;
		}

	public void setActionType(String actionType) 
		{
		this.actionType = actionType;
		}

	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ActionTypeH object");
		// stampa il tipo azione
		System.out.println(this.getActionType());
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param o - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof ActionTypeH)) return false;
		ActionTypeH actionTypeH = (ActionTypeH)o;
		// confronta i tipi di azione
		boolean ris = getActionType().equals(actionTypeH.getActionType());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ActionTypeH copy()
		{
		ActionTypeH actionTypeH = new ActionTypeH();
		if (getActionType() != null)
			actionTypeH.setActionType(new String(actionTypeH.getActionType()));
		return actionTypeH;
		}

	@Override
	public String toString() 
		{
		return this.getActionType();
		}
	}
