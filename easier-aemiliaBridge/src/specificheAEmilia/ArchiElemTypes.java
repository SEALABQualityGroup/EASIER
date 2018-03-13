package specificheAEmilia;


/**
 * Rappresenta i tipi di elementi architetturali presenti
 * in un tipo architetturale.
 * La prima sezione di una specifica AEmilia inizia con
 * la parola chiave ARCHI_ELEM_TYPES ede'composta da una
 * sequenza non vuota di definizioni AET (oggetti ElemType) separate da punti e virgola.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ArchiElemTypes 
	implements AEmiliaBase
	{

	/**
	 * array di oggetti ElemType presenti. elementTypes non puo' essere un'array vuoto.
	 */
	private ElemType[] elementTypes;

	/**
	 * Crea un'istanza vuota di un'oggetto ArchiElemTypes.
	 *
	 */
	public ArchiElemTypes() {
	}

	/**
	 * Crea un oggetto ArchiElemTypes con tipi di elementi
	 * forniti come parametro al costruttore.
	 * @param elementTypes - array di oggetti ElemType.
	 */
	public ArchiElemTypes(ElemType[] elementTypes) {
		// assegna i tipi di elementi architetturali
		this.elementTypes = elementTypes;
	}

	/**
	 * Restituisce l'array dei tipi di elementi presenti.
	 * @return array di oggetti ElemType.
	 */
	public ElemType[] getElementTypes()
		{
		// restituisce i tipi di elementi architetturali
		return this.elementTypes;
		}

	/**
	 * Imposta i tipi di elemento pesenti uguali a quelli indicati
	 * nel parametro.
	 * @param elementTypes - array di oggetti ElemType.
	 */
	public void setElementTypes(ElemType[] elementTypes) {
		// assegna i tipi di elementi architetturali
		this.elementTypes = elementTypes;
	}

	/**
	 * Visualizza le informazioni relative a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ArchiElemTypes object");
		System.out.println("Architectural elements types:");
		// stampa i tipi di elementi architetturali
		for (int i=0; i < getElementTypes().length; i++)
			{
			getElementTypes()[i].print();
			}
		}

	/**
	 * Verifica se questo oggettoe'uguale al parametro del metodo.
	 * @param aet - oggetto ArchiElemTypes da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * hanno le stesse informazioni.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private ElemType[] elementTypes;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ArchiElemTypes)) return false;
		ArchiElemTypes aet = (ArchiElemTypes)o;
		boolean ris = true;
		// confronta i tipi di elementi architetturali
		// dei due AET anche nel caso in cui non ci sono
		// tipi di elementi architetturali nei due AET
		if (getElementTypes() != null && aet.getElementTypes() != null)
			{
			if (getElementTypes().length == aet.getElementTypes().length)
				{
				for (int i = 0; i < getElementTypes().length; i++)
					{
					ris = ris &&
					getElementTypes()[i].equals(aet.getElementTypes()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getElementTypes() == null && aet.getElementTypes() == null)
			return ris;
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ArchiElemTypes copy()
		{
		ArchiElemTypes a = new ArchiElemTypes();
		if (getElementTypes() != null)
			{
			a.setElementTypes(new ElemType[getElementTypes().length]);
			for (int i = 0; i < getElementTypes().length; i++)
				{
				a.getElementTypes()[i] = getElementTypes()[i].copy();
				}
			}
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "ARCHI_ELEM_TYPES ";
		for (int i = 0; i < this.elementTypes.length - 1; i++)
			{
			string = string + this.elementTypes[i].toString();
			}
		string = string + this.elementTypes[this.elementTypes.length - 1].toString();
		return string;
		}
	}