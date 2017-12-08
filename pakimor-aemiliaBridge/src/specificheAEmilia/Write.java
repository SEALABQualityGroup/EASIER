/**
 * 
 */
package specificheAEmilia;

/**
 * "write" "(" <expr> "," <expr> "," <identifier> ")"
 * 
 * write scrive nel suo terzo argomento, che deve essere un array con elementi a sufficienza dello stesso
 * tipo del secondo argomento, il valore rappresentato dal suo secondo argomento nella posizione il cui
 * indicee'il primo argomento, che deve essere un intero tra zero e la lunghezza dell'array rappresentato
 * dal terzo argomento decrementato di uno.
 * 
 * @author Mirko
 *
 */
public class Write 
	extends Expression 
	{
	
	private Expression index;
	private Expression element;
	private IdentExpr array;
	
	public Write(Expression index, Expression element, IdentExpr array) 
		{
		super();
		this.index = index;
		this.element = element;
		this.array = array;
		}

	public Write() 
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
	
	public Expression getElement() 
		{
		return element;
		}
	
	public void setElement(Expression element) 
		{
		this.element = element;
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
	public Write copy() 
		{
		Expression expression = getIndex().copy();
		Expression expression2 = getElement().copy();
		IdentExpr expression3 = getArray().copy();
		Write write = new Write(expression,expression2,expression3);
		return write;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof Write)) return false;
		Write write = (Write)o;
		boolean ris = true;
		ris = ris && getIndex().equals(write.getIndex());
		ris = ris && getElement().equals(write.getElement());
		ris = ris && getArray().equals(write.getArray());
		return ris;
		}

	@Override
	public void print() 
		{
		System.out.println("Write object");
		super.print();
		// stampo l'idice dell'elemento
		System.out.println("Element's index:");
		getIndex().print();
		// stampo l'elemento da scrivere
		System.out.println("Element:");
		getElement().print();
		// stampo l'array a cui appartiene l'elemento in lettura
		System.out.println("Element's array:");
		getArray().print();
		}

	@Override
	public String toString() 
		{
		// "write" "(" <expr> "," <expr> "," <expr> ")"
		String string = "write (";
		string = string + getIndex().toString();
		string = string + ",";
		string = string + getElement().toString();
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
