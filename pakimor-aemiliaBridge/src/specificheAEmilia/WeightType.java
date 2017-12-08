package specificheAEmilia;

public class WeightType 
	extends SpecialType 
	{

	@Override
	public boolean equals(Object o) 
		{
		if (o instanceof WeightType)
			return true;
		else return false;
		}

	@Override
	public WeightType copy() 
		{
		return new WeightType();
		}

	@Override
	public String toString() 
		{
		return "weight";
		}
	}
