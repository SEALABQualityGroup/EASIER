package specificheAEmilia;

/**
 * Rappresenta uno dei possibili tipi di dato che possono essere
 * presenti in una specifica AEmilia. La sua sintassie'la
 * seguente:
 * <pre>
 * <code>
 * &lt;data_type&gt; ::= &lt;normal_type&gt;
 * | &lt;special_type&gt;
 * &lt;normal_type&gt; ::= "integer"
 * | "integer" "(" &lt;expr&gt; ".." &lt;expr&gt; ")"
 * | "real"
 * | "boolean"
 * | "list" "(" &lt;normal_type&gt; ")"
 * | "array" "(" &lt;expr&gt; "," &lt;normal_type&gt; ")"
 * | "record" "(" &lt;field_decl_sequence&gt; ")"
 * &lt;special_type&gt; ::= "prio"
 * | "rate"
 * | "weight"
 * </code>
 * </pre>
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class DataType implements AEmiliaBase 
	{

	/**
	 * Crea un oggetto DataType vuoto.
	 *
	 */
	public DataType() {
	}

	/**
	 * Stampa sullo standard output le informazioni relative a
	 * questo oggetto.
	 */
	public void print() 
		{
		System.out.println("DataType object");
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public DataType copy()
		{
		DataType a = new DataType();
		return a;
		}
}