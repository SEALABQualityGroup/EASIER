package specificheAEmilia;

/**
 * Rappresenta un'identificatore che puo' essere presente
 * in un'espressione, all'interno di una specifica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class IdentExpr extends Expression 
	{

	/**
	 * Nome dell'identificatore
	 */
	private String Nome;

	/**
	 * Crea un oggetto IdentExpr vuoto.
	 *
	 */
	public IdentExpr() {
		}

	/**
	 * Crea un oggetto IdentExpr con nome fornito come parametro.
	 * @param nome - oggetto String.
	 */
	public IdentExpr(String nome) {
		this.Nome = nome;
	}

	/**
	 * Restituisce il nome dell'identificatore.
	 * @return oggetto String.
	 */
	public String getNome() {
		return this.Nome;
	}

	/**
	 * Assegna un nuovo nome all'identificatore.
	 * @param nome - oggetto String.
	 */
	public void setNome(String nome) {
		this.Nome = nome;
	}

	/**
	 * Stampa sullo standard output le informazioni
	 * relative a questo oggetto.
	 */
	public void print() {
		System.out.println("IdentExpr object");
		super.print();
		System.out.println("Identifier name: "+getNome());
	}

	/**
	 * Restituisce true se e solo se i due identificatori sono
	 * uguali.
	 * @param ie - oggetto IdentExpr da confrontare.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String Nome;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof Expression)) return false;
		Expression e = (Expression)o;
		try {
			boolean ris = super.equals(e);
			ris = ris && getNome().equals(((IdentExpr)e).getNome());
			return ris;
			}
		catch (ClassCastException c)
			{
			return false;
			}
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public IdentExpr copy()
		{
		IdentExpr a = new IdentExpr();
		a.setNome(new String(getNome()));
		return a;
		}

	@Override
	public String toString() 
		{		
		return this.Nome;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}