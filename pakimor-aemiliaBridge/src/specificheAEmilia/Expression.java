package specificheAEmilia;

/**
 * Rappresenta un'espressione che puo' essere presente in una
 * specifica. Di seguito, elenchiamo la grammatica
 * delle possibili espressioni AEmilia che possono essere
 * rappresentate.<br/>
 *
 * Espressioni aritmetiche.
 * <pre>
 * <code>
 * &lt;expr&gt; ::= &lt;expr&gt; "+" &lt;expr&gt;
 * | &lt;expr&gt; "-" &lt;expr&gt;
 * | &lt;expr&gt; "*" &lt;expr&gt;
 * | &lt;expr&gt; "/" &lt;expr&gt;
 * </code>
 * </pre>
 * Espressioni con operatori relazionali.
 * <pre>
 * <code>
 * &lt;expr&gt; ::= &lt;expr&gt; "=" &lt;expr&gt;
 * | &lt;expr&gt; "!=" &lt;expr&gt;
 * | &lt;expr&gt; "<" &lt;expr&gt;
 * | &lt;expr&gt; "<=" &lt;expr&gt;
 * | &lt;expr&gt; ">" &lt;expr&gt;
 * | &lt;expr&gt; ">=" &lt;expr&gt;
 * </code>
 * </pre>
 * Espressioni booleane.
 * <pre>
 * <code>
 * &lt;expr&gt; ::= &lt;expr&gt; "&&" &lt;expr&gt;
 * | &lt;expr&gt; "||" &lt;expr&gt;
 * | "!" &lt;expr&gt;
 * </code>
 * </pre>
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

// Implementare gli array, i record e le liste

public abstract class Expression implements AEmiliaBase
	{

	/**
	 * Crea un oggetto Expression vuoto.
	 *
	 */
	public Expression() {
	}

	/**
	 * Stampa sullo standard output le informazioni
	 * relative ad un oggetto Expression.
	 *
	 */
	public void print()
		{
		System.out.println("Expression object");
		}

	/**
	 * Restituisce true se e solo se le due espressioni
	 * cofrontate sono uguali.
	 * @param e - oggetto Expression da confrontare con
	 * questo oggetto.
	 * @return valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * null
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof Expression)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public abstract Expression copy();
	
	public abstract boolean isLiteral();

	
}