package scanSpecAEmilia.scanArchiInteractions;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ArchiInteractions;
import utilities.ErrorMessage;


/**
 * Classe utilizzata per scannerizzare ogni parte della
 * dichiarazione di interazioni architetturali, dettata
 * dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 * Le interazioni architetturali sono dichiarati attraverso la
 * seguente sintassi:
 *
 * "ARCHI_INTERACTIONS" <pe_architectural_interaction_decl>
 *
 * dove <pe_architectural_interaction_decl>e'o void o una
 * sequenza non vuota di dichiarazioni di interazioni
 * architetturali separate da punti e virgola. Una dichiarazione
 * di interazione architetturale ha la seguente forma:
 *
 * <architectural_interaction_decl> ::= <identifier> ["[" <expr> "]"] "." <identifier>
 * | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 * <identifier> "[" <expr> "]" "." <identifier>
 */

public class ScanArchiInteractions {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanArchiInteractions(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * a dichiarazioni di interazioni architetturali secondo
	 * la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isArchiInteractions(String specifiche)
		{
		String ais = new String();
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*ARCHI_INTERACTIONS\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"ARCHI_INTERACTIONS\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\z");
		// aise'una sequenza di dichiarazioni di
		// interazioni architetturali separate da punti e
		// virgola
		try {
			ais = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "sequence of architectural interaction declarations expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		ScanPAID scanPAID = new ScanPAID(this.depth + 1);
		if (!scanPAID.isPAID(ais))
			{
			String string = specifiche + " is not declaration of architectural interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string2 = ais + " is not sequence of architectural interaction declarations";
			errorMessage.setErrorMessage(string2);
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ArchiInteractions, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ArchiInteractions.
	 * @throws ScanException
	 */
	public ArchiInteractions scanArchiInteractions(String specifiche)
		throws ScanException
		{
		try {
			ArchiInteractions ai = new ArchiInteractions();
			String ais = new String();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*ARCHI_INTERACTIONS\\s*");
			s.useDelimiter("\\s*\\z");
			// aise'una sequenza di dichiarazioni di
			// interazioni architetturali separate da punti e
			// virgola
			ais = s.next();
			ScanPAID scanPAID = new ScanPAID(this.depth + 1);
			ai.setInteractions(scanPAID.scanPAID(ais));
			return ai;
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
