package specificheAEmilia;

public class RealType 
	extends NormalType 
	{

	@Override
	public boolean equals(Object arg0) 
		{
		if (arg0 instanceof RealType)
			return true;
		else return false;
		}

	@Override
	public RealType copy() 
		{
		return new RealType();
		}

	@Override
	public String toString() 
		{
		return "real";
		}
	}
