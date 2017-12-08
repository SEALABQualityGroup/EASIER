package scanSpecAEmilia.scanAETbehavior;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.BehavEquation;
import utilities.ErrorMessage;


/**
 * Classe utilizzata per scannerizzare ogni parte di un
 * comportamento di un tipo di elemento architetturale, dettato
 * dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 * Il comportamento di un AET ha la seguente sintassi:
 * "BEHAVIOR" <behav_equation_sequence> dove
 * <behav_equation_sequence>e'una sequenza non vuota di equazioni
 * comportamentali EMPA (oggetti BehavEquation) separate da punti
 * e virgole. La prima equazione comportamentale nella sequenza
 * rappresenta il comportamento iniziale per l'AET. Ogni altra
 * equazione comportamentale possibile nella sequenza deve
 * descrivere un comportamento che puo' essere direttamente o
 * indirettamente invocato da quella iniziale.
 */

public class ScanAETbehavior {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanAETbehavior(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche contiene la
	 * definizione del comportamento di un tipo di elemento
	 * architetturale secondo la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isAETbehavior(String specifiche)
		{
		/* MODELLED */
		/*
		 * Il comportamento di un AET ha la seguente sintassi:
		 * "BEHAVIOR" <behav_equation_sequence>
		 */
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*BEHAVIOR\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not architectural element type behavior";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"BEHAVIOR\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\z");
		// bes indica una sequenza di equazioni comportamentali
		String bes = new String();
		try {
			bes = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not architectural element type behavior";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence of behavioral equations expected";
			ErrorMessage errorMessage3 = new ErrorMessage(this.depth + 1);
			errorMessage3.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage3);
			return false;
			}
		ScanBES scanBES = new ScanBES(this.depth + 1);
		if (!scanBES.isBES(bes))
			{
			String string = specifiche + " is not architectural element type behavior";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanBES.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto AETbehavior, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto AETbehavior.
	 * @throws ScanException
	 */
	public AETbehavior scanAETbehavior(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Il comportamento di un AET ha la seguente sintassi:
			 * "BEHAVIOR" <behav_equation_sequence>
			 */
			AETbehavior ab = new AETbehavior();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*BEHAVIOR\\s*");
			s.useDelimiter("\\s*\\z");
			// bes indica una sequenza di equazioni comportamentali
			String bes = s.next();
			ScanBES scanBES = new ScanBES(this.depth + 1);
			BehavEquation[] behavEquations = scanBES.scanBES(bes);
			ab.setBehaviors(behavEquations);
			return ab;
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
