package specificheAEmilia;

/**
 * Indica un'operazione di disgiunzione logica tra due espressioni.
 * In AEmilia, l'or ha la seguente sintassi:
 * <pre>
 * <code>
 * &lt;expr&gt; "||" &lt;expr&gt;
 * </code>
 * </pre>
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class Or extends Expression 
	{

	/**
	 * Primo operando dell'operazione.
	 */
	private Expression expr1;

	/**
	 * Secondo operando dell'operazione.
	 */
	private Expression expr2;

	/**
	 * Istanzia un oggetto Or vuoto.
	 *
	 */
	public Or() {
		}

	/**
	 * Crea un oggetto Or con operandi uguali a quelli
	 * indicati dai parametri.
	 * @param expr1 - oggetto Expression corrispondente
	 * al primo operando.
	 * @param expr2 - oggetto Expression corrispondente
	 * al secondo operando.
	 */
	public Or(Expression expr1, Expression expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
		}

	/**
	 * Restituisce il primo operando dell'operazione.
	 * @return oggetto Expression.
	 */
	public Expression getExpr1() {
		return this.expr1;
	}

	/**
	 * Restituisce il secondo operando dell'operazione.
	 * @return oggetto Expression.
	 */
	public Expression getExpr2() {
		return this.expr2;
	}

	/**
	 * Imposta il primo operando dell'operazione uguale
	 * a quello indicato dal parametro.
	 * @param expr1 - nuovo primo operando dell'operazione.
	 */
	public void setExpr1(Expression expr1) {
		this.expr1 = expr1;
	}

	/**
	 * Imposta il secondo operando dell'operazione uguale
	 * a quello indicato dal parametro.
	 * @param expr2 - nuovo secondo operando dell'operazione.
	 */
	public void setExpr2(Expression expr2) {
		this.expr2 = expr2;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("Or object");
		super.print();
		System.out.println("Expression operands:");
		System.out.println("Operand 1:");
		getExpr1().print();
		System.out.println("Operand 2:");
		getExpr2().print();
		}

	/**
	 * Verifica se questo oggettoe'uguale al parametro del metodo.
	 * @param a - oggetto Or da confrontare.
	 * @return un valore booleano indicante se gli oggetti
	 * confrontati hanno le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression expr1;
	 * private Expression expr2;
	 */

	public boolean equals(Object o1)
		{
		if (!(o1 instanceof Or)) return false;
		Or o = (Or)o1;
		boolean ris = super.equals(o);
		ris = ris && getExpr1().equals(o.getExpr1());
		ris = ris && getExpr2().equals(o.getExpr2());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Or copy()
		{
		Or a = new Or();
		if (getExpr1() != null)
		a.setExpr1(getExpr1().copy());
		if (getExpr2() != null)
		a.setExpr2(getExpr2().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = (this.expr1.isLiteral() ? this.expr1.toString() : "(" + this.expr1.toString() + ")") 
			+ " || " + (this.expr2.isLiteral() ? this.expr2.toString() : "(" + this.expr2.toString() + ")");
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return false;
		}
	}