/**
 * 
 */
package specificheAEmilia;

/**
 * "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * "HIDE" <identifier> "[" <expr> "]" "." <action_type_set_h>
 * 
 * dove
 *
 *  <action_type_set_h> ::= <identifier>
 *  | "INTERNALS"
 *  | "INTERACTIONS"
 *  | "ALL"

 * Nasconde un insieme di tipi azione di uno specifico AEI. 
 * <identifier>e'l'AEI a cui i tipi azione da nascondere appartengono.
 * L'espressione tra parentesi quadre deve essere valutata come un intero e
 * rappresenta un selettore e deve essere libero da identificatori non dichiarati e
 * invocazioni a generatori di numeri pseudo-casuali. 
 * Le opzioni definite in action_type_set_h definiscono insiemi di tipi
 * azione da nascondere o un identificatore del tipo azione da nascondere.
 * Se specificato, il tipo azione da nascondere deve essere presente nel comportamento
 * dell'AEI e non puo' essere un'interazione architetturale.
 * I soli identificatori che si possono presentare nell'espressione di selezione sono
 * quelli relativi ai parametri formali costanti dichiarati nell'intestazione del
 * tipo architetturale.
 * Il primo identificatoree'un indice che puo' presentarsi nel selettore ed il suo valore
 * deve essere compreso all'interno del suo range dichiarato tramite le due espressioni
 * separate da '..' e che devono essere valutate come interi.
 * Queste due espressioni devono essere libere da identificatori non dichiarati e 
 * invocazioni a generatori di numeri pseudo-casuali, con il valore della prima espressione
 * che non deve essere piu' grande del valore della seconda espressione.
 * Se un tipo di azione internae'nascosta, viene convertita al tipo di azione speciale
 * invisible. Se viene nascosta un'interazione non architetturale, tutti i tipi azione sincronizzanti
 * in cuie'coinvolta vengono convertiti nel tipo azione invisible. 
 * In entrambi i casi, tutti i parametri del tipo azione vengono cancellati.
 * 
 * @author Mirko
 *
 */
public class HideInd 
	extends Hide
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
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 */
	public void print()
		{
		System.out.println("HideInd object");
		// stampa le informazioni della superclasse Hide
		super.print();
		System.out.println("Index identifier: "+getIndex());
		System.out.println("Top of indexed range");
		getBeginningExpr().print();
		System.out.println("End of indexed range");
		getEndingExpr().print();
		}

	/**
	 * Confornta questo oggetto con quello referenziato dal
	 * parametro del metodo.
	 * @param o - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof HideInd)) return false;
		HideInd hideInd = (HideInd)o;
		// confronta i campi della superclasse
		boolean ris = super.equals(hideInd);
		ris = ris && getIndex().equals(hideInd.getIndex());
		// confronta i campi, considerando anche la
		// falcoltativit� di alcuni di loro
		ris = ris && getBeginningExpr().equals(hideInd.getBeginningExpr());
		if (getEndingExpr() != null && hideInd.getEndingExpr() != null)
			ris = ris && getEndingExpr().equals(hideInd.getEndingExpr());
		else if (getEndingExpr() == null && hideInd.getEndingExpr() == null)
			ris = ris && true;
		else ris = ris && false;
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public HideInd copy()
		{
		HideInd hideInd = new HideInd();
		hideInd.setActionTypeSetH(getActionTypeSetH().copy());
		AEIident aeIident = this.getAei().copy();
		hideInd.setAei(aeIident.copy());
		hideInd.setEndingExpr(getEndingExpr().copy());
		hideInd.setIndex(new String(getIndex()));
		hideInd.setBeginningExpr(getBeginningExpr().copy());
		return hideInd;
		}

	/*
	 * "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
	 * "HIDE" <identifier> "[" <expr> "]" "." <action_type_set_h>
	 */
	@Override
	public String toString() 
		{
		String string = "FOR_ALL " + this.index + " IN " + this.beginningExpr.toString() +
			".." + this.endingExpr.toString() + " ";
		string = string + super.toString();
		return string;
		}
	}
