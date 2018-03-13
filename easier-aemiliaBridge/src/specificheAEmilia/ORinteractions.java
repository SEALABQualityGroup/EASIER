package specificheAEmilia;

/**
 * Rappresenta le Or interazioni di una specifica AEmilia.
 * Una or-interazione di un'istanza di un AET puo' comunicare
 * con una delle diverse interazioni di altri AEI. Se non e'
 * vuota, &lt;or_interactions&gt; ha la seguente sintassi:
 * <pre>
 * <code>
 * "OR" &lt;identifier_sequence&gt;
 * </code>
 * </pre>
 * dove &lt;identifier_sequence&gt;e'una sequenza non vuota di
 * identificatori di tipo azione separati da punti e virgola.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ORinteractions implements AEmiliaBase
	{

	/**
	 * Indica una sequenza di identificatori di azioni che
	 * che comunicano in modo server-clients.
	 */

	private String[] actions; // sequenza di tipi azione

	/**
	 * Istanzia un oggetto ORinteractions.
	 *
	 */
	public ORinteractions() {
	}

	/**
	 * Crea un oggetto ORinteractions con azioni specificate
	 * dal parametro del costruttore.
	 * @param actions - array di String che rappresentano le
	 * azioni che comunicano in modo server-clients.
	 */
	public ORinteractions(String[] actions) {
		this.actions = actions;
	}

	/**
	 * Restituisce le azioni che comunicano in modo server-clients.
	 * @return un array di String.
	 */
	public String[] getActions() {
		return this.actions;
	}

	/**
	 * Imposta le azioni di un oggetto Orinteractions uguali
	 * a quelle specificate nel parametro.
	 * @param actions - array di String che costituiscono le nuove
	 * azioni dell'ORinteractions.
	 */
	public void setActions(String[] actions) {
		this.actions = actions;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ORinteractions object");
		System.out.println("Actions:");
		for (int i=0; i < getActions().length; i++)
			{
			System.out.print("Action number ");
			System.out.print(i);
			System.out.print(": ");
			System.out.println(getActions()[i]);
			}
		}

	/**
	 * Verifica se questo oggettoe'uguale a quello referenziato
	 * dal parametro.
	 * @param a - oggetto ORinteractions da confrontare.
	 * @return un valore booleano che indica se questo oggetto
	 *e'uguale al parametro del metodo.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String[] actions;
	 */

	public boolean equals(Object o1)
		{
		if (!(o1 instanceof ORinteractions)) return false;
		ORinteractions o = (ORinteractions)o1;
		boolean ris = true;
		// anche se non rispetta la grammatica AEmilia
		// si considera anche il caso in cui possono non
		// esserci azioni
		if (getActions() != null && o.getActions() != null)
			{
			if (getActions().length == o.getActions().length)
				{
				for (int i = 0; i < getActions().length; i++)
					{
					ris = ris &&
					getActions()[i].equals(o.getActions()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getActions() == null && o.getActions() == null)
			return ris;
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ORinteractions copy()
		{
		ORinteractions a = new ORinteractions();
		if (getActions() != null)
			{
			String[] strings = new String[getActions().length];
			for (int i = 0; i < getActions().length; i++)
				{
				strings[i] = new String(getActions()[i]);
				}
			a.setActions(strings);
			}
		return a;
		}
	
	@Override
	public String toString() 
		{
		String string = "OR ";
		for (int i = 0; i < this.actions.length - 1; i++)
			{
			string = string + this.actions[i] + ";";
			}
		string = string + this.actions[this.actions.length - 1];
		return string;
		}
	}