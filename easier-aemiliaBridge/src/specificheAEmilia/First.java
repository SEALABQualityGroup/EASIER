package specificheAEmilia;

/**
 * "first" "(" <identifier> ")"
 * 
 * first restituisce il primo elemento della lista, costituita dal suo argomento.
 * 
 */
public class First 
	extends Expression 
	{

	private IdentExpr list;

	public First() 
		{
		super();
		}

	public First(IdentExpr lista) 
		{
		super();
		this.list = lista;
		}

	public IdentExpr getList() 
		{
		return list;
		}

	public void setList(IdentExpr lista) 
		{
		this.list = lista;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof First)) return false;
		First first = (First)o;
		// confronta la list
		return this.getList().equals(first.getList());
		}

	@Override
	public void print() 
		{
		System.out.println("First object");
		super.print();
		System.out.println("List:");
		getList().print();
		}

	// // "first" "(" <identifier> ")"
	@Override
	public String toString() 
		{
		String string = "first (";
		string = string + getList().toString();
		string = string + ")";
		return string;
		}

	@Override
	public First copy() 
		{
		First first = new First();
		IdentExpr list = getList().copy();
		first.setList(list);
		return first;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}
