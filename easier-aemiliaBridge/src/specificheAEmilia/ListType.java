/**
 * 
 */
package specificheAEmilia;

/**
 * Rappresenta una lista. In AEmilia ha la seguente sintassi:
 * <pre>
 * <code>
 * "list" "(" &lt;normal_type&gt; ")"
 * </code>
 * </pre>
 * dove &lt;normal_type&gt;e'il tipo degli elementi della lista.
 * 
 * @author Mirko
 *
 */
public class ListType 
	extends NormalType 
	{
	
	private NormalType type;
	
	public NormalType getType() 
		{
		return type;
		}

	public ListType() 
		{
		super();
		}

	public ListType(NormalType type) {
		super();
		this.type = type;
	}

	public void setType(NormalType normalType) 
		{
		this.type = normalType;
		}
	
	@Override
	public ListType copy() 
		{
		ListType listType = new ListType();
		if (getType() != null)
			listType.setType(getType().copy());
		return listType;
		}

	@Override
	public void print() 
		{
		System.out.println("ListType object");
		super.print();
		System.out.println("Normal type: ");
		getType().print();
		}

	@Override
	public boolean equals(Object arg0) 
		{
		if (!(arg0 instanceof ListType)) return false;
		ListType listType = (ListType)arg0;
		try {
			boolean ris = getType().equals(listType.getType());
			return ris;
			}
		catch (ClassCastException c)
			{
			return false;
			}
		}
	
	@Override
	public String toString() 
		{
		String string = "list (" + this.getType().toString() + ")";
		return string;
		}
	}
