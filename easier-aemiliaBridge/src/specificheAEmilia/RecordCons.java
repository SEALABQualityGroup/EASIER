/**
 * 
 */
package specificheAEmilia;

/**
 * "record_cons" "(" <expr_sequence> ")"
 * 
 * dove <expr_sequence>e'una sequenza non vuota di espressioni separate da virgole.
 * record_cons costruisce un record composto dai valori delle espressioni presenti
 * nel suo argomento. 
 * 
 * @author Mirko
 *
 */
public class RecordCons 
	extends Expression 
	{
	
	private Expression[] values;
	
	public RecordCons() 
		{
		super();
		}

	public RecordCons(Expression[] values) 
		{
		super();
		this.values = values;
		}

	public Expression[] getValues() 
		{
		return values;
		}

	public void setValues(Expression[] values) 
		{
		this.values = values;
		}

	@Override
	public RecordCons copy() 
		{
		RecordCons recordCons = new RecordCons();
		if (getValues() != null)
			{
			// effettuo la copia in profondit� degli elementi dei campi del record
			Expression[] expressions = new Expression[getValues().length];
			for (int i = 0; i < getValues().length; i++)
				{
				expressions[i] = getValues()[i].copy();
				}
			recordCons.setValues(expressions);
			}
		return recordCons;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof RecordCons)) return false;
		RecordCons recordCons = (RecordCons)o;
		boolean ris = true;
		// confronta gli elementi
		// dei record
		if (getValues().length == recordCons.getValues().length)
			{
			for (int i = 0; i < getValues().length; i++)
				{
				ris = ris &&
					getValues()[i].equals(recordCons.getValues()[i]);
				}
			return ris;
			}
		else return false;
		}

	@Override
	public void print() 
		{
		System.out.println("RecordCons object");
		super.print();
		// stampo i campi del record
		System.out.println("Record's fields:");
		for (int i = 0; i < getValues().length; i++)
			{
			getValues()[i].print();
			}
		}

	@Override
	public String toString() 
		{
		// "record_cons" "(" <expr_sequence> ")"
		String string = "record_cons (";
		for (int i = 0; i < this.getValues().length - 1; i++)
			{
			string = string + this.getValues()[i] + ",";
			}
		string = string + this.getValues()[this.getValues().length - 1];
		string = string + ")";
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}