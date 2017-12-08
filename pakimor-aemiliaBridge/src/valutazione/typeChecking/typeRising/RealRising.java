/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.TreeMap;

import specificheAEmilia.DataType;
import specificheAEmilia.Real;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeRisingException;

/**
 * @author Mirko
 *
 */
class RealRising {

    private TreeMap<String, ValueIdentExpr> tm;
    private boolean errorOccurred = false;    
    private ErrorMessage errorMessage;

    public RealRising(int depth)
    	{
    	super();
    	this.tm = new TreeMap<String, ValueIdentExpr>();
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
	
	public boolean isErrorOccurred() 
		{
		return errorOccurred;
		}

	public DataTypeEnum risingReal(Real real, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		return DataTypeEnum.Real;
		}
	
	public DataTypeEnum risingReal(Real real, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		return DataTypeEnum.Real;
		}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	
	
}
