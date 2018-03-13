package specificheAEmilia;

/**
 * "put" "(" <identifier> "," <expr> "," <identifier> ")"
 * 
 * put mette nel suo terzo argomento che deve essere un record, il valore del suo secondo
 * argomento nel campo il cui identificatoree'dato dal suo primo argomento, che deve
 * appartenere al terzo argomento ed essere dello stesso tipo del secondo argomento.
 * 
 * @author Mirko
 *
 */
public class Put 
	extends Expression
	{
	
	private String field;
	private Expression value;
	private IdentExpr record;
	
	public Put(String field, Expression value, IdentExpr record) 
		{
		super();
		this.field = field;
		this.value = value;
		this.record = record;
		}

	public Put() 
		{
		super();
		}

	public String getField() 
		{
		return field;
		}
	
	public void setField(String field) 
		{
		this.field = field;
		}
	
	public Expression getValue() 
		{
		return value;
		}
	
	public void setValue(Expression value) 
		{
		this.value = value;
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
	public Put copy() 
		{
		String field = new String(getField());
		Expression expression = getValue().copy();
		IdentExpr expression2 = getRecord().copy();
		Put put = new Put(field,expression,expression2);
		return put;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof Put)) return false;
		Put put = (Put)o;
		boolean ris = true;
		ris = ris && getField().equals(put.getField());
		ris = ris && getValue().equals(put.getValue());
		ris = ris && getRecord().equals(put.getRecord());
		return ris;
		}

	@Override
	public void print() 
		{
		System.out.println("Put object");
		super.print();
		// stampo il campo
		System.out.println("Field:");
		System.out.println(getField());
		// stampo il valore
		System.out.println("Value:");
		getValue().print();
		// stampo il record
		System.out.println("Record:");
		getRecord().print();
		}

	@Override
	public String toString() 
		{
		// "put" "(" <identifier> "," <expr> "," <expr> ")"
		String string = "put (";
		string = string + getField().toString();
		string = string + ",";
		string = string + getValue().toString();
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
