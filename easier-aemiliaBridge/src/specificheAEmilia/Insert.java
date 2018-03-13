package specificheAEmilia;

/**
 * "insert" "(" <expr> "," <expr> ")"
 *
 * insert inserisce il valore del suo primo argomento nel secondo argomento, che deve essere una lista i cui
 * elementi sono dello stesso tipo del primo argomento. La posizione in cui accade l'inserimento
 *e'stabilito secondo l'ordine lessicografico degli elementi.
 * 
 * @author Mirko
 *
 */
public class Insert 
	extends Expression 
	{
	
	private Expression item;
	private IdentExpr list;
	
	public Insert() 
		{
		super();
		}

	public Insert(Expression item, IdentExpr list) 
		{
		super();
		this.item = item;
		this.list = list;
		}

	public Expression getItem() 
		{
		return item;
		}
	
	public void setItem(Expression item) 
		{
		this.item = item;
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
	public Insert copy() 
		{
		Insert insert = new Insert();
		Expression item = getItem().copy();
		IdentExpr list = getList().copy();
		insert.setItem(item);
		insert.setList(list);
		return insert;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof Insert)) return false;
		Insert insert = (Insert)o;
		// confronta l'elemento e la lista
		boolean b = this.getItem().equals(insert.getItem());
		b = b && this.getList().equals(insert.getList());
		return b;
		}

	@Override
	public void print() 
		{
		System.out.println("Insert object");
		super.print();
		System.out.println("Item:");
		getItem().print();
		System.out.println("List:");
		getList().print();
		}
	
	
	// "insert" "(" <expr> "," <expr> ")"
	@Override
	public String toString() 
		{
		String string = "insert (";
		string = string + getItem().toString() + ",";
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
