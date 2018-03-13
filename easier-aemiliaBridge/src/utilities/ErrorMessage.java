package utilities;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessage 
	{
	private String errorMessage;
	private List<ErrorMessage> causes = new ArrayList<ErrorMessage>();
	private int depth;
	
	public ErrorMessage(int depth) 
		{
		super();
		this.depth = depth;
		}
	
	public String getErrorMessage() 
		{
		return errorMessage;
		}
	
	public void setErrorMessage(String errorMessage) 
		{
		this.errorMessage = errorMessage;
		}
	
	public List<ErrorMessage> getCauses() 
		{
		return causes;
		}
	
	public void setCauses(List<ErrorMessage> causes) 
		{
		this.causes = causes;
		}

	@Override
	public String toString() 
		{
		String string = new String();
		for (int i = 0; i < this.depth; i++)
			{
			string = string + "\t";
			}
		string = string + "Message: " + errorMessage + "\n";
		for (int i = 0; i < this.causes.size(); i++)
			{
			ErrorMessage errorMessage = this.causes.get(i);
			for (int j = 0; j < this.depth; j++)
				{
				string = string + "\t";
				}
			string = string + " cause " + (i +1) + ": \n" + 
				errorMessage.toString();
			}
		return string;
		}
	}
