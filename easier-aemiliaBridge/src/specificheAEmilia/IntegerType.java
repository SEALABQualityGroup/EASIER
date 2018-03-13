package specificheAEmilia;

public class IntegerType 
	extends NormalType 
	{

	@Override
	public boolean equals(Object arg0) 
		{
		if (arg0 instanceof IntegerType)
			return true;
		else return false;
		}

	@Override
	public IntegerType copy() 
		{
		return new IntegerType();
		}

	@Override
	public String toString() 
		{
		return "integer";
		}
	}
