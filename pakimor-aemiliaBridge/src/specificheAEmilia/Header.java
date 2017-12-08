package specificheAEmilia;

/**
 * Questa classe viene utilizzata per la definizione di un'intestazione in una
 * specifica AEmilia. Un'intestazione viene utilizzata dopo la parola chiave
 * "ARCHI_TYPE", "ELEM_TYPE" e nella definizione di un'equazione
 * comportamentale. L'intestazione del tipo architetturale all'inizio di una
 * specifica AEmilia ha la seguente sintassi:
 * 
 * <pre>
 * <code>
 * "ARCHI_TYPE" &lt;identifier&gt; "(" &lt;init_const_formal_par_decl_sequence&gt; ")"
 * </code>
 * </pre>
 * 
 * dove &lt;identifier&gt;e'il nome del tipo architetturale e
 * &lt;init_const_formal_par_decl_sequence&gt;e'o void o una sequenza non vuota
 * di dichiarazioni separate da virgole di parametri formali costanti
 * inizializzati. L'intestazione di un AET ha la seguente sintassi:
 * 
 * <pre>
 * <code>
 * "ELEM_TYPE" &lt;identifier&gt; "(" &lt;const_formal_par_decl_sequence&gt; ")"
 * </code>
 * </pre>
 * 
 * dove &lt;identifier&gt;e'il nome dell'AET e
 * &lt;const_formal_par_decl_sequence&gt;e'o void o una sequenza non vuota di
 * dichiarazioni separate da virgole di parametri formali costanti.
 * L'intestazione della prima equazione comportamentale ha la seguente sintassi:
 * 
 * <pre>
 * <code>
 * &lt;identifier&gt; "(" &lt;init_var_formal_par_decl_sequence&gt; ";" &lt;local_var_decl_sequence&gt; ")"
 * </code>
 * </pre>
 * 
 * mentre l'intestazione di ogni equazione comportamentale susseguente ha la
 * seguente sintassi:
 * 
 * <pre>
 * <code>
 * &lt;identifier&gt; "(" &lt;var_formal_par_decl_sequence&gt; ";" &lt;local_var_decl_sequence&gt; ")"
 * </code>
 * </pre>
 * 
 * dove, ognuno delle precedenti sequenze puo' essere void.
 *
 * @author Mirko Email: <a href=
 *         "mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class Header implements AEmiliaBase {

	/**
	 * Nome dell'intestazione.
	 */
	private String name;

	/**
	 * Array di dichiarazioni di parametri.
	 */
	private ParamDeclaration[] parameters;

	/**
	 * Crea un oggetto Header vuoto.
	 *
	 */
	public Header() {
	}

	/**
	 * Crea un oggetto Header con nome e parametri formali forniti come parametri.
	 * 
	 * @param name
	 *            - oggetto String.
	 * @param parameters
	 *            - array di oggetti ParamDeclaration.
	 */
	public Header(String name, ParamDeclaration[] parameters) {
		this.name = name;
		this.parameters = parameters;
	}

	/**
	 * Restituisce il nome dell'intestazione.
	 * 
	 * @return un oggetto String.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Restituisce l'array di dichiarazioni di parametri dell'intestazione.
	 * 
	 * @return un array di oggetti ParamDeclaration.
	 */
	public ParamDeclaration[] getParameters() {
		return this.parameters;
	}

	/**
	 * Assegna un nome all'intestazione.
	 * 
	 * @param name
	 *            - oggetto String.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Assegna parametri all'intestazione
	 * 
	 * @param parameters
	 *            - array di oggetti ParamDeclaration.
	 */
	public void setParameters(ParamDeclaration[] parameters) {
		this.parameters = parameters;
	}

	/**
	 * Stampa sullo standard output le informazioni che caratterizzano
	 * un'intestazione.
	 *
	 */
	public void print() {
		System.out.println("Header object");
		System.out.println("Name: " + getName());
		// in un'intestazione possono non esserci parametri
		if (getParameters() != null) {
			for (int i = 0; i < getParameters().length; i++) {
				System.out.print("Parameter number ");
				System.out.println(i);
				if (getParameters()[i] != null) {
					getParameters()[i].print();
				} else {
					System.out.println("null");
				}
			}
		}
	}

	/**
	 * Restituisce true se e solo se ie'uguale a questa intestazione.
	 * 
	 * @param i
	 *            - oggetto Header.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private String name; private ParamDeclaration[] parameters;
	 */

	public boolean equals(Object o) {
		if (!(o instanceof Header))
			return false;
		Header i = (Header) o;
		boolean ris = getName().equals(i.getName());
		// in un'intestazione possono non esserci parametri
		if (getParameters() != null && i.getParameters() != null) {
			if (getParameters().length == i.getParameters().length) {
				for (int j = 0; j < getParameters().length; j++) {
					// i primi due parametri dell'intestazione possono essere null
					if (getParameters()[j] != null && i.getParameters()[j] == null)
						return false;
					if (getParameters()[j] == null && i.getParameters()[j] != null)
						return false;
					if (getParameters()[j] != null && i.getParameters()[j] != null) {
						ris = ris && getParameters()[j].equals(i.getParameters()[j]);
					}
					if (getParameters()[j] == null && i.getParameters()[j] == null)
						ris = ris && true;
				}
				return ris;
			} else
				return false;
		} else if (getParameters() == null && i.getParameters() == null)
			return ris;
		else
			return false;
	}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * 
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Header copy() {
		Header a = new Header();
		a.setName(new String(getName()));
		if (getParameters() != null) {
			a.setParameters(new ParamDeclaration[getParameters().length]);
			for (int i = 0; i < getParameters().length; i++) {
				if (getParameters()[i] != null)
					a.getParameters()[i] = getParameters()[i].copy();
			}
		}
		return a;
	}

	@Override
	public String toString() {
		String string = this.name + "(";
		if (this.parameters != null && this.parameters.length > 0) {
			for (int i = 0; i < this.parameters.length - 1; i++) {
				ParamDeclaration paramDeclaration = this.parameters[i];
				// 1) se paramDeclaratione'ConstInit siamo di fronte a
				// un'intestazione architetturale e this.Parametri rappresenta
				// una sequenza non vuota separata da virgole
				// 2) se paramDeclaratione'Const siamo di fronte a
				// un'intestazione di tipo di elemento architetturale
				// e this.Parametri rappresenta una sequenza non vuota separata
				// da virgole
				// 3) se paramDeclaratione'VarInit siamo di fronte all'
				// intestazione della prima equazione comportamentale e
				// this.Parametri rappresenta una sequenza non vuota separata da virgole.
				// In questo caso, se l'elemento successivoe'un VarInit si separa con
				// la virgola, altrimenti con un punto e virgola
				// 4) se paramDeclaratione'VariableDeclaration siamo di fronte all'intestazione
				// di un'equazione comportamentale che none'la prima e this.Parametri
				// rappresenta una sequenza non vuota separata da virgole.
				// In questo caso, se l'elemento successivoe'un VariableDeclaration si separa
				// con
				// la virgola, altrimenti con un punto e virgola
				if (paramDeclaration instanceof ConstInit) {
					string = string + paramDeclaration.toString() + ",";
				} else if (paramDeclaration instanceof Const) {
					string = string + paramDeclaration.toString() + ",";
				} else if (paramDeclaration instanceof VarInit) {
					ParamDeclaration paramDeclaration2 = this.parameters[i + 1];
					if (paramDeclaration2 instanceof VarInit) {
						string = string + paramDeclaration.toString() + ",";
					} else
					// caso in cui siamo di fronte ad un void o una sequenza di Local
					{
						string = string + paramDeclaration.toString() + ";";
					}
				} else if (paramDeclaration instanceof VariableDeclaration) {
					ParamDeclaration paramDeclaration2 = this.parameters[i + 1];
					if (paramDeclaration2 instanceof VariableDeclaration) {
						string = string + paramDeclaration.toString() + ",";
					} else
					// caso in cui siamo di fronte ad un void o una sequenza di Local
					{
						string = string + paramDeclaration.toString() + ";";
					}
				} else if (paramDeclaration instanceof Local) {
					string = string + paramDeclaration.toString() + ",";
				} else {
					// caso in cui c'� una dichiarazione di sequenza void
					string = string + "void" + ";";
				}
			}
			ParamDeclaration paramDeclaration = this.parameters[this.parameters.length - 1];
			if (paramDeclaration == null)
				string = string + "void";
			else
				string = string + paramDeclaration.toString();
		} else {
			string = string + "void";
		}
		string = string + ")";
		return string;
	}
}