package specificheAEmilia;

/**
 * Rappresenta una dichiarazione di collegamento tra istanze di
 * elementi architetturali ed ha la seguente forma sintattica:
 * <pre>
 * <code>
 * &lt;architectural_attachment_decl&gt; ::=
 * "FROM" &lt;identifier&gt; ["[" &lt;expr&gt; "]"] "." &lt;identifier&gt;
 * "TO" &lt;identifier&gt; ["[" &lt;expr&gt; "]"] "." &lt;identifier&gt;
 * | "FOR_ALL" &lt;identifier&gt; "IN" &lt;expr&gt; ".." &lt;expr&gt;
 * ["AND" "FOR_ALL" &lt;identifier&gt; "IN" &lt;expr&gt; ".." &lt;expr&gt;]
 * "FROM" &lt;identifier&gt; ["[" &lt;expr&gt; "]"] "." &lt;identifier&gt;
 * "TO" &lt;identifier&gt; ["[" &lt;expr&gt; "]"] "." &lt;identifier&gt;
 * </code>
 * </pre>
 * Nella sua forma piu' semplice, una dichiarazione di collegamento
 * architetturale contiene l'indicazione di un'interazione di
 * output seguita dall'indicazione di un'interazione di input.
 * Ognuna delle due interazionie'espressa in una notazione
 * puntata attraverso l'identificatore dell' AEI a cui
 * l'interazione appartiene, un'espressione intera racchiusa
 * tra parentesi quadre, che rappresenta un selettore e deve
 * essere privo di identificatori non dichiarati e invocazioni
 * a generatori di numeri pseudo-casuali, e l'identificatore
 * dell'interazione. L'interazione non deve essere di tipo
 * architetturale.
 * I due AEI devono essere diversi tra loro. Almeno una delle due
 * interazioni deve essere una uni-interaction, e almeno una di
 * loro deve essere un'azione passiva all'interno del comportamento
 * dell'AEI a cui appartiene. I soli identificatori che si possono
 * presentare nell'espressione di selezione possibile sono quelli
 * dei parametri formali costanti dichiarati nell'intestazione del
 * tipo architetturale. La seconda formae'utile per dichiarare
 * in modo conciso diversi collegamenti architetturali attraverso
 * un meccanismo di indicizzazione. Questo richiede la specifica
 * aggiuntiva di fino a due identificatori di indice differenti,
 * che si possono presntare nell'espressioni di selezione, insieme
 * con i loro intervalli, ognuno dei qualie'dato da due
 * espressioni intere. Queste due espressioni devono essere prive
 * di identificari non dichiarati e invocazioni di numeri
 * generatori di pseudo-casuali, con il valore della prima
 * espressione non più grande del valore della seconda espressione.
 * Tutte le uni-interazioni attacate alla stessa and o or
 * interazione deve appartenere a AEI differenti. Tra tutte le
 * uni-interazioni attaccate alla stessa and-interazione passiva,
 * al più una puo' essere un'azione non passiva nel comportamento
 * dell'AEI al quale appartiene.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class AttacDecl implements AEmiliaBase
	{

	private AEIident outputAeiIdent;
	
	private AEIident inputAeiIdent;
	
	/**
	 * Nome dell'interazione di output appartenente
	 * all'istanza dell'elemento architetturale al quale
	 * arriva il collegamento.
	 */
	private String inputInteraction;

	/**
	 * Nome dell'interazione di input appartenente
	 * all'istanza dell'elemento architetturale dal quale
	 * parte il collegamento.
	 */
	private String outputInteraction;

	/**
	 * Crea un oggetto AttacDecl vuoto.
	 *
	 */
	public AttacDecl() {
	}
	
	/**
	 * Crea un oggetto AttacDecl con le informazioni fornite
	 * come parametri al costruttore.
	 */
	public AttacDecl(AEIident outputAeiIdent, 
			String outputInteraction,
			AEIident inputAeiIdent,
			String inputInteraction) 
		{
		// assegna l'aei di partenza
		this.outputAeiIdent = outputAeiIdent;
		// assegna il nome dell'interazione di input
		this.inputInteraction = inputInteraction;
		// assegna l'aei di arrivo
		this.inputAeiIdent = inputAeiIdent;
		// assegna il nome delle interazioni di output
		this.outputInteraction = outputInteraction;
		}

	public AttacDecl(String outputAei, Expression fromSelector,
			String outputInteraction2, String inputAei, Expression toSelector,
			String inputInteraction2) 
		{
		AEIident aeIident = new AEIident(outputAei, fromSelector);
		AEIident aeIident2 = new AEIident(inputAei, toSelector);
		this.inputAeiIdent = aeIident2;
		this.inputInteraction = inputInteraction2;
		this.outputAeiIdent = aeIident;
		this.outputInteraction = outputInteraction2;
		}

	/**
	 * Restituisce l'istanza di un elemento architetturale
	 * dalla quale parte il collegamento.
	 * @return oggetto indicante un'istanza di un
	 * elemento architetturale.
	 */
	public AEIident getOutputAei() 
		{
		// resttuisce l'aei di partenza
		return this.outputAeiIdent;
		}

	/**
	 * Restituisce l'azione di un'istanza di un elemento
	 * architetturale corrispondente all'interazione
	 * di input.
	 * @return oggetto String che indica un'interazione di
	 * input.
	 */
	public String getInputInteraction() 
		{
		// restituisce il nome dell'interazione di input
		return this.inputInteraction;
		}

	/**
	 * Restituisce l'azione di un'istanza di un elemento
	 * architetturale corrispondente all'interazione
	 * di output.
	 * @return oggetto String che indica un'interazione di
	 * output.
	 */
	public String getOutputInteraction() 
		{
		// resttuisce il nome dell'interazione di output
		return this.outputInteraction;
		}

	/**
	 * Restituisce l'istanza di un elemento architetturale
	 * alla quale arriva il collegamento.
	 * @return oggetto indicante un'istanza di un
	 * elemento architetturale.
	 */
	public AEIident getInputAei() 
		{
		// restituisce l'aei di arrivo
		return this.inputAeiIdent;
		}

	/**
	 * Imposta una nuova istanza di un elemento architetturale
	 * dalla quale parte il collegamento.
	 * @param outputAei - oggetto che indica un'istanza di
	 * un elemento architetturale.
	 */
	public void setOutputAei(AEIident outputAei) 
		{
		// assegna l'aei di partenza
		this.outputAeiIdent = outputAei;
		}

	/**
	 * Imposta una nuova interazione di input per il
	 * collegamento.
	 * @param inputInteraction - oggetto indicante
	 * un'interazione di input.
	 */
	public void setInputInteraction(String inputInteraction) 
		{
		// assegna il nome dell'interazione di input
		this.inputInteraction = inputInteraction;
		}

	/**
	 * Imposta una nuova interazione di output per il
	 * collegamento.
	 * @param outputInteraction - oggetto String indicante
	 * un'interazione di output.
	 */
	public void setOutputInteraction(String outputInteraction) 
		{
		// assegna il nome dell'interazione di output
		this.outputInteraction = outputInteraction;
		}

	/**
	 * Imposta una nuova istanza di un elemento architetturale
	 * alla quale arriva il collegamento.
	 * @param inputAei - oggetto che indica un'istanza di
	 * un elemento architetturale.
	 */
	public void setInputAei(AEIident inputAei) 
		{
		// assegna l'aei di arrivo
		this.inputAeiIdent = inputAei;
		}

	/**
	 * Stampa sullo standard output le informazione relative
	 * a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("AttacDecl object");
		System.out.println("Output AEIs: "+getOutputAei());
		System.out.println("Output interaction: "+getOutputInteraction());
		System.out.println("Input AEIs: "+getInputAei());
		System.out.println("Input interaction: "+getInputInteraction());
		}

	/**
	 * Verifica se questo oggettoe'uguale al parametro di
	 * tale metodo.
	 * @param ad - oggetto AttacDecl da confrontare.
	 * @return un valore booleano indicante se i due oggetti
	 * hanno le stesse informazioni.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof AttacDecl)) return false;
		AttacDecl ad = (AttacDecl)o;
		boolean ris = getOutputAei().equals(ad.getOutputAei());
		ris = ris && getInputInteraction().equals(ad.getInputInteraction());
		ris = ris && getOutputInteraction().equals(ad.getOutputInteraction());
		ris = ris && getInputAei().equals(ad.getInputAei());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public AttacDecl copy()
		{
		AttacDecl a = new AttacDecl();
		a.setOutputAei(this.outputAeiIdent.copy());
		a.setInputInteraction(new String(getInputInteraction()));
		a.setOutputInteraction(new String(getOutputInteraction()));
		a.setInputAei(this.inputAeiIdent.copy());
		return a;
		}
	@Override
	public String toString() 
		{
		String string = "FROM " + this.outputAeiIdent + "." + this.outputInteraction + " ";
		string = string + "TO " + this.inputAeiIdent + "." + this.inputInteraction;
		return string;
		}
	}