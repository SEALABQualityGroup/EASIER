package specificheAEmilia;

/**
 * Rappresenta un tipo di dato speciale. In AEmilia, ha la
 * seguente sintassi:
 * <pre>
 * <code>
 * &lt;special_type&gt; ::= "prio"
 * | "rate"
 * | "weight"
 * </code>
 * </pre>
 * Il tipo prio denota l'insieme delle priorità delle azioni
 * passive e immediate, che coincide con l'insieme degli interi
 * positivi. Il tipo rate denota l'insieme dei tassi azioni
 * temporizzate esponenzialmente, che coincide con l'insieme dei
 * reali positivi. Il tipo weight denota l'insieme dei pesi azioni
 * passive e immediate, che coincidono con l'insieme dei reali
 * positivi.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class SpecialType 
	extends DataType 
	{

	/**
	 * Crea un oggetto SpecialType vuoto.
	 *
	 */
	public SpecialType() {
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print() {
		System.out.println("SpecialType object");
		super.print();
	}

	/**
	 * Restituisce true se e solo se questo oggetto e'
	 * uguale a st.
	 * @param st - oggetto SpecialType.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * null
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof Expression)) return false;
		Expression st = (Expression)o;
		try {
			boolean ris = super.equals(st);
			return ris;
			}
		catch (ClassCastException e)
			{
			return false;
			}
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public SpecialType copy()
		{
		SpecialType a = new SpecialType();
		return a;
		}
}