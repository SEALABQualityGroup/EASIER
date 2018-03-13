package specificheAEmilia;

/**
 * Rappresenta una dichiarazione di collegamento tra istanze
 * di elementi architetturali indicizzata. Una dichiarazione di
 * collegamento architetturale indicizzata ha la seguente forma:
 * <pre>
 * <code>
 * "FOR_ALL" &lt;identifier&gt; "IN" &lt;expr&gt; ".." &lt;expr&gt;
 * ["AND" "FOR_ALL" &lt;identifier&gt; "IN" &lt;expr&gt; ".." &lt;expr&gt;]
 * "FROM" &lt;identifier&gt; ["[" &lt;expr&gt; "]"] "." &lt;identifier&gt;
 * "TO" &lt;identifier&gt; ["[" &lt;expr&gt; "]"] "." &lt;identifier&gt;
 * </code>
 * </pre>
 * Tale formae'utile per dichiarare in modo conciso diversi
 * collegamenti architetturali attraverso un meccanismo di
 * indicizzazione. Questo richiede la specifica aggiuntiva di fino
 * a due identificatori di indice differenti, che si possono
 * presntare nell'espressioni di selezione, insieme con i
 * loro intervalli, ognuno dei qualie'dato da due espressioni
 * intere. Queste due espressioni devono essere prive di
 * identificatori non dichiarati e invocazioni di numeri
 * generatori di pseudo-casuali, con il valore della prima
 * espressione non più grande del valore della seconda espressione.
 * Tutte le uni-interazioni attacate alla stessa and o or
 * interazione deve appartenere a AEI differenti. Tra tutte le
 * uni-interazioni attaccate alla stessa and-interazione
 * passiva, al più una puo' essere un'azione non passiva nel
 * comportamento dell'AEI al quale appartiene.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 */

