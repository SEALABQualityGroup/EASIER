package specificheAEmilia;

/**
 * Tale classe contiene tutte le informazioni necessarie per
 * descrivere un'azione. In AEmilia un'azionee'definita dalla
 * seguente grammatica:
 * <pre>
 * <code>
 * &lt;action&gt; ::= "<" &lt;action_type&gt; "," &lt;action_rate&gt; ">"
 *
 * &lt;action_type&gt; ::= &lt;identifier&gt;
 * | &lt;identifier&gt; "?" "(" &lt;local_var_sequence&gt; ")"
 * | &lt;identifier&gt; "!" "(" &lt;expr_sequence&gt; ")"
 *
 * &lt;action_rate&gt; ::= "exp" "(" &lt;expr&gt; ")"
 * | "inf" "(" &lt;expr&gt; "," &lt;expr&gt; ")"
 * | "inf"
 * | "_" "(" &lt;expr&gt; "," &lt;expr&gt; ")"
 * | "_"
 * </code>
 * </pre>
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class Action implements AEmiliaBase
	{

	/**
	 * Indica il tipo di un'azione.
	 */
	private ActionType type;

	/**
	 * Indica il tasso di un'azione.
	 */
	private ActionRate rate;

	/**
	 * Costruttore vuoto per creare istanze di un'azione.
	 *
	 */
	public Action() {
	}

	/**
	 * Crea una vuova azione con tipo e tasso forniti.
	 *
	 * @param type - indica il tipo dell'azione istanziata.
	 * @param rate - indica il tasso dell'azione istanziata.
	 */
	public Action(ActionType type, ActionRate rate) {
		// assegna un tipo all'azione
		this.type = type;
		// assegna un tasso all'azione
		this.rate = rate;
	}

	/**
	 * Restituisce il tipo dell'azione.
	 * @return un oggetto ActionType che indica il tipo dell'azione.
	 */
	public ActionType getType()
		{
		// restituisce il tipo dell'azione
		return this.type;
		}

	/**
	 * Restituisce il tasso dell'azione.
	 * @return un oggetto ActionRate che indica il tasso dell'azione.
	 */
	public ActionRate getRate()
		{
		// restituisce il tasso dell'azione
		return this.rate;
		}

	/**
	 * Imposta il tasso a quello fornito come parametro.
	 * @param rate - oggetto ActionRate.
	 */
	public void setRate(ActionRate rate) {
		// assegna un tasso all'azione
		this.rate = rate;
	}

	/**
	 * Imposto il tipo a quello fornito come parametro.
	 * @param type - oggetto ActionType.
	 */
	public void setType(ActionType type) {
		// assegna un tipo all'azione
		this.type = type;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * all'azione.
	 */
	public void print()
		{
		System.out.println("Action object");
		// stampa il tipo dell'azione
		getType().print();
		// stampa il tasso dell'azione
		getRate().print();
		}

	/**
	 * Restituisce true se l'azione contiene le stesse informazioni
	 * contenute nel parametro della funzione.
	 * @param a - oggetto Action da confrontare.
	 * @return un booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * 	private ActionType type;
	 *  private ActionRate rate;
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof Action)) return false;
		Action a = (Action)o;
		// confronta il tasso ed il tipo delle due azioni
		return getRate().equals(a.getRate()) &&
		getType().equals(a.getType());
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Action copy()
		{
		Action a = new Action();
		if (getRate() != null)
		a.setRate(getRate().copy());
		if (getType() != null)
		a.setType(getType().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		return "<" + this.type.toString() + "," + this.rate.toString() + ">";
		}
	}