package specificheAEmilia;

/**
 * Rappresenta un termine di processo presente in una specifica
 * AEmilia.
 * Un termine di processo segue l'intestazione di equazione comportamentale ed e'
 * definita nel seguente modo:
 * <pre>
 * <code>
 * &lt;process_term&gt; ::= "stop"
 * | &lt;action&gt; "." &lt;process_term_1&gt;
 * | "choice" "{" &lt;process_term_2_sequence&gt; "}"
 * &lt;process_term_1&gt; ::= &lt;process_term&gt;
 * | &lt;identifier&gt; "(" &lt;actual_par_sequence&gt; ")"
 * &lt;process_term_2&gt; ::=
 * ["cond" "(" &lt;expr&gt; ")" "->"] &lt;process_term&gt;
 * </code>
 * </pre>
 * &lt;process_term_2_sequence>e'una sequenza di almeno due termini di processo separati
 * da virgole, ognuna possibilmente preceduta da un'espressione booleana che stabilisce
 * la condizione sotto cuie'disponibile.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ProcessTerm implements AEmiliaBase
	{

	/*
	 * affinche' il processo possa essere eseguito la condizione
	 * deve essere vera.
	 */
	/**
	 * condition per l'esecuzione del termine di processo.
	 */
	private Expression condition = new Boolean(true);

	/**
	 * Crea un oggetto ProcessTerm vuoto.
	 *
	 */
	public ProcessTerm() {
	}

	/**
	 * Crea un oggetto ProcessTerm con condizione di esecuzione
	 * fornita come parametro.
	 * @param condition - oggetto Expression.
	 */
	public ProcessTerm(Expression condition) {
		this.condition = condition;
	}

	/**
	 * Restituisce la condizione di esecuzione del termine
	 * di processo.
	 * @return un oggetto Expression.
	 */
	public Expression getCondition()
		{
		return this.condition;
		}

	/**
	 * Assegna una condizione di esecuzione a questo termine di
	 * processo.
	 * @param condition - oggetto Expression.
	 */
	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ProcessTerm object");
		if (getCondition() != null)
			{
			System.out.println("Execution condition:");
			getCondition().print();
			}
		}

	/**
	 * Restituisce true se e solo se questo termine di processo
	 *e'uguale a pt.
	 * @param pt - oggetto ProcessTerm.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression condition;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ProcessTerm)) return false;
		ProcessTerm pt = (ProcessTerm)o;
		try {
			boolean ris = true;
			// si considera il caso in cui la condizione
			// di esecuzione del termine di processo puo'
			// non essere stata inizializzata
			if (getCondition() != null && pt.getCondition() != null)
				ris = ris && getCondition().equals(pt.getCondition());
			else if (getCondition() == null && pt.getCondition() == null)
				ris = ris && true;
			else ris = ris && false;
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
	public ProcessTerm copy()
		{
		ProcessTerm a = new ProcessTerm();
		if (getCondition() != null)
		a.setCondition(getCondition().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		if (!this.condition.equals(new Boolean(true)))
			string = "cond (" + this.condition.toString()+ ") ->";
		return string;
		}
	}