package equivalenzaComportamentale;

import java.util.ArrayList;
import java.util.List;

public class Liste 
	{

	/**
	 * Restituisce una lista che contiene tutti gli elementi 
	 * di list diversi da null
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> returnListNoNull(List<String> list)
		{
		if (list == null) return null;
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++)
			{
			if (list.get(i) != null)
				{
				String string = list.get(i);
				list2.add(string);
				}
			}
		return list2;
		}
	}
