package specificheAEmilia;

/**
 * "get" "(" <identifier> "," <identifier> ")"
 * 
 * get prende dal suo secondo argomento che deve essere un record, il valore del campo il cui
 * identificatoree'dato dal suo primo argomento, che deve appartenere al secondo argomento.
 * 
 * @author Mirko
 *
 */
public class Get 
	extends Expression 
	{
	
	private String field;
	private IdentExpr record;
	
	public Get() 
		{
		super();
		}

	public Get(String field, IdentExpr record) 
		{
		super();
		this.field = field;
		this.record = record;
		}
	
	public String getField() 
		{
		return field;
		}
	
	public void setField(String field) 
		{
		this.field = field;
		}
	
	public IdentExpr getRecord() 
		{
		return record;
		}
	
	public void setRecord(IdentExpr record) 
		{
		this.record = record;
		}
	
	@Override
	public Get copy() 
		{
		Get get = new Get();
		String string = new String(getField());
		IdentExpr expression = getRecord().copy();
		get.setField(string);
		get.setRecord(expression);
		return get;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof Get)) return false;
		Get get = (Get)o;
		boolean ris = true;
		ris = ris && getField().equals(get.getField());
		ris = ris && getRecord().equals(get.getRecord());
		return ris;
		}

	@Override
	public void print() 
		{
		System.out.println("Get object");
		super.print();
		// stampo il campo
		System.out.println("Field:");
		System.out.println(getField());
		// stampo il record
		System.out.println("Record:");
		getRecord().print();
		}

	@Override
	public String toString() 
		{
		// "get" "(" <identifier> "," <identifier> ")"
		String string = "get (";
		string = string + getField().toString();
		string = string + ",";
		string = string + getRecord().toString();
		string = string + ")";
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}