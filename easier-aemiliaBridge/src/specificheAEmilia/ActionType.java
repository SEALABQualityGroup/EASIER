package specificheAEmilia;

/**
 * Corrisponde ad un tipo azione di una specifica AEmilia, la
 * cui sintassie'la seguente:
 * <pre>
 * <code>
 * &lt;action_type&gt; ::= &lt;identifier&gt;
 * | &lt;identifier&gt; "?" "(" &lt;local_var_sequence&gt; ")"
 * | &lt;identifier&gt; "!" "(" &lt;expr_sequence&gt; ")"
 * </pre>
 * </code>
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

public class ActionType implements AEmiliaBase
	{

	/**
	 * Indica il nome del tipo azione.
	 */
	private String name;

	/**
	 * Costruttore vuoto per istanziare un oggetto ActionType.
	 *
	 */
	public ActionType() {
	}

	/**
	 * Costruttore che prende come argomento il nome del tipo
	 * azione da istanziare
	 * @param name - oggetto String che contiene il nome del tipo
	 * azione.
	 */
	public ActionType(String name) {
		// assegna un nome al tipo azione
		this.name = name;
	}

	/**
	 * Restituisce il nome del tipo azione
	 * @return - oggetto String che denota il nome del tipo azione.
	 */
	public String getName()
		{
		// restituisce il nome
		return this.name;
		}

	/**
	 * Imposta il nome del tipo azione secondo l'argomento del
	 * metodo.
	 * @param name - oggetto String che contiene il nuovo nome
	 * per il tipo azione.
	 */
	public void setName(String name) {
		// assegna un nome
		this.name = name;
	}

	/**
	 * Stampa sullo standard output le informazioni associate ad
	 * un tipo azione.
	 *
	 */
	public void print()
		{
		// stampa il nome del tipo azione
		System.out.println("ActionType object");
		System.out.println("Action type name: "+getName());
		}

	/**
	 * Confronta questo oggetto con l'argomento del metodo.
	 * @param ai - oggetto ActionType da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * confrontati sono uguali.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String name;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ActionType)) return false;
		ActionType ai = (ActionType)o;
		boolean ris = true;
		// confronta i due tipi azione considerando anche il
		// caso in cui il loro nome sia null
		if (getName() != null && ai.getName() != null)
			ris = ris && getName().equals(ai.getName());
		else if (getName() == null && ai.getName() == null)
			ris = ris && true;
		else ris = ris && false;
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ActionType copy()
		{
		ActionType a = new ActionType();
		a.setName(new String(getName()));
		return a;
		}

	@Override
	public String toString() 
		{
		return this.name;
		}	
	}