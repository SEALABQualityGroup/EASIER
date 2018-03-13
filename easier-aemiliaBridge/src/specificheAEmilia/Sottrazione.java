package specificheAEmilia;

/**
 * Rappresenta un'operazione di sottrazione di una
 * specifica AEmilia. La sua sintassie'la seguente:
 * <pre>
 * <code>
 * &lt;expr&gt; "-" &lt;expr&gt;
 * </code>
 * </pre>
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class Sottrazione extends Expression 
	{

	/**
	 * Primo operando.
	 */
	private Expression Expr1;

	/**
	 * Secondo operando.
	 */
	private Expression Expr2;

	/**
	 * Crea un oggetto Sottrazione vuoto.
	 *
	 */
	public Sottrazione()
	{}

	/**
	 * Crea un oggetto Sottrazione con operandi forniti come
	 * parametri.
	 * @param expr1 - oggetto Expression, che rappresenta
	 * il primo operando dell'espressione.
	 * @param expr2 - oggetto Expression, che rappresenta
	 * il secondo operando dell'espressione.
	 */
	public Sottrazione(Expression expr1, Expression expr2) {
		this.Expr1 = expr1;
		this.Expr2 = expr2;
	}

	/**
	 * Restituisce il primo operando dell'espressione.
	 * @return oggetto Expression.
	 */
	public Expression getExpr1() {
		return this.Expr1;
	}

	/**
	 * Restituisce il secondo operando dell'espressione.
	 * @return oggetto Expression.
	 */
	public Expression getExpr2() {
		return this.Expr2;
	}

	/**
	 * Assegna un nuovo primo operando per questa operazione.
	 * @param expr1 - oggetto Expression.
	 */
	public void setExpr1(Expression expr1) {
		this.Expr1 = expr1;
	}

	/**
	 * Assegna un nuovo secondo operando per questa operazione.
	 * @param expr2 - oggetto Expression.
	 */
	public void setExpr2(Expression expr2) {
		this.Expr2 = expr2;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("Sottrazione object");
		super.print();
		System.out.println("Expression operands:");
		System.out.println("Operand 1:");
		getExpr1().print();
		System.out.println("Operand 2:");
		getExpr2().print();
		}

	/**
	 * Confronta questo oggetto con quello referenziato dal
	 * parametro del metodo.
	 * @param s - oggetto Sottrazione da confrontare.
	 * @return true se e solo se i due oggetti contengono
	 * le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression Expr1;
	 * private Expression Expr2;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof Sottrazione)) return false;
		Sottrazione s = (Sottrazione)o;
		boolean ris = super.equals(s);
		ris = ris && getExpr1().equals(s.getExpr1());
		ris = ris && getExpr2().equals(s.getExpr2());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Sottrazione copy()
		{
		Sottrazione a = new Sottrazione();
		if (getExpr1() != null)
			a.setExpr1(getExpr1().copy());
		if (getExpr2() != null)
			a.setExpr2(getExpr2().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = (this.Expr1.isLiteral() ? this.Expr1.toString() : "(" + this.Expr1.toString() + ")") 
			+ " - " + (this.Expr2.isLiteral() ? this.Expr2.toString() : "(" + this.Expr2.toString() + ")"); 
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return false;
		}
	}