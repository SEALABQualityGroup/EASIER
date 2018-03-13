package specificheAEmilia;

public class BooleanType 
	extends NormalType 
	{

	@Override
	public boolean equals(Object arg0) 
		{
		if (arg0 instanceof BooleanType)
			return true;
		else return false;
		}

	@Override
	public BooleanType copy() 
		{
		return new BooleanType();
		}

	@Override
	public String toString() 
		{
		return "boolean";
		}
	}
