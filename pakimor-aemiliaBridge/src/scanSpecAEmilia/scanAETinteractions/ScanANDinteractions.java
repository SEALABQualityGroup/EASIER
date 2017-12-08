/**
 * 
 */
package scanSpecAEmilia.scanAETinteractions;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ANDinteractions;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanANDinteractions {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanANDinteractions(int depth) 
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
	 * and-interazioni secondo la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isANDinteractions(String specifiche)
		{
		/* MODELLED */
		/*
		 * Se none'vuota, <and_interactions> ha la seguente
		 * sintassi:
		 *
		 * "AND" <identifier_sequence>
		 *
		 * dove <identifier_sequence>e'una sequenza non vuota di
		 * identificatori di tipo azione separati da punti e
		 * virgole. Un identificatore che si presenta in azioni
		 * di input non puo' essere dichiarato una and-interaction.
		 */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\z");
		String IdSeq = new String();
		try {
			s.skip("\\s*AND\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of AND interactions";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"AND\" expected";
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
			String string = specifiche + " is not declaration of AND interactions";
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
			String string = specifiche + " is not declaration of AND interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list2 = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = scanIdSeq.getErrorMessage();
			list2.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ANDinteractions, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ANDinteractions.
	 * @throws ScanException
	 */
	public ANDinteractions scanANDinteractions(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Se none'vuota, <and_interactions> ha la seguente
			 * sintassi:
			 *
			 * "AND" <identifier_sequence>
			 *
			 * dove <identifier_sequence>e'una sequenza non vuota di
			 * identificatori di tipo azione separati da punti e
			 * virgole. Un identificatore che si presenta in azioni
			 * di input non puo' essere dichiarato una and-interaction.
			 */
			ANDinteractions ANDs = null;
			String[] IdSeq = null;
			String IdSt = new String();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*AND\\s*");
			IdSt = s.next();
			ScanIdSeq scanIdSeq = new ScanIdSeq(this.depth + 1);
			IdSeq = scanIdSeq.scanIdSeq(IdSt);
			ANDs = new ANDinteractions();
			ANDs.setActions(IdSeq);
			return ANDs;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
