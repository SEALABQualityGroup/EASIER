package specificheAEmilia;

/**
 * Rappresenta una dichiarazione di variabile locale.
 * Ha la seguente sintassi:
 * <pre>
 * <code>
 * "local" &lt;normal_type&gt; &lt;identifier&gt;
 * </code>
 * </pre>
 * viene di solito utilizzata quando si sincronizza un'azione
 * di input di un'istanza dell'AET con un'azione di output di
 * un altro AEI. Tale dichiarazione viene utilizzata
 * nell'intestazione di un'equazione comportamentale.
 * Ogni volta che una variabile locale si presenta in
 * un'espressione all'interno di un'azione di output,
 * un'invocazione di un'equazione comportamentale, o una
 * guardia booleana senza che si sia presentata
 * precedentemente in un'azione di input, viene valutata a
 * zero, false, o null a seconda del suo tipo.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class Local 
	extends ParamDeclaration 
	{
	
	private NormalType type;

	/**
	 * Crea un oggetto Local vuoto.
	 *
	 */
	public Local() {
	}

	/**
	 * Crea un oggetto Local con nome e tipo forniti come
	 * parametri.
	 * @param type - oggetto NormalType.
	 * @param nome - oggetto String.
	 */
	public Local(NormalType type, String nome) 
		{
		setName(nome);
		this.type = type;
		}

	/**
	 * Restituisce il tipo della variabile.
	 * @return oggetto NormalType.
	 */
	public NormalType getType()
		{
		return this.type;
		}

	/**
	 * Assegna un tipo alla variabile locale.
	 * @param type - oggetto NormalType.
	 */
	public void setType(NormalType type) 
		{
		this.type = type;
		}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("Local object");
		super.print();
		System.out.print("Parameter type: ");
		getType().print();
		}

	/**
	 * Restituisce true se e solo se le'una dichiarazione
	 * uguale a questa.
	 * @param l - oggetto Local.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression type;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof Local)) return false;
		Local local = (Local)o;
		boolean ris = super.equals(local);
		ris = ris && getType().equals(local.getType());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Local copy()
		{
		Local a = new Local();
		if (getName() != null)
			a.setName(new String(getName()));
		if (getType() != null)
			a.setType(getType().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "local " + this.type.toString() + " " + super.toString(); 
		return string;
		}
	}