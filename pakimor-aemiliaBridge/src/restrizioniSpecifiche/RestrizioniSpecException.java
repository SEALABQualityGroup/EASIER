package restrizioniSpecifiche;

import genericClasses.TransformException;

public class RestrizioniSpecException extends TransformException {

	private static final long serialVersionUID = 1L;

	public RestrizioniSpecException()
		{
		super();
		}

	public RestrizioniSpecException(String message, Throwable cause)
		{
		super(message, cause);
		}

	public RestrizioniSpecException(String message)
		{
		super(message);
		}

	public RestrizioniSpecException(Throwable cause)
		{
		super(cause);
		}
}
