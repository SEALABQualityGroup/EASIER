/**
 * 
 */
package specificheAEmilia;


/**
 * Gli occultamenti comportamentali sono dichiarati secondo la seguente sintassi:
 * 
 * "BEHAV_HIDINGS" <behav_hiding_decl_sequence>
 * 
 * dove <behav_hiding_decl_sequence>e'una sequenza non vuota di dichiarazioni di  
 * occultamenti comportamentali separati da punti e virgola.
 * 
 * 
 * @author Mirko
 *
 */
public class BehavHidings implements AEmiliaBase
	{
	
	private BehavHidingDecl[] behavHidingDecls;

	public BehavHidingDecl[] getBehavHidingDecls() 
		{
		return behavHidingDecls;
		}

	public void setBehavHidingDecls(BehavHidingDecl[] behavHidingDecls) 
		{
		this.behavHidingDecls = behavHidingDecls;
		}
	
	/**
	 * Stampa sullo standard output le informazioni contenute
	 * in questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("BehavHidings object");
		System.out.println("Behavioral Hiding sequence:");
		// stampa gli occultamenti comportamentali
		for (int i=0; i < getBehavHidingDecls().length; i++)
			{
			System.out.print("Behavioral hiding number ");
			System.out.print(i);
			System.out.println(":");
			getBehavHidingDecls()[i].print();
			}
		}

	/**
	 * Confronta se questo oggettoe'uguale al parametro del metodo
	 * @param o - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof BehavHidings)) return false;
		BehavHidings behavHidings = (BehavHidings)o;
		// confronta le dichiarazioni di occultamento architetturale
		boolean ris = true;
		if (getBehavHidingDecls() != null && behavHidings.getBehavHidingDecls() != null)
			{
			if (getBehavHidingDecls().length == behavHidings.getBehavHidingDecls().length)
				{
				for (int i = 0; i < getBehavHidingDecls().length; i++)
					{
					ris = ris &&
					getBehavHidingDecls()[i].equals(behavHidings.getBehavHidingDecls()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getBehavHidingDecls() == null && behavHidings.getBehavHidingDecls() == null)
			return ris;
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public BehavHidings copy()
		{
		BehavHidings behavHidings = new BehavHidings();
		// effettuamo una copia deep di getBehavHidingDecls()
		BehavHidingDecl[] behavHidingDecls = new BehavHidingDecl[getBehavHidingDecls().length];
		for (int i = 0; i < getBehavHidingDecls().length; i++)
			{
			behavHidingDecls[i] = getBehavHidingDecls()[i].copy();
			}
		behavHidings.setBehavHidingDecls(behavHidingDecls);
		return behavHidings;
		}

	@Override
	public String toString() 
		{
		String string = "BEHAV_HIDINGS ";
		for (int i = 0; i < this.getBehavHidingDecls().length - 1; i++)
			{
			string = string + this.getBehavHidingDecls()[i].toString() + ";";
			}
		string = string + this.getBehavHidingDecls()[this.getBehavHidingDecls().length - 1].toString();
		return string;
		}
	}
