package specificheAEmilia;

/**
 * Rappresenta le interazioni architetturali presenti in una
 * specifica AEmilia.
 * Le interazioni architetturali sono dichiarati attraverso la
 * seguente sintassi:
 * <pre>
 * <code>
 * "ARCHI_INTERACTIONS" &lt;pe_architectural_interaction_decl&gt;
 * </code>
 * </pre>
 * dove &lt;pe_architectural_interaction_decl&gt;e'o void o una
 * sequenza non vuota di dichiarazioni di interazioni
 * architetturali separate da punti e virgola.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ArchiInteractions implements AEmiliaBase
	{

	/**
	 * Array di dichiarazioni di interazioni architetturali. interactions puo' essere null.
	 */
	private InteractionDecl[] interactions;

	/**
	 * Crea un oggetto vuoto di tipo ArchiInteractions.
	 *
	 */
	public ArchiInteractions() {
	}

	/**
	 * Crea un oggetto ArchiInteractions con interazioni
	 * architetturali fernite come parametro.
	 * @param interactions - array di oggetti InteractionDecl.
	 */
	public ArchiInteractions(InteractionDecl[] interactions) {
		// assegna le dichiarazioni di interazioni architetturali
		this.interactions = interactions;
	}

	/**
	 * Restituisce l'insieme delle dichiarazioni di interazioni
	 * presenti in questo oggetto.
	 * @return - array di oggetti InteractionDecl.
	 */
	public InteractionDecl[] getInteractions() {
		// restituisce le dichiarazioni di interazioni
		// architetturali
		return this.interactions;
	}

	/**
	 * Imposta le dichiarazioni di interazioni architetturali
	 * uguali a quelle indicate dal parametro del metodo.
	 * @param interactions - array di oggetti InteractionDecl.
	 */
	public void setInteractions(InteractionDecl[] interactions) {
		// assegna le dichiarazioni di interazioni architetturali
		this.interactions = interactions;
	}

	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ArchiInteractions object");
		// stampa le dichiarazioni di interazioni architetturali
		// anche nel caso in cui non sono presenti tali
		// dichiarazioni
		if (getInteractions() != null)
			{
			System.out.println("Interactions declarations:");
			for (int i = 0; i < getInteractions().length; i++)
				{
				getInteractions()[i].print();
				}
			}
		}

	/**
	 * Verifica se questo oggettoe'uguale a quello referenziato
	 * dal parametro del metodo.
	 * @param ai - oggetto ArchiInteractions da confrontare.
	 * @return un valore booleano indicante se i due oggetti
	 * confrontati sono uguali.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private InteractionDecl[] interactions;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ArchiInteractions)) return false;
		ArchiInteractions ai = (ArchiInteractions)o;
		boolean ris = true;
		// confronta le interazioni architetturali delle due
		// istanze, considerando anche il caso in cui non ci
		// sono interazioni architetturali dichiarate
		if (getInteractions() != null && ai.getInteractions() != null)
			{
			if (getInteractions().length == ai.getInteractions().length)
				{
				for (int i = 0; i < getInteractions().length; i++)
					{
					ris = ris &&
					getInteractions()[i].equals(ai.getInteractions()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getInteractions() == null && ai.getInteractions() == null)
			return ris;
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ArchiInteractions copy()
		{
		ArchiInteractions a = new ArchiInteractions();
		if (getInteractions() != null)
			{
			a.setInteractions(new InteractionDecl[getInteractions().length]);
			for (int i = 0; i < getInteractions().length; i++)
				{
				a.getInteractions()[i] = getInteractions()[i].copy();
				}
			}
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "ARCHI_INTERACTIONS ";
		if (this.interactions == null || this.interactions.length == 0)
			{
			string = string + "void";
			}
		else
			{
			for (int i = 0; i < this.interactions.length - 1; i++)
				{
				string = string + this.interactions[i].toString() + ";";
				}
			string = string + this.interactions[this.interactions.length - 1].toString();
			}
		return string;
		}
	}