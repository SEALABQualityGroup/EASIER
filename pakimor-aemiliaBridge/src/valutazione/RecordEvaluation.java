package valutazione;

import java.util.ArrayList;

/**
 * Classe utilizzata per la valutazione di un Record, cioe' un espressione 
 * RecordCons, Get o Put. Supponiamo che la chiave di ogni coppia nome campo, 
 * valore contenuta in un oggetto
 * RecordMapping sia diversa da null.
 * 
 * @author Mirko
 *
 */
public class RecordEvaluation extends ArrayList<RecordMapping>
	{

	private static final long serialVersionUID = 1L;

	/**
	 * Restituisce l'oggetto associato a campo.
	 * Restituisce null se non esiste una coppia che abbia campo come chiave.
	 * 
	 * @param campo
	 * @return
	 */
	public Object get(String campo) 
		{
		for (RecordMapping recordMapping : this)
			{
			String key = recordMapping.getField();
			if (key.equals(campo))
				return recordMapping.getValue();
			}
		return null;
		}

	/**
	 * Inserisce una coppia RecordMappng con chiave uguale a campo e valore uguale a object.
	 * Si suppone che campo sia una chiave di una qualche coppia.
	 * 
	 * @param campo
	 * @param object
	 */
	public void put(String campo, Object object) 
		{
		for (RecordMapping recordMapping : this)
			{
			String string = recordMapping.getField();
			if (string.equals(campo))
				{
				recordMapping.setValue(object);
				break;
				}	
			}
		}
	
	/**
	 * Restituisce l'indice della coppia con chiave key.
	 * Restituisce -1 se non esiste una entry con chiave key.
	 * 
	 * @param key
	 * @return
	 */
	public int indexOf(String key)
		{
		for (int i = 0; i < this.size(); i++)
			{
			RecordMapping entry = this.get(i); 
			String string = entry.getField();
			if (string.equals(key))
				return i;
			}
		return -1;
		}
	}
