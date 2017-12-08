package scanSpecAEmilia.scanArchiAttac;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ArchiAttachments;
import utilities.ErrorMessage;


/**
 * Classe utilizzata per scannerizzare ogni parte delle
 * dichiarazioni di collegamenti architetturali, secondo la
 * grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 * I collegamenti architetturali sono dichiarati attraverso la
 * seguente sintassi:
 *
 * "ARCHI_ATTACHMENTS" <pe_architectural_attachment_decl>
 *
 * dove <pe_architectural_attachment_decl>e'o void o una sequenza
 * non vuota di dichiarazioni di collegamenti architetturali
 * separati da punti e virgole.
 *
 * <architectural_attachment_decl> ::=
 * "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
 * "TO" <identifier> ["[" <expr> "]"] "." <identifier>
 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * ["AND" "FOR_ALL" <identifier> "IN" <expr> ".." <expr>]
 * "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
 * "TO" <identifier> ["[" <expr> "]"] "." <identifier>
 */

public class ScanArchiAttac {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanArchiAttac(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde a
	 * dichiarazioni di collegamenti architetturali secondo la
	 * grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isArchiAttac(String specifiche)
		{
		String aas = new String();
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*ARCHI_ATTACHMENTS\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural attachments";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"ARCHI_ATTACHMENTS\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\z");
		// aas contiene la sequenza di dichiarazioni di
		// collegamenti architetturali
		try {
			aas = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural attachments";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "sequence of architectural attachment declarations expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		ScanPAAD scanPAAD = new ScanPAAD(this.depth + 1);
		if (!scanPAAD.isPAAD(aas))
			{
			String string = specifiche + " is not declaration of architectural attachments";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanPAAD.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ArchiAttachments, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ArchiAttachments.
	 * @throws ScanException
	 */
	public ArchiAttachments scanArchiAttac(String specifiche)
		throws ScanException
		{
		try {
			ArchiAttachments aa = new ArchiAttachments();
			String aas = new String();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*ARCHI_ATTACHMENTS\\s*");
			s.useDelimiter("\\s*\\z");
			// aas contiene la sequenza di dichiarazioni di
			// collegamenti architetturali
			aas = s.next();
			ScanPAAD scanPAAD = new ScanPAAD(this.depth + 1);
			aa.setAttachments(scanPAAD.scanPAAD(aas));
			return aa;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}