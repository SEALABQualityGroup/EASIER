package specificheAEmilia;

/**
 * "length" "(" <identifier> ")"
 * 
 * length computa il numero di elementi del suo primo argomento, che deve essere una lista.
 * 
 * @author Mirko
 *
 */
public class Length 
	extends Expression 
	{
	
	private IdentExpr list;

	public Length() 
		{
		super();
		}

	public Length(IdentExpr list) 
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
	public Length copy() 
		{
		Length length = new Length();
		IdentExpr list = getList().copy();
		length.setList(list);
		return length;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof Length)) return false;
		Length length = (Length)o;
		// confronta la lista
		boolean b = getList().equals(length.getList());
		return b;
		}

	@Override
	public void print() 
		{
		System.out.println("Length object");
		super.print();
		System.out.println("List:");
		getList().print();
		}

	// "length" "(" <identifier> ")"
	@Override
	public String toString() 
		{
		String string = "length (";
		string = string + getList().toString();
		string = string + ")";
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}
