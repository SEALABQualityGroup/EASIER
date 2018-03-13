package specificheAEmilia;

import java.lang.Integer;
/**
 * Indica un'azione di input ed ha la seguente sintassi:
 * <pre>
 * <code>
 * &lt;identifier&gt; "?" "(" &lt;local_var_sequence&gt; ")"
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

public class ActionInput extends ActionType
	{

	/**
	 * Array che contiene le variabili di input.
	 */
	private String[] inputVariables;

	/**
	 * Costruttore vuoto per istanziare un oggetto ActionInput.
	 */
	public ActionInput() {
	}

	/**
	 * Crea un oggetto ActionInput con nome e variabili forniti.
	 * @param name - nome dell'azione.
	 * @param inputVariables - array di stringhe contenenti
	 * le variabili di input.
	 */
	public ActionInput(String name, String[] inputVariables) {
		// assegna un nome all'azione di input
		super(name);
		// assegna variabili di input all'azione
		this.inputVariables = inputVariables;
	}

	/**
	 * Restituisce le variabili di input dell' azione.
	 * @return un array di oggetti String.
	 */
	public String[] getInputVariables()
		{
		return this.inputVariables;
		}

	/**
	 * Serve per modificare le variabili di input per l'azione.
	 * @param inputVariables - array di stringhe che contiene i
	 * nomi delle variabili di input.
	 */
	public void setInputVariables(String[] inputVariables) {
		// assegna variabili di input all'azione
		this.inputVariables = inputVariables;
	}

	/**
 	 * Stampa sullo standard output le informazioni relative
	 * all'oggetto ActionInput.
	 */
	public void print()
		{
		System.out.println("ActionInput object");
		// stampa le informazioni contenute nella superclasse
		// ActionType
		super.print();
		// stampa le variabili di input
		for (int i = 0; i < getInputVariables().length; i++)
			{
			System.out.println("Input variable "+Integer.toString(i)+": "+getInputVariables()[i]);
			}
		}

	/**
	 * Restituisce true se l'azione contiene le stesse informazioni
	 * contenute nel parametro della funzione.
	 * @param ai - oggetto ActionInput da confrontare.
	 * @return un booleano
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 *  private String[] inputVariables;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ActionType)) return false;
		ActionType at = (ActionType)o;
		// se il tipo azionee'ActionType o ActioOutput si restituisce
		// false
		try {
			if (at instanceof ActionOutput) return false;
			// confronta le due azioni di input come oggetti
			// ActionType
			boolean ris = super.equals(at);
			// confronta l'uguaglianza delle variabili di input
			// delle due azioni, includendo il caso in cui possono
			// non esserci variabili di input
			if (getInputVariables() != null && ((ActionInput)at).getInputVariables() != null)
				{
				if (getInputVariables().length == ((ActionInput)at).getInputVariables().length)
					{
					for (int i = 0; i < getInputVariables().length; i++)
						{
						ris = ris &&
						getInputVariables()[i].equals(((ActionInput)at).getInputVariables()[i]);
						}
					return ris;
					}
				else return false;
				}
			else if (getInputVariables() == null && ((ActionInput)at).getInputVariables() == null)
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
	public ActionInput copy()
		{
		ActionInput a = new ActionInput();
		a.setName(new String(getName()));
		if (getInputVariables() != null)
			{
			String[] strings = new String[getInputVariables().length];
			for (int i = 0; i < getInputVariables().length; i++)
				{
				strings[i] = new String(getInputVariables()[i]); 
				}
			a.setInputVariables(strings);
			}
		return a;
		}

	@Override
	public String toString() 
		{
		String string = super.toString();
		if (this.inputVariables != null && this.inputVariables.length > 0)
			{
			string = string + "?" + "(";
			for (int i = 0; i < this.inputVariables.length - 1; i++)
				{
				string = string + this.inputVariables[i] + ",";
				}
			string = string + this.inputVariables[this.inputVariables.length - 1] + ")";
			}
		return string; 
		}
	}