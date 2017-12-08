package scanSpecAEmilia.scanIntestazione;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.Header;
import utilities.ErrorMessage;

/**
 * Classe utilizzata per scannerizzare ogni parte di
 * un'intestazione, secondo la grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

/*
 * Questa classe viene utilizzata per la definizione di
 * un'intestazione in una specifica AEmilia. Un'intestazione
 * viene utilizzata dopo la parola chiave "ARCHI_TYPE", "ELEM_TYPE"
 * e nella definizione di un'equazione comportamentale.
 */

public class ScanIntestazione {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanIntestazione(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifichee'una sequenza di
	 * dichiarazioni di parametri.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	/*
	public static boolean isDeclParSeq(String specifiche)
		{
		//
		if (specifiche.matches("\\s*\\,(.)*")) return false;
		if (specifiche.matches("(.)*\\,\\s*\\z")) return false;
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\,\\s*");
		try {
			String dic = new String();
			while (s.hasNext())
				{
				dic = s.next();
				if (!isDeclPar(dic)) return false;
				}
			return true;
			}
		catch (NoSuchElementException e)
			{
			return false;
			}
		}
	*/
	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad una intestazione secondo la grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isIntestazione(String specifiche)
		{
		/*
		 * L'intestazione del tipo architetturale all'inizio di
		 * una specifica AEmilia ha la seguente sintassi:
		 *
		 * "ARCHI_TYPE" <identifier>
		 * "(" <init_const_formal_par_decl_sequence> ")"
		 *
		 * dove <identifier>e'il nome del tipo architetturale e
		 * <init_const_formal_par_decl_sequence>e'o void o una
		 * sequenza non vuota di dichiarazioni separate da
		 * virgole di parametri formali costanti inizializzati.
		 *
		 * L'intestazione di un AET ha la seguente sintassi:
		 *
		 * "ELEM_TYPE" <identifier>
		 * "(" <const_formal_par_decl_sequence> ")"
		 *
		 * dove <identifier>e'il nome dell'AET e
		 * <const_formal_par_decl_sequence>e'o void o una
		 * sequenza non vuota di dichiarazioni separate da virgole
		 * di parametri formali costanti. L'intestazione della
		 * prima equazione comportamentale ha la seguente sintassi:
		 *
		 * <identifier> "(" <init_var_formal_par_decl_sequence> ";"
		 *  <local_var_decl_sequence> ")"
		 *
		 * mentre l'intestazione di ogni equazione comportamentale
		 * susseguente ha la seguente sintassi:
		 *
		 * <identifier> "(" <var_formal_par_decl_sequence> ";"
		 * <local_var_decl_sequence> ")"
		 *
		 * dove, ognuno delle precedenti sequenze puo' essere void.
		 */
		ScanIntestARCHI scanIntestARCHI = new ScanIntestARCHI(this.depth + 1);
		ScanIntestELEM scanIntestELEM = new ScanIntestELEM(this.depth + 1);
		ScanIntestBEHAV1 scanIntestBEHAV1 = new ScanIntestBEHAV1(this.depth + 1);
		ScanIntestBEHAV2 scanIntestBEHAV2 = new ScanIntestBEHAV2(this.depth + 1);
		if (!(scanIntestARCHI.isIntestARCHI(specifiche) ||
				scanIntestELEM.isIntestELEM(specifiche) ||
				scanIntestBEHAV1.isIntestBEHAV1(specifiche) ||
				scanIntestBEHAV2.isIntestBEHAV2(specifiche)))
			{
			String string = specifiche + " is not header declaration";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanIntestARCHI.getErrorMessage();
			ErrorMessage errorMessage2 = scanIntestBEHAV1.getErrorMessage();
			ErrorMessage errorMessage3 = scanIntestBEHAV2.getErrorMessage();
			ErrorMessage errorMessage4 = scanIntestELEM.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			list.add(errorMessage3);
			list.add(errorMessage4);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Header, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Header.
	 * @throws ScanException
	 */

	public Header scanIntestazione(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * L'intestazione del tipo architetturale all'inizio di
			 * una specifica AEmilia ha la seguente sintassi:
			 *
			 * "ARCHI_TYPE" <identifier>
			 * "(" <init_const_formal_par_decl_sequence> ")"
			 *
			 * dove <identifier>e'il nome del tipo architetturale e
			 * <init_const_formal_par_decl_sequence>e'o void o una
			 * sequenza non vuota di dichiarazioni separate da
			 * virgole di parametri formali costanti inizializzati.
			 *
			 * L'intestazione di un AET ha la seguente sintassi:
			 *
			 * "ELEM_TYPE" <identifier>
			 * "(" <const_formal_par_decl_sequence> ")"
			 *
			 * dove <identifier>e'il nome dell'AET e
			 * <const_formal_par_decl_sequence>e'o void o una
			 * sequenza non vuota di dichiarazioni separate da virgole
			 * di parametri formali costanti. L'intestazione della
			 * prima equazione comportamentale ha la seguente sintassi:
			 *
			 * <identifier> "(" <init_var_formal_par_decl_sequence> ";"
			 *  <local_var_decl_sequence> ")"
			 *
			 * mentre l'intestazione di ogni equazione comportamentale
			 * susseguente ha la seguente sintassi:
			 *
			 * <identifier> "(" <var_formal_par_decl_sequence> ";"
			 * <local_var_decl_sequence> ")"
			 *
			 * dove, ognuno delle precedenti sequenze puo' essere void.
			 */
			Header i = new Header();
			ScanIntestARCHI scanIntestARCHI = new ScanIntestARCHI(this.depth + 1);
			ScanIntestELEM scanIntestELEM = new ScanIntestELEM(this.depth + 1);
			ScanIntestBEHAV1 scanIntestBEHAV1 = new ScanIntestBEHAV1(this.depth + 1);
			ScanIntestBEHAV2 scanIntestBEHAV2 = new ScanIntestBEHAV2(this.depth + 1);
			if (scanIntestARCHI.isIntestARCHI(specifiche))
				i = scanIntestARCHI.scanIntestARCHI(specifiche);
			else if (scanIntestELEM.isIntestELEM(specifiche))
				i = scanIntestELEM.scanIntestELEM(specifiche);
			else if (scanIntestBEHAV1.isIntestBEHAV1(specifiche))
				i = scanIntestBEHAV1.scanIntestBEHAV1(specifiche);
			else if (scanIntestBEHAV2.isIntestBEHAV2(specifiche))
				i = scanIntestBEHAV2.scanIntestBEHAV2(specifiche);
			else
				return null;
			return i;
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