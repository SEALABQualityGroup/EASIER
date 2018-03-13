package specificheAEmilia;

/**
 * Rappresenta un termine di processo corrispondente ad un
 * comportamento. In AEmilia, ha la seguente sintassi:
 * <pre>
 * <code>
 * &lt;identifier&gt; "(" &lt;actual_par_sequence&gt; ")"
 * </code>
 * </pre>
 * dove &lt;actual_par_sequence&gt;e'una sequenza possibilmente
 * vuota di espressioni separate da virgole.
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class BehavProcess extends ProcessTerm {

	/**
	 * name del comportamento.
	 */
	private String name;

	/**
	 * Array di espressioni che rappresentano i parametri
	 * attuali per il comportamento. Exprs non puo' essere null.
	 */
	private Expression[] Exprs;

	/**
	 * Crea un oggetto BehavProcess vuoto.
	 *
	 */
	public BehavProcess() {
		super();
	}

	/**
	 * Crea un oggetto BehavProcess con le informazioni
	 * fornite come parametri dal costruttore.
	 * @param condizione - oggetto Expression che indica la
	 * condizione di esecuzione del comportamento.
	 * @param name - oggetto String corrispondente al nome
	 * dell'espressione.
	 * @param exprs - array di oggetti Expression rappresentanti
	 * i parametri attuali per un comportamento.
	 */
	public BehavProcess(Boolean condizione, String name, Expression[] exprs) {
		super(condizione);
		// assegna il nome del comportamento chiamato
		this.name = name;
		// assegna i parametri attuali al comportamento
		this.Exprs = exprs;
	}

	public BehavProcess(String name, Expression[] espressiones) 
		{
		// assegna il nome del comportamento chiamato
		this.name = name;
		// assegna i parametri attuali al comportamento
		this.Exprs = espressiones;
		}
	
	/**
	 * Restituisce l'insieme dei parametri attuali per la
	 * chiamata ad un comportamento.
	 * @return array di oggetti Expression.
	 */
	public Expression[] getExprs() {
		// restituisce i parametri attuali
		return this.Exprs;
	}

	/**
	 * Restituisce il nome del comportamento.
	 * @return oggetto String.
	 */
	public String getName() {
		// restituisce il nome
		return this.name;
	}

	/**
	 * Assegna a questo oggetto una nuova sequenza di parametri
	 * attuali.
	 * @param exprs - array di oggetti Expression.
	 */
	public void setExprs(Expression[] exprs) {
		// assegna i parametri attuali
		this.Exprs = exprs;
	}

	/**
	 * Assegna a questo oggetto un nuovo nome di comportamento.
	 * @param name - oggetto String.
	 */
	public void setName(String name) {
		// assegna il nome
		this.name = name;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("BehavProcess object");
		super.print();
		System.out.println("Behavior header: "+getName());
		// considera il caso in cui non ci sono parametri attuali
		if (getExprs().length > 0)
			{
			System.out.println("Process behavior expressions: ");
			for (int i = 0; i < getExprs().length; i++)
				{
				System.out.print("Expression number ");
				System.out.print(i);
				System.out.println(":");
				getExprs()[i].print();
				}
			}
		}

	/**
	 * Confronta questo oggetto con quello referenziato
	 * dal parametro.
	 * @param bp - oggetto BehavProcess.
	 * @return un valore booleano che indica se i due oggetti
	 * contengono le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String name;
	 * private Expression[] Exprs;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ProcessTerm)) return false;
		ProcessTerm pt = (ProcessTerm)o;
		try {
			boolean ris = super.equals(pt);
			ris = ris && getName().equals(((BehavProcess)pt).getName());
			// ricordarsi che possono non esserci parametri
			// attuali nella chiamata di un comportamento
			if (getExprs() != null && ((BehavProcess)pt).getExprs() != null)
				{
				if (getExprs().length == ((BehavProcess)pt).getExprs().length)
					{
					for (int i = 0; i < getExprs().length; i++)
						{
						ris = ris &&
						getExprs()[i].equals(((BehavProcess)pt).getExprs()[i]);
						}
					return ris;
					}
				else return false;
				}
			else if (getExprs() == null && ((BehavProcess)pt).getExprs() == null)
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
	public BehavProcess copy()
		{
		BehavProcess a = new BehavProcess();
		if (getCondition() != null)
		a.setCondition(getCondition().copy());
		if (getExprs() != null)
			{
			a.setExprs(new Expression[getExprs().length]);
			for (int i = 0; i < getExprs().length; i++)
				{
				a.getExprs()[i] = getExprs()[i].copy();
				}
			}
		a.setName(new String(getName()));
		return a;
		}

	@Override
	public String toString() 
		{
		String string = this.name + "(";
		if (this.Exprs.length > 0)
			{
			for (int i = 0; i < this.Exprs.length - 1; i++)
				{
				string = string + this.Exprs[i].toString() + ",";
				}
			string = string + this.Exprs[this.Exprs.length - 1].toString();
			}
		string = string + ")";
		return string;
		}
	}