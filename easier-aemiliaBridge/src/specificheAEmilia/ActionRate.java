package specificheAEmilia;

/**
 * Indica il tasso di un'azione ed ha la seguente sintassi:
 * <pre>
 * <code>
 * &lt;action_rate&gt; ::= "exp" "(" &lt;expr&gt; ")"
 * | "inf" "(" &lt;expr&gt; "," &lt;expr&gt; ")"
 * | "inf"
 * | "_" "(" &lt;expr&gt; "," &lt;expr&gt; ")"
 * | "_"
 * </pre>
 * </code>
 * Il tasso di un'azione temporizzata esponenzialmente (exp)e'data
 * da un'espressione, il cui valore deve essere un reale positivo,
 * ede'interpretato come il tasso della variabile casuale
 * distribuita esponenzialmente che descrive la durata
 * dell'azione. Il tasso di un'azione immediata (inf)e'espresso
 * attraverso una priorita', data da un'espressione il cui
 * valore deve essere un intero non minore di uno, e un peso,
 * dato da un'espressione il cui valore deve essere un reale
 * positivo. Il tasso di un'azione passiva (_)e'di nuovo espressa
 * attraverso attraverso due espressioni che denotano una priorita'
 * ed un peso, rispettivamente. Se none'specificato, i valori
 * della priorita' e il peso di un'azione passiva o immediata
 * sono uno.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ActionRate implements AEmiliaBase 
	{

	/**
	 * Costruttore vuoto per istanziare un oggetto ActtionRate
	 *
	 */
	public ActionRate() {
		}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * ad un oggetto ActionRate.
	 *
	 */
	public void print(){
		System.out.println("ActionRate object");
		}

	/**
	 * Indica se questo oggettoe'uguale all'argomento del metodo.
	 * @param ar - oggetto ActionRate da cofrontare.
	 * @return un valore booleano che indica se questo
	 * oggettoe'uguale all'argomento del suo metodo.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * void
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ActionRate)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ActionRate copy()
		{
		ActionRate a = new ActionRate();
		return a;
		}
}
