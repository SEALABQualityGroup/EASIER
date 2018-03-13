package specificheAEmilia;


/**
 * Rappresenta un tipo di dato normale. In AEmilia ha la
 * seguente sintassi:
 * <pre>
 * <code>
 * &lt;normal_type&gt; ::= "integer"
 * | "integer" "(" &lt;expr&gt; ".." &lt;expr&gt; ")"
 * | "real"
 * | "boolean"
 * </code>
 * </pre>
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 */

public class NormalType 
	extends DataType 
	{

	/**
	 * Crea un oggetto NormalType vuoto.
	 *
	 */
	public NormalType() 
		{
		}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print() 
		{
		System.out.println("NormalType object");
		super.print();
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public NormalType copy()
		{
		NormalType a = new NormalType();
		return a;
		}
	}
