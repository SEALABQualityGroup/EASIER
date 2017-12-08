package specificheAEmilia;

/**
 * Rappresenta un'intera specifica AEmilia per la descrizione
 * di un tipo architetturale. Tale descrizionee'composta da
 * tre sezioni:
 * <pre>
 * <code>
 * ARCHI_TYPE /name and formal parameters.
 * ARCHI_ELEM_TYPES
 * ELEM_TYPE /definition of the first architectural element type.
 * ...
 * ...
 * ELEM_TYPE /definition of the last architectural element type.
 * ARCHI_TOPOLOGY
 * ARCHI_ELEM_INSTANCES /declaration of the architectural element instances.
 * ARCHI_INTERACTIONS /declaration of the architectural interactions.
 * ARCHI_ATTACHMENTS /declaration of the architectural attachments.
 * [BEHAV_VARIATIONS
 * [BEHAV_HIDINGS /declaration of the behavioral hidings.]
 * [BEHAV_RESTRICTIONS /declaration of the behavioral restrictions.]
 * [BEHAV_RENAMINGS /declaration of the behavioral renamings.]]
 * END
 * </code>
 * </pre>
 * L'intestazione del tipo architetturale all'inizio di una specifica AEmilia ha la
 * seguente sintassi:
 * <pre>
 * <code>
 * "ARCHI_TYPE" &lt;identifier&gt; "(" &lt;init_const_formal_par_decl_sequence&gt; ")"
 * </code>
 * </pre>
 * dove <identifier>e'il nome del tipo architetturale e
 * &lt;init_const_formal_par_decl_sequence&gt;e'o void o una sequenza non vuota di dichiarazioni
 * separate da virgole di parametri formali costanti inizializzati.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ArchiType implements AEmiliaBase
	{

	/**
	 * Header del tipo architetturale. Non deve essere null.
	 */
	private Header archiTypeHeader;

	/**
	 * Tipi architetturali presenti nella specifica. Non deve essere null.
	 */
	private ArchiElemTypes archiElemTypes;

	/**
	 * Descrive la topologia del tipo architetturale. Non deve essere null.
	 */
	private ArchiTopology archiTopology;
	
	/**
	 * Descrive le variazioni comportamentali. puo' essere null.
	 */
	private BehavVariations behavVariations;

	/**
	 * Crea un oggetto ArchiType vuoto.
	 *
	 */
	public ArchiType() {
	}

	/**
	 * Crea un oggetto ArchiType con intestazione, tipi di elementi
	 * e topologia forniti come parametri al metodo.
	 * @param nomeTipo - oggetto Header da assegnare
	 * all'intestazione del tipo architetturale.
	 * @param tipiArchitetturali - oggetto ArchiElemTypes da
	 * assegnare al tipo architetturale.
	 * @param topologia - oggetto ArchiTopology da assegnare
	 * al tipo architetturale.
	 */
	public ArchiType(Header nomeTipo, ArchiElemTypes tipiArchitetturali, ArchiTopology topologia) {
		// assegna il nome
		this.archiTypeHeader = nomeTipo;
		// assegna i tipi di elementi architetturali
		this.archiElemTypes = tipiArchitetturali;
		// assegna la topologia
		this.archiTopology = topologia;
	}

	/**
	 * Restituisce l'intestazione del tipo architetturale.
	 * @return oggetto Header presente in questo oggetto.
	 */
	public Header getArchiTypeHeader()
		{
		// restituisce il nome
		return this.archiTypeHeader;
		}

	/**
	 * Restituisce i tipi di elementi architetturali presenti nel
	 * tipo architetturale
	 * @return oggetto ArchiElemTypes presente in questo oggetto.
	 */
	public ArchiElemTypes getArchiElemTypes()
		{
		// restituisce i tipi di elementi architetturali
		return this.archiElemTypes;
		}

	/**
	 * Restituisce la topologia del tipo architetturale.
	 * @return oggetto ArchiTopology presente in questo oggetto.
	 */
	public ArchiTopology getTopologia() {
		// restituisce la tipologia
		return this.archiTopology;
	}

	/**
	 * Resstituisce le variazioni comportamentali.
	 * 
	 * @return
	 */
	public BehavVariations getBehavVariations() 
		{
		return behavVariations;
		}
	
	/**
	 * Imposta il parametro come intestazione di questo oggetto.
	 * @param nomeTipo - nuovo oggetto Header.
	 */
	public void setArchiTypeHeader(Header nomeTipo) {
		// assegna il nome
		this.archiTypeHeader = nomeTipo;
	}

	/**
	 * Imposta il parametro come tipi di elementi architetturali
	 * per questo oggetto.
	 * @param tipiArchitetturali - nuovo oggetto ArchiElemTypes.
	 */
	public void setArchiElemTypes(ArchiElemTypes tipiArchitetturali) {
		// assegna i tipi di elementi architetturali
		this.archiElemTypes = tipiArchitetturali;
	}

	/**
	 * Imposta il prametro come topologia per questo oggetto.
	 * @param topologia - nuovo oggetto ArchiTopology.
	 */
	public void setTopologia(ArchiTopology topologia) {
		// assegna la topologia
		this.archiTopology = topologia;
	}

	public void setBehavVariations(BehavVariations behavVariations) 
		{
		this.behavVariations = behavVariations;
		}
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ArchiType object");
		System.out.println("Architectural type header:");
		// stampa il nome
		getArchiTypeHeader().print();
		System.out.println("Architectural elements types:");
		// stampa i tipi di elementi architetturali
		getArchiElemTypes().print();
		System.out.println("Architectural type topology:");
		// stampa la tipologia
		getTopologia().print();
		System.out.println("Architectural type variations");
		// stampa le variazioni architetturali
		getBehavVariations().print();
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param at - oggetto ArchiType da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Header archiTypeHeader;
	 * private ArchiElemTypes archiElemTypes;
	 * private ArchiTopology archiTopology;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ArchiType)) return false;
		ArchiType at = (ArchiType)o;
		// confronta i nomi
		boolean ris = getArchiTypeHeader().equals(at.getArchiTypeHeader());
		// confronta i tipi di elementi architetturali
		ris = ris && getArchiElemTypes().equals(at.getArchiElemTypes());
		// confronta le tipologie
		ris = ris && getTopologia().equals(at.getTopologia());
		// confronta le variazioni, che possono essere null
		ris = ris && (getBehavVariations() == null ? 
				at.getBehavVariations() == null : getBehavVariations().equals(at.getBehavVariations()));
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ArchiType copy()
		{
		ArchiType a = new ArchiType();
		if (getArchiTypeHeader() != null)
		a.setArchiTypeHeader(getArchiTypeHeader().copy());
		if (getArchiElemTypes() != null)
		a.setArchiElemTypes(getArchiElemTypes().copy());
		if (getTopologia() != null)
		a.setTopologia(getTopologia().copy());
		if (getBehavVariations() != null)
		a.setBehavVariations(getBehavVariations().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "ARCHI_TYPE " + this.archiTypeHeader.toString() + " ";
		string = string + this.archiElemTypes.toString() + " ";
		string = string + this.archiTopology.toString();
		if (this.getBehavVariations() != null)
			{
			string = string + this.behavVariations.toString();
			}
		return string + " END";
		}
	}