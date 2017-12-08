package specificheAEmilia;

/**
 * Rappresenta un collegamento architetturale. In AEmilia,
 * i collegamenti architetturali sono dichiarati attraverso la
 * seguente sintassi:
 * <pre>
 * <code>
 * "ARCHI_ATTACHMENTS" &lt;pe_architectural_attachment_decl&gt;
 * </code>
 * </pre>
 * dove &lt;pe_architectural_attachment_decl&gt;e'o void o una
 * sequenza non vuota di dichiarazioni di collegamenti
 * architetturali separati da punti e virgole.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ArchiAttachments implements AEmiliaBase
	{

	/**
	 * Contiene i collegamenti architetturali di un tipo
	 * architetturale. attachments puo' essere null.
	 */
	private AttacDecl[] attachments;

	/**
	 * Crea un'istanza vuota di un oggetto ArchiAttachments.
	 *
	 */
	public ArchiAttachments() {
	}

	/**
	 * Crea un'istanza di un oggetto ArchiAttachments con collegamenti
	 * forniti come parametri.
	 * @param attachments - array di oggetti AttacDecl che
	 * costituiscono i legami architetturali.
	 */
	public ArchiAttachments(AttacDecl[] attachments) {
		// assegna i collegamenti
		this.attachments = attachments;
	}

	/**
	 * Restituisce l'insieme dei collegamenti architetturali
	 * del tipo architetturale.
	 * @return array di oggetti AttacDecl.
	 */
	public AttacDecl[] getAttachments() {
		// restituisce i collegamenti
		return this.attachments;
	}

	/**
	 * Imposta i collegamenti architetturali uguali a quelli
	 * indicati nel parametro del metodo.
	 * @param attachments - array di oggetti AttacDecl.
	 */
	public void setAttachments(AttacDecl[] attachments) {
		// assegna i collegamenti
		this.attachments = attachments;
	}

	/**
	 * Stampa sullo standard output le informazioni relative a
	 * questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("ArchiAttachments object");
		// stampa i collegamenti
		if (getAttachments() != null)
			{
			System.out.println("Attachments:");
			for (int i=0; i < getAttachments().length; i++)
				{
				System.out.print("Attachment number: ");
				System.out.println(i);
				getAttachments()[i].print();
				}
			}
		}

	/**
	 * Verifica se questo oggetto contiene le stesse informazioni
	 * dell'oggetto referenziato dal parametro del metodo.
	 * @param aa - oggetto ArchiAttachments da confrontare
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private AttacDecl[] attachments;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ArchiAttachments)) return false;
		ArchiAttachments aa = (ArchiAttachments)o;
		boolean ris = true;
		// confronta i collegamenti delle due istanze,
		// considerando anche il caso in cui non ci sono
		// collegamenti
		if (getAttachments() != null && aa.getAttachments() != null)
			{
			if (getAttachments().length == aa.getAttachments().length)
				{
				for (int i = 0; i < getAttachments().length; i++)
					{
					ris = ris &&
					getAttachments()[i].equals(aa.getAttachments()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getAttachments() == null && aa.getAttachments() == null)
			return ris;
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public ArchiAttachments copy()
		{
		ArchiAttachments a = new ArchiAttachments();
		if (getAttachments() != null)
			{
			a.setAttachments(new AttacDecl[getAttachments().length]);
			for (int i = 0; i < getAttachments().length; i++)
				{
				a.getAttachments()[i] = getAttachments()[i].copy();
				}
			}
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "ARCHI_ATTACHMENTS ";
		if (this.attachments == null || this.attachments.length == 0)
			{
			string = string + "void";
			}
		else
			{
			for (int i = 0; i < this.attachments.length - 1; i++)
				{
				string = string + this.attachments[i].toString() + ";";
				}
			string = string + this.attachments[this.attachments.length - 1].toString();
			}
		return string;
		}
	}