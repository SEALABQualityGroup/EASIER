package restrizioniSpecifiche;

import specificheAEmilia.AEIident;

/**
 * Un'oggetto Interaction rappresenta un'interazione, data dal nome dell'istanza di un tipo di elemento 
 * architetturale e dal nome dell'interazione.
 * 
 * @author Mirko
 *
 */
public class Interaction 
	{
	private AEIident Instance;
	private String Action;
	
	public Interaction(AEIident instance, String action) 
		{
		super();
		Instance = instance;
		Action = action;
		}

	public AEIident getInstance() 
		{
		return Instance;
		}
	
	public void setInstance(AEIident instance) 
		{
		Instance = instance;
		}
	
	public String getAction() 
		{
		return Action;
		}
	
	public void setAction(String action) 
		{
		Action = action;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof Interaction)) return false;
		Interaction interaction = (Interaction)obj;
		String string = interaction.getAction();
		AEIident aeIident = interaction.getInstance();
		if (!this.getAction().equals(string)) return false;
		if (!this.getInstance().equals(aeIident)) return false;
		return true;
		}

	@Override
	public String toString() 
		{
		return this.Instance + "." + this.Action;
		}
	}