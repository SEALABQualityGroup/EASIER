package specificheAEmilia;

/**
 * Rappresenta una dichiarazione di costante inizializzata che
 * puo' essere presente in una specifica AEmilia. Ha la seguente
 * sintassi:
 * <pre>
 * <code>
 * "const" &lt;data_type&gt; &lt;identifier&gt; ":=" &lt;expr&gt;
 * </code>
 * </pre>
 * e viene utilizzata nell'intestazione
 * della definizione di un tipo architetturale.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 */

public class ConstInit extends ParamDeclaration {

	/**
	 * type di dato della costante.
	 */
	private DataType type;

	/**
	 * Expression di inizializzazione per la costante.
	 */
	private Expression expr;

	/**
	 * Crea un oggetto ConstInit vuoto.
	 *
	 */
	public ConstInit() {
	}

	/**
	 * Crea un oggetto ConstInit con nome, tipo e espressione
	 * di inizializzazione forniti come parametri per il costruttore.
	 * @param type - oggetto DataType.
	 * @param name - oggetto String.
	 * @param expr - oggetto Expression.
	 */
	public ConstInit(DataType type, String name, Expression expr) 
		{
		setName(name);
		this.type = type;
		this.expr = expr;
		}

	/**
	 * Restituisce il tipo di dati per la costante.
	 * @return oggetto DataType.
	 */
	public DataType getType()
		{
		return this.type;
		}

	/**
	 * Assegna un nuovo tipo di dato alla costante.
	 * @param type - oggetto DataType.
	 */
	public void setType(DataType type) 
		{
		this.type = type;
		}

	/**
	 * Restituisce l'espressione di inizializzazione della
	 * costante.
	 * @return oggetto Expression.
	 */
	public Expression getExpr()
		{
		return this.expr;
		}

	/**
	 * Assegna alla costante un'espressione di inizializzazione
	 * uguale a quella referenziata dal parametro.
	 * @param expr - oggetto Expression.
	 */
	public void setExpr(Expression expr) 
		{
		this.expr = expr;
		}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questa costante.
	 */
	public void print()
		{
		System.out.println("ConstInit object");
		super.print();
		System.out.print("Parameter type: ");
		getType().print();
		System.out.println("Assigned expression");
		getExpr().print();
		}

	/**
	 * Confronta se questo oggettoe'uguale all'oggetto fornito
	 * come parametro.
	 * @param ci - oggetto ConstInit da confrontare.
	 * @return true se e solo se questo oggettoe'uguale
	 * a ci.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private DataType type;
	 * private Expression expr;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ConstInit)) return false;
		ConstInit constInit = (ConstInit)o;
		boolean ris = super.equals(constInit);
		ris = ris && getExpr().equals(constInit.getExpr());
		ris	= ris && getType().equals(constInit.getType());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ConstInit copy()
		{
		ConstInit a = new ConstInit();
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
		String string = "const " + this.type.toString() + " " + super.toString() + " : = " +
			this.expr.toString();
		return string;
		}
	}