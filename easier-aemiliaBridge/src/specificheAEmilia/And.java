package specificheAEmilia;

/**
 * Indica un'operazione di congiunzione logica tra due espressioni.
 * In AEmilia, l'and ha la seguente sintassi:
 * <pre>
 * <code>
 * &lt;expr&gt; "&&" &lt;expr&gt;
 * </code>
 * </pre>
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
public class And 
	extends Expression 
	{

	/**
	 * Primo operando dell'operazione.
	 */
	private Expression Expr1;

	/**
	 * Secondo operando dell'operazione.
	 */
	private Expression Expr2;

	/**
	 * Istanzia un oggetto And vuoto.
	 *
	 */
	public And() {
		}

	/**
	 * Crea un oggetto And con operandi uguali a quelli
	 * indicati dai parametri.
	 * @param Expr1 - oggetto Expression corrispondente
	 * al primo operando.
	 * @param Expr2 - oggetto Expression corrispondente
	 * al secondo operando.
	 */
	public And(Expression Expr1, Expression Expr2) {
		// assegna il primo operando
		this.Expr1 = Expr1;
		// assegna il secondo operando
		this.Expr2 = Expr2;
		}

	/**
	 * Restituisce il primo operando dell'operazione.
	 * @return oggetto Expression.
	 */
	public Expression getExpr1() {
		// restituisce il primo operando
		return this.Expr1;
	}

	/**
	 * Restituisce il secondo operando dell'operazione.
	 * @return oggetto Expression.
	 */
	public Expression getExpr2() {
		// resttuisce il secondo operando
		return this.Expr2;
	}

	/**
	 * Imposta il primo operando dell'operazione uguale
	 * a quello indicato dal parametro.
	 * @param expr1 - nuovo primo operando dell'operazione.
	 */
	public void setExpr1(Expression expr1) {
		// assegna il primo operando
		this.Expr1 = expr1;
	}

	/**
	 * Imposta il secondo operando dell'operazione uguale
	 * a quello indicato dal parametro.
	 * @param expr2 - nuovo secondo operando dell'operazione.
	 */
	public void setExpr2(Expression expr2) {
		// assegna il secondo operando
		this.Expr2 = expr2;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("And object");
		// stampa le informazioni della superclasse Boolean
		super.print();
		System.out.println("Expression operands:");
		System.out.println("Operand 1:");
		// stampa il primo operando
		getExpr1().print();
		System.out.println("Operand 2:");
		// stampa il secondo operando
		getExpr2().print();
		}

	/**
	 * Verifica se questo oggettoe'uguale al parametro del metodo.
	 * @param a - oggetto And da confrontare.
	 * @return un valore booleano indicante se gli oggetti
	 * confrontati hanno le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression Expr1;
	 * private Expression Expr2;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof And)) return false;
		And and = (And)o;
		// confronta le due istanze come oggetti Boolean
		boolean ris = super.equals(and);
		// confronta i primi operandi delle due operazioni
		ris = ris && getExpr1().equals(and.getExpr1());
		// confronta i secondi operandi delle due operazioni
		ris = ris && getExpr2().equals(and.getExpr2());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public And copy()
		{
		And a = new And();
		if (getExpr1() != null)
		a.setExpr1(getExpr1().copy());
		if (getExpr2() != null)
		a.setExpr2(getExpr2().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		return (this.Expr1.isLiteral() ? this.Expr1.toString() : "(" + this.Expr1.toString() + ")") 
			+ " && " + (this.Expr2.isLiteral() ? this.Expr2.toString() : "(" + this.Expr2.toString() + ")");
		}

	@Override
	public boolean isLiteral() 
		{
		return false;
		}
	}