package specificheAEmilia;

/**
 * Rappresenta le Uni interazioni che possono essere presenti in
 * una specifica AEmilia.
 * Una uni-interaction di un'istanza di un AET puo' comunicare solo
 * con un'interazione di un'altro AEI. Se none'vuota,
 * &lt;uni_interactions&gt; ha la seguente sintassi:
 * <pre>
 * <code>
 * "UNI" &lt;identifier_sequence&gt;
 * </code>
 * </pre>
 *
 * dove &lt;identifier_sequence&gt;e'una sequenza non vuota di
 * identificatori di tipo azione separati da punti e virgole.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 */

public class UNIinteractions implements AEmiliaBase
	{

	/**
	 * Indica una sequenza di identificatori di azioni.
	 */
	private String[] actions; // sequenza di tipi azione

	/**
	 * Istanzia un oggetto UNIinteractions.
	 *
	 */
	public UNIinteractions() {
	}

	/**
	 * Crea un oggetto UNIinteractions con azioni specificate
	 * dal parametro del costruttore.
	 * @param actions - array di String che rappresentano le
	 * azioni.
	 */
	public UNIinteractions(String[] actions) {
		this.actions = actions;
	}

	/**
	 * Restituisce le azioni.
	 * @return un array di String.
	 */
	public String[] getActions() {
		return this.actions;
	}

	/**
	 * Imposta le azioni di un oggetto UNIinteractions uguali
	 * a quelle specificate nel parametro.
	 * @param actions - array di String che costituiscono le nuove
	 * azioni dell'UNIinteractions.
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
		System.out.println("UNIinteractions object");
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
	 * @param u - oggetto UNIinteractions da confrontare.
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
		if (!(o instanceof UNIinteractions)) return false;
		UNIinteractions u = (UNIinteractions)o;
		boolean ris = true;
		// si considera anche il caso in cui possono non
		// esserci azioni
		if (getActions() != null && u.getActions() != null)
			{
			if (getActions().length == u.getActions().length)
				{
				for (int i = 0; i < getActions().length; i++)
					{
					ris = ris &&
					getActions()[i].equals(u.getActions()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getActions() == null && u.getActions() == null)
			return ris;
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public UNIinteractions copy()
		{
		UNIinteractions a = new UNIinteractions();
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
		String string = "UNI ";
		for (int i = 0; i < this.actions.length - 1; i++)
			{
			string = string + this.actions[i] + ";";
			}
		string = string + this.actions[this.actions.length - 1];
		return string;
		}
	}