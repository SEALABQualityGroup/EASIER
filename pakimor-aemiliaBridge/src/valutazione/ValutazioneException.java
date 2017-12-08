package valutazione;

import genericClasses.TransformException;

/**
 * Questa eccezione viene sollevata quando none'possibile valutare
 * un identificatore, di solito perche'e'una variabile non inizializzata o
 *e'una variabile locale appartenente ad un comportamento.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
public class ValutazioneException extends TransformException {

	private static final long serialVersionUID = 1L;

	public ValutazioneException(Throwable cause)
		{
		super(cause);
		}

	public ValutazioneException()
		{
		super();
		}

	public ValutazioneException(String message)
		{
		super(message);
		}
}
