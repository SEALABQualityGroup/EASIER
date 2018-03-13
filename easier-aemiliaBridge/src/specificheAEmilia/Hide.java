/**
 * 
 */
package specificheAEmilia;

/**
 * "HIDE" <identifier> ["[" <expr> "]"] "." <action_type_set_h>
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
 * 
 * @author Mirko
 *
 */
public class Hide 
	extends BehavHidingDecl 
	{
	
	private AEIident aeIident;
	private ActionTypeSetH actionTypeSetH;
	
	public AEIident getAei() 
		{
		return aeIident;
		}
	
	public void setAei(AEIident aeIident) 
		{
		this.aeIident = aeIident;
		}
	
	public ActionTypeSetH getActionTypeSetH() 
		{
		return actionTypeSetH;
		}
	
	public void setActionTypeSetH(ActionTypeSetH actionTypeSetH) 
		{
		this.actionTypeSetH = actionTypeSetH;
		}
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("Hide object");
		System.out.println("AEI:");
		// stampa l'AEI
		this.aeIident.print();
		// stampa l'insieme dei tipi azione da nascondere
		System.out.println("Actions Hiding:");
		System.out.println(getActionTypeSetH());
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param at - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof Hide)) return false;
		Hide hide = (Hide)o;
		// confronta i nomi delle istanze
		boolean ris = getAei().equals(hide.getAei());
		// confronta le azioni da nascondere
		ris = ris && getActionTypeSetH().equals(hide.getActionTypeSetH());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Hide copy()
		{
		Hide hide = new Hide();
		AEIident aeIident = this.aeIident.copy();
		hide.setAei(aeIident);
		if (getActionTypeSetH() != null)
			hide.setActionTypeSetH(getActionTypeSetH().copy());
		return hide;
		}

	/*
	 * "HIDE" <identifier> ["[" <expr> "]"] "." <action_type_set_h>
	 * 
	 * dove
	 *
	 *  <action_type_set_h> ::= <identifier>
	 *  | "INTERNALS"
	 *  | "INTERACTIONS"
	 *  | "ALL"
	 */
	@Override
	public String toString() 
		{
		String string = "HIDE " + this.getAei() + " ";
		string = string + ".";
		string = string + this.getActionTypeSetH().toString();
		return string;
		}
	}
