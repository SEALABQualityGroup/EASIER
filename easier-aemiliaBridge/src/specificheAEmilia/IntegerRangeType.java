package specificheAEmilia;

/**
 * Rappresenta un intervallo di interi. In AEmilia, ha la
 * seguente sintassi:
 * <pre>
 * <code>
 * "integer" "(" &lt;expr&gt; ".." &lt;expr&gt; ")"
 * </code>
 * </pre>
 * denota l'insieme degli interi tra il valore della prima
 * espressione e il valore della seconda espressione.
 * Entrambe l'espressioni devono essere intere, prive di
 * identificatori non dichiarati e prive di invocazioni a
 * generatori di numeri pseudo-casuali. Inoltre, il valore
 * della prima espressione non puo' essere maggiore del valore
 * della seconda espressione.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class IntegerRangeType extends NormalType {

	/**
	 * Expression iniziale dell'intervallo.
	 */
	private Expression beginningInt;

	/**
	 * Expression finale dell'intervallo.
	 */
	private Expression endingInt;

	/**
	 * Crea un oggetto IntegerRangeType vuoto.
	 *
	 */
	public IntegerRangeType() {
	}

	/**
	 * Crea un oggetto IntegerRangeType con espressione
	 * iniziale e finale forniti come parametri.
	 * @param beginningInt - oggetto Expression.
	 * @param endingInt - oggetto Expression.
	 */
	public IntegerRangeType(Expression beginningInt, Expression endingInt) 
		{
		this.beginningInt = beginningInt;
		this.endingInt = endingInt;
		}

	/**
	 * Restituisce l'espressione iniziale dell'intervallo.
	 * @return un oggetto Expression.
	 */
	public Expression getBeginningInt()
		{
		return this.beginningInt;
		}

	/**
	 * Restituisce l'espressione finale dell'intervallo.
	 * @return un oggetto Expression.
	 */
	public Expression getEndingInt()
		{
		return this.endingInt;
		}

	/**
	 * Assegna un'espressione finale all'intervallo.
	 * @param endingInt - oggetto Expression.
	 */
	public void setEndingInt(Expression endingInt) 
		{
		this.endingInt = endingInt;
		}
	
	/**
	 * Assegna un'espressione iniziale all'intervallo.
	 * @param beginningInt - oggetto Expression.
	 */
	public void setBeginningInt(Expression beginningInt) 
		{
		this.beginningInt = beginningInt;
		}

	/**
	 * Stampa sullo standard output le informazioni relativo
	 * a questo intervallo di interi.
	 */
	public void print()
		{
		System.out.println("IntegerRangeType object");
		super.print();
		System.out.println("Top of range: ");
		getBeginningInt().print();
		System.out.println("End of range: ");
		getEndingInt().print();
		}

	/**
	 * Restituisce true se e solo se iie'uguale a questo
	 * oggetto.
	 * @param ii - oggetto IntegerRangeType.
	 * @return un valore booleano.
	 */
	/*
	 * I campi della classe da equiparare sono:
	 *
	 * private Expression beginningInt;
	 * private Expression endingInt;
	 * private int Valore;
	 */

	public boolean equals(Object o)
		{
		if (!(o instanceof IntegerRangeType)) return false;
		IntegerRangeType e = (IntegerRangeType)o;
		try {
			boolean ris = getEndingInt().equals(((IntegerRangeType)e).getEndingInt());
			ris = ris && getBeginningInt().equals(((IntegerRangeType)e).getBeginningInt());
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
	public IntegerRangeType copy()
		{
		IntegerRangeType a = new IntegerRangeType();
		if (getEndingInt() != null)
		a.setEndingInt(getEndingInt().copy());
		if (getBeginningInt() != null)
		a.setBeginningInt(getBeginningInt().copy());
		return a;
		}

	@Override
	public String toString() 
		{
		String string = "integer (" + this.beginningInt.toString() + ".." + this.endingInt.toString() + ")";
		return string;
		}
	}