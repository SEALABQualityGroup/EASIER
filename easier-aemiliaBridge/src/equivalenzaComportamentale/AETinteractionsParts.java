package equivalenzaComportamentale;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import specificheAEmilia.AETinteractions;
import specificheAEmilia.ANDinteractions;
import specificheAEmilia.InputInteractions;
import specificheAEmilia.ORinteractions;
import specificheAEmilia.OutputInteractions;
import specificheAEmilia.UNIinteractions;

public class AETinteractionsParts 
	{
	
	private AETinteractions tinteractions;
	
	public AETinteractionsParts(AETinteractions tinteractions) 
		{
		super();
		this.tinteractions = tinteractions;
		}

	public List<String> getNamesFromInteractions()
		{
		List<String> list = new ArrayList<String>();
		InputInteractions inputInteractions = tinteractions.getInIn();
		if (inputInteractions != null)
			{
			ANDinteractions dinteractions = inputInteractions.getAnd();
			if (dinteractions != null)
				{
				String[] strings = dinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			ORinteractions rinteractions = inputInteractions.getOr();
			if (rinteractions != null)
				{
				String[] strings = rinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			UNIinteractions iinteractions = inputInteractions.getUni();
			if (iinteractions != null)
				{
				String[] strings = iinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			}
		OutputInteractions outputInteractions = tinteractions.getOuIn();
		if (outputInteractions != null)
			{
			ANDinteractions dinteractions = outputInteractions.getAnd();
			if (dinteractions != null)
				{
				String[] strings = dinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			ORinteractions rinteractions = outputInteractions.getOr();
			if (rinteractions != null)
				{
				String[] strings = rinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			UNIinteractions iinteractions = outputInteractions.getUni();
			if (iinteractions != null)
				{
				String[] strings = iinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			}
		return list;
		}
	
	public List<String> getOrOutput()
		{
		List<String> list = new ArrayList<String>();
		OutputInteractions outputInteractions = tinteractions.getOuIn();
		if (outputInteractions != null)
			{
			ORinteractions rinteractions = outputInteractions.getOr();
			if (rinteractions != null)
				{
				String[] strings = rinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			}
		return list;
		}
	
	public List<String> getUniOutput()
		{
		List<String> list = new ArrayList<String>();
		OutputInteractions outputInteractions = tinteractions.getOuIn();
		if (outputInteractions != null)
			{
			UNIinteractions iinteractions = outputInteractions.getUni();
			if (iinteractions != null)
				{
				String[] strings = iinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			}
		return list;
		}
	
	public List<String> getOrInput()
		{
		List<String> list = new ArrayList<String>();
		InputInteractions inputInteractions = tinteractions.getInIn();
		if (inputInteractions != null)
			{
			ORinteractions rinteractions = inputInteractions.getOr();
			if (rinteractions != null)
				{
				String[] strings = rinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			}
		return list;
		}

	public List<String> getUniInput()
		{
		List<String> list = new ArrayList<String>();
		InputInteractions inputInteractions = tinteractions.getInIn();
		if (inputInteractions != null)
			{
			UNIinteractions iinteractions = inputInteractions.getUni();
			if (iinteractions != null)
				{
				String[] strings = iinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			}
		return list;
		}
	
	public List<String> getAndInput()
		{
		List<String> list = new ArrayList<String>();
		InputInteractions inputInteractions = tinteractions.getInIn();
		if (inputInteractions != null)
			{
			ANDinteractions dinteractions = inputInteractions.getAnd();
			if (dinteractions != null)
				{
				String[] strings = dinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			}
		return list;
		}
	
	public List<String> getAndOutput()
		{
		List<String> list = new ArrayList<String>();
		OutputInteractions outputInteractions = tinteractions.getOuIn();
		if (outputInteractions != null)
			{
			ANDinteractions dinteractions = outputInteractions.getAnd();
			if (dinteractions != null)
				{
				String[] strings = dinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			}
		return list;
		}
	
	public List<String> getInputInteractions()
		{
		List<String> list = new ArrayList<String>();
		InputInteractions inputInteractions = tinteractions.getInIn();
		if (inputInteractions != null)
			{
			ANDinteractions dinteractions = inputInteractions.getAnd();
			if (dinteractions != null)
				{
				String[] strings = dinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			ORinteractions rinteractions = inputInteractions.getOr();
			if (rinteractions != null)
				{
				String[] strings = rinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			UNIinteractions iinteractions = inputInteractions.getUni();
			if (iinteractions != null)
				{
				String[] strings = iinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			}
		return list;
		}
	
	public List<String> getOutputInteractions()
		{
		List<String> list = new ArrayList<String>();
		OutputInteractions outputInteractions = tinteractions.getOuIn();
		if (outputInteractions != null)
			{
			ANDinteractions dinteractions = outputInteractions.getAnd();
			if (dinteractions != null)
				{
				String[] strings = dinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			ORinteractions rinteractions = outputInteractions.getOr();
			if (rinteractions != null)
				{
				String[] strings = rinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			UNIinteractions iinteractions = outputInteractions.getUni();
			if (iinteractions != null)
				{
				String[] strings = iinteractions.getActions();
				CopyOnWriteArrayList<String> copyOnWriteArrayList =
					new CopyOnWriteArrayList<String>(strings);
				list.addAll(copyOnWriteArrayList);
				}
			}
		return list;
		}
	}
