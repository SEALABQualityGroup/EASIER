package scanSpecAEmilia.scanArchiElemTypes;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ArchiElemTypes;
import utilities.ErrorMessage;


/**
 * Classe utilizzata per scannerizzare ogni parte della
 * dichiarazione di tipi di elementi architetturali, dettata
 * dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 * La prima sezione di una specifica AEmilia inizia con la parola
 * chiave ARCHI_ELEM_TYPES ede'composta da una sequenza non
 * vuota di definizioni AET (oggetti ElemType)
 */

public class ScanArchiElemTypes {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanArchiElemTypes(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * a dichiarazioni di tipi di elementi architetturali secondo
	 * la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isArchiElemTypes(String specifiche)
		{
		MyScanner s = new MyScanner(specifiche);
		String ets = new String();
		try {
			s.skip("\\s*ARCHI_ELEM_TYPES\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural element types";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"ARCHI_ELEM_TYPES\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\z");
		// ets indica una sequenza di dichiarazioni di tipi
		// di elementi architetturali
		try {
			ets = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural element types";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "sequence of architectural element type declarations expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		ScanETS scanETS = new ScanETS(this.depth + 1);
		if (!scanETS.isETS(ets))
			{
			String string = specifiche + " is not declaration of architectural element types";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = scanETS.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ArchiElemTypes, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ArchiElemTypes.
	 * @throws ScanException
	 */
	public ArchiElemTypes scanArchiElemTypes(String specifiche)
		throws ScanException
		{
		try {
			ArchiElemTypes aet = new ArchiElemTypes();
			MyScanner s = new MyScanner(specifiche);
			String ets = new String();
			s.skip("\\s*ARCHI_ELEM_TYPES\\s*");
			s.useDelimiter("\\s*\\z");
			// ets indica una sequenza di dichiarazioni di tipi
			// di elementi architetturali
			ets = s.next();
			ScanETS scanETS = new ScanETS(this.depth + 1);
			aet.setElementTypes(scanETS.scanETS(ets));
			return aet;
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