/**
 * 
 */
package specificheAEmilia;

/**
 * "read" "(" <expr> "," <identifier> ")"
 * 
 * legge dal suo secondo argomento, che deve essere un array con elementi a sufficienza, il valore
 * dell'elemento indicizzato dal suo primo argomento, che deve essere un intero tra zero e la lunghezza
 * dell'array nel secondo argomento decrementato di uno.
 * 
 * @author Mirko
 *
 */
public class Read 
	extends Expression 
	{

	private Expression index;
	private IdentExpr array;
	
	public Read(Expression index, IdentExpr array) 
		{
		super();
		this.index = index;
		this.array = array;
		}

	public Read() 
		{
		super();
		}

	public Expression getIndex() 
		{
		return index;
		}
	
	public void setIndex(Expression index) 
		{
		this.index = index;
		}
	
	public IdentExpr getArray() 
		{
		return array;
		}
	
	public void setArray(IdentExpr array) 
		{
		this.array = array;
		}
	
	@Override
	public Read copy() 
		{
		Expression expression = getIndex().copy();
		IdentExpr expression2 = getArray().copy();
		Read read = new Read(expression,expression2);
		return read;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof Read)) return false;
		Read read = (Read)o;
		boolean ris = true;
		ris = ris && getIndex().equals(read.getIndex());
		ris = ris && getArray().equals(read.getArray());
		return ris;
		}

	@Override
	public void print() 
		{
		System.out.println("Read object");
		super.print();
		// stampo l'idice dell'elemento
		System.out.println("Element's index:");
		getIndex().print();
		// stampo l'array a cui appartiene l'elemento in lettura
		System.out.println("Element's array:");
		getArray().print();
		}

	@Override
	public String toString() 
		{
		// "read" "(" <expr> "," <identifier> ")"
		String string = "read (";
		string = string + getIndex().toString();
		string = string + ",";
		string = string + getArray().toString();
		string = string + ")";
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}
