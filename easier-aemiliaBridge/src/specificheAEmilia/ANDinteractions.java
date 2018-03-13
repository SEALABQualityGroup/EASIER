package specificheAEmilia;

/**
 * Rappresenta le And interazioni presenti in una specifica AEmilia.
 * Una and-interaction di un'istanza di un AET puo'
 * simultaneamente comunicare con diverse interazioni di altri AEI
 * (comunicazioni broadcast). Se none'vuota, <and_interactions>
 * ha la seguente sintassi:
 * <pre>
 * <code>
 * "AND" &lt;identifier_sequence&gt;
 * </code>
 * </pre>
 * dove <identifier_sequence>e'una sequenza non vuota di
 * identificatori di tipo azione separati da punti e virgola.
 * Un identificatore che si presenta in azioni di input non puo'
 * essere dichiarato un and-interaction.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ANDinteractions
	implements AEmiliaBase
	{

	/**
	 * Indica una sequenza di identificatori di azioni che
	 * che comunicano in modo broadcast o antibroadcast.
	 */
	private String[] actions; // sequenza di tipi azione

	/**
	 * Istanzia un oggetto ANDinteractions.
	 *
	 */
	public ANDinteractions() {
	}

	/**
	 * Crea un oggetto ANDinteractions con azioni specificate
	 * dal parametro del costruttore.
	 * @param actions - array di String che rappresentano le
	 * azioni che comunicano in modo (anti)broadcast.
	 */
	public ANDinteractions(String[] actions) {
		// assegna le azioni
		this.actions = actions;
	}

	/**
	 * Restituisce le azioni che comunicano in modo (anti)broadcast.
	 * @return un array di String.
	 */
	public String[] getActions() {
		// restituisce le azioni
		return this.actions;
	}

	/**
	 * Imposta le azioni di un oggetto ANDinteractions uguali
	 * a quelle specificate nel parametro.
	 * @param actions - array di String che costituiscono le nuove
	 * azioni dell'ANDinteractions.
	 */
	public void setActions(String[] actions) {
		// assegna le azioni
		this.actions = actions;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ANDinteractions object");
		System.out.println("Actions:");
		// stampa le azioni coinvolte nelle and-interazioni
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
	 * @param a - oggetto ANDinteractions da confrontare.
	 * @return un valore booleano che indica se questo oggetto
	 *e'uguale al parametro del metodo.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String[] actions;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ANDinteractions)) return false;
		ANDinteractions a = (ANDinteractions)o;
		boolean ris = true;
		// confronta le azioni delle due and-interazioni,
		// considerando anche il caso in cui non ci sono azioni.
		if (getActions() != null && a.getActions() != null)
			{
			if (getActions().length == a.getActions().length)
				{
				for (int i = 0; i < getActions().length; i++)
					{
					ris = ris &&
					getActions()[i].equals(a.getActions()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getActions() == null && a.getActions() == null)
			return ris;
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ANDinteractions copy()
		{
		ANDinteractions a = new ANDinteractions();
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
		String string = "AND ";
		for (int i = 0; i < this.actions.length - 1; i++)
			{
			string = string + this.actions[i] + ";";
			}
		string = string + this.actions[this.actions.length - 1];
		return string;
		}
	}