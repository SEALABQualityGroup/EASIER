/**
 * 
 */
package specificheAEmilia;

/**
 * "array_cons" "(" <expr_sequence> ")"
 * 
 * dove <expr_sequence>e'una sequenza non vuota di espressioni separate da virgole.
 * Rappresenta un array composto dai valori delle espressioni rappresentate nel suo argomento,
 * che deve essere dello stesso tipo.
 * 
 * @author Mirko
 *
 */
public class ArrayCons 
	extends Expression 
	{
	
	// rappresenta un array non vuoto di elementi dell'array
	// arrayElements e sempre diverso da null
	private Expression[] arrayElements;

	public ArrayCons(Expression[] expressions) 
		{
		this.arrayElements = expressions;
		}

	public ArrayCons() 
		{
		super();
		}

	public Expression[] getArrayElements() 
		{
		return arrayElements;
		}

	public void setArrayElements(Expression[] arrayElements) 
		{
		this.arrayElements = arrayElements;
		}

	@Override
	public ArrayCons copy() 
		{
		ArrayCons arrayCons = new ArrayCons();
		if (getArrayElements() != null)
			{
			// effettuo la copia in profondit� degli elementi dell'array
			Expression[] expressions = new Expression[getArrayElements().length];
			for (int i = 0; i < getArrayElements().length; i++)
				{
				expressions[i] = getArrayElements()[i].copy();
				}
			arrayCons.setArrayElements(expressions);
			}
		return arrayCons;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof ArrayCons)) return false;
		ArrayCons arrayCons = (ArrayCons)o;
		boolean ris = true;
		// confronta gli elementi
		// degli array anche nel caso in cui non ci sono
		// elementi nei due array
		if (getArrayElements() != null && arrayCons.getArrayElements() != null)
			{
			if (getArrayElements().length == arrayCons.getArrayElements().length)
				{
				for (int i = 0; i < getArrayElements().length; i++)
					{
					ris = ris &&
					getArrayElements()[i].equals(arrayCons.getArrayElements()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getArrayElements() == null && arrayCons.getArrayElements() == null)
			return ris;
		else return false;
		}

	@Override
	public void print() 
		{
		System.out.println("ArrayCons object");
		super.print();
		// stampo gli elementi dell'array
		System.out.println("Array's elements:");
		for (int i = 0; i < getArrayElements().length; i++)
			{
			getArrayElements()[i].print();
			}
		}

	@Override
	public String toString() 
		{
		// "array_cons" "(" <expr_sequence> ")"
		String string = "array_cons (";
		for (int i = 0; i < this.getArrayElements().length - 1; i++)
			{
			string = string + this.getArrayElements()[i] + ",";
			}
		string = string + this.getArrayElements()[this.getArrayElements().length - 1];
		string = string + ")";
		return string;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}