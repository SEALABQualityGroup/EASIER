package specificheAEmilia;

/**
 * Rappresenta una dichiarazione di parametro presente all'interno
 * di una specifica AEmilia.
 * <pre>
 * <code>
 * "const" &lt;data_type&gt; &lt;identifier&gt; ":=" &lt;expr&gt;
 * </code>
 * </pre>
 *e'una dichiarazione di parametri formali costanti e
 * inizializzati utilizzata nell'intestazione della definizione
 * di un tipo architetturale.
 * <pre>
 * <code>
 * "const" &lt;data_type&gt; &lt;identifier&gt;
 * </code>
 * </pre>
 *e'una dichiarazione di parametro formale costante utilizzata
 * nell'intestazione di un AET, il cui valoree'definito nella
 * dichiarazione delle istanze dell'AET nella sezione di topologia
 * architetturale.
 * <pre>
 * <code>
 * "local" &lt;normal_type&gt; &lt;identifier&gt;
 * </code>
 * </pre>
 * viene di solito utilizzata quando si sincronizza un'azione di
 * input di un'istanza dell'AET con un'azione di output di un
 * altro AEI. Tale dichiarazione viene utilizzata
 * nell'intestazione di un'equazione comportamentale.
 * <pre>
 * <code>
 * &lt;normal_type&gt; &lt;identifier&gt; ":=" &lt;expr&gt;
 * </code>
 * </pre>
 * viene utilizzata nell'intestazione della prima equazione
 * comportamentale e consiste nella dichiarazione di un parametro
 * formale variabile inizializzata.
 * <pre>
 * <code>
 * &lt;normal_type&gt; &lt;identifier&gt;
 * </code>
 * </pre>
 * viene utilizzata nell'equazioni comportamentali susseguenti
 * la prima e consiste nella dichiarazione di un parametro
 * formale variabile. Nessuna espressione di inizializzazione
 *e'necessaria per i parametri formali variabili di ogni
 * equazione comportamentale susseguente, poich�, ad essi,
 * saranno assegnati i valori dei parametri attuali contenuti
 * nell'invocazioni dell'equazione comportamentale correlata.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

abstract public class ParamDeclaration 
	implements AEmiliaBase
	{

	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public void print(){
		System.out.println("ParamDeclaration object");
		System.out.println("Parameter name: "+getName());
		}

	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String name;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof ParamDeclaration)) return false;
		ParamDeclaration dp = (ParamDeclaration)o;
		boolean ris = true;
		// si considera anche il caso in cui non c'� il nome
		// nelle due dichiarazioni
		if (getName() != null && dp.getName() != null)
			ris = ris && getName().equals(dp.getName());
		else if (getName() == null && dp.getName() == null)
			ris = ris && true;
		else ris = ris && false;
		return ris;
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	abstract public ParamDeclaration copy();
	
	/**
	 * restituisce il tipo del parametro.
	 * 
	 * @return
	 */
	abstract public DataType getType();
	
	@Override
	public String toString() 
		{
		return this.name;
		}
	}