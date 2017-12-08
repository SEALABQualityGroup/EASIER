package specificheAEmilia;

/**
 * Rappresenta una dichiarazione di variabile. In AEmilia, ha la
 * seguente sintassi:
 * <pre>
 * <code>
 * &lt;normal_type&gt; &lt;identifier&gt;
 * </code>
 * </pre>
 *
 * e viene utilizzata nell'equazioni comportamentali susseguenti
 * la prima e consiste nella dichiarazione di un parametro formale
 * variabile. Nessuna espressione di inizializzazionee'necessaria
 * per i parametri formali variabili di ogni equazione
 * comportamentale susseguente, poich�, ad essi, saranno
 * assegnati i valori dei parametri attuali contenuti
 * nell'invocazioni dell'equazione comportamentale correlata.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class VariableDeclaration extends ParamDeclaration {

	/**
	 * type di dato della variabile.
	 */
	private NormalType type;

	/**
	 * Crea un oggetto VariableDeclaration vuoto.
	 *
	 */
	public VariableDeclaration() {
	}

	/**
	 * Crea un oggetto VariableDeclaration con nome e tipo forniti come parametri.
	 * @param nome - oggetto String.
	 * @param type - oggetto NormalType.
	 */
	public VariableDeclaration(String nome, NormalType type) 
		{
		setName(nome);
		this.type = type;
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
	 * Assegna un tipo di dato alla variabile.
	 * @param type - oggetto NormalType.
	 */
	public void setType(NormalType type) 
		{
		this.type = type;
		}

	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 */
	public void print()
		{
		System.out.println("VariableDeclaration object");
		super.print();
		System.out.print("Parameter type: ");
		getType().print();
		}

	/**
	 * Restituisce true se e solo se questo oggettoe'uguale a v.
	 * @param v - oggetto VariableDeclaration.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private NormalType type;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof VariableDeclaration)) return false;
		VariableDeclaration v = (VariableDeclaration)o;
		boolean ris = super.equals(v);
		ris = ris && this.type.equals(v.getType());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public VariableDeclaration copy()
		{
		VariableDeclaration a = new VariableDeclaration();
		if (getName() != null)
			a.setName(new String(getName()));
		if (getType() != null)
			a.setType(getType().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		return this.type.toString() + " " + super.toString();
		}
	}