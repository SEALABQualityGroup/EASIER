package specificheAEmilia;

/**
 * Rappresenta il tasso di un'azione immediata in AEmilia.
 * La sua sintassie'la seguente:
 * <pre>
 * <code>
 * "inf" "(" &lt;expr&gt; "," &lt;expr&gt; ")"
 * | "inf"
 * </code>
 * </pre>
 * Il tasso di un'azione immediata (inf)e'espresso attraverso
 * una priorita, data da un'espressione il cui valore deve essere
 * un intero non minore di uno, e un peso, dato da un'espressione
 * il cui valore deve essere un reale positivo.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class RateInf extends RateNoExp {

	/**
	 * priorita' dell'azione immediata.
	 */
	private Expression prio;

	/**
	 * weight dell'azione immediata.
	 */
	private Expression weight;

	/**
	 * Crea un oggetto RateInf vuoto.
	 *
	 */
	public RateInf() {
	}

	/**
	 * Crea un oggetto RateInf con priorita e peso forniti
	 * come parametri al metodo.
	 * @param prio - oggetto Expression.
	 * @param weight - oggetto Expression.
	 */
	public RateInf(Expression prio, Expression weight) 
		{
		this.prio = prio;
		this.weight = weight;
		}

	/**
	 * Restituisce la priorita dell'azione immediata.
	 * @return - oggetto Expression.
	 */
	public Expression getPrio()
		{
		return this.prio;
		}

	/**
	 * Restituisce il peso dell'azione immediata.
	 * @return - oggetto Expression.
	 */
	public Expression getWeight()
		{
		return this.weight;
		}

	/**
	 * Assegna il peso a questo tasso.
	 * @param weight - oggetto Expression.
	 */
	public void setWeight(Expression weight) {
		this.weight = weight;
	}

	/**
	 * Assegna la priorita a questo tasso.
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
		System.out.println("RateInf object");
		super.print();
		System.out.println("Action priority: ");
		getPrio().print();
		System.out.println("Action weight: ");
		getWeight().print();
		}

	/**
	 * Restituisce true se e solo se questo oggettoe'uguale a
	 * ri.
	 * @param ri - oggetto RateInf.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression Priorita�;
	 * private Expression weight;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ActionRate)) return false;
		ActionRate ri = (ActionRate)o;
		try {
			boolean ris = super.equals(ri);
			ris = ris && getWeight().equals(((RateInf)ri).getWeight());
			ris = ris && getPrio().equals(((RateInf)ri).getPrio());
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
	public RateInf copy()
		{
		RateInf a = new RateInf();
		if (getWeight() != null)
		a.setWeight(getWeight().copy());
		if (getPrio() != null)
		a.setPrio(getPrio().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "inf" + (this.weight == null ? "" : "(" + 
				this.prio.toString() + "," + this.weight.toString() + ")");
		return string;
		}
	}