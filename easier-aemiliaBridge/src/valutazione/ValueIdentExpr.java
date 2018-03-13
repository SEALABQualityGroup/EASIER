package valutazione;

import specificheAEmilia.DataType;
import specificheAEmilia.Expression;

/**
 * Viene utilizzata come valore per un identificatore presente in una specifica
 * AEmilia e contiene: un oggetto Expression che
 * rappresenta un valore per un'identificatore; un booleano valutazione che se vale false vuol dire
 * che l'identificatore non puo' essere valutato; dataType che rappresenta il tipo di dato di valore.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
public class ValueIdentExpr {

	private Expression valore;
	private boolean valutazione;
	private DataType dataType;

	public ValueIdentExpr(Expression valore, boolean valutazione, DataType dataType)
		{
		super();
		this.valore = valore;
		this.valutazione = valutazione;
		this.dataType = dataType;
		}

	public Expression getValore()
		{
		return valore;
		}

	public void setValore(Expression valore)
		{
		this.valore = valore;
		}

	public boolean isValutazione()
		{
		return valutazione;
		}

	public void setValutazione(boolean valutazione)
		{
		this.valutazione = valutazione;
		}
	
	public DataType getDataType() 
		{
		return dataType;
		}

	public void setDataType(DataType dataType) 
		{
		this.dataType = dataType;
		}

	public boolean equals(Object o)
		{
		if (!(o instanceof ValueIdentExpr)) return false;
		ValueIdentExpr vie = (ValueIdentExpr)o;
		if (getValore() != null && vie.getValore() == null) return false;
		else if (getValore() == null && vie.getValore() != null) return false;
		else if (getValore() != null && vie.getValore() != null && !getValore().equals(vie.getValore())) return false;
		if (isValutazione() != vie.isValutazione()) return false;
		if (getDataType() != null && vie.getDataType() == null) return false;
		if (getDataType() == null && vie.getDataType() != null) return false;
		if (getDataType() != null && vie.getDataType() != null && !getDataType().equals(vie.getDataType())) return false;
		return true;
		}

	@Override
	public String toString() 
		{
		String string = "{";
		string += this.valore != null ? "valore: "+this.valore.toString()+"," : "valore: null,";
		string += "valutazione: "+this.valutazione+",";
		string += this.dataType != null ? "tipo dato: "+this.dataType.toString()+"}" : "tipo dato: null}";
		return string;
		}
	}
