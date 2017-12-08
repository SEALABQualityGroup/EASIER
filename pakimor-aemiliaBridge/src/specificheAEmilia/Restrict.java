/**
 * 
 */
package specificheAEmilia;

/**
 * "RESTRICT" <identifier> ["[" <expr> "]"] "." <action_type_set_r>
 * 
 * Si riferisce ad un insieme di tipi azione di un AEI. In questo caso,
 * la dichiarazione di restrizione comportamentale contiene l'identificatore dell'AEI a cui i
 * tipi azione da restringere appartengono, un'espressione racchiusa tra parentesi quadre che: deve
 * essere valutata come un intero, rappresenta un selettore, deve essere priva di identificatori non 
 * dichiarati e invocazioni a generatori di numeri pseudo-casuali. Inoltre, dopo il punto deve essere
 * presente il tipo azione da restringere o un insieme di tipi azione. Il tipo
 * azione da restringere non puo' essere nascosto. I soli identificatori che possono essere presenti
 * nell'espressione di selezione sono quelli dei parametri formali costanti dichiarati
 * nell'intestazione del tipo architetturale.
 * 
 * @author Mirko
 *
 */
public class Restrict 
	extends BehavRestrictionDecl 
	{
	
	private AEIident aeIident;
	private ActionTypeSetR actionTypeSetR;
	
	public AEIident getAei() 
		{
		return aeIident;
		}
	
	public void setAei(AEIident aei) 
		{
		this.aeIident = aei;
		}
	
	public ActionTypeSetR getActionTypeSetR() 
		{
		return actionTypeSetR;
		}
	
	public void setActionTypeSetR(ActionTypeSetR actionTypeSetR) 
		{
		this.actionTypeSetR = actionTypeSetR;
		}
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("Restrict object");
		System.out.println("AEI:");
		// stampa l'AEI
		this.aeIident.print();
		// stampa l'insieme dei tipi azione da restringere
		System.out.println("Actions Restricting:");
		System.out.println(getActionTypeSetR());
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param at - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof Restrict)) return false;
		Restrict restrict = (Restrict)o;
		// confronta i nomi delle istanze
		boolean ris = getAei().equals(restrict.getAei());
		// confronta le azioni da nascondere
		ris = ris && getActionTypeSetR().equals(restrict.getActionTypeSetR());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Restrict copy()
		{
		Restrict restrict = new Restrict();
		AEIident aeIident = this.aeIident.copy();
		restrict.setAei(aeIident);
		if (getActionTypeSetR() != null)
			restrict.setActionTypeSetR(getActionTypeSetR().copy());
		return restrict;
		}

	/*
	 * "RESTRICT" <identifier> ["[" <expr> "]"] "." <action_type_set_r>
	 */
	@Override
	public String toString() 
		{
		String string = "RESTRICT " + this.getAei() + " ";
		string = string + ".";
		string = string + this.getActionTypeSetR().toString();
		return string;
		}
	}
