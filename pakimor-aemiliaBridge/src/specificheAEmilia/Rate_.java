package specificheAEmilia;

/**
 * Rappresenta il tasso di un'azione passiva in AEmilia.
 * La sua sintassie'la seguente:
 * <pre>
 * <code>
 * "_" "(" &lt;expr&gt; "," &lt;expr&gt; ")"
 * | "_"
 * </code>
 * </pre>
 * Il tasso di un'azione passiva (_) a di nuovo espressa attraverso attraverso
 * due espressioni che denotano una priorita ed un peso,
 * rispettivamente. Se non a specificato, i valori della priorita
 * e il peso di un'azione passiva sono uno.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class Rate_ extends RateNoExp {

	/**
	 * prio dell'azione.
	 */
	private Expression prio;

	/**
	 * weight dell'azione.
	 */
	private Expression weight;

	/**
	 * Crea un oggetto Rate_ vuoto.
	 *
	 */
	public Rate_() {
	}

	/**
	 * Crea un oggetto Rate_ con priorita e peso forniti come
	 * parametri.
	 * @param prio - oggetto Expression.
	 * @param weight - oggetto Expression.
	 */
	public Rate_(Expression prio, Expression weight) {
		this.prio = prio;
		this.weight = weight;
	}

	/**
	 * Restituisce la priorita dell'azione.
	 * @return un oggetto Expression.
	 */
	public Expression getPrio()
		{
		return this.prio;
		}

	/**
	 * Restituisce il peso dell'azione.
	 * @return un oggetto Expression.
	 */
	public Expression getWeight()
		{
		return this.weight;
		}

	/**
	 * Assegna un peso a questo tasso.
	 * @param weight - oggetto Expression.
	 */
	public void setWeight(Expression weight) {
		this.weight = weight;
	}

	/**
	 * Assegna una priorita a questo tasso.
	 * @param prio - oggetto Expression.
	 */
	public void setPrio(Expression prio) {
		this.prio = prio;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("Rate_ object");
		super.print();
		System.out.println("Action priority: ");
		getPrio().print();
		System.out.println("Action weight: ");
		getWeight().print();
		}

	/**
	 * Restituisce true se e solo se questo oggetto a uguale a r.
	 * @param r - oggetto Rate_.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression prio;
	 * private Expression weight;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ActionRate)) return false;
		ActionRate r = (ActionRate)o;
		try {
			boolean ris = super.equals(r);
			ris = ris && getWeight().equals(((Rate_)r).getWeight());
			ris = ris && getPrio().equals(((Rate_)r).getPrio());
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
	public Rate_ copy()
		{
		Rate_ a = new Rate_();
		if (getWeight() != null)
		a.setWeight(getWeight().copy());
		if (getPrio() != null)
		a.setPrio(getPrio().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "_" + (this.prio == null ? 
				"" : "(" + this.prio.toString() + "," + this.weight.toString() + ")"); 
		return string;
		}
	}