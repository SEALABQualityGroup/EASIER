/**
 * 
 */
package scanSpecAEmilia.scanAETinteractions;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.InputInteractions;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanInputInteractions {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanInputInteractions(int depth) 
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
	 * Restituisce true se e solo se specifiche corrisponde alle
	 * interazioni di input di un tipo di elemento architetturale
	 * secondo la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isInputInteractions(String specifiche)
		{
		/* MODELLED */
		/*
		 * "INPUT_INTERACTIONS" <input_interactions>
		 * Poi, un'interazione di inpute'classificata essere una
		 * uni-interaction, and-interaction o or-interaction
		 * dipendente dalla moltiplicità delle
		 * comunicazioni in cui puo' essere coinvolta.
		 * Sintatticamente parlando, ogni <output_interactions>e'o
		 * void o ha il seguente formato:
		 *
		 * <uni_interactions> <and_interactions> <or_interactions>
		 *
		 * con almeno uno dei tre elementi, che rappresenta
		 * sequenze di identificatori di tipo azione, essere non
		 * vuoti.
		 */
		MyScanner s = new MyScanner(specifiche);
		String unis = new String();
		String ands = new String();
		String ors = new String();
		// possono non esserci interazioni di input
		if (s.hasNext("\\s*void\\s*")) return true;
		s.useDelimiter("\\s*((AND)|(OR))\\s*");
		boolean founded = false;
		// possono non esserci uni-interazioni
		if (s.hasNext("\\s*UNI\\s*(.)*"))
			{
			founded = true;
			try {
				unis = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not declaration of input interactions";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "declaration of uni interactions expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;					
				}
			}
		s.useDelimiter("\\s*OR\\s*");
		// possono non esserci and-interazioni
		if (s.hasNext("\\s*AND\\s*(.)*"))
			{
			founded = true;
			try {
				ands = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not declaration of input interactions";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "there is not declaration of and interactions";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;					
				}
			}
		s.useDelimiter("\\s*\\z");
		// possono non esserci or-interazioni
		if (s.hasNext("\\s*OR\\s*(.)*"))
			{
			founded = true;
			try {
				ors = s.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not declaration of input interactions";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "there is not declaration of or interactions";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;					
				}
			}
		if (!unis.equals(""))
			{
			ScanUNIinteractions scanUNIinteractions = new ScanUNIinteractions(this.depth + 1);
			if (!scanUNIinteractions.isUNIinteractions(unis))
				{
				String string2 = specifiche + " is not declaration of input interactions";
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scanUNIinteractions.getErrorMessage();
				list2.add(errorMessage);
				return false;					
				}
			}
		if (!ands.equals(""))
			{
			ScanANDinteractions scanANDinteractions = new ScanANDinteractions(this.depth + 1);
			if (!scanANDinteractions.isANDinteractions(ands))
				{
				String string2 = specifiche + " is not declaration of output interactions";
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scanANDinteractions.getErrorMessage();
				list2.add(errorMessage);
				return false;					
				}
			}
		if (!ors.equals(""))
			{
			ScanORinteractions scanORinteractions = new ScanORinteractions(this.depth + 1);
			if (!scanORinteractions.isORinteractions(ors))
				{
				String string2 = specifiche + " is not declaration of output interactions";
				this.errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list2 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scanORinteractions.getErrorMessage();
				list2.add(errorMessage);
				return false;					
				}
			}
		// almeno una tra le sezioni tra UNI, AND e OR deve esserci
		if (!founded)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not declaration of input interactions";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "at least one of UNI, AND or OR element must be not empty";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		return true;
		}
	
	/**
	 * Crea un oggetto InputInteractions, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto InputInteractions.
	 * @throws ScanException
	 */
	public InputInteractions scanInputInteractions(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * "INPUT_INTERACTIONS" <input_interactions>
			 * Poi, un'interazione di inpute'classificata essere una uni-interaction,
			 * and-interaction o or-interaction dipendente dalla moltiplicità delle
			 * comunicazioni in cui puo' essere coinvolta.
			 * Sintatticamente parlando, ogni <output_interactions>e'o void
			 * o ha il seguente formato:
			 *
			 * <uni_interactions> <and_interactions> <or_interactions>
			 *
			 * con almeno uno dei tre elementi, che rappresenta sequenze di identificatori di tipo
			 * azione, essere non vuoti.
			 */
			InputInteractions ii = new InputInteractions();
			MyScanner s = new MyScanner(specifiche);
			String unis = new String();
			String ands = new String();
			String ors = new String();
			// void corrisponde a null
			if (s.hasNext("\\s*void\\s*"))
				return null;
			s.useDelimiter("\\s*((AND)|(OR))\\s*");
			// possono non esserci uni-interazioni
			if (s.hasNext("\\s*UNI\\s*(.)*"))
				unis = s.next();
			s.useDelimiter("\\s*OR\\s*");
			// possono non esserci and-interazioni
			if (s.hasNext("\\s*AND\\s*(.)*"))
				ands = s.next();
			s.useDelimiter("\\s*\\z");
			// possono non esserci or-interazioni
			if (s.hasNext("\\s*OR\\s*(.)*"))
				ors = s.next();
			ScanUNIinteractions scanUNIinteractions = new ScanUNIinteractions(this.depth + 1);
			ScanANDinteractions scanANDinteractions = new ScanANDinteractions(this.depth + 1);
			ScanORinteractions scanORinteractions = new ScanORinteractions(this.depth + 1);
			if (!unis.equals(""))
				ii.setUni(scanUNIinteractions.scanUNIinteractions(unis));
			if (!ands.equals(""))
				ii.setAnd(scanANDinteractions.scanANDinteractions(ands));
			if (!ors.equals(""))
				ii.setOr(scanORinteractions.scanORinteractions(ors));
			return ii;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
