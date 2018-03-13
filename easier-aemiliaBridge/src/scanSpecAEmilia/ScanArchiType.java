package scanSpecAEmilia;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.scanArchiElemTypes.ScanArchiElemTypes;
import scanSpecAEmilia.scanIntestazione.ScanIntestazione;
import specificheAEmilia.ArchiElemTypes;
import specificheAEmilia.ArchiTopology;
import specificheAEmilia.ArchiType;
import specificheAEmilia.Header;
import utilities.ErrorMessage;

/**
 * Classe utilizzata per scannerizzare ogni parte della
 * dichiarazione di un tipo architetturale, dettata
 * dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

/*
 * La descrizione di un tipo tipo architetturale in AEmilia e'
 * composta da tre sezioni:
 *
 * ARCHI_TYPE /name and formal parameters.
 * ARCHI_ELEM_TYPES
 * ELEM_TYPE /definition of the first architectural element type.
 * ...
 * ...
 * ELEM_TYPE /definition of the last architectural element type.
 * ARCHI_TOPOLOGY
 * ARCHI_ELEM_INSTANCES /declaration of the architectural element
 * instances.
 * ARCHI_INTERACTIONS /declaration of the architectural
 * interactions.
 * ARCHI_ATTACHMENTS /declaration of the architectural
 * attachments.
 * [BEHAV_VARIATIONS
 * [BEHAV_HIDINGS /declaration of the behavioral hidings.]
 * [BEHAV_RESTRICTIONS /declaration of the behavioral restrictions.]
 * [BEHAV_RENAMINGS /declaration of the behavioral renamings.]]
 * END
 *
 * L'intestazione del tipo architetturale all'inizio di una specifica
 * AEmilia ha la seguente sintassi:
 *
 * "ARCHI_TYPE"
 * <identifier> "(" <init_const_formal_par_decl_sequence> ")"
 *
 * dove <identifier>e'il nome del tipo architetturale e
 * <init_const_formal_par_decl_sequence>e'o void o una sequenza
 * non vuota di dichiarazioni separate da virgole di parametri
 * formali costanti inizializzati.
 */

public class ScanArchiType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanArchiType(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * alla dichiarazione di un tipo architetturale secondo
	 * la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isArchiType(String specifiche)
		{
		String intestazione = new String();
		String tipiElementi = new String();
		String topologia = new String();
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*ARCHI_ELEM_TYPES\\s*");
		try {
			s.skip("\\s*ARCHI_TYPE\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"ARCHI_TYPE\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		try {
			intestazione = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "header of architectural type expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*ARCHI_TOPOLOGY\\s*");
		try {
			tipiElementi = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "declaration of architectural element types expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*END\\s*\\z");
		try {
			topologia = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "declaration of architectural topology expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		if (topologia.matches("(.)*\\sBEHAV_VARIATIONS\\s(.)*"))
			{
			this.errorMessage = new ErrorMessage(this.depth + 1);
			this.errorMessage.setErrorMessage("Architectural type contains Behavioral Variations");
			return false;
			}
		ScanIntestazione scanIntestazione = new ScanIntestazione(this.depth + 1);
		ScanArchiElemTypes scanArchiElemTypes = new ScanArchiElemTypes(this.depth + 1);
		ScanArchiTopology scanArchiTopology = new ScanArchiTopology(this.depth + 1);
		if (!scanIntestazione.isIntestazione(intestazione))
			{
			this.errorMessage = new ErrorMessage(this.depth + 1);
			this.errorMessage.setErrorMessage(specifiche + 
					" is not architectural type");
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanIntestazione.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanArchiElemTypes.isArchiElemTypes(tipiElementi))
			{
			this.errorMessage = new ErrorMessage(this.depth + 1);
			this.errorMessage.setErrorMessage(specifiche + 
					" is not architectural type");
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanArchiElemTypes.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanArchiTopology.isArchiTopology(topologia))
			{
			this.errorMessage = new ErrorMessage(this.depth + 1);
			this.errorMessage.setErrorMessage(specifiche +
					" is not architectural type");
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanArchiTopology.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ArchiType, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ArchiType.
	 * @throws ScanException
	 */
	public ArchiType scanArchiType(String specifiche)
		throws ScanException
		{
		try {
			ArchiType at = new ArchiType();
			Header intestazioneo = new Header();
			ArchiElemTypes tipiElementio = new ArchiElemTypes();
			ArchiTopology topologiao = new ArchiTopology();
			String intestaziones = new String();
			String tipiElementis = new String();
			String topologias = new String();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*ARCHI_ELEM_TYPES\\s*");
			s.skip("\\s*ARCHI_TYPE\\s*");
			intestaziones = s.next();
			s.useDelimiter("\\s*ARCHI_TOPOLOGY\\s*");
			tipiElementis = s.next();
			s.useDelimiter("\\s*END\\s*");
			topologias = s.next();
			if (topologias.matches("(.)*\\sBEHAV_VARIATIONS\\s(.)*"))
				throw new ScanException(
						"Behavioral variations not realized");
			ScanIntestazione scanIntestazione = new ScanIntestazione(this.depth + 1);
			ScanArchiElemTypes scanArchiElemTypes = new ScanArchiElemTypes(this.depth + 1);
			ScanArchiTopology scanArchiTopology = new ScanArchiTopology(this.depth + 1);
			intestazioneo = scanIntestazione
					.scanIntestazione(intestaziones);
			tipiElementio = scanArchiElemTypes
					.scanArchiElemTypes(tipiElementis);
			topologiao = scanArchiTopology.scanArchiTopology(topologias);
			at.setArchiTypeHeader(intestazioneo);
			at.setArchiElemTypes(tipiElementio);
			at.setTopologia(topologiao);
			return at;
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
