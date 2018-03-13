/**
 * 
 */
package scanSpecAEmilia.scanIntestazione;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import specificheAEmilia.Header;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
public class ScanIntestELEM {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanIntestELEM(int depth) 
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
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad un'intestazione di un tipo di elemento architetturale.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isIntestELEM(String specifiche)
		{
		/*
		 * L'intestazione di un AET ha la seguente sintassi:
		 *
		 * "ELEM_TYPE" <identifier>
		 * "(" <const_formal_par_decl_sequence> ")"
		 *
		 * dove <identifier>e'il nome dell'AET e
		 * <const_formal_par_decl_sequence>e'o void o una
		 * sequenza non vuota di dichiarazioni separate da virgole
		 * di parametri formali costanti.
		 */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\(\\s*");
		// ie'l'identificatore di un tipo di elemento
		// architetturale
		String i = null;
		try {
			i = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not element type header";
			this.errorMessage.setErrorMessage(string);
			String string2 = "identifier expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\)\\s*\\z");
		try {
			s.skip("\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not element type header";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"(\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// parse'una sequenza di dichiarazioni di parametri
		// formali costanti
		String pars = null;
		try {
			pars = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not element type header";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence of constant parameters expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		try {
			s.skip("\\s*\\)\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not element type header";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\")\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		ScanConstSeq scanConstSeq = new ScanConstSeq(this.depth + 1);
		if (!scanIdent.isIdent(i))
			{
			String string = specifiche + " is not element type header";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		if (!scanConstSeq.isConstSeq(pars))
			{
			String string = specifiche + " is not element type header";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Header, includendo informazioni
	 * ottenute attraverso la scannerizzazione dell'intestazione
	 * di un tipo di elemento architetturale.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Header.
	 * @throws ScanException
	 */
	public Header scanIntestELEM(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * L'intestazione di un AET ha la seguente sintassi:
			 *
			 * "ELEM_TYPE" <identifier>
			 * "(" <const_formal_par_decl_sequence> ")"
			 *
			 * dove <identifier>e'il nome dell'AET e
			 * <const_formal_par_decl_sequence>e'o void o una
			 * sequenza non vuota di dichiarazioni separate da
			 * virgole di parametri formali costanti.
			 */
			Header i = new Header();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\(\\s*");
			// ide'l'identificatore di un tipo di elemento
			// architetturale
			String id = s.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			i.setName(scanIdent.scanIdent(id));
			s.useDelimiter("\\s*\\)\\s*\\z");
			s.skip("\\s*\\(\\s*");
			// parse'una sequenza di dichiarazioni di parametri
			// formali costanti
			String pars = s.next();
			s.skip("\\s*\\)\\s*\\z");
			ScanDeclParSeq scanDeclParSeq = new ScanDeclParSeq(this.depth + 1);
			i.setParameters(scanDeclParSeq.scanDeclParSeq(pars));
			return i;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
