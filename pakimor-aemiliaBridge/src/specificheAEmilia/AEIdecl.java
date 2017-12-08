package specificheAEmilia;

/**
 * Indica una dichiarazione di un'istanza di un elemento
 * architetturale. In AEmilia, la sintassie'la seguente:
 * <pre>
 * <code>
 * &lt;AEI_decl&gt; ::= &lt;identifier&gt; ["[" &lt;expr&gt; "]"] ":"
 * &lt;identifier&gt; "(" &lt;pe_expr_sequence&gt; ")"
 * | "FOR_ALL" &lt;identifier&gt; "IN" &lt;expr&gt; ".." &lt;expr&gt;
 * &lt;identifier&gt; "[" &lt;expr&gt; "]" ":"
 * &lt;identifier&gt; "(" &lt;pe_expr_sequence&gt; ")"
 * </code>
 * </pre>
 * Nella sua forma più semplice, una dichiarazione AEI contiene
 * l'identificatore di un AEI, un'espressione intera racchiusa
 * tra parentesi quadre, che rappresenta un selettore e deve
 * essere libero da identificatori non dichiarati e invocazioni
 * a generatori di numeri pseudo-casuali, l'identificatore
 * dell'AET relativo, che deve essere stato definito nella prima
 * sezione della specifica AEmilia, e una sequenza possibilmente
 * vuota di espressioni libere da invocazioni a generatori di
 * numeri pseudo-casuali, che forniscono i valori attuali per i
 * parametri formali costanti dell'AET. I soli identificatori che
 * si possono presentare nell'espressione di selezione e nei
 * parametri attuali sono i parametri formali costanti dichiarati
 * nell'intestazione del tipo architetturale.
 * La seconda forma (rappresentabile con un oggetto AEIdecl)e'utile per dichiarare
 * in maniera concisa diverse istanze dello stesso AET attraverso
 * un meccanismo di indicizzazione. Questa richiede la specifica
 * dell'identificatore indice, che si puo' presentare
 * nell'espressione di selezione e nei
 * parametri attuali, insieme con il suo intervallo, chee'dato da due espressioni intere.
 * Queste due espressioni devono essere libere da identificatori non dichiarati e
 * invocazioni a generatori di numeri pseudo-casuali, con il valore della prima espressione
 * non più grande del valore della seconda espressione.
 * Anche nella forma più semplice di una dichiarazione di AEI l'identificatore di un AEI
 * puo' essere aumentato con un'espressione di selezione. Questoe'utile ogni volta
 *e'desiderabile dichiarare un insieme di istanze indicizzate dello stesso AET, ma solo
 * qualcuna di loro ha un'espressione di selezione comune.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class AEIdecl
	implements AEmiliaBase
	{

	private AEIident aeIident;
	
	/**
	 * Indica il tipo di elemento architetturale al quale
	 * l'istanza appartiene.
	 */
	private String aet;

	/**
	 * Parametri attuali utilizzati per istanziare un tipo
	 * architetturale. E' un attributo diverso da null.
	 */
	private Expression[] actualParams;

	/**
	 * Costruttore vuoto per istanziare un oggetto AEIdecl.
	 *
	 */
	public AEIdecl() {
	}

	/**
	 * Costruttore che istanzia un oggetto AEIdecl con nome,
	 * selettore, tipo architetturale e sequenza di parametri
	 * attuali forniti come argomenti.
	 * @param name - nome dell'istanza.
	 * @param selector - espressione di selezione di un'istanza.
	 * @param aet - nome del tipo di elemento architetturale
	 * a cui l'istanza appartiene.
	 * @param actualParams - parametri attuali utilizzati per
	 * istanziare il tipo architetturale.
	 */
	public AEIdecl(String name, Expression selector, String aet, Expression[] actualParams)
		{
		this.aeIident = new AEIident(name,selector);
		// assegna un tipo
		this.aet = aet;
		// assegna i parametri attuali
		this.actualParams = actualParams;
		}

	/**
	 * Restituisce il tipo di elemento architetturale a cui
	 * un'istanza appartiene.
	 * @return il nome di un tipo di elemento architetturale.
	 */
	public String getAET()
		{
		// restituisce un tipo di elemento architetturale
		return this.aet;
		}

	/**
	 * Restituisce un array di parametri attuali per
	 * istanziare un tipo architetturale.
	 * @return un array di oggetti Expression.
	 */
	public Expression[] getActualParams()
		{
		// restituisce i parametri attuali
		return this.actualParams;
		}

	/**
	 * Imposta il tipo architetturale a cui un'istanza
	 * di un elemento appartiene uguale al parametro di tale
	 * metodo.
	 * @param tipoArch - nuovo nome del tipo architetturale
	 * a cui l'istanza appartiene.
	 */
	public void setAET(String aet)
		{
		// assegna un tipo di elementi architetturali
		this.aet = aet;
		}

	/**
	 * Imposta i parametri attuali uguali a quelli indicati
	 * nell'argomento di tale metodo.
	 * @param actualParams - un array di oggetti Expression
	 * che indicano i nuovi parametri attuali
	 * per l'instanziazione del tipo architetturale.
	 */
	public void setActualParams(Expression[] actualParams)
		{
		// assegna parametri attuali
		this.actualParams = actualParams;
		}

	public AEIident getAeIident() {
		return aeIident;
	}

	public void setAeIident(AEIident aeIident) {
		this.aeIident = aeIident;
	}

	/**
	 * Stampa sullo standard output le informazione
	 * relative a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("AEIdecl object");
		// stampa il nome dell'istanza
		System.out.println("AEI ident: "+getAeIident());
		// stampa l'aet di appartenenza
		System.out.println("aet relative: "+getAET());
		// stampa i parametri attuali
		if (getActualParams().length > 0)
			{
			System.out.println("AEI actual parameters:");
			for (int i = 0; i < getActualParams().length; i++)
				{
				System.out.print("Parameter number ");
				System.out.print(i);
				System.out.println(":");
				getActualParams()[i].print();
				}
			}
		}

	/**
	 * Confronta questo oggetto con il parametro del metodo.
	 * @param aei - oggetto AEIdecl da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * confrotati contengono le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String name;
	 * private Expression selector;
	 * private String TipoArch;
	 * private Expression[] actualParams;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof AEIdecl)) return false;
		AEIdecl aei = (AEIdecl)o;
		// confronta gli oggetti di identificazione
		boolean ris = this.aeIident.equals(aei.getAeIident());
		// confronta i tipi di elementi architetturali
		// di appartenenza
		ris = ris && getAET().equals(aei.getAET());
		// confronta i parametri attuali, considerando anche il
		// caso in cui non ci sono parametri attuali
		if (getActualParams() != null && aei.getActualParams() != null)
			{
			if (getActualParams().length == aei.getActualParams().length)
				{
				for (int i = 0; i < getActualParams().length; i++)
					{
					ris = ris &&
					getActualParams()[i].equals(aei.getActualParams()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getActualParams() == null && aei.getActualParams() == null)
			return ris;
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public AEIdecl copy()
		{
		AEIdecl a = new AEIdecl();
		a.setAET(new String(getAET()));
		AEIident aeIident = this.aeIident.copy();
		a.setAeIident(aeIident);
		if (getActualParams() != null)
			{
			a.setActualParams(new Expression[getActualParams().length]);
			for (int i = 0; i < getActualParams().length; i++)
				{
				a.getActualParams()[i] = getActualParams()[i].copy();
				}
			}
		return a;
		}

	@Override
	public String toString() 
		{
		String string = this.aeIident.toString() +
				":" + this.aet;
		string = string + "(" ;
		if (this.actualParams != null && this.actualParams.length > 0)
			{
			for (int i = 0; i < this.actualParams.length - 1; i++)
				{
				string = string + this.actualParams[i].toString() + ",";
				}
			string = string + this.actualParams[this.actualParams.length - 1].toString();
			}
		string = string + ")";
		return string;
		}
	}