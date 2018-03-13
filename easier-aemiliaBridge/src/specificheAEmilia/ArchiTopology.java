package specificheAEmilia;

/**
 * Rappresenta la topologia architetturale di una specifica
 * AEmilia.
 * La seconda sezione di una specifica AEmilia ha la seguente
 * sintassi:
 * <pre>
 * <code>
 * "ARCHI_TOPOLOGY" &lt;aEIs&gt; &lt;architectural_interactions&gt;
 * &lt;architectural_attachments&gt;
 * </code>
 * </pre>
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ArchiTopology implements AEmiliaBase
	{

	/**
	 * Indica le istanze di elementi architetturali dichiarate
	 * nella topologia.
	 */
	private ArchiElemInstances aEIs;

	/**
	 * Indica le interazioni architetturali dichiarate
	 * nella topologia.
	 */
	private ArchiInteractions archiInteractions;

	/**
	 * Indica i collegamenti architetturali dichiarati
	 * nella topologia.
	 */
	private ArchiAttachments attachments;

	/**
	 * Crea un oggetto ArchiTopology vuoto.
	 *
	 */
	public ArchiTopology() {
	}

	/**
	 * Crea un oggetto ArchiTopology con istanze di elementi,
	 * interazioni architetturali, collegamenti architetturali
	 * forniti come parametri al costruttre
	 * @param is - oggetto ArchiElemInstances che rappresenta
	 * le istanze di elementi architetturali dell'oggetto
	 * creato.
	 * @param archiInteractions - oggetto ArchiInteractions che
	 * rappresenta le interazioni architetturali
	 * presenti nell'oggetto creato.
	 * @param attachments - oggetto ArchiAttachments che rappresenta
	 * i collegamenti architetturali dell'oggetto creato.
	 */
	public ArchiTopology(ArchiElemInstances is, ArchiInteractions archiInteractions, ArchiAttachments attachments) {
		// assegna le istanze di elementi architetturali
		this.aEIs = is;
		// assegna le interazioni architetturali
		this.archiInteractions = archiInteractions;
		// assegna i collegamenti tra istanze di elementi
		// di un'architettura
		this.attachments = attachments;
	}

	/**
	 * Restituisce le istanze di elementi architetturali presenti
	 * in questo oggetto.
	 * @return oggetto ArchiElemInstances presente come campo.
	 */
	public ArchiElemInstances getAEIs() {
		// restituisce le istanze di elementi architetturali
		return this.aEIs;
	}

	/**
	 * Restituisce i collegamenti architetturali presenti in
	 * questo oggetto.
	 * @return oggetto ArchiAttachments presente come campo.
	 */
	public ArchiAttachments getAttachments() {
		// restituisce i collegamenti tra istanze di elementi
		return this.attachments;
	}

	/**
	 * Restituisce le interazioni architetturali presenti in questo
	 * oggetto.
	 * @return oggetto ArchiInteractions presente come campo.
	 */
	public ArchiInteractions getArchiInteractions() {
		// restituisce le interazioni architetturali
		return this.archiInteractions;
	}

	/**
	 * Imposta le istanze di elementi architetturali uguali
	 * a quelle referenziate dal parametro del metodo.
	 * @param is - oggetto ArchiElemInstances che rappresenta
	 * le nuove istanze di elementi architetturali per
	 * questo oggetto.
	 */
	public void setAEIs(ArchiElemInstances is) {
		// assegna le istanze di elementi architetturali
		this.aEIs = is;
	}

	/**
	 * Imposta i collegamenti architetturali uguali a quelli
	 * referenziati dal parametro del metodo.
	 * @param attachments - oggetto ArchiAttachments che rappresenta
	 * i nuovi collegamenti architetturali per questo
	 * oggetto.
	 */
	public void setAttachments(ArchiAttachments attachments) {
		// assegna i collegamenti tra istanze di elementi
		this.attachments = attachments;
	}

	/**
	 * Imposta le interazioni architetturali uguali a quelle
	 * referenziate dal parametro del metodo.
	 * @param archiInteractions - oggetto ArchiInteractions
	 * che rappresenta le nuove interazioni architetturali
	 * per questo oggetto.
	 */
	public void setArchiInteractions(ArchiInteractions archiInteractions) {
		// assegna le interazioni architetturali
		this.archiInteractions = archiInteractions;
	}

	/**
	 * Stampa sullo standard output le informazioni presenti
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ArchiTopology object");
		System.out.println("Architectural elements instances:");
		// stampa le istanze di elementi architetturali
		getAEIs().print();
		System.out.println("Architectural interactions:");
		// stampa le interazioni architetturali
		getArchiInteractions().print();
		System.out.println("Attachments between architectural elements:");
		// stampa i collegamenti tra istanze di elementi
		// architetturali
		getAttachments().print();
		}

	/**
	 * Verifica se questo oggettoe'uguale al parametro del metodo.
	 * @return un valore booleano che indica se i due oggetti
	 * confrontati sono uguali.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private ArchiElemInstances aEIs;
	 * private ArchiInteractions archiInteractions;
	 * private ArchiAttachments attachments;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ArchiTopology)) return false;
		ArchiTopology at = (ArchiTopology)o;
		// confronta le istanze di elementi architetturali
		boolean ris = getAEIs().equals(at.getAEIs());
		// confronta i collegamenti tra istanze di elementi
		// architetturali
		ris = ris && getAttachments().equals(at.getAttachments());
		// confronta le interazioni architetturali
		ris = ris && getArchiInteractions().equals(at.getArchiInteractions());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ArchiTopology copy()
		{
		ArchiTopology a = new ArchiTopology();
		if (getAEIs() != null)
		a.setAEIs(getAEIs().copy());
		if (getAttachments() != null)
		a.setAttachments(getAttachments().copy());
		if (getAttachments() != null)
		a.setArchiInteractions(getArchiInteractions().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "ARCHI_TOPOLOGY ";
		string = string + this.aEIs.toString() + " ";
		string = string + this.archiInteractions.toString() + " ";
		string = string + this.attachments.toString() + " ";
		return string;
		}
	}