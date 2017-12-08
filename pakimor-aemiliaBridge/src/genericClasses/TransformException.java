/**
 * 
 */
package genericClasses;

/**
 * @author Mirko
 *
 */
public class TransformException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public TransformException() {
	}

	/**
	 * @param message
	 */
	public TransformException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TransformException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TransformException(String message, Throwable cause) {
		super(message, cause);
	}
}
