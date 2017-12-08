/**
 * 
 */
package valutazione.typeChecking;

import genericClasses.TransformException;

/**
 * @author Mirko
 *
 */
public class TypeCheckingException extends TransformException {

	private static final long serialVersionUID = -3490990738457817645L;

	/**
	 * 
	 */
	public TypeCheckingException() 
		{
		super();
		}

	/**
	 * @param arg0
	 */
	public TypeCheckingException(String arg0) 
		{
		super(arg0);
		}

	/**
	 * @param arg0
	 */
	public TypeCheckingException(Throwable arg0) 
		{
		super(arg0);
		}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TypeCheckingException(String arg0, Throwable arg1) 
		{
		super(arg0, arg1);
		}
	}
