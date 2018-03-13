package specificheAEmilia;

/**
 * Rappresenta un'equazione di comportamento. In AEmilia,
 * un'equazione comportamentale EMPA ha la seguente forma:
 * <pre>
 * <code>
 * &lt;behav_equation_header&gt; "=" &lt;process_term&gt;
 * </pre>
 * </code>
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class BehavEquation implements AEmiliaBase
	{

	/**
	 * Header di un'equazione di comportamento.
	 */
	private Header BehavHeader;

	/**
	 * Termine di processo che definisce un'equazione di
	 * comportamento.
	 */
	private ProcessTerm TermineProcesso;

	/**
	 * Crea un oggetto BehavEquation vuoto.
	 *
	 */
	public BehavEquation() {
	}

	/**
	 * Crea un oggetto BehavEquation con intestazione e
	 * termine di processo forniti come parametri.
	 * @param behavHeader - oggetto Header da assegnare
	 * all'equazione.
	 * @param termineProcesso - oggetto ProcessTerm da assegnare
	 * all'equazione.
	 */
	public BehavEquation(Header behavHeader, ProcessTerm termineProcesso) {
		// assegna l'intestazione dell'equazione comportamentale
		this.BehavHeader = behavHeader;
		// assegna il termine di processo all'equazione
		this.TermineProcesso = termineProcesso;
	}

	/**
	 * Restituisce l'intestazione di un'equazione comportamento.
	 * @return oggetto Header.
	 */
	public Header getBehavHeader()
		{
		// resttuisce l'intestazione
		return this.BehavHeader;
		}

	/**
	 * Restituisce il termine di processo che definisce
	 * un'equazione di comportamento.
	 * @return oggetto ProcessTerm.
	 */
	public ProcessTerm getTermineProcesso()
		{
		// restituisce il termine di processo che
		// definisce il comportamento
		return this.TermineProcesso;
		}

	/**
	 * Assegna una nuova intestazione all'equazione di
	 * comportamento.
	 * @param behavHeader - oggetto Header.
	 */
	public void setBehavHeader(Header behavHeader) {
		// assegna l'intestazione
		this.BehavHeader = behavHeader;
	}

	/**
	 * Assegna un nuovo termine di processo all'equazione di
	 * comportamento.
	 * @param termineProcesso - oggetto ProcessTerm.
	 */
	public void setTermineProcesso(ProcessTerm termineProcesso) {
		// assegna il termine di processo
		this.TermineProcesso = termineProcesso;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("BehavEquation object");
		System.out.println("Behavior header:");
		getBehavHeader().print();
		System.out.println("Process term:");
		getTermineProcesso().print();
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro
	 * del metodo.
	 * @param be - oggetto BehavEquation da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * contengono le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Header BehavHeader;
	 * private ProcessTerm TermineProcesso;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof BehavEquation)) return false;
		BehavEquation be = (BehavEquation)o;
		boolean ris = getBehavHeader().equals(be.getBehavHeader());
		ris = ris && getTermineProcesso().equals(be.getTermineProcesso());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public BehavEquation copy()
		{
		BehavEquation a = new BehavEquation();
		if (getBehavHeader() != null)
		a.setBehavHeader(getBehavHeader().copy());
		if (getTermineProcesso() != null)
		a.setTermineProcesso(getTermineProcesso().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = this.BehavHeader.toString() + " = " + this.TermineProcesso.toString();
		return string;
		}
	}