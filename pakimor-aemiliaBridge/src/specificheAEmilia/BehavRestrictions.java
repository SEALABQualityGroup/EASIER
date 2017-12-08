package specificheAEmilia;


/**
 * Le restrizioni comportamentali sono dichiarate attraverso la seguente sintassi:
 * 
 * "BEHAV_RESTRICTIONS" <behav_restriction_decl_sequence>
 * 
 * dove <behav restriction decl sequence>e'una sequenza non vuota di dichiarazioni
 * di restrizione comportamentale separate da punti e virgole.
 * 
 * @author Mirko
 *
 */
public class BehavRestrictions implements AEmiliaBase
	{
	
	private BehavRestrictionDecl[] behavRestrictionDecls;

	public BehavRestrictionDecl[] getBehavRestrictionDecls() 
		{
		return behavRestrictionDecls;
		}

	public void setBehavRestrictionDecls(
			BehavRestrictionDecl[] behavRestrictionDecls) 
		{
		this.behavRestrictionDecls = behavRestrictionDecls;
		}
	
	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("BehavRestrictions object");
		System.out.println("Behavioral Restriction sequence:");
		// stampa le restrizioni comportamentali
		for (int i=0; i < getBehavRestrictionDecls().length; i++)
			{
			System.out.print("Behavioral Restriction number ");
			System.out.print(i);
			System.out.println(":");
			getBehavRestrictionDecls()[i].print();
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
		if (!(o instanceof BehavRestrictions)) return false;
		BehavRestrictions behavRestrictions = (BehavRestrictions)o;
		boolean ris = true;
		if (getBehavRestrictionDecls().length == behavRestrictions.getBehavRestrictionDecls().length)
			{
			for (int i = 0; i < getBehavRestrictionDecls().length; i++)
				{
				ris = ris &&
					getBehavRestrictionDecls()[i].equals(
							behavRestrictions.getBehavRestrictionDecls()[i]);
				}
			return ris;
			}
		else return false;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public BehavRestrictions copy()
		{
		BehavRestrictions behavRestrictions = new BehavRestrictions();
		// effettuamo una copia deep di getBehavHidingDecls()
		BehavRestrictionDecl[] behavRestrictionDecls = 
			new BehavRestrictionDecl[getBehavRestrictionDecls().length];
		for (int i = 0; i < getBehavRestrictionDecls().length; i++)
			{
			behavRestrictionDecls[i] = getBehavRestrictionDecls()[i].copy();
			}
		behavRestrictions.setBehavRestrictionDecls(behavRestrictionDecls);
		return behavRestrictions;
		}

	/*
	 * "BEHAV_RESTRICTIONS" <behav_restriction_decl_sequence>
	 */
	@Override
	public String toString() 
		{
		String string = "BEHAV_RESTRICTIONS ";
		for (int i = 0; i < this.getBehavRestrictionDecls().length - 1; i++)
			{
			string = string + this.getBehavRestrictionDecls()[i].toString() + ";";
			}
		string = string + this.getBehavRestrictionDecls()[
				this.getBehavRestrictionDecls().length - 1].toString();
		return string;
		}
	}
