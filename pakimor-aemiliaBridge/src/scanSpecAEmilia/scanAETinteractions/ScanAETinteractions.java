package scanSpecAEmilia.scanAETinteractions;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.InputInteractions;
import specificheAEmilia.OutputInteractions;
import utilities.ErrorMessage;


/**
 * Classe utilizzata per scannerizzare ogni parte delle
 * interazioni di un tipo di elemento architetturale, dettate
 * dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
/*
 * "INPUT_INTERACTIONS" <input_interactions> "OUTPUT_INTERACTIONS" <output_interactions>
 *
 * Un'interazionee'classificata essere un'interazione di input o
 * un'interazione di output basandosi sulla sua direzione di
 * comunicazione. Poi, un'interazione di input o output e'
 * classificata essere una uni-interaction, and-interaction o
 * or-interaction dipendente dalla moltiplicità delle comunicazioni
 * in cui puo' essere coinvolta. Sintatticamente parlando, ogni
 * <input_interactions> e <output_interactions>e'o void o ha il
 * seguente formato:
 *
 * <uni_interactions> <and_interactions> <or_interactions>
 *
 * con almeno uno dei tre elementi, che rappresenta sequenze di
 * identificatori di tipo azione, essere non vuoti.
 * Una uni-interaction di un'istanza di un AET puo' comunicare solo
 * con un'interazione di un'altro AEI. Se none'vuota,
 * <uni_interactions> ha la seguente sintassi:
 *
 * "UNI" <identifier_sequence>
 *
 * dove <identifier_sequence>e'una sequenza non vuota di
 * identificatori di tipo azione separati da punti e virgole.
 * Una and-interaction di un'istanza di un AET puo' simultaneamente
 * comunicare con diverse interazioni di altri AEI (comunicazioni
 * broadcast). Se none'vuota, <and_interactions> ha la seguente
 * sintassi:
 *
 * "AND" <identifier_sequence>
 *
 * dove <identifier_sequence>e'una sequenza non vuota di
 * identificatori di tipo azione separati da punti e virgole.
 * Un identificatore che si presenta in azioni di input non puo'
 * essere dichiarato una and-interaction. Una or-interazion di
 * un'istanza di un AET puo' comunicare con una delle diverse
 * interazioni di altri AEI. Se none'vuota, <or_interactions>
 * ha la seguente sintassi:
 *
 * "OR" <identifier_sequence>
 *
 * dove <identifier_sequence>e'una sequenza non vuota di
 * identificatori di tipo azione separati da punti e virgole.
 */

