/**
 * 
 */
package scanSpecAEmilia.scanAETinteractions;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ORinteractions;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanORinteractions {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanORinteractions(int depth) 
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
	 * or-interazioni secondo la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isORinteractions(String specifiche)
		{
		/* MODELLED */
		/*
		 * Una or-interazion di un'istanza di un AET puo' comunicare
		 * con una delle diverse interazioni di altri AEI. Se non
		 *e'vuota, <or_interactions> ha la seguente sintassi:
		 *
		 * "OR" <identifier_sequence>
		 *
		 * dove <identifier_sequence>e'una sequenza non vuota di
		 * identificatori di tipo azione separati da punti e
		 * virgole.
		 */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		String IdSeq = new String();
		try {
			s.skip("\\s*OR\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of OR interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"OR\" expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		try {
			IdSeq = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of OR interactions";
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
			String string = specifiche + " is not declaration of OR interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = scanIdSeq.getErrorMessage();
			list2.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ORinteractions, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ORinteractions.
	 * @throws ScanException
	 */
	public ORinteractions scanORinteractions(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Una or-interazion di un'istanza di un AET puo' comunicare
			 * con una delle diverse interazioni di altri AEI. Se non
			 *e'vuota, <or_interactions> ha la seguente sintassi:
			 *
			 * "OR" <identifier_sequence>
			 *
			 * dove <identifier_sequence>e'una sequenza non vuota
			 * di identificatori di tipo azione separati da punti e
			 * virgole.
			 */
			ORinteractions ORs = null;
			String[] IdSeq = null;
			String IdSt = new String();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*OR\\s*");
			IdSt = s.next();
			ScanIdSeq scanIdSeq = new ScanIdSeq(this.depth + 1);
			IdSeq = scanIdSeq.scanIdSeq(IdSt);
			ORs = new ORinteractions();
			ORs.setActions(IdSeq);
			return ORs;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
