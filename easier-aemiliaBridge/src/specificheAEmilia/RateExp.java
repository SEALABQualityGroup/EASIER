package specificheAEmilia;

/**
 * Rappresenta il tasso di un'azione temporizzata esponenzialmente.
 * In AEmilia, la sua sintassie'la seguente:
 * <pre>
 * <code>
 * "exp" "(" &lt;expr&gt; ")"
 * </code>
 * </pre>
 * Il tasso di un'azione temporizzata esponenzialmente (exp)e'data
 * da un'espressione, il cui valore deve essere un reale positivo,
 * ede'interpretato come il tasso della variabile casuale
 * distribuita esponenzialmente che descrive la durata dell'azione.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class RateExp extends ActionRate {

	/**
	 * Tasso della variabile casuale distribuita esponenzialmente
	 * che descrive la durata di un'azione.
	 */
	private Expression expr;

	/**
	 * Crea un oggetto RateExp vuoto.
	 *
	 */
	public RateExp() {
	}

	/**
	 * Crea un oggetto RateExp con tasso fornito come parametro.
	 * @param expr - oggetto Expression.
	 */
	public RateExp(Expression expr) {
		this.expr = expr;
	}

	/**
	 * Restituisce il tasso dell'azione temporizzata
	 * esponenzialmente.
	 * @return oggetto Expression.
	 */
	public Expression getExpr()
		{
		return this.expr;
		}

	/**
	 * Assegna il tasso ad un'azione temporizzata
	 * esponenzialmente.
	 * @param expr - oggetto Expression.
	 */
	public void setExpr(Expression expr) {
		this.expr = expr;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("RateExp object");
		super.print();
		System.out.println("Action duration: ");
		getExpr().print();
		}

	/**
	 * Restituisce true se e solo se questo oggettoe'uguale
	 * a re.
	 * @param re - oggetto RateExp.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression expr;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ActionRate)) return false;
		ActionRate re = (ActionRate)o;
		try {
			boolean ris = super.equals(re);
			ris = ris && getExpr().equals(((RateExp)re).getExpr());
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
	public RateExp copy()
		{
		RateExp a = new RateExp();
		if (getExpr() != null)
		a.setExpr(getExpr().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "exp(" + this.expr.toString() + ")";
		return string;
		}
	}