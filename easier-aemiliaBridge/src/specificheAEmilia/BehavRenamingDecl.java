/**
 * 
 */
package specificheAEmilia;

/**
 * <behav_renaming_decl> ::= "RENAME" <identifier> ["[" <expr> "]"] "." <identifier>
 * "AS" <identifier> ["[" <expr> "]"]
 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * "RENAME" <identifier> "[" <expr> "]" "." <identifier>
 * "AS" <identifier> ["[" <expr> "]"]
 * 
 * Nella sua forma piu' semplice, una dichiarazione di rinomina comportamentale contiene l'identificatore
 * dell'AEI al quale il tipo azione da rinominare appartiene, un'espressione di selezione, che deve
 * essere valutata come un intero e deve essere libera da identificatori non dichiarati e invocazioni
 * a generatori di numeri pseudo-casuali, e l'identificatore rinominante che puo' essere seguito da
 * un selettore.
 * Il tipo azione non puo' essere nascosto o ristretto.
 * I soli identificatori che si possono presentare nei selettori sono quelli dei parametri formali costanti
 * dichiarati nell'intestazione del tipo architetturale.
 * La forma piu' complessa rinomina piu' AEI attraverso un meccanismo di indicizzazione.
 * Questo richiede la specifica di un identificatore indice, che puo' essere presente nelle espressioni di
 * selezione, e di un range fornito tramite due espressioni che devono essere valutate come due interi.
 * Queste due espressioni devono essere libere da identificatori non dichiarati e da invocazioni
 * a generatori di numeri pseudo-casuali, con il valore della prima espressione che non puo'
 * essere piu' grande del valore della seconda espressione.
 * Se un tipo azione o interazione non architetturale osservabile e non ristretta viene rinominata,
 *e'convertito al tipo azione rinominante specificato. Ogni tipo azione rinominante
 * deve essere differente dalle parole chive di NuSMV. 
 * 
 * @author Mirko
 *
 */
public class BehavRenamingDecl implements AEmiliaBase
	{

	private String aei;
	private String originalActionType;
	private Expression originalSelector;
	private String renamingActionType;
	private Expression renamingSelector;
	
	public String getAei() 
		{
		return aei;
		}
	
	public void setAei(String aei) 
		{
		this.aei = aei;
		}
	
	public String getOriginalActionType() 
		{
		return originalActionType;
		}
	
	public void setOriginalActionType(String actionTypeOriginal) 
		{
		this.originalActionType = actionTypeOriginal;
		}
	
	public Expression getOriginalSelector() 
		{
		return originalSelector;
		}
	
	public void setOriginalSelector(Expression selectorOriginal) 
		{
		this.originalSelector = selectorOriginal;
		}
	
	public String getRenamingActionType() 
		{
		return renamingActionType;
		}
	
	public void setRenamingActionType(String actionTypeRenaming) 
		{
		this.renamingActionType = actionTypeRenaming;
		}
	
	public Expression getRenamingSelector() 
		{
		return renamingSelector;
		}
	
	public void setRenamingSelector(Expression selectorRenaming) 
		{
		this.renamingSelector = selectorRenaming;
		}
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("BehavRenamingDecl object");
		System.out.println("AEI:");
		// stampa l'AEI
		System.out.println(getAei());
		// stampa l'eventuale selettore originale
		if (getOriginalSelector() != null)
			{
			System.out.println("Original selector:");
			getOriginalSelector().print();
			}
		// stampa il tipo azione
		System.out.println("Action type original:");
		System.out.println(getOriginalActionType());
		// stampa il tipo azione rinominante
		System.out.println("Action type renaming");
		System.out.println(getRenamingActionType());
		// stampa l'eventuale selettore rinominante
		if (getRenamingSelector() != null)
			{
			System.out.println("Renaming selector");
			getRenamingSelector().print();
			}
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param at - oggetto BehavRenamingDecl da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof BehavRenamingDecl)) return false;
		BehavRenamingDecl behavRenamingDecl = (BehavRenamingDecl)o;
		// confronta i nomi delle istanze
		boolean ris = getAei().equals(behavRenamingDecl.getAei());
		// confronta gli eventuali selettori originali
		if (getOriginalSelector() != null && behavRenamingDecl.getOriginalSelector() != null)
			ris = ris && getOriginalSelector().equals(behavRenamingDecl.getOriginalSelector());
		else if (getOriginalSelector() == null && behavRenamingDecl.getOriginalSelector() == null)
			ris = ris && true;
		else ris = ris && false;
		// confronta i tipi azione originali
		ris = ris && getOriginalActionType().equals(behavRenamingDecl.getOriginalActionType());
		// confronta i tipi azione rinominanti
		ris = ris && getRenamingActionType().equals(behavRenamingDecl.getRenamingActionType());
		// confronta gli eventuali selettori rinominanti
		if (getRenamingSelector() != null && behavRenamingDecl.getRenamingSelector() != null)
			ris = ris && getRenamingSelector().equals(behavRenamingDecl.getRenamingSelector());
		else if (getRenamingSelector() == null && behavRenamingDecl.getRenamingSelector() == null)
			ris = ris && true;
		else ris = ris && false;
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public BehavRenamingDecl copy()
		{
		BehavRenamingDecl behavRenamingDecl = new BehavRenamingDecl();
		if (getAei() != null)
			behavRenamingDecl.setAei(new String(getAei()));
		if (getOriginalActionType() != null)
			behavRenamingDecl.setOriginalActionType(new String(getOriginalActionType()));
		if (getOriginalSelector() != null)
			behavRenamingDecl.setOriginalSelector(getOriginalSelector().copy());
		if (getRenamingActionType() != null)
			behavRenamingDecl.setRenamingActionType(new String(getRenamingActionType()));
		if (getRenamingSelector() != null)
			behavRenamingDecl.setRenamingSelector(getRenamingSelector().copy());
		return behavRenamingDecl;
		}

	/*
	 * "RENAME" <identifier> ["[" <expr> "]"] "." <identifier>
	 * "AS" <identifier> ["[" <expr> "]"]	 
	 */
	@Override
	public String toString() 
		{
		String string = "RENAME " + this.getAei() + " ";
		string = string + (this.getOriginalSelector() == null ? "" : 
			"[" + this.getOriginalSelector().toString() + "]");
		string = string + ".";
		string = string + this.getOriginalActionType();
		string = string + " AS ";
		string = string + this.getRenamingActionType();
		string = string + (this.getRenamingSelector() == null ? "" :
			"[" + this.getRenamingSelector().toString() + "]");
		return string;
		}
	}
