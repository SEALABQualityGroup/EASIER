package specificheAEmilia;

/**
 * "remove" "(" <expr> "," <identifier> ")"
 * 
 * remove rimuove il valore dell'elemento la cui posizionee'data dal suo primo argomento, che deve essere
 * un intero non minore di uno, dal suo secondo argomento, che deve essere una lista con elementi
 * a sufficienza.
 * 
 * @author Mirko
 *
 */
public class Remove 
	extends Expression 
	{
	
	private Expression position;
	private IdentExpr list;
	
	public Remove() 
		{
		super();
		}

	public Remove(Expression position, IdentExpr list) 
		{
		super();
		this.position = position;
		this.list = list;
		}

	public Expression getPosition() 
		{
		return position;
		}

	public void setPosition(Expression position) 
		{
		this.position = position;
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
	public Remove copy() 
		{
		Remove remove = new Remove();
		Expression position = getPosition().copy();
		IdentExpr list = getList().copy();
		remove.setPosition(position);
		remove.setList(list);
		return remove;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof Remove)) return false;
		Remove remove = (Remove)o;
		// confronta la posizione e la lista
		boolean b = getPosition().equals(remove.getPosition());
		b = b && getList().equals(remove.getList());
		return b;
		}

	@Override
	public void print() 
		{
		System.out.println("Remove object");
		super.print();
		System.out.println("Position:");
		getPosition().print();
		System.out.println("List:");
		getList().print();
		}

	// "remove" "(" <expr> "," <expr> ")"
	@Override
	public String toString() 
		{
		String string = "remove (";
		string = string + getPosition().toString() + ",";
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