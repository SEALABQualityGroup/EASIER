package specificheAEmilia;

/**
 * Indica un'operazione di negazione logica di un'espressione.
 * In AEmilia, la negazione ha la seguente sintassi:
 * <pre>
 * <code>
 * "!" &lt;expr&gt;
 * </code>
 * </pre>
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class Negazione extends Expression 
	{

	/**
	 * Operando dell'operazione.
	 */
	private Expression Expr1;

	/**
	 * Istanzia un oggetto Negazione vuoto.
	 *
	 */
	public Negazione() {
		}

	/**
	 * Crea un oggetto Negazione con operando fornito come
	 * parametro.
	 * @param Expr1
	 */
	public Negazione(Expression Expr1) {
		this.Expr1 = Expr1;
		}

	/**
	 * Restituisce l'operando dell'operazione.
	 * @return oggetto Expression.
	 */
	public Expression getExpr1() {
		return this.Expr1;
	}

	/**
	 * Imposta l'operando dell'operazione uguale
	 * a quello indicato dal parametro.
	 * @param expr1 - nuovo operando dell'operazione.
	 */
	public void setExpr1(Expression expr1) {
		this.Expr1 = expr1;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("Negazione object");
		super.print();
		System.out.println("Expression operand:");
		getExpr1().print();
		}

	/**
	 * Verifica se questo oggettoe'uguale al parametro del metodo.
	 * @param n - oggetto Negazione da confrontare.
	 * @return un valore booleano indicante se gli oggetti
	 * confrontati hanno le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression Expr1;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof Negazione)) return false;
		Negazione n = (Negazione)o;
		boolean ris = super.equals(n);
		ris = ris && getExpr1().equals(n.getExpr1());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Negazione copy()
		{
		Negazione a = new Negazione();
		if (getExpr1() != null)
		a.setExpr1(getExpr1().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "!" + (this.Expr1.isLiteral() ? 
				this.Expr1.toString() : "(" + this.Expr1.toString() + ")");
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}