public class AttacDeclInd 
	extends AttacDecl
	{

	/**
	 * Identificatore per la prima indicizzazione.
	 */
	private String index1;

	/**
	 * Expression di inizio per la prima indicizzazione.
	 */
	private Expression beginningExpr1;

	/**
	 * Expression di fine per la prima indicizzazione.
	 */
	private Expression endingExpr1;

	/**
	 * Identificatore per la seconda indicizzazione.
	 */
	private String index2;

	/**
	 * Expression di inizio per la seconda indicizzazione.
	 */
	private Expression beginningExpr2;

	/**
	 * Expression di fine per la seconda indicizzazione.
	 */
	private Expression endingExpr2;

	/**
	 * Crea un oggetto AttacDeclInd vuoto.
	 *
	 */
	public AttacDeclInd() {
	}

	/**
	 * Crea un oggetto AttacDeclInd con tutte le informazioni
	 * necessarie fornite come parametri.
	 */
	public AttacDeclInd(String index1, 
			Expression beginningExpr1, 
			Expression endingExpr1, String index2, 
			Expression beginningExpr2, Expression endingExpr2,
			String outputAei, Expression fromSelector, 
			String outputInteraction, String inputAei, 
			Expression toSelector, String inputInteraction) 
		{
		// assegna opportunamente i campi della superclasse
		super(outputAei, fromSelector, outputInteraction, inputAei, toSelector,
				inputInteraction);
		// assegna opportunamente i campi della classe
		this.index1 = index1;
		this.beginningExpr1 = beginningExpr1;
		this.endingExpr1 = endingExpr1;
		this.index2 = index2;
		this.beginningExpr2 = beginningExpr2;
		this.endingExpr2 = endingExpr2;
		}

	/**
	 * Restituisce l'espressione finale per la prima indicizzazione.
	 * @return oggetto Expression.
	 */
	public Expression getEndingExpr1() {
		// restituisce l'espressione finale assegnata al
		// primo indice
		return this.endingExpr1;
	}

	/**
	 * Restituisce l'espressione finale per la seconda
	 * indicizzazione.
	 * @return oggetto Expression.
	 */
	public Expression getEndingExpr2() {
		// restituisce l'espressione finale assegnata al
		// secondo indice
		return this.endingExpr2;
	}

	/**
	 * Restituisce l'espressione iniziale per la prima
	 * indicizzazione.
	 * @return oggetto Expression.
	 */
	public Expression getBeginningExpr1() {
		// restituisce l'espressione iniziale assegnata al
		// primo indice
		return this.beginningExpr1;
	}

	/**
	 * Restituisce l'espressione iniziale per la seconda
	 * indicizzazione.
	 * @return oggetto Expression.
	 */
	public Expression getBeginningExpr2() {
		// restituisce l'espressione iniziale assegnata al
		// secondo indice
		return this.beginningExpr2;
	}

	/**
	 * Restituisce l'identificatore indice della prima
	 * indicizzazione.
	 * @return oggetto String.
	 */
	public String getIndex1() {
		// restituisce il nome del primo indice
		return this.index1;
	}

	/**
	 * Restituisce l'identificatore indice della seconda
	 * indicizzazione.
	 * @return oggetto String.
	 */
	public String getIndex2() {
		// restituisce il nome del secondo indice
		return this.index2;
	}

	/**
	 * Aggiorna l'espressione di fine della prima indicizzazione
	 * con quella indicata dal parametro.
	 * @param endingExpr1 - oggetto Expression.
	 */
	public void setEndingExpr1(Expression endingExpr1) {
		// assegna l'espressione finale del primo indice
		this.endingExpr1 = endingExpr1;
	}

	/**
	 * Aggiorna l'espressione di fine della seconda indicizzazione
	 * con quella indicata dal parametro.
	 * @param endingExpr2 - oggetto Expression.
	 */
	public void setEndingExpr2(Expression endingExpr2) {
		// assegna l'espressione finale del secondo indice
		this.endingExpr2 = endingExpr2;
	}

	/**
	 * Aggiorna l'espressione di inizio della prima indicizzazione
	 * con quella indicata dal parametro.
	 * @param beginningExpr1 - oggetto Expression.
	 */
	public void setBeginningExpr1(Expression beginningExpr1) {
		// assegna l'espressione iniziale del primo indice
		this.beginningExpr1 = beginningExpr1;
	}

	/**
	 * Aggiorna l'espressione di inizio della seconda indicizzazione
	 * con quella indicata dal parametro.
	 * @param beginningExpr2 - oggetto Expression.
	 */
	public void setBeginningExpr2(Expression beginningExpr2) {
		// assegna l'espressione iniziale del secondo indice
		this.beginningExpr2 = beginningExpr2;
	}

	/**
	 * Aggiorna l'identificatore dell'indice della prima
	 * indicizzazione con quello indicato nel parametro.
	 * @param index1 - oggetto String.
	 */
	public void setIndex1(String index1) {
		// assegna il primo indice
		this.index1 = index1;
	}

	/**
	 * Aggiorna l'identificatore dell'indice della seconda
	 * indicizzazione con quello indicato nel parametro.
	 * @param index2 - oggetto String.
	 */
	public void setIndex2(String index2) {
		// assegna il secondo indice
		this.index2 = index2;
	}

	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 */
	public void print()
		{
		System.out.println("AttacDeclInd object");
		// stampa le informazioni della superclasse AttacDecl
		super.print();
		System.out.println("First index identifier: "+getIndex1());
		System.out.println("Top of output interactions indexed range");
		getBeginningExpr1().print();
		System.out.println("End of output interactions indexed range");
		getEndingExpr1().print();
		// la seconda indicizzazione puo' essere opzionale
		if (getIndex2() != null)
			{
			System.out.println("Second index identifier: "+getIndex2());
			System.out.println("Top of input interactions indexed range");
			getBeginningExpr2().print();
			System.out.println("End of input interactions indexed range");
			getEndingExpr2().print();
			}
		}

	/**
	 * Confornta questo oggetto con quello referenziato dal
	 * parametro del metodo.
	 * @param adi - oggetto AttacDeclInd da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String index1;
	 * private Expression beginningExpr1;
	 * private Expression endingExpr1;
	 * (i seguenti campi sono opzionali)
	 * private String index2;
	 * private Expression beginningExpr2;
	 * private Expression endingExpr2;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof AttacDecl)) return false;
		AttacDecl ad = (AttacDecl)o;
		try {
			// confronta i campi della superclasse
			boolean ris = super.equals(ad);
			ris = ris && getEndingExpr1().equals(((AttacDeclInd)ad).getEndingExpr1());
			// confronta i campi, considerando anche la
			// falcoltatività di alcuni di loro
			if (getEndingExpr2() != null && ((AttacDeclInd)ad).getEndingExpr2() != null)
				ris = ris && getEndingExpr2().equals(((AttacDeclInd)ad).getEndingExpr2());
			else if (getEndingExpr2() == null && ((AttacDeclInd)ad).getEndingExpr2() == null)
				ris = ris && true;
			else ris = ris && false;
			ris = ris && getBeginningExpr1().equals(((AttacDeclInd)ad).getBeginningExpr1());
			if (getBeginningExpr2() != null && ((AttacDeclInd)ad).getBeginningExpr2() != null)
				ris = ris && getBeginningExpr2().equals(((AttacDeclInd)ad).getBeginningExpr2());
			else if (getBeginningExpr2() == null && ((AttacDeclInd)ad).getBeginningExpr2() == null)
				ris = ris && true;
			else ris = ris && false;
			ris = ris && getIndex1().equals(((AttacDeclInd)ad).getIndex1());
			if (getIndex2() != null && ((AttacDeclInd)ad).getIndex2() != null)
				ris = ris && getIndex2().equals(((AttacDeclInd)ad).getIndex2());
			else if (getIndex2() == null && ((AttacDeclInd)ad).getIndex2() == null)
				ris = ris && true;
			else ris = ris && false;
			return ris;
			}
		catch (ClassCastException e)
			{
			return false;
			}
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public AttacDeclInd copy()
		{
		AttacDeclInd a = new AttacDeclInd();
		a.setOutputAei(this.getOutputAei().copy());
		a.setInputInteraction(new String(getInputInteraction()));
		a.setOutputInteraction(new String(getOutputInteraction()));
		a.setInputAei(this.getInputAei().copy());
		if (getEndingExpr1() != null)
			a.setEndingExpr1(getEndingExpr1().copy());
		if (getEndingExpr2() != null)
			a.setEndingExpr2(getEndingExpr2().copy());
		if (getBeginningExpr1() != null)
			a.setBeginningExpr1(getBeginningExpr1().copy());
		if (getBeginningExpr2() != null)
			a.setBeginningExpr2(getBeginningExpr2().copy());
		a.setIndex1(new String(getIndex1()));
		if (getIndex2() != null)
			a.setIndex2(new String(getIndex2()));
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "FOR_ALL " + this.index1 + " IN " + this.beginningExpr1.toString() +
			".." + this.endingExpr1.toString() + " ";
		string = string + (this.index2 == null ? "" : "AND FOR_ALL " + this.index2 + " IN " +
				this.beginningExpr2.toString() + ".." + this.endingExpr2.toString());
		string = string + super.toString();
		return string;
		}
	}