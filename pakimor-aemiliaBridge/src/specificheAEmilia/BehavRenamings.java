/**
 * 
 */
package specificheAEmilia;


/**
 * Le rinomine comportamentali sono dichiarate attraverso la seguente sintassi:
 * 
 * "BEHAV_RENAMINGS" <behav_renaming_decl_sequence>
 * 
 * dove <behav_renaming_decl_sequence>e'una sequenza non vuota di dichiarazioni di rinomina
 * comportamentali separate da punti e virgola.
 * 
 * @author Mirko
 *
 */
public class BehavRenamings implements AEmiliaBase
	{
	
	private BehavRenamingDecl[] behavRenamingDecls;

	public BehavRenamingDecl[] getBehavRenamingDecls() 
		{
		return behavRenamingDecls;
		}

	public void setBehavRenamingDecls(BehavRenamingDecl[] behavRenamingDecls) 
		{
		this.behavRenamingDecls = behavRenamingDecls;
		}
	
	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("BehavRenamings object");
		System.out.println("Behavioral Renaming sequence:");
		// stampa le rinomine comportamentali
		for (int i=0; i < getBehavRenamingDecls().length; i++)
			{
			System.out.print("Behavioral renaming number ");
			System.out.print(i);
			System.out.println(":");
			getBehavRenamingDecls()[i].print();
			}
		}

	/**
	 * Verifica se questo oggettoe'uguale all'oggetto indicato
	 * dal parametro del metodo.
	 * @param o - oggetto da confrontare.
	 * @return un valore booleano che indica se i due oggetti
	 * sono uguali.
	 */
	public boolean equals(Object o)
		{
		if (!(o instanceof BehavRenamings)) return false;
		BehavRenamings behavRenamings = (BehavRenamings)o;
		boolean ris = true;
		if (getBehavRenamingDecls().length == behavRenamings.getBehavRenamingDecls().length)
			{
			for (int i = 0; i < getBehavRenamingDecls().length; i++)
				{
				ris = ris &&
					getBehavRenamingDecls()[i].equals(behavRenamings.getBehavRenamingDecls()[i]);
				}
			return ris;
			}
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public BehavRenamings copy()
		{
		BehavRenamings behavRenamings = new BehavRenamings();
		// effettuamo una copia deep di getBehavHidingDecls()
		BehavRenamingDecl[] behavRenamingDecls = 
			new BehavRenamingDecl[getBehavRenamingDecls().length];
		for (int i = 0; i < getBehavRenamingDecls().length; i++)
			{
			behavRenamingDecls[i] = getBehavRenamingDecls()[i].copy();
			}
		behavRenamings.setBehavRenamingDecls(behavRenamingDecls);
		return behavRenamings;
		}

	/*
	 * "BEHAV_RENAMINGS" <behav_renaming_decl_sequence>
	 */
	@Override
	public String toString() 
		{
		String string = "ARCHI_ELEM_INSTANCES ";
		for (int i = 0; i < this.getBehavRenamingDecls().length - 1; i++)
			{
			string = string + this.getBehavRenamingDecls()[i].toString() + ";";
			}
		string = string + this.getBehavRenamingDecls()[
				this.getBehavRenamingDecls().length - 1].toString();
		return string;
		}
	}
