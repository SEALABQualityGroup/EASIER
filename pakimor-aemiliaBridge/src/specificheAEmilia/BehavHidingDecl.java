/**
 * 
 */
package specificheAEmilia;

/**
 * Una dichiarazione di occultamento comportamentalee'definita dalla seguente grammatica:
 * 
 * <behav_hiding_decl> ::= "HIDE" "INTERNALS"
 * | "HIDE" "INTERACTIONS"
 * | "HIDE" "ALL"
 * | "HIDE" <identifier> ["[" <expr> "]"] "." <action_type_set_h>
 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * "HIDE" <identifier> "[" <expr> "]" "." <action_type_set_h>
 * 
 * @author Mirko
 *
 */
public class BehavHidingDecl implements AEmiliaBase
	{
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("BehavHidingDecl object");
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param o - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof BehavHidingDecl)) return false;
		return true;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public BehavHidingDecl copy()
		{
		BehavHidingDecl behavHidingDecl = new BehavHidingDecl();
		return behavHidingDecl;
		}	
	}
