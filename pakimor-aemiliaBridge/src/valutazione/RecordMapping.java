/**
 * 
 */
package valutazione;

/**
 * Classe utilizzata per associare il nome del campo di un record con
 * un oggetto.
 * 
 * @author Mirko
 *
 */
public class RecordMapping {

	private String field;
	private Object value;
	
	public RecordMapping(String field, Object value) 
		{
		super();
		this.field = field;
		this.value = value;
		}

	public String getField() 
		{
		return field;
		}

	public void setField(String field) 
		{
		this.field = field;
		}

	public Object getValue() 
		{
		return value;
		}
	
	public void setValue(Object value) 
		{
		this.value = value;
		}
	}
