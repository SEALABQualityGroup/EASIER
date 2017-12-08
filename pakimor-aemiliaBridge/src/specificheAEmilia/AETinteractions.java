package specificheAEmilia;

/**
 * Rappresenta le interazioni di un tipo di elemento architetturale.
 * In AEmilia, per specificare tale interazioni, si usa la seguente
 * sintassi:
 * <pre>
 * <code>
 * "INPUT_INTERACTIONS" &lt;input_interactions&gt; "OUTPUT_INTERACTIONS" &lt;output_interactions&gt;
 * </code>
 * </pre>
 * Un'interazionee'classificata essere un'interazione di input o
 * un'interazione di output basandosi sulla sua direzione di
 * comunicazione. Poi, un'interazione di input o output e'
 * classificata essere una uni-interaction, and-interaction o
 * or-interaction dipendente dalla moltiplicit� delle comunicazioni
 * in cui puo' essere coinvolta. Sintatticamente parlando, ogni
 * &lt;input_interactions&gt; e &lt;output_interactions&gt;e'o
 * void o ha il seguente formato:
 *
 * &lt;uni_interactions&gt; &lt;and_interactions&gt; &lt;or_interactions&gt;
 *
 * con almeno uno dei tre elementi, che rappresenta sequenze di
 * identificatori di tipo azione, essere non vuoti.
 * Una uni-interaction di un'istanza di un AET puo' comunicare solo
 * con un'interazione di un'altro AEI. Se none'vuota,
 * &lt;uni_interactions&gt; ha la seguente sintassi:
 *
 * "UNI" &lt;identifier_sequence&gt;
 *
 * dove &lt;identifier_sequence&gt;e'una sequenza non vuota di
 * identificatori di tipo azione separati da punti e virgole.
 * Una and-interaction di un'istanza di un AET puo' simultaneamente
 * comunicare con diverse interazioni di altri AEI (comunicazioni
 * broadcast). Se none'vuota, &lt;and_interactions> ha la seguente
 * sintassi:
 *
 * "AND" &lt;identifier_sequence&gt;
 *
 * dove &lt;identifier_sequence&gt;e'una sequenza non vuota di
 * identificatori di tipo azione separati da punti e virgole.
 * Un identificatore che si presenta in azioni di input non puo'
 * essere dichiarato un and-interaction. Una or-interazione di
 * un'istanza di un AET puo' comunicare con una delle diverse
 * interazioni di altri AEI. Se none'vuota, &lt;or_interactions>
 * ha la seguente sintassi:
 *
 * "OR" &lt;identifier_sequence&gt;
 *
 * dove &lt;identifier_sequence&gt;e'una sequenza non vuota di
 * identificatori di tipo azione separati da punti e virgole.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class AETinteractions implements AEmiliaBase
	{

	/**
	 * Indica le interazioni di input. InIn puo' essere null
	 */
	private InputInteractions InIn;

	/**
	 * Indica le interazioni di output. OuIn puo' essere null 
	 */
	private OutputInteractions OuIn;

	/**
	 * Costruttore vuoto per istanziare un oggetto AETinteractions
	 *
	 */
	public AETinteractions() {
	}

	/**
	 * Istanzia un oggetto AETinteractions con le interazioni
	 * di input e output indicate dai parametri
	 * @param inIn - oggetto InputInteractions che contiene
	 * le interazioni di input del tipo di elemento architetturale.
	 * @param ouIn - oggetto OutputInteractions che contiene
	 * le interazioni di output del tipo di elemento architetturale.
	 */
	public AETinteractions(InputInteractions inIn, OutputInteractions ouIn) {
		// assegna le interazioni di input
		this.InIn = inIn;
		// assegna le interazioni di output
		this.OuIn = ouIn;
	}

	/**
	 * Restituisce le interazioni di input di un
	 * tipo architetturale.
	 * @return un oggetto InputInteractions.
	 */
	public InputInteractions getInIn() {
		// restituisce le interazioni di input
		return this.InIn;
	}

	/**
	 * Restituisce le interazioni di output di un
	 * tipo architetturale.
	 * @return un oggetto OutputInteractions.
	 */
	public OutputInteractions getOuIn() {
		// restituisce le interazioni di output
		return this.OuIn;
	}

	/**
	 * Imposta le interazioni di input uguali a quelle
	 * indicate dal parametro.
	 * @param inIn - oggetto InputInteractions che contiene
	 * le nuove interazioni di input.
	 */
	public void setInIn(InputInteractions inIn) {
		// assegna le interazioni di input
		this.InIn = inIn;
	}

	/**
	 * Imposta le interazioni di output uguali a quelle
	 * indicate dal parametro.
	 * @param ouIn - oggetto OutputInteractions che contiene
	 * le nuove interazioni di output.
	 */
	public void setOuIn(OutputInteractions ouIn) {
		// assegna le interazioni di output
		this.OuIn = ouIn;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * ad un oggetto AETinteractions.
	 *
	 */
	public void print()
		{
		System.out.println("AETInteractions object");
		System.out.println("Input interactions");
		// stampa le interazioni di input
		if (getInIn() != null) getInIn().print();
		System.out.println("Output interactions");
		// stampa le interazioni di output
		if (getOuIn() != null) getOuIn().print();
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del
	 * metodo.
	 * @param aeti - oggetto AETinteractions da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private InputInteractions InIn;
	 * private OutputInteractions OuIn;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof AETinteractions)) return false;
		AETinteractions aeti = (AETinteractions)o;
		boolean ris = true;
		// confronta le interazioni di input delle due
		// istanze, considerando anche il caso in cui non ci
		// sono tali interazioni di input
		if (getInIn() != null && aeti.getInIn() != null)
			ris = ris && getInIn().equals(aeti.getInIn());
		else if (getInIn() == null && aeti.getInIn() == null)
			ris = ris && true;
		else ris = ris && false;
		// confronta le interazioni di output delle due
		// istanze, considerando anche il caso in cui non ci
		// sono tali interazioni di output
		if (getOuIn() != null && aeti.getOuIn() != null)
			ris = ris && getOuIn().equals(aeti.getOuIn());
		else if (getOuIn() == null && aeti.getOuIn() == null)
			ris = ris && true;
		else ris = ris && false;
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public AETinteractions copy()
		{
		AETinteractions a = new AETinteractions();
		if (getInIn() != null)
		a.setInIn(getInIn().copy());
		if (getOuIn() != null)
		a.setOuIn(getOuIn().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = (this.InIn == null ? "INPUT_INTERACTIONS void " : (this.InIn.toString() + " "));
		string = string + (this.OuIn == null ? "OUTPUT_INTERACTIONS void" : this.OuIn.toString());
		return string;
		}
	}