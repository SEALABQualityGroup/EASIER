/**
 * 
 */
package scanSpecAEmilia.scanAETinteractions;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.UNIinteractions;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanUNIinteractions {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanUNIinteractions(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde a
	 * uni-interazioni secondo la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isUNIinteractions(String specifiche)
		{
		/* MODELLED */
		/*
		 * Una uni-interaction di un'istanza di un AET puo'
		 * comunicare solo con un'interazione di un'altro AEI.
		 * Se none'vuota, <uni_interactions> ha la seguente
		 * sintassi:
		 *
		 * "UNI" <identifier_sequence>
		 *
		 * dove <identifier_sequence>e'una sequenza non vuota di
		 * identificatori di tipo azione separati da punti e
		 * virgole.
		 */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		String IdSeq = new String();
		try {
			s.skip("\\s*UNI\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of UNI interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"UNI\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		// IdSeqe'una sequenza di identificatori di azioni
		// separate da punti e virgole
		try {
			IdSeq = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of UNI interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence of identifiers expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanIdSeq scanIdSeq = new ScanIdSeq(this.depth + 1);
		if (!scanIdSeq.isIdSeq(IdSeq))
			{
			String string = specifiche + " is not declaration of UNI interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanIdSeq.getErrorMessage();
			list2.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto UNIinteractions, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto UNIinteractions.
	 * @throws ScanException
	 */
	public UNIinteractions scanUNIinteractions(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Una uni-interaction di un'istanza di un AET puo'
			 * comunicare solo con  un'interazione di un'altro AEI.
			 * Se none'vuota, <uni_interactions> ha la seguente
			 * sintassi:
			 *
			 * "UNI" <identifier_sequence>
			 *
			 * dove <identifier_sequence>e'una sequenza non vuota di
			 * identificatori di tipo azione separati da punti e
			 * virgole.
			 */
			UNIinteractions UNIs = null;
			String[] IdSeq = null;
			String IdSt = new String();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*UNI\\s*");
			// IdSte'una sequenza di identificatori di azioni
			// separati da punti e virgola
			IdSt = s.next();
			// IdSeqe'un array di identificatori di azioni
			ScanIdSeq scanIdSeq = new ScanIdSeq(this.depth + 1);
			IdSeq = scanIdSeq.scanIdSeq(IdSt);
			UNIs = new UNIinteractions();
			UNIs.setActions(IdSeq);
			return UNIs;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
