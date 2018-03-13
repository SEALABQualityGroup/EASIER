/**
 * 
 */
package restrizioniIstanze;

import genericClasses.TransformException;

/**
 * @author Mirko
 *
 */
public class RestrizioniIstanzeException 
	extends TransformException 
	{

	private static final long serialVersionUID = 1L;

	public RestrizioniIstanzeException() 
		{
		super();
		}

	public RestrizioniIstanzeException(String message, Throwable cause) 
		{
		super(message, cause);
		}

	public RestrizioniIstanzeException(String message) 
		{
		super(message);
		}

	public RestrizioniIstanzeException(Throwable cause) 
		{
		super(cause);
		}
	}
