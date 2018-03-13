package scanSpecAEmilia;

import genericClasses.TransformException;

public class ScanException extends TransformException {

	private static final long serialVersionUID = 1L;

	public ScanException() {
		super();
	}

	public ScanException(String message, Throwable cause) {
		super(message, cause);
	}

	public ScanException(String message) {
		super(message);
	}

	public ScanException(Throwable cause) {
		super(cause);
	}
}
