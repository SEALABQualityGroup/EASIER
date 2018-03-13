/**
 * 
 */
package specificheAEmilia;

/**
 * "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * "RESTRICT" <identifier> "[" <expr> "]" "." <action_type_set_r>
 * 
 * Utilizza un meccanismo di indicizzazione. Questo richiede la specifica
 * dell'identificatore indice, che puo' essere presente nell'espressione di selezione, insieme
 * con il suo range, dato da due espressioni che devono essere valutate come interi. 
 * Queste due espressioni devono essere prive da identificatori non dichiarati e invocazioni
 * a generatori di numeri pseudo-casuali, con il valore della prima espressione che non deve
 * essere piu' grande del valore della seconda espressione.

 * @author Mirko
 *
 */
public class RestrictInd 
	extends Restrict 
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
		System.out.println("RestrictInd object");
		// stampa le informazioni della superclasse Restrict
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
		if (!(o instanceof RestrictInd)) return false;
		RestrictInd restrictInd = (RestrictInd)o;
		// confronta i campi della superclasse
		boolean ris = super.equals(restrictInd);
		ris = ris && getIndex().equals(restrictInd.getIndex());
		// confronta i campi, considerando anche la
		// falcoltativit� di alcuni di loro
		ris = ris && getBeginningExpr().equals(restrictInd.getBeginningExpr());
		if (getEndingExpr() != null && restrictInd.getEndingExpr() != null)
			ris = ris && getEndingExpr().equals(restrictInd.getEndingExpr());
		else if (getEndingExpr() == null && restrictInd.getEndingExpr() == null)
			ris = ris && true;
		else ris = ris && false;
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public RestrictInd copy()
		{
		RestrictInd restrictInd = new RestrictInd();
		restrictInd.setActionTypeSetR(getActionTypeSetR().copy());
		AEIident aeIident = this.getAei().copy();
		restrictInd.setAei(aeIident);
		restrictInd.setEndingExpr(getEndingExpr().copy());
		restrictInd.setIndex(new String(getIndex()));
		restrictInd.setBeginningExpr(getBeginningExpr().copy());
		return restrictInd;
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
