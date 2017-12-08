package specificheAEmilia;

public abstract class RateNoExp extends ActionRate 
	{

	public abstract Expression getPrio();
	public abstract Expression getWeight();
	public abstract void setWeight(Expression expression);
	public abstract void setPrio(Expression expression);
	
	}