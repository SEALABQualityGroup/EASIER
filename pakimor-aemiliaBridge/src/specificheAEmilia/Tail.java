package specificheAEmilia;

/**
 * "tail" "(" <identifier> ")"
 * 
 * tail restituisce la coda del suo argomento che deve essere una lista.
 * 
 * @author Mirko
 *
 */
public class Tail 
	extends Expression 
	{
	
	private IdentExpr list;

	public Tail() 
		{
		super();
		}

	public Tail(IdentExpr list) 
		{
		super();
		this.list = list;
		}

	public IdentExpr getList() 
		{
		return list;
		}

	public void setList(IdentExpr list) 
		{
		this.list = list;
		}

	@Override
	public Tail copy() 
		{
		Tail tail = new Tail();
		IdentExpr list = getList().copy();
		tail.setList(list);
		return tail;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof Tail)) return false;
		Tail tail = (Tail)o;
		// confronta la list
		return this.getList().equals(tail.getList());
		}

	@Override
	public void print() 
		{
		System.out.println("Tail object");
		super.print();
		System.out.println("Tail:");
		getList().print();
		}

	// // "tail" "(" <expr> ")"
	@Override
	public String toString() 
		{
		String string = "tail (";
		string = string + getList().toString() + ",";
		string = string + ")";
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}
