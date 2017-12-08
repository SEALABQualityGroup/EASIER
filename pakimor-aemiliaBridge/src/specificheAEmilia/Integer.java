package specificheAEmilia;

import interfacceSpecifiche.NumberExp;
import utilities.MetodiVari;

/**
 * Rappesenta un intero che puo' essere presente all'interno di una specifica
 * AEmilia.
 *
 * @author Mirko Email: <a href=
 *         "mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
public class Integer extends NumberExp {

	/**
	 * Valore dell'intero.
	 */
	// il valore di un tipo di datoe'utile per la valutazione
	// di una costante o variabile
	private int Valore;

	/**
	 * Crea un oggetto Integer vuoto.
	 *
	 */
	public Integer() {
	}

	/**
	 * Crea un oggetto Integer con valore fornito come parametro.
	 * 
	 * @param valore
	 *            - valore intero primitivo.
	 */
	public Integer(int valore) {
		this.Valore = valore;
	}

	/**
	 * Restituisce il valore.
	 * 
	 * @return valore intero.
	 */
	public int getValore() {
		return this.Valore;
	}

	/**
	 * Assegna un nuovo valore intero all'oggetto.
	 * 
	 * @param valore
	 *            - valore intero primitivo.
	 */
	public void setValore(int valore) {
		this.Valore = valore;
	}

	/**
	 * Stampa sullo standard output le informazioni relative ad un oggetto Integer.
	 */
	public void print() {
		System.out.println("Integer object");
		super.print();
		System.out.println("Value: " + java.lang.Integer.toString(getValore()));
	}

	/**
	 * Restituisce true se e solo se questo oggetto ha lo stesso valore di i.
	 * 
	 * @param i
	 *            - oggetto Integer da confrontare.
	 * @return valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private int Valore;
	 */

	public boolean equals(Object o) {
		if (!(o instanceof Expression))
			return false;
		Expression e = (Expression) o;
		try {
			boolean ris = super.equals(e);
			if (e instanceof Integer)
				ris = ris && (getValore() == ((Integer) e).getValore());
			else if (MetodiVari.isOnlyReal(e))
				ris = ris && (getValore() == ((Real) e).getValore());
			else
				ris = ris && false;
			return ris;
		} catch (ClassCastException c) {
			return false;
		}
	}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * 
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Integer copy() {
		Integer a = new Integer();
		a.setValore(getValore());
		return a;
	}

	@Override
	public String toString() {
		return new java.lang.Integer(this.Valore).toString();
	}

	@Override
	public double getNumber() {
		return getValore();
	}

	@Override
	public boolean isLiteral() {
		return true;
	}
}