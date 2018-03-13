package specificheAEmilia;

/**
 * Indica una dichiarazione di istanze di elementi
 * architetturali indicizzata che, in AEmilia, ha la seguente
 * sintassi:
 * <pre>
 * <code>
 * "FOR_ALL" &lt;identifier&gt; "IN" &lt;expr&gt; ".." &lt;expr&gt;
 * &lt;identifier&gt; "[" &lt;expr&gt; "]" ":" &lt;identifier&gt;
 * "(" &lt;pe_expr_sequence&gt; ")"
 * </code>
 * </pre>
 * Tale formae'utile per dichiarare in maniera concisa diverse
 * istanze dello stesso AET attraverso un meccanismo di
 * indicizzazione. Questa richiede la specifica dell'identificatore
 * indice, che si puo' presentare nell'espressione di selezione e
 * nei parametri attuali, insieme con il suo intervallo, che e'
 * dato da due espressioni intere. Queste due espressioni devono
 * essere libere da identificatori non dichiarati e invocazioni
 * a generatori di numeri pseudo-casuali, con il valore della prima
 * espressione non piu' grande del valore della seconda espressione.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class AEIdeclInd 
	extends AEIdecl 
	{

	/**
	 * Indica l'indice per la dichiarazioni di diverse istanze
	 * di un elemento architetturale
	 */
	private String index;

	/**
	 * Expression iniziale per l'indicizzazione.
	 */
	private Expression beginningExpr;

	/**
	 * Expression finale per l'indicizzazione.
	 */
	private Expression endingExpr;

	/**
	 * Costruttore vuoto per istanziare un oggetto AEIdeclInd.
	 *
	 */
	public AEIdeclInd() {
	}

	/**
	 * Costruttore per istanziare un oggetto AEIdeclInd con tutte
	 * le informazioni necessarie fornite come parametri al
	 * metodo.
	 * @param name - nome dell'istanza indicizzata.
	 * @param selector - espressione di selezione di un istanza.
	 * @param aet - tipo di elemento architetturale a cui
	 * l'istanza appartiene.
	 * @param actualParams - array di parametri attuali che servono
	 * per istanziare un tipo di elemento architetturale.
	 * @param index - identificatore per l'indicizzazione
	 * delle istanze.
	 * @param beginningExpr - espressione iniziale di indicizzazione.
	 * @param endingExpr - espressione finale per l'indicizzazione.
	 */
	public AEIdeclInd(String name, Expression selector, String aet,
			Expression[] actualParams, String index, 
			Expression beginningExpr, 
			Expression endingExpr) {
		// assegna i campi della superclasse AEIdecl
		super(name, selector, aet, actualParams);
		// assegna un indice
		this.index = index;
		// assegna un'espressione iniziale
		this.beginningExpr = beginningExpr;
		// assegna un'espressione finale
		this.endingExpr = endingExpr;
	}

	/**
	 * Restituisce l'indice per la dichiarazione indicizzata.
	 * @return oggetto String che contiene l'identificatore
	 * dell'indice.
	 */
	public String getIndex()
		{
		// restituisce l'indice
		return this.index;
		}

	/**
	 * Restituisce l'espressione iniziale per l'indicizzazione.
	 * @return oggetto Expression.
	 */
	public Expression getBeginningExpr()
		{
		// restituisce l'espressione iniziale
		return this.beginningExpr;
		}

	/**
	 * Restituisce l'espressione finale per l'indicizzazione.
	 * @return oggetto Expression.
	 */
	public Expression getEndingExpr()
		{
		// restituisce l'espressione finale
		return this.endingExpr;
		}

	/**
	 * Imposta l'espressione finale dell'indicizzazione uguale
	 * al parametro di tale metodo.
	 * @param endingExpr - nuova espressione finale.
	 */
	public void setEndingExpr(Expression endingExpr) {
		// assegna l'espressione finale
		this.endingExpr = endingExpr;
	}

	/**
	 * Imposta l'espressione iniziale dell'indicizzazione uguale
	 * al parametro del metodo.
	 * @param beginningExpr - nuova espressione iniziale.
	 */
	public void setBeginningExpr(Expression beginningExpr) {
		// assegna l'espressione iniziale
		this.beginningExpr = beginningExpr;
	}

	/**
	 * Imposta l'indice della dichiarazione uguale al parametro del
	 * metodo.
	 * @param index - nuovo indice per la dichiarazione.
	 */
	public void setIndex(String index) {
		// assegna l'indice
		this.index = index;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("AEIdeclInd object");
		// stampa le informazioni contenute nella
		// superclasse AEIdeclInd
		super.print();
		// stampa l'indice
		System.out.println("Index identifier: "+getIndex());
		System.out.println("Indexing range top");
		// stampa l'espressione iniziale
		getBeginningExpr().print();
		System.out.println("Indexing range end");
		// stampa l'espressione finale
		getEndingExpr().print();
		}

	/**
	 * Confornta due oggetti AEIdeclInd e restituisce true
	 * se i due oggetti contengono le stesse informazioni.
	 * @param aei - oggetto AEIdeclInd da confrontare con questo.
	 * @return un valore booleano indicante se i due oggetti
	 * AEIdeclInd sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof AEIdeclInd)) return false;
		AEIdeclInd ideclInd = (AEIdeclInd)o;
		// confronta due dichiarazioni indicizzate come
		// oggetti della superclasse AEIdecl
		boolean ris = super.equals(ideclInd);
		// confronta le due espressioni di fine
		ris = ris && getEndingExpr().equals(((AEIdeclInd)o).getEndingExpr());
		// confronta le due espressioni di inizio
		ris = ris && getBeginningExpr().equals(((AEIdeclInd)o).getBeginningExpr());
		// confronta i due indici
		ris = ris && getIndex().equals(((AEIdeclInd)o).getIndex());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public AEIdeclInd copy()
		{
		AEIdeclInd a = new AEIdeclInd();
		a.setAET(new String(getAET()));
		AEIident aeIident = getAeIident().copy();
		a.setAeIident(aeIident);
		if (getActualParams() != null)
			{
			a.setActualParams(new Expression[getActualParams().length]);
			for (int i = 0; i < getActualParams().length; i++)
				{
				a.getActualParams()[i] = getActualParams()[i].copy();
				}
			}
		if (getEndingExpr() != null)
			a.setEndingExpr(getEndingExpr().copy());
		if (getBeginningExpr() != null)
			a.setBeginningExpr(getBeginningExpr().copy());
		a.setIndex(new String(getIndex()));
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "FOR_ALL " + this.index + " IN " + this.beginningExpr.toString() + 
			".." + this.endingExpr.toString() + " "; 
		return string + super.toString();
		}
	}