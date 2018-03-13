package specificheAEmilia;

/**
 * Corrisponde ad un termine di processo che inizia con un'azione,
 * ed ha la seguente sintassi:
 * <pre>
 * <code>
 * &lt;action&gt; "." &lt;process_term_1&gt;
 * </code>
 * </pre>
 * dove:
 * <pre>
 * <code>
 * &lt;process_term_1&gt; ::= &lt;process_term&gt;
 * | &lt;identifier&gt; "(" &lt;actual_par_sequence&gt; ")"
 * </code>
 * </pre>
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ActionProcess 
	extends ProcessTerm 
	{

	/**
	 * Indica l'azione con cui inizia un termine di processo.
	 */
	private Action Azione;

	/**
	 * Indica il termine di processo che segue un'azione
	 * di un termine di processo
	 */
	private ProcessTerm Processo;

	/**
	 * Costruttore vuoto per istanziare un oggetto ActionProcess.
	 */
	public ActionProcess() {
		super();
	}

	/**
	 * Serve ad istanziare un oggetto ActionProcess con
	 * la condizione di esecuzione, azione e termine di processo
	 * forniti come parametri.
	 * @param condizione - oggetto Boolean che indica la
	 * condizione per l'esecuizione del termine di processo.
	 * @param azione - oggetto Action che indica un'azione
	 * iniziale di un termine di processo.
	 * @param processo - oggetto ProcessTerm che indica
	 * un termine di processo seguente un'azione.
	 */
	public ActionProcess(Expression condizione, Action azione, ProcessTerm processo) {
		// assegna una condizione di esecuzione
		super(condizione);
		// assegna un'azione
		this.Azione = azione;
		// assegna un termine di processo
		this.Processo = processo;
	}

	public ActionProcess(Action action2, ProcessTerm processTerm) 
		{
		this.Azione = action2;
		this.Processo = processTerm;
		}

	/**
	 * Restituisce l'azione iniziale di un termine di processo.
	 * @return un oggetto Action.
	 */
	public Action getAzione()
		{
		// restituisce l'azione
		return this.Azione;
		}

	/**
	 * Restituisce un termine di processo che segue un'azione.
	 * @return un oggetto ProcessTerm.
	 */
	public ProcessTerm getProcesso()
		{
		// restituisce il termine di processo
		return this.Processo;
		}

	/**
	 * Imposta l'azione iniziale di un termine di processo uguale
	 * a quella del parametro di input.
	 * @param azione - oggetto Action.
	 */
	public void setAzione(Action azione) {
		// assegna un'azione
		this.Azione = azione;
	}

	/**
	 * Imposta il termine seguente un'azione secodo il parametro
	 * fornito in input.
	 * @param processo - oggetto ProcessTerm.
	 */
	public void setProcesso(ProcessTerm processo) {
		// assegna un termine di processo
		this.Processo = processo;
	}

	/**
	 * Visualizza le informazioni dell'oggetto ActionProcess
	 * sullo schermo.
	 */
	public void print()
		{
		System.out.println("ActionProcess object");
		// stampa le informazioni contenute nella sua superclasse
		// ProcessTerm
		super.print();
		System.out.println("Process action");
		// stampa l'azione
		getAzione().print();
		System.out.println("Subsequent terms");
		// stampa il termine di processo
		getProcesso().print();
		}

	/**
	 * Confronta due oggetti ActionProcess.
	 * @param ap - oggetto ActionProcess da cofrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Action Azione;
	 * private ProcessTerm Processo;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ActionProcess)) return false;
		ProcessTerm pt = (ProcessTerm)o;
		try {
			// confronta le due azioni di processo come
			// termini di processo
			boolean ris = super.equals(pt);
			// confronta le azioni
			ris = ris && getAzione().equals(((ActionProcess)pt).getAzione());
			// confronta il termine di processo
			ProcessTerm processTerm = getProcesso();
			ProcessTerm processTerm2 = ((ActionProcess)pt).getProcesso();
			ris = ris && (processTerm == null ? 
					processTerm2 ==  null : processTerm.equals(processTerm2));
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
	public ActionProcess copy()
		{
		ActionProcess a = new ActionProcess();
		if (getCondition() != null)
		a.setCondition(getCondition().copy());
		if (getAzione() != null)
		a.setAzione(getAzione().copy());
		if (getProcesso() != null)
		a.setProcesso(getProcesso().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		return super.toString() + this.Azione.toString() + 
			"." + (this.Processo != null ? this.Processo.toString() : "null");
		}
	}