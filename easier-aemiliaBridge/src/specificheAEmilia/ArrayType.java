/**
 * 
 */
package specificheAEmilia;

/**
 * Rapprsenta un array. In AEmilia ha la seguente sintassi:
 * <pre>
 * <code>
 * "array" "(" &lt;expr&gt; "," &lt;normal_type&gt; ")"
 * </code>
 * </pre>
 * dove &lt;expr&gt;e'la lunghezza della lista e &lt;normal_type&gt;e'il tipo dei suoi elementi.
 * L'espressione della lunghezza dell'array deve essere valutato come un intero, privo di identificatori
 * non dichiarati, privo di invocazioni a generatori di numeri pseudo-casuali e deve essere almeno uno.
 * 
 * @author Mirko
 *
 */
public class ArrayType 
	extends NormalType
	{
	private Expression length;
	private NormalType type;
	
	public Expression getLength() 
		{
		return length;
		}
	
	public void setLength(Expression length) 
		{
		this.length = length;
		}
	
	public NormalType getType() 
		{
		return type;
		}
	
	public void setType(NormalType type) 
		{
		this.type = type;
		}
	
	@Override
	public ArrayType copy() 
		{
		ArrayType arrayType = new ArrayType();
		if (getLength() != null)
			arrayType.setLength(getLength().copy());
		if (getType() != null)
			arrayType.setType(getType().copy());
		return arrayType;
		}

	@Override
	public void print() 
		{
		System.out.println("ArrayType object");
		super.print();
		System.out.println("Length: ");
		getLength().print();
		System.out.println("Data type: ");
		getType().print();
		}

	@Override
	public boolean equals(Object arg0) 
		{
		if (!(arg0 instanceof ArrayType)) return false;
		ArrayType arrayType = (ArrayType)arg0;
		try {
			boolean ris = getLength().equals(arrayType.getLength());
			ris = ris && getType().equals(arrayType.getType());
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
		// "array" "(" &lt;expr&gt; "," &lt;normal_type&gt; ")"
		String string = "array (" + getLength().toString() + "," + getType().toString() + ")";
		return string;
		}
	}