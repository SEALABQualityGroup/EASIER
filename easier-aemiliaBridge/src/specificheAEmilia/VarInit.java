package specificheAEmilia;

/**
 * Rappresenta una dichiarazione di variabile inizializzata.
 * In AEmilia, ha la seguente sintassi:
 * <pre>
 * <code>
 * &lt;normal_type&gt; &lt;identifier&gt; ":=" &lt;expr&gt;
 * </code>
 * </pre>
 *
 * e viene utilizzata
 * nell'intestazione della prima equazione comportamentale e
 * consiste nella dichiarazione di un parametro formale
 * variabile inizializzato.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class VarInit extends ParamDeclaration {

	/**
	 * type di dato della variabile.
	 */
	private NormalType type;

	/**
	 * Expression di inizializzazione per la variabile.
	 */
	private Expression expr;

	/**
	 * Crea un oggetto VarInit vuoto.
	 *
	 */
	public VarInit() {
	}

	/**
	 * Crea un oggetto VarInit con nome, tipo  ed espressione
	 * di inizializzazione forniti come parametri.
	 * @param type - oggetto NormalType.
	 * @param name - oggetto String.
	 * @param expr - oggetto Expression.
	 */
	public VarInit(NormalType type, String name, Expression expr) {
		setName(name);
		this.type = type;
		this.expr = expr;
	}

	/**
	 * Restituisce il tipo di dato della variabile.
	 * @return un oggetto NormalType.
	 */
	public NormalType getType()
		{
		return this.type;
		}

	/**
	 * Restituisce l'espressione di inizializzazione per
	 * questa variabile.
	 * @return un oggetto Expression.
	 */
	public Expression getExpr()
		{
		return this.expr;
		}

	/**
	 * Assegna un'espressione di inizializzazione alla variabile.
	 * @param expr - oggetto Expression.
	 */
	public void setExpr(Expression expr) 
		{
		this.expr = expr;
		}

	/**
	 * Assegna un tipo di dato alla variabile.
	 * @param type - oggetto NormalType.
	 */
	public void setType(NormalType type) 
		{
		this.type = type;
		}

	/**
	 * Stampa sullo standard output le informazioni relative a
	 * questo oggetto.
	 */
	public void print()
		{
		System.out.println("VarInit object");
		super.print();
		System.out.print("Parameter type: ");
		getType().print();
		System.out.println("Variable expression");
		getExpr().print();
		}

	/**
	 * Restituisce true se e solo se questo oggettoe'uguale a vi.
	 * @param vi - oggetto VarInit.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private NormalType type;
	 * private Expression expr;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof VarInit)) return false;
		VarInit vi = (VarInit)o;
		boolean ris = super.equals(vi);
		ris = ris && getExpr().equals(((VarInit)vi).getExpr());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public VarInit copy()
		{
		VarInit a = new VarInit();
		if (getName() != null)
			a.setName(new String(getName()));
		if (getExpr() != null)
			a.setExpr(getExpr().copy());
		if (getType() != null)
			a.setType(getType().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		return this.type.toString() + " " + super.toString() + " := " + this.expr.toString();
		}
	}