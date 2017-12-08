/**
 * 
 */
package valutazione.normalization.normalizeDataType;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.dataTypes.ListTypeNorm;
import restrizioniIstanze.dataTypes.NormalTypeNorm;
import specificheAEmilia.ListType;
import specificheAEmilia.NormalType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;

/**
 * @author Mirko
 *
 */
class NormalizeListType {

    private TreeMap<String, ValueIdentExpr> tm;
    private int depth;
    private ErrorMessage errorMessage;
    private boolean errorOccurred = false;

    public NormalizeListType(int depth) 
		{
		super();
		this.tm = new TreeMap<String, ValueIdentExpr>();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}
	
	public TreeMap<String, ValueIdentExpr> getTm() 
		{
		return tm;
		}
	
	public void setTm(TreeMap<String, ValueIdentExpr> tm) 
		{
		this.tm = tm;
		}
    
    /**
	 * Viene restituita la normalizzazione di dataType, effettuando
	 * typeChecking durante la valutazione.
	 * 
	 * @param dataType
	 * @return
	 * @throws NormalizeException
	 */
	public ListTypeNorm normalize(ListType dataType)
		throws NormalizeException
		{
		try {
			ListType listType = dataType.copy();
			// normalizza il tipo
			NormalType normalType = listType.getType();
			NormalizeNormalType normalizeNormalType = new NormalizeNormalType(this.depth + 1);
			normalizeNormalType.setTm(this.tm);
			NormalTypeNorm dataType2 = normalizeNormalType.normalize(normalType);
			if (normalizeNormalType.isErrorOccurred()) 
				{
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeNormalType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			listType.setType(dataType2.getNewDataType());
			ListTypeNorm listTypeNorm = new ListTypeNorm();
			listTypeNorm.setOldListType(dataType);
			listTypeNorm.setNewListType(listType);
			return listTypeNorm;
			} 
		catch (Exception e) 
			{
			throw new NormalizeException(e);
			}
		}
	
    /**
	 * Viene restituita la normalizzazione di dataType, effettuando
	 * typeChecking durante la valutazione. Utilizza uno scope tm per la valutazione,
	 * passandolo come parametro.
	 * 
	 * @param dataType
	 * @param tm
	 * @return
	 * @throws NormalizeException
	 */
	public ListTypeNorm normalize(ListType dataType, TreeMap<String, ValueIdentExpr> tm)
		throws NormalizeException
		{
		/* MODELED */
		try {
			ListType listType = dataType.copy();
			// normalizza il tipo
			NormalType normalType = listType.getType();
			NormalizeNormalType normalizeNormalType = new NormalizeNormalType(this.depth + 1);
			NormalTypeNorm dataType2 = normalizeNormalType.normalize(normalType, tm);
			if (normalizeNormalType.isErrorOccurred()) 
				{
				// 1
				String string = "Normalizing error for " + dataType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeNormalType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			listType.setType(dataType2.getNewDataType());
			ListTypeNorm listTypeNorm = new ListTypeNorm();
			listTypeNorm.setOldListType(dataType);
			listTypeNorm.setNewListType(listType);
			return listTypeNorm;
			} 
		catch (Exception e) 
			{
			throw new NormalizeException(e);
			}
		}

	public boolean isErrorOccurred() 
		{
		return this.errorOccurred;
		}

	public ErrorMessage getErrorMessage() 
		{
		return this.errorMessage;
		}

}
