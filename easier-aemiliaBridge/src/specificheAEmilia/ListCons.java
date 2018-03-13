package specificheAEmilia;

/**
 * "list_cons" "(" <pe_expr_sequence> ")"
 * 
 * dove <pe_expr_sequence>e'una lista possibilmente vuota di espressioni separate da virgole.
 *  
 * list_cons costruisce una lista possibilmente vuota composta dai valori dettati dalla sequenza
 * di espressioni, che devono essere dello stesso tipo.
 *  
 * @author Mirko
 *
 */
public class ListCons 
	extends Expression 
	{

	private Expression[] listElements;

	public ListCons() 
		{
		super();
		}

	public ListCons(Expression[] listElements) 
		{
		this.listElements = listElements;
		}

	public Expression[] getListElements() 
		{
		return listElements;
		}

	public void setListElements(Expression[] listElements) 
		{
		this.listElements = listElements;
		}

	@Override
	public String toString() 
		{
		// "list_cons" "(" <pe_expr_sequence> ")"
		String string = "list_cons (";
		for (int i = 0; i < this.getListElements().length - 1; i++)
			{
			string = string + this.getListElements()[i] + ",";
			}
		string = string + this.getListElements()[this.getListElements().length - 1];
		string = string + ")";
		return string;
		}

	@Override
	public void print() 
		{
		System.out.println("ListCons object");
		super.print();
		// stampo gli elementi della lista
		System.out.println("List's elements:");
		for (int i = 0; i < getListElements().length; i++)
			{
			getListElements()[i].print();
			}
		}

	@Override
	public ListCons copy() 
		{
		ListCons listCons = new ListCons();
		if (getListElements() != null)
			{
			// effettuo la copia in profondit� degli elementi della lista
			Expression[] expressions = new Expression[getListElements().length];
			for (int i = 0; i < getListElements().length; i++)
				{
				expressions[i] = getListElements()[i].copy();
				}
			listCons.setListElements(expressions);
			}
		return listCons;
		}

	@Override
	public boolean equals(Object o) 
		{
		if (!(o instanceof ListCons)) return false;
		ListCons listCons = (ListCons)o;
		boolean ris = true;
		// confronta gli elementi
		// delle due liste anche nel caso in cui non ci sono
		// elementi nelle due liste
		if (getListElements() != null && listCons.getListElements() != null)
			{
			if (getListElements().length == listCons.getListElements().length)
				{
				for (int i = 0; i < getListElements().length; i++)
					{
					ris = ris &&
					getListElements()[i].equals(listCons.getListElements()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getListElements() == null && listCons.getListElements() == null)
			return ris;
		else return false;
		}

	@Override
	public boolean isLiteral() 
		{
		return true;
		}
	}