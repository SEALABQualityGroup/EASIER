package specificheAEmilia;

/**
 * Corrisponde ad un'azione di output ed ha la seguente sintassi:
 * <pre>
 * <code>
 * &lt;identifier&gt; "!" "(" &lt;expr_sequence&gt; ")"
 * </code>
 * </pre>
 * Ogni volta che una variabile locale si presenta in un'espressione all'interno di un'
 * azione di output, un'invocazione di un'equazione comportamentale, o una guardia
 * booleana senza che si sia presentata precedentemente in un'azione di input, viene
 * valutata a zero, false, o null, a seconda del suo tipo.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ActionOutput 
	extends ActionType 
	{

	/**
	 * Array che contiene le espressioni di output.
	 */
	private Expression[] outputExprs;

	/**
	 * Costruttore vuoto per istanziare un oggetto ActionOutput.
	 *
	 */
	public ActionOutput() {
	}

	/**
	 * Crea un oggetto ActionInput con nome e variabili forniti.
	 * @param name - nome dell'azione.
	 * @param outputExprs - array di espressioni di output.
	 */
	public ActionOutput(String name, Expression[] outputExprs) {
		// assegna un nome all'azione di output
		super(name);
		// assegna le espressioni di output all'azione
		this.outputExprs = outputExprs;
	}

	/**
	 * Restiutisce le espressioni di output dell'azione.
	 * @return un array di oggetti Expression.
	 */
	public Expression[] getOutputExprs()
		{
		// restituisce le espressioni di output
		return this.outputExprs;
		}

	/**
	 * Imposta le espressioni di output delle azioni a seconda del
	 * parametro fornito.
	 * @param outputExprs - array di espressioni.
	 */
	public void setOutputExprs(Expression[] outputExprs) {
		// assegna le espressioni di output all'azione
		this.outputExprs = outputExprs;
	}

	/**
	 * Visualizza sullo standard output le informazioni
	 * che caratterizzano l'azione.
	 */
	public void print()
		{
		System.out.println("ActionOutput object");
		// stampa le informazioni relative alla superclasse
		// ActionType
		super.print();
		System.out.println("Output expressions");
		// stampa le espressioni di output
		for (int i=0; i < getOutputExprs().length; i++)
			{
			getOutputExprs()[i].print();
			}
		}

	/**
	 * Restituisce true se l'azione contiene le stesse informazioni
	 * contenute nel parametro della funzione.
	 * @param ao - oggetto ActionOutput da confrontare.
	 * @return un valore booleano
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression[] outputExprs;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ActionType)) return false;
		ActionType at = (ActionType)o;
		// se ate'un'istanza di ActionType o ActionInput si restituisce
		// false
		try {
			if (at instanceof ActionInput) return false;
			// confronta le due azioni di output come
			// oggetti ActionType
			boolean ris = super.equals(at);
			// confronta le espressioni di output delle due azioni
			// di output tenendo in considerazione anche il caso in cui
			// possono non esserci espressioni di output
			if (getOutputExprs() != null && ((ActionOutput)at).getOutputExprs() != null)
				{
				if (getOutputExprs().length == ((ActionOutput)at).getOutputExprs().length)
					{
					for (int i = 0; i < getOutputExprs().length; i++)
						{
						ris = ris &&
						getOutputExprs()[i].equals(((ActionOutput)at).getOutputExprs()[i]);
						}
					return ris;
					}
				else return false;
				}
			else if (getOutputExprs() == null && ((ActionOutput)at).getOutputExprs() == null)
				return ris;
			else return false;
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
	public ActionOutput copy()
		{
		ActionOutput a = new ActionOutput();
		a.setName(new String(getName()));
		if (getOutputExprs() != null)
			{
			Expression[] expressions = new Expression[getOutputExprs().length];
			for (int i = 0; i < getOutputExprs().length; i++)
				{
				expressions[i] = getOutputExprs()[i].copy();
				}
			a.setOutputExprs(expressions);
			}
		return a;
		}

	@Override
	public String toString() 
		{
		String string = this.getName();
		if (this.outputExprs != null && this.outputExprs.length > 0)
			{
			string = string + "!" + "(";
			for (int i = 0; i < this.outputExprs.length - 1; i++)
				{
				string = string + this.outputExprs[i].toString() +",";
				}
			string = string + this.outputExprs[this.outputExprs.length - 1].toString() + ")";
			}
		return string;
		}
	}