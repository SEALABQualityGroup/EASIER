package specificheAEmilia;

/**
 * Rappresenta un'operazione booleana di diverso. In AEmilia
 * la sua sintassi e':
 * <pre>
 * <code>
 * &lt;expr&gt; "!=" &lt;expr&gt;
 * </code>
 * </pre>
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
public class Different extends Expression 
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
	 * Crea un oggetto Different vuoto.
	 *
	 */
	public Different() {
		}

	/**
	 * Crea un oggetto Different con operandi forniti come
	 * parametri.
	 * @param Expr1 - oggetto Expression, primo operando
	 * dell'operazione.
	 * @param Expr2 - oggetto Expression, secondo operando
	 * dell'operazione.
	 */
	public Different(Expression Expr1, Expression Expr2) {
		this.Expr1 = Expr1;
		this.Expr2 = Expr2;
		}

	/**
	 * Restituisce il primo operando.
	 * @return oggetto Expression.
	 */
	public Expression getExpr1() {
		return this.Expr1;
	}

	/**
	 * Restituisce il secondo operando.
	 * @return oggetto Expression.
	 */
	public Expression getExpr2() {
		return this.Expr2;
	}

	/**
	 * Assegna un nuovo primo operando all'operazione.
	 * @param expr1 - oggetto Expression.
	 */
	public void setExpr1(Expression expr1) {
		this.Expr1 = expr1;
	}

	/**
	 * Assegna un nuovo secondo operando all'operazione.
	 * @param expr2 - oggetto Expression.
	 */
	public void setExpr2(Expression expr2) {
		this.Expr2 = expr2;
	}

	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 */
	public void print()
		{
		System.out.println("Different object");
		super.print();
		System.out.println("Expression operands:");
		System.out.println("Operand 1:");
		getExpr1().print();
		System.out.println("Operand 2:");
		getExpr2().print();
		}

	/**
	 * Confronta se due oggetti Different hanno gli stessi
	 * operandi.
	 * @param d - oggetto Different da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * contengono le stesse informazioni
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression Expr1;
	 * private Expression Expr2;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof Different)) return false;
		Different e = (Different)o;
		boolean ris = super.equals(e);
		ris = ris && getExpr1().equals(e.getExpr1());
		ris = ris && getExpr2().equals(e.getExpr2());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Different copy()
		{
		Different a = new Different();
		if (getExpr1() != null)
		a.setExpr1(getExpr1().copy());
		if (getExpr2() != null)
		a.setExpr2(getExpr2().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = (this.Expr1.isLiteral() ? this.Expr1.toString() : "(" + this.Expr1.toString() + ")") + 
			" != " + (this.Expr2.isLiteral() ? this.Expr2.toString() : "(" + this.Expr2.toString() + ")");
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return false;
		}
	}