package specificheAEmilia;

/**
 * Rappresenta una dichiarazione di interazione architetturale
 * indicizzata. Ha la seguente sintassi AEmilia:
 * <pre>
 * <code>
 * "FOR_ALL" &lt;identifier&gt; "IN" &lt;expr&gt; ".." &lt;expr&gt;
 * &lt;identifier&gt; "[" &lt;expr&gt; "]" "." &lt;identifier&gt;
 * </code>
 * </pre>
 * &Egrave; utile per dichiarare in modo conciso diverse
 * interazioni architetturali attraverso un meccanismo di
 * indicizzazione. Questo richiede la specifica dell'identificatore
 *  indice, che si puo' successivamente presentare nell'espressione
 * di selezione, insieme con il suo intervallo, chee'dato da due
 * espressioni intere. Queste due espressioni devono essere prive
 * da identificatori non dichiarati e invocazioni a generatori di
 * numeri pseudo-casuali, con il valore della prima espressione
 * non più grande del valore della seconda espressione.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class InteractionDeclInd 
	extends InteractionDecl 
	{

	/**
	 * Identificatore per l'indicizzazione.
	 */
	private String index;

	/**
	 * Expression di inizio per l'indicizzazione.
	 */
	private Expression beginningExpr;

	/**
	 * Expression di fine per l'indicizzazione.
	 */
	private Expression endingExpr;

	/**
	 * Crea un oggetto InteractionDeclInd vuoto.
	 *
	 */
	public InteractionDeclInd() {
	}

	/**
	 * Crea un oggetto InteractionDeclInd con le informazioni
	 * caratterizzanti fornite come parametri.
	 * @param aei - oggetto String.
	 * @param selector - oggetto Expression.
	 * @param interaction - oggetto String.
	 * @param index - oggetto String.
	 * @param beginningExpr - oggetto Expression.
	 * @param endingExpr - oggetto Expression.
	 */
	public InteractionDeclInd(String aei, Expression selector, String interaction, String index, 
			Expression beginningExpr, Expression endingExpr) {
		super(aei, selector, interaction);
		this.index = index;
		this.beginningExpr = beginningExpr;
		this.endingExpr = endingExpr;
	}

	/**
	 * Restituisce l'espressione il cui valoree'l'ultimo assunto
	 *  dall'indice per la dichiarazione di un'interazione.
	 * @return un oggetto Expression.
	 */
	public Expression getEndingExpr() {
		return this.endingExpr;
	}

	/**
	 * Restituisce l'espressione il cui valoree'il primo assunto
	 *  dall'indice per la dichiarazione di un'interazione.
	 * @return un oggetto Expression.
	 */
	public Expression getBeginningExpr() {
		return this.beginningExpr;
	}

	/**
	 * Restituisce il nome dell'indice.
	 * @return un oggetto String.
	 */
	public String getIndex() {
		return this.index;
	}

	/**
	 * Assegna un'espressione finale per l'indicizzazione.
	 * @param endingExpr - oggetto Expression.
	 */
	public void setExprFine(Expression endingExpr) {
		this.endingExpr = endingExpr;
	}

	/**
	 * Assegna un'espressione iniziale per l'indicizzazione.
	 * @param beginningExpr - oggetto Expression.
	 */
	public void setExprInizio(Expression beginningExpr) {
		this.beginningExpr = beginningExpr;
	}

	/**
	 * Assegna un nome all'indice.
	 * @param index - oggetto String.
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("InteractionDeclInd object");
		super.print();
		System.out.println("Index identifier: "+getIndex());
		System.out.println("Top of indexing range");
		getBeginningExpr().print();
		System.out.println("End of indexing range");
		getEndingExpr().print();
		}

	/**
	 * Restituisce true se e solo se idi contiene le stesse
	 * informazioni di questo oggetto.
	 * @param idi - oggetto InteractionDeclInd.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String index;
	 * private Expression ExprInizio;
	 * private Expression ExprFine;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof InteractionDecl)) return false;
		InteractionDecl id = (InteractionDecl)o;
		try {
			boolean ris = super.equals(id);
			ris = ris && getIndex().equals(((InteractionDeclInd)id).getIndex());
			ris = ris && getEndingExpr().equals(((InteractionDeclInd)id).getEndingExpr());
			ris = ris && getBeginningExpr().equals(((InteractionDeclInd)id).getBeginningExpr());
			return ris;
			}
		catch (ClassCastException c)
			{
			return false;
			}
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public InteractionDeclInd copy()
		{
		InteractionDeclInd a = new InteractionDeclInd();
		a.setAei(getAei().copy());
		a.setInteraction(new String(getInteraction()));
		if (getEndingExpr() != null)
		a.setExprFine(getEndingExpr().copy());
		if (getBeginningExpr() != null)
		a.setExprInizio(getBeginningExpr().copy());
		a.setIndex(new String(getIndex()));
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "FOR_ALL " + this.index + " IN " + this.beginningExpr.toString() + ".." +
			this.endingExpr.toString() + " " + super.toString();
		return string;
		}
	}