package specificheAEmilia;

public class RateType 
	extends SpecialType 
	{

	@Override
	public boolean equals(Object o) 
		{
		if (o instanceof RateType)
			return true;
		else return false;
		}

	@Override
	public RateType copy() 
		{
		return new RateType();
		}

	@Override
	public String toString() 
		{
		return "rate";
		}
	}