public class ScanAETinteractions {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanAETinteractions(int depth) 
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
	public boolean isAETinteractions(String specifiche)
		{
		/*
		 * "INPUT_INTERACTIONS" <input_interactions>
		 * "OUTPUT_INTERACTIONS" <output_interactions>
		 *
		 * Un'interazionee'classificata essere un'interazione di
		 * input o un'interazione di output basandosi sulla sua
		 * direzione di comunicazione. Poi, un'interazione di input
		 * o outpute'classificata essere una uni-interaction,
		 * and-interaction o or-interaction dipendente dalla
		 * moltiplicità delle comunicazioni in cui puo' essere
		 * coinvolta. Sintatticamente parlando, ogni
		 * <input_interactions> e <output_interactions>e'o void
		 * o ha il seguente formato:
		 *
		 * <uni_interactions> <and_interactions> <or_interactions>
		 *
		 * con almeno uno dei tre elementi, che rappresenta sequenze
		 * di identificatori di tipo azione, essere non vuoti.
		 * Una uni-interaction di un'istanza di un AET puo'
		 * comunicare solo con un'interazione di un'altro AEI.
		 * Se none'vuota, <uni_interactions> ha la seguente
		 * sintassi:
		 *
		 * "UNI" <identifier_sequence>
		 *
		 * dove <identifier_sequence>e'una sequenza non vuota di
		 * identificatori di tipo azione separati da punti e
		 * virgole. Una and-interaction di un'istanza di un AET puo'
		 * simultaneamente comunicare con diverse interazioni di
		 * altri AEI (comunicazioni broadcast). Se none'vuota,
		 * <and_interactions> ha la seguente sintassi:
		 *
		 * "AND" <identifier_sequence>
		 *
		 * dove <identifier_sequence>e'una sequenza non vuota di
		 * identificatori di tipo azione separati da punti e
		 * virgole. Un identificatore che si presenta in azioni
		 * di input non puo' essere dichiarato una and-interaction.
		 * Una or-interazion di un'istanza di un AET puo' comunicare
		 * con una delle diverse interazioni di altri AEI. Se non
		 *e'vuota, <or_interactions> ha la seguente sintassi:
		 *
		 * "OR" <identifier_sequence>
		 *
		 * dove <identifier_sequence>e'una sequenza non vuota di
		 * identificatori di tipo azione separati da punti e
		 * virgole.
		 */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*OUTPUT_INTERACTIONS\\s*");
		try {
			s.skip("\\s*INPUT_INTERACTIONS\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural element type interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"INPUT_INTERACTIONS\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		// ii indica le interazioni di input
		String ii = null;
		try {
			ii = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural element type interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "there is not input interactions";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\z");
		try {
			s.skip("\\s*OUTPUT_INTERACTIONS\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural element type interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"OUTPUT_INTERACTIONS\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		// oi indica le interazioni di output
		String oi = null;
		try {
			oi = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural element type interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "there is not output interactions";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		ScanInputInteractions scanInputInteractions = new ScanInputInteractions(this.depth + 1);
		if (!scanInputInteractions.isInputInteractions(ii))
			{
			String string = specifiche + " is not declaration of architectural element type interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanInputInteractions.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		ScanOutputInteractions scanOutputInteractions = new ScanOutputInteractions(this.depth + 1);
		if (!scanOutputInteractions.isOutputInteractions(oi))
			{
			String string = specifiche + " is not declaration of architectural element type interactions";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanOutputInteractions.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto AETinteractions, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto AETinteractions.
	 * @throws ScanException
	 */
	public AETinteractions scanAETinteractions(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * "INPUT_INTERACTIONS" <input_interactions>
			 * "OUTPUT_INTERACTIONS" <output_interactions>
			 *
			 * Un'interazionee'classificata essere un'interazione di
			 * input o un'interazione di output basandosi sulla sua
			 * direzione di comunicazione. Poi, un'interazione di input
			 * o outpute'classificata essere una uni-interaction,
			 * and-interaction o or-interaction dipendente dalla
			 * moltiplicità delle comunicazioni in cui puo' essere
			 * coinvolta. Sintatticamente parlando, ogni
			 * <input_interactions> e <output_interactions>e'o void
			 * o ha il seguente formato:
			 *
			 * <uni_interactions> <and_interactions> <or_interactions>
			 *
			 * con almeno uno dei tre elementi, che rappresenta
			 * sequenze di identificatori di tipo azione, essere non
			 * vuoti.
			 * Una uni-interaction di un'istanza di un AET puo'
			 * comunicare solo con un'interazione di un'altro AEI.
			 * Se none'vuota, <uni_interactions> ha la seguente
			 * sintassi:
			 *
			 * "UNI" <identifier_sequence>
			 *
			 * dove <identifier_sequence>e'una sequenza non vuota di
			 * identificatori di tipo azione separati da punti e
			 * virgole. Una and-interaction di un'istanza di un AET puo'
			 * simultaneamente comunicare con diverse interazioni di
			 * altri AEI (comunicazioni broadcast). Se none'vuota,
			 * <and_interactions> ha la seguente sintassi:
			 *
			 * "AND" <identifier_sequence>
			 *
			 * dove <identifier_sequence>e'una sequenza non vuota di
			 * identificatori di tipo azione separati da punti e
			 * virgole. Un identificatore che si presenta in azioni di
			 * input non puo' essere dichiarato una and-interaction.
			 * Una or-interazion di un'istanza di un AET puo' comunicare
			 * con una delle diverse interazioni di altri AEI. Se non
			 *e'vuota, <or_interactions> ha la seguente sintassi:
			 *
			 * "OR" <identifier_sequence>
			 *
			 * dove <identifier_sequence>e'una sequenza non vuota di
			 * identificatori di tipo azione separati da punti e
			 * virgole.
			 */
			AETinteractions ai = new AETinteractions();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*OUTPUT_INTERACTIONS\\s*");
			s.skip("\\s*INPUT_INTERACTIONS\\s*");
			// ii indica le interazioni di input
			String ii = s.next();
			s.useDelimiter("\\s*\\z");
			s.skip("\\s*OUTPUT_INTERACTIONS\\s*");
			// oi indica le interazioni di output
			String oi = s.next();
			ScanInputInteractions scanInputInteractions = new ScanInputInteractions(this.depth + 1);
			ScanOutputInteractions scanOutputInteractions = new ScanOutputInteractions(this.depth + 1);
			InputInteractions inputInteractions = scanInputInteractions.scanInputInteractions(ii);
			OutputInteractions outputInteractions = scanOutputInteractions.scanOutputInteractions(oi);
			ai.setInIn(inputInteractions);
			ai.setOuIn(outputInteractions);
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