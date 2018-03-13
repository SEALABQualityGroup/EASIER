/**
 * 
 */
package specificheAEmilia;

/**
 * <behav_restriction_decl> ::= "RESTRICT" "OBS_INTERNALS"
 * | "RESTRICT" "OBS_INTERACTIONS"
 * | "RESTRICT" "ALL_OBSERVABLES"
 * | "RESTRICT" <identifier> ["[" <expr> "]"] "." <action_type_set_r>
 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * "RESTRICT" <identifier> "[" <expr> "]" "." <action_type_set_r>
 * 
 * <action_type_set_r> ::= <identifier>
 * | "OBS_INTERNALS"
 * | "OBS_INTERACTIONS"
 * | "ALL_OBSERVABLES"
 * 
 * La prima restrizione consiste nell'evitare l'esecuzione di tutti i tipi azione osservabili,
 * che sono interni agli AEI della specifica AEmilia.
 * La seconda restrizione consiste nell'evitare l'esecuzione di tutte le interazioni non architetturali 
 * osservabili.
 * La terza consiste nell'evitare l'esecuzione dei tipi azioni interne e interazioni non architetturali
 * osservabili.
 * La quarta restrizione si riferisce ad un insieme di tipi azione di un AEI. In questo caso,
 * la dichiarazione di restrizione comportamentale contiene l'identificatore dell'AEI a cui i
 * tipi azione da restringere appartengono, un'espressione racchiusa tra parentesi quadre che: deve
 * essere valutata come un intero, rappresenta un selettore, deve essere priva di identificatori non 
 * dichiarati e invocazioni a generatori di numeri pseudo-casuali. Inoltre, dopo il punto deve essere
 * presente il tipo azione da restringere o un insieme di tipi azione. Il tipo
 * azione da restringere non puo' essere nascasto. I soli identificatori che possono essere presenti
 * nell'espressione di selezione sono quelli dei parametri formali costanti dichiarati
 * nell'intestazione del tipo architetturale.
 * L'ultima restrizione utilizza un meccanismo di indicizzazione. Questo richiede la specifica
 * dell'identificatore indice, che puo' essere presente nell'espressione di selezione, insieme
 * con il suo range, dato da due espressioni che devono essere valutate come interi. 
 * Queste due espressioni devono essere prive da identificatori non dichiarati e invocazioni
 * a generatori di numeri pseudo-casuali, con il valore della prima espressione che non deve
 * essere piu' grande del valore della seconda espressione.
 * Se un tipo di azione interno e osservabile viene ristretto, non puo' essere eseguito.
 * Se viene ristretta un'interazione non architetturale, nessuna dei tipi azioni con cui si sincronizza
 * puo' essere eseguita. 
 * 
 * @author Mirko
 *
 */
public class BehavRestrictionDecl implements AEmiliaBase
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("BehavRestrictionDecl object");
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param o - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof BehavRestrictionDecl)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public BehavRestrictionDecl copy()
		{
		BehavRestrictionDecl behavRestrictionDecl = new BehavRestrictionDecl();
		return behavRestrictionDecl;
		}	
	
	}
