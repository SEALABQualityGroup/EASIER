package scanSpecAEmilia;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.scanAETbehavior.ScanAETbehavior;
import scanSpecAEmilia.scanAETinteractions.ScanAETinteractions;
import scanSpecAEmilia.scanIntestazione.ScanIntestELEM;
import scanSpecAEmilia.scanIntestazione.ScanIntestazione;
import specificheAEmilia.ElemType;
import utilities.ErrorMessage;


/**
 * Classe utilizzata per scannerizzare ogni parte della
 * dichiarazione di un tipo di elemento architetturale, dettata
 * dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 * Una definizione di un AET ha la seguente forma:
 *
 * <AET_header> <AET_behavior> <AET_interactions>
 *
 * L'intestazione di un AET ha la seguente sintassi:
 *
 * "ELEM_TYPE" <identifier> "(" <const_formal_par_decl_sequence> ")"
 *
 * dove <identifier>e'il nome dell'AET e
 * <const_formal_par_decl_sequence>e'o void o una sequenza non
 * vuota di dichiarazioni separate da virgole di parametri formali
 * costanti.
 *
 * Il comportamento di un AET ha la seguente sintassi:
 * "BEHAVIOR" <behav_equation_sequence> dove
 * <behav_equation_sequence>e'una sequenza non vuota di
 * equazioni comportamentali EMPA (oggetti BehavEquation)
 * separate da punti e virgole.
 *
 * Le interazioni AET hanno il seguente formato:
 *
 * "INPUT_INTERACTIONS" <input_interactions>
 * "OUTPUT_INTERACTIONS" <output_interactions>
 *
 * Un'interazionee'classificata essere un'interazione di input
 * o un'interazione di output basandosi sulla sua direzione di
 * comunicazione.
 * Sintatticamente parlando, ogni <input_interactions> e
 * <output_interactions>e'o void o ha il seguente formato:
 *
 * <uni_interactions> <and_interactions> <or_interactions>
 *
 * con almeno uno dei tre elementi, che rappresenta sequenze di
 * identificatori di tipo azione, essere non vuoti.
 */

public class ScanElemType {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanElemType(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad un tipo di elemento architetturale secondo la grammatica
	 * AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isElemType(String specifiche)
		{
		String intestazione = new String();
		String comportamento = new String();
		String interazioni = new String();
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*BEHAVIOR\\s*");
		try {
			s.skip("\\s*ELEM_TYPE\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of element type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"ELEM_TYPE\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		try {
			intestazione = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of element type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "header of element type expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*INPUT_INTERACTIONS\\s*");
		try {
			comportamento = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of element type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "behavior of element type expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\z");
		try {
			interazioni = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of element type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "interactions of element type expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		ScanIntestazione scanIntestazione = new ScanIntestazione(this.depth + 1);
		ScanAETbehavior scanAETbehavior = new ScanAETbehavior(this.depth + 1);
		ScanAETinteractions scanAETinteractions = new ScanAETinteractions(this.depth + 1);
		if (!scanIntestazione.isIntestazione(intestazione))
			{
			this.errorMessage.setErrorMessage(specifiche + 
					" is not declaration of element type");
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanIntestazione.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanAETbehavior.isAETbehavior(comportamento))
			{
			this.errorMessage.setErrorMessage(specifiche + 
					" is not declaration of element type");
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanAETbehavior.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanAETinteractions.isAETinteractions(interazioni))
			{
			this.errorMessage.setErrorMessage(specifiche + 
					" is not declaration of element type");
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanAETinteractions.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ElemTypes, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ElemType.
	 * @throws ScanException
	 */
	public ElemType scanElemType(String specifiche)
		throws ScanException
		{
		try {
			ElemType et = new ElemType();
			String intestazione = new String();
			String comportamento = new String();
			String interazioni = new String();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*BEHAVIOR\\s*");
			s.skip("\\s*ELEM_TYPE\\s*");
			intestazione = s.next();
			s.useDelimiter("\\s*INPUT_INTERACTIONS\\s*");
			comportamento = s.next();
			s.useDelimiter("\\s*\\z");
			interazioni = s.next();
			ScanIntestELEM scanIntestELEM = new ScanIntestELEM(this.depth + 1);
			ScanAETbehavior scanAETbehavior = new ScanAETbehavior(this.depth + 1);
			ScanAETinteractions scanAETinteractions = new ScanAETinteractions(this.depth + 1);
			et.setHeader(scanIntestELEM.scanIntestELEM(intestazione));
			et.setBehavior(scanAETbehavior.scanAETbehavior(comportamento));
			et.setInteractions(scanAETinteractions
					.scanAETinteractions(interazioni));
			return et;
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