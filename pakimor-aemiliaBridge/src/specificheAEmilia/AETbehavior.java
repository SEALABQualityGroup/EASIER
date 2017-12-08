package specificheAEmilia;

/**
 * Contiene le informazioni relative al comportamento di un
 * tipo di elemento architetturale (AET). In AEmilia un AET
 * ha la seguente sintassi:
 * <pre>
 * <code>
 * "BEHAVIOR" &lt;behav_equation_sequence&gt;
 * </code>
 * </pre>
 * dove &lt;behav_equation_sequence&gt;e'una sequenza non
 * vuota di equazioni comportamentali EMPA (oggetti BehavEquation)
 * separate da punti e virgole. La prima equazione comportamentale
 * nella sequenza rappresenta il comportamento iniziale
 * per l'AET. Ogni altra equazione comportamentale possibile
 * nella sequenza deve descrivere un comportamento che puo' essere
 * direttamente o indirettamente invocato da quella iniziale.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class AETbehavior
	implements AEmiliaBase
	{

	/**
	 * Array di comportamenti del tipo di elementi architetturali.
	 */
	private BehavEquation[] behaviors;

	/**
	 * Construttore vuoto per istanziare un oggetto AETbehavior.
	 *
	 */
	public AETbehavior() {
	}

	/**
	 * Dato un array di comportamenti, costruisce un
	 * tipo di elementi architetturali con tali comportamenti.
	 * @param behaviors - un array di oggetti BehavEquation.
	 */
	public AETbehavior(BehavEquation[] behaviors) {
		// assegna le equazioni comportamentali
		this.behaviors = behaviors;
	}

	/**
	 * Restituisce un array che contiene i comportamenti del tipo
	 * architetturale.
	 * @return un array di oggetti BehavEquation.
	 */
	public BehavEquation[] getBehaviors()
		{
		// restituisce le equazioni comportamentali
		return this.behaviors;
		}

	/**
	 * Imposta i comportamenti di un tipo di elemento
	 * architetturale uguali a quelli indicati dal parametro
	 * del metodo.
	 * @param behaviors - array di oggetti BehavEquation.
	 */
	public void setBehaviors(BehavEquation[] behaviors) {
		// assegna le equazioni comportamentali
		this.behaviors = behaviors;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto AETbehavior.
	 *
	 */
	public void print()
		{
		System.out.println("AETbehavior object");
		System.out.println("AET behaviors:");
		// stampa le equazioni comportamentali
		for (int i=0; i < getBehaviors().length; i++)
			{
			System.out.print("Behavior number ");
			System.out.print(i);
			System.out.println(":");
			getBehaviors()[i].print();
			}
		}

	/**
	 * Verifica se questo oggettoe'uguale al parametro del
	 * metodo.
	 * @param b - oggetto AETbehavior da confrontare
	 * @return true se e solo se questo oggetto e quello indicato
	 * dal parametro contengono le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private BehavEquation[] behaviors;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof AETbehavior)) return false;
		AETbehavior b = (AETbehavior)o;
		boolean ris = true;
		// confronta le equazioni comportamentali delle
		// due istanze, considerando anche il caso in cui
		// non ci sono equazioni nei due oggetti AETbehavior
		if (getBehaviors() != null && b.getBehaviors() != null)
			{
			if (getBehaviors().length == b.getBehaviors().length)
				{
				for (int i = 0; i < getBehaviors().length; i++)
					{
					ris = ris &&
					getBehaviors()[i].equals(b.getBehaviors()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getBehaviors() == null && b.getBehaviors() == null)
			return ris;
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public AETbehavior copy()
		{
		AETbehavior a = new AETbehavior();
		if (getBehaviors() != null)
			{
			a.setBehaviors(new BehavEquation[getBehaviors().length]);
			for (int i = 0; i < getBehaviors().length; i++)
				{
				a.getBehaviors()[i] = getBehaviors()[i].copy();
				}
			}
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "BEHAVIOR ";
		if (this.behaviors != null && this.behaviors.length > 0)
			{
			for (int i = 0; i < this.behaviors.length - 1; i++)
				{
				string = string + this.behaviors[i].toString() + "; ";
				}
			string = string + this.behaviors[this.behaviors.length - 1].toString();
			}
		return string;
		}
	}