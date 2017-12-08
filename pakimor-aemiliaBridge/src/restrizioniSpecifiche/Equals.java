package restrizioniSpecifiche;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import specificheAEmilia.Expression;

/**
 * Classe statica, che contiene metodi per confrontare delle
 * strutture, utilizzate nel processo di trasformazione di
 * una specifica AEmilia in una rete di code.
 *
 * @author Mirko
 *
 */
public class Equals {

	/**
	 * Restituisce true se e solo se tm1 e tm2 sono uguali.
	 *
	 * @param tm1
	 * @param tm2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	static public boolean equalsTreeMap(TreeMap tm1, TreeMap tm2)
		{
		if (tm1 == null && tm2 == null) return true;
		if (tm1 != null && tm2 == null) return false;
		if (tm1 == null && tm2 != null) return false;
		Set s1 = tm1.keySet();
		Set s2 = tm2.keySet();
		Object[] os1 = s1.toArray();
		if (s1.size() != s2.size()) return false;
		for (int i = 0; i < s1.size(); i++)
			{
			Object key = os1[i];
			if (!tm2.containsKey(key)) return false;
			if (!tm2.get(key).equals(tm1.get(key))) return false;
			}
		return true;
		}
	
	/**
	 * Restituisce true se e solo se hashMap e hashMap2 contengono gli stessi valori.
	 * 
	 * @param hashMap
	 * @param hashMap2
	 * @return
	 */
	static public boolean equalsServiceTimesMap(HashMap<String, Expression[]> hashMap, 
			HashMap<String, Expression[]> hashMap2)
		{
		if (hashMap == null && hashMap2 == null) return true;
		if (hashMap != null && hashMap2 == null) return false;
		if (hashMap == null && hashMap2 != null) return false;
		Set<String> set = hashMap.keySet();
		Set<String> set2 = hashMap2.keySet();
		String[] strings = new String[set.size()];
		set.toArray(strings);
		if (set.size() != set2.size()) return false;
		for (int i = 0; i < set.size(); i++)
			{
			String string = strings[i];
			if (!hashMap2.containsKey(string)) return false;
			// si verifica che i valori nelle due mappe per questa chiave siano uguali
			Expression[] espressiones = hashMap.get(string);
			Expression[] espressiones2 = hashMap2.get(string);
			if (espressiones.length != espressiones2.length) return false;
			// si verifica che ogni espressione sia uguale
			for (int j = 0; j < espressiones.length; j++)
				{
				Expression expression = espressiones[j];
				Expression espressione2 = espressiones2[j];
				if (!expression.equals(espressione2)) return false;
				}
			}
		return true;
		}
	
	/**
	 * Restituisce true se e solo se hashMap e hashMap2 sono uguali.
	 * 
	 * @param hashMap
	 * @param hashMap2
	 * @return
	 */
	// il seguente metodo puo' essere sostituito dal metodo equals di AbstractMap
	static public boolean equalsMaps(HashMap<?, ?> hashMap, HashMap<?, ?> hashMap2)
		{
		Set<?> set = hashMap.entrySet();
		Set<?> set2 = hashMap2.entrySet();
		if (!set.containsAll(set2)) return false;
		if (!set2.containsAll(set)) return false;
		return true;
		}
}
