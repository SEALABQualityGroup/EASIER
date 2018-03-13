package specificheAEmilia;

/**
 * "concat" "(" <identifier> "," <identifier> ")"
 * 
 * concat concatena i suoi argomenti, che devono essere due liste i cui elementi sono dello stesso tipo.
 * 
 * @author Mirko
 *
 */
public class Concat 
	extends Expression 
	{

	private IdentExpr list1;
	private IdentExpr list2;
	
	public Concat() 
		{
		super();
		}
	
	public Concat(IdentExpr list1, IdentExpr list2) 
		{
		super();
		this.list1 = list1;
		this.list2 = list2;
		}

	public IdentExpr getList1() 
		{
		return list1;
		}

	public void setList1(IdentExpr list1) 
		{
		this.list1 = list1;
		}

	public IdentExpr getList2() 
		{
		return list2;
		}

	public void setList2(IdentExpr list2) 
		{
		this.list2 = list2;
		}

	@Override
	public Concat copy() 
		{
		Concat concat = new Concat();
		IdentExpr list1 = getList1().copy();
		IdentExpr list2 = getList2().copy();
		concat.setList1(list1);
		concat.setList2(list2);
		return concat;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof Concat)) return false;
		Concat concat = (Concat)o;
		// confronta le liste argomento
		boolean b = this.getList1().equals(concat.getList1());
		b = b && this.getList2().equals(concat.getList2());
		return b;
		}

	@Override
	public void print() 
		{
		System.out.println("Concat object");
		super.print();
		System.out.println("List 1:");
		getList1().print();
		System.out.println("List 2:");
		getList2().print();
		}

	// "concat" "(" <expr> "," <expr> ")"
	@Override
	public String toString() 
		{
		String string = "concat (";
		string = string + getList1().toString() + ",";
		string = string + getList2().toString();
		string = string + ")";
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}

	}
