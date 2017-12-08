package specificheAEmilia;

/**
 * Rappresenta un tipo di elemento architetturale (AET).
 * Una definizione di un AET ha la seguente forma:
 * <pre>
 * <code>
 * &lt;AET_header&gt; &lt;AET_behavior&gt; &lt;AET_interactions&gt;
 * </code>
 * </pre>
 * L'intestazione di un AET ha la seguente sintassi:
 * <pre>
 * <code>
 * "ELEM_TYPE" &lt;identifier&gt; "(" &lt;const_formal_par_decl_sequence&gt; ")"
 * </code>
 * </pre>
 * dove &lt;identifier&gt;e'il nome dell'AET e
 * &lt;const_formal_par_decl_sequence&gt;e'o void o una sequenza non
 * vuota di dichiarazioni separate da virgole di parametri
 * formali costanti.
 *
 * Il comportamento di un AET ha la seguente sintassi:
 * <pre>
 * <code>
 * "BEHAVIOR" &lt;behav_equation_sequence&gt;
 * </code>
 * </pre>
 * dove &lt;behav_equation_sequence&gt;e'una sequenza non vuota
 * di equazioni comportamentali EMPA (oggetti BehavEquation)
 * separate da punti e virgole.
 *
 * Le interazioni AET hanno il seguente formato:
 * <pre>
 * <code>
 * "INPUT_INTERACTIONS" &lt;input_interactions&gt;
 * "OUTPUT_INTERACTIONS" &lt;output_interactions&gt;
 * </code>
 * </pre>
 * Un'interazionee'classificata essere un'interazione di input
 * o un'interazione di output basandosi sulla sua direzione di
 * comunicazione. Sintatticamente parlando, ogni
 * &lt;input_interactions&gt; e &lt;output_interactions&gt;e'o void
 * o ha il seguente formato:
 * <pre>
 * <code>
 * &lt;uni_interactions&gt; &lt;and_interactions&gt; &lt;or_interactions&gt;
 * </code>
 * </pre>
 * con almeno uno dei tre elementi, che rappresenta sequenze
 * di identificatori di tipo azione, essere non vuoti.

 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ElemType implements AEmiliaBase
	{

	/**
	 * Header dell'AET.
	 */
	private Header header;

	/**
	 * behavior dell'AET.
	 */
	private AETbehavior behavior;

	/**
	 * interactions dell'AET.
	 */
	private AETinteractions interactions;

	/**
	 * Crea un oggetto ElemType vuoto.
	 *
	 */
	public ElemType() {
	}

	/**
	 * Crea un oggetto ElemType con intestazione, comportamento e
	 * interazioni fornite come parametri.
	 * @param header - oggetto Header.
	 * @param behavior - oggetto AETbehavior.
	 * @param interactions - oggetto AETinteractions.
	 */
	public ElemType(Header header, AETbehavior behavior, AETinteractions interactions) {
		this.header = header;
		this.behavior = behavior;
		this.interactions = interactions;
	}

	/**
	 * Restituisce l'intestazione dell'AET.
	 * @return oggetto Header.
	 */
	public Header getHeader()
		{
		return this.header;
		}

	/**
	 * Restituisce il comportamento dell'AET.
	 * @return oggetto AETbehavior.
	 */
	public AETbehavior getBehavior()
		{
		return this.behavior;
		}

	/**
	 * Restituisce le interazioni dell'AET.
	 * @return oggetto AETinteractions.
	 */
	public AETinteractions getInteractions()
		{
		return this.interactions;
		}

	/**
	 * Assegna un nuovo comportamento all'AET.
	 * @param behavior - oggetto AETbehavior.
	 */
	public void setBehavior(AETbehavior behavior) {
		this.behavior = behavior;
	}

	/**
	 * Assegna nuove interazioni all'AET.
	 * @param interactions - oggetto AETinteractions.
	 */
	public void setInteractions(AETinteractions interactions) {
		this.interactions = interactions;
	}

	/**
	 * Assegna una nuova intestazione all'AET.
	 * @param header - oggetto Header.
	 */
	public void setHeader(Header header) {
		this.header = header;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ElemType object");
		System.out.println("Architectural element type header:");
		getHeader().print();
		System.out.println("Architectural element type behavior:");
		getBehavior().print();
		System.out.println("Architectural element type interactions:");
		getInteractions().print();
		}

	/**
	 * Confronta questo oggetto con quello referenziato dal
	 * parametro del metodo.
	 * @param et - oggetto ElemType da confrotare.
	 * @return true se e solo se i due oggetti confrontati
	 * contengono le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Header header;
	 * private AETbehavior behavior;
	 * private AETinteractions interactions;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ElemType)) return false;
		ElemType et = (ElemType)o;
		boolean ris = getBehavior().equals(et.getBehavior());
		ris = ris && getInteractions().equals(et.getInteractions());
		ris = ris && getHeader().equals(et.getHeader());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ElemType copy()
		{
		ElemType a = new ElemType();
		if (getBehavior() != null)
		a.setBehavior(getBehavior().copy());
		if (getInteractions() != null)
		a.setInteractions(getInteractions().copy());
		if (getHeader() != null)
		a.setHeader(getHeader().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "ELEM_TYPE " + this.header.toString() + " ";
		string = string + this.behavior.toString() + " ";
		string = string + this.interactions.toString();
		return string;
		}
	}