/**
 * 
 */
package specificheAEmilia;

/**
 * Classe utilizzta per generalizzare le interazioni di input e output. Contiene
 * gli attributi uni, or e and utilizzati dalle classi InputInteractions e
 * OutputInteractions.
 * 
 * @author Mirko
 *
 */
public class Interactions implements AEmiliaBase {

	/**
	 * uni-interazioni di output presenti in un AET.
	 */
	private UNIinteractions uni;

	/**
	 * or-interazioni di output presenti in un AET.
	 */
	private ORinteractions or;

	/**
	 * and-interazioni di output presenti in un AET.
	 */
	private ANDinteractions and;

	public UNIinteractions getUni() {
		return uni;
	}

	public void setUni(UNIinteractions uni) {
		this.uni = uni;
	}

	public ORinteractions getOr() {
		return or;
	}

	public void setOr(ORinteractions or) {
		this.or = or;
	}

	public ANDinteractions getAnd() {
		return and;
	}

	public void setAnd(ANDinteractions and) {
		this.and = and;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see specificheAEmilia.AEmiliaBase#copy()
	 */
	@Override
	public Object copy() {
		Interactions a = new Interactions();
		if (getAnd() != null)
			a.setAnd(getAnd().copy());
		if (getOr() != null)
			a.setOr(getOr().copy());
		if (getUni() != null)
			a.setUni(getUni().copy());
		return a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see specificheAEmilia.AEmiliaBase#print()
	 */
	@Override
	public void print() {
		System.out.println("Interactions object");
		// possono non esserci uni-interazioni
		if (getUni() != null)
			getUni().print();
		// possono non esserci or-interazioni
		if (getOr() != null)
			getOr().print();
		// possono non esserci and-interazioni
		if (getAnd() != null)
			getAnd().print();
	}

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof Interactions))
			return false;
		Interactions i = (Interactions) arg0;
		boolean ris = true;
		// possono non esserci and-interazioni
		if (getAnd() != null && i.getAnd() != null)
			ris = getAnd().equals(i.getAnd());
		else if (getAnd() == null && i.getAnd() == null)
			ris = ris && true;
		else
			ris = ris && false;
		// possono non esserci or-interazioni
		if (getOr() != null && i.getOr() != null)
			ris = getOr().equals(i.getOr());
		else if (getOr() == null && i.getOr() == null)
			ris = ris && true;
		else
			ris = ris && false;
		// possono non esserci uni-interazioni
		if (getUni() != null && i.getUni() != null)
			ris = getUni().equals(i.getUni());
		else if (getUni() == null && i.getUni() == null)
			ris = ris && true;
		else
			ris = ris && false;
		return ris;
	}

	@Override
	public String toString() {
		String string = "";
		string = string + (this.uni == null ? "" : this.uni.toString() + " ");
		string = string + (this.and == null ? "" : this.and.toString() + " ");
		string = string + (this.or == null ? "" : this.or.toString());
		return string;
	}
}
