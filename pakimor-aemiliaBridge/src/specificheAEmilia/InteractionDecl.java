package specificheAEmilia;

/**
 * Rappresenta una dichiarazione di interazione architetturale.
 * In AEmilia, ha la seguente forma:
 * <pre>
 * <code>
 * &lt;architectural_interaction_decl&gt; ::=
 * &lt;identifier&gt; ["[" &lt;expr&gt; "]"] "." &lt;identifier&gt;
 * | "FOR_ALL" &lt;identifier&gt; "IN" &lt;expr&gt; ".." &lt;expr&gt;
 * &lt;identifier&gt; "[" &lt;expr&gt; "]" "." &lt;identifier&gt;
 * </code>
 * </pre>
 * Nella sua forma piu' semplice, una dichiarazione di interazione
 * architetturale contiene l'identificatore di un aei a cui
 * l'interazione appartiene, una possibile espressione intera
 * racchiusa tra parentesi quadre, che rappresenta un selettore e
 * deve essere privo di identificatori non dichiarati e
 * invocazioni a generatori di numeri pseudo-casuali, e
 * l'identificatore dell'interazione. I soli identificatori che
 * si possono presentare nella possibile espressione di selezione
 * sono quelli dei parametri formali costanti dichiarati
 * nell'intestazione del tipo architetturale. La seconda forma
 *e'utile per dichiarare in modo conciso diverse interazioni
 * architetturali attraverso un meccanismo di indicizzazione.
 * Questo richiede la specifica dell'identificatore indice,
 * che si puo' successivamente presentare nell'espressione di
 * selezione, insieme con il suo intervallo, chee'dato da due
 * espressioni intere. Queste due espressioni devono essere prive
 * da identificatori non dichiarati e invocazioni a generatori di
 * numeri pseudo-casuali, con il valore della prima espressione
 * non piu' grande del valore della seconda espressione.

 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class InteractionDecl implements AEmiliaBase
	{

	/**
	 * Istanza di un elemento architetturale che interagisce
	 * con l'esterno.
	 */
	private AEIident aei;

	/**
	 * Nome dell'azione che costituisce l'interazione
	 * architetturale.
	 */
	private String interaction;

	/**
	 * Crea un oggetto InteractionDecl vuoto.
	 *
	 */
	public InteractionDecl() {
	}

	/**
	 * Crea un oggetto InteractionDecl con le iformazioni
	 * necessarie fornite come parametri.
	 * @param aei - oggetto String.
	 * @param selector - oggetto Expression.
	 * @param interaction - oggetto String.
	 */
	public InteractionDecl(String aei, Expression selector, String interaction) 
		{
		AEIident aeIident = new AEIident(aei, selector);
		this.aei = aeIident;
		this.interaction = interaction;
		}

	/**
	 * Restituisce l'aei di apparetenenza dell'interazione.
	 */
	public AEIident getAei() 
		{
		return this.aei;
		}	

	/**
	 * Restituisce il nome dell'azione che costituisce
	 * l'interazione con l'esterno rispetto al tipo
	 * architetturale specificato in AEmilia.
	 * @return oggetto String.
	 */
	public String getInteraction() 
		{
		return this.interaction;
		}

	/**
	 * Assegna il nome dell'aei di appartenenza dell'interazione.
	 */
	public void setAei(AEIident aei) 
		{
		this.aei = aei;
		}

	/**
	 * Assegna il nome dell'azione che costituisce l'interazione
	 * architetturale.
	 * @param interaction - oggetto String.
	 */
	public void setInteraction(String interaction) 
		{
		this.interaction = interaction;
		}

	/**
	 * Stampa sullo standard output le informazioni di
	 * un oggetto InteractionDecl.
	 *
	 */
	public void print()
		{
		System.out.println("InteractionDecl object");
		System.out.println("Aei: "+getAei());
		System.out.println("Interaction: "+getInteraction());
		}

	/**
	 * Restituisce true se e solo se ide'uguale a questo oggetto.
	 * @param id - oggetto InteractionDecl.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String aei;
	 * private Expression selector;
	 * private String interaction;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof InteractionDecl)) return false;
		InteractionDecl id = (InteractionDecl)o;
		boolean ris = getAei().equals(id.getAei());
		ris = ris && getInteraction().equals(id.getInteraction());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public InteractionDecl copy()
		{
		InteractionDecl a = new InteractionDecl();
		a.setAei(getAei().copy());
		a.setInteraction(new String(getInteraction()));
		return a;
		}

	@Override
	public String toString() 
		{
		String string = this.aei +
			"." + this.interaction;
		return string;
		}
	}