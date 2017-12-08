/**
 * 
 */
package specificheAEmilia;

/**
 * "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * "RENAME" <identifier> "[" <expr> "]" "." <identifier>
 * "AS" <identifier> ["[" <expr> "]"]
 * 
 * Rinomina piu' AEI attraverso un meccanismo di indicizzazione.
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
public class BehavRenamingDeclInd 
	extends BehavRenamingDecl 
	implements AEmiliaBase
	{
	
	private String index;
	private Expression beginningExpr;
	private Expression endingExpr;
	
	public String getIndex() 
		{
		return index;
		}
	
	public void setIndex(String index) 
		{
		this.index = index;
		}
	
	public Expression getBeginningExpr() 
		{
		return beginningExpr;
		}
	
	public void setBeginningExpr(Expression beginningExpr) 
		{
		this.beginningExpr = beginningExpr;
		}
	
	public Expression getEndingExpr() 
		{
		return endingExpr;
		}
	
	public void setEndingExpr(Expression endingExpr) 
		{
		this.endingExpr = endingExpr;
		}
	
	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 */
	public void print()
		{
		System.out.println("BehavRenamingDeclInd object");
		// stampa le informazioni contenute nella
		// superclasse BehavRenamingDeclInd
		super.print();
		// stampa l'indice
		System.out.println("Index identifier: "+getIndex());
		System.out.println("Indexing range top");
		// stampa l'espressione iniziale
		getBeginningExpr().print();
		System.out.println("Indexing range end");
		// stampa l'espressione finale
		getEndingExpr().print();
		}

	/**
	 * Confornta due oggetti BehavRenamingDeclInd e restituisce true
	 * se i due oggetti contengono le stesse informazioni.
	 * @param o - oggetto BehavRenamingDeclInd da confrontare con questo.
	 * @return un valore booleano indicante se i due oggetti
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof BehavRenamingDeclInd)) return false;
		BehavRenamingDeclInd behavRenamingDeclInd = (BehavRenamingDeclInd)o;
		// confronta due dichiarazioni indicizzate come
		// oggetti della superclasse BehavRenamingDeclInd
		boolean ris = super.equals(behavRenamingDeclInd);
		// confronta le due espressioni di fine
		ris = ris && getEndingExpr().equals(((BehavRenamingDeclInd)behavRenamingDeclInd).getEndingExpr());
		// confronta le due espressioni di inizio
		ris = ris && getBeginningExpr().equals(((BehavRenamingDeclInd)behavRenamingDeclInd).getEndingExpr());
		// confronta i due indici
		ris = ris && getIndex().equals(((BehavRenamingDeclInd)behavRenamingDeclInd).getIndex());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public BehavRenamingDeclInd copy()
		{
		BehavRenamingDeclInd behavRenamingDeclInd = new BehavRenamingDeclInd();
		behavRenamingDeclInd.setAei(new String(getAei()));
		behavRenamingDeclInd.setEndingExpr(getEndingExpr().copy());
		behavRenamingDeclInd.setIndex(new String(getIndex()));
		behavRenamingDeclInd.setBeginningExpr(getBeginningExpr().copy());
		behavRenamingDeclInd.setOriginalActionType(new String(getOriginalActionType()));
		behavRenamingDeclInd.setOriginalSelector(getOriginalSelector().copy());
		behavRenamingDeclInd.setRenamingActionType(new String(getRenamingActionType()));
		if (behavRenamingDeclInd.getRenamingSelector() != null)
			behavRenamingDeclInd.setRenamingSelector(getRenamingSelector().copy());
		return behavRenamingDeclInd;
		}

	/*
	 * "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
	 * "RENAME" <identifier> "[" <expr> "]" "." <identifier>
	 * "AS" <identifier> ["[" <expr> "]"]
	 */
	@Override
	public String toString() 
		{
		String string = "FOR_ALL " + this.getIndex() + " IN " + this.getBeginningExpr().toString() + 
			".." + this.getEndingExpr().toString() + " "; 
		return string + super.toString();
		}
	}
