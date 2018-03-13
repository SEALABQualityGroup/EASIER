/**
 * 
 */
package specificheAEmilia;

/**
 * @author Mirko
 *
 */
public class AEIident implements AEmiliaBase {

	/**
	 * Indica il nome di un istanza di un elemento architetturale.
	 */
	private String name;

	/**
	 * Expression che seleziona un'istanza di un elemento architetturale.
	 */
	private Expression selector;

	public AEIident(String name) {
		super();
		this.name = name;
	}

	public AEIident(String name, Expression selector) {
		super();
		this.name = name;
		this.selector = selector;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Expression getSelector() {
		return selector;
	}

	public void setSelector(Expression selector) {
		this.selector = selector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public AEIident copy() {
		String name = new String(this.name);
		Expression selector = null;
		if (this.selector != null)
			selector = this.selector.copy();
		AEIident aeIident = new AEIident(name, selector);
		return aeIident;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() {
		System.out.println("AEIdent object");
		// stampa il nome dell'istanza
		System.out.println("AEI name: " + getName());
		// stampa l'eventuale selettore
		if (getSelector() != null) {
			System.out.println("Selector:");
			getSelector().print();
		}
	}

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof AEIident))
			return false;
		AEIident aei = (AEIident) arg0;
		// confronta i nomi delle istanze
		boolean ris = getName().equals(aei.getName());
		// confronta gli eventuali selettori
		if (getSelector() != null && aei.getSelector() != null)
			ris = ris && getSelector().equals(aei.getSelector());
		else if (getSelector() == null && aei.getSelector() == null)
			ris = ris && true;
		else
			ris = ris && false;
		return ris;
	}

	@Override
	public String toString() {
		String string = this.name + (this.selector == null ? "" : "[" + this.selector.toString() + "]");
		return string;
	}
}
