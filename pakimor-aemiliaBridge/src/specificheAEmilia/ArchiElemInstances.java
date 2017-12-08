package specificheAEmilia;

/**
 * Rappresenta le istanze di elementi architetturali.
 * Le istanze degli AET definite nella prima sezione di una
 * specifica AEmilia sono dichiarate come segue:
 * <pre>
 * <code>
 * "ARCHI_ELEM_INSTANCES" &lt;AEI_decl_sequence&gt;
 * </code>
 * </pre>
 * dove &lt;AEI_decl_sequence&gt;e'una sequenza non vuota di
 * dichiarazioni AEI separate da punti e virgole.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ArchiElemInstances
	implements AEmiliaBase
	{

	/**
	 * Indica una sequenza di dichiarazioni di istanze di
	 * elementi architetturali. aeiDeclSeq non puo' essere null o vuota.
	 */
	private AEIdecl[] aeiDeclSeq;

	/**
	 * Crea un'istanza vuota di un oggetto ArchiElemInstances.
	 *
	 */
	public ArchiElemInstances() {
	}

	/**
	 * Crea un'istanza di un oggetto ArchiElemInstances con
	 * dichiarazioni di istanza di elementi uguale a quella
	 * indicata dal parametro del costruttore.
	 * @param ideclSeq - array di AEIdecl indicante la
	 * dichiarazione di istanze di elementi architetturali.
	 */
	public ArchiElemInstances(AEIdecl[] ideclSeq) {
		// assegna le dichiarazioni di istanze di elementi
		this.aeiDeclSeq = ideclSeq;
	}

	/**
	 * Restituisce le dichiarazioni delle istanze degli elementi.
	 * @return array di oggetti AEIdecl.
	 */
	public AEIdecl[] getAEIdeclSeq()
		{
		// resttuisce le dichiarazioni di istanze di elementi
		return this.aeiDeclSeq;
		}

	/**
	 * Imposta la sequenza di dichiarazione delle istanze
	 * di elementi uguale al parametro del metodo.
	 * @param ideclSeq - array di oggetti AEIdecl.
	 */
	public void setAEIdeclSeq(AEIdecl[] ideclSeq) {
		// assegna le dichiarazioni di istanze di elementi
		this.aeiDeclSeq = ideclSeq;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ArchiElemInstances object");
		System.out.println("AEI declarations sequence:");
		// stampa le dichiarazioni di istanze di elementi
		for (int i=0; i < getAEIdeclSeq().length; i++)
			{
			System.out.print("AEI declaration number ");
			System.out.print(i);
			System.out.println(":");
			getAEIdeclSeq()[i].print();
			}
		}

	/**
	 * Verifica se questo oggettoe'uguale all'oggetto indicato
	 * dal parametro del metodo.
	 * @param aei - oggetto ArchiElemInstances da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private AEIdecl[] aeiDeclSeq;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ArchiElemInstances)) return false;
		ArchiElemInstances aei = (ArchiElemInstances)o;
		boolean ris = true;
		// confronta le dichiarazioni di istanze di elementi,
		// cosiderando anche il caso in cui non ci sono
		// dichiarazioni per i due oggetti confrontati
		if (getAEIdeclSeq() != null && aei.getAEIdeclSeq() != null)
			{
			if (getAEIdeclSeq().length == aei.getAEIdeclSeq().length)
				{
				for (int i = 0; i < getAEIdeclSeq().length; i++)
					{
					ris = ris &&
					getAEIdeclSeq()[i].equals(aei.getAEIdeclSeq()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getAEIdeclSeq() == null && aei.getAEIdeclSeq() == null)
			return ris;
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ArchiElemInstances copy()
		{
		ArchiElemInstances a = new ArchiElemInstances();
		if (getAEIdeclSeq() != null)
			{
			a.setAEIdeclSeq(new AEIdecl[getAEIdeclSeq().length]);
			for (int i = 0; i < getAEIdeclSeq().length; i++)
				{
				a.getAEIdeclSeq()[i] = getAEIdeclSeq()[i].copy();
				}
			}
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "ARCHI_ELEM_INSTANCES ";
		for (int i = 0; i < this.aeiDeclSeq.length - 1; i++)
			{
			string = string + this.aeiDeclSeq[i].toString() + ";";
			}
		string = string + this.aeiDeclSeq[this.aeiDeclSeq.length - 1].toString();
		return string;
		}
	}