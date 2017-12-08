package specificheAEmilia;

/**
 * La costante stop rappresenta il termine di processo che non puo'
 * eseguire nessuna azione. In AEmilia, la sua sintassie'la
 * seguente:
 * <pre>
 * <code>
 * "stop"
 * </code>
 * </pre>
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 */

public class Stop extends ProcessTerm {

	/**
	 * Crea un termine di processo Stop vuoto.
	 *
	 */
	public Stop() {
		super();
	}

	/**
	 * Crea un termine di processo Stop con condizione di
	 * esecuzione fornita come parametro.
	 * @param condizione - oggetto Expression.
	 */
	public Stop(Expression condizione) {
		super(condizione);
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("Stop object");
		super.print();
		}

	/**
	 * Restituisce true se e solo se questo oggettoe'uguale a s.
	 * @param s - oggetto Stop.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * null
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ProcessTerm)) return false;
		ProcessTerm s = (ProcessTerm)o;
		try {
			boolean ris = super.equals(s);
			return ris;
			}
		catch (ClassCastException e)
			{
			return false;
			}
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Stop copy()
		{
		Stop a = new Stop();
		if (getCondition() != null)
		a.setCondition(getCondition().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		return "stop";
		}
	}
