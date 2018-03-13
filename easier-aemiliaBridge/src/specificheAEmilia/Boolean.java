package specificheAEmilia;

/**
 * Rappresenta un valore booleano che puo' essere presente
 * in una specifica AEmilia. Il tipo Boolean denota
 * l'insieme composto dei valori di verit� true e false.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class Boolean extends Expression
	{

	/**
	 * Valore di tipo booleano.
	 */
	// il valore di un tipo di datoe'utile per la valutazione
	// di una costante o variabile
	private boolean Valore;

	/**
	 * Crea un oggetto Boolean vuoto.
	 *
	 */
	public Boolean() {
	}

	/**
	 * Crea un oggetto Boolean con valore uguale
	 * al valore referenziato dal parametro del metodo.
	 * @param valore - oggetto boolean.
	 */
	public Boolean(boolean valore) {
		this.Valore = valore;
	}

	/**
	 * Restituisce il valore.
	 * @return oggetto boolean.
	 */
	public boolean getValore()
		{
		return this.Valore;
		}

	/**
	 * Assegna un nuovo valore all'oggetto Boolean.
	 * @param valore - oggetto boolean.
	 */
	public void setValore(boolean valore) {
		this.Valore = valore;
	}

	/**
	 * Stampa sullo standard output le informazioni
	 * relative a questo oggetto.
	 */
	public void print()
		{
		System.out.println("Boolean object");
		super.print();
		System.out.print("Value: ");
		System.out.println(getValore());
		}

	/**
	 * Confronta questo oggetto con l'oggetto referenziato
	 * dal parametro del metodo.
	 * @param b - oggetto Boolean da confrontare.
	 * @return un valore booleano che indica se i due ogetti
	 * sono uguali.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private boolean Valore;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof Expression)) return false;
		Expression e = (Expression)o;
		try {
			boolean ris = super.equals(e);
			ris = ris && (getValore() == ((Boolean)e).getValore());
			return ris;
			}
		catch (ClassCastException c)
			{
			return false;
			}
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Boolean copy()
		{
		Boolean a = new Boolean();
		a.setValore(getValore());
		return a;
		}

	@Override
	public String toString() 
		{
		return new java.lang.Boolean(this.Valore).toString();
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}