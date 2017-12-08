/**
 * 
 */
package valutazione.typeChecking;

import genericClasses.TransformException;

/**
 * @author Mio
 *
 */
public class TypeRisingException 
	extends TransformException 
	{

	private static final long serialVersionUID = 1157570977034562756L;

	public TypeRisingException() 
		{
		super();
		}

	public TypeRisingException(String arg0, Throwable arg1) 
		{
		super(arg0, arg1);
		}

	public TypeRisingException(String arg0) 
		{
		super(arg0);
		}

	public TypeRisingException(Throwable arg0) 
		{
		super(arg0);
		}	
	}
