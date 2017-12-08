package specificheAEmilia;

/**
 * Rappresenta le interazioni di output che possono essere
 * presenti all'interno di una dichiarazione di un AET.
 * La sintassi in AEmiliae'la seguente:
 * <pre>
 * <code>
 * "OUTPUT_INTERACTIONS" &lt;output_interactions&gt;
 * </code>
 * </pre>
 * Un'interazione di outpute'classificata essere una
 * uni-interaction, and-interaction o or-interaction
 * dipendente dalla moltiplicità delle comunicazioni
 * in cui puo' essere coinvolta.
 * Sintatticamente parlando, ogni &lt;output_interactions&gt;
 *e'o void o ha il seguente formato:
 * <pre>
 * <code>
 * &lt;uni_interactions&gt; &lt;and_interactions&gt; &lt;or_interactions&gt;
 * </code>
 * </pre>
 * con almeno uno dei tre elementi, che rappresenta sequenze
 * di identificatori di tipo azione, essere non vuoti.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class OutputInteractions extends Interactions
	{

	/**
	 * Crea un oggetto OutputInteractions vuoto.
	 *
	 */
	public OutputInteractions() {
	}

	/**
	 * Crea un oggetto OutputInteractions con i tipi di interazione
	 * forniti come parametri al metodo.
	 * @param uni - oggetto UNIinteractions.
	 * @param and - oggetto ANDinteractions.
	 * @param or - oggetto ORinteractions.
	 */
	public OutputInteractions(UNIinteractions uni, ANDinteractions and, ORinteractions or) 
		{
		this.setUni(uni);
		this.setOr(or);
		this.setAnd(and);
		}

	/**
	 * Stampa sullo standard output le informazioni relative a
	 * questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("Interactions object");
		// spampo le informazioni relative alla superclasse
		super.print();
		}

	/**
	 * Confronta se questo oggettoe'uguale a quello referenziato
	 * dal parametro del metodo.
	 * @param ii - oggetto OutputInteractions.
	 * @return true se e solo se i due oggetti contengono
	 * le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private UNIinteractions Uni;
	 * private ORinteractions Or;
	 * private ANDinteractions And;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof OutputInteractions)) return false;
		OutputInteractions oi = (OutputInteractions)o;
		boolean ris = true;
		// possono non esserci and-interazioni
		if (getAnd() != null && oi.getAnd() != null)
			ris = getAnd().equals(oi.getAnd());
		else if (getAnd() == null && oi.getAnd() == null)
			ris = ris && true;
		else ris = ris && false;
		// possono non esserci or-interazioni
		if (getOr() != null && oi.getOr() != null)
			ris = getOr().equals(oi.getOr());
		else if (getOr() == null && oi.getOr() == null)
			ris = ris && true;
		else ris = ris && false;
		// possono non esserci uni-interazioni
		if (getUni() != null && oi.getUni() != null)
			ris = getUni().equals(oi.getUni());
		else if (getUni() == null && oi.getUni() == null)
			ris = ris && true;
		else ris = ris && false;
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public OutputInteractions copy()
		{
		OutputInteractions a = new OutputInteractions();
		if (getAnd() != null)
		a.setAnd(getAnd().copy());
		if (getOr() != null)
		a.setOr(getOr().copy());
		if (getUni() != null)
		a.setUni(getUni().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "OUTPUT_INTERACTIONS ";
		string = string + super.toString();
		return string;
		}
	}