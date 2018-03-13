package scanSpecAEmilia.scanArchiElemInstances;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ArchiElemInstances;
import utilities.ErrorMessage;


/**
 * Classe utilizzata per scannerizzare ogni parte della
 * dichiarazione di istanze di elementi architetturali, dettata
 * dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 * Le istanze degli AET definite nella prima sezione di una
 * specifica AEmilia sono dichiarate come segue:
 *
 * "ARCHI_ELEM_INSTANCES" <AEI_decl_sequence>
 *
 * dove <AEI_decl_sequence>e'una sequenza non vuota di
 * dichiarazioni AEI separate da punti e virgole.
 */

public class ScanArchiElemInstances {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanArchiElemInstances(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde alle
	 * interazioni di un tipo di elemento architetturale secondo
	 * la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isArchiElemInstances(String specifiche)
		{
		String aas = new String();
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*ARCHI_ELEM_INSTANCES\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"ARCHI_ELEM_INSTANCES\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\z");
		// aase'una sequenza di dichiarazioni di istanze
		// di elementi architetturali separate da punti
		// e virgola
		try {
			aas = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "sequence of architectural element instance declarations expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		ScanADS scanADS = new ScanADS(this.depth + 1);
		if (!scanADS.isADS(aas))
			{
			String string = specifiche + " is not declaration of architectural element instances";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanADS.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ArchiElemInstances, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ArchiElemInstances.
	 * @throws ScanException
	 */
	public ArchiElemInstances scanArchiElemInstances(String specifiche)
		throws ScanException
		{
		try {
			ArchiElemInstances aei = new ArchiElemInstances();
			String aas = new String();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*ARCHI_ELEM_INSTANCES\\s*");
			s.useDelimiter("\\s*\\z");
			// aase'una sequenza di dichiarazioni di istanze
			// di elementi architetturali separate da punti
			// e virgola
			aas = s.next();
			ScanADS scanADS = new ScanADS(this.depth + 1);
			aei.setAEIdeclSeq(scanADS.scanADS(aas));
			return aei;
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