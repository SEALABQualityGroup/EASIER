package specificheAEmilia;

public class PrioType 
	extends SpecialType 
	{

	@Override
	public boolean equals(Object o) 
		{
		if (o instanceof PrioType)
			return true;
		else return false;
		}

	@Override
	public PrioType copy() 
		{
		return new PrioType();
		}

	@Override
	public String toString() 
		{
		return "prio";
		}
	}
