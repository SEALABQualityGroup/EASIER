/**
 * 
 */
package scanSpecAEmilia.scanProcessTerm;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanProcTerm2Seq {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanProcTerm2Seq(int depth) 
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
	 * Restituisce true se e solo se specifichee'una sequenza di almeno due elementi di
	 * dichiarazioni di termini di processi eventalmente
	 * condizionati da un'espressione, separate da virgole.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isProcTerm2Seq(String specifiche)
		{
		/* MODELLED */
		if (specifiche.matches("\\s*\\,(.)*")) 
			{
			String string = specifiche + " is not sequence of process term";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence begin with comma";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (specifiche.matches("(.)*\\,\\s*\\z")) 
			{
			String string = specifiche + " is not sequence of process term";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence end with comma";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\,\\s*");
		String pt = new String();
		int c = 0;
		while (s.hasNext())
			{
			pt = s.next();
			ScanProcTerm2 scanProcTerm2 = new ScanProcTerm2(this.depth + 1);
			while (!scanProcTerm2.isProcTerm2(pt))
				{
				if (s.hasNext())
					{
					pt = pt + "," + s.next();
					}
				else 
					{
					String string = specifiche + " is not sequence of process term";
					this.errorMessage.setErrorMessage(string);
					ErrorMessage errorMessage = scanProcTerm2.getErrorMessage();
					List<ErrorMessage> list = this.errorMessage.getCauses();
					list.add(errorMessage);
					return false;
					}
				}
			c++;
			}
		if (c < 2) 
			{
			String string = specifiche + " is not sequence of process term";
			this.errorMessage.setErrorMessage(string);
			String string2 = "at least two process term expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Scannerizza una sequenza di dichiarazioni di termini di
	 * processo eventualmente condizionati da un'espressione,
	 * generando un array di oggetti ProcessTerm.
	 *
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti ProcessTerm.
	 * @throws ScanException
	 */
	public ProcessTerm[] scanProcTerm2Seq(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * <process_term_2_sequence>e'una sequenza di almeno due termini di processo separati
			 * da virgole, ognuna possibilmente preceduta da un'espressione booleana che stabilisce
			 * la condizione sotto cuie'disponibile.
			 */
			ProcessTerm[] pta = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\,\\s*");
			String pt = new String();
			int contpt = 0;
			while (s.hasNext()) {
				pt = s.next();
				ScanProcTerm2 scanProcTerm2 = new ScanProcTerm2(this.depth + 1);
				while (!scanProcTerm2.isProcTerm2(pt)) {
					pt = pt + "," + s.next();
				}
				contpt++;
			}
			pta = new ProcessTerm[contpt];
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\,\\s*");
			for (int i = 0; i < contpt; i++) {
				pt = s.next();
				ScanProcTerm2 scanProcTerm2 = new ScanProcTerm2(this.depth + 1);
				while (!scanProcTerm2.isProcTerm2(pt)) {
					pt = pt + "," + s.next();
				}
				pta[i] = scanProcTerm2.scanProcTerm2(pt);
			}
			return pta;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
