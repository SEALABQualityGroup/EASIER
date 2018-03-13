package specificheAEmilia;

import interfacceSpecifiche.NumberExp;
import utilities.MetodiVari;

/**
 * Rappesenta un numero reale che puo' essere presente all'interno
 * di una specifica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class Real extends NumberExp
	{

	/**
	 * valore del reale.
	 */
	// il valore di un tipo di datoe'utile per la valutazione
	// di una costante o variabile
	private double valore;

	/**
	 * Crea un oggetto Real vuoto.
	 *
	 */
	public Real() {
	}

	/**
	 * Crea un oggetto Real con valore fornito come parametro.
	 * @param valore - valore reale primitivo.
	 */
	public Real(double valore) {
		this.valore = valore;
	}

	/**
	 * Restituisce il valore.
	 * @return valore reale.
	 */
	public double getValore()
		{
		return this.valore;
		}

	/**
	 * Assegna un nuovo valore reale all'oggetto.
	 * @param valore - valore reale primitivo.
	 */
	public void setValore(double valore) {
		this.valore = valore;
	}

	/**
	 * Stampa sullo standard output le informazioni relative
	 * ad un oggetto Real.
	 */
	public void print()
		{
		System.out.println("Real object");
		super.print();
		System.out.print("Value: ");
		System.out.println(getValore());
		}

	/**
	 * Restituisce true se e solo se questo oggetto ha lo stesso
	 * valore di r.
	 * @param i - oggetto Real da confrontare.
	 * @return valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private float valore;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof Expression)) return false;
		Expression r = (Expression)o;
		try {
			boolean ris = super.equals(r);
			if (MetodiVari.isOnlyReal(r))
				ris = ris && (getValore() == ((Real)r).getValore());
			if (MetodiVari.isOnlyInteger(r))
				ris = ris && (getValore() == ((Integer)r).getValore());
			return ris;
			}
		catch (ClassCastException e)
			{
			return false;
			}
		}

	/**
	 * Copia i dati contenuti in questo oggetto.
	 * @return un reference ad una copia di questo oggetto.
	 */
	public Real copy()
		{
		Real a = new Real();
		a.setValore(getValore());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = new Double(this.valore).toString();
		return string;
		}

	@Override
	public double getNumber() 
		{
		return this.valore;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}
