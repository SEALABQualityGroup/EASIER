/**
 * 
 */
package specificheAEmilia;


/**
 * La terza sezione di una specifica AEmilia ha la seguente sintassi:
 * 
 * ["BEHAV_VARIATIONS" [<behav_hidings>] [<behav_restrictions>] [<behav_renamings>]]
 * 
 * Questa sezionee'opzionale. See'presente, almeno una delle tre sottosezioni deve essere
 * presente.
 * 
 * @author Mirko
 *
 */
public class BehavVariations implements AEmiliaBase
	{
	
	private BehavHidings behavHidings;
	private BehavRestrictions behavRestrictions;
	private BehavRenamings behavRenamings;
	
	public BehavHidings getBehavHidings() 
		{
		return behavHidings;
		}
	
	public void setBehavHidings(BehavHidings behavHidings) 
		{
		this.behavHidings = behavHidings;
		}
	
	public BehavRestrictions getBehavRestrictions() 
		{
		return behavRestrictions;
		}
	
	public void setBehavRestrictions(BehavRestrictions behavRestrictions) 
		{
		this.behavRestrictions = behavRestrictions;
		}
	
	public BehavRenamings getBehavRenamings() 
		{
		return behavRenamings;
		}
	
	public void setBehavRenamings(BehavRenamings behavRenamings) 
		{
		this.behavRenamings = behavRenamings;
		}
	
	/**
	 * Stampa sullo standard output le informazioni relative
	 * a questo oggetto.
	 *
	 */
	public void print()
		{
		System.out.println("BehavVariations object");
		if (getBehavHidings() != null)
			{
			System.out.println("BehavHidings object");
			getBehavHidings().print();
			}
		if (getBehavRestrictions() != null)
			{
			System.out.println("BehavRestrictions object");
			getBehavRestrictions().print();
			}
		if (getBehavRenamings() != null)
			{
			System.out.println("BehavRenamings object");
			getBehavRenamings().print();
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
		if (!(o instanceof BehavVariations)) return false;
		BehavVariations behavVariations = (BehavVariations)o;
		boolean ris = true;
		if (getBehavHidings() != null && behavVariations.getBehavHidings() == null)
			ris = false;
		else if (getBehavHidings() == null && behavVariations.getBehavHidings() != null)
			ris = false;
		else if (getBehavHidings() == null && behavVariations.getBehavHidings() == null)
			ris = ris && true;
		else
			ris = ris && getBehavHidings().equals(behavVariations.getBehavHidings());
		if (getBehavRenamings() != null && behavVariations.getBehavRenamings() == null)
			ris = false;
		else if (getBehavRenamings() == null && behavVariations.getBehavRenamings() != null)
			ris = false;
		else if (getBehavRenamings() == null && behavVariations.getBehavRenamings() == null)
			ris = ris && true;
		else
			ris = ris && getBehavRenamings().equals(behavVariations.getBehavRenamings());
		if (getBehavRestrictions() != null && behavVariations.getBehavRestrictions() == null)
			ris = false;
		else if (getBehavRestrictions() == null && behavVariations.getBehavRestrictions() != null)
			ris = false;
		else if (getBehavRestrictions() == null && behavVariations.getBehavRestrictions() == null)
			ris = ris && true;
		else
			ris = ris && getBehavRestrictions().equals(behavVariations.getBehavRestrictions());
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public BehavVariations copy()
		{
		BehavVariations behavVariations = new BehavVariations();
		if (getBehavHidings() != null)
			behavVariations.setBehavHidings(getBehavHidings().copy());
		if (getBehavRenamings() != null)
			behavVariations.setBehavRenamings(getBehavRenamings().copy());
		if (getBehavRestrictions() != null)
			behavVariations.setBehavRestrictions(getBehavRestrictions().copy());
		return behavVariations;
		}

	/*
	 * ["BEHAV_VARIATIONS" [<behav_hidings>] [<behav_restrictions>] [<behav_renamings>]]
	 */
	@Override
	public String toString() 
		{
		String string = "BEHAV_VARIATIONS ";
		if (getBehavHidings() != null) 
			string = string + getBehavHidings().toString();
		if (getBehavRenamings() != null)
			string = string + getBehavRenamings().toString();
		if (getBehavRestrictions() != null)
			string = string + getBehavRestrictions().toString();
		return string;
		}
	}
