package specificheAEmilia;

/**
 * Rappresenta una dichiarazione di costante all'interno di una specifica
 * EAmilia. La relativa grammaticae'la seguente:
 * 
 * <pre>
 * <code>
 * "const" &lt;data_type&gt; &lt;identifier&gt;
 * </code>
 * </pre>
 * 
 *e'una dichiarazione di parametro formale costante utilizzata
 * nell'intestazione di un AET, il cui valoree'definito nella dichiarazione
 * delle istanze dell'AET nella sezione di topologia architetturale.
 *
 * @author Mirko Email: <a href=
 *         "mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class Const extends ParamDeclaration {

	/**
	 * type di dato della costante dichiarata.
	 */
	private DataType type;

	/**
	 * Costruisce un oggetto Const vuoto.
	 *
	 */
	public Const() {
	}

	/**
	 * Costruisce un oggetto Const con nome e tipo di dato forniti come parametri.
	 * 
	 * @param type
	 *            - oggetto DataType.
	 * @param name
	 *            - oggetto String.
	 */
	public Const(DataType type, String name) {
		setName(name);
		this.type = type;
	}

	/**
	 * Restituisce il tipo di dato della costante.
	 * 
	 * @return oggetto DataType.
	 */
	public DataType getType() {
		return this.type;
	}

	/**
	 * Assegna un nuovo tipo di dato alla costante.
	 * 
	 * @param type
	 *            - oggetto DataType.
	 */
	public void setType(DataType type) {
		this.type = type;
	}

	/**
	 * Stampa sullo standard output le informazioni relative a questo oggetto.
	 */
	public void print() {
		System.out.println("Const object");
		super.print();
		System.out.print("Parameter type: ");
		getType().print();
	}

	/**
	 * Confornta se questo oggettoe'uguale a quello referenziato dal parametro del
	 * metodo.
	 * 
	 * @param c
	 *            - oggetto Const da confrontare.
	 * @return true se e solo se i due oggetti contengono le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private DataType type;
	 */

	public boolean equals(Object o) {
		if (!(o instanceof Const))
			return false;
		Const const1 = (Const) o;
		boolean ris = super.equals(const1);
		ris = ris && getType().equals((const1).getType());
		return ris;
	}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * 
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Const copy() {
		Const a = new Const();
		a.setName(new String(getName()));
		if (getType() != null)
			a.setType(getType().copy());
		return a;
	}

	@Override
	public String toString() {
		String string = "const " + this.getType().toString() + " " + super.toString();
		return string;
	}
}