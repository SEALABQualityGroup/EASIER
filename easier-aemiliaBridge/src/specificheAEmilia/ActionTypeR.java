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
public class ActionTypeR 
	extends ActionTypeSetR 
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
		System.out.println("ActionTypeR object");
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
		if (!(o instanceof ActionTypeR)) return false;
		ActionTypeR actionTypeR = (ActionTypeR)o;
		// confronta i tipi di azione
		boolean ris = getActionType().equals(actionTypeR.getActionType());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ActionTypeR copy()
		{
		ActionTypeR actionTypeR = new ActionTypeR();
		if (getActionType() != null)
			actionTypeR.setActionType(new String(actionTypeR.getActionType()));
		return actionTypeR;
		}

	@Override
	public String toString() 
		{
		return this.getActionType();
		}
	
	}
