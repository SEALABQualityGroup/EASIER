package specificheAEmilia;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Rappresenta un termine di processo come una scelta in base
 * ad una condizione tra più di un termine di processo.
 * In AEmilia un processo scelta ha la seguente sintassi:
 * <pre>
 * <code>
 * "choice" "{" &lt;process_term_2_sequence&gt; "}"
 * </code>
 * </pre>
 * dove &lt;process_term_2_sequence&gt;e'una sequenza di
 * almeno due termini di processo separati da virgole,
 * ognuno possibilmente preceduto da un'espressione
 * booleana che stabilisce la condizione sotto cuie'disponibile.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ChoiceProcess extends ProcessTerm {

	/**
	 * Array di termini di processi che possono essere scelti.
	 * processes ha taglia di almeno 2.
	 */
	private ProcessTerm[] processes;

	/**
	 * Crea un oggetto ChoiceProcess vuoto.
	 *
	 */
	public ChoiceProcess() {
		super();
	}

	/**
	 * Crea un oggetto ChoiceProcess con condizione per
	 * l'esecuzione e insieme di processi che possono
	 * essere scelti forniti come parametri.
	 * @param cp - oggetto Boolean.
	 * @param processes - array di oggetti ProcessTerm.
	 */
	public ChoiceProcess(Expression condizione, ProcessTerm[] processes) {
		super(condizione);
		// assegna i processi alternativi
		this.processes = processes;
	}

	public ChoiceProcess(ProcessTerm[] processes) {
		super();
		this.processes = processes;
	}

	/**
	 * Restituisce i possibili termini di processo che
	 * possono essere eseguiti.
	 * @return l'array di termini di processo che possono
	 * essere scelti.
	 */
	public ProcessTerm[] getProcesses()
		{
		// restituisce i processi alternativi
		return this.processes;
		}

	/**
	 * Assegna un nuovo array di termini di processo che
	 * possono essere eseguiti.
	 * @param processes - array di oggetti ProcessTerm.
	 */
	public void setProcesses(ProcessTerm[] processes) {
		// assegna i processi alternativi
		this.processes = processes;
	}

	/**
	 * Stampa sullo standard output le informazioni relative a
	 * questo oggetto.
	 */
	public void print()
		{
		System.out.println("ChoiceProcess object");
		super.print();
		System.out.println("Processes under conditions:");
		// l'array dei processi alternativi non puo' essere vuoto
		for (int i=0; i < getProcesses().length; i++)
			{
			System.out.print("Process number ");
			System.out.print(i);
			System.out.println(":");
			getProcesses()[i].print();
			}
		}

	/**
	 * Restituisce true se e solo se questo oggettoe'uguale
	 * a quello referenziato dal parametro del metodo.
	 * @param cp - oggetto ChoiceProcess da confrontare.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private ProcessTerm[] processes;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ProcessTerm)) return false;
		ProcessTerm pt = (ProcessTerm)o;
		try {
			boolean ris = super.equals(pt);
			// si considera anche il caso in cui i due oggetti
			// non hanno processi alternativi
			if (getProcesses() != null && ((ChoiceProcess)pt).getProcesses() != null)
				{
				if (getProcesses().length == ((ChoiceProcess)pt).getProcesses().length)
					{
					CopyOnWriteArrayList<ProcessTerm> copyOnWriteArrayList = new CopyOnWriteArrayList<ProcessTerm>(getProcesses());
					CopyOnWriteArrayList<ProcessTerm> copyOnWriteArrayList2 = new CopyOnWriteArrayList<ProcessTerm>(((ChoiceProcess)pt).getProcesses());
					if (!copyOnWriteArrayList.containsAll(copyOnWriteArrayList2))
						return false;
					else if (!copyOnWriteArrayList2.containsAll(copyOnWriteArrayList))
						return false;
					}
				else return false;
				}
			else if (getProcesses() == null && ((ChoiceProcess)pt).getProcesses() == null)
				return ris;
			else return false;
			}
		catch (ClassCastException c)
			{
			return false;
			}
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ChoiceProcess copy()
		{
		ChoiceProcess a = new ChoiceProcess();
		if (getCondition() != null)
		a.setCondition(getCondition().copy());
		if (getProcesses() != null)
			{
			a.setProcesses(new ProcessTerm[getProcesses().length]);
			for (int i = 0; i < getProcesses().length; i++)
				{
				a.getProcesses()[i] = getProcesses()[i].copy();
				}
			}
		return a;
		}

	@Override
	public String toString() 
		{
		String string = super.toString() + "choice {"; 
		for (int i = 0; i < this.processes.length - 1; i++)
			{
			string = string + this.processes[i].toString() + ",";
			}
		string = string + this.processes[this.processes.length - 1].toString() + "}";
		return string;
		}
	